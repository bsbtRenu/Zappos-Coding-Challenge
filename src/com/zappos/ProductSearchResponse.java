/*************************************************************************************************************
 * File             :   ProductSearchResponse.java
 * Description      :   This class is used a Response Object for Product Details conversion from Json to Java. 
 *************************************************************************************************************/
package com.zappos;

import java.util.ArrayList;

public class ProductSearchResponse {
	private String limit;
	private String statusCode;
	private ArrayList<PriceNotifProdDetDTO> results;
	
	public ProductSearchResponse(String statusCode,  ArrayList<PriceNotifProdDetDTO> result){
		this.statusCode = statusCode;
		this.results = result;		
	}

	/**
	 * @return the limit
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the result
	 */
	public  ArrayList<PriceNotifProdDetDTO> getResults() {
		return results;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults( ArrayList<PriceNotifProdDetDTO> results) {
		this.results = results;
	}
	
}
