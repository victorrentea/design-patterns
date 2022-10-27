package victor.training.patterns.adapter.domain;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

// ajva 17 ~= @Value (lombok)
// "Value Object" pattern = small immutable object who's eq/hash is compute on all fields. also it does not have
// a persistent PK
public record User(
    String username,
    String fullName,
    Optional<String> workEmail // FAIL: Opt is dubious on fields!
) {
    public User {
        requireNonNull(username); // fail fast
    }

    public void moreLogicInside() {

    }
}
