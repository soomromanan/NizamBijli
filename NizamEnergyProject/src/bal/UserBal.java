package bal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import security.Hashing;
import bean.UserBean;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class UserBal {

	final static Logger logger = Logger.getLogger(UserBal.class);

	public static UserBean checkUser(String email, String pasword) {
		UserBean bean = null;
		String pass = Hashing.getMD5(pasword);
		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL check_user(?, ?)}");
				prepareCall.setString(1, email);
				prepareCall.setString(2, pass);
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					bean = new UserBean();
					bean.setUserId(resultSet.getInt(1));
					bean.setUserName(resultSet.getString(2));
					bean.setEmail(resultSet.getString(3));
					bean.setPassword(resultSet.getString(4));
					bean.setCnicNo(resultSet.getString(5));
					bean.setUserType(resultSet.getInt(6));
					bean.setGender(resultSet.getString(7));
					bean.setUser_district(resultSet.getInt(8));
					bean.setUser_district_name(resultSet.getString(9));
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return bean;
	}

	public static boolean checkEmail(String email) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_email(?)}");
				prepareCall.setString(1, email);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.info(e);
		}
		return result;
	}

	public static void updateUserPassword(String password, String email) {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL update_password(?,?)}");
			prepareCall.setString(1, email);
			prepareCall.setString(2, password);
			prepareCall.executeQuery();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static String getUserEmailByMobileNumber(String mobileNumber) {
		String email = "";
		try (Connection con = Connect.getConnection();) {
			String query = "SELECT user_email FROM user WHERE primary_phone_number=?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, mobileNumber);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				email = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return email;
	}

	public static int addUserIp(int userId, String localIp, String publicIp) {
		int count = 0;
		try (Connection connect = Connect.getConnection()) {
			PreparedStatement ps = (PreparedStatement) connect
					.prepareStatement("INSERT INTO user_ip (user_id,local_ip,public_ip,created_on)VALUES (?,?,?,CURDATE());");
			ps.setInt(1, userId);
			ps.setString(2, localIp);
			ps.setString(3, publicIp);
			count = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

}
