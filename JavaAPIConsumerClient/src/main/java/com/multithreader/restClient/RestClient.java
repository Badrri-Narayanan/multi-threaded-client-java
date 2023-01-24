package com.multithreader.restClient;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	RestTemplate restTemplate;
	
	private static final String URL = "http://localhost:3000/countries";
	
	public RestClient() {
		restTemplate = new RestTemplate();
	}
	
	public List<String> getCountries(int limit, int offset) {
		String resultantUrl = URL + String.format("?limit=%d&offset=%d", limit, offset);
		ResponseEntity<ExpressServerResponse> response = restTemplate.getForEntity(resultantUrl, ExpressServerResponse.class);
		
		return response.getBody().getCountries();
	}
}
