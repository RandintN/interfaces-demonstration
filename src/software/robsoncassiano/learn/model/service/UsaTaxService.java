package software.robsoncassiano.learn.model.service;

public class UsaTaxService implements TaxService{
  public double tax(double amount) {
    if (amount <= 100) {
      return amount * 0.10;
    } else {
      return amount * 0.05;
    }
  }
}
