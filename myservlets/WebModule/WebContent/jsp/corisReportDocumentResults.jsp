<%@page import="gov.utcourts.corisweb.report.DocumentReportSearchCriteria"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.documenttypeprofile.DocumentTypeProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.efilingactivity.EfilingActivityBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.civilcase.CivilCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.document.DocumentBO"%>
<%@page import="java.util.List"%>
<%
	List<DocumentBO> docList = request.getAttribute("docList") == null ? new ArrayList<DocumentBO>() : (List<DocumentBO>) request.getAttribute("docList");
	String courtType = (String)request.getAttribute("courtType");
	boolean withoutImgOnly = request.getAttribute("withoutImgOnly")==null?false:(Boolean)request.getAttribute("withoutImgOnly");
	DocumentReportSearchCriteria criteria = request.getAttribute("rptCriteria")==null? new DocumentReportSearchCriteria(request):(DocumentReportSearchCriteria)request.getAttribute("rptCriteria");
%>

<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<th>Date</th>
			<th>Case Number</th>
			<th>Type</th>
			<th>Tier</th>
			<th>Judge</th>
			<th>Clerk</th>
			<th>EFiled</th>
			<th>Case Sec</th>
			<th>Doc Sec</th>
			<th>Img</th>
			<th>Doc Type</th>
			<th>Title</th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<% for(DocumentBO doc:docList){ 
			String judgeName = new PersonnelBO(courtType).includeFields(PersonnelBO.LOGNAME).includeTables(new KaseBO(courtType).includeFields(KaseBO.INTCASENUM).where(KaseBO.INTCASENUM,doc.getIntCaseNum()))
								.addForeignKey(PersonnelBO.USERIDSRL, KaseBO.ASSNJUDGEID).orderBy(PersonnelBO.LOGNAME, DirectionType.DESC).find(PersonnelBO.LOGNAME).getLogname();
			List<EfilingActivityBO> efileActivities = new EfilingActivityBO(courtType).includeFields(EfilingActivityBO.EFILACTIVITYSRL)
											.where(EfilingActivityBO.INTCASENUM,doc.getIntCaseNum())
											.where(EfilingActivityBO.EFILACTIVITYTYPE,"DOCUMENT")
											.where(EfilingActivityBO.EVENTID,String.valueOf(doc.getDocumentNum())).search();
		%>
			<tr>
				<td width="6%"><%=doc.getEntryDatetime() %></td>
				<td width="7%">
					<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%=CaseHistoryAllUtil.getEncryptedUrl((String)doc.get(KaseBO.CASENUM), criteria.getCourtType(), criteria.getLocnCode(),  criteria.getLogName()) %>');">
						<%=doc.get(KaseBO.CASENUM) %>
					</a>
				</td>
				<td><%=doc.get(KaseBO.CASETYPE) %></td>
				<td><%=TextUtil.print(doc.get(CivilCaseBO.DISCOVERYTIER)) %></td>
				<td><%=judgeName %></td>
				<td><%=doc.get(PersonnelBO.LOGNAME) %></td>
				<td><%=efileActivities.size()>0?"E":"" %></td>
				<td><%=Constants.CASE_SEC_CODE_MAP.get(doc.get(KaseBO.CASESECURITY)) %></td>
				<td><%=Constants.DOC_SEC_CODE_MAP.get(doc.getDocSecurity()) %></td>
				<td><%=(doc.getDmDocid()>0 && !withoutImgOnly)?"":" N " %></td>
				<td><%=doc.get(DocumentTypeProfileBO.DDLBENTRY) %></td>
				<td><%=doc.getTitle() %></td>
			</tr>
		<%} %>
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
		
		/* $('body').removeClass('waiting'); */
</script>