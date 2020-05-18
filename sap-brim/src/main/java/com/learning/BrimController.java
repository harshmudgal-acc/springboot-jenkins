package com.learning;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Person;
import com.learning.facade.SolutionQuoteFacade;

@RestController
@RequestMapping("/brim")
public class BrimController {

	private SolutionQuoteFacade solutionQuoteFacade;
	
	@RequestMapping("/getToken")
	public String getTokenFromBrim() {
		return solutionQuoteFacade.getToken();
	}
	
	
	@RequestMapping(value = "/updatePerson",method = RequestMethod.PUT)
	public String updatePerson(@RequestBody Person person) {
		System.out.println("Id ="+person.getId());
		System.out.println("Name ="+person.getName());
		System.out.println("Age ="+person.getAge());
		return "Person Update";
	}
}
