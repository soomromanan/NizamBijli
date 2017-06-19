package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

import bean.CityBean;
import bean.DistrictBean;
import bean.DistrictOfficerBean;
import bean.SalesManBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.ocpsoft.pretty.time.PrettyTime;

import connection.Connect;

public class DoFoNdBal {
	final static Logger logger = Logger.getLogger(DoFoNdBal.class);

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

	public static String updateFo(int foIdd) {
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		int foId = 0;
		try (Connection con = Connect.getConnection();
				Statement s = con.createStatement();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_nd_list(?)}");
			prepareCall.setInt(1, foIdd);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("foName", "" + rs.getString("fo_name"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("do_id"));
				list.add(map);
			}
			System.err.println(list);
			rs = s.executeQuery("SELECT fo.fo_id FROM field_officer fo JOIN `user` u ON u.user_id = fo.do_id "
					+ "JOIN district d ON d.district_id = u.user_district WHERE u.`user_id` = "
					+ list.get(0).get("doId")
					+ " AND fo.fo_name LIKE '%Bulk%' ");
			if (rs.next()) {
				foId = rs.getInt("fo_id");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		if (foId != 0) {
			for (int i = 0; i < list.size(); i++) {
				try (Connection con = Connect.getConnection();
						Statement s = con.createStatement();) {
					s.executeUpdate("UPDATE `salesman` SET fo_id = " + foId
							+ ",last_fo = '" + list.get(i).get("foName")
							+ "' WHERE `salesman_id` = "
							+ list.get(i).get("ndId") + " And fo_id = "
							+ list.get(i).get("foId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			try (Connection con = Connect.getConnection();
					Statement s = con.createStatement();) {
				s.executeUpdate("UPDATE `field_officer` SET is_left = 1 WHERE `fo_id` = "
						+ foIdd + "");
			} catch (Exception ex) {
				logger.error("", ex);
				ex.printStackTrace();
			}
		}
		return "Ok Done";
	}

	public static String updateNizamDost(int ndIdd) {
		ResultSet rs = null;
		ArrayList<HashMap<String, String>> listEl = new ArrayList<>();
		ArrayList<HashMap<String, String>> listSold = new ArrayList<>();
		int ndId = 0;
		HashMap<String, String> map = null;
		try (Connection con = Connect.getConnection();
				Statement s = con.createStatement();) {
			CallableStatement prepareCall = con
					.prepareCall("{CALL get_list(?)}");
			prepareCall.setInt(1, ndIdd);
			rs = prepareCall.executeQuery();
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("user_id"));
				listEl.add(map);
			}
			String query = "SELECT e.`sold_to_id`,a.`appliance_id`,s.salesman_id, fo.`fo_id`,u.`user_id` FROM `sold_to` e JOIN `customer` "
					+ "cs ON cs.`customer_id` = e.`customer_id` JOIN `appliance` a ON a.`appliance_id` = e.`appliance_id`"
					+ " JOIN `salesman` s ON s.`salesman_id` = e.`salesman_id` JOIN `field_officer` fo  "
					+ "ON fo.`fo_id` = s.`fo_id` JOIN user u ON u.`user_id` = fo.`do_id` WHERE s.`salesman_id` ="
					+ ndIdd;
			rs = s.executeQuery(query);
			while (rs.next()) {
				map = new HashMap<>();
				map.put("foId", "" + rs.getInt("fo_id"));
				map.put("ndId", "" + rs.getInt("salesman_id"));
				map.put("doId", "" + rs.getInt("user_id"));
				listSold.add(map);
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		System.err.println(listSold);

		try (Connection con = Connect.getConnection();
				Statement s = con.createStatement();) {
			rs = s.executeQuery("select salesman_id from salesman s JOIN field_officer fo on fo.fo_id = s.fo_id WHERE `do_id`= "
					+ listEl.get(0).get("doId")
					+ " AND salesman_name LIKE '%Bulk%' ");
			if (rs.next()) {
				ndId = rs.getInt("salesman_id");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		if (ndId != 0) {
			for (int i = 0; i < listEl.size(); i++) {
				try (Connection con = Connect.getConnection();
						Statement s = con.createStatement();) {
					s.executeUpdate("UPDATE `eligibility` SET salesman_id = "
							+ ndId + " WHERE `salesman_id` = "
							+ listEl.get(i).get("ndId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			for (int i = 0; i < listSold.size(); i++) {
				try (Connection con = Connect.getConnection();
						Statement s = con.createStatement();) {
					s.executeUpdate("UPDATE `sold_to` SET salesman_id = "
							+ ndId + " WHERE `salesman_id` = "
							+ listSold.get(i).get("ndId") + " ");
				} catch (Exception ex) {
					logger.error("", ex);
					ex.printStackTrace();
				}
			}

			try (Connection con = Connect.getConnection();
					Statement s = con.createStatement();) {
				s.executeUpdate("UPDATE `salesman` SET is_left = 1 WHERE `salesman_id` = "
						+ ndIdd + "");
			} catch (Exception ex) {
				logger.error("", ex);
				ex.printStackTrace();
			}
		}
		return "Ok Done";
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListByIdWithOrder(
			int start, int length, int doid, int order, String dir,
			String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs;
		CallableStatement call = null;
		PrettyTime p = new PrettyTime();
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{Call get_all_field_officer_by_doid_in_order_search(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(5, doid);
			call.setInt(4, order);
			call.setString(3, dir);
			call.setString(6, search);
			rs = call.executeQuery();
			if (call != null) {
				call.setInt(1, start);
				call.setInt(2, length);
				rs = call.executeQuery();
				while (rs.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("salesmanId", rs.getInt("salesman_id") + "");
					map.put("salesmanName", rs.getString("salesman_name"));
					map.put("salesmanNumber",
							rs.getString("salesman_primary_phone"));
					map.put("foName", rs.getString("fo_name") + "");
					map.put("total_apps", rs.getString("total_apps") + "");
					map.put("total_installs", rs.getString("total_installs")
							+ "");
					map.put("recovery",
							(NumberFormat.getNumberInstance(Locale.US)
									.format((rs.getDouble("recovery")))) + "");
					map.put("monthly_installs", rs.getInt("monthly_installs")
							+ "");
					map.put("weekly_installs", rs.getInt("weekly_installs")
							+ "");
					map.put("monthly_apps", rs.getInt("monthly_apps") + "");
					map.put("weekly_apps", rs.getInt("weekly_apps") + "");
					int count = rs.getInt("salesman_status");
					if (count > 0) {
						map.put("salesman_status", "Active");
					} else {
						map.put("salesman_status", "Inactive");
					}
					if (rs.getString("last_sale") != null) {
						Timestamp dateTime = rs.getTimestamp("last_sale");
						Date date = new Date(dateTime.getTime());
						map.put("last_sale", p.format(date));
					} else {
						map.put("last_sale", "No Sale");
					}
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}

		return list;
	}

	public static int getSalesmanSearchCountById(int doid, String value) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_salesman_do_count_search(?,?)}");
			call.setInt(1, doid);
			call.setString(2, value);
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

	public static SalesManBean getSalesManByID(int salesmanId) {
		SalesManBean bean = null;
		int salesman_id, total_customer, commsion_date;
		String name, cnic, phoneNo, district, address;
		double salary, salesman_basic_sallery;
		Date date, date_of_birth;
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date newDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(newDate);
		c.add(Calendar.MONTH, -1);
		c.add(Calendar.DATE, 1);
		String formated = formate.format(c.getTime());
		System.out.println(formated);
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{call get_salesman_by_id(?)}");
			prepareCall.setInt(1, salesmanId);
			ResultSet rs = prepareCall.executeQuery();
			while (rs.next()) {
				salesman_id = rs.getInt(1);
				name = rs.getString(2);
				cnic = rs.getString(3);
				district = rs.getString(4);
				address = rs.getString(5);
				phoneNo = rs.getString(6);
				salary = rs.getDouble(7);
				date = rs.getDate(8);
				total_customer = rs.getInt(9);
				date_of_birth = rs.getDate(10);
				salesman_basic_sallery = rs.getDouble(11);
				commsion_date = rs.getInt("commission_date");
				bean = new SalesManBean();
				bean.set__commission_date(commsion_date);
				bean.setSalesmanId(salesman_id);
				bean.setName(name);
				bean.setCnic(cnic);
				bean.setDatejoin(date);
				bean.setDistrict(district);
				bean.setTotal_customer(total_customer);
				bean.setPhone_number(phoneNo);
				bean.setSallery(salary);
				bean.setAddress(address);
				bean.setTotalSales(rs.getInt("totalsales"));
				bean.setDate_of_birth(date_of_birth);
				bean.setSalesman_basic_sallery(salesman_basic_sallery);
				bean.setGender(rs.getString("gender"));
				bean.setMarritalStatus(rs.getString("marrital_status"));
				bean.setEducated(rs.getString("education"));
				bean.setTahsel(rs.getString("city"));
				bean.setFoname(rs.getString("fo_name"));
				bean.setDoname(rs.getString("user_name"));
				bean.setPer_sale(rs.getInt("per_sale"));
				bean.setVle_acount_no(rs.getString("vle_acount_no"));
				bean.setDoid(rs.getInt("user_id"));
				bean.setFoid(rs.getInt("fo_id"));
				bean.setPhone_number2(rs.getString("salesman_secondary_phone"));
				bean.setSalesman_email(rs.getString("salesman_email"));
				bean.setDistrict_name(rs.getString("district_name"));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

	public static ArrayList<HashMap<String, String>> getSalesmanListWith(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		PrettyTime p = new PrettyTime();
		try (Connection connection = Connect.getConnection()) {
			call = connection.prepareCall("{Call get_Nd_data(?,?,?,?,?)}");
			call.setInt(1, str);
			call.setInt(2, rng);
			call.setString(3, orde);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesmanId", rs.getInt("salesman_id") + "");
				map.put("salesmanName", rs.getString("salesman_name"));
				map.put("salesmanAddress", rs.getString("salesman_address"));
				String number = CustomerBal.getFormattedPhoneNumber(rs
						.getString("salesman_primary_phone"));
				map.put("salesmanNumber", number);
				map.put("salesmanSalary",
						rs.getDouble("salesman_basic_sallery") + "");
				map.put("salesmanJoiningDate", rs.getDate("salesman_joindate")
						+ "");
				map.put("salesmanDistrict", rs.getString("district_data"));
				map.put("foname", rs.getString("fo_name"));
				if (rs.getTimestamp("last_sale") != null) {
					Timestamp dateTime = rs.getTimestamp("last_sale");
					Date date = new Date(dateTime.getTime());
					map.put("lastSale", p.format(date));
				} else {
					map.put("lastSale", "No Sale");
				}
				map.put("tatalApps", rs.getString("total_apps"));
				map.put("totalInstalls", rs.getString("total_installs"));
				map.put("monthlyApps", rs.getString("monthly_apps"));
				map.put("monthlyInstalls", rs.getString("monthly_installs"));
				map.put("weeklyApps", rs.getString("weekly_apps"));
				map.put("weeklyInstalls", rs.getString("weekly_installs"));
				map.put("recovery", rs.getString("recovery"));
				int count = rs.getInt("salesman_status");
				if (count > 0) {
					map.put("status", "Active");
				} else {
					map.put("status", "Inactive");
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
	}

	public static int getSearchCountForSalesman(String search) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_salesmans_count_search(?)}");
			call.setString(1, search);
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

	public static ArrayList<HashMap<String, String>> getFoSalesman(int foID) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_fo_salesman(?) }");
			call.setInt(1, foID);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("SalesName", rs.getString("salesman_name"));
				map.put("Cnic", rs.getString("salesman_cnic"));
				map.put("Address", rs.getString("salesman_address"));
				map.put("District", rs.getString("salesman_district"));
				map.put("Phone", rs.getString("salesman_primary_phone"));
				map.put("Salary", rs.getDouble("salesman_basic_sallery") + "");
				map.put("SalesMan_id", rs.getInt("salesman_id") + "");
				map.put("lateCustomer", rs.getInt("latecustomer") + "");
				map.put("Sales", rs.getInt("Sales") + "");
				map.put("salesmanID", rs.getInt("salesman_id") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return list;
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

	public static int getDistrictOfficerListBySearchCount(String a) {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_district_officer_list_search_count(?)}");
			call.setString(1, a);
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

	public static ArrayList<HashMap<String, String>> getDistrictOfficerListWithSearch(
			int start, int length, String order, int col, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL getDoDataSearch(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, order);
			call.setInt(4, col);
			call.setString(5, search);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("userId", rs.getInt("user_id") + "");
				map.put("userName",
						rs.getString("district_name") + ",  "
								+ rs.getString("user_name"));
				map.put("districtName", rs.getString("district_name"));
				map.put("fos", rs.getInt("fos") + "");
				map.put("vle", rs.getInt("vle") + "");
				map.put("activeND", rs.getInt("activ_vle") + "");
				map.put("totalApps", rs.getInt("total_Apps") + "");
				map.put("Installed", rs.getInt("total_installed") + "");
				map.put("monthlyInstalled", rs.getInt("monthly_installed") + "");
				map.put("weeklyInstalled", rs.getInt("weekly_installed") + "");
				map.put("monthlyApps", rs.getInt("monthly_apps") + "");
				map.put("weeklyApps", rs.getInt("weekly_apps") + "");
				map.put("recovery", rs.getString("recovery") + " %");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getDistrictOfficerListCount() {
		int count = 0;
		CallableStatement call = null;
		ResultSet rs;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_district_officer_list_count()}");
			rs = call.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return count;
	}

	public static ArrayList<HashMap<String, String>> getDistricOfficerListWithOrder(
			int start, int length, int order, String dir) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL getDodata(?,?,?,?)}");
			if (call != null) {
				call.setInt(1, start);
				call.setInt(2, length);
				call.setString(3, dir);
				call.setInt(4, order);
				rs = call.executeQuery();
				while (rs.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("userId", rs.getInt("user_id") + "");
					map.put("userName", rs.getString("district_name") + ",  "
							+ rs.getString("user_name"));
					map.put("districtName", rs.getString("district_name"));
					map.put("fos", rs.getInt("fos") + "");
					map.put("vle", rs.getInt("vle") + "");
					map.put("activeND", rs.getInt("activ_vle") + "");
					map.put("totalApps", rs.getInt("total_Apps") + "");
					map.put("Installed", rs.getInt("total_installed") + "");
					map.put("monthlyInstalled", rs.getInt("monthly_installed")
							+ "");
					map.put("weeklyInstalled", rs.getInt("weekly_installed")
							+ "");
					map.put("monthlyApps", rs.getInt("monthly_apps") + "");
					map.put("weeklyApps", rs.getInt("weekly_apps") + "");
					map.put("recovery", rs.getString("recovery") + " %");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNizamDosts(
			int districtId) {
		ResultSet rs = null;
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{call get_do_nizamdosts(?)}");
				prepareCall.setInt(1, districtId);
				rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("name", rs.getString("salesman_name"));
					map.put("foname", rs.getString("fo_name"));
					map.put("salesmanId", rs.getString("salesman_id"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	} // end of getting all Salesman form particular District

	public static DistrictOfficerBean getDO_details(int do_id) {
		DistrictOfficerBean bean = null;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_detail(?)}");
			prepareCall.setInt(1, do_id);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				bean = new DistrictOfficerBean();
				bean.setDo_name(resultSet.getString("user_name"));
				bean.setCnic(resultSet.getString("user_cnic"));
				bean.setPhone(resultSet.getString("primary_phone_number"));
				bean.setSallery(resultSet.getDouble("user_basic_salary"));
				bean.setDistrict_name(resultSet.getString("district_name"));
				bean.setAddress(resultSet.getString("user_address"));
				bean.setUseremail(resultSet.getString("user_email"));
				bean.setDateofjoining(resultSet.getString("joining_date"));
				bean.setDateofbirth(resultSet.getString("date_of_birth"));
				bean.setMarrital_status(resultSet.getString("marrital_status"));
				bean.setUser_gender(resultSet.getString("user_gender"));
				bean.setEducation(resultSet.getString("education"));
				bean.setRefferal_number1(resultSet
						.getString("refferal_number1"));
				bean.setRefferal_number2(resultSet
						.getString("refferal_number2"));
				bean.setSecondary_phone(resultSet
						.getString("secondary_phone_number"));
				bean.setLatitude(resultSet.getDouble("latitude"));
				bean.setLongitutde(resultSet.getDouble("longitude"));
			}
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return bean;
	}

	public static ArrayList<HashMap<String, String>> getFieldOfficers(int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();

		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_field_officers(?)}");
			call.setInt(1, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foid", rs.getString("fo_id"));
				map.put("foName", rs.getString("fo_name"));
				map.put("foPhone", rs.getString("fo_priamary_phone"));
				map.put("cnic", rs.getString("fo_cnic"));
				map.put("address", rs.getString("fo_address"));
				map.put("salary", rs.getString("basic_salary"));
				map.put("joiningDate", rs.getString("fo_date_of_joining"));
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDistrict() {
		ResultSet rs = null;
		HashMap<String, String> map = null;
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{call get_districts()}");
				rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("name", rs.getString("district_name"));
					map.put("userId", rs.getString("district_id"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all Salesman form particular District
	
	

	public static ArrayList<SalesManBean> getSallesman(int doId) {
		ResultSet rs = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(new Date());
		SalesManBean bean = null;
		ArrayList<SalesManBean> SallesmanList = new ArrayList<SalesManBean>();
		int salesman_id, latecustomer, totalSales;
		double monthlyIncome;
		String sallesman_name, cnicNo, address, district, phone;
		Date datejoin;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{call get_do_salesman(?)}");
				prepareCall.setInt(1, doId);
				rs = prepareCall.executeQuery();
				while (rs.next()) {
					sallesman_name = rs.getString(1);
					cnicNo = rs.getString(2);
					address = rs.getString(3);
					district = rs.getString(4);
					address = rs.getString(5);
					monthlyIncome = rs.getDouble(6);
					datejoin = rs.getDate(7);
					phone = rs.getString(8);
					salesman_id = rs.getInt(9);
					latecustomer = rs.getInt(10);
					totalSales = rs.getInt(11);
					bean = new SalesManBean();
					bean.setName(sallesman_name);
					bean.setCnic(cnicNo);
					bean.setAddress(address);
					bean.setDistrict(district);
					bean.setDatejoin(datejoin);
					bean.setSallery(monthlyIncome);
					bean.setPhone_number(phone);
					bean.setSalesmanId(salesman_id);
					bean.setLateCustomer(latecustomer);
					bean.setTotalSales(totalSales);
					bean.setFoname(rs.getString("fo_name"));
					SallesmanList.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return SallesmanList;
	} // end of getting all Salesman form particular District

	public static ArrayList<DistrictBean> getDistrict_names() {
		ResultSet rs = null;
		DistrictBean bean = null;
		ArrayList<DistrictBean> list = new ArrayList<>();
		int districtId;
		String district_name;
		try (Connection con = Connect.getConnection()) {

			String query = "SELECT district_id, district_name FROM district Order by district_name;";
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				districtId = rs.getInt(1);
				district_name = rs.getString(2);
				bean = new DistrictBean();
				bean.setDistrict_id(districtId);
				bean.setDistrict_name(district_name);
				list.add(bean);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all custome

	public static ArrayList<CityBean> getDistrictCities(int district) {
		ArrayList<CityBean> city_list = new ArrayList<CityBean>();
		CityBean bean;
		String query = "SELECT city.city_id,city.city_name,dis.`district_name` FROM city "
				+ "JOIN city_district ON city.city_id=city_district.city_id "
				+ "JOIN `district` dis ON dis.`district_id`=`city_district`.`district_id` "
				+ "WHERE city_district.district_id=" + district;
		try (Connection con = Connect.getConnection()) {
			ResultSet rs = null;
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				bean = new CityBean();
				bean.setCity_id(rs.getInt("city_id"));
				bean.setCity_name(rs.getString("city_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				city_list.add(bean);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return city_list;
	}

	public static ArrayList<CityBean> getFOCities(int DoId) {
		ResultSet rs = null;
		ArrayList<CityBean> city_list = new ArrayList<CityBean>();
		CityBean bean = null;
		String query = " SELECT city.city_id,city.city_name,dis.`district_name` FROM city "
				+ " JOIN city_district ON city.city_id=city_district.city_id "
				+ " JOIN `district` dis ON dis.`district_id`=`city_district`.`district_id` "
				+ "JOIN  USER u ON dis.`district_id`=u.`user_district`"
				+ "WHERE u.`user_id`=" + DoId;
		try (Connection con = Connect.getConnection()) {
			Statement stmt = (Statement) con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				bean = new CityBean();
				bean.setCity_id(rs.getInt("city_id"));
				bean.setCity_name(rs.getString("city_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				city_list.add(bean);
			}

		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return city_list;
	}

	public static ArrayList<SalesManBean> getDistrictSalesman(int doId) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(new Date());
		SalesManBean bean = null;
		ArrayList<SalesManBean> SallesmanList = new ArrayList<SalesManBean>();
		ArrayList<SalesManBean> list1 = new ArrayList<SalesManBean>();
		int salesman_id, latecustomer, totalSales;
		double monthlyIncome;
		String sallesman_name, cnicNo, address, district, phone;
		Date datejoin;

		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{call get_do_salesman(?)}");
				prepareCall.setInt(1, doId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					sallesman_name = rs.getString(1);
					cnicNo = rs.getString(2);
					address = rs.getString(3);
					district = rs.getString(4);
					address = rs.getString(5);
					monthlyIncome = rs.getDouble(6);
					datejoin = rs.getDate(7);
					phone = rs.getString(8);
					salesman_id = rs.getInt(9);
					latecustomer = rs.getInt(10);
					totalSales = rs.getInt(11);
					bean = new SalesManBean();
					bean.setName(sallesman_name);
					bean.setCnic(cnicNo);
					bean.setAddress(address);
					bean.setDistrict(district);
					bean.setDatejoin(datejoin);
					bean.setSallery(monthlyIncome);
					bean.setPhone_number(phone);
					bean.setSalesmanId(salesman_id);
					bean.setLateCustomer(latecustomer);
					bean.setTotalSales(totalSales);
					SallesmanList.add(bean);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list1;
	}

}
