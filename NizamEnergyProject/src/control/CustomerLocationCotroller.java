package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bal.AndroidBal;


@WebServlet("/CustomerLocationCotroller")
public class CustomerLocationCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLocationCotroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			JSONObject json = new JSONObject();
			String status = "", message = "";
			if (action != null && !action.isEmpty()
					&& action.equals("location")) {
				String applianceId = request.getParameter("applianceId");
				String latitude = request.getParameter("latitude");
				String longitude = request.getParameter("longitude");
				System.err.println(applianceId);
				if (applianceId != null && !applianceId.equals("0")) {
					int appId = Integer.parseInt(applianceId);
					AndroidBal.insertCustomersLocation(appId,
							Double.parseDouble(latitude),
							Double.parseDouble(longitude));
					status = "ok";
				} else {
					status = "error";
					message = "applianceId not defined";
				}
			} else {
				status = "error";
				message = "action not defined";
			}
			json.put("status", status);
			json.put("message", message);
			out.print(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
