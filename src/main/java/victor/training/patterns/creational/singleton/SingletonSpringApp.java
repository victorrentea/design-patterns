package victor.training.patterns.creational.singleton;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

import static victor.training.patterns.stuff.ThreadUtils.sleepr;

@Slf4j // if this doesn't compile:
// IntelliJ: Settings>Plugins> install "Lombok" plugin + Restart
// Eclipse: navigate to m2 repo/org/projectlombok/lombok/<latest> => run the .jar as java application (double click) -> "Lombok your Eclipse installation"

@EnableCaching
@SpringBootApplication
public class SingletonSpringApp implements CommandLineRunner{
	@Bean
	public static CustomScopeConfigurer defineThreadScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("thread", new SimpleThreadScope()); // WARNING: Leaks memory. Prefer 'request' scope or read here: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/SimpleThreadScope.html 
		return configurer;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SingletonSpringApp.class);
	}
	
	@Autowired 
	private OrderExporter exporter;

	// TODO [0] Lombok
	// TODO [1] state in a singleton is DANGEROUS https://rules.sonarsource.com/java/RSPEC-3749?search=injected
	// TODO [2] instantiate manually, set dependencies, pass around; no AOP
	// TODO [3] prototype scope + ObjectFactory or @Lookup. Did you say "Factory"? ...
	// TODO [4] thread/request scope. HOW it works?! Leaks: @see SimpleThreadScope javadoc
	public void run(String... args) {
		new Thread(() -> exporter.export(Locale.ENGLISH)).start();
		new Thread(() -> exporter.export(Locale.FRENCH)).start();
	}
}

@Slf4j
@Service
@RequiredArgsConstructor
class OrderExporter {
	//	private final LabelService labelService;
	private final CountryRepo countryRepo;

	public /*synchronized - avoid*/ void export(Locale locale) {
		log.debug("Running export in " + locale);
		LabelService labelService = new LabelService(countryRepo, locale);
		log.debug("Origin Country: " + labelService.getCountryName("rO"));
	}
}

@Slf4j
//@Service // panica!
class LabelService {
//	private static final Logger log = LoggerFactory.getLogger(LabelService.class);
//	private static final Logger log2 = LoggerFactory.getLogger(LabelService.class);
//	private static final Logger log3 = LoggerFactory.getLogger(OrderExporter.class);
//	log==log2
//	log!=log3
private final CountryRepo countryRepo;
	private final Map<String, String> countryNames;
	//private Map<Locale, Map<String, String>> allCountryNames;
	// EN ->  RO -> Romania
	// FR -> RO -> Roumanie

	public LabelService(CountryRepo countryRepo, Locale locale) {
		this.countryRepo = countryRepo;
		countryNames = countryRepo.loadCountryNamesAsMap(locale);
	}


	public String getCountryName(String iso2Code) {
		log.debug("getCountryName() in instance: " + this.hashCode());
		sleepr();
		return countryNames.get(iso2Code.toUpperCase());
	}
}