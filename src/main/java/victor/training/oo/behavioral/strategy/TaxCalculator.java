package victor.training.oo.behavioral.strategy;// SOLUTION

import static java.util.Arrays.asList;

import org.springframework.stereotype.Component;

public interface TaxCalculator {
	double computeTax(double tobacoValue, double regularValue);
	boolean isApplicable(String country);
}

@Component
class ChinaTaxCalculator implements TaxCalculator {
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue + regularValue;
	}
	public boolean isApplicable(String country) {
		return "CH".equals(country);
	}
}

@Component
class UKTaxCalculator implements TaxCalculator {
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue/2 + regularValue/2;
	}
	public boolean isApplicable(String country) {
		return "UK".equals(country);
	}
}

@Component
class EUTaxCalculator implements TaxCalculator {
	public double computeTax(double tobacoValue, double regularValue) {
		return tobacoValue/3;
	}
	public boolean isApplicable(String country) {
		return asList("RO","ES","FR").contains(country);
	}
}


