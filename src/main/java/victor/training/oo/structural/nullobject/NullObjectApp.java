package victor.training.oo.structural.nullobject;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NullObjectApp {
    public static void main(String[] args) {
        // usual call
        System.out.println(parseInts(Arrays.asList("12","13","14")));
        System.out.println(parseDoubles(Arrays.asList("12","13","14")));

        // evil/careless call
        // TODO ideas ?...
    }

    public static List<Integer> parseInts(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Double> parseDoubles(List<String> numbers) {
        return numbers.stream().map(Double::new).collect(Collectors.toList());
    }

    public Order getOrder(long orderId, PermissionGranter permission) {
        if (permission == null) {
            throw new IllegalArgumentException("Forbidden");
        }
        Order order = repo.getById(orderId);
        if (!permission.canView(order)) {
            throw new IllegalArgumentException("Forbidden");
        }
        return order;
    }
}

enum Role {
    ADMIN, COUNTRY_MANAGER, USER
}


@Slf4j
class PermissionGranter {
    private final Set<Long> managedCountryIds;
    private final Role role;

    PermissionGranter(Set<Long> managedCountryIds, Role role) {
        this.managedCountryIds = managedCountryIds;
        this.role = role;
    }
    public boolean canEdit(Order order) {
        if (order.isConfidential() && role == Role.USER) {
            return false;
        }
        if (!managedCountryIds.contains(order.getDestinationCountryId())) {
            return false;
        }
        log.trace("Grating edit access");
        return true;
    }
    public boolean canView(Order order) {
        if (order.isConfidential() && role == Role.USER) {
            return false;
        }
        log.trace("Grating view access");
        return true;
    }
}
class NoPermissionGranter extends PermissionGranter {

}


class Order {}

class MemberCard {
    private String email, address, socialHandles, phone;
    private int shoeSize, nbChildren;

    private int fidelityPoints;

    public int getFidelityPoints() {
        return fidelityPoints;
    }
}
class Customer {
    private MemberCard card;

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
        customer.getCard().addPoints(order.getTotalPrice() / 100d)
    }

    private double transportCost(String address) {
        return 10;
    }
}