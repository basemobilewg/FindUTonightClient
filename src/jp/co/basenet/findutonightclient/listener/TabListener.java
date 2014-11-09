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
	
	public TabListener(Activity activity, String tag, Class<T> clz) {
		this.activity = activity;
		this.tag = tag;
		this.clz = clz;
		this.fragment = activity.getFragmentManager().findFragmentByTag(tag);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// fragmentを作成してない場合作成、すでに存在してる場合状態hide→show
		if(fragment == null) {
			fragment = Fragment.instantiate(activity, clz.getName());
			FragmentManager fm = activity.getFragmentManager();
			fm.beginTransaction().add(R.id.frame_home, fragment, tag).commit();
		} else {
			if(fragment.isHidden()) {
				FragmentManager fm = activity.getFragmentManager();
				fm.beginTransaction().show(fragment).commit();
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//fragment状態をshow→hide
		if(fragment != null) {
			FragmentManager fm = activity.getFragmentManager();
			fm.beginTransaction().hide(fragment).commit();
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// 何もしなくてもいい	
	}

}
