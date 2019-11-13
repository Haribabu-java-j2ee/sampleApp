
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.corisweb.report.CorisDomModiPendingReportSearchCriteria"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="java.util.Calendar"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.domesticmodifications.DomesticModificationsBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.report.CorisDomModiPendingReport"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.List"%>
<%
	CorisDomModiPendingReportSearchCriteria criteria = request.getAttribute("rptCriteria")!=null?
			(CorisDomModiPendingReportSearchCriteria)request.getAttribute("rptCriteria"):new CorisDomModiPendingReportSearchCriteria(request);
	List<KaseBO> rptData = request.getAttribute("reportData")==null? new ArrayList<KaseBO>():(List<KaseBO>)request.getAttribute("reportData");
	HashMap<String, String> logNames = null;
	List<PartyBO> parties = null;
%>
<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<th>Case Number</th>
			<th>Judge</th>
			<th>Commissioner</th>
			<th>Start Date</th>
			<th>Pending Days</th>
			<th>Filed By</th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<%
			for (KaseBO kase : rptData) {
				logNames = CorisDomModiPendingReport.getLogNameMap(kase.getUseridSrl(), kase.getAssnJudgeId(), kase.getAssnCommId(), criteria.getCourtReadOnlyDB());
				parties = CorisDomModiPendingReport.getCaseParties(kase.getIntCaseNum(), criteria.getCourtReadOnlyDB());
				long pendingDays = (long)(Calendar.getInstance().getTimeInMillis() - ((Date)kase.get(DomesticModificationsBO.STARTDATE)).getTime())/86400000;
		%>
		<tr>
			<td>
				<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%=CaseHistoryAllUtil.getEncryptedUrl(kase.getCaseNum(), criteria.getCourtType(), criteria.getLocnCode(),  criteria.getLogName()) %>');">
					<%=kase.getCaseNum()%>
				</a>
			</td>
			<td><%=TextUtil.print(logNames.get("J"))%></td>
			<td><%=TextUtil.print(logNames.get("C"))%></td>
			<td><%=TextUtil.printDate((Date)kase.get(DomesticModificationsBO.STARTDATE)) %></td>
			<td><%=pendingDays %></td>
			<td><%=TextUtil.print(logNames.get("K")) %></td>
		</tr>
			<%for(PartyBO party:parties){ %>
			<tr>
				<td></td>
				<td colspan="5"><%=party.get(PartyCaseBO.PARTYCODE) + " " + party.getLastName() + " " + party.getFirstName() %></td>
			</tr>
		<%
			}
		}
		%>
	</tbody>
</table>

<script type="text/javascript">
		$(document).ready(function() {
			$('#loadingContainer').hide();
		
			$('#searchResultsTable').DataTable({
		        "retrieve": true,
		        "stateSave": true,
				"oLanguage": {
					"sSearch": "Filter results:"
				},		        
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
				"columnDefs": [
    				{ "type": "date", "targets": 0 }
				]
		    });
			$('#searchResultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
		}); 
</script>