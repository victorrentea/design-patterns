package victor.training.patterns.builder.lombok;

public class PlayerPlay {
    public static void main(String[] args) {

        new Player(1L,
               "John", "DOE", 36,
                "photoid", "bonuspackage1", 0, "RO");

        Player player = Player.builder()
                .id(1L)
                .firstName("John")
                .lastName("DOE")
                .profilePhotoId("photoId")
                .build();
    }
}
