package com.hack17.hybo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class InvestorProfile {

	@Id
	private long id;
	private Date dateOfBirth;
	@Enumerated(EnumType.STRING)
	private RiskTolerance riskTolerance;
	private int investmentHorizonInMonths;
	private Date horizonAsOfDate;

	public InvestorProfile(Date dateOfBirth, RiskTolerance riskTolerance,
			int investmentHorizon, Date horizonAsOfDate) {
		super();
		this.dateOfBirth = dateOfBirth;
		this.riskTolerance = riskTolerance;
		this.investmentHorizonInMonths = investmentHorizon;
		this.horizonAsOfDate = horizonAsOfDate;
	}
	
	

}
