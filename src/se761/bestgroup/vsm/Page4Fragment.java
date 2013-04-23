package se761.bestgroup.vsm;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Page4Fragment extends Fragment {
	

	private PatientModel _model;

	public Page4Fragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_model = (PatientModel) getArguments().get("model");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_page_4, container, false);
		
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
}
