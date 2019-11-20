package victor.training.oo.structural.nullobject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NullObjectApp {
    public static void main(String[] args) {
        // usual call
        System.out.println(parseInts(Arrays.asList("12","13","14")));
        System.out.println(parseDoubles(Arrays.asList("12.2","13.3","14.4")));

        // evil/careless call
        // TODO ideas ?...
    }

    public static List<Integer> parseInts(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Double> parseDoubles(List<String> numbers) {
        return numbers.stream().map(Double::new).collect(Collectors.toList());
    }
}


class Order {
    public double getTotalPrice() {
        return 13d;
    }
}

interface  MemberCard {
    public int getFidelityPoints();

    public void addPoints(int newPoints);
}
final class NoCard implements MemberCard {
    public int getFidelityPoints() {
        return -1;
    }
    public void addPoints(int newPoints) {
    }
}
class MemberCardImpl implements MemberCard {
    private String email, address, socialHandles, phone;
    private int shoeSize, nbChildren;

    private int fidelityPoints;

    public int getFidelityPoints() {
        return fidelityPoints;
    }

    public void addPoints(int newPoints) {
        fidelityPoints += newPoints;
    }
}
class Customer {
    private static final MemberCard NO_CARD = new NoCard();
    private MemberCard card = NO_CARD;

    public MemberCard getCard() {
        return card;
    }
    public String getAddress() {
        return "Eden";
    }
}

class PriceService {
    public double computePrice(double basePrice, Customer customer) {
        double price = basePrice;
        price += transportCost(customer.getAddress()); // pretend compute transport price for Customer address
        double discount = customer.getCard().getFidelityPoints() / 10d;
        price -= discount;
        return price;
    }

    public void order(Order order, Customer customer) {
        // process order placement
        customer.getCard().addPoints((int) (order.getTotalPrice() / 100));
    }

    private double transportCost(String address) {
        return 10;
    }
}