package br.furb.avamobile;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int id = item.getItemId();
		if (id == R.id.atualizar) 
		{
			//new HttpRequestTask().execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void loginClick(View view)
	{
		new HttpRequestTask().execute();
	}
	
	private class HttpRequestTask extends AsyncTask<Void, Void, String> 
	{
		
        @Override
        protected String doInBackground(Void... params) 
        {
            try
            {
            	final String url = "http://192.168.56.1:8080/furb-services/login";
            	
            	TextView edtNome = (TextView) findViewById(R.id.editNome);
	            TextView edtSenha = (TextView) findViewById(R.id.editSenha);
	            
	            String nome = edtNome.getText().toString();
	            String senha = edtSenha.getText().toString();
            	
            	//*
            	
            	HttpClient httpClient = new DefaultHttpClient();
            	HttpContext localContext = new BasicHttpContext();
            	HttpGet httpGet = new HttpGet(url);
            	
            	httpGet.addHeader("username", nome);
            	httpGet.addHeader("password", senha);

        		HttpResponse response = httpClient.execute(httpGet, localContext);        		
    			
    			/*/
    			// Exemplo com método POST
                HttpPost post = new HttpPost(url);
                
                StringEntity stringEntity = new StringEntity(json.toString());

                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                
                post.setEntity(stringEntity);

                HttpResponse response = httpClient.execute(post);
                
                //*/
        		
        		String jsonString = EntityUtils.toString(response.getEntity());
                
    			return jsonString;
            }
            catch (Exception ex)
            {
            	return "{ \"error\" = \"" + ex.getMessage() + "\" }";
            }
        }

        @Override
        protected void onPostExecute(String jsonString)
        {
        	try
        	{
	        	JSONObject json = new JSONObject(jsonString);
	    		
	    		if (json.has("error"))
	    		{
	    			Toast.makeText(MainActivity.this, json.getString("error"), Toast.LENGTH_LONG).show();
	    			return;
	    		}
	        	
	            TextView editToken = (TextView) findViewById(R.id.editToken);
	            
	            editToken.setText(Integer.toString(json.getInt("token")));
        	}
        	catch (JSONException ex)
        	{
        		Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        	}
        }
    }
}
