package jp.co.basenet.findutonightclient.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	public DatabaseOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sqlStr;
		
		//t_chatroom
		sqlStr = "create table t_chatpartener " +
		         "( " +
		         "PartnerId varchar(50), " +
		         "ChatroomID varchar(16), " +
		         "primary key (PartnerId, ChatroomID) " +
		         ") ";
		db.execSQL(sqlStr);
		
		//t_chatmessage
		sqlStr = "create table t_chatmessage " +
				 "( " +
				 "ChatmessageId int, " +
				 "ChatroomId varchar(16), " +
				 "Chatmessage varchar(1024), " +
				 "SenderId varchar(50), " +
				 "ReadedFlg varchar(1), " +
				 "SendedFlg varchar(1), " +
				 "SenderFlg varchar(1), " +
				 "Timestamp varchar(19), " +
				 "primary key (ChatmessageId, ChatroomId) " +
                 ") ";
		db.execSQL(sqlStr);
		
		//t_partenerinfo
		sqlStr = "create table t_partenerinfo " +
				 "( " +
				 "PartnerId varchar(50) primary key, " +
				 "Icon BLOB "  +
                 ") ";
		db.execSQL(sqlStr);
		
		//t_chatroom
		sqlStr = "create table t_chatroom " +
				 "( " +
				 "ChatroomId varchar(16) primary key, " +
				 "SurviveFlg varchar(1), " +
				 "ShopName varchar(100) " +
				 ") ";
		db.execSQL(sqlStr);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
