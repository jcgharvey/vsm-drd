package se761.bestgroup.vsm;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public boolean onActionAdd(MenuItem item){
    	Toast.makeText(this, "ADD", Toast.LENGTH_LONG).show();
    	return true;
    }
    
    public boolean onActionRefresh(MenuItem item){
    	Toast.makeText(this, "REFRESH", Toast.LENGTH_LONG).show();
    	return true;
    }
    
}
