<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtlocationxref.PsCourtLocationXrefBO"%>
<%
List<PsCourtLocationXrefBO> xrefList = (List<PsCourtLocationXrefBO>) request.getAttribute("xrefList");
List<CourtProfileBO> courtProfileList = (List<CourtProfileBO>) request.getAttribute("courtProfileList");
List<PsCourtDefnBO> psCourtDefnList = (List<PsCourtDefnBO>) request.getAttribute("psCourtDefnList");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Problem Solving Courts - Court - Location Cross Reference</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>

	<div class="container-fluid">
		<div class="card m-2">
			<div class="card-header">
				<strong>Edit Court - Location Cross Reference</strong>
			</div>
			<div class="card-body">

				<form id="actionForm" action="" method="post">
		
				<table class="table">
					<thead class="bg-dark text-white">
						<tr>
							<% int colspan = 2; %>
							<th>Court</th>
							<th class="sorter-false">PS Courts</th>
						</tr>
					</thead>
					<tbody>
						<%
						//display the list of results
						int psCourtLocationXrefId = 0;
						int xrefCourtProfileId = 0;
						int psXrefCourtDefnId = 0;
						int psCourtDefnId = 0;
						int courtProfileId = 0;
						String courtLocationCode = "";
						String courtType = "";
						String courtTitle = "";
						String psCourtCode = "";
						String psCourtType = "";
						String psCourtDescr = "";
						String psCourtRemovedFlag = "";
						for(CourtProfileBO courtListBO : courtProfileList) {
							courtProfileId = courtListBO.getCourtProfileId();
							courtLocationCode = courtListBO.getCourtLocnCode();
							courtType = courtListBO.getCourtType();
							courtTitle = courtListBO.getCourtTitle();
							%>
							<tr>
								<td><%=courtLocationCode %> - <%=courtType %> - <%=courtTitle %></td>
								<td>
									<table class="table table-borderless">
										<tbody>
											<%
											for(PsCourtDefnBO psCourtListBO : psCourtDefnList) {
												psCourtDefnId = psCourtListBO.getPsCourtDefnId();
												psCourtCode = psCourtListBO.getPsCourtCode();
												psCourtDescr = psCourtListBO.getPsCodeDescr();
												psCourtRemovedFlag = psCourtListBO.getRemovedFlag();
												if("N".equals(psCourtRemovedFlag)){
													%>
													<tr>
														<td>
															<div class="form-check">
																<input type="checkbox" class="form-check-input" id="action-<%=courtProfileId%>-<%=psCourtDefnId%>" name="action-<%=courtProfileId%>-<%=psCourtDefnId%>" 
																<%
																if(xrefList.size() > 0){
																	for(PsCourtLocationXrefBO xrefListBO : xrefList) {
																		psCourtLocationXrefId = xrefListBO.getPsCourtLocationXrefId();
																		xrefCourtProfileId = xrefListBO.getCourtProfileId();
																		psXrefCourtDefnId = xrefListBO.getPsCourtDefnId();
																		if(courtProfileId == xrefCourtProfileId && psCourtDefnId == psXrefCourtDefnId){
																			%>
																			checked
																			<%
																		}
																	}
																}
																%>
																>
																<label class="form-check-label"><%=psCourtCode%> - <%=psCourtDescr%></label>
															</div>
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
				<input type="hidden" id="courtProfileId" value="<%=courtProfileId %>">
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
				saveRecords(data, $('#courtProfileId').val());
			});
		});
		function saveRecords(data, courtProfileId) {
			var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationRedoServlet";
			appUX.ajax.call(url, 
				function (err, data, xhr) { 
					appUX.pop.refreshCornFrame('modalCourtLocationXrefPrimary', false);
					closePop(); 
				}, 
				'POST', 
				'courtProfileId='+courtProfileId+'&mode=save&'+data
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
psCourtDefnList = null;
courtProfileList = null;
psCourtCode = null;
psCourtDescr = null;
psCourtRemovedFlag = null;
psCourtCode = null;
psCourtType = null;
psCourtDescr = null;	
%>
