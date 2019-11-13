<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%
//this comes from either a jsp login screen or is passed in as a URL query param from Coris power builder
String logName = URLEncryption.getParamAsString(request, "logName", (String) request.getParameter("logName"));
if (TextUtil.isEmpty(logName)){
	logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
}

String theUrl = "logName="+logName;

//get the courtType to select the dropdown, if it has been set
String courtType = URLEncryption.getParamAsString(request, "courtType", (String) request.getParameter("courtType"));
if (TextUtil.isEmpty(courtType)){
	courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE);
}

//get the caseNum and locnCode to populate the inputs, if they have been set
String caseNum = URLEncryption.getParamAsString(request, "caseNum", (String) request.getParameter("caseNum"));
String locnCode = URLEncryption.getParamAsString(request, "locnCode", (String) request.getParameter("locnCode"));

//encrypt the URL
URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");
%>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Coris Search</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body style="height: 100vh;">
<div class="container-fluid">

<%-- 	<%// top nav bar is fixed to the top of the screen %> --%>
<!-- 	<div id="searchForm" class="fixed-top"> -->
<%-- 		<%@include file="corisHeader.jsp" %> --%>
<!-- 	</div> -->

	<div class="card m-2">
		<div class="card-header text-dark">
			<strong>Case Summary Search</strong>
		</div>
		<div class="card-body">
			<form id="lookupCases" name="lookupCases" novalidate>
			<div class="form-row mt-4">
				<div class="form-group col-md offset-lg-2 col-lg-6">
					<div class="row align-items-center">
						<label class="col-sm-8 col-md-10 control-label text-sm-right">Case Number&nbsp;<span class="text-danger">!</span></label>
						<div class="col-sm-12 col-md-13">
							<input type="text" class="form-control" id="caseNum" name="caseNum" placeholder="Enter case number" maxlength="9" required autofocus>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
								<span class="float-right">Case Number or Last Name is required. Case Number must be 9 numbers.</span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-md col-lg-8">
					<div class="row align-items-center">
						<label class="col-sm-8 col-md-10 control-label text-sm-right">Location</label>
						<div class="col-sm-12 col-md-13">
							<select class="form-control" id="locnCode" name="locnCode">
							</select>
						</div>
					</div>
				</div>
				<div class="form-group col-md col-lg-8">
					<div class="row align-items-center">
						<label class="col-sm-8 col-md-10 control-label text-sm-right">Court Type</label>
						<div class="col-sm-12 col-md-13">
							<select class="form-control" id="courtType" name="courtType">
								<option value="D">District</option>
								<option value="J">Justice</option>
								<option value="both">District and Justice</option>
							</select>
						</div>
					</div>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md offset-lg-2 col-lg-6">
					<div class="row align-items-center">
						<label class="col-sm-8 col-md-10 control-label text-sm-right">Last Name&nbsp;<span class="text-danger">!</span></label>
						<div class="col-sm-12 col-md-13">
							<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" maxlength="30" required>
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
								<span class="float-right">Last Name or Case Number is required. Last name must be 2 to 30 characters. One asterisk (*) may be used as a wildcard.</span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-md col-lg-8">
					<div class="row align-items-center">
						<label class="col-sm-8 col-md-10 control-label text-sm-right">First Name</label>
						<div class="col-sm-12 col-md-13">
							<input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" maxlength="20">
							<div class="invalid-feedback" style="margin:0; padding:0; padding-bottom: 2px;">
								<span class="float-right">First Name must be 2 to 20 characters. One asterisk (*) may be used as a wildcard.</span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-md col-lg-8">
				</div>
			</div>
			<div>
				<div class="float-left mt-6 mb-0"><span class="text-danger">!</span> Case Number or Last Name is required.</div>
				<div class="float-right">
					<button type="button" id="clearBtn" name="clearBtn" class="btn btn-secondary ml-2 mt-2 mr-2 float-right">Clear</button>
					<button type="submit" id="searchBtn" name="searchBtn" class="btn btn-primary ml-2 mt-2 mr-2 float-right">Search</button>
				</div>
			</div>
			</form>
		</div>
		<div class="card-header card-footer text-dark">
			<strong>Search Results</strong>
		</div>
		<div class="card-body">
			<%//search results are loaded dynamically when the search() function is called %>
			<div id="searchResults">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>

