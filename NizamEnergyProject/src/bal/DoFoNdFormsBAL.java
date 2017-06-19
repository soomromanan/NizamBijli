package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class DoFoNdFormsBAL {
	final static Logger logger = Logger.getLogger(DoFoNdFormsBAL.class);

	public static int insert_user(HashMap<String, String> map) {
		int id = 0;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL insert_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			call.setString(1, map.get("doName"));
			call.setString(2, map.get("email"));
			call.setString(3, map.get("password"));
			call.setString(4, map.get("cnic"));
			call.setString(5, map.get("primaryPhone"));
			call.setString(6, map.get("secondaryPhone"));
			call.setString(7, map.get("address"));
			call.setDouble(8, Double.valueOf(map.get("salary")));
			call.setInt(9, Integer.valueOf(map.get("district")));
			call.setInt(10, Integer.valueOf(map.get("userType")));
			call.setString(11, map.get("gender"));
			call.setString(12, map.get("joiningDate"));
			call.setString(13, map.get("dateOfBirth"));
			call.setInt(14, Integer.valueOf(map.get("education")));
			call.setString(15, map.get("marritalStatus"));
			call.setString(16, map.get("refNumber1"));
			call.setString(17, map.get("refNumber2"));
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return id;

	}

	public static HashMap<String, String> update_user(
			HashMap<String, String> map) {

		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, Integer.valueOf(map.get("doid")));
			call.setString(2, map.get("doName"));
			call.setString(3, map.get("email"));
			call.setString(4, map.get("password"));
			call.setString(5, map.get("cnic"));
			call.setString(6, map.get("primaryPhone"));
			call.setString(7, map.get("secondaryPhone"));
			call.setString(8, map.get("address"));
			call.setDouble(9, Double.valueOf(map.get("salary")));
			call.setInt(10, Integer.valueOf(map.get("district")));
			call.setInt(11, 101);
			call.setString(12, map.get("gender"));
			call.setString(13, map.get("joiningDate"));
			call.setString(14, map.get("dateOfBirth"));
			call.setInt(15, Integer.valueOf(map.get("education")));
			call.setString(16, map.get("marritalStatus"));
			call.setString(17, map.get("refNumber1"));
			call.setString(18, map.get("refNumber2"));

			call.executeQuery();

		} catch (SQLException e) {
			logger.error("", e);
		}
		System.out.println("User updated");

		return map;

	}

	public static HashMap<String, String> update_fo(HashMap<String, String> map) {

		System.err.println(" update_fo DO map in BAL... " + map);

		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_field_officer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, Integer.parseInt(map.get("foid")));
			call.setString(2, map.get("foName"));
			call.setString(3, map.get("foPrimaryPhone"));
			call.setString(4, map.get("foSecondaryNumber"));
			call.setString(5, map.get("foEmail"));
			call.setString(6, map.get("foGender"));
			call.setString(7, map.get("foDateOfBirth"));
			call.setString(8, map.get("foCnic"));
			call.setString(9, map.get("foDateOfJoining"));
			call.setString(10, map.get("foMaritalStatus"));
			call.setString(11, map.get("foAddress"));
			call.setInt(12, Integer.parseInt(map.get("foEducation")));
			call.setDouble(13, Double.valueOf(map.get("foSalary")));
			call.setInt(14, Integer.parseInt(map.get("percellcomm")));
			call.setString(15, map.get("foAccNo"));
			call.executeQuery();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		System.out.println("FO Updated");

		return map;

	}

	public static int insert_fo(HashMap<String, String> map) {
		int id = 0;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL insert_field_officer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, Integer.parseInt(map.get("doId")));
			call.setString(2, map.get("foName"));
			call.setString(3, map.get("foPrimaryPhone"));
			call.setString(4, map.get("foSecondaryNumber"));
			call.setString(5, map.get("foEmail"));
			call.setString(6, map.get("foGender"));
			call.setString(7, map.get("foDateOfBirth"));
			call.setString(8, map.get("foCnic"));
			call.setString(9, map.get("foDateOfJoining"));
			call.setInt(10, Integer.parseInt(map.get("foCity")));
			call.setString(11, map.get("foMaritalStatus"));
			call.setString(12, map.get("foAddress"));
			call.setInt(13, Integer.parseInt(map.get("foEducation")));
			call.setDouble(14, Double.valueOf(map.get("foSalary")));
			call.setString(15, map.get("password"));
			call.setInt(16, Integer.parseInt(map.get("percellcomm")));
			call.setString(17, map.get("foAccNo"));
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return id;

	}

	public static int insert_NizamDost(HashMap<String, String> map) {
		int id = 0;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL insert_nizamdost(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			call.setInt(1, Integer.parseInt(map.get("foList")));
			call.setString(2, map.get("ndName"));
			call.setString(3, map.get("ndCnic"));
			call.setString(4, map.get("ndDistrict"));
			call.setString(5, map.get("ndAddress"));
			call.setString(6, map.get("ndPrimaryPhone"));
			call.setString(7, map.get("ndSecondaryPhone"));
			call.setDouble(8, Double.valueOf(map.get("ndSalary")));
			call.setString(9, map.get("ndDateOfJoining"));
			call.setInt(10, Integer.parseInt(map.get("ndCity")));
			call.setString(11, map.get("ndDateOfBirth"));
			call.setString(12, map.get("ndEducation"));
			call.setString(13, map.get("ndMaritalStatus"));
			call.setString(14, map.get("ndEmail"));
			call.setString(15, map.get("ndGender"));
			call.setInt(16, Integer.parseInt(map.get("ndPerSellCommission")));
			call.setString(17, map.get("ndAccNumber"));
			call.setString(18, map.get("password"));
			ResultSet rs = call.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return id;
	}

	public static HashMap<String, String> update_Salesman(
			HashMap<String, String> map) {
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL update_salesman(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			call.setInt(1, Integer.parseInt(map.get("ndId")));
			call.setString(2, map.get("ndName"));
			call.setString(3, map.get("ndCnic"));
			call.setString(4, map.get("ndAddress"));
			call.setString(5, map.get("ndPrimaryPhone"));
			call.setString(6, map.get("ndSecondaryPhone"));
			call.setDouble(7, Double.valueOf(map.get("ndSalary")));
			call.setString(8, map.get("ndDateOfJoining"));
			call.setString(9, map.get("ndDateOfBirth"));
			call.setString(10, map.get("ndEducation"));
			call.setString(11, map.get("ndMaritalStatus"));
			call.setString(12, map.get("ndEmail"));
			call.setString(13, map.get("ndGender"));
			call.setInt(14, Integer.parseInt(map.get("ndPerSellCommission")));
			call.setString(15, map.get("ndAccNumber"));

			call.executeQuery();

			System.err.println("nd map >>>>>> " + map);

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		System.out.println("ND updated");

		return map;

	}

	public static HashMap<String, String> getSalesmanDetailForUpdate(int Ndid) {
		HashMap<String, String> map = null;

		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_salesman_update_by_id(?)}");
			call.setInt(1, Ndid);
			rs = call.executeQuery();
			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}

			if (rs.next()) {
				map = new HashMap<>();

				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

	public static HashMap<String, String> getDoDetailForUpdate(int doId) {
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection();) {
			call = con.prepareCall("{CALL get_district_officer_detail(?)}");
			call.setInt(1, doId);
			rs = call.executeQuery();
			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}
			if (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
			}

		} catch (SQLException e) {
			logger.error("", e);
		}

		return map;
	}

	public static HashMap<String, String> getFieldOfficerDetailForUpdate(
			int foId) {
		HashMap<String, String> map = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_field_officer_details(?)}");
			call.setInt(1, foId);
			rs = call.executeQuery();
			ResultSetMetaData metaDeta = rs.getMetaData();
			String columns[] = new String[metaDeta.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaDeta.getColumnLabel(i + 1);
			}
			if (rs.next()) {
				map = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					map.put(columns[i], rs.getString(columns[i]));
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		System.err.println(map);
		return map;
	}

	public static ArrayList<HashMap<String, String>> getFieldOfficersbyDistrict(
			int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{Call get_field_officers_by_district(?)}");
			call.setInt(1, userId);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foid", rs.getString("fo_id"));
				map.put("foName", rs.getString("fo_name"));
				map.put("foPhone", rs.getString("fo_priamary_phone"));
				map.put("cnic", rs.getString("fo_cnic"));
				map.put("address", rs.getString("fo_address"));
				map.put("salary", rs.getDouble("basic_salary") + "");
				map.put("joiningDate", rs.getString("fo_date_of_joining"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}
	
}
