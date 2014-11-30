package br.furb.avamobile.core;

import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class ServerRequest extends AsyncTask<NameValuePair, String, HttpResponse>
{
	private String url;
	private ServerRequestListener listener;
	private Context context; 
	
	public ServerRequest(Context context, String url, ServerRequestListener listener)
	{
		this.context = context;
		this.url = url;
		this.listener = listener;
	}
	
	@Override
	protected HttpResponse doInBackground(NameValuePair... params)
	{	
		try
    	{
			HttpClient httpClient = new DefaultHttpClient();
	    	HttpContext localContext = new BasicHttpContext();
	    	HttpGet httpGet = new HttpGet(url);
	    	
	    	for (NameValuePair param : params)
	    		httpGet.addHeader(param.getName(), param.getValue());
			
    		return httpClient.execute(httpGet, localContext);
    	}
    	catch (Exception ex)
    	{
    		return null;
    	}
	}
	
	@Override
	protected void onPreExecute()
	{
		if (isOnline())
			listener.onRequestStart();
		else
		{
			this.cancel(true);
			listener.onRequestComplete("Sem conexão com a internet", true);
		}
	}
	
	@Override
	protected void onPostExecute(HttpResponse result)
	{
		try
		{
			if (result == null)
				throw new Exception("Sem comunicação com o servidor");
			
			StatusLine statusLine = result.getStatusLine();
			
			if (statusLine.getStatusCode() != HttpURLConnection.HTTP_OK)
				throw new Exception("Erro na requisição: Código do erro = " + statusLine.getStatusCode());
			
			String jsonString = EntityUtils.toString(result.getEntity(), HTTP.UTF_8);
			
			listener.onRequestComplete(jsonString, false);
		}
		catch (Exception ex)
		{
			listener.onRequestComplete(ex.getMessage(), true);
		}
	}
	
	private boolean isOnline()
	{	
		ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}
	
	public interface ServerRequestListener 
	{
		void onRequestStart();
		void onRequestComplete(String response, boolean error);
	}
}
