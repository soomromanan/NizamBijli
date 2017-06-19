package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerFormBAL;
import bal.CustomerFormWizardBAL;
import bal.CustomerRetrieveDataBAL;
import bal.DoFoNdBal;
import bean.CityBean;
import bean.SalesManBean;
import bean.UserBean;

@WebServlet("/CustomerFormController")
public class CustomerFormController extends HttpServlet {
	final static Logger logger = Logger.getLogger(CustomerFormController.class);
	private static final long serialVersionUID = 1L;

	public CustomerFormController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private CustomerFormWizardBAL customerFormWizardBAL;
	private CustomerFormBAL customerBAL;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean bean = (UserBean) session.getAttribute("email");
			String action = request.getParameter("action");
			customerFormWizardBAL = new CustomerFormWizardBAL();
			customerBAL = new CustomerFormBAL();
			if (action.equals("getCustomerForm")) {
				out.print(getCustomerForm(request));
			}
			if (action.equals("insertCustomerInfo")) {
				out.print(insertCustomerInfo(request));
			}
			if (action.equals("insertCustomerIncomeSource")) {
				out.print(insertCustomerIncomeSource(request));
			}
			if (action.equals("insertIncomeSource")) {
				out.print(insertIncomeSource(request));
			}
			if (action.equals("insertCustomerEmployeeInfo")) {
				out.print(insertCustomerEmployeeInfo(request));
			}
			if (action.equals("insertCustomerBusinessInfo")) {
				out.print(insertCustomerBusinessInfo(request));
			}
			if (action.equals("insertCustomerExpense")) {
				out.print(insertCustomerExpense(request));
			}
			if (action.equals("insertMonthlyExpense")) {
				out.print(insertMonthlyExpense(request));
			}
			if (action.equals("insertCustomerLivestockAssets")) {
				out.print(insertCustomerLivestockAssets(request));
			}
			if (action.equals("insertLoan")) {
				out.print(insertLoan(request));
			}

			if (action.equals("insertAssets")) {
				out.print(insertAssets(request));
			}

