package doubles;

import pl.rwieckowski.demo.jbehave.ShoppingList;

public class MemoryShoppingListRepository implements pl.rwieckowski.demo.jbehave.ShoppingListRepository {
    @Override
    public ShoppingList save(ShoppingList entity) {
        return entity;
    }
}
