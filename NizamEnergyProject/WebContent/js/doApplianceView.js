var appId = document.getElementById("appID").value;
var customerId = document.getElementById("customer_id").value;
var GSM = document.getElementById("gsmNumber");
var curr = document.getElementById("current").value;

$(document)
		.ready(
				function() {

					$('#editConsumer')
							.on(
									'click',
									function() {

										$('input:text').removeAttr("disabled");
										$('input:text').removeAttr("style");
										$("#imeinumber")
												.attr("onChange",
														"existingData('imeinumber','imeiresult',this);");

										$('#editConsumer').hide();
										$('#cancelEditConsumer').show();
										$('#saveConsumer').show();
									});

					$('#cancelEditConsumer').on(
							'click',
							function() {

								$('input:text').attr("disabled", "disabled");
								$('input:text').attr("style",
										"border:none;background-color:white")
								$('#cancelEditConsumer').hide();
								$('#editConsumer').show();
								$('#saveConsumer').hide();
							});

					$('#saveConsumer')
							.on(
									'click',
									function() {
										$('input:text').attr("disabled",
												"disabled");
										$('input:text')
												.attr("style",
														"border:none;background-color:white")
										$('#cancelEditConsumer').hide();
										$('#editConsumer').show();
										$('#saveConsumer').hide();
										$
												.ajax({
													url : "UpdateAppliance",
													method : "post",
													type : "json",
													data : {

														action : "updateCredentials",
														appImei : $(
																"#imeinumber")
																.val(),
														appGsm : $("#gsm")
																.val(),
														consumer : $(
																"#ConsumerNumber")
																.val(),
														appId : appId,

													},
													success : function(data) {
														console.log("data.data"
																+ data.data)
														if (data.data > 1) {
															console.log(data)
															$(
																	'#cloader span.fa-spin')
																	.fadeOut(
																			1000,
																			'linear',
																			function() {
																				$(
																						'#cloader')
																						.append(
																								'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
																			});
															window.location = "ViewServlet?click=viewDoAppliance&id="
																	+ data.data;
														}
														if (data.data < 1) {
															$(
																	'##cloader span.fa-spin')
																	.fadeOut(
																			10000,
																			'linear',
																			function() {
																				$(
																						'##cloader')
																						.append(
																								'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
																			});
														}

													}

												});

									});

					$('#editApp')
							.on(
									'click',
									function() {

										$('input:text').removeAttr("disabled");
										$('input:text').removeAttr("style");
										$("#imeinumber")
												.attr("onChange",
														"existingData('imeinumber','imeiresult',this);");

										$('#editApp').hide();
										$('#cancelEdit').show();
										$('#saveApp').show();
									});
					$('#cancelEdit').on(
							'click',
							function() {

								$('input:text').attr("disabled", "disabled");
								$('input:text').attr("style",
										"border:none;background-color:white")
								$('#cancelEdit').hide();
								$('#editApp').show();
								$('#saveApp').hide();
							});

					$('#saveApp')
							.on(
									'click',
									function() {

										$('input:text').attr("disabled",
												"disabled");
										$('input:text')
												.attr("style",
														"border:none;background-color:white")
										$('#cancelEdit').hide();
										$('#editApp').show();
										$('#saveApp').hide();
										$
												.ajax({
													url : "UpdateAppliance",
													method : "post",
													type : "json",
													beforeSend : function() {
														$('#loader')
																.append(
																		'<span class="fa fa-circle-o-notch fa-spin"></span>')
													},
													data : {
														action : "updateAppliance",
														appImei : $(
																"#imeinumber")
																.val(),
														appGsm : $("#gsm")
																.val(),
														appId : appId,
														customerId : customerId

													},
													success : function(data) {
														console.log("data.data"
																+ data.data)
														console
																.log("customerId"
																		+ customerId)
														console.log()
														if (data.data > 1) {
															console.log(data)
															$(
																	'#loader span.fa-spin')
																	.fadeOut(
																			1000,
																			'linear',
																			function() {
																				$(
																						'#loader')
																						.append(
																								'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
																			});
															window.location = "ViewServlet?click=viewDoAppliance&id="
																	+ data.data;
														}
														if (data.data < 1) {
															$(
																	'##loader span.fa-spin')
																	.fadeOut(
																			10000,
																			'linear',
																			function() {
																				$(
																						'##loader')
																						.append(
																								'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
																			});
														}

													}

												});

									});
				});
