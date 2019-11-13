<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsStatusDefnBO> codesList = (List<PsStatusDefnBO>) request.getAttribute("codesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<div class="table-responsive" id="displayResults">

	<table id="tableResults" class="table table-hover table-activate">
		<thead class="bg-dark text-white">
			<tr>
				<% int colspan = 3; %>
				<th>Status Code</th>
				<th>Status Description</th>
				<th class="sorter-false"></th>
			</tr>
		</thead>
		<tbody>
			<%
			//display the list of results
			int psStatusDefnId = 0;
			String psStatusCode = "";
			String psStatusDescr = "";
			String psStatusRemovedFlag = "";
			if(codesList.size() > 0) {
				for (PsStatusDefnBO codeBO : codesList) {
					psStatusDefnId = codeBO.getPsStatusDefnId();
					psStatusCode = codeBO.getPsStatusCode();
					psStatusDescr = codeBO.getPsStatusDescr();
					psStatusRemovedFlag = codeBO.getRemovedFlag();
					%>
					<tr>
						<td><%=psStatusCode %></td>
						<td><%=psStatusDescr %></td>
						<td style="white-space: nowrap;">
							<% if("N".equals(psStatusRemovedFlag)) { %>
							<%//TODO can't for the life of me figure out how to get these onclick() to work from the .js file instead of from here...fix someday %>
							<i id="editBtn" onclick="edit('<%=psStatusCode%>', '<%=psStatusDescr%>', <%=psStatusDefnId%>)" title="Edit" class="text-warning far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
							<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psStatusCode%>', <%=psStatusDefnId%>)" title="Disable"></img>
							<% } else { %>
							<i class="text-muted far fa-edit fa-lg fa-fw"></i>
							<i id="enableBtn" onclick="enable('<%=psStatusCode%>', <%=psStatusDefnId%>)" title="Enable" class="text-info fas fa-redo fa-lg fa-fw" style="cursor: pointer;"></i>
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
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function edit(psStatusCode, psStatusDescr, psStatusDefnId) {
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceStatusDefnServlet?psStatusCode="+psStatusCode+"&psStatusDescr="+psStatusDescr+"&psStatusDefnId="+psStatusDefnId+"&mode=edit&displayDisabled="+disabled;
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	function disable(psStatusCode, psStatusDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Status Code "+psStatusCode+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceStatusDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psStatusDefnId='+psStatusDefnId+'&psStatusRemovedFlag=Y&mode=disable&displayDisabled='+disabled
				);
			} else {
				corn.close();
			}
		}
	
	}
	function enable(psStatusCode, psStatusDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "N"
		var title = "Enable";
		var message = "Would you like to enable Status Code "+psStatusCode+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceStatusDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psStatusDefnId='+psStatusDefnId+'&psStatusRemovedFlag=N&mode=enable&displayDisabled='+disabled
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
psStatusCode = null;
psStatusDescr = null;
psStatusRemovedFlag = null;
%>
