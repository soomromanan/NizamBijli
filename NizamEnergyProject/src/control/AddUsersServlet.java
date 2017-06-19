package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.DoFoNdBal;
import bal.DoFoNdFormsBAL;
import bean.CityBean;
import bean.UserBean;

@WebServlet(name = "AddUsersServlet", urlPatterns = { "/AddUsersServlet" })
@MultipartConfig
public class AddUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddUsersServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");
	
		String action = request.getParameter("action");
		if (action.equals("getAllDistricts")) {
			String districtid = request.getParameter("districtid");
			JSONObject json = new JSONObject();
			if (districtid != null) {
				try {
					json.put("data", DoFoNdBal.getDistrict_names());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.print(json);
		} else if (action.equals("getAllCities")) {
			String districtid = request.getParameter("cityId");
			ArrayList<CityBean> cities = DoFoNdBal
					.getDistrictCities(bean.getUser_district());
			JSONObject json = new JSONObject();
			if (cities != null) {
				try {
					json.put("data", cities);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.print(json);
		} else if (action.equals("getFOCities")) {
			int Doid = Integer.parseInt(request.getParameter("DoId"));
			ArrayList<CityBean> cities = DoFoNdBal.getFOCities(Doid);
			JSONObject json = new JSONObject();
			if (cities != null) {
				try {
					json.put("data", cities);
					System.err.println(cities);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.print(json);
		} else if (action.equals("getAllFO")) {
			ArrayList<HashMap<String, String>> folist = DoFoNdBal
					.getFieldOfficers(bean.getUserId());

			JSONObject json = new JSONObject();
			if (folist != null) {
				try {
					json.put("data", folist);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			out.print(json);
		}

		else if (action.equals("getAllFODistrictWise")) {
			ArrayList<HashMap<String, String>> folist = DoFoNdFormsBAL
					.getFieldOfficersbyDistrict(bean.getUser_district());
			JSONObject json = new JSONObject();
			if (folist != null) {
				try {
					json.put("data", folist);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			out.print(json);
		}

	}

}
