package br.furb.avamobile.core;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import br.furb.avamobile.core.ServerRequest.ServerRequestListener;

public class LoginRequest implements ServerRequestListener
{
	final String LOGIN_URL = ServerUtils.SERVER_URL + "login";
	
	Context context;
	ProgressDialog progress;	
	LoginListener listener;
	
	private LoginRequest(Context context, LoginListener listener)
	{
		this.listener = listener;
		this.context = context;
		
		this.progress = new ProgressDialog(context);
		this.progress.setIndeterminate(true);
		this.progress.setCancelable(false);
		this.progress.setTitle("Aguarde");
		this.progress.setMessage("Processando...");
	}
	
	public void login(String username, String password)
	{
		NameValuePair kpUsername = new BasicNameValuePair("username", username);
		NameValuePair kpPassword = new BasicNameValuePair("password", password);	
		try
		{
			ServerRequest loginTask = new ServerRequest(context, LOGIN_URL, this);		
			loginTask.execute(kpUsername, kpPassword);
		}
		catch (Exception ex)
		{
			listener.error(ex.getMessage());
		}
	}

	@Override
	public void onRequestStart()
	{
		progress.show();
	}

	@Override
	public void onRequestComplete(String response, boolean error)
	{	
		progress.dismiss();		
		
		if (!error)
		{
			try
			{
				JSONObject json = new JSONObject(response);
				
				if ((!error) && (json.has("error")))
					throw new Exception(json.getString("error"));
				
				listener.sucess(json.getString("token"));
			}
			catch (Exception ex)
			{
				error = true;
				response = ex.getMessage();
			}
		}
		
		if (error) listener.error(response);
	}
	
	public static void login(Context context, LoginListener listener, String username, String password)
	{
		LoginRequest lr = new LoginRequest(context, listener);
		lr.login(username, password);
	}
	
	public interface LoginListener
	{
		void sucess(String token);
		void error(String error);
	}
}
