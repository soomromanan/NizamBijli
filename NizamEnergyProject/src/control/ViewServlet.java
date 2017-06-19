package control;

import bal.ApplianceBal;
import bal.CustomerRetrieveDataBAL;
import bean.ApplianceBean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

import org.apache.log4j.Logger;

@WebServlet(name = "ViewServlet", urlPatterns = { "/ViewServlet" })
public class ViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ViewServlet.class);

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		PrintWriter out = response.getWriter();
		try {
			String option = request.getParameter("click");
			if (option.equals("doview")) {
				String customerId = request.getParameter("id");
				String cnic = request.getParameter("cnic");
				String appId = request.getParameter("applianceId");
				if (customerId != null && cnic != null) {
					request.setAttribute("customerId", customerId);
					request.setAttribute("cnic", cnic);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 1);
					RequestDispatcher rd = request
							.getRequestDispatcher("doCustomerProfile.jsp");
					rd.forward(request, response);
				}
			} else if (option.equals("doCustomerView")) {
				String cnic = request.getParameter("cnic");
				String customerId = request.getParameter("id");
				String appId = request.getParameter("applianceId");
				if (cnic != null) {
					request.setAttribute("customerId", customerId);
					request.setAttribute("cnic", cnic);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 2);
					RequestDispatcher rd = request
							.getRequestDispatcher("doCustomerProfile.jsp");
					rd.forward(request, response);
				}
			} else if (option.equals("view")) {
				String customerId = request.getParameter("id");
				String cnic = request.getParameter("cnic");
				String appId = request.getParameter("appId");
				if (customerId != null && cnic != null) {
					int status = CustomerRetrieveDataBAL.getLoanStatus(Integer
							.parseInt(appId));
					request.setAttribute("customerId", customerId);
					request.setAttribute("cnic", cnic);
					request.setAttribute("status", status);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 2);
					RequestDispatcher rd = request
							.getRequestDispatcher("CustomerProfile.jsp");
					rd.forward(request, response);
				}
			} // end of else if
			else if (option.equals("viewRequest")) {
				String customerId = request.getParameter("id");
				String cnic = request.getParameter("cnic");
				String appId = request.getParameter("appId");
				if (customerId != null && cnic != null) {
					int status = CustomerRetrieveDataBAL.getLoanStatus(Integer
							.parseInt(appId));
					request.setAttribute("customerId", customerId);
					request.setAttribute("cnic", cnic);
					request.setAttribute("status", status);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 1);
					RequestDispatcher rd = request
							.getRequestDispatcher("CustomerProfile.jsp");
					rd.forward(request, response);
				}
			} // end of else if
			else if (option.equals("viewLoanRequest")) {
				String customerId = request.getParameter("id");
				String cnic = request.getParameter("cnic");
				String appId = request.getParameter("appId");
				if (customerId != null && cnic != null) {
					int status = CustomerRetrieveDataBAL.getLoanStatus(Integer
							.parseInt(appId));
					request.setAttribute("customerId", customerId);
					request.setAttribute("cnic", cnic);
					request.setAttribute("status", status);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 3);
					RequestDispatcher rd = request
							.getRequestDispatcher("CustomerProfile.jsp");
					rd.forward(request, response);
				}
			} // end of else if

			else if (option.equals("viewAppliance")) {
				try {
					String id = request.getParameter("id");

					if (id != null) {
						int applianceId = Integer.parseInt(id);
						ApplianceBean bean = ApplianceBal
								.getApplianceInfo(applianceId);
						request.setAttribute("bean", bean);
						RequestDispatcher rd = request
								.getRequestDispatcher("ApplianceView");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

				}
			} // end of else if
			else if (option.equals("viewDoAppliance")) {
				try {
					String id = request.getParameter("id");
					if (id != null) {
						int applianceId = Integer.parseInt(id);
						ApplianceBean bean = ApplianceBal
								.getApplianceInfo(applianceId);
						request.setAttribute("bean", bean);
						RequestDispatcher rd = request
								.getRequestDispatcher("doApplianceView.jsp");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

				}
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
