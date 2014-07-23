package pl.rwieckowski.demo.jbehave;

import java.util.Objects;

public class ShoppingListSummary {
    public String title;

    @Override
    public boolean equals(Object other) {
        if (other instanceof ShoppingListSummary) {
            ShoppingListSummary sls = (ShoppingListSummary) other;
            return Objects.equals(title, sls.title);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ShoppingListSummary{" +
                "title='" + title + '\'' +
                '}';
    }
}
