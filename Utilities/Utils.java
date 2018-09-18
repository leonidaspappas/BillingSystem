package com.gnt.BillingSystem.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import com.gnt.BillingSystem.POJO.Client;
import com.gnt.BillingSystem.POJO.Constants;
import com.gnt.BillingSystem.POJO.Employee;

public final class Utils {
	@SuppressWarnings("unused")
	private static void equalsEnum(String str) throws Exception {

		if (!Constants.enumContains(str)) {
			throw new Exception("Den periexete");
		} else {
			System.out.println("Periexete");
		}

	}

	public static void printCalculatedCostAmountPerDate(Client client) {
		// elegxos empty list
		terminateIfClientHasNoEmployees(client);

		String jobTitle = "";
		// a for loop for getting sorted for both I and O employees
		for (int index = 0; index < Constants.employeeJobTitle.values().length; index++) {
			// assing jobTitle with the current index of enum. Overloaded toString()
			jobTitle = Constants.employeeJobTitle.values()[index].toString();

			ArrayList<Employee> employeesWithDesiredJobTitle = new ArrayList<Employee>();
			employeesWithDesiredJobTitle = getEmployeesOfDesiredJobTitle(client.getEmployees(), jobTitle);

			ArrayList<Date> dates = new ArrayList<Date>();
			ArrayList<Double> sumOfDates = new ArrayList<Double>();

			dates = getDistinctDates(employeesWithDesiredJobTitle);
			if (dates.isEmpty()) {
				System.out.println("There are no dates of this " + jobTitle);
				return;
			}
			// ftiaxnw ta sum gia to kathe date
			sumOfDates = getSumOfDates(employeesWithDesiredJobTitle, dates);

			System.out.println("Sum per Date for " + jobTitle + " is bellow");
			printSumOfDates(dates, sumOfDates);

		}

	}

	// all the bellow methods are for ranking grouped by employees' id and based on
	
	public static void rankingReport(Client client) {
		// if Client's employee are not filled it terminates the program
		terminateIfClientHasNoEmployees(client);
		String jobTitle = "";
		// a for loop for getting sorted for both I and O employees
		for (int index = 0; index < Constants.employeeJobTitle.values().length; index++) {

			// Assign jobTitle with the current index of enum. Overloaded toString()
			jobTitle = Constants.employeeJobTitle.values()[index].toString();
			System.out.println("Ranking report for " + jobTitle + " job title is below");
			// creates an ArrayList of employee with the desired jobTitle
			ArrayList<Employee> employeesWithJobTitle = new ArrayList<Employee>();
			employeesWithJobTitle = getEmployeesOfDesiredJobTitle(client.getEmployees(), jobTitle);
			// create a list with all the employees id from the clients list with desired
			// jobTitle
			ArrayList<String> employeesId = new ArrayList<String>();
			employeesId = getEmployeesId(employeesWithJobTitle);
			// create an array with distinct id values (it does not return duplicate values)
			String[] employeesIdDistinct = getEmployeesIdWithDistinctValues(employeesId);
			// initialize a list with distinct keys (id) and values (sum) that are equal to
			// 0.0
			HashMap<String, Double> employeesUnranked = new HashMap<String, Double>();
			employeesUnranked = getEmployeeIdDistinctkeys(employeesIdDistinct);
			// calculates the sum of each individual employee and assigns it to the value
			employeesUnranked = calculateSumOfEachEmployee(employeesWithJobTitle, employeesIdDistinct,
					employeesUnranked);
			// prints the sorted list of employees with desired jobTitle based on the sum
			// (value)
			printSortedEmployeesBasedOnSum(employeesUnranked);
		}

	}

	private static void printSortedEmployeesBasedOnSum(HashMap<String, Double> employeesUnranked) {

		ArrayList<String> mapKeys = new ArrayList<String>(employeesUnranked.keySet());
		ArrayList<Double> mapValues = new ArrayList<Double>(employeesUnranked.values());

		double temp;
		String strTemp = "";
		// methodos sort bubblesort
		for (int index1 = 0; index1 < mapValues.size() - 1; index1++) {

			for (int index2 = 0; index2 < (mapValues.size() - index1 - 1); index2++) {

				if (mapValues.get(index2) < mapValues.get(index2 + 1)) {

					temp = mapValues.get(index2);
					mapValues.set(index2, mapValues.get(index2 + 1));
					mapValues.set(index2 + 1, temp);
					strTemp = mapKeys.get(index2);
					mapKeys.set(index2, mapKeys.get(index2 + 1));
					mapKeys.set(index2 + 1, strTemp);

				}
			}
		}
		// ektupwsh twn sum gia kathe id 3exwrista
		for (int index1 = 0; index1 < mapKeys.size(); index1++) {
			System.out.println("ID is :" + mapKeys.get(index1) + " with a total cost of :" + mapValues.get(index1));
		}
	}

	private static HashMap<String, Double> calculateSumOfEachEmployee(ArrayList<Employee> employeeWithJobTitle,
			String[] employeesIdDistinct, HashMap<String, Double> employeesUnranked) {

		for (int index1 = 0; index1 < employeesIdDistinct.length; index1++) {
			for (Employee e : employeeWithJobTitle) {
				if (employeesIdDistinct[index1].equals(e.getID())) {
					double value = employeesUnranked.get(employeesIdDistinct[index1]);
					value += (e.getHours() * e.getHourlyRate()) + e.getFixedCost();
					employeesUnranked.put(employeesIdDistinct[index1], value);
				}
			}
		}
		return employeesUnranked;
	}

