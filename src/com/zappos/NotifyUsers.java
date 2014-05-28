/************************************************************************************
 * File             :   NotifyUsers.java
 * Description      :   This class is used to design and send Mail Notifications 
 ************************************************************************************/
package com.zappos;

import com.github.kevinsawicki.http.HttpRequest;

public class NotifyUsers {

	private static final String PARAM_API_USER = "api_user";
	private static final String PARAM_API_KEY = "api_key";
	private static final String PARAM_TOS = "to[]";
	private static final String PARAM_FROM = "from";
	private static final String PARAM_SUBJECT = "subject";
	private static final String PARAM_HTML = "html";
	
	//To send mails to the users
	public String send(PriceNotifProdDetDTO mailDTO) {
		return this.web(mailDTO);
	}

	//To set all the details required for mailing
	public String web(PriceNotifProdDetDTO mailDTO) {

		HttpRequest request = HttpRequest.post("https://api.sendgrid.com/api/mail.send.json");

		if (mailDTO != null) {
			request.part(PARAM_API_USER, "sendRenuGrid");
		}
		if (mailDTO != null) {
			request.part(PARAM_API_KEY, "sendgrid8");
		}
		if (mailDTO.getMailId() != null) {
			request.part(PARAM_TOS, mailDTO.getMailId());
		}

		if (mailDTO != null) {
			request.part(PARAM_FROM, "PriceNotifications@zappos.com");
		}
		if (mailDTO != null) {
			request.part(PARAM_SUBJECT,
					"It's your lucky day at Zappos! Hurry Up!");
		}
		if (mailDTO != null) {
			request.part(PARAM_HTML, setMailContent(mailDTO));
		}
		return request.body();
	}

	//To set mail content
	private String setMailContent(PriceNotifProdDetDTO mailDTO) {
		// TODO Auto-generated method stub
		System.out.println(mailDTO.getThumbnailImageUrl());
		String s1 = "<html><head></head><body><h1> Your favourite product is on sale! </h1><br/><table border=0 cellpadding=0 cellspacing=0>"
				+ "<tr><td rowspan=3 ><a href='"+ mailDTO.getProductUrl()+ "'><img src='"+ mailDTO.getThumbnailImageUrl()+ "'/></a>"+
				"</td><td></td><td></td><td></td><td><h4 style=\"color:blue\";> Product Name: "+ mailDTO.getProductName()+ "</h4>\n<h4 style=\"color:red\";> Percent Off: "+ mailDTO.getPercentOff() + "</h4>\n <h4 style=\"color:blue\";>Original Price: "+ mailDTO.getOriginalPrice() + "</h4></td>";
				/*"<td valign='bottom' style=\"color:blue;\"><h4> Product Name: "+ mailDTO.getProductName()+ "</h4></td></tr>"+
				"<tr><td style=\"color:red;\"><h4> Percent Off: "+ mailDTO.getPercentOff() + "</h4></td></tr>"+
				"<tr><td valign='top' style=\"color:blue;\"><h4>Original Price: "+ mailDTO.getOriginalPrice() + "</h4></td></tr><tr></tr><tr></tr>";*/
		String s2 = "</table> <br/><h4 style=\"color:blue;\">Click on product to start partying!</h4></body></html>";
		return s1 + s2;
	}

}
