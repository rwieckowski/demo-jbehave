package pl.rwieckowski.demo.jbehave;

import java.util.List;
import java.util.stream.Collectors;

public class ViewShoppingListsUseCase {
    public List<ShoppingListSummary> viewShoppingLists(User user) {
        return Context.accessRepository.findAccessesFor(user).stream()
                .map(Access::getShoppingList)
                .filter(sl -> !sl.isArchived())
                .sorted((sl1, sl2) -> sl2.getCreateDate().compareTo(sl1.getCreateDate()))
                .map(this::makeSummary)
                .collect(Collectors.toList());
    }

    private ShoppingListSummary makeSummary(ShoppingList shoppingList) {
        ShoppingListSummary summary = new ShoppingListSummary();
        summary.title = shoppingList.getTitle();
        return summary;
    }
}
