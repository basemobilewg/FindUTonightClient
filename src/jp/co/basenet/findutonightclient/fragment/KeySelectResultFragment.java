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
	//�u���[�h�L���X�g�ʐM�p
	private BroadcastReceiver rec = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//true��ݒ肵���ꍇ�AFragment�ł�ActionBar�̓��e���ύX�ł���
		setHasOptionsMenu(true);
		
		//Activity����ActionBar���擾
        final ActionBar actionBar = getActivity().getActionBar();
        //ActionBar�̃^�C�g����ύX
        actionBar.setTitle("��������");
        
        //�u���[�h�L���X�g�ʐM�ݒ�
        this.rec = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				TextView txtResult = (TextView)getActivity().findViewById(R.id.txtResult);
				txtResult.setText(intent.getStringExtra("BODY"));
			}
        };
        
        //KEY_SELECT_REC�݂̂�����
        IntentFilter filter = new IntentFilter();
        filter.addAction(Const.ORDER.KEY_SELECT_REC);
        getActivity().registerReceiver(this.rec, filter);
        
        //�O�̉�ʂ�������������KEY��SocketService�ɓn��
		Intent intent = new Intent(getActivity(), SocketService.class);
		intent.setAction(Const.ORDER.KEY_SELECT_SED);
		intent.putExtra("BODY", getArguments().getString("KEY"));
		getActivity().startService(intent);
	} 
	
	@Override
	public void onStart() {
		super.onStart();
		//����������SocketService�֑��M
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
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		//Destroy�O�ɓo�^����BroadcastReceiver������
		getActivity().unregisterReceiver(this.rec);
	}
}
