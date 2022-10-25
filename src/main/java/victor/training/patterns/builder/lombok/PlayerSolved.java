package victor.training.patterns.builder.lombok;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class PlayerSolved {
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

    // attempt to fix the risk of forgetting a required field (imperfect) #3
    public static PlayerSolvedBuilder builder(String id, String firstName, Integer age, String profilePhotoId) {
        return new PlayerSolvedBuilder()
                .id(id)
                .firstName(firstName)
                .age(age)
                .profilePhotoId(profilePhotoId);
    }
}

