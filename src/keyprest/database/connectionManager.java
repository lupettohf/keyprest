package keyprest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionManager {
	public static Connection databaseConnection;
	
	public static void createConnection(){
		try {
			databaseConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/my_keys?useSSL=false", "andrea", "xx");
		} catch (SQLException e) {
			// TODO: Autogenerato, da cambiare
			e.printStackTrace();
		}
		
		
	}
	
	public static void closeConnection() throws SQLException {
		 /* Esegue il commit delle transazioni in attesa prima della chiusura della connessione */
		 databaseConnection.commit();
		 
		 databaseConnection.close();
	}
	
}
