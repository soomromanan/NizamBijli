package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.mysql.jdbc.Connection;

import connection.Connect;

import org.apache.log4j.Logger;

public class CashSaleBAL {
	final static Logger logger = Logger.getLogger(CashSaleBAL.class);

	public static int insertCashCustomersPaymentToDO(HashMap<String, String> map) {
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `insert_cash_customers`(?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("City")));
			prepareCall.setString(2, map.get("customerName"));
			prepareCall.setString(3, map.get("CustomerCnic"));
			prepareCall.setString(4, map.get("customerPhone"));
			prepareCall.setString(5, map.get("customerAddress"));
			prepareCall.setInt(6, Integer.parseInt(map.get("FoId")));
			prepareCall.setInt(7, Integer.parseInt(map.get("NdId")));
			prepareCall.setInt(8, Integer.parseInt(map.get("appliancePrice")));
			prepareCall.setString(9, map.get("applianceName"));
			prepareCall.setString(10, map.get("consumerNo"));
			prepareCall.setString(11, map.get("gsmNumber"));
			prepareCall.setString(12, map.get("imeiNumber"));
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				id = rs.getInt("customer_id");
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return id;
	}

	public static int insertCashCustomersPaymentByMobileWallet(
			HashMap<String, String> map) {
		int id = 0;
		try (Connection con = Connect.getConnection()) {
			CallableStatement prepareCall = con
					.prepareCall("{Call `insert_cash_customers_payment_by_mobile_wallet`(?,?,?,?,?,?,?,?,?,?,?,?)}");
			prepareCall.setInt(1, Integer.parseInt(map.get("City")));
			prepareCall.setString(2, map.get("customerName"));
			prepareCall.setString(3, map.get("CustomerCnic"));
			prepareCall.setString(4, map.get("customerPhone"));
			prepareCall.setString(5, map.get("customerAddress"));
			prepareCall.setInt(6, Integer.parseInt(map.get("FoId")));
			prepareCall.setInt(7, Integer.parseInt(map.get("NdId")));
			prepareCall.setInt(8, Integer.parseInt(map.get("appliancePrice")));
			prepareCall.setString(9, map.get("applianceName"));
			prepareCall.setString(10, map.get("consumerNo"));
			prepareCall.setString(11, map.get("gsmNumber"));
			prepareCall.setString(12, map.get("imeiNumber"));
			ResultSet rs = prepareCall.executeQuery();
			if (rs.next()) {
				id = rs.getInt("customer_id");
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return id;
	}

}
