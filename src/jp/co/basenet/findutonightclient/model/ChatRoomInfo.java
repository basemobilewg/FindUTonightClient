package jp.co.basenet.findutonightclient.model;

public class ChatRoomInfo {
	int roomId;
	String partenerName;
	String shopName;
	String lastChatMessage;
	String lastChatDate;
	
	public ChatRoomInfo(int roomId, String partenerName, String shopName, String lastChatMessage, String lastChatDate) {
		this.roomId = roomId;
		this.partenerName = partenerName;
		this.shopName = shopName;
		this.lastChatMessage = lastChatMessage;
		this.lastChatDate = lastChatDate;
	}
	
	public int getRoomId() {
		return roomId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public String getPartenerName() {
		return partenerName;
	}
	
	public void setPartenerName(String partenerName) {
		this.partenerName = partenerName;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getLastChatMessage() {
		return lastChatMessage;
	}
	
	public void setLastChatMessage(String lastChatMessage) {
		this.lastChatMessage = lastChatMessage;
	}
	
	public String getLastChatDate() {
		return lastChatDate;
	}
	
	public void setLastChatDate(String lastChatDate) {
		this.lastChatDate = lastChatDate;
	}
	
	
}
