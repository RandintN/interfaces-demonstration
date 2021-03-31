package software.robsoncassiano.learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import software.robsoncassiano.learn.model.entities.CarRental;
import software.robsoncassiano.learn.model.entities.Vehicle;
import software.robsoncassiano.learn.model.service.BrazilTaxService;
import software.robsoncassiano.learn.model.service.RentalService;
import software.robsoncassiano.learn.model.service.TaxService;
import software.robsoncassiano.learn.model.service.UsaTaxService;

public class Main {

    public static void main(String[] args) throws ParseException {
	    Locale.setDefault(Locale.US);
        final Scanner scanner = new Scanner(System.in);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data");
        System.out.println("Car model: ");
        final String carModel = scanner.nextLine();

        System.out.println("Pickup (dd/MM/yyyy HH:ss): ");
        final Date start = sdf.parse(scanner.nextLine());

        System.out.println("Pickup (dd/MM/yyyy HH:ss): ");
        final Date finish = sdf.parse(scanner.nextLine());

        final CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));

        System.out.println("Enter price per hour");
        final double pricePerHour = scanner.nextDouble();
        System.out.println("Enter price per day");
        final double pricePerDay = scanner.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new UsaTaxService());

        rentalService.processInvoice(carRental);
        System.out.println("INVOICE");
        System.out.println("Basic payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", carRental.getInvoice().getTax()));
        System.out.println("Total Payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));

        scanner.close();
    }
}
