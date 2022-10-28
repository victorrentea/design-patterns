package victor.training.patterns.strategy;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum CountryEnum {
    RO(Calculators::calculateEU) {
//        @Override
//        public Double computeTax(Parcel parcel) {
//            // unexpected to have logic in enum
//            // inheretnly innevitably static
//            return null;
//        }
    },
    ES(Calculators::calculateEU),
    FR(Calculators::calculateEU),
    UK(Calculators::calculateUK),
    CN(Calculators::calculateChina);
    public final Function<Parcel, Double> function;

    CountryEnum(Function<Parcel, Double> function) {
        this.function = function;
    }

//    abstract public Double computeTax(Parcel parcel);

    // without usign a switch expression
}
