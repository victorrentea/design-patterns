package victor.training.patterns.observer.subdomains;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import victor.training.patterns.observer.subdomains.order.OrderService;

import java.util.Random;

@Slf4j
@SpringBootApplication
public class VerticalSlicingAnApp {
	public static void main(String[] args) {
		SpringApplication.run(VerticalSlicingAnApp.class, args);
	}

//	@Bean
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }

	@Autowired
	private OrderService orderService;

	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		orderService.placeOrder(new Random().nextLong());
		// afterTransaction.runInTransaction();
	}


}

