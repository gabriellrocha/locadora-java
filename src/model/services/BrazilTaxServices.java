package model.services;

public final class BrazilTaxServices {
	
	public static double tax(double amount) {
		if (amount <= 100) {
			return amount * 0.20;
		}
		else {
			return amount * 0.15;
		}
		
	}
}
