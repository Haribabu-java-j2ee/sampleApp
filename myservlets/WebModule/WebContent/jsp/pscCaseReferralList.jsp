<!DOCTYPE html>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp"%>
<%@page import="gov.utcourts.problemsolving.xo.PsActionDefnXO"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.corisweb.xo.PsReferralXO"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="gov.utcourts.problemsolving.constants.PSCDefnConstants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.pscourtdefn.PsCourtDefnBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.courtprofile.CourtProfileBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralcase.PsReferralCaseBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psparticipant.PsParticipantBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psstatusdefn.PsStatusDefnBO"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferralhistory.PsReferralHistoryBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psreferral.PsReferralBO"%>
<%@page import="gov.utcourts.problemsolving.dataaccess.psactiondefn.PsActionDefnBO"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables" %>
<%@page import="java.util.List"%>
<%
	List<PsReferralBO> caseReferrals = (List<PsReferralBO>) request.getAttribute("caseReferrals");
	HashMap<String, Integer> actionCodeIdMap = PsActionDefnXO.getPsActionDefnCodeIdMap();
	int acceptanceActionId = actionCodeIdMap.get(PSCDefnConstants.ACTION_ADMIT_CODE);
	int completeActionId = actionCodeIdMap.get(PSCDefnConstants.ACTION_GRADUATE_CODE);
	int terminateActionId =actionCodeIdMap.get(PSCDefnConstants.ACTION_TERMINATE_CODE); 
	int intCaseNum =URLEncryption.getParamAsInt(request, Constants.INT_CASE_NUM);
	PsReferralHistoryBO meReferralHistory = request.getAttribute("meReferralHistory")==null?new PsReferralHistoryBO():(PsReferralHistoryBO)request.getAttribute("meReferralHistory");
	int meId = URLEncryption.getParamAsInt(request, Constants.MINUTE_ID);
	boolean disableAdd = request.getAttribute("hasActiveReferral")!=null? Boolean.valueOf((String)request.getAttribute("hasActiveReferral")):intCaseNum==0;	
	int meReferralId = meReferralHistory.getPsReferralId();
	boolean hasMinute = request.getAttribute("hasMinute")!=null?Boolean.valueOf((String)request.getAttribute("hasMinute")):false;
	int deletedStatusId = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSCODE,PSCDefnConstants.STATUS_DELETE_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
	int terminatedStatusId =new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSCODE,PSCDefnConstants.STATUS_TERMINATE_CODE).find(PsStatusDefnBO.PSSTATUSDEFNID).getPsStatusDefnId();
	int meHistId = meReferralHistory.getPsHistoryId();
	boolean isEditReferral = "true".equalsIgnoreCase((String)request.getAttribute("refreshAfterEdit"));
	boolean minuteDateUpdated = request.getAttribute("minuteDateUpdated")!=null?(Boolean)request.getAttribute("minuteDateUpdated"):false;
%>

