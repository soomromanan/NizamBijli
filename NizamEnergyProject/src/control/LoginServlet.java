package control;

import bal.DoFoNdBal;
import bal.UserBal;
import bean.DistrictOfficerBean;
import bean.UserBean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import security.Hashing;

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoginServlet.class);

	public LoginServlet() {
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Start================//
		String operatingSystem = "";
		try {
			String browserDetails = request.getHeader("User-Agent");
			String userAgent = browserDetails;

			// =================OS=======================
			if (userAgent.toLowerCase().indexOf("windows") >= 0) {
				operatingSystem = "Windows";
			} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
				operatingSystem = "Mac";
			} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
				operatingSystem = "Unix";
			} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
				operatingSystem = "Android";
			} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
				operatingSystem = "IPhone";
			} else {
				operatingSystem = "UnKnown";
			}
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
		}

		String IpAddress = request.getRemoteAddr();

		try {
			HttpSession session = request.getSession();
			String option = request.getParameter("click");
			session.setMaxInactiveInterval(30 * 60);
			if (option != null) {
				if (option.equals("checkMail")) {
					String email = request.getParameter("userEmail");
					logger.info("Email : " + email + " , IP : " + IpAddress
							+ " , Operating System : " + operatingSystem);
					if (UserBal.checkEmail(email)) {
						out.println("");
					} else {
						out.println("Invalid email");
					}
				} else if (option.equals("logout")) {
					session.invalidate();
					response.sendRedirect("SolarHomeSystemLogin");
				} else if (session.getAttribute("userType") == null) {
					if (option.equals("login")) {
						String email = request.getParameter("userEmail");
						String password = request.getParameter("userPassword");
						String ipAddress = request.getParameter("ipAddress");
						UserBean bean = UserBal.checkUser(email, password);
						password = Hashing.getMD5(password);
						if (bean != null) {
							session.setAttribute("userType", bean.getUserType());
							session.setAttribute("ipAddress", ipAddress);
							int user_type = bean.getUserType();
							if (!password.equals(bean.getPassword())) {
								logger.info("Email : " + email + " , IP : "
										+ IpAddress + " , Operating System : "
										+ operatingSystem);
								request.setAttribute("msg", "Invalid password");
								response.sendRedirect("SolarHomeSystemLogin");
							} else if (email.equals(bean.getEmail())
									&& password.equals(bean.getPassword())
									&& user_type == 100) {
								session.setAttribute("email", bean);
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName()
										+ " , UserID : " + bean.getUserId()
										+ " , IP : " + IpAddress
										+ " , Operating System : "
										+ operatingSystem);

								request.setAttribute("msg", "");
								response.sendRedirect("SuperAdminDashboard");

							} else if (email.equals(bean.getEmail())
									&& password.equals(bean.getPassword())
									&& user_type == 101) {
								DistrictOfficerBean dobean = DoFoNdBal
										.getDO_details(bean.getUserId());
								session.setAttribute("email", bean);
								session.setAttribute("dobean", dobean);
								request.setAttribute("msg", "");
								logger.info("User Name : " + bean.getUserName()
										+ " , UserID : " + bean.getUserId()
										+ " , IP : " + IpAddress
										+ " , Operating System : "
										+ operatingSystem);

								RequestDispatcher rs = request
										.getRequestDispatcher("DistrictOfficerDashboard");
								rs.forward(request, response);

							}
						} else {
							logger.error("User name or password is incorrect");
							request.setAttribute("msg", "Invalid password");
							RequestDispatcher rd = request
									.getRequestDispatcher("SolarHomeSystemLogin");
							rd.include(request, response);
						}
					}
				} else {
					System.out.println(session.getAttribute("userType"));
					if (option.equals("login")) {
						String email = request.getParameter("userEmail");
						String password = request.getParameter("userPassword");
						UserBean bean = UserBal.checkUser(email, password);
						password = Hashing.getMD5(password);
						if (bean != null) {
							DistrictOfficerBean dobean = DoFoNdBal
									.getDO_details(bean.getUserId());
							session.setAttribute("email", bean);
							session.setAttribute("dobean", dobean);
							session.setAttribute("userType", bean.getUserType());
							if (email.equals(bean.getEmail())
									&& password.equals(bean.getPassword())
									&& session.getAttribute("userType")
											.toString().equals("100")) {
								System.out.println("100");
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName()
										+ " , UserID : " + bean.getUserId()
										+ " , IP : " + IpAddress
										+ " , Operating System : "
										+ operatingSystem);

								RequestDispatcher rs = request
										.getRequestDispatcher("SuperAdminDashboard");
								rs.forward(request, response);

							} else if (email.equals(bean.getEmail())
									&& password.equals(bean.getPassword())
									&& session.getAttribute("userType")
											.toString().equals("101")) {
								session.setAttribute("email", bean);
								session.setAttribute("dobean", dobean);
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName()
										+ " , UserID : " + bean.getUserId()
										+ " , IP : " + IpAddress
										+ " , Operating System : "
										+ operatingSystem);

								RequestDispatcher rs = request
										.getRequestDispatcher("DistrictOfficerDashboard");
								rs.forward(request, response);

							}
						} else {
							request.setAttribute("msg", "invalid password");
							RequestDispatcher rd = request
									.getRequestDispatcher("SolarHomeSystemLogin");
							rd.forward(request, response);
						}
					} else if (session.getAttribute("userType").toString()
							.equals("100")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request
								.getRequestDispatcher("SuperAdminDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").toString()
							.equals("101")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request
								.getRequestDispatcher("DistrictOfficerDashboard");
						rs.forward(request, response);
					}
				}
			} else {
				System.out.println("Option is null");
				if (session.getAttribute("userType") != null) {
					System.out.println("If on option null");
					System.out.println("User Type "
							+ session.getAttribute("userType"));
					if (session.getAttribute("userType").toString()
							.equals("100")) {
						System.out.println("User Type:  1000");
						request.setAttribute("msg", "");
						RequestDispatcher rs = request
								.getRequestDispatcher("SuperAdminDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").equals("101")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request
								.getRequestDispatcher("DistrictOfficerDashboard");
						rs.forward(request, response);
					}
				} else {
					System.out.println("else on option null");
					request.setAttribute("msg", "Invalid password");
					RequestDispatcher rd = request
							.getRequestDispatcher("SolarHomeSystemLogin");
					rd.forward(request, response);
				}

			}

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("msg", "Invalid password");
			RequestDispatcher rd = request
					.getRequestDispatcher("SolarHomeSystomLogin");
			rd.forward(request, response);
			e.printStackTrace();
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
