package keyprest.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class connectionManager {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    
   
    
	static {
        config.setJdbcUrl( "jdbc:mysql://localhost:3306/keyprest" );
        config.setUsername( "keyprest" );
        config.setPassword( "PorcodioPorcamadonna" );
        config.addDataSourceProperty("dataSourceClassName", "com.mysql.cj.jdbc.Driver");
        ds = new HikariDataSource( config );
    }
	
	private connectionManager() {}
	
	public static Connection getConnection() throws SQLException {
	        return ds.getConnection();
	}
	
}
