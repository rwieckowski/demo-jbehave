package pl.rwieckowski.demo.jbehave;

import java.util.List;

public interface AccessRepository {
    Access save(Access access);

    List<Access> findAccessesFor(User user);
}
