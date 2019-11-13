<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psphasedefn.PsPhaseDefnBO"%>
<%@page import="java.util.List"%>
<%
List<PsPhaseDefnBO> results = (List<PsPhaseDefnBO>)request.getAttribute("PhaseDefnList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<table id="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Phase Code</th>
			<th>Action</th>
			<th>Phase Description</th>
			<th class="sorter-false"></th>
		</tr>
	</thead>
	<tbody>
		
		<%  PsActionDefnBO psAction = null;
			if(results != null && results.size() > 0){
				for(PsPhaseDefnBO phase:results) {
					psAction = new PsActionDefnBO().where(PsActionDefnBO.PSACTIONDEFNID,phase.getPsActionDefnId()).find();
		%>
			<tr>
				<td id="<%=phase.getPsPhaseCode() %>"><%=phase.getPsPhaseCode() %></td>
				<td id="<%=psAction.getPsActionCode() %>"><%=psAction.getPsActionDescr() %></td>
				<td id="<%=phase.getPsPhaseDescr() %>"><%=phase.getPsPhaseDescr() %></td>
				<td style="white-space: nowrap;">
					<% if("N".equals(phase.getRemovedFlag())) { %>
						<i style="cursor: pointer;" onclick="edit('<%=phase.getPsPhaseDefnId() %>');" title="Edit" class="text-warning far fa-edit fa-lg"></i>
						<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="setRemovedFlag('<%=phase.getPsPhaseDefnId() %>', 'Y', '<%=phase.getPsPhaseCode() %>');" title="Remove"></img>
						<% } else { %>
						<i class="text-muted far fa-edit fa-lg fa-fw"></i>
						<i style="cursor: pointer;" onclick="setRemovedFlag('<%=phase.getPsPhaseDefnId() %>','N', '<%=phase.getPsPhaseCode() %>');" title="Enable" class="text-info fas fa-redo fa-lg"></i>
					<% } %>
				</td>
			</tr>
		<%} }else {%>
			<tr>
				<td colspan="4" align="center">No Results (Please select your court to find phase list)</td>
			</tr>
		<%} %>
		
	</tbody>
</table>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	
	$(document).ready(function(){
	
		$("#tableResults").tablesorter();
		
	});

	function edit(phaseId) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenancePhaseDefnServlet?phaseDefnId="+phaseId+ "&mode=edit";
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	
	function setRemovedFlag(id, flag, code){
		var displayRemovedToggle = $('#displayRemoved:checked').val();
		var displayRemoved = "false"; //don't display the disabled
		if(displayRemovedToggle) {
			displayRemoved = "true"; //display the disabled
		}
		//set the remove flag in the database to "Y"
		var title = (flag=="Y"? "Disable":"Enable");
		var message = "Would you like to " + (flag=="Y"? "disable":"enable") + " phase definition "+code+"?";
		var trueText = title;
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);

		function confirmCallback(action) {
			if (action) {
			appUX.ajax.call("/CorisWEB/PSCMaintenancePhaseDefnServlet?phaseDefnId=" + id + "&mode=set-remove&removedFlag=" + flag + "&showRemoved=" + displayRemoved, 
							function (err, data, xhr){ corn.close(); $("#displayResults").html(data);});
			} else {
				corn.close();
			}
		}
	}

</script>
