package br.furb.avamobile.core;

public class SessionManager
{
	private static Session logedSession;
	
	private SessionManager()
	{
		// Empty constructor for private to use this class only Static Class
	}
	
	public static Session login(String username, String password) throws Exception
	{
		logedSession = new Session(username, 0);
		
		return logedSession;	
	}
	
	public static void logout()
	{
		
	}
	
	public static Session getLogedSession()
	{
		return logedSession;
	}
	
}
