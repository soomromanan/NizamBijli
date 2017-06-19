package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bal.UserBal;
import security.Hashing;
import messageAPI.MoblinkMessageService;

@WebServlet(name = "SendRandomGeneratedPassword", urlPatterns = { "/SendRandomGeneratedPassword" })
public class SendRandomGeneratedPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(SendRandomGeneratedPassword.class);

	public SendRandomGeneratedPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("click");
			String number = request.getParameter("mobileNumber");
			if (action.equalsIgnoreCase("login")) {
				String email = UserBal.getUserEmailByMobileNumber(number);
				int r = (int) (Math.random() * 10000);
				String password = number + "" + r;
				logger.info("UserEmail : " + email + " Password : " + password
						+ " IP: " + request.getRemoteAddr());
				String pass = Hashing.getMD5(password);
				UserBal.updateUserPassword(pass, email);
				String Str = "Your new password is :" + password;
				MoblinkMessageService.SendMessage(number, Str);
			}
			RequestDispatcher rs = request
					.getRequestDispatcher("SolarHomeSystemLogin");
			rs.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
