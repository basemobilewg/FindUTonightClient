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
import android.widget.Button;

public class KeySelectFragment extends Fragment {
	
	@Override
	public void onStart() {
		super.onStart();
		//trueを設定した場合、FragmentでもActionBarの内容が変更できる
		setHasOptionsMenu(true);
		
		//ActivityからActionBarを取得
        final ActionBar actionBar = getActivity().getActionBar();
        
        //ActionBarのタイトルを変更
        actionBar.setTitle("条件検索");
		
        //Activityから当該Fragment上のButtonを取得
		Button btnSelect = (Button)getActivity().findViewById(R.id.btnSelect);
		//clicklistenerを設定(イベント監視)
		btnSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//ActivityからFragmentManagerを取得
				FragmentManager fm = getActivity().getFragmentManager();
				
				//KeySelectResltFragmentのインスタンスを生成
				Fragment keySelectResultFragment = Fragment.instantiate(getActivity(), KeySelectResultFragment.class.getName());
				
				//setCustomAnimations Fragment切り替えの時のアニメーションの設定
				//replace Fragment切り替え
				//addToBackStack 切り替え元のFragmentをスタックに入れる
				fm.beginTransaction().setCustomAnimations(
											R.animator.object_in_from_right, R.animator.object_out_to_left,
											R.animator.object_in_from_left, R.animator.object_out_to_right)
				                     .replace(R.id.frame_find, keySelectResultFragment).addToBackStack("select").commit();
			}
		});
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_key_select, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//戻るボタンを押したらMainActivityに戻る
        	startActivity(new Intent(getActivity(), MainActivity.class));
        	
        	//アニメーションを設定
        	getActivity().overridePendingTransition(R.anim.activity_in_from_left, R.anim.activity_out_to_right);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
