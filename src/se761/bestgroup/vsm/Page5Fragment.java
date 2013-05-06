package se761.bestgroup.vsm;

import se761.bestgroup.vsm.AddAlergyDialogFragment.AddAlergyDiaglogListener;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Page5Fragment extends Fragment {
	
	private PatientModel _model;
	private ArrayAdapter<String> _adapter;
	private int _positionLongClicked;
	private ListView _listView;
	
	public Page5Fragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_model = (PatientModel) getArguments().get("model");
		setHasOptionsMenu(true);
		setRetainInstance(true);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_page_5, container, false);
		
		_listView = (ListView) root.findViewById(R.id.allergiesListView);
		_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
		
		_listView.setAdapter(_adapter);
		_adapter.addAll(_model.getAllergies());
		
		
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		registerForContextMenu(_listView);
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		_positionLongClicked = ((AdapterView.AdapterContextMenuInfo)menuInfo).position; 
		menu.add(menu.NONE, R.id.action_delete_allergy, menu.NONE, "Delete");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.action_delete_allergy){
			_model.getAllergies().remove(_adapter.getItem(_positionLongClicked));
			_adapter.remove(_adapter.getItem(_positionLongClicked));
		}
		return true;
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
			AddAlergyDialogFragment dialog = new AddAlergyDialogFragment();
			dialog.setAddAlergyDialogListener(new AddAlergyDiaglogListener() {
				
				@Override
				public void onPositiveClick(String value) {
					_adapter.add(value);
					_model.getAllergies().add(value);
				}
			});
			dialog.show(getFragmentManager(), "AddAlergyDialog");
		}
		return true;
	}
	


	public static Page5Fragment newInstance(PatientModel model) {
		Page5Fragment newFragment = new Page5Fragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable("model", model);
		newFragment.setArguments(bundle);
		return newFragment;
	}
}
