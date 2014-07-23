package doubles;

import pl.rwieckowski.demo.jbehave.User;
import pl.rwieckowski.demo.jbehave.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<User>();

    @Override
    public User findUserByName(String userName) {
        for (User user : users) {
            if (Objects.equals(user.getUserName(), userName)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
