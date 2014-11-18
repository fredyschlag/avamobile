package br.furb.avamobile.core;

import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public class ServerRequest extends AsyncTask<NameValuePair, String, HttpResponse>
{
	private String url;
	private ServerRequestListener listener;
	private Activity context; 
	
	public ServerRequest(Activity context, String url, ServerRequestListener listener)
	{
		this.context = context;
		this.url = url;
		this.listener = listener;
	}
	
	@Override
	protected HttpResponse doInBackground(NameValuePair... params)
	{			
		HttpClient httpClient = new DefaultHttpClient();
    	HttpContext localContext = new BasicHttpContext();
    	HttpGet httpGet = new HttpGet(url);
    	
    	for (NameValuePair param : params)
    		httpGet.addHeader(param.getName(), param.getValue());		
		
    	try
    	{
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
			listener.onRequestComplete("No internet connection", true);
		}
	}
	
	@Override
	protected void onPostExecute(HttpResponse result)
	{
		try
		{
			StatusLine statusLine = result.getStatusLine();
			
			if (statusLine.getStatusCode() != HttpURLConnection.HTTP_OK)
				throw new Exception("Request error: Status code = " + statusLine.getStatusCode());
			
			String jsonString = EntityUtils.toString(result.getEntity());
			
			listener.onRequestComplete(jsonString, false);
		}
		catch (Exception ex)
		{
			listener.onRequestComplete(ex.getMessage(), true);
		}
	}
	
	private boolean isOnline()
	{	
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}
	
	public interface ServerRequestListener 
	{
		void onRequestStart();
		void onRequestComplete(String response, boolean error);
	}
}
