package modal.services;

import java.time.LocalDate;

import modal.entities.Installment;

public class PaypalService implements OnlinePaymentService {

	@Override
	public double paymentFee(Double amount) {

		double fee = 0.02;
		double feeValue = amount * fee;

		return feeValue;
	}

	@Override
	public double interest(Double amount, Integer months) {

		double tax = 0.01;
		double interestTax = (amount * tax) * months;

		return interestTax;

	}

}
