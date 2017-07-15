package com.hack17.hybo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hack17.hybo.domain.Portfolio;

@Repository
public class PortfolioRepository {
	@PersistenceContext
	EntityManager entityManager;
	public Portfolio getPortfolio(long portfolioId){
		return entityManager.find(Portfolio.class, portfolioId);
	}
}
