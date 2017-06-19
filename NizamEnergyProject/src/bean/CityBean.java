package bean;

public class CityBean {

	private int city_id;
	private String city_name;
	private String district_name;

	public CityBean() {
		// TODO Auto-generated constructor stub
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "City_Id " + city_id + " City_name " + city_name;
	}

}
