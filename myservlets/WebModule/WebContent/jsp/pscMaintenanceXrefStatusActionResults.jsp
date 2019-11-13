<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusactionxref.PsStatusActionXrefBO"%>
<%
List<PsStatusActionXrefBO> xrefList = (List<PsStatusActionXrefBO>) request.getAttribute("xrefList");
List<PsActionDefnBO> actionDefnList = (List<PsActionDefnBO>) request.getAttribute("actionDefnList");
List<PsStatusDefnBO> statusDefnList = (List<PsStatusDefnBO>) request.getAttribute("statusDefnList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Status Code</th>
			<th>Status Description</th>
			<th class="sorter-false">Action Codes</th>
			<th class="sorter-false"><span style="white-space: nowrap;" class="text-white"><i onclick="toggleRow(null, 'show')" title="Expand All" style="cursor: pointer;" class="far fa-plus-square fa-lg fa-fw"></i><i onclick="toggleRow(null, 'hide')" title="Collapse All" style="cursor: pointer;" class="far fa-minus-square fa-lg fa-fw"></i></span></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		int psStatusActionId = 0;
		int psXrefActionDefnId = 0;
		int psXrefStatusDefnId = 0;
		int psActionDefnId = 0;
		String psActionCode = "";
		String psActionDescr = "";
		String psActionRemovedFlag = "";
		int psStatusDefnId = 0;
		String psStatusCode = "";
		String psStatusDescr = "";
		boolean codesResults = false;
		for(PsStatusDefnBO statusListBO : statusDefnList) {
			psStatusDefnId = statusListBO.getPsStatusDefnId();
			psStatusCode = statusListBO.getPsStatusCode();
			psStatusDescr = statusListBO.getPsStatusDescr();
			%>
			<tr>
				<td><%=psStatusCode %></td>
				<td><%=psStatusDescr %></td>
				<td>
					<div id="div<%=psStatusDefnId %>" class="initialState">
					<table class="table table-borderless">
						<tbody>
							<%
							for(PsActionDefnBO actionListBO : actionDefnList) {
								psActionDefnId = actionListBO.getPsActionDefnId();
								psActionCode = actionListBO.getPsActionCode();
								psActionDescr = actionListBO.getPsActionDescr();
								psActionRemovedFlag = actionListBO.getRemovedFlag();
								if("N".equals(psActionRemovedFlag)){
									for(PsStatusActionXrefBO xrefListBO : xrefList) {
										psStatusActionId = xrefListBO.getPsStatusActionId();
										psXrefActionDefnId = xrefListBO.getPsActionDefnId();
										psXrefStatusDefnId = xrefListBO.getPsStatusDefnId();
										if(psStatusDefnId == psXrefStatusDefnId && psActionDefnId == psXrefActionDefnId){
											%>
											<tr>
												<td style="margin: 0px; padding: 0px;">
													<%=psActionCode%> - <%=psActionDescr%>
												</td>
											</tr>
											<%
											codesResults = true;
										}
									}
								}
							}
							%>
						</tbody>
					</table>
					</div>
				</td>
				<td style="white-space: nowrap;">
					<%if(codesResults) {%>
					<span class="expandRow" style="cursor: pointer; display: none;" id="expand<%= psStatusDefnId %>" title="Expand" onclick="toggleRow('<%=psStatusDefnId %>', 'show');"><i class="text-primary far fa-plus-square fa-lg fa-fw"></i></span>
					<span class="collapseRow" style="cursor: pointer;" id="collapse<%= psStatusDefnId %>" title="Collapse" onclick="toggleRow('<%=psStatusDefnId %>', 'hide');"><i class="text-primary far fa-minus-square fa-lg fa-fw"></i></span>					
					<% } else { %>
					<span><i class="text-white far fa-square fa-lg fa-fw"></i></span>
					<% }
					codesResults = false;
					%>
					<i onclick="editRecordPopup('<%=psStatusDefnId%>')" title="Edit" style="cursor: pointer;" class="text-warning far fa-edit fa-lg fa-fw"></i>
				</td>
			</tr>
			<%
		}
		%>
	</tbody>
</table>

<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
	});
	function editRecordPopup(psStatusDefnId){
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceXrefStatusActionServlet?psStatusDefnId="+psStatusDefnId+"&mode=edit";
		var width = 600;
		var height = 400;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	function toggleRow(row, toggle) {
		if (row == null && toggle == 'show') {
			$('.initialState').show();
			$('.expandRow').hide();
			$('.collapseRow').show();
		} else if (row == null && toggle == 'hide') {
			$('.initialState').hide();
			$('.expandRow').show();
			$('.collapseRow').hide();
		} else {
			if (toggle == 'show') {
				$('#div' + row).show();
				$('#expand' + row).hide();
				$('#collapse' + row).show();
			} else if (toggle == 'hide') {
				$('#div' + row).hide();
				$('#expand' + row).show();
				$('#collapse' + row).hide();
			}
		}
	}
</script>
<%
//cleanup
xrefList = null;
psActionCode = null;
psActionDescr = null;
psActionRemovedFlag = null;
psStatusCode = null;
psStatusDescr = null;
xrefList = null;
actionDefnList = null;
statusDefnList = null;
%>
