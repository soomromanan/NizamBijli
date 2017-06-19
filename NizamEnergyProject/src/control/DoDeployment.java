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

import bal.ApplianceBal;
import bean.UserBean;

/**
 * Servlet implementation class DoDeployment
 */
@WebServlet("/DoDeployment")
public class DoDeployment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoDeployment.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoDeployment() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase("doDeployment")) {
				int userId = ubean.getUserId();
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

				int count = ApplianceBal.getDoDeploymentListSearchCount(userId,
						search[0]);
				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("deploymentCount", count);
				jobject.put("data", ApplianceBal.getDoDeploymentList(start,
						length, orderDir, orderBy, userId, search[0]));
				out.print(jobject);

			} else if (request.getParameter("action").equals("countFilters")) {
				HashMap<String, Integer> map = ApplianceBal
						.countDoPipelineFilters(ubean.getUserId());
				JSONObject jsonObject = new JSONObject(map);
				out.print(jsonObject);
			}

			if (request.getParameter("action").equals("filterPipeline")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

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

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = ApplianceBal
						.countDoPipelineFiltersWithSearch(ubean.getUserId(),
								search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					loanApplications = ApplianceBal.getDoDeploymentListPending(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("readyToAssign")) {
					count = map.get("readyToAssign");
					loanApplications = ApplianceBal
							.getDoDeploymentListRtoAssign(start, length,
									orderDir, orderBy, ubean.getUserId(),
									search[0]);

				} else if (filters[0].equals("awaitingDownpayment")) {
					count = map.get("awaitingDownpayment");
					loanApplications = ApplianceBal.getDoDeploymentListAdp(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("installed")) {
					count = map.get("installed");
					loanApplications = ApplianceBal
							.getDoDeploymentListInstalled(start, length,
									orderDir, orderBy, ubean.getUserId(),
									search[0]);

				} else if (filters[0].equals("returned")) {
					count = map.get("returned");
					loanApplications = ApplianceBal
							.getDoDeploymentListReturned(start, length,
									orderDir, orderBy, ubean.getUserId(),
									search[0]);

				} else if (filters[0].equals("rdp")) {
					count = map.get("rdp");
					loanApplications = ApplianceBal.getDoDeploymentListRdp(
							start, length, orderDir, orderBy,
							ubean.getUserId(), search[0]);

				} else if (filters[0].equals("installed_ns")) {
					count = map.get("installedNoSignal");
					loanApplications = ApplianceBal
							.getDoDeploymentListInstalledNoSignal(start,
									length, orderDir, orderBy,
									ubean.getUserId(), search[0]);

				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
