package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

enum CountryEnum {
  RO, ES, FR, UK, CN
}


record Parcel(String originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
  //	private Map<String, Class<? extends TaxCalculator>> calculators; // configured in application.properties 😮

  public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
    return selectCalculator(parcel.originCountry()).calculate(parcel);
  }

  public TaxCalculator selectCalculator(String originCountryIso) {
    return switch (originCountryIso) {
      case "UK" -> new BrexitTaxCalculator();
      case "CN" -> new ChinaTaxCalculator();
      case "FR", "ES", "RO" -> new EUTaxCalculator();
      default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountryIso);
    };
  }

  // A) a map links iso -> impl = STRATEGY PATTERN
  // B) each implementation tells what it applies for = CHAIN OF RESPONSIBILITY PATTERN
  // C) switch(enum)
}
interface TaxCalculator {
//  boolean isApplicable(Parcel parcel); >>>> Chain of Responsibility pattern
  double calculate(Parcel parcel);
}
class BrexitTaxCalculator implements TaxCalculator{
  public double calculate(Parcel parcel) {
    // COMPLEXITY
    return parcel.tobaccoValue() / 2 + parcel.regularValue();
  }
}

class ChinaTaxCalculator implements TaxCalculator{
  public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() + parcel.regularValue();
  }
}

class EUTaxCalculator implements TaxCalculator{
   public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() / 3;
  }
}

