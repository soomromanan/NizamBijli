package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class DoDashboardBal {

	final static Logger logger = Logger.getLogger(DoDashboardBal.class);

	public static HashMap<String, String> getDoRecovery(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_do_recovery_rate(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("amount", Math.round(rs.getDouble(1)) + "");
					map.put("percentage", Math.round(rs.getDouble(2)) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> getDoOverduePayments(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_over_due_customers(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); 
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getDoDefaulters(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_do_wise_defaulters(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> getDoInstallations(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call count_total_do_installations_of_current_month(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); 
				while (rs.next()) {
					map = new HashMap<>();
					map.put("total", rs.getInt(1) + "");
					map.put("perc", rs.getDouble(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> getDoSales(int districId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_do_sales(?)}");
				prepareCall.setInt(1, districId);
				ResultSet rs = prepareCall.executeQuery(); 
				while (rs.next()) {
					map = new HashMap<>();
					map.put("toatlApps", rs.getInt(1) + "");
					map.put("currentMonthApps", rs.getInt(2) + "");
					map.put("installed", rs.getInt(3) + "");
					map.put("currentWeekApps", rs.getInt(4) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> getDoPerformanceDetails(int userId,
			String to, String from) { //
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = null;
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection
							.prepareCall("{call get_do_fos_sales(?)}");
					prepareCall.setInt(1, userId);
				} else {
					prepareCall = connection
							.prepareCall("{call get_do_fos_sales_to_from(?,?,?)}");
					prepareCall.setInt(1, userId);
					prepareCall.setString(2, to);
					prepareCall.setString(3, from);
				}
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("fos", rs.getInt(1) + "");
					map.put("nds", rs.getInt(2) + "");
					map.put("active_nds", rs.getInt(3) + "");
					map.put("installed", rs.getInt(4) + "");
					map.put("total", rs.getInt(5) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static HashMap<String, String> getLoanStatus(int userId) {
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call count_do_loan_statuses(?)}");
				prepareCall.setInt(1, userId);
				ResultSet rs = prepareCall.executeQuery(); 
				if (rs.next()) {
					map = new HashMap<>();
					map.put("maintained", rs.getInt(1) + "");
					map.put("defaulter", rs.getInt(2) + "");
					map.put("owned", rs.getInt(3) + "");
					map.put("late", rs.getInt(4) + "");
					map.put("cash", rs.getInt(5) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoTopFiveNds(
			int userId, String to, String from) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = null;
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection
							.prepareCall("{call get_do_top_five_nds(?)}");
					prepareCall.setInt(1, userId);
				} else {
					prepareCall = connection
							.prepareCall("{call get_do_top_five_nds_to_from(?,?,?)}");
					prepareCall.setInt(1, userId);
					prepareCall.setString(2, to);
					prepareCall.setString(3, from);
				}
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map = new HashMap<>();
					map.put("ndName", rs.getString(1) + "");
					map.put("installed", rs.getInt(2) + "");
					map.put("total", rs.getInt(3) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	}

	public static int countPendingCustomersDo(int districtId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_pending_customers_do(?)}");
			prepareCall.setInt(1, districtId);
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

	public static int countAcceptedCustomersDo(int districtId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_accepted_customers_do(?)}");
			prepareCall.setInt(1, districtId);
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

	public static int countVarifiedCustomersDo(int districtId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_varified_customers_do(?)}");
			prepareCall.setInt(1, districtId);
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

	public static int countCCVarifyCustomersDo(int districtId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_cc_varify_customers_do(?)}");
			prepareCall.setInt(1, districtId);
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

	public static HashMap<String, String> countAllStatusAndApplianceStatusDo(
			int districId) {
		HashMap<String, String> map = new HashMap<>();
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
			CallableStatement preparecall = connection
					.prepareCall("{CALL count_all_status_and_appliance_status_do(?)}");
			preparecall.setInt(1, districId);
			ResultSet resultSet = preparecall.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getInt("appliance_status")) {
				case 0:
					inactive += resultSet.getInt("count_appliance_status");
					break;
				case 1:
					active += resultSet.getInt("count_appliance_status");
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
					installed += resultSet.getInt("count_status");
					break;
				case 7:
					installedNoSignal += resultSet.getInt("count_status");
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
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;
	}

}
