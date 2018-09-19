package victor.training.oo.creational.singleton;

import static victor.training.oo.stuff.Helper.workSomeTime;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SingletonSpringApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SingletonSpringApp.class);
	}
	@Autowired
	private ReportExporter exporter;
	public void run(String... args) throws Exception {
		exporter.export("en");
		exporter.export("es");
	}
}

@Service
class ReportExporter  {
	@Autowired
	private SenderDetailsWriter helper;
	@Autowired
	private LabelService labelService;
	
	public void export(String language) {
		labelService.init(language);
		System.out.println("Origin Country: " + labelService.getCountryName("RO")); 
		System.out.println("Dest Country: " + labelService.getCountryName("ES"));
		helper.writeSenderDetails();
	}
}

@Service 
class SenderDetailsWriter {
	@Autowired
	private LabelService labelService;
	public void writeSenderDetails() {
		System.out.println("Sender Country: " + labelService.getCountryName("RO"));
	}
}

@Service
class LabelService {
	@Autowired
	private CountryRepo countryRepo;
	private Map<Object, String> countries;
	
//	@PostConstruct
	public void init(String lang) {
		countries = countryRepo.loadCountryNamesAsMap(lang);
	}
	
	public String getCountryName(String isoCode) {
		return countries.get(isoCode);
	}
}

@Repository
class CountryRepo {
	public Map<Object, String> loadCountryNamesAsMap(String lang) {
		// connect to database, get data
		workSomeTime("Load country names");

		Map<Object, String> map = new HashMap<>();
		if (lang.equals("en")) {
			map.put("RO", "Romania");
			map.put("ES", "Spain");
		} else if (lang.equals("es")){
			map.put("RO", "Roumanie");
			map.put("ES", "Espana");
		}
		return map; 
	}
	
}