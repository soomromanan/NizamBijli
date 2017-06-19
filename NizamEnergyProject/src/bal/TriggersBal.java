package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class TriggersBal {

	private final static Logger logger = Logger.getLogger(TriggersBal.class);

	public static void updateAcceptedCustomersToNoIntersted(int appliance_id) {
		CallableStatement call = null;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{CALL revoke_by_thread(?)}");
			call.setInt(1, appliance_id);
			call.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void updateVarifiedCustomersToNoIntersted(int appliance_id) {
		CallableStatement call = null;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{CALL update_customers_to_not_interested(?)}");
			call.setInt(1, appliance_id);
			call.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static ArrayList<HashMap<String, String>> getAcceptedCustomers() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_accepted_customers()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customer_name", rs.getString("customer_name"));
				map.put("remaining", "" + rs.getInt("remaining"));
				map.put("eligibility_id", "" + rs.getInt("eligibility_id"));
				map.put("appliance_id", "" + rs.getInt("appliance_id"));
				map.put("customer_id", rs.getInt("customer_id") + "");
				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getVerifiedCustomers() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_verified_customers()}");
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("customer_name", rs.getString("c.customer_name"));
				map.put("customer_id", "" + rs.getInt("c.customer_id"));
				map.put("remaining", "" + rs.getInt("remaining"));
				map.put("eligibility_id", "" + rs.getInt("e.eligibility_id"));
				map.put("appliance_id", "" + rs.getInt("a.appliance_id"));
				list.add(map);
			}

		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getOffMessages() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_devices_in_minus_one()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map = new HashMap<>();
				map.put("days", resultSet.getInt("days") + "");
				map.put("applianceName", resultSet.getString("appliance_name")
						+ "");
				map.put("gsmNumber", resultSet.getString("appliance_GSMno")
						+ "");
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> sendDueDateReminders() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("{call get_duedate_reminders()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map = new HashMap<>();
				int days = resultSet.getInt("days");
				int downpayment_days = resultSet.getInt("downpayment_days");
				String customerName = resultSet.getString("customer_name");
				String customerPhone = resultSet.getString("customer_phone");
				String applianceName = resultSet.getString("appliance_name");
				int monthlyInstallment = resultSet
						.getInt("installment_amount_month");
				String gsmNumber = resultSet.getString("appliance_GSMno");
				Date duedate = resultSet.getDate("due_date");
				String imei_number = resultSet.getString("imei_number");
				String NdName = resultSet.getString("salesman_name");
				String foName = resultSet.getString("fo_name");
				String doName = resultSet.getString("user_name");
				Date date = addDays(duedate, 10);
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

				map.put("days", days + "");
				map.put("downpayment_days", downpayment_days + "");
				map.put("customerName", customerName + "");
				map.put("customerPhone", customerPhone + "");
				map.put("applianceName", applianceName + "");
				map.put("monthlyInstallment", monthlyInstallment + "");
				map.put("gsmNumber", gsmNumber + "");
				map.put("duedate", duedate + "");
				map.put("imei_number", imei_number + "");
				map.put("afterTenDays", df.format(date) + "");
				map.put("NdName", NdName + "");
				map.put("foName", foName + "");
				map.put("doName", doName + "");
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static void isLive() {
		System.out.println("isLive()!!!!!!!!!");
		ArrayList<HashMap<String, String>> list = null;
		int previousStatus = 0;
		int flag = 0;
		int status = 0;
		double solarPower = 0.0;
		double loadPower = 0.0;
		try {
			list = getHealth();
			for (HashMap<String, String> hashMap : list) {
				int health = Integer.parseInt(hashMap.get("number"));
				int appid = Integer.parseInt(hashMap.get("appId"));

				if (health > 15) {
					solarPower = ApplianceStatusBal.ApplianceSummary(appid)[0];
					loadPower = ApplianceStatusBal.ApplianceLoadSummary(appid);
					try (Connection con = Connect.getConnection();
							Statement statement = con.createStatement()) {
						statement
								.executeUpdate("UPDATE appliance SET appliance.is_alive=1, appliance.status = 6 ,"
										+ " appliance.health_flag=0 ,appliance.health_status=3 , appliance.load_consumed="
										+ loadPower
										+ ",appliance.power_produced="
										+ solarPower
										+ " WHERE appliance.`appliance_id`="
										+ appid);

					} catch (SQLException e) {
						logger.error(e);
						e.printStackTrace();
					}
					try (Connection con = Connect.getConnection();
							Statement statement = con.createStatement()) {
						statement
								.executeUpdate("INSERT INTO appliance_analytics(appliance_id , power_produced , power_consumed , date )"
										+ "VALUES ("
										+ appid
										+ ","
										+ solarPower
										+ "," + loadPower + ", CURDATE() ) ");

					} catch (SQLException e) {
						logger.error(e);
						e.printStackTrace();
					}
				} else {
					try (Connection con = Connect.getConnection();) {
						ResultSet rs = null;
						Statement s = null;
						s = con.createStatement();
						rs = s.executeQuery("SELECT a.is_alive,a.health_flag ,a.status FROM appliance a WHERE a.`appliance_id`="
								+ appid);
						while (rs.next()) {
							previousStatus = rs.getInt("is_alive");
							flag = rs.getInt("health_flag");
							status = rs.getInt("status");
						}
					} catch (Exception ex) {
						ex.getStackTrace();
					}
					if ((previousStatus == 1) && (flag == 0)) {

						try (Connection con = Connect.getConnection();
								Statement statement = con.createStatement()) {
							System.out.println("isLive()!!!!!!!!!<15");
							statement
									.executeUpdate("UPDATE appliance a SET a.`is_alive`=0 , a.`dead_since`=CURDATE(),a.health_flag=1 "
											+ " ,a.health_status=2 ,a.`load_consumed`=NULL , a.`power_produced`=NULL  "
											+ " WHERE `appliance_id`=" + appid);

						} catch (SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					} else if ((previousStatus == 0) && (flag == 0)) {
						if (status == 6) {
							try (Connection con = Connect.getConnection();
									Statement statement = con.createStatement()) {
								statement
										.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=1 ,a.dead_since=CURDATE() ,a.health_status=2 ,"
												+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
												+ " WHERE a.`appliance_id`="
												+ appid);

							} catch (SQLException e) {
								logger.error(e);
								e.printStackTrace();
							}
						} else if (status == 7) {

							try (Connection con = Connect.getConnection();
									Statement statement = con.createStatement()) {
								statement
										.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=0 ,a.dead_since=CURDATE() ,a.health_status=1 ,"
												+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
												+ " WHERE a.`appliance_id`="
												+ appid);

							} catch (SQLException e) {
								logger.error(e);
								e.printStackTrace();
							}

						}
					} else if ((previousStatus == 0) && (flag == 1)) {
						try (Connection con = Connect.getConnection();
								Statement statement = con.createStatement()) {
							statement
									.executeUpdate("UPDATE appliance a SET a.is_alive=0 , a.health_flag=1 ,a.health_status=2 , "
											+ " a.`load_consumed`=NULL , a.`power_produced`=NULL  "
											+ " WHERE a.`appliance_id`="
											+ appid);

						} catch (SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public static ArrayList<HashMap<String, String>> getHealth() {
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection();) {
			ResultSet rs = null;
			Statement s = null;
			s = con.createStatement();
			rs = s.executeQuery("SELECT ap.appliance_id , HealthStatus(ap.appliance_id)AS number "
					+ " FROM appliance ap INNER JOIN sold_to sld on sld.appliance_id = ap.appliance_id "
					+ " WHERE (ap.appliance_name!='60 W' AND ap.appliance_name!='7 W' AND ap.appliance_name!='P-60') AND (ap.status = 6 || ap.status = 7) "
					+ " GROUP BY ap.appliance_id;");
			while (rs.next()) {
				map = new HashMap<>();
				map.put("appId", rs.getInt("appliance_id") + "");
				map.put("number", rs.getInt("number") + "");
				list.add(map);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		}
		return list;
	}

}
