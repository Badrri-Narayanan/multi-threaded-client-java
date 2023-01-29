package com.multithreader;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multithreader.restClient.RestClient;

/**
 * The Main driver class which uses the REST client to fetch the complete<br/>
 * list of countries from the REST API.
 * 
 * @author Badrri Narayanan S
 *
 */
public class MultiThreadMain {
	/**
	 * Entrypoint
	 * @param args
	 */
	public static void main(String[] args) {
		Vector<String> countries = null;
		try {
			countries = getCountries();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		System.out.println(countries);
	}
	
	/**
	 * This method contains the multi-threaded logic to fetch the list of
	 * countries from the REST API.
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private static Vector<String> getCountries()  throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		final int limit = 10;
		// to get total count from the API
		int totalCount = getTotalCountryCount();
		
		int numberOfRequestsRequired = (totalCount/limit) + 1;
		
		List<CompletableFuture<List<String>>> results = new ArrayList<CompletableFuture<List<String>>>();
		
		// to get details of the thread
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		
		for(int offset=0; offset < numberOfRequestsRequired; offset++) {
			int currentOffset = offset;
			CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(() -> {
            	try {
            			long threadId = Thread.currentThread().getId();
            			System.out.println("thread id = " + threadId);

            			System.out.println(threadMXBean.getThreadInfo(threadId));
            			RestClient restClient = new RestClient();
						return restClient.getCountries(limit, currentOffset);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            	return new ArrayList<>();
	            }, executorService);
	
	            // Add the Future to the list of results
	            results.add(future);
	    }
		
		// Wait for all the tasks to complete
		CompletableFuture.allOf(results.toArray(new CompletableFuture[results.size()])).join();
		
		// we use Vector because it is thread-safe.
		Vector<String> countries = new Vector<String>();
		
		// Process the results
		for (CompletableFuture<List<String>> future : results) {
		    List<String> batch = future.get();
		    if (batch.size() > 0) {
		        countries.addAll(batch);
		    }
		}

		// Shutdown the thread pool
		executorService.shutdown();
		
		return countries;
	}

	/**
	 * Gets the total number of countries from the API.<br/>
	 * This helps us in determining the number of trips we need to do<br/>
	 * to get the full list.
	 * @return
	 */
	private static int getTotalCountryCount() {
		RestClient restClient = new RestClient();
		
		return restClient.getTotalCountryCount();
	}

	/**
	 * This is a sample method to see how we can fetch countries without <br/>
	 * multi-threading.
	 */
	public static void noThreadingResponse() {
		RestClient restClient = new RestClient();
		List<String> countries = restClient.getCountries(10, 0);
		
		System.out.println(countries);
	}
}
