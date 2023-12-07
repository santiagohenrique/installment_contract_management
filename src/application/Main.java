package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import modal.entities.Contract;
import modal.entities.Installment;
import modal.services.ContractService;
import modal.services.OnlinePaymentService;
import modal.services.PaypalService;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		List<Installment> installments = new ArrayList<>();
		
		System.out.println("-- Enter the contract data -- ");
		System.out.print("Number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/mm/yyyy): ");
		LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		sc.nextLine();

		Contract contract = new Contract(number, date, totalValue);
	
		System.out.print("Enter the number of installments: ");
		int numberOfInstallments = sc.nextInt();
		
		ContractService service = new ContractService(new PaypalService());
		service.processContract(contract, numberOfInstallments);
		
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}

	}

}
