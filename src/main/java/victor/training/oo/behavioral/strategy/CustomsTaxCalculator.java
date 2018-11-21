package victor.training.oo.behavioral.strategy;

interface CustomsTaxCalculator {

	double calculateTax(double tobacoValue, double regularValue);

	boolean accept(String originCountry);

}