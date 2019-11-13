<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psgoaldefn.PsGoalDefnBO"%>
<%
String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
List<PsGoalDefnBO> codesList = (List<PsGoalDefnBO>) request.getAttribute("codesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<div class="row">
	<div class="table-responsive" id="displayResults">

		<table id="tableResults" class="table table-hover table-activate">
			<thead class="bg-dark text-white">
				<tr>
					<% int colspan = 4; %>
					<th>Goal Code</th>
					<th>Goal Description</th>
					<th>Court Code</th>
					<th class="sorter-false"></th>
				</tr>
			</thead>
			<tbody>
				<%
				//display the list of results
				int psGoalDefnId = 0;
				String psGoalCode = "";
				String psGoalDescr = "";
				int psCourtDefnId = 0;
				String psCourtCode = "";
				String psGoalRemovedFlag = "";
				if(codesList.size() > 0) {
					for (PsGoalDefnBO codeBO : codesList) {
						psGoalDefnId = codeBO.getPsGoalDefnId();
						psGoalCode = codeBO.getPsGoalCode();
						psGoalDescr = codeBO.getPsGoalDescr();
						psCourtDefnId = codeBO.getPsCourtDefnId();
						psCourtCode = (String) codeBO.get(PsCourtDefnBO.PSCOURTCODE);
						if(psCourtCode == null) { psCourtCode = ""; } //prevents the word "null" displaying
						psGoalRemovedFlag = codeBO.getRemovedFlag();
						%>
						<tr>
							<td><%=psGoalCode %></td>
							<td><%=psGoalDescr %></td>
							<td><%=psCourtCode %></td>
							<td style="white-space: nowrap;">
								<% if("N".equals(psGoalRemovedFlag)) { %>
								<%//TODO can't for the life of me figure out how to get these onclick() to work from the .js file instead of from here...fix someday %>
								<i id="editBtn" onclick="edit('<%=psGoalCode%>', '<%=psGoalDescr%>', <%=psGoalDefnId%>, <%=psCourtDefnId%>)" title="Edit" class="text-warning far fa-edit fa-lg fa-fw" style="cursor: pointer;"></i>
								<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psGoalCode%>', <%=psGoalDefnId%>)" title="Disable"></img>
								<% } else { %>
								<i class="text-muted far fa-edit fa-lg fa-fw"></i>
								<i id="enableBtn" onclick="enable('<%=psGoalCode%>', <%=psGoalDefnId%>)" title="Enable" class="text-info fas fa-redo fa-lg fa-fw" style="cursor: pointer;"></i>
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
	
	function edit(psGoalCode, psGoalDescr, psGoalDefnId, psCourtDefnId) {
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceGoalDefnServlet?psGoalCode="+psGoalCode+"&psGoalDescr="+psGoalDescr+"&psGoalDefnId="+psGoalDefnId+"&psCourtDefnId="+psCourtDefnId+"&mode=edit&displayDisabled="+disabled;
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	
	function disable(psGoalCode, psGoalDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Goal Code "+psGoalCode+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceGoalDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psGoalDefnId='+psGoalDefnId+'&psGoalRemovedFlag=Y&mode=disable&displayDisabled='+disabled
				);
			} else {
				corn.close();
			}
		}
	}
	
	function enable(psGoalCode, psGoalDefnId){
		var disabledToggle = $('#displayDisabled:checked').val();
		var disabled = false; //don't display the disabled
		if(disabledToggle != undefined) {
			disabled = true; //display the disabled
		}
		//set the remove flag in the database to "N"
		var title = "Enable";
		var message = "Would you like to enable Goal Code "+psGoalCode+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				var url = "/CorisWEB/PSCMaintenanceGoalDefnServlet";
				appUX.ajax.call(url, 
					function (err, data, xhr) { 
						corn.close(); 
						$("#displayResults").html(data); 
					}, 
					'POST', 
					'psGoalDefnId='+psGoalDefnId+'&psGoalRemovedFlag=N&mode=enable&displayDisabled='+disabled
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
psGoalCode = null;
psGoalDescr = null;
psCourtCode = null;
psGoalRemovedFlag = null;
%>
