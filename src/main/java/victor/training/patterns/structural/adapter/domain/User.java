package victor.training.patterns.structural.adapter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

// This would normally be placed in a 'domain model' package
@Data // i'm sorry
@AllArgsConstructor 
public class User {
//	private Long id;
private String username;
	private String fullName;
	private String workEmail;
}
