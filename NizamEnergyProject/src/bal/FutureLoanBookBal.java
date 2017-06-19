package bal;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import connection.Connect;

public class FutureLoanBookBal {
	final static Logger logger = Logger.getLogger(FutureLoanBookBal.class);

	public static HashMap<String, String> countFutureLoanBookFilters(
			String toDate) {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_filters_toDate(?)}");

			prepareCall.setString(1, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1) + "");
				map.put("Late", resultSet.getInt(3) + "");
				map.put("Defaulter", resultSet.getInt(2) + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> futureLoanBooks() {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		Calendar calendar = Calendar.getInstance();
		int currentMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		Date today = new Date();

		DateFormat day = new SimpleDateFormat("dd");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat year = new SimpleDateFormat("YYYY");

		int currentday = Integer.parseInt(day.format(today));
		String months = month.format(today) + "";
		String years = year.format(today) + "";

		for (int i = 1; currentday <= currentMonthDays; i++) {
			String passDate = years + "-" + months + "-" + currentday;
			try (Connection connection = Connect.getConnection()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_super_admin_loan_book_filters_toDate(?)}");
				prepareCall.setString(1, passDate);

				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					map = new HashMap<>();
					map.put("Date", passDate + "");
					map.put("Maintained", resultSet.getInt(1) + "");
					map.put("Defaulter", resultSet.getInt(2) + "");
					map.put("Late", resultSet.getInt(3) + "");
					list.add(map);
				}

			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			currentday++;
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getFutureLoanBookDefaulter(
			int start, int range, String order, int column, String search,
			String toDate) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_defaulter_toDate(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			prepareCall.setString(6, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countFutureLoanBookDefaulter(String search, String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_defaulter_toDate(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setString(2, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getFutureLoanBookLate(
			int start, int range, String order, int column, String search,
			String toDate) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_late_toDate(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			prepareCall.setString(6, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countFutureLoanBookLate(String search, String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_late_toDate(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setString(2, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getFutureLoanBookMaintained(
			int start, int range, String order, int column, String search,
			String toDate) throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_maintained_toDate(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			prepareCall.setString(6, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countFutureLoanBookMaintained(String search, String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_maintained_toDate(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setString(2, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getFutureSuperAdminLoanBook(
			int start, int range, String order, int column, String search,
			String toDate) throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_toDate(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
			prepareCall.setString(6, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				map.put("doName", resultSet.getString("user_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int getCountFutureLoanBookSearchCount(String search,
			String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_toDate_by_search_count(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setString(2, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoFutureLoanBook(
			int start, int range, String order, int column, int doId,
			String search, String toDate) throws ServiceException, IOException,
			JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_toDate(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			prepareCall.setString(7, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");

				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int getDoFutureLoanBookSearchCount(int doId, String search,
			String toDate) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_do_loan_books_count_toDate(?,?,?)}");
			callst.setInt(1, doId);
			callst.setString(2, search);
			callst.setString(3, toDate);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoFutureLoanBookDefaulter(
			int start, int range, String order, int column, int doId,
			String search, String toDate) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_defaulter_toDate(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			prepareCall.setString(7, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countDoFutureLoanBookDefaulter(int doId, String search,
			String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_defaulter_toDate(?,?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			prepareCall.setString(3, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoFutureLoanBookLate(
			int start, int range, String order, int column, int doId,
			String search, String toDate) throws ServiceException, IOException,
			JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_late_toDate(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			prepareCall.setString(7, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countDoFutureLoanBookLate(int doId, String search,
			String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_late_toDate(?,?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			prepareCall.setString(3, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getDoFutureLoanBookMaintained(
			int start, int range, String order, int column, int doId,
			String search, String toDate) throws ServiceException, IOException,
			JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_maintained_toDate(?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
			prepareCall.setString(7, toDate);
			ResultSet resultSet = prepareCall.executeQuery();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("applianceId", resultSet.getInt("appliance_id") + "");
				map.put("customerName", resultSet.getString("customer_name"));
				map.put("loanId", resultSet.getInt("loan_id") + "");
				map.put("applianceName", resultSet.getString("appliance_name"));
				map.put("applianceNumber", resultSet.getString("imei_number"));
				map.put("cityName", resultSet.getString("city_name"));
				map.put("monthlyPay", NumberFormat.getNumberInstance(Locale.US)
						.format(Math.round(resultSet.getDouble("monthly_pay")))
						+ "");
				map.put("installmentScheme",
						resultSet.getInt("installment_scheme") + "");
				map.put("downPayment",
						NumberFormat.getNumberInstance(Locale.US)
								.format(Math.round(resultSet
										.getDouble("down_payment")))
								+ "");
				map.put("cummalativePaid", resultSet.getInt("cummalative_paid")
						+ "");
				map.put("statusGet", resultSet.getString("status_get"));
				map.put("cummalativeDue", resultSet.getInt("cummalative_due")
						+ "");
				map.put("appliancePrice",
						Math.round(resultSet.getDouble("appliance_price")) + "");
				map.put("totalPaid", NumberFormat.getNumberInstance(Locale.US)
						.format(resultSet.getInt("total_paid")) + "");
				map.put("remainingBalance",
						NumberFormat.getNumberInstance(Locale.US).format(
								Math.round(resultSet
										.getDouble("remaining_balance")))
								+ "");
				map.put("remaining_days", resultSet.getInt("remaining_days")
						+ "");
				map.put("foName", resultSet.getString("fo_name") + "");
				map.put("NdName", resultSet.getString("salesman_name") + "");
				if (resultSet.getInt("rating") == 0) {
					map.put("customer_rating", "N/A");
				} else {
					map.put("customer_rating", resultSet.getInt("rating")
							+ "  %");
				}
				maps.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return maps;
	}

	public static int countDoFutureLoanBookMaintained(int doId, String search,
			String toDate) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_maintained_toDate(?,?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			prepareCall.setString(3, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> countDoFutureLoanBookFilters(
			int userId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;

		Calendar calendar = Calendar.getInstance();
		int currentMonthDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		Date today = new Date();

		DateFormat day = new SimpleDateFormat("dd");
		DateFormat month = new SimpleDateFormat("MM");
		DateFormat year = new SimpleDateFormat("YYYY");

		int currentday = Integer.parseInt(day.format(today));
		String months = month.format(today) + "";
		String years = year.format(today) + "";

		for (int i = 1; currentday <= currentMonthDays; i++) {
			String passDate = years + "-" + months + "-" + currentday;
			try (Connection connection = Connect.getConnection()) {
				CallableStatement prepareCall = connection
						.prepareCall("{CALL count_do_loan_book_filters_toDate(?,?)}");
				prepareCall.setInt(1, userId);
				prepareCall.setString(2, passDate);
				ResultSet resultSet = prepareCall.executeQuery();
				while (resultSet.next()) {
					map = new HashMap<>();
					map.put("Date", passDate + "");
					map.put("Maintained", resultSet.getInt(1) + "");
					map.put("Late", resultSet.getInt(3) + "");
					map.put("Defaulter", resultSet.getInt(2) + "");
					list.add(map);
				}
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			currentday++;
		}
		return list;
	}

	public static HashMap<String, Integer> countDoFutureLoanBookFilters(
			int userId, String toDate) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_filters_toDate(?,?)}");
			prepareCall.setInt(1, userId);
			prepareCall.setString(2, toDate);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1));
				map.put("Late", resultSet.getInt(3));
				map.put("Defaulter", resultSet.getInt(2));
				map.put("Owned", resultSet.getInt(4));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}
}
