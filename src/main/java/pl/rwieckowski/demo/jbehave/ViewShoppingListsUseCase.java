package pl.rwieckowski.demo.jbehave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ViewShoppingListsUseCase {
    public List<ShoppingListSummary> viewShoppingLists(User user) {
        List<Access> userAccesses = Context.accessRepository.findAccessesFor(user);
        List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
        for (Access userAccess : userAccesses) {
            ShoppingList shoppingList = userAccess.getShoppingList();
            if (!shoppingList.isArchived()) {
                shoppingLists.add(shoppingList);
            }
        }

        ShoppingList[] sla = shoppingLists.toArray(new ShoppingList[shoppingLists.size()]);
        Arrays.sort(sla, new Comparator<ShoppingList>() {
            @Override
            public int compare(ShoppingList o1, ShoppingList o2) {
                return o2.getCreateDate().compareTo(o1.getCreateDate());
            }
        });

        List<ShoppingListSummary> sls = new ArrayList<ShoppingListSummary>();
        for (ShoppingList sl : sla) {
            ShoppingListSummary summary = new ShoppingListSummary();
            summary.title = sl.getTitle();
            sls.add(summary);
        }
        return sls;
    }
}
