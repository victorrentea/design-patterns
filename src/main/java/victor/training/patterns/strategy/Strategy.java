package victor.training.patterns.strategy;

import lombok.Data;
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

    private final UKTaxCalculator ukTaxCalculator;
    private final ChinaTaxCalculator chinaTaxCalculator;
    private final EUTaxCalculator euTaxCalculator;

    public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
        TaxCalculator taxCalculator = selectTaxCalculator(Country.valueOf(parcel.originCountry()));
        return taxCalculator.calculate(parcel);
    }

    private TaxCalculator selectTaxCalculator(Country originCountry) {
        return switch (originCountry) { // a la java 17
            case UK -> ukTaxCalculator;
            case CN -> chinaTaxCalculator; // other EU country codes...
            case FR, ES, RO -> euTaxCalculator;
           // antipattern in java17 sa mai pui  default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
            // daca ai facut return switch(enum)
        };
//        switch (parcel.originCountry()) {
//            case "UK":
//                return ukTaxCalculator;
//            case "CN":
//                return chinaTaxCalculator;
//            case "FR":
//            case "ES": // other EU country codes...
//            case "RO":
//                return euTaxCalculator;
//            default:
//                throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
//        }
    }
}
interface TaxCalculator {
    double calculate(Parcel parcel);
}
@Service
class EUTaxCalculator implements TaxCalculator{
    public double calculate(Parcel parcel) {
        // logica groasa
        return parcel.tobaccoValue() / 3 +f(parcel);
    }
    private static double f(Parcel parcel) {
        // more logic
        return 0;
    }
}
@Service
class ChinaTaxCalculator implements TaxCalculator{
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }

}
@Service
class UKTaxCalculator implements TaxCalculator{
   public double calculate(Parcel parcel) {
        // if
        // if
        // if
        // if
        // if
        // if
        // if
        // if
        // if
        // if
        // if
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}