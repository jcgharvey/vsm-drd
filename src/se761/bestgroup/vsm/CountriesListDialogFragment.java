package se761.bestgroup.vsm;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class CountriesListDialogFragment extends DialogFragment {
	
	private ArrayList<Integer> _selectedItems;
	private ArrayList<CountriesListDialogListener> _listeners;

	public CountriesListDialogFragment() {
		_selectedItems = new ArrayList<Integer>();  // Where we track the selected items
	}
	
	public interface CountriesListDialogListener{
		public void onPositiveClick(CountriesListDialogFragment dialog);
		public void onNegativeClick(CountriesListDialogFragment dialog);
	}
	
	public void addListener(CountriesListDialogListener listener){
		_listeners.add(listener);
	}
	
	public void removeListener(CountriesListDialogListener listener){
		_listeners.remove(listener);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle(R.string.pickCountries)
	    // Specify the list array, the items to be selected by default (null for none),
	    // and the listener through which to receive callbacks when items are selected
	           .setMultiChoiceItems(R.array.countries, null,
	                      new DialogInterface.OnMultiChoiceClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int which,
	                       boolean isChecked) {
	                   if (isChecked) {
	                       // If the user checked the item, add it to the selected items
	                       _selectedItems.add(which);
	                   } else if (_selectedItems.contains(which)) {
	                       // Else, if the item is already in the array, remove it 
	                       _selectedItems.remove(Integer.valueOf(which));
	                   }
	               }
	           })
	    // Set the action buttons
	           .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   // User clicked OK, so save the mSelectedItems results somewhere
	                   // or return them to the component that opened the dialog
	            	   for(CountriesListDialogListener listener : _listeners){
	            		   listener.onPositiveClick(CountriesListDialogFragment.this);
	            	   }
	                   
	               }
	           })
	           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   for(CountriesListDialogListener listener : _listeners){
	            		   listener.onNegativeClick(CountriesListDialogFragment.this);
	            	   }
	               }
	           });

	    return builder.create();
	}
	
	public ArrayList<Integer> getSelectedCountries(){
		return _selectedItems;
	}
}
