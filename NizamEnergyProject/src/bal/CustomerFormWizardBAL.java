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

public class CustomerFormWizardBAL {
	final static Logger logger = Logger.getLogger(CustomerFormWizardBAL.class);

	private HashMap<String, Object> map;
	private HashMap<String, String> metaDataMap;

	public CustomerFormWizardBAL() {
		map = new HashMap<>();
	}

	public HashMap<String, Object> getCustomerForm(int customerId) {

		metaDataMap = new HashMap<>();

		try (Connection connection = Connect.getConnection()) {
			CallableStatement getFormWizardPrepareCall = connection
					.prepareCall("{CALL get_customer_form_wizard_step(?)}");
			getFormWizardPrepareCall.setInt(1, customerId);
			ResultSet getFormWizardResultSet = getFormWizardPrepareCall
					.executeQuery();
			if (getFormWizardResultSet.next()) {
				metaDataMap.put("customerId",
						getFormWizardResultSet.getInt("customer_id") + "");
				metaDataMap.put("formWizard",
						getFormWizardResultSet.getInt("form_wizard_step") + "");
				metaDataMap.put("vle", getFormWizardResultSet.getInt("vle_id")
						+ "");
				metaDataMap.put("district", getFormWizardResultSet.getInt("district_id")
						+ "");
				metaDataMap.put("cityId", getFormWizardResultSet.getInt("customer_city")
						+ "");

			}
			map.put("metaData", metaDataMap);

			CallableStatement getCustomerPrepareCall;
			ResultSet getCustomerResultSet;
			ResultSetMetaData metaData;
			String columns[];

			switch (Integer.parseInt(metaDataMap.get("formWizard"))) {
			case 5:
				getAppliances();
			case 4:
				getGuarantors();
			case 3:
				getCustomerPrepareCall = connection
						.prepareCall("{CALL get_customer_form_wizard_three(?)}");
				getCustomerPrepareCall.setInt(1,
						Integer.parseInt(metaDataMap.get("customerId")));
				getCustomerResultSet = getCustomerPrepareCall.executeQuery();
				metaData = getCustomerResultSet.getMetaData();
				columns = new String[metaData.getColumnCount()];
				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);
				}
				HashMap<String, String> assetsMap = new HashMap<>();
				if (getCustomerResultSet.next()) {

					for (int i = 0; i < metaData.getColumnCount(); i++) {
						assetsMap.put(columns[i],
								getCustomerResultSet.getString(columns[i]));
					}
				}
				map.put("assetsMap", assetsMap);
				getExpenses();
				getAssets();
				getLoans();
			case 2:
				getCustomerPrepareCall = connection
						.prepareCall("{CALL get_customer_form_wizard_two(?)}");
				getCustomerPrepareCall.setInt(1,
						Integer.parseInt(metaDataMap.get("customerId")));
				getCustomerResultSet = getCustomerPrepareCall.executeQuery();
				metaData = getCustomerResultSet.getMetaData();
				columns = new String[metaData.getColumnCount()];
				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);
				}
				HashMap<String, String> incomeSourceMap = new HashMap<>();
				if (getCustomerResultSet.next()) {
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						incomeSourceMap.put(columns[i],
								getCustomerResultSet.getString(columns[i]));
					}
				}
				map.put("incomeSource", incomeSourceMap);
				getOtherIncomeSource();
			case 1:
				getCustomerPrepareCall = connection
						.prepareCall("{CALL get_customer_form_wizard_one(?)}");
				getCustomerPrepareCall.setInt(1,
						Integer.parseInt(metaDataMap.get("customerId")));
				getCustomerResultSet = getCustomerPrepareCall.executeQuery();
				metaData = getCustomerResultSet.getMetaData();
				columns = new String[metaData.getColumnCount()];
				HashMap<String, String> customerInfoMap = new HashMap<>();
				for (int i = 0; i < columns.length; i++) {
					columns[i] = metaData.getColumnLabel(i + 1);
				}

				if (getCustomerResultSet.next()) {
					for (int i = 0; i < metaData.getColumnCount(); i++) {
						
						if(columns[i].equalsIgnoreCase("date_of_birth") || columns[i].equalsIgnoreCase("requesteddate")){
							customerInfoMap.put(columns[i], getCustomerResultSet.getDate(columns[i])+ "");
						}else{
						customerInfoMap.put(columns[i], getCustomerResultSet.getString(columns[i]));
						}
					}
				}
				map.put("customerInfo", customerInfoMap);
				getOtherNumbers();
			case 0:
				break;
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return map;
	}

	private void getOtherIncomeSource() {
		try (Connection connection = Connect.getConnection()) {
			ArrayList<HashMap<String, String>> list = new ArrayList<>();

			com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT " + " oir.`id` AS id, "
							+ " oir.`detail` AS type, "
							+ " oir.`amount` AS amount " + " FROM "
							+ " `other_income_resource` oir "
							+ " WHERE oir.`customer_id` = ? ");
			statement
					.setInt(1, Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = statement.executeQuery();
//			System.out.println(statement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
			map.put("otherIncomeSources", list);
			} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	private void getLoans() {
		try (Connection connection = Connect.getConnection();) {
			ArrayList<HashMap<String, String>> list = new ArrayList<>();

			com.mysql.jdbc.PreparedStatement olStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT  "
							+ "  ol.`loan_id` AS id, "
							+ "  ol.`donner` AS donnerName, "
							+ "  ol.`barrowed_amount` AS barrowedAmount, "
							+ "  ol.`remaining_amount` AS remainingAmount, "
							+ "  ol.`monthly_installment` AS monthlyInstallment, "
							+ "  ol.`payment_planInMonth` AS payment_planInMonth, "
							+ "  ol.`frequency_paymentInMonth` AS frequency_paymentInMonth, "
							+ "  ol.`type` AS type  " + "FROM "
							+ "  `other_loan` ol "
							+ "WHERE ol.`customer_id` = ? ");

			olStatement.setInt(1,
					Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = olStatement.executeQuery();
//			System.out.println(olStatement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
			map.put("loans", list);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	private void getAssets() {
		try (Connection connection = Connect.getConnection()) {
			ArrayList<HashMap<String, String>> list = new ArrayList<>();

			com.mysql.jdbc.PreparedStatement asStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT " + "  a.`asset_id` AS id, "
							+ "  a.`type` AS type, "
							+ "  a.`amount` AS amount " + "  FROM "
							+ "  `assets` a " + "  WHERE a.`customer_id` = ?");

			asStatement.setInt(1,
					Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = asStatement.executeQuery();
//			System.out.println(asStatement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			while (resultSet.next()) {
				HashMap<String, String> assets = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					assets.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(assets);
			}
			map.put("assets", list);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	private void getOtherNumbers() {
		try (Connection connection = Connect.getConnection()) {

			com.mysql.jdbc.PreparedStatement meStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT * FROM `customer_other_phonenumber` co WHERE co.`customer_id` = ?");

			meStatement.setInt(1,
					Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = meStatement.executeQuery();
//			System.out.println(meStatement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			ArrayList<HashMap<String, String>> list = new ArrayList<>();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
			map.put("otherNumbers", list);
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	private void getExpenses() {
		try (Connection connection = Connect.getConnection()) {

			com.mysql.jdbc.PreparedStatement meStatement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT  "
							+ "  me.`expense_id` AS expenseId, "
							+ "  me.`type`, " + "  me.`amount` " + "FROM "
							+ "  `monthly_home_expenses` me "
							+ "WHERE me.`customer_id` = ?");

			meStatement.setInt(1,
					Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = meStatement.executeQuery();
//			System.out.println(meStatement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			ArrayList<HashMap<String, String>> list = new ArrayList<>();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
			map.put("expenses", list);

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	private void getGuarantors() {
		try (Connection connection = Connect.getConnection()) {

			com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT "
							+ " cg.`guarantor_id` AS id, "
							+ " cg.`gguarantorName` AS 'name', "
							+ " cg.`gguarantorCnic` AS CNIC, "
							+ " cg.`gRelationToCustomer` AS relation, "
							+ " cg.`gguarantorPhone` AS primaryPhone, "
							+ " cg.`gguarantorSecondryPhone` AS secondaryPhone, "
							+ " cg.`gguarantorIncome` AS income,"
							+ " cg.`guaranterProfession` AS guarantorProfession,"
							+ "cg.`guarantertype` AS 'type' " + " FROM "
							+ " `customer_guarantor` AS cg "
							+ " WHERE cg.`customer_id` = ?");

			statement
					.setInt(1, Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = statement.executeQuery();
//			System.out.println(statement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			ArrayList<HashMap<String, String>> list = new ArrayList<>();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}
			map.put("guarantors", list);

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

	}

	public void getAppliances() {
			try (Connection connection = Connect.getConnection()) {

			com.mysql.jdbc.PreparedStatement statement = (com.mysql.jdbc.PreparedStatement) connection
					.prepareStatement("SELECT eligibility_id,"
							+ " appliance_id,"
							+ " app.appliance_name,"
							+ "appliance_status FROM eligibility JOIN appliance app USING(appliance_id)"
							+ " WHERE customer_id=?");
			statement
					.setInt(1, Integer.parseInt(metaDataMap.get("customerId")));
			ResultSet resultSet = statement.executeQuery();
//			System.out.println(statement.asSql());
			ResultSetMetaData metaData = resultSet.getMetaData();
			String[] columns = new String[metaData.getColumnCount()];
			for (int i = 0; i < columns.length; i++) {
				columns[i] = metaData.getColumnLabel(i + 1);
			}
			ArrayList<HashMap<String, String>> list = new ArrayList<>();
			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();
				for (String string : columns) {
					map.put(string, resultSet.getString(string));
				}

				list.add(map);
			}
			map.put("appliances", list);

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		
	}

}
