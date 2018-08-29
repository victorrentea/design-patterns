package victor.training.oo.creational.singleton;

import static victor.training.oo.stuff.Helper.workSomeTime;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;

@SpringBootApplication
public class SingletonPlayApp implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(SingletonPlayApp.class);
	}
	@Autowired
	private ReportExporter exporter;
	public void run(String... args) throws Exception {
		exporter.export();
	}
}

@Data
@Service
class ReportExporter  {
	private final ExportHelper helper;
	
	public void export() {
		System.out.println("Origin Country: " + new LabelService().getCountryName("RO")); 
		System.out.println("Dest Country: " + new LabelService().getCountryName("ES"));
		helper.writeSenderDetails();
	}
}

@Service 
class ExportHelper {
	public void writeSenderDetails() {
		System.out.println("Sender Country: " + new LabelService().getCountryName("RO"));
	}
}

class LabelService {
	private Map<Object, String> countries;
	
	public LabelService() {
		countries = CountryRepo.loadCountryNamesAsMap();
	}
	
	public String getCountryName(String isoCode) {
		return countries.get(isoCode);
	}

}

class CountryRepo {
	public static Map<Object, String> loadCountryNamesAsMap() {
		// connect to database, get data
		workSomeTime("Load country names");

		Map<Object, String> map = new HashMap<>();
		map.put("RO", "Romania");
		map.put("ES", "Spain");
		return map; 
	}
	
}