package pl.rwieckowski.demo.jbehave.steps;

import doubles.MemoryAccessRepository;
import doubles.MemoryShoppingListRepository;
import doubles.MemoryUserRepository;
import org.hamcrest.Matcher;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import pl.rwieckowski.demo.jbehave.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ShoppingListsSteps {
    private ViewShoppingListsUseCase useCase;
    private List<ShoppingListSummary> shoppingLists;

    @BeforeScenario
    public void initContext() {
        Context.userRepository = new MemoryUserRepository();
        Context.shoppingListRepository = new MemoryShoppingListRepository();
        Context.accessRepository = new MemoryAccessRepository();
        useCase = new ViewShoppingListsUseCase();
    }

    @Given("existing user $userName")
    public void addUser(String userName) {
        User user = new User(userName);
        Context.userRepository.save(user);
    }

    @Given("user $userName is logged in")
    public void userIsLoggedIn(String userName) {
        User loggedInUser = Context.userRepository.findUserByName(userName);
        Session.setLoggedInUser(loggedInUser);
    }

    @Given("user $user has no shopping lists")
    public void userHasNoShoppingLists(String user) {
    }

    @Given("user $userName has following shopping lists: $shoppingLists")
    public void userHasShoppingLists(String userName, ExamplesTable shoppingLists) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        User user = Context.userRepository.findUserByName(userName);

        for (Map<String, String> row : shoppingLists.getRows()) {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setTitle(row.get("title"));
            shoppingList.setCreateDate(df.parse(row.get("createDate")));
            shoppingList.setArchived("+".equals(row.get("archived")));
            Context.shoppingListRepository.save(shoppingList);

            Access access = new Access(user, shoppingList);
            Context.accessRepository.save(access);
        }
    }

    @When("user $user views shopping lists")
    public void userViewsShoppingLists(String user) {
        assertThat(Session.getLoggedInUser(), is(notNullValue()));
        shoppingLists = useCase.viewShoppingLists(Session.getLoggedInUser());
    }

    @Then("results are empty")
    public void resultsAreEmpty() {
        assertThat(shoppingLists, is(empty()));
    }

    @Then("results contains: $shoppingLists")
    public void resultsContains(ExamplesTable shoppingLists) {
        List<Matcher<? super ShoppingListSummary>> matchers = new ArrayList<>();
        for (Map<String, String> row : shoppingLists.getRows()) {
            ShoppingListSummary sls = new ShoppingListSummary();
            sls.title = row.get("title");
            matchers.add(equalTo(sls));
        }

        assertThat(this.shoppingLists.toString(), this.shoppingLists, contains(matchers));
    }
}
