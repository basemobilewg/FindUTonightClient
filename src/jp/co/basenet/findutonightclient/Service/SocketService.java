package jp.co.basenet.findutonightclient.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SocketService extends Service {
	
	private static final String ADDRESS = "192.168.1.49"; 
	private static final int PORT = 50001; 
	
	int i = 0;
	
	private String msg = null;
	
	//�N���C�A���g�\�P�b�g�`�����l��
	private SocketChannel sc;
	
	//�T�[�o��M�p�X���b�h
	private Thread td;

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
		
		//listener�X���b�h�J�n������
		//td = new Thread(new ServerListener());
		//td.start();
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		i++;
		if("SEND".equals(intent.getAction())) {
			this.msg = intent.getStringExtra("msg");
			this.sendBroadcastToActivity();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public void sendBroadcastToActivity() {
		Intent it = new Intent("RECEIVE");
		it.putExtra("back", this.msg + "from service" + i);
		super.sendBroadcast(it);
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
	
	private class ServerListener implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//true-size���擾�@false-size�擾�ς݁A�ق��̕�����ǂ�ł�
				boolean readStatus = true;
				int size = 0;
				int order = 0;
				byte[] body = null;
				while(!sc.finishConnect()) {
					//�Ƃ肠����10240�ɂ���A���Ԃ񑫂�邩��
					ByteBuffer buf = ByteBuffer.allocate(1024*10);
					
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
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void forwardMessage(int order, int size, byte[] body) {
		
	}
}
