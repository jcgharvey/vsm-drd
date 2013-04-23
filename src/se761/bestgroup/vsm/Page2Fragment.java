package se761.bestgroup.vsm;

import java.util.Arrays;

import se761.bestgroup.vsm.PatientModel.Gender;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Page2Fragment extends Fragment {

	private PatientModel _model;

	public Page2Fragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_model = (PatientModel) getArguments().get("model");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_page_2, container, false);

		// Set up the gender spinner
		final Spinner bloodTypeSpinner = (Spinner) root.findViewById(R.id.bloodType);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.bloodTypes, R.layout.custom_spinner_list);
		adapter.setDropDownViewResource(R.layout.custom_spinner);

		bloodTypeSpinner.setAdapter(adapter);
		//bloodTypeSpinner.setSelection(Arrays.asList(getResources().getStringArray(R.array.bloodTypes)).indexOf(_model.getBloodType().toString()));

		bloodTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				//_model.setGender(Gender.valueOf((String) bloodTypeSpinner.getItemAtPosition(position)));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});

		return root;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	public static Page2Fragment newInstance(PatientModel model) {
		Page2Fragment newFragment = new Page2Fragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("model", model);
		newFragment.setArguments(bundle);
		return newFragment;
	}
}
