package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.RentalServices;

public class Program {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		Scanner input = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String modelo = input.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(input.nextLine(), fmt);
		LocalDateTime finish = LocalDateTime.parse(input.nextLine(), fmt);
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(modelo));
		
		System.out.println("Entre com o preço por hora: ");
		double pricePerHour = input.nextDouble();
		System.out.println("Entre com o preço por dia: ");
		double pricePerDay = input.nextDouble();
		
		RentalServices rentalServices = new RentalServices(pricePerHour, pricePerDay);
		rentalServices.processInvoice(carRental);
		
		System.out.println("Fatura: ");
		System.out.println("Pagamento básico: " + carRental.getInvoice().getBasicPayment());
		System.out.println("Imposto: ");
		input.close();
	}
	
}
