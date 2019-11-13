<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psevaluationdefn.PsEvaluationDefnBO"%>
<%
//get the list
List<PsEvaluationDefnBO> codesList = (List<PsEvaluationDefnBO>) request.getAttribute("codesList");
List<PsCourtDefnBO> codesList2 = (List<PsCourtDefnBO>) request.getAttribute("codesList2");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<div class="table-responsive" id="displayResults">

	<table id="tableResults" name="tableResults" class="table table-hover table-activate">
		<thead class="bg-dark text-white">
			<tr>
				<th>Evaluation Code</th>
				<th>Evaluation Description</th>
				<th>Court Type</th>
				<th class="sorter-false"></th>
			</tr>
		</thead>
		<tbody>
			<%
			//display the list of results
			String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
			int psEvalDefnId = 0;
			String psEvaluationCode = "";
			String psEvaluationDescr = "";
			int psCourtDefnId = 0;
			String psCourtDefnDescr = "";
			String removedFlag = "";
			if(codesList != null && codesList.size() > 0) {
				for (PsEvaluationDefnBO codeBO : codesList) {
					psEvalDefnId = codeBO.getPsEvaluationDefnId();
					psEvaluationCode = codeBO.getPsEvaluationCode();
					psEvaluationDescr = codeBO.getPsEvaluationDescr();
					psCourtDefnId = codeBO.getPsCourtDefnId();
					 for(PsCourtDefnBO codeVO2 : codesList2) {
						if(psCourtDefnId == codeVO2.getPsCourtDefnId()){
							psCourtDefnDescr = codeVO2.getPsCodeDescr();
						}
						
					 } 
					
					removedFlag = codeBO.getRemovedFlag();
					%>
					<tr>
						<td id="<%=psEvaluationCode %>" name="<%=psEvaluationCode %>"><%=psEvaluationCode %></td>
						<td id="<%=psEvaluationDescr %>" name="<%=psEvaluationDescr %>"><%=psEvaluationDescr %></td>
						<td id="<%=psCourtDefnDescr %>" name="<%=psCourtDefnDescr %>"><%=psCourtDefnDescr %></td>
						<td style="white-space: nowrap;">
							<% if("N".equals(removedFlag)) { %>
							<i style="cursor: pointer;" onclick="edit('<%=psEvaluationCode %>', '<%=psEvaluationDescr %>', '<%=psCourtDefnId %>','<%=psEvalDefnId %>');" title="Edit" class="text-warning far fa-edit fa-lg"></i>
							<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psEvaluationCode %>', '<%=displayDisabled %>');" title="Disable"></img>
							<% } else { %>
							<i title="Edit" class="text-muted far fa-edit fa-lg"></i>
							<i style="cursor: pointer;" onclick="enable('<%=psEvaluationCode %>', '<%=displayDisabled %>');" title="Enable" class="text-info fas fa-redo fa-lg"></i>
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

	function edit(code, descr, courtId, evalDefnId) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceEvaluationDefnServlet?code="+code+"&descr="+descr+"&courtId="+courtId+"&evalDefnId="+evalDefnId+"&mode=edit";
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}

	function disable(code, displayDisabled){
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Evaluation Code "+code+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);

		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceEvaluationDefnServlet", 
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
		var message = "Would you like to enable Evaluation Code "+code+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);

		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceEvaluationDefnServlet", 
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
psEvaluationCode = null;
psEvaluationDescr = null;
psCourtDefnId = 0;
removedFlag = null;
%>
