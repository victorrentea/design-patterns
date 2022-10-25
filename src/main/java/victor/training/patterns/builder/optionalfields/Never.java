package victor.training.patterns.builder.optionalfields;

public class Never {
    private static Never INSTANCE;

    public static Never getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Never();
        }
        return INSTANCE;
    }

    private Never() {
    }

    public void stuff() {
        throw new RuntimeException("Method not implemented");
    }
}
class Code {
    public void method() {
        Never.getInstance().stuff();
    }
}