<table id="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>PSC Minutes</th>
			<th>Referral Number</th>
			<th>Referral Date</th>
			<th>Problem Solving Court</th>
			<th>Problem Solving Court Location</th>
			<th>Acceptance Status</th>
			<th>Status</th>
			<th>Completion Date</th>
			<th>Unsuccessful Date</th>
			<th>Entry Creator</th>
			<th class="sorter-false"></th>
		</tr>
	</thead>
	<tbody>
		<%
			if (caseReferrals != null && caseReferrals.size() > 0) {
				for (PsReferralBO vo : caseReferrals) {
					PsReferralHistoryBO acceptanceDate = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, vo.getPsReferralId())
							.where(PsReferralHistoryBO.PSACTIONDEFNID, acceptanceActionId).find(PsReferralHistoryBO.PSACTIONDATE);
					PsReferralHistoryBO completeDate = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, vo.getPsReferralId())
							.where(PsReferralHistoryBO.PSACTIONDEFNID, completeActionId).find(PsReferralHistoryBO.PSACTIONDATE);
					PsReferralHistoryBO unsuccessfulDate = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID, vo.getPsReferralId())
							.where(PsReferralHistoryBO.PSACTIONDEFNID, terminateActionId).find(PsReferralHistoryBO.PSACTIONDATE);
					PsStatusDefnBO status = new PsStatusDefnBO().where(PsStatusDefnBO.PSSTATUSDEFNID, vo.getPsStatusDefnId()).find(PsStatusDefnBO.PSSTATUSDESCR);
					PsReferralHistoryBO logname = new PsReferralHistoryBO()
											.where(PsReferralHistoryBO.PSREFERRALID,vo.getPsReferralId())
											.orderBy(PsReferralHistoryBO.PSREFERRALID,DirectionType.DESC)
											.find(PsReferralHistoryBO.PSLOGNAME);
					hasMinute = new PsReferralHistoryBO().where(PsReferralHistoryBO.PSREFERRALID,vo.getPsReferralId()).where(PsReferralHistoryBO.MEID, Exp.GREATER_THAN, 0).search().size() > 0;
		%>
		<tr>
			<td>
			<% 
				if(vo.getPsStatusDefnId()!=deletedStatusId && meId > 0 && meReferralId ==vo.getPsReferralId() && hasMinute){ %>
					<i id="minuteBtn" title="Minutes" style="cursor: pointer;" class="text-warning far fa-edit fa-lg fa-fw"
							onclick="openMinutes(<%=vo.getPsReferralId() %>, <%=meHistId %>, <%=meId%>);"></i>
			<% }else if(vo.getPsStatusDefnId()!=deletedStatusId && meId > 0 && !isEditReferral){%> 
				<div class="form-check form-check-inline">
					<input type="checkbox" <%=meReferralId == 0?"":"disabled" %> class="form-check-input" id="meReferralId" name="meReferralId" onclick="<%=meId > 0? "openMinutes(" + vo.getPsReferralId()+"," + meHistId + "," + meId +")":""%>">
				</div>
			<%} %>
			</td>
			<td><a href="#" class="btn <%=vo.getPsStatusDefnId()==deletedStatusId?"disabled":"" %>"
					onclick="editReferral('<%=vo.getPsReferralId() %>','<%=intCaseNum %>')"><%=vo.getPsReferralId() %></a>
			</td>
			<td><%=vo.getPsReferralDate() %></td>
			<td><%=vo.get(PsCourtDefnBO.PSCODEDESCR) %></td>
			<td><%=vo.get(CourtProfileBO.COURTTITLE) %></td>
			<td><%=TextUtil.printDate(acceptanceDate.getPsActionDate(),"MM/dd/yyyy")%></td>
			<td><%=status!=null?status.getPsStatusDescr():"" %></td>
			<td><%=TextUtil.printDate(completeDate.getPsActionDate(),"MM/dd/yyyy") %></td>
			<td><%=TextUtil.printDate(unsuccessfulDate.getPsActionDate(),"MM/dd/yyyy") %></td>
			<td><%=TextUtil.print(logname.getPsLogname()) %></td>
			<td style="white-space: nowrap;">
				<i class="text-danger fas fa-file-pdf fa-lg fa-fw" title="View PDF" style="cursor: pointer;"
					onclick="window.open('/CorisWEB/PSCReferralDetailPdfServlet?referralId=' + <%=vo.getPsReferralId() %>, 'Referral Details', 'width=650, height=850');"></i>
				<% if(vo.getPsStatusDefnId()==deletedStatusId || hasMinute) {%>
					<i class="text-muted fas fa-minus-circle fa-lg fa-fw" title="<%=vo.getPsStatusDefnId()==deletedStatusId?"Removed":"Cannot delete referral - CORIS minutes exist."%>"></i>
				<%}else { %>
					<i class="text-danger fas fa-minus-circle fa-lg fa-fw" style="cursor: pointer;" title="Remove" onclick="deleteReferral('<%=vo.getPsReferralId()%>', parentCornId);"></i>
				<%} %>
				<i class="<%=vo.getPsStatusDefnId()==deletedStatusId?"text-muted":"text-danger" %> fas fa-play-circle fa-lg fa-fw" style="cursor: pointer;" title="Status History" 
					onclick="<%=vo.getPsStatusDefnId()==deletedStatusId?"":"viewActions(" + vo.getPsReferralId() + "," + meId + ");"%>"></i>
			</td>
		</tr>
		<%}}else{ %>
		<tr>
			<td colspan="10">No Referrals Found</td>
		</tr>
		<%} %>
	</tbody>
</table>

<script>
	$(document).ready(function(){
		$("#tableResults").tablesorter();
		var btn_disable = '<%=disableAdd%>';
		if('true'==btn_disable)	{
			$('#btnAddReferral').prop('disabled',true);
		}else {
			$('#btnAddReferral').prop('disabled',false);
		}
		if('<%=caseReferrals==null || caseReferrals.size()==0%>'=='true'){
			addReferral();	
		}else if('<%=meReferralId > 0 && !isEditReferral%>' == 'true'){
			openMinutes(<%=meReferralId %>, <%=meHistId%>, <%=meId%>, <%=minuteDateUpdated%>);
		}else if('<%=meId > 0 && !isEditReferral%>' == 'true'){
			var message = "Please select a referral for the minute.";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm("Select Referral", message, "OK", "Cancel", width, height, style, 
											function(action){ 
														if(action){
															$.each($('input[type=checkbox]'),function(){ $(this).removeAttr('disabled'); });
														}
														corn.close();
													 });
		}
			
	});
	
	function viewActions(psReferralId, meId){
		var url = 'jsp/pscReferralActions.jsp?psReferralId=' + psReferralId + '&parentCornId=' + parentCornId + '&meId=' + meId;
		var cornId = "viewActions";
		var title = "Statuses";
		var width = appUX.pop.windowSize().width/2;
		var height = appUX.pop.windowSize().height - 100;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
				
</script>
