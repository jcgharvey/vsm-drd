package se761.bestgroup.vsm;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Page5Fragment extends ListFragment {
	
	private PatientModel _model;
	private ArrayAdapter<String> _adapter;
	
	public Page5Fragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_model = (PatientModel) getArguments().get("model");
		setHasOptionsMenu(true);
		_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		setListAdapter(_adapter);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		MenuItem addAllergy = menu.add(Menu.NONE, R.id.action_add_allergy, 0, "");
		addAllergy.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		addAllergy.setIcon(R.drawable.content_new);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId() == R.id.action_add_allergy){
			Toast.makeText(getActivity(), "+", Toast.LENGTH_LONG).show();
		}
		return true;
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}

	public static Page5Fragment newInstance(PatientModel model) {
		Page5Fragment newFragment = new Page5Fragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("model", model);
		newFragment.setArguments(bundle);
		return newFragment;
	}
}
