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
		//true��ݒ肵���ꍇ�AFragment�ł�ActionBar�̓��e���ύX�ł���
		setHasOptionsMenu(true);
		
		//Activity����ActionBar���擾
        final ActionBar actionBar = getActivity().getActionBar();
        //ActionBar�̃^�C�g����ύX
        actionBar.setTitle("��������");
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//�g�p����Flagment���w��
        return inflater.inflate(R.layout.fragment_key_select_result, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//�߂�{�^������������O��Fragment���X�^�b�N����擾���Ė߂�
        	//�����A�j���[�V�����ݒ肪�s�v�A�Ȃ��Ȃ�addToBackStack�������ꍇ���łɐݒ�ς�
        	FragmentManager fm = getActivity().getFragmentManager();
        	fm.popBackStack();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
