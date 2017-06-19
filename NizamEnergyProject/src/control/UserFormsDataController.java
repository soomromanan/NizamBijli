package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.DoFoNdFormsBAL;
import bean.UserBean;

/**
 * Servlet implementation class GetDataById
 */
@WebServlet("/UserFormsDataController")
public class UserFormsDataController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UserFormsDataController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserFormsDataController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			
			String action = request.getParameter("action");
			String id = request.getParameter("id");

			if (action.equals("FOInfo")) {
				JSONObject FOJSON = new JSONObject();
				try {
					FOJSON.put("FODetail", DoFoNdFormsBAL
							.getFieldOfficerDetailForUpdate(Integer
									.parseInt(id)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.print(FOJSON);
			} else if (action.equals("salesmanInfo")) {

				JSONObject VLEJSON = new JSONObject();

				try {
					VLEJSON.put("NDDetail", DoFoNdFormsBAL
							.getSalesmanDetailForUpdate(Integer.parseInt(id)));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				out.print(VLEJSON);
			} else if (action.equals("doInfo")) {
				JSONObject DOJSON = new JSONObject();
				try {
					DOJSON.put("doDetail", DoFoNdFormsBAL
							.getDoDetailForUpdate(Integer.parseInt(id)));
				} catch (NumberFormatException e) {
					logger.error("", e);
					e.printStackTrace();
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(DOJSON);
			}

		}

	}

}
