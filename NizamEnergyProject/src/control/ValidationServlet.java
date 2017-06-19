package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.CustomerBal;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ValidationServlet.class);

	public ValidationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {

			String column = request.getParameter("column");
			String table = request.getParameter("table");
			String type = request.getParameter("type");
			String phoneNumber = request.getParameter("PhoneNumber");

			if (type.equalsIgnoreCase("cnic")) {

			} else {
				phoneNumber = phoneNumber.replace("-", "");
			}

			JSONObject object = null;
			if (type.equals("cnic") || type.equals("email")
					|| type.equals("phone") || type.equals("imei")
					|| type.equals("consumer")) {
				object = new JSONObject();
				if (type.equals("phone")) {
					if (phoneNumber != "") {
						phoneNumber = "92" + phoneNumber;
					}
				}
				object.put(type, CustomerBal.getCustomerNumber(column, table,
						phoneNumber));
			}
			out.print(object);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}