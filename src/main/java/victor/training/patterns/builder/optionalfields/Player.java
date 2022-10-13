package victor.training.patterns.builder.optionalfields;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class Player {

    @NonNull
    String id;
    @NonNull
    String firstName;
    String lastName;
    @NonNull
    Integer age;
    @NonNull
    String profilePhotoId;
    String bonusPackage;
    long penalty;
    String country;

    public static PlayerBuilder builder(String id, String firstName, Integer age, String profilePhotoId) {
        return new PlayerBuilder().id(id)
                .firstName(firstName)
                .age(age)
                .profilePhotoId(profilePhotoId);
    }

}

class PlayerPlay {

    public static void main(String[] args) {
//        new Player(null, null, null, null, null, null, 1, null);
        Player player = Player.builder("ghfhj", "Adi", 23, "hgmh")
                .country("Romania")
                .bonusPackage("aa")

                .build();
        System.out.println(player);
    }
}