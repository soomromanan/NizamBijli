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
 * Servlet implementation class DoLoanBookController
 */
@WebServlet("/DoLoanBookController")
public class DoLoanBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoLoanBookController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLoanBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		String option = request.getParameter("action");
		if (option.equals("viewloan")) {
			String id = request.getParameter("applianceId");
			if (id != null) {
				int applianceId = Integer.parseInt((id));
				CustomerLoanBean bean = LoanBookBal
						.getApplianceDetailsForLoanAccount(applianceId);
				List<MonthlyWisePayment> monthlyPayments = LoanBookBal
						.loanBookByApplianceId(applianceId,
								bean.getApplianceName());

				request.setAttribute("appliance", bean);
				request.setAttribute("loanBook", monthlyPayments);

				RequestDispatcher rd = request
						.getRequestDispatcher("doLoanbookView.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");

		int userId = ubean.getUserId();

		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase(
					"getDoLoanBookTable")) {
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
				int count = LoanBookBal.getDoLoanBookSearchCount(userId,
						search[0]);
				ArrayList<HashMap<String, String>> list = LoanBookBal
						.getDoLoanBook(start, length, orderDir, orderBy,
								userId, search[0]);
				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("loanbook_count", count);
				jobject.put("data", list);
				out.print(jobject);

			}
			if (request.getParameter("action").equalsIgnoreCase(
					"getDoFutureLoanBookssTable")) {

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
							.getDoFutureLoanBookSearchCount(userId, search[0],
									date);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", FutureLoanBookBal.getDoFutureLoanBook(
							start, length, orderDir, orderBy, userId,
							search[0], date));
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);
			}

			if (request.getParameter("action").equals("filterLoanBook")) {

				String date = request.getParameter("toDate");

				if (date == null) {
					Map<String, String[]> parameterMap = request
							.getParameterMap();

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
					ArrayList<HashMap<String, String>> loanBook = new ArrayList<>();

					if (filters[0].equals("maintained")) {
						count = LoanBookBal.countDoLoanBookMaintained(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBookMaintained(start,
								length, orderDir, orderBy, userId, search[0]);

					} else if (filters[0].equals("late")) {
						count = LoanBookBal.countDoLoanBookLate(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBookLate(start, length,
								orderDir, orderBy, userId, search[0]);
					} else if (filters[0].equals("defaulter")) {
						count = LoanBookBal.countDoLoanBookDefaulter(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBookDefaulter(start,
								length, orderDir, orderBy, userId, search[0]);
					} else if (filters[0].equals("owned")) {
						count = LoanBookBal.countDoLoanBookOwned(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBookOwned(start,
								length, orderDir, orderBy, userId, search[0]);
					} else if (filters[0].equals("cash")) {
						count = LoanBookBal.countDoLoanBookCash(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBookCash(start, length,
								orderDir, orderBy, userId, search[0]);
					}

					else if (filters[0].equals("all")) {
						count = LoanBookBal.getDoLoanBookSearchCount(userId,
								search[0]);
						loanBook = LoanBookBal.getDoLoanBook(start, length,
								orderDir, orderBy, userId, search[0]);
					}
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", loanBook);
					out.print(jobject);
				}
				if (date != null) {
					Map<String, String[]> parameterMap = request
							.getParameterMap();

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
					ArrayList<HashMap<String, String>> loanBook = new ArrayList<>();

					if (filters[0].equals("maintained")) {
						count = FutureLoanBookBal
								.countDoFutureLoanBookMaintained(userId,
										search[0], date);
						loanBook = FutureLoanBookBal
								.getDoFutureLoanBookMaintained(start, length,
										orderDir, orderBy, userId, search[0],
										date);

					} else if (filters[0].equals("late")) {
						count = FutureLoanBookBal.countDoFutureLoanBookLate(
								userId, search[0], date);
						loanBook = FutureLoanBookBal.getDoFutureLoanBookLate(
								start, length, orderDir, orderBy, userId,
								search[0], date);
					} else if (filters[0].equals("defaulter")) {
						count = FutureLoanBookBal
								.countDoFutureLoanBookDefaulter(userId,
										search[0], date);
						loanBook = FutureLoanBookBal
								.getDoFutureLoanBookDefaulter(start, length,
										orderDir, orderBy, userId, search[0],
										date);
					} else if (filters[0].equals("all")) {
						count = FutureLoanBookBal
								.getDoFutureLoanBookSearchCount(userId,
										search[0], date);
						loanBook = FutureLoanBookBal.getDoFutureLoanBook(start,
								length, orderDir, orderBy, userId, search[0],
								date);
					}
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", loanBook);
					out.print(jobject);
				}
			}

			if (request.getParameter("action").equals("countFilters")) {
				String date = request.getParameter("toDate");
				if (date == null || date.equals("null")) {
					try {
						HashMap<String, Integer> countLoanBookFilters = LoanBookBal
								.countDoLoanBookFilters(userId);
						JSONObject jsonObject = new JSONObject(
								countLoanBookFilters);
						out.print(jsonObject);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
				if (date != null && !date.equals("null")) {
					try {
						JSONObject json = new JSONObject(
								FutureLoanBookBal.countDoFutureLoanBookFilters(
										userId, date));
						out.println(json);
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}

			if (request.getParameter("action").equals("getDoFutureLoanBookss")) {
				ArrayList<HashMap<String, String>> countLoanBookFilters = FutureLoanBookBal
						.countDoFutureLoanBookFilters(userId);
				JSONArray jsonObject = new JSONArray(countLoanBookFilters);
				System.err.println(countLoanBookFilters.size());
				out.print(jsonObject);
			}

		} catch (JSONException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
