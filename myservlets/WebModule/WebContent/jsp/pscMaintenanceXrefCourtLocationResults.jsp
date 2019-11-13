<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO"%>
<%
List<PsCourtLocationXrefBO> xrefList = (List<PsCourtLocationXrefBO>) request.getAttribute("xrefList");
List<CourtProfileBO> courtProfileList = (List<CourtProfileBO>) request.getAttribute("courtProfileList");
List<PsCourtDefnBO> psCourtDefnList = (List<PsCourtDefnBO>) request.getAttribute("psCourtDefnList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>Court Code</th>
			<th>Court Type</th>
			<th>Court Title</th>
			<th class="sorter-false">Problem Solving Courts</th>
			<th class="sorter-false"><span style="white-space: nowrap;" class="text-white"><i onclick="toggleRow(null, 'show')" title="Expand All" style="cursor: pointer;" class="far fa-plus-square fa-lg fa-fw"></i><i onclick="toggleRow(null, 'hide')" title="Collapse All" style="cursor: pointer;" class="far fa-minus-square fa-lg fa-fw"></i></span></th>
		</tr>
	</thead>
	<tbody>
		<%
		//display the list of results
		int psCourtLocationXrefId = 0;
		int xrefCourtProfileId = 0;
		int psXrefCourtDefnId = 0;
		int courtProfileId = 0;
		String courtLocnCode = "";
		String courtType = "";
		String courtTitle = "";
		int psCourtDefnId = 0;
		String psCourtCode = "";
		String psCourtType = "";
		String psCourtDescr = "";
		String psCourtRemovedFlag = "";
		boolean codesResults = false;
		for(CourtProfileBO courtProfileBO : courtProfileList) {
			courtProfileId = courtProfileBO.getCourtProfileId();
			courtLocnCode = courtProfileBO.getCourtLocnCode();
			courtType = courtProfileBO.getCourtType();
			courtTitle = courtProfileBO.getCourtTitle();
			%>
			<tr>
				<td><%=courtProfileId %></td>
				<td><%=courtLocnCode %></td>
				<td><%=courtTitle %></td>
				<td>
					<div id="div<%=courtProfileId %>" class="initialState">
					<table class="table table-borderless">
						<tbody>
							<%
							for(PsCourtDefnBO psCourtDefnListBO : psCourtDefnList) {
								psCourtDefnId = psCourtDefnListBO.getPsCourtDefnId();
								psCourtCode = psCourtDefnListBO.getPsCourtCode();
								psCourtDescr = psCourtDefnListBO.getPsCodeDescr();
								psCourtRemovedFlag = psCourtDefnListBO.getRemovedFlag();
								if("N".equals(psCourtRemovedFlag)){
									for(PsCourtLocationXrefBO xrefListBO : xrefList) {
										psCourtLocationXrefId = xrefListBO.getPsCourtLocationXrefId();
										xrefCourtProfileId = xrefListBO.getCourtProfileId();
										psXrefCourtDefnId = xrefListBO.getPsCourtDefnId();
										if(courtProfileId == xrefCourtProfileId && psCourtDefnId == psXrefCourtDefnId){
											%>
											<tr>
												<td style="margin: 0px; padding: 0px;">
													<%=psCourtCode%> - <%=psCourtDescr%>
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
					<span class="expandRow" style="cursor: pointer; display: none;" id="expand<%= courtProfileId %>" title="Expand" onclick="toggleRow('<%=courtProfileId %>', 'show');"><i class="text-primary far fa-plus-square fa-lg fa-fw"></i></span>
					<span style="cursor: pointer;" class="collapseRow" id="collapse<%= courtProfileId %>" title="Collapse" onclick="toggleRow('<%=courtProfileId %>', 'hide');"><i class="text-primary far fa-minus-square fa-lg fa-fw"></i></span>
					<% } else { %>
					<span><i class="text-white far fa-square fa-lg fa-fw"></i></span>
					<% }
					codesResults = false;
					%>
					<i onclick="editRecordPopup('<%=courtProfileId%>')" title="Edit" style="cursor: pointer;" class="text-warning far fa-edit fa-lg fa-fw"></i>
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
	function editRecordPopup(courtProfileId){
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationRedoServlet?courtProfileId="+courtProfileId+"&mode=edit";
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
courtLocnCode = null;
courtTitle = null;
psCourtRemovedFlag = null;
psCourtCode = null;
psCourtType = null;
psCourtDescr = null;
xrefList = null;
courtProfileList = null;
psCourtDefnList = null;
%>