<%//scripts that need to be created after the page has loaded go here %>
$(document).ready(function(){
	<%//if courtType is set, then select the correct option in the dropdown %>
	<% if(!"".equals(courtType)) { %>
		$('#courtType').val('<%=courtType %>');
	<% } %>
	
	<%//if locnCode is set, then select the correct option in the dropdown %>
	<% if(!"".equals(locnCode)) { %>
		$('#locnCode').val('<%=locnCode %>');
	<% } %>
	
	<%//displays a message to the user when the page is first loaded %>
	$("#searchResults").html("Please enter search criteria to display results.");

	<%//fix for the content being hidden by the fixed-top navbar/search area on different sized screens %>
	$(document.body).css('padding-top', $('#searchForm').height() + 2);
	$(window).resize(function(){
		$(document.body).css('padding-top', $('#searchForm').height() + 2);
	});
	
	<%//submits the search form when the search button is clicked or when the enter key is pressed within the form %>
	$('#searchBtn').on("click", function( event ){
		search($('#caseNum').val(), $('#locnCode').val(), $('#courtType').val(), $('#firstName').val(), $('#lastName').val(), '<%=urlCryptor.urlEncrypt(theUrl) %>');
		event.preventDefault();
	});
	$( "#lookupCases" ).submit(function( event ) {
		search($('#caseNum').val(), $('#locnCode').val(), $('#courtType').val(), $('#firstName').val(), $('#lastName').val(), '<%=urlCryptor.urlEncrypt(theUrl) %>');
		event.preventDefault();
	});
	
	<%//re-populates the locations, when the courtType dropdown is changed %>
	$('#courtType').on("change", function(){
		loadLocations($('#courtType').val(), '');
	});
	
	<%//if location dropdown is changed to "both" (All locations), then set Court Type to "District and Justice", too %>
	$('#locnCode').on("change", function(){
		if($('#locnCode').val() == ""){
			$('#courtType').val("both");
		}
		var str = $('#locnCode option:selected').text();
		if (str.indexOf("District") >= 0) {
			$('#courtType').val("D");
		} else if (str.indexOf("Justice") >= 0) {
			$('#courtType').val("J");
		} else {
			$('#courtType').val("both");
		}
		loadLocations($('#courtType').val(), $('#locnCode').val());
	});
	
	<%//clears the search form and search results when the clear button is clicked %>
	$('#clearBtn').on("click", function(){
		clearForm();
	});
	
	<%//make sure the form focus is the first form field %>
	$("#caseNum").focus();
	
	<%//if caseNum and locnCode and courtType are set, then populate the inputs and do a search on them %>
	<% if(!TextUtil.isEmpty(caseNum)) { %>
		$('#caseNum').val('<%=caseNum %>');
	<% } %>
	<% if(!TextUtil.isEmpty(courtType)) { %>
		$('#courtType').val('<%=courtType %>');
	<% } %>
	<% if(!TextUtil.isEmpty(caseNum) && !TextUtil.isEmpty(locnCode) && !TextUtil.isEmpty(courtType)) { %>
		//NOTE: because of the way the location list is populated, we have to pass in the locnCode from java rather than from the dropdown list
		search($('#caseNum').val(), '<%=locnCode %>', $('#courtType').val(), $('#firstName').val(), $('#lastName').val(), '<%=urlCryptor.urlEncrypt(theUrl) %>');
	<% } %>
	
	<%//get the location dropdown list for the logged in person, based on the personnel table %>
	getLocationLists();

});

var newData;
function getLocationLists(){
	url = '<%=urlCryptor.urlEncrypt(theUrl) %>';
	appUX.ajax.call(url, 
		function (err, data, xhr) {
			newData = data;
			loadLocations('<%=courtType%>', '<%=locnCode%>')
		}, 
		'POST', 
		'mode=getLocationsList'
	); 
}

//loads the location dropdown
function loadLocations(courtType, locnCode) {
	var jsonObj = JSON.parse(newData);
	selected = false;
	var optionHTML = "";
	var $locnCodeList = $("#locnCode");
	$locnCodeList.empty();
	if(locnCode == null || locnCode == ""){
		if(courtType == "both"){
			$locnCodeList.append('<option value="" selected>All locations</option>');
			selected = true;
		}else{
			$locnCodeList.append('<option value="" selected>All locations</option>');
		}
	}else{
		$locnCodeList.append('<option value="">All locations</option>');
	}
	for (var i = 0; i < jsonObj.personnelLocationList.length; i++) {
		locnCodeDescr = jsonObj.personnelLocationList[i].locnDescr;
		locnCourtType = jsonObj.personnelLocationList[i].courtType;
		locnCodeValue = jsonObj.personnelLocationList[i].locnCode;
		defltLogin = jsonObj.personnelLocationList[i].defltLogin;
		selected = false;
		if(locnCourtType == courtType || courtType == "both"){
			optionHTML = "<option value='"+locnCodeValue+"'"; 
			if(
				(
					locnCodeValue == locnCode
					&& locnCourtType == courtType
				) 
				&& selected == false
			) {
				selected = true;
				optionHTML += " selected";
			}
			
			optionHTML += ">"+locnCodeDescr+"</option>";
			$locnCodeList.append(optionHTML);
		}
	}
}

