package victor.training.oo.creational.nullobject;

import lombok.Getter;
import lombok.Setter;
import victor.training.oo.stuff.pretend.Entity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NullObject {
    public static void main(String[] args) {
        // 1
        PriceService priceService = new PriceService();
        priceService.m(new Customer().setFullName("John Doe"));

//
        Customer customer = new Customer();
        System.out.println(priceService.computePrice(100, customer));

        customer.setCard(new MemberCard(10));
        System.out.println(priceService.computePrice(100, customer));
//
//        // 2
//        System.out.println(parseInts(Arrays.asList("1","2"))); // TODO null it
//        // TODO sum() Tip: CTRL-SHIFT-SPACE in .map..()
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

    public double computePrice(double basePrice) {
        System.out.println("Using fidelity points: " + points);
        return basePrice - points / 10d;
    }
}

@Entity
class Customer {
    @Getter @Setter
    private String fullName;
    @Setter
    // e nullable in db
    private String prefix;
    @Getter @Setter
    private MemberCard card;

    public Optional<String> getPrefix() {
        return Optional.ofNullable(prefix);
    }
}

class PriceService {
    public void m(Customer customer) {
        System.out.println("Hello " + customer.getPrefix().orElse("") + " " + customer.getFullName());
    }
    public double computePrice(double basePrice, Customer customer) {
        if (customer.getCard() == null) {
            return basePrice;
        }
        return customer.getCard().computePrice(basePrice);
    }
    public double computePrice2(double basePrice, Customer customer) {
        if (customer.getCard() == null) {
            return basePrice;
        }
        return customer.getCard().computePrice(basePrice);
    }
    public double computePrice3(double basePrice, Customer customer) {
        if (customer.getCard() == null) {
            return basePrice;
        }
        return customer.getCard().computePrice(basePrice);
    }
    public double computePrice4(double basePrice, Customer customer) {
        if (customer.getCard() == null) {
            return basePrice;
        }
        return customer.getCard().computePrice(basePrice);
    }

}