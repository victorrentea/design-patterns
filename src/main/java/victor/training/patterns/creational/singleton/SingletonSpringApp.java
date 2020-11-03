package victor.training.patterns.creational.singleton;

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

import javax.annotation.PostConstruct;

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
	
	// TODO [1] state in a singleton is DANGEROUS https://rules.sonarsource.com/java/RSPEC-3749?search=injected
	// TODO [2] instantiate manually, set dependencies, pass around; no AOP
	// TODO [3] prototype scope + ObjectFactory or @Lookup. Did you say "Factory"? ...
	// TODO [4] thread/request scope. HOW it works?! Leaks: @see SimpleThreadScope javadoc
	// TODO [5] (after AOP): RequestContext, @Cacheable. on thread?! @ThreadLocal
	public void run(String... args) {
		exporter.export(Locale.ENGLISH);
//		exporter.export(Locale.FRENCH);
	}
}

@Service
class OrderExporter  {
	private static final Logger log = LoggerFactory.getLogger(OrderExporter.class);
	private final InvoiceExporter invoiceExporter;
	private final LabelService labelService;

	public OrderExporter(InvoiceExporter invoiceExporter, LabelService labelService) {
		this.invoiceExporter = invoiceExporter;
		this.labelService = labelService;
	}

	public void export(Locale locale) {
		log.debug("Running export in " + locale);
		log.debug("Origin Country: " + labelService.getCountryName("rO")); 
		invoiceExporter.exportInvoice();
	}
}

@Service
class InvoiceExporter {
	private static final Logger log = LoggerFactory.getLogger(InvoiceExporter.class);
	private final LabelService labelService;

	public InvoiceExporter(LabelService labelService) {
		this.labelService = labelService;
	}

	public void exportInvoice() {
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}

@Service
class LabelService {
	private static final Logger log = LoggerFactory.getLogger(LabelService.class);
	private final CountryRepo countryRepo;
	private Map<String, String> countryNames;

	public LabelService(CountryRepo countryRepo) {
		this.countryRepo = countryRepo;
	}

	@PostConstruct
	public void load() {
		log.debug("load() map in instance: " + this.hashCode());
		countryNames = countryRepo.loadCountryNamesAsMap(Locale.ENGLISH);
	}
	
	public String getCountryName(String iso2Code) {
		log.debug("getCountryName() in instance: " + this.hashCode());
		sleepr();
		return countryNames.get(iso2Code.toUpperCase());
	}
}