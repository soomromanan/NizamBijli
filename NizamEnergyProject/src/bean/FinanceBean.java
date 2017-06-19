/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author AqeelRahu
 */
public class FinanceBean {
    private String customer_name;
    private String imei_number;
    private String consumer_number;
    private String app_name;
    private String Bank_name;
    private double installment_paid;
    private String date;
    private String transaction_id;
    private double total_installement_payments;
    private double total_amount;
    private double total_down_pament;
    private String payment_status;
    private int totalInstallmentPaymentsCount;
    private int totalDownPaymentsCount;
    private int newDownPaymentsCount;
    private int newInstalment;
    
    public int getDown_payment_count() {
		return down_payment_count;
	}
	public void setDown_payment_count(int down_payment_count) {
		this.down_payment_count = down_payment_count;
	}
	public void setNewInstallment(int newInstalment) {
		this.newInstalment = newInstalment;
	}
	public int getNewInstallment() {
		return newInstalment;
	}
	public void setNew_Down_payment_count(int newDownPaymentsCount) {
		this.newDownPaymentsCount = newDownPaymentsCount;
	}
	public String getFo_name() {
		return fo_name;
	}
	public void setFo_name(String fo_name) {
		this.fo_name = fo_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getNd_name() {
		return nd_name;
	}
	public void setNd_name(String nd_name) {
		this.nd_name = nd_name;
	}
	public double getFo_commission_per_device() {
		return fo_commission_per_device;
	}
	public void setFo_commission_per_device(double fo_commission_per_device) {
		this.fo_commission_per_device = fo_commission_per_device;
	}
	public double getNd_commission_per_device() {
		return nd_commission_per_device;
	}
	public void setNd_commission_per_device(double nd_commission_per_device) {
		this.nd_commission_per_device = nd_commission_per_device;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public int getTotalInstallmentPaymentsCount() {
		return totalInstallmentPaymentsCount;
	}
	public void setTotalInstallmentPaymentsCount(int totalInstallmentPaymentsCount) {
		this.totalInstallmentPaymentsCount = totalInstallmentPaymentsCount;
	}
	public int getTotalDownPaymentsCount() {
		return totalDownPaymentsCount;
	}
	public void setTotalDownPaymentsCount(int totalDownPaymentsCount) {
		this.totalDownPaymentsCount = totalDownPaymentsCount;
	}



	private int down_payment_count;
    private String fo_name;
    private String nd_name;
    private double fo_commission_per_device;
    private double nd_commission_per_device;
    private String district_name;
    public FinanceBean() {
		// TODO Auto-generated constructor stub
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getImei_number() {
		return imei_number;
	}
	public String getConsumer_number() {
		return consumer_number;
	}
	public void setImei_number(String imei_number) {
		this.imei_number = imei_number;
	}
	
	public void setConsumer_number(String consumer_number) {
		this.consumer_number = consumer_number;
	}
	public String getBank_name() {
		return Bank_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setBank_name(String bank_name) {
		Bank_name = bank_name;
	}
	public double getInstallment_paid() {
		return installment_paid;
	}
	public void setInstallment_paid(double installment_paid) {
		this.installment_paid = installment_paid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public double getTotal_installement_payments() {
		return total_installement_payments;
	}
	public void setTotal_installement_payments(double total_installement_payments) {
		this.total_installement_payments = total_installement_payments;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public double getTotal_down_pament() {
		return total_down_pament;
	}
	public int getNew_down_pament_count() {
		return newDownPaymentsCount;
	}
	public void setTotal_down_pament(double total_down_pament) {
		this.total_down_pament = total_down_pament;
	}
   

    
	@Override
	public String toString() {
		return "FinanceBean [customer_name=" + customer_name + ", imei_number="
				+ imei_number + ", Bank_name=" + Bank_name
				+ ", installment_paid=" + installment_paid + ", date=" + date
				+ ", transaction_id=" + transaction_id + "]";
	}
}
