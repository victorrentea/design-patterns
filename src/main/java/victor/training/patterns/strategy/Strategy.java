package victor.training.patterns.strategy;

import lombok.Data;
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
        TaxCalculator taxCalculator = selectCalculator(parcel);
        return taxCalculator.calculate(parcel);
    }

    // switch rules: 1) the only thing in its method. 2) each case: = 1 line 3) always default throw (unless java 17)
    private static TaxCalculator selectCalculator(Parcel parcel) {
        // DON't EVER DO THIS!!!!!
        switch (parcel.originCountry()) {
            case "UK":
                return new BrexitTaxCalculator();
            case "CN":
                return new ChinaTaxCalculator();
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return new EUTaxCalculator();
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
        }
    }
}
interface TaxCalculator {
    double calculate(Parcel parcel);
}

class BrexitTaxCalculator implements TaxCalculator{
    public double calculate(Parcel parcel) {
        // in fact ... 40 lines , 12 if statements
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}
class EUTaxCalculator implements TaxCalculator{
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }
}
class ChinaTaxCalculator implements TaxCalculator{
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }
}
