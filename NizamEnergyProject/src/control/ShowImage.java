package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import bal.ImageBAL;
import bean.Image;

/**
 * Servlet implementation class ShowImage
 */
@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ShowImage.class);

	public ShowImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idString = request.getParameter("id");
			if (idString != "0") {
				if (!idString.isEmpty()) {
					int id = Integer.parseInt(idString);
					Image image = new ImageBAL().getImageByteArray(id);
					response.setContentType(image.getContentType());
					response.getOutputStream().write(image.getBytes());
					response.getOutputStream().flush();
					response.getOutputStream().close();
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		JSONObject json = new JSONObject();
		try (PrintWriter out = response.getWriter()) {
			if (action != null) {
				if (!action.isEmpty()) {
					if (action.equals("getImage")) {
						String idString = request.getParameter("id");
						if (idString != null) {
							if (!idString.isEmpty()) {
								int id = Integer.parseInt(idString);
								Image image = new ImageBAL().getImageBase64(id);
								json.put("status", "ok");
								json.put("data", new JSONObject(image));
							} else {
								json.put("status", "error");
								json.put("message", "id is empty");
							}
						} else {
							json.put("status", "error");
							json.put("message", "id not provided");
						}
					} else {
						json.put("status", "error");
						json.put("message", "action not found");
					}
				} else {
					json.put("status", "error");
					json.put("message", "action is empty");
				}
			} else {
				json.put("status", "error");
				json.put("message", "action null");
			}
			out.println(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
