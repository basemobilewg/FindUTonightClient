package jp.co.basenet.findutonightclient.fragment;

import jp.co.basenet.findutonightclient.R;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ChatRoomFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//trueを設定した場合、FragmentでもActionBarの内容が変更できる
		setHasOptionsMenu(true);

		//ActivityからActionBarを取得
        final ActionBar actionBar = getActivity().getActionBar();
        
        //ActionBarのタイトルを変更
        actionBar.setTitle(getActivity().getIntent().getStringExtra("NAME"));
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//戻るボタンを押したらMainActivityに戻る
        	getActivity().finish();
        	//アニメーションを設定
        	getActivity().overridePendingTransition(R.anim.activity_in_from_left, R.anim.activity_out_to_right);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
