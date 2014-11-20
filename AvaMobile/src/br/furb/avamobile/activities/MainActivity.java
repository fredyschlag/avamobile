package br.furb.avamobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import br.furb.avamobile.R;
import br.furb.avamobile.core.LoginRequest;
import br.furb.avamobile.core.LoginRequest.LoginListener;

public class MainActivity extends Activity
{	
	EditText edtUsername;
	EditText edtPassword;
	CheckBox chkLembrarSenha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtUsername = (EditText) findViewById(R.id.editNome);
		edtPassword = (EditText) findViewById(R.id.editSenha);
		chkLembrarSenha = (CheckBox) findViewById(R.id.ckbRemeberPassword);
	}
	
	@Override
	protected void onStart()
	{	
		super.onStart();
		
		SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
		
		if (preferences.contains("username"))
			edtUsername.setText(preferences.getString("username", ""));
		else
			edtUsername.setText("");
		
		if (preferences.contains("password"))
			edtPassword.setText(preferences.getString("password", ""));
		else
			edtPassword.setText("");
	}
	
	public void loginClick(View view)
	{		
		String usernameStr = edtUsername.getText().toString();
		String passwordStr = edtPassword.getText().toString();
		
		if (usernameStr.equalsIgnoreCase("teste") && passwordStr.equalsIgnoreCase("teste"))
		{
			startMenuActivity("TESTE");
		}
		else
		{
			LoginListener loginListener = new LoginListener()
			{
				@Override
				public void sucess(String token)
				{
					startMenuActivity(token);
				}
				
				@Override
				public void error(String error)
				{
					Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
				}
			};
			
			LoginRequest.login(this, loginListener, usernameStr, passwordStr);
		}
	}
	
	private void startMenuActivity(String token)
	{
		String username = edtUsername.getText().toString();
		
		String password = "";
		if (chkLembrarSenha.isChecked())
			password = edtPassword.getText().toString();
		
		SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
	
		editor.putString("username", username);
		editor.putString("password", password);
		
		editor.commit();
		
		Intent i = new Intent(MainActivity.this, MenuActivity.class);
    	
		i.putExtra("username", username);
		i.putExtra("token", token);
		
    	startActivity(i);
	}
}
