package jp.co.basenet.findutonightclient.fragment;

import java.util.ArrayList;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.activity.ChatActivity;
import jp.co.basenet.findutonightclient.activity.KeySelectActivity;
import jp.co.basenet.findutonightclient.adapter.ChatRoomListAdapter;
import jp.co.basenet.findutonightclient.model.ChatRoomInfo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony.TextBasedSmsColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChatTagFragment extends Fragment {
	
	private ListView listView;
	private BaseAdapter baseAdapter;
	private ArrayList<ChatRoomInfo> chatRoomList;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_chat_tag, container, false);
    }
    
	@Override
	public void onStart() {
		super.onStart();
		
		chatRoomList = new ArrayList<ChatRoomInfo>();
		ChatRoomInfo roomInfo = new ChatRoomInfo(001, "ルリルリ", "機動戦艦ナデシコ", "バカばっか。あたしも結構バカよねー", "12:57");
		chatRoomList.add(roomInfo);
		
		roomInfo = new ChatRoomInfo(002, "アスカ", "特務機関NERV", "あんたバカァ?", "10:01");
		chatRoomList.add(roomInfo);
		//ArrayList<E>
		
		listView = (ListView)getActivity().findViewById(R.id.chatroomlist);
		baseAdapter = new ChatRoomListAdapter(getActivity(), chatRoomList);
		listView.setAdapter(baseAdapter);
		listView.setSelection(listView.getCount() - 1);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), ChatActivity.class);
				intent.putExtra("ROOMID", ((TextView)view.findViewById(R.id.txtRoomId)).getText());
				intent.putExtra("NAME",  ((TextView)view.findViewById(R.id.txtName)).getText());
				getActivity().startActivity(intent);
				
				//アニメーションを設定
				getActivity().overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
			}
		});
	}
}
