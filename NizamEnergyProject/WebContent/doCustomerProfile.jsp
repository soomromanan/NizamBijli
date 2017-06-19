<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link href="assets/css/myGallery.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<link href="assets/plugins/isotope/isotope.css" rel="stylesheet" />
<link href="assets/plugins/lightbox/css/lightbox.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->
<link rel="stylesheet" href="assets/css/viewer.css" />
<link rel="stylesheet" href="assets/css/main.css" />
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />

</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			String customerId = (String) request.getAttribute("customerId");
			String customerCnic = (String) request.getAttribute("cnic");
			int key = (Integer) request.getAttribute("key");
			String eligibilityId = (String) request.getAttribute("appId");

	%>
	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="has-sub "><a href="DistrictOfficerDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li <%if (key == 1) {
					out.print("class='active'");
				}%>><a
						href="LoanRequest"> <i class="icon-note"></i> <span>Loan
								Request</span> <span class="badge pull-right"
							id="do_unseen_loan_request_count"></span>
					</a></li>


					<li <%if (key == 2) {
					out.print("class='active'");
				}%>><a
						href="DoWiseAdvanceBooking"><i class="fa fa-star"></i><span>Advance
								Booking</span> <span class="badge pull-right"
							id="do_unseen_bookings_count"></span></a></li>

					<li class="has-sub "><a href="DoAppliances"><i
							class="fa fa-suitcase"></i><span>Pipeline</span></a></li>

					<li class="has-sub"><a href="DoLoanBooks"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DoWiseFieldOfficers">Field Officer</a></li>
							<li><a href="DoWiseNizamDost">Nizam Dost</a></li>
						</ul></li>


					<li class="has-sub"><a href="DoAlarmsPage"> <i
							class="fa fa-star"></i> <span>Live Alerts</span>
					</a></li>

					<li class="has-sub "><a href="DoFutureLoanBooks"> <i
							class="fa fa-star"></i> <span>Future Loan Books</span>
					</a></li>
					<!-- begin sidebar minify button -->
					<li><a href="javascript:;" class="sidebar-minify-btn"
						data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
					<!-- end sidebar minify button -->
				</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">

			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header" id="customerName"></span> <span
				style="float: right; margin-bottom: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Created Date /<small
					id="created_date"> &nbsp;</small>
			</a> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Requested Date /<small
					id="requested_date"> &nbsp;<%="0000-00-00"%></small>
			</a></span><br>


			<!-- begin profile-info -->

			<!-- begin table -->

			<div class="row">
				<div class="col-sm-12">
					<div class="panel  panel-with-tabs">
						<div class="panel-heading" style="margin-left: -15px">
							<ul id="" class="nav nav-tabs">
								<li class="active"><a href="#personalInformation"
									data-toggle="tab"> <span class="hidden-xs">Personal
											Information</span>
								</a></li>

								<li><a href="#incomeDetails" data-toggle="tab"><span
										class="hidden-xs">Income Details</span></a></li>
								<li><a href="#assetsExpenses" data-toggle="tab"><span
										class="hidden-xs">Assets, Expenses & Liabilities</span></a></li>
								<li><a href="#gaurantorsInformation" data-toggle="tab"><span
										class="hidden-xs">Guarantors Information</span></a></li>

							</ul>
							<h4 class="panel-title"></h4>
						</div>

						<div id="myTabContent" class="tab-content">

							<!-- personal information tab starts -->

							<div class="tab-pane fade in active" id="personalInformation">
								<div class="panel-body " style="">
									<div class="table table-striped" style="font-size: 13px">
										<div class="row-well">
											<div class="col-md-6 well">
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Full
															Name</b></label>
													<div class="col-md-5" id="customer_name"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Primary
															number</b></label>
													<div class="col-md-5" id="customer_phone"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Secondary
															number</b></label>
													<div class="col-md-5" id="customer_phone2"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>CNIC</b></label>
													<div class="col-md-5" id="customer_cnic"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Date
															of Birth</b></label>
													<div class="col-md-5" id="date_of_birth"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Gender</b></label>
													<div class="col-md-5" id="gender"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Marital
															Status</b></label>
													<div class="col-md-5" id="relation_status"></div>
												</div>
												<br>
												<div class="form-group row">
													<label class="col-md-7 control-label"><b>Disability</b></label>
													<div class="col-md-5" id="customer_disibility"></div>
												</div>
												<br> <br>

												<fieldset>
													<legend>
														<b>Location</b>
													</legend>

													<div class="form-group row">
														<label class="col-md-7 control-label"><b>System
																installed place, Address</b></label>
														<div class="col-md-5" id="customer_sys_installed_place"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Address</b></label>
														<div class="col-md-5" id="customer_address"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Union
																Council</b></label>
														<div class="col-md-5" id="customer_union_council"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Tehsil,
																District</b></label>
														<div class="col-md-5" id="tehsil_district"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Province</b></label>
														<div class="col-md-5" id="province"></div>
													</div>
													<br> <br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Additional Contact Information</b>
													</legend>
													<table class="table equal table-responsive table-bordered"
														style="font-size: 13px">

														<thead style="background-color: ghostwhite;">
															<tr>
																<th>Name</th>
																<th>Relation</th>
																<th>Phone Number</th>
															</tr>
														</thead>
														<tbody id="additionContact">
														</tbody>
													</table>
												</fieldset>
											</div>

											<div class="col-md-6 well">
												<fieldset>
													<legend>
														<b>Household & Family</b>
													</legend>

													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Father
																Name</b></label>
														<div class="col-md-5" id="father_name"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Head
																of family</b></label>
														<div class="col-md-5" id="customer_head_faimly"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Caste</b></label>
														<div class="col-md-5" id="customerCaste"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Household
																members</b></label>
														<div class="col-md-5"
															id="customer_total_household_members"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Household
																Members Depends (Under 18)</b></label>
														<div class="col-md-5" id="customer_dependants_members"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Total
																Household Members (Adult & Above 60)</b></label>
														<div class="col-md-5" id="customer_total_adult_members"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Number
																of Childrens going to School</b></label>
														<div class="col-md-5" id="customer_children"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Family
																Type</b></label>
														<div class="col-md-5" id="customer_families_household"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Families
																living in Household</b></label>
														<div class="col-md-5" id="customer_families_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Room
																Occupied By Household (Only bedroom & living room)</b></label>
														<div class="col-md-5" id="customer_roomoccupied_household"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Residence</b></label>
														<div class="col-md-5" id="customer_residence"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Residence
																Since (number of years)</b></label>
														<div class="col-md-5" id="customer_residence_period"></div>
													</div>
													<br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Qualification & Other Information</b>
													</legend>

													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Education</b></label>
														<div class="col-md-5" id="education"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"><b>Technical/Vocational
																Training, Course name</b></label>
														<div class="col-md-5" id="customer_training_skill"></div>
													</div>
													<br>
												</fieldset>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- personal information tab ends -->

							<!-- income details tab starts -->

							<div class="tab-pane fade" id="incomeDetails">
								<div class="panel-body " style="">
									<div class="table table-striped" style="font-size: 13px">
										<div class="row-well">
											<div class="col-md-6 well">
												<fieldset>
													<div class="form-group row">
														<label class="col-md-7 control-label"><legend>
																<b>Monthly Income</b>
															</legend></label>
														<div class="col-md-5" id="net_monthly_income"
															style="font-size: 21px; font-weight: bold;"></div>
													</div>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Total
																Monthly Income</b>
														</label>
														<div class="col-md-5" id="customer_total_income"
															style="font-size: 12px; font-weight: bold;"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Total
																Irregularly Income</b>
														</label>
														<div class="col-md-5"
															id="customer_total_irregularly_income"
															style="font-size: 12px; font-weight: bold;"></div>
													</div>
													<br> <br>
												</fieldset>

												<fieldset>
													<legend>
														<b>Monthly Income Details</b>
													</legend>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Remittances
																Frequency (Amount)</b>
														</label>
														<div class="col-md-5" id="customerRemittancee"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Remittances
																Relation</b>
														</label>
														<div class="col-md-5" id="customerRemittancesRelation"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Remittances
																Profession</b>
														</label>
														<div class="col-md-5" id="customer_remittances_profession"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Rental
																Frequency, Rental Income (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_rental_profit_income"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Rental
																Profit Income Source</b>
														</label>
														<div class="col-md-5" id="customer_rental_from"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Labour
																Type</b>
														</label>
														<div class="col-md-5" id="customer_labour_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Labour
																Amount (Daily)</b>
														</label>
														<div class="col-md-5" id="customer_labour_amount"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Other
																Labour Type</b>
														</label>
														<div class="col-md-5" id="customer_other_labour_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>No.
																of Labour Days in Month</b>
														</label>
														<div class="col-md-5" id="customer_labour_in_month"></div>
													</div>
													<br> <br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Irregularly Income Details</b>
													</legend>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Agri
																income frequency (Amount)</b>
														</label>
														<div class="col-md-5" id="farming"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Live
																stock Type </b>
														</label>
														<div class="col-md-5" id="customerLivestockType"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Live
																stock (Profit)</b>
														</label>
														<div class="col-md-5" id="customerLivestockIncome"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Other
																Income Contributors (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_income_comtributers"></div>
													</div>
													<br>
												</fieldset>

											</div>
											<div class="col-md-6 well">
												<fieldset>
													<div class="form-group row">
														<label class="col-md-7 control-label"><legend>
																<b>Additional Income Details</b>
															</legend></label>
														<div class="col-md-5" id="additional_income_details"></div>
														<table class="table equal table-responsive table-bordered"
															style="font-size: 13px">

															<thead style="background-color: ghostwhite;">
																<tr>
																	<th>Detail</th>
																	<th>Amount</th>
																</tr>
															</thead>
															<tbody id="additionalIncome">

															</tbody>
														</table>
													</div>
												</fieldset>
												<br> <br>
												<fieldset>
													<legend>
														<b>Business Details</b>
													</legend>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Business
																Income (monthly)</b>
														</label>
														<div class="col-md-5" id="business_income"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Business
																Title</b>
														</label>
														<div class="col-md-5" id="business_name"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Business
																Type</b>
														</label>
														<div class="col-md-5" id="businees_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Years
																in Business</b>
														</label>
														<div class="col-md-5" id="period"></div>
													</div>
													<br> <br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Employment Details</b>
													</legend>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Salary/Pension</b>
														</label>
														<div class="col-md-5" id="salary_or_pension"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Company/Org.
																Name</b>
														</label>
														<div class="col-md-5" id="organisation_name"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Designation</b>
														</label>
														<div class="col-md-5" id="job_position"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Length
																of Job</b>
														</label>
														<div class="col-md-5" id="job_period"></div>
													</div>
													<br>
												</fieldset>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- income details tab ends -->

							<!-- assets, Expenses and liabilities tab starts -->
							<div class="tab-pane fade" id="assetsExpenses">
								<div class="panel-body " style="">
									<div class="table table-striped" style="font-size: 13px">
										<div class="row-well">
											<div class="col-md-6 well">
												<fieldset>
													<div class="form-group row">
														<label class="col-md-7 control-label"><legend>
																<b>Assets</b>
															</legend></label>
														<div class="col-md-5" id="assets_amount"
															style="font-size: 21px; font-weight: bold;"></div>
													</div>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Land
																in acres (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_land"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Buffalo
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_bbuffalo"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Cow
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_cow"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Calf
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_calf"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Goat/Sheep
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_goat_sheep"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>No.
																of Motar Bikes (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_bike"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>No.
																of Cars (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_car"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>No.
																of Tractors (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_tractors"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>TV
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_tv"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Fridge
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_fridge"></div>
													</div>
													<br> <br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Additional Assets</b>
													</legend>
													<table class="table equal table-responsive table-bordered"
														style="font-size: 13px">

														<thead style="background-color: ghostwhite;">
															<tr>
																<th>Asset Type</th>
																<th>Asset Amount</th>
															</tr>
														</thead>
														<tbody id="additionalAssets">

														</tbody>
													</table>
												</fieldset>
											</div>
											<div class="col-md-6 well">
												<fieldset>
													<div class="form-group row">
														<label class="col-md-7 control-label"><legend>
																<b>Monthly Expenses</b>
															</legend></label>
														<div class="col-md-5" id="monthly_expenses"
															style="font-size: 21px; font-weight: bold;"></div>
													</div>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Connectivity</b>
														</label>
														<div class="col-md-5" id="customer_electricity_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Primary
																Source of Electricity</b>
														</label>
														<div class="col-md-5" id="customer_sourceof_electricity"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Electricity
																Expense (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_electricity_amount"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Mobile
																Type</b>
														</label>
														<div class="col-md-5" id="customer_mobile_type"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Mobile
																Bills Amount</b>
														</label>
														<div class="col-md-5" id="customer_mobilebills_amount"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Network
																types in Area</b>
														</label>
														<div class="col-md-5" id="networks_in_area"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Transport
																Mode (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_transport_mode"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Karyana
																(Amount)</b>
														</label>
														<div class="col-md-5" id="customer_karyana"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Education
																Expense Amount</b>
														</label>
														<div class="col-md-5" id="customer_education_amount"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Medical
																Recurring (Amount)</b>
														</label>
														<div class="col-md-5" id="customer_medical_recuring"></div>
													</div>
													<br> <br>
												</fieldset>
												<fieldset>
													<legend>
														<b>Additional Expenses</b>
													</legend>
													<table class="table equal table-responsive table-bordered"
														style="font-size: 13px">

														<thead style="background-color: ghostwhite;">
															<tr>
																<th>Expense Type</th>
																<th>Expense Amount</th>
															</tr>
														</thead>
														<tbody id="additionalMonthlyExpenses">

														</tbody>
													</table>
												</fieldset>
											</div>
										</div>

										<!-- Loan and Liabilities starts -->
										<div class="row-well">
											<div class="col-md-12 well">
												<fieldset>
													<legend>
														<b>Loan and Liabilities</b>
													</legend>
													<table class="table equal table-responsive table-bordered"
														style="font-size: 13px">
														<thead style="background-color: ghostwhite;">
															<tr>
																<th>Name</th>
																<th>Borrowed Amount</th>
																<th>Remaining Amount</th>
																<th>Monthly Installments</th>
																<th>Duration of Loan</th>
																<th>Frequency of Payments</th>
															</tr>
														</thead>
														<tbody id="loanAndLiabilities">
														</tbody>
													</table>
												</fieldset>
											</div>
										</div>
										<!-- Loan and Liabilities ends -->
									</div>
								</div>
							</div>
							<!-- assets, Expenses and liabilities tab ends -->

							<!-- guarantors tab stars -->
							<div class="tab-pane fade" id="gaurantorsInformation">
								<div class="panel-body " style="">
									<div class="table table-striped" style="font-size: 13px">
										<div class="row-well">
											<div class="col-md-6 well">
												<fieldset>
													<legend>
														<b>Family Gaurantor</b>
													</legend>

													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Name</b>
														</label>
														<div class="col-md-5" id="f_guarantor_name"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>CNIC</b>
														</label>
														<div class="col-md-5" id="f_guarantor_cnic"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Primary
																Phone</b>
														</label>
														<div class="col-md-5" id="f_guarantor_primary_phone"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Secondary
																Phone</b>
														</label>
														<div class="col-md-5" id="f_guarantor_secondary_phone"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Relationship</b>
														</label>
														<div class="col-md-5" id="f_guarantor_relationship"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Monthly
																Income</b>
														</label>
														<div class="col-md-5" id="f_guarantor_monthly_income"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Profession</b>
														</label>
														<div class="col-md-5" id="f_guarantor_profession"></div>
													</div>
													<br>

												</fieldset>
											</div>
											<div class="col-md-6 well">
												<fieldset>
													<legend>
														<b>Outside Gaurantor</b>
													</legend>

													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Name</b>
														</label>
														<div class="col-md-5" id="o_guarantor_name"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>CNIC</b>
														</label>
														<div class="col-md-5" id="o_guarantor_cnic"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Primary
																Phone</b>
														</label>
														<div class="col-md-5" id="o_guarantor_primary_phone"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Secondary
																Phone</b>
														</label>
														<div class="col-md-5" id="o_guarantor_secondary_phone"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Relationship</b>
														</label>
														<div class="col-md-5" id="o_guarantor_relationship"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Monthly
																Income</b>
														</label>
														<div class="col-md-5" id="o_guarantor_monthly_income"></div>
													</div>
													<br>
													<div class="form-group row">
														<label class="col-md-7 control-label"> <b>Profession</b>
														</label>
														<div class="col-md-5" id="o_guarantor_profession"></div>
													</div>
													<br>

												</fieldset>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- guarantors tab ends -->
						</div>
					</div>
					<!-- CC verification ends -->
				</div>
			</div>
		</div>
	</div>
	<!-- end scrollbar -->




	<!-- begin profile-section -->

	<!-- end row -->

	<!-- begin scroll to top btn -->
	<a href="javascript:;"
		class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
		data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
	<!-- end scroll to top btn -->
	</div>
	</div>

	<!-- ================== BEGIN BASE JS ================== -->


	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/viewer.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/isotope/jquery.isotope.min.js"></script>
	<script src="assets/plugins/lightbox/js/lightbox-2.6.min.js"></script>
	<script src="assets/js/gallery.demo.min.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/bootstrap-wizard/js/ccVerification.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>
	<script
		src="assets/plugins/bootstrap-multiple-select/js/bootstrap-multiple-select.js"></script>
	<script src="js/customerProfile.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<script>
			$(document).ready(function() {
				App.init();
				getCustomerProfile('<%=customerId%>', '<%=customerCnic%>');
				getCustomerOtherPhoneNumbers('<%=customerId%>');
				getOtherIncome('<%=customerId%>');
				getOtherAssets('<%=customerId%>');
				getOtherMonthlyExpenses('<%=customerId%>');
				getCustomerLoanAndLiabilities('<%=customerId%>');
				getCustomerFamilyGuarantor('<%=customerId%>', 1);
				getCustomerOutsideGuarantor('<%=customerId%>', 2);
		});
	</script>
	<%
		}
	%>

</body>

</html>