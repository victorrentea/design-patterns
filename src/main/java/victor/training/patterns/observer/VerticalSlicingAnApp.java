package victor.training.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import victor.training.patterns.observer.subdomains.order.OrderService;

@Slf4j
@SpringBootApplication
public class VerticalSlicingAnApp {
	public static void main(String[] args) {
		SpringApplication.run(VerticalSlicingAnApp.class, args);
	}

	// Unleash hell: multi-threaded event listener
//	@Bean
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }

	@Autowired
	private OrderService orderService;

	@EventListener(ApplicationStartedEvent.class)
	public void atStartup() {
		orderService.placeOrder();
	}


}

