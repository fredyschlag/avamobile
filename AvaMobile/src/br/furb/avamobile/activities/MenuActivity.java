package br.furb.avamobile.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.furb.avamobile.R;

public class MenuActivity extends Activity
{
	String token;
	
	Button btnConsultaFinanceiro;
	Button btnFinalizaSessao;
	
	TextView txtUsername;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		
		btnConsultaFinanceiro = (Button) findViewById(R.id.btnConsultarFinanceiro);
		btnConsultaFinanceiro.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				// TODO Gielez: Aqui deve ser aberta a interface com a listagem do financeiro
				
				if (!token.equals(""))
	    		{
	    			//TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
	    			
	    			//Intent i = new Intent(MenuActivity.this, FinanceiroActivity.class);
	    	    	
	    			//i.putExtra("username", txtUsername.getText().toString());
	    			//i.putExtra("token", Integer.toString(token));
	    			
	    	    	//startActivity(i);
	    		}
			}
		});
		
		btnFinalizaSessao = (Button) findViewById(R.id.btnFinalizaSessao);
		btnFinalizaSessao.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				if (!token.equals(""))
	    		{
	    			// TODO: Informar o web-service sobre o fim da sessão
	    		}
				
				token = "";
				
				finish();
			}
		});
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();

		txtUsername.setText(getIntent().getStringExtra("username"));
		
		token = getIntent().getStringExtra("token");
		
		if (token.equals(""))
			finish();
	}
	
	@Override
	public void onBackPressed()
	{
		//
	}

}
