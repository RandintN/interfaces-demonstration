package software.robsoncassiano.exercices.model.services;

public class PagseguroService implements OnlinePaymentService{

  private static final double PAYMENT_FEE = 0.03;
  private static final double MONTHLY_INTEREST = 0.2;

  @Override
  public double paymentFee(Double amount) {
    return amount * PAYMENT_FEE;
  }

  @Override
  public double interest(Double amount, Integer months) {
    return amount * months * MONTHLY_INTEREST;
  }
}

