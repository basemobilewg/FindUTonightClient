package jp.co.basenet.findutonightclient.fragment;

import jp.co.basenet.findutonightclient.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HistoryTagFragment extends Fragment {
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	//使用するFlagmentを指定
        return inflater.inflate(R.layout.fragment_history_tag, container, false);
    }
}
