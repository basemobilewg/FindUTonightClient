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
	
	//クライアントソケットチャンネル
	private SocketChannel sc;
	
	//サーバ受信用スレッド
	private Thread td;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//サーバに接続
		//connectServer();
		
		//listenerスレッド開始させる
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
				//true-size未取得　false-size取得済み、ほかの部分を読んでる
				boolean readStatus = true;
				int size = 0;
				int order = 0;
				byte[] body = null;
				while(!sc.finishConnect()) {
					//とりあえず10240にする、たぶん足りるかな
					ByteBuffer buf = ByteBuffer.allocate(1024*10);
					
					while(sc.read(buf) != -1) {
						if(buf.position() < 8 && readStatus ) {
							//sizeを取得するには少なくとも4バイトが必要
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
							//ペッケージまだ不完全
							continue;
						}
						
						buf.flip();
						body = new byte[size];
						buf.get(body);
						
						//ひとつのレコード取得済み、次のレコードへ
						buf.compact();
						readStatus = true;
						
						//UIに転送
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
