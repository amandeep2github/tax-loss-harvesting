package com.hack17.hybo;

import static com.hack17.hybo.util.DateTimeUtil.getDate;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.hack17.hybo.domain.Allocation;
import com.hack17.hybo.domain.CorrelatedFund;
import com.hack17.hybo.domain.Fund;
import com.hack17.hybo.domain.InvestorProfile;
import com.hack17.hybo.domain.Portfolio;
import com.hack17.hybo.domain.RiskTolerance;
import com.hack17.hybo.domain.SecurityPrice;

import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HybodbApplicationTests {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Before
	public void setUp() throws Exception {
		createFunds();
		createAllocations();
		createInvestorProfile();
		createPortfolios();
		createPrice();
		createCorrelatedFund();
	}

	private void createCorrelatedFund() {
		CorrelatedFund correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("VTI");
		correlatedFund.setCorrelatedTicker("SCHB");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("VEA");
		correlatedFund.setCorrelatedTicker("SCHF");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("VWO");
		correlatedFund.setCorrelatedTicker("IEMG");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("VIG");
		correlatedFund.setCorrelatedTicker("SCHD");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("XLE");
		correlatedFund.setCorrelatedTicker("VDE");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("SCHP");
		correlatedFund.setCorrelatedTicker("VTIP");
		entityManager.persist(correlatedFund);
		correlatedFund = new CorrelatedFund();
		correlatedFund.setTicker("MUB");
		correlatedFund.setCorrelatedTicker("TFI");
		entityManager.persist(correlatedFund);
		entityManager.flush();
	}

	private void createPrice() {
		SecurityPrice price = new SecurityPrice();
		price.setTicker("VTI");
		price.setPrice(124d);
		price.setPriceDate(getDate("JUL 01, 2017"));
		entityManager.persist(price);
		price = new SecurityPrice();
		price.setTicker("VTV");
		price.setPrice(96d);
		price.setPriceDate(getDate("JUL 01, 2017"));
		entityManager.persist(price);
		price = new SecurityPrice();
		price.setTicker("VEA");
		price.setPrice(41d);
		price.setPriceDate(getDate("JUL 01, 2017"));
		entityManager.persist(price);
		entityManager.flush();
		
	}

	private void createInvestorProfile() {
		InvestorProfile investorProfile = new InvestorProfile(getDate("Apr 7, 1972"), RiskTolerance.MEDIUM, 26, getDate("Jan 1, 2017"));
		investorProfile.setId(501);
		entityManager.persist(investorProfile);
		entityManager.flush();
		
	}

	private void createPortfolios() {
		InvestorProfile investorProfile = entityManager.find(InvestorProfile.class, 501l);
		Portfolio portfolio = new Portfolio();
		portfolio.addAllocation(findAllocation(301l));
		portfolio.addAllocation(findAllocation(302l));
		portfolio.setInvestorProfile(investorProfile);
		portfolio.setId(101l);
		entityManager.persist(portfolio);
		portfolio = new Portfolio();
		portfolio.addAllocation(findAllocation(303l));
		portfolio.addAllocation(findAllocation(304l));
		portfolio.setInvestorProfile(investorProfile);
		portfolio.setId(102l);
		entityManager.persist(portfolio);
		portfolio = new Portfolio();
		portfolio.addAllocation(findAllocation(305l));
		portfolio.addAllocation(findAllocation(306l));
		portfolio.setInvestorProfile(investorProfile);
		portfolio.setId(103l);
		entityManager.persist(portfolio);
		entityManager.flush();
		
	}

	private Allocation findAllocation(long allocId) {
		return entityManager.find(Allocation.class, allocId);
	}

	private void createAllocations() {
		Fund fundVTI = findFund("VTI");
		Fund fundVTV = findFund("VTV");
		Fund fundVEA = findFund("VEA");
		
		Allocation alloc = new Allocation(fundVTI,120.4,1000,50d, getDate("APR 01, 2017"), .04);
		alloc.setId(301l);
		entityManager.persist(alloc);
		
		alloc = new Allocation(fundVTV,44.01,1200,50d, getDate("APR 01, 2017"), .06);
		alloc.setId(302l);
		entityManager.persist(alloc);
		
		alloc = new Allocation(fundVTI,120.4,1000,50d, getDate("APR 01, 2017"), .04);
		alloc.setId(303l);
		entityManager.persist(alloc);
		
		alloc = new Allocation(fundVEA,105.01,1200,50d, getDate("APR 01, 2017"), .06);
		alloc.setId(304l);
		entityManager.persist(alloc);
		
		alloc = new Allocation(fundVTI,120.4,1000,50d, getDate("APR 01, 2017"), .04);
		alloc.setId(305l);
		entityManager.persist(alloc);
		
		alloc = new Allocation(fundVEA,105.01,1200,50d, getDate("JUL 01, 2017"), .06);
		alloc.setId(306l);
		entityManager.persist(alloc);
		
		entityManager.flush();
		
		
	}

	private Fund findFund(String fundTicker) {		
		return entityManager.createQuery(String.format("from Fund f where f.ticker='%s'",fundTicker),  Fund.class).getSingleResult();
	}

	private void createFunds() {
		Fund fund = new Fund();
		fund.setTicker("VTI");
		entityManager.persist(fund);
		fund = new Fund();
		fund.setTicker("VTV");
		entityManager.persist(fund);
		fund = new Fund();
		fund.setTicker("VEA");
		entityManager.persist(fund);
		entityManager.flush();
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void portfolioLoads() {
		Portfolio portfolio = entityManager.find(Portfolio.class, 101l);
		System.out.println(portfolio);
		assertNotNull(portfolio);
	}

}
