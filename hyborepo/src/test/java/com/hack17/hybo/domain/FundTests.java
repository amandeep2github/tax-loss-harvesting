package com.hack17.hybo.domain;

import static com.hack17.hybo.util.DateTimeUtil.getDate;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hack17.hybo.domain.Allocation;
import com.hack17.hybo.domain.Fund;

@ContextConfiguration(locations={"classpath:com/hack17/hybo/OrderPersistenceTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FundTests {

	@PersistenceContext
	private EntityManager entityManager;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		createFunds();
		createAllocations();
		createInvestorProfile();
		createPortfolios();
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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Transactional()
	@Rollback(false)
	public void testSave() {
		Fund fund = new Fund();
		fund.setTicker("VTI");
		entityManager.persist(fund);
		entityManager.flush();
		List<Fund> funds = entityManager.createQuery("from Fund", Fund.class).getResultList();
		assertFalse(funds.isEmpty());
	}

}
