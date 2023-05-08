package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import victor.training.patterns.strategy.UKTaxCalculator.ChinaTaxCalculator;
import victor.training.patterns.strategy.UKTaxCalculator.EUTaxCalculator;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

enum CountryEnum {
    RO, ES, FR, UK,CN
}


record Parcel(String originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {

    public double calculateCustomsTax(Parcel parcel) {
        TaxCalculator calculator = selectCalculator(parcel.originCountry());
        return calculator.calculate(parcel); // polymorphic call
    }
Map<List<String>, Class<? extends TaxCalculator>> map;
    public static TaxCalculator selectCalculator(String originCountry) {
//        List<TaxCalculator> toate = List.of(new ChinaTaxCalculator(), new UKTaxCalculator(), new EUTaxCalculator());
        switch (originCountry) {
            case "UK":
                return new UKTaxCalculator();
            case "CN":
                return new ChinaTaxCalculator();
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return new EUTaxCalculator();
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
        }
        
    }
}
interface TaxCalculator {
    double calculate(Parcel parcel);
}
class UKTaxCalculator implements TaxCalculator {
    public double calculate(Parcel parcel) {
        // logica x 20+ linii de cod
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}





class ChinaTaxCalculator implements TaxCalculator {
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }
}
class EUTaxCalculator implements TaxCalculator {
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }
}

