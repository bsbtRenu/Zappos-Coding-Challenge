Zappos Price Notification Application

Description:
1. Developed GUI using JFrame.
2. Used different files for invoking API, Java objects, Adding product details in json to a text file(db), constants in a separate file etc. as per the industry format.
3. Used two runnable files 
	PriceNotificationFrame.java
		This is used to create the UI for the user to enter the product and email id 
		Used Gson library to convert Json Object to Java object.
	scheduleMailing.java
		This is used to send mail notifications to the users. It is scheduled for every 2hours.
		Once an email notification is sent, the user will not be notified again when it runs for the second time
		Used SendGrid API for email notifications(SendGrid account credentials are included)
4. Sorry, I was not able to create runnable jar files due to image issues. Please try executing in Eclipse or cmd prompt.

Files Used:
PriceNotificationFrame.java
PriceNotificationInvokeZAPI.java
PriceNotifProdDetDTO.java
ProductSearchResponse.java
AddToFavorites.java
FamilyCoreValues.java
FamilyCoreValuesResponse.java
scheduleMailing.java
NotifyUsers.java
ZapposConstants.java

Database/Storage Files:
Add_Favorites.json
Notify_Favorites.json

Images:
searchIcon.png
windowLogo.png

Future Work:
As of now the in the application, percent-off checking is done with out connecting to Zappos API considering the traffic to the API. But it can be done by fetching the current Percent-Off field from Zappos API for that particular product and cross verifying with the Percent-off in the database.

Note:
When API reaches the max limit of hits, the application generates an exception. In such cases, please change API Key in ZapposConstants.java
