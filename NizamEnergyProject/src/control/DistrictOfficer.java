package control;

import bal.DoFoNdBal;
import bean.DistrictOfficerBean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "DistrictOfficer", urlPatterns = { "/DistrictOfficer" })
public class DistrictOfficer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DistrictOfficer.class);

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");

			String id = request.getParameter("do_id");
			if (id != null) {
				int districtId = Integer.parseInt(id);
				DistrictOfficerBean bean = DoFoNdBal
						.getDO_details(districtId);
				request.setAttribute("bean", bean);
				request.setAttribute("district", districtId);
				RequestDispatcher rd = request
						.getRequestDispatcher("DistrictOfficerProfile");
				rd.forward(request, response);
			}
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
