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

    @NonNull
    String requiredCountry;// in the biz logic, someone REALLY needs this to be NOT NULL
    String country2;

    public static PlayerBuilder builder(String id, String firstName, Integer age, String profilePhotoId, String requiredCountry) {
        return new PlayerBuilder()
                .id(id)
                .firstName(firstName)
                .age(age)
                .profilePhotoId(profilePhotoId)
                .requiredCountry(requiredCountry);
    }

}

class PlayerPlay {

    public static void main(String[] args) {

//        new Player()

//        Player playerBad = new Player.PlayerBuilder()
//                .id("1")
//                .firstName("firstName")
//                .age(14)
//                .profilePhotoId("1")
//                .build();

        // imagine you add a REQUIRED FIELD to the constructed object
        // NOTHING in the code will blow up>>>> NPE at RUNTIME!

// HORRID constructor call
//        new Player(null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                1,
//                null, "country2");




        Player player = Player.builder("ghfhj", "Adi", 23, "hgmh", "damn2")
                .requiredCountry("Romania")
                .bonusPackage("aa")

                .build();
        System.out.println(player);
    }
}