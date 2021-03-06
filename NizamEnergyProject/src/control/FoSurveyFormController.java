package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.AndroidBal;

/**
 * Servlet implementation class FoSurveyFormController
 */
@WebServlet("/FoSurveyFormController")
public class FoSurveyFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FoSurveyFormController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoSurveyFormController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			String userIdString = request.getParameter("userId");
			String userType = request.getParameter("userType");
			JSONObject json = new JSONObject();
			String status = "", message = "";
			if (action != null && !action.isEmpty() && action.equals("survey")) {
				if (userType.equals("FO")) {
					System.err.println(userIdString);
					status = "ok";
					json.put("survey_form", AndroidBal.getFoSurveyForms());
				} else {
					status = "error";
					message = "userType not defined";
				}
			} else {
				status = "error";
				message = "action not defined";
			}
			json.put("status", status);
			json.put("message", message);
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}
