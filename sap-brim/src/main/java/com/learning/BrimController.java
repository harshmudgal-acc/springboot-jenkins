package com.learning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.facade.SolutionQuoteFacade;

@RestController
@RequestMapping("/brim")
public class BrimController {

	private SolutionQuoteFacade solutionQuoteFacade;
	
	@RequestMapping("/getToken")
	public String getTokenFromBrim() {
		return solutionQuoteFacade.getToken();
	}
}
