<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
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
<!-- ================== BEGIN BASE JS ================== -->
<!-- ================== END BASE JS ================== -->
<style>
#helpico {
	vertical-align: text-bottom;
}
</style>

<script type="text/javascript">
	function checkEmail() {
		var email = document.getElementById('userEmail').value;
		$.ajax({
			url : 'UserLoginController?click=checkMail&userEmail=' + email,
			success : function(data) {
				document.getElementById("emailErrorMsg").innerHTML = data;
			}
		});
	}

	function checkFormValidation(){
		var email =document.getElementById('userEmail').value;
		var emailErrorMsg = document.getElementById("emailErrorMsg").innerHTML;
		if(email=="" || emailErrorMsg.trim()!=""){
			return false;
		}else{
			<%
			if(request.getAttribute("msg")!=null){
			if(request.getAttribute("msg").toString().equalsIgnoreCase("New Ip")){%>
				var userCode = document.getElementById("userCode").value;
				var userHiddenCode = document.getElementById("userHiddenCode").value;
				if(userCode==userHiddenCode){
					var localIp = document.getElementById("ipAddress").value;
					alert(localIp);
					$.ajax({
						url : '/AddIpAddress',
						dataType : 'json',
						method : 'POST',
						data : {
							localIpAddress : localIp,
							
						},
						success : function(data){
							alert("hgsk");
						}
					});	
					return true;
				}else{
					return false;
				}
			<%}
				else{%>
				return true;
			<%}
			}%>
		}
	}
</script>
<script>
	function getIPs(callback) {
		var ip_dups = {};

		//compatibility for firefox and chrome
		var RTCPeerConnection = window.RTCPeerConnection
				|| window.mozRTCPeerConnection
				|| window.webkitRTCPeerConnection;
		var useWebKit = !!window.webkitRTCPeerConnection;

		if (!RTCPeerConnection) {
			var win = iframe.contentWindow;
			RTCPeerConnection = win.RTCPeerConnection
					|| win.mozRTCPeerConnection || win.webkitRTCPeerConnection;
			useWebKit = !!win.webkitRTCPeerConnection;
		}

		//minimal requirements for data connection
		var mediaConstraints = {
			optional : [ {
				RtpDataChannels : true
			} ]
		};

		var servers = {
			iceServers : [ {
				urls : "stun:stun.services.mozilla.com"
			} ]
		};

		//construct a new RTCPeerConnection
		var pc = new RTCPeerConnection(servers, mediaConstraints);

		function handleCandidate(candidate) {
			//match just the IP address
			var ip_regex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/
			var ip_addr = ip_regex.exec(candidate)[1];

			//remove duplicates
			if (ip_dups[ip_addr] === undefined)
				callback(ip_addr);

			ip_dups[ip_addr] = true;
		}

		//listen for candidate events
		pc.onicecandidate = function(ice) {

			//skip non-candidate events
			if (ice.candidate)
				handleCandidate(ice.candidate.candidate);
		};

		//create a bogus data channel
		pc.createDataChannel("");

		//create an offer sdp
		pc.createOffer(function(result) {
			pc.setLocalDescription(result, function() {
			}, function() {
			});

		}, function() {
		});

		//wait for a while to let everything done
		setTimeout(function() {
			var lines = pc.localDescription.sdp.split('\n');
			lines.forEach(function(line) {
				if (line.indexOf('a=candidate:') === 0)
					handleCandidate(line);
			});
		}, 1000);
	}
	getIPs(function(ip) {
		//local IPs
		if (ip.match(/^(192\.168\.|169\.254\.|10\.|172\.(1[6-9]|2\d|3[01]))/)) {
			document.getElementById("ipAddress").value = ip;
		}
	});
</script>

</head>
<body class="pace-top">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<div class="login-cover" style="background: #333;">
		<!--             <div class="login-cover-image"><img style="width: 100%" src="assets/img/login-bg/BG3.jpg" data-id="login-cover-image" alt="" /></div> -->
		<div class="login-cover-bg"></div>
	</div>
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin login -->
		<div class="login login-v2" data-pageload-addclass="animated fadeIn">
			<!-- begin brand -->
			<div class="login-header" style="padding-left: 1px;">
				<div class="brand">
					<img id="helpico" src="assets/icons/logo.png">
				</div>
			</div>
			<!-- end brand -->
			<div class="login-content" style="padding: 24px; color: #999">
				<form action="UserLoginController"
					onsubmit="return checkFormValidation();" method="POST"
					class="margin-bottom-0">
					<div class="form-group m-b-20">
						<span style="color: red;" id="emailErrorMsg"></span>
						<%
							if (request.getAttribute("userEmail") != null) {
						%>
						<input type="text" class="form-control input-lg"
							placeholder="Email Address" name="userEmail" maxlength="50"
							id="userEmail"
							value="<%=request.getAttribute("userEmail").toString()%>"
							onchange="checkEmail()" required />
						<%
							} else {
						%>
						<input type="text" class="form-control input-lg"
							placeholder="Email Address" name="userEmail" id="userEmail"
							maxlength="50" value="" onchange="checkEmail()" required />
						<%
							}
						%>
					</div>
					<div class="form-group m-b-20">
						<%
							if (request.getAttribute("msg") != null) {
								if (!request.getAttribute("msg").toString()
										.equalsIgnoreCase("New Ip")) {
						%>
						<span style="color: red"> <%=request.getAttribute("msg").toString()%>
						</span>
						<%
							}
							}
						%>

						<%
							if (request.getAttribute("password") != null) {
						%>
						<input type="password" class="form-control input-lg"
							maxlength="30" placeholder="Password" name="userPassword"
							value="<%=request.getAttribute("password").toString()%>" required />
						<%
							} else {
						%>
						<input type="password" class="form-control input-lg"
							placeholder="Password" maxlength="30" name="userPassword"
							value="" required />
						<%
							}
						%>
						<input type="hidden" id="ipAddress" name="ipAddress" />

					</div>
					<%
						if (request.getAttribute("msg") != null) {
					%>
					<%
						if (request.getAttribute("msg").toString()
									.equalsIgnoreCase("New Ip")) {
					%>
					<div class="form-group m-b-20">
						<span style="color: red;" id="codeErrorMsg"></span> <span
							style="color: white;">You are accessing from new IP
							address. Verification code sent to your email.</span> <input
							type="hidden"
							value='<%=request.getAttribute("code").toString()%>'
							id="userHiddenCode"> <input type="text"
							class="form-control input-lg" name="userCode" id="userCode" />
					</div>
					<%
						}
						}
					%>
					<div class="checkbox m-b-20">
						<label> <input type="checkbox" /> Remember Me
						</label>
					</div>
					<div class="login-buttons">
						<button type="submit" name="click" value="login"
							class="btn btn-success btn-block  btn-lg"
							style="background-color: rgba(245, 179, 31, 0.88);">
							<span style="color: white"><b>Login</b></span>
						</button>
					</div>
					<div style="margin-top: 2%;">
						<a href="ForgotPasswordd">Forgot your password?</a>
					</div>


				</form>
			</div>
		</div>
		<!-- end login -->

	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/login-v2.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
		$(document).ready(function() {
			App.init();
			LoginV2.init();
		});
	</script>

</body>
</html>
