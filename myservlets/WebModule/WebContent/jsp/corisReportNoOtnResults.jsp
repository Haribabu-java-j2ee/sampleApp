<%@page import="gov.utcourts.corisweb.report.CorisNoOtnReportSearchCriteria"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="gov.utcourts.corisweb.report.CorisNoOtnReport"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.crimcase.CrimCaseBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page
	import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dto.NoOtnReportDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%
	List<NoOtnReportDTO> result = request.getAttribute("reportData") == null ? new ArrayList<NoOtnReportDTO>() : (List<NoOtnReportDTO>) request.getAttribute("reportData");
	NoOtnReportDTO dto = result.size() > 0 ? result.get(0) : null;
	List<KaseBO> noOtnCases = dto != null ? dto.getKases() : new ArrayList<KaseBO>();
	CorisNoOtnReportSearchCriteria criteria = request.getAttribute("rptCriteria")==null?new CorisNoOtnReportSearchCriteria(request):(CorisNoOtnReportSearchCriteria)request.getAttribute("rptCriteria");
%>

<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<th>Case Number</th>
			<th>Defendant Name</th>
			<th>OTN Available</th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<%
			for (KaseBO kase : noOtnCases) {
				if(CorisNoOtnReport.isNoOTNCitiationCase(kase)){
		%>
		<tr>
			<td>
			<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%=CaseHistoryAllUtil.getEncryptedUrl(kase.getCaseNum(), criteria.getCourtType(), criteria.getLocnCode(),  criteria.getLogName()) %>');">
				<%=kase.getCaseNum()%>
			</a>
			</td>
			<td><%=TextUtil.print(kase.get(PartyBO.FIRSTNAME))  + " " + kase.get(PartyBO.LASTNAME)%></td>
			<td><%="N".equalsIgnoreCase((String) kase.get(PartyCaseBO.OTNAVAILABLE))?"(OTN Not Available)":"" %></td>
		</tr>

		<%
			}
			}
		%>
	</tbody>
</table>
<br>
<div class="container" id="summary">
<%
	if (dto != null) {
%>
	<div class="row">
		<label class="col-sm-8 col-md-9 control-label text-sm-left"><strong>Summary:<span class="text-danger"></span> </strong> </label>
	</div>
	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Total Cases Eligible:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalCases()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with OTNs:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalOtns()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with Valid Citations (No OTN):<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalCitations()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with neither OTN nor Citation:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getCasesWithout()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with Date of Arrest:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getCasesWithArrestDate()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>


	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with Birth Dates:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getCasesWithDob()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with DOB and OTN:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getCasesWithDobOtn()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Cases with DOA and OTN:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getCasesWithDoaOtn()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Total Charges:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalCharges()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Total Felony Charges:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalFelonyCharges()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>

	<div class="row align-items-center">
		<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Total Misdem Charges:<span class="text-danger"></span> </strong> </label>
		<div class="col-sm-12 col-md-13">
			<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong><%=dto.getTotalMisdemCharges()%><span class="text-danger"></span> </strong> </label>
		</div>
	</div>
<% } %>
</div>

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
		
</script>