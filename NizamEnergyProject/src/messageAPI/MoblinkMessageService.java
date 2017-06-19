package messageAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.XML;

import connection.Connect;
import bal.ApplianceAlarmsBal;
import bal.ApplianceBal;
import bal.ApplianceStatusBal;
import bean.ApplianceStatusBean;
import bean.PendingMessages;

public class MoblinkMessageService {
	final static Logger logger = Logger.getLogger(MoblinkMessageService.class);

	public static boolean mobilink_status = false;

	public static int SendMessage(String phone, String message)
			throws IOException, JSONException, SQLException {

		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
		String url = "http://119.160.92.2:7700/sendsms_xml.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		StringBuffer sb = new StringBuffer();
		sb.append("<SMSRequest>");
		sb.append("<Username>03028501626</Username>");
		sb.append("<Password>Pakistan123</Password> ");
		sb.append("<From>7005148</From> ");
		sb.append("<To>" + phone + "</To> ");
		sb.append("<Message>" + message + "</Message> ");
		sb.append("</SMSRequest>");
		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("xmldoc", sb.toString()));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(60000).setConnectTimeout(90000)
				.setConnectionRequestTimeout(120000).build();
		post.setConfig(requestConfig);
		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		String soapmessageString = result.toString();
		org.json.JSONObject soapDatainJsonObject = XML
				.toJSONObject(soapmessageString);
		if (soapDatainJsonObject.getJSONObject("response").has("messageid")) {
			insertTotalMessage("Mobilink", phone, message);
			return 0;
		} else {
			PendingMessages pm = new PendingMessages();
			pm.setRecieverNumber(phone);
			pm.setMessage(message);
			System.out.println("no sms");
			return 1;
		}

	}

	public static boolean get_mobilink_status() {
		return mobilink_status;
	}

	public static String RecieveSMSList() throws IOException, JSONException,
			ParseException, SQLException {
		String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
		String url = "http://119.160.92.2:7700/receivesms_xml.html";

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = (Date) Calendar.getInstance().getTime();

		StringBuffer sb = new StringBuffer();
		sb.append("<SMSRequest>");
		sb.append("<Username>03028501626</Username>");
		sb.append("<Password>Pakistan123</Password> ");
		sb.append("<Shortcode>7005148</Shortcode> ");
		sb.append("</SMSRequest>");

		post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("xmldoc", sb.toString()));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		StringBuffer sb1 = new StringBuffer(
				"<SMSRsponse><Error>No Record Found.</Error></SMSRsponse>");
		mobilink_status = false;
		if (sb1.equals(result)) {
			return "No records foundasass";
		}
		String soapmessageString = result.toString();
		org.json.JSONObject soapDatainJsonObject = XML
				.toJSONObject(soapmessageString);

		org.json.JSONObject newJSON = soapDatainJsonObject
				.getJSONObject("SMSRsponse");
		if (!newJSON.has("SMSInfo")) {
			mobilink_status = false;
			return "No Record found";
		} else {
			Object item = newJSON.get("SMSInfo");
			boolean json = item.toString().startsWith("{");
			if (json) {
				mobilink_status = true;
				org.json.JSONObject obj = newJSON.getJSONObject("SMSInfo");
				String smsFrom = obj.getString("smsFrom").substring(1);
				double smsTo = obj.getDouble("smsTo");
				String message = obj.getString("smsMessage");
				String date = obj.getString("smsDate");
				String msgto = new Double(smsTo).toString();
				System.out.println(message);
				int appliance_id = ApplianceBal.get_appliance_id(smsFrom);
				String arr[] = message.split(" ", 2);
				String firstWord = "";
				int Option_Number = 0;
				if (message.startsWith("5") || message.startsWith("4")) {
					firstWord = arr[0];
					Option_Number = Integer.parseInt(firstWord);
				}
				if (Option_Number == 5) {
					logger.info(message);
					ApplianceBal.update_appliance_on_off(true, appliance_id);
				}
				if (Option_Number == 4) {
					logger.info(message);
					ApplianceBal.update_appliance_on_off(false, appliance_id);
				}
				if (message.startsWith("IMEI")) {
					logger.info(message);
					message = message.replace("+", "");
					if (message.contains(" ")) {
						updateApplianceStatus(message);
					}
				}
			} else {
				org.json.JSONArray ARRAY = newJSON.getJSONArray("SMSInfo");
				if (ARRAY.length() < 1) {
					return "NO";
				}
				for (int i = 0; i < ARRAY.length(); i++) {
					mobilink_status = true;
					org.json.JSONObject obj = ARRAY.getJSONObject(i);
					String smsFrom = obj.getString("smsFrom").substring(1);
					double smsTo = obj.getDouble("smsTo");
					String message = obj.getString("smsMessage");
					String date = obj.getString("smsDate");
					String msgto = new Double(smsTo).toString();
					int appliance_id = ApplianceBal.get_appliance_id(smsFrom);
					String arr[] = message.split(" ", 2);
					String firstWord = "";
					int Option_Number = 0;
					if (message.startsWith("5") || message.startsWith("4")) {
						firstWord = arr[0];
						Option_Number = Integer.parseInt(firstWord);
					}
					if (Option_Number == 5) {
						logger.info(message);
						ApplianceBal
								.update_appliance_on_off(true, appliance_id);
					}
					if (Option_Number == 4) {
						logger.info(message);
						ApplianceBal.update_appliance_on_off(false,
								appliance_id);
					}
					if (message.startsWith("IMEI")) {
						logger.info(message);
						message = message.replace("+", "");
						if (message.contains(" ")) {
							updateApplianceStatus(message);
						}
					}
				}
			}
		}
		return result.toString();
	}

	private static void updateApplianceStatus(String message) {
		Map<String, String> map = null;
		try {
			map = getQueryMap(message);
		} catch (Exception e) {
			logger.error(e);
		}
		ApplianceStatusBean statusBean = new ApplianceStatusBean();
		statusBean.setBa(Double.parseDouble(map.get("ba")));
		statusBean.setBv(Double.parseDouble(map.get("bv")));
		statusBean.setLa(Double.parseDouble(map.get("la")));
		statusBean.setLv(Double.parseDouble(map.get("lv")));
		statusBean.setSa(Double.parseDouble(map.get("sa")));
		statusBean.setSv(Double.parseDouble(map.get("sv")));
		statusBean.setTemperature(Double.parseDouble(map.get("tp")));
		statusBean.setSignalStrength(Integer.parseInt(map.get("db")));
		statusBean.setIMEI(map.get("IMEI"));
		String lat = map.get("lat").equals("") ? "0" : map.get("lat");
		String log = map.get("lon").equals("") ? "0" : map.get("lon");
		statusBean.setLatitude(Double.parseDouble(lat));
		statusBean.setLongitude(Double.parseDouble(log));
		String alarmBits = map.get("st");
		int id = 0;
		try {
			id = ApplianceStatusBal.insertStatus(statusBean);
		} catch (Exception e) {
			logger.error(e);
		}

		int idd = 0;
		HashMap<String, String> mapAlarms = new HashMap<>();
		int alarm = 0;
		if (alarmBits != null) {
			alarm = Integer.parseInt(alarmBits);
			alarmBits = binaryFill(alarm);
		}
		char nums[] = alarmBits.toCharArray();

		mapAlarms.put("betteryLevel", nums[8] + "");
		mapAlarms.put("solarPower", nums[7] + "");
		mapAlarms.put("currentLoad", nums[2] + "");
		mapAlarms.put("temperature", nums[1] + "");
		mapAlarms.put("lidOpen", nums[4] + "");
		mapAlarms.put("signalStrength", nums[3] + "");
		mapAlarms.put("commonFault", nums[5] + "");
		mapAlarms.put("gsm_suicide", nums[0] + "");
		mapAlarms.put("battery_overcharge", nums[6] + "");

		mapAlarms.put("latitude", 0 + "");
		mapAlarms.put("longitude", 0 + "");
		mapAlarms.put("imeiNumber", map.get("IMEI"));
		try {
			idd = ApplianceAlarmsBal.insertAlarms(mapAlarms);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static Map<String, String> getQueryMap(String query) {
		String[] params = query.split(" ");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = "";
			if (param.equals("lon=") || param.equals("lat=")) {
				value = "0";
			} else {
				value = param.split("=")[1];
			}
			map.put(name, value);
		}
		return map;
	}

	public static String binaryFill(int number) {
		String numberAsString = Integer.toBinaryString(number);
		StringBuilder sb = new StringBuilder();
		while (sb.length() + numberAsString.length() < 9) {
			sb.append('0');
		}
		sb.append(numberAsString);
		String paddedNumberAsString = sb.toString();
		return paddedNumberAsString.toString();
	}

	public static int insertTotalMessage(String send_from, String send_to,
			String message) throws SQLException {
		int rows = 0;
		try (Connection con = Connect.getConnection();) {
			CallableStatement prepareCall = con
					.prepareCall("Call insert_messages(?,?,?)");
			prepareCall.setString(1, send_from);
			prepareCall.setString(2, send_to);
			prepareCall.setString(3, message);
			prepareCall.executeQuery();
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		return rows;
	}
}
