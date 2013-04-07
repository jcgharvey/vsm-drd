package se761.bestgroup.vsm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewItemActivity extends Activity {

	private class SubmitVitalStats extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			
			
			HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("localhost");

		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("id", "12345"));
		        nameValuePairs.add(new BasicNameValuePair("stringdata", "Hi"));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);

		    } catch (ClientProtocolException e) {
		       
		    } catch (IOException e) {
		       
		    }
			
			Boolean success = false;
			return success ;
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
