<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dto.NsfChecksDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.RevenueHeLeaDTO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casefeature.CaseFeatureBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.schooldistrict.SchoolDistrictBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.transdist.TransDistBO"%>
<%@page import="gov.utcourts.corisweb.report.CorisReportRevDistSumSearchCriteria"%>
<%@page import="gov.utcourts.coriscommon.dto.RevenueProsecDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.RevenueDetailDTO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.dto.RevenueDistributionSummaryDTO"%>
<%
	CorisReportRevDistSumSearchCriteria criteria = request.getAttribute("rptCriteria")==null? new CorisReportRevDistSumSearchCriteria(request):(CorisReportRevDistSumSearchCriteria) request.getAttribute("rptCriteria");
	List<RevenueDistributionSummaryDTO> results = request.getAttribute("reportData")==null? new ArrayList<RevenueDistributionSummaryDTO>()
																		:(List<RevenueDistributionSummaryDTO>)request.getAttribute("reportData");
	RevenueDistributionSummaryDTO rptData =results.size() > 0? results.get(0):new RevenueDistributionSummaryDTO();
	List<RevenueDetailDTO> revDetails = rptData.getDistrDetails();
	List<RevenueProsecDTO> revProsecs = rptData.getRevenueProsecs();
	List<TransDistBO> transDists = rptData.getYbDistributions();
	List<RevenueHeLeaDTO> revHeLeas = rptData.getRevenueHeLeas();
	List<NsfChecksDTO> nsfChks = rptData.getNsfChecks();
	double heleaAccum = 0;
	double rvLeaAccum = 0;
	double ybSchlTotal = 0;
   	double ybStateTotal = 0;
   	double stateFnedew = 0;
   	double stateHe = 0;
   	double stateIr = 0;
   	double stateTr = 0;
   	double zero = 0;
   	double totalNsf=0;
   	double totalRev = 0;
   	double prosecFnedew = 0;
   	double fnedewAmt, heAmt, irAmt;
	double prosecHe = 0;
	double prosecIr = 0;
	double prosecTr = 0;
 %>

