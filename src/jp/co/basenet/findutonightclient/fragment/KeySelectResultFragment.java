package jp.co.basenet.findutonightclient.fragment;

import jp.co.basenet.findutonightclient.R;
import jp.co.basenet.findutonightclient.activity.MainActivity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class KeySelectResultFragment extends Fragment {
	
	@Override
	public void onStart() {
		super.onStart();
		//trueを設定した場合、FragmentでもActionBarの内容が変更できる
		setHasOptionsMenu(true);
		
		//ActivityからActionBarを取得
        final ActionBar actionBar = getActivity().getActionBar();
        //ActionBarのタイトルを変更
        actionBar.setTitle("検索結果");
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
}
