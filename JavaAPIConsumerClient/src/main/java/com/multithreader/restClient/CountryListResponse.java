package com.multithreader.restClient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API Response DTO to get list of countries.
 * @author Badrri Narayanan S
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryListResponse {
	@JsonProperty("countries")
	List<String> countries;

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
	public CountryListResponse() {
	}
}
