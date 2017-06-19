package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import bal.OldAndroidBal;

@WebServlet("/AndrGetCustomers")
public class AndrGetCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrGetCustomers.class);

	public AndrGetCustomers() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("salesmanId") != null) {
				try {
					int salemanId = Integer.parseInt(request
							.getParameter("salesmanId"));
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("Customers", OldAndroidBal
							.getCustomersDetailBySalesmanId(salemanId));
					jsonObj.put("VLECounters",
							OldAndroidBal.getVLECounters(salemanId));
					out.print(jsonObj);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}

		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
