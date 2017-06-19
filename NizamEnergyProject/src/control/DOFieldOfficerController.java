package control;

import java.io.IOException;
import java.io.PrintWriter;
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

import bal.DoFoNdBal;
import bean.UserBean;

/**
 * Servlet implementation class FieldOfficerController
 */
@WebServlet("/DOFieldOfficerController")
public class DOFieldOfficerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(DOFieldOfficerController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DOFieldOfficerController() {
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

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("action").equalsIgnoreCase(
					"getDoFieldOfficerTable")) {
				int userDistrict = ubean.getUser_district();
				int doId = ubean.getUserId();
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
				int count = DoFoNdBal.getFOlistSearchCount(doId, search[0]);
				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("FOCount", count);
				jobject.put("data", DoFoNdBal.getFOlistSearch(start, length,
						orderDir, orderBy, doId, search[0]));
				out.print(jobject);
			}
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