//validation and submit of search form
function search(caseNum, locnCode, courtType, firstName, lastName, encryptedUrl){
	//clear out the previous search results
	$("#searchResults").html("");

	//do some validation of caseNum
	var validatedCaseNum = true;
	if(caseNum.length < 9 || caseNum.length > 9) { validatedCaseNum = false; } //must be 9 digits long
	if(!$.isNumeric(caseNum)) { validatedCaseNum = false; } //must be only numeric digits, even though it's a string

	//do some validation of firstName
	var validatedFirstName = true;
	if(firstName != "" && firstName != null) { 
		if(firstName.length < 2 || firstName.length > 20) { validatedFirstName = false; } //must be 2 to 20 characters long
	}

	//do some validation of lastName
	var validatedLastName = true;
	if(lastName != "" && lastName != null) { 
		if(lastName.length < 2 || lastName.length > 30) { validatedLastName = false; } //must be 2 to 30 characters long
	}

	//do validation of required fields (caseNum and lastName)
	if(caseNum == "" && lastName == "") { validatedCaseNum = false; validatedLastName = false; } //at least one required field must have a value
	if(caseNum == null && lastName == null) { validatedCaseNum = false; validatedLastName = false; } //at least one required field must have a value
	if((caseNum == "" || caseNum == null) && validatedLastName) { validatedCaseNum = true; } //reset validatedCaseNum, if it's empty or null
	if((lastName == "" || lastName == null) && validatedCaseNum) { validatedLastName = true; } //reset validatedLastName, if it's empty or null

	if(validatedCaseNum && validatedFirstName && validatedLastName) {

		//display loading spinner
		$("#searchResults").html(
			'<div style="width: 100%; text-align: center; padding: 20px;">'
				+ '<span style="padding: 5px; font-size: 16px; border: 1px solid rgba(0,0,0,.25); color: rgba(0,0,0,.5);">'
					+ '<span class="fa fa-spinner fa-spin"></span>'
					+ ' Loading'
				+ '</span>'
			+ '</div>'
		);
	
		//populate the table results (same as doing a submit of the form but we don't want to reload the entire page again)
		appUX.ajax.call(encryptedUrl, 
			function (err, data, xhr) { 
				$("#searchResults").html(data); 
			}, 
			'POST', 
			'locnCode='+locnCode+'&caseNum='+caseNum+'&courtType='+courtType+'&firstName='+firstName+'&lastName='+lastName+'&mode=getCasesList'
		); 
		
	} else {
		$("#searchResults").html("Please enter search criteria to display results.");
	}
	
	//highlight the fields in red (is-invalid) or green (is-valid) or remove the highlight
	if(!validatedCaseNum) { 
		$('#caseNum').removeClass("is-valid").addClass("is-invalid"); //red
	} else if(validatedCaseNum) { 
		if(caseNum == "" || caseNum == null){
			$('#caseNum').removeClass("is-invalid is-valid"); //no red or green
		}else{
			$('#caseNum').removeClass("is-invalid").addClass("is-valid"); //green
		}
	}
	if(!validatedFirstName) { 
		$('#firstName').removeClass("is-valid").addClass("is-invalid"); //red
	} else if(validatedFirstName) { 
		if(firstName == "" || firstName == null){
			$('#firstName').removeClass("is-invalid is-valid"); //no red or green
		}else{
			$('#firstName').removeClass("is-invalid").addClass("is-valid"); //green
		}
	}
	if(!validatedLastName) { 
		$('#lastName').removeClass("is-valid").addClass("is-invalid"); //red
	} else if(validatedLastName) { 
		if(lastName == "" || lastName == null){
			$('#lastName').removeClass("is-invalid is-valid"); //no red or green
		}else{
			$('#lastName').removeClass("is-invalid").addClass("is-valid"); //green
		}
	}

}

//clears search form and results
function clearForm(){
	$('#caseNum').val("");
	$('#caseNum').removeClass("is-valid is-invalid");
	$('#locnCode').val("");
	$('#locnCode').removeClass("is-valid is-invalid");
	$('#firstName').val("");
	$('#firstName').removeClass("is-valid is-invalid");
	$('#lastName').val("");
	$('#lastName').removeClass("is-valid is-invalid");
	$("#searchResults").html("Please enter search criteria to display results.");
	$(document).attr("title", "Coris Search");
}

</script>
</body>
</html>
