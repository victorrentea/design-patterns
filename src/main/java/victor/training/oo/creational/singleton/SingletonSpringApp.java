package victor.training.oo.creational.singleton;

import java.util.HashMap;
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
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

@SpringBootApplication
public class SingletonSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SingletonSpringApp.class);
	}
	@Bean
	public static CustomScopeConfigurer defineThreadScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.addScope("thread", new SimpleThreadScope()); // WARNING. Leaks memory. Prefer 'request' scope or read here: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/support/SimpleThreadScope.html
		return configurer;
	}
	@Autowired 
	private ReportExporter exporter;
	
	// TODO [1] instantiate, set dependencies, pass around
	// TODO [2] make singleton. Then test multi-thread. State is ... 
	// TODO [3] ObjectFactory + prototype scope
	// TODO [4] thread/request scope. Leaks.
	public void run(String... args) throws Exception {
		new Thread(() ->exporter.export(Locale.FRENCH)).start();
		exporter.export(Locale.ENGLISH);
	}
}

@Slf4j
@Service
class ReportExporter  {
	@Autowired
	private InvoiceDetailsWriter helper;
	@Autowired
	private ObjectFactory<LabelService> labelServiceProvider;
	
	public void export(Locale locale) {
		LabelService labelService = labelServiceProvider.getObject();
		labelService.init(locale);
		log.debug("Origin Country: " + labelService.getCountryName("RO")); 
		helper.writeSenderDetails(labelService);
	}
}

@Slf4j
@Service 
class InvoiceDetailsWriter {
//	@Autowired
//	private LabelService labelService;
	
	public void writeSenderDetails(LabelService labelService) {
		log.debug("Invoice Country: " + labelService.getCountryName("ES"));
	}
}

@Service
//@Scope(scopeName = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope("prototype")
class LabelService {
	@Autowired
	private CountryRepo countryRepo;
	private Map<Object, String> countries;
	
//	@PostConstruct // ?
	public void init(Locale locale) {
		countries = countryRepo.loadCountryNamesAsMap(locale);
	}
	
	public String getCountryName(String isoCode) {
		return countries.get(isoCode);
	}
}

@Slf4j
@Repository
class CountryRepo {
	public Map<Object, String> loadCountryNamesAsMap(Locale locale) {
		// connect to database, get data. fake some latency
		log.debug("Loading country names for language: {} ...", locale );
		ThreadUtils.sleep(2000);
		log.debug("done");

		Map<Object, String> map = new HashMap<>();
		switch (locale.getLanguage()) {
		case "en":
			map.put("RO", "Romania");
			map.put("ES", "Spain");
			break;
		case "fr":
			map.put("RO", "Roumanie");
			map.put("ES", "Espagne");
			break;
		default: throw new IllegalArgumentException("best practice");
		}
		return map; 
	}
	
}