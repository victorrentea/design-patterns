package victor.training.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import victor.training.patterns.stuff.ThreadUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.annotation.PostConstruct;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@Slf4j
@SpringBootApplication
public class SingletonIntro {
	@Autowired
	BizService biz;
	public static void main(String[] args) {
		SpringApplication.run(SingletonIntro.class);
	}
	@PostConstruct
	public void m() {
		log.debug("Start");
		log.debug("Out: " + biz.bizMethod());
		log.debug("Out: " + biz.bizMethod());
	}
}
@Service
class BizService {
	@Autowired // @EJB  @Inject
	ConfigManager configManager;
	public int bizMethod() {
		String config = configManager.getConfig();
		if (config.equals("NOOP")) {
			return -1;
		}
		return 0;
	}
}


@Service // @Component // @Stateless
class ConfigManager {
	private final String config;

	private ConfigManager() {
		System.out.println("Incarc config");
		try (Reader reader = new FileReader("f.txt")) {
			config = IOUtils.toString(reader); // takes time
			sleepq(1000);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* static */ int i; 
	private  String currentUserName; // NU ARE SENS< atata vreme cat sunt MULTE 
	// requesturi cu multi useri simultan in executie
	
	public int nextId() { // daca asta e chemata din 2o de requesturi simultane, de multe ori, pariu ca va da si ID-uri identice.
		return i++;
	}

	public String getConfig() {
		return config;
	}
}

//class ServiceLocator {
//   private static final Map<Class<?>, Object> singletons = new HashMap<>();
//
//   public static <T> T getInstance(Class<T> aClass) {
//      return (T) singletons.computeIfAbsent(aClass,
//          Unchecked.function(Class::newInstance));
//   }
//   public static <T> void setTestInstance(Class<T> aClass, T testDouble) {
//      singletons.put(aClass, testDouble);
//   }
//}
