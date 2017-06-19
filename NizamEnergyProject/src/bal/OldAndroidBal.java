package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class OldAndroidBal {

	final static Logger logger = Logger.getLogger(OldAndroidBal.class);

	public static HashMap<String, String> getDashboardData(String userType,
			int userId) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con
					.prepareCall("{CALL get_payment_status(?, ?)}");
			cs.setString(1, userType);
			cs.setInt(2, userId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}
			if (rs.next()) {
				for (String column : columns) {
					map.put(column, rs.getString(column));
				}
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getFOCounters(int foId) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cstmt = con
					.prepareCall("{CALL get_counts_for_android_app_fo(?)}");
			cstmt.setInt(1, foId);
			ResultSet resutlSet = cstmt.executeQuery();
			ResultSetMetaData metaDeta = resutlSet.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaDeta.getColumnLabel(a + 1);
			}
			if (resutlSet.next()) {
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resutlSet.getString(columns[i]));
				}
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getPaymentHistory(
			String userType, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con
					.prepareCall("{CALL get_payment_history(?, ?)}");
			cs.setString(1, userType);
			cs.setInt(2, userId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String column : columns) {
					map.put(column, rs.getString(column));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllCustomersByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_eligibility_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDefaultedCustomersByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_defaulted_customers_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSalesmen(int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_salesmen(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllHandoveredEligibilityByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_handovered_eligibility_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomersDetailBySalesmanId(
			Integer salesmanId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = connection.Connect.getConnection();) {
			CallableStatement cs = con
					.prepareCall("{CALL get_customer_by_salemanId(?)}");
			cs.setInt(1, salesmanId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllCustomerBySaler(
			int salerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cs = con
					.prepareCall("{CALL get_recoveries_by_vle(?)}");
			cs.setInt(1, salerId);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (rs.next()) {

				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, rs.getString(string));
				}

				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}// end of getting all customers by SalerID

	public static ArrayList<HashMap<String, String>> getDefaultCustomer(
			int salesmanId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			CallableStatement cs = connection
					.prepareCall("{call get_default_customers(?)}");
			cs.setInt(1, salesmanId);
			ResultSet rs = cs.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaData.getColumnLabel(a + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getAllReadyForPickUpEligibilityByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_ready_for_pickup_eligibility_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> getVLECounters(int vleId) {

		HashMap<String, String> map = new HashMap<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement cstmt = con
					.prepareCall("{CALL get_counts_for_android_app_vle(?)}");
			cstmt.setInt(1, vleId);
			ResultSet resutlSet = cstmt.executeQuery();
			ResultSetMetaData metaDeta = resutlSet.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];

			for (int a = 0; a < columns.length; a++) {
				columns[a] = metaDeta.getColumnLabel(a + 1);
			}
			if (resutlSet.next()) {
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resutlSet.getString(columns[i]));
				}
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	}

	public static String updateApplianceStatus(int applianceId,
			int applianceStatus) {
		String phoneNo = "";
		int row = 0;
		try (Connection con = Connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet rs = null;
			row = statement.executeUpdate("UPDATE appliance SET `status` = "
					+ applianceStatus + " WHERE `appliance_id` = "
					+ applianceId);
			statement = con.createStatement();
			rs = statement
					.executeQuery("Select cs.customer_phone from eligibility e JOIN customer cs "
							+ "ON cs.customer_id = e.customer_id WHERE e.appliance_id = "
							+ applianceId);

			while (rs.next()) {
				phoneNo = rs.getString(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return phoneNo + ":" + row;
	}

}
