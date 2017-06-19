package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.FinanceBAL;
import bal.PaymentsBal;
import bean.FinanceBean;
import bean.UserBean;

@WebServlet("/FinanceController")
public class FinanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FinanceController.class);

	public FinanceController() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		String dateTo = "0";
		String dateFrom = "0";

		// PrintWriter out = response.getWriter();
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase(
					"getpaymentorderby")) {
				dateTo = request.getParameter("to");
				dateFrom = request.getParameter("from");
				Date date = new Date();
				DateFormat curdate = null;
				String currDate;
				if (dateTo == "" && dateFrom == "") {
					curdate = new SimpleDateFormat("E yyyy/MM/dd");
					currDate = curdate.format(date);
				} else {
					currDate = "To: " + dateTo + "  From: " + dateFrom;
				}
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]); // (Page

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				int installmentPayments = PaymentsBal
						.getInstallmentPaymentCount(dateTo, dateFrom);
				int downPayments = PaymentsBal.getDownPaymentCount(dateTo,
						dateFrom);

				List<FinanceBean> list = null;
				int count = 0;
				// String app_name="";
				double total_istallments = 0, down_payments = 0, total_amount = 0, Fo_Commission = 0, Nd_commision = 0, total_commission = 0;
				int newdown = 0;
				int newinstallment = 0;

				if (!search[0].isEmpty()) {

					list = PaymentsBal.getPayments_Search_fliter(dateTo,
							dateFrom, start, length, col, order, search[0]);
					count = PaymentsBal.getPaymentSearchCount(dateTo, dateFrom,
							search[0]);

				} else {
					list = PaymentsBal.getPayments_fliter(dateTo, dateFrom,
							start, length, col, order);
					count = PaymentsBal.getPaymentCount(dateTo, dateFrom);

				}

				if (count > 0) {
					FinanceBean financeBean = list.get(0);
					total_istallments = financeBean
							.getTotal_installement_payments();

					newdown = financeBean.getNew_down_pament_count();
					newinstallment = financeBean.getNewInstallment();
					// app_name=financeBean.getApp_name();
					down_payments = financeBean.getTotal_down_pament();
					int down_payments_count = financeBean
							.getDown_payment_count();
					// total_amount = financeBean.getTotal_amount();
					Fo_Commission = (float) total_istallments
							* ((float) .5 / 100) + down_payments_count * 200
							+ newdown * 400;
					// float percent=(float)(((float)7/100*2700))+3*1000;
					Nd_commision = (float) total_istallments
							* ((float) 7 / 100) + down_payments_count * 1000
							+ newdown * 1200;

					total_commission = Fo_Commission + Nd_commision;
					total_amount = down_payments + total_istallments
							+ newinstallment;
					total_istallments = total_istallments + newinstallment;
				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				jobject.put("data", list);
				jobject.put("curdate", currDate);
				jobject.put("total_istallments", total_istallments);
				jobject.put("down_payments", down_payments);
				jobject.put("total_amount", total_amount);
				jobject.put("fo_commission", Fo_Commission);
				jobject.put("nd_commission", Nd_commision);
				jobject.put("total_commission", total_commission);
				jobject.put("installmentCounts", installmentPayments);
				jobject.put("downPaymentCounts", downPayments);

				out.print(jobject);

			} else if (request.getParameter("action").equals("getinstallments")) {

				dateTo = request.getParameter("to");
				dateFrom = request.getParameter("from");

				Date date = new Date();
				DateFormat curdate = null;
				String currDate;
				if (dateTo == "" && dateFrom == "") {
					curdate = new SimpleDateFormat("E yyyy/MM/dd");
					currDate = curdate.format(date);
				} else {
					currDate = "To: " + dateTo + "  From: " + dateFrom;
				}
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]); 

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				int count = 0;

				int installmentPayments = 0, downPayments = 0;

				List<FinanceBean> list = null;

				double total_istallments = 0, down_payments = 0, total_amount = 0, Fo_Commission = 0, Nd_commision = 0, total_commission = 0;

				if (!search[0].isEmpty()) {

					list = PaymentsBal.getPaymentsStatus_Search_fliter(dateTo,
							dateFrom, start, length, col, order, search[0], 0);
					count = PaymentsBal.getPaymentStatusSearchCount(dateTo,
							dateFrom, search[0], 0);

				} else {

					list = PaymentsBal.getPaymentsStatus_fliter_Using_Date(
							dateTo, dateFrom, start, length, col, order, 0);
					count = PaymentsBal.getPaymentStatusCount(dateTo, dateFrom,
							0);

				}

				if (count > 0) {
					FinanceBean financeBean = list.get(0);
					total_istallments = financeBean
							.getTotal_installement_payments();

					down_payments = financeBean.getTotal_down_pament();
					int down_payments_count = financeBean
							.getDown_payment_count();
					int newdown = financeBean.getNew_down_pament_count();
					int newinstallment = financeBean.getNewInstallment();

					// total_amount = financeBean.getTotal_amount();
					Fo_Commission = (float) total_istallments
							* ((float) .5 / 100) + down_payments_count * 200
							+ newdown * 400;
					// float percent=(float)(((float)7/100*2700))+3*1000;
					Nd_commision = (float) total_istallments
							* ((float) 7 / 100) + down_payments_count * 1000
							+ newdown * 1200;
					total_commission = Fo_Commission + Nd_commision;
					total_amount = down_payments + total_istallments
							+ newinstallment;
					total_istallments = total_istallments + newinstallment;
					installmentPayments = PaymentsBal
							.getInstallmentPaymentCount(dateTo, dateFrom);
					downPayments = PaymentsBal.getDownPaymentCount(dateTo,
							dateFrom);

				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				jobject.put("data", list);
				jobject.put("curdate", currDate);
				jobject.put("total_istallments", total_istallments);
				jobject.put("down_payments", 0);
				jobject.put("total_amount", total_amount);
				jobject.put("fo_commission", Fo_Commission);
				jobject.put("nd_commission", Nd_commision);
				jobject.put("total_commission", total_commission);
				jobject.put("installmentCounts", installmentPayments);
				jobject.put("downPaymentCounts", downPayments);

				out.print(jobject);
			} else if (request.getParameter("action").equals("getdownpayments")) {

				dateTo = request.getParameter("to");
				dateFrom = request.getParameter("from");

				Date date = new Date();
				DateFormat curdate = null;
				String currDate;
				if (dateTo == "" && dateFrom == "") {
					curdate = new SimpleDateFormat("E yyyy/MM/dd");
					currDate = curdate.format(date);
				} else {
					currDate = "To: " + dateTo + "  From: " + dateFrom;
				}
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				int installmentPayments = 0, downPayments = 0;

				List<FinanceBean> list = null;
				int count = 0;
				double total_istallments = 0, down_payments = 0, total_amount = 0, Fo_Commission = 0, Nd_commision = 0, total_commission = 0;

				if (!search[0].isEmpty()) {

					list = PaymentsBal.getPaymentsStatus_Search_fliter(dateTo,
							dateFrom, start, length, col, order, search[0], 1);
					count = PaymentsBal.getPaymentStatusSearchCount(dateTo,
							dateFrom, search[0], 1);

				} else {

					list = PaymentsBal.getPaymentsStatus_fliter_Using_Date(
							dateTo, dateFrom, start, length, col, order, 1);
					count = PaymentsBal.getPaymentStatusCount(dateTo, dateFrom,
							1);

				}

				if (count > 0) {
					int newinstallment = 0;
					FinanceBean financeBean = list.get(0);
					total_istallments = financeBean
							.getTotal_installement_payments();
					down_payments = financeBean.getTotal_down_pament();
					int down_payments_count = financeBean
							.getDown_payment_count();
					int newdown = financeBean.getNew_down_pament_count();
					newinstallment = financeBean.getNewInstallment();
					// total_istallments=total_istallments + newinstallment;
					Fo_Commission = (float) total_istallments
							* ((float) .5 / 100) + down_payments_count * 200
							+ newdown * 400;
					// float percent=(float)(((float)7/100*2700))+3*1000;
					Nd_commision = (float) total_istallments
							* ((float) 7 / 100) + down_payments_count * 1000
							+ newdown * 1200;

					total_istallments = total_istallments + newinstallment;
					total_commission = Fo_Commission + Nd_commision;
					total_amount = down_payments + total_istallments
							+ newinstallment;
					installmentPayments = PaymentsBal
							.getInstallmentPaymentCount(dateTo, dateFrom);
					downPayments = PaymentsBal.getDownPaymentCount(dateTo,
							dateFrom);

				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				jobject.put("data", list);
				jobject.put("curdate", currDate);
				jobject.put("total_istallments", 0);
				jobject.put("down_payments", down_payments);
				jobject.put("total_amount", total_amount);
				jobject.put("fo_commission", Fo_Commission);
				jobject.put("nd_commission", Nd_commision);
				jobject.put("total_commission", total_commission);
				jobject.put("installmentCounts", installmentPayments);
				jobject.put("downPaymentCounts", downPayments);

				out.print(jobject);
			}

			else if (request.getParameter("action").equalsIgnoreCase(
					"cash_customer")) {
				dateTo = request.getParameter("to");
				dateFrom = request.getParameter("from");

				Date date = new Date();
				DateFormat curdate = null;
				String currDate;
				if (dateTo == "" && dateFrom == "") {
					curdate = new SimpleDateFormat("E yyyy/MM/dd");
					currDate = curdate.format(date);
				} else {
					currDate = "To: " + dateTo + "  From: " + dateFrom;
				}
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				ArrayList<HashMap<String, String>> list = null;
				int count = 0;
				double total_sale = 0, total_amount = 0, Fo_Commission = 0, Nd_commision = 0, total_commission = 0;
				;
				if (!search[0].isEmpty()) {
					list = FinanceBAL.getCashPayments_Search_fliter(dateTo,
							dateFrom, start, length, col, order, search[0]);
					for (int i = 0; i < 1; i++) {

						Iterator it = (Iterator) list.get(i).entrySet()
								.iterator();
						while (it.hasNext())
							while (it.hasNext()) {
								Map.Entry pairs = (Map.Entry) it.next();
								if (pairs.getKey() == "fo_commission") {
									Fo_Commission = Double
											.parseDouble((String) (pairs
													.getValue()));
								}

								else if (pairs.getKey() == "nd_commission") {
									Nd_commision = Double
											.parseDouble((String) (pairs
													.getValue()));
								}

								else if (pairs.getKey() == "total_sale") {
									total_sale = Double
											.parseDouble((String) (pairs
													.getValue()));
								} else if (pairs.getKey() == "total_Amount") {
									total_amount = Double
											.parseDouble((String) (pairs
													.getValue()));
								} else if (pairs.getKey() == "total_commission") {
									total_commission = Double
											.parseDouble((String) (pairs
													.getValue()));
								}
							}

					}
					count = FinanceBAL.getCashPaymentSearchCount(dateTo,
							dateFrom, search[0]);

				} else {
					list = FinanceBAL.getCashPayments_fliter(dateTo, dateFrom,
							start, length, col, order);
					if (list.size() > 1) {
						for (int i = 0; i < 1; i++) {

							Iterator it = (Iterator) list.get(i).entrySet()
									.iterator();

							while (it.hasNext())
								while (it.hasNext()) {
									Map.Entry pairs = (Map.Entry) it.next();
									if (pairs.getKey() == "fo_commission") {
										Fo_Commission = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

									else if (pairs.getKey() == "nd_commission") {
										Nd_commision = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

									else if (pairs.getKey() == "total_sale") {
										total_sale = Double
												.parseDouble((String) (pairs
														.getValue()));
									} else if (pairs.getKey() == "total_Amount") {
										total_amount = Double
												.parseDouble((String) (pairs
														.getValue()));
									} else if (pairs.getKey() == "total_commission") {
										total_commission = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

								}

						}
					}
					count = FinanceBAL.getCashPaymentCount(dateTo, dateFrom);
				}

				if (count > 0) {
					// FinanceBean financeBean = list.get(0);
					// total_istallments = financeBean
					// .getTotal_installement_payments();
					// down_payments = financeBean.getTotal_down_pament();
					// int
					// down_payments_count=financeBean.getDown_payment_count();
					// total_amount = financeBean.getTotal_amount();
					// Fo_Commission=(float)total_istallments*((float).5/100)+down_payments_count*200;
					// float percent=(float)(((float)7/100*2700))+3*1000;
					// Nd_commision=(float)total_istallments*((float)7/100)+down_payments_count*1000;

				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				JSONArray jsonArray = new JSONArray(list);
				jobject.put("data", jsonArray);
				jobject.put("curdate", currDate);
				jobject.put("total_sale", total_sale);
				jobject.put("total_Amount", total_amount);

				jobject.put("fo_commission", Fo_Commission);
				jobject.put("nd_commission", Nd_commision);
				jobject.put("total_commission", total_commission);

				out.print(jobject);

			} else if (request.getParameter("action").equalsIgnoreCase(
					"DOcash_customer")) {

				dateTo = request.getParameter("to");
				dateFrom = request.getParameter("from");

				Date date = new Date();
				DateFormat curdate = null;
				String currDate;
				if (dateTo == "" && dateFrom == "") {
					curdate = new SimpleDateFormat("E yyyy/MM/dd");
					currDate = curdate.format(date);
				} else {
					currDate = "To: " + dateTo + "  From: " + dateFrom;
				}
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]); // (Page
				// No/Start
				// Position)

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				ArrayList<HashMap<String, String>> list = null;
				int count = 0;
				double total_sale = 0, total_amount = 0, Fo_Commission = 0, Nd_commision = 0, total_commission = 0;
				;
				if (!search[0].isEmpty()) {

					// list = FinanceBAL.getCashPayments_Search_fliter(dateTo,
					// dateFrom, start, length, col, order, search[0]);
					list = FinanceBAL.getCashPayments_Search_fliterDO(dateTo,
							dateFrom, start, length, col, order, search[0],
							ubean.getUserId());
					for (int i = 0; i < 1; i++) {

						Iterator it = (Iterator) list.get(i).entrySet()
								.iterator();
						while (it.hasNext())
							while (it.hasNext()) {
								Map.Entry pairs = (Map.Entry) it.next();
								if (pairs.getKey() == "fo_commission") {
									Fo_Commission = Double
											.parseDouble((String) (pairs
													.getValue()));
								}

								else if (pairs.getKey() == "nd_commission") {
									Nd_commision = Double
											.parseDouble((String) (pairs
													.getValue()));
								}

								else if (pairs.getKey() == "total_sale") {
									total_sale = Double
											.parseDouble((String) (pairs
													.getValue()));
								} else if (pairs.getKey() == "total_Amount") {
									total_amount = Double
											.parseDouble((String) (pairs
													.getValue()));
								} else if (pairs.getKey() == "total_commission") {
									total_commission = Double
											.parseDouble((String) (pairs
													.getValue()));
								}

							}

					}
					count = FinanceBAL.getCashPaymentSearchCountDO(dateTo,
							dateFrom, search[0], ubean.getUserId());

				} else {
					list = FinanceBAL.getCashPayments_fliterDO(dateTo,
							dateFrom, start, length, col, order,
							ubean.getUserId());
					if (list.size() > 1) {
						for (int i = 0; i < 1; i++) {

							Iterator it = (Iterator) list.get(i).entrySet()
									.iterator();

							while (it.hasNext())
								while (it.hasNext()) {
									Map.Entry pairs = (Map.Entry) it.next();
									if (pairs.getKey() == "fo_commission") {
										Fo_Commission = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

									else if (pairs.getKey() == "nd_commission") {
										Nd_commision = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

									else if (pairs.getKey() == "total_sale") {
										total_sale = Double
												.parseDouble((String) (pairs
														.getValue()));
									} else if (pairs.getKey() == "total_Amount") {
										total_amount = Double
												.parseDouble((String) (pairs
														.getValue()));
									} else if (pairs.getKey() == "total_commission") {
										total_commission = Double
												.parseDouble((String) (pairs
														.getValue()));
									}

								}

						}
					}

					count = FinanceBAL.getCashPaymentCountDO(dateTo, dateFrom,
							ubean.getUserId());
				}

				if (count > 0) {
					// FinanceBean financeBean = list.get(0);
					// total_istallments = financeBean
					// .getTotal_installement_payments();
					// down_payments = financeBean.getTotal_down_pament();
					// int
					// down_payments_count=financeBean.getDown_payment_count();
					// total_amount = financeBean.getTotal_amount();
					// Fo_Commission=(float)total_istallments*((float).5/100)+down_payments_count*200;
					// float percent=(float)(((float)7/100*2700))+3*1000;
					// Nd_commision=(float)total_istallments*((float)7/100)+down_payments_count*1000;

				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				JSONArray jsonArray = new JSONArray(list);
				jobject.put("data", jsonArray);
				jobject.put("curdate", currDate);
				jobject.put("total_sale", total_sale);
				jobject.put("total_Amount", total_amount);

				jobject.put("fo_commission", Fo_Commission);
				jobject.put("nd_commission", Nd_commision);
				jobject.put("total_commission", total_commission);

				out.print(jobject);

			}
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
