/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author hist
 */
public class UserBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
    private String userName;
    private String email;
    private String password;
    private String cnicNo;
    private String gender;
    private int userType;
    private String userLastLogin;
    private int user_district;
    private String user_district_name;
    private int image_id;

    public UserBean() {
    }

    public UserBean(int userId, String userName, String email, String password, String cnicNo, String gender, int userType, String userLastLogin, int user_district) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.cnicNo = cnicNo;
        this.gender = gender;
        this.userType = userType;
        this.userLastLogin = userLastLogin;
        this.user_district = user_district;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnicNo() {
        return cnicNo;
    }

    public void setCnicNo(String cnicNo) {
        this.cnicNo = cnicNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(String userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public int getUser_district() {
        return user_district;
    }

    public void setUser_district(int user_district) {
        this.user_district = user_district;
    }
    
    

    public String getUser_district_name() {
		return user_district_name;
	}

	public void setUser_district_name(String user_district_name) {
		this.user_district_name = user_district_name;
	}

	
	
	public int getImage_id() {
		return image_id;
	}

	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName
				+ ", email=" + email + ", password=" + password + ", cnicNo="
				+ cnicNo + ", gender=" + gender + ", userType=" + userType
				+ ", userLastLogin=" + userLastLogin + ", user_district="
				+ user_district + ", user_district_name=" + user_district_name
				+ ", image_id=" + image_id + "]";
	}

	

    
    
}
