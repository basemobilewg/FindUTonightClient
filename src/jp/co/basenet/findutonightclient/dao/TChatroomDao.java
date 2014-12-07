package jp.co.basenet.findutonightclient.dao;

import java.util.ArrayList;

import jp.co.basenet.findutonightclient.model.ChatRoomInfoBean;

import android.database.sqlite.SQLiteDatabase;

public class TChatroomDao {
    private SQLiteDatabase sdb;
    
	public TChatroomDao(SQLiteDatabase sdb) {
		this.sdb = sdb;
	}
	
	public ArrayList<ChatRoomInfoBean> selectChatRoomInfoList() {
		ArrayList<ChatRoomInfoBean> chatRoomInfoList = new ArrayList<ChatRoomInfoBean>();
		ChatRoomInfoBean roomInfo = new ChatRoomInfoBean(001, "ルリルリ1", "機動戦艦ナデシコB", "バカばっか。あたしも結構バカよねー", "12:57");
		chatRoomInfoList.add(roomInfo);
		roomInfo = new ChatRoomInfoBean(002, "アスカ", "特務機関NERV", "あんたバカァ?", "10:01");
		chatRoomInfoList.add(roomInfo);
		return chatRoomInfoList;
	}
}
