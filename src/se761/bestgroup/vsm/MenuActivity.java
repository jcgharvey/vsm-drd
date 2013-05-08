package se761.bestgroup.vsm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu);
		
	}
	
	public void menuButtonClick(View v){
		Intent i = null;
		switch(v.getId()){
		case R.id.menuEditButton:
			i =  new Intent(this, VitalStatsFormActivity.class);
			break;
		case R.id.menuTransmitButton:
			i =  new Intent(this, TransmitStatsActivity.class);
			break;
		}
		
		startActivity(i);
	}
	
}
