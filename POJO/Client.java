package com.gnt.BillingSystem.POJO;

import java.util.ArrayList;

public class Client {
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private String name;

	// Constructors
	public Client() {
		this.name = Constants.COMPANY;
		this.employees = new ArrayList<Employee>();
	}

	public Client(String name) {

		this.name = name;
		this.employees = new ArrayList<Employee>();
	}

	public Client(ArrayList<Employee> employees) {
		this.name = Constants.COMPANY;
		if (!this.employees.isEmpty()) {
			this.employees.clear();
		}
		for (Employee e : employees) {
			this.employees.add(e);
		}
	}

	public Client(String name, ArrayList<Employee> employees) {
		employees = new ArrayList<Employee>();
		this.name = name;
		for (Employee e : employees) {
			this.employees.add(e);
		}
	}

	public Client(Client client) {
		this.name = client.getName();
		if (!this.employees.isEmpty()) {
			this.employees.clear();
		}
		for (Employee e : client.getEmployees()) {
			this.employees.add(e);
		}
	}

	public ArrayList<Employee> getEmployees() {
		return this.employees;
	}

	public void setClients(ArrayList<Employee> employees) {
		if (!this.employees.isEmpty()) {
			this.employees.clear();
		}
		for (Employee e : employees) {
			this.employees.add(e);
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean areEmployeesEmpty() {
		if (employees.isEmpty()) {
			return true;
		}
		return false;
	}
}
