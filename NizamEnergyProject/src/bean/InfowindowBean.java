/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 * 
 * @author AqeelRahu
 */
public class InfowindowBean {

	private String customer_name;
	private String app_name;
	private int scheme;
	private double lati;
	private double longi;
	private int type;
	private int applianceId;

	public InfowindowBean() {
	}

	public InfowindowBean(String customer_name, String app_name, int scheme,
			double lati, double longi) {
		this.customer_name = customer_name;
		this.app_name = app_name;
		this.scheme = scheme;
		this.lati = lati;
		this.longi = longi;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public int getScheme() {
		return scheme;
	}

	public void setScheme(int scheme) {
		this.scheme = scheme;
	}

	public double getLati() {
		return lati;
	}

	public void setLati(double lati) {
		this.lati = lati;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "InfowindowBean [customer_name=" + customer_name + ", app_name="
				+ app_name + ", scheme=" + scheme + ", lati=" + lati
				+ ", longi=" + longi + ", type=" + type + ", applianceId="
				+ applianceId + "]";
	}

}
