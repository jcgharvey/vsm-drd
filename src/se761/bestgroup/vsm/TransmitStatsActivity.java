package se761.bestgroup.vsm;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;

public class TransmitStatsActivity extends Activity  implements
CreateNdefMessageCallback{
	
	private NfcAdapter mNfcAdapter;
	private PatientModel _model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_transmit);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		mNfcAdapter.setNdefPushMessageCallback(this, this);
		
		SharedPreferences sharedPreferences = getSharedPreferences(Keys.VSM, MODE_PRIVATE);		
		String jsonModel = sharedPreferences.getString(Keys.MODEL, null);
		_model = new PatientModel();
		
		if (jsonModel != null) {
			Log.d("VSM", "Deserializing saved model");			
			try {
				_model.fromJSON(jsonModel);
				// set check in time.
				_model.setCheckInTime();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.d("VSM", "Blank Model");
		}
	}
	
	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
		Time time = new Time();
		time.setToNow();
		
		String packageName = Keys.PACKAGE;
		String json = _model.toJSON().toString();
		
		NdefMessage msg = new NdefMessage(
				NdefRecord.createMime(packageName, json.getBytes()));
		Log.v("NDEF",json);
		Log.v("NDEF",msg.getRecords().length+"");
		return msg;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mNfcAdapter.setNdefPushMessageCallback(null, this);
		Log.d("nfc", "being called");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent parentActivityIntent = new Intent(this, MenuActivity.class);
		parentActivityIntent.addFlags(
				Intent.FLAG_ACTIVITY_CLEAR_TOP |
				Intent.FLAG_ACTIVITY_NEW_TASK);
		
		switch(item.getItemId()){
		case android.R.id.home:
			// This is called when the Home (Up) button is pressed
            // in the Action Bar.
            startActivity(parentActivityIntent);
            finish();
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
