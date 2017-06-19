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
import org.json.simple.JSONArray;

import bal.ApplianceBal;
import bean.UserBean;

/**
 * Servlet implementation class AddApplianceController
 */
@WebServlet("/AddApplianceController")
public class AddApplianceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddApplianceController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddApplianceController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);

	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean bean = (UserBean) session.getAttribute("email");
			if (request.getParameter("action").equalsIgnoreCase("testImei")) {
				JSONArray array = new JSONArray();
				String imei = request.getParameter("testImeiValue");
				array.add(ApplianceBal.checkImeiInAppliance(imei));
				out.print(array);
			}

			if (request.getParameter("action")
					.equalsIgnoreCase("testGsmNumber")) {
				JSONArray array = new JSONArray();
				String gsmNumber = request.getParameter("testNumber");
				array.add(ApplianceBal.checkNumberInAppliance(gsmNumber));
				out.print(array);
			}

			if (request.getParameter("action").equalsIgnoreCase("testConsumer")) {
				String consumer = request.getParameter("testConsumer");
				JSONArray array = new JSONArray();
				array.add(ApplianceBal.checkConsumer(consumer));
				out.print(array);
			}

		}
	}

}
