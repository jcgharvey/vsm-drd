package se761.bestgroup.vsm;

import org.json.JSONException;

import se761.bestgroup.vsm.model.Keys;
import se761.bestgroup.vsm.model.PatientModel;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);

	}

	public void menuButtonClick(View v) {

		Intent i;

		switch (v.getId()) {
		case R.id.menuEditButton:
			i = new Intent(this, VitalStatsFormActivity.class);
			startActivity(i);
			break;
		case R.id.menuTransmitButton:

			boolean modelIsValid = false;
			// Restore model from shared preferences
			SharedPreferences sharedPreferences = getSharedPreferences(Keys.VSM, MODE_PRIVATE);
			String jsonString = sharedPreferences.getString(Keys.MODEL, null);

			PatientModel _model = new PatientModel();

			if (jsonString != null) {
				try {
					_model.fromJSON(jsonString);
					if (_model.isValid()) {
						modelIsValid = true;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			if (modelIsValid) {
				// Check if it's valid
				i = new Intent(this, TransmitStatsActivity.class);
				startActivity(i);
			} else {
				Builder b = new Builder(this);
				b.setTitle("Can't Transmit");
				TextView textView = new TextView(this);
				textView.setText("You've entered a blank or invalid value.\nPlease go to the form and check what you've entered");
				b.setPositiveButton("OK", new OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
				b.setView(textView);
				b.create().show();
			}
			break;
		}

	}

}
