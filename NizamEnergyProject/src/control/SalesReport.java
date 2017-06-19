package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import bal.DataAndReportsBal;
import bean.UserBean;

/**
 * Servlet implementation class SalesReport
 */
@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SalesReport.class);

	public SalesReport() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : "
					+ ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String action = request.getParameter("click");
			if (action.equals("Generate Report")) {

				String from = request.getParameter("from");
				String to = request.getParameter("to");

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("customerList");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Installed Date");
				header.createCell(1).setCellValue("Customer Name");
				header.createCell(2).setCellValue("Customer Contact");
				header.createCell(3).setCellValue("District");
				header.createCell(4).setCellValue("FO Name");
				header.createCell(5).setCellValue("FO Contact");
				header.createCell(6).setCellValue("ND Name");
				header.createCell(7).setCellValue("ND Contact");
				header.createCell(8).setCellValue("Consumer Number");
				ArrayList<HashMap<String, String>> list = DataAndReportsBal
						.getCustomersList(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(
							list.get(i).get("insalled_date"));
					dataRow.createCell(1).setCellValue(
							list.get(i).get("customerName"));
					dataRow.createCell(2).setCellValue(
							list.get(i).get("customerContact"));
					dataRow.createCell(3).setCellValue(
							list.get(i).get("district"));
					dataRow.createCell(4).setCellValue(
							list.get(i).get("foName"));
					dataRow.createCell(5).setCellValue(
							list.get(i).get("foContact"));
					dataRow.createCell(6).setCellValue(
							list.get(i).get("NdName"));
					dataRow.createCell(7).setCellValue(
							list.get(i).get("NdContact"));
					dataRow.createCell(8).setCellValue(
							list.get(i).get("consumerNumber"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=customerList.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();

			}
			if (action.equals("Generate Customer Rating Report")) {

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("customerRatingList");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Customer Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("DO Name");
				header.createCell(3).setCellValue("FO Name");
				header.createCell(4).setCellValue("ND Name");
				header.createCell(5).setCellValue("Rating");
				ArrayList<HashMap<String, String>> list = DataAndReportsBal
						.getCustomerRatingList();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(
							list.get(i).get("customerName"));
					dataRow.createCell(1).setCellValue(
							list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(
							list.get(i).get("doName"));
					dataRow.createCell(3).setCellValue(
							list.get(i).get("foName"));
					dataRow.createCell(4).setCellValue(
							list.get(i).get("NdName"));
					dataRow.createCell(5).setCellValue(
							list.get(i).get("rating"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=customerRatingList.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();

			} else if (action.equals("Generate Sales Report")) {
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String emp_categ = request.getParameter("category");

				if (emp_categ.equals("DO")) {

					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("DoSales");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("DO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("Total Fos");
					header.createCell(3).setCellValue("Active Nds");
					header.createCell(4).setCellValue("Sales");
					header.createCell(5).setCellValue("Sales/FO");
					header.createCell(6).setCellValue("Days Since Last Sale");
					ArrayList<HashMap<String, String>> list = null;
					list = DataAndReportsBal.getDoSales(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("fos"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("active_nds"));
						dataRow.createCell(4).setCellValue(
								list.get(i).get("sales"));
						dataRow.createCell(5).setCellValue(
								list.get(i).get("average"));
						dataRow.createCell(6).setCellValue(
								list.get(i).get("last_sale"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=DoSales.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				} else if (emp_categ.equals("FO")) {

					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("FoSales");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("FO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("DO Name");
					header.createCell(3).setCellValue("Total Nds");
					header.createCell(4).setCellValue("Active Nds");
					header.createCell(5).setCellValue("Sales");
					header.createCell(6).setCellValue("Days Since Last Sale");

					ArrayList<HashMap<String, String>> list = DataAndReportsBal
							.getFoSales(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("foName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("total_nds"));
						dataRow.createCell(4).setCellValue(
								list.get(i).get("active_nds"));
						dataRow.createCell(5).setCellValue(
								list.get(i).get("sales"));
						dataRow.createCell(6).setCellValue(
								list.get(i).get("last_sale"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=FoSales.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				} else if (emp_categ.equals("ND")) {

					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook
							.createSheet("Calculate Simple Interest");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("Nd Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("DO Name");
					header.createCell(3).setCellValue("FO Name");
					header.createCell(4).setCellValue("Sales");
					header.createCell(5).setCellValue("Days Since Last Sale");

					ArrayList<HashMap<String, String>> list = DataAndReportsBal
							.getNDSales(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("ndName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("foName"));
						dataRow.createCell(4).setCellValue(
								list.get(i).get("sales"));
						dataRow.createCell(5).setCellValue(
								list.get(i).get("last_sale"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=NizamDostSales.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				}
			} else if (action.equals("Generate Recovery Report")) {

				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String emp_categ = request.getParameter("category");

				if (emp_categ.equals("DO")) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("DoRecovery");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("DO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("Recovery Rate");
					header.createCell(3).setCellValue("Credit Rating");
					ArrayList<HashMap<String, String>> list = null;
					list = DataAndReportsBal.getDoRecoveryRateReport(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("recovery"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("average_rating"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=DoRecovery.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				} else if (emp_categ.equals("FO")) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("FoRecovery");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("FO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("DO Name");
					header.createCell(3).setCellValue("Recovery Rate");
					header.createCell(4).setCellValue("Credit Rating");
					ArrayList<HashMap<String, String>> list = DataAndReportsBal
							.getFoRecoveryRateReport(from, to);

					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("foName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("recovery"));
						dataRow.createCell(4).setCellValue(
								list.get(i).get("average_rating"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=FoRecovery.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				} else if (emp_categ.equals("ND")) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook.createSheet("NDRecovery");
					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("Nd Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("DO Name");
					header.createCell(3).setCellValue("FO Name");
					header.createCell(4).setCellValue("Recovery Rate");
					header.createCell(5).setCellValue("Credit Rating");
					ArrayList<HashMap<String, String>> list = DataAndReportsBal
							.getNDRecoveryRateReport(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("salesman_name"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("foName"));
						dataRow.createCell(4).setCellValue(
								list.get(i).get("recovery"));
						dataRow.createCell(5).setCellValue(
								list.get(i).get("average_rating"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=NizamDostRecovery.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				}
			} else if (action.equals("Generate defaulter Report")) {
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String category = request.getParameter("category");
				if (category.equals("DO")) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook
							.createSheet("Sales and Defaulter");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("DO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("Sales");
					header.createCell(3).setCellValue("Defaulter");
					ArrayList<HashMap<String, String>> list = null;
					list = DataAndReportsBal.getDoSalesAndDefaulter(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("doName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("sales"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("defaulter"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=DoSalesAndDefaulter.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				} else if (category.equals("FO")) {
					HSSFWorkbook workbook = new HSSFWorkbook();
					HSSFSheet sheet = workbook
							.createSheet("Sales and Defaulter");

					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("FO Name");
					header.createCell(1).setCellValue("District");
					header.createCell(2).setCellValue("Sales");
					header.createCell(3).setCellValue("Defaulter");
					ArrayList<HashMap<String, String>> list = null;
					list = DataAndReportsBal.getFoSalesAndDefaulter(from, to);
					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						dataRow.createCell(0).setCellValue(
								list.get(i).get("foName"));
						dataRow.createCell(1).setCellValue(
								list.get(i).get("district"));
						dataRow.createCell(2).setCellValue(
								list.get(i).get("sales"));
						dataRow.createCell(3).setCellValue(
								list.get(i).get("defaulter"));
					}
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=FoSalesAndDefaulter.xls");
					workbook.write(response.getOutputStream());
					response.getOutputStream().flush();
				}
			} else if (action.equals("Generate FO Sales Report")) {

				String from = request.getParameter("from");
				String to = request.getParameter("to");

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("FO Sales");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Sales");
				header.createCell(3).setCellValue("Last Sale");
				ArrayList<HashMap<String, String>> list = null;
				list = DataAndReportsBal.getFoSalesfoDOs(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(
							list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(
							list.get(i).get("district"));
					dataRow.createCell(2)
							.setCellValue(list.get(i).get("sales"));
					dataRow.createCell(3).setCellValue(
							list.get(i).get("last_sale"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=FoSalesAndPerformance.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (action.equals("Download Recovery Report")) {
				int doId = Integer.parseInt(request.getParameter("doId"));

				System.out.println("Download Recovery Report: " + doId);

				System.out.println("Download Recovery Report");

				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("DORecoveryReport");

				ArrayList<HashMap<String, String>> list1 = DataAndReportsBal.getRecoveryReport(doId);

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Customer Name");
				header.createCell(1).setCellValue("Customer Phone");
				header.createCell(2).setCellValue("installment");
				header.createCell(3).setCellValue("FoName");
				header.createCell(4).setCellValue("Nd Name");
				header.createCell(5).setCellValue("Nd Phone");
				header.createCell(6).setCellValue("Month");
				header.createCell(7).setCellValue("Due Date");
				header.createCell(8).setCellValue("Paid Date");
				header.createCell(9).setCellValue("Next Due Date ");
				header.createCell(10).setCellValue("Paid Date Next");

				for (int i = 0; i < list1.size(); i++) {

					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(
							list1.get(i).get("customerName") + "");
					dataRow.createCell(1).setCellValue(
							list1.get(i).get("customerPhone") + "");
					dataRow.createCell(2).setCellValue(
							list1.get(i).get("installment") + "");
					dataRow.createCell(3).setCellValue(
							list1.get(i).get("FOName") + "");
					dataRow.createCell(4).setCellValue(
							list1.get(i).get("NdName") + "");
					dataRow.createCell(5).setCellValue(
							list1.get(i).get("NdPhone") + "");
					dataRow.createCell(6).setCellValue(
							list1.get(i).get("months") + "");
					dataRow.createCell(7).setCellValue(
							list1.get(i).get("due") + "");
					dataRow.createCell(8).setCellValue(
							list1.get(i).get("paid") + "");
					dataRow.createCell(9).setCellValue(
							list1.get(i).get("nextdue") + "");
					dataRow.createCell(10).setCellValue(
							list1.get(i).get("latepaid") + "");

				}

				System.out.println("Download Recovery Report end");
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment; filename=FO:report.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();

			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

}
