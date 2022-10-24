package victor.training.patterns.adapter.domain;

import java.util.Objects;

// Value Object = small immutable, hash-code equals on all fields
//     no persistent ID.

// @Value class ... Lombok
public record User(
        String username,
        String workEmail,
        String fullName
) {
    public User {
        Objects.requireNonNull(username);
    }

    public boolean hasWorkEmail() {
        return workEmail != null;
    }
}
