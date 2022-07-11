package victor.training.patterns.strategy;

import org.springframework.stereotype.Component;

@Component
public class UKTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue) {
        // multa logica grea
        return tobaccoValue / 2 + regularValue;
    }

    @Override
    public boolean applicableFor(String originCountry) {
        return "UK".equals(originCountry);
    }
}
