<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsActionDefnBO> codesList = (List<PsActionDefnBO>) request.getAttribute("codesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<div class="row">
	<div class="table-responsive" id="displayResults">
		<table id="tableResults" class="table table-hover table-activate">
			<thead class="bg-dark text-light">
				<tr>
					<% int colspan = 4; %>
					<th>Action Code</th>
					<th>Action Description</th>
					<th>Status Code</th>
					<th class="sorter-false"></th>
				</tr>
			</thead>
			<tbody>
				<%
				//display the list of results
				int psActionDefnId = 0;
				String psActionCode = "";
				String psActionDescr = "";
				int defaultPsStatusDefnId = 0;
				String psStatusCode = "";
				String psActionRemovedFlag = "";
				if(codesList.size() > 0) {
					for (PsActionDefnBO codeBO : codesList) {
						psActionDefnId = codeBO.getPsActionDefnId();
						psActionCode = codeBO.getPsActionCode();
						psActionDescr = codeBO.getPsActionDescr();
						defaultPsStatusDefnId = codeBO.getDefaultPsStatusDefnId();
						psStatusCode = (String) codeBO.get(PsStatusDefnBO.PSSTATUSCODE);
						if(psStatusCode == null) { psStatusCode = ""; } //prevents the word "null" displaying
						psActionRemovedFlag = codeBO.getRemovedFlag();
						%>
						<tr>
							<td><%=psActionCode %></td>
							<td><%=psActionDescr %></td>
							<td><%=psStatusCode %></td>
							<td style="white-space: nowrap;">
								<% if("N".equals(psActionRemovedFlag)) { %>
								<i id="editBtn" onclick="edit('<%=psActionCode%>', '<%=psActionDescr%>', <%=psActionDefnId%>, <%=defaultPsStatusDefnId%>)" title="Edit" class="text-warning far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
								<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psActionCode%>', <%=psActionDefnId%>)" title="Disable"></img>
								<% } else { %>
								<i class="text-muted far fa-edit fa-lg fa-fw"></i>
								<i id="enableBtn" onclick="enable('<%=psActionCode%>', <%=psActionDefnId%>)" title="Enable" class="text-info fas fa-redo fa-lg fa-fw" style="cursor: pointer;"></i>
								<% } %>
							</td>
						</tr>
						<%
					}
				} else {
					%>
					<tr>
						<td colspan="<%=colspan%>">No Results.</td>
					</tr>
					<% 
				}
				%>
			</tbody>
		</table>
	</div>
</div>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	
	$(document).ready(function(){
	
		$("#tableResults").tablesorter();
		
	});
	
	function edit(psActionCode, psActionDescr, psActionDefnId, psStatusDefnId) {
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceActionDefnServlet?psActionCode="+psActionCode+"&psActionDescr="+psActionDescr+"&psActionDefnId="+psActionDefnId+"&psStatusDefnId="+psStatusDefnId+"&mode=edit&displayDisabled="+disabled;
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	
	function disable(psActionCode, psActionDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Action Code "+psActionCode+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceActionDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psActionDefnId='+psActionDefnId+'&psActionRemovedFlag=Y&mode=disable&displayDisabled='+disabled
				);
			} else {
				corn.close();
			}
		}
	
	}
	
	function enable(psActionCode, psActionDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "N"
		var title = "Enable";
		var message = "Would you like to enable Action Code "+psActionCode+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceActionDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psActionDefnId='+psActionDefnId+'&psActionRemovedFlag=N&mode=enable&displayDisabled='+disabled
				);
			} else {
				corn.close();
			}
		}
	}
</script>
<%
//cleanup
displayDisabled = null;
codesList = null;
psActionCode = null;
psActionDescr = null;
psStatusCode = null;
psActionRemovedFlag = null;
%>
