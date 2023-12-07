package modal.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

import modal.entities.Contract;
import modal.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;

	public ContractService(OnlinePaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {
		
		Double installmentBaseValue = contract.getTotalValue() / months;

		for(int i = 0; i < months; i++) {
			
			Double installmentInterestValue = paymentService.interest(installmentBaseValue, i + 1);
			Double installmentFeeValue = paymentService.paymentFee(installmentBaseValue + installmentInterestValue);
			Double amount = installmentBaseValue + installmentInterestValue + installmentFeeValue;
			
			LocalDate dueDate = contract.getDate().plusMonths(i + 1);
			Installment installment = new Installment(dueDate, amount);
			contract.getInstallments().add(installment);
		}
			
			
	}

}
