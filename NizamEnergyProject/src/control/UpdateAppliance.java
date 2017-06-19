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

import bal.ApplianceBal;
import bean.UserBean;

/**
 * Servlet implementation class UpdateAppliance
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppliance" })
public class UpdateAppliance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateAppliance.class);

	public UpdateAppliance() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		response.setContentType("text/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			if (action.equals("updateAppliance")) {
				String applianceGSM = request.getParameter("appGsm");
				String applianceImei = request.getParameter("appImei");
				int appId = Integer.parseInt(request.getParameter("appId"));
				int customerId = Integer.parseInt(request
						.getParameter("customerId"));
				logger.info("User Name : " + ubean.getUserName()
						+ " customerId : " + customerId + " appId : " + appId
						+ " " + applianceImei + " " + applianceGSM);
				applianceGSM = applianceGSM.replace("-", "");
				JSONObject json = new JSONObject();
				String number = null;
				try {
					number = ApplianceBal.replaceAppliance(applianceGSM,
							applianceImei, appId, customerId);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				json.put("data", number);
				out.print(json);
			} else if (request.getParameter("action")
					.equals("updateCredential")) {
				int applianceId = Integer.parseInt(request
						.getParameter("appId"));
				String consumer = request.getParameter("consumer");
				String imei = request.getParameter("appImei");
				String gsm = request.getParameter("appGsm");
				gsm = gsm.replace("-", "");
				gsm = gsm.replace(" ", "");
				logger.info("User Name : " + ubean.getUserName() + " IMEI "
						+ imei + " Consumer " + consumer + " GSM " + gsm);
				try {
					ApplianceBal.updateImeiGsmConsumer(applianceId, gsm, imei,
							consumer);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (request.getParameter("action").equals(
					"updateCredentials")) {
				int applianceId = Integer.parseInt(request
						.getParameter("appId"));
				String consumer = request.getParameter("consumer");
				String imei = request.getParameter("appImei");
				String gsm = request.getParameter("appGsm");
				gsm = "92" + gsm.replace("-", "");
				logger.info("User Name : " + ubean.getUserName() + " IMEI "
						+ imei + " Consumer " + consumer + " GSM " + gsm);
				try {
					ApplianceBal.updateImeiGsmConsumer(applianceId, gsm, imei,
							consumer);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
