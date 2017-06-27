package com.hack17.poc.domain;

public class Allocation {
	
	private double percentage;
	private Fund fund;
	private double expenseRatio;

	public Allocation(double percentage) {
		this.percentage = percentage;
	}

	public Allocation(Fund fund, double percentage) {
		this.fund = fund;
		this.percentage = percentage;
	}

	public Allocation(Fund fund, double percentage, double expenseRatio) {
		this.fund = fund;
		this.percentage = percentage;
		this.expenseRatio = expenseRatio;
	}

	public double getPercentage(){
		return percentage;
	}

	public Fund getFund() {
		
		return fund;
	}

	public double getExpenseRatio() {
		// TODO Auto-generated method stub
		return expenseRatio;
	}
}
