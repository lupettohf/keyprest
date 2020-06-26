package keyprest.store;

import javax.servlet.http.HttpSession;

public class Alerts {
	
	public enum AlertType {
		ERROR,
		SUCCESS,
		INFO;
	}
	
	public static void setAlert(String Message, AlertType Type, HttpSession session)
	{
		switch(Type)
		{
			case ERROR:
				session.setAttribute("error", Message);
				break;
			case INFO:
				session.setAttribute("info", Message);
				break;
			case SUCCESS:
				session.setAttribute("sucess", Message);
				break;
			default:
				return;		
		}
	}
	
}
