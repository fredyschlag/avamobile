package br.furb.avamobile.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.furb.avamobile.R;
import br.furb.avamobile.core.FinanceiroDataRequest;
import br.furb.avamobile.core.FinanceiroDataRequest.FinanceiroDataListener;
import br.furb.avamobile.core.FinanceiroData;
import br.furb.avamobile.core.FinanceiroLink;
import br.furb.avamobile.core.FinanceiroLinkRequest;
import br.furb.avamobile.core.FinanceiroLinkRequest.FinanceiroLinkListener;
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
				if (!token.equals(""))
				{
					final FinanceiroDataListener fdrListener = new FinanceiroDataListener()
					{						
						@Override
						public void sucess(List<FinanceiroData> dados) throws Exception
						{
							Intent i = new Intent(MenuActivity.this, FinanceiroActivity.class);
			    			
							i.putExtra("dados", ServerMethods.montarDadosFinanceiros(dados));
			    	    	
							startActivity(i);
						}
						
						@Override
						public void error(String error)
						{
							Toast.makeText(MenuActivity.this, error, Toast.LENGTH_LONG).show();
						}
					};
					
					final FinanceiroLinkListener flrListener = new FinanceiroLinkListener()
					{
						@Override
						public void sucess(List<FinanceiroLink> links)
						{
							if (links.size() > 0)
							{
								FinanceiroLink link = links.get(links.size() - 1);
								
								new FinanceiroDataRequest(MenuActivity.this, fdrListener).execute(token, link);
							}
							else
								Toast.makeText(MenuActivity.this, "Não há convênios financeiros", Toast.LENGTH_LONG).show();
						}
						
						@Override
						public void error(String error)
						{
							Toast.makeText(MenuActivity.this, error, Toast.LENGTH_LONG).show();
						}
					};
					
					new FinanceiroLinkRequest(MenuActivity.this, flrListener).execute(token);
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
