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
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
	
	private class HttpRequestTask extends AsyncTask<Void, Void, User> 
	{
		
        @Override
        protected User doInBackground(Void... params) 
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
        		
        		String jsonString = EntityUtils.toString(response.getEntity());
    			
        		JSONObject json = new JSONObject(jsonString);
    						
        		User user = new User();
        		
        		user.setUsername(json.getString("username"));
        		user.setPassword(json.getString("password"));
        		user.setToken(json.getInt("token"));
    			
    			/*/
            	
	            User usuario = new User(nome, senha);
	            
            	JSONObject json = usuario.toJSONObject();
            	
            	HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                
                StringEntity stringEntity = new StringEntity(json.toString());

                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                
                post.setEntity(stringEntity);

                HttpResponse response = client.execute(post);
                
                System.out.print(response.toString());
                
                String responseBody = EntityUtils.toString(response.getEntity());
                
                System.out.print(responseBody);
                
                User user = new User(responseBody);
                
                //*/
                
    			return user;
            }
            catch (Exception ex)
            {            
            	ex.printStackTrace();
            	return null;
            }
        }

        @Override
        protected void onPostExecute(User usuario)
        {
        	if (usuario != null)
        	{
	            TextView editToken = (TextView) findViewById(R.id.editToken);
	            
	            editToken.setText(usuario.getToken());
        	}
        }
    }
}
