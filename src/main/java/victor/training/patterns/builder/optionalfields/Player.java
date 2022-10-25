package victor.training.patterns.builder.optionalfields;


public class Player {

    private int id; // *required
    private String firstName; // *required
    private String lastName;
    private Integer age; // *required
    private String profilePhotoUrl; // *required
    private String bonusPackage;
    private long penalty;

    public int getId() {
        return id;
    }

    public Player setId(int id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Player setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public Player setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
        return this;
    }

    public String getBonusPackage() {
        return bonusPackage;
    }

    public Player setBonusPackage(String bonusPackage) {
        this.bonusPackage = bonusPackage;
        return this;
    }

    public long getPenalty() {
        return penalty;
    }

    public Player setPenalty(long penalty) {
        this.penalty = penalty;
        return this;
    }
}

class PlayerPlay {

    public static void main(String[] args) {
        Player player = john().setFirstName(null);

        System.out.println(player);
    }

    private static Player john() {
        return new Player()
                .setId(1)
                .setFirstName("John");
    }
}