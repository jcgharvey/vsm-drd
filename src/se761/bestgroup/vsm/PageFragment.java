package se761.bestgroup.vsm;

import android.app.Fragment;

public abstract class PageFragment extends Fragment {

	protected PatientModel _model;

	public PageFragment(){
		
	}
	
	public PageFragment(PatientModel model){
		this._model = model;
		
	}

}
