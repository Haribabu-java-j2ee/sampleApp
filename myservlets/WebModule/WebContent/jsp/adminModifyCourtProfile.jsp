<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.county.CountyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	String locnCode = TextUtil.getParamAsString(request, "locnCode");
	ProfileBO profileBO = (ProfileBO) request.getAttribute("profile");
	
	List<CountyBO> countyCodes = (List<CountyBO>) request.getAttribute("countyCodes");
	List<StateBO> stateCodes = (List<StateBO>) request.getAttribute("stateCodes");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Modify Court Profile</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<!-- scripts -->
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<!-- this is used for the popcorn window -->
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script>

	$(document).ready(function(){
		<% // make sure the form focus is the first form field %>
		$("#title").focus();
	});
	
	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function refresh() {
		parent.showProfiles("<%= courtType %>");
	}
	
	function courtDefaults() {
		parent.showCourtDefaults("<%= locnCode %>", "<%= courtType %>");
	}
	
	function updateForm(court, locnCode, courtTitle, address1, address2, city, stateCode, zipCode, countyCode, emailAddress, drLicPrecinct, generalPhone, criminalPhone, civilPhone, juryPhone, interpreterPhone, debtCollPhone, adaName, adaAddress, adaPhone) {
		
		$('#locnCode').removeClass("is-invalid");
 		$('#locnCode').addClass("is-valid");
		
 		$('#court').removeClass("is-invalid");
 		$('#court').addClass("is-valid");
 		
 		$('#courtTitle').removeClass("is-invalid");
 		$('#courtTitle').addClass("is-valid");
 		
 		$('#emailAddress').removeClass("is-invalid");
 		$('#emailAddress').addClass("is-valid");
 		
 		$('#drLicPrecinct').removeClass("is-invalid");
 		$('#drLicPrecinct').addClass("is-valid");
 		
 		var locnCodeValid = true;
		if (locnCode == "") { locnCodeValid = false; } // can't be an empty string
		if (locnCode == null) { locnCodeValid = false; } // must contain a value
		if (locnCode.length > 4) { locnCodeValid = false; } // must be 1 chars long or shorter
		
		var courtValid = true;
		if (court == "") { courtValid = false; } // can't be an empty string
		if (court == null) { courtValid = false; } // must contain a value
		if (court.length > 1) { courtValid = false; } // must be 1 chars long or shorter
		
		var courtTitleValid = true;
		if (courtTitle == "") { courtTitleValid = false; } // can't be an empty string
		if (courtTitle == null) { courtTitleValid = false; } // must contain a value
		if (courtTitle.length > 30) { courtTitleValid = false; } // must be 1 chars long or shorter

		var emailAddressValid = true;
		if (emailAddress == "") { emailAddressValid = false; } // can't be an empty string
		if (emailAddress == null) { emailAddressValid = false; } // must contain a value
		if (emailAddress.length > 30) { emailAddressValid = false; } // must be 1 chars long or shorter

		var drLicPrecinctValid = true;
		if (drLicPrecinct == "") { drLicPrecinctValid = false; } // can't be an empty string
		if (drLicPrecinct == null) { drLicPrecinctValid = false; } // must contain a value
		if (drLicPrecinct.length > 2) { drLicPrecinctValid = false; } // must be 1 chars long or shorter

		// if (description.search(/[^a-zA-Z ]+/) != -1) { validatedText = false; } // must be only chars/spaces
		
		if (courtValid && locnCodeValid && courtTitleValid && emailAddressValid && drLicPrecinctValid) {
			
		   	appUX.ajax.call('/CorisWEB/AdminCourtProfileServlet?mode=update&action=<%= mode %>&courtType=<%= courtType %>'  
		   	+ '&court=' + court 
		   	+ '&locnCode=' + locnCode 
		   	+ '&courtTitle=' + courtTitle 
		   	+ '&address1=' + address1 
		   	+ '&address2=' + address2 
		   	+ '&city=' + city
		   	+ '&stateCode=' + stateCode
		   	+ '&zipCode=' + zipCode
		   	+ '&countyCode=' + countyCode
		   	+ '&emailAddress=' + emailAddress
		   	+ '&drLicPrecinct=' + drLicPrecinct
		   	+ '&generalPhone=' + generalPhone
		   	+ '&criminalPhone=' + criminalPhone
		   	+ '&civilPhone=' + civilPhone
		   	+ '&juryPhone=' + juryPhone
		   	+ '&interpreterPhone=' + interpreterPhone
		   	+ '&debtCollPhone=' + debtCollPhone
		   	+ '&adaName=' + adaName
		   	+ '&adaAddress=' + adaAddress
		   	+ '&adaPhone=' + adaPhone
		   	, function (err, data, xhr) { refresh(); closePop(); });
		   	
		} else {
			// highlight the field(s) in red and display message
			
			if (!locnCodeValid) {
				$('#locnCode').removeClass("is-valid");
				$('#locnCode').addClass("is-invalid");
			}
			
			if (!courtValid) {
				$('#court').removeClass("is-valid");
				$('#court').addClass("is-invalid");
			}
			
			if (!courtTitleValid) {
				$('#courtTitle').removeClass("is-valid");
				$('#courtTitle').addClass("is-invalid");
			}
			
			if (!emailAddressValid) {
				$('#emailAddress').removeClass("is-valid");
				$('#emailAddress').addClass("is-invalid");
			}
			
			if (!drLicPrecinctValid) {
				$('#drLicPrecinct').removeClass("is-valid");
				$('#drLicPrecinct').addClass("is-invalid");
			}
		}
	}
	
