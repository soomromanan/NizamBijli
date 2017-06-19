package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ocpsoft.pretty.time.PrettyTime;

import connection.Connect;

public class ApplianceAlarmsBal {
	final static Logger logger = Logger.getLogger(ApplianceAlarmsBal.class);

	public static int insertAlarms(HashMap<String, String> map) {
		int id = -1;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_appliance_alarms(?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setString(1, map.get("imeiNumber"));
			prepareCall.setString(2, map.get("betteryLevel"));
			prepareCall.setString(3, map.get("solarPower"));
			prepareCall.setString(4, map.get("currentLoad"));
			prepareCall.setString(5, map.get("temperature"));
			prepareCall.setString(6, map.get("lidOpen"));
			prepareCall.setString(7, map.get("signalStrength"));
			prepareCall.setDouble(8, Double.parseDouble(map.get("latitude")));
			prepareCall.setDouble(9, Double.parseDouble(map.get("longitude")));
			prepareCall.setString(10, map.get("commonFault"));
			prepareCall.setString(11, map.get("gsm_suicide"));
			prepareCall.setString(12, map.get("battery_overcharge"));
			ResultSet resultSet = null;
			resultSet = prepareCall.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;
	}

	public static ArrayList<HashMap<String, String>> getDoApplianceAlarmsList(
			int start, int length, String order, int col, int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL do_appliance_alarms(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setInt(5, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getDoApplianceAlarmsListCount(String order, int col,
			int userId) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL do_appliance_alarms_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
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

	public static ArrayList<HashMap<String, String>> getDoApplianceAlarmsListSearch(
			int start, int length, String order, int col, int userId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL do_appliance_alarms_search(?,?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setInt(5, userId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getDoApplianceAlarmsListSearchCount(String order,
			int col, int userId, String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL do_appliance_alarms_search_count(?,?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
			call.setInt(3, userId);
			call.setString(4, search);
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

	public static int getSuperadminAlarmsListCount() {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("call superadmin_appliance_alarms_count()");
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

	public static ArrayList<HashMap<String, String>> getSuperadminAlarmsList(
			int start, int length, String order, int column) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("call superadmin_appliance_alarms(?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminAlarmsListSearchCount(String order, int col,
			String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_appliance_alarms_search_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, col);
			call.setString(3, search);
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

	public static ArrayList<HashMap<String, String>> getSuperadminAlarmListSearch(
			int start, int length, String order, int col, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;

		ResultSet rs = null;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_appliance_alarms_search(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("foName", rs.getString("fo_name") + "");
				map.put("userName", rs.getString("user_name") + "");
				map.put("salesman", rs.getString("salesman_name") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("imeiNumber", rs.getString("imei_number") + "");
				map.put("district", rs.getString("district_name") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				map.put("customerName", rs.getString("customer_name") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int countUnseenMessagesFromAppliance() {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_unseen_status_messages_from_appliance()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
				connection.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;

	}

	public static ArrayList<HashMap<String, String>> getUnseenMessagesFromAppliance() {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_unseen_status_messages_from_appliance()}");
			ResultSet result = prepareCall.executeQuery();
			while (result.next()) {
				map = new HashMap<>();
				map.put("messageId", String.valueOf(result.getInt("alarm_id")));
				map.put("messageFrom", result.getString("appliance_GSMno"));
				Timestamp dateTime = result.getTimestamp("alarm_time");
				Date date = new Date(dateTime.getTime());
				map.put("messageDate", p.format((date)));
				map.put("applianceId", result.getString("appliance_id"));
				list.add(map);
			}
			result.close();
			prepareCall.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;

	}// end of getting all message number from appliance_message

	public static int getSuperadminApplianceviewAlertsListSearchCount(
			String order, int column, int appId, String search) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_search_count(?,?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, appId);
			call.setString(4, search);
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

	public static ArrayList<HashMap<String, String>> getSuperadminApplianceviewAlertsListSearch(
			int start, int length, String order, int column, int appId,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_search(?,?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, appId);
			call.setString(6, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("gsm_suicide", rs.getBoolean("gsm_suicide") + "");
				map.put("common_fault", rs.getBoolean("common_fault") + "");
				map.put("battery_overcharge",
						rs.getBoolean("battery_overcharge") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, String>> getSuperadminApplianceViewAlertList(
			int start, int length, String order, int column, int appId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table(?,?,?,?,?)");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, column);
			call.setInt(5, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("alarmId", rs.getDouble("alarm_id") + "");
				map.put("battery", rs.getBoolean("battery_level") + "");
				map.put("temprature", rs.getBoolean("temperature") + "");
				map.put("solar_power", rs.getBoolean("solar_power") + "");
				map.put("current_load", rs.getBoolean("current_load") + "");
				map.put("lid_connectivity", rs.getBoolean("lid_connectivity")
						+ "");
				map.put("gsmSignal", rs.getInt("gsm_signal_strength") + "");
				map.put("gsm_suicide", rs.getBoolean("gsm_suicide") + "");
				map.put("common_fault", rs.getBoolean("common_fault") + "");
				map.put("battery_overcharge",
						rs.getBoolean("battery_overcharge") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("applianceId", rs.getInt("appliance_id") + "");
				map.put("alarmTime", rs.getString("alarm_time") + "");
				map.put("ticketStatus", rs.getInt("status") + "");
				map.put("gsmNumber", rs.getString("appliance_GSMno") + "");
				map.put("resolvedTime", rs.getString("closed_time") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSuperadminApplianceViewAlertListCount(String order,
			int column, int appId) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("CALL superadmin_applianceview_alerts_table_count(?,?,?)");
			call.setString(1, order);
			call.setInt(2, column);
			call.setInt(3, appId);
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

}
