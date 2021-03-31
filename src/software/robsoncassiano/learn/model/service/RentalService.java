package software.robsoncassiano.learn.model.service;

import org.jetbrains.annotations.NotNull;
import software.robsoncassiano.learn.model.entities.CarRental;
import software.robsoncassiano.learn.model.entities.Invoice;

public class RentalService {
  private Double pricePerDay;
  private Double pricePerHour;

  private TaxService taxService;

  public RentalService() {
  }

  public RentalService(Double pricePerDay,
                       Double pricePerHour,
                       TaxService taxService) {
    this.pricePerDay = pricePerDay;
    this.pricePerHour = pricePerHour;
    this.taxService = taxService;
  }

  public void processInvoice(@NotNull CarRental carRental) {
    final long startTime = carRental.getStart().getTime();
    final long endTime = carRental.getFinish().getTime();

    final double hours = (double) (startTime - endTime) / 1000 / 60 / 60;

    double basicPayment;
    if (hours <= 12) {
      basicPayment = Math.ceil(hours) * pricePerHour;
    } else {
      basicPayment = Math.ceil(hours / 24) * pricePerDay;
    }

    final double tax = taxService.tax(basicPayment);
    carRental.setInvoice(new Invoice(basicPayment, tax));
  }
}
