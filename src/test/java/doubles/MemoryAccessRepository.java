package doubles;

import pl.rwieckowski.demo.jbehave.Access;
import pl.rwieckowski.demo.jbehave.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryAccessRepository implements pl.rwieckowski.demo.jbehave.AccessRepository {
    private List<Access> accesses = new ArrayList<>();

    @Override
    public Access save(Access access) {
        accesses.add(access);
        return access;
    }

    @Override
    public List<Access> findAccessesFor(User user) {
        return accesses.stream()
                .filter(access -> access.getUser().isSame(user))
                .collect(Collectors.toList());
    }
}
