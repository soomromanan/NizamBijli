package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageAPI.MoblinkMessageService;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bal.ApplianceBal;
import bean.UserBean;

@WebServlet(asyncSupported = true, urlPatterns = { "/LoanPaymentController" })
public class LoanPaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoanPaymentController.class);

	public LoanPaymentController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void proccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		String click = request.getParameter("click");

		if (click != null) {
			if (click.equals("assignImei")) {
				String applianceIdString = request.getParameter("applianceId");
				String imei = request.getParameter("imei");
				String gsm = request.getParameter("gsm");
				String csId = request.getParameter("customerId");
				String consumerNum = request.getParameter("consumerNum");
				logger.info("User Name : " + ubean.getUserName()
						+ " , UserID : " + ubean.getUserId() + " , Id : "
						+ applianceIdString + " " + imei + " " + gsm + " "
						+ consumerNum);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						String phone = ApplianceBal.InsertImeiGsmConsumer(
								applianceId, imei, gsm, Integer.valueOf(csId),
								consumerNum);
						String msg = "Aap ka consumer number hai "
								+ consumerNum
								+ ". Nizam Bijli ki mahaana adaaigi aap ko easypaisa / mobicash k "
								+ "zaryay karni hai. Mazeed Maloomat k liye 03111741741 par rabta karin";
						try {
							MoblinkMessageService.SendMessage(phone, msg);
						} catch (Exception e) {
							logger.error("", e);
							e.printStackTrace();
						}
					}
					response.sendRedirect("DoAppliances");
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (click.equals("checkGsm")) {
				if (ApplianceBal.checkGsm(request.getParameter("GSM"))) {
					out.println("0");
				} else {
					out.println("1");
				}

			} else if (click.equals("checkImei")) {
				if (ApplianceBal.checkImei(request.getParameter("Imei"))) {
					out.println("0");
				} else {
					out.println("1");
				}
			} else if (click.equals("checkConsumer")) {
				if (ApplianceBal
						.checkConsumer(request.getParameter("Consumer"))) {
					out.println("0");
				} else {
					out.println("1");
				}

			} else if (click.equals("revoke")) {
				int csId = Integer.parseInt(request.getParameter("customerId"));
				int appid = Integer.parseInt(request
						.getParameter("applianceId"));
				try {
					ApplianceBal.revokeConsumerNum(appid, csId);
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (click.equals("assignConsumerDO")) {
				String applianceIdString = request.getParameter("applianceId");
				String csId = request.getParameter("customerId");
				String consumerNum = request.getParameter("consumerNum");
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						String phone = ApplianceBal.setConsumerAdvance(
								applianceId, csId, consumerNum);
						String msg = "Aap ka consumer number hai "
								+ consumerNum
								+ ". Nizam Bijli ki mahaana adaaigi aap ko easypaisa / mobicash k "
								+ "zaryay karni hai. Mazeed Maloomat k liye 03111741741 par rabta karin";
						try {
							MoblinkMessageService.SendMessage(phone, msg);
						} catch (Exception e) {
							logger.error("", e);
							e.printStackTrace();
						}

					}
					response.sendRedirect("DoWiseAdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}

			}

			else if (click.equals("editConsumerDO")) {
				String applianceIdString = request.getParameter("applianceId");
				String consumerNum = request.getParameter("consumerNum");
				logger.info("User Name : " + ubean.getUserName()
						+ " , UserID : " + ubean.getUserId() + " , Id : "
						+ applianceIdString + " " + consumerNum);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						ApplianceBal.updateConsumerAdvance(applianceId,
								consumerNum);
					}
					response.sendRedirect("DoWiseAdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (click.equals("assignBookingDO")) {

				String applianceIdString = request.getParameter("applianceId");
				String imei = request.getParameter("imei");
				String gsm = request.getParameter("gsm");
				logger.info("User Name : " + ubean.getUserName()
						+ " , UserID : " + ubean.getUserId() + " , Id : "
						+ applianceIdString + " " + imei + " " + gsm);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						ApplianceBal.assignBooking(applianceId, imei, gsm);
						ApplianceBal.insertLoanDueDate(applianceId);
					}
					response.sendRedirect("DoWiseAdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (click.equals("assignConsumer")) {
				String applianceIdString = request.getParameter("applianceId");
				String csId = request.getParameter("customerId");
				String consumerNum = request.getParameter("consumerNum");
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						String phone = ApplianceBal.setConsumerAdvance(
								applianceId, csId, consumerNum);
						String msg = "Aap ka consumer number hai "
								+ consumerNum
								+ ". Nizam Bijli ki mahaana adaaigi aap ko easypaisa / mobicash k "
								+ "zaryay karni hai. Mazeed Maloomat k liye 03111741741 par rabta karin";
						try {
							MoblinkMessageService.SendMessage(phone, msg);
						} catch (Exception e) {
							logger.error("", e);
							e.printStackTrace();
						}
					}
					response.sendRedirect("AdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}
			}

			else if (click.equals("assignBooking")) {
				String applianceIdString = request.getParameter("applianceId");
				String imei = request.getParameter("imei");
				String gsm = request.getParameter("gsm");
				logger.info("User Name : " + ubean.getUserName()
						+ " , UserID : " + ubean.getUserId() + " , Id : "
						+ applianceIdString + " " + imei + " " + gsm);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						ApplianceBal.assignBooking(applianceId, imei, gsm);
						ApplianceBal.insertLoanDueDate(applianceId);
					}
					response.sendRedirect("AdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}
			} else if (click.equals("editConsumer")) {
				String applianceIdString = request.getParameter("applianceId");
				String csId = request.getParameter("customerId");
				String consumerNum = request.getParameter("consumerNum");
				logger.info("User Name : " + ubean.getUserName()
						+ " , UserID : " + ubean.getUserId() + " , Id : "
						+ applianceIdString + " " + consumerNum);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						ApplianceBal.updateConsumerAdvance(applianceId,
								consumerNum);
					}
					response.sendRedirect("AdvanceBooking");
				} catch (Exception e) {
					logger.error("", e);
				}
			}

		}
	}
}