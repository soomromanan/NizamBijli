package connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Connect {

	final static Logger logger = Logger.getLogger(Connect.class);

	private static Properties properties;

	public static final void setProperties() {

		properties = new Properties();

		// properties.setProperty("user", "root");
		// properties.setProperty("password", "nizambijli");

		properties.setProperty("user", "root");
		properties.setProperty("password", "root");

	}

	// private static final String DBURL =
	// "jdbc:mysql://175.107.206.22:3306/nizamdb_tester";

	private static final String DBURL = "jdbc:mysql://localhost:3306/nizambijli_database_test?zeroDateTimeBehavior=convertToNull";

	public static Connection getConnection() {
		setProperties();
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(DBURL,
					properties);
		} catch (SQLException e) {
			logger.error("connection Not Established");
			logger.error("", e);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {

	}
} // end of class
