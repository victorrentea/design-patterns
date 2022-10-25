package victor.training.patterns.builder.optionalfields;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;


public class Player {

    int id; // *required
    String firstName; // *required
    String lastName;
    Integer age; // *required
    String profilePhotoUrl; // *required
    String bonusPackage;
    long penalty;

}

class PlayerPlay {

    public static void main(String[] args) {
        Player player = new Player();
        player.id = 1;

        System.out.println(player);
    }
}