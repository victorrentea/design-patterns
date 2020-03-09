package victor.training.oo.creational.singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FreddyPlay {
    public static void main(String[] args) {
        System.out.println(Freddy.getInstance().m());

        System.out.println(Freddy.getInstance().m());
    }
}
class Freddy {
    private static Freddy freddy = null;

    private Freddy() {}

    public static Freddy getInstance() {
        if (freddy == null) {
            freddy = new Freddy();
        }
        return freddy;
    }

    private List<String> words = new ArrayList<>(Arrays.asList("I", "am", "the", "one", "and", "only"));

    public String m() {
        return words.remove(0);
    }

}
