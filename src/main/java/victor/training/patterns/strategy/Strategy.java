package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

enum CountryEnum {
    RO, ES, FR, UK,CN
}


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
                return computeUKTax(parcel);
            case "CN":
                return computeChinaTax(parcel);
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return computeEUTax(parcel);
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
        }
    }

    private static double computeEUTax(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }

    private static double computeChinaTax(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }

    private static double computeUKTax(Parcel parcel) {
        // if
        // if
        // if
        // if
        // if
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}

