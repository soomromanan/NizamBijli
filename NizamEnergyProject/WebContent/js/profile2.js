
$(document).ready(function(){
	
	var customerId=$('#customerBeanID').val();
	var expensesSize=$('#expenses').val();
	var assetsSize=$("#assetsSize").val();
	$('.photo-thumbnails .thumbnail').click(function() {
		$('.photo-thumbnails .thumbnail').removeClass('current');
		$(this).addClass('current');
		var path = $(this).find('img').attr('src');
		$('#big-photo img').attr('src', path);
 });	
	

	
	$('#cust').click(function()
			{
//		console.log('1111111111111111111111111111111')
		$("#profile input:text").removeAttr("disabled")
		$("#profile input:text").removeAttr("style")
		$("#profile input[value='N/A']").val('')
		$("#profile select").removeAttr("disabled")
		$("#profile select").removeAttr("style")
		$("#profile input:text").attr("maxlength","50")
		$('#customerPrimaryNumber').attr("maxlength","11")
		$('#customerCnic').attr("maxlength","15")
		
		$('#cust').hide();
		$('#cancelcust').show();
		$('#savecustomers').show();
        $('#cancelcust').click(function()
		{
			$("#profile input:text").attr("disabled")
			$("#profile input:text").attr("style","background-color:white;border:none;")
			$("#profile select").attr("disabled")
			$("#profile select").attr("style","background-color:white;border:none;-webkit-appearance: none;-moz-appearance: none;")
			$('#profile input:text').filter(function() { return this.value == ""; }).val("N/A");
			$('#cust').show();
			$('#cancelcust').hide();
			$('#savecustomers').hide();
				});
		$('#savecustomers').click(function(){
//			$("#personalinformation").validate({
//		   });
			$("#profile input:text").attr("disabled","disabled")
			$("#profile input:text").attr("style","background-color:white;border:none;width:100%")
			$("#profile select").attr("disabled","disabled")
			$("#profile select").attr("style","background-color:white;border:none;-webkit-appearance: none;-moz-appearance: none;")
			$('#profile input:text').filter(function() { return this.value == ""; }).val("N/A");
			
			$.ajax({
				url : 'EditInformation',
				method : 'Post',
				dataType : 'json',
				async:false,
				beforeSend : function(){
					$('#customerCircle').show();
					},
					data:{
						customrId:customerId,
						action:'updatecustomers',
						customerName:$('#name').val(),
						customerCnic: $('#customerCnic').val(),
						customerAddress:$('#customerAddress').val(),
						customerPrimaryNumber:$('#customerPrimaryNumber').val(),
						customerSecondaryNumber:$('#customerSecondaryNumber').val(),
						cusotmerGender:$('#customerEmail').val(),
						customerDob:$('#slectEducation').val(),
						customerAge:$('#MaritalStatus').val(),
						customerFather:$('#fatherName').val(),
						customerFamily:$('#family').val(),
						generalOne:$('#general1').val(),
						generalTwo:$('#general2').val(),
						generalThree:$('#general3').val(),
						generalFoure:$('#general4').val(),
						generalFive:$('#general5').val()
//						
					},
					success:function(data){
						console.log(data)
						if(data.data==true)
						{
							$('#customerCircle').fadeOut(10000,'linear',function(){
								$('#customerSuccess').show();
								$('#customerSuccess').fadeOut(5000,'linear',function(){})
							});
							$('#cust').show();
							$('#cancelcust').hide();
							$('#savecustomers').hide();
							
						}//end if 
						if(data.data==false)
							{
								$('#custEditButtons span.fa-spin').fadeOut(10000,'linear',function(){
									$('#custEditButtons').html('<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
								});
							}
					}
			});
		});

			
		}); // end of customer personal information
				

	
	
	
	$('#garantorButton').click(function()
			{
		
		$('#famGaurantorIncomeSpan').css("display", "none");
		$('#famGaurantorIncome').prop("type", "text");
		$('#famGaurantorIncome').css("display","block");
		
		$('#outGaurantorIncomeSpan').css("display", "none");
		$('#outGaurantorIncome').prop("type", "text");
		$('#outGaurantorIncome').css("display","block");
		
		
		$('#garantorButton').hide();
		$('#saveGaran').show();
		$('#cancelGarantor').show();
		$("#gaur input:text, #gaur input:text").removeAttr("disabled")
		$("#gaur input:text, #gaur input:text").removeAttr("style")
		$("#gaur input[value='N/A']").val('')
		$("#gaur input:text, #gaur input:text").attr("maxlength","50")
		$("#famGaurantorPhone, #outGaurantorPhone").attr("maxlength","11")
		$("#famGaurantorCnic, #outGaurantorCnic").attr("maxlength","15")
		
		$('#saveGaran').click(function()
					{
					$("#gaur input:text, #gaur input:text").attr("disabled")
					$("#gaur input:text, #gaur input:text").attr("style","background-color:white;border:none;")
							$.ajax({
								url:"EditInformation",
								method:"Post",
								dataType:"json",
								beforeSend : function(){
									$('#guarantorCircle').show();
									},
								data:
								{	
									action:"saveGaurantor",
									famGaurantorName:$('#famGaurantorName').val(),
									famGaurantorFather:$('#famGaranFather').val(),
									famGaurantorPhone:$('#famGaurantorPhone').val(),
									famGaurantorCnic:$('#famGaurantorCnic').val(),
									famGaurantorRelation:$('#famGaurantorRelation').val(),
									famGaurantorId:$("#famGaurantorId").val(),
									famGaurantorIncome:$("#famGaurantorIncome").val(),
									
									outGaurantorName:$('#outGaurantorName').val(),
									outGaurantorFather:$('#outGaranFather').val(),
									outGaurantorPhone:$('#outGaurantorPhone').val(),
									outGaurantorCnic:$('#outGaurantorCnic').val(),
									outGaurantorRelation:$('#outGaurantorRelation').val(),
									outGaurantorIncome:$("#outGaurantorIncome").val(),
									outGaurantorId:$("#outGaurantorId").val(),
									customrId:customerId,
									
									
								},
								success:function(data)
								{
									console.log(data)
									if(data.data==true)
									{
										$('#guarantorCircle').fadeOut(10000,function()
												{
											$('#guarantorSuccess').show();
											$('#guarantorSuccess').fadeOut(5000,function()
													{});
											});
										$('#garantorButton').show();
										$('#cancelGarantor').hide();
										$('#saveGaran').hide();
									}
									if(data.data==false)
									{
										$('#custEditButtons span.fa-spin').fadeOut(10000,'linear',function(){
											$('#custEditButtons').html('<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
										});
									}
								}	
									
							});
						});/// end of save of
				$('#cancelGarantor').click(function()
						{
					$("#gaur input:text, #gaur input:text").attr("disabled")
					$("#gaur input:text, #gaur input:text").attr("style","background-color:white;border:none;");
					$('#garantorButton').show();
					$('#saveGaran').hide();
					$('#cancelGarantor').hide();
					
						});
				
			});////end of  gaurantors information
	
	function formatNumber(number)
	{
	    number = number.toFixed(2) + '';
	    x = number.split('.');
	    x1 = x[0];
	    x2 = x.length > 1 ? '.' + x[1] : '';
	    var rgx = /(\d+)(\d{3})/;
	    while (rgx.test(x1)) {
	        x1 = x1.replace(rgx, '$1' + ',' + '$2');
	    }
	    return x1 + x2;
	}
	
	$('#customerIncomeButton').click(function()
			{

		$('#salaryOrPansionSpan').css("display","none");
		$("#salaryOrPansion").prop("type", "text");
		$('#salaryOrPansion').css("display","block");

		$('#businessIncomeSpan').css("display", "none");
		$('#businessIncome').prop("type", "text");
		$('#businessIncome').css("display","block");
		
		$('#farmingSpan').css("display", "none");
		$('#farming').prop("type", "text");
		$('#farming').css("display","block");
		
		$('#familyContributionSpan').css("display", "none");
		$('#familyContribution').prop("type", "text");
		$('#familyContribution').css("display","block");
		
		$('#otherIncomeAmountSpan').css("display", "none");
		$('#otherIncomeAmount').prop("type", "text");
		$('#otherIncomeAmount').css("display","block");
		
		
		
		
		
		$("#income input:text").removeAttr("disabled")
		$("#income input:text").removeAttr("style")
		$("#income input[value='N/A']").val('')
		$("#income input:text").attr("maxlength","50")
		$('#customerBusinessPhone').attr("maxlength","11")
		$('#customerEmploymentOrgPhone').attr("maxlength","11")
		
		$('#customerIncomeButton').hide();
		$('#saveCustomerIncome').show();
		$('#canceCustomerIncome').show();
		var $form = $('#incomeForm'),
		$summands = $form.find('.income_count'),
		 $sumDisplay = $('#income_sum');
		$form.delegate('.income_count', 'change', function ()
				{
				    var sum = 0;
				    $summands.each(function ()
				    {
				        var value = Number($(this).val());
				        if (!isNaN(value)) sum += value;
				    });

				    $sumDisplay.html("<b>"+formatNumber(sum)+"  PKR </b>");
				});
				$('#saveCustomerIncome').click(function()
						{
						console.log('--------')
						$("#income input:text").attr("diabled")
						$("#income input:text").attr("style","background-color:white;border:none;")
						var data =new Object();
						var otherIncomeIds=$('[name=otherIncomeIds]').toArray();
						var otherIncomeDetails=$('[name=otherIncomeDetail]').toArray();
						var otherIncomeAmount=$('[name=otherIncomeAmount]').toArray();
						data.incomeIds=[];
						data.incomeDetails=[];
						data.incomeAmounts=[];
						console.log("otehrIncomeIds")
						
						$.each(otherIncomeIds, function(e)
								{
									data.incomeIds[e]=otherIncomeIds[e].value
									console.log(otherIncomeIds[e].value)
								});
						console.log("otherIncomeDetails")
						$.each(otherIncomeDetails, function(e)
						{
							data.incomeDetails[e]=otherIncomeDetails[e].value
							console.log(otherIncomeDetails[e].value)
						});
						console.log("otherIncomeAmount")
						$.each(otherIncomeAmount,function(e)
								{
								 data.incomeAmounts[e]=otherIncomeAmount[e].value;
								 console.log(otherIncomeAmount[e].value)
								});
						console.log("other Income sourses otherIncomeIds  "+otherIncomeIds)
						console.log(data)
//						alert($('#salaryOrPansion').val()
//								)
						$.ajax({
							url:"EditInformation",
							method:"POST",
							dataType:"json",
							beforeSend : function(){
								$('#incomeCircle').show();
								},
							data:{
								action:"SaveIncome",
								customrId:customerId,
								data:data,
								business:$('#customerBusinessName').val(),
								businessType:$('#customerBusinessType').val(),
								businessAddress:$('#customerBusinessAddress').val(),
								businessPhone:$('#customerBusinessPhone').val(),
								businessPeriod:$('#bussinessPeriod').val(),
								bussinessComments:$('#customerBusinessComment').val(),
								employmentPosition:$('#customerEmploymentPosition').val(),
								employmentPeriod:$('#customerEmploymentPeriod').val(),
								employmentOrg:$('#customerEmploymentOrganization').val(),
								employmentOrgPhone:$('#customerEmploymentOrgPhone').val(),
								supervisor:$('#EmployementSupervisor').val(),
								customerSalaryPansion:$('#salaryOrPansion').val(),
								customerFarming:$('#farming').val(),
								customerBusinessIncome:$('#businessIncome').val(),
								customerFamilyContribution:$('#familyContribution').val(),
								customerDetail:$('#otherDetail').val(),
								Otheramount:$('#otherAmount').val(),
								businessId:$("#businessId").val(),
								employementId:$("#employementId").val(),
								EmployementAddress:$("#EmployementAddress").val(),
								
								
							},
							success:function(data)
							{
								console.log(data)
								if( data.data=true && data.employement==true && data.income==true){
									console.log("data to true hy fir ye q lafra ker rahi hy")
								$('#incomeCircle').fadeOut(10000,function(){
									$('#incomeSuccess').show();
									$('#incomeSuccess').fadeOut(5000,function(){});
									});
									
								}
								
								if(data.data==false)
								{
									$('#custEditButtons span.fa-spin').fadeOut(10000,function(){
										$('#custEditButtons').append('<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
									});
									
								}
								
								
								$('#customerIncomeButton').show();
								$('#saveCustomerIncome').hide();
								$('#canceCustomerIncome').hide();
							}
							
							
						});/////end of ajax
						
						});/////end save of income detatils
				
		$('#canceCustomerIncome').click(function()
						{
					$("#income input:text").attr("diabled")
					$("#income input:text").attr("style","background-color:white;border:none;")
					$('#canceCustomerIncome').hide();
					$('#saveCustomerIncome').hide();
					$('#customerIncomeButton').show();
					});
				
			});/// end of customer income details
	
	
	///Genral information starts

	 $('#customerGenralInformation').click(function()
	   {
		 
		 // work if using loop
			$('#expenseAmountSpan').css("display", "none");
			$('#expenseAmount').prop("type", "text");
			$('#expenseAmount').css("display","block");
			
			$('#assetsAmountSpan').css("display", "none");
			$('#assetsAmount').prop("type", "text");
			$('#assetsAmount').css("display","block");
			
			
			$('#borrowedAmmountSpan').css("display", "none");
			$('#borrowedAmmount').prop("type", "text");
			$('#borrowedAmmount').css("display","block");
			
			$('#remainingAmountSpan').css("display", "none");
			$('#remainingAmount').prop("type", "text");
			$('#remainingAmount').css("display","block");
			
			$('#monthlyInstallmentSpan').css("display", "none");
			$('#monthlyInstallment').prop("type", "text");
			$('#monthlyInstallment').css("display","block");
			
			$('#GKborrowedAmmountSpan').css("display", "none");
			$('#GKborrowedAmmount').prop("type", "text");
			$('#GKborrowedAmmount').css("display","block");
		 
			$('#GKremainingAmountSpan').css("display", "none");
			$('#GKremainingAmount').prop("type", "text");
			$('#GKremainingAmount').css("display","block");
		 
			$('#GKmonthlyInstallmentSpan').css("display", "none");
			$('#GKmonthlyInstallment').prop("type", "text");
			$('#GKmonthlyInstallment').css("display","block");
		 
			$('#FFborrowedAmmountSpan').css("display", "none");
			$('#FFborrowedAmmount').prop("type", "text");
			$('#FFborrowedAmmount').css("display","block");
		 
			$('#FFremainingAmountSpan').css("display", "none");
			$('#FFremainingAmount').prop("type", "text");
			$('#FFremainingAmount').css("display","block");
		 
			$('#FFmonthlyInstallmentSpan').css("display", "none");
			$('#FFmonthlyInstallment').prop("type", "text");
			$('#FFmonthlyInstallment').css("display","block");
		 
		 
		 $("#geninfo input:text").removeAttr("disabled")
		 $("#geninfo input:text").removeAttr("style")
		 $("#geninfo input[value='N/A']").val('')
		 $("#geninfo input:text").attr("maxlength","50")
		 $("#genralSave").show();
		 $("#genralcancel").show();
		 $(".addOther").show();
		 $('#customerGenralInformation').hide();
		 $("#genralcancel").click(function(){
			 $("#geninfo input:text").attr("diabled");
			 $("#geninfo input:text").attr("style","background-color:white;border:none;");
			 $("#genralSave").hide();
			 $("#genralcancel").hide();
			 $('#customerGenralInformation').show();
			 $(".addOther").hide();
		 });
		 $("#genralSave").click(function(){
			 $("#geninfo input:text").attr("diabled");
			 $("#geninfo input:text").attr("style","background-color:white;border:none;");
			 var data = new Object();
			 var types=$('[name=expenseTypes]').toArray()
			 var amounts=$('[name="expenseAmount"]').toArray()
			 var assetsIDs=$('[name=assetsIDs]').toArray()
			 var assetType=$('[name=assetsType]').toArray()
			 var assetsAmount=$('[name="assetsAmount"]').toArray()
			 var expenseIDs=$('[name="expenseIDs"]').toArray()
			 data.monthlyExpenseType=[];
			 data.monthlyExpenseAmount=[];
			 data.ExpenseIDs=[];
			 data.assetTypesArray=[];
			 data.assetAmountArray=[];
			 data.assetsIds=[];
			 $.each(types, function(e){
					data.monthlyExpenseType[e] = types[e].value
				})
			$.each(amounts, function(e){
					data.monthlyExpenseAmount[e] = amounts[e].value
				})	
			$.each(assetType,function(e){
				data.assetTypesArray[e]=assetType[e].value
			})	
			$.each(assetsAmount,function(e){
				data.assetAmountArray[e]=assetsAmount[e].value
			})
			$.each(assetsIDs,function(e){
				data.assetsIds[e]=assetsIDs[e].value
			})
			$.each(expenseIDs,function(e){
				data.ExpenseIDs[e]=expenseIDs[e].value
			})
			console.log(data);
			console.log($("#loanID").val())
			console.log("11111111111111111111111111111111111111111")
			console.log($("#GKloanID").val()) 
		console.log("22222222222222222222222222222222222222222222222")
			console.log($("#FFloanID").val()) 
			 $.ajax({
					url:"EditInformation",
					method:"POST",
					dataType:"json",
					beforeSend : function(){
					$("#genInfoCircle")	.show();
					},
					data:{
						action:"savegenInfo",
						customrId:customerId,
						data:data,
						loanDonner:$('#loanDonners').val(),
						borrowedAmmount:$('#borrowedAmmount').val(),
						remainingAmount:$('#remainingAmount').val(),
						monthlyInstallment:$("#monthlyInstallment").val(),
						loanID:$("#loanID").val(),
						GKloanDonner:$("#GKloanDonner").val(),
						GKborrowedAmmount:$("#GKborrowedAmmount").val(),
						GKremainingAmount:$("#GKremainingAmount").val(),
						GKmonthlyInstallment:$("#GKmonthlyInstallment").val(),
						GKloanID:$("#GKloanID").val(),
						FFloanDonner:$("#FFloanDonner").val(),
						FFborrowedAmmount:$("#FFborrowedAmmount").val(),
						FFremainingAmount:$("#FFremainingAmount").val(),
						FFmonthlyInstallment:$("#FFmonthlyInstallment").val(),
						FFloanID:$("#FFloanID").val()
						
					},
					success:function(data)
					{
						console.log(data)
						if(data.monthlyExpense==1 || data.bank==1 || data.kryanaGrocery==1 || data.familyFreind==1 || data.assets==1 )
						{
							$("#genInfoCircle").fadeOut(10000,function(){
								$("#genInfoSuccess").show();
								$('#genInfoSuccess').fadeOut(5000,function(){});
							});
							
						}
					}
			 });	 


			 $("#genralSave").hide();
			 $("#genralcancel").hide();
			 $(".addOther").hide();
			 $('#customerGenralInformation').show();
			 
		 });
		 
	   });


});
	
	
