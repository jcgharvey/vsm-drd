package se761.bestgroup.vsm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private ArrayAdapter<String> adapter;
	private int positionLongClicked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);

		registerForContextMenu(getListView());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onActionAdd(MenuItem item) {
		Intent i = new Intent(this, NewItemActivity.class);
		startActivityForResult(i, 1);
		return false;
	}

	public boolean onActionRefresh(MenuItem item) {

		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		String firstName = data.getStringExtra("firstName");
		String lastName = data.getStringExtra("lastName");
		adapter.add(firstName + " " + lastName);
		
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// Get index of long clicked item
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		positionLongClicked = info.position;

		// Add menu options
		menu.add("Edit");
		menu.add("Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		CharSequence menuItemTitle = item.getTitle();
		if (menuItemTitle.equals("Edit")) {

		} else {
			adapter.remove(adapter.getItem(positionLongClicked));
		}
		return true;
	}

}
