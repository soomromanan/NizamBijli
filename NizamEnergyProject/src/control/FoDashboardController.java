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

@WebServlet("/FoDashboardController")
public class FoDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FoDashboardController.class);
	
	public FoDashboardController() {
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
			if (action != null && !action.isEmpty() && action.equals("performance")) {
				if (userIdString != null && !userIdString.equals("0")) {
					int userId = Integer.parseInt(userIdString);
					if (userType.equals("FO")) {
						status = "ok";
						System.err.println("foPerformanceInfo");
						json.put("perfInfo",AndroidBal.foPerformanceInfo(userId));
						
						// for late and Defaulter counts data
						json.put("FoCounts", AndroidBal.countLateDefaultersFoWise(userId));
						
						json.put("commissionAmount", AndroidBal.getFoCommission(userType, userId));

					} else {
						status = "error";
						message = "userType not defined";
					}
				} else {
					status = "error";
					message = "userId not defined";
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
