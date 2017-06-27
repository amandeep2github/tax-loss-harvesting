package com.hack17.poc.repository;

import org.springframework.stereotype.Repository;

import com.hack17.poc.domain.Allocation;
import com.hack17.poc.domain.Fund;
import com.hack17.poc.domain.Portfolio;


@Repository
public class PortfolioRepository {

	public Portfolio loadPortfolio(long portfolioId) {
		Portfolio portfolio = new Portfolio();
		portfolio.addAllocation(new Allocation(new Fund("VT"),23.4, .18));
		portfolio.addAllocation(new Allocation(new Fund("AGG"),76.6, .08));
		return portfolio;
	}

}
