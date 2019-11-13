<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dto.CorisWarrantWithPaymentsMadeDTO"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
String logName = (String) request.getAttribute("logName");
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");
List<CorisWarrantWithPaymentsMadeDTO> corisWarrantWithPaymentsMadeListDTO = (List<CorisWarrantWithPaymentsMadeDTO>) request.getAttribute("corisWarrantWithPaymentsMadeListDTO");
%>
<body>
	<table id="searchResultsTable" class="table hover row-border compact">
		<thead class="text-light bg-dark">
			<tr>
				<th>Case Number</th>
				<th>Defendant</th>
				<th>Amount Paid</th>
				<th>Judge</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody id="bodyResults">
			<% if (corisWarrantWithPaymentsMadeListDTO != null){%>
				<% for(CorisWarrantWithPaymentsMadeDTO corisWarrantWithPaymentsMadeDTO : corisWarrantWithPaymentsMadeListDTO){%>
					<tr>
						<td>
							<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%= CaseHistoryAllUtil.getEncryptedUrl(corisWarrantWithPaymentsMadeDTO.getCaseNum(), corisWarrantWithPaymentsMadeDTO.getCourtType(), corisWarrantWithPaymentsMadeDTO.getLocnCode(),  logName) %>');">
								<%=TextUtil.print(corisWarrantWithPaymentsMadeDTO.getCaseNum()) %>									
							</a>
						</td>
						<td><%=TextUtil.print(corisWarrantWithPaymentsMadeDTO.getDefLastName() + ", " + corisWarrantWithPaymentsMadeDTO.getDefFirstName())%></td>
						<td align="right"><%=TextUtil.print(Constants.receiptDecimalFormatter.format(corisWarrantWithPaymentsMadeDTO.getAmtPaid()))%></td>
						<td><%=TextUtil.print(corisWarrantWithPaymentsMadeDTO.getJudgeLastName() + ", " + corisWarrantWithPaymentsMadeDTO.getJudgeFirstName())%></td>
						<td><%=TextUtil.print(corisWarrantWithPaymentsMadeDTO.getStatus())%></td>
					</tr>
				<% } %>
			<% } %>
		</tbody>
	</table>
	<script>
		$(document).ready(function(){

	  		$('#loadingContainer').hide();
	  		
			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
		
		    $('#searchResultsTable').DataTable( {
		    	"retrieve": true,
		    	"stateSave": true,
		    	"paging": true,
		    	"info": true,
		    	"searching": true,
		        "lengthMenu": [[100, 50, 10, 1], [100, 50, 10, 1]]
		    } );
		    
			$('#searchResultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});

			loadLocation('<%=courtType%>');
			loadJudges();
		});
		
	</script>
</body>

<%
logName = null;
locnCode = null;
courtType = null;
corisWarrantWithPaymentsMadeListDTO = null;
%>