package se761.bestgroup.vsm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


public class VitalStatsFormActivity extends Activity{
	
	private ViewPager _viewPager;
	private PagerAdapter _pagerAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_pager);
		
		_viewPager = (ViewPager) findViewById(R.id.pager);
		_pagerAdapter = new SliderAdapter(getFragmentManager());
		_viewPager.setAdapter(_pagerAdapter);
		_viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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

        menu.findItem(R.id.action_previous).setEnabled(_viewPager.getCurrentItem() > 0);

        // Add either a "next" or "finish" button to the action bar, depending on which page
        // is currently selected.
        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                (_viewPager.getCurrentItem() == _pagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }
	

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                _viewPager.setCurrentItem(_viewPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                _viewPager.setCurrentItem(_viewPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
	private class SliderAdapter extends FragmentStatePagerAdapter{
		
		private List<Fragment> _pages;
		
		public SliderAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			_pages = new ArrayList<Fragment>();
			_pages.add(new Page1Fragment());
			_pages.add(new Page2Fragment());
			_pages.add(new Page3Fragment());
			_pages.add(new Page4Fragment());
			_pages.add(new Page5Fragment());
			
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
}
