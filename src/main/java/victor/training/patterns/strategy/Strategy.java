package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    // The taxes to apply to China will change starting Nov 1, 2022
    private  TaxCalculator selectCalculator(Parcel parcel) {
        for (TaxCalculator taxCalculator : taxCalculators) {
            if (taxCalculator.appliesFor(parcel)) {
                return taxCalculator;
            }
        }
        throw new IllegalArgumentException("Not supported parcel");
    }

    @Autowired
    private List<TaxCalculator> taxCalculators;
}
interface TaxCalculator {
    boolean appliesFor(Parcel parcel);
    double calculate(Parcel parcel);
}

@Service
class BrexitTaxCalculator implements TaxCalculator{
    @Override
    public boolean appliesFor(Parcel parcel) {
        return "UK".equals(parcel.originCountry());
    }

    public double calculate(Parcel parcel) {
        // in fact ... 40 lines , 12 if statements
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}
@Service
class EUTaxCalculator implements TaxCalculator{
    @Override
    public boolean appliesFor(Parcel parcel) {
        return List.of("FR", "ES", "RO").contains(parcel.originCountry());
    }

    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }
}
@Service
class ChinaTaxCalculator implements TaxCalculator{
    @Override
    public boolean appliesFor(Parcel parcel) {
        return "CN".equals(parcel.originCountry()) && parcel.date().isBefore(LocalDate.parse("2022-11-01"));
    }

    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }
}
class ChinaTaxCalculatorSince2022Nov implements TaxCalculator{
    @Override
    public boolean appliesFor(Parcel parcel) {
        return "CN".equals(parcel.originCountry()) && !parcel.date().isBefore(LocalDate.parse("2022-11-01"));
    }

    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue()  + 1; // different !
    }
}
