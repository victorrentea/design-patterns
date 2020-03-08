package victor.training.oo.creational.nullobject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NullObject {
    private List<String> stringuri = new ArrayList<>();

    public static void main(String[] args) {
        parseInts(null);
    }

    static List<Integer> parseInts(List<String> numere) {
        return numere.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}


class MemberCard {
    public static final MemberCard NO_MEMBER_CARD = new MemberCard();
    private String email, undeLocuiesti,aiCopii;
    private int points;

    public int getPoints() {
        return points;
    }
}
// @Entity
class Customer {
    private MemberCard card;// = MemberCard.NO_MEMBER_CARD;

    public MemberCard getCard() {
        return card;
    }
}

class PriceService {
    void computePrice(Customer customer) {
        if (customer.getCard() != null) {
            System.out.println("aplica reducerea pe " + customer.getCard().getPoints());
        }
    }
}