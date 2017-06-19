package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import bal.DataAndReportsBal;
import bean.UserBean;

/**
 * Servlet implementation class ReportAnalyticsController
 */
@WebServlet("/ReportAnalyticsController")
public class ReportAnalyticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ReportAnalyticsController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportAnalyticsController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("getDoWiseLateDefaulters")) {
				try {
					ArrayList<HashMap<String, String>> map = DataAndReportsBal
							.getDoWiseLateDefaulters();
					JSONArray json = new JSONArray(
							DataAndReportsBal.getDoWiseLateDefaulters());
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (action.equalsIgnoreCase("getFoWiseLateDefaulters")) {
				try {

					Map<String, String[]> parameterMap = request
							.getParameterMap();
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
					int col = Integer.parseInt(orderByArrayString[0]);

					String[] orderDirArrayString = parameterMap
							.get("order[0][dir]");
					String order = orderDirArrayString[0];

					String[] search = parameterMap.get("search[value]");

					List<HashMap<String, String>> list = null;
					int count = 0;

					if (!search[0].isEmpty()) {
						list = DataAndReportsBal
								.getFoWiseLateDefaulterSearchFilter(start,
										length, col, order, search[0]);
						count = DataAndReportsBal
								.getFoWiseLateDefaultersSearchCount(search[0]);
					} else {

						list = DataAndReportsBal.getFoWiseLateDefaultersFilter(
								start, length, col, order);
						count = DataAndReportsBal
								.getFoWiseLateDefaultersCount();
					}
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("paymentsCount", count);
					jobject.put("data", list);
					out.print(jobject);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		} catch (Exception e) {
			logger.equals(e);
			e.printStackTrace();
		}

	}

}
