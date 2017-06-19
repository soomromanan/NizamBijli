/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 * 
 * @author Jetander
 */
public class CustomerLoanBean {

	private int customerId;
	private int appliance_id;
	private String customerName;
	private String cnicNo;
	private String phoneNo;
	private String address;
	private String occpation;
	private Double monthlyIncome;
	private int familyMember;
	private String paymentMethod;
	private String customer_pic;
	private String appliance_pic;
	private String form_pic;
	private String Created_on;
	private String Terminate_date;
	private int total_installments;
	private double total_amount;
	private int grace_period;
	private double downPayment;
	private double recent_payed_amount;
	private double remaining_balanse;
	private double monthly_amount;
	private String salemanPhone;
	private String gsmNumber;
	private String imeiNumber;

	private String applianceName;
	private double appliancePrice;
	private int applianceStatus;
	private int loanId;
	private int salesmanId;
	private String salesmanName;
	private String loanStatus;
	private String fieldOfficer;
	private String userName;
	private int other_loan;
	private int foid;
	private int doid;
	private int Customer_Rating;
	
	
	
	public int getCustomer_Rating() {
		return Customer_Rating;
	}



	public void setCustomer_Rating(int customer_Rating) {
		Customer_Rating = customer_Rating;
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



	public int getOther_loan() {
		return other_loan;
	}



	public void setOther_loan(int other_loan) {
		this.other_loan = other_loan;
	}



	public String getFieldOfficer() {
		return fieldOfficer;
	}
	
	

	public String getImeiNumber() {
		return imeiNumber;
	}



	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}



	public void setFieldOfficer(String fieldOfficer) {
		this.fieldOfficer = fieldOfficer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAppliance_id() {
		return appliance_id;
	}

	public void setAppliance_id(int appliance_id) {
		this.appliance_id = appliance_id;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public CustomerLoanBean() {
	}

	public double getMonthly_amount() {
		return monthly_amount;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public void setMonthly_amount(double monthly_amount) {
		this.monthly_amount = monthly_amount;
	}

	public double getRemaining_balanse() {
		return remaining_balanse;
	}

	public void setRemaining_balanse(double remaining_balanse) {
		this.remaining_balanse = remaining_balanse;
	}

	public String getCreated_on() {
		return Created_on;
	}

	public void setCreated_on(String Created_on) {
		this.Created_on = Created_on;
	}

	public String getTerminate_date() {
		return Terminate_date;
	}

	public void setTerminate_date(String Terminate_date) {
		this.Terminate_date = Terminate_date;
	}

	public int getTotal_installments() {
		return total_installments;
	}

	public void setTotal_installments(int total_installments) {
		this.total_installments = total_installments;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public int getGrace_period() {
		return grace_period;
	}

	public void setGrace_period(int grace_period) {
		this.grace_period = grace_period;
	}

	public double getRecent_payed_amount() {
		return recent_payed_amount;
	}

	public void setRecent_payed_amount(double recent_payed_amount) {
		this.recent_payed_amount = recent_payed_amount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCnicNo() {
		return cnicNo;
	}

	public void setCnicNo(String cnicNo) {
		this.cnicNo = cnicNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccpation() {
		return occpation;
	}

	public void setOccpation(String occpation) {
		this.occpation = occpation;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(int familyMember) {
		this.familyMember = familyMember;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCustomer_pic() {
		return customer_pic;
	}

	public void setCustomer_pic(String customer_pic) {
		this.customer_pic = customer_pic;
	}

	public String getAppliance_pic() {
		return appliance_pic;
	}

	public void setAppliance_pic(String appliance_pic) {
		this.appliance_pic = appliance_pic;
	}

	public String getForm_pic() {
		return form_pic;
	}

	public void setForm_pic(String form_pic) {
		this.form_pic = form_pic;
	}
	
	public String getGsmNumber() {
		return gsmNumber;
	}

	public void setGsmNumber(String gsmNumber) {
		this.gsmNumber = gsmNumber;
	}

	public String getApplianceName() {
		return applianceName;
	}

	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}

	public double getAppliancePrice() {
		return appliancePrice;
	}

	public void setAppliancePrice(double appliancePrice) {
		this.appliancePrice = appliancePrice;
	}

	public int getApplianceStatus() {
		return applianceStatus;
	}

	public void setApplianceStatus(int applianceStatus) {
		this.applianceStatus = applianceStatus;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSalemanPhone() {
		return salemanPhone;
	}

	public void setSalemanPhone(String salemanPhone) {
		this.salemanPhone = salemanPhone;
	}

	@Override
	public String toString() {
		return "CustomerLoanBean [customerId=" + customerId + ", customerName="
				+ customerName + ", cnicNo=" + cnicNo + ", phoneNo=" + phoneNo
				+ ", address=" + address + ", occpation=" + occpation
				+ ", monthlyIncome=" + monthlyIncome + ", familyMember="
				+ familyMember + ", paymentMethod=" + paymentMethod
				+ ", customer_pic=" + customer_pic + ", appliance_pic="
				+ appliance_pic + ", form_pic=" + form_pic + ", Created_on="
				+ Created_on + ", Terminate_date=" + Terminate_date
				+ ", total_installments=" + total_installments
				+ ", total_amount=" + total_amount + ", grace_period="
				+ grace_period + ", downPayment=" + downPayment
				+ ", recent_payed_amount=" + recent_payed_amount
				+ ", remaining_balanse=" + remaining_balanse
				+ ", monthly_amount=" + monthly_amount + ", salemanPhone="
				+ salemanPhone + ", gsmNumber=" + gsmNumber
				+ ", applianceName=" + applianceName + ", appliancePrice="
				+ appliancePrice + ", applianceStatus=" + applianceStatus
				+ ", loanId=" + loanId + ", salesmanId=" + salesmanId
				+ ", salesmanName=" + salesmanName + "]";
	}
	
}
