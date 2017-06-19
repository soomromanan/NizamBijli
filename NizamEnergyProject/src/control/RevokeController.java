package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bal.CustomerBal;

@WebServlet("/RevokeController")
public class RevokeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(RevokeController.class);

	public RevokeController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("click");
			if (action.equals("revoke")) {
				int applianceId = Integer.parseInt(request
						.getParameter("applianceId"));
				CustomerBal.revokeCustomer(applianceId);
				response.sendRedirect("customer.jsp");
			} else if (action.equals("doRevoke")) {
				int applianceId = Integer.parseInt(request
						.getParameter("applianceId"));
				CustomerBal.revokeCustomer(applianceId);
				response.sendRedirect("LoanRequest");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
