package victor.training.patterns.template.support;

import java.util.List;
import java.util.stream.Stream;

public interface OrderRepo { // Spring Data FanClub
   List<Order> findByActiveTrue(); // 1 Mln orders ;)
}