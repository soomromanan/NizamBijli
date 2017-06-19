package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import bal.CommentsBal;
import bean.UserBean;

/**
 * Servlet implementation class CommentsController
 */
@WebServlet("/CommentsController")
public class CommentsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CommentsController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		if (ubean != null) {
			String click = request.getParameter("click");
			if (click.equals("getComments")) {
				String id = request.getParameter("appId");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				int applianceId = Integer.parseInt(id);
				JSONArray json = null;
				try {
					ArrayList<HashMap<String, String>> comments = CommentsBal.getAllComments(applianceId,
							from, to);
					CommentsBal.updateMessageStatus(applianceId, ubean.getUserId());
					json = new JSONArray(comments);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (click.equals("insertComments")) {
				String id = request.getParameter("appId");
				String text = request.getParameter("message");
				try {
					CommentsBal.insertComments(text, ubean.getUserId(), Integer.parseInt(id));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (click.equals("countUnseenMessages")) {
				JSONObject json = new JSONObject();
				try {
					json.put("countMessages", CommentsBal.countUnseenMessages(ubean.getUserId()));
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (click.equals("showUnseenMessages")) {
				JSONArray json = null;
				try {
					json = new JSONArray(CommentsBal.getUnseenMessagesForLoanBook(ubean.getUserId()));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			}
		}
	}

}
