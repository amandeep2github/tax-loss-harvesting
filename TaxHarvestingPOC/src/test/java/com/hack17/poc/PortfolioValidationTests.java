package com.hack17.poc;

import static org.junit.Assert.*;
import java.util.List;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.hack17.poc.domain.Allocation;
import com.hack17.poc.domain.Portfolio;
import com.hack17.poc.repository.PortfolioRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={TaxHarvestingPocApplication.class})
public class PortfolioValidationTests {

	@Autowired
	PortfolioRepository portfolioRepository;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void portfolioAssetPercentageIsValid(){
		long portfolioId = 101;
		List<Allocation> allocations = getAllocations(portfolioId);
		
		double sumOfAllocations = allocations.stream().mapToDouble(alloc -> alloc.getPercentage()).sum();
		assertEquals(100, sumOfAllocations,0);
		
	}

	@Test
	public void portfolioAllocationETFStructure(){
		long portfolioId = 101;
		List<Allocation> allocations = getAllocations(portfolioId);
		allocations.forEach(alloc->{
			assertNotNull(alloc.getFund());
			assertNotNull(alloc.getFund().getSymbol());
			assertNotNull(alloc.getQuantity());
			assertNotNull(alloc.getTransactionDate());
			assertNotNull(alloc.getExpenseRatio());
		});
	}
	
	@Test
	public void portfolioInvestorProfile(){
		long portfolioId = 101;
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		assertNotNull(portfolio.getInvestorProfile());
	}

	private List<Allocation> getAllocations(long portfolioId) {
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		List<Allocation> allocations = portfolio.getAllocations();
		return allocations;
	}
	

}
