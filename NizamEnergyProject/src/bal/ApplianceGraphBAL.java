package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class ApplianceGraphBAL {

	private final static Logger logger = Logger
			.getLogger(ApplianceGraphBAL.class);

	public static ArrayList<HashMap<String, String>> getWattageStaticsDateWise(
			int appId, String date) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL appliance_bv_graph_datewise(?,?)}");
			call.setInt(1, appId);
			call.setString(2, date);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("batteryWatt", rs.getDouble("battery_watt") + "");
				map.put("lodWatt", rs.getDouble("solar_watt") + "");
				map.put("solarWatt", rs.getDouble("power_watt") + "");
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getloadSolarAmpere(
			int appId, String date) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();

		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{Call get_loadSolar_ampare(?,?)}");
			callst.setInt(1, appId);
			callst.setString(2, date);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("ApplianceId", rs.getInt("appliance_id"));
				map.put("solar", rs.getDouble("solar_ampare"));
				map.put("load", rs.getDouble("load_ampere"));
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime2", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getApplianceBatteryPerformance(
			int appId, String date) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL appliance_battery_performance_graph_datewise(?,?)}");
			callst.setInt(1, appId);
			callst.setString(2, date);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("ApplianceId", rs.getInt("appliance_id"));
				map.put("batteryVoltage", rs.getDouble("battery_voltage"));
				map.put("batteryAmpere", rs.getDouble("battery_ampere"));
				map.put("solarVoltage", rs.getDouble("solar_voltage"));
				map.put("solarAmpere", rs.getDouble("solar_ampare"));
				map.put("applianceTemperature",
						rs.getDouble("appliance_temperature"));
				String string = rs.getString("status_date");
				String s = string.substring(0, string.length() - 2);
				map.put("datetime2", s);
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

	}

}
