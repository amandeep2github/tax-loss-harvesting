package com.hack17.poc.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack17.poc.domain.Portfolio;
import com.hack17.poc.domain.Recommendation;
import com.hack17.poc.domain.TLHAdvice;
import com.hack17.poc.service.strategy.TLHStrategy;
import com.hack17.poc.service.strategy.TLHThresholdBasedStrategy;

@Service
public class TLHAdvisorService {
	
	private Map<String, TLHStrategy> tlhStrategyMap;
	
	@Autowired
	private ReferenceDataService refDataService;
	
	public TLHAdvice advise(Portfolio portfolio){
		TLHAdvice tlhAdvice = new TLHAdvice();
		TLHStrategy tlhStrategy=tlhStrategyMap.get("threshold");
		portfolio.getAllocations().forEach(allocation-> {
			tlhAdvice.addRecommendation(tlhStrategy.execute(allocation));
		});
		return tlhAdvice;
	}
	
	@PostConstruct
	private void init(){
		tlhStrategyMap = new HashMap<>();
		tlhStrategyMap.put("threshold", new TLHThresholdBasedStrategy(5000d, refDataService));
		
		
	}
}
