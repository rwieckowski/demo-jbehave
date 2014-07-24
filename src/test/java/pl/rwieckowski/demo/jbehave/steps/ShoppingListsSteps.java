package pl.rwieckowski.demo.jbehave.steps;

import doubles.MemoryAccessRepository;
import doubles.MemoryShoppingListRepository;
import doubles.MemoryUserRepository;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import pl.rwieckowski.demo.jbehave.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ShoppingListsSteps {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private ViewShoppingListsUseCase useCase;
    private List<ShoppingListSummary> shoppingLists;

    @BeforeScenario
    public void initContext() {
        Context.userRepository = new MemoryUserRepository();
        Context.shoppingListRepository = new MemoryShoppingListRepository();
        Context.accessRepository = new MemoryAccessRepository();
        useCase = new ViewShoppingListsUseCase();
    }

    @Given("existing user $username")
    public void addUser(String username) {
        User user = new User(username);
        Context.userRepository.save(user);
        assertThat(Context.userRepository.findUserByName(username), is(notNullValue()));
    }

    @Given("user $username is logged in")
    public void userIsLoggedIn(String username) {
        User loggedInUser = Context.userRepository.findUserByName(username);
        Session.setLoggedInUser(loggedInUser);
        assertThat(Session.getLoggedInUser().getUserName(), is(equalTo(username)));
    }

    @Given("user $username has no shopping lists")
    public void userHasNoShoppingLists(String username) {
        User user = Context.userRepository.findUserByName(username);
        assertThat(Context.accessRepository.findAccessesFor(user), empty());
    }

    @Given("user $username has following shopping lists: $shoppingLists")
    public void userHasShoppingLists(String username, ExamplesTable shoppingLists) {
        User user = Context.userRepository.findUserByName(username);

        shoppingLists.getRows().stream()
                .map(this::makeShoppingList)
                .forEach(sl -> {
                    Context.shoppingListRepository.save(sl);
                    Access access = new Access(user, sl);
                    Context.accessRepository.save(access);
                });
    }

    private ShoppingList makeShoppingList(Map<String, String> row) {
        try {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setTitle(row.get("title"));
            shoppingList.setCreateDate(dateFormat.parse(row.get("createDate")));
            shoppingList.setArchived("+".equals(row.get("archived")));
            return shoppingList;
        } catch (ParseException e) {
            throw new RuntimeException("Shopping list parse error", e);
        }
    }

    @When("user $user views shopping lists")
    public void userViewsShoppingLists(String user) {
        assertThat(Session.getLoggedInUser(), is(notNullValue()));
        shoppingLists = useCase.viewShoppingLists(Session.getLoggedInUser());
        assertThat(shoppingLists, is(notNullValue()));
    }

    @Then("results are empty")
    public void resultsAreEmpty() {
        assertThat(shoppingLists, is(empty()));
    }

    @Then("results contains: $shoppingLists")
    public void resultsContains(ExamplesTable shoppingLists) {
        assertThat(this.shoppingLists.stream().map(this::makeRow).collect(Collectors.toList()),
                is(equalTo(shoppingLists.getRows())));
    }

    private Map<String, String> makeRow(ShoppingListSummary summary) {
        return new HashMap<String, String>() {{
            put("title", summary.title);
        }};
    }
}
