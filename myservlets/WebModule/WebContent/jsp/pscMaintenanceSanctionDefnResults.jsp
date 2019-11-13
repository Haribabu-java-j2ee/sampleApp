<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctionleveldefn.PsSanctionLevelDefnBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO"%>
<%
List<PsSanctionDefnBO> results = (List<PsSanctionDefnBO>)request.getAttribute("sanctionDefnList");
List<PsSanctionLevelDefnBO> sanctionLevelDefnList = (List<PsSanctionLevelDefnBO>)request.getAttribute("sanctionLevelDefnList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<table id="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Sanction Code</th>
			<th>Sanction Description</th>
			<th>Sanction Level</th>
			<th class="sorter-false"></th>
		</tr>
	</thead>
	<tbody>
		
		<% if(results != null && results.size() > 0){
			String sanctionLevelDescr = "";
			for(PsSanctionDefnBO sanction:results) {
				if(sanctionLevelDefnList != null && sanctionLevelDefnList.size() > 0){
					int sanctionLevelId = 0; 
					for (PsSanctionLevelDefnBO results2 : sanctionLevelDefnList) { 
						if(results2.getSanctionLevelSrl() == sanction.getPsSanctionLevel()){
							sanctionLevelDescr = results2.getSanctionLevelDescr();
						}
					}
				} 
			%>
			<tr>
				<td id="<%=sanction.getPsSanctionCode() %>"><%=sanction.getPsSanctionCode() %></td>
				<td id="<%=sanction.getPsSanctionDescr() %>"><%=sanction.getPsSanctionDescr() %></td>
				<td id="<%=sanction.getPsSanctionLevel() %>"><%=sanctionLevelDescr %></td>
				<td style="white-space: nowrap;">
					<% if("N".equals(sanction.getRemovedFlag())) { %>
						<i style="cursor: pointer;" onclick="edit('<%=sanction.getPsSanctionCode() %>', '<%=sanction.getPsSanctionDescr() %>', <%=sanction.getPsSanctionLevel() %>);" title="Edit" class="text-warning far fa-edit fa-lg fa-fw"></i>
						<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="setRemovedFlag('<%=sanction.getPsSanctionCode() %>', 'Y', '<%=sanction.getPsSanctionCode() %>');" title="Remove"></img>
						<% } else { %>
						<i class="text-muted far fa-edit fa-lg fa-fw"></i>
						<i style="cursor: pointer;" onclick="setRemovedFlag('<%=sanction.getPsSanctionCode() %>','N', '<%=sanction.getPsSanctionCode() %>');" title="Enable" class="text-info fas fa-redo fa-lg fa-fw"></i>
					<% } %>
				</td>
			</tr>
		<%	} 
		}else {%>
			<tr>
				<td colspan="4" align="center">No Results</td>
			</tr>
		<%} %>
		
	</tbody>
</table>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function edit(code,descr,level) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceSanctionDefnServlet?sanctionCode="+code+ "&descr=" + descr + "&level=" + level + "&mode=edit";
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
		var message = "Would you like to " + (flag=="Y"? "disable":"enable") + " sanction definition "+code+"?";
		var trueText = title;
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceSanctionDefnServlet", 
					function (err, data, xhr) { 
						corn.close();
						$("#displayResults").html(data);
					}, 
					'POST', 
					'sanctionCode='+id+'&mode=set-removedFlag&removedFlag='+flag+'&showRemoved='+displayRemoved
				);	
			} else {
				corn.close();
			}
		}

	}
</script>
