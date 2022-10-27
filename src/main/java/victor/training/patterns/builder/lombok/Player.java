package victor.training.patterns.builder.lombok;

import lombok.Data;
import victor.training.patterns.util.pretend.Entity;

@Entity
@Data
public class Player {
    private Long id; // *required
    private String firstName; // *required
    private String lastName;
    private Integer age; // *required
    private String profilePhotoId; // *required
    private String bonusPackage;
    private long penalty;
    private String country;

}

// imagine no Hibernate.....