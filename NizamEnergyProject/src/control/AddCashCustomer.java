package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CashSaleBAL;

@WebServlet("/AddCashCustomer")
public class AddCashCustomer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddCashCustomer.class);

	public AddCashCustomer() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter();) {
			String action = request.getParameter("action");
			if (action.equals("addCashCustomers")) {
				int City = Integer.parseInt(request.getParameter("CashCCity"));
				String customerName = request.getParameter("cfirstname");
				String CustomerCnic = request.getParameter("cashCustomerCnic");
				CustomerCnic = CustomerCnic.replaceAll("-", "");
				String customerPhone = request.getParameter("cashCPhone");
				customerPhone = "92" + customerPhone.replaceAll("-", "");
				String customerAddress = request.getParameter("cashCAddress");
				int FoId = Integer.parseInt(request.getParameter("CashCFoId"));
				int NdId = Integer.parseInt(request.getParameter("cashCNdId"));
				String applianceName = request.getParameter("cashCAppliance");
				int appliancePrice = Integer.parseInt(request
						.getParameter("cashCPrice"));
				String consumerNo = request.getParameter("cashCConsumerNo");
				String gsmNumber = request.getParameter("cashCGsmNumber");
				gsmNumber = "92" + gsmNumber.replaceAll("-", "");
				String imeiNumber = request.getParameter("cashCImeiNumber");
				String paymentMethod = request
						.getParameter("cashCPaymentMethod");
				int customerId = 0;
				HashMap<String, String> map = new HashMap<>();
				map.put("City", City + "");
				map.put("customerName", customerName);
				map.put("CustomerCnic", CustomerCnic);
				map.put("customerPhone", customerPhone);
				map.put("customerAddress", customerAddress);
				map.put("FoId", FoId + "");
				map.put("NdId", NdId + "");
				map.put("applianceName", applianceName);
				map.put("appliancePrice", appliancePrice + "");
				map.put("consumerNo", consumerNo);
				map.put("gsmNumber", gsmNumber);
				map.put("imeiNumber", imeiNumber);
				JSONObject json = new JSONObject();

				if (paymentMethod.equals("Cash")) {
					customerId = CashSaleBAL
							.insertCashCustomersPaymentToDO(map);
					if (customerId != 0) {
						try {
							json.put("id", customerId);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							MoblinkMessageService
									.SendMessage(
											customerPhone,
											"Shukariya, Nizam Bijli ki device kharidne k liye, Mazeed Maloomat k liye 03111741741 par rabta karin");
						} catch (JSONException | SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					} else {
						try {
							json.put("id", customerId);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				} else if (paymentMethod.equals("MobileWallet")) {
					customerId = CashSaleBAL
							.insertCashCustomersPaymentByMobileWallet(map);
					String msg = "Aap ka consumer number hai "
							+ consumerNo
							+ ". Nizam Bijli ki payment ki adaigee "
							+ "easypaisa / mobicash k zaryay karni hai. Mazeed Maloomat k liye 03111741741 par rabta karin";
					if (customerId != 0) {
						try {
							json.put("id", customerId);
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
						try {
							MoblinkMessageService.SendMessage(customerPhone,
									msg);
						} catch (JSONException | SQLException e) {
							logger.error(e);
							e.printStackTrace();
						}
					} else {
						try {
							json.put("id", customerId);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				out.print(json);
			}
		} catch (Exception e) {
			logger.equals(e);
		}
	}

}