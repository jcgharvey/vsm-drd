package se761.bestgroup.vsm;

import se761.bestgroup.vsm.PatientModel.Gender;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Page1Fragment extends Fragment {

	private PatientModel _model;

	public Page1Fragment(){
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_model = (PatientModel) getArguments().get("model");
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//Inflate the first page layout and set up the gender spinner
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_page_1, container, false); 
		
		final Spinner genderSpinner = (Spinner) root.findViewById(R.id.gender);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.genders, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		
		genderSpinner.setAdapter(adapter);
		
		genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				_model.setGender(Gender.valueOf((String) genderSpinner.getItemAtPosition(position)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		return root;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		//Save the state?
	}
	
	public static Page1Fragment newInstance(PatientModel model){
		Page1Fragment newFragment = new Page1Fragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("model", model);
		newFragment.setArguments(bundle);
		return newFragment;
	}
}
