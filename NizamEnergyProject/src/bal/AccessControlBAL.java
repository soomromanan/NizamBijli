package bal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import connection.Connect;

public class AccessControlBAL {

	final static Logger logger = Logger.getLogger(AccessControlBAL.class);

	public static List<String> getScreenByUserType(int userType) {
		List<String> screenList = new ArrayList<String>();
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			PreparedStatement prepareCall = (PreparedStatement) con
					.prepareStatement("SELECT screen FROM access_control WHERE user_type <> ? and user_type IS NOT NULL ");
			prepareCall.setInt(1, userType);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				screenList.add(rs.getString("screen"));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return screenList;
	}

	public static void main(String[] argv) {
		System.out.println(getScreenByUserType(100));
	}

}
