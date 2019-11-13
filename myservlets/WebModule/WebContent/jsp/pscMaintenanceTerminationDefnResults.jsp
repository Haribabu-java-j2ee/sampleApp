<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psterminationdefn.PsTerminationDefnBO"%>
<%
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsTerminationDefnBO> codesList = (List<PsTerminationDefnBO>) request.getAttribute("codesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<table id="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<% int colspan = 3; %>
			<th>Termination Code</th>
			<th>Termination Description</th>
			<th class="sorter-false"></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		int psTerminationDefnId = 0;
		String psTerminationCode = "";
		String psTerminationDescr = "";
		String psTerminationRemovedFlag = "";
		if(codesList.size() > 0) {
			for (PsTerminationDefnBO codeBO : codesList) {
				psTerminationDefnId = codeBO.getPsTerminationDefnId();
				psTerminationCode = codeBO.getPsTerminationCode();
				psTerminationDescr = codeBO.getPsTerminationDescr();
				psTerminationRemovedFlag = codeBO.getRemovedFlag();
				%>
				<tr>
					<td id="<%=psTerminationCode %>"><%=psTerminationCode %></td>
					<td id="<%=psTerminationDescr %>"><%=psTerminationDescr %></td>
					<td style="white-space: nowrap;">
						<% if("N".equals(psTerminationRemovedFlag)) { %>
						<i id="editBtn" onclick="edit('<%=psTerminationCode%>', '<%=psTerminationDescr%>', <%=psTerminationDefnId%>)" title="Edit" class="text-warning far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
						<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psTerminationCode%>', <%=psTerminationDefnId%>)" title="Disable"></img>
						<% } else { %>
						<i class="text-muted far fa-edit fa-lg fa-fw"></i>
						<i id="enableBtn" onclick="enable('<%=psTerminationCode%>', <%=psTerminationDefnId%>)" title="Enable" class="text-info fas fa-redo fa-lg fa-fw" style="cursor: pointer;"></i>
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
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function edit(psTerminationCode, psTerminationDescr, psTerminationDefnId) {
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceTerminationDefnServlet?psTerminationCode="+psTerminationCode+"&psTerminationDescr="+psTerminationDescr+"&psTerminationDefnId="+psTerminationDefnId+"&mode=edit&displayDisabled="+disabled;
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	function disable(psTerminationCode, psTerminationDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Termination Code "+psTerminationCode+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceTerminationDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psTerminationDefnId='+psTerminationDefnId+'&psTerminationRemovedFlag=Y&mode=disable&displayDisabled='+disabled
				);
			} else {
				corn.close();
			}
		}
	}
	function enable(psTerminationCode, psTerminationDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "N"
		var title = "Enable";
		var message = "Would you like to enable Termination Code "+psTerminationCode+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceTerminationDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psTerminationDefnId='+psTerminationDefnId+'&psTerminationRemovedFlag=N&mode=enable&displayDisabled='+disabled
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
psTerminationCode = null;
psTerminationDescr = null;
psTerminationRemovedFlag = null;
%>
