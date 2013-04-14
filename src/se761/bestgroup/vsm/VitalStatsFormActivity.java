package se761.bestgroup.vsm;

import java.util.ArrayList;

import se761.bestgroup.vsm.CountriesListDialogFragment.CountriesListDialogListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
