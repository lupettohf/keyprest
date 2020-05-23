package keyprest.user;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

import keyprest.database.connectionManager;
import keyprest.utils.Globals;
import keyprest.utils.Validators;

public class User_utils {

	private static String hashPassword(String Password)
	{
		return DigestUtils.sha256Hex(Password + Globals.SALT); 
	}
	
	
	private static String genUserSession(String Username) throws SQLException
	{
		String QUERY = "UPDATE users SET `sessionkey` = ? WHERE username = ?";
		
		if(Username.isEmpty()) { return null; }
		
		String SessionKey = generateSecureString() + Username.toLowerCase();
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, SessionKey);
		preparedStatement.setString(2, Username);
		
		if(preparedStatement.executeUpdate() == 1) {return SessionKey;}
		
		return null;
		
	}
	
	public static User getUser(String SessionKey) throws SQLException
	{
		String QUERY = "SELECT * FROM users WHERE sessionkey = ?";
		
		if(SessionKey.isEmpty()) { return null; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, SessionKey);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		//Se ci sono risultati nella query, il login é corretto. 
		if(rs.next()) { 
			return new User(
					rs.getInt("id"), 
					rs.getString("username"), 
					rs.getString("password"), 
					rs.getString("mailaddr"), 
					rs.getString("realname"), 
					rs.getString("billing_address"),
					rs.getInt("points"), 
					rs.getBoolean("administrator")					
		); 		
		} else { return null; }
	}
	
	public static boolean createUser(String Username, String Password, String Password_confirm, String EMail) throws SQLException, ClassNotFoundException
	{
		//TODO: Verificare che username ed email non siano gia presenti nel database, attualmente sono contrassegnati come UNIQUE
		
		String QUERY = "INSERT INTO users" +
						"(username, password, mailaddr) VALUES (?, ?, ?)";
		/* Primo stage controlli: campi vuoti/password non uguali.
		 * Richiede anche verifica client-side per informare l'utente dell'eventuale errore. 
		 */
		if(Username.isEmpty() || Password.isEmpty() || EMail.isEmpty()|| !Password.equals(Password_confirm)) {return false;}
		/* Controllo che mail e username contengano caratteri validi e rispettino le lunghezze (L<30 L>3)*/
		if(!Validators.ValidateEmail(EMail) || !Validators.ValidateUsername(Username)) { return false; }
		
		if(connectionManager.databaseConnection.isClosed()) { connectionManager.createConnection(); }
			
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, hashPassword(Password));
		preparedStatement.setString(3, EMail);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	public static String doLogin(String Username, String Password) throws SQLException
	{
		String QUERY = "SELECT id FROM users "
				+ "WHERE EXISTS(select 1 from users where username = ?) AND password = ?";
		
		if(Username.isEmpty() || Password.isEmpty()) { return "false"; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, hashPassword(Password));
		
		ResultSet rs = preparedStatement.executeQuery();
		
		//Se ci sono risultati nella query, il login é corretto. 
		if(rs.next()) { 
			return genUserSession(Username);
		} else { return null; }
	}
	
	public static boolean changePassword(String Username, String Old_password, String New_password) throws SQLException
	{
		String QUERY = "UPDATE users " + 
				"SET `password` = ? " + 
				"WHERE EXISTS(select 1 where username = ?) AND password = ?";
		
		if(Old_password.isEmpty() || New_password.isEmpty()) { return false; }
		
		String HashedPassword = DigestUtils.sha256Hex(New_password + Globals.SALT); 
		String HashedPassword_old = DigestUtils.sha256Hex(Old_password + Globals.SALT); 
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, HashedPassword);
		preparedStatement.setString(2, Username);
		preparedStatement.setString(3, HashedPassword_old);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	public static boolean setBillingAddress(String SessionKey, String BillingAddress) throws SQLException
	{
		String QUERY = "UPDATE users SET `billing_address` = ? WHERE sessionkey = ?";
		
		if(BillingAddress.isEmpty()) { return false; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, BillingAddress);
		preparedStatement.setString(2, SessionKey);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	public static boolean setRealName(String SessionKey, String RealName) throws SQLException
	{
		String QUERY = "UPDATE users SET `realname` = ? WHERE sessionkey = ?";
		
		if(RealName.isEmpty()) { return false; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, RealName);
		preparedStatement.setString(2, SessionKey);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	public static boolean isAdmin(String SessionKey) throws SQLException
	{
		String QUERY = "SELECT id FROM users WHERE administrator = TRUE AND sessionkey = ?";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		if(SessionKey.isEmpty()) { return false; }
		
		preparedStatement.setString(1, SessionKey);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		if(rs.next()) { return true; } else { return false; }
	}
	
	private static String generateSecureString() {
        
		StringBuilder sb = new StringBuilder(31);
        SecureRandom random = new SecureRandom();
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String upperAlphabet = lowerAlphabet.toUpperCase();
        String alphabet = lowerAlphabet + upperAlphabet;
        
        for (int i = 0; i < 31; i++) {
            int rndCharAt = random.nextInt(alphabet.length());
            char rndChar = alphabet.charAt(rndCharAt);
            sb.append(rndChar);
        }
        
        return sb.toString();
	}
	
}
