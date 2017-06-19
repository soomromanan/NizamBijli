package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.LoanRequestBal;
import bean.UserBean;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(RequestServlet.class);

	public RequestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			if (request.getParameter("action").equals("getRequest")) {
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
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = LoanRequestBal
							.getLoanRequest(start, length, orderBy, orderDir,
									search[0]);
					int count = LoanRequestBal.getLoanRequestCount(search[0]);

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("sa_loan_apps_counts", count);
					json.put("data", list);

				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("getDoRequest")) {
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
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = LoanRequestBal
							.getDoLoanRequest(start, length, orderBy, orderDir,
									search[0], ubean.getUserId());
					int count = LoanRequestBal.getDoLoanRequestCount(search[0],
							ubean.getUserId());

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("loan_apps_count", count);
					json.put("data", list);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("countFilters")) {
				try {
					HashMap<String, Integer> map = LoanRequestBal
							.countDoLoanRequestFilters(ubean.getUserId());
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			if (request.getParameter("action").equals("countSAFilters")) {
				try {
					HashMap<String, Integer> map = LoanRequestBal
							.countSALoanRequestFilters();
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			if (request.getParameter("action").equals(
					"filterSALoanApplications")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];
				String[] search = parameterMap.get("search[value]");

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = LoanRequestBal
						.countSALoanRequestFiltersWithSearch(search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = LoanRequestBal
								.getSALoanRequestPending(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = LoanRequestBal
								.getSALoanRequestAccepted(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = LoanRequestBal
								.getSALoanRequestVarified(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("ccVerification")) {
					count = map.get("ccVerification");
					try {
						loanApplications = LoanRequestBal
								.getSALoanRequestCCVerification(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("sa_loan_apps_counts", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);
			}
			if (request.getParameter("action").equals("filterLoanApplications")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];
				String[] search = parameterMap.get("search[value]");

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = LoanRequestBal
						.countDoLoanRequestFiltersWithSearch(ubean.getUserId(),
								search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = LoanRequestBal
								.getDoLoanRequestPending(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = LoanRequestBal
								.getDoLoanRequestAccepted(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = LoanRequestBal
								.getDoLoanRequestVarified(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("ccVerification")) {
					count = map.get("cc_varified");
					try {
						loanApplications = LoanRequestBal
								.getDoLoanRequestCCVerification(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loan_apps_count", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);

			}
			if (request.getParameter("action").equals("getPrePayment")) {

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
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = LoanRequestBal
							.getPrePayments(start, length, orderBy, orderDir,
									search[0]);
					int count = LoanRequestBal.getBookingsCount(search[0]);

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("sa_prepayments", count);
					json.put("data", list);

				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("getPrePaymentDO")) {
				int userId = ubean.getUserId();
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
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = LoanRequestBal
							.getPrePaymentsDO(start, length, orderBy, orderDir,
									search[0], userId);
					int count = LoanRequestBal.getPrePaymentCountDO(search[0],
							userId);

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("loan_apps_count", count);
					json.put("data", list);

				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("countPPFilters")) {
				try {
					HashMap<String, Integer> map = LoanRequestBal
							.countPrePaymentFilters();
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			if (request.getParameter("action").equals("filterPrePayments")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];
				String[] search = parameterMap.get("search[value]");

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = LoanRequestBal
						.countSAPrePaymentsFiltersWithSearch(search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = LoanRequestBal
								.getPPLoanRequestPending(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = LoanRequestBal
								.getSAPrePaymentAccepted(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = LoanRequestBal
								.getSAPrePaymentVarified(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("ccVerification")) {
					count = map.get("ccVerification");
					try {
						loanApplications = LoanRequestBal
								.getSAPrePaymentCCVerification(start, length,
										orderBy, orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("booked")) {
					count = map.get("booked");
					try {
						loanApplications = LoanRequestBal
								.getSAPrePaymentBooked(start, length, orderBy,
										orderDir, search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("sa_loan_apps_counts", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);
			}
			if (request.getParameter("action").equals("countPPFiltersDO")) {
				try {
					HashMap<String, Integer> map = LoanRequestBal
							.countDoPrePaymentsFilters(ubean.getUserId());
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}

			if (request.getParameter("action").equals("filterPrePaymentsDO")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap
						.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap
						.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];
				String[] search = parameterMap.get("search[value]");

				String[] filters = parameterMap.get("filter");
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = LoanRequestBal
						.countDoPrePaymentsFiltersWithSearch(ubean.getUserId(),
								search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = LoanRequestBal
								.getDoPrePaymentsPending(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = LoanRequestBal
								.getDoPrePaymentsAccepted(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = LoanRequestBal
								.getDoPrePaymentstVarified(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("ccVerification")) {
					count = map.get("cc_varified");
					try {
						loanApplications = LoanRequestBal
								.getDoPrePaymentsCCVerification(start, length,
										orderBy, orderDir, search[0],
										ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("booked")) {
					count = map.get("booked");
					try {
						loanApplications = LoanRequestBal
								.getDoPrePaymentsbooked(start, length, orderBy,
										orderDir, search[0], ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loan_apps_count", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);

			}

		} catch (Exception e) {
			logger.error(e);
		}

	}

}
