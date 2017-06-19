package bal;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import connection.Connect;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;

public class LoanBookBal {

	final static Logger logger = Logger.getLogger(LoanBookBal.class);

	public static HashMap<String, Integer> countDoLoanBookFilters(int userId) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_filters(?)}");
			prepareCall.setInt(1, userId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1));
				map.put("Late", resultSet.getInt(3));
				map.put("Defaulter", resultSet.getInt(2));
				map.put("Owned", resultSet.getInt(4));
				map.put("cash", resultSet.getInt(5));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanBookCash(
			int start, int range, String order, int column, int doId,
			String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_cash(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int countDoLoanBookCash(int doId, String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_cash(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public static ArrayList<HashMap<String, String>> getDoLoanBookOwned(
			int start, int range, String order, int column, int doId,
			String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_owned(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int countDoLoanBookOwned(int doId, String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_owned(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public static ArrayList<HashMap<String, String>> getDoLoanBookDefaulter(
			int start, int range, String order, int column, int doId,
			String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_defaulter(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int countDoLoanBookDefaulter(int doId, String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_defaulter(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public static ArrayList<HashMap<String, String>> getDoLoanBookLate(
			int start, int range, String order, int column, int doId,
			String search) throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_late(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int countDoLoanBookLate(int doId, String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_late(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public static ArrayList<HashMap<String, String>> getDoLoanBookMaintained(
			int start, int range, String order, int column, int doId,
			String search) throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book_maintained(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int countDoLoanBookMaintained(int doId, String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {

			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_do_loan_book_maintained(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
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

	public static ArrayList<HashMap<String, String>> getDoLoanBook(int start,
			int range, String order, int column, int doId, String search)
			throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_do_loan_book(?,?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setInt(5, doId);
			prepareCall.setString(6, search);
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

	public static int getDoLoanBookSearchCount(int doId, String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement callst = con
					.prepareCall("{CALL get_do_loan_books_count(?,?)}");
			callst.setInt(1, doId);
			callst.setString(2, search);
			ResultSet rs = callst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookCash(int start,
			int range, String order, int column, String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_cash(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static int countLoanBookCash(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_cash(?)}");
			prepareCall.setString(1, search);
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

	public static ArrayList<HashMap<String, String>> getLoanBookOwned(
			int start, int range, String order, int column, String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_owned(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static int countLoanBookOwned(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_owned(?)}");
			prepareCall.setString(1, search);
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

	public static ArrayList<HashMap<String, String>> getLoanBookDefaulter(
			int start, int range, String order, int column, String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_defaulter(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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
		}
		return maps;
	}

	public static int countLoanBookDefaulter(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_defaulter(?)}");
			prepareCall.setString(1, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getLoanBookLate(int start,
			int range, String order, int column, String search) {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_late(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static int countLoanBookLate(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_late(?)}");
			prepareCall.setString(1, search);
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

	public static ArrayList<HashMap<String, String>> getLoanBookMaintained(
			int start, int range, String order, int column, String search)
			throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_maintained(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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

	public static int countLoanBookMaintained(String search) {
		int count = 0;
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_maintained(?)}");
			prepareCall.setString(1, search);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public static HashMap<String, String> countLoanBookFilters() {
		HashMap<String, String> map = new HashMap<>();
		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_super_admin_loan_book_filters()}");

			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				map.put("Maintained", resultSet.getInt(1) + "");
				map.put("Late", resultSet.getInt(3) + "");
				map.put("Defaulter", resultSet.getInt(2) + "");
				map.put("Owned", resultSet.getInt(4) + "");
				map.put("cash", resultSet.getInt(5) + "");
			}

		} catch (SQLException e) {
			logger.error("", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getSuperAdminLoanBook(
			int start, int range, String order, int column, String search)
			throws ServiceException, IOException, JSONException {
		ArrayList<HashMap<String, String>> maps = new ArrayList<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book(?,?,?,?,?)}");
			prepareCall.setInt(1, start);
			prepareCall.setInt(2, range);
			prepareCall.setString(3, order);
			prepareCall.setInt(4, column);
			prepareCall.setString(5, search);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maps;
	}

	public static int getCountLoanBookSearchCount(String search) {

		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_super_admin_loan_book_by_search_count(?)}");
			prepareCall.setString(1, search);
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

	public static ArrayList<MonthlyWisePayment> loanBookByApplianceId(
			int applianceId, String applianceName) {
		ResultSet rs = null;
		CallableStatement cstmt;
		ArrayList<MonthlyWisePayment> customerList = new ArrayList<MonthlyWisePayment>();
		Date due_date, paid_date;
		String trasnction_id;
		String paid_amount, appliance_imei;
		int activated_days, remaining_days;
		try (Connection con = Connect.getConnection()) {
			String query = "{CALL get_monthly_wise_payment(?)}";
			cstmt = con.prepareCall(query);
			cstmt.setInt(1, applianceId);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				due_date = rs.getDate(1);
				paid_amount = "" + rs.getInt(2);
				paid_date = rs.getDate(3);
				activated_days = rs.getInt(5);
				trasnction_id = "" + rs.getInt(6);
				remaining_days = rs.getInt(7);
				appliance_imei = rs.getString(8);
				MonthlyWisePayment bean = new MonthlyWisePayment();
				bean.setDue_date(due_date);
				bean.setPaid_amount(paid_amount);
				bean.setPaid_date(paid_date);
				bean.setDays_activated(activated_days);
				bean.setTrasnction_id(trasnction_id);
				bean.setRemaining_days(remaining_days);
				bean.setAppliance_imei(rs.getString("consumer_number"));
				customerList.add(bean);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return customerList;
	}

	public static CustomerLoanBean getApplianceDetailsForLoanAccount(
			int applianceId) {
		CustomerLoanBean bean = null;
		int customerId, loanId, salesmanId, total_installments, grace_period, applianceStatus;
		double appliancePrice, remaining_balance, total_amount, monthly_amount, down_payment;
		String CustomerName, CustomerCnic, applianceName, salesmanName, imeiNumber, date, terminate_date, customerPhone, salemanPhone;
		int other_loan;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call get_appliance_loan_info(?)}");
				prepareCall.setInt(1, applianceId);
				ResultSet rs = prepareCall.executeQuery();
				if (rs.next()) {
					CustomerName = rs.getString(1);
					total_installments = rs.getInt(2);
					date = rs.getString(3);
					grace_period = rs.getInt(4);
					down_payment = rs.getDouble(5);
					remaining_balance = rs.getDouble(6);
					terminate_date = rs.getString(7);
					total_amount = rs.getDouble(8);
					monthly_amount = rs.getDouble(9);
					customerId = rs.getInt(10);
					customerPhone = rs.getString(11);
					salemanPhone = rs.getString(12);
					applianceName = rs.getString(13);
					imeiNumber = rs.getString(14);
					appliancePrice = rs.getDouble(15);
					applianceStatus = rs.getInt(16);
					loanId = rs.getInt(17);
					salesmanId = rs.getInt(18);
					salesmanName = rs.getString(19);
					String status = rs.getString("loanStatus");
					other_loan = rs.getInt("other_loan");
					int appliance_id = rs.getInt(21);
					bean = new CustomerLoanBean();
					bean.setOther_loan(other_loan);
					bean.setCustomerId(customerId);
					bean.setCustomerName(CustomerName);
					bean.setTotal_installments(total_installments);
					bean.setCreated_on(date);
					bean.setGrace_period(grace_period);
					bean.setDownPayment(down_payment);
					bean.setRemaining_balanse(remaining_balance);
					bean.setTerminate_date(terminate_date);
					bean.setTotal_amount(total_amount);
					bean.setMonthly_amount(monthly_amount);
					bean.setPhoneNo(customerPhone);
					bean.setSalemanPhone(salemanPhone);
					bean.setApplianceName(applianceName);
					bean.setGsmNumber(rs.getString("appliance_GSMno"));
					bean.setAppliancePrice(appliancePrice);
					bean.setApplianceStatus(applianceStatus);
					bean.setLoanId(loanId);
					bean.setSalesmanId(salesmanId);
					bean.setSalesmanName(salesmanName);
					bean.setLoanStatus(status);
					bean.setAppliance_id(appliance_id);
					bean.setFieldOfficer(rs.getString("fo_name"));
					bean.setUserName(rs.getString("user_name"));
					bean.setImeiNumber(imeiNumber);
					bean.setCnicNo(rs.getString("cs.customer_cnic"));
					bean.setFoid(rs.getInt("fo_id"));
					bean.setCustomer_Rating(rs.getInt("rating"));
					bean.setDoid(rs.getInt("user_id"));
				}
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return bean;
	}

}
