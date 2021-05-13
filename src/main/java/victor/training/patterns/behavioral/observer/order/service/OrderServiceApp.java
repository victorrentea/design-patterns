package victor.training.patterns.behavioral.observer.order.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;


@EnableBinding(OrderQueues.class)
@SpringBootApplication
@Slf4j
public class OrderServiceApp implements CommandLineRunner {

   @Autowired
   private OrderQueues orderQueues;

   public static void main(String[] args) {
      SpringApplication.run(OrderServiceApp.class, args);
   }

   public void placeOrder(Long orderId) {
      log.debug("Halo!");
      orderQueues.orderPlacedEventQueue().send(MessageBuilder.withPayload("" + orderId).build());
   }

   @Override
   public void run(String... args) throws Exception {
      placeOrder(13L);
   }
}

interface OrderQueues {
   @Output("q1out")
   MessageChannel orderPlacedEventQueue();
}
