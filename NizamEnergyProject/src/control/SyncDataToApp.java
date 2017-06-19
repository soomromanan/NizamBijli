package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.AndroidBal;
import bal.OldAndroidBal;

@WebServlet("/SyncDataToApp")
public class SyncDataToApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SyncDataToApp.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			String action = request.getParameter("action");
			String userIdString = request.getParameter("userId");
			String userType = request.getParameter("userType");

			JSONObject json = new JSONObject();

			String status = "", message = "";

			if (action != null && !action.isEmpty()
					&& action.equals("syncDataToApp")) {

				if (userIdString != null && !userIdString.equals("0")) {
					int userId = Integer.parseInt(userIdString);
					if (userType.equals("FO")) {
						status = "ok";
						// for dashboard data
						json.put("dashboard", OldAndroidBal.getDashboardData(
								userType, userId));

						// for counts data
						json.put("counts", OldAndroidBal.getFOCounters(userId));

						// for payments data
						json.put("payments", OldAndroidBal.getPaymentHistory(
								userType, userId));

						// for customers data
						json.put("customers",
								OldAndroidBal.getAllCustomersByFO(userId));

						// for recoveries data
						json.put("recoveries",
								AndroidBal.getRecoveriesByFO(userId));

						// for defaulters data
						json.put("defaulters",
								OldAndroidBal.getDefaultedCustomersByFO(userId));

						// for vles data
						json.put("vles", OldAndroidBal.getSalesmen(userId));

						// for verifies data
						json.put("verifies", AndroidBal
								.getAllAcceptedEligibilityByFO(userId));

						// for installs data
						json.put("installs", OldAndroidBal
								.getAllHandoveredEligibilityByFO(userId));

					} else if (userType.equals("VLE")) {
						status = "ok";

						// for dashboard data
						json.put("dashboard", OldAndroidBal.getDashboardData(
								userType, userId));

						// for payments data
						json.put("payments", OldAndroidBal.getPaymentHistory(
								userType, userId));

						// for customers data
						json.put("customers", OldAndroidBal
								.getCustomersDetailBySalesmanId(userId));

						// for recoveries data
						json.put("recoveries",
								OldAndroidBal.getAllCustomerBySaler(userId));

						// for defaulters data
						json.put("defaulters",
								OldAndroidBal.getDefaultCustomer(userId));
					} else {
						status = "error";
						message = "userType not defined";
					}
				} else {
					status = "error";
					message = "userId not defined";
				}

			} else {
				status = "error";
				message = "action not defined";
			}
			json.put("status", status);
			json.put("message", message);
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}