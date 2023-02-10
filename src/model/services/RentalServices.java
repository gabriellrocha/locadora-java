package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {
	
	private double pricePerHour;
	private double pricePerDay;
	
	private TaxService taxService;

	public RentalServices(double pricePerHour, double pricePerDay, BrazilTaxServices taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	
	public TaxService getTaxServices() {
		return taxService;
	}

	public void setTaxServices(BrazilTaxServices taxServices) {
		this.taxService = taxServices;
	}

	public void processInvoice(CarRental carRental) {
		
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.0;
		
		double basicPayment;
		
		if (hours <= 12.0) {
			basicPayment = getPricePerHour() * Math.ceil(hours);
		}
		else {
			basicPayment = getPricePerDay() * Math.ceil(hours / 24.0);
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
