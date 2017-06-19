package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import bean.FinanceBean;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class PaymentsBal {
	final static Logger logger = Logger.getLogger(PaymentsBal.class);

	public static int getPaymentStatusCount(String dateTo, String dateFrom,
			int paymentStatusId) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_payment_status_using_date_count(?,?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
			call.setInt(3, paymentStatusId);
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

	public static ArrayList<FinanceBean> getPaymentsStatus_fliter_Using_Date(
			String to, String from, int start, int length, int col,
			String order, int payment_Status) {
		ArrayList<FinanceBean> list = new ArrayList<FinanceBean>();

		String customer_name, appliance_imei, bank_name;
		double installment_amount;

		String due_Date, paymentStatus, app_name;
		int installments = 0, downPayment = 0;

		String transaction_id;
		FinanceBean bean = null;
		ResultSet rs = null;
		CallableStatement call = null;

		double installPayments, downPayments;
		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{CALL get_PaymentsStatus_fliter_using_date(?,?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);
			call.setString(5, to);
			call.setString(6, from);
			call.setInt(7, payment_Status);

			rs = call.executeQuery();
			while (rs.next()) {
				customer_name = rs.getString(1);
				appliance_imei = rs.getString(2);
				installment_amount = rs.getDouble(3);
				transaction_id = rs.getString(4);
				due_Date = rs.getString(5);
				bank_name = rs.getString(6);
				paymentStatus = rs.getString("payment_status");
				app_name = rs.getString("appliance_name");
				if (bank_name == "" || bank_name == null
						|| bank_name == "undefined") {
					bank_name = "N/A";
				}
				if (transaction_id == "" || transaction_id == null
						|| transaction_id == "undefined") {
					transaction_id = "N/A";
				}
				if (appliance_imei == "" || appliance_imei == null
						|| appliance_imei == "undefined") {
					appliance_imei = "N/A";
				}

				bean = new FinanceBean();
				bean.setBank_name(bank_name);
				bean.setCustomer_name(customer_name);
				bean.setDate(due_Date);
				bean.setTransaction_id(transaction_id);
				bean.setInstallment_paid(installment_amount);
				bean.setImei_number(appliance_imei);
				bean.setPayment_status(paymentStatus);
				bean.setTotalInstallmentPaymentsCount(installments);
				bean.setTotalDownPaymentsCount(downPayment);
				bean.setTotal_installement_payments(rs
						.getDouble("total_installement_payments"));
				bean.setTotal_amount(rs.getDouble("total_amount"));
				bean.setTotal_down_pament(rs.getDouble("total_down_pament"));
				bean.setDown_payment_count(rs.getInt("total_downpayment_count"));
				bean.setFo_name(rs.getString("fo_name"));
				bean.setNd_name(rs.getString("salesman_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setApp_name(rs.getString("appliance_name"));
				int Down_paymentstatus = rs.getInt("is_first_installment");
				bean.setNewInstallment(rs.getInt("new_installement_payments"));
				bean.setNew_Down_payment_count(rs
						.getInt("new_downpayment_count"));

				if (app_name.equals("P-60") || app_name.equals("60 W")
						|| app_name.equals("7 W")) {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(200);
						bean.setNd_commission_per_device(1000);
					} else {
						bean.setFo_commission_per_device((float) rs
								.getDouble(3) * ((float) .5 / 100));
						bean.setNd_commission_per_device((float) rs
								.getDouble(3) * ((float) 7 / 100));
					}

				} else {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(400);
						bean.setNd_commission_per_device(1200);
					}
				}
				// bean.setDue_date(due_Date);
				list.add(bean);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}

	public static int getPaymentStatusSearchCount(String dateTo,
			String dateFrom, String value, int installments) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_paymentStatus_using_search_count(?,?,?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
			call.setString(3, value);
			call.setInt(4, installments);
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

	public static ArrayList<FinanceBean> getPaymentsStatus_Search_fliter(
			String to, String from, int start, int length, int col,
			String order, String value, int installments) {
		ArrayList<FinanceBean> list = new ArrayList<FinanceBean>();

		String customer_name, appliance_imei, bank_name, app_name;
		double installment_amount;
		String due_Date, paymentStatus;

		String transaction_id;
		FinanceBean bean = null;
		// Statement s = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{call get_PaymentsStatus_fliter_by_search(?,?,?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, to);
			call.setString(4, from);
			call.setInt(5, col);
			call.setString(6, order);
			call.setString(7, value);
			call.setInt(8, installments);

			rs = call.executeQuery();
			while (rs.next()) {

				customer_name = rs.getString(1);

				appliance_imei = rs.getString(2);

				installment_amount = rs.getDouble(3);
				transaction_id = rs.getString(4);

				due_Date = rs.getString(5);
				bank_name = rs.getString(6);
				paymentStatus = rs.getString("payment_status");
				app_name = rs.getString("appliance_name");
				if (bank_name == "" || bank_name == null
						|| bank_name == "undefined") {
					bank_name = "N/A";
				}
				if (transaction_id == "" || transaction_id == null
						|| transaction_id == "undefined") {
					transaction_id = "N/A";
				}
				if (appliance_imei == "" || appliance_imei == null
						|| appliance_imei == "undefined") {
					appliance_imei = "N/A";
				}

				bean = new FinanceBean();
				bean.setBank_name(bank_name);
				bean.setCustomer_name(customer_name);
				bean.setDate(due_Date);
				bean.setTransaction_id(transaction_id);
				bean.setInstallment_paid(installment_amount);
				bean.setImei_number(appliance_imei);
				bean.setPayment_status(paymentStatus);
				bean.setTotal_installement_payments(rs
						.getDouble("total_installement_payments"));
				bean.setTotal_amount(rs.getDouble("total_amount"));
				bean.setTotal_down_pament(rs.getDouble("total_down_pament"));
				bean.setDown_payment_count(rs.getInt("total_downpayment_count"));
				bean.setFo_name(rs.getString("fo_name"));
				bean.setNd_name(rs.getString("salesman_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setApp_name(rs.getString("appliance_name"));
				int Down_paymentstatus = rs.getInt("is_first_installment");
				bean.setNewInstallment(rs.getInt("new_installement_payments"));
				bean.setNew_Down_payment_count(rs
						.getInt("new_downpayment_count"));

				if (app_name.equals("P-60") || app_name.equals("60 W")
						|| app_name.equals("7 W")) {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(200);
						bean.setNd_commission_per_device(1000);
					} else {
						bean.setFo_commission_per_device((float) rs
								.getDouble(3) * ((float) .5 / 100));
						bean.setNd_commission_per_device((float) rs
								.getDouble(3) * ((float) 7 / 100));
					}
				} else {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(400);
						bean.setNd_commission_per_device(1200);
					}
				}

				// bean.setDue_date(due_Date);
				list.add(bean);

			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}

	public static int getPaymentCount(String dateTo, String dateFrom) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_payment_using_date_count(?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
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

	public static ArrayList<FinanceBean> getPayments_fliter(String to,
			String from, int start, int length, int col, String order) {
		ArrayList<FinanceBean> list = new ArrayList<FinanceBean>();
		String customer_name, appliance_imei, bank_name, app_name;
		double installment_amount;

		String due_Date;
		int newdown, newinstallment = 0;
		String transaction_id, paymentStatus;
		FinanceBean bean = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {

			call = con
					.prepareCall("{call get_Payments_fliter_using_date(?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setInt(3, col);
			call.setString(4, order);
			call.setString(5, to);
			call.setString(6, from);

			rs = call.executeQuery();
			while (rs.next()) {
				customer_name = rs.getString(1);
				appliance_imei = rs.getString(2);
				installment_amount = rs.getDouble(3);
				transaction_id = rs.getString(4);
				due_Date = rs.getString(5);
				bank_name = rs.getString(6);
				due_Date = rs.getString(5);
				bank_name = rs.getString(6);
				newdown = rs.getInt("new_downpayment_count");
				newinstallment = rs.getInt("new_installement_payments");
				app_name = rs.getString("appliance_name");
				if (bank_name == "" || bank_name == null
						|| bank_name == "undefined") {
					bank_name = "N/A";
				}
				if (transaction_id == "" || transaction_id == null
						|| transaction_id == "undefined") {
					transaction_id = "N/A";
				}
				if (appliance_imei == "" || appliance_imei == null
						|| appliance_imei == "undefined") {
					appliance_imei = "N/A";
				}

				paymentStatus = rs.getString("payment_status");
				bean = new FinanceBean();
				bean.setBank_name(bank_name);
				bean.setCustomer_name(customer_name);
				bean.setDate(due_Date);
				bean.setTransaction_id(transaction_id);
				bean.setInstallment_paid(installment_amount);
				bean.setImei_number(appliance_imei);
				bean.setTotal_installement_payments(rs
						.getDouble("total_installement_payments"));
				bean.setTotal_amount(rs.getDouble("total_amount"));
				bean.setTotal_down_pament(rs.getDouble("total_down_pament"));
				bean.setDown_payment_count(rs.getInt("total_downpayment_count"));
				bean.setFo_name(rs.getString("fo_name"));
				bean.setNd_name(rs.getString("salesman_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setPayment_status(paymentStatus);
				bean.setApp_name(rs.getString("appliance_name"));
				bean.setNewInstallment(rs.getInt("new_installement_payments"));
				bean.setNew_Down_payment_count(rs
						.getInt("new_downpayment_count"));
				int Down_paymentstatus = rs.getInt("is_first_installment");

				if (app_name.equals("P-60") || app_name.equals("60 W")
						|| app_name.equals("7 W")) {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(200);
						bean.setNd_commission_per_device(1000);
					} else {
						bean.setFo_commission_per_device((float) rs
								.getDouble(3) * ((float) .5 / 100));
						bean.setNd_commission_per_device((float) rs
								.getDouble(3) * ((float) 7 / 100));
					}
				} else {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(400);
						bean.setNd_commission_per_device(1200);
					}

				}

				// bean.setDue_date(due_Date);
				list.add(bean);

			}

		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}

	public static int getPaymentSearchCount(String dateTo, String dateFrom,
			String value) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con.prepareCall("{CALL get_payment_search_count(?,?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
			call.setString(3, value);
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

	public static ArrayList<FinanceBean> getPayments_Search_fliter(String to,
			String from, int start, int length, int col, String order,
			String value) {
		ArrayList<FinanceBean> list = new ArrayList<FinanceBean>();
		String customer_name, appliance_imei, bank_name;
		double installment_amount;
		String due_Date, consumer_number, app_name;
		String transaction_id, paymentStatus;
		FinanceBean bean = null;
		ResultSet rs = null;
		CallableStatement call = null;
		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{call get_Payments_fliter_by_search(?,?,?,?,?,?,?)}");
			call.setInt(1, start);
			call.setInt(2, length);
			call.setString(3, to);
			call.setString(4, from);
			call.setInt(5, col);
			call.setString(6, order);
			call.setString(7, value);

			rs = call.executeQuery();
			while (rs.next()) {

				customer_name = rs.getString(1);

				appliance_imei = rs.getString(2);

				installment_amount = rs.getDouble(3);
				transaction_id = rs.getString(4);

				due_Date = rs.getString(5);
				bank_name = rs.getString(6);
				// consumer_number=rs.getString("consumer_number");
				app_name = rs.getString("appliance_name");
				if (bank_name == "" || bank_name == null
						|| bank_name == "undefined") {
					bank_name = "N/A";
				}
				if (transaction_id == "" || transaction_id == null
						|| transaction_id == "undefined") {
					transaction_id = "N/A";
				}
				if (appliance_imei == "" || appliance_imei == null
						|| appliance_imei == "undefined") {
					appliance_imei = "N/A";
				}
				paymentStatus = rs.getString("payment_status");
				bean = new FinanceBean();
				bean.setBank_name(bank_name);
				bean.setCustomer_name(customer_name);
				bean.setDate(due_Date);
				bean.setTransaction_id(transaction_id);
				bean.setInstallment_paid(installment_amount);
				bean.setImei_number(appliance_imei);
				bean.setTotal_installement_payments(rs
						.getDouble("total_installement_payments"));
				bean.setTotal_amount(rs.getDouble("total_amount"));
				bean.setTotal_down_pament(rs.getDouble("total_down_pament"));
				bean.setDown_payment_count(rs.getInt("total_downpayment_count"));
				bean.setFo_name(rs.getString("fo_name"));
				bean.setNd_name(rs.getString("salesman_name"));
				bean.setDistrict_name(rs.getString("district_name"));
				bean.setApp_name(rs.getString("appliance_name"));
				int Down_paymentstatus = rs.getInt("is_first_installment");
				bean.setNewInstallment(rs.getInt("new_installement_payments"));
				bean.setNew_Down_payment_count(rs
						.getInt("new_downpayment_count"));
				bean.setPayment_status(paymentStatus);

				if (app_name.equals("P-60") || app_name.equals("60 W")
						|| app_name.equals("7 W")) {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(200);
						bean.setNd_commission_per_device(1000);
					} else {
						bean.setFo_commission_per_device((float) rs
								.getDouble(3) * ((float) .5 / 100));
						bean.setNd_commission_per_device((float) rs
								.getDouble(3) * ((float) 7 / 100));
					}

				} else {

					if (Down_paymentstatus == 1) {
						bean.setFo_commission_per_device(400);
						bean.setNd_commission_per_device(1200);
					}
				}

				// bean.setDue_date(due_Date);
				list.add(bean);

			}
		} catch (SQLException ex) {
			logger.error("", ex);
			ex.printStackTrace();
		}

		return list;

	}

	public static int getDownPaymentCount(String dateTo, String dateFrom) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_downPayment_using_date_count(?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
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

	public static int getInstallmentPaymentCount(String dateTo, String dateFrom) {
		int count = 0;
		CallableStatement call = null;

		ResultSet rs;

		try (Connection con = Connect.getConnection()) {
			call = con
					.prepareCall("{CALL get_installments_payment_using_date_count(?,?)}");
			call.setString(1, dateTo);
			call.setString(2, dateFrom);
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
}
