<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil.Role"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.country.CountryBO"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	AttorneyBO attorney = (AttorneyBO) request.getAttribute("attorneyDetails");
	String courtType = (String)request.getAttribute("courtType");
	List<StateBO> states = new StateBO(courtType).orderBy(StateBO.STATECODE).search();
	List<CountryBO> countries = new CountryBO(courtType).search();
	boolean hasRole = false;
	if(RoleUtil.hasRole(request, Role.COURT_SERVICES, Role.IT_ADMIN)){
		hasRole = true;
	}
	String disabled = (hasRole)?"":"disabled";
	String parentCornId = (String)request.getAttribute("parentCornId");
	String action = (String)request.getAttribute("action");
	
	URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CorisAttorneyDetailsServlet");

	String paramsSubmitForm = "action="+action;
	String paramsDeleteAttorney = "mode=delete&action=deleteAttorney";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CORIS - Attorney Management</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

    <!-- main content -->
    <main>
		<div class="container-fluid">
		
			<% if (!TextUtil.isEmpty(TextUtil.print(errorMessage))) { %>
			<div class="alert alert-danger text-center m-2"><%=TextUtil.print(errorMessage) %></div>
			<% } %>
			
			<div class="card m-2">
			
				<div class="card-header">
					<% if("add".equals(action)){ %>
						<strong>Attorney Add</strong>
					<% }else{ %>
						<strong><%=attorney.getFirstName() + " " + attorney.getLastName() %></strong>  <span class="float-right"><strong>Bar Number / State:</strong> <%=attorney.getBarNum() %> / <%=attorney.getBarState() %></span>
					<% } %>
				</div>
				
				<div class="card-body">
				
					<form id="attorneyForm" name="attorneyForm" action="" novalidate>
						<input type="hidden" name="mode" value="update"/>
						<input type="hidden" name="barNum" value="<%=attorney.getBarNum() %>"/>
						<input type="hidden" name="barState" value="<%=attorney.getBarState() %>"/>
						
						<div class="row col-24">
						
							<div class='col-md-8'>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Bar Number<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="barNum" name="barNum" value="<%=TextUtil.print(attorney.getBarNum()) %>" maxlength="10" <%=("add".equals(action))?"":"disabled"%> required>
									</div>
								</div>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Bar State<span class="text-danger">*</span></strong></label>
										<select class="form-control" id="barState" name="barState" <%=("add".equals(action))?"":"disabled"%> required>
											<option value=""></option>
											<% for(StateBO st:states){ %>
											<option value="<%=st.getStateCode() %>" <%=st.getStateCode().equals(attorney.getBarState())?"selected":"" %>><%=TextUtil.print(st.getStateCode())%> </option>
											<%} %>
										</select>
									</div>
								</div>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Bar Status<span class="text-danger">*</span></strong></label>
										<select class="form-control" id="barStatus" name="barStatus" <%=disabled %> required>
											<option value=""></option>
											<option value="A" <%="A".equalsIgnoreCase(attorney.getBarStatus())?"selected":"" %>>Active</option>
											<option value="D" <%="D".equalsIgnoreCase(attorney.getBarStatus())?"selected":"" %>>Deceased</option>
											<option value="N" <%="N".equalsIgnoreCase(attorney.getBarStatus())?"selected":"" %>>Inactive</option>
											<option value="S" <%="S".equalsIgnoreCase(attorney.getBarStatus())?"selected":"" %>>Suspended</option>
										</select>
									</div>
								</div>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Prefix</strong></label>
										<select class="form-control" id="prefix" name="prefix" <%=disabled %>>
											<option value=""></option>
											<option value="MR." <%="MR.".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Mr.</option>
											<option value="MRS." <%="MRS.".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Mrs.</option>
											<option value="MS." <%="MS.".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Ms.</option>
											<option value="DR." <%="DR.".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Dr.</option>
											<option value="HON" <%="HON".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Honorable</option>
											<option value="JUDGE" <%="JUDGE".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Judge</option>
											<option value="LT. COL." <%="LT. COL.".equalsIgnoreCase(attorney.getPrefix())?"selected":"" %>>Lt. Col.</option>
										</select>
									</div>
								</div>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>First Name<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="firstName" name="firstName" value="<%=TextUtil.print(attorney.getFirstName()) %>" maxlength="30" <%=disabled %> required>
									</div>
								</div>
						
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Last Name<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="lastName" name="lastName" value="<%=TextUtil.print(attorney.getLastName()) %>" maxlength="30" <%=disabled %> required>
									</div>
								</div>
								
							</div>
							
							<div class='col-md-8'>
							
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Organization</strong></label>
										<input type="text" class="form-control" id="organization" name="organization" value="<%=TextUtil.print(attorney.getOrganization()) %>" maxlength="50" <%=disabled %>>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Address 1<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="address1" name="address1" value="<%=TextUtil.print(attorney.getAddress1()) %>" maxlength="40" <%=disabled %> required>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Address 2</strong></label>
										<input type="text" class="form-control" id="address2" name="address2" value="<%=TextUtil.print(attorney.getAddress2()) %>" maxlength="40" <%=disabled %>>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Address 3</strong></label>
										<input type="text" class="form-control" id="address3" name="address3" value="<%=TextUtil.print(attorney.getAddress3()) %>" maxlength="40" <%=disabled %>>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>City<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="city" name="city" value="<%=TextUtil.print(attorney.getCity()) %>" maxlength="25" <%=disabled %> required>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>State/Province<span class="text-danger">*</span></strong></label>
										<select class="form-control" id="state" name="state" <%=disabled %> required>
											<option value=""></option>
											<% for(StateBO st:states){ %>
											<option value="<%=st.getStateCode() %>" <%=st.getStateCode().equals(attorney.getStateCode())?"selected":"" %>><%=TextUtil.print(st.getStateCode())%> </option>
											<%} %>
										</select>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Postal Code<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="zipCode" name="zipCode" value="<%=TextUtil.print(attorney.getZipCode())%>" maxlength="10" <%=disabled %> required>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Country</strong></label>
										<select class="form-control" id="country" name="country" <%=disabled %>>
											<option value=""></option>
											<% for(CountryBO country:countries){ %>
												<option value="<%=country.getCountryName() %>" <%=country.getCountryName().equalsIgnoreCase(attorney.getCountry())?"selected":"" %>><%=TextUtil.print(country.getCountryName()) %></option>
											<%} %>
										</select>
									</div>
								</div>
								
							</div>
							
							<div class='col-md-8'>
							
								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Business Phone<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="busPhone" name="busPhone" value="<%=TextUtil.print(attorney.getBusPhone()) %>" maxlength="18" <%=disabled %> required>
									</div>
								</div>

								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Mobile Phone</strong></label>
										<input type="text" class="form-control" id="cellPhone" name="cellPhone" value="<%=TextUtil.print(attorney.getCellPhone()) %>" maxlength="13" <%=disabled %>>
									</div>
								</div>

								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Fax Number</strong></label>
										<input type="text" class="form-control" id="faxNum" name="faxNum" value="<%=TextUtil.print(attorney.getFaxNum()) %>" maxlength="13" <%=disabled %>>
									</div>
								</div>

								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Home Phone</strong></label>
										<input type="text" class="form-control" id="homePhone" name="homePhone" value="<%=TextUtil.print(attorney.getHomePhone()) %>" maxlength="13" <%=disabled %>>
									</div>
								</div>

								<div class="row mt-4">
									<div class='input-group col-md-24'>
										<label class="mr-2 col-md-10"><strong>Email Address<span class="text-danger">*</span></strong></label>
										<input type="text" class="form-control" id="email" name="email" value="<%=TextUtil.print(attorney.getEmailAddress()) %>" maxlength="255" <%=disabled %>>
									</div>
								</div>
								
							</div>
							
						</div>
						<span class="text-danger">* Required</span>
						
						<div class="row col-24">
						
							<div class="row col-12">
							
								<% if (hasRole) { %>
								
									<div class="mt-5 text-danger text-center">
										<strong>Email Address on non production servers will NOT be updated</strong>
									</div>
									<div class="mt-5 text-center">
										<strong>Coris Servers</strong>
									</div>
									<div class="row ml-2 mb-4">
										<div class="form-check form-check-inline">
											<input type="checkbox" class="form-check-input" name="productionDistrict" id="productionDistrict" value="" checked>
											<label class="form-check-label" for="productionDistrict">Production District</label>
										</div>
										<div class="form-check form-check-inline">
											<input type="checkbox" class="form-check-input" name = "productionJustice" id="productionJustice" value="" checked>
											<label class="form-check-label" for="productionJustice">Production Justice</label>
										</div>
										<div class="form-check form-check-inline text-danger" >
											<input type="checkbox" class="form-check-input" name="verify" id="verify" value="" checked>
											<label class="form-check-label" for="verify">Verify</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name="training" id="training" value="" checked>
											<label class="form-check-label" for="training">Training</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name="testDistrict" id="testDistrict" value="" checked>
											<label class="form-check-label" for="testDistrict">Test District</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name = "testJustice" id="testJustice" value="" checked>
											<label class="form-check-label" for="testJustice">Test Justice</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name="development" id="development" value="" checked>
											<label class="form-check-label" for="development">Development</label>
										</div>
									</div>
									<div class="mt-5 text-center">
										<strong>Care Servers (Future Release)</strong>
									</div>
									<div class="row ml-2 mb-4">
										<div class="form-check form-check-inline">
											<input type="checkbox" class="form-check-input" name = "productionCare" id="productionCare" value="" disabled unchecked>
											<label class="form-check-label" for="productionCare">Production Care</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name = "testCare" id="testCare" value="" disabled unchecked >
											<label class="form-check-label" for="testCare">Test Care</label>
										</div>
										<div class="form-check form-check-inline text-danger">
											<input type="checkbox" class="form-check-input" name = "developmentCare" id="developmentCare" value="" disabled unchecked>
											<label class="form-check-label" for="developmentJustice">Development Care</label>
										</div>
									</div>
								
								<% } %>
								
							</div>
							
							<div class="row offset-4 col-8">
								<div class="mt-5">
									<strong>Print Labels or Envelopes</strong>
									<br><span class="text-danger">For envelopes, select paper size in browser print settings.</span>
								</div>
								<div class='form-group col-md-24'>
									<div class="form-check form-radio-inline">
										<input type="radio" class="form-check-input" id="labelTemplate" name="labelTemplate" value="9x3.5" checked>
										<label class="form-check-label" for="inlineCheckbox1">Standard Envelope (9" x 3-1/2")</label>
									</div> 
									<div class="form-check form-radio-inline">
										<input type="radio" class="form-check-input" id="labelTemplate" name="labelTemplate" value="9x12">
										<label class="form-check-label" for="inlineCheckbox1">Legal Envelope (9" x 12")</label>
									</div> 
									<div class="form-check form-radio-inline">
										<input type="radio" class="form-check-input" id="labelTemplate" name="labelTemplate" value="3x10">
										<label class="form-check-label" for="labelTemplate">3 x 10 Labels (1" x 2-5/8")</label>
									</div>
									<div class="form-check form-radio-inline">
										<input type="radio" class="form-check-input" id="labelTemplate" name="labelTemplate" value="2x10">
										<label class="form-check-label" for="labelTemplate">2 x 10 Labels (1" x 4")</label>
									</div>
									<div class="form-check form-radio-inline">
										<input type="radio" class="form-check-input" id="labelTemplate" name="labelTemplate" value="2x5">
										<label class="form-check-label" for="labelTemplate">2 x 5 Labels (2" x 4")</label>
									</div> 
								</div>
							</div>
						
						</div>
							
						<div class="std-fixed bottom bg-light popcorn-ftr active">
							<div class='mb-1 col-24'>
								<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn" onclick="closePop();">Cancel</button>
								<%if(hasRole){ %>
									<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="saveBtn" onclick="validate($('#barNum').val(), $('#barState').val())">Save</button>
								<%} %>
								<% if(!"add".equals(action)){ %>
									<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="printBtn" onclick="printLabels();">Print Label or Envelope</button>
									<% if (hasRole) { %>
										<button type="button" class="btn btn-danger ml-2 mt-2 mr-2 float-left" id="deleteBtn" onclick="deleteAttorney(<%=attorney.getBarNum() %>, '<%=attorney.getBarState() %>');">Delete Attorney</button>
									<% } %>
								<%} %>
							</div>
						</div>

					</form>
						
				</div>
				
			</div>

    	</div>
    </main>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
	
			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
			
			<% if("add".equals(action)){ %>
				$('#barNum').focus();
			<% } %>
			
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function submitForm(barNum, barState){
			appUX.ajax.call("<%=urlCryptor.urlEncrypt(paramsSubmitForm) %>", 
				function (err, data, xhr) { 
					var jsonObj = JSON.parse(data);
					if(err){
						message = "Error: Changes were not saved.";
						appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
					}
					if (jsonObj && jsonObj.error) {
 						appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
					}
					if(jsonObj && jsonObj.success){
						appUX.pop.declare("Success", jsonObj.successMessage, 300, 'auto', appUX.pop.styles.SUCCESS);
					}
					var paramObj = {
						barNum: barNum,
						barState: barState,
						showDetails: "false"
					};
					appUX.pop.refreshCornFrame('<%=parentCornId%>', true, paramObj);
				}, 
				'POST', 
				'&barNum='+$("#barNum").val()+'&barState='+$("#barState").val()+'&'+$("#attorneyForm").serialize()
			);
		}
		
		function validate(barNum, barState){
			var message = "";
			var messageWarning = "";
			//reset the highlighting
			$('#barNum').removeClass("is-invalid is-valid"); //no red or green
			$('#barState').removeClass("is-invalid is-valid"); //no red or green
			$('#barStatus').removeClass("is-invalid is-valid"); //no red or green
			$('#firstName').removeClass("is-invalid is-valid"); //no red or green
			$('#lastName').removeClass("is-invalid is-valid"); //no red or green
			$('#address1').removeClass("is-invalid is-valid"); //no red or green
			$('#city').removeClass("is-invalid is-valid"); //no red or green
			$('#state').removeClass("is-invalid is-valid"); //no red or green
			$('#zipCode').removeClass("is-invalid is-valid"); //no red or green
			$('#busPhone').removeClass("is-invalid is-valid"); //no red or green
			if($('#barNum').val() == "" || $('#barNum').val() == null){
				message += "Bar Number is required.<br>";
				$('#barNum').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#barState').val() == "" || $('#barState').val() == null){
				message += "Bar State is required.<br>";
				$('#barState').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#barStatus').val() == "" || $('#barStatus').val() == null){
				message += "Bar Status is required.<br>";
				$('#barStatus').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#firstName').val() == "" || $('#firstName').val() == null){
				message += "First Name is required.<br>";
				$('#firstName').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#lastName').val() == "" || $('#lastName').val() == null){
				message += "Last Name is required.<br>";
				$('#lastName').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#address1').val() == "" || $('#address1').val() == null){
				message += "Address 1 is required.<br>";
				$('#address1').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#city').val() == "" || $('#city').val() == null){
				message += "City is required.<br>";
				$('#city').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#state').val() == "" || $('#state').val() == null){
				message += "State is required.<br>";
				$('#state').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#zipCode').val() == "" || $('#zipCode').val() == null){
				message += "Postal Code is required.<br>";
				$('#zipCode').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($('#busPhone').val() == "" || $('#busPhone').val() == null){
				message += "Business Phone is required.<br>";
				$('#busPhone').removeClass("is-valid").addClass("is-invalid"); //highlight field in red
			}
			if($("#productionDistrict").prop("checked") || $("#productionJustice").prop("checked")){
				if($('#email').val() == "" || $('#email').val() == null){
					messageWarning = "Email Address is a required field and should not be left blank. By continuing, email address will be updated (even if left blank) on production, training and verify servers, if selected. Do you wish to continue?";
				}
			}
			var dbServerValue = false;
			if($("#productionDistrict").prop("checked")){ dbServerValue = true; }
			if($("#productionJustice").prop("checked")){ dbServerValue = true; }
			if($("#verify").prop("checked")){ dbServerValue = true; }
			if($("#training").prop("checked")){ dbServerValue = true; }
			if($("#testDistrict").prop("checked")){ dbServerValue = true; }
			if($("#testJustice").prop("checked")){ dbServerValue = true; }
			if($("#development").prop("checked")){ dbServerValue = true; }
			if($("#productionCare").prop("checked")){ dbServerValue = true; }
			if($("#testCare").prop("checked")){ dbServerValue = true; }
			if($("#developmentCare").prop("checked")){ dbServerValue = true; }
			if(!dbServerValue){
				message += "At least one database needs to be selected.";
			}
			if(message != "" || messageWarning != ""){
				if(message != ""){
					appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				}else if(messageWarning != ""){
					var corn = appUX.pop.confirm("Warning", messageWarning, "Yes", "No", 400, 'auto', appUX.pop.styles.WARNING, function(action){
						if(action){
							submitForm(barNum, barState);
							corn.close();
						}else{
							corn.close();
						}
					});	
				}
			}else{
				submitForm(barNum, barState);
			}
		}
		
		function printLabels(){
			var cornId = "printAttnyLabels";
			var title = "Print Attorney Labels";
			 var radioValue = $("input[name='labelTemplate']:checked").val();
			var url = "jsp/corisAttorneyPrintLabels.jsp?courtType=<%=courtType%>&barNum=<%=attorney.getBarNum()%>&barState=<%=attorney.getBarState()%>&labelTemplate=" + radioValue;
			if(radioValue != '9x12' && radioValue != '9x3.5'){
				appUX.pop.modal(cornId, title, url, 400, 500, appUX.pop.styles.LIGHT);
			}else {
				url = "/CorisWEB/CorisAttorneyDetailsServlet?mode=print&labelTemplate=" + radioValue +
							"&courtType=<%=courtType%>&barNum=<%=attorney.getBarNum()%>&barState=<%=attorney.getBarState()%>"
				window.open(url,'Envelope', 600, 1100);
			}
		}
		
		function deleteAttorney(barNum, barState){
			var corn = appUX.pop.confirm("Danger", "Deleting an attorney cannot be undone. Do you wish to delete attorney?<br>Bar Number: "+barNum+"<br>Bar State: "+barState, "Yes, Delete Attorney", "No", 400, 'auto', appUX.pop.styles.DANGER, function(action){
				if(action){
					corn.close();
					appUX.ajax.call("<%=urlCryptor.urlEncrypt(paramsDeleteAttorney) %>", 
						function (err, data, xhr) {
							var jsonObj = JSON.parse(data);
							if(err){
								message = "Error: Changes were not saved.";
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}
							if (jsonObj && jsonObj.error) {
 								appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							} 
							if (jsonObj && jsonObj.success) {
								appUX.pop.declare("Success", jsonObj.successMessage, 300, 'auto', appUX.pop.styles.SUCCESS);
							}
							var paramObj = {
								barNum: barNum,
								barState: barState,
								showDetails: "false"
							};
							appUX.pop.refreshCornFrame('<%=parentCornId%>', true, paramObj);
						}, 
						'POST', 
						'&barNum='+$("#barNum").val()+'&barState='+$("#barState").val()+'&'+$("#attorneyForm").serialize()
					);					
				}else{
					corn.close();
				}
			});				
		}
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	</script>
</body>
</html>
<%

%>