package victor.training.patterns.builder.lombok;

import lombok.Builder;
import victor.training.patterns.util.pretend.Entity;

import java.util.Objects;

@Entity
@Builder
public class Player {
    private final long id; // *required
    private final String firstName; // *required
    private final String lastName;
    private final int age; // *required
    private final String profilePhotoId; // *required
    private final String bonusPackage;
    private final long penalty;
    private final String country;

    // canonical ctor
    public Player(long id, String firstName, String lastName, int age, String profilePhotoId, String bonusPackage, long penalty, String country) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = lastName;
        this.age = age;
        this.profilePhotoId = Objects.requireNonNull(profilePhotoId);
        this.bonusPackage = bonusPackage;
        this.penalty = penalty;
        this.country = country;
    }
    //minimal ctor


    public Player(long id, String firstName, int age, String profilePhotoId) {
        this(id, firstName, null, age, profilePhotoId, null, 0, null);
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