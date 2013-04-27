package se761.bestgroup.vsm;

import java.util.ArrayList;

import se761.bestgroup.vsm.CountriesListDialogFragment.CountriesListDialogListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Page4Fragment extends Fragment implements
		CountriesListDialogListener {

	private PatientModel _model;
	private CountriesListDialogFragment _selectCountriesDialog;

	public Page4Fragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_model = (PatientModel) getArguments().get("model");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_page_4,
				container, false);

		// Set up the countries selection dialog

		_selectCountriesDialog = new CountriesListDialogFragment();
		_selectCountriesDialog.addListener(this);

		Button editCountriesButton = (Button) root
				.findViewById(R.id.editCountriesButton);
		editCountriesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Page4Fragment.this._selectCountriesDialog.show(
						getFragmentManager(), "VSM");

			}
		});

		return root;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	public static Page4Fragment newInstance(PatientModel model) {
		Page4Fragment newFragment = new Page4Fragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("model", model);
		newFragment.setArguments(bundle);
		return newFragment;
	}

	@Override
	public void onPositiveClick(CountriesListDialogFragment dialog) {
		ArrayList<Integer> selectedCountries = _selectCountriesDialog
				.getSelectedCountries();
		String countriesString = "Selected:\n";
		String[] countries = getResources().getStringArray(R.array.countries);
		int count = 0;
		for (Integer i : selectedCountries) {
			countriesString += countries[i];
			count++;
			if (count == 5) {
				break;
			} else {
				countriesString += ", ";
			}
		}
		countriesString += " ...";

		
		TextView tv = (TextView) getView().findViewById(R.id.countries);
		tv.setText(countriesString);
	}

	@Override
	public void onNegativeClick(CountriesListDialogFragment dialog) {
		
	}
}
