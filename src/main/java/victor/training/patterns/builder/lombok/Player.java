package victor.training.patterns.builder.lombok;

import lombok.Builder;
import victor.training.patterns.util.pretend.Entity;

@Builder
@Entity
public class Player {
    private final Long id; // *required
    private final String firstName; // *required
    private final String lastName;
    private final Integer age; // *required
    private final String profilePhotoId; // *required
    private final String bonusPackage;
    private final long penalty;
    private final String country;

    // canonical ctor
    public Player(Long id, String firstName, String lastName, Integer age, String profilePhotoId, String bonusPackage, long penalty, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profilePhotoId = profilePhotoId;
        this.bonusPackage = bonusPackage;
        this.penalty = penalty;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getProfilePhotoId() {
        return profilePhotoId;
    }

    public String getBonusPackage() {
        return bonusPackage;
    }

    public long getPenalty() {
        return penalty;
    }

    public String getCountry() {
        return country;
    }
}

// imagine no Hibernate.....