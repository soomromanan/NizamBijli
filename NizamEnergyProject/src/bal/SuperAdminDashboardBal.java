package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import connection.Connect;

public class SuperAdminDashboardBal {

	final static Logger logger = Logger.getLogger(SuperAdminDashboardBal.class);

	public static HashMap<String, String> getRecoveryRateBeforeDueDate() {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_recovery_rate_before_due_date()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map.put("amount", Math.round(rs.getDouble(1)) + "");
					map.put("recoveryRate", rs.getDouble(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getRecoveryRateAfterDueDate() {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_recovery_rate_after_due_date()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map.put("defaulters", rs.getInt(1) + "");
					map.put("parTen", Math.random() + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, String> getTotalOutstandingLoans() {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_outstanding_payment()}");
				ResultSet rs = prepareCall.executeQuery();
				while (rs.next()) {
					map.put("amount", rs.getInt(1) + "");
					map.put("portfolio", rs.getInt(2) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getTopFiveNDs() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_five_nds()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("nd_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end

	public static ArrayList<HashMap<String, String>> getTopFiveFos() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call get_top_fos()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fo_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		} finally {
		}
		return list;
	} // end

	public static ArrayList<HashMap<String, String>> getTopFiveDos() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con.prepareCall("{call `get_top_five_dos`()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("do_name", resultSet.getString(1) + "");
					map.put("district", resultSet.getString(2) + "");
					map.put("current_month_sale", resultSet.getInt(3) + "");
					map.put("total_sale", resultSet.getInt(4) + "");
					map.put("last_sale", resultSet.getInt(5) + "");
					map.put("average",
							roundOffTo2DecPlaces(resultSet.getDouble(6)) + "");
					map.put("district_wise_rating", resultSet.getInt(7) + "");
					map.put("recovery",
							((resultSet.getInt(9) - resultSet.getInt(8)) * 100)
									/ resultSet.getInt(9) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	} // end

	public static HashMap<String, String> totalPortfolioHealth() {
		HashMap<String, String> map = null;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call total_portfolio_health()}");
				ResultSet resultSet = prepareCall.executeQuery();
				if (resultSet.next()) {
					map = new HashMap<>();
					map.put("BeforeTen",
							roundOffTo2DecPlaces(resultSet.getDouble(1)) + "");
					map.put("BeforeFive",
							roundOffTo2DecPlaces(resultSet.getDouble(2)) + "");
					map.put("BeforeDueDate",
							roundOffTo2DecPlaces(resultSet.getDouble(3)) + "");
					map.put("dueDate",
							roundOffTo2DecPlaces(resultSet.getDouble(4)) + "");
					map.put("MinusOneToFive",
							roundOffTo2DecPlaces(resultSet.getDouble(5)) + "");
					map.put("MinusSixToTen",
							roundOffTo2DecPlaces(resultSet.getDouble(6)) + "");
					map.put("AfterMinusTen",
							roundOffTo2DecPlaces(resultSet.getDouble(7)) + "");
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> monthlyPortfolio() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call current_month_recovery_breakup()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					map = new HashMap<>();
					map.put("district", resultSet.getString(1) + "");
					map.put("sales", resultSet.getInt(2) + "");
					map.put("portfolio",
							roundOffTo2DecPlaces(resultSet.getDouble(3)) + "");
					map.put("recovered",
							roundOffTo2DecPlaces(resultSet.getDouble(4)) + "");
					map.put("expected",
							roundOffTo2DecPlaces(resultSet.getDouble(5)) + "");
					map.put("late",
							roundOffTo2DecPlaces(resultSet.getDouble(6)) + "");
					map.put("total",
							roundOffTo2DecPlaces(resultSet.getDouble(7)) + "");
					map.put("previousRecoveryUnpaid", resultSet.getInt(8) + "");
					map.put("previousRecoveryPaid", resultSet.getInt(9) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;

	}

	public static String roundOffTo2DecPlaces(double val) {
		return String.format("%.2f", val);
	}

}
