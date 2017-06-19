package bean;

public class PendingMessages {
	
	private int messageId;
	private String recieverNumber;
	private String message;
	
	
	public PendingMessages() {
		
	}
	
	public PendingMessages(int messageId, String recieverNumber, String message) {
		this.messageId = messageId;
		this.recieverNumber = recieverNumber;
		this.message = message;
	}
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getRecieverNumber() {
		return recieverNumber;
	}
	public void setRecieverNumber(String recieverNumber) {
		this.recieverNumber = recieverNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
