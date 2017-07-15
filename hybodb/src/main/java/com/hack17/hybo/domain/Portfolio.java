package com.hack17.hybo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Data
@Entity
public class Portfolio {
	
	@Id
	private long id;
	@OneToMany
	@Fetch(FetchMode.JOIN)
	private List<Allocation> allocations = new ArrayList<>();
	@OneToOne
	private InvestorProfile investorProfile;

	public List<Allocation> getAllocations() {
		// TODO Auto-generated method stub
		return allocations;
	}

	public void addAllocation(Allocation allocation) {
		allocations.add(allocation);
		
	}

	public InvestorProfile getInvestorProfile() {
		return investorProfile;
	}

	public void setInvestorProfile(InvestorProfile investorProfile) {
		this.investorProfile = investorProfile;
	}
	
	

}
