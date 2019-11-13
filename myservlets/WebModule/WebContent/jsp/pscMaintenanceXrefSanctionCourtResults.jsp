<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctioncourtxref.PsSanctionCourtXrefBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pssanctiondefn.PsSanctionDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%
List<PsSanctionCourtXrefBO> xrefList = (List<PsSanctionCourtXrefBO>) request.getAttribute("xrefList");
List<PsSanctionDefnBO> sanctionDefnList = (List<PsSanctionDefnBO>) request.getAttribute("sanctionDefnList");
List<CourtProfileBO> courtProfilesList = (List<CourtProfileBO>) request.getAttribute("courtProfilesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Court Type</th>
			<th>Court</th>
			<th class="sorter-false">Sanction Codes</th>
			<th class="sorter-false"><span style="white-space: nowrap;" class="text-white"><i onclick="toggleRow(null, 'show')" title="Expand All" style="cursor: pointer;" class="far fa-plus-square fa-lg fa-fw"></i><i onclick="toggleRow(null, 'hide')" title="Collapse All" style="cursor: pointer;" class="far fa-minus-square fa-lg fa-fw"></i></span></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		int psSanctionCourtId = 0;
		int psXrefSanctionDefnId = 0;
		int psXrefCourtProfileId = 0;
		int psSanctionDefnId = 0;
		String psSanctionCode = "";
		String psSanctionDescr = "";
		String psSanctionRemovedFlag = "";
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
							for(PsSanctionDefnBO sanctionListBO : sanctionDefnList) {
								psSanctionDefnId = sanctionListBO.getPsSanctionDefnId();
								psSanctionCode = sanctionListBO.getPsSanctionCode();
								psSanctionDescr = sanctionListBO.getPsSanctionDescr();
								psSanctionRemovedFlag = sanctionListBO.getRemovedFlag();
								if("N".equals(psSanctionRemovedFlag)){
									for(PsSanctionCourtXrefBO xrefListBO : xrefList) {
										psSanctionCourtId = xrefListBO.getPsSanctionCourtXrefId();
										psXrefSanctionDefnId = xrefListBO.getPsSanctionDefnId();
										psXrefCourtProfileId = xrefListBO.getCourtProfileId();
										if(psCourtProfileId == psXrefCourtProfileId && psSanctionDefnId == psXrefSanctionDefnId){
											%>
											<tr>
												<td style="margin: 0px; padding: 0px;">
													<%=psSanctionCode%> - <%=psSanctionDescr%>
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
		var url = "/CorisWEB/PSCMaintenanceXrefSanctionCourtServlet?psCourtProfileId="+psCourtProfileId+"&mode=edit";
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
psSanctionCode = null;
psSanctionDescr = null;
psSanctionRemovedFlag = null;
psCourtTitle = null;
psCourtCode = null;
psCourtType = null;
%>
