package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceBal;
import bal.ApplianceStatusBal;
import bal.OldAndroidBal;
import bean.UserBean;

@WebServlet("/ApplianceController")
public class ApplianceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ApplianceController.class);

	public ApplianceController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equals("getApplianceTable")) {

				HttpSession session = request.getSession();

				UserBean bean = (UserBean) session.getAttribute("email");

				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				JSONObject jobject = new JSONObject();
				try {
					int searchCount = ApplianceBal
							.getApplianceSearchCount(search[0]);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", searchCount);
					jobject.put("recordsFiltered", searchCount);
					jobject.put("applianceCount", searchCount);
					jobject.put("data", ApplianceBal
							.getApplianceByLimitAndRangeAndOrderBy(start,
									length, orderBy, orderDir, search[0]));
				} catch (Exception e) {
					logger.error("", e);
				}
				out.print(jobject);
			}

			else if (request.getParameter("action").equals(
					"countApplianceAndSoldStatus")) {
				try {
					HashMap<String, String> countAllStatusAndApplianceStatus = ApplianceBal
							.countAllStatusAndApplianceStatus();
					JSONObject jsonObject = new JSONObject(
							countAllStatusAndApplianceStatus);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (request.getParameter("action").equals(
					"getAllApplianceNameBySoldTo")) {
				try {
					ArrayList<HashMap<String, String>> allApplianceBySoldTo = ApplianceBal
							.getAllApplianceBySoldTo();
					JSONArray jsonArray = new JSONArray(allApplianceBySoldTo);
					out.print(jsonArray);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (request.getParameter("action").equals(
					"updateApplianceStatus")) {
				response.setContentType("application/json;charset=UTF-8");
				int applianceId = Integer.parseInt(request
						.getParameter("appliance_id"));
				int applianceStatus = Integer.parseInt(request
						.getParameter("appliance_status"));
				String data[] = OldAndroidBal.updateApplianceStatus(
						applianceId, applianceStatus).split(":");
				JSONObject obj = new JSONObject();
				if (data[1].equals("1")) {
					obj.put("status", "ok");
					obj.put("appliance_id", applianceId);
				} else {
					obj.put("status", "error");
					obj.put("message", "error in Installing Appliance");
				}
				String str = "Congratulations, your device has been installed, now pay first installment to active the device.";
				try {
					MoblinkMessageService.SendMessage(data[0], str);
				} catch (Exception e) {
					logger.error(e);
				}
				out.print(obj);

			}

			else if (request.getParameter("action").equals("districtSummary")) {
				try {
					int applianceId = Integer.parseInt(request
							.getParameter("appId"));
					JSONObject json = new JSONObject(
							ApplianceBal.getDistrictSummary(applianceId));
					System.out.print(json);
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (request.getParameter("action").equals("setNotIntrested")) {
				try {
					int applianceId = Integer.parseInt(request
							.getParameter("applianceId"));
					ApplianceBal.setNotIntrested(applianceId);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (request.getParameter("action")
					.equals("applianceSummary")) {
				try {
					int applianceId = Integer.parseInt(request
							.getParameter("appId"));
					JSONObject json = new JSONObject(
							ApplianceStatusBal
									.getApplianceStatusbyId(applianceId));
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}

			} else if (request.getParameter("action").equals("applianceData")) {
				try {
					int applianceId = Integer.parseInt(request
							.getParameter("appId"));
					JSONObject json = new JSONObject(
							ApplianceBal.getApplianceData(applianceId));

					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}