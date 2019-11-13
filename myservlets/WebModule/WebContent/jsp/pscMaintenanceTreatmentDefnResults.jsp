<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pstreatmentdefn.PsTreatmentDefnBO"%>
<%
//get the list
List<PsTreatmentDefnBO> codesList = (List<PsTreatmentDefnBO>) request.getAttribute("codesList");
List<PsCourtDefnBO> codesList2 = (List<PsCourtDefnBO>) request.getAttribute("codesList2");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Treatment Code</th>
			<th>Treatment Description</th>
			<th>Court Type</th>
			<th class="sorter-false"></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
		int psTreatDefnId = 0;
		String psTreatmentCode = "";
		String psTreatmentDescr = "";
		int psCourtDefnId = 0;
		String psCourtDefnDescr = "";
		String removedFlag = "";
		if(codesList != null && codesList.size() > 0) {
			for (PsTreatmentDefnBO codeBO : codesList) {
				psTreatDefnId = codeBO.getPsTreatmentDefnId();
				psTreatmentCode = codeBO.getPsTreatmentCode();
				psTreatmentDescr = codeBO.getPsTreatmentDescr();
				psCourtDefnId = codeBO.getPsCourtDefnId();
				 for(PsCourtDefnBO codeVO2 : codesList2) {
					if(psCourtDefnId == codeVO2.getPsCourtDefnId()){
						psCourtDefnDescr = codeVO2.getPsCodeDescr();
					}
					
				 } 
				
				removedFlag = codeBO.getRemovedFlag();
				%>
				<tr>
					<td id="<%=psTreatmentCode %>" name="<%=psTreatmentCode %>"><%=psTreatmentCode %></td>
					<td id="<%=psTreatmentDescr %>" name="<%=psTreatmentDescr %>"><%=psTreatmentDescr %></td>
					<td id="<%=psCourtDefnDescr %>" name="<%=psCourtDefnDescr %>"><%=psCourtDefnDescr %></td>
					<td style="white-space: nowrap;">
						<% if("N".equals(removedFlag)) { %>
						<i style="cursor: pointer;" onclick="edit('<%=psTreatmentCode %>', '<%=psTreatmentDescr %>', '<%=psCourtDefnId %>', '<%=psTreatDefnId %>');" title="Edit" class="text-warning far fa-edit fa-lg fa-fw"></i>
						<img src='/CorisWEB/images/delete.png' style="cursor: pointer" onclick="disable('<%=psTreatmentCode %>', '<%=displayDisabled %>')" title="Disable";></img>
						<% } else { %>
						<i class="text-muted far fa-edit fa-lg fa-fw"></i>
						<i style="cursor: pointer;" onclick="enable('<%=psTreatmentCode %>', '<%=displayDisabled %>');" title="Enable" class="text-info fas fa-redo fa-lg fa-fw"></i>
						<% } %>
					</td>
				</tr>
				<%
			}
		} else {
			%>
			<tr>
				<td colspan="3">No Results.</td>
			</tr>
			<% 
		}
		%>
	</tbody>
</table>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function edit(code, descr, courtId, treatDefnId) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceTreatmentDefnServlet?code="+code+"&descr="+descr+"&courtId="+courtId+"&treatDefnId="+treatDefnId+"&mode=edit";
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	function disable(code, displayDisabled){
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Treatment Code "+code+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceTreatmentDefnServlet", 
					function (err, data, xhr) { 
						corn.close();
						$("#displayResults").html(data);
					}, 
					'POST', 
					'code='+code+'&flag=Y&mode=disable&displayResults=true&displayDisabled='+displayDisabled
				);
			} else {
				corn.close();
			}
		}
	}
	function enable(code, displayDisabled){
		//set the remove flag in the database to "N"
		var title = "Enable";
		var message = "Would you like to enable Treatment Code "+code+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceTreatmentDefnServlet", 
					function (err, data, xhr) { 
						corn.close();
						$("#displayResults").html(data);
					}, 
					'POST', 
					'code='+code+'&flag=N&mode=enable&displayResults=true&displayDisabled='+displayDisabled
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
psTreatmentCode = null;
psTreatmentDescr = null;
psCourtDefnId = 0;
removedFlag = null;
%>
