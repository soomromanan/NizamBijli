/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 * 
 * @author Jetander Kumar
 */
public class ApplianceBean {

	private int doid;
	private int applianceId;
	private int salesmanId;
	private int soldtoId;
	private String salesmanPhone;
	private String applianceName;
	private double price;
	private boolean state;
	private String productId;
	private String applianceGsmNo;
	private String image;
	private String salesmanName;
	private int paymentType;
	private Date handoverDate;
	private int customerId;
	private String customerName;
	private String customerPhone;
	private String customerCnic;
	private double isOwned;
	private String imiNumber;
	private String userName;
	private String foName;
	private String consumerNumber;
	private int foid;
	private String installedDate;
	private int signal;
	private int status;
	private String statusget;
	private int healthStatus;
	private int isALive;
	private int deadSince;
	private String returnedDate;

	public ApplianceBean() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public String getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(String installedDate) {
		this.installedDate = installedDate;
	}

	public String getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public int getDoid() {
		return doid;
	}

	public void setDoid(int doid) {
		this.doid = doid;
	}

	public int getFoid() {
		return foid;
	}

	public void setFoid(int foid) {
		this.foid = foid;
	}

	public String getImiNumber() {
		return imiNumber;
	}

	public void setImiNumber(String imiNumber) {
		this.imiNumber = imiNumber;
	}

	public double getIsOwned() {
		return isOwned;
	}

	public void setIsOwned(double isOwned) {
		this.isOwned = isOwned;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanPhone() {
		return salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public Date getHandoverDate() {
		return handoverDate;
	}

	public void setHandoverDate(Date handoverDate) {
		this.handoverDate = handoverDate;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public String getApplianceName() {
		return applianceName;
	}

	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getApplianceGsmNo() {
		return applianceGsmNo;
	}

	public void setApplianceGsmNo(String applianceGsmNo) {
		this.applianceGsmNo = applianceGsmNo;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getSoldtoId() {
		return soldtoId;
	}

	public void setSoldtoId(int soldtoId) {
		this.soldtoId = soldtoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFoName() {
		return foName;
	}

	public void setFoName(String foName) {
		this.foName = foName;
	}

	public String getCustomerCnic() {
		return customerCnic;
	}

	public void setCustomerCnic(String customerCnic) {
		this.customerCnic = customerCnic;
	}

	public String getStatusget() {
		return statusget;
	}

	public void setStatusget(String statusget) {
		this.statusget = statusget;
	}

	public int getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(int healthStatus) {
		this.healthStatus = healthStatus;
	}

	public int getIsALive() {
		return isALive;
	}

	public void setIsALive(int isALive) {
		this.isALive = isALive;
	}

	public int getDeadSince() {
		return deadSince;
	}

	public void setDeadSince(int deadSince) {
		this.deadSince = deadSince;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	@Override
	public String toString() {
		return "ApplianceBean [doid=" + doid + ", applianceId=" + applianceId
				+ ", salesmanId=" + salesmanId + ", soldtoId=" + soldtoId
				+ ", salesmanPhone=" + salesmanPhone + ", applianceName="
				+ applianceName + ", price=" + price + ", state=" + state
				+ ", productId=" + productId + ", applianceGsmNo="
				+ applianceGsmNo + ", image=" + image + ", salesmanName="
				+ salesmanName + ", paymentType=" + paymentType
				+ ", handoverDate=" + handoverDate + ", customerId="
				+ customerId + ", customerName=" + customerName
				+ ", customerPhone=" + customerPhone + ", customerCnic="
				+ customerCnic + ", isOwned=" + isOwned + ", imiNumber="
				+ imiNumber + ", userName=" + userName + ", foName=" + foName
				+ ", consumerNumber=" + consumerNumber + ", foid=" + foid
				+ ", installedDate=" + installedDate + ", signal=" + signal
				+ ", status=" + status + ", statusget=" + statusget
				+ ", healthStatus=" + healthStatus + ", isALive=" + isALive
				+ ", deadSince=" + deadSince + ", returnedDate=" + returnedDate
				+ "]";
	}
}
