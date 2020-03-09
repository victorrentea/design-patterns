package victor.training.oo.creational.nullobject;

import victor.training.oo.stuff.pretend.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NullObject {
    public static void main(String[] args) {
        // 1
        PriceService priceService = new PriceService();
        Customer customer = new Customer();
        double price = priceService.computePrice(100, customer); // TODO null it
        System.out.println(price);

        // 2
        System.out.println(parseInts(Arrays.asList("1", "2"))); // TODO null it
        // TODO sum() Tip: CTRL-SHIFT-SPACE in .map..()
    }

    static List<Integer> parseInts(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}


class MemberCard {
    public static final MemberCard NO_CARD = new MemberCard(0);
    private String email;
    private String mailAddress;
    private boolean hipster;
    private boolean withChildren;
    private int points;

    public MemberCard(int points) {
        this.points = points;
    }

    public MemberCard() {
    }

    public MemberCard setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getPoints() {
        return points;
    }
}

@Entity
class Customer {
    private String fullName;
    private MemberCard card = MemberCard.NO_CARD;

    public Customer setCard(MemberCard card) {
        this.card = card;
        return this;
    }

    public Optional<MemberCard> getCard() {
        return Optional.ofNullable(card);
    }
}

class PriceService {
    public double computePrice(double basePrice, Customer customer) {
        Optional<MemberCard> optCard = customer.getCard();

        if (optCard.isPresent()) {
            MemberCard card = optCard.get();
            System.out.println("Using fidelity points: " + card.getPoints());
            return basePrice - card.getPoints() / 10d;
        } else {
            return basePrice;
        }
    }
}