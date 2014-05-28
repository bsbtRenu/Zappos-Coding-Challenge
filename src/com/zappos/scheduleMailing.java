/**************************************************************************************************************************
 * File             :   scheduleMailing.java
 * Description      :   This class acts a scheduler class which runs for every 2 hours to send price notifications to users
 **************************************************************************************************************************/
package com.zappos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class scheduleMailing {
	
	private static AddToFavorites createDupDB;
	private static ArrayList<PriceNotifProdDetDTO> proList;
	private static PriceNotifProdDetDTO mailDTO;
	
	public static void main(String args[]) throws FileNotFoundException {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				sendMailsToUsers();
			}
		},0,120*60*1000 );//Scheduled for every 2 hours
		
	}

	//To check for constraints and invoke mail functionality
	public static void sendMailsToUsers() {
		try {		
			createDupDB =  new AddToFavorites();
			createDupDB.createDupForNotifications();
			
			proList = createDupDB.readJsonFile();
			for(int count = 0;count<proList.size();count++){
				mailDTO = proList.get(count);
				NotifyUsers mailNotify = new NotifyUsers();
				System.out.println(Float.parseFloat(mailDTO.getPercentOff().substring(0,mailDTO.getPercentOff().length()-1)));
				if(!mailDTO.isNotifyFlag() && Float.parseFloat(mailDTO.getPercentOff().substring(0,mailDTO.getPercentOff().length()-1))>=20){
					mailNotify.send(mailDTO);
					System.out.println("Mail Id: "+mailDTO.getMailId());
					System.out.println("Pro Id: "+mailDTO.getProductId());
					createDupDB.updateNotifyFlag(mailDTO);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

}
