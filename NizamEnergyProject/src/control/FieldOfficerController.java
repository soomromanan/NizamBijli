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

import bal.OldAndroidBal;

@WebServlet("/FieldOfficerController")
public class FieldOfficerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FieldOfficerLoginController.class);

	public FieldOfficerController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			JSONObject json = new JSONObject();
			if (action != null) {
				if (action.equals("getVle")) {
					String userIdString = request.getParameter("userId");
					if (userIdString != null) {
						if (!userIdString.isEmpty()) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");
							json.put("vles", OldAndroidBal.getSalesmen(userId));
						} else {
							json.put("status", "error");
							json.put("message", "userId is not initialized");
						}
					} else {
						json.put("status", "error");
						json.put("message", "userId is not declared");
					}
				} else if (action.equals("getDefaultedCustomers")) {
					String userIdString = request.getParameter("userId");
					String userType = request.getParameter("userType");
					if (userIdString != null) {
						if (!userIdString.isEmpty()) {
							int userId = Integer.parseInt(userIdString);
							if (userType != null) {
								json.put("status", "ok");
								if (userType.equals("FO")) {
									json.put("customers", OldAndroidBal.getDefaultedCustomersByFO(userId));
								} else if (userType.equals("VLE")) {
									json.put("customers", OldAndroidBal.getDefaultCustomer(userId));
								} else {
									json.put("status", "error");
									json.put("message", "userType not found");
								}
								System.out.println(userType + " => " + userId);
								// out.print(json);
							} else {
								json.put("status", "error");
								json.put("message", "userType not defined");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId is not initialized");
						}
					} else {
						json.put("status", "error");
						json.put("message", "userId is not declared");
					}
				} else {
					json.put("status", "error");
					json.put("message", "required action is not defined");
				}
			} else {
				json.put("status", "Error");
				json.put("message", "action not defined");
			}
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}
