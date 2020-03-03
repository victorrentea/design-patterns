package victor.training.oo.behavioral.strategy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class StrategySpringApp implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(StrategySpringApp.class)
                .profiles("localProps")
                .run(args);
    }


    private ConfigProvider configProvider = new ConfigFileProvider();

    // TODO [1] Break CustomsService logic into Strategies
    // TODO [2] Convert it to Chain Of Responsibility
    // TODO [3] Wire with Spring
    // TODO [4] ConfigProvider: selected based on environment props, with Spring
    public void run(String... args) {
        CustomsService service = new CustomsService();
        System.out.println("Tax for (RO,100,100) = " + service.computeCustomsTax("RO", 100, 100));
        System.out.println("Tax for (CN,100,100) = " + service.computeCustomsTax("CN", 100, 100));
        System.out.println("Tax for (UK,100,100) = " + service.computeCustomsTax("UK", 100, 100));

        System.out.println("Property: " + configProvider.getProperties().getProperty("someProp"));
    }
}

class CustomsService {
    public double computeCustomsTax(String originCountry, double tobaccoValue, double regularValue) { // UGLY API we CANNOT change
        TaxComputer computer = selectComputer(originCountry);
        return computer.compute(tobaccoValue, regularValue);
    }
	private static List<TaxComputer> taxComputers = asList(
			new UKTaxComputer(),
			new ChinaTaxComputer(),
			new EUTaxComputer());
    private TaxComputer selectComputer(String originCountry) {

        for (TaxComputer taxComputer : taxComputers) {
            if (taxComputer.accepts(originCountry)) {
                return taxComputer;
            }
        }
        throw new IllegalStateException("Unexpected value: " + originCountry);
    }
//	enum E {
//		A,B,C
//	}
//	public String m(E e) {
//	    return switch (e) {
//			case A ->"a";
//			case B->"b";
//			case C -> "c";
//		};
//	}
}

interface TaxComputer {
    boolean accepts(String originCountry);

    double compute(double tobaccoValue, double regularValue);
}

class UKTaxComputer implements TaxComputer {
	@Override
	public boolean accepts(String originCountry) {
		return "UK".equals(originCountry);
	}

	public double compute(double tobaccoValue, double regularValue) {
        // Gigel: pun si io aicea astea 200de linii de cod si un if intautru si paote alt switch inauntru
        return tobaccoValue / 2 + regularValue;
    }
}

class ChinaTaxComputer implements TaxComputer {
	@Override
	public boolean accepts(String originCountry) {
		return "CN".equals(originCountry);
	}

	public double compute(double tobaccoValue, double regularValue) {
        return tobaccoValue + regularValue;
    }
}

class ChinaExtraLogic {
    private final ChinaTaxComputer computer;

    ChinaExtraLogic(ChinaTaxComputer computer) {
        this.computer = computer;
    }

    public void logicaExtra() {

    }
}

class EUTaxComputer implements TaxComputer {
	@Override
	public boolean accepts(String originCountry) {
		return asList("RO","ES","FR").contains(originCountry);
	}

	@Override
    public double compute(double tobaccoValue, double regularValue) {
        // Maricica:
        return tobaccoValue / 3;
    }
}
