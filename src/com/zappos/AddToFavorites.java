/************************************************************************************
 * File             :   AddToFavorites.java
 * Description      :   This class is used to add the user's favorite products details). 
 ************************************************************************************/

package com.zappos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddToFavorites {
	BufferedWriter writer;
	ArrayList<PriceNotifProdDetDTO> results;
	ProductSearchResponse proGetResp;
	ProductSearchResponse proResp;
		
	//To add the user's favorite products to a Json File(considered as DB)
	public boolean writeToJson(PriceNotifProdDetDTO prodDTO) throws IOException {
		Gson gson = new Gson();
		BufferedReader br = null;
		PrintWriter pw = null;
		PrintWriter out = null;
		boolean addFavFlg = true;
		try {
			br = new BufferedReader(new FileReader("Add_Favorites.json"));
			Gson gsonWrite = new GsonBuilder().create();
			proGetResp = gsonWrite.fromJson(br, ProductSearchResponse.class);
			if (proGetResp != null && proGetResp.getResults().size() >= 1) {
				proResp = new ProductSearchResponse("", results);
				proGetResp.getResults().add(proGetResp.getResults().size(),prodDTO);
				proResp.setResults(proGetResp.getResults());
			} else {
				results = new ArrayList<PriceNotifProdDetDTO>();
				proResp = new ProductSearchResponse("", results);
				results.add(proResp.getResults().size(), prodDTO);
				proResp.setLimit("1");
				proResp.setStatusCode("200");
				proResp.setResults(results);
			}

			String json = gson.toJson(proResp);
			System.out.println("Using Gson.toJson() on a raw collection: "
					+ json);
			pw = new PrintWriter("Add_Favorites.json");
			pw.close();
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"Add_Favorites.json", true)));
			out.write(json);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			addFavFlg = false;
		} catch (IOException e) {
			e.printStackTrace();
			addFavFlg = false;
		} finally {
			out.close();
			br.close();
		}
		return addFavFlg;

	}

	//To create a copy of database for mail Notifications
	public void createDupForNotifications() throws IOException {
		try {
			Files.copy(new java.io.File("Add_Favorites.json").toPath(),
					new java.io.File("Notify_Favorites.json").toPath(),
					java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//To fetch details from database for mail Notifications
	public ArrayList<PriceNotifProdDetDTO> readJsonFile() throws IOException {
		BufferedReader buffReader = null;
		try {
			buffReader = new BufferedReader(new FileReader(
					"Notify_Favorites.json"));
			Gson gsonRead = new GsonBuilder().create();
			proResp = gsonRead.fromJson(buffReader, ProductSearchResponse.class);
			System.out.println(proResp.getResults().size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			buffReader.close();
		}
		return proResp.getResults();

	}
	
	//To update the NotifyFlag once a mail notification is sent to a user
	public void updateNotifyFlag(PriceNotifProdDetDTO usrDTO)
			throws IOException {
		Gson gson = new Gson();
		BufferedReader buffRead = null;
		PrintWriter out = null;
		PrintWriter pw = null;
		PriceNotifProdDetDTO getDTO;
		try {
			buffRead = new BufferedReader(new FileReader("Add_Favorites.json"));
			Gson gsonUpdate = new GsonBuilder().create();
			proGetResp = gsonUpdate.fromJson(buffRead, ProductSearchResponse.class);
			if (proGetResp != null && proGetResp.getResults().size() >= 1) {
				proResp = new ProductSearchResponse("", results);
				getDTO = new PriceNotifProdDetDTO();
				for (int count = 0; count < proGetResp.getResults().size(); count++) {
					getDTO = proGetResp.getResults().get(count);
					System.out.println("Mail Id: " + getDTO.getMailId());
					System.out.println("Pro Id: " + getDTO.getProductId());
					if (getDTO.getMailId().equalsIgnoreCase(usrDTO.getMailId())
							&& getDTO.getProductId().equalsIgnoreCase(usrDTO.getProductId())
							&& !usrDTO.isNotifyFlag()) {
						getDTO.setNotifyFlag(true);
						proGetResp.getResults().set(count, getDTO);
					} else {
						proGetResp.getResults().set(count, getDTO);
					}
				}
				String json = gson.toJson(proGetResp);
				pw = new PrintWriter("Add_Favorites.json");
				pw.close();
				out = new PrintWriter(new BufferedWriter(new FileWriter("Add_Favorites.json", true)));
				out.write(json);
				out.flush();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			buffRead.close();
		}
	}

}
