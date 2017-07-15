package com.hack17.hybo.repository;

import javax.persistence.EntityManager;



import org.springframework.stereotype.Repository;

import com.hack17.hybo.domain.InvestorProfile;

import javax.persistence.PersistenceContext;

@Repository
public class ProfileRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public InvestorProfile getProfile(long profileId){
		return entityManager.find(InvestorProfile.class, profileId);
	}
}
