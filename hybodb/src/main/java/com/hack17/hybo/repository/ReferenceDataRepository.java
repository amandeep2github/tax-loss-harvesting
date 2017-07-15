package com.hack17.hybo.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hack17.hybo.domain.CorrelatedFund;
import com.hack17.hybo.domain.SecurityPrice;

@Repository
public class ReferenceDataRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public double getLatestPrice(String ticker) {
		return entityManager
				.createQuery(
						"from SecurityPrice sp where sp.ticker=:ticker order by sp.priceDate desc",
						SecurityPrice.class).setParameter("ticker", ticker).getSingleResult().getPrice();
	}

	public double getPriceOnDate(String ticker, Date priceDate) {

		return entityManager
				.createQuery(
						"from SecurityPrice sp where sp.priceDate = :priceDate",
						SecurityPrice.class)
				.setParameter("priceDate", priceDate).setFirstResult(1)
				.getSingleResult().getPrice();
	}

	public String getCorrelatedTicker(String ticker) {
		return entityManager
				.createQuery("from CorrelatedFund cf where cf.ticker=:ticker",
						CorrelatedFund.class).setParameter("ticker", ticker)
				.getSingleResult().getCorrelatedTicker();
	}

}