</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<% 
					String disabled = "edit".equals(mode) ? "disabled" : ""; 
				%>
				<div class="input-group m-4">
					<label for="locnCode" style="width: 35%; text-align: right; padding-right: 3px;">Location <span class="text-danger">*</span></label>
					<input class="form-control" id="locnCode" name="locnCode" <%= disabled %> autofocus value="<%= profileBO != null ? TextUtil.print(profileBO.getLocnCode()) : "" %>" \>
					<div class="invalid-feedback">Location is required and must be 4 characters.</div>
				</div>
				<div class="input-group m-4">
					<label for="court" style="width: 35%; text-align: right; padding-right: 3px;">Type <span class="text-danger">*</span></label>
					<input class="form-control" id="court" name="court" <%= disabled %> value="<%= profileBO != null ? TextUtil.print(profileBO.getCourtType()) : "" %>" \>
					<div class="invalid-feedback">Type is required and must be 1 character.</div>
				</div>
				<div class="input-group m-4">
					<label for="courtTitle" style="width: 35%; text-align: right; padding-right: 3px;">Title <span class="text-danger">*</span></label>
					<input class="form-control" id="courtTitle" name="courtTitle" value="<%= profileBO != null ? TextUtil.print(profileBO.getCourtTitle()) : "" %>" \>
					<div class="invalid-feedback">Title is required and must be less than 30 characters.</div>
				</div>
				<div class="input-group m-4">
					<label for="address1" style="width: 35%; text-align: right; padding-right: 3px;">Address 1</label>
					<input class="form-control" id="address1" name="address1" value="<%= profileBO != null ? TextUtil.print(profileBO.getAddress1()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="address2" style="width: 35%; text-align: right; padding-right: 3px;">Address 2</label>
					<input class="form-control" id="address2" name="address2" value="<%= profileBO != null ? TextUtil.print(profileBO.getAddress2()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="city" style="width: 35%; text-align: right; padding-right: 3px;">City</label>
					<input class="form-control" id="city" name="city" value="<%= profileBO != null ? TextUtil.print(profileBO.getCity()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="stateCode" style="width: 35%; text-align: right; padding-right: 3px;">State</label>
					<select class="form-control" id="stateCode" name="stateCode">
						<% 
							String selectedState = profileBO != null ? profileBO.getStateCode() : "Y"; 
							for (StateBO stateCode : stateCodes) {
								String state = stateCode.getStateCode();
						%>
								<option value="<%= state %>" <%= state.equals(selectedState) ? "selected" : "" %>>(<%= stateCode.getStateCode() %>) <%= stateCode.getName() %></option>
						<%  
							} 
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="zipCode" style="width: 35%; text-align: right; padding-right: 3px;">ZIP Code</label>
					<input class="form-control" id="zipCode" name="zipCode" value="<%= profileBO != null ? TextUtil.print(profileBO.getZipCode()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="emailAddress" style="width: 35%; text-align: right; padding-right: 3px;">Email Address <span class="text-danger">*</span></label>
					<input class="form-control" id="emailAddress" name="emailAddress" value="<%= profileBO != null ? TextUtil.print(profileBO.getEmailAddress()) : "" %>" disabled>
					<div class="invalid-feedback">Email Address is required and must be less than 255 characters.</div>
				</div>
				<div class="input-group m-4">
					<label for="cntyCode" style="width: 35%; text-align: right; padding-right: 3px;">County Code</label>
					<select class="form-control" id="cntyCode" name="cntyCode">
						<% 
							String selectedCty = profileBO != null ? profileBO.getCntyCode() : "Y"; 
							for (CountyBO countyCode : countyCodes) {
								String county = countyCode.getCntyCode();
						%>
								<option value="<%= county %>" <%= county.equals(selectedCty) ? "selected" : "" %>>(<%= countyCode.getCntyCode() %>) <%= countyCode.getName() %></option>
						<%  
							} 
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="drLicPrecinct" style="width: 35%; text-align: right; padding-right: 3px;">Driver's License Precinct <span class="text-danger">*</span></label>
					<input class="form-control" id="drLicPrecinct" name="drLicPrecinct" value="<%= profileBO != null ? TextUtil.print(profileBO.getDrLicPrecinct()) : "" %>" \>
					<div class="invalid-feedback">Driver's License Precinct is required and must be less than 2 characters.</div>
				</div>
				<div class="input-group m-4">
					<label for="generalPhone" style="width: 35%; text-align: right; padding-right: 3px;">General Phone</label>
					<input class="form-control" id="generalPhone" name="generalPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getGeneralPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="criminalPhone" style="width: 35%; text-align: right; padding-right: 3px;">Criminal Phone</label>
					<input class="form-control" id="criminalPhone" name="criminalPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getCriminalPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="civilPhone" style="width: 35%; text-align: right; padding-right: 3px;">Civil Phone</label>
					<input class="form-control" id="civilPhone" name="civilPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getCivilPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="juryPhone" style="width: 35%; text-align: right; padding-right: 3px;">Jury Phone</label>
					<input class="form-control" id="juryPhone" name="juryPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getJuryPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="interpreterPhone" style="width: 35%; text-align: right; padding-right: 3px;">Interpreter Phone</label>
					<input class="form-control" id="interpreterPhone" name="interpreterPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getInterpreterPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="debtCollPhone" style="width: 35%; text-align: right; padding-right: 3px;">Debt Collection Phone</label>
					<input class="form-control" id="debtCollPhone" name="debtCollPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getDebtcollPhone()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="adaName" style="width: 35%; text-align: right; padding-right: 3px;">ADA Name</label>
					<input class="form-control" id="adaName" name="adaName" value="<%= profileBO != null ? TextUtil.print(profileBO.getAdaName()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="adaAddress" style="width: 35%; text-align: right; padding-right: 3px;">ADA Address</label>
					<input class="form-control" id="adaAddress" name="adaadaAddressName" value="<%= profileBO != null ? TextUtil.print(profileBO.getAdaAddress()) : "" %>" \>
				</div>
				<div class="input-group m-4">
					<label for="adaPhone" style="width: 35%; text-align: right; padding-right: 3px;">ADA Phone Number</label>
					<input class="form-control" id="adaPhone" name="adaPhone" value="<%= profileBO != null ? TextUtil.print(profileBO.getAdaPhone()) : "" %>" \>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Update" onclick="updateForm($('#court').val(), $('#locnCode').val(), $('#courtTitle').val(), $('#address1').val(), $('#address2').val(), $('#city').val(), $('#stateCode').val(), $('#zipCode').val(), $('#cntyCode').val(), $('#emailAddress').val(), $('#drLicPrecinct').val(), $('#generalPhone').val(), $('#criminalPhone').val(), $('#civilPhone').val(), $('#juryPhone').val(), $('#interpreterPhone').val(), $('#debtCollPhone').val(), $('#adaName').val(), $('#adaAddress').val(), $('#adaPhone').val());">Update</button>
					<% if ("edit".equals(mode)) { %>
						<button type="button" class="btn btn-primary btn-sm float-left ml-2" title="Court Defaults" onclick="javascript:courtDefaults();">Court Defaults</button>
					<% } %>
				</div>
				<div><br><br></div>
			</form>
		</div>
	</main>
<%
	// cleanup
	mode = null;
%>
</body>
</html>
