<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@include file="/fragments/NoCache.jspf"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	int incorrectBarNum = TextUtil.getParamAsInt(request, "incorrectBarNum");
	int correctBarNum = TextUtil.getParamAsInt(request, "correctBarNum");
	String incorrectBarState = TextUtil.getParamAsString(request, "incorrectBarState");
	String correctBarState = TextUtil.getParamAsString(request, "correctBarState");
	
	List<StateBO> statesList = (List<StateBO>) request.getAttribute("statesList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>CORIS - Attorney Bar Number Fix</title>
    
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
</head>
<body>

    <!-- loading screen -->
    <div id="loadingContainer">
        <div id="loadingCard">
            <div id="loadingMessage">Fixing...</div>
        </div>
    </div>
    
    <!-- main content -->
    <main>
		<div class="container-fluid">

		<form id="attorneysForm" name="attorneysForm" onsubmit="searchForm(event, false);">			
			<div class="card m-2">
				
				<div class="card-header">
					<strong>Incorrect Attorney</strong>
				</div>
				
				<div class="card-body">
					
						<div class="form-row">
							
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar Number</strong></label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="incorrectBarNum" name="incorrectBarNum" value="<%= (incorrectBarNum > 0) ? incorrectBarNum : "" %>" maxlength="10">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar State</strong></label>
										<div class="col-sm-12 col-md-13">
											<select class="form-control" id="incorrectBarState" name="incorrectBarState">
											<%
												if (statesList != null) {
													String stateName = "";
													String stateCode = "";
													String selected = "";
													for (StateBO temp : statesList) {
														stateCode = temp.getStateCode();
														stateName = temp.getName();
														if (stateCode.equals(incorrectBarState)) {
															selected = "selected";
														} else if ("".equals(selected) && "UT".equals(stateCode) && TextUtil.isEmpty(incorrectBarState)) {
															selected = "selected";
														} else {
															selected = "";
														}
											%>
														<option value="<%= stateCode %>" <%= selected %>><%= stateName %></option>
											<%
													}
												}
											%>
											</select>
										</div>
									</div>
								</div>
							
							</div>
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyName" name="incorrectAttyName" value="" maxlength="30">
										</div>
									</div>
								</div>
	
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyAddr1" name="incorrectAttyAddr1" value="" maxlength="30">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyAddr2" name="incorrectAttyAddr2" value="" maxlength="30">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyCity" name="incorrectAttyCity" value="" maxlength="20">
										</div>
									</div>
								</div>

								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyState" name="incorrectAttyState" value="" maxlength="20">
										</div>
									</div>
								</div>

								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="incorrectAttyZip" name="incorrectAttyZip" value="" maxlength="20">
										</div>
									</div>
								</div>
							
							</div>
							
						</div>
						
					</div>
	    		</div>
	    	
	    	</div>
	    		
	    	<div class="card m-2">
				
				<div class="card-header">
					<strong>Correct Attorney</strong>
				</div>
				
				<div class="card-body">
					
						<div class="form-row">
							
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar Number</strong></label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="correctBarNum" name="correctBarNum" value="<%= (correctBarNum > 0) ? correctBarNum : "" %>" maxlength="10">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Bar State</strong></label>
										<div class="col-sm-12 col-md-13">
											<select class="form-control" id="correctBarState" name="correctBarState">
											<%
												if (statesList != null) {
													String stateName = "";
													String stateCode = "";
													String selected = "";
													for (StateBO temp : statesList) {
														stateCode = temp.getStateCode();
														stateName = temp.getName();
														if (stateCode.equals(correctBarState)) {
															selected = "selected";
														} else if ("".equals(selected) && "UT".equals(stateCode) && TextUtil.isEmpty(correctBarState)) {
															selected = "selected";
														} else {
															selected = "";
														}
											%>
														<option value="<%= stateCode %>" <%= selected %>><%= stateName %></option>
											<%
													}
												}
											%>
											</select>
										</div>
									</div>
								</div>
							
							</div>
							<div class="col-12">
							
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyName" name="correctAttyName" value="" maxlength="30">
										</div>
									</div>
								</div>
	
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyAddr1" name="correctAttyAddr1" value="" maxlength="30">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyAddr2" name="correctAttyAddr2" value="" maxlength="30">
										</div>
									</div>
								</div>
								
								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyCity" name="correctAttyCity" value="" maxlength="20">
										</div>
									</div>
								</div>

								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyState" name="correctAttyState" value="" maxlength="20">
										</div>
									</div>
								</div>

								<div class="form-group col-md-16 offset-lg-2 col-lg-18">
									<div class="row align-items-center">
										<div class="col-sm-12 col-md-13">
											<input disabled type="text" class="form-control" id="correctAttyZip" name="correctAttyZip" value="" maxlength="20">
										</div>
									</div>
								</div>
							
							</div>
							
						</div>
						
					</div>
	    		</div>
	    		
				<div class="card m-2">
					<div class="card-header">
						<strong>Coris Servers</strong>
					</div>
					
					<div class="card-body">
						<div class="form-row">
					
							<div class="form-check form-check-inline">
								<input type="checkbox" class="form-check-input" name="productionDistrict" id="productionDistrict" value="true" checked>
								<label class="form-check-label" for="productionDistrict">Production District</label>
							</div>
							<div class="form-check form-check-inline">
								<input type="checkbox" class="form-check-input" name = "productionJustice" id="productionJustice" value="true" checked>
								<label class="form-check-label" for="productionJustice">Production Justice</label>
							</div>
							<div class="form-check form-check-inline text-danger" >
								<input type="checkbox" class="form-check-input" name="verify" id="verify" value="true" checked>
								<label class="form-check-label" for="verify">Verify</label>
							</div>
							<div class="form-check form-check-inline text-danger">
								<input type="checkbox" class="form-check-input" name="training" id="training" value="true" checked>
								<label class="form-check-label" for="training">Training</label>
							</div>
							<div class="form-check form-check-inline text-danger">
								<input type="checkbox" class="form-check-input" name="testDistrict" id="testDistrict" value="true" checked>
								<label class="form-check-label" for="testDistrict">Test District</label>
							</div>
							<div class="form-check form-check-inline text-danger">
								<input type="checkbox" class="form-check-input" name = "testJustice" id="testJustice" value="true" checked>
								<label class="form-check-label" for="testJustice">Test Justice</label>
							</div>
							<div class="form-check form-check-inline text-danger">
								<input type="checkbox" class="form-check-input" name="development" id="development" value="true" checked>
								<label class="form-check-label" for="development">Development</label>
							</div>
	
						</div>	
		    		</div>
		    	</div>
	    		
	    		<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
	    			<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="submit" class="btn btn-primary btn-sm float-right ml-2" id="searchBtn">Find</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Fix bar number" onclick="javascript:fixBarNumber();">Fix</button>
				</div>
				
	    	</form>
	    </div>
    </main>

    <!-- footer -->
    <footer></footer>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			$('#loadingContainer').hide(); 
			
			$('#incorrectBarNum').focus();
		});
		
		var parentCornId = appUX.pop.getSelfHandle().id;

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
 		function clearForm() {
 			$("#incorrectAttyName").val('');
			$("#incorrectAttyAddr1").val('');
			$("#incorrectAttyAddr2").val('');
			$("#incorrectAttyCity").val('');
			$("#incorrectAttyState").val('');
			$("#incorrectAttyZip").val('');
			
			$("#correctAttyName").val('');
			$("#correctAttyAddr1").val('');
			$("#correctAttyAddr2").val('');
			$("#correctAttyCity").val('');
			$("#correctAttyState").val('');
			$("#correctAttyZip").val('');
 		}
		
		function searchForm(e, disablePopup) {
			if (e) { e.preventDefault() };
			
			var data = $('#attorneysForm').serialize();
			if ($('#incorrectBarNum').val() > 0 && $('#incorrectBarState').val() != "" && $('#correctBarNum').val() > 0 && $('#correctBarState').val() != "") {
				appUX.ajax.call("/CorisWEB/CorisAttorneyBarNumberFixServlet", 
					function (err, data, xhr) {
						var jsonObj = JSON.parse(data);
						if (jsonObj.errorMessage) {
						
							appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							clearForm();
							
						} else {
						
							// incorrect attorney
	    					$("#incorrectAttyName").val(jsonObj.incorrectAttyName);
	    					$("#incorrectAttyAddr1").val(jsonObj.incorrectAttyAddr1);
							$("#incorrectAttyAddr2").val(jsonObj.incorrectAttyAddr2);
							$("#incorrectAttyCity").val(jsonObj.incorrectAttyCity);
							$("#incorrectAttyState").val(jsonObj.incorrectAttyState);
							$("#incorrectAttyZip").val(jsonObj.incorrectAttyZip);

							// correct attorney
	    					$("#correctAttyName").val(jsonObj.correctAttyName);
	    					$("#correctAttyAddr1").val(jsonObj.correctAttyAddr1);
							$("#correctAttyAddr2").val(jsonObj.correctAttyAddr2);
							$("#correctAttyCity").val(jsonObj.correctAttyCity);
							$("#correctAttyState").val(jsonObj.correctAttyState);
							$("#correctAttyZip").val(jsonObj.correctAttyZip);
	    					
							$('#incorrectBarNum').focus();
							
						}
					}, 
					'POST', 
					'mode=find&' + data
				);
			} else {
				clearForm();
			 
				message = "Both Bar Numbers/States are required for search.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#incorrectBarNum').focus();
			}
		}
		
		function fixBarNumber() {
			
			var data = $('#attorneysForm').serialize();
			if ($('#incorrectBarNum').val() > 0 && $('#incorrectBarState').val() != "" && $('#correctBarNum').val() > 0 && $('#correctBarState').val() != "") {
				
				$('#loadingContainer').show(); 
				
				appUX.ajax.call("/CorisWEB/CorisAttorneyBarNumberFixServlet", 
					function (err, data, xhr) {
						var jsonObj = JSON.parse(data);
						if (jsonObj.errorMessage) {
						
							appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							clearForm();
				
							$('#loadingContainer').hide();
							
						} else {
						
							appUX.pop.declare("Success", "Bar number fixed.", 300, 'auto', appUX.pop.styles.SUCCESS);
							closePop();
							
							$('#loadingContainer').hide();
 						}
					}, 
					'POST', 
					'mode=update&' + data
				);
				
			} else {
				clearForm();
			 
				message = "Both Bar Numbers/States are required for fix.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				$('#incorrectBarNum').focus();
			}
		}
		
	</script>
</body>
</html>
<%

%>