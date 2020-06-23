package victor.training.oo.creational.singleton;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

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
//	@Autowired
//	private ApplicationContext springu;
	// TODO [1] make singleton; test multi-thread: state is [ | | | ]
	// TODO [2] instantiate manually, set dependencies, pass around; no AOP
	// TODO [3] prototype scope + ObjectFactory or @Lookup. Did you said "Factory"? ...
	// TODO [4] thread/request scope. HOW it works?! Leaks: @see SimpleThreadScope javadoc
	// TODO [5] (after AOP): RequestContext, @Cacheable. on thread?! @ThreadLocal
	public void run(String... args) throws Exception {
		// simulam HTTP requests:
		new Thread(() -> exporter.export(Locale.ENGLISH)).start();
		new Thread(() -> exporter.export(Locale.FRENCH)).start();
	}
}

@Slf4j
@Service
@Scope("prototype")
class OrderExporter  {
	@Autowired
	private InvoiceExporter invoiceExporter;
//	@Autowired
//	private LabelService labelService; // @AUtowired a unui @Service @Scope(prototype sau request sau session) intrun singleton === BUG in prod.

//	@Autowired
//	private ApplicationContext springu;
	@Autowired
	private CountryRepo countryRepo; // imi injectez eu dependintele ei :( #eroina mea

	public void export(Locale locale) {
//		LabelService labelService = springu.getBean(LabelService.class);

		// back to the roots: OOP tata
		LabelService labelService = new LabelService(countryRepo);

		log.debug("Running export in " + locale);
		labelService.load(locale);
		log.debug("Origin Country: " + labelService.getCountryName("rO"));
		invoiceExporter.exportInvoice(labelService);
	}
}
@Slf4j
//@Scope("prototype")
@Service
class InvoiceExporter {
	public void exportInvoice(LabelService labelService) {
		System.out.println("Lab ser = " + labelService);
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}
// Spring (orice DI Framework) == Anti OOP
//@Service
@Slf4j
@RequiredArgsConstructor
class LabelService {
	private final CountryRepo countryRepo;
	private Map<String, String> countryNames; // -in ap obisnuite, pe singletoane @SErvice nu AI VOIE sa ai campuri cu date, specifice unui anumit request.

	public void load(Locale locale) {
		log.debug("load() in instance: " + this.hashCode());
		countryNames = countryRepo.loadCountryNamesAsMap(locale);
	}
	
	public String getCountryName(String iso2Code) {
		log.debug("getCountryName() in instance: " + this.hashCode());
		return countryNames.get(iso2Code.toUpperCase());
	}
}