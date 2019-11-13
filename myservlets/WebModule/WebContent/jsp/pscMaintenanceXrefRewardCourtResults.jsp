<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewarddefn.PsRewardDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psrewardcourtxref.PsRewardCourtXrefBO"%>
<%
List<PsRewardCourtXrefBO> xrefList = (List<PsRewardCourtXrefBO>) request.getAttribute("xrefList");
List<PsRewardDefnBO> rewardDefnList = (List<PsRewardDefnBO>) request.getAttribute("rewardDefnList");
List<CourtProfileBO> courtProfilesList = (List<CourtProfileBO>) request.getAttribute("courtProfilesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Court Type</th>
			<th>Court</th>
			<th class="sorter-false">Reward Codes</th>
			<th class="sorter-false"><span style="white-space: nowrap;" class="text-white"><i onclick="toggleRow(null, 'show')" title="Expand All" style="cursor: pointer;" class="far fa-plus-square fa-lg fa-fw"></i><i onclick="toggleRow(null, 'hide')" title="Collapse All" style="cursor: pointer;" class="far fa-minus-square fa-lg fa-fw"></i></span></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		int psRewardCourtId = 0;
		int psXrefRewardDefnId = 0;
		int psXrefCourtProfileId = 0;
		int psRewardDefnId = 0;
		String psRewardCode = "";
		String psRewardDescr = "";
		String psRewardRemovedFlag = "";
		int psCourtProfileId = 0;
		String psCourtTitle = "";
		String psCourtCode = "";
		String psCourtType = "";
		boolean codesResults = false;
		for(CourtProfileBO courtListBO : courtProfilesList) {
			psCourtProfileId = courtListBO.getCourtProfileId();
			psCourtCode = courtListBO.getCourtLocnCode();
			psCourtType = courtListBO.getCourtType();
			psCourtTitle = courtListBO.getCourtTitle();
			%>
			<tr>
				<td><%=psCourtType %></td>
				<td><%=psCourtTitle %></td>
				<td>
					<div id="div<%=psCourtProfileId %>" class="initialState">
					<table class="table table-borderless">
						<tbody>
							<%
							for(PsRewardDefnBO rewardListBO : rewardDefnList) {
								psRewardDefnId = rewardListBO.getPsRewardDefnId();
								psRewardCode = rewardListBO.getPsRewardCode();
								psRewardDescr = rewardListBO.getPsRewardDescr();
								psRewardRemovedFlag = rewardListBO.getRemovedFlag();
								if("N".equals(psRewardRemovedFlag)){
									for(PsRewardCourtXrefBO xrefListBO : xrefList) {
										psRewardCourtId = xrefListBO.getPsRewardCourtXrefId();
										psXrefRewardDefnId = xrefListBO.getPsRewardDefnId();
										psXrefCourtProfileId = xrefListBO.getCourtProfileId();
										if(psCourtProfileId == psXrefCourtProfileId && psRewardDefnId == psXrefRewardDefnId){
											%>
											<tr>
												<td style="margin: 0px; padding: 0px;">
													<%=psRewardCode%> - <%=psRewardDescr%>
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
					<span class="expandRow" style="cursor: pointer; display: none;" id="expand<%= psCourtProfileId %>" title="Expand" onclick="toggleRow('<%=psCourtProfileId %>', 'show');"><i class="text-primary far fa-plus-square fa-lg fa-fw"></i></span>
					<span class="collapseRow" style="cursor: pointer;" id="collapse<%= psCourtProfileId %>" title="Collapse" onclick="toggleRow('<%=psCourtProfileId %>', 'hide');"><i class="text-primary far fa-minus-square fa-lg fa-fw"></i></span>
					<% } else { %>
					<span><i class="text-white far fa-square fa-lg fa-fw"></i></span>
					<% }
					codesResults = false;
					%>
					<i onclick="editRecordPopup('<%=psCourtProfileId%>')" title="Edit" style="cursor: pointer;" class="text-warning far fa-edit fa-lg fa-fw"></i>
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
	function editRecordPopup(psCourtProfileId){
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceXrefRewardCourtServlet?psCourtProfileId="+psCourtProfileId+"&mode=edit";
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
psRewardCode = null;
psRewardDescr = null;
psRewardRemovedFlag = null;
psCourtTitle = null;
psCourtCode = null;
psCourtType = null;
%>
