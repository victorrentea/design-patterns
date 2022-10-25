package victor.training.patterns.builder.optionalfields;


import lombok.Data;

@Data // + @see lombok.config
public class Player {

    private final int id; // *required
    private final String firstName; // *required
    private final String lastName;
    private final Integer age; // *required
    private final String profilePhotoUrl; // *required
    private final String bonusPackage;
    private final long penalty;

}

class PlayerPlay {

    public static void main(String[] args) {
        Player player = new Player(1, "John", null, 36, "photo.jpg", "aa",0);

        System.out.println(player);
    }

}