<table id="searchResultsTable" class="table hover no-border compact">
	<thead class="text-dark bg-grey">
		<tr>
			<th>REVENUE APPLIED TO:</th>
			<th>______________________</th>
			<th>______________________</th>
			<th colspan="5"></th>
			
		</tr>
		<tr>
			<th></th>
			<th>Org # </th>
			<th>for Week Ending</th>
			<th colspan="5"></th>
		</tr>
		<tr height="20px"><th colspan="8"></th></tr>
		<tr>
			<th>Account Number</th>
			<th>Description</th>
			<th>Revenue Code</th>
			<th>Amount</th>
			<th>Quantity</th>
			<th colspan="3"></th>
		</tr>
		
	</thead>
	<tbody>
		<% 	
			for(RevenueDetailDTO detail:revDetails){ 
				totalRev += detail.getRevenue().doubleValue();
		%>
		<tr>
			<td><%=detail.getAcctNum() %></td>
			<td><%=detail.getDescr() %></td>
			<td><%=detail.getDistrbCode() %></td>
			<td><%=detail.getRevenue() %></td>
			<td><%=detail.getCount() %></td>
			<th colspan="3"></th>
		</tr>
		<% if("ST".equals(detail.getDistrbCode())) { 
			double stCnty = detail.getRevenue().doubleValue()*0.625;
			double stJuvSec = detail.getRevenue().doubleValue()*0.25;
		%>
			<tr>
				<td colspan="3"><%="County - 62.5%" %></td> <td colspan="5"><%=stCnty %></td>
			</tr>
			<tr>
				<td colspan="3"><%="Court Security - 25%" %></td> <td colspan="5"><%=stJuvSec %></td>
			</tr>
			<tr>
				<td colspan="3"><%="Technology - 12.5%" %></td> <td colspan="5"><%=detail.getRevenue().doubleValue() - stCnty - stJuvSec %></td>
			</tr>	
		<%} %>
		<%} %>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr>
			<td></td>
			<th>TOTAL REVENUE:</th> 
			<td></td>
			<td colspan="5"><%=String.format("%.2f", totalRev) %></td>
		</tr>
		<tr height="20px"><td colspan="8"></td></tr>
	
		<tr>
			<th colspan="8">DISTRIBUTION SPLITS</th>
		</tr>
		<tr>
			<th>PROSECUTOR</th>
			<th>FN+ED+EW+PN</th>
			<th>YB</th>
			<th>HE</th>
			<th>IR</th>
			<th>RV</th>
			<th>TR</th>
			<th>Total</th>
		</tr>
   </thead>
   <tbody id="bodyParty4">
		<% 
			for(RevenueProsecDTO dto:revProsecs){ 
				fnedewAmt = dto.getFnAmt().doubleValue()/2 + dto.getEdAmt().doubleValue()/2 + dto.getEwAmt().doubleValue()/2 + dto.getPnAmt().doubleValue()/2;
				heAmt = "J".equals(criteria.getCourtType())?dto.getHeAmt().doubleValue()/2:0;
				irAmt = dto.getIrAmt().doubleValue()/2;
				prosecFnedew += fnedewAmt;
				prosecHe +=heAmt;
				prosecIr +=irAmt;
				prosecTr +=dto.getTrAmt().doubleValue();
				stateFnedew += fnedewAmt;
				stateIr += dto.getIrAmt().doubleValue()/2;
				stateTr += dto.getTrAmt().doubleValue()/2;
		%>
			<tr>
				<td><%=dto.getProsecAgency() + " " + dto.getProsecDescr() %></td>
				<td><%=String.format("%.2f", fnedewAmt) %></td>
				<td></td>
				<td><%=heAmt %></td>
				<td><%=irAmt %></td>
				<td><%=zero %></td>
				<td><%=dto.getTrAmt().doubleValue() %></td>
				<td><%=String.format("%.2f", fnedewAmt + heAmt + irAmt + dto.getTrAmt().doubleValue()) %></td>
			</tr>
		<%} %>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr><th>PROSECUTOR SUBTOTALS:</th>
			<td><%=String.format("%.2f",prosecFnedew) %></td>
			<td></td>
			<td><%=String.format("%.2f",prosecHe) %></td>
			<td><%=String.format("%.2f",prosecIr) %></td>
			<td><%=String.format("%.2f",zero) %></td>
			<td><%=String.format("%.2f",prosecTr) %></td>
			<td><%=String.format("%.2f", prosecFnedew + prosecHe + prosecIr + prosecTr) %></td>
		</tr>
	</thead>
	<tbody>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody>
	<!-- part 50 to 52 -->
	<% if(rptData.getYbTotal() > 0) {%>
		<thead class="text-dark bg-grey">
			<tr>
				<th colspan="8">20% FINE FOR SCHOOL BUS OFFENSES </th>
			</tr>
		<% 
			for(TransDistBO trans:transDists){
				ybSchlTotal +=trans.getAmtPaid().doubleValue();
				ybStateTotal = rptData.getYbTotal() - ybSchlTotal;
	 	%>		
	 		<tr>
	 			<td colspan="2"><%=trans.get(CaseFeatureBO.FEATUREVALUE) + " " + trans.get(SchoolDistrictBO.SCHOOLSHORTNAME) %></td>
	 			<td colspan="6"><%=(Double)trans.get("amtSum") %></td>
	 		</tr>
	 
	 	<%	} %>
	 	<tr height="20px"><td colspan="8"></td></tr>
		</thead>

	<thead class="text-dark bg-grey">
		<tr>
			<th colspan="2">SCHOOL SUBTOTALS:</th>
			<td colspan="4"><%=ybSchlTotal %></td>
			<td></td>
			<td><%=String.format("%.2f", ybSchlTotal) %></td>
		</tr>
	</thead>
	<% }%>
		
	<!-- part 101 10 -->
	<thead>
		<tr>
			<th colspan="8">HIGHER ED CAMPUS LEA</th>
		</tr>
	</thead>
	<tbody>
	<% for(RevenueHeLeaDTO helea:revHeLeas) {
		heleaAccum +=helea.getHeLeaAmt();
		rvLeaAccum +=helea.getRevLeaAmt();
	%>
		<tr>
			<td><%=helea.getLea() %></td>
			<td><%=helea.getLeaDescr() %></td>
			<td><%=!"J".equals(criteria.getCourtType())?helea.getHeLeaAmt()/2:helea.getHeLeaAmt() %></td>
			<td><%=helea.getRevLeaAmt() %></td>
			<td><%=String.format("%.2f", helea.getHeLeaAmt() + helea.getRevLeaAmt()) %></td>
			<td colspan="3"></td>
		</tr>
	<% } %>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr><th>HIGHER ED / LEA SUBTOTAL:</th>
			<td colspan="2"></td>
			<td><%=String.format("%.2f",heleaAccum) %></td>
			<td></td>
			<td><%=String.format("%.2f",rvLeaAccum) %></td>
			<td></td>
			<td><%=String.format("%.2f", heleaAccum + rvLeaAccum) %></td>
		</tr>
	</thead>
	<tbody>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr><th>STATE SUBTOTALS:</th>
			<td><%=String.format("%.2f",stateFnedew) %></td>
			<td><%=String.format("%.2f",ybStateTotal) %></td>
			<td><%=String.format("%.2f",stateHe) %></td>
			<td><%=String.format("%.2f",stateIr) %></td>
			<td><%=String.format("%.2f",zero) %></td>
			<td><%=String.format("%.2f",stateTr) %></td>
			<td><%=String.format("%.2f", stateFnedew + ybStateTotal + stateHe + stateIr + stateTr) %></td>
		</tr>
	</thead>
	<tbody>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr><th>GRAND TOTALS:</th>
			<td><%=String.format("%.2f",prosecFnedew + stateFnedew) %></td>
			<td><%=rptData.getYbTotal() %></td>
			<td><%=String.format("%.2f",prosecHe + heleaAccum + stateHe) %></td>
			<td><%=String.format("%.2f",prosecIr + stateIr) %></td>
			<td><%=String.format("%.2f",rvLeaAccum) %></td>
			<td><%=String.format("%.2f",prosecTr + stateTr) %></td>
			<td><%=String.format("%.2f",prosecFnedew + prosecHe + prosecIr + prosecTr + stateFnedew + stateHe + stateIr + stateTr + rptData.getYbTotal() + heleaAccum + rvLeaAccum) %></td>
		</tr>
		<tr>
			<th></th>
			<th>FN+ED+EW+PN</th>
			<th>YB</th>
			<th>HE</th>
			<th>IR</th>
			<th>RV</th>
			<th>TR</th>
			<th>Total</th>
		</tr>
		<tr height="20px"><td colspan="8"></td></tr>
		<tr><th colspan="8">DEPOSIT SUMMARY</th></tr>
		<tr><th colspan="8">DEPOSITS:</th></tr>
		<tr>
			<th></th>
			<th>Journal</th>
			<th>Journal Date</th>
			<th>Amount</th>
			<th colspan="4"></th>
		</tr>
	</thead>
	<tbody>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody> <!-- empty body for deposit as stored procedure has empty body and returns nothing -->
	<thead>
		<tr>
			<th></th>
			<th>TOTAL DEPOSITS:</th>
			<th colspan="6"></th>
		</tr>
		<tr height="20px"><td colspan="8"></td></tr>
	</thead>
	
	<!-- part 8 NSFs -->
	<thead>
		<tr>
			<th colspan="8">NSF REVERSALS:</th>
		</tr>
		<tr>
			<th></th>
			<th>Payor</th>
			<th>Case</th>
			<th>Amount</th>
			<th>Type</th>
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody id="bodyParty8">
		<% for(NsfChecksDTO nsf:nsfChks) {
			totalNsf +=nsf.getTransAmt();
		%>
			<tr>
				<td></td>
				<td><%=nsf.getFirstName() + " " + nsf.getLastName() %></td>
				<td><%=nsf.getCaseNum() %></td>
				<td><%=nsf.getTransAmt() %></td>
				<td><%="N".equals(nsf.getOutType())?"NSF":"Disputed Card" %></td>
				<td colspan="3"></td>
			</tr>
		<% } %>
		<tr height="20px"><td colspan="8"></td></tr>
	</tbody>
	<thead class="text-dark bg-grey">
		<tr>
			<th></th>
			<th>TOTAL NSF REVERSALS:</th>
			<th colspan="6"><%=String.format("%.2f", totalNsf) %></th>
		</tr>
		<tr height="20px"><td colspan="8"></td></tr>
		<tr>
			<th>_______________________</th><th colspan="7"> __________________</th>
		</tr>
		<tr>
			<th>Prepared by</th><th colspan="7">Phone #</th>
		</tr>
	</thead>
</table>