package victor.training.patterns.builder.lombok;

public class PlayerPlay {
    public static void main(String[] args) {

        new Player(1L,
               "John", "DOE", 36,
                "photoid", "bonuspackage1", 0, "RO");




        new Player(1L,
                "John",
                36,
                "photo")
                .withCountry("RO");

        Player player = aPlayer()
                .build();

        new Player(1L, "firs", 36, "profile");

//        player.getProfilePhotoId().toUpperCase()// NPE
    }

    private static Player.PlayerBuilder aPlayer() {
        return Player.builder(1L,"first", 36,"photo")
//                .id(1L)
                // weakness of a builder:
                    // it throws an error at runtime for a REQUIRED field
                .lastName("DOE");
//                .profilePhotoId("photoId");
    }
}
