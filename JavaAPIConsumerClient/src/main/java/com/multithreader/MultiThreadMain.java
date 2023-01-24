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

public class MultiThreadMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		Vector<String> countries = new Vector<String>();
		final int limit = 10;
		List<CompletableFuture<List<String>>> results = new ArrayList<CompletableFuture<List<String>>>();
		
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		
		for(int offset=0; offset<20; offset++) {
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
		
		// Process the results
		for (CompletableFuture<List<String>> future : results) {
		    List<String> batch = future.get();
		    if (batch.size() > 0) {
		        countries.addAll(batch);
		    }
		}

		// Shutdown the thread pool
		executorService.shutdown();
		
		System.out.println(countries);
	}
	
	public static void noThreadingResponse() {
		RestClient restClient = new RestClient();
		List<String> countries = restClient.getCountries(10, 0);
		
		System.out.println(countries);
	}
}
