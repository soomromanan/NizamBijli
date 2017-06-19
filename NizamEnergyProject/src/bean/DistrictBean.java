/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author waseem
 */
public class DistrictBean {
    
    private int  district_id;
    private String district_name;
    private String latitude ;
    private String longitude;

   
public DistrictBean() {
    }
    public int getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(int district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "DistrictBean{" + "district_id=" + district_id + ", district_name=" + district_name + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    

    public DistrictBean(int district_id, String district_name, String latitude, String longitude) {
        this.district_id = district_id;
        this.district_name = district_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

   
    
    
    
    
}
