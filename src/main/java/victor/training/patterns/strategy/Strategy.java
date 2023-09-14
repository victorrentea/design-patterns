package victor.training.patterns.strategy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static victor.training.patterns.strategy.Country.*;

enum CountryEnum {
  RO, ES, FR, UK, CN
}


interface TaxCalculator {
  List<Country> getCountry();

  double calculate(Parcel parcel);
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

  public double calculateCustomsTax(Parcel parcel) { // UGLY API we CANNOT change
    TaxCalculator taxCalculator = selectTaxCalculator(Country.valueOf(parcel.originCountry()));
    return taxCalculator.calculate(parcel);
  }

//    Map<String, TaxCalculator> mapateFrumos; // int performanta
//    @PostConstruct
//    public void init() {
//    }

  private TaxCalculator selectTaxCalculator(Country originCountry) {
    for (TaxCalculator taxCalculator : toate) {
      if (taxCalculator.getCountry().contains(originCountry)) {
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

@Service
class EUTaxCalculator implements TaxCalculator {
  private static double f(Parcel parcel) {
    // more logic
    return 0;
  }

  @Override
  public List<Country> getCountry() {
    return List.of(FR, ES, RO);
  }

  public double calculate(Parcel parcel) {
    // logica groasa
    return parcel.tobaccoValue() / 3 + f(parcel);
  }
}

@Service
class ChinaTaxCalculator implements TaxCalculator {
  @Override
  public List<Country> getCountry() {
    return List.of(CN);
  }

  public double calculate(Parcel parcel) {
    return parcel.tobaccoValue() + parcel.regularValue();
  }

}

@Service
class UKTaxCalculator implements TaxCalculator {
  @Override
  public List<Country> getCountry() {
    return List.of(UK);
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