package se761.bestgroup.vsm;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		//inflate the layout
		setContentView(R.layout.activity_login);
		
		
		//Bind the button click
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View button) {
				Intent i = new Intent(LoginActivity.this, VitalStatsFormActivity.class);
				startActivity(i);
			}
		});
	}
}