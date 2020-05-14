package com.learning.service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SolutionQuoteServiceImpl implements SolutionQuoteService {

	private static final String X_CSRF_TOKEN = "x-csrf-token";
	private static final int STATUS_OK = 200;
	private static final String brim_fetch_token_url = "/sample/token";

	@Override
	public String getToken() {
		try {

			final RestTemplate restTemplate = new RestTemplate();
			final ResponseEntity<Map> csrfTokenExchange = restTemplate.exchange(brim_fetch_token_url, HttpMethod.GET,
					new HttpEntity(createHttpHeaders()), Map.class);
			if (csrfTokenExchange.getStatusCodeValue() == STATUS_OK) {
				System.out.println("***************** Token *********************");
				return csrfTokenExchange.getHeaders().getFirst(X_CSRF_TOKEN);
			}
		} catch (final Exception ex) {
			System.out.println("#################### Error ####################");
			System.out.println(ex);
		}
		return null;
	}

	public HttpHeaders createHttpHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		final String auth = "harsh.mudgal" + ":" + "welcome";
		final byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
		final String authHeader = "Basic " + new String(encodedAuth);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add(HttpHeaders.AUTHORIZATION, authHeader);
		headers.add(X_CSRF_TOKEN, "fetch");
		return headers;
	}

	@Override
	public HttpHeaders getHeaders() {
		return createHttpHeaders();
	}

}
