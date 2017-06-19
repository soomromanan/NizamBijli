package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageAPI.MoblinkMessageService;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.log4j.Logger;

import bal.AndroidBal;

@WebServlet("/FieldOfficerLoginController")
public class FieldOfficerLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(FieldOfficerLoginController.class);

	public FieldOfficerLoginController() {
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
					if (action.equals("checkNumber")) {
						String number = request.getParameter("number");
						if (number != null) {
							if (!number.isEmpty()) {
								number = number.replace("-", "");
								String password = AndroidBal
										.getPassword(number);
								if (password.equals("")) {
									if (password.equals("")) {
										json.put("status", "error");
										json.put("message", "Number not found");
									}
								} else {
									try {
										json.put("userType", "FO");
										MoblinkMessageService.SendMessage(
												number, "Your SPG password is "
														+ password);
										json.put("status", "ok");
									} catch (Exception e) {
										json.put("status", "error");
										json.put("exception message",
												e.getMessage());
									}
								}
							} else {
								json.put("status", "error");
								json.put("message",
										"mobile number is empty or not provided");
							}
						} else {
							json.put("status", "error");
							json.put("message", "mobile number null");
						}
					} else if (action.equals("login")) {
						String cellNumber = request.getParameter("number")
								.replace("-", "");
						String password = request.getParameter("password");
						String userType = request.getParameter("userType");
						if (password != null && cellNumber != null) {
							if (!password.isEmpty() && !cellNumber.isEmpty()) {
								if (userType.equals("FO")) {
									HashMap<String, String> map = AndroidBal
											.login(cellNumber, password);
									if (map == null) {
										json.put("status", "error");
										json.put("message", "wrong password");
									} else {
										json.put("status", "ok");
										json.put("userType", userType);
										json.put("fieldOfficer", map);
									}

								}
							} else {
								json.put("status", "error");
								json.put("message",
										"code is empty or not provided");
							}
						} else {
							json.put("status", "error");
							json.put("message", "code is null");
						}
					} else {
						json.put("status", "error");
						json.put("message", "action not initialized");
					}
				} else {
					json.put("status", "error");
					json.put("message", "action not initialized");
				}
			}
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}
