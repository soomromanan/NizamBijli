package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceBal;
import bal.LoanBookBal;
import bal.LoanRequestBal;
import bal.SuperAdminDashboardBal;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(asyncSupported = true, urlPatterns = { "/SuperAdminDashboardController" })
public class SuperAdminDashboardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(SuperAdminDashboardController.class);

	public SuperAdminDashboardController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		if (ubean != null) {
			PrintWriter out = response.getWriter();
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()) {
				if (action.equals("getUnseenRequest")) {
					JSONObject json = new JSONObject();
					try {
						int countRequests = LoanRequestBal.getUnseenRequests();
						json.put("request", countRequests);
					} catch (JSONException e) {
						logger.error("", e);
						e.printStackTrace();
					}
					out.println(json);
				} else if (action.equals("getUnseenRequestForBooking")) {
					JSONObject json = new JSONObject();
					try {
						int countRequests = LoanRequestBal
								.getUnseenBookingApps();
						json.put("request", countRequests);
					} catch (JSONException e) {
						logger.error("", e);
						e.printStackTrace();
					}
					out.println(json);
				} else if (action.equals("getRecovery")) {
					try {
						HashMap<String, String> recovery = SuperAdminDashboardBal
								.getRecoveryRateBeforeDueDate();
						JSONObject json = new JSONObject(recovery);
						json.put("getRecoveryRateBeforeDueDate", recovery);
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getParTen")) {
					try {
						HashMap<String, String> parTen = SuperAdminDashboardBal
								.getRecoveryRateAfterDueDate();
						JSONObject json = new JSONObject(parTen);
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getPortfolio")) {
					try {
						HashMap<String, String> getTotalOutstandingLoans = SuperAdminDashboardBal
								.getTotalOutstandingLoans();
						JSONObject json = new JSONObject(
								getTotalOutstandingLoans);
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getDoTopFiveNDs")) {
					try {
						JSONArray json = new JSONArray(
								SuperAdminDashboardBal.getTopFiveNDs());
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
					}
				} else if (action.equals("getDoTopFiveFos")) {
					try {
						JSONArray json = new JSONArray(
								SuperAdminDashboardBal.getTopFiveFos());
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getDoPerformance")) {
					try {
						JSONArray json = new JSONArray(
								SuperAdminDashboardBal.getTopFiveDos());
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getPortfolioHealth")) {
					try {
						HashMap<String, String> map = SuperAdminDashboardBal
								.totalPortfolioHealth();
						JSONObject json = new JSONObject(map);
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getRecoveryDetails")) {
					try {
						ArrayList<HashMap<String, String>> list = SuperAdminDashboardBal
								.monthlyPortfolio();
						JSONArray json = new JSONArray(list);
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (action.equals("getTotalCustomersAndAppliances")) {
					try {
						int countPendingCustomers = LoanRequestBal
								.countPendingCustomers();
						int countAcceptedCustomers = LoanRequestBal
								.countAcceptedCustomers();
						int countVarifiedCustomers = LoanRequestBal
								.countVarifiedCustomers();
						int countCCVerify = LoanRequestBal
								.countCCVarifyCustomers();
						HashMap<String, String> countAllStatusAndApplianceStatus = ApplianceBal
								.countAllStatusAndApplianceStatus();
						HashMap<String, String> countLoanBookFilters = LoanBookBal
								.countLoanBookFilters();
						JSONObject jobject = new JSONObject();
						jobject.put("countCCVerify", countCCVerify);
						jobject.put("countPendingCustomers",
								countPendingCustomers);
						jobject.put("countAcceptedCustomers",
								countAcceptedCustomers);
						jobject.put("countVarifiedCustomers",
								countVarifiedCustomers);
						jobject.put("countAllStatusAndApplianceStatus",
								countAllStatusAndApplianceStatus);
						jobject.put("countLoanBookFilters",
								countLoanBookFilters);
						out.println(jobject);

					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
			}
		}
	}

}