package keyprest.utils;

public class validators {

	public static boolean ValidateUsername(String Username)
	{
		if(Username.length() > 30 || Username.length() < 3) { return false; }
		
		/* Dalla A alla Z (maiuscole e minuscole), numeri 0-9, e underscore. */
		if(!Username.matches("^[a-zA-Z0-9_]*$")) { return false; }
		
		return true;
	}
	
	public static boolean ValidateEmail(String Email)
	{
		return Email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
	}
}
