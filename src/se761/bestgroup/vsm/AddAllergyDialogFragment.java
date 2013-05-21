package se761.bestgroup.vsm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddAllergyDialogFragment extends DialogFragment {

	private AddAlergyDiaglogListener _listener;

	public interface AddAlergyDiaglogListener {
		void onPositiveClick(String value);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View view = layoutInflater.inflate(R.layout.add_allergy_dialog, null);
		final EditText alergyInput = (EditText) view
				.findViewById(R.id.addAlergyInput);
		builder.setView(view).setTitle("Add an Allergy")
				.setPositiveButton("Add", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						_listener.onPositiveClick(alergyInput.getText()
								.toString());
					}
				}).setNegativeButton("Cancel", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						getDialog().cancel();
						// Same as pressing the back button
					}
				});

		final AlertDialog dialog = builder.create();
		alergyInput.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (PatientModel.isValidAllergy(s.toString())) {
					alergyInput.setError(null);
					dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(
							true);
				} else {
					dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(
							false);
					alergyInput.setError("You can't use semicolons");
				}
			}

		});

		return dialog;
	}

	public void setAddAlergyDialogListener(AddAlergyDiaglogListener listener) {
		this._listener = listener;

	}
}
