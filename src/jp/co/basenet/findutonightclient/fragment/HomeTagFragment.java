package jp.co.basenet.findutonightclient.fragment;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.Service.SocketService;
import jp.co.basenet.findutonightclient.activity.KeySelectActivity;
import jp.co.basenet.findutonightclient.activity.MainActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeTagFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_home_tag, container, false);
    }
	
	@Override
	public void onStart() {
		super.onStart();
		
		//Activityから当該Fragment上のButtonを取得
		Button btnKeySelect = (Button)getActivity().findViewById(R.id.btnKeySelect);
		
		//clicklistenerを設定(イベント監視)
		btnKeySelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//このボタンを押したらkeySelectActivityに移動する
				getActivity().startActivity(new Intent(getActivity(), KeySelectActivity.class));
				
				//アニメーションを設定
				getActivity().overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_to_left);
			}
		});
		
		//現在地周辺
		//テスト用ボタン
		//TODO
		Button btnMapSelect = (Button)getActivity().findViewById(R.id.btnMapSelect);
		btnMapSelect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), SocketService.class);
				intent.setAction("SEND");
				intent.putExtra("msg", "test message");
				getActivity().startService(intent);
			}
		});
	}
}
