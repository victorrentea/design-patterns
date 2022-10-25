package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

record Parcel(String originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
    //	private Map<String, Class<? extends TaxCalculator>> calculators; // configured in application.properties 😮

    public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
        switch (parcel.originCountry()) {
            case "UK":
                return parcel.tobaccoValue() / 2 + parcel.regularValue();
            case "CN":
                return parcel.tobaccoValue() + parcel.regularValue();
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return parcel.tobaccoValue() / 3;
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
        }
    }
}
