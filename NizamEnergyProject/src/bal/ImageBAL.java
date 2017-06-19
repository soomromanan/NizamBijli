package bal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import bean.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import com.mysql.jdbc.Connection;
import connection.Connect;

public class ImageBAL {

	final static Logger logger = Logger.getLogger(ImageBAL.class);

	public static ArrayList<String> getImages(String path, int appId) {
		ArrayList<String> list = new ArrayList<String>();
		File dir = new File(path + File.separator + appId);
		if (dir.isDirectory()) {
			for (final File f : dir.listFiles()) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(f);
					if (f.getName().endsWith(".png")) {
						list.add(f.getName());
					}
				} catch (final IOException e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public static HashMap<String, String[]> getAssetsImages(String path) {
		HashMap<String, String[]> map = null;
		try {
			File f = new File(path);
			if (f.exists() && f.isDirectory()) {
				map = new HashMap<>();
				File[] fList = f.listFiles();
				for (File file : fList) {
					if (file.isDirectory() && file.canExecute()
							&& !file.getName().toString().equals("101")) {
						String subFList[] = file.list();
						map.put(file.getName().toString(), subFList);
					}
				}

			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;

	}

	public static HashMap<String, String[]> getAssetsImages2(String path) {
		HashMap<String, String[]> map = null;
		try {
			File f = new File(path);
			if (f.exists() && f.isDirectory()) {
				map = new HashMap<>();
				File[] fList = f.listFiles();
				for (File file : fList) {
					if (file.isDirectory() && file.canExecute()) {
						String subFList[] = file.list();
						map.put(file.getName().toString(), subFList);
					}
				}
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return map;

	}

	public int insertImage(InputStream inputStream, String contentType,
			String imageName) {

		int id = 0;
		try (Connection connection = Connect.getConnection()) {
			PreparedStatement prepareStatement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO image(image, image_content_type, image_name) value(?, ?, ?)");
			prepareStatement.setBlob(1, inputStream);
			prepareStatement.setString(2, contentType);
			prepareStatement.setString(3, imageName);
			prepareStatement.execute();
			id = (int) prepareStatement.getLastInsertID();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return id;
	}

	public Image getImageByteArray(int id) {
		Image image = new Image();
		try (Connection connection = Connect.getConnection();) {
			PreparedStatement prepareStatement = (PreparedStatement) connection
					.prepareStatement("SELECT i.image_id, i.image, i.image_content_type FROM image i where i.image_id = ?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				image.setId(resultSet.getInt("image_id"));
				image.setBytes(resultSet.getBytes("image"));
				image.setContentType(resultSet.getString("image_content_type"));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return image;
	}

	public Image getImageBase64(int id) {
		Image image = new Image();
		try (Connection connection = Connect.getConnection()) {
			PreparedStatement prepareStatement = (PreparedStatement) connection
					.prepareStatement("SELECT i.image_id, i.image, i.image_content_type FROM image i where i.image_id = ?");
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				image.setId(resultSet.getInt("image_id"));
				image.setBytes(resultSet.getBytes("image"));
				image.setBase64(Base64.encode(resultSet.getBytes("image")));
				image.setContentType(resultSet.getString("image_content_type"));
			}
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return image;
	}

}