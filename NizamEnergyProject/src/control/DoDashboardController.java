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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.DoDashboardBal;
import bal.LoanRequestBal;
import bean.UserBean;

@WebServlet("/DoDashboardController")
public class DoDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoDashboardController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoDashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		int userid = ubean.getUserId();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action != null && !action.isEmpty()) {
			if (action.equals("getUnseenRequest")) {
				JSONObject json = new JSONObject();
				try {
					json.put("request",
							LoanRequestBal.getUnseenRequestsByDistrict(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getUnseenBookings")) {
				JSONObject json = new JSONObject();
				try {
					json.put("bookings",
							LoanRequestBal.getPreBookingsByDistrict(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoRecovery")) {
				JSONObject json = null;
				try {
					HashMap<String, String> recovery = DoDashboardBal
							.getDoRecovery(userid);
					json = new JSONObject(recovery);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoOverduePayments")) {
				JSONObject json = null;
				try {
					HashMap<String, String> overduePayment = DoDashboardBal
							.getDoOverduePayments(userid);
					json = new JSONObject(overduePayment);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoDefaulters")) {
				JSONObject json = null;
				try {
					json = new JSONObject(
							DoDashboardBal.getDoDefaulters(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoInstallations")) {
				JSONObject json = null;
				try {
					json = new JSONObject(
							DoDashboardBal.getDoInstallations(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoSales")) {
				JSONObject json = null;
				try {
					json = new JSONObject(DoDashboardBal.getDoSales(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoPerformanceDetails")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
				JSONObject json = null;
				try {
					json = new JSONObject(
							DoDashboardBal.getDoPerformanceDetails(userid, to,
									from));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoLoanStatus")) {
				JSONObject json = null;
				try {
					json = new JSONObject(DoDashboardBal.getLoanStatus(userid));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			} else if (action.equals("getDoTopFiveNDs")) {
				String to = request.getParameter("to");
				String from = request.getParameter("from");
				JSONArray json = null;
				try {
					ArrayList<HashMap<String, String>> ndDetails = DoDashboardBal
							.getDoTopFiveNds(userid, to, from);
					json = new JSONArray(ndDetails);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (action.equals("getTotalCustomersAndAppliances")) {
				try {
					int countPendingCustomersDo = DoDashboardBal
							.countPendingCustomersDo(userid);
					int countAcceptedCustomersDo = DoDashboardBal
							.countAcceptedCustomersDo(userid);
					int countVarifiedCustomersDo = DoDashboardBal
							.countVarifiedCustomersDo(userid);
					int countCCVerifyCustomerDo = DoDashboardBal
							.countCCVarifyCustomersDo(userid);
					HashMap<String, String> countAllStatusAndApplianceStatusDo = DoDashboardBal
							.countAllStatusAndApplianceStatusDo(userid);
					JSONObject jobject = new JSONObject();
					jobject.put("countCCVerifyCustomerDo",
							countCCVerifyCustomerDo);
					jobject.put("countPendingCustomersDo",
							countPendingCustomersDo);
					jobject.put("countAcceptedCustomersDo",
							countAcceptedCustomersDo);
					jobject.put("countVarifiedCustomersDo",
							countVarifiedCustomersDo);
					jobject.put("countAllStatusAndApplianceStatusDo",
							countAllStatusAndApplianceStatusDo);
					out.println(jobject);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
		}
	}

}
