package br.furb.avamobile.activities;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.furb.avamobile.R;
import br.furb.avamobile.core.ServerRequest;
import br.furb.avamobile.core.ServerRequest.ServerRequestListener;

public class MainActivity extends Activity implements ServerRequestListener
{	
	final String LOGIN_URL = "http://192.168.56.1:8080/furb-services/login";
	
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void loginClick(View view)
	{
		final EditText nome = (EditText) findViewById(R.id.editNome);
		final EditText senha = (EditText) findViewById(R.id.editSenha);
		
		NameValuePair username = new BasicNameValuePair("username", nome.getText().toString());
		NameValuePair password = new BasicNameValuePair("password", senha.getText().toString());
		
		ServerRequest loginTask = new ServerRequest(this, LOGIN_URL, this);
		loginTask.execute(username, password);
	}

	@Override
	public void onRequestStart()
	{
		progress = ProgressDialog.show(this, "Aguarde", "processando..", true, false);
		progress.setCancelable(false);
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
				{
					error = true;
					response = json.getString("error");
				}
				
				if (!error)
				{
					String token = json.getString("token");
					
					/*
					SharedPreferences preferences = getSharedPreferences("token-login", MODE_PRIVATE);
					SharedPreferences.Editor editor = preferences.edit();
				
					editor.putString("token", token);
					
					// Gravamos a session
					editor.commit();
					*/
					
					if (!token.equals(""))
		    		{
		    			TextView edtNome = (TextView) findViewById(R.id.editNome);
		    			
		    			Intent i = new Intent(MainActivity.this, MenuActivity.class);
		    	    	
		    			i.putExtra("username", edtNome.getText().toString());
		    			i.putExtra("token", token);
		    			
		    	    	startActivity(i);
		    		}
				}
			}
			catch (JSONException e) 
			{
				error = true;
				response = e.getMessage();
			}
		}
		
		if (error) Toast.makeText(this, response, Toast.LENGTH_LONG).show();
	}
}
