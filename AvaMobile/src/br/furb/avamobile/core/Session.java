package br.furb.avamobile.core;

public class Session
{
	private String username;
	private int token;
	
	public Session(String username, int token)
	{
		this.username = username;
		this.token = token;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public int getToken()
	{
		return token;
	}

}
