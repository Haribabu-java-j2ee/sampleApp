<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psappearancedefn.PsAppearanceDefnBO"%>
<%
//get the list
List<PsAppearanceDefnBO> codesList = (List<PsAppearanceDefnBO>) request.getAttribute("codesList");
List<PsActionDefnBO> codesList2 = new PsActionDefnBO().where(PsActionDefnBO.REMOVEDFLAG, "N").orderBy(PsActionDefnBO.PSACTIONCODE).search();
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<div class="table-responsive" id="displayResults">
	<table id="tableResults" name="tableResults" class="table table-hover table-activate">
		<thead class="bg-dark text-white">
			<tr>
				<th>Appearance Code</th>
				<th>Appearance Description</th>
				<th>Action Code</th>
				<th>Minutes Text</th>
				<th class="sorter-false"></th>
			</tr>
		</thead>
		<tbody>
			<%
			//display the list of results
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			int psAppearDefnId = 0;
			String psAppearanceCode = "";
			String psAppearanceDescr = "";
			int psActionDefnId = 0;
			String psActionDefnDescr = "";
			String psMinutesText = "";
			
			String removedFlag = "";
			if(codesList != null && codesList.size() > 0) {
				for (PsAppearanceDefnBO codeBO : codesList) {
					psAppearDefnId = codeBO.getPsAppearanceDefnId();
					psAppearanceCode = codeBO.getPsAppearanceCode();
					psAppearanceDescr = codeBO.getPsAppearanceDescr();
					psActionDefnId = codeBO.getPsActionDefnId();
					for(PsActionDefnBO codeVO2 : codesList2) {
						if(psActionDefnId == codeVO2.getPsActionDefnId()){
							psActionDefnDescr = codeVO2.getPsActionCode();
						}
					}
					psMinutesText = codeBO.getPsMinutesText(); 
					removedFlag = codeBO.getRemovedFlag();
					%>
					<tr>
						<td id="<%=psAppearanceCode %>" name="<%=psAppearanceCode %>"><%=psAppearanceCode %></td>
						<td id="<%=psAppearanceDescr %>" name="<%=psAppearanceDescr %>"><%=psAppearanceDescr %></td>
						<td id="<%=psActionDefnDescr %>" name="<%=psActionDefnDescr %>"><%=psActionDefnDescr %></td>
						<td id="<%=psMinutesText %>" name="<%=psMinutesText %>"><%=psMinutesText %></td>
						<td style="white-space: nowrap;">
							<% if("N".equals(removedFlag)) { %>
							<i style="cursor: pointer;" onclick="edit('<%=psAppearDefnId %>');" title="Edit" class="text-warning far fa-edit fa-lg fa-fw"></i>
							<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psAppearanceCode %>', '<%=displayDisabled %>');" title="Disable"></img>
							<% } else { %>
							<i class="text-muted far fa-edit fa-lg fa-fw"></i>
							<i style="cursor: pointer;" onclick="enable('<%=psAppearanceCode %>', '<%=displayDisabled %>');" title="Enable" class="text-info fas fa-redo fa-lg fa-fw"></i>
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
</div>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
	
		$("#tableResults").tablesorter();
		
	});
	
	function edit(appearDefnId) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "jsp/pscMaintenanceAppearanceDefnAddEdit.jsp?appearDefnId="+appearDefnId;
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	
	function disable(code, displayDisabled){
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Appearance Code "+code+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceAppearanceDefnServlet", 
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
		var message = "Would you like to enable Appearance Code "+code+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceAppearanceDefnServlet", 
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
psAppearanceCode = null;
psAppearanceDescr = null;
psActionDefnId = 0;
psMinutesText= null;
removedFlag = null;
%>
