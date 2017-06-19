package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.ApplianceBal;
import bean.UserBean;

@WebServlet("/QuickFilterController")
public class QuickFilterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(QuickFilterController.class);

	public QuickFilterController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		try (PrintWriter writer = response.getWriter()) {
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()) {
				if (action.equals("getApplianceByQuickFilters")) {
					Map<String, String[]> parameterMap = request
							.getParameterMap();

					String[] drawString = parameterMap.get("draw");
					int draw = Integer.parseInt(drawString[0]);

					String[] startString = parameterMap.get("start");
					int start = Integer.parseInt(startString[0]);

					String[] lengthString = parameterMap.get("length");
					int length = Integer.parseInt(lengthString[0]);

					String[] orderByString = parameterMap
							.get("order[0][column]");
					int orderBy = Integer.parseInt(orderByString[0]);

					String[] orderDirString = parameterMap.get("order[0][dir]");
					String orderDir = orderDirString[0];

					String[] search = parameterMap.get("search[value]");

					String applianceStatusString = parameterMap
							.get("applianceStatus")[0];
					int applianceStatus = Integer
							.parseInt(applianceStatusString);
					String[] soldStatusArrayString = parameterMap
							.get("soldStatus[]");
					String[] healthStatusArrayString = parameterMap
							.get("healthStatus[]");

					if (soldStatusArrayString == null) {
						soldStatusArrayString = new String[0];
					}
					if (healthStatusArrayString == null) {
						healthStatusArrayString = new String[0];
					}

					JSONObject jsonObject = new JSONObject();

					ArrayList<HashMap<String, String>> quickedFilteredCustomers = ApplianceBal
							.getQuickedFilteredAppliances(
									Integer.parseInt(applianceStatusString),
									soldStatusArrayString,
									healthStatusArrayString, orderBy, orderDir,
									start, length, search[0]);

					JSONArray jsonArray = new JSONArray(
							quickedFilteredCustomers);

					jsonObject.put("draw", draw);
					int count = ApplianceBal.countQuickedFilteredAppliances(
							applianceStatus, soldStatusArrayString,
							healthStatusArrayString, search[0]);
					jsonObject.put("recordsTotal", count);
					jsonObject.put("recordsFiltered", count);
					jsonObject.put("applianceCount", count);
					jsonObject.put("data", jsonArray);
					writer.print(jsonObject);

				}
			}
		}
	}

}
