package se761.bestgroup.vsm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {

	private String _pin;
	private String _correctPin;
	private List<TextView> _textViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		_pin = "";
		_correctPin = "00000";
		// inflate the layout
		setContentView(R.layout.activity_login);
			
		_textViews = new ArrayList<TextView>();
		_textViews.add((TextView) findViewById(R.id.pin1));
		_textViews.add((TextView) findViewById(R.id.pin2));
		_textViews.add((TextView) findViewById(R.id.pin3));
		_textViews.add((TextView) findViewById(R.id.pin4));
		_textViews.add((TextView) findViewById(R.id.pin5));
		
		// Bind the button click
		OnClickListener buttonClickListener = new OnClickListener() {
			@Override
			public void onClick(View view) {
				int id = view.getId();
				
				if(id == R.id.buttonBackspace){
					if(_pin.length() == 0) return;
					_pin = _pin.substring(0, _pin.length() - 1);
				}else if(id == R.id.buttonForgotPin){
					_pin = _correctPin; //LOLOL you can login with the forgot pin button
				}else if(_pin.length() == 5){
					return; //The textviews are full
				}
				
				switch (id){
				case R.id.button0:
					_pin += "0";
					break;
				case R.id.button1:
					_pin += "1";
					break;
				case R.id.button2:
					_pin += "2";
					break;
				case R.id.button3:
					_pin += "3";
					break;
				case R.id.button4:
					_pin += "4";
					break;
				case R.id.button5:
					_pin += "5";
					break;
				case R.id.button6:
					_pin += "6";
					break;
				case R.id.button7:
					_pin += "7";
					break;
				case R.id.button8:
					_pin += "8";
					break;
				case R.id.button9:
					_pin += "9";
					break;
				}
				Log.i("VSM", _pin);
				
				for(TextView tv : _textViews){
					tv.setText("");
				}
				for(int i = 0; i < _pin.length(); i++){
					_textViews.get(i).setText("" + _pin.charAt(i));
				}
				
				if(_pin.length() == 5){ //The textviews are full
					if(_pin.equals(_correctPin)) {
						Intent i = new Intent(getApplicationContext(), VitalStatsFormActivity.class);
						startActivity(i);
					}
				}
			}
		};
		
		((Button)findViewById(R.id.button0)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button1)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button2)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button3)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button4)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button5)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button6)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button7)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button8)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.button9)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.buttonBackspace)).setOnClickListener(buttonClickListener);
		((Button)findViewById(R.id.buttonForgotPin)).setOnClickListener(buttonClickListener);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		_pin = "";
		
		for(TextView tv : _textViews){
			tv.setText("");
		}
		for(int i = 0; i < _pin.length(); i++){
			_textViews.get(i).setText("" + _pin.charAt(i));
		}
	}
}










