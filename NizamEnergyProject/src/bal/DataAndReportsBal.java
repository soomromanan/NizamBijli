package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class DataAndReportsBal {
	final static Logger logger = Logger.getLogger(DataAndReportsBal.class);

	public static ArrayList<HashMap<String, String>> getRecoveryReport(int DoId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL recovery_report(?)}");
			prepareCall.setInt(1, DoId);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerPhone", resultSet.getString("customer_phone"));
				map.put("installment",
						resultSet.getInt("installment_amount_month") + "");
				map.put("NdName", resultSet.getString("salesman_name"));
				map.put("NdPhone",
						resultSet.getString("salesman_primary_phone"));
				map.put("due", resultSet.getDate("due_date") + "");
				map.put("paid", resultSet.getDate("Paid_Date") + "");
				map.put("months", resultSet.getString("months"));
				map.put("nextdue", resultSet.getString("Second_due") + "");
				map.put("latepaid", resultSet.getString("paidDateLate") + "");
				map.put("FOName", resultSet.getString("fo_name") + "");
				list.add(map);
			}
			System.out.println("end getFoReport ");
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesfoDOs(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_for_dos(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesAndDefaulter(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_and_defaulter(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSalesAndDefaulter(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_sales_report_and_defaulter(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("salesman_name", resultSet.getString("salesman_name")
						+ "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRecoveryRateReport(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_recovery_rate_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("recovery", resultSet.getInt("recovery") + "");
				map.put("average_rating", resultSet.getInt("average_rating")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNDSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_sales_report(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_by_perf_from_to(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("total_nds", resultSet.getInt("total_nds") + "");
				map.put("active_nds", resultSet.getInt("active_nds") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSales(String from,
			String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_sales_report_by_perf_from_to(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getInt("sales") + "");
				map.put("fos", resultSet.getInt("fos") + "");
				map.put("active_nds", resultSet.getInt("active_nds") + "");
				map.put("last_sale", resultSet.getInt("last_sale") + "");
				map.put("average", resultSet.getInt("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomerRatingList() {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_rating()}");
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("rating", resultSet.getString("rating") + "");
				maps.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getCustomersList(
			String from, String to) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_weekly_customer_list(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("customerContact",
						resultSet.getString("customer_phone"));
				map.put("foContact", resultSet.getString("fo_priamary_phone"));
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("NdContact",
						resultSet.getString("salesman_primary_phone") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("insalled_date", resultSet.getString("insalled_date")
						+ "");
				map.put("consumerNumber", resultSet.getString("imei_number")
						+ "");
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static ArrayList<HashMap<String, String>> getCustomersRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_customer_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("customerName", resultSet.getString("customer_name")
						+ "");
				map.put("rating", resultSet.getInt("rating") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNizamDostRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL nizamdost_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL fo_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoRating() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL do_wise_rating()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("district", resultSet.getString("district_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("rating", resultSet.getInt("rating") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNdSalesByTop(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_nd_sales_report_from_to_by_limit(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getString("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesByTop(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_sales_report_from_to_by_limit(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("sales", resultSet.getString("sales") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getNdSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_nd(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("ndName", resultSet.getString("salesman_name") + "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_fo(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoSalesAverage(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_last_sales_average_do(?,?)}");
			prepareCall.setString(1, from);
			prepareCall.setString(2, to);
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("handover", resultSet.getString("handover") + "");
				map.put("average", resultSet.getDouble("average") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoWiseLoanApps(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = null;
			if (from.isEmpty() && to.isEmpty()) {
				prepareCall = connection
						.prepareCall("{CALL fo_wise_laon_apps()}");
			} else {
				prepareCall = connection
						.prepareCall("{CALL fo_wise_laon_apps_day_wise(?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
			}
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("pending", resultSet.getString("pending") + "");
				map.put("accepted", resultSet.getString("accepted") + "");
				map.put("varified", resultSet.getString("varified") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoWiseLoanApps(
			String from, String to) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = null;
			if (from.isEmpty() && to.isEmpty()) {
				prepareCall = connection
						.prepareCall("{CALL district_wise_laon_apps()}");
			} else {
				prepareCall = connection
						.prepareCall("{CALL district_wise_laon_apps_day_wise(?,?)}");
				prepareCall.setString(1, from);
				prepareCall.setString(2, to);
			}
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("pending", resultSet.getString("pending") + "");
				map.put("accepted", resultSet.getString("accepted") + "");
				map.put("varified", resultSet.getString("varified") + "");
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getCustomersLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_all_late_and_defaulters()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("duedate", resultSet.getString("due_date") + "");
				map.put("customerName", resultSet.getString("customer_name")
						+ "");
				map.put("customerNumber", resultSet.getString("customer_phone")
						+ "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("NdNumber",
						resultSet.getString("salesman_primary_phone") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("doNumber", resultSet.getString("primary_phone_number")
						+ "");
				map.put("foNumber", resultSet.getString("fo_priamary_phone")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("remaining_days", resultSet.getString("remaining_days")
						+ "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFosLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_fo_wise_late_defaulter()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("late", resultSet.getInt("late") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDosLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			ResultSet resultSet = null;
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_wise_late_defaulter()}");
			resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("doName", resultSet.getString("user_name") + "");
				map.put("district", resultSet.getString("district_name") + "");
				map.put("late", resultSet.getInt("late") + "");
				map.put("defaulter", resultSet.getInt("defaulter") + "");
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{CALL get_fo_wise_late_defaulter()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fo_name", resultSet.getString(1) + "");
					map.put("district_name", resultSet.getString(2) + "");
					map.put("late", resultSet.getInt(3) + "");
					map.put("defaulter", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getFoWiseLateDefaultersCount() {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_fo_wise_late_defaulter_count()}");
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

	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaultersFilter(
			int start, int length, int col, String order) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_fo_wise_late_defaulter_filter(?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);
			ResultSet rs = call.executeQuery();
			while (rs.next()) {

				HashMap<String, String> map = new HashMap<>();
				map.put("fo_name", rs.getString(1));
				map.put("district_name", rs.getString(2));
				map.put("late", rs.getInt(3) + "");
				map.put("defaulter", rs.getInt(4) + "");
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;

	}

	public static int getFoWiseLateDefaultersSearchCount(String value) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement call = con
					.prepareCall("{CALL get_fo_wise_late_defaulter_search_count(?)}");
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

	public static ArrayList<HashMap<String, String>> getFoWiseLateDefaulterSearchFilter(
			int start, int length, int col, String order, String value) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{call get_fo_wise_late_defaulter_search_filter(?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);
			call.setString(5, value);
			rs = call.executeQuery();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("fo_name", rs.getString(1));
				map.put("district_name", rs.getString(2));
				map.put("late", rs.getInt(3) + "");
				map.put("defaulter", rs.getInt(4) + "");
				list.add(map);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return list;

	}

	public static ArrayList<HashMap<String, String>> getDoWiseLateDefaulters() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{CALL get_do_wise_late_defaulter()}");
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("user_name", resultSet.getString(1) + "");
					map.put("district_name", resultSet.getString(2) + "");
					map.put("late", resultSet.getInt(3) + "");
					map.put("defaulter", resultSet.getInt(4) + "");
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getDoWiseLateDefaulters
}
