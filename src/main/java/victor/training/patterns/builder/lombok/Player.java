package victor.training.patterns.builder.lombok;

public class Player {
    Long id; // *required
    String firstName; // *required
    String lastName;
    Integer age; // *required
    String profilePhotoId; // *required
    String bonusPackage;
    long penalty;
    String country;
}

// imagine no Hibernate.....