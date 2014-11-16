package jp.co.basenet.findutonightclient.fragment;

import common.Const;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.service.SocketService;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class KeySelectResultFragment extends Fragment {
	//ブロードキャスト通信用
	private BroadcastReceiver rec = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//trueを設定した場合、FragmentでもActionBarの内容が変更できる
		setHasOptionsMenu(true);
		
		//ActivityからActionBarを取得
        final ActionBar actionBar = getActivity().getActionBar();
        //ActionBarのタイトルを変更
        actionBar.setTitle("検索結果");
        
        //ブロードキャスト通信設定
        this.rec = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				TextView txtResult = (TextView)getActivity().findViewById(R.id.txtResult);
				txtResult.setText(intent.getStringExtra("BODY"));
			}
        };
        
        //KEY_SELECT_RECのみを処理
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.ORDER.KEY_SELECT_REC);
        getActivity().registerReceiver(this.rec, filter);
        
        //前の画面からもらった検索KEYをSocketServiceに渡す
		Intent intent = new Intent(getActivity(), SocketService.class);
		intent.setAction(Const.ORDER.KEY_SELECT_SED);
		intent.putExtra("BODY", getArguments().getString("KEY"));
		getActivity().startService(intent);
	} 
	
	@Override
	public void onStart() {
		super.onStart();
		//検索条件をSocketServiceへ送信
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_key_select_result, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//戻るボタンを押したら前のFragmentをスタックから取得して戻る
        	//ここアニメーション設定が不要、なぜならaddToBackStackをした場合すでに設定済み
        	FragmentManager fm = getActivity().getFragmentManager();
        	fm.popBackStack();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//Destroy前に登録したBroadcastReceiverを解除
		getActivity().unregisterReceiver(this.rec);
	}
}
