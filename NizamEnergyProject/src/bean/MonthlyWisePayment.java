/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author waseem
 */
public class MonthlyWisePayment {
    
    private int customer_id,remaining_balance,outstanding,grace_consumed,payment_method,days,days_activated,remaining_days;
    private Date due_date,paid_date;
    private String trasnction_id,paid_amount,appliance_imei;
    

    public MonthlyWisePayment() {
    }

    public String getTrasnction_id() {
        return trasnction_id;
    }

    public void setTrasnction_id(String trasnction_id) {
        this.trasnction_id = trasnction_id;
    }

    public MonthlyWisePayment(int customer_id, int remaining_balance, int outstanding, int grace_consumed, int payment_method, String paid_amount, int days, Date due_date, Date paid_date, int days_activated, String trasnction_id) {
        this.customer_id = customer_id;
        this.remaining_balance = remaining_balance;
        this.outstanding = outstanding;
        this.grace_consumed = grace_consumed;
        this.payment_method = payment_method;
        this.paid_amount = paid_amount;
        this.days = days;
        this.due_date = due_date;
        this.paid_date = paid_date;
        this.days_activated = days_activated;
        this.trasnction_id = trasnction_id;
    }

  

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    
    
    public int getRemaining_days() {
		return remaining_days;
	}

	public void setRemaining_days(int remaining_days) {
		this.remaining_days = remaining_days;
	}

	public int getRemaining_balance() {
        return remaining_balance;
    }

    public void setRemaining_balance(int remaining_balance) {
        this.remaining_balance = remaining_balance;
    }

    public int getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(int outstanding) {
        this.outstanding = outstanding;
    }

    public int getGrace_consumed() {
        return grace_consumed;
    }

    public void setGrace_consumed(int grace_consumed) {
        this.grace_consumed = grace_consumed;
    }

    public int getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(int payment_method) {
        this.payment_method = payment_method;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
    }

    public int getDays_activated() {
        return days_activated;
    }

    public void setDays_activated(int days_activated) {
        this.days_activated = days_activated;
    }

    public String getAppliance_imei() {
		return appliance_imei;
	}

	public void setAppliance_imei(String appliance_imei) {
		this.appliance_imei = appliance_imei;
	}

	@Override
    public String toString() {
        return "Payment_Daywise{" + "customer_id=" + customer_id + ", remaining_balance=" + remaining_balance + ", outstanding=" + outstanding + ", grace_consumed=" + grace_consumed + ", payment_method=" + payment_method + ", paid_amount=" + paid_amount + ", days=" + days + ", due_date=" + due_date + ", paid_date=" + paid_date + ", days_activated=" + days_activated + ", trasnction_id=" + trasnction_id + '}';
    }

   
    
    
    
}