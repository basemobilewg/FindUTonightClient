package jp.co.basenet.findutonightclient.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import jp.co.basenet.findutonightclient.common.Const;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public class SocketService extends Service {
	
	private static final String ADDRESS = "10.0.2.2"; 
	private static final int PORT = 50001; 
	
	private String msg = null;
	
	//�N���C�A���g�\�P�b�g�`�����l��
	private SocketChannel sc;
	
	//�T�[�o��M�p�X���b�h
	private ThreadReceive tr;
	//�T�[�o���M�p�X���b�h
	private ThreadSend ts;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//�T�[�o�ɐڑ�
		//connectServer();
		
		//TR,TS�X���b�h�J�n������
		tr = new ThreadReceive();
		tr.start();
		ts = new ThreadSend();
		ts.start();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		if(intent.getAction() != null) {
			Message msg = new Message();
			Bundle b = new Bundle();
			b.putInt("ORDER", Integer.parseInt(intent.getAction()));
			b.putString("BODY", intent.getStringExtra("BODY"));
			msg.setData(b);
			ts.tsHandler.sendMessage(msg);
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public void connectServer() {
		try {
			sc = SocketChannel.open();
			sc.configureBlocking(false);
			sc.connect(new InetSocketAddress(ADDRESS, PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void forwardMessage(int order, int size, byte[] body) throws Exception {
		Intent it;
		switch(String.valueOf(order)) {
			case Const.ORDER.KEY_SELECT_REC:
				it = new Intent(Const.ORDER.KEY_SELECT_REC);
				it.putExtra("BODY", new String(body, "UTF-8"));
				super.sendBroadcast(it);
				break;
			default:
				break;
		}
	}
	
	//�T�[�o��M�p�N���X
	//TODO�����I��������
	private class ThreadReceive extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			connectServer();
			
			try {
				//true-size���擾�@false-size�擾�ς݁A�ق��̕�����ǂ�ł�
				boolean readStatus = true;
				int size = 0;
				int order = 0;
				byte[] body = null;
				//while(!sc.finishConnect()) {
					//�Ƃ肠����10240�ɂ���A���Ԃ񑫂�邩��
				ByteBuffer buf = ByteBuffer.allocate(1024*10);
				
				//�ڑ������܂őҋ@
				while(!sc.finishConnect()){}
				
				while(sc.read(buf) != -1) {
					if(buf.position() < 8 && readStatus ) {
						//size���擾����ɂ͏��Ȃ��Ƃ�4�o�C�g���K�v
						continue;
					}
					
					if(readStatus) {
						readStatus = false;
						buf.flip();
						order = buf.getInt();
						size = buf.getInt();
						buf.compact();
					}
					
					if(buf.position() < size) {
						//�y�b�P�[�W�܂��s���S
						continue;
					}
					
					buf.flip();
					body = new byte[size];
					buf.get(body);
					
					//�ЂƂ̃��R�[�h�擾�ς݁A���̃��R�[�h��
					buf.compact();
					readStatus = true;
					
					//UI�ɓ]��
					forwardMessage(order, size, body);
				}
				//}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//�T�[�o���M�p�N���X
	//TODO�����I��������
	private class ThreadSend extends Thread {

		public Handler tsHandler;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			
			tsHandler = new Handler(){
				
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
				    sendMsg(msg);
				}
			};
			Looper.loop();
		}
		
		private void sendMsg(Message msg) {
			try {
				int order = msg.getData().getInt("ORDER");
				byte bbody[] = String.valueOf(msg.getData().getString("BODY")).getBytes("UTF-8");
				int size = bbody.length;
				
				ByteBuffer buffer = ByteBuffer.allocate(8 + size);
				buffer.putInt(order);
				buffer.putInt(size);
				buffer.put(bbody);
				buffer.flip();
				
				sc.write(buffer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
