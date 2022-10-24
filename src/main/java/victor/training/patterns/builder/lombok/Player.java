package victor.training.patterns.builder.lombok;

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

    // atempt to fix the risk of forgetting a required field (imperfect) #3
    public static PlayerBuilder builder(String id, String firstName, Integer age, String profilePhotoId) {
        return new PlayerBuilder().id(id)
                .firstName(firstName)
                .age(age)
                .profilePhotoId(profilePhotoId);
    }
}

class PlayerPlay {
    public static void main(String[] args) {
        // 1) canonical constructor is hard to follow
        new Player("1", "Alpha", null, 36, "face.jpg", null, 1, null);

        // 2) Builder + required fields = RISK
        // eg: missing required attribute (photoId) -> no compiler/IDE hint
        // (thanks to @NonNull it explodes at .build(), not later)
        new Player.PlayerBuilder()
                .id("1")
                .firstName("Brad")
                .age(22)
                .country("Romania")
                .build();

        // 3) factory method for builder taking all required fields
        Player.builder("1", "Comet", 23, "back.jpg")
                .country("Romania")
                .bonusPackage("aa")
                .build();
    }
}