package bal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import java.sql.Connection;

import connection.Connect;

public class FieldOfficerBAL {

	final static Logger logger = Logger.getLogger(FieldOfficerBAL.class);

	public static HashMap<String, String> getFieldOfficerDetail(int foID) {
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_field_officer_details(?)}");
			call.setInt(1, foID);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("fo_id", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("Cnic", rs.getString("fo_cnic"));
				map.put("priamary_phone", rs.getString("fo_priamary_phone"));
				map.put("secondary_phone", rs.getString("fo_secondary_phone"));
				map.put("address", rs.getString("fo_address"));
				map.put("DistrictOfficer", rs.getString("user_name"));
				map.put("District", rs.getString("district_name"));
				map.put("salary", rs.getString("basic_salary"));
				map.put("per_sale", rs.getInt("per_sale") + "");
				map.put("fo_acount_no", rs.getString("fo_acount_no") + "");
				map.put("city", rs.getString("fo_city"));
				map.put("date_of_birth", rs.getString("fo_date_of_birth"));
				map.put("date_of_joining", rs.getString("fo_date_of_joining"));
				map.put("user_id", rs.getString("user_id"));
				map.put("fo_email", rs.getString("fo_email") == null ? "N/A"
						: rs.getString("fo_email"));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getFOlist(int start,
			int length, String order, int column, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list1(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foid", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("last_sale", rs.getString("last_sale"));
				map.put("vles", rs.getString("vles"));
				map.put("activ_vles", rs.getString("activ_vles"));
				map.put("total_apps", rs.getString("total_apps"));
				map.put("installed_apps", rs.getString("installed_apps"));
				map.put("currentMonthApps", rs.getString("currentMonthApps"));
				map.put("currentweekApps", rs.getString("currentweekApps"));
				map.put("currentMonthInstalls",
						rs.getString("currentMonthInstalls"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				list.add(map);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getFOlistCount(String order, int column, int userId) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_count(?,?,?)}");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getFOlistSearch(int start,
			int length, String order, int column, int userId, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_search(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, userId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foid", rs.getInt("fo_id") + "");
				map.put("foName", rs.getString("fo_name"));
				map.put("last_sale", rs.getString("last_sale"));
				map.put("vles", rs.getString("vles"));
				map.put("activ_vles", rs.getString("activ_vles"));
				map.put("total_apps", rs.getString("total_apps"));
				map.put("installed_apps", rs.getString("installed_apps"));
				map.put("currentMonthApps", rs.getString("currentMonthApps"));
				map.put("currentweekApps", rs.getString("currentweekApps"));
				map.put("currentMonthInstalls",
						rs.getString("currentMonthInstalls"));
				map.put("currentWeekInstalls",
						rs.getString("currentWeekInstalls"));
				map.put("recovery_rate", rs.getString("recovery_rate"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getFOlistSearchCount(int userId, String search) {
		int count = 0;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_field_officers_list_search_count(?,?)}");
			call.setInt(1, userId);
			call.setString(2, search);
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
		}
		return count;
	}

	public static HashMap<String, String> getJoiningDateByFoId(int foId) {
		String query = "SELECT fo.fo_date_of_joining,fo.fo_before_time,fo.fo_on_time,fo.fo_after_time FROM field_officer fo WHERE fo.fo_id = ?";
		HashMap<String, String> map = new HashMap<String, String>();
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, foId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("joiningDate", rs.getString(1));
				map.put("bt", "" + rs.getInt(2));
				map.put("ot", "" + rs.getInt(3));
				map.put("at", "" + rs.getInt(4));
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return map;

	}

	public static String getNameById(int foId) {
		String query = "SELECT fo.fo_name FROM field_officer fo WHERE fo.fo_id =  ?";
		String name = "";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, foId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return name;
	}

	public static ArrayList<HashMap<String, String>> getAllFieldOfficers() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_field_officer_data()}");
			rs = call.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);
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

	public static ArrayList<HashMap<String, String>> getAllFieldOfficersById(
			int doid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{Call get_all_field_officers_by_doid(?)}");
			call.setInt(1, doid);
			rs = call.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			String columns[] = new String[metaData.getColumnCount()];

			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}

			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);

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

	public static ArrayList<HashMap<String, String>> getDefaultCustomerSpFo(
			int foid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement cs = connection
					.prepareCall("call get_fo_default_customers(?)");
			cs.setInt(1, foid);
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
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDefaultCustomerDoFo(
			int foid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement cs = connection
					.prepareCall("call get_fo_default_customers(?)");
			cs.setInt(1, foid);
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
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getProductPrice() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_product_price()}");

			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("product_id", "" + rs.getInt("product_id"));
				map.put("product_name", rs.getString("product_name"));
				map.put("product_price", rs.getString("product_price"));

				list.add(map);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> checkFoHaveNetwork(int foId) {

		HashMap<String, String> map = null;
		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL check_fo_have_network(?)}");
			prepareCall.setInt(1, foId);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("fo_id", "" + rs.getString("fo.fo_id"));
				map.put("foName", "" + rs.getString("fo.fo_name"));
				map.put("district", "" + rs.getString("district_name"));
				map.put("Have_Network", "" + rs.getString("Have_Network"));
				map.put("bulkFo", "" + rs.getString("bulkFo"));
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	} // end of checkFoHaveNetwork

	public static HashMap<String, String> checkSalesmanHaveNetwork(
			int salesmanId) {
		HashMap<String, String> map = null;
		String network = "";
		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;

			CallableStatement prepareCall = con
					.prepareCall("{CALL check_nd_have_network(?)}");
			prepareCall.setInt(1, salesmanId);
			rs = prepareCall.executeQuery();

			while (rs.next()) {
				map = new HashMap<>();
				map.put("ndId", "" + rs.getString("salesman_id"));
				map.put("ndName", "" + rs.getString("salesman_name"));
				network = rs.getString("Have_Network");
				map.put("Have_Network", "" + network);
				map.put("district", "" + rs.getString("district_name"));
				map.put("bulkNd", "" + rs.getString("bulkNd"));
			}

		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return map;
	} // end of checkSalesmanHaveNetwork

	public static ArrayList<HashMap<String, String>> getBulkUsers() {

		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		try (Connection con = connection.Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_bulk_users()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("fo_id", "" + rs.getInt("fo.fo_id"));
				map.put("fo_name", "" + rs.getString("fo.fo_name"));
				list.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;
	} // end of getBulkUsers

}
