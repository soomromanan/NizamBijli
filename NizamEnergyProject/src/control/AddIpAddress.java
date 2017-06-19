package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bal.UserBal;
import bean.UserBean;

/**
 * Servlet implementation class AddIpAddress
 */
@WebServlet("/AddIpAddress")
public class AddIpAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddIpAddress.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddIpAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");

		String localIP = request.getParameter("localIpAddress").toString();
		String publicIp = request.getRemoteAddr();

		int userId = bean.getUserId();
		UserBal.addUserIp(userId, localIP, publicIp);

	}

}
