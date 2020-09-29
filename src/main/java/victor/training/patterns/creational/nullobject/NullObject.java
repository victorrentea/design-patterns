package victor.training.patterns.creational.nullobject;

import victor.training.patterns.stuff.pretend.Entity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NullObject {
    public static void main(String[] args) {
        // 1
        PriceService priceService = new PriceService();
        Customer customer = new Customer().setCard(new MemberCard(10));
        double price = priceService.computePrice(100, customer); // TODO null it
        System.out.println(price);

        // 2
        System.out.println(parseInts(Arrays.asList("1","2"))); // TODO null it
        // TODO sum() Tip: CTRL-SHIFT-SPACE in .map..()
    }

    static List<Integer> parseInts(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}


class MemberCard {
    private String email;
    private String mailAddress;
    private boolean hipster;
    private boolean withChildren;
    private int points;
    public MemberCard(int points) {
        this.points = points;
    }
    public MemberCard() {}

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
    private MemberCard card;

    public Customer setCard(MemberCard card) {
        this.card = card;
        return this;
    }

    public MemberCard getCard() {
        return card;
    }
}

class PriceService {
    public double computePrice(double basePrice, Customer customer) {
        System.out.println("Using fidelity points: " + customer.getCard().getPoints());
        return basePrice - customer.getCard().getPoints() / 10d;
    }
}