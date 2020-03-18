package keyprest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionManager {
	public static Connection databaseConnection;
	
	public static void createConnection() throws ClassNotFoundException, SQLException {
		
		/* TODO: Rimuovere con aggiornamento.
		 *  In the past, it was required to load a JDBC driver before creating a java.sql.Connection. 
		 *  Nowadays, when using JDBC 4.0 drivers, this is no longer required and Class.forName() can be safely 
		 *    removed because JDBC 4.0 (JDK 6) drivers available in the classpath are automatically loaded.
		 */
		Class.forName("com.mysql.jdbc.Driver");
		
		databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root");
		
	}
	
	public static void closeConnection() throws SQLException {
		 /* Esegue il commit delle transazioni in attesa prima della chiusura della connessione */
		 databaseConnection.commit();
		 
		 databaseConnection.close();
	}
	
}
