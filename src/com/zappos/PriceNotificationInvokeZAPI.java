/***************************************************************************************
 * File             :   PriceNotificationInvokeZAPI.java
 * Description      :   This class is used to invoke Zappos API and convert Json to Java
 ***************************************************************************************/

package com.zappos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PriceNotificationInvokeZAPI {
	
	public ZapposConstants zapposConstants;
	public PriceNotifProdDetDTO prodDetDTO;
	public FamilyCoreValues coreValueDTO;
	public ArrayList<PriceNotifProdDetDTO> arrPro;
	public ArrayList<FamilyCoreValues> arrCoreVal;
	public ProductSearchResponse proSearchResp;
	
	//To fetch the details of the product searched from Zappos API
	public PriceNotifProdDetDTO getProdDetFrmAPI(String prod) {	
		System.out.println("Function getProdDetFrmAPI - trying to get the details of the product entered");
		prodDetDTO = new PriceNotifProdDetDTO();
		System.out.println(prod);
		String jsonResult ="";
		zapposConstants = new ZapposConstants();
		String url;
		url = zapposConstants.SEARCH_PRODUCT_URL+prod+zapposConstants.SEARCH_LIMIT+zapposConstants.API_KEY;
		System.out.println(url);
		try {
			jsonResult = invokeAPI(url);
			Gson gson = new GsonBuilder().create();
			proSearchResp = gson.fromJson(jsonResult, ProductSearchResponse.class);
			arrPro = proSearchResp.getResults();
			if(arrPro!=null && arrPro.size()>0){
				prodDetDTO = arrPro.get(0);
				prodDetDTO.setSearchFlag(true);
			}else{
				prodDetDTO.setSearchFlag(false);
			}
		} catch (IOException e) {
			prodDetDTO.setErrorMsg("Zappos API requests are being throttled");
		}
		return prodDetDTO;

	}

	//Connect to API and retrieve Json
	public String invokeAPI(String url) throws IOException{
		System.out.println("Function invokeAPI - connecting to url");
		HttpURLConnection conn = null;
		BufferedReader buffRead = null;
		String eachLine;
		URL apiURL;
		String jsonResponse = "";
		try {
			apiURL = new URL(url);		
			conn = (HttpURLConnection) apiURL.openConnection();
			if (conn.getResponseCode() != 200) {
				throw new IOException(conn.getResponseMessage());
			}
			buffRead = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 while ((eachLine = buffRead.readLine()) != null) {
		            jsonResponse += eachLine;
		         }
			 buffRead.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return jsonResponse;

	}

	//To fetch the random core value by invoking API
	public FamilyCoreValues getRandomCoreValue() {
		zapposConstants = new ZapposConstants();
		String url;
		String jsonResult ="";		
		try {
			url = zapposConstants.CORE_VALUE;
			jsonResult = invokeAPI(url);
			Gson gson = new GsonBuilder().create();
			arrCoreVal = gson.fromJson(jsonResult, FamilyCoreValuesResponse.class).getValues();
			if(arrCoreVal!=null && arrCoreVal.size()>0){
				coreValueDTO = arrCoreVal.get(0);
			}else{
				coreValueDTO = new FamilyCoreValues();
				coreValueDTO.setSummary("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coreValueDTO;
	}
}
