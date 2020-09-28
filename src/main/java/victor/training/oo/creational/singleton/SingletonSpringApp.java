package victor.training.oo.creational.singleton;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j // if this doesn't compile:
// IntelliJ: Settings>Plugins> install "Lombok" plugin + Restart
// Eclipse: navigate to m2 repo/org/projectlombok/lombok/<latest> => run the .jar as java application (double click) -> "Lombok your Eclipse installation"

@EnableCaching
@SpringBootApplication
public class SingletonSpringApp implements CommandLineRunner{
	@Bean
	public static CustomScopeConfigurer defineThreadScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("thread", new SimpleThreadScope()); // WARNING: Leaks memory. Prefer 'request' scope or read hee: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/SimpleThreadScope.html
		return configurer;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SingletonSpringApp.class);
	}
	
	@Autowired 
	private OrderExporter exporter;
	
	// TODO [1] make singleton; test multi-thread: state is [ | | | ]
	// TODO [2] instantiate manually, set dependencies, pass around; no AOP
	// TODO [3] prototype scope + ObjectFactory or @Lookup. Did you said "Factory"? ...
	// TODO [4] thread/request scope. HOW it works?! Leaks: @see SimpleThreadScope javadoc
	// TODO [5] (after AOP): RequestContext, @Cacheable. on thread?! @ThreadLocal
	public void run(String... args) throws Exception {
		new Thread(() -> exporter.export(Locale.ENGLISH)).start();
		new Thread(() -> exporter.export(Locale.FRENCH)).start();
		new Thread(() -> exporter.export(Locale.ENGLISH)).start();
	}
}

@Service
class OrderExporter  {
	private static final Logger log = LoggerFactory.getLogger(OrderExporter.class);
	private final InvoiceExporter invoiceExporter;
	private final LabelServiceFactory labelServiceFactory;

	public OrderExporter(InvoiceExporter invoiceExporter, LabelServiceFactory labelServiceFactory) {
		this.invoiceExporter = invoiceExporter;
		this.labelServiceFactory = labelServiceFactory;
	}

	public void export(Locale locale) {
		LabelService labelService = labelServiceFactory.createLabelService(locale);
		log.debug("Oare ce mi-a injectat Springul: " + labelService.getClass());
		log.debug("Running export in " + locale);

		log.debug("Origin Country: " + labelService.getCountryName("rO"));
		invoiceExporter.exportInvoice(labelService);
	}
}

@Service
class InvoiceExporter {
	private static final Logger log = LoggerFactory.getLogger(InvoiceExporter.class);

	public void exportInvoice(LabelService labelService) {
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}

@Service
class LabelServiceFactory {
	private final CountryRepo countryRepo;

	LabelServiceFactory(CountryRepo countryRepo) {
		this.countryRepo = countryRepo;
	}

	public LabelService createLabelService(Locale locale) {
		LabelService labelService = new LabelService();
		labelService.setCountryRepo(countryRepo);
		labelService.init();
		labelService.load(locale);
		return labelService;
	}
}

// NOT Thread safe!
//@Service
//@Scope(scopeName = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
class LabelService {
	private static final Logger log = LoggerFactory.getLogger(LabelService.class);
	private CountryRepo countryRepo;

	public void setCountryRepo(CountryRepo countryRepo) {
		this.countryRepo = countryRepo;
	}

	public void init() {
		System.out.println("TREBUIE CHEMATA INAINTE CA INSTANTA SA FIE USABLE");
	}

	private Map<String, String> countryNames;

	public void load(Locale locale) {
		log.debug("load() in instance: " + this.hashCode());
		countryNames = countryRepo.loadCountryNamesAsMap(locale);
	}

	public String getCountryName(String iso2Code) {
		log.debug("getCountryName() in instance: " + this.hashCode());
		return countryNames.get(iso2Code.toUpperCase());
	}
}