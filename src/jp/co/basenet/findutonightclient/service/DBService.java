package jp.co.basenet.findutonightclient.service;

import jp.co.basenet.findutonightclient.common.Const;
import jp.co.basenet.findutonightclient.dao.DaoFactory;
import jp.co.basenet.findutonightclient.dao.DatabaseOpenHelper;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class DBService extends Service {
	
	private SQLiteDatabase sdb;
	private DaoFactory factory;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	
	public void onCreate() {
		super.onCreate();
		
		//OPEN
		DatabaseOpenHelper openDAO = new DatabaseOpenHelper(getApplicationContext(), "chat_db", null, 1);
		sdb = openDAO.getWritableDatabase();
		factory = new DaoFactory(sdb);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Intent it;
		
		if(intent.getAction() != null) {
			switch(intent.getAction()) {
			case Const.DB_ORDER.REQ_CHAT_ROOM_INFO_LIST:
				it = new Intent(Const.DB_ORDER.RES_CHAT_ROOM_INFO_LIST);
				it.putExtra("RESULT", factory.getTChatroomDao().selectChatRoomInfoList());
				super.sendBroadcast(it);
			}
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		
	}

}
