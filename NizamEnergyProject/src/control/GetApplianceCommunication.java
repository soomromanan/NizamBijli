package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageAPI.MoblinkMessageService;

import org.apache.log4j.Logger;

import bal.ApplianceAlarmsBal;
import bal.ApplianceStatusBal;
import bean.ApplianceStatusBean;

@WebServlet("/GetApplianceCommunication")
public class GetApplianceCommunication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(GetApplianceCommunication.class);

	public GetApplianceCommunication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String batteryVoltage = request.getParameter("bv");
		String batteryCurrent = request.getParameter("ba");
		String solarVoltage = request.getParameter("sv");
		String solarAmpere = request.getParameter("sa");
		String loadVoltage = request.getParameter("lv");
		String loadCurrent = request.getParameter("la");
		String temperature = request.getParameter("tp");
		String signalStrength = request.getParameter("db");
		String latitude = request.getParameter("lat");
		String longitude = request.getParameter("lon");
		String imeiNumber = request.getParameter("IMEI");
		String alarmBits = request.getParameter("st");

		ApplianceStatusBean statusBean = new ApplianceStatusBean();
		statusBean.setBa(Double.parseDouble(batteryCurrent));
		statusBean.setBv(Double.parseDouble(batteryVoltage));
		statusBean.setLa(Double.parseDouble(loadCurrent));
		statusBean.setLv(Double.parseDouble(loadVoltage));
		statusBean.setSa(Double.parseDouble(solarAmpere));
		statusBean.setSv(Double.parseDouble(solarVoltage));
		statusBean.setTemperature(Double.parseDouble(temperature));
		statusBean.setSignalStrength(Integer.parseInt(signalStrength));
		statusBean.setIMEI(imeiNumber);
		statusBean.setLatitude(Double.parseDouble(latitude));
		statusBean.setLongitude(Double.parseDouble(longitude));
		int id = 0;
		try {
			id = ApplianceStatusBal.insertStatus(statusBean);
		} catch (Exception e) {
			logger.error(e);
		}
		int alarm = 0;
		if (alarmBits != null) {
			alarm = Integer.parseInt(alarmBits);
			alarmBits = MoblinkMessageService.binaryFill(alarm);
		}
		int idd = 0;
		HashMap<String, String> mapAlarms = new HashMap<>();
		char nums[] = alarmBits.toCharArray();

		mapAlarms.put("betteryLevel", nums[8] + "");
		mapAlarms.put("solarPower", nums[7] + ""); // Over Current or Voltage
		mapAlarms.put("currentLoad", nums[2] + "");
		mapAlarms.put("temperature", nums[1] + "");
		mapAlarms.put("lidOpen", nums[4] + "");
		mapAlarms.put("signalStrength", nums[3] + "");
		mapAlarms.put("commonFault", nums[5] + "");
		mapAlarms.put("gsm_suicide", nums[0] + "");
		mapAlarms.put("battery_overcharge", nums[6] + "");

		mapAlarms.put("latitude", 0 + "");
		mapAlarms.put("longitude", 0 + "");
		mapAlarms.put("imeiNumber", imeiNumber);
		try {
			idd = ApplianceAlarmsBal.insertAlarms(mapAlarms);
		} catch (Exception e) {
			logger.error(e);
		}
		out.println(id + " " + idd + " " + alarmBits);
	}

}
