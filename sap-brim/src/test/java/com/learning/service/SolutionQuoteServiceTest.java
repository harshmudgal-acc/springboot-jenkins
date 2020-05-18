package com.learning.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest
public class SolutionQuoteServiceTest {

	@Mock
	RestTemplate restTemplate;

	@Autowired
	SolutionQuoteService solutionQuoteService;

	@Mock
	SolutionQuoteService solutionQuoteService1;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getTokenTest() {
	
		ResponseEntity<Map> myEntity = new ResponseEntity<Map>(HttpStatus.ACCEPTED);
		//myEntity.getHeaders().set("x-csrf-token", "harsh");
		
		Mockito.when(restTemplate.exchange(
	            Matchers.eq("/sample/token"),
	            Matchers.eq(HttpMethod.GET),
	            Matchers.<HttpEntity<HttpHeaders>>any(),
	            Matchers.<ParameterizedTypeReference<Map>>any())
	        ).thenReturn(myEntity);
		
		String token=solutionQuoteService.getToken();
		assertEquals(token, myEntity.getHeaders().get("x-csrf-token"));
	}
	
	@Test
	public void getHeaders() {
		
		final HttpHeaders headers = new HttpHeaders();
		final String auth = "harsh.mudgal" + ":" + "welcome";
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		final String authHeader = "Basic " + new String(encodedAuth);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add(HttpHeaders.AUTHORIZATION, authHeader);
		headers.add("x-csrf-token", "fetch");
		Mockito.when(solutionQuoteService1.getHeaders()).thenReturn(headers);
		HttpHeaders header1=solutionQuoteService1.getHeaders();
		
		assertEquals(header1.get("x-csrf-token").get(0), headers.get("x-csrf-token").get(0));
	}
	
}
