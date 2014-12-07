package jp.co.basenet.findutonightclient.dao;

import android.database.sqlite.SQLiteDatabase;

public class DaoFactory {
	private SQLiteDatabase sdb;
    private TChatroomDao tChatroomDao = null;
    
    public DaoFactory(SQLiteDatabase sdb) {
    	this.sdb = sdb;
    }
    
    public TChatroomDao getTChatroomDao() {
    	if(tChatroomDao == null) {
    		tChatroomDao = new TChatroomDao(sdb);
    	}
    	return tChatroomDao;
    }
}
