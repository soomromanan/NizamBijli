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
		data-userid="<%=sessionBean.getUserId()%>">
		<!-- begin container-fluid -->
		<div class="container-fluid">
			<!-- begin mobile sidebar expand / collapse button -->
			<div class="navbar-header navbar-header-without-bg">
				<a href="SuperAdminDashboard" class="navbar-brand"> <img
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
				<!-- appliance alerts -->
				<li class="dropdown" id="appliance_messages"><a
					href="javascript:;" data-toggle="dropdown"
					class="dropdown-toggle f-s-14"> <i class="fa fa-bell-o"></i> <span
						class="label"></span>
				</a>
					<ul
						class="dropdown-menu media-list pull-right animated fadeInDown width-md">
						<li class="dropdown-header bg-silver">Appliance Alarms</li>
						<li class="media" id="no_message"><a href="javascript:;">
								<div class="media-body">
									<h6 class="media-heading">No Alarm</h6>
								</div>
						</a></li>

						<li class="dropdown-footer text-center"><a
							href="SuperAdminNock">View more</a></li>
					</ul></li>


				<li class="dropdown" id="messages"><a href="javascript:;"
					data-toggle="dropdown" class="dropdown-toggle f-s-14"> <i
						class="fa fa-envelope m-r-5"></i> <span class="label"></span>
				</a>
					<ul
						class="dropdown-menu media-list pull-right animated fadeInDown width-md">
						<li class="dropdown-header bg-silver">Messages</li>
						<li class="media" id="no_loanbook_message"><a
							href="javascript:;">
								<div class="media-body">
									<h6 class="media-heading">No Message</h6>
								</div>
						</a></li>

						<li class="dropdown-footer text-center"><a
							href="SuperAdminNock">View more</a></li>
					</ul></li>


				<li class="dropdown navbar-user"><a href="javascript:;"
					data-toggle="dropdown" class="dropdown-toggle"> <i
						class="fa fa-bars"></i>
				</a>
					<ul class="dropdown-menu media-list animated fadeInLeft">
						<li class="media"><a href="DistrictOfficerForm">
								<div class="media-left">
									<i class="fa fa-user fa-lg"></i>
								</div>
								<div class="media-body">
									<p>Add User</p>
								</div>
						</a></li>
						<li class="media"><a href="ArchivedCustomers">
								<div class="media-left">
									<i class="fa fa-user fa-lg"></i>
								</div>
								<div class="media-body">
									<p>Archived Customers</p>
								</div>
						</a></li>
						<li class="media"><a href="CustomerLoanForm">
								<div class="media-left">
									<i class="fa fa-user fa-lg"></i>
								</div>
								<div class="media-body">
									<p>Add Customer</p>
								</div>
						</a></li>
						<li class="media"><a href="LoginServlet?click=logout"> <!--                                	<i class="fa fa-sign-out"></i> -->
								<div class="media-left">
									<i class="fa fa-sign-out fa-lg"></i>
								</div>
								<div class="media-body">
									<p class="">Log Out</p>
								</div>
						</a></li>
					</ul></li>
			</ul>

			<%
				}
			%>

			<!-- end header navigation right -->
		</div>
		<!-- end container-fluid -->
	</div>

	<!--  	================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>

	<!-- ================== END BASE JS ================== -->

	<script type="text/javascript"
		src="http://thorst.github.io/jquery-idletimer/prod/src/idle-timer.js"></script>
	<script type="text/javascript" src="assets/async/superAdminHeader.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 	updateData();
		});
	</script>



</body>
</html>




