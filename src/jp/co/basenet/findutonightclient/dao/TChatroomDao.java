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
		ChatRoomInfoBean roomInfo = new ChatRoomInfoBean(001, "��������1", "�@����̓i�f�V�RB", "�o�J�΂����B�����������\�o�J��ˁ[", "12:57");
		chatRoomInfoList.add(roomInfo);
		roomInfo = new ChatRoomInfoBean(002, "�A�X�J", "�����@��NERV", "���񂽃o�J�@?", "10:01");
		chatRoomInfoList.add(roomInfo);
		return chatRoomInfoList;
	}
}
