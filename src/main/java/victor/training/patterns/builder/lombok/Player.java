package victor.training.patterns.builder.lombok;

import victor.training.patterns.util.pretend.Entity;

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

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
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

    public static class PlayerBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String profilePhotoId;
        private String bonusPackage;
        private long penalty;
        private String country;

        PlayerBuilder() {
        }

        public PlayerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PlayerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PlayerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PlayerBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public PlayerBuilder profilePhotoId(String profilePhotoId) {
            this.profilePhotoId = profilePhotoId;
            return this;
        }

        public PlayerBuilder bonusPackage(String bonusPackage) {
            this.bonusPackage = bonusPackage;
            return this;
        }

        public PlayerBuilder penalty(long penalty) {
            this.penalty = penalty;
            return this;
        }

        public PlayerBuilder country(String country) {
            this.country = country;
            return this;
        }

        public Player build() {
            return new Player(id, firstName, lastName, age, profilePhotoId, bonusPackage, penalty, country);
        }

        public String toString() {
            return "Player.PlayerBuilder(id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", age=" + this.age + ", profilePhotoId=" + this.profilePhotoId + ", bonusPackage=" + this.bonusPackage + ", penalty=" + this.penalty + ", country=" + this.country + ")";
        }
    }
}

// imagine no Hibernate.....