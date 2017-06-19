package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class LoanRequestBal {
	final static Logger logger = Logger.getLogger(LoanRequestBal.class);

	public static int getUnseenRequests() {
		int countEligibility = 0;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call count_unseen_eligibility_request()}");
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return countEligibility;
	} // end of getting new Loan Request

	public static int getUnseenBookingApps() {
		int countEligibility = 0;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call count_unseen_booking_apps()}");
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error("", ex);
		}
		return countEligibility;
	} // end of getting new Booking Loan

	public static int getUnseenRequestsByDistrict(int districtId) {
		int countEligibility = 0;
		CallableStatement prepareCall = null;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				prepareCall = con
						.prepareCall("{call count_unseen_eligibility_request_by_district(?)}");
				prepareCall.setInt(1, districtId);
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
				prepareCall.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return countEligibility;
	} // end of getting unseen notifications of Loan Request

	public static int getPreBookingsByDistrict(int districtId) {
		int countEligibility = 0;
		try (Connection con = Connect.getConnection()) {
			if (con != null) {
				CallableStatement prepareCall = con
						.prepareCall("{call count_unseen_bookings_by_district(?)}");
				prepareCall.setInt(1, districtId);
				ResultSet resultSet = prepareCall.executeQuery();
				resultSet.next();
				countEligibility = resultSet.getInt(1);
				prepareCall.close();
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return countEligibility;
	}

	public static int acceptLoanRequestBySuperadmin(int applianceId) {
		int status = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{call accept_loan_request(?)}");
			prepareCall.setInt(1, applianceId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				status = rs.getInt(1);
			}
		} catch (Exception ex) {
			logger.error("", ex);
		}

		return status;
	}

	public static int rejecttLoanRequestBySuperadmin(int customerId,
			int applianceId, String text) {
		int row = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{call update_rejectection_of_appliance(?,?,?)}");
			prepareCall.setInt(1, customerId);
			prepareCall.setInt(2, applianceId);
			prepareCall.setString(3, text);
			row = prepareCall.executeUpdate();
			if (row > 0) {
				System.out.println("Data has been Updated");
			} else {
				System.out.println("Data has not been Updated");
			}
		} catch (Exception ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}
		return row;
	} // end of rejection

	public static int getLoanRequestCount(String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_count`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Loan Requests Count

	public static int getBookingsCount(String search) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_prepayment_list_search_count`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Loan Requests Count

	public static ArrayList<HashMap<String, String>> getLoanRequest(int str,
			int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequest(int str,
			int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static int getDoLoanRequestCount(String search, int doId) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_count_do`(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setInt(2, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	} // end of getting all customers form Loan Requests Count

	public static HashMap<String, Integer> countDoLoanRequestFilters(int doId) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_loan_application`(?)}");
			prepareCall.setInt(1, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
				map.put("cc_varified", rs.getInt(4));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countSALoanRequestFilters() {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_sa_loan_application`()}");
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
				map.put("ccVerification", rs.getInt(4));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getPrePayments(int str,
			int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_Pre_PaymentData`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);

			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getPrePaymentsDO(int str,
			int rng, int col, String orde, String search, int userid) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_Pre_PaymentDataDO`(?, ?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, userid);

			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static int getPrePaymentCountDO(String search, int doId) {
		int count = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_prepayment_list_search_count_do`(?,?)}");
			prepareCall.setString(1, search);
			prepareCall.setInt(2, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				count = rs.getInt("counts");
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return count;
	}

	public static HashMap<String, Integer> countSAPrePaymentsFiltersWithSearch(
			String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_sa_pre_payment_search`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("ccVerification", rs.getInt(1));
				map.put("pending", rs.getInt(2));
				map.put("accepted", rs.getInt(3));
				map.put("varified", rs.getInt(4));
				map.put("booked", rs.getInt(5));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countPrePaymentFilters() {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_pre_payment_application`()}");
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("ccVerification", rs.getInt(1));
				map.put("pending", rs.getInt(2));
				map.put("accepted", rs.getInt(3));
				map.put("varified", rs.getInt(4));
				map.put("booked", rs.getInt(5));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countDoPrePaymentsFilters(int doId) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pre_payments_application`(?)}");
			prepareCall.setInt(1, doId);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("ccVerification", rs.getInt(1));
				map.put("pending", rs.getInt(2));
				map.put("accepted", rs.getInt(3));
				map.put("varified", rs.getInt(4));
				map.put("booked", rs.getInt(5));
				System.err.println("asd" + map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countDoPrePaymentsFiltersWithSearch(
			int doId, String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_pre_payments_application_search`(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("cc_varified", rs.getInt(1));
				map.put("pending", rs.getInt(2));
				map.put("accepted", rs.getInt(3));
				map.put("varified", rs.getInt(4));
				map.put("booked", rs.getInt(5));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countSALoanRequestFiltersWithSearch(
			String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_sa_loan_application_search`(?)}");
			prepareCall.setString(1, search);
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
				map.put("ccVerification", rs.getInt(4));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static HashMap<String, Integer> countDoLoanRequestFiltersWithSearch(
			int doId, String search) {
		HashMap<String, Integer> map = new HashMap<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `count_do_loan_application_search`(?,?)}");
			prepareCall.setInt(1, doId);
			prepareCall.setString(2, search);
			ResultSet rs = prepareCall.executeQuery();

			if (rs.next()) {
				map.put("pending", rs.getInt(1));
				map.put("accepted", rs.getInt(2));
				map.put("varified", rs.getInt(3));
				map.put("cc_varified", rs.getInt(4));
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	public static ArrayList<HashMap<String, String>> getDoLoanRequestPending(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_pending`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestPending(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_pending`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequestAccepted(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_accepted`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestAccepted(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_accepted`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequestVarified(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_do_varified`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getSALoanRequestVarified(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_varified`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static String roundOffTo2DecPlaces(double val) {
		return String.format("%.2f", val);
	}

	public static ArrayList<HashMap<String, String>> getPPLoanRequestPending(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payment_search_sa_pending`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSAPrePaymentAccepted(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payment_search_sa_accepted`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSAPrePaymentVarified(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payment_search_sa_varified`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSAPrePaymentCCVerification(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payment_search_sa_ccVerification`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSAPrePaymentBooked(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payment_search_sa_booked`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoPrePaymentsPending(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payments_search_do_pending`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoPrePaymentsAccepted(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payments_search_do_accepted`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();

				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);

			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoPrePaymentstVarified(
			int str, int rng, int col, String orde, String search, int doId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payments_search_do_varified`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, doId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoPrePaymentsCCVerification(
			int str, int rng, int col, String orde, String search, int DoId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payments_search_do_ccVerification`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, DoId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getDoPrePaymentsbooked(
			int str, int rng, int col, String orde, String search, int DoId) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_pre_payments_search_do_booked`(?, ?, ?, ?, ?,?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			prepareCall.setInt(6, DoId);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;

			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<HashMap<String, String>> getSALoanRequestCCVerification(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_ccVerification`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;
			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static ArrayList<HashMap<String, String>> getDoLoanRequestCCVerification(
			int str, int rng, int col, String orde, String search) {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `get_request_list_search_sa_ccVerification`(?, ?, ?, ?, ?)}");
			prepareCall.setInt(1, str);
			prepareCall.setInt(2, rng);
			prepareCall.setString(3, orde);
			prepareCall.setInt(4, col);
			prepareCall.setString(5, search);
			ResultSet rs = prepareCall.executeQuery();
			HashMap<String, String> map = null;
			ResultSetMetaData metaData = rs.getMetaData();
			String arg[] = new String[metaData.getColumnCount()];
			for (int a = 0; a < arg.length; a++) {
				arg[a] = metaData.getColumnLabel(a + 1);
			}
			while (rs.next()) {
				map = new HashMap<>();
				for (String string : arg) {
					map.put(string, rs.getString(string));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return list;
	} // end of getting all customers form Loan Requests

	public static int countPendingCustomers() {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_pending_customers()}");
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

	public static int countAcceptedCustomers() {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_accepted_customers()}");
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

	public static int countVarifiedCustomers() {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_varified_customers()}");
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

	public static int countCCVarifyCustomers() {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_cc_varify_customers()}");
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

}
