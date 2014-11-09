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
		//true��ݒ肵���ꍇ�AFragment�ł�ActionBar�̓��e���ύX�ł���
		setHasOptionsMenu(true);
		
		//Activity����ActionBar���擾
        final ActionBar actionBar = getActivity().getActionBar();
        
        //ActionBar�̃^�C�g����ύX
        actionBar.setTitle("��������");
		
        //Activity���瓖�YFragment���Button���擾
		Button btnSelect = (Button)getActivity().findViewById(R.id.btnSelect);
		//clicklistener��ݒ�(�C�x���g�Ď�)
		btnSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Activity����FragmentManager���擾
				FragmentManager fm = getActivity().getFragmentManager();
				
				//KeySelectResltFragment�̃C���X�^���X�𐶐�
				Fragment keySelectResultFragment = Fragment.instantiate(getActivity(), KeySelectResultFragment.class.getName());
				
				//setCustomAnimations Fragment�؂�ւ��̎��̃A�j���[�V�����̐ݒ�
				//replace Fragment�؂�ւ�
				//addToBackStack �؂�ւ�����Fragment���X�^�b�N�ɓ����
				fm.beginTransaction().setCustomAnimations(
											R.animator.object_in_from_right, R.animator.object_out_to_left,
											R.animator.object_in_from_left, R.animator.object_out_to_right)
				                     .replace(R.id.frame_find, keySelectResultFragment).addToBackStack("select").commit();
			}
		});
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//�g�p����Flagment���w��
        return inflater.inflate(R.layout.fragment_key_select, container, false);
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	//�߂�{�^������������MainActivity�ɖ߂�
        	startActivity(new Intent(getActivity(), MainActivity.class));
        	
        	//�A�j���[�V������ݒ�
        	getActivity().overridePendingTransition(R.anim.activity_in_from_left, R.anim.activity_out_to_right);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
