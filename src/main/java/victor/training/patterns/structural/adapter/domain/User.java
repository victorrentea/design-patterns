package victor.training.patterns.structural.adapter.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

// This would normally be placed in a 'domain model' package
@Value // i'm sorry
@AllArgsConstructor
public class User {
	String username;
	String fullName;
	String workEmail;
}