	private static HashMap<String, Double> getEmployeeIdDistinctkeys(String[] employeeIdDistinct) {
		HashMap<String, Double> employeesUnranked = new HashMap<String, Double>();
		for (int index1 = 0; index1 < employeeIdDistinct.length; index1++) {
			employeesUnranked.put(employeeIdDistinct[index1], 0.0);
		}
		return employeesUnranked;

	}

	private static String[] getEmployeesIdWithDistinctValues(ArrayList<String> employeesId) {
		
		return new HashSet<String>(employeesId).toArray(new String[0]);
	}

	private static ArrayList<String> getEmployeesId(ArrayList<Employee> employeeWithJobTitle) {
		
		ArrayList<String> employeesId = new ArrayList<String>();
		for (Employee e : employeeWithJobTitle) {// epistrefei array mono me strings, ta id tou kathe employee
			employeesId.add(e.getID());
		}
		return employeesId;

	}

	private static void terminateIfClientHasNoEmployees(Client client) {
		if (client.areEmployeesEmpty()) {
			System.out.println("Employees of client " + client.getName() + " not filled");
			//System.exit(0);
		}
	}

	private static ArrayList<Employee> getEmployeesOfDesiredJobTitle(ArrayList<Employee> employees, String jobTitle) {
		ArrayList<Employee> employeeWithJobTitle = new ArrayList<Employee>();
		for (Employee e : employees) {
			if (e.getJobTitle().equals(jobTitle)) {
				employeeWithJobTitle.add(e);
			}
		}
		return employeeWithJobTitle;
	}

	// Methodos upologismou tou sum gia kathe date ana jobTitle twn employees

	private static void printSumOfDates(ArrayList<Date> dates, ArrayList<Double> sumOfDates) {
		// date
		for (int index = 0; index < dates.size(); index++) {
			System.out.println("Date is :" + dates.get(index) + "and it's total sum : " + sumOfDates.get(index));
		}
	}

	private static ArrayList<Double> getSumOfDates(ArrayList<Employee> employeesWithJobTitle, ArrayList<Date> dates) {
		ArrayList<Double> sumOfDates = new ArrayList<Double>();
		for (int index1 = 0; index1 < dates.size(); index1++) {
			double tempSumPerDay = 0.0;
			for (Employee e : employeesWithJobTitle) {
				if ((dates.get(index1).equals(e.getDateOfBill()))) {
					tempSumPerDay += (e.getHours() * e.getHourlyRate()) + e.getFixedCost();
				}
			}
			sumOfDates.add(tempSumPerDay);
		}
		return sumOfDates;
	}

	private static ArrayList<Date> getDistinctDates(ArrayList<Employee> employeesWithJobTitle) {
		ArrayList<Date> dates = new ArrayList<Date>();
		for (int index1 = 0; index1 < employeesWithJobTitle.size(); index1++) {
			if (doesNotContainDate(employeesWithJobTitle, dates, index1)) {
				dates.add(employeesWithJobTitle.get(index1).getDateOfBill());
			}
		}
		return dates;
	}

	private static boolean doesNotContainDate(ArrayList<Employee> employeesWithJobTitle, ArrayList<Date> dates,
			int index1) {
		return ((dates.size() == 0) || (!(dates.contains(employeesWithJobTitle.get(index1).getDateOfBill()))));
	}

	public static ArrayList<Employee> mockDataCreate() throws ParseException {
		try {

			ArrayList<Employee> employees = new ArrayList<Employee>();
			String dateString = "22/05/2017";
			Date dateTemp = new Date();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			dateTemp = formatter.parse(dateString);

			Employee emplTemp = new Employee("A17", "I", dateTemp, 7.5, 22, 0);

			employees.add(emplTemp);

			dateString = "21/05/2017";
			dateTemp = formatter.parse(dateString);
			employees.add(new Employee("A17", "I", dateTemp, 7.5, 22, 0));
			dateString = "21/05/2017";
			dateTemp = formatter.parse(dateString);

			employees.add(new Employee("A239", "O", dateTemp, 8.4, 19, 10));
			dateString = "22/05/2017";
			dateTemp = formatter.parse(dateString);
			employees.add(new Employee("A239", "O", dateTemp, 8.4, 19, 5));
			employees.add(new Employee("A238", "I", dateTemp, 8.4, 19, 5));
			employees.add(new Employee("A238", "I", dateTemp, 8.4, 19, 5));
			employees.add(new Employee("A238", "I", dateTemp, 8.4, 19, 5));
			//employees.add(new Employee("A17", "I", dateTemp, 7.5, 22, 0));
			for (int index = 0; index < 5; index++) {
				Random random = new Random();
				int x = random.nextInt(((100 - 0) + 1) + 0);
				String id = "A" + x;
				String[] arr = { "I", "O" };
				int indexArr = random.nextInt(arr.length);
				double xDouble = random.nextDouble();
				x = random.nextInt(((1000 - 0) + 1) + 0);
				employees.add(new Employee(id, arr[indexArr], dateTemp, xDouble, xDouble, x));
			}

			System.out.println("Random data utilized");
			return employees;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

}
