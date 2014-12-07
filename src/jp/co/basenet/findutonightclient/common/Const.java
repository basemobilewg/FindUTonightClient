package jp.co.basenet.findutonightclient.common;

public final class Const {
	
	private Const() {};
	
	//SOCKET通信用ORDER
	public final class SOCKET_ORDER {
		private SOCKET_ORDER() {};
		public static final String KEY_SELECT_SED = "1001";
		public static final String KEY_SELECT_REC = "9001";
	}
	
	//DB通信用ORDER
	public final class DB_ORDER {
		private DB_ORDER() {};
		public static final String REQ_CHAT_ROOM_INFO_LIST = "1";
		public static final String RES_CHAT_ROOM_INFO_LIST = "2";
	}	


}
