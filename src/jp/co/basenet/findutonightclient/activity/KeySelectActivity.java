package jp.co.basenet.findutonightclient.activity;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.fragment.KeySelectFragment;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class KeySelectActivity extends Activity {
		
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_select);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle(title)
        
        //最初のFragmentを作成
        Fragment fragment = Fragment.instantiate(this, KeySelectFragment.class.getName());
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.frame_find, fragment).commit();
        
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
