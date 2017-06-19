// created by Junaid Ali

package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

import bal.FutureLoanBookBal;
import bal.LoanBookBal;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;
import bean.UserBean;

/**
 * Servlet implementation class SuperAdminLoanBookController
 */
@WebServlet("/SuperAdminLoanBookController")
public class SuperAdminLoanBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(SuperAdminLoanBookController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuperAdminLoanBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");

		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("appliace_key");

			if (id != null) {
				int applianceId = Integer.parseInt((id));

				CustomerLoanBean bean = LoanBookBal
						.getApplianceDetailsForLoanAccount(applianceId);
				List<MonthlyWisePayment> monthlyPayments = LoanBookBal
						.loanBookByApplianceId(applianceId,
								bean.getApplianceName());

				request.setAttribute("appliance", bean);
				request.setAttribute("loanBook", monthlyPayments);

				RequestDispatcher rd = request.getRequestDispatcher("LoanView");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase(
					"getLoanBookTable")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
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
				JSONObject jobject = new JSONObject();
				try {
					int count = LoanBookBal
							.getCountLoanBookSearchCount(search[0]);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", LoanBookBal.getSuperAdminLoanBook(
							start, length, orderDir, orderBy, search[0]));
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);

			} else if (request.getParameter("action").equalsIgnoreCase(
					"getFutureLoanBook")) {

				String date = request.getParameter("toDate");

				Map<String, String[]> parameterMap = request.getParameterMap();
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
				JSONObject jobject = new JSONObject();
				try {
					int count = FutureLoanBookBal
							.getCountFutureLoanBookSearchCount(search[0], date);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", FutureLoanBookBal
							.getFutureSuperAdminLoanBook(start, length,
									orderDir, orderBy, search[0], date));
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);
			} else if (request.getParameter("action").equals("filterLoanBook")) {
				String date = request.getParameter("toDate");
				if (date == null) {
					Map<String, String[]> parameterMap = request
							.getParameterMap();

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

					String[] filters = parameterMap.get("filter");
					int count = 0;
					ArrayList<HashMap<String, String>> loanBook = new ArrayList<>();
					JSONObject jobject = new JSONObject();
					try {
						if (filters[0].equals("maintained")) {
							count = LoanBookBal
									.countLoanBookMaintained(search[0]);
							loanBook = LoanBookBal.getLoanBookMaintained(start,
									length, orderDir, orderBy, search[0]);
						} else if (filters[0].equals("late")) {
							count = LoanBookBal.countLoanBookLate(search[0]);
							loanBook = LoanBookBal.getLoanBookLate(start,
									length, orderDir, orderBy, search[0]);
						} else if (filters[0].equals("defaulter")) {
							count = LoanBookBal
									.countLoanBookDefaulter(search[0]);
							loanBook = LoanBookBal.getLoanBookDefaulter(start,
									length, orderDir, orderBy, search[0]);
						} else if (filters[0].equals("owned")) {
							count = LoanBookBal.countLoanBookOwned(search[0]);
							loanBook = LoanBookBal.getLoanBookOwned(start,
									length, orderDir, orderBy, search[0]);
						} else if (filters[0].equals("cash")) {
							count = LoanBookBal.countLoanBookCash(search[0]);
							loanBook = LoanBookBal.getLoanBookCash(start,
									length, orderDir, orderBy, search[0]);
						} else if (filters[0].equals("all")) {
							count = FutureLoanBookBal
									.getCountFutureLoanBookSearchCount(
											search[0], date);
							loanBook = FutureLoanBookBal
									.getFutureSuperAdminLoanBook(start, length,
											orderDir, orderBy, search[0], date);
						}

						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("loanBooksCount", count);
						jobject.put("data", loanBook);
					} catch (JSONException e) {
						logger.error(e);
						e.printStackTrace();
					}
					out.print(jobject);
				}

				else if (date != null) {

					Map<String, String[]> parameterMap = request
							.getParameterMap();

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

					String[] filters = parameterMap.get("filter");
					int count = 0;
					ArrayList<HashMap<String, String>> loanBook = new ArrayList<>();
					JSONObject jobject = new JSONObject();
					try {
						if (filters[0].equals("maintained")) {
							count = FutureLoanBookBal
									.countFutureLoanBookMaintained(search[0],
											date);
							loanBook = FutureLoanBookBal
									.getFutureLoanBookMaintained(start, length,
											orderDir, orderBy, search[0], date);
						} else if (filters[0].equals("late")) {
							count = FutureLoanBookBal.countFutureLoanBookLate(
									search[0], date);
							loanBook = FutureLoanBookBal.getFutureLoanBookLate(
									start, length, orderDir, orderBy,
									search[0], date);
						} else if (filters[0].equals("defaulter")) {
							count = FutureLoanBookBal
									.countFutureLoanBookDefaulter(search[0],
											date);
							loanBook = FutureLoanBookBal
									.getFutureLoanBookDefaulter(start, length,
											orderDir, orderBy, search[0], date);
						} else if (filters[0].equals("all")) {
							count = FutureLoanBookBal
									.getCountFutureLoanBookSearchCount(
											search[0], date);
							loanBook = FutureLoanBookBal
									.getFutureSuperAdminLoanBook(start, length,
											orderDir, orderBy, search[0], date);
						}

						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("loanBooksCount", count);
						jobject.put("data", loanBook);
					} catch (JSONException e) {
						logger.error(e);
						e.printStackTrace();
					}
					out.print(jobject);
				}
			}

			else if (request.getParameter("action").equals("countFilters")) {

				String date = request.getParameter("toDate");

				if (date == null || date.equals("null")) {
					try {
						JSONObject jsonObject = new JSONObject(
								LoanBookBal.countLoanBookFilters());
						out.print(jsonObject);
					} catch (Exception e) {
						logger.error("", e);
					}
				}

				else if (date != null && !date.equals("null")) {
					try {
						JSONObject json = new JSONObject(
								FutureLoanBookBal
										.countFutureLoanBookFilters(date));
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}

			else if (request.getParameter("action")
					.equals("getFutureLoanBooks")) {
				try {
					JSONArray json = new JSONArray(
							FutureLoanBookBal.futureLoanBooks());
					out.println(json);
				} catch (Exception e) {
					logger.error("", e);
				}
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
