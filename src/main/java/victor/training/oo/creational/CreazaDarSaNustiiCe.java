package victor.training.oo.creational;

interface Draft {
    void m();
}
class DraftImpl implements Draft {
    public void m() {
    }
}
class DraftV2Impl implements Draft {
    public void m() {
    }
}
public class CreazaDarSaNustiiCe {
    public static Draft createDraft(int x) {
        if (x == 1) {
            return new DraftImpl();
        } else {
            return new DraftV2Impl();
        }
    }

}
/// --- cod client mai jos

class CodClient {
    public static void main(String[] args) {
        Draft draft = CreazaDarSaNustiiCe.createDraft(1);
        draft.m();
    }
}