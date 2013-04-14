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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemActivity extends Activity {

	private class SubmitVitalStats extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			
			//instantiates httpclient to make request
		    DefaultHttpClient httpclient = new DefaultHttpClient();

		    //url with the post data
		    HttpPost httpost = new HttpPost("http://vsm.herokuapp.com/new");
		    JSONObject data = new JSONObject();
			
			try {
				data.put("firstName", params[0]);
				data.put("lastName", params[1]);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		    //passes the results to a string builder/entity
		    StringEntity se = null;
			try {
				se = new StringEntity(data.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}

		    //sets the post request as the resulting string
		    httpost.setEntity(se);
		    //sets a request header so the page receving the request
		    //will know what to do with it
		    httpost.setHeader("Accept", "application/json");
		    httpost.setHeader("Content-type", "application/json");
		
		    
		    try {
				HttpResponse response = httpclient.execute(httpost);
				InputStream content = response.getEntity().getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(content));
				String line;
				StringBuilder sb = new StringBuilder();
				while((line = br.readLine())!= null){
					sb.append(line);
				}
				
				Log.d("VSM", sb.toString());
			} catch (ClientProtocolException e) {
				
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}			
			
			return true;
		}		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_item);
	}
	
	public void onSubmitClick(View v){
		Intent result = new Intent();
		
		EditText fnInput = (EditText)findViewById(R.id.firstName);
		EditText lnInput = (EditText)findViewById(R.id.lastName);
		
		String firstName = fnInput.getText().toString();
		result.putExtra("firstName", firstName);
		String lastname = lnInput.getText().toString();
		result.putExtra("lastName", lastname);
		setResult(RESULT_OK, result);
		
		//Lets do a network call.
		SubmitVitalStats task = new SubmitVitalStats();
		task.execute(new String[] {firstName, lastname});
		
		finish();
	}
	
	
}
