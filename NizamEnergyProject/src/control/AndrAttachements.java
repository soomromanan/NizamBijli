package control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.misc.BASE64Decoder;

@WebServlet("/AndrAttachements")
public class AndrAttachements extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrAttachements.class);

	public AndrAttachements() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processsReequest(request, response, "GET");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processsReequest(request, response, "POST");
	}

	private void processsReequest(HttpServletRequest request,
			HttpServletResponse response, String method)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			JSONObject obj = new JSONObject();
			String status = "ok";
			try {
				String action = request.getParameter("action");
				if (action.equals("uploadImage")) {
					String imageId = request.getParameter("imageId");
					String customerId = request.getParameter("customerId");
					String imageType = request.getParameter("imageType");
					String tableName = request.getParameter("tableName");
					String imageData = request.getParameter("imageData");
					logger.info("action :" + action + " customerId "
							+ customerId);
					if (imageId != null && customerId != null
							&& imageType != null && imageData != null
							&& tableName != null) {

						System.err.println("imageId " + imageId
								+ " customerId " + customerId + " imageType "
								+ imageType + " tableName " + tableName);

						String realPath = getServletContext().getRealPath(
								"/Images")
								+ File.separator + customerId;

						realPath += File.separator + imageType;
						System.out.println(realPath);
						File file = new File(realPath);
						if (!file.exists()) {
							System.out.println(realPath + " Directory Created");
							file.mkdirs();
						}

						if (imageType.equals("102") || imageType.equals("103")) {

							String imageLabel = request
									.getParameter("imageLabel");
							try {
								convertAndSaveImage(request, imageData,
										realPath + File.separator + imageLabel
												+ ".jpg");
							} catch (Exception e) {
								status = "error";
								obj.put("error", e.toString());
								e.printStackTrace();
							}

							obj.put("imageId", imageId);
							obj.put("tableName", tableName);
							System.out.println("All work done");
						} else if (imageType.equals("101")) {
							String assetId = request.getParameter("assetId");
							realPath += File.separator + assetId;
							System.out.println(realPath);
							file = new File(realPath);
							if (!file.exists()) {
								System.out.println(realPath
										+ " Directory Created");
								file.mkdirs();
							}

							try {
								convertAndSaveImage(request, imageData,
										realPath + File.separator + imageId
												+ ".jpg");
							} catch (Exception e) {
								status = "error";
								obj.put("error", e.toString());
								e.printStackTrace();
							}

							obj.put("imageId", imageId);
							obj.put("tableName", tableName);
							System.out.println("All work done");

						}

					} else {
						status = "error";
						obj.put("error", "Parameters not found");
						System.out.println("Parameters not found");
					}
				} else if (action.equals("allImages")) {

					String attachments = request.getParameter("attachments");
					if (attachments != null) {

						JSONObject object = new JSONObject(attachments);
						JSONArray arr = object.getJSONArray("attachments");
						for (int i = 0; i < arr.length(); i++) {
							object = arr.getJSONObject(i);
							String appId = object.getString("appId");
							JSONArray jsonArr = object
									.getJSONArray("attachments");
							String realPath = getServletContext().getRealPath(
									"/Images")
									+ File.separator + appId;

							System.out.println(realPath);
							File file = new File(realPath);
							if (!file.exists()) {
								System.out.println("Directory Created");
								file.mkdirs();
							}

							for (int j = 0; j < jsonArr.length(); j++) {
								try {
									convertAndSaveImage(request,
											obj.getString(String.valueOf(j)),
											realPath + File.separator + j
													+ ".jpg");
								} catch (Exception e) {
									status = "error";
									obj.put("error", e.toString());
									e.printStackTrace();
								}

							}
						}

					} else {
						status = "error";
						obj.put("error", "Attachments Not found");
						System.out.println("Attachments Not found");
					}
				} else {
					status = "error";
					obj.put("error", "Request Not Found");
					System.out.println("Null");
				}

				obj.put("status", status);
				obj.put("Method", method);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			out.print(obj);
		}

	}

	public void convertAndSaveImage(HttpServletRequest request,
			String base64String, String fileName) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(base64String);
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(
				decodedBytes));
		if (image == null) {
			System.err.println("Image Null");
		}
		File f = new File(fileName);
		ImageIO.write(image, "jpg", f);
	}
}