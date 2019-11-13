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
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Sanction - Court Cross Reference</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Edit Sanction - Court</strong>
			</div>
			<div class="card-body">
				<form id="actionForm" action="" method="post">
		
				<table class="table">
					<thead class="bg-dark text-white">
						<tr>
							<% int colspan = 2; %>
							<th>Court</th>
							<th class="sorter-false">Sanction Codes</th>
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
						for(CourtProfileBO courtListBO : courtProfilesList) {
							psCourtProfileId = courtListBO.getCourtProfileId();
							psCourtCode = courtListBO.getCourtLocnCode();
							psCourtType = courtListBO.getCourtType();
							psCourtTitle = courtListBO.getCourtTitle();
							%>
							<tr>
								<td><%=psCourtTitle %> (<%=psCourtType %>)</td>
								<td>
									<table class="table table-borderless">
										<tbody>
											<%
											for(PsSanctionDefnBO sanctionListBO : sanctionDefnList) {
												psSanctionDefnId = sanctionListBO.getPsSanctionDefnId();
												psSanctionCode = sanctionListBO.getPsSanctionCode();
												psSanctionDescr = sanctionListBO.getPsSanctionDescr();
												psSanctionRemovedFlag = sanctionListBO.getRemovedFlag();
												if("N".equals(psSanctionRemovedFlag)){
													%>
													<tr>
														<td>
															<div class="form-check">
																<input type="checkbox" class="form-check-input" id="sanction-<%=psCourtProfileId%>-<%=psSanctionDefnId%>" name="sanction-<%=psCourtProfileId%>-<%=psSanctionDefnId%>" 
																<%
																if(xrefList.size() > 0){
																	for(PsSanctionCourtXrefBO xrefListBO : xrefList) {
																		psSanctionCourtId = xrefListBO.getPsSanctionCourtXrefId();
																		psXrefSanctionDefnId = xrefListBO.getPsSanctionDefnId();
																		psXrefCourtProfileId = xrefListBO.getCourtProfileId();
																		if(psSanctionDefnId == psXrefSanctionDefnId && psCourtProfileId == psXrefCourtProfileId){
																			%>
																			checked
																			<%
																		}
																	}
																}
																%>
																>
																<label class="form-check-label"><%=psSanctionCode%> - <%=psSanctionDescr%></label>
															</div>
														</td>
														<td>
														</td>
													</tr>
													<%
												}
											}
											%>
										</tbody>
									</table>
								</td>
							</tr>
						<%
						}
						%>
					</tbody>
				</table>
				<input type="hidden" id="psCourtProfileId" value="<%=psCourtProfileId %>">
				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<button type="button" id="cancelBtn" class="btn btn-secondary ml-2 mt-2 mr-2 btn-sm float-right" title="Cancel">Cancel</button>
					<button type="button" id="updateBtn" class="btn btn-primary ml-2 mt-2 mr-2 btn-sm float-right" title="Save">Save</button>
				</div>
				</form>

			</div>
		</div>
	</div>
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		$(document).ready(function(){
			$('#cancelBtn').on('click', function(){
				closePop();
			});	
			$('#updateBtn').on('click', function(){
				var data = $('#actionForm').serialize();
				saveRecords(data, $('#psCourtProfileId').val());
			});
		});
		function saveRecords(data, psCourtProfileId) {
			var url = "/CorisWEB/PSCMaintenanceXrefSanctionCourtServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					appUX.pop.refreshCornFrame('modalSanctionCourtXrefPrimary', false);
					closePop(); 
				}, 
				'POST', 
				'psCourtProfileId='+psCourtProfileId+'&mode=save&'+data
			);
		}
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	</script>
</body>
</html>
<%
//cleanup
xrefList = null;
sanctionDefnList = null;
courtProfilesList = null;
psSanctionCode = null;
psSanctionDescr = null;
psSanctionRemovedFlag = null;
psCourtTitle = null;
psCourtCode = null;
psCourtType = null;
%>
