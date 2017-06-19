package bean;

import java.util.Date;

public class SalesManBean {

	private int salesmanId;
	private String Name;
	private String district;
	private String address;
	private Double Total_commission;
	private String cnic;
	private Double sallery;
	private int customer_on_cash;
	private String employee_status;
	private int lateCustomer;
	private int beforeTime;
	private int onTime;
	private int afterTime;
	private int totalSales;
	private double recovery;
	private double commission;
	private int totalCustomers;
	private Date date_of_birth;
	private String gender;
	private String marritalStatus;
	private String blood_group;
	private String vehicle;
	private String educated;
	private String institue;
	private int imageId;
	private int commission_date;
	private String tahsel;
	private String foname;
	private String doname;
	private int per_sale;
	private String vle_acount_no;
	private int doid;
	private int foid;
	private String salesman_email;
	private String district_name;
	
	
	
	
	
	public int getFoid() {
		return foid;
	}
	public void setFoid(int foid) {
		this.foid = foid;
	}
	public int getDoid() {
		return doid;
	}
	public void setDoid(int doid) {
		this.doid = doid;
	}
	public int get_commission_date(){
		return commission_date;
		
	}
	public void set__commission_date(int commission_date){
		this.commission_date=commission_date;
	}
	
	
	
	public int getCommission_date() {
		return commission_date;
	}
	public void setCommission_date(int commission_date) {
		this.commission_date = commission_date;
	}
	public String getTahsel() {
		return tahsel;
	}
	public void setTahsel(String tahsel) {
		this.tahsel = tahsel;
	}
	public String getFoname() {
		return foname;
	}
	public void setFoname(String foname) {
		this.foname = foname;
	}
	public String getDoname() {
		return doname;
	}
	public void setDoname(String doname) {
		this.doname = doname;
	}
	public String getEducated() {
		return educated;
	}

	public void setEducated(String educated) {
		this.educated = educated;
	}

	public String getInstitue() {
		return institue;
	}

	public void setInstitue(String institue) {
		this.institue = institue;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	private String phone_number2;
	private String licence_no;
	private String degree;
	private Date start_date;
	private Date end_date;
	private double percentage;
	private double salesman_basic_sallery;

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getSalesman_basic_sallery() {
		return salesman_basic_sallery;
	}

	public void setSalesman_basic_sallery(double salesman_basic_sallery) {
		this.salesman_basic_sallery = salesman_basic_sallery;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	private double sal_lat;
	private double sal_lng;

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getPhone_number2() {
		return phone_number2;
	}

	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}

	public String getMarritalStatus() {
		return marritalStatus;
	}

	public void setMarritalStatus(String marritalStatus) {
		this.marritalStatus = marritalStatus;
	}

	public double getSal_lat() {
		return sal_lat;
	}

	public void setSal_lat(double sal_lat) {
		this.sal_lat = sal_lat;
	}

	public double getSal_lng() {
		return sal_lng;
	}

	public void setSal_lng(double sal_lng) {
		this.sal_lng = sal_lng;
	}

	public int getLateCustomer() {
		return lateCustomer;
	}

	public void setLateCustomer(int lateCustomer) {
		this.lateCustomer = lateCustomer;
	}

	public int getCustomer_on_cash() {
		return customer_on_cash;
	}

	public String getEmployee_status() {
		return employee_status;
	}

	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
	}

	public void setCustomer_on_cash(int customer_on_cash) {
		this.customer_on_cash = customer_on_cash;
	}

	public int getTotal_customer() {
		return total_customer;
	}

	public void setTotal_customer(int total_customer) {
		this.total_customer = total_customer;
	}

	public int getCustomer_on_installments() {
		return customer_on_installments;
	}

	public void setCustomer_on_installments(int customer_on_installments) {
		this.customer_on_installments = customer_on_installments;
	}

	private String phone_number;
	private Date datejoin;
	private int total_customer;
	private int customer_on_installments;

	public SalesManBean(int salesmanId, String Name, String district,
			String address, Double Total_commission, String cnic,
			Double sallery, String phone_number, Date datejoin) {
		this.salesmanId = salesmanId;
		this.Name = Name;
		this.district = district;
		this.address = address;
		this.Total_commission = Total_commission;
		this.cnic = cnic;
		this.sallery = sallery;
		this.phone_number = phone_number;
		this.datejoin = datejoin;
	
	}

	
	public SalesManBean() {
	}

	public SalesManBean(int salesmanId) {

		this.salesmanId = salesmanId;
	}

	public int getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getTotal_commission() {
		return Total_commission;
	}

	public void setTotal_commission(Double Total_commission) {
		this.Total_commission = Total_commission;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public Double getSallery() {
		return sallery;
	}

	public void setSallery(Double sallery) {
		this.sallery = sallery;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getDatejoin() {
		return datejoin;
	}

	public void setDatejoin(Date datejoin) {
		this.datejoin = datejoin;
	}

	public int getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(int beforeTime) {
		this.beforeTime = beforeTime;
	}

	public int getOnTime() {
		return onTime;
	}

	public void setOnTime(int onTime) {
		this.onTime = onTime;
	}

	public int getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(int afterTime) {
		this.afterTime = afterTime;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}

	public double getRecovery() {
		return recovery;
	}

	public void setRecovery(double recovery) {
		this.recovery = recovery;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public int getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(int totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	

	public int getPer_sale() {
		return per_sale;
	}
	public void setPer_sale(int per_sale) {
		this.per_sale = per_sale;
	}
	public String getVle_acount_no() {
		return vle_acount_no;
	}
	public void setVle_acount_no(String vle_acount_no) {
		this.vle_acount_no = vle_acount_no;
	}
	
	public String getSalesman_email() {
		return salesman_email;
	}
	public void setSalesman_email(String salesman_email) {
		this.salesman_email = salesman_email;
	}
	
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	@Override
	public String toString() {
		return "SalesManBean [salesmanId=" + salesmanId + ", Name=" + Name
				+ ", district=" + district + ", address=" + address
				+ ", Total_commission=" + Total_commission + ", cnic=" + cnic
				+ ", sallery=" + sallery + ", customer_on_cash="
				+ customer_on_cash + ", employee_status=" + employee_status
				+ ", lateCustomer=" + lateCustomer + ", beforeTime="
				+ beforeTime + ", onTime=" + onTime + ", afterTime="
				+ afterTime + ", totalSales=" + totalSales + ", recovery="
				+ recovery + ", commission=" + commission + ", totalCustomers="
				+ totalCustomers + ", phone_number=" + phone_number
				+ ", datejoin=" + datejoin + ", total_customer="
				+ total_customer + ", customer_on_installments="
				+ customer_on_installments + "]";
	}

}
