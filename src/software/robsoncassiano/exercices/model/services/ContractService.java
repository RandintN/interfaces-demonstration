package software.robsoncassiano.exercices.model.services;

import java.util.Calendar;
import java.util.Date;
import software.robsoncassiano.exercices.model.entities.Contract;
import software.robsoncassiano.exercices.model.entities.Installments;

public class ContractService {
  private OnlinePaymentService onlinePaymentService;

  public ContractService(OnlinePaymentService onlinePaymentService) {
    this.onlinePaymentService = onlinePaymentService;
  }

  public void processContract(Contract contract, Integer months) {
    double basicQuota = contract.getTotalValue() / months;

    for (int i = 1; i <= months; i++) {
      double updateQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
      double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
      Date dueDate = addMonths(contract.getDate(), i);

      contract.getInstallments().add(new Installments(dueDate, fullQuota));
    }
  }

  private Date addMonths(Date contractDate, int monthsToBeAdded) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(contractDate);
    calendar.add(Calendar.MONTH, monthsToBeAdded);
    return calendar.getTime();
  }
}
