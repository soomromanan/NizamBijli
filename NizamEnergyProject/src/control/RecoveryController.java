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
import bal.OldAndroidBal;

@WebServlet("/RecoveryController")
public class RecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(RecoveryController.class);

	public RecoveryController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			JSONObject json = new JSONObject();
			String action = request.getParameter("action");
			if (action != null) {
				if (!action.isEmpty()) {
					if (action.equals("getRecoveries")) {
						String userIdString = request.getParameter("userId");
						String userType = request.getParameter("userType");
						if (userIdString != null && !userIdString.equals("0")) {
							json.put("status", "ok");
							if (userType.equals("FO")) {
								json.put("recoveries", AndroidBal
										.getRecoveriesByFO(Integer
												.parseInt(userIdString)));
							} else if (userType.equals("VLE")) {
								json.put("recoveries", OldAndroidBal
										.getAllCustomerBySaler(Integer
												.parseInt(userIdString)));
							} else {
								json.put("status", "error");
								json.put("message", "userType not defined");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId not defined");
						}
					} else {
						json.put("status", "error");
						json.put("message", "action not found");
					}
				} else {
					json.put("status", "error");
					json.put("message", "action not Initialized");
				}
			} else {
				json.put("status", "error");
				json.put("message", "action not defined");
			}
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}
