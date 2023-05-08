package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    @Autowired
    private List<TaxCalculator> taxCalculators; // iti aduna toate implementarile de care stie ale interfetei aleia

    public TaxCalculator selectCalculator(String originCountry) {
        return taxCalculators.stream()
                .filter(c -> c.supports(originCountry))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry));
    }
}
interface TaxCalculator {
    boolean supports(String originCountry); // mai bine incapsulata. zice doar da/nu OCP
    double calculate(Parcel parcel);
}
@Component
class UKTaxCalculator implements TaxCalculator {
    @Override
    public boolean supports(String originCountry) {
        return "UK".equals(originCountry);
    }

    public double calculate(Parcel parcel) {
        // logica x 20+ linii de cod
        return parcel.tobaccoValue() / 2 + parcel.regularValue();
    }
}
@Component
class ChinaTaxCalculator implements TaxCalculator {
    @Override
    public boolean supports(String originCountry) {
        return "CN".equals(originCountry);
    }

    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() + parcel.regularValue();
    }
}
@Component
class EUTaxCalculator implements TaxCalculator {
    @Override
    public boolean supports(String originCountry) {
        return List.of("RO", "ES", "FR").contains(originCountry);
    }

    public double calculate(Parcel parcel) {
        return parcel.tobaccoValue() / 3;
    }
}

