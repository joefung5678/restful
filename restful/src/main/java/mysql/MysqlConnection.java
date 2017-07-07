package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;


import restful.HelloRS;

public class MysqlConnection {
	
	private Connection connection;
	private Properties properties;
	private String url = "jdbc:mysql://localhost:3306/javabase";
	private String username = "java";
	private String password = "password";
	private String MAX_POOL = "250";
	
	static Logger log = Logger.getLogger(HelloRS.class.getName());

	// CONNECT DB
	public Connection connection() throws ClassNotFoundException{
		
		log.debug("Running MysqlConnection connection()....");
		
	    if (connection == null) {
	        try {
	        	
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = (Connection) DriverManager.getConnection(url, getProperties());
	        } catch (ClassNotFoundException | SQLException e) {
	            // Java 7+
	            log.error("Error in connecting DB..."  + e);
	        }
	    }
	    return connection;
	}
	
	// DISCONNECT DB
	public void disconnect() {
		
		log.debug("Running MysqlConnection disconnect()....");
		
	    if (connection != null) {
	        try {
	            connection.close();
	            connection = null;
	        } catch (SQLException e) {
	        	log.error("Error in disconnect DB..."  + e);
	        }
	    }
	}


	// create properties
	private Properties getProperties() {
	    if (properties == null) {
	        properties = new Properties();
	        properties.setProperty("user", username);
	        properties.setProperty("password", password);
	        properties.setProperty("MaxPooledStatements", MAX_POOL);
	    }
	    return properties;
	}
}
