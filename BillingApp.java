package com.gnt.BillingSystem;

import java.text.ParseException;
import java.util.ArrayList;

import com.gnt.BillingSystem.POJO.Client;
import com.gnt.BillingSystem.POJO.Employee;
import com.gnt.BillingSystem.Utilities.Utils;

public class BillingApp {

	public static void main(String[] args) throws Exception {
		
		//arxikopoihsh enos client
		Client client = new Client(init());
		

		Utils.printCalculatedCostAmountPerDate(client);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		Utils.rankingReport(client);
	}

	public static Client init() throws ParseException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		//dhmiourgw ena tuxaio array apo employees
		employees = Utils.mockDataCreate();

		System.out.println(employees.get(0));
		Client client = new Client(employees);
		return client;
	}
}
