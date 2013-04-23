package se761.bestgroup.vsm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddAlergyDialogFragment extends DialogFragment {

	private AddAlergyDiaglogListener _listener;

	public interface AddAlergyDiaglogListener{
		void onPositiveClick(String value);
	}
	
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.add_allergy_dialog, null);
		final EditText alergyInput = (EditText) view.findViewById(R.id.addAlergyInput);
		builder.setView(view)
		.setPositiveButton("Add", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				_listener.onPositiveClick(alergyInput.getText().toString());
			}
		})
		.setNegativeButton("Canecel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				getDialog().cancel();
				//Same as pressing the back button
			}
		});
		
		
		return builder.create();
	}
	
	public void setAddAlergyDialogListener(AddAlergyDiaglogListener listener){
		this._listener = listener;
		
	}
}
