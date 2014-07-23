package pl.rwieckowski.demo.jbehave;

public interface UserRepository {
    User findUserByName(String userName);

    User save(User user);
}
