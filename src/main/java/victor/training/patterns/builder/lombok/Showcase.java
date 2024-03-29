package victor.training.patterns.builder.lombok;

import victor.training.patterns.builder.lombok.PlayerSolved.PlayerSolvedBuilder;

public class Showcase {
    public static void main(String[] args) {
        // 1) canonical constructor = hard to follow
        new PlayerSolved("1", "Alpha", null, 36, "face.jpg", null, 1, null);

        // 2) Builder + required fields = RISK
        // eg: missing required attribute (photoId) -> no compiler/IDE hint
        // (thanks to @NonNull it explodes at .build(), not later)
        new PlayerSolvedBuilder()
                .id("1")
                .firstName("Brad")
                .age(22)
                .country("Romania")
                .build();

        // 3) factory method for builder taking all required fields
        PlayerSolved.builder("1", "Comet", 23, "back.jpg")
                .country("Romania")
                .bonusPackage("aa")
                .build();
    }
}
