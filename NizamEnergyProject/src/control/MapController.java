package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceStatusBal;
import bal.MapBal;
import bean.InfowindowBean;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(urlPatterns = { "/MapController" })
public class MapController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(MapController.class);

	public MapController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		UserBean ubean = (UserBean) session.getAttribute("email");
		if (action.equalsIgnoreCase("getmarkersdata")) {
			JSONArray jArray = new JSONArray();
			ArrayList<InfowindowBean> list = MapBal.customerLocation();
			for (int i = 0; i < list.size(); i++) {
				JSONObject studentJSON = new JSONObject();
				try {
					studentJSON.put("Type", list.get(i).getApp_name());
					studentJSON.put("Scheme", list.get(i).getScheme());
					studentJSON.put("Name", list.get(i).getCustomer_name());
					studentJSON.put("status", list.get(i).getType());
					studentJSON.put("Latitude", list.get(i).getLati());
					studentJSON.put("Longitude", list.get(i).getLongi());
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
				jArray.put(studentJSON);
			}
			out.println(jArray);
		} else if (action.equalsIgnoreCase("getDomarkersdata")) {
			ArrayList<HashMap<String, Object>> list = MapBal
					.getDoAppliances(ubean.getUserId());
			JSONArray jArray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				try {
					json.put("Type", list.get(i).get("ApplianceName"));
					json.put("status", list.get(i).get("status"));
					json.put("Latitude", list.get(i).get("latitude"));
					json.put("Longitude", list.get(i).get("longitude"));
					json.put("Name", list.get(i).get("CustomerName"));
					json.put("applianceId", list.get(i).get("applianceId"));

				} catch (JSONException e) {
					logger.error(e);
				}
				jArray.put(json);
			}
			out.println(jArray);
		} else if (request.getParameter("action").equals("mapData")) {
			try {
				int applianceId = Integer.parseInt(request
						.getParameter("appId"));
				int lastId = ApplianceStatusBal.getLastStatusId(applianceId);
				JSONObject json = new JSONObject(MapBal.getStatusInfo(lastId,
						applianceId));
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
				e.printStackTrace();
			}

		}

	}

}
