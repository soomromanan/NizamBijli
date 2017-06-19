package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.AndroidBal;
import bal.OldAndroidBal;
import sun.misc.BASE64Decoder;

@WebServlet("/EligibilityController")
public class EligibilityController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(EligibilityController.class);

	public EligibilityController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			JSONObject json = new JSONObject();
			if (action != null) {
				if (!action.isEmpty()) {
					if (action.equals("getPaymentsHistory")) {
						String userIdString = request.getParameter("userId");
						String userType = request.getParameter("userType");
						if (userIdString != null) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");
							if (userType != null) {
								json.put("payments", OldAndroidBal
										.getPaymentHistory(userType, userId));
								System.out.println(userType + " => " + userId);
							} else {
								json.put("status", "error");
								json.put("message", "userType not found");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId not defined");
						}

					} else if (action.equals("getDashboardData")) {
						String userIdString = request.getParameter("userId");
						String userType = request.getParameter("userType");
						if (userIdString != null && !userIdString.equals("0")) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");

							if (userType != null && !userType.equals("N/A")) {
								json.put("dashboard", OldAndroidBal
										.getDashboardData(userType, userId));
								if (userType.equals("FO"))
									json.put("counts",
											OldAndroidBal.getFOCounters(userId));
								System.out.println(userType + " => " + userId);
							} else {
								json.put("status", "error");
								json.put("message", "userType not found");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId not defined");
						}

					} else if (action.equals("getAllCustomers")) {
						String userIdString = request.getParameter("userId");
						String userType = request.getParameter("userType");
						if (userIdString != null) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");
							if (userType.equalsIgnoreCase("FO")) {
								json.put("customers", OldAndroidBal
										.getAllCustomersByFO(userId));
								System.out.println("FO => " + userId);
							} else if (userType.equalsIgnoreCase("VLE")) {
								json.put("customers", OldAndroidBal
										.getCustomersDetailBySalesmanId(userId));
								System.out.println("VLE => " + userId);
							} else {
								json.put("status", "error");
								json.put("message", "userType not defined");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId not defined");
						}
					} else if (action.equals("getVerify")) {
						String userIdString = request.getParameter("userId");
						if (userIdString != null) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");
							json.put("verifies", AndroidBal
									.getAllAcceptedEligibilityByFO(userId));
						} else {
							json.put("status", "error");
							json.put("error", "userId not defined");
						}
					} else if (action
							.equals("getAllReadyForPickUpCustomersByFO")) {
						String foIdString = request.getParameter("foId");
						if (foIdString != null) {
							int foId = Integer.parseInt(foIdString);
							json.put("status", "ok");
							json.put("customers", OldAndroidBal
									.getAllReadyForPickUpEligibilityByFO(foId));
						} else {
							json.put("status", "error");
							json.put("error", "foId not defined");
						}
					} else if (action.equals("getInstall")) {
						String userIdString = request.getParameter("userId");
						if (userIdString != null) {
							int userId = Integer.parseInt(userIdString);
							json.put("status", "ok");
							json.put("installs", OldAndroidBal
									.getAllHandoveredEligibilityByFO(userId));
						} else {
							json.put("status", "error");
							json.put("error", "userId not defined");
						}
					} else if (action.equals("updateEligibilityStatus")) {
						String eligibilityIdString = request
								.getParameter("eligibility_id");
						String eligibilityStatusString = request
								.getParameter("eligibility_status");
						if (eligibilityIdString != null
								&& eligibilityStatusString != null) {
							int eligibilityId = Integer
									.parseInt(eligibilityIdString);
							int eligibilityStatus = Integer
									.parseInt(eligibilityStatusString);
							json.put("status", "ok");
							json.put("result", AndroidBal
									.updateEligibilityRequest(eligibilityId,
											eligibilityStatus));
							json.put("text", AndroidBal
									.insertIntoRejection(eligibilityId));
						} else {
							json.put("status", "error");
							json.put("error",
									"eligibility id or eligibility status or not defined");
						}
					} else if (action.equals("getDefaultedCustomers")) {
						String userIdString = request.getParameter("userId");
						String userType = request.getParameter("userType");
						if (userIdString != null) {
							if (!userIdString.isEmpty()) {
								int userId = Integer.parseInt(userIdString);
								if (userType != null) {
									json.put("status", "ok");
									if (userType.equals("FO")) {
										json.put(
												"defaulters",
												OldAndroidBal
														.getDefaultedCustomersByFO(userId));
									} else if (userType.equals("VLE")) {
										json.put("defaulters", OldAndroidBal
												.getDefaultCustomer(userId));
									} else {
										json.put("status", "error");
										json.put("message",
												"userType not found");
									}
								} else {
									json.put("status", "error");
									json.put("message", "userType not defined");
								}
							} else {
								json.put("status", "error");
								json.put("message", "userId is not initialized");
							}
						} else {
							json.put("status", "error");
							json.put("message", "userId is not declared");
						}
					} else if (action.equals("verifyEligibilityStatus")) {

						String eligibilityIdString = request
								.getParameter("eligibility_id");
						String eligibilityStatusString = request
								.getParameter("eligibility_status");
						String applianceGsm = request
								.getParameter("appliance_gsm");
						String applianceIMInumber = request
								.getParameter("appliance_imei");
						String applianceId = request
								.getParameter("appliance_id");
						int customerId = Integer.parseInt(request
								.getParameter("customer_id"));
						int eligibilityId = Integer
								.parseInt(eligibilityIdString);
						int eligibilityStatus = Integer
								.parseInt(eligibilityStatusString);
						int applianceIdInt = Integer.parseInt(applianceId);

						System.out.println(eligibilityId);

						String data[] = AndroidBal.updateAllLoanAccepted(
								applianceIdInt, customerId, applianceGsm,
								applianceIMInumber, eligibilityStatus).split(
								":");

						if (data != null && data[3].equals("OK")) {
							System.out.println("inside the if");
							json.put("status", "ok");
							try {

								System.out.println("phone " + data[0]
										+ " customerName " + data[1]
										+ " applianceName" + data[2]
										+ " status" + data[3] + " downpayment"
										+ data[4]);

								MoblinkMessageService
										.SendMessage(
												data[0],
												"Mubarak ho, aap ki Nizam Bijli k solar sytem k lia manzoori ho gae hai. System install karwanay "
														+ "k lia Rs "
														+ data[4]
														+ " ki adaaigi kijiyay. Nizam Bijli ma cash ka amal dakhal naheen. Aap k Nizam Bijli k"
														+ "solar system ka aik Consumer number ho ga, jo istamaal kartay howay aap ko jazzcash k zaryay apni "
														+ "mahaana adaaigi karni hai.");
							} catch (SQLException e) {
								e.printStackTrace();
							}

						} else {
							json.put("status", "error");
							json.put("message", "Error in Verifying");
						}

					} else if (action.equals("attachments")) {

						String applianceId = request
								.getParameter("applianceId");
						String attachments = request
								.getParameter("attachments");
						logger.info("action :" + action + " customerId "
								+ applianceId);
						String realPath = getServletContext().getRealPath(
								"/Images")
								+ File.separator + applianceId;

						JSONObject obj = new JSONObject(attachments);
						JSONArray arr = obj.getJSONArray("attachments");

						System.out.println(realPath);
						File file = new File(realPath);
						if (!file.exists()) {
							System.out.println("Directory Created");
							file.mkdirs();
						}

						String status = "ok";

						for (int a = 0; a < arr.length(); a++) {
							obj = arr.getJSONObject(a);

							try {
								convertAndSaveImage(request,
										obj.getString(String.valueOf(a)),
										realPath + File.separator + a + ".png");
							} catch (Exception e) {
								status = "error";
								json.put("message", e.toString());
								e.printStackTrace();
							}
						}
						json.put("status", status);
					} else {
						json.put("status", "error");
						json.put("message", "action '" + action + "' not found");
					}
				} else {
					json.put("status", "error");
					json.put("message", "action not intialized");
				}
			} else {
				json.put("status", "error");
				json.put("message", "action not defined");
			}
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void convertAndSaveImage(HttpServletRequest request,
			String base64String, String fileName) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(base64String);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(
				decodedBytes));
		if (image == null) {
			System.err.println("Image Null");
		}
		File f = new File(fileName);
		// write the image
		ImageIO.write(image, "png", f);
	}

}
