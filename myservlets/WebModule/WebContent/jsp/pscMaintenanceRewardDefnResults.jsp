<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewardleveldefn.PsRewardLevelDefnBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO"%>
<%
//get the list
List<PsRewardDefnBO> codesList = (List<PsRewardDefnBO>) request.getAttribute("codesList");
List<PsRewardLevelDefnBO> rewardLevelDefnList = (List<PsRewardLevelDefnBO>)request.getAttribute("rewardLevelDefnList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<div class="row">
	<div class="table-responsive" id="displayResults">

		<table id="tableResults" name="tableResults" class="table table-hover table-activate">
			<thead class="bg-dark text-white">
				<tr>
					<th>Reward Code</th>
					<th>Reward Description</th>
					<th>Reward Level</th>
					<th class="sorter-false"></th>
				</tr>
			</thead>
			<tbody>
				<%
				//display the list of results
				String displayDisabled = TextUtil.getParamAsString(request, "displayDisabled");
				String psRewardCode = "";
				String psRewardDescr = "";
				String removedFlag = "";
				int psRewardLevel = 0;
				int psRewDefnId = 0;
				String rewardLevelDescr = "";
				if(codesList != null && codesList.size() > 0) {
					for (PsRewardDefnBO codeBO : codesList) {
						if(rewardLevelDefnList != null && rewardLevelDefnList.size() > 0){
							int rewardLevelId = 0; 
							for (PsRewardLevelDefnBO results2 : rewardLevelDefnList) { 
								if(results2.getRewardLevelSrl() == codeBO.getPsRewardLevel()){
									rewardLevelDescr = results2.getRewardLevelDescr();
								}
							}
						} 
						psRewardCode = codeBO.getPsRewardCode();
						psRewardDescr = codeBO.getPsRewardDescr();
						psRewardLevel = codeBO.getPsRewardLevel();
						psRewDefnId = codeBO.getPsRewardDefnId();
						removedFlag = codeBO.getRemovedFlag();
						%>
						<tr>
							<td id="<%=psRewardCode %>" name="<%=psRewardCode %>"><%=psRewardCode %></td>
							<td id="<%=psRewardDescr %>" name="<%=psRewardDescr %>"><%=psRewardDescr %></td>
							<td id="<%=codeBO.getPsRewardLevel() %>"><%=rewardLevelDescr %></td>
							<td style="white-space: nowrap;">
								<% if("N".equals(removedFlag)) { %>
								<i style="cursor: pointer;" onclick="edit('<%=psRewardCode %>', '<%=psRewardDescr %>', '<%=psRewDefnId %>', <%=psRewardLevel %>);" title="Edit" class="text-warning far fa-edit fa-lg fa-fw"></i>
								<img src='/CorisWEB/images/delete.png' style="cursor: pointer;" onclick="disable('<%=psRewardCode %>', '<%=displayDisabled %>');" title="Disable"></img>
								<% } else { %>
								<i class="text-muted far fa-edit fa-lg fa-fw"></i>
								<i style="cursor: pointer;" onclick="enable('<%=psRewardCode %>', '<%=displayDisabled %>');" title="Enable" class="text-info fas fa-redo fa-lg fa-fw"></i>
								<% } %>
							</td>
						</tr>
						<%
					}
				} else {
					%>
					<tr>
						<td colspan="4">No Results.</td>
					</tr>
					<% 
				}
				%>
			</tbody>
		</table>

	</div>
</div>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function edit(code, descr, rewDefnId, level) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceRewardDefnServlet?code="+code+"&descr="+descr+"&level="+level+"&rewDefnId="+rewDefnId+"&mode=editPopup";
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	function disable(code, displayDisabled){
		//set the remove flag in the database to "Y"
		var title = "Disable";
		var message = "Would you like to disable Reward Code "+code+"?";
		var trueText = "Disable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceRewardDefnServlet", 
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
		var message = "Would you like to enable Reward Code "+code+"?";
		var trueText = "Enable";
		var falseText = "Cancel";
		var width = 300;
		var height = "auto";
		var style = appUX.pop.styles.PRIMARY;
		var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		function confirmCallback(action) {
			if (action) {
				appUX.ajax.call("/CorisWEB/PSCMaintenanceRewardDefnServlet", 
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
psRewardCode = null;
psRewardDescr = null;
removedFlag = null;
%>
