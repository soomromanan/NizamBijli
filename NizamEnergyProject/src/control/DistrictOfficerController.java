package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.DoFoNdBal;
import bean.DistrictOfficerBean;
import bean.UserBean;

/**
 * Servlet implementation class DistrictOfficerController
 */
@WebServlet("/DistrictOfficerController")
public class DistrictOfficerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(DistrictOfficerController.class);

	public DistrictOfficerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");

			if (request.getParameter("action").equalsIgnoreCase("getDoTable")) {

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
				String[] searchRegex = parameterMap.get("search[regex]");

				try {
					if (!search[0].isEmpty()) {
						int count = DoFoNdBal
								.getDistrictOfficerListBySearchCount(search[0]);
						JSONObject jobject = new JSONObject();
						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("salesmansCount", count);
						jobject.put("data", DoFoNdBal
								.getDistrictOfficerListWithSearch(start,
										length, orderDir, orderBy, search[0]));
						out.print(jobject);

					} else {

						int count = DoFoNdBal.getDistrictOfficerListCount();
						JSONObject jobject = new JSONObject();
						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("salesmansCount", count);
						jobject.put("data", DoFoNdBal
								.getDistricOfficerListWithOrder(start, length,
										orderBy, orderDir));
						out.print(jobject);

					}
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
			} else if (request.getParameter("action").equalsIgnoreCase(
					"getFoTable")) {
				String id = request.getParameter("do_id");
				JSONObject jobject = new JSONObject();
				if (id != null) {
					int districtId = Integer.parseInt(id);
					DistrictOfficerBean bean = DoFoNdBal
							.getDO_details(districtId);
					request.setAttribute("bean", bean);
					request.setAttribute("district", districtId);
					try {
						jobject.put("data",
								DoFoNdBal.getDO_details(districtId));
					} catch (JSONException e) {
						logger.error(e);
						e.printStackTrace();
					}
					out.print(jobject);
					RequestDispatcher rd = request
							.getRequestDispatcher("DoProfile");
					rd.forward(request, response);
				}
			}
		}

	}

}
