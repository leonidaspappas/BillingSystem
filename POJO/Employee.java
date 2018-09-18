package com.gnt.BillingSystem.POJO;

import java.util.Date;

public class Employee {
	private String id;
	private String jobTitle;
	private Date dateOfBill;
	private double hours;
	private double hourlyRate;
	private double fixedCost;

	public Employee(String id, String jobTitle, Date dateOfBill, double hours, double hourlyRate, double fixedCost) {

		this.id = id;

		this.jobTitle = jobTitle;
		this.dateOfBill = dateOfBill;
		this.hours = hours;
		this.hourlyRate = hourlyRate;
		this.fixedCost = fixedCost;
	}

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getDateOfBill() {
		return this.dateOfBill;
	}

	public void setDateOfBill(Date dateOfBill) {
		this.dateOfBill = dateOfBill;
	}

	public double getHours() {
		return this.hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getHourlyRate() {
		return this.hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}

}
