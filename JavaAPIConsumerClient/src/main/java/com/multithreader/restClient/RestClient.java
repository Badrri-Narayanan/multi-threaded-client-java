package com.multithreader.restClient;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * REST client which abstracts the handling of API calls
 * 
 * @author Badrri Narayanan S
 *
 */
public class RestClient {
	RestTemplate restTemplate;
	
	private static final String URL = "http://localhost:3000/countries";
	
	public RestClient() {
		restTemplate = new RestTemplate();
	}
	
	/**
	 * Gets a part of the list of countries from the API.
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<String> getCountries(int limit, int offset) {
		String resultantUrl = URL + String.format("?limit=%d&offset=%d", limit, offset);
		ResponseEntity<CountryListResponse> response = restTemplate.getForEntity(resultantUrl, CountryListResponse.class);
		
		return response.getBody().getCountries();
	}

	/**
	 * Gets the total number of countries available.
	 * @return
	 */
	public int getTotalCountryCount() {
		String resultantUrl = URL + "/total";
		
		ResponseEntity<TotalCountryCountResponse> response = restTemplate.getForEntity(resultantUrl, TotalCountryCountResponse.class);
		
		return response.getBody().getTotalCount();
	}
}
