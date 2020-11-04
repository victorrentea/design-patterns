package victor.training.patterns.creational.nullobject;

import java.util.List;
import java.util.stream.Collectors;

class MemberCard {
    private final int points;
    private final double discountFactor;

    public MemberCard(int points, double discountFactor) {
        this.points = points;
        this.discountFactor = discountFactor;
    }

    public int getPoints() {
        return points;
    }

    public double getDiscountFactor() {
        return discountFactor;
    }
}

class Customer {
    private MemberCard memberCard;

    public Customer setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
        return this;
    }

    public MemberCard getMemberCard() {
        return memberCard;
    }
}

class PriceService {
    public double computePrice(double basePrice, Customer customer) {
        System.out.println("Using fidelity points: " + customer.getMemberCard().getPoints());
        return basePrice * customer.getMemberCard().getDiscountFactor() - customer.getMemberCard().getPoints() / 10d;
    }
}

public class NullObject {
    public static void main(String[] args) {
        // 1
        // TODO no member card
        // TODO default state, eg parseInts(emptyList)
        // Imagine price being used in many places
        // TODO Fix Feature Envy + dummy behavior (Null Object Pattern)
        Customer customer = new Customer().setMemberCard(new MemberCard(10, .95));
        double price = new PriceService().computePrice(100, customer);
        System.out.println(price);
    }

    static List<Integer> parseInts(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}