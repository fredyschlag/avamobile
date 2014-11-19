package br.furb.login;

import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.header.Token;

public class TokensController {
	
	private static TokensController instance;
	private Map<String, TokenLogin> tokens;
	
	private TokensController() 
	{
		tokens = new HashMap<>();
	}
	
	public static TokensController getInstance() 
	{
		if (instance == null)
			instance = new TokensController();
		
		return instance;
	}
	
	private String generateToken(String user, String password) 
	{
		return String.valueOf(user.hashCode()) + String.valueOf(password.hashCode());
	}
	
	public TokenLogin generateToken(String user, String password, Client client) 
	{
		String tokenStr = generateToken(user, password);
		TokenLogin token = new TokenLogin(user, password, tokenStr, client);
		tokens.put(tokenStr, token);
		return token;
	}
	
	public TokenLogin getToken(String user, String password) 
	{
		return tokens.get(generateToken(user, password));
	}
	
	public void removeToken(Token token) 
	{
		tokens.remove(token.getToken());
	}
	
	public void updateToken(TokenLogin token, String newToken) 
	{
		tokens.remove(token.getToken());
		token.setToken(newToken);
	}
	
	public TokenLogin getToken(String token) 
	{
		return tokens.get(token);
	}

}
