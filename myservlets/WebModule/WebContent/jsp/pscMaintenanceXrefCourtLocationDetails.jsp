<!DOCTYPE html>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%
List<PsCourtLocationXrefBO> xrefList = (List<PsCourtLocationXrefBO>) request.getAttribute("xrefList");
List<CourtProfileBO> courtCodesList = (List<CourtProfileBO>) request.getAttribute("courtCodesList");
List<PsCourtDefnBO> psCourtCodesList = (List<PsCourtDefnBO>) request.getAttribute("psCourtCodesList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<div class="row std-panel-primary std-panel-md">
	<span class="fullWidth" style="width: 100%;">Details</span>
</div>

<div class="row">
	<div class="table-responsive" id="displayTheseDetails">

		<table id="tableDetails" class="table table-hover table-activate tablesorter">
			<thead class="thead-light">
				<tr>
					<% int colspan = 3; %>
					<th class="fourthWidth">Court Code</th>
					<th class="sorter-false" style="text-align: center;"><span style="white-space: nowrap;"><i onclick="$('.initialHide').show();" style="cursor: pointer;" class="text-primary fas fa-arrow-down fa-lg fa-fw"></i><i onclick="$('.initialHide').hide();" style="cursor: pointer;" class="text-primary fas fa-arrow-up fa-lg fa-fw"></i></span></th>
					<th class="sorter-false" style="width: 75%;">Ps Court Codes</th>
				</tr>
			</thead>
			<tbody>
				<%
				//display the list of details
				int psCourtLocationXrefId = 0;
				int psXrefCourtDefnId = 0;
				int psXrefCourtProfileId = 0;
				int courtProfileId = 0;
				String courtLocnCode = "";
				String courtTitle = "";
				String courtType = "";
				int psCourtDefnId = 0;
				String psCourtCode = "";
				String psCourtDescr = "";
				String psCourtType = "";
				String psCourtRemovedFlag = "";
				for(CourtProfileBO courtListBO : courtCodesList) {
					courtProfileId = courtListBO.getCourtProfileId();
					courtLocnCode = courtListBO.getCourtLocnCode();
					courtTitle = courtListBO.getCourtTitle();
					courtType = courtListBO.getCourtType();
					{
					%>
					<tr>
						<td><span style="cursor: pointer;" onclick="$('#div<%=courtProfileId %>').toggle();"><%=courtLocnCode %> - <%=courtTitle %> (<%=courtType %>)</span></td>
						<td style="text-align: center;"><span style="cursor: pointer;" onclick="$('#div<%=courtProfileId %>').toggle();"><i class="text-primary fas fa-arrows-alt-h fa-lg fa-fw"></i></span></td>
						<td>
							<div id="div<%=courtProfileId %>" class="initialHide">
							<form id="actionForm" action="#" method="post">
						<%
						for(PsCourtDefnBO psCourtCodesListBO : psCourtCodesList) {
							psCourtDefnId = psCourtCodesListBO.getPsCourtDefnId();
							psCourtCode = psCourtCodesListBO.getPsCourtCode();
							psCourtDescr = psCourtCodesListBO.getPsCodeDescr();
							psCourtType = psCourtCodesListBO.getPsCodeType();
							psCourtRemovedFlag = psCourtCodesListBO.getRemovedFlag();
							if("N".equals(psCourtRemovedFlag)){
							%>
								<div class="form-check">
									<%
									if(xrefList.size() > 0){
										for(PsCourtLocationXrefBO xrefListBO : xrefList) {
											psCourtLocationXrefId = xrefListBO.getPsCourtLocationXrefId();
											psXrefCourtDefnId = xrefListBO.getPsCourtDefnId();
											psXrefCourtProfileId = xrefListBO.getCourtProfileId();
											if(psCourtDefnId == psXrefCourtDefnId && courtProfileId == psXrefCourtProfileId){
												%>
												<input type="checkbox" class="form-check-input" checked onclick="deleteRecord(<%=psCourtLocationXrefId%>);">
												<%
												break;
											}else{
												%>
												<input type="checkbox" class="form-check-input" onclick="addRecord(<%=courtProfileId%>, <%=psCourtDefnId%>);">
												<%
											}
										}
									}else{
										%>
										<input type="checkbox" class="form-check-input" onclick="addRecord(<%=courtProfileId%>, <%=psCourtDefnId%>);">
										<%
									}
									%>
									<label class="form-check-label"><%=psCourtCode%> - <%=psCourtDescr%></label>
								</div>
							<%
							}
						}
						%>
							</form>
							</div>
						</td>
					</tr>
					<%
					}
				}
				%>
			</tbody>
		</table>
	
	</div>
</div>
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>
	function displayHeader(url) {
		appUX.ajax.call(url, function (err, data, xhr) { $("#displayHeader").html(data); }, 'POST', 'mode=displayHeader'); 
	}
	function displayList() {
		var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
		appUX.ajax.call(url, 
			function(err, data, xhr) {
				$("#displayDetails").html(data);
			}, 
			'POST', 
			'mode=displayDetails'
		); 
	}
	function addRecord(courtProfileId, psCourtDefnId) {
		var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
		appUX.ajax.call(url, 
			function (err, data, xhr) { 
				/*do nothing*/ 
			}, 
			'POST', 
			'courtProfileId='+courtProfileId+'&psCourtDefnId='+psCourtDefnId+'&mode=add'
		);
	}
	function deleteRecord(psCourtLocationXrefId){
		var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationServlet";
		appUX.ajax.call(url, 
			function (err, data, xhr) { 
				/*do nothing*/ 
			}, 
			'POST', 
			'psCourtLocationXrefId='+psCourtLocationXrefId+'&mode=delete'
		);
	}
</script>
<%
//cleanup
xrefList = null;
courtLocnCode = null;
courtTitle = null;
psCourtCode = null;
psCourtDescr = null;
psCourtType = null;
psCourtRemovedFlag = null;
%>
