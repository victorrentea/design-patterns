package victor.training.oo.behavioral.command;

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.pattern.color.WhiteCompositeConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

@EnableAsync // SOLUTION
@SpringBootApplication
public class CommandSpringApp implements AsyncConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Value("${barman.thread.count:2}")
	private int workerCount;
	
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(workerCount);
		executor.setMaxPoolSize(workerCount);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("barman-");
		executor.initialize();
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}

}

@Slf4j
@Component
class Drinker implements CommandLineRunner {
	@Autowired
	private Barman barman;
	
	// [1] inject and use a ThreadPoolTaskExecutor.submit
	// [2] make them return a CompletableFuture + @Async + asyncExecutor bean
	public void run(String... args) throws Exception {
		log.debug("Submitting my order");
		Future<Void> futureSms = barman.sendSms("Ti-am dat bip! caci sunt voinic");

		
		Future<Ale> futureAle = barman.getOneAle();
		Future<Wiskey> futureWiskey = barman.getOneWiskey();
		log.debug("A plecat cu comanda mea");
		Ale ale = futureAle.get();
		Wiskey wiskey = futureWiskey.get();
		
		futureSms.get(); // ce mai faci, bre contractorule?
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, wiskey));
		
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public Future<Ale> getOneAle() {
		 ThreadUtils.sleep(1000);
		 log.debug("Pouring Ale...");
		 return completedFuture(new Ale());
	 }
	
	@Async
	 public Future<Wiskey> getOneWiskey() {
		 ThreadUtils.sleep(1000);
		 log.debug("Pouring Wiskey...");
		 return completedFuture(new Wiskey());
	 }
	@Async
	public Future<Void> sendSms(String sms) {
		if (true) throw new IllegalArgumentException();
		log.debug("Trimis semeseu: {}", sms);
		return completedFuture(null);
	}
}

@Data
class Ale {
}

@Data
class Wiskey {
}
