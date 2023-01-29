package com.multithreader.restClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * API Response DTO to get total number of countries.
 * @author Badrri Narayanan S
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalCountryCountResponse {
	private int totalCount;
	
	public TotalCountryCountResponse() {
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
