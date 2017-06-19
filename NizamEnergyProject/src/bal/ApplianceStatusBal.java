package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.ApplianceStatusBean;
import connection.Connect;

public class ApplianceStatusBal {

	final static Logger logger = Logger.getLogger(ApplianceStatusBal.class);

	public static int insertStatus(ApplianceStatusBean bean) {
		int id = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call insert_status_of_appliance(?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setDouble(1, bean.getBv());
			prepareCall.setDouble(2, bean.getBa());
			prepareCall.setDouble(3, bean.getSv());
			prepareCall.setDouble(4, bean.getSa());
			prepareCall.setDouble(5, bean.getLv());
			prepareCall.setDouble(6, bean.getLa());
			prepareCall.setInt(7, bean.getSignalStrength());
			prepareCall.setDouble(8, bean.getTemperature());
			prepareCall.setDouble(9, bean.getLatitude());
			prepareCall.setDouble(10, bean.getLongitude());
			prepareCall.setString(11, bean.getIMEI());
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

	public static ArrayList<HashMap<String, Object>> getTopProduction() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_top_power_production()}");
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("customerName", rs.getString("customer_name"));
				map.put("district_name", rs.getString("district_name"));
				map.put("name", rs.getString("appliance_name"));
				map.put("power", rs.getInt("power_produced"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getLeastProduction() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_least_power_production()}");
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("customerName", rs.getString("customer_name"));
				map.put("district_name", rs.getString("district_name"));
				map.put("name", rs.getString("appliance_name"));
				map.put("power", rs.getInt("power_produced"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getTopConsumption() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_top_load_consumed()}");
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("customerName", rs.getString("customer_name"));
				map.put("district_name", rs.getString("district_name"));
				map.put("name", rs.getString("appliance_name"));
				map.put("load", rs.getInt("load_consumed"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getLeastConsumption() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_least_load_consumed()}");
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("customerName", rs.getString("customer_name"));
				map.put("district_name", rs.getString("district_name"));
				map.put("name", rs.getString("appliance_name"));
				map.put("load", rs.getInt("load_consumed"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, Object>> getTotalEnergy() {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_total_energy()}");
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("district_name", rs.getString("district_name"));
				map.put("devices", rs.getInt("devices"));
				map.put("totalProduced", rs.getInt("totalProduced"));
				map.put("total_consumed", rs.getInt("total_consumed"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static double[] ApplianceSummary(int appid) {

		ArrayList<String> timelist = getApplianceSummaryTime(appid);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getApplianceSummaryWatt(appid);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);
		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		double total_time = 0.0;
		int i, start = 0;
		int times = 0;
		double total_watt_hours, counter = 0.0;
		double[] returnvalues;
		returnvalues = new double[] { 0.0, 0.0, 0.0 };
		counter = 0;

		total_watt_hours = 0.0;
		start = 0;
		double sum = 0.0;
		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 15
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_hours > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = total_time + hours;
						this_time_WH = sum * hours;
						total_watt_hours = total_watt_hours + this_time_WH;
						System.out.println("no.of time: " + times
								+ " and watt is = " + this_time_WH + " in "
								+ hours + " and watt hour = "
								+ total_watt_hours);
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = hours;
						total_watt_hours = sum * hours;
						System.out.println("no.of time: " + times
								+ " and watt is = " + total_watt_hours + "in "
								+ hours + " and watt hour = "
								+ total_watt_hours);
					}
					sum = 0;
					counter = 0;
				}

			}

		}
		returnvalues[0] = total_watt_hours;
		returnvalues[1] = total_time;
		returnvalues[2] = (total_time * 3600 % 3600) % 60;
		return returnvalues;

	}

	public static double ApplianceLoadSummary(int appid) {

		ArrayList<String> timelist = getLoadSummaryTime(appid);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getloadSummaryWatt(appid);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);

		// int[] watts = new int[watt.length];getloadSummaryWatt
		// int[] times = new int[time.length];

		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		int i, start = 0;
		int times = 0;
		double total_watt_consume, counter = 0.0;
		counter = 0;
		total_watt_consume = 0.0;
		start = 0;
		double sum = 0.0;

		//

		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 1
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_consume > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						this_time_WH = sum * hours;
						total_watt_consume = total_watt_consume + this_time_WH;
						System.out.println("no.of time: " + times
								+ " and watt is = " + this_time_WH + " in "
								+ hours + " and watt hour = "
								+ total_watt_consume);
						System.out.println("Currnet index is : " + i);
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_watt_consume = sum * hours;
						System.out.println("no.of time: " + times
								+ " and watt is = " + total_watt_consume
								+ "in " + hours + " and watt hour = "
								+ total_watt_consume);
						System.out.println("Currnet index is : " + i);

					}
					sum = 0;
					counter = 0;
				}

			}

		}
		return Math.round(total_watt_consume);
	}

	public static ArrayList<String> getLoadSummaryTime(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getloadSummaryWatt(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("loadwatt") + " ");

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getApplianceSummaryWatt(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("watt") + " ");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<String> getApplianceSummaryTime(int appId) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary(?)");
			call.setInt(1, appId);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static HashMap<String, String> getApplianceStatusbyId(int app_id) {
		CallableStatement call = null;
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_appliance_allstatus(?)}");
			call.setInt(1, app_id);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {
				map.put("temprature", rs.getDouble("appliance_temperature")
						+ "");
				map.put("gsmSignal", rs.getDouble("appliance_gsm_signal") + "");
				map.put("latitude", rs.getDouble("latitude") + "");
				map.put("longitude", rs.getDouble("longitude") + "");
				map.put("batteryVolt", rs.getDouble("battery_voltage") + "");
				map.put("batteryAmp", rs.getDouble("battery_ampere") + "");
				map.put("solarVolt", rs.getDouble("solar_voltage") + "");
				map.put("solarAmp", rs.getDouble("solar_ampare") + "");
				map.put("loadAmp", rs.getDouble("load_ampere") + "");
				map.put("loadVolt", rs.getDouble("load_voltage") + "");
				map.put("appId", rs.getInt("appliance_id") + "");
				map.put("date", rs.getString("status_date") + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static int getLastStatusId(int id) {
		int lastId = 0;
		String query = "Select MAX(status_appliance_id) from appliance_status WHERE appliance_id=?;";
		try (Connection con = Connect.getConnection()) {
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lastId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return lastId;
	}

	public static double[] ApplianceSummaryDateWise(int appid, String date) {

		ArrayList<String> timelist = getApplianceSummaryTimeDateWise(appid,
				date);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getApplianceSummaryWattDateWise(appid,
				date);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);
		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		double total_time = 0.0;
		int i, start = 0;
		int times = 0;
		double total_watt_hours, counter = 0.0;
		double[] returnvalues;
		returnvalues = new double[] { 0.0, 0.0, 0.0 };
		counter = 0;

		total_watt_hours = 0.0;
		start = 0;
		double sum = 0.0;
		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 15
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_hours > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = total_time + hours;
						this_time_WH = sum * hours;
						total_watt_hours = total_watt_hours + this_time_WH;
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_time = hours;
						total_watt_hours = sum * hours;
					}
					sum = 0;
					counter = 0;
				}

			}

		}
		returnvalues[0] = total_watt_hours;
		returnvalues[1] = total_time;
		returnvalues[2] = (total_time * 3600 % 3600) / 60;
		return returnvalues;

	}

	public static double ApplianceLoadSummaryDateWise(int appid, String date) {

		ArrayList<String> timelist = getLoadSummaryTimeDateWise(appid, date);
		String[] time = timelist.toArray(new String[timelist.size()]);

		ArrayList<String> wattlist = getloadSummaryWattDateWise(appid, date);
		String[] watt = wattlist.toArray(new String[wattlist.size()]);
		boolean start_time = false;
		double hours = 0.0;
		double this_time_WH = 0.00;
		int i, start = 0;
		int times = 0;
		double total_watt_consume, counter = 0.0;
		counter = 0;
		total_watt_consume = 0.0;
		start = 0;
		double sum = 0.0;
		for (i = 0; i < watt.length; i++) {

			if (Double.parseDouble(watt[i].trim()) >= 1
					&& i < (watt.length - 1)) {

				if (start_time == false) {

					start = Integer.valueOf(time[i]);
					start_time = true;
				}
				sum = sum + Double.parseDouble(watt[i].trim());
				counter++;
			} else {

				if (start_time == true) {

					start_time = false;
					sum = sum / counter;
					times++;
					if (total_watt_consume > 0) {

						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						this_time_WH = sum * hours;
						total_watt_consume = total_watt_consume + this_time_WH;
					} else {
						hours = ((Double.valueOf(time[i - 1]) - start) / 3600);
						total_watt_consume = sum * hours;
					}
					sum = 0;
					counter = 0;
				}
			}
		}
		return Math.round(total_watt_consume);
	}

	public static ArrayList<String> getLoadSummaryTimeDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<String> getApplianceSummaryTimeDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("TIME") + "");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<String> getApplianceSummaryWattDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_appliance_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("watt") + " ");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<String> getloadSummaryWattDateWise(int appId,
			String date) {
		ArrayList<String> list = new ArrayList<String>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("CALL get_load_summary_datewise(?,?)");
			call.setInt(1, appId);
			call.setString(2, date);
			rs = call.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("loadwatt") + " ");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

}
