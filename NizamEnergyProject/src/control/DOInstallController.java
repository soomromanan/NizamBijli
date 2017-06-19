package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bal.ApplianceBal;
import bean.UserBean;

@WebServlet("/DOInstallController")
public class DOInstallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DOInstallController.class);

	public DOInstallController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		String click = request.getParameter("click");
		if (click.equals("install")) {
			int appId = Integer.parseInt(request.getParameter("applianceId"));
			logger.info("User Name : " + ubean.getUserName() + " , UserID : "
					+ ubean.getUserId() + " , Id : " + appId);
			try {
				ApplianceBal.installDevice(appId);
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			response.sendRedirect("DoAppliances");
		}
		if (click.equals("varifySignals")) {
			int appId = Integer.parseInt(request.getParameter("applianceId"));
			logger.info("User Name : " + ubean.getUserName() + " , UserID : "
					+ ubean.getUserId() + " , Id : " + appId);
			try {
				ApplianceBal.installNoSignalDevice(appId);
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			response.sendRedirect("DoAppliances");
		}
		if (click.equals("SAInstall")) {
			int appId = Integer.parseInt(request.getParameter("applianceId"));
			logger.info("User Name : " + ubean.getUserName() + " , UserID : "
					+ ubean.getUserId() + " , Id : " + appId);
			try {
				ApplianceBal.installDevice(appId);
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			response.sendRedirect("Appliances");
		}
		if (click.equals("SAVarifySignals")) {
			int appId = Integer.parseInt(request.getParameter("applianceId"));
			logger.info("User Name : " + ubean.getUserName() + " , UserID : "
					+ ubean.getUserId() + " , Id : " + appId);
			try {
				ApplianceBal.installNoSignalDevice(appId);
			} catch (SQLException e) {
				logger.error("", e);
				e.printStackTrace();
			}
			response.sendRedirect("Appliances");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
