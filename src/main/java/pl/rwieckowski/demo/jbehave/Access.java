package pl.rwieckowski.demo.jbehave;

public class Access {
    private final User user;
    private final ShoppingList shoppingList;

    public Access(User user, ShoppingList shoppingList) {
        this.user = user;
        this.shoppingList = shoppingList;
    }

    public User getUser() {
        return user;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }
}
