package se761.bestgroup.vsm;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import se761.bestgroup.vsm.CountriesListDialogFragment.CountriesListDialogListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class VitalStatsFormActivity extends Activity implements CountriesListDialogListener{
	
	private CountriesListDialogFragment _selectCountriesDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_vital_stats_form);
		
		//Set up the gender spinner
		Spinner genderSpinner = (Spinner) findViewById(R.id.gender);
		ArrayAdapter<CharSequence> genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
		genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		genderSpinner.setAdapter(genderSpinnerAdapter);
		
		
		//Set up the blood type spinner
		Spinner bloodTypeSpinner = (Spinner) findViewById(R.id.bloodType);
		ArrayAdapter<CharSequence> bloodTypeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.bloodTypes, android.R.layout.simple_spinner_item);
		bloodTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bloodTypeSpinner.setAdapter(bloodTypeSpinnerAdapter);
		
		//Set up the countries selection dialog
		
		_selectCountriesDialog = new CountriesListDialogFragment();
		_selectCountriesDialog.addListener(this);
		
		TextView countriesEditText = (TextView) findViewById(R.id.countries);
		countriesEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				VitalStatsFormActivity.this._selectCountriesDialog.show(getFragmentManager(), "VSM");
				
			}
		});
		
		
		//Set up submit button binding
		Button submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Create the JSONObject and then pass it to our AsyncTask subclass to send it.
				SubmitTask task = new SubmitTask(VitalStatsFormActivity.this);
				JSONObject params = new JSONObject();
				JSONObject patient = new JSONObject();
					
				try {
					patient.put("firstname", ((EditText)findViewById(R.id.firstName)).getText().toString());
					patient.put("lastname", ((EditText)findViewById(R.id.lastName)).getText().toString());
					patient.put("nhi", ((EditText)findViewById(R.id.nhi)).getText().toString());
					patient.put("occupation", ((EditText)findViewById(R.id.occupation)).getText().toString());
					patient.put("citizen_resident", ((Switch)findViewById(R.id.citizenOrResident)).isChecked());
					patient.put("contact_num", Integer.parseInt(((EditText)findViewById(R.id.contactNumber)).getText().toString()));
					patient.put("gender", ((Spinner)findViewById(R.id.gender)).getSelectedItem().toString());
					patient.put("dob", "2/11/2992");//PLACEHOLDER
					
					
					params.put("patient", patient);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				task.execute(params);
				
			}
		});
		
	}

	@Override
	public void onPositiveClick(CountriesListDialogFragment dialog) {
		ArrayList<Integer> selectedCountries = _selectCountriesDialog.getSelectedCountries();
		
		
	}

	@Override
	public void onNegativeClick(CountriesListDialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}
