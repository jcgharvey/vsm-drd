package se761.bestgroup.vsm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MenuActivity extends ListActivity implements OnItemClickListener {

	private ArrayAdapter<String> _adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		setListAdapter(_adapter);
		_adapter.add("Edit Vital Information");
		_adapter.add("Transmit Vital Information");
		
		getListView().setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View item, int position, long rowID) {
		
		Intent i;
		switch (position){
		case 0:
			i = new Intent(this, VitalStatsFormActivity.class);
			startActivity(i);
			break;
		case 1:
			i = new Intent(this, TransmitStatsActivity.class);
			startActivity(i);
			break;
		case 2:
			break;
		}
	}

	

	
	
}
