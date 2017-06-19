<%@page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet" />
</head>
<body>
	<%
		UserBean sessionBean = (UserBean) session.getAttribute("email");
		if (sessionBean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
	%>
	<div id="header" class="header navbar navbar-inverse navbar-fixed-top"
		data-userid="<%=sessionBean.getUserId()%>"
		data-districtid="<%=sessionBean.getUser_district()%>">
		<!-- begin container-fluid -->
		<div class="container-fluid">
			<!-- begin mobile sidebar expand / collapse button -->
			<div class="navbar-header navbar-header-without-bg">
				<a href="DistrictOfficerDashboard" class="navbar-brand"> <img
					src="assets/icons/logo.png"
					style="margin-top: -15px; margin-left: -8px;">
				</a>
				<button type="button" class="navbar-toggle"
					data-click="sidebar-toggled">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>


			<!-- begin header navigation right -->
			<ul class="nav navbar-nav navbar-right">

				<li class="dropdown navbar-user"><a href="javascript:;"
					data-toggle="dropdown" class="dropdown-toggle"> <i
						class="fa fa-bars"></i>
				</a>
					<ul class="dropdown-menu animated fadeInLeft">
						<li class="arrow"></li>
						<li><a href="NdFoForm">Add Team</a></li>
						<li><a href="CashCustomerForm">Add Cash Customer</a></li>
						<li class="arrow">
						<li class="divider"></li>
						<li><a href="LoginServlet?click=logout">Log Out</a></li>
					</ul></li>
			</ul>
		</div>
		<%
			}
		%>
	</div>
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/async/doHeader.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>

</body>
</html>