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

import com.hack17.poc.domain.Action;
import com.hack17.poc.domain.Allocation;
import com.hack17.poc.domain.Portfolio;
import com.hack17.poc.domain.TLHAdvice;
import com.hack17.poc.repository.PortfolioRepository;
import com.hack17.poc.service.TLHAdvisorService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={TaxHarvestingPocApplication.class})
public class SimpleSellAndBuyThresholdStrategyTests {

	
	@Autowired
	PortfolioRepository portfolioRepository;
	@Autowired
	private TLHAdvisorService tlhAdvisorService;
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
	public void noStockInlossBeyondThreshold() {
		long portfolioId = 101;
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		TLHAdvice tlhAdvice = tlhAdvisorService.advise(portfolio);
		assertEquals(2,  tlhAdvice.getRecommendations().size());
		assertEquals(Action.HOLD, tlhAdvice.getRecommendations().get(0).getAction());
		assertEquals(Action.HOLD, tlhAdvice.getRecommendations().get(1).getAction());
	}
	
	@Test
	public void recommendationForSell() {
		long portfolioId = 102;
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		TLHAdvice tlhAdvice = tlhAdvisorService.advise(portfolio);
		assertEquals(2,  tlhAdvice.getRecommendations().size());
		assertEquals(Action.HOLD, tlhAdvice.getRecommendations().get(0).getAction());
		assertEquals(Action.SELL, tlhAdvice.getRecommendations().get(1).getAction());

	}
	
	@Test
	public void recommendationForSellWithWashSale() {
		long portfolioId = 103;
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		TLHAdvice tlhAdvice = tlhAdvisorService.advise(portfolio);
		assertEquals(2,  tlhAdvice.getRecommendations().size());
		assertEquals(Action.HOLD, tlhAdvice.getRecommendations().get(0).getAction());
		assertEquals(Action.HOLD, tlhAdvice.getRecommendations().get(1).getAction());

	}
	
	private List<Allocation> getAllocations(long portfolioId) {
		Portfolio portfolio = portfolioRepository.loadPortfolio(portfolioId);
		List<Allocation> allocations = portfolio.getAllocations();
		return allocations;
	}

}
