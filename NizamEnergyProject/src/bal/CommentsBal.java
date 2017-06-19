package bal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ocpsoft.pretty.time.PrettyTime;

import connection.Connect;

public class CommentsBal {
	final static Logger logger = Logger.getLogger(CommentsBal.class);

	public static ArrayList<HashMap<String, String>> getAllComments(
			int applianceId, String from, String to) {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		HashMap<String, String> map = null;
		ResultSet rs = null;
		CallableStatement prepareCall = null;
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				if (to.isEmpty() && from.isEmpty()) {
					prepareCall = connection
							.prepareCall("{call get_comments_for_loan_book(?)}");
					prepareCall.setInt(1, applianceId);
					rs = prepareCall.executeQuery();
				} else {
					prepareCall = connection
							.prepareCall("{call get_comments_for_loan_book_from(?,?,?)}");
					prepareCall.setInt(1, applianceId);
					prepareCall.setString(2, from);
					prepareCall.setString(3, to);
					rs = prepareCall.executeQuery();
				}
				while (rs.next()) {
					map = new HashMap<>();
					map.put("text", rs.getString("chat_text"));
					Timestamp dateTime = rs.getTimestamp("txt_date_time");
					Date date = new Date(dateTime.getTime());
					System.out.println(p.format(date));
					map.put("dateTime", p.format(date));
					map.put("userId", rs.getInt("sender") + "");
					map.put("applianceId", rs.getInt("appliance_id") + "");
					map.put("userName", rs.getString("u.user_name"));
					list.add(map);
				}
			}
		} catch (SQLException e) {
			logger.error("get_comments_for_loan_book\n", e);
		}
		return list;
	}

	public static int insertComments(String text, int userId, int applianceId) {
		try (Connection connection = Connect.getConnection()) {
			if (connection != null) {
				CallableStatement prepareCall = connection
						.prepareCall("{call insert_comment(?,?,?)}");
				prepareCall.setString(1, text);
				prepareCall.setInt(2, userId);
				prepareCall.setInt(3, applianceId);
				prepareCall.executeQuery();
			}
		} catch (SQLException e) {
			logger.error("", e);
		}
		return 0;
	}

	public static int countUnseenMessages(int userId) {
		int count = 0;
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL count_comments(?)}");
			prepareCall.setInt(1, userId);
			ResultSet resultSet = prepareCall.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("count_comments/n", e);
			e.printStackTrace();
		}
		return count;
	}

	public static ArrayList<HashMap<String, String>> getUnseenMessagesForLoanBook(
			int userId) {
		PrettyTime p = new PrettyTime();
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try (Connection connection = Connect.getConnection();) {
			CallableStatement prepareCall = connection
					.prepareCall("{CALL get_unseen_messages(?)}");
			prepareCall.setInt(1, userId);
			ResultSet result = prepareCall.executeQuery();
			while (result.next()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("messageFrom", result.getString("user_name"));
				Timestamp dateTime = result.getTimestamp("txt_date_time");
				Date date = new Date(dateTime.getTime());
				map.put("messageDate", p.format(date));
				map.put("applianceId", result.getString("appliance_id"));
				list.add(map);
			}
		} catch (SQLException e) {
			logger.error("get_unseen_messages/n", e);
		}
		return list;

	}// end of getting all message number from appliance_message

	public static void updateMessageStatus(int applianceId, int userId) {
		Statement s = null;
		String query = "UPDATE sa_cc_comments_chat SET seen = 1 WHERE `appliance_id` = "
				+ applianceId + " and sender !=" + userId;
		int row = 0;
		try (Connection con = Connect.getConnection();) {
			s = con.createStatement();
			row = s.executeUpdate(query);
			if (row > 0) {
				System.out.println("Data is updated");
			} else {
				System.out.println("Data is not updated");
			}
			con.close();
		} catch (Exception e) {
			logger.error("updateMessageStatus/n", e);
		}

	}

}
