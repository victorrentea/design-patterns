package victor.training.oo.creational.singleton;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class SingletonSpringApp implements CommandLineRunner{
	@Bean
	public static CustomScopeConfigurer defineThreadScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("thread", new SimpleThreadScope()); // WARNING. Leaks memory. Prefer 'request' scope or read here: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/SimpleThreadScope.html
		return configurer;
	}
	public static void main(String[] args) {
		SpringApplication.run(SingletonSpringApp.class);
	}
	
	@Autowired 
	private ReportExporter exporter;
	
	// TODO [1] instantiate manually, set dependencies, pass around
	// TODO [2] make singleton; test multi-thread: state is [ | | | ]
	// TODO [3] ObjectFactory + prototype scope; autowiring myself in cntr: no AOP
	// TODO [4] thread/request scope. HOW it works?! Leaks.
	// TODO [5] (after AOP): @Cacheable on thread?!
	public void run(String... args) throws Exception {
		exporter.export(Locale.FRENCH);
		exporter.export(Locale.ENGLISH);
	}
}

@Slf4j
@Service
class ReportExporter  {
	@Autowired
	private InvoiceDetailsWriter helper;
	@Autowired
	private CountryRepo countryRepo;
	
	public void export(Locale locale) {
		LabelService labelService = new LabelService(countryRepo);
		labelService.loadFor(locale);
		log.debug("Origin Country: " + labelService.getCountryName("RO")); 
		helper.writeSenderDetails(labelService);
	}
}

@Slf4j
@Service 
class InvoiceDetailsWriter {
	
	public void writeSenderDetails(LabelService labelService) {
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}

class LabelService {
	private final CountryRepo countryRepo;
	public LabelService(CountryRepo countryRepo) {
		this.countryRepo = countryRepo;
	}

	private Map<String, String> countryNames;
	
	// a sort of @PostConstruct .. parameterized ?!
	public void loadFor(Locale locale) {
		countryNames = countryRepo.loadCountryNamesAsMap(locale);
	}
	
	public String getCountryName(String iso2Code) {
		return countryNames.get(iso2Code.toUpperCase());
	}
}