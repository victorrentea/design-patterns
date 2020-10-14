package victor.training.patterns.creational.singleton.threads;

import javax.xml.ws.ServiceMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;


@SpringBootApplication
public class ThePerfectService implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ThePerfectService.class);
	}
@Autowired
MyService service;
	
	@Override
	public void run(String... args) throws Exception {
		new Thread(() ->service.me("a")).start(); 
		new Thread(() ->service.me("b")).start(); 
	}
}



@Service
class MyService {
private static final Logger log = LoggerFactory.getLogger(MyService.class);
	private String currentUserName;
	
	public void me(String currentUser) {
		currentUserName = currentUser;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		other();
	}
	
	private void other() {
		
		log.info(currentUserName);
	}
	
}
