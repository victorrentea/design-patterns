package victor.training.patterns.strategy;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EUTaxCalculator implements TaxCalculator {
    public double calculate(double tobaccoValue, double regularValue_degeaba_NASPA) {
        // multa logica grea
        return tobaccoValue / 3;
    }

    @Override
    public boolean applicableFor(String originCountry) {
        return List.of("FR", "ES", "RO").contains(originCountry);
    }
}
