package pl.rwieckowski.demo.jbehave;

import java.util.Objects;

public class User {
    private final String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isSame(User user) {
        return Objects.equals(userName, user.userName);
    }
}
