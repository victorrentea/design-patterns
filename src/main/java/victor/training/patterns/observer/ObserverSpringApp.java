package victor.training.patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import victor.training.patterns.observer.order.OrderService;

import java.util.Random;

@Slf4j
@SpringBootApplication
public class ObserverSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(ObserverSpringApp.class, args);
	}

//	@Bean
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        return eventMulticaster;
//    }

	@Autowired
	private ObserverTransaction afterTransaction;

	@Autowired
	private OrderService orderService;

	@EventListener
	public void atStartup(ContextRefreshedEvent event) {
		orderService.placeOrder(new Random().nextLong());
		// afterTransaction.runInTransaction();
	}


}

