package control;

import bal.DoFoNdBal;
import bean.SalesManBean;

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

@WebServlet("/NizamDostViewController")
public class NizamDostViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(NizamDostViewController.class);

	public NizamDostViewController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("ok")) {
				String id = request.getParameter("salesman_id");
				if (id != null) {
					int SalesmanId = Integer.parseInt(id);
					SalesManBean salesman = DoFoNdBal
							.getSalesManByID(SalesmanId);
					request.setAttribute("salesman", salesman);
					RequestDispatcher rd = request
							.getRequestDispatcher("NizamDostView");
					rd.forward(request, response);
				}
			} else if (option.equals("vleprofile")) {
				String id = request.getParameter("salesman_id");
				if (id != null) {
					int SalesmanId = Integer.parseInt(id);
					SalesManBean salesman = DoFoNdBal
							.getSalesManByID(SalesmanId);
					request.setAttribute("salesman", salesman);
					RequestDispatcher rd = request
							.getRequestDispatcher("doNizamDostProfile.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

}
