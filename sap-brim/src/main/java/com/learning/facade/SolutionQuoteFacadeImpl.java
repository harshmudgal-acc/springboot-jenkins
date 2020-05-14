package com.learning.facade;

import org.springframework.stereotype.Component;

import com.learning.service.SolutionQuoteService;

@Component
public class SolutionQuoteFacadeImpl implements SolutionQuoteFacade {

	private SolutionQuoteService solutionQuoteService;
	
	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return solutionQuoteService.getToken();
	}

}
