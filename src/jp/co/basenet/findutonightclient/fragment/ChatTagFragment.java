package jp.co.basenet.findutonightclient.fragment;

import java.util.ArrayList;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.activity.ChatActivity;
import jp.co.basenet.findutonightclient.adapter.ChatRoomListAdapter;
import jp.co.basenet.findutonightclient.common.Const;
import jp.co.basenet.findutonightclient.model.ChatRoomInfoBean;
import jp.co.basenet.findutonightclient.service.DBService;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
	private ArrayList<ChatRoomInfoBean> chatRoomList;
	//�u���[�h�L���X�g�ʐM�p
	private BroadcastReceiver rec = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        //�u���[�h�L���X�g�ʐM�ݒ�
        this.rec = new BroadcastReceiver() {
			@SuppressWarnings("unchecked")
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				chatRoomList = ((ArrayList<ChatRoomInfoBean>)intent.getSerializableExtra("RESULT"));
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
						
						//�A�j���[�V������ݒ�
						getActivity().overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
					}
				});
			}
        };
        
        //RES_CHAT_ROOM_INFO_LIST�݂̂�����
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.DB_ORDER.RES_CHAT_ROOM_INFO_LIST);
        getActivity().registerReceiver(this.rec, filter);
        
        //�O�̉�ʂ�������������KEY��SocketService�ɓn��
		Intent intent = new Intent(getActivity(), DBService.class);
		intent.setAction(Const.DB_ORDER.REQ_CHAT_ROOM_INFO_LIST);
		getActivity().startService(intent);
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//�g�p����Flagment���w��
        return inflater.inflate(R.layout.fragment_chat_tag, container, false);
    }
    
	@Override
	public void onStart() {
		super.onStart();
	}
}
