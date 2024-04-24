package victor.training.patterns.strategy;

import jakarta.inject.Inject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

enum CountryEnum {
  RO, ES, FR, UK, CN//, CH
}


record Parcel(CountryEnum originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
  public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
    return selectCalculator(parcel.originCountry()).calculate(parcel);
  }

//  @Resource
//  Map<String, Class<? extends TaxCalculator>> CALCULATORS; // configured in application.properties 😮

  //@Inject// if I had CDI on
  private List<TaxCalculator> allCalculators = List.of(new BrexitTaxCalculator(), new ChinaTaxCalculator(), new EUTaxCalculator());

  public TaxCalculator selectCalculator(CountryEnum originCountryIso) {
    // makes the code more misterious
    // - what if 2 calculators apply?
    //  a) which wins? the first? oups....??!?!#!@#$!%! the order matters
      ///    please imagine a "DefaultTaxCalculator" that applies to all "other"
    //  b) should I run both ?! think: web.xml filters, Spring Security filters
    // - the exception is back (we killed it with swithc expression(enum))
    return allCalculators.stream()
        .filter(calculator -> calculator.isApplicable(originCountryIso))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Not a valid country ISO2 code: " + originCountryIso));

    // #2 switch is hard-coded=>     // Map<String/emnum, Class<?>> is still in the codeconfigured in application.properties 😮
//    return switch (originCountryIso) {
//      case UK -> new BrexitTaxCalculator();
//      case CN -> new ChinaTaxCalculator();
//      case FR, ES, RO -> new EUTaxCalculator();
//    };


      // in java 17 it is an anti-pattern to use default,
      // if your switch is on an enum and is used as an expression
//      default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountryIso);
//       #1 switch on a String is a code smell. enum!! no strings with fixed values should enter our core logic
  }

  // A) Central decision: a map links iso -> impl = STRATEGY PATTERN
  // B) Decentralize decision: with implementation tells what it applies for = CHAIN OF RESPONSIBILITY PATTERN
}
interface TaxCalculator {
  boolean isApplicable(CountryEnum originCountry); // >>>> Chain of Responsibility pattern
  double calculate(Parcel parcel);
}
class BrexitTaxCalculator implements TaxCalculator{
  @Override
  public boolean isApplicable(CountryEnum originCountry) {
    return originCountry == CountryEnum.UK;
  }

  public double calculate(Parcel parcel) {
    // COMPLEXITY
    return parcel.tobaccoValue() / 2 + parcel.regularValue();
  }
}

class ChinaTaxCalculator implements TaxCalculator{
  @Override
  public boolean isApplicable(CountryEnum originCountry) {
    return originCountry == CountryEnum.CN;
  }

  public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() + parcel.regularValue();
  }
}

class EUTaxCalculator implements TaxCalculator{
  @Override
  public boolean isApplicable(CountryEnum originCountry) {
    return originCountry == CountryEnum.FR
//           || originCountry == CountryEnum.UK << this ruins everyhing
           || originCountry == CountryEnum.ES
           || originCountry == CountryEnum.RO;
  }

  public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() / 3;
  }
}

// @Order(MAX_INT)
//class DefaultTaxCalculator implements TaxCalculator{
//  @Override
//  public boolean isApplicable(CountryEnum originCountry) {
//    return true;
//  }
//  public double calculate(Parcel parcel) {
//    return parcel.tobaccoValue() + parcel.regularValue();
//  }
//}

// OCP applied=+1 more country = +1 more class