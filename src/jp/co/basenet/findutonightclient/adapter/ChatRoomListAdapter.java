package jp.co.basenet.findutonightclient.adapter;

import java.util.ArrayList;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.activity.MainActivity;
import jp.co.basenet.findutonightclient.model.ChatRoomInfoBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ChatRoomListAdapter extends BaseAdapter {
	private ArrayList<ChatRoomInfoBean> chatRoomList;
	private Context context;
	
	public ChatRoomListAdapter(Context context, ArrayList<ChatRoomInfoBean> chatRoomList) {
		this.chatRoomList = chatRoomList;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chatRoomList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return chatRoomList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater li = LayoutInflater.from(context);  
		View view = li.inflate(R.layout.linerlayout_chatroomlist, null);
		((TextView)view.findViewById(R.id.txtRoomId)).setText(String.valueOf(chatRoomList.get(position).getRoomId()));
		((TextView)view.findViewById(R.id.txtName)).setText(
				chatRoomList.get(position).getPartenerName() + " (" + chatRoomList.get(position).getShopName() +")");
		((TextView)view.findViewById(R.id.txtlastMessage)).setText(chatRoomList.get(position).getLastChatMessage());
		((TextView)view.findViewById(R.id.txtDate)).setText(chatRoomList.get(position).getLastChatDate());

		return view;
	}

}
