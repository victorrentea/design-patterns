package victor.training.patterns.builder.lombok;

import lombok.Data;
import victor.training.patterns.util.pretend.Entity;

@Entity
public class Player {
    private Long id; // *required
    private String firstName; // *required
    private String lastName;
    private Integer age; // *required
    private String profilePhotoId; // *required
    private String bonusPackage;
    private long penalty;
    private String country;

    public Long getId() {
        return id;
    }

    public Player setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Player setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getProfilePhotoId() {
        return profilePhotoId;
    }

    public Player setProfilePhotoId(String profilePhotoId) {
        this.profilePhotoId = profilePhotoId;
        return this;
    }

    public String getBonusPackage() {
        return bonusPackage;
    }

    public Player setBonusPackage(String bonusPackage) {
        this.bonusPackage = bonusPackage;
        return this;
    }

    public long getPenalty() {
        return penalty;
    }

    public Player setPenalty(long penalty) {
        this.penalty = penalty;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Player setCountry(String country) {
        this.country = country;
        return this;
    }
}

// imagine no Hibernate.....