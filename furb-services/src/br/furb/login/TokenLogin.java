package br.furb.login;

import com.sun.jersey.api.client.Client;

public class TokenLogin {

	private String user;
	private String password;
	private String token;
	private Client client;

	TokenLogin(String user, String password, String token, Client client) {
		this.user = user;
		this.password = password;
		this.token = token;
		this.client = client;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getToken() {
		return token;
	}

	void setToken(String token) {
		this.token = token;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	

}
