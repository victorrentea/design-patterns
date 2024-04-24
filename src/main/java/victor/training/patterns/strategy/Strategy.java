package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Map;

enum CountryEnum {
  RO, ES, FR, UK, CN//, CH
}


record Parcel(CountryEnum originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
  //	private Map<String, Class<? extends TaxCalculator>> calculators; // configured in application.properties 😮

  public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
    return selectCalculator(parcel.originCountry()).calculate(parcel);
  }

//  @Resource
//  Map<String, Class<? extends TaxCalculator>> CALCULATORS; // configured in application.properties 😮
  public TaxCalculator selectCalculator(CountryEnum originCountryIso) {
    // #2 switch is hard-coded=>     // Map<String/emnum, Class<?>> is still in the codeconfigured in application.properties 😮
    return switch (originCountryIso) {
      case UK -> new BrexitTaxCalculator();
      case CN -> new ChinaTaxCalculator();
      case FR, ES, RO -> new EUTaxCalculator();
      // in java 17 it is an anti-pattern to use default,
      // if your switch is on an enum and is used as an expression
//      default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountryIso);
//       #1 switch on a String is a code smell. enum!! no strings with fixed values should enter our core logic

    };
  }

  // A) Central decision: a map links iso -> impl = STRATEGY PATTERN
  // B) Decentralize decision: with implementation tells what it applies for = CHAIN OF RESPONSIBILITY PATTERN
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

