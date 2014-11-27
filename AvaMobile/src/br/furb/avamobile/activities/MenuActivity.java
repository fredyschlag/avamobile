package br.furb.avamobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.furb.avamobile.R;
import br.furb.avamobile.core.ServerMethods;

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
					Intent i = new Intent(MenuActivity.this, FinanceiroActivity.class);
					//TODO Alterar a linha abaixo para pegar os dados financeiros do usuário logado
	    			i.putExtra("dados", ServerMethods.retornarDadosFinanceiros());	    			
	    	    	startActivity(i);
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
