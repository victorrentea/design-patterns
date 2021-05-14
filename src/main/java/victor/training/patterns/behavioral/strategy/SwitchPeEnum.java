package victor.training.patterns.behavioral.strategy;

import lombok.SneakyThrows;

enum Country {
   RO(EUTaxCalculator.class),
   ES(EUTaxCalculator.class),
   FR(EUTaxCalculator.class),
   CN(ChinaTaxCalculator.class),
   UK(UKTaxCalculator.class), SK(null);

   private final Class<? extends TaxCalculator> calculatorClass;

   <T extends TaxCalculator> Country(Class<T> calculatorClass) {

      this.calculatorClass = calculatorClass;
   }

   public Class<? extends TaxCalculator> getCalculatorClass() {
      return calculatorClass;
   }
}

public class SwitchPeEnum {
   public static void main(String[] args) {


   }

   public void computeTax(Country country, double value) {
      TaxCalculator calculator = select(country);
   }

   @SneakyThrows
   private TaxCalculator select(Country country) {
//      spring.getBean(clasa)
      return country.getCalculatorClass().newInstance();
//      switch (country) {
//         case RO:
//         case ES:
//         case FR: return new EUTaxCalculator();
//         case CN: return new ChinaTaxCalculator();
//         case UK: return new UKTaxCalculator();
//
//         default:
//            throw new IllegalStateException("Unexpected value: " + country);
//      }
//      return switch (country) {
//         case RO, ES, FR, SK -> new EUTaxCalculator();
//         case CN -> new ChinaTaxCalculator();
//         case UK -> new UKTaxCalculator();
//      };
   }
}