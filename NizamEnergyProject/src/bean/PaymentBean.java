/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;

/**
 *
 * @author Jeatnder Khatri
 */
public class PaymentBean {
    private String customerName;
    private double totalAmount;
    private  int installmentCount;
    private int installmentRemaining;
    private double totalPayedInstallment;
    private Date PayedDate;
    private String InstallmentPayedMonth ;
    private double recent_installment_amount;
    private int gracePeriod;
    private Date createdOn;
    private Date terminatedOn;

    public PaymentBean() {}

    public PaymentBean(String customerName, double totalAmount, int installmentCount, int installmentRemaining, double totalPayedInstallment, Date PayedDate, String InstallmentPayedMonth, double recent_installment_amount, int gracePeriod, Date createdOn, Date terminatedOn) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.installmentCount = installmentCount;
        this.installmentRemaining = installmentRemaining;
        this.totalPayedInstallment = totalPayedInstallment;
        this.PayedDate = PayedDate;
        this.InstallmentPayedMonth = InstallmentPayedMonth;
        this.recent_installment_amount = recent_installment_amount;
        this.gracePeriod = gracePeriod;
        this.createdOn = createdOn;
        this.terminatedOn = terminatedOn;
    }

    

//    public int getLoanId() {
//        return loanId;
//    }
//
//    public void setLoanId(int loanId) {
//        this.loanId = loanId;
//    }
//
//    public int getSoldId() {
//        return soldId;
//    }
//
//    public void setSoldId(int soldId) {
//        this.soldId = soldId;
//    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(int installmentCount) {
        this.installmentCount = installmentCount;
    }

    public int getInstallmentRemaining() {
        return installmentRemaining;
    }

    public void setInstallmentRemaining(int installmentRemaining) {
        this.installmentRemaining = installmentRemaining;
    }

    public double getTotalPayedInstallment() {
        return totalPayedInstallment;
    }

    public void setTotalPayedInstallment(double totalPayedInstallment) {
        this.totalPayedInstallment = totalPayedInstallment;
    }

    public Date getPayedDate() {
        return PayedDate;
    }

    public void setPayedDate(Date PayedDate) {
        this.PayedDate = PayedDate;
    }

    public String getInstallmentPayedMonth() {
        return InstallmentPayedMonth;
    }

    public void setInstallmentPayedMonth(String InstallmentPayedMonth) {
        this.InstallmentPayedMonth = InstallmentPayedMonth;
    }

    public double getRecent_installment_amount() {
        return recent_installment_amount;
    }

    public void setRecent_installment_amount(double recent_installment_amount) {
        this.recent_installment_amount = recent_installment_amount;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getTerminatedOn() {
        return terminatedOn;
    }

    public void setTerminatedOn(Date terminatedOn) {
        this.terminatedOn = terminatedOn;
    }

    @Override
    public String toString() {
        return "PaymentBean{" + "customerName=" + customerName + ", totalAmount=" + totalAmount + ", installmentCount=" + installmentCount + ", installmentRemaining=" + installmentRemaining + ", totalPayedInstallment=" + totalPayedInstallment + ", PayedDate=" + PayedDate + ", InstallmentPayedMonth=" + InstallmentPayedMonth + ", recent_installment_amount=" + recent_installment_amount + ", gracePeriod=" + gracePeriod + ", createdOn=" + createdOn + ", terminatedOn=" + terminatedOn + '}';
    }
    
    
    
}
