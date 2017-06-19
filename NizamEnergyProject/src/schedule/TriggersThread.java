package schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;

import bal.TriggersBal;

public class TriggersThread extends Thread {

	private final static Logger logger = Logger.getLogger(TriggersThread.class);

	public void run() {
		try {
			while (true) {
				DateFormat df = new SimpleDateFormat("HH:mm");
				Date dateobj = new Date();
				if (df.format(dateobj).equals("01:01")) {
					logger.info("isLive");
					TriggersBal.isLive();
				} else if (df.format(dateobj).equals("02:01")) {
					logger.info("checkNotIntrustedCustomerToAcceptedCust");
					checkNotIntrustedCustomerToVarCust();
					checkNotIntrustedCustomerToAcceptedCust();
				} else if (df.format(dateobj).equals("07:01")) {
					logger.info("switchOffDevices");
					switchOffDevices();
				} else if (df.format(dateobj).equals("07:30")) {
					logger.info("sendWellWishes");
					sendMessageNotices();
					sendWellWishes();
				}
				Thread.sleep(60000);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static void checkNotIntrustedCustomerToVarCust() {
		ArrayList<HashMap<String, String>> list = TriggersBal
				.getVerifiedCustomers();
		for (HashMap<String, String> hashMap : list) {
			int remaining = Integer.parseInt(hashMap.get("remaining"));
			if (remaining > 30) {
				int appid = Integer.parseInt(hashMap.get("appliance_id"));
				TriggersBal.updateVarifiedCustomersToNoIntersted(appid);
			}
		}
	}

	public static void checkNotIntrustedCustomerToAcceptedCust() {
		ArrayList<HashMap<String, String>> list = TriggersBal
				.getAcceptedCustomers();
		for (HashMap<String, String> hashMap : list) {
			int remaining = Integer.parseInt(hashMap.get("remaining"));
			if (remaining > 30) {
				int appid = Integer.parseInt(hashMap.get("appliance_id"));
				TriggersBal.updateAcceptedCustomersToNoIntersted(appid);
			}
		}
	}

	public static void switchOffDevices() {
		ArrayList<HashMap<String, String>> list = TriggersBal.getOffMessages();
		for (int i = 0; i < list.size(); i++) {
			try {
				MoblinkMessageService.SendMessage(list.get(i).get("gsmNumber"),
						"$4$");
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	public static void sendMessageNotices() {
		ArrayList<HashMap<String, String>> list = null;
		try {
			list = TriggersBal.sendDueDateReminders();
			for (int i = 0; i < list.size(); i++) {
				int days = Integer.parseInt(list.get(i).get("days"));
				if (days == 10) {
					String str = "Moaziz Saarif: Nizam Bijli istimaal karnay k lia shukriya, aap apna bill eaypaisa ya mobicash ke zarya "
							+ "jama karwa sak tae hain, mazeed maloomat kae liye 03111741741 sae rabta kar sak te hain.";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}

				} else if (days == 8) {
					String str = "Moaziz Saarif: aap ka solar system ka bill 2 din mae due ho jai ga, "
							+ "mazeed maloomat kae liye Nizam Bijli helpline par rabta kar lain 03111741741.";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}

				} else if (days == 6) {
					String str = "Moaziz Saarif: aap kae solar system ka bill due ho gai hai. "
							+ "Rs. "
							+ list.get(i).get("monthlyInstallment")
							+ "  es consumer number "
							+ list.get(i).get("imei_number")
							+ " par jama kar wa din. "
							+ "Mazeed maloomat kae liye 03111741741 par rabta karin. Nizam Bijli istimaal karnay k lia shukriya";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}

				} else if (days >= 1 && days <= 3) {
					String str = "Moaziz Saarif: Aap kae solar system kae bill ki adaaigi ki akhri tareekh guzar chuki hai. Brahay meharbaani "
							+ "Rs. "
							+ list.get(i).get("monthlyInstallment")
							+ " ki foran adaaigi jald sa jald kar dein. Aap ka consumer number "
							+ "hai "
							+ list.get(i).get("imei_number")
							+ ". Nizam Bijli istimaal karnay k lia shukriya. 03111741741";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}

				} else if (days <= 0 && days >= -5) {
					String str = "Moaziz Saarif: apki is mahaane ki adaaigi abhi tak rehti hai. Brahay meharbani jald sa "
							+ "jald apni adaaigi jamma kar dein. Adaaigi na honay ki sourat main Nizam Bijli apna system wapis lay le ge. "
							+ "Aap ka consumer number hai "
							+ list.get(i).get("imei_number")
							+ ", brahay meharbani is pe apni adaaigi kar dijiyay. "
							+ "Nizam Bijli istimaal karnay k lia shukriya. 03111741741";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}

				} else if (days == -6) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				} else if (days == -8) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				} else if (days == -10) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				} else if (days == -12) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				} else if (days == -15) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				} else if (days == -20) {
					String str = "Moziz Saarif: stamp paper ke tehat, solar system Nizam Bijli ki amanat hai, kist fauran jama kar wa din, "
							+ "na kar nae ki surat ma system hamare nimaendey aakar vapis lenay kay majaz hon gey. Or kisi be qism ki rakam "
							+ "wapis nahn ki jaegi, mazeed maloomat kae liye 03111741742";
					try {
						MoblinkMessageService.SendMessage(
								list.get(i).get("customerPhone"), str);
					} catch (Exception e) {
						logger.error(e);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void sendWellWishes() {
		ArrayList<HashMap<String, String>> list = TriggersBal
				.sendDueDateReminders();
		for (int i = 0; i < list.size(); i++) {
			int days = Integer.parseInt(list.get(i).get("downpayment_days"));
			if (days == 21) {
				String str = "Hope you are happy with Nizam Bijli, if you are having any problems "
						+ "please do not hestiate to call our call centre 03-111-741-741 we will make "
						+ "sure to resolve any problem you are facing as we aim to give you an amazing customer experience";
				try {
					MoblinkMessageService.SendMessage(
							list.get(i).get("customerPhone"), str);
				} catch (Exception e) {
					logger.error(e);
				}
			} else if (days == 101) {
				String str = "Hope you are happy with Nizam Bijli, if you are having any problems "
						+ "please do not hestiate to call our call centre 03-111-741-741 we will make "
						+ "sure to resolve any problem you are facing as we aim to give you an amazing customer experience";
				try {
					MoblinkMessageService.SendMessage(
							list.get(i).get("customerPhone"), str);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
	}

}
