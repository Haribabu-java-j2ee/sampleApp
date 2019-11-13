
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.corisweb.report.CorisDomViolReportCriteria"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.corisweb.report.CorisDomViolReport"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.charge.ChargeBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.List"%>
<%
	List<KaseBO> results = request.getAttribute("reportData") == null ? new ArrayList<KaseBO>() : (List<KaseBO>) request.getAttribute("reportData");
	String courtType = (String) request.getSession().getAttribute(SessionVariables.COURT_TYPE) + "_READONLY";
	CorisDomViolReportCriteria criteria = request.getAttribute("rptCriteria")==null?new CorisDomViolReportCriteria(request):(CorisDomViolReportCriteria)request.getAttribute("rptCriteria");
 %>
<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<th>Case</th>
			<th>Type</th>
			<th>Defendant</th>
			<th>Filing Date</th>
			<th>Disposition Date</th>
			<th>Charge</th>
			<th>DV Level</th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<% for(KaseBO kase:results) { 
			String lvl = CorisDomViolReport.calcDomViolLevel(kase.getIntCaseNum(),courtType);
			if(lvl != null && CorisDomViolReport.isDvCharge(kase.getIntCaseNum(),(Integer)kase.get(ChargeBO.SEQ), courtType)){
		%>
		<tr>
			<td>
				<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%=CaseHistoryAllUtil.getEncryptedUrl(kase.getCaseNum(), criteria.getCourtType(), criteria.getLocnCode(),  criteria.getLogName()) %>');">
					<%=kase.getCaseNum() %>
				</a>
			</td>
			<td><%=kase.getCaseType() %></td>
			<td><%=kase.get(PartyBO.LASTNAME) + ", " + kase.get(PartyBO.FIRSTNAME) %></td>
			<td><%=TextUtil.printDate(kase.getFilingDate()) %></td>
			<td><%=TextUtil.printDate((Date)kase.get(ChargeBO.JDMTDATE)) %></td>
			<td><%=kase.get(ChargeBO.SEQ) %></td>
			<td><%=lvl %></td>
		</tr>
		<%} 
		}%>
	</tbody>
</table>

<script type="text/javascript">
		$(document).ready(function() {
			$('#searchResultsTable').DataTable( {
		        "retrieve": true,
		        "stateSave": true,
				"oLanguage": {
					"sSearch": "Filter results:"
				},		        
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
				"columnDefs": [
    				{ "type": "date", "targets": 0 }
				]
		    } );
			$('#searchResultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});

		}); 
		
		$('body').removeClass('waiting');
</script>