			if (action.equals("getSchemeFrequencies")) {
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("data", CustomerFormBAL.getLoan());
				} catch (Exception e) {
					logger.error("", e);
					jobject.put("status", "error");
					jobject.put("message", e.getMessage());
				}
				out.print(jobject);
			}
			if (action.equals("insertGuarantor")) {
				out.print(insertGuarantor(request));
			}
			if (action.equals("insertVleGuarantor")) {
				JSONObject json = new JSONObject();
				try {
					int customerId = Integer.parseInt(request
							.getParameter("customerId"));
					int salesmanId = Integer.parseInt(request
							.getParameter("salesmanId"));
					int id = customerBAL.insertCustomerVleGaurantor(customerId,
							salesmanId);
					json.put("status", "ok");
					json.put("data", id);
				} catch (Exception e) {
					logger.error("", e);
					json.put("status", "error");
					json.put("message", e.getMessage());
				}
				out.print(json);
			}
			if (action.equals("getCnic")) {
				JSONObject json = new JSONObject();
				try {
					String cnic = request.getParameter("customerCnic");
					String str = customerBAL.getCnic(cnic);
					if (str.equalsIgnoreCase(cnic)) {
						json.put("status", "ok");
					} else {
						json.put("status", "not");
					}
				} catch (Exception e) {
					logger.error("", e);
					json.put("status", "error");
					json.put("message", e.getMessage());
				}
				out.print(json);
			}
			if (action.equals("insertAppliance")) {
				out.print(insertAppliance(request));
			}
			if (action.equals("getCustomerFormVerification")) {
				out.print(getCustomerVerificationData(request));
			}
			if (action.equals("getCustomerProfile")) {
				out.print(getCustomerProfile(request));
			}

			if (action.equals("ccVerify")) {

				String verifyND = request.getParameter("ndVerifiedStatus");
				String verifyFG = request.getParameter("fgVerifiedStatus");
				String verifyOG = request.getParameter("ogVerifiedStatus");
				String verifyCS = request.getParameter("csVerifiedStatus");
				String verifyALL = request.getParameter("allVerifiedStatus");
				String eligId = request.getParameter("eligId");

				HashMap<String, String> map = null;

				if (verifyND != null && eligId != null) {

					int eligibilityId = Integer.parseInt(eligId);
					int verifyNd = Integer.parseInt(verifyND);

					map = new HashMap<String, String>();
					map.put("eligibilityId", eligibilityId + "");

					map.put("ndQ1", request.getParameter("ndQ1"));
					map.put("ndQ2", request.getParameter("ndQ2"));
					map.put("ndQ3", request.getParameter("ndQ3"));
					map.put("ndQ4", request.getParameter("ndQ4"));
					map.put("ndQ5", request.getParameter("ndQ5"));
					map.put("ndVerifiedStatus", verifyNd + "");

					CustomerRetrieveDataBAL.verifyCustomerByCC(map,
							eligibilityId);
				}
				if (verifyFG != null && eligId != null) {

					int eligibilityId = Integer.parseInt(eligId);
					int verifyFg = Integer.parseInt(verifyFG);

					map = new HashMap<String, String>();

					map.put("eligibilityId", eligibilityId + "");
					map.put("fgQ1", request.getParameter("fgQ1"));
					map.put("fgQ2", request.getParameter("fgQ2"));
					map.put("fgQ3", request.getParameter("fgQ3"));
					map.put("fgQ4", request.getParameter("fgQ4"));
					map.put("fgQ5", request.getParameter("fgQ5"));
					map.put("fgVerifiedStatus", verifyFg + "");

					CustomerRetrieveDataBAL.verifyCustomerByCC(map,
							eligibilityId);

				}
				if (verifyOG != null && eligId != null) {

					int eligibilityId = Integer.parseInt(eligId);
					int verifyOg = Integer.parseInt(verifyOG);

					map = new HashMap<String, String>();

					map.put("ogQ1", request.getParameter("ogQ1"));
					map.put("ogQ2", request.getParameter("ogQ2"));
					map.put("ogQ3", request.getParameter("ogQ3"));
					map.put("ogQ4", request.getParameter("ogQ4"));
					map.put("ogQ5", request.getParameter("ogQ5"));
					map.put("ogVerifiedStatus", verifyOg + "");

					CustomerRetrieveDataBAL.verifyCustomerByCC(map,
							eligibilityId);

				}
				if (verifyCS != null && eligId != null) {

					int eligibilityId = Integer.parseInt(eligId);
					int verifyCs = Integer.parseInt(verifyCS);

					map = new HashMap<String, String>();

					map.put("csQ1", request.getParameter("csQ1"));
					map.put("csQ2", request.getParameter("csQ2"));
					map.put("csQ3", request.getParameter("csQ3"));
					map.put("csQ4", request.getParameter("csQ4"));
					map.put("csQ5", request.getParameter("csQ5"));
					map.put("csQ6", request.getParameter("csQ6"));
					map.put("csVerifiedStatus", verifyCs + "");

					CustomerRetrieveDataBAL.verifyCustomerByCC(map,
							eligibilityId);
				}

				if (verifyALL != null && eligId != null) {
					JSONObject json = new JSONObject();

					try {
						int eligibilityId = Integer.parseInt(eligId);
						int verifyAll = Integer.parseInt(verifyALL);

						map = new HashMap<String, String>();
						map.put("verifyAll", verifyAll + "");

						CustomerRetrieveDataBAL.verifyCustomerByCC(map,
								eligibilityId);

					} catch (Exception je) {
						json.put("status", "error");
						json.put("message", je.getStackTrace());
					}
				}
			}
			if (action.equals("updatePricePlan")) {
				int scheme = Integer.valueOf(request
						.getParameter("scheme_plan"));
				int downpayment = Integer.valueOf(request
						.getParameter("downpayment"));
				int monthly_payment = Integer.valueOf(request
						.getParameter("monthly_payment"));
				int total_payment = Integer.valueOf(request
						.getParameter("total_payment"));
				String appName = request.getParameter("appName");
				int appId = Integer.valueOf(request.getParameter("appId"));
				JSONObject json = new JSONObject();
				try {
					int row = CustomerFormBAL.updatePricePlan(appId, scheme,
							downpayment, monthly_payment, total_payment,
							appName);
					String result = "";
					if (row == 1) {
						result = "Updated";
					} else {
						result = "Not Updated";
					}
					json.put("ok", result);
				} catch (Exception e) {
					logger.error("", e);
				}
				out.print(json);
			}

			if (action.equals("getDOCities")) {
				String districtid = request.getParameter("districtid");
				JSONObject json = new JSONObject();
				if (districtid != null) {
					if (!districtid.isEmpty()) {
						ArrayList<CityBean> districtCities = DoFoNdBal
								.getDistrictCities(Integer.parseInt(districtid));
						if (districtCities != null) {
							json.put("status", "ok");
							json.put("data", districtCities);
						} else {
							json.put("status", "error");
							json.put("message", "district cities is null");
						}
					} else {
						json.put("status", "error");
						json.put("message", "doid is empty");
					}
				} else {
					json.put("status", "error");
					json.put("message", "doid is null");
				}
				out.print(json);
			}

			if (action.equals("getVLEs")) {
				String id = request.getParameter("districtid");
				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> nizamdost = DoFoNdBal
							.getNizamDosts(Integer.parseInt(id));
					if (nizamdost != null) {
						json.put("status", "ok");
						json.put("data", nizamdost);
					} else {
						json.put("status", "error");
						json.put("message", "salesmen is null");
					}
				} catch (Exception e) {
					json.put("message", e.getStackTrace());
					e.printStackTrace();
				}
				out.print(json);
			}

			if (action.equals("getVLE")) {
				JSONObject json = new JSONObject();
				try {
					ArrayList<SalesManBean> salesmen = DoFoNdBal
							.getSallesman(Integer.parseInt(request
									.getParameter("userId")));
					if (salesmen != null) {
						json.put("status", "ok");
						json.put("data", salesmen);
					} else {
						json.put("status", "error");
						json.put("message", "salesmen is null");
					}
				} catch (Exception e) {
					json.put("message", e.getStackTrace());
					e.printStackTrace();
				}
				out.print(json);
			}
			if (action.equals("getDistricts")) {
				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> districts = DoFoNdBal.getDistrict();
					if (districts != null) {
						json.put("status", "ok");
						json.put("data", districts);
					} else {
						json.put("status", "error");
						json.put("message", "districts is null");
					}
				} catch (Exception e) {
					logger.error("", e);
					json.put("message", e.getStackTrace());
					e.printStackTrace();
				}
				out.print(json);
			}

			if (action.equals("getCustomerOtherPhoneNumbers")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {
						json.put("data", CustomerRetrieveDataBAL
								.getCustomerOtherPhoneDetails(custId));
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}

			if (action.equals("getCustomerOtherIncome")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {
						json.put("data",
								CustomerRetrieveDataBAL.getOtherIncome(custId));
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}
			if (action.equals("getCustomerOtherAssets")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {
						json.put("data", CustomerRetrieveDataBAL
								.getOtherAssetsOfCustomer(custId));
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}

			if (action.equals("getCustomerMonthlyExpenses")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {
						json.put("data", CustomerRetrieveDataBAL
								.getOtherMonthlyExpenses(custId));
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}

			if (action.equals("getCustomerLoanAndLiabilities")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {
						json.put("data", CustomerRetrieveDataBAL
								.getLoanAndLiabilities(custId));
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}
			if (action.equals("getCustomerGuarantors")) {
				JSONObject json = new JSONObject();
				String customerId = request.getParameter("customerId");
				int gType = Integer.parseInt(request.getParameter("gType"));
				if (customerId != null) {
					int custId = Integer.parseInt(customerId);
					try {

						if (gType == 1) {
							json.put("data", CustomerRetrieveDataBAL
									.getGuaranterDetailById(custId, gType));
						} else if (gType == 2) {
							json.put("data", CustomerRetrieveDataBAL
									.getGuaranterDetailById(custId, gType));
						}
					} catch (Exception e) {
						logger.error("", e);
						json.put("message", e.getStackTrace());
						e.printStackTrace();
					}
					out.print(json);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSONObject insertAppliance(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			int customerId = Integer.parseInt(request
					.getParameter("customerId"));
			int vleId = Integer.parseInt(request.getParameter("vleId"));

			String[] applianceName = request
					.getParameterValues("data[appliance][]");
			String[] salesType = request.getParameterValues("data[saleType][]");
			String[] scheme = request.getParameterValues("data[schemes][]");
			String[] advance = request.getParameterValues("data[advance][]");
			String[] monthly = request.getParameterValues("data[monthly][]");
			String[] total = request.getParameterValues("data[total][]");

			int[] id = customerBAL.insertApplianceInEligibility(customerId,
					vleId, applianceName, scheme, advance, monthly, total,
					salesType);
			json.put("status", "ok");
			json.put("data", id);
		} catch (Exception e) {
			try {
				json.put("status", "error");
				json.put("message", e.getStackTrace());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertGuarantor(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			int customerId = Integer.parseInt(request
					.getParameter("customerId"));
			int guarantorType = Integer.parseInt(request
					.getParameter("guarantorType"));

			HashMap<String, String> map = new HashMap<>();
			map.put("customerId", customerId + "");
			map.put("guarantorType", guarantorType + "");

			map.put("name", request.getParameter("data[name]"));
			map.put("cnic", request.getParameter("data[cnic]"));
			map.put("cellNumber", request.getParameter("data[cellNumber]"));
			map.put("secondaryCellNumber",
					request.getParameter("data[secondaryCellNumber]"));
			map.put("relation", request.getParameter("data[relation]"));
			map.put("incomeSource", request.getParameter("data[incomeSource]"));
			map.put("guarantorProfession",
					request.getParameter("data[guarantorProfession]"));

			int id = customerBAL.insertCustomerGuarantorInfo(map, customerId,
					guarantorType);
			json.put("status", "ok");
			json.put("data", id);
		} catch (Exception e) {
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertAssets(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String[] types = request.getParameterValues("data[types][]");
			String[] amounts = request.getParameterValues("data[amounts][]");
			String customerId = request.getParameter("customerId");
			if (customerId != null && types != null && amounts != null) {
				HashMap<String, String> map = new HashMap<>();
				for (int i = 0; i < types.length; i++) {
					if (!types[i].isEmpty() && !amounts[i].isEmpty())
						map.put(types[i], amounts[i]);
				}
				json.put("data", customerBAL.insertAssets(
						Integer.parseInt(customerId), map));
				json.put("status", "ok");
			} else {
				json.put("status", "error");
				json.put("message", "customer id or assets is null");
			}

		} catch (Exception e) {
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertMonthlyExpense(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerId = request.getParameter("customerId");
		HashMap<String, String> map = new HashMap<>();
		try {
			if (customerId != null) {
				if (!customerId.isEmpty()) {
					String[] types = request
							.getParameterValues("data[monthlyExpenseType][]");
					String[] amounts = request
							.getParameterValues("data[monthlyExpenseAmount][]");
					if (types != null && amounts != null) {
						for (int i = 0; i < types.length; i++) {
							map.put(types[i], amounts[i]);
						}
						json.put("status", "ok");
						json.put(
								"data",
								customerBAL.insertMonthlyExpense(
										Integer.parseInt(customerId), map));
					}
				} else {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id is null");
			}
		} catch (NumberFormatException | JSONException e) {
			logger.error("", e);
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject insertIncomeSource(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerId = request.getParameter("data[customerId]");
		try {
			if (customerId != null) {
				if (!customerId.isEmpty()) {
					HashMap<String, String> incomeSourceMap = new HashMap<>();
					HashMap<String, String> incomemmothlyYearly = new HashMap<>();
					incomeSourceMap.put("customerId", customerId);
					incomeSourceMap.put("salaryOrPension",
							request.getParameter("data[salaryOrPension]"));
					incomeSourceMap.put("businessIncome",
							request.getParameter("data[businessIncome]"));
					incomeSourceMap.put("farmingIncome",
							request.getParameter("data[farmingIncome]"));
					incomeSourceMap
							.put("familyContribution",
									request.getParameter("data[customer_contributers_amount]"));
					incomeSourceMap
							.put("customer_income_comtributers",
									request.getParameter("data[customer_income_comtributers]"));

					incomemmothlyYearly.put("customerId", customerId);
					incomemmothlyYearly
							.put("customer_total_income_name",
									request.getParameter("data[customer_total_income_name]"));
					incomemmothlyYearly
							.put("customer_total_irregularly_income_name",
									request.getParameter("data[customer_total_irregularly_income_name]"));
					incomemmothlyYearly
							.put("agricultureProfitFrequency",
									request.getParameter("data[agricultureProfitFrequency]"));
					incomemmothlyYearly.put("customerLivestockIncome", request
							.getParameter("data[customerLivestockIncome]"));
					incomemmothlyYearly
							.put("customerLivestockType",
									request.getParameter("data[customerLivestockType]"));
					incomemmothlyYearly
							.put("customerLivestockOthertype",
									request.getParameter("data[customerLivestockOthertype]"));
					incomemmothlyYearly.put("customerRemittances",
							request.getParameter("data[customerRemittances]"));
					incomemmothlyYearly
							.put("customerRemittancesFrequency",
									request.getParameter("data[customerRemittancesFrequency]"));
					incomemmothlyYearly
							.put("customerRemittancesRelation",
									request.getParameter("data[customerRemittancesRelation]"));
					incomemmothlyYearly
							.put("customer_remittances_profession",
									request.getParameter("data[customer_remittances_profession]"));
					incomemmothlyYearly
							.put("customer_rental_profit_income",
									request.getParameter("data[customer_rental_profit_income]"));
					incomemmothlyYearly
							.put("customer_rental_frequency",
									request.getParameter("data[customer_rental_frequency]"));
					incomemmothlyYearly.put("customer_rental_from",
							request.getParameter("data[customer_rental_from]"));
					incomemmothlyYearly.put("customer_labour_amount", request
							.getParameter("data[customer_labour_amount]"));
					incomemmothlyYearly.put("customer_labour_type",
							request.getParameter("data[customer_labour_type]"));
					incomemmothlyYearly.put("customer_labour_in_month", request
							.getParameter("data[customer_labour_in_month]"));
					incomemmothlyYearly
							.put("customer_other_labour_type",
									request.getParameter("data[customer_other_labour_type]"));

					customerBAL.updateCustomerIncomeSource(incomeSourceMap);
					customerBAL.inserMonthlyYearlyIncome(incomemmothlyYearly);
					HashMap<String, String> otherIncomeSource = new HashMap<>();
					String[] types = request
							.getParameterValues("data[otherIncomeSourcesType][]");
					String[] amounts = request
							.getParameterValues("data[otherIncomeSourcesAmount][]");
					int i = 0;
					if (types != null) {
						for (String type : types) {
							otherIncomeSource.put(type, amounts[i]);
							i++;
						}
						json.put("otherIncomeSourceIds", customerBAL
								.insertOtherIncomeResource(
										Integer.parseInt(customerId),
										otherIncomeSource));
					}
					json.put("status", "ok");

				} else {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id is null");
			}
		} catch (JSONException e) {
			logger.error("", e);
			try {
				json.put("status", "error");
				json.put("message", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject getCustomerForm(HttpServletRequest request) {
		String customerIdString = request.getParameter("customerId");
		JSONObject json = new JSONObject();
		if (customerIdString != null) {
			if (!customerIdString.isEmpty()) {
				HashMap<String, Object> customerForm = customerFormWizardBAL
						.getCustomerForm(Integer.parseInt(customerIdString));
				try {
					json.put("customerForm", new JSONObject(customerForm));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} 
		return json;

	}

	private JSONObject insertCustomerInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		HashMap<String, String> map = new HashMap<>();

		try {
			map.put("customerIdd", request.getParameter("customerId"));
			map.put("applianceId", request.getParameter("applianceId"));

			map.put("education_medical_amount",
					request.getParameter("education_medical_amount") == null
							|| request.getParameter("education_medical_amount") == ""
							|| request.getParameter("education_medical_amount") == "null" ? "0"
							: request.getParameter("education_medical_amount"));
			map.put("city",
					request.getParameter("city") == null ? "" : request
							.getParameter("city"));
			map.put("province", request.getParameter("province") == "" ? ""
					: request.getParameter("province"));

			map.put("address", request.getParameter("address") == null ? ""
					: request.getParameter("address"));
			map.put("customer_union_council", request
					.getParameter("customer_union_council") == null ? ""
					: request.getParameter("customer_union_council"));
			map.put("requesteddate",
					request.getParameter("requesteddate") == null ? ""
							: request.getParameter("requesteddate"));
			map.put("vle",
					request.getParameter("vle") == null ? "0" : request
							.getParameter("vle"));
			map.put("customer_sys_installed_place",
					request.getParameter("customer_sys_installed_place"));
			map.put("customer_installment_address",
					request.getParameter("customer_installment_address"));

			map.put("fullname", request.getParameter("fullname") == null ? ""
					: request.getParameter("fullname"));
			map.put("fatherName",
					request.getParameter("fatherName") == null ? "" : request
							.getParameter("fatherName"));
			map.put("gender", request.getParameter("gender") == null ? ""
					: request.getParameter("gender"));
			map.put("dateOfBirth",
					request.getParameter("dateOfBirth") == null ? "" : request
							.getParameter("dateOfBirth"));
			map.put("customerCaste",
					request.getParameter("customerCaste") == null ? ""
							: request.getParameter("customerCaste"));
			map.put("cnic",
					request.getParameter("cnic") == null ? "" : request
							.getParameter("cnic"));
			map.put("primaryPhone",
					request.getParameter("primaryPhone") == null ? "" : request
							.getParameter("primaryPhone"));
			map.put("secondaryPhone",
					request.getParameter("secondaryPhone") == null ? ""
							: request.getParameter("secondaryPhone"));

			map.put("maritalStatus",
					request.getParameter("maritalStatus") == null
							&& !request.getParameter("maritalStatus").equals(
									"on") ? "" : request
							.getParameter("maritalStatus"));
			map.put("education", request.getParameter("education") == null ? ""
					: request.getParameter("education"));
			map.put("customer_disibility",
					request.getParameter("customer_disibility") == null ? ""
							: request.getParameter("customer_disibility"));
			map.put("customer_training_skill", request
					.getParameter("customer_training_skill") == null ? ""
					: request.getParameter("customer_training_skill"));
			map.put("customer_course_name", request
					.getParameter("customer_course_name") == null ? ""
					: request.getParameter("customer_course_name"));
			map.put("customer_head_faimly", request
					.getParameter("customer_head_faimly") == null ? ""
					: request.getParameter("customer_head_faimly"));
			map.put("customer_total_household_members",
					request.getParameter("customer_total_household_members") == null ? ""
							: request
									.getParameter("customer_total_household_members"));
			map.put("customer_dependants_members", request
					.getParameter("customer_dependants_members") == null ? ""
					: request.getParameter("customer_dependants_members"));
			map.put("customer_children",
					request.getParameter("customer_children") == null ? ""
							: request.getParameter("customer_children"));
			map.put("customer_education_amount", request
					.getParameter("customer_education_amount") == null ? ""
					: request.getParameter("customer_education_amount"));
			map.put("customer_total_adult_members", request
					.getParameter("customer_total_adult_members") == null ? ""
					: request.getParameter("customer_total_adult_members"));
			map.put("customer_medical_recuring", request
					.getParameter("customer_medical_recuring") == null ? ""
					: request.getParameter("customer_medical_recuring"));
			map.put("customer_medical_recuring_amount",
					request.getParameter("customer_medical_recuring_amount") == null ? ""
							: request
									.getParameter("customer_medical_recuring_amount"));
			map.put("customer_families_type", request
					.getParameter("customer_families_type") == null ? ""
					: request.getParameter("customer_families_type"));

			map.put("customer_families_household", request
					.getParameter("customer_families_household") == null ? ""
					: request.getParameter("customer_families_household"));
			map.put("customer_roomoccupied_household",
					request.getParameter("customer_roomoccupied_household") == null ? ""
							: request
									.getParameter("customer_roomoccupied_household"));
			map.put("customer_residence",
					request.getParameter("customer_residence") == null ? ""
							: request.getParameter("customer_residence"));
			map.put("customer_other_residence", request
					.getParameter("customer_other_residence") == null ? ""
					: request.getParameter("customer_other_residence"));
			map.put("customer_residence_period", request
					.getParameter("customer_residence_period") == null ? ""
					: request.getParameter("customer_residence_period"));

			int customerId = customerBAL.insertCustomer(map);
			if (customerId > 0
					|| Integer.parseInt(request.getParameter("customerId")) > 0) {
				customerBAL.insertEducationMedicalAmount(customerId, (Double
						.parseDouble(map.get("education_medical_amount"))));
			}
			if (customerId > 0
					&& Integer.parseInt(request
							.getParameter("user_form_status")) == 0) {

				ArrayList<HashMap<String, String>> list = new ArrayList<>();

				String[] whoIsOther = request
						.getParameterValues("data[who_is_other][]");
				String[] relationCustomer = request
						.getParameterValues("data[relation_with_customer][]");
				String[] otherPhone = request
						.getParameterValues("data[customer_other_phone][]");

				if (whoIsOther != null) {
					for (int k = 0; k < whoIsOther.length; k++) {
						HashMap<String, String> mapgeneral = new HashMap<>();
						mapgeneral.put("whoIsOther",
								whoIsOther[k].isEmpty() ? "" : whoIsOther[k]);
						mapgeneral.put("relationCustomer", relationCustomer[k]
								.isEmpty() ? "" : relationCustomer[k]);
						mapgeneral.put("otherPhone",
								otherPhone[k].isEmpty() ? "" : "92"
										+ otherPhone[k]);
						list.add(mapgeneral);
					}
					json.put("otherPhoneIds", customerBAL
							.insertOtherPhoneDetails(customerId, list));
				}
			}
			ArrayList<HashMap<String, String>> listgenfields = new ArrayList<>();
			HashMap<String, String> generalTextMap = new HashMap<>();
			HashMap<String, String> generalDigitMap = new HashMap<>();

			String[] generalTextFieldTypes = request
					.getParameterValues("data[general_field_in_text_foramte_type][]");
			String[] generalTextField = request
					.getParameterValues("data[general_field_in_text_foramte][]");

			String[] generalDigitFieldTypes = request
					.getParameterValues("data[general_field_in_digit_foramte_type][]");
			String[] generalDigitField = request
					.getParameterValues("data[general_field_in_digit_foramte][]");

			int ii = 0;
			if (generalTextFieldTypes != null) {
				for (String type : generalTextFieldTypes) {
					generalTextMap.put(type, generalTextField[ii]);
					ii++;
				}
				json.put("generalTextFieldIds", customerBAL
						.insertGeneralDetails(customerId, generalTextMap));
			}

			int jj = 0;
			if (generalDigitFieldTypes != null) {
				for (String type : generalDigitFieldTypes) {
					generalDigitMap.put(type, generalDigitField[jj]);
					jj++;
				}
				json.put("generalDigitFieldIds", customerBAL
						.insertGeneralDetails(customerId, generalDigitMap));
			}

			json.put("status", "ok");
			json.put("data", customerId);
		} catch (Exception e) {
			logger.error("", e);
			try {
				json.put("status", "error");
				json.put("message", e.getStackTrace());
				e.printStackTrace();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		return json;

	}

	private JSONObject insertCustomerIncomeSource(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("salaryOrPension",
							request.getParameter("salaryOrPension"));
					map.put("businessIncome",
							request.getParameter("businessIncome"));
					map.put("farming", request.getParameter("farmingIncome"));
					map.put("familyContribution",
							request.getParameter("familyContribution"));
					map.put("incomeSource",
							request.getParameter("incomeSource"));
					int status = customerBAL.updateCustomerIncomeSource(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertCustomerExpense(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("customer_total_expense_income", request
							.getParameter("customer_total_expense_income"));
					map.put("customer_electricity_type",
							request.getParameter("customer_Connectivity"));
					map.put("customer_sourceof_electricity", request
							.getParameter("customer_sourceof_electricity"));
					map.put("customer_electricity_amount", request
							.getParameter("customer_primary_sourse_amount"));
					map.put("customer_mobile_type",
							request.getParameter("customer_mobile_type"));
					map.put("customer_mobilebills_amount",
							request.getParameter("customer_mobilebills_amount"));
					map.put("mobilink_network",
							request.getParameter("mobilink_network"));
					map.put("ufone_network",
							request.getParameter("ufone_network"));
					map.put("zong_network",
							request.getParameter("zong_network"));
					map.put("warid_network",
							request.getParameter("warid_network"));
					map.put("telenor_network",
							request.getParameter("telenor_network"));
					map.put("customer_transport_mode",
							request.getParameter("customer_transport_mode"));
					map.put("customer_transport_amount",
							request.getParameter("customer_transport_amount"));
					map.put("customer_karyana",
							request.getParameter("customer_karyana"));
					map.put("customer_karyana_amount",
							request.getParameter("customer_karyana_amount"));
					int status = customerBAL.insertCustomerExpense(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertCustomerLivestockAssets(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("customerTotalAssetsIncome",
							request.getParameter("customerTotalAssetsIncome"));
					map.put("customer_land",
							request.getParameter("customer_land"));
					map.put("customer_bbuffalo",
							request.getParameter("customer_bbuffalo"));
					map.put("customer_cow",
							request.getParameter("customer_cow"));
					map.put("customer_calf",
							request.getParameter("customer_calf"));
					map.put("customer_goat_sheep",
							request.getParameter("customer_goat_sheep"));
					map.put("customer_bike",
							request.getParameter("customer_bike"));
					map.put("customer_car",
							request.getParameter("customer_car"));
					map.put("customer_tractors",
							request.getParameter("customer_tractors"));
					map.put("customer_tv", request.getParameter("customer_tv"));
					map.put("customer_fridge",
							request.getParameter("customer_fridge"));

					map.put("customer_land_amount",
							request.getParameter("customer_land_amount"));
					map.put("customer_buffalo_amount",
							request.getParameter("customer_buffalo_amount"));
					map.put("customer_cow_amount",
							request.getParameter("customer_cow_amount"));
					map.put("customer_calf_amount",
							request.getParameter("customer_calf_amount"));
					map.put("customer_goat_Sheep_amount",
							request.getParameter("customer_goat_Sheep_amount"));
					map.put("customer_bike_amount",
							request.getParameter("customer_bike_amount"));
					map.put("customer_car_amount",
							request.getParameter("customer_car_amount"));
					map.put("customer_tractors_amount",
							request.getParameter("customer_tractors_amount"));
					map.put("customer_tv_amount",
							request.getParameter("customer_tv_amount"));
					map.put("customer_fridge_amount",
							request.getParameter("customer_fridge_amount"));

					int status = customerBAL.insertCustomerLivestockAssets(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("data", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("data", "customer id not declared");
			}
		} catch (JSONException e) {
			logger.error("", e);
		}
		return json;
	}

	private JSONObject insertCustomerEmployeeInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("companyTitle",
							request.getParameter("companyTitle"));
					map.put("designation", request.getParameter("designation"));
					map.put("jobPeriod", request.getParameter("jobPeriod"));
					int status = customerBAL.insertCustomerEmployementInfo(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("message", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id not declared");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	private JSONObject insertCustomerBusinessInfo(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String customerIdString = request.getParameter("customerId");
		try {
			if (customerIdString != null) {
				if (!customerIdString.isEmpty()) {
					HashMap<String, String> map = new HashMap<>();
					map.put("customerId", customerIdString);
					map.put("title", request.getParameter("data[title]"));
					map.put("type", request.getParameter("data[type]"));
					map.put("timePeriod",
							request.getParameter("data[timePeriod]"));
					int status = customerBAL.insertCustomerBusinessInfo(map);
					json.put("status", "ok");
					json.put("data", status);
				} else {
					json.put("status", "error");
					json.put("message", "customer id is null");
				}
			} else {
				json.put("status", "error");
				json.put("message", "customer id not declared");
			}
		} catch (JSONException e) {
			logger.error("", e);
		}
		return json;

	}

	private JSONObject insertLoan(HttpServletRequest request) {

		String customerId = request.getParameter("customerId");
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		JSONObject json = new JSONObject();
		if (customerId != null) {
			if (!customerId.isEmpty()) {
				try {
					String[] names = request
							.getParameterValues("data[bankOrOrgName][]");
					String[] types = request
							.getParameterValues("data[types][]");
					String[] amounts = request
							.getParameterValues("data[totalAmount][]");
					String[] installments = request
							.getParameterValues("data[monthlyInstallment][]");
					String[] remainingAmounts = request
							.getParameterValues("data[remainingAmount][]");

					String[] customerPaymentPlanInMonth = request
							.getParameterValues("data[customer_payment_plan_in_month][]");
					String[] customerFrequencyInMonth = request
							.getParameterValues("data[customer_frequency_in_month][]");
					for (int i = 0; i < names.length; i++) {
						HashMap<String, String> map = new HashMap<>();
						map.put("donner", names[i].isEmpty() ? "" : names[i]);
						map.put("type", types[i].isEmpty() ? "0" : types[i]);
						map.put("totalAmount", amounts[i].isEmpty() ? "0"
								: amounts[i]);
						map.put("monthlyInstallment",
								installments[i].isEmpty() ? "0"
										: installments[i]);
						map.put("remainingAmount", remainingAmounts[i]
								.isEmpty() ? "0" : remainingAmounts[i]);
						map.put("customerPaymentPlanInMonth",
								customerPaymentPlanInMonth[i] == "" ? "0"
										: customerPaymentPlanInMonth[i]);
						map.put("customerFrequencyInMonth",
								customerFrequencyInMonth[i] == "" ? "0"
										: customerFrequencyInMonth[i]);
						list.add(map);
					}
					json.put("data", customerBAL.insertLoan(
							Integer.parseInt(customerId), list));
					json.put("status", "ok");
				} catch (Exception e) {
					try {
						json.put("status", "error");
						json.put("message", e.getMessage());
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					json.put("status", "error");
					json.put("message", "customer id is empty");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				json.put("status", "error");
				json.put("message", "customer id is null");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return json;
	}

	private JSONObject getCustomerVerificationData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String eligibID = request.getParameter("eligibID");
			if (eligibID != null) {
				int eligibilityID = Integer.parseInt(eligibID);
				try {
					json.put("data", CustomerRetrieveDataBAL
							.getCustomerVerificationData(eligibilityID));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return json;
	}

	private JSONObject getCustomerProfile(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String customerId = request.getParameter("customerId");
			String customerCnic = request.getParameter("customerCnic");
			if (customerId != null && customerCnic != null) {
				int custID = Integer.parseInt(customerId);
				try {
					json.put("data", CustomerRetrieveDataBAL
							.getCustomerProfile(custID, customerCnic));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return json;
	}

}