package pl.rwieckowski.demo.jbehave.steps;

import pl.rwieckowski.demo.jbehave.Access;
import pl.rwieckowski.demo.jbehave.User;

import java.util.ArrayList;
import java.util.List;

public class MemoryAccessRepository implements pl.rwieckowski.demo.jbehave.AccessRepository {
    private List<Access> accesses = new ArrayList<Access>();

    @Override
    public Access save(Access access) {
        accesses.add(access);
        return access;
    }

    @Override
    public List<Access> findAccessesFor(User user) {
        List<Access> results = new ArrayList<Access>();
        for (Access access : accesses) {
            if (access.getUser().isSame(user)) {
                results.add(access);
            }
        }
        return results;
    }
}
