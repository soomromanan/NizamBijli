package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.InfowindowBean;
import connection.Connect;

public class MapBal {

	private final static Logger logger = Logger.getLogger(MapBal.class);

	public static ArrayList<InfowindowBean> customerLocation() {
		ArrayList<InfowindowBean> list = new ArrayList<InfowindowBean>();
		double latitude, longitude;
		String customer_name, app_name;
		int scheme, type, id;
		InfowindowBean bean = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{CALL customers_location()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					bean = new InfowindowBean();
					customer_name = resultSet.getString(2);
					app_name = resultSet.getString(1);
					latitude = resultSet.getDouble(3);
					longitude = resultSet.getDouble(4);
					scheme = resultSet.getInt(5);
					type = resultSet.getInt(6);
					id = resultSet.getInt(7);
					bean.setLati(latitude);
					bean.setLongi(longitude);
					bean.setApp_name(app_name);
					bean.setCustomer_name(customer_name);
					bean.setScheme(scheme);
					bean.setType(type);
					bean.setApplianceId(id);
					list.add(bean);
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<HashMap<String, Object>> getDoAppliances(int userId) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement cal = con
					.prepareCall("{CALL view_do_appliances_marker(?)}");
			cal.setInt(1, userId);
			ResultSet rs = cal.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("applianceId", rs.getInt("appliance_id"));
				map.put("status", rs.getInt("appliance_status"));
				map.put("latitude", rs.getDouble("latitude"));
				map.put("longitude", rs.getDouble("longitude"));
				map.put("ApplianceName", rs.getString("appliance_name"));
				map.put("CustomerName", rs.getString("customer_name"));
				list.add(map);
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}
	
	public static HashMap<String, String> getStatusInfo(int lastId,
			int applianceID) {
		ResultSet rs = null;
		int statusId, applianceId, type;
		double temperatue, lati, longi;
		String statusDate;
		CallableStatement call = null;
		HashMap<String, String> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{call get_status_map_info(?,?)}");
			call.setInt(1, applianceID);
			call.setInt(2, lastId);
			rs = call.executeQuery();
			while (rs.next()) {
				statusId = rs.getInt(1);
				applianceId = rs.getInt(2);
				temperatue = rs.getDouble(3);
				type = rs.getInt("appliance_status");
				lati = rs.getDouble(4);
				longi = rs.getDouble(5);
				statusDate = rs.getString(6);
				map.put("statusId", statusId + "");
				map.put("applianceId", applianceId + "");
				map.put("temperatue", temperatue + "");
				map.put("type", type + "");
				map.put("lati", lati + "");
				map.put("longi", longi + "");
				map.put("statusDate", statusDate);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}


}
