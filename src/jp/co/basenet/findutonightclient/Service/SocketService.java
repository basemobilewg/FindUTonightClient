package jp.co.basenet.findutonightclient.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SocketService extends Service implements Runnable {
	private String msg = null;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if("SEND".equals(intent.getAction())) {
			this.msg = intent.getStringExtra("msg");
			this.sendBroadcastToActivity();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public void sendBroadcastToActivity() {
		Intent it = new Intent("RECEIVE");
		it.putExtra("back", this.msg + "from service");
		super.sendBroadcast(it);
	}

}
