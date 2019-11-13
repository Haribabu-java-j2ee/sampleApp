<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusactionxref.PsStatusActionXrefBO"%>
<%
List<PsStatusActionXrefBO> xrefList = (List<PsStatusActionXrefBO>) request.getAttribute("xrefList");
List<PsActionDefnBO> actionDefnList = (List<PsActionDefnBO>) request.getAttribute("actionDefnList");
List<PsStatusDefnBO> statusDefnList = (List<PsStatusDefnBO>) request.getAttribute("statusDefnList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Status - Action Cross Reference</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Edit Status - Action</strong>
			</div>
			<div class="card-body">
				<form id="actionForm" action="" method="post">
		
				<table class="table">
					<thead class="bg-dark text-white">
						<tr>
							<% int colspan = 2; %>
							<th>Status</th>
							<th class="sorter-false">Action Codes</th>
						</tr>
					</thead>
					<tbody>
						<%
						//display the list of results
						int psStatusActionId = 0;
						int psXrefActionDefnId = 0;
						int psXrefStatusDefnId = 0;
						int psActionDefnId = 0;
						String psActionCode = "";
						String psActionDescr = "";
						String psActionRemovedFlag = "";
						int psStatusDefnId = 0;
						String psStatusCode = "";
						String psStatusDescr = "";
						for(PsStatusDefnBO statusListBO : statusDefnList) {
							psStatusDefnId = statusListBO.getPsStatusDefnId();
							psStatusCode = statusListBO.getPsStatusCode();
							psStatusDescr = statusListBO.getPsStatusDescr();
							%>
							<tr>
								<td><%=psStatusCode %> - <%=psStatusDescr %></td>
								<td>
									<table class="table table-borderless">
										<tbody>
											<%
											for(PsActionDefnBO actionListBO : actionDefnList) {
												psActionDefnId = actionListBO.getPsActionDefnId();
												psActionCode = actionListBO.getPsActionCode();
												psActionDescr = actionListBO.getPsActionDescr();
												psActionRemovedFlag = actionListBO.getRemovedFlag();
												if("N".equals(psActionRemovedFlag)){
													%>
													<tr>
														<td>
															<div class="form-check">
																<input type="checkbox" class="form-check-input" id="action-<%=psStatusDefnId%>-<%=psActionDefnId%>" name="action-<%=psStatusDefnId%>-<%=psActionDefnId%>" 
																<%
																if(xrefList.size() > 0){
																	for(PsStatusActionXrefBO xrefListBO : xrefList) {
																		psStatusActionId = xrefListBO.getPsStatusActionId();
																		psXrefActionDefnId = xrefListBO.getPsActionDefnId();
																		psXrefStatusDefnId = xrefListBO.getPsStatusDefnId();
																		if(psActionDefnId == psXrefActionDefnId && psStatusDefnId == psXrefStatusDefnId){
																			%>
																			checked
																			<%
																				if(actionListBO.getDefaultPsStatusDefnId() == psXrefStatusDefnId){
																			%>
																				disabled
																			<% 
																		}
																		}
																		
																	}
																}
																%>
																>
																<label class="form-check-label"><%=psActionCode%> - <%=psActionDescr%></label>
															</div>
														</td>
													</tr>
													<%
												}
											}
											%>
										</tbody>
									</table>
								</td>
							</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<input type="hidden" id="psStatusDefnId" value="<%=psStatusDefnId %>">
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" id="cancelBtn" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel">Cancel</button>
					<button type="button" id="updateBtn" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save">Save</button>
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
	<script>
		$(document).ready(function(){
			$('#cancelBtn').on('click', function(){
				closePop();
			});	
			$('#updateBtn').on('click', function(){
				var data = $('#actionForm').serialize();
				saveRecords(data, $('#psStatusDefnId').val());
			});
		});
		function saveRecords(data, psStatusDefnId) {
			var url = "/CorisWEB/PSCMaintenanceXrefStatusActionServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					appUX.pop.refreshCornFrame('modalStatusActionXrefPrimary', false);
					closePop(); 
				}, 
				'POST', 
				'psStatusDefnId='+psStatusDefnId+'&mode=save&'+data
			);
		}
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	</script>
</body>
</html>
<%
//cleanup
psActionDescr = null;
psActionRemovedFlag = null;
psStatusCode = null;
psStatusDescr = null;
psActionCode = null;
xrefList = null;
actionDefnList = null;
statusDefnList = null;
%>
