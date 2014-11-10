package jp.co.basenet.findutonightclient.listener;

import jp.co.basenet.findutonightclient.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class TabListener<T extends Fragment> implements ActionBar.TabListener {
	
	private Fragment fragment;
	private final Activity activity;
	private final String tag;
	private final Class<T> clz;
	private final int tabNum;
	
	private static int preTabNum = 0;
	private static Fragment preFragment = null;

	
	public TabListener(Activity activity, String tag, Class<T> clz, int tabNum) {
		this.activity = activity;
		this.tag = tag;
		this.clz = clz;
		this.tabNum = tabNum;
		this.fragment = activity.getFragmentManager().findFragmentByTag(tag);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// fragmentを作成してない場合作成、すでに存在してる場合状態hide→show
		int object_in_from;
		int object_out_to;
		
		if(preTabNum < tabNum) {
			//当該タブの右タブを押した場合
			object_in_from = R.animator.object_in_from_right;
			object_out_to = R.animator.object_out_to_left;
		} else {
			//当該タブの左タブを押した場合
			object_in_from = R.animator.object_in_from_left;
			object_out_to = R.animator.object_out_to_right;
		}

		FragmentManager fm = activity.getFragmentManager();
		
		if(preFragment != null) {
			fm.beginTransaction().setCustomAnimations(object_in_from, object_out_to).hide(preFragment).commit();
		}
		
		if(fragment == null) {
			fragment = Fragment.instantiate(activity, clz.getName());
			if(tabNum == 0) {
				fm.beginTransaction().add(R.id.frame_home, fragment, tag).commit();
			} else {
				fm.beginTransaction().setCustomAnimations(object_in_from, object_out_to).add(R.id.frame_home, fragment, tag).commit();
			}
		} else {
			if(fragment.isHidden()) {
				fm.beginTransaction().setCustomAnimations(object_in_from, object_out_to).show(fragment).commit();
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//fragment状態をshow→hide
		if(fragment != null) {
			//FragmentManager fm = activity.getFragmentManager();
			//fm.beginTransaction().setCustomAnimations(R.animator.object_in_from_right, R.animator.object_out_to_left).hide(fragment).commit();
			preFragment = fragment;
			preTabNum = tabNum;
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// 何もしなくてもいい	
	}

}
