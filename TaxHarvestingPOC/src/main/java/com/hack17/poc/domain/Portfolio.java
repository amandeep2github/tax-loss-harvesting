package com.hack17.poc.domain;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
	List<Allocation> allocations = new ArrayList<>();

	public List<Allocation> getAllocations() {
		// TODO Auto-generated method stub
		return allocations;
	}

	public void addAllocation(Allocation allocation) {
		allocations.add(allocation);
		
	}

}
