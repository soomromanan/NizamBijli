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

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceAlarmsBal;
import bean.UserBean;

/**
 * Servlet implementation class ApplianceAlarms
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ApplianceAlarms" })
public class ApplianceAlarms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ApplianceAlarms.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApplianceAlarms() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase(
					"getAlarmsForSuperadmin")) {
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

				if (!search[0].isEmpty()) {
					JSONObject json = new JSONObject();
					ArrayList<HashMap<String, String>> list = ApplianceAlarmsBal
							.getSuperadminAlarmListSearch(start, length,
									orderDir, orderBy, search[0]);
					int count = ApplianceAlarmsBal
							.getSuperadminAlarmsListSearchCount(orderDir,
									orderBy, search[0]);
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);
				} else {
					JSONObject json = new JSONObject();
					ArrayList<HashMap<String, String>> list = ApplianceAlarmsBal
							.getSuperadminAlarmsList(start, length, orderDir,
									orderBy);
					int count = ApplianceAlarmsBal
							.getSuperadminAlarmsListCount();
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);
				}

			} else if (request.getParameter("action").equalsIgnoreCase(
					"getAlarmsForDo")) {
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

				if (!search[0].isEmpty()) {

					ArrayList<HashMap<String, String>> list = ApplianceAlarmsBal
							.getDoApplianceAlarmsListSearch(start, length,
									orderDir, orderBy, bean.getUserId(),
									search[0]);
					int count = ApplianceAlarmsBal
							.getDoApplianceAlarmsListSearchCount(orderDir,
									orderBy, bean.getUserId(), search[0]);
					JSONObject json = new JSONObject();
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);

				} else {

					JSONObject json = new JSONObject();
					ArrayList<HashMap<String, String>> list = ApplianceAlarmsBal
							.getDoApplianceAlarmsList(start, length, orderDir,
									orderBy, bean.getUserId());
					int count = ApplianceAlarmsBal
							.getDoApplianceAlarmsListCount(orderDir, orderBy,
									bean.getUserId());
					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("salesmansCount", count);
					json.put("data", list);
					out.print(json);

				}

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
