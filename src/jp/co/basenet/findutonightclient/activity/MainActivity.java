package jp.co.basenet.findutonightclient.activity;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.fragment.ChatTagFragment;
import jp.co.basenet.findutonightclient.fragment.HistoryTagFragment;
import jp.co.basenet.findutonightclient.fragment.HomeTagFragment;
import jp.co.basenet.findutonightclient.fragment.SettingTagFragment;
import jp.co.basenet.findutonightclient.listener.TabListener;
import jp.co.basenet.findutonightclient.service.SocketService;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	//�u���[�h�L���X�g�ʐM�p
	private BroadcastReceiver rec = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //ActionBar�擾
        final ActionBar actionBar = getActionBar();
        
        //ActionBar���i�r�Q�[�V�������[�h�ɐݒ�
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        //�l��Tab��listener��ݒ�
        actionBar.addTab(actionBar.newTab()
        		.setText("Home")
        		.setTabListener(new TabListener<HomeTagFragment>(this,
        					"home", HomeTagFragment.class, 1, "���X��T��")));
        
        actionBar.addTab(actionBar.newTab()
        		.setText("Chat")
        		.setTabListener(new TabListener<ChatTagFragment>(this,
        					"chat", ChatTagFragment.class, 2, "�`���b�g")));
        
        actionBar.addTab(actionBar.newTab()
        		.setText("His")
        		.setTabListener(new TabListener<HistoryTagFragment>(this,
        					"his", HistoryTagFragment.class, 3,"����")));
        
        
        actionBar.addTab(actionBar.newTab()
        		.setText("Set")
        		.setTabListener(new TabListener<SettingTagFragment>(this,
        					"set", SettingTagFragment.class, 4,"�ݒ�")));
        
        //Service�������Őݒ�(socket�ʐM�p)
        this.rec = new SocketInfoReceiver();
        
        //IntentFilter filter = new IntentFilter();
        //filter.addAction("RECEIVE");
        //MainActivity.this.registerReceiver(this.rec, filter);
        
        //SocketService�N��
		Intent intent = new Intent(this, SocketService.class);
		startService(intent);

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
    
    private class SocketInfoReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

		}
    }
}
