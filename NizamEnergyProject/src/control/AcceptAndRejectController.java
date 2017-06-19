package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.LoanRequestBal;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "AcceptAndRejectController", urlPatterns = { "/AcceptAndRejectController" })
public class AcceptAndRejectController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AcceptAndRejectController.class);

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("accept")) {
				int eligibilityId = Integer.parseInt(request
						.getParameter("eligibilityId"));
				logger.info("User Name : " + ubean.getUserName()
						+ " , EligibilityId : " + eligibilityId);
				int status = LoanRequestBal
						.acceptLoanRequestBySuperadmin(eligibilityId);
				if (status == 1) {
					RequestDispatcher rd = request
							.getRequestDispatcher("/Request");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("/AdvanceBooking.jsp");
					rd.forward(request, response);
				}

			} else if (option.equals("reject")) {
				int customerId = Integer.parseInt(request
						.getParameter("customerId"));
				int applianceId = Integer.parseInt(request
						.getParameter("applianceId"));
				String text = request.getParameter("text");
				logger.info("User Name : " + ubean.getUserName()
						+ " , applianceId : " + applianceId);
				LoanRequestBal.rejecttLoanRequestBySuperadmin(customerId, applianceId, text);
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
