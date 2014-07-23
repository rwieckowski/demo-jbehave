package pl.rwieckowski.demo.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import static org.junit.Assert.fail;

public class ShoppingListsSteps {
    @Given("user $user is logged in")
    public void userIsLoggedIn(String user) {
        fail("not implemented");
    }

    @Given("user $user has no shopping lists")
    public void userHasNoShoppingLists(String user) {
        fail("not implemented");
    }

    @Given("user $user has following shopping lists: $shoppingLists")
    public void userHasShoppingLists(String user, ExamplesTable shoppingLists) {
        fail("not implemented");
    }

    @When("user $user views shopping lists")
    public void userViewsShoppingLists(String user) {
        fail("not implemented");
    }

    @Then("results are empty")
    public void resultsAreEmpty() {
        fail("not implemented");
    }

    @Then("results contains: $shoppingLists")
    public void resultsContains(ExamplesTable shoppingLists) {
        fail("not implemented");
    }
}
