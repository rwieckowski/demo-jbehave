package pl.rwieckowski.demo.jbehave;

import java.util.Date;

public class ShoppingList {
    private String title;
    private Date createDate;
    private boolean archived;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isArchived() {
        return archived;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
