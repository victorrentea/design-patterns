package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static victor.training.patterns.strategy.Country.*;

enum CountryEnum {
  RO, ES, FR, UK, CN
}


record Parcel(String originCountry, double tobaccoValue, double regularValue, LocalDate date) {
}

// TODO pot adauga tari noi fara impact in cod (OCP)

@Service
@Data
@ConfigurationProperties(prefix = "customs")
class CustomsService {
  //	private Map<String, Class<? extends TaxCalculator>> calculators; // configured in application.properties 😮


  //    private final UKTaxCalculator ukTaxCalculator;
//    private final ChinaTaxCalculator chinaTaxCalculator;
//    private final EUTaxCalculator euTaxCalculator;
  private final List<TaxCalculator> toate;
  private Map<Country, Class<? extends TaxCalculator>> calculators;

  public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
    TaxCalculator taxCalculator = selectTaxCalculator(Country.valueOf(parcel.originCountry()));
    return taxCalculator.calculate(parcel);
  }

//    Map<String, TaxCalculator> mapateFrumos; // int performanta
//    @PostConstruct
//    public void init() {
//    }
private final ApplicationContext spring;
  private TaxCalculator selectTaxCalculator(Country originCountry) {
//    TaxCalculator calculator = spring.getBean(calculators.get(originCountry));
//    return calculator;
//
    for (TaxCalculator taxCalculator : toate) {
      if (taxCalculator.supports(originCountry)) {
        return taxCalculator;
      }
    }
    throw new IllegalArgumentException();
  }
//        return switch (originCountry) { // a la java 17
//            case UK -> ukTaxCalculator;
//            case CN -> chinaTaxCalculator; // other EU country codes...
//            case FR, ES, RO -> euTaxCalculator;
//           // antipattern in java17 sa mai pui  default -> throw new IllegalArgumentException("Not a valid country ISO2 code: " + originCountry);
//            // daca ai facut return switch(enum)
//        };

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

interface TaxCalculator {
  //  List<Country> getCountry();
  boolean supports(Country country);

  double calculate(Parcel parcel);
}

@Service
class EUTaxCalculator implements TaxCalculator {
  private static double f(Parcel parcel) {
    // more logic
    return 0;
  }

  @Override
  public boolean supports(Country country) {
    return List.of(FR, ES, RO).contains(country);
  }

  public double calculate(Parcel parcel) {
    // logica groasa
    return parcel.tobaccoValue() / 3 + f(parcel);
  }
}

@Service
class ChinaTaxCalculator implements TaxCalculator {
  @Override
  public boolean supports(Country country) {
    return country == CN;
  }

  public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() + parcel.regularValue();
  }

}

@Service
class UKTaxCalculator implements TaxCalculator {
  @Override
  public boolean supports(Country country) {
    return country == UK;
  }

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