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
		
		//true��ݒ肵���ꍇ�AFragment�ł�ActionBar�̓��e���ύX�ł���
		setHasOptionsMenu(true);

		//Activity����ActionBar���擾
        final ActionBar actionBar = getActivity().getActionBar();
        
        //ActionBar�̃^�C�g����ύX
        actionBar.setTitle(getActivity().getIntent().getStringExtra("NAME"));
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//�g�p����Flagment���w��
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//�߂�{�^������������MainActivity�ɖ߂�
        	getActivity().finish();
        	//�A�j���[�V������ݒ�
        	getActivity().overridePendingTransition(R.anim.activity_in_from_left, R.anim.activity_out_to_right);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
