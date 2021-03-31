package software.robsoncassiano.exercices.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import software.robsoncassiano.exercices.model.entities.Contract;
import software.robsoncassiano.exercices.model.entities.Installments;
import software.robsoncassiano.exercices.model.services.ContractService;
import software.robsoncassiano.exercices.model.services.PaypalService;

public class Main {
  public static void main(String[] args) throws ParseException {
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Locale.setDefault(Locale.US);
    final Scanner scan = new Scanner(System.in);

    System.out.println("Enter contract data: ");
    System.out.println("Number: ");
    final Integer number = scan.nextInt();

    System.out.println("Date (dd/MM/yyyy): ");
    final Date date = simpleDateFormat.parse(scan.next());

    System.out.println("Contract value: ");
    final Double totalValue = scan.nextDouble();
    System.out.println("Enter number of installments");
    final int N = scan.nextInt();


    final Contract contract = new Contract(number, date, totalValue);

    ContractService cs = new ContractService(new PaypalService());

    cs.processContract(contract, N);

    System.out.println("installments: ");

    for (Installments it : contract.getInstallments()) {
      System.out.println(it);
    }

    scan.close();
  }
}
