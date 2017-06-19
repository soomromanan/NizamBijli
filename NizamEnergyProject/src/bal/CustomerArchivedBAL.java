package bal;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

import connection.Connect;

public class CustomerArchivedBAL {
	
	final static Logger logger = Logger.getLogger(CustomerArchivedBAL.class);
	
	public ArrayList<HashMap<String, String>> getArchivedCustomers(){		
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		try(Connection connection = Connect.getConnection()) {
			CallableStatement prepareCall = connection.prepareCall("{call get_archived_customers()}");
			ResultSet resultSet = prepareCall.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			String [] columns = new String[metaData.getColumnCount()];
			for(int i = 0; i < columns.length; i++){
				columns[i] = metaData.getColumnLabel(i+1);
			}
			while(resultSet.next()){
				HashMap<String, String> map = new HashMap<>();
				for(int i = 0; i < columns.length; i++){
					map.put(columns[i], resultSet.getString(columns[i]));
				}
				list.add(map);
			}  
			
			
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}	
		return list;
	}
	
	public static void main(String arg[]) {
		System.out.print(""+new CustomerArchivedBAL().getArchivedCustomers());
	}
	

}
