package victor.training.oo.creational.singleton;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

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
	
	@Inject
	private OrderExporter exporter;
	
	// TODO [1] make singleton; test multi-thread: state is [ | | | ]
	// TODO [2] instantiate manually, set dependencies, pass around; no AOP
	// TODO [3] prototype scope + ObjectFactory or @Lookup. Did you said "Factory"? ...
	// TODO [4] thread/request scope. HOW it works?! Leaks: @see SimpleThreadScope javadoc
	// TODO [5] (after AOP): RequestContext, @Cacheable. on thread?! @ThreadLocal
	public void run(String... args) throws Exception {
		new Thread(() -> exporter.export(Locale.ENGLISH)).start();
		new Thread(() -> exporter.export(Locale.FRENCH)).start();
	}
}

@Slf4j
@Named
class OrderExporter  {
	@Inject
	private InvoiceExporter invoiceExporter;
	@Inject
	private Provider<LabelService> labelServiceProvider;

	public void export(Locale locale) {
		LabelService labelService = labelServiceProvider.get(); // this forces the container to GET you a fresh instance each time
		log.debug("Running export in " + locale);
		labelService.load(locale);
		log.debug("Origin Country: " + labelService.getCountryName("rO")); 
		invoiceExporter.exportInvoice(labelService);
	}
}
@Slf4j
@Named
class InvoiceExporter {
	public void exportInvoice(LabelService labelService) {
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}
@Slf4j
// these two, because I'm faking JavaEE in SPring
@Named
@Scope("prototype") // wiping this out ~= adding @Singleton
class LabelService {
	@Inject
	private CountryRepo countryRepo;

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