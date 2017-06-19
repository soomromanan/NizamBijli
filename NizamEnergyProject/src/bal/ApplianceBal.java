package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.ApplianceBean;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import connection.Connect;

public class ApplianceBal {
	final static Logger logger = Logger.getLogger(ApplianceBal.class);

	public static int getApplianceSearchCount(String value) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_appliance_list_search_count(?)}");
			call.setString(1, value);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getApplianceByLimitAndRangeAndOrderBy(
			int start, int range, int column, String dir, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection connection = Connect.getConnection()) {
			call = connection
					.prepareCall("{Call get_appliance_list_search(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, range);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("appliancePrice", rs.getString("appliance_price"));
				map.put("customerName", rs.getString("customer_name"));
				map.put("applianceGSM", rs.getString("appliance_GSMno"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("salesmanDistrict", rs.getString("district_name"));
				map.put("customerPhone", rs.getString("customer_phone"));
				map.put("doName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("applianceStatusActive", rs.getInt("appliance_status")
						+ "");
				map.put("imeiNumber", rs.getString("imei_number"));
				map.put("applianceStatus", rs.getInt("a.status") + "");
				map.put("isDefaulted", rs.getInt("defaulted") + "");
				map.put("islive", rs.getInt("is_alive") + "");
				map.put("deadSince", rs.getInt("deadSince") + "");
				map.put("deadDate", rs.getString("dead_since") + "");
				map.put("healthStatus", rs.getInt("health_status") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return list;
	}

	public static HashMap<String, String> countAllStatusAndApplianceStatus() {
		HashMap<String, String> map = new HashMap<>();
		int dumb = 0;
		int dead = 0;
		int dead_since = 0;
		int alive = 0;
		int active = 0;
		int inactive = 0;
		int pending = 0;
		int readyToAssign = 0;
		int rdp = 0;
		int owned = 0;
		int returned = 0;
		int awaitingForDownpayment = 0;
		int installed = 0;
		int installedNoSignal = 0;
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = connection.prepareCall(
					"{CALL count_all_status_and_appliance_status()}")
					.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getInt("appliance_status")) {
				case 0:
					inactive += resultSet.getInt("count_appliance_status");
					break;
				case 1:
					active += resultSet.getInt("count_appliance_status");
					break;
				}

				switch (resultSet.getInt("health_status")) {
				case 0:
					dumb += resultSet.getInt("count_health_status");
					break;
				case 1:
					dead += resultSet.getInt("count_health_status");
					break;
				case 2:
					dead_since += resultSet.getInt("count_health_status");
					break;
				case 3:
					alive += resultSet.getInt("count_health_status");
					break;
				}
				switch (resultSet.getInt("status")) {
				case 0:
					pending += resultSet.getInt("count_status");
					break;
				case 1:
					readyToAssign += resultSet.getInt("count_status");
					break;
				case 2:
					rdp += resultSet.getInt("count_status");
					break;
				case 3:
					owned += resultSet.getInt("count_status");
					break;
				case 4:
					returned += resultSet.getInt("count_status");
					break;
				case 5:
					awaitingForDownpayment += resultSet.getInt("count_status");
					break;
				case 6:
					installed += resultSet.getInt("count_appliance_status");
					break;
				case 7:
					installedNoSignal += resultSet
							.getInt("count_appliance_status");
					break;
				}
				map.put("active", active + "");
				map.put("inactive", inactive + "");
				map.put("pending", pending + "");
				map.put("readyToAssign", readyToAssign + "");
				map.put("rdp", rdp + "");
				map.put("owned", owned + "");
				map.put("awaitingForDownpayment", awaitingForDownpayment + "");
				map.put("returned", returned + "");
				map.put("installed", installed + "");
				map.put("installedNoSignal", installedNoSignal + "");
				map.put("dumb", dumb + "");
				map.put("dead", dead + "");
				map.put("dead_since", dead_since + "");
				map.put("alive", alive + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getAllApplianceBySoldTo() {
		ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_appliance_by_sold_to()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceName", resultSet.getString("appliance_name"));
				mapList.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return mapList;
	}

	public static int countQuickedFilteredAppliances(int applianceStatus,
			String[] soldStatus, String[] healthStatus, String search) {
		int count = 0;
		StringBuilder condition = new StringBuilder();
		condition
				.append(" WHERE (e.status !=3 && e.status !=2 && a.status != 10) ");
		if (applianceStatus != -1) {
			condition.append("AND a.appliance_status = " + applianceStatus);
		}
		if ((soldStatus != null && soldStatus.length > 0)) {
			condition.append(" AND a.status IN ");
			for (int i = 0; i < soldStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(soldStatus[i]);

				if (i >= 0 && i < (soldStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (soldStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}

		if ((healthStatus != null && healthStatus.length > 0)) {
			condition.append(" AND a.health_status IN ");
			for (int i = 0; i < healthStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(healthStatus[i]);

				if (i >= 0 && i < (healthStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (healthStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if (!search.equals("")) {
			condition.append(" AND (a.appliance_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " a.imei_number LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " a.appliance_GSMno LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " cl.consumer_number LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " d.district_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " c.customer_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " c.customer_phone LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " s.salesman_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " fo.fo_name LIKE CONCAT(" + "'%','"
					+ search + "','%')) ||" + " us.user_name LIKE CONCAT("
					+ "'%','" + search + "','%') ");
		}

		String query = "SELECT COUNT(distinct a.appliance_id) FROM eligibility e "
				+ "INNER JOIN customer c ON c.customer_id = e.customer_id  "
				+ "INNER JOIN appliance a ON a.appliance_id = e.appliance_id "
				+ "LEFT JOIN `consumer_list` cl ON cl.appliance_id = a.appliance_id "
				+ "INNER JOIN salesman s ON s.salesman_id = e.salesman_id "
				+ "INNER JOIN district d ON d.district_id = s.salesman_district "
				+ "INNER JOIN field_officer fo ON fo.fo_id=s.fo_id "
				+ "INNER JOIN user us ON us.user_id = fo.do_id ";
		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(condition);
		try (Connection connection = Connect.getConnection()) {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryBuilder
					.toString());
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getQuickedFilteredAppliances(
			int applianceStatus, String[] soldStatus, String[] healthStatus,
			int columnIndex, String dir, int start, int range, String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		StringBuilder condition = new StringBuilder();
		condition
				.append(" WHERE (e.status !=3 && e.status !=2 && a.status != 10) ");
		if (applianceStatus != -1) {
			condition.append(" AND a.appliance_status = " + applianceStatus);
		}

		if ((soldStatus != null && soldStatus.length > 0)) {
			condition.append(" AND a.status IN ");
			for (int i = 0; i < soldStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(soldStatus[i]);

				if (i >= 0 && i < (soldStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (soldStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if ((healthStatus != null && healthStatus.length > 0)) {
			condition.append(" AND a.health_status IN ");
			for (int i = 0; i < healthStatus.length; i++) {
				if (i == 0) {
					condition.append(" ( ");
				}
				condition.append(healthStatus[i]);

				if (i >= 0 && i < (healthStatus.length - 1)) {
					condition.append(", ");
				}
				if (i == (healthStatus.length - 1)) {
					condition.append(" ) ");
				}
			}
		}
		if (!search.equals("")) {
			condition.append(" AND (a.appliance_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " a.imei_number LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " a.appliance_GSMno LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " cl.consumer_number LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " d.district_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " c.customer_name LIKE CONCAT(" + "'%','"
					+ search + "','%') || " + " c.customer_phone LIKE CONCAT("
					+ "'%','" + search + "','%') || "
					+ " s.salesman_name LIKE CONCAT(" + "'%','" + search
					+ "','%') || " + " fo.fo_name LIKE CONCAT(" + "'%','"
					+ search + "','%')) ||" + " us.user_name LIKE CONCAT("
					+ "'%','" + search + "','%') GROUP BY a.appliance_id ");
		}
		String column = "a.appliance_name";
		switch (columnIndex) {
		case 1:
			column = "a.appliance_name";
			break;
		case 2:
			column = "a.appliance_gsmno";
			break;
		case 4:
			column = "d.district_name";
			break;
		case 5:
			column = "c.customer_name";
			break;
		case 6:
			column = "s.salesman_name";
			break;
		case 7:
			column = "fo.fo_name";
			break;
		default:
			column = "c.customer_name";
			break;
		}
		condition.append(" ORDER BY " + column + " " + dir);
		condition.append(" LIMIT " + start + ", " + range);
		String query = "SELECT "
				+ "a.appliance_id, "
				+ "a.appliance_GSMno, "
				+ "a.appliance_name, "
				+ "a.appliance_status, "
				+ "c.customer_name, "
				+ "d.district_name, "
				+ "s.salesman_name, "
				+ "a.status, "
				+ "s.salesman_id, "
				+ "c.customer_phone, a.imei_number, "
				+ "fo.fo_name ,a.is_alive,"
				+ "DATEDIFF(CURDATE(),CAST(a.dead_since AS DATE))AS deadSince ,a.dead_since ,a.health_status,c.customer_id "
				+ "FROM eligibility e "
				+ "INNER JOIN customer c ON c.customer_id = e.customer_id  "
				+ "INNER JOIN appliance a ON a.appliance_id = e.appliance_id "
				+ "LEFT JOIN `consumer_list` cl ON cl.appliance_id = a.appliance_id "
				+ "INNER JOIN salesman s ON s.salesman_id = e.salesman_id "
				+ "INNER JOIN district d ON d.district_id = s.salesman_district "
				+ "INNER JOIN field_officer fo ON fo.fo_id=s.fo_id "
				+ "INNER JOIN user us ON us.user_id = fo.do_id ";
		StringBuilder queryBuilder = new StringBuilder(query);
		queryBuilder.append(condition);
		try (Connection connection = Connect.getConnection()) {
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryBuilder
					.toString());
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("applianceGSM", resultSet.getString("appliance_GSMno"));
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("salesmanDistrict",
						resultSet.getString("district_name"));
				map.put("customerPhone", resultSet.getString("customer_phone"));
				map.put("salesmanName", resultSet.getString("salesman_name"));
				map.put("applianceStatusActive",
						resultSet.getInt("appliance_status") + "");
				map.put("applianceStatus", resultSet.getInt("a.status") + "");
				map.put("imeiNumber", resultSet.getString("imei_number"));
				map.put("foName", resultSet.getString("fo.fo_name"));
				map.put("islive", resultSet.getInt("is_alive") + "");
				map.put("deadSince", resultSet.getInt("deadSince") + "");
				map.put("deadDate", resultSet.getString("dead_since") + "");
				map.put("healthStatus", resultSet.getInt("health_status") + "");
				map.put("customerid", resultSet.getInt("customer_id") + "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int getDoDeploymentListSearchCount(int districtId,
			String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("CALL get_do_deployment_list_count(?,?)");
			call.setInt(1, districtId);
			call.setString(2, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentList(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number = rs.getString("imei_number");
					map.put("applianceNumber", number);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));
				map.put("customerNumber", rs.getString("customer_phone"));
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static HashMap<String, Integer> countDoPipelineFilters(int doId) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pipeline`(?)}");
			prepareCall.setInt(1, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("readyToAssign", rs.getInt(2));
				map.put("awaitingDownpayment", rs.getInt(3));
				map.put("installed", rs.getInt(4));
				map.put("returned", rs.getInt(5));
				map.put("rdp", rs.getInt(6));
				map.put("installedNoSignal", rs.getInt(7));
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, Integer> countDoPipelineFiltersWithSearch(
			int doId, String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pipeline_search`(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("readyToAssign", rs.getInt(2));
				map.put("awaitingDownpayment", rs.getInt(3));
				map.put("installed", rs.getInt(4));
				map.put("returned", rs.getInt(5));
				map.put("rdp", rs.getInt(6));
				map.put("installedNoSignal", rs.getInt(7));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListPending(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_Pending(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				map.put("customerNumber", rs.getString("customer_phone"));
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListRtoAssign(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		ResultSet rs = null;
		CallableStatement call = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_do_deployment_list_r_to_a(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListAdp(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_adp(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListInstalled(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_installed(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListReturned(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_returned(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListRdp(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_rdp(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoDeploymentListInstalledNoSignal(
			int start, int length, String dir, int column, int districtId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_do_deployment_list_installed_ns(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, dir);
			call.setInt(4, column);
			call.setInt(5, districtId);
			call.setString(6, search);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id") + "");
				if (rs.getString("imei_number") == null) {
					map.put("applianceNumber", "Not Assign");
				} else if (rs.getString("imei_number").equals("null")) {
					map.put("applianceNumber", "Not Assign");
				} else {
					String number2 = rs.getString("imei_number");
					map.put("applianceNumber", number2);
				}
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("applianceActiveStatus",
						rs.getInt("appliance_active_inactive_status") + "");
				map.put("applianceGivenStatus",
						rs.getInt("appliance_given_status") + "");
				map.put("customerName", rs.getString("customer_name"));

				String customerNumber2 = CustomerBal.getFormattedPhoneNumber(rs
						.getString("customer_phone"));

				map.put("customerNumber", customerNumber2);
				map.put("eStatusAppliedRejected",
						rs.getInt("e_status_applied_rejected") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("customerid", rs.getInt("customer_id") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> getApplianceData(int applianceId) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_appliance_info(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				map.put("gsmNumber", rs.getString("appliance_GSMno"));
				map.put("applianceName", rs.getString("appliance_name"));
				map.put("price", rs.getDouble("appliance_price") + "");
				map.put("state", rs.getBoolean("appliance_status") + "");
				map.put("salesman", rs.getString("salesman_name"));
				map.put("customer", rs.getString("customer_name"));
				map.put("handover", rs.getDate("product_handover") + "");
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanPhone", rs.getString("salesman_primary_phone")
						+ "");
				map.put("customerPhone", rs.getString("customer_phone") + "");
				map.put("soldtoId", rs.getInt("sold_to_id") + "");
				map.put("userName", rs.getString("user_name"));
				map.put("foName", rs.getString("fo_name"));
				map.put("doid", rs.getInt("user_id") + "");
				map.put("IMEInumber", rs.getString("a.imei_number") + "");
				map.put("customercnic", rs.getString("c.customer_cnic") + "");
				map.put("applianceIdd", rs.getInt("a.appliance_id") + "");
				map.put("status", rs.getInt("status") + "");
				map.put("consumer", rs.getString("consumer_number"));
				map.put("returned", rs.getString("returned_date"));
				map.put("foid", rs.getInt("fo_id") + "");
				map.put("healthstatus", rs.getInt("health_status") + "");
				map.put("deadsince", rs.getInt("deadsince") + "");
				map.put("isSignal", rs.getInt("is_signal_catching") + "");
				map.put("remaining", rs.getDouble("remaining_balance") + "");
				map.put("customerId", rs.getInt("customer_id") + "");

			}
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return map;
	}

	public static void installDevice(int applianceId) throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL update_device_into_installed(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void installNoSignalDevice(int applianceId)
			throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL varify_signals(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static boolean updateReturned(int applianceId) {
		boolean status = false;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL update_returned_device(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet resultSet = prepareCall.executeQuery();
			status = resultSet.next();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return status;
	}

	public static String replaceAppliance(String appGSM, String appIMEI,
			int appId, int customerId) {
		String ap = "0";
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `change_device`(?,?,?,?,?)}");
			prepareCall.setInt(1, appId);
			prepareCall.setInt(2, customerId);
			prepareCall.setString(3, appGSM);
			prepareCall.setString(4, appIMEI);
			prepareCall.registerOutParameter(5, java.sql.Types.VARCHAR);
			prepareCall.executeQuery();
			ap = prepareCall.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ap;
	}

	public static void updateImeiGsmConsumer(int applianceId, String gsm,
			String imei, String consumer) throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL update_imei_gsm_consumer(?,?,?,?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.setString(2, imei);
			prepareCall.setString(3, gsm);
			prepareCall.setString(4, consumer);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static String InsertImeiGsmConsumer(int applianceId, String imei,
			String gsm, int csId, String consumerNum) throws SQLException {
		String phoneNumber = "";
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_imei_and_consumer(?,?,?,?,?)}");
			call.setInt(1, applianceId);
			call.setString(2, imei);
			call.setString(3, gsm);
			call.setInt(4, csId);
			call.setString(5, consumerNum);
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				phoneNumber = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return phoneNumber;
	}

	public static boolean checkGsm(String gsm) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_gsm(?)}");
				prepareCall.setString(1, gsm);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkImei(String imei) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_imei(?)}");
				prepareCall.setString(1, imei);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static boolean checkConsumer(String consumer) {
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL check_consumer(?)}");
				prepareCall.setString(1, consumer);
				ResultSet resultSet = prepareCall.executeQuery();
				result = resultSet.next();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return result;
	}

	public static String setConsumerAdvance(int applianceId, String csId,
			String consumerNum) throws SQLException {
		String phone = "";
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL set_consumer_advance(?,?,?)}");
			call.setInt(1, applianceId);
			call.setString(2, csId);
			call.setString(3, consumerNum);
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				phone = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return phone;
	}

	public static boolean updateConsumerAdvance(int applianceId,
			String consumerNum) throws SQLException {
		boolean row = false;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL update_consumer_advance(?,?)}");
			call.setInt(1, applianceId);
			call.setString(2, consumerNum);
			call.executeQuery();
			row = true;
		} catch (SQLException e) {
			logger.error("", e);
		}
		return row;
	}

	public static boolean assignBooking(int applianceId, String imei, String gsm)
			throws SQLException {
		boolean row = false;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL assign_booking(?,?,?)}");
			call.setInt(1, applianceId);
			call.setString(2, imei);
			call.setString(3, gsm);
			call.executeQuery();
			row = true;
		} catch (SQLException e) {
			logger.error("", e);
		}
		return row;
	}

	public static int insertLoanDueDate(int applianceId) {
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_duedate_entry(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeQuery();
			connection.close();
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return 0;
	}

	public static ApplianceBean getApplianceInfo(int applianceId) {
		ApplianceBean bean = null;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_appliance_info(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				bean = new ApplianceBean();
				bean.setApplianceId(rs.getInt("a.appliance_id"));
				bean.setApplianceName(rs.getString("appliance_name"));
				bean.setPrice(rs.getDouble("appliance_price"));
				bean.setState(rs.getBoolean("appliance_status"));
				bean.setApplianceGsmNo(rs.getString("appliance_GSMno"));
				bean.setSalesmanName(rs.getString("salesman_name"));
				bean.setHandoverDate(rs.getDate("product_handover"));
				bean.setCustomerName(rs.getString("customer_name"));
				bean.setImiNumber(rs.getString("a.imei_number"));
				bean.setSalesmanId(rs.getInt("salesman_id"));
				bean.setSalesmanPhone(rs.getString("salesman_primary_phone"));
				bean.setCustomerPhone(rs.getString("customer_phone"));
				bean.setSoldtoId(rs.getInt("sold_to_id"));
				bean.setUserName(rs.getString("user_name"));
				bean.setFoName(rs.getString("fo_name"));
				bean.setDoid(rs.getInt("user_id"));
				bean.setCustomerId(rs.getInt("customer_id"));
				bean.setFoid(rs.getInt("fo.fo_id"));
				bean.setCustomerCnic(rs.getString("c.customer_cnic"));
				bean.setConsumerNumber(rs.getString("consumer_number"));
				bean.setIsOwned(rs.getDouble("remaining_balance"));
				bean.setInstalledDate(rs.getString("product_handover"));
				bean.setSignal(rs.getInt("is_signal_catching"));
				bean.setStatus(rs.getInt("status"));
				bean.setHealthStatus(rs.getInt("health_status"));
				bean.setIsALive(rs.getInt("is_alive"));
				bean.setDeadSince(rs.getInt("deadsince"));
				bean.setReturnedDate(rs.getString("returned_date"));
			}
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return bean;
	}

	public static void setNotIntrested(int applianceId) throws SQLException {
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL set_advance_request_to_not_intrested(?)}");
			prepareCall.setInt(1, applianceId);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
		}
	}

	public static boolean revokeConsumerNum(int appliance_id, int csId) {
		CallableStatement call = null;
		boolean result = false;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{CALL revoke_Consumer(?)}");
			call.setInt(1, appliance_id);
			ResultSet rs = call.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return result;
	}

	public static HashMap<String, String> getDistrictSummary(int appId) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{Call get_district_summary(?)}");
			call.setInt(1, appId);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map.put("consumed", rs.getInt("avg_consumed") + " ");
				map.put("produced", rs.getInt("avg_produced") + " ");
				map.put("name", rs.getString("district_name") + " ");
				map.put("StdProduced", 187 + " ");
				map.put("StdLoad", 156 + " ");
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static String checkNumberInAppliance(String number) {
		String imeiStatus = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection();) {
			ps = (PreparedStatement) con
					.prepareStatement("SELECT ap.appliance_name FROM appliance ap WHERE ap.appliance_GSMno = ?");
			ps.setString(1, number);
			rs = ps.executeQuery();
			while (rs.next()) {
				imeiStatus = rs.getString("appliance_name");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return imeiStatus;
	}

	public static String checkImeiInAppliance(String imei) {
		String imeiStatus = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try (Connection con = Connect.getConnection();) {
			ps = (PreparedStatement) con
					.prepareStatement("SELECT ap.appliance_name FROM appliance ap WHERE ap.imei_number = ?");
			ps.setString(1, imei);
			rs = ps.executeQuery();
			while (rs.next()) {
				imeiStatus = rs.getString("appliance_name");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return imeiStatus;
	}

	public static int get_appliance_id(String GSM) {
		CallableStatement call = null;
		ResultSet rs;
		int appliance_id = 0;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_appliance_id_byGSM(?)}");
			call.setString(1, GSM);
			rs = call.executeQuery();
			while (rs.next()) {
				appliance_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return appliance_id;
	}

	public static void update_appliance_on_off(boolean on_off, int appliance_id) {
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL update_appliance_on_off(?,?)}");
			call.setInt(1, appliance_id);
			call.setBoolean(2, on_off);
			call.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public static boolean updateApplianceAlerts(int applianceId) {
		boolean row = false;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL update_appliance_alerts_with_appliance_id(?)}");
			call.setInt(1, applianceId);
			call.executeQuery();
			row = true;
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return row;
	}

	public static void main(String arg[]) {
		System.err.println(getDistrictSummary(1728));
	}

}
