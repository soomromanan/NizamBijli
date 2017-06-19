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

import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.CustomerBal;
import bean.UserBean;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = { "/CustomerController" })
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CustomerController.class);

	public CustomerController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("email");
		if (userBean != null) {
			String action = request.getParameter("action");
			if (action.equals("getExpansion")) {
				String id = request.getParameter("id");
				JSONArray json = new JSONArray();
				try {
					json.put(CustomerBal.getCustomersAppliances(Integer
							.parseInt(id)));
				} catch (Exception e) {
					logger.error(e);
				}
				response.getWriter().print(json);
			} else if (action.equals("getRejectedExpansion")) {
				String id = request.getParameter("id");
				JSONArray json = new JSONArray();
				try {
					json.put(CustomerBal.getRejectedAppliances(Integer
							.parseInt(id)));
				} catch (Exception e) {
					logger.error(e);
				}
				response.getWriter().print(json);
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("email");
		if (userBean != null) {
			String action = request.getParameter("action");
			if (action != null && !action.isEmpty()) {
				if (action.equalsIgnoreCase("getCustomersByLimitAndRange")) {
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
					int orderBy = Integer.parseInt(orderByArrayString[0]);

					String[] orderDirArrayString = parameterMap
							.get("order[0][dir]");
					String orderDir = orderDirArrayString[0];
					String searchValue = parameterMap.get("search[value]")[0];
					JSONObject jsonObject = new JSONObject();

					ArrayList<HashMap<String, String>> customerList = CustomerBal
							.getCustomersByLimitAndRangeAndOrderBy(start,
									length, orderBy, orderDir, searchValue);
					int total = CustomerBal
							.countCustomersByRegexSearch(searchValue);
					JSONArray jsonArray = new JSONArray(customerList);
					jsonObject.put("draw", draw);
					jsonObject.put("recordsTotal", total);
					jsonObject.put("recordsFiltered", total);
					jsonObject.put("data", jsonArray);
					out.println(jsonObject);
				} else if (action.equalsIgnoreCase("getRejectedCustomers")) {
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
					int orderBy = Integer.parseInt(orderByArrayString[0]);

					String[] orderDirArrayString = parameterMap
							.get("order[0][dir]");
					String orderDir = orderDirArrayString[0];
					String searchValue = parameterMap.get("search[value]")[0];
					JSONObject jsonObject = new JSONObject();
					ArrayList<HashMap<String, String>> customerList = CustomerBal
							.getRejectedCustomers(start, length, orderBy,
									orderDir, searchValue);
					int total = CustomerBal.countRejectedCustomers(searchValue);
					JSONArray jsonArray = new JSONArray(customerList);
					jsonObject.put("draw", draw);
					jsonObject.put("recordsTotal", total);
					jsonObject.put("recordsFiltered", total);
					jsonObject.put("data", jsonArray);
					out.println(jsonObject);
				}
			}
		}

	}

}
