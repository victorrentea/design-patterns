package victor.training.patterns.builder.optionalfields;


import lombok.Data;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Data // + @see lombok.config
public class Player {
    private final int id; // *required
    private final String firstName; // *required
    private final String lastName;
    private final Integer age; // *required
    private final String profilePhotoUrl; // *required
    private final String bonusPackage;
    private final long penalty;

    // canonical cto
    public Player(int id, String firstName, String lastName, Integer age, String profilePhotoUrl, String bonusPackage, long penalty) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = lastName;
        this.age = Objects.requireNonNull(age);
        this.profilePhotoUrl = Objects.requireNonNull(profilePhotoUrl);
        this.bonusPackage = bonusPackage;
        this.penalty = penalty;
    }
    public Player(int id, String firstName, Integer age, String profilePhotoUrl) {
        this(id, firstName, null, age, profilePhotoUrl, null, null);
    }

    //± data class copy()
    public Player withLastName(String lastName) {
        return new Player(id, firstName, lastName, age, profilePhotoUrl, bonusPackage, penalty);
    }

    public Optional<String> getLastName() {
        return ofNullable(lastName);
    }

    public Optional<String> getBonusPackage() {
        return ofNullable(bonusPackage);
    }
}

class PlayerPlay {

    public static void main(String[] args) {
        Player player = new Player(1, "John", null, 36, "photo.jpg", "aa",0);
        Player player2 = new Player(1, "John", 36, "photo.jpg")
                .withLastName("Doe");


        System.out.println(player);
    }

}