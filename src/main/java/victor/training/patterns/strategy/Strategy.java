package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

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
        TaxCalculator taxCalculator = selectTaxCalculator(parcel);
        return taxCalculator.calculate(parcel);
    }

//    private Function<Parcel, Double> selectTaxCalculator(Parcel parcel) {
    private TaxCalculator selectTaxCalculator(Parcel parcel) {
//        return switch (parcel.originCountry()) {
//            case "UK" -> ukTaxCalculator;
//            case "CN" -> chinaTaxCalculator; // other EU country codes...
//            case "FR", "ES", "RO" -> euTaxCalculator;
//            default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
//        };
        switch (parcel.originCountry()) {
            case "UK":
                return new TaxCalculator() { // puteam din java2, dar era scarbos
                  @Override
                  public double calculate(Parcel parcel1) {
                    return ukTaxCalculator.calculate(parcel1);
                  }
                };
            case "CN":
                return chinaTaxCalculator::calculate; // syntactic sugar, miere si lapte
            case "FR":
            case "ES": // other EU country codes...
            case "RO":
                return p -> euTaxCalculator.calculate(p);
            default:
                throw new IllegalArgumentException("Not a valid country ISO2 code: " + parcel.originCountry());
        }
    }
}
@FunctionalInterface // adica vreao sa o iau ƛ
interface TaxCalculator {
    double calculate(Parcel parcel/*, de evitat: Map<ParamEnum, Object> ceVreaMuschiu*/);
}
@Service
class EUTaxCalculator {
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
class ChinaTaxCalculator {
    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }

}
@Service
class UKTaxCalculator {
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