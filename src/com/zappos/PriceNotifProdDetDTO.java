/************************************************************************************
 * File             :   PriceNotifProdDetDTO.java
 * Description      :   This class is used as a Transfer Object for Product Details
 ************************************************************************************/
package com.zappos;

import java.net.URL;

public class PriceNotifProdDetDTO {
	
	private String styleId;
	private String price;
	private String originalPrice;
	private URL productUrl;
	private String colorId;
	private String productName;
	private String brandName;
	private String percentOff;
	private URL thumbnailImageUrl;
	private String productId;
	private String mailId;
	private boolean notifyFlag;
	private boolean searchFlag;
	private String errorMsg;
	
	
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the searchFlag
	 */
	public boolean isSearchFlag() {
		return searchFlag;
	}
	/**
	 * @param searchFlag the searchFlag to set
	 */
	public void setSearchFlag(boolean searchFlag) {
		this.searchFlag = searchFlag;
	}
	/**
	 * @return the notifyFlag
	 */
	public boolean isNotifyFlag() {
		return notifyFlag;
	}
	/**
	 * @param notifyFlag the notifyFlag to set
	 */
	public void setNotifyFlag(boolean notifyFlag) {
		this.notifyFlag = notifyFlag;
	}
	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	/**
	 * @return the styleId
	 */
	public String getStyleId() {
		return styleId;
	}
	/**
	 * @param styleId the styleId to set
	 */
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the originalPrice
	 */
	public String getOriginalPrice() {
		return originalPrice;
	}
	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	/**
	 * @return the productUrl
	 */
	public URL getProductUrl() {
		return productUrl;
	}
	/**
	 * @param productUrl the productUrl to set
	 */
	public void setProductUrl(URL productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * @return the colorId
	 */
	public String getColorId() {
		return colorId;
	}
	/**
	 * @param colorId the colorId to set
	 */
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the percentOff
	 */
	public String getPercentOff() {
		return percentOff;
	}
	/**
	 * @param percentOff the percentOff to set
	 */
	public void setPercentOff(String percentOff) {
		this.percentOff = percentOff;
	}
	/**
	 * @return the thumbnailImageUrl
	 */
	public URL getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}
	/**
	 * @param thumbnailImageUrl the thumbnailImageUrl to set
	 */
	public void setThumbnailImageUrl(URL thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	
	
}
