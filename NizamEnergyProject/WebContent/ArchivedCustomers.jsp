<%@page import="java.util.HashMap"%>
<%@page import="bal.CustomerArchivedBAL"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />

<!-- ================== END BASE CSS STYLE ================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
 
<!-- ================== END BASE JS ================== -->
</head>
<body>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container" class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp" %>
		<!-- begin page-header -->
		<!-- end page-header -->
		<div class="content">
			<h1 class="page-header">Archived Customers</h1>
			<div class="panel panel-inverse">
			    <div class="panel-heading">
			        <h4 class="panel-title">Archived Customers Table</h4>
			    </div>
			    <div class="panel-body">
			    <div class="table-responsive" style="font-size: 13px;">
	                 <table id="data-table" class="table table-hover table-bordered">
	                     <thead>
	                         <tr>
	                             <th>#Id</th>
	                             <th>Customer Name</th>
	                             <th>Created On</th>
	                             <th>Updated On</th>
	                             <th>Form Wizard Step</th>
	                             <th>Complete Form</th>
	                         </tr>
	                     </thead>
	                     <tbody>
							<% 	CustomerArchivedBAL customerArchivedBAL = new CustomerArchivedBAL();
								ArrayList<HashMap<String, String>> list = customerArchivedBAL.getArchivedCustomers();
								for(int i = 0; i < list.size(); i++){
								%>
								<tr>
								<td><%= list.get(i).get("customerId") %></td>
								<td>
									<a href="UpdateCustomerForm?customerid=<%= list.get(i).get("customerId") %>" ><%= list.get(i).get("customerName") %></a>
								</td>
								<td><%= list.get(i).get("createdDate") %></td>
								<td>
									<%
									if(list.get(i).get("updatedDate") == null){
										%><p>Not Updated</p><%
									}else{
										%><%= list.get(i).get("updatedDate") %><%
									}
									%>
									
								</td>
								<td><%= list.get(i).get("formWizardStep") %></td>
								<td><%= list.get(i).get("percentage") %></td>
								</tr>
								<%
							}
							%>
	                     </tbody>
	                 </table>
	                 </div>
			    </div>
			</div>
		</div>
		<!-- end #header -->
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>
<!-- 	<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script> -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/js/table-manage-default.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		$(document).ready(function() {
			App.init();
			TableManageDefault.init();
		});
	</script>
	
	
</body>


</html>



