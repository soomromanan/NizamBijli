package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.CustomerInfoBean;
import connection.Connect;

public class CustomerBal {

	final static Logger logger = Logger.getLogger(CustomerBal.class);

	public static int countCustomersByRegexSearch(String regex) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_all_customers_by_limit_range_regex(?)}");
			prepareCall.setString(1, regex);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getCustomersByLimitAndRangeAndOrderBy(
			int start, int length, int column, String dir, String search) {
		ArrayList<HashMap<String, String>> listMap = new ArrayList<>();
		CallableStatement prepareCall = null;
		ResultSet resultSet;
		try (Connection connection = Connect.getConnection();) {
			prepareCall = connection
					.prepareCall("{call get_customers_list(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, length);
			prepareCall.setString(3, dir);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				map.put("customerCnic", resultSet.getString("customer_cnic"));
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ " %");
				}
				listMap.add(map);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return listMap;
	}

	public static ArrayList<HashMap<String, String>> getRejectedCustomers(
			int start, int range, int col, String dir, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_rejected_appliance_loan_requests(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setInt(3, col);
			prepareCall.setString(4, dir);
			prepareCall.setString(5, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("eligibilityId", resultSet.getInt("eligibility_id")
						+ "");
				map.put("DT_RowId",
						"row_customer_id_" + resultSet.getInt("eligibility_id"));
				map.put("eligibilityStatus", resultSet.getInt("e.status") + "");
				map.put("customerId", resultSet.getInt("customer_id") + "");
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("districtName", resultSet.getString("district_name"));
				map.put("customerPhoneNumber",
						resultSet.getString("customer_phone"));
				map.put("applianceName", resultSet.getString("appliances"));
				map.put("applianceStatus", resultSet.getInt("appliance_status")
						+ "");
				list.add(map);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	}

	public static int countRejectedCustomers(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_rejected_customers(?)}");
			prepareCall.setString(1, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return count;
	}

	public static ArrayList<CustomerInfoBean> getRejectedAppliances(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();
		CustomerInfoBean bean = null;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_rejected_expansion(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setPurpose(resultSet.getString("text"));
				bean.setFoName(resultSet.getString("fo_name"));
				bean.setDoName(resultSet.getString("user_name"));
				maps.add(bean);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<CustomerInfoBean> getCustomersAppliances(
			int customerId) {
		ArrayList<CustomerInfoBean> maps = new ArrayList<>();
		CustomerInfoBean bean = null;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_expantion(?)}");
			prepareCall.setInt(1, customerId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new CustomerInfoBean();
				bean.setCustomerId(resultSet.getInt("customer_id"));
				bean.setApplianceId(resultSet.getInt("appliance_id"));
				bean.setApplianceName(resultSet.getString("appliance_name"));
				bean.setApplianceStatus(resultSet.getInt("appliance_status"));
				bean.setSalesmanName(resultSet.getString("salesman_name"));
				bean.setTransferStatus(resultSet.getInt("a.status"));
				bean.setEligibilityStatus(resultSet.getInt("e.status"));
				bean.setLoanStatus(resultSet.getString("loan_status"));
				bean.setIsDefaulted(resultSet.getInt("pickup"));
				bean.setFoName(resultSet.getString("fo_name"));
				bean.setDoName(resultSet.getString("user_name"));
				maps.add(bean);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getNotInterstedCustomers() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_notInterested_customers()}");
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("last_status", "" + rs.getInt("e.last_status"));
				list.add(map);
			}
		} catch (Exception ex) {
			logger.error(ex);
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoNotInterstedCustomers(
			int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_do_notInterested_customers(?)}");
			prepareCall.setInt(1, userId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("customer_name", "" + rs.getString("c.customer_name"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				map.put("appliance_name", "" + rs.getString("a.appliance_name"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("last_status", "" + rs.getInt("e.last_status"));
				list.add(map);
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return list;
	}

	public static void revokeCustomer(int applianceId) {
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call revoke_customer(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeQuery();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static String getFormattedPhoneNumber(String num) {
		String number = "";
		try {
			if (num == null || num == "null" || num == "92" || num.equals("")
					|| num.equals("92")) {
				number = "N/A";
			} else {
				String concat = "+" + num;
				number = String.format("(%s) %s-%s", concat.substring(0, 3),
						concat.substring(3, 6), concat.substring(6, 13));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return number;
	}

	public static boolean getCustomerNumber(String column, String table,
			String number) {
		int mobile = 0;
		String query = "SELECT count(*) FROM " + table + " WHERE " + column
				+ "= '" + number + "'";
		try (Connection con = Connect.getConnection();) {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				mobile = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		if (mobile > 0) {
			return true;
		} else {
			return false;
		}
	}
}
