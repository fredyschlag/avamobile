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
	int token;
	
	Button btnConsultaFinanceiro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		btnConsultaFinanceiro = (Button) findViewById(R.id.btnConsultarFinanceiro);
		btnConsultaFinanceiro.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				// TODO Gielez: Aqui deve ser aberta a interface com a listagem do financeiro
				
				if (token != 0)
	    		{
	    			//TextView txtUsername = (TextView) findViewById(R.id.txtUsername);
	    			
	    			//Intent i = new Intent(MenuActivity.this, FinanceiroActivity.class);
	    	    	
	    			//i.putExtra("username", txtUsername.getText().toString());
	    			//i.putExtra("token", Integer.toString(token));
	    			
	    	    	//startActivity(i);
	    		}
			}
		});
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		
		TextView txtUsername = (TextView) findViewById(R.id.txtUsername);

		txtUsername.setText(getIntent().getStringExtra("username"));
		
		token =  getIntent().getIntExtra("token", 0);
	}

}
