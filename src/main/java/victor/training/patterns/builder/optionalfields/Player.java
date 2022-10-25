package victor.training.patterns.builder.optionalfields;


import lombok.Data;

import java.util.Objects;

@Data // + @see lombok.config
public class Player {
    private final int id; // *required
    private final String firstName; // *required
    private final String lastName;
    private final Integer age; // *required
    private final String profilePhotoUrl; // *required
    private final String bonusPackage;
    private final long penalty;

    public Player(int id, String firstName, String lastName, Integer age, String profilePhotoUrl, String bonusPackage, long penalty) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = lastName;
        this.age = Objects.requireNonNull(age);
        this.profilePhotoUrl = Objects.requireNonNull(profilePhotoUrl);
        this.bonusPackage = bonusPackage;
        this.penalty = penalty;
    }
}

class PlayerPlay {

    public static void main(String[] args) {
        Player player = new Player(1, "John", null, 36, "photo.jpg", "aa",0);

        System.out.println(player);
    }

}