package se761.bestgroup.vsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class SubmitTask extends AsyncTask<JSONObject, Void, HttpResponse> {

	private Context _context;


	public SubmitTask(Context c) {
		_context = c;
	}
	
	@Override
	protected HttpResponse doInBackground(JSONObject... params) {
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

	    // url with the post data
	    HttpPost httpost = new HttpPost("http://vsm.herokuapp.com/patients");

	    // passes the results to a string builder/entity
	    StringEntity se = null;
		try {
			se = new StringEntity(params.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	    //sets the post request as the resulting string
	    httpost.setEntity(se);
	    //sets a request header so the page receving the request
	    //will know what to do with it
	    httpost.setHeader("Accept", "application/json");
	    httpost.setHeader("Content-type", "application/json");

	    HttpResponse response;
	    try {
			
			response = httpclient.execute(httpost);
			
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}			

		return response;
	}		
	

	@Override
	protected void onPostExecute(HttpResponse response) {
		try{
			InputStream content = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(content));
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine())!= null){
				sb.append(line);
			}
			
			Toast.makeText(_context, sb.toString(), Toast.LENGTH_LONG).show();
		}catch(Exception e){
			
		}
	}

}
