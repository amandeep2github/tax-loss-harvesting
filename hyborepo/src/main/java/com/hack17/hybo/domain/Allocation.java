package com.hack17.hybo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Allocation {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Fund fund;
	private double costPrice;
	private int quantity;
	private double percentage;
	private Date transactionDate;
	private double expenseRatio;
	public Allocation(Fund fund, double costPrice, int quantity,
			double percentage, Date transactionDate, double expenseRatio) {
		super();
		this.fund = fund;
		this.costPrice = costPrice;
		this.quantity = quantity;
		this.percentage = percentage;
		this.transactionDate = transactionDate;
		this.expenseRatio = expenseRatio;
	}
	
	


}
