package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class AndroidBal {

	final static Logger logger = Logger.getLogger(AndroidBal.class);

	public static String getPassword(String phoneNumber) {
		String query = "SELECT fo.password FROM field_officer fo WHERE fo.fo_priamary_phone = ?";
		String password = "";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, phoneNumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				password = rs.getString(1);

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return password;
	}

	public static HashMap<String, String> login(String cellNumber,
			String password) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL login_fo(?, ?)}");
			prepareCall.setString(1, cellNumber);
			prepareCall.setString(2, password);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();

			String columns[] = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			if (resultSet.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return map;
	}

	public static HashMap<String, String> foPerformanceInfo(int foId) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_fo_dashboard_info(?)}");
			prepareCall.setInt(1, foId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("sales_current_month", rs.getInt("sales_current_month")
						+ "");
				map.put("recovery_rate",
						Math.round(rs.getDouble("recovery_rate")) + "");
				map.put("credit_score", rs.getInt("credit_score") + "");
				map.put("total_fos", rs.getInt("total_fos") + "");
				map.put("sales_ranking", rs.getInt("sales_ranking") + "");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> countLateDefaultersFoWise(int foId) {
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = null;
			ResultSet rs = null;
			call = con.prepareCall("{CALL count_late_defaulters_fo_wise(?)}");
			call.setInt(1, foId);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("lateCount", rs.getInt(1) + "");
				map.put("LateInThreeDays", rs.getInt(2) + "");
				map.put("varifies", rs.getInt(3) + "");
				map.put("installations", rs.getInt(4) + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	public static HashMap<String, String> getFoCommission(String userType,
			int foId) {
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = null;
			ResultSet rs = null;
			call = con.prepareCall("{CALL get_payment_status(?, ?)}");
			call.setString(1, userType);
			call.setInt(2, foId);
			rs = call.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("previousHoldAmount", "500");
				map.put("currentHoldAmount", "500");
				map.put("currentEarnedAmount", "500");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	public static ArrayList<HashMap<String, String>> getLateAndDefaulterCustomersByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_late_and_defaulter_customers_by_fo`(?)}");
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

	public static ArrayList<HashMap<String, String>> getLateInThreeDaysCustomersByFO(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_late_in_threedays_customers_by_fo`(?)}");
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

	public static ArrayList<HashMap<String, String>> getPreviousHoldCustomers(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_previous_hold_info_by_fo`(?)}");
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

	public static ArrayList<HashMap<String, String>> getFoCustomersInfo(int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_fo_customers_info_list`(?)}");
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

	public static ArrayList<HashMap<String, String>> getFoCommission(int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_fo_commission_history`(?)}");
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

	public static HashMap<String, String> getFoSurveyForms() {
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_survey_form`()}");
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("survey_name", rs.getString(2));
				map.put("survey_link", rs.getString(3));

			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return map;
	}

	public static HashMap<String, String> getFoInfoForms() {
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_fo_info_form`()}");
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map = new HashMap<>();
				map.put("info_name", rs.getString(1));
				map.put("info_link", rs.getString(2));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getNizamDostDetails(
			int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nizam_dost_info_for_fo(?)}");
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

	public static String updateAllLoanAccepted(int applianceId, int customerId,
			String applianceGsm, String IMInumber, int elg_status) {
		String customerPhoneNo = null, customerName = null, applianceName = null, status = null, payment = null;
		try (Connection con = Connect.getConnection()) {

			int info_status = 0;
			int appliance_status = 1;
			int customer_status = 1;

			int appliance_option = 1;
			boolean payement_option = true;

			int st_status = 0;
			double down_payment = 0.0;

			boolean down_payment_status = true;

			String query = "{CALL update_all_loan_request_accepted(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";

			CallableStatement cst = con.prepareCall(query);

			cst.setInt(1, applianceId);
			cst.setInt(2, customerId);
			cst.setInt(3, info_status);
			cst.setInt(4, elg_status);
			cst.setInt(5, appliance_status);
			cst.setInt(6, customer_status);
			cst.setInt(7, appliance_option);
			cst.setBoolean(8, payement_option);
			cst.setInt(9, st_status);
			cst.setDouble(10, down_payment);
			cst.setBoolean(11, down_payment_status);

			cst.registerOutParameter(12, java.sql.Types.VARCHAR);
			cst.registerOutParameter(13, java.sql.Types.VARCHAR);
			cst.registerOutParameter(14, java.sql.Types.VARCHAR);
			cst.registerOutParameter(15, java.sql.Types.VARCHAR);
			cst.registerOutParameter(16, java.sql.Types.VARCHAR);
			cst.executeUpdate();

			customerPhoneNo = cst.getString(12);
			customerName = cst.getString(13);
			applianceName = cst.getString(14);
			status = cst.getString(15);
			payment = cst.getString(16);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("", ex);
		}

		return customerPhoneNo + ":" + customerName + ":" + applianceName + ":"
				+ status + ":" + payment;
	}

	public static int insertCustomersLocation(int appId, double latitude,
			double longitude) {
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `insert_customers_location`(?,?,?)}");
			prepareCall.setInt(1, appId);
			prepareCall.setDouble(2, latitude);
			prepareCall.setDouble(3, longitude);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error(e);
		}
		return id;
	}

	public static boolean updateEligibilityRequest(int eligibilityId, int status) {
		boolean result = false;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `update_eligibility_status`(?, ?)}");
			prepareCall.setInt(1, eligibilityId);
			prepareCall.setInt(2, status);
			if (prepareCall.executeUpdate() == 1)
				result = true;

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return result;
	}

	public static int insertIntoRejection(int eligibilityId) {
		int customerId = 0;
		try (Connection con = Connect.getConnection()) {

			Statement statement = con.createStatement();
			ResultSet rs = null;
			rs = statement
					.executeQuery("Select customer_id from eligibility WHERE eligibility_id = "
							+ eligibilityId);

			while (rs.next()) {
				customerId = rs.getInt(1);
			}
			String text = "Fo rejected";
			statement
					.executeUpdate("insert into loan_rejection_purpose(customer_id,text) values("
							+ customerId + ",'" + text + "')");

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return customerId;
	}

	public static ArrayList<HashMap<Object, Object>> getAllAcceptedEligibilityByFO(
			int foId) {
		ArrayList<HashMap<Object, Object>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_accepted_eligibility_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (resultSet.next()) {
				HashMap<Object, Object> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				String customerId = (String) map.get("customer_id");
				map.put("assets",
						getAllAssetsByCustomer(Integer.parseInt(customerId)));
				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getRecoveriesByFO(int foId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_recoveries_by_fo`(?)}");
			prepareCall.setInt(1, foId);
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];
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

	public static ArrayList<HashMap<String, String>> getAllAssetsByCustomer(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL `get_all_assets_by_customer`(?)}");
			prepareCall.setInt(1, customerId);
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

}
