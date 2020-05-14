package com.learning.service;

import org.springframework.http.HttpHeaders;

public interface SolutionQuoteService {

	/**
	 * This will return csrf token retrun from  BRIM.
	 * 
	 * @return
	 */
	public String getToken();
	
	/**
	 * This will create a Solution Quote in BRIM
	 * @return
	 */
	public HttpHeaders getHeaders();

}
