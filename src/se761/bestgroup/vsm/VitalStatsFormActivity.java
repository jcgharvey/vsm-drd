package se761.bestgroup.vsm;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences.Editor;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class VitalStatsFormActivity extends Activity implements
		CreateNdefMessageCallback {

	private ViewPager _viewPager;
	private PagerAdapter _pagerAdapter;
	private PatientModel _model;
	private NfcAdapter mNfcAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_pager);

		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		mNfcAdapter.setNdefPushMessageCallback(null, this);

		String jsonSerializedModel = getPreferences(MODE_PRIVATE).getString(
				"model", null);
		_model = new PatientModel();
		if (jsonSerializedModel != null) {
			Log.d("VSM", "Deserializing saved model");
			try {
				_model.fromJSONString(jsonSerializedModel);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.d("VSM", "Blank Model");
		}

		_viewPager = (ViewPager) findViewById(R.id.pager);
		_pagerAdapter = new SliderAdapter(getFragmentManager());
		_viewPager.setAdapter(_pagerAdapter);
		_viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						invalidateOptionsMenu();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

		menu.findItem(R.id.action_previous).setEnabled(
				_viewPager.getCurrentItem() > 0);

		// Add either a "next" or "finish" button to the action bar, depending
		// on which page
		// is currently selected.
		MenuItem item = menu
				.add(Menu.NONE, (_viewPager.getCurrentItem() == _pagerAdapter
						.getCount() - 1) ? R.id.action_finish
						: R.id.action_next, 2,
						(_viewPager.getCurrentItem() == _pagerAdapter
								.getCount() - 1) ? R.string.action_finish
								: R.string.action_next);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Save the model's state
		Editor preferencesEditor = getPreferences(MODE_PRIVATE).edit();
		preferencesEditor.putString("model", _model.toJSON().toString());
		preferencesEditor.apply();
		Log.d("VSM", "Serializing and saving model");
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_previous:
			// Go to the previous step in the wizard. If there is no previous
			// step,
			// setCurrentItem will do nothing.
			_viewPager.setCurrentItem(_viewPager.getCurrentItem() - 1);
			return true;

		case R.id.action_next:
			// Advance to the next step in the wizard. If there is no next step,
			// setCurrentItem
			// will do nothing.
			_viewPager.setCurrentItem(_viewPager.getCurrentItem() + 1);
			return true;

		case R.id.action_finish:
			mNfcAdapter.setNdefPushMessageCallback(this, this);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private class SliderAdapter extends FragmentStatePagerAdapter {

		private List<Fragment> _pages;

		public SliderAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			_pages = new ArrayList<Fragment>();

			_pages.add(Page1Fragment.newInstance(_model));
			_pages.add(Page2Fragment.newInstance(_model));
			_pages.add(Page3Fragment.newInstance(_model));
			_pages.add(Page4Fragment.newInstance(_model));
			_pages.add(Page5Fragment.newInstance(_model));

		}

		@Override
		public Fragment getItem(int page) {
			return _pages.get(page);
		}

		@Override
		public int getCount() {
			return _pages.size();
		}

	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent arg0) {
		Time time = new Time();
		time.setToNow();
		String text = ("This could be JSON: " + time
				.format("%H:%M:%S"));
		text = _model.toJSON().toString();
		NdefMessage msg = new NdefMessage(NdefRecord.createMime(
				"application/se761.bestgroup.vsmreceiver", text.getBytes()));
		return msg;
	}
}
