package bean;

import java.sql.Blob;

public class CustomerInfoBean {
    
    private int soldId, applianceId, customerId, salesamanId, familySize, status, alertId, customerStatus,applianceStatus,transferStatus,eligibilityStatus,isDefaulted;
    
    private String foName, doName, familyIncome,purpose, monthlyIncome,customerName, occupation, cnicNo, phoneNo, address, district, gsmNumber, salesmanName, createdOn, handoverAt, applianceName,loanStatus ;
    private boolean state, paymentMethod;
    private Blob image;

    public CustomerInfoBean() {
    }

    public CustomerInfoBean(int soldId, int applianceId, int customerId,
			int salesamanId, int familySize, int status, int alertId,
			int customerStatus, String familyIncome, String monthlyIncome,
			String customerName, String occupation, String cnicNo,
			String phoneNo, String address, String district, String gsmNumber,
			String salesmanName, String createdOn, String handoverAt,
			String applianceName, boolean state, boolean paymentMethod,
			Blob image) {
		super();
		this.soldId = soldId;
		this.applianceId = applianceId;
		this.customerId = customerId;
		this.salesamanId = salesamanId;
		this.familySize = familySize;
		this.status = status;
		this.alertId = alertId;
		this.customerStatus = customerStatus;
		this.familyIncome = familyIncome;
		this.monthlyIncome = monthlyIncome;
		this.customerName = customerName;
		this.occupation = occupation;
		this.cnicNo = cnicNo;
		this.phoneNo = phoneNo;
		this.address = address;
		this.district = district;
		this.gsmNumber = gsmNumber;
		this.salesmanName = salesmanName;
		this.createdOn = createdOn;
		this.handoverAt = handoverAt;
		this.applianceName = applianceName;
		this.state = state;
		this.paymentMethod = paymentMethod;
		this.image = image;
	}
    
    

	public String getFoName() {
		return foName;
	}

	public void setFoName(String foName) {
		this.foName = foName;
	}

	public String getDoName() {
		return doName;
	}

	public void setDoName(String doName) {
		this.doName = doName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getApplianceStatus() {
		return applianceStatus;
	}

	public void setApplianceStatus(int applianceStatus) {
		this.applianceStatus = applianceStatus;
	}

	public String getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

	public int getAlertId() {
		return alertId;
	}

	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public int getSoldId() {
        return soldId;
    }

    public void setSoldId(int soldId) {
        this.soldId = soldId;
    }

    public int getApplianceId() {
        return applianceId;
    }

    public void setApplianceId(int applianceId) {
        this.applianceId = applianceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getHandoverAt() {
        return handoverAt;
    }

    public void setHandoverAt(String handoverAt) {
        this.handoverAt = handoverAt;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalesamanId() {
        return salesamanId;
    }

    public void setSalesamanId(int salesamanId) {
        this.salesamanId = salesamanId;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public boolean getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(boolean paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public int getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(int transferStatus) {
		this.transferStatus = transferStatus;
	}

	public int getEligibilityStatus() {
		return eligibilityStatus;
	}

	public void setEligibilityStatus(int eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}


	public int getIsDefaulted() {
		return isDefaulted;
	}

	public void setIsDefaulted(int isDefaulted) {
		this.isDefaulted = isDefaulted;
	}

	@Override
	public String toString() {
		return "CustomerInfoBean [soldId=" + soldId + ", applianceId="
				+ applianceId + ", customerId=" + customerId + ", salesamanId="
				+ salesamanId + ", familySize=" + familySize + ", status="
				+ status + ", alertId=" + alertId + ", customerStatus="
				+ customerStatus + ", applianceStatus=" + applianceStatus
				+ ", transferStatus=" + transferStatus + ", eligibilityStatus="
				+ eligibilityStatus + ", familyIncome=" + familyIncome
				+ ", monthlyIncome=" + monthlyIncome + ", customerName="
				+ customerName + ", occupation=" + occupation + ", cnicNo="
				+ cnicNo + ", phoneNo=" + phoneNo + ", address=" + address
				+ ", district=" + district + ", gsmNumber=" + gsmNumber
				+ ", salesmanName=" + salesmanName + ", createdOn=" + createdOn
				+ ", handoverAt=" + handoverAt + ", applianceName="
				+ applianceName + ", loanStatus=" + loanStatus + ", state="
				+ state + ", paymentMethod=" + paymentMethod + ", image="
				+ image + "]";
	}

	  
    
}
