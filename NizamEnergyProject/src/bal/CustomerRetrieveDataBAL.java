package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import connection.Connect;

public class CustomerRetrieveDataBAL {
	final static Logger logger = Logger
			.getLogger(CustomerRetrieveDataBAL.class);

	public static ArrayList<HashMap<String, String>> getCustomerProfile(
			int customerId, String cnic) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {

			CallableStatement prepareCall = con
					.prepareCall("{call view_customer_profile(?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setString(2, cnic);
			ResultSet rs = prepareCall.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomerOtherPhoneDetails(
			int customerId) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_other_phone_number(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getOtherIncome(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_other_income_resource(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getOtherAssetsOfCustomer(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_other_assets(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getOtherMonthlyExpenses(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_other_monthly_expenses(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getLoanAndLiabilities(
			int customerId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_loan_and_liabilities(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getGuaranterDetailById(
			int customerId, int gtype) {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customer_guarantor(?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, gtype);
			ResultSet rs = prepareCall.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomerVerificationData(
			int eligibilityID) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_customers_verification_form(?)}");
			prepareCall.setInt(1, eligibilityID);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("elgibilityId", rs.getInt("eligibility_id") + "");
				map.put("salesmanQ1", rs.getInt("salesman_q1") + "");
				map.put("salesmanQ2", rs.getInt("salesman_q2") + "");
				map.put("salesmanQ3", rs.getInt("salesman_q3") + "");
				map.put("salesmanQ4", rs.getInt("salesman_q4") + "");
				map.put("salesmanQ5", rs.getInt("salesman_q5") + "");
				map.put("slVerifiedStatus", rs.getInt("sl_verified_status")
						+ "");
				map.put("familyGuarantorQ1",
						rs.getString("family_guarantor_q1"));
				map.put("familyGuarantorQ2",
						rs.getString("family_guarantor_q2") + "");
				map.put("familyGuarantorQ3",
						rs.getString("family_guarantor_q3") + "");
				map.put("familyGuarantorQ4", rs.getInt("family_guarantor_q4")
						+ "");
				map.put("familyGuarantorQ5", rs.getInt("family_guarantor_q5")
						+ "");
				map.put("fgVerifiedStatus", rs.getInt("fg_verified_status")
						+ "");
				map.put("outsideGuarantorQ1",
						rs.getString("outside_guarantor_q1"));
				map.put("outsideGuarantorQ2",
						rs.getString("outside_guarantor_q2") + "");
				map.put("outsideGuarantorQ3",
						rs.getString("outside_guarantor_q3") + "");
				map.put("outsideGuarantorQ4", rs.getInt("outside_guarantor_q4")
						+ "");
				map.put("outsideGuarantorQ5", rs.getInt("outside_guarantor_q5")
						+ "");
				map.put("ogVerifiedStatus", rs.getInt("og_verified_status")
						+ "");
				map.put("customerQ1", rs.getString("customer_q1"));
				map.put("customerQ2", rs.getString("customer_q2"));
				map.put("customerQ3", rs.getInt("customer_q3") + "");
				map.put("customerQ4", rs.getString("customer_q4"));
				map.put("customerQ5", rs.getInt("customer_q5") + "");
				map.put("customerQ6", rs.getInt("customer_q6") + "");
				map.put("customerVerifiedStatus",
						rs.getInt("customer_verified_status") + "");
				map.put("verifiedAllStatus", rs.getInt("verified_all_status")
						+ "");
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> verifyCustomerByCC(
			HashMap<String, String> map, int eligibilityId) {

		try (Connection con = Connect.getConnection()) {

			PreparedStatement preparedStatement = (PreparedStatement) con
					.prepareStatement("SELECT * FROM cc_verified_customers cc WHERE cc.eligibility_id = ?");
			preparedStatement.setInt(1, eligibilityId);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				// update
				int verifyNd = (map.get("ndVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("ndVerifiedStatus")));
				int verifyfg = (map.get("fgVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("fgVerifiedStatus")));
				int verifyog = (map.get("ogVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("ogVerifiedStatus")));
				int verifycs = (map.get("csVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("csVerifiedStatus")));
				int verifyAll = (map.get("verifyAll") == null ? 0 : Integer
						.parseInt(map.get("verifyAll")));
				if (verifyfg == 1) {
					preparedStatement = (PreparedStatement) con
							.prepareStatement("UPDATE `cc_verified_customers` SET `family_guarantor_q1` = ?, "
									+ " `family_guarantor_q2` = ?, "
									+ " `family_guarantor_q3` = ?, "
									+ " `family_guarantor_q4` = ?, "
									+ " `family_guarantor_q5` = ?, "
									+ " `fg_verified_status` = ? WHERE `eligibility_id` = ?;");

					preparedStatement.setString(1, map.get("fgQ1"));
					preparedStatement.setString(2, map.get("fgQ2"));
					preparedStatement.setString(3, map.get("fgQ3"));
					preparedStatement.setInt(4, (map.get("fgQ4") == null ? 0
							: Integer.parseInt(map.get("fgQ4"))));
					preparedStatement.setInt(5, (map.get("fgQ5") == null ? 0
							: Integer.parseInt(map.get("fgQ5"))));
					preparedStatement.setInt(
							6,
							(map.get("fgVerifiedStatus") == null ? 0 : Integer
									.parseInt(map.get("fgVerifiedStatus"))));
					preparedStatement.setInt(7, eligibilityId);
					preparedStatement.executeUpdate();
				}
				if (verifyNd == 1) {
					preparedStatement = (PreparedStatement) con
							.prepareStatement("UPDATE `cc_verified_customers` SET `salesman_q1` = ?, "
									+ " `salesman_q2` = ?, "
									+ " `salesman_q3` = ?, "
									+ " `salesman_q4` = ?, "
									+ " `salesman_q5` = ?, "
									+ " `sl_verified_status` = ? WHERE `eligibility_id` = ?;");

					preparedStatement.setInt(1, (map.get("ndQ1") == null ? 0
							: Integer.parseInt(map.get("ndQ1"))));
					preparedStatement.setInt(2, (map.get("ndQ2") == null ? 0
							: Integer.parseInt(map.get("ndQ2"))));
					preparedStatement.setInt(3, (map.get("ndQ3") == null ? 0
							: Integer.parseInt(map.get("ndQ3"))));
					preparedStatement.setInt(4, (map.get("ndQ4") == null ? 0
							: Integer.parseInt(map.get("ndQ4"))));
					preparedStatement.setInt(5, (map.get("ndQ5") == null ? 0
							: Integer.parseInt(map.get("ndQ5"))));
					preparedStatement.setInt(
							6,
							(map.get("ndVerifiedStatus") == null ? 0 : Integer
									.parseInt(map.get("ndVerifiedStatus"))));
					preparedStatement.setInt(7, eligibilityId);
					preparedStatement.executeUpdate();
				}
				if (verifyog == 1) {
					preparedStatement = (PreparedStatement) con
							.prepareStatement("UPDATE `cc_verified_customers` SET `outside_guarantor_q1` = ?, "
									+ " `outside_guarantor_q2` = ?, "
									+ " `outside_guarantor_q3` = ?, "
									+ " `outside_guarantor_q4` = ?, "
									+ " `outside_guarantor_q5` = ?, "
									+ " `og_verified_status` = ? WHERE `eligibility_id` = ?;");

					preparedStatement.setString(1, map.get("ogQ1"));
					preparedStatement.setString(2, map.get("ogQ2"));
					preparedStatement.setString(3, map.get("ogQ3"));
					preparedStatement.setInt(4, (map.get("ogQ4") == null ? 0
							: Integer.parseInt(map.get("ogQ4"))));
					preparedStatement.setInt(5, (map.get("ogQ5") == null ? 0
							: Integer.parseInt(map.get("ogQ5"))));
					preparedStatement.setInt(
							6,
							(map.get("ogVerifiedStatus") == null ? 0 : Integer
									.parseInt(map.get("ogVerifiedStatus"))));
					preparedStatement.setInt(7, eligibilityId);
					preparedStatement.executeUpdate();
				}
				if (verifycs == 1) {

					preparedStatement = (PreparedStatement) con
							.prepareStatement("UPDATE `cc_verified_customers` SET `customer_q1` = ?, "
									+ " `customer_q2` = ?, "
									+ " `customer_q3` = ?, "
									+ " `customer_q4` = ?, "
									+ " `customer_q5` = ?, "
									+ " `customer_q6` = ?, "
									+ " `customer_verified_status` = ? WHERE `eligibility_id` = ?;");

					preparedStatement.setString(1, map.get("csQ1"));
					preparedStatement.setString(2, map.get("csQ2"));
					preparedStatement.setInt(3, (map.get("csQ3") == null ? 0
							: Integer.parseInt(map.get("csQ3"))));
					preparedStatement.setString(4, map.get("csQ4"));
					preparedStatement.setInt(5, (map.get("csQ5") == null ? 0
							: Integer.parseInt(map.get("csQ5"))));
					preparedStatement.setInt(6, (map.get("csQ6") == null ? 0
							: Integer.parseInt(map.get("csQ6"))));
					preparedStatement.setInt(
							7,
							(map.get("csVerifiedStatus") == null ? 0 : Integer
									.parseInt(map.get("csVerifiedStatus"))));
					preparedStatement.setInt(8, eligibilityId);
					preparedStatement.executeUpdate();
				}
				if (verifyAll == 1) {

					preparedStatement = (PreparedStatement) con
							.prepareStatement("UPDATE `cc_verified_customers` SET `verified_all_status` = ? WHERE `eligibility_id` = ?;");

					preparedStatement.setInt(1, verifyAll);
					preparedStatement.setInt(2, eligibilityId);
					preparedStatement.executeUpdate();

					// updating eligibility status
					CallableStatement cs = con
							.prepareCall("{call verify_customer_by_cc(?)}");
					cs.setInt(1, eligibilityId);
					cs.executeUpdate();
				}

			} else {
				CallableStatement prepareCall = con
						.prepareCall("{call insert_cc_verification_customer_form(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				prepareCall.setInt(1, eligibilityId);

				prepareCall.setInt(
						2,
						(map.get("ndQ1") == null ? 0 : Integer.parseInt(map
								.get("ndQ1"))));
				prepareCall.setInt(
						3,
						(map.get("ndQ2") == null ? 0 : Integer.parseInt(map
								.get("ndQ2"))));
				prepareCall.setInt(
						4,
						(map.get("ndQ3") == null ? 0 : Integer.parseInt(map
								.get("ndQ3"))));
				prepareCall.setInt(
						5,
						(map.get("ndQ4") == null ? 0 : Integer.parseInt(map
								.get("ndQ4"))));
				prepareCall.setInt(
						6,
						(map.get("ndQ5") == null ? 0 : Integer.parseInt(map
								.get("ndQ5"))));
				prepareCall.setInt(7, (map.get("ndVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("ndVerifiedStatus"))));

				prepareCall.setString(8, map.get("fgQ1"));
				prepareCall.setString(9, map.get("fgQ2"));
				prepareCall.setString(10, map.get("fgQ3"));
				prepareCall.setInt(
						11,
						(map.get("fgQ4") == null ? 0 : Integer.parseInt(map
								.get("fgQ4"))));
				prepareCall.setInt(
						12,
						(map.get("fgQ5") == null ? 0 : Integer.parseInt(map
								.get("fgQ5"))));
				prepareCall.setInt(13, (map.get("verifyFg") == null ? 0
						: Integer.parseInt(map.get("verifyFg"))));
				prepareCall.setString(14, map.get("ogQ1"));
				prepareCall.setString(15, map.get("ogQ2"));
				prepareCall.setString(16, map.get("ogQ3"));
				prepareCall.setInt(
						17,
						(map.get("ogQ4") == null ? 0 : Integer.parseInt(map
								.get("ogQ4"))));
				prepareCall.setInt(
						18,
						(map.get("ogQ5") == null ? 0 : Integer.parseInt(map
								.get("ogQ5"))));
				prepareCall.setInt(19, (map.get("ogVerifiedStatus") == null ? 0
						: Integer.parseInt(map.get("ogVerifiedStatus"))));
				prepareCall.setString(20, map.get("csQ1"));
				prepareCall.setString(21, map.get("csQ1"));
				prepareCall.setInt(
						22,
						(map.get("csQ3") == null ? 0 : Integer.parseInt(map
								.get("csQ3"))));
				prepareCall.setString(23, map.get("csQ4"));
				prepareCall.setInt(
						24,
						(map.get("csQ5") == null ? 0 : Integer.parseInt(map
								.get("csQ5"))));
				prepareCall.setInt(
						25,
						(map.get("csQ6") == null ? 0 : Integer.parseInt(map
								.get("csQ6"))));
				prepareCall.setInt(26, (map.get("verifyCs") == null ? 0
						: Integer.parseInt(map.get("verifyCs"))));

				prepareCall.executeUpdate();
			}

		} catch (Exception ex) {
			logger.error("", ex);
		}

		return map;
	}

	public static ArrayList<HashMap<String, String>> getRequestStatus(int id) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL acceptedRejectedLoans(?)}");
			prepareCall.setInt(1, id);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("EligibilityId", rs.getInt("eligibility_id") + "");
				map.put("Status", rs.getString("status"));
				map.put("ApplianceName", rs.getString("appliance_name"));
				map.put("ApplianceId", rs.getString("appliance_id"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerId", rs.getString("customer_id"));
				map.put("appStatus", "appliance_status");
				map.put("salesman", rs.getString("salesman_name"));
				map.put("scheme", rs.getInt("installment_scheme") + "");

				maps.add(map);
			}
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int getLoanStatus(int eligId) {
		int status = 0;
		try (Connection con = Connect.getConnection();) {
			CallableStatement call = con
					.prepareCall("{CALL get_status_of_loanApp(?)}");
			call.setInt(1, eligId);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				status = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return status;
	}

	public static void main(String[] args) {
		System.out.print(getCustomerOtherPhoneDetails(1369));
	}

}
