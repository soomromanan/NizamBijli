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
import org.json.JSONArray;

import bal.ApplianceStatusBal;
import bean.UserBean;

/**
 * Servlet implementation class EnergyAnalyticsController
 */
@WebServlet("/EnergyAnalyticsController")
public class EnergyAnalyticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger
			.getLogger(EnergyAnalyticsController.class);

	public EnergyAnalyticsController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action.equals("topProduction")) {
			try {
				JSONArray json = new JSONArray(
						ApplianceStatusBal.getTopProduction());
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
			}

		} else if (action.equals("leastProduction")) {
			try {
				JSONArray json = new JSONArray(
						ApplianceStatusBal.getLeastProduction());
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
			}

		} else if (action.equals("topConsumption")) {
			try {
				JSONArray json = new JSONArray(
						ApplianceStatusBal.getTopConsumption());
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
			}

		} else if (action.equals("leastConsumption")) {
			try {
				JSONArray json = new JSONArray(
						ApplianceStatusBal.getLeastConsumption());
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
			}

		} else if (action.equals("totalEnergy")) {
			try {
				JSONArray json = new JSONArray(
						ApplianceStatusBal.getTotalEnergy());
				out.println(json);
			} catch (Exception e) {
				logger.error("", e);
			}

		}
	}

}
