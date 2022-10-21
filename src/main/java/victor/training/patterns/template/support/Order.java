package victor.training.patterns.template.support;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {
   private Long id;
   private Long customerId;
   private Double amount;
}
