package schedule;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;

public class MessageRecieverThread implements Runnable {

	private final static Logger logger = Logger.getLogger(MessageRecieverThread.class);
	
	@Override
	public void run() {
		try {
			MoblinkMessageService.RecieveSMSList();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
