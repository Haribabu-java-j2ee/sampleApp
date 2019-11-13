<!DOCTYPE html>
<%@page import="com.sun.xml.internal.bind.v2.runtime.Location"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO" %>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO" %>
<%@page import="gov.utcourts.coriscommon.dto.CategoryDTO" %>
<%@page import="gov.utcourts.corisweb.util.URLEncryption" %>
<%@page import="java.util.List"%>
<%
	String logName = (String) request.getSession().getAttribute(SessionVariables.LOG_NAME);
	String errorMessage = (String) request.getAttribute("errorMessage");
	List<LocationBO> locationList = (List<LocationBO>) request.getAttribute("locationList");
	String defaultLocnCode = (String) request.getAttribute("defaultLocnCode");
	String defaultCourtType = (String) request.getAttribute("defaultCourtType");
	List<CaseTypeBO> caseTypeList = (List<CaseTypeBO>) request.getAttribute("caseTypeList");
	List<CategoryDTO> categoryList = (List<CategoryDTO>) request.getAttribute("categoryList");
	URLEncryption pdfCryptor = new URLEncryption("/CorisWEB/CorisJudgeCommRotationServlet");
%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Judge Assignment</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<script type="text/javascript" src="/CorisWEB/js/coris.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/datetime-moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function() {
			<% if (!TextUtil.isEmpty(errorMessage)) { %> 
				dispayError('<%= errorMessage %>');
			<% } %>	
			
			changeCourtLocation();
			changeCaseType();
		});

		function displayError(errorMessage) {
			// $('#errorMessage').html('<span style="color:red;">' + errorMessage + '</span>');
			appUX.pop.alert("" + errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
		}
		
		function displayMessage(title, message) {
			appUX.pop.declare("" + title, "" + message, 300, 'auto', appUX.pop.styles.SUCCESS);
		}
		
		function closePop() {
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function clearCaseTypeSelection() {
			$("#caseType").val('');
		}
		
		function clearCategorySelection() {
			$("#category").val('');
		}
		
		function clearSelections() {
			$("#judgeFrom").val('');
			$("#judgeTo").val('');
			$("#caseType").val('');
			$("#category").val('');
			$("#percentage").val('0');
			
	     	$('#reassignDisposedCases').find('span').removeClass(' checked');
     		$('#reassignDisposedCases').prop('checked', false);
   
			$("#totalEligible").val('');
			$("#totalReassignable").val('');
			$("#totalRemaining").val('');
		}
		
		function process() {
			var courtLocn = $('#courtLocn').val();
			var judgeComm = $("input[name='judgeComm']:checked").val();
			var judgeFrom = $("#judgeFrom").val();
			var judgeTo = $("#judgeTo").val();
			var caseType = $("#caseType").val();
			var category = $("#category").val();
			var percentage = $("#percentage").val();
			var reassignDisposedCases = $("#reassignDisposedCases:checked").val();
			var reassign = "I" == reassignDisposedCases ? "Y" : "N";
			var rotateAmount = $("#totalReassignable").val();
			
			if (isValid(judgeFrom, judgeTo, percentage)) {
				var totalEligible = $("#totalEligible").val();
				if (totalEligible == '' || totalEligible == '0' || totalEligible == 'Calculating...') {
					displayError("Please press 'Calc' before submitting");
					valid = false;
				} else {
					
					var encryptedUrl = '<%= pdfCryptor.urlEncrypt("mode=process") %>&courtLocn=' + courtLocn + '&judgeComm=' + judgeComm + "&judgeFrom=" + judgeFrom + "&judgeTo=" + judgeTo + "&caseType=" + caseType + "&category=" + category + "&percentage=" + percentage + "&reassignDisposedCases=" + reassign + "&rotateAmount=" + rotateAmount;
	
					// loading spinner html
					var htmlDisplay = 
						'<div style="width: 100%; text-align: center; padding: 20px;">'
							+ '<span style="padding: 5px; font-size: 16px; border: 1px solid rgba(0,0,0,.25); color: rgba(0,0,0,.5);">'
								+ '<span class="fa fa-spinner fa-spin"></span>'
								+ ' Loading'
							+ '</span>'
						+ '</div>'
					;
					
					// open a new window
					var pdf = window.open("", "PDF", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
					if (pdf) {
						pdf.document.write(htmlDisplay); // display loading spinner
						pdf.location.href = encryptedUrl; // replace the window with the contents of the PDF
					} else {
						var message = "To view this PDF, please allow popups in the browser.";
						appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
					}
			
				}
			}
		}
		
		function calc() {
			var courtLocn = $('#courtLocn').val();
			var judgeComm = $("input[name='judgeComm']:checked").val();
			var judgeFrom = $("#judgeFrom").val();
			var judgeTo = $("#judgeTo").val();
			var caseType = $("#caseType").val();
			var category = $("#category").val();
			var percentage = $("#percentage").val();
			var reassignDisposedCases = $("#reassignDisposedCases:checked").val();
			var reassign = "I" == reassignDisposedCases ? "true" : "false";
			
			if (isValid(judgeFrom, judgeTo, percentage)) {
			
				$("#totalEligible").val('Calculating...');
				$("#totalReassignable").val('Calculating...');
				$("#totalRemaining").val('Calculating...');
			
				appUX.ajax.call("/CorisWEB/CorisJudgeCommRotationServlet", 
					function (err, data, xhr) { 
					
						var jsonObj = JSON.parse(data);
						$("#totalEligible").val(jsonObj.totalEligible);
						$("#totalReassignable").val(jsonObj.totalReassignable);
						$("#totalRemaining").val(jsonObj.totalRemaining);
							
					}, 
					'POST', 
					'mode=calc&courtLocn=' + courtLocn + '&judgeComm=' + judgeComm + "&judgeFrom=" + judgeFrom + "&caseType=" + caseType + "&category=" + category + "&percentage=" + percentage + "&reassignDisposedCases=" + reassign
				); 
			}
		}
		
		function isValid(judgeFrom, judgeTo, percentage) {
			var valid = true;

			if (judgeTo == '') {
				displayError("Please select a to judge");
				valid = false;
			} else if (judgeFrom == judgeTo) {
				displayError("The from and to judge are the same");
				valid = false;	
			} else if (percentage == '' || percentage == '0') {
				displayError("Percentage must be between 1% and 100%");
				valid = false;	
			}
			 
			return valid;
		}
		
		function changeCourtLocation() {
		
			$("#totalEligible").val('');
			$("#totalReassignable").val('');
			$("#totalRemaining").val('');
				
			var courtLocn = $('#courtLocn').val();
			var judgeComm = $("input[name='judgeComm']:checked").val();
			
			appUX.ajax.call("/CorisWEB/CorisJudgeCommRotationServlet", 
				function (err, data, xhr) { 

					var $judgeFromDropdown = $("#judgeFrom");
					$judgeFromDropdown.empty();
					$judgeFromDropdown.append("<option value=''></option>");
						
					var $judgeToDropdown = $("#judgeTo");
					$judgeToDropdown.empty();
					$judgeToDropdown.append("<option value=''></option>");
					
					var jsonObj = JSON.parse(data);
					for (var i=0; i < jsonObj.judgeCommList.length; i++) {
						var lastName = jsonObj.judgeCommList[i].lastName;
						var firstName = jsonObj.judgeCommList[i].firstName;
						var useridSrl = jsonObj.judgeCommList[i].useridSrl;
						$judgeFromDropdown.append("<option value='" + useridSrl + "'>" + lastName + ", " + firstName + "</option>");
						$judgeToDropdown.append("<option value='" + useridSrl + "'>" + lastName + ", " + firstName + "</option>");
					}
					
				}, 
				'POST', 
				'mode=changeLocation&courtLocn=' + courtLocn + '&judgeComm=' + judgeComm
			); 
			
		}
		
		function changeJudgeFrom() {
			$("#totalEligible").val('');
			$("#totalReassignable").val('');
			$("#totalRemaining").val('');
		}
		
		function changeCaseType() {
		
			$("#totalEligible").val('');
			$("#totalReassignable").val('');
			$("#totalRemaining").val('');
			
			var courtLocn = $('#courtLocn').val();
			
			appUX.ajax.call("/CorisWEB/CorisJudgeCommRotationServlet", 
				function (err, data, xhr) { 

					var $caseTypeDropdown = $("#caseType");
					$caseTypeDropdown.empty();
					$caseTypeDropdown.append("<option value=''></option>");
					
					var jsonObj = JSON.parse(data);
					for (var i=0; i < jsonObj.caseTypeList.length; i++) {
						var caseType = jsonObj.caseTypeList[i].caseType;
						var description = jsonObj.caseTypeList[i].description;
						$caseTypeDropdown.append("<option value='" + caseType + "'>" + description + "</option>");
					}
					
				}, 
				'POST', 
				'mode=changeCaseType&courtLocn=' + courtLocn
			); 
			
		}
	</script>
</head>
<body>
	<div class="container-fluid" style="text-align:center" id="errorMessage"></div>

	<div class="card mx-2 my-4">
		<div class="card-header font-weight-bold">
			Change Judge/Commissioner Assignments
		</div>
		<div class="font-weight-bold text-center text-danger mt-4">
			This screen is used to randomly select and reassign cases/hearings from one judge/commissioner to another judge/commissioner.
		</div>
	<div class="card-body">
	<form id="lookup" name="lookup">
		<div class="form-row mt-4">
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="from" class="col-sm-8 col-md-9 control-label text-sm-right">Court Location:</label>
					<div class="col-sm-12 col-md-13">
						<select class="form-control" id="courtLocn" name="courtLocn" onchange="changeCourtLocation(); changeCaseType();">
							<option></option>
							<% 
								if (locationList != null && locationList.size() > 0) {
									for (LocationBO locationBO : locationList) {
										String selected = locationBO.getLocnCode().equalsIgnoreCase(defaultLocnCode) && locationBO.getCourtType().equalsIgnoreCase(defaultCourtType) ? "selected" : "";
						 	%>
										<option value="<%= locationBO.getCourtType() + locationBO.getLocnCode() %>" <%= selected %>><%= locationBO.getLocnDescr() %></option>
							<% 
									}
								}
							%>
						</select>
						
					</div>
				</div>
			</div>
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="judge" class="radio-inline col control-label text-sm-center">
					<input type="radio" name="judgeComm" id="judgeComm" value="judge" onclick="changeCourtLocation(); changeCaseType();" checked>Judge</label>
					<label for="commissioner" class="radio-inline col control-label text-sm-center">
					<input type="radio" name="judgeComm" id="judgeComm" value="commissioner" onclick="changeCourtLocation(); changeCaseType();">Commissioner</label>
				</div>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="from" class="col-sm-8 col-md-9 control-label text-sm-right">From:</label>
					<div class="col-sm-12 col-md-13">
						<select class="form-control" id="judgeFrom" name="judgeFrom" onchange="changeJudgeFrom()"></select>
					</div>
				</div>
			</div>
			<div class="form-group col-md-13 col-lg-10"">
				<div class="row align-items-center">
					<label for="to" class="col-sm-8 col-md-9 control-label text-sm-right"><span class="text-danger">To:</span></label>
					<div class="col-sm-12 col-md-13">
						<select class="form-control" id="judgeTo" name="judgeTo"></select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="from" class="col-sm-8 col-md-9 control-label text-sm-right">Case Type:</label>
					<div class="col-sm-12 col-md-13">
						<select class="form-control" id="caseType" name="caseType" onchange="clearCategorySelection()">
							<option></option>
							<% 
								if (caseTypeList != null && caseTypeList.size() > 0) {
									for (CaseTypeBO caseTypeBO : caseTypeList) {
						 	%>
										<option value="<%= caseTypeBO.getCaseType() %>"><%= caseTypeBO.getDescr() %></option>
							<% 
									}
								}
							%>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group col-md-13 col-lg-10"">
				<div class="row align-items-center">
					<label for="to" class="col-sm-8 col-md-9 control-label text-sm-right">Category:</label>
					<div class="col-sm-12 col-md-13">
						<select class="form-control" id="category" name="category" onchange="clearCaseTypeSelection()">
							<option></option>
							<% 
								if (categoryList != null && categoryList.size() > 0) {
									for (CategoryDTO category : categoryList) {
						 	%>
										<option value="<%= category.getCategoryCode() %>"><%= category.getDescription() %></option>
							<% 
									}
								}
							%>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="percentage" class="col-sm-8 col-md-9 control-label text-sm-right"><span class="text-danger">Percentage</span></label>
					<div class="col-sm-12 col-md-13">
						<input type="number" min="0" max="100" value="0" oninput="this.value = Math.abs(this.value); if (this.value > 100) this.value=100;" class="form-control" id="percentage" name="percentage">
					</div>
				</div>
			</div>
			<div class="form-group col-md-13 col-lg-10">
				<div class="row align-items-center">
					<label for="reassignDisposedCases" class="col-sm-8 col-md-9 control-label text-sm-right form-check-label">Reassign Disposed Cases:</label>
					<div class="col-sm-12 col-md-13">
						<div class="form-check form-check-inline">
							<input type="checkbox" class="form-check-input" id="reassignDisposedCases" name="reassignDisposedCases" value="I">
						</div>
					</div>
				</div>
			</div>
		</div>
			
		<div class="card border-secondary mb-2">
			<div class="card-header">Totals</div>
				<div class="card-body">
				
					<div class="form-group col-md-13 col-lg-10">
						<div class="row align-items-center">
							<label for="totalEligible" class="col-sm-8 col-md-9 control-label text-sm-right">Total Cases Eligible:</label>
							<div class="col-sm-12 col-md-13">
								<input type="text" disabled class="form-control" id="totalEligible" name="totalEligible">
							</div>
						</div>
					</div>
					<div class="form-group col-md-13 col-lg-10">
						<div class="row align-items-center">
							<label for="totalReassignable" class="col-sm-8 col-md-9 control-label text-sm-right"><span class="text-danger">Total Cases Reassignable:</span></label>
							<div class="col-sm-12 col-md-13">
								<input type="text" disabled class="form-control" id="totalReassignable" name="totalReassignable">
							</div>
						</div>
					</div>
					<div class="form-group col-md-13 col-lg-10">
						<div class="row align-items-center">
							<label for="totalRemaining" class="col-sm-8 col-md-9 control-label text-sm-right">Total Cases Remaining:</label>
							<div class="col-sm-12 col-md-13">
								<input type="text" disabled class="form-control" id="totalRemaining" name="totalRemaining">
							</div>
						</div>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-primary float-right" id="calculateBtn" onclick="calc();">Calc</button>
		</div>
				
		<div class="form-row">
			<div class="form-group col-sm-20 col-md-23 col-lg-21 clearfix">
				<button type="button" class="btn btn-secondary ml-2 float-right" id="exitBtn" onclick="closePop();">Exit</button>
				<button type="button" class="btn btn-primary ml-2 float-right" id="clearBtn" onclick="clearSelections();">Clear</button>
				<button type="button" class="btn btn-primary ml-2 float-right" id="enterBtn" onclick="process();">Submit</button>
			</div>
		</div>
		</form>
		</div>
	</div>
</div>

	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		
	</script>
</body>
</html>
<%
	// cleanup
	locationList = null;
%>
