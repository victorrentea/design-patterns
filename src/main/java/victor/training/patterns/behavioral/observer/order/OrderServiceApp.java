package victor.training.patterns.behavioral.observer.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@EnableBinding(OrderQueues.class)
@Service
@SpringBootApplication
public class OrderServiceApp implements CommandLineRunner {
   private static final Logger log = LoggerFactory.getLogger(OrderServiceApp.class);
   @Autowired
   OrderQueues queues;

   public static void main(String[] args) {
      SpringApplication.run(OrderServiceApp.class, args);
   }

   public void placeOrder(Long orderId) {
      log.debug("Persist the order!");

      queues.sendOrderPlacedEvent().send(MessageBuilder.withPayload(orderId + "").build());

      log.debug("After all hadlers ran in my thread");
   }

   @Override
   public void run(String... args) throws Exception {
      placeOrder(13L);
   }
}

interface OrderQueues {
   @Output("ope-out")
   MessageChannel sendOrderPlacedEvent();
}
