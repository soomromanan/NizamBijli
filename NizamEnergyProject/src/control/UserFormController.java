package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import bal.DoFoNdFormsBAL;
import bean.UserBean;

@WebServlet("/UserFormController")
public class UserFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UserFormController.class);

	public UserFormController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");

			String action = request.getParameter("action");
			if (action.equals("addDistrictOfficer")) {

				HashMap<String, String> usermap = new HashMap<>();

				String primaryPhone = request.getParameter("primaryPhone");
				if (primaryPhone != null) {
					primaryPhone = "92" + request.getParameter("primaryPhone");
					primaryPhone = primaryPhone.replace("-", "");
				}

				String secondaryPhone = request.getParameter("secondaryPhone");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					usermap.put("secondaryPhone", "");
				} else {
					secondaryPhone = "92" + secondaryPhone;
					secondaryPhone = secondaryPhone.replace("-", "");
					usermap.put("secondaryPhone", secondaryPhone);
				}
				String refferal1 = request.getParameter("refNumber1");
				if (refferal1 == null || refferal1.equals("")) {
					usermap.put("refNumber1", "");
				} else {
					refferal1 = "92" + refferal1;
					refferal1 = refferal1.replace("-", "");
					usermap.put("refNumber1", refferal1);
				}

				String refferal2 = request.getParameter("refNumber1");
				if (refferal2 == null || refferal2.equals("")) {
					usermap.put("refNumber2", "");
				} else {
					refferal2 = "92" + refferal2;
					refferal2 = refferal2.replace("-", "");
					usermap.put("refNumber2", refferal2);
				}
				String email = request.getParameter("email");
				JSONObject json = new JSONObject();
				int id = 0;
				int r = (int) (Math.random() * 10000);
				String password = primaryPhone + "" + r;
				String message = "Welcome to Nizam Energy. Your login credentials are: "
						+ "Userid: " + email + " and Password: " + password;
				password = security.Hashing.getMD5(password);
				usermap.put("doName", "" + request.getParameter("name"));
				usermap.put("cnic", "" + request.getParameter("cnic"));
				usermap.put("password", "" + password);
				usermap.put("dateOfBirth",
						"" + request.getParameter("dateOfBirth"));
				usermap.put("gender", "" + request.getParameter("gender"));
				usermap.put("marritalStatus",
						"" + request.getParameter("marritalStatus"));
				usermap.put("joiningDate",
						"" + request.getParameter("joiningDate"));
				usermap.put("primaryPhone", "" + primaryPhone);
				usermap.put("email", "" + request.getParameter("email"));
				usermap.put("address", "" + request.getParameter("address"));
				usermap.put("district", "" + request.getParameter("district"));
				usermap.put(
						"education",
						""
								+ Integer.parseInt(request
										.getParameter("education")));
				usermap.put("salary", "" + request.getParameter("salary"));
				usermap.put("userType", "" + request.getParameter("userType"));
				id = DoFoNdFormsBAL.insert_user(usermap);
				if (id > 0) {
					try {
						MoblinkMessageService
								.SendMessage(primaryPhone, message);
					} catch (Exception e) {
						logger.error(e);
					}
				}
				json.put("id", id);
				out.println(json);
			} else if (action.equals("updateDistrictOfficer")) {
				String primaryPhone = request.getParameter("primaryPhone");
				if (primaryPhone != null) {
					primaryPhone = "92" + primaryPhone;
					primaryPhone = primaryPhone.replace("-", "");
				}
				HashMap<String, String> usermap = new HashMap<>();
				String secondaryPhone = request.getParameter("secondaryPhone");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					usermap.put("secondaryPhone", "");

				} else {
					secondaryPhone = "92" + secondaryPhone;
					secondaryPhone = secondaryPhone.replace("-", "");
					usermap.put("secondaryPhone", secondaryPhone);
				}

				String refferal1 = request.getParameter("refNumber1");
				if (refferal1 == null || refferal1.equals("")) {
					usermap.put("refNumber1", "");

				} else {
					refferal1 = "92" + refferal1;
					refferal1 = refferal1.replace("-", "");
					usermap.put("refNumber1", refferal1);
				}
				String refferal2 = request.getParameter("refNumber1");
				if (refferal2 == null || refferal2.equals("")) {
					usermap.put("refNumber2", "");
				} else {
					refferal2 = "92" + refferal2;
					refferal2 = refferal2.replace("-", "");
					usermap.put("refNumber2", refferal2);
				}

				int r = (int) (Math.random() * 10000);
				String password = primaryPhone + "" + r;
				password = security.Hashing.getMD5(password);
				String doid = request.getParameter("doid");

				usermap.put("doName", "" + request.getParameter("name"));
				usermap.put("cnic", "" + request.getParameter("cnic"));
				usermap.put("password", "" + password);
				usermap.put("dateOfBirth",
						"" + request.getParameter("dateOfBirth"));
				usermap.put("gender", "" + request.getParameter("gender"));
				usermap.put("marritalStatus",
						"" + request.getParameter("marritalStatus"));
				usermap.put("joiningDate",
						"" + request.getParameter("joiningDate"));
				usermap.put("primaryPhone", "" + primaryPhone);
				usermap.put("email", "" + request.getParameter("email"));
				usermap.put("address", "" + request.getParameter("address"));
				usermap.put("district", "" + request.getParameter("district"));
				usermap.put(
						"education",
						""
								+ Integer.parseInt(request
										.getParameter("education")));
				usermap.put("salary", "" + request.getParameter("salary"));
				usermap.put("doid", doid);

				DoFoNdFormsBAL.update_user(usermap);

			} else if (action.equals("insertFieldOfficer")) {

				String foPrimaryPhone = request.getParameter("foPrimaryPhone");

				int r = (int) (Math.random() * 10000);
				foPrimaryPhone = "92" + foPrimaryPhone.replace("-", "");
				String password = foPrimaryPhone + "" + r;
				String message = "Welcome to Nizam Energy as a Field Officer. Please collect mobile application from your District Officer. Your mobile application's password is: "
						+ password;
				System.err.println(message);
				HashMap<String, String> fomap = new HashMap<>();
				fomap.put("foName", "" + request.getParameter("foName"));
				fomap.put("foCnic", "" + request.getParameter("foCnic"));
				fomap.put("foDateOfBirth",
						"" + request.getParameter("foDateOfBirth"));
				fomap.put("foGender", "" + request.getParameter("foGender"));
				fomap.put("foMaritalStatus",
						"" + request.getParameter("foMaritalStatus"));
				fomap.put("foDateOfJoining",
						"" + request.getParameter("foDateOfJoining"));
				fomap.put("foEducation",
						"" + request.getParameter("foEducation"));
				fomap.put("foPrimaryPhone",
						""
								+ "92"
								+ request.getParameter("foPrimaryPhone")
										.replace("-", ""));

				String secondaryPhone = request
						.getParameter("foSecondaryNumber");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					fomap.put("foSecondaryNumber", "");

				} else {

					secondaryPhone = "92" + secondaryPhone;

					secondaryPhone = secondaryPhone.replace("-", "");
					fomap.put("foSecondaryNumber", secondaryPhone);
				}

				fomap.put("foAccNo", "" + request.getParameter("foAccNo"));
				fomap.put("foEmail", "" + request.getParameter("foEmail"));
				fomap.put("foAddress", "" + request.getParameter("foAddress"));
				fomap.put("foCity", "" + request.getParameter("foCity"));
				fomap.put("foSalary", "" + request.getParameter("foSalary"));
				fomap.put("password", "" + password);
				fomap.put(
						"percellcomm",
						""
								+ Integer.parseInt(request
										.getParameter("percellcomm")));
				fomap.put("doId", request.getParameter("doid"));
				JSONObject json = new JSONObject();
				int id = 0;
				id = DoFoNdFormsBAL.insert_fo(fomap);
				if (id > 0) {
					try {
						MoblinkMessageService.SendMessage(foPrimaryPhone,
								message);
					} catch (Exception e) {
						logger.error(e);
					}
				}
				json.put("id", id);
				out.println(json);
			} else if (action.equals("updateFieldOfficer")) {
				HashMap<String, String> fomap = new HashMap<>();
				String primaryPhone = request.getParameter("foPrimaryPhone");
				if (primaryPhone != null) {
					primaryPhone = "92" + primaryPhone;
					primaryPhone = primaryPhone.replace("-", "");
				}
				String secondaryPhone = request
						.getParameter("foSecondaryNumber");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					fomap.put("foSecondaryNumber", "");
				} else {
					secondaryPhone = "92" + secondaryPhone;
					secondaryPhone = secondaryPhone.replace("-", "");
					fomap.put("foSecondaryNumber", secondaryPhone);
				}
				fomap.put("foName", "" + request.getParameter("foName"));
				fomap.put("foCnic", "" + request.getParameter("foCnic"));
				fomap.put("foDateOfBirth",
						"" + request.getParameter("foDateOfBirth"));
				fomap.put("foGender", "" + request.getParameter("foGender"));
				fomap.put("foMaritalStatus",
						"" + request.getParameter("foMaritalStatus"));
				fomap.put("foDateOfJoining",
						"" + request.getParameter("foDateOfJoining"));
				fomap.put("foEducation",
						"" + request.getParameter("foEducation"));
				fomap.put("foPrimaryPhone", primaryPhone);
				fomap.put("foAccNo", "" + request.getParameter("foAccNo"));
				fomap.put("foEmail", "" + request.getParameter("foEmail"));
				fomap.put("foAddress", "" + request.getParameter("foAddress"));
				fomap.put("foSalary", "" + request.getParameter("foSalary"));
				fomap.put(
						"percellcomm",
						""
								+ Integer.parseInt(request
										.getParameter("percellcomm")));
				fomap.put("foid",
						Integer.parseInt(request.getParameter("foid")) + "");
				DoFoNdFormsBAL.update_fo(fomap);
			}

			else if (action.equals("insertSalesman")) {
				HashMap<String, String> salesmanmap = new HashMap<>();
				JSONObject json = new JSONObject();
				int id = 0;
				String ndPrimaryPhone = request.getParameter("ndPrimaryPhone");
				if (ndPrimaryPhone != null) {
					ndPrimaryPhone = "92" + ndPrimaryPhone;
					ndPrimaryPhone = ndPrimaryPhone.replace("-", "");
				}
				String secondaryPhone = request
						.getParameter("ndSecondaryPhone");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					salesmanmap.put("ndSecondaryPhone", "");
				} else {
					secondaryPhone = "92" + secondaryPhone;
					secondaryPhone = secondaryPhone.replace("-", "");
					salesmanmap.put("ndSecondaryPhone", secondaryPhone);
				}

				int r = (int) (Math.random() * 10000);
				String password = ndPrimaryPhone + "" + r;
				password = security.Hashing.getMD5(password);

				salesmanmap.put("ndName", "" + request.getParameter("ndName"));
				salesmanmap.put("ndDateOfBirth",
						"" + request.getParameter("ndDateOfBirth"));
				salesmanmap.put("ndCnic", "" + request.getParameter("ndCnic"));
				salesmanmap.put("ndGender",
						"" + request.getParameter("ndGender"));
				salesmanmap.put("ndMaritalStatus",
						"" + request.getParameter("ndMaritalStatus"));
				salesmanmap.put("ndDateOfJoining",
						"" + request.getParameter("ndDateOfJoining"));
				salesmanmap.put("ndEducation",
						"" + request.getParameter("ndEducation"));
				salesmanmap.put("ndPrimaryPhone", "" + ndPrimaryPhone);

				salesmanmap.put("ndAccNumber",
						"" + request.getParameter("ndAccNumber"));
				salesmanmap
						.put("ndEmail", "" + request.getParameter("ndEmail"));
				salesmanmap.put("ndAddress",
						"" + request.getParameter("ndAddress"));
				salesmanmap.put("foList", "" + request.getParameter("foList"));
				salesmanmap.put("ndCity", "" + request.getParameter("ndCity"));
				salesmanmap.put("ndSalary",
						"" + request.getParameter("ndSalary"));
				salesmanmap.put(
						"ndPerSellCommission",
						""
								+ Integer.parseInt(request
										.getParameter("ndPerSellCommission")));
				salesmanmap.put(
						"ndDistrict",
						""
								+ Integer.parseInt(request
										.getParameter("ndDistrict")));
				salesmanmap.put("password", password);

				id = DoFoNdFormsBAL.insert_NizamDost(salesmanmap);
				if (id > 0) {
					String message = "Welcome to Nizam Energy as our Nizam Dost, Please contact your District Officer for further instructions";
					try {
						MoblinkMessageService.SendMessage(ndPrimaryPhone,
								message);
					} catch (Exception e) {
						logger.error(e);
					}
				}
				json.put("id", id);
				out.println(json);
			} else if (action.equals("updateSalesman")) {
				HashMap<String, String> salesmanmap = new HashMap<>();
				String primaryPhone = request.getParameter("ndPrimaryPhone");
				if (primaryPhone != null) {
					primaryPhone = "92" + primaryPhone;
					primaryPhone = primaryPhone.replace("-", "");
				}

				String secondaryPhone = request
						.getParameter("ndSecondaryPhone");
				if (secondaryPhone == null || secondaryPhone.equals("")) {
					salesmanmap.put("ndSecondaryPhone", "");

				} else {

					secondaryPhone = "92" + secondaryPhone;

					secondaryPhone = secondaryPhone.replace("-", "");
					salesmanmap.put("ndSecondaryPhone", secondaryPhone);
				}

				System.err.println("update Salesman");
				String ndPrimaryPhone = primaryPhone;
				ndPrimaryPhone = ndPrimaryPhone.replace("-", "");

				salesmanmap.put("ndId", "" + request.getParameter("ndId"));
				salesmanmap.put("ndName", "" + request.getParameter("ndName"));
				salesmanmap.put("ndDateOfBirth",
						"" + request.getParameter("ndDateOfBirth"));
				salesmanmap.put("ndCnic", "" + request.getParameter("ndCnic"));
				salesmanmap.put("ndGender",
						"" + request.getParameter("ndGender"));
				salesmanmap.put("ndMaritalStatus",
						"" + request.getParameter("ndMaritalStatus"));
				salesmanmap.put("ndDateOfJoining",
						"" + request.getParameter("ndDateOfJoining"));
				salesmanmap.put("ndEducation",
						request.getParameter("ndEducation"));
				salesmanmap.put("ndPrimaryPhone", ndPrimaryPhone);

				salesmanmap.put("ndAccNumber",
						request.getParameter("ndAccNumber"));
				salesmanmap.put("ndEmail", request.getParameter("ndEmail"));
				salesmanmap.put("ndAddress", request.getParameter("ndAddress"));

				salesmanmap.put("ndSalary", request.getParameter("ndSalary")
						+ "");
				salesmanmap.put(
						"ndPerSellCommission",
						""
								+ Integer.parseInt(request
										.getParameter("ndPerSellCommission")));
				DoFoNdFormsBAL.update_Salesman(salesmanmap);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
