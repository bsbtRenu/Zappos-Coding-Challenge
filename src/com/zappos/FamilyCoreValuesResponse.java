/*********************************************************************************************************
 * File             :   FamilyCoreValuesResponse.java
 * Description      :   This class is used a Response Object for Core Values conversion from Json to Java. 
 ********************************************************************************************************/
package com.zappos;

import java.util.ArrayList;

public class FamilyCoreValuesResponse {

	private String limit;
	private String statusCode;
	private ArrayList<FamilyCoreValues> values;

	public FamilyCoreValuesResponse(String statusCode,
			ArrayList<FamilyCoreValues> values) {
		this.statusCode = statusCode;
		this.values = values;
	}

	/**
	 * @return the limit
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(String limit) {
		this.limit = limit;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the values
	 */
	public ArrayList<FamilyCoreValues> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(ArrayList<FamilyCoreValues> values) {
		this.values = values;
	}

}
