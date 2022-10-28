package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.Function;

record Parcel(String originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
    //	private Map<String, Class<? extends TaxCalculator>> calculators; // configured in application.properties 😮

    public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
        Function<Parcel,Double> taxCalculator = selectCalculator(CountryEnum.valueOf(parcel.originCountry()));
        return taxCalculator.apply(parcel);
    }

    // switch rules: 1) the only thing in its method. 2) each case: = 1 line 3) always default throw (unless java 17)
    private static Function<Parcel, Double> selectCalculator(CountryEnum originCountry) {
        // DON't EVER DO THIS!!!!!
        return originCountry.function;
//        return switch (originCountry) {
//            case UK -> Calculators::calculateUK;
//            case CN -> Calculators::calculateChina;
//            case FR, ES, RO -> Calculators::calculateEU;
//
//            // we cover with an exception for a bad input
//            // can we use the compiler to tell us that we missed a supported country ?
//        };
    }

//    public Void stupidHabbit(CountryEnum originCountry) {
//        return switch (originCountry) {
//            case UK -> {System.out.println("stuff to do "); yield null;}
//            case CN -> new ChinaTaxCalculator(); // other EU country codes...
//            case FR, ES, RO -> new EUTaxCalculator();
//
//            // we cover with an exception for a bad input
//            // can we use the compiler to tell us that we missed a supported country ?
//        };
//    }
}

class Calculators {
    public static double calculateUK(Parcel parcel) {
        // in fact ... 10 lines , 3 if statements
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
    public static double calculateEU(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }
    public static double calculateChina(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }
}
