<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dto.CorisWarrantDTO"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%

String caseNum = (String) request.getAttribute("caseNum");
String logName = (String) request.getAttribute("logName");
String locnCode = (String) request.getAttribute("locnCode");
String courtType = (String) request.getAttribute("courtType");

List<CorisWarrantDTO> corisWarrantListDTO = (List<CorisWarrantDTO>) request.getAttribute("corisWarrantListDTO");

//encrypt the URL
//URLEncryption urlCaseHistoryAllCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");
//String theUrl = "logName="+logName;
%>

<body>
	<table id="searchResultsTable" class="table hover row-border compact">
		<thead class="text-light bg-dark">
			<tr>
				<th>Case Number</th>
				<th>Defendant</th>
				<th>Birth Date</th>
				<th>Amount</th>
				<th>Judge</th>
				<th>Order</th>
				<th>Issue</th>
				<th>Expire</th>
				<th>Recall</th>
			</tr>
		</thead>
		<tbody id="bodyResults">
			<% if (corisWarrantListDTO != null){%>
				<% for(CorisWarrantDTO corisWarrantDTO : corisWarrantListDTO){%>
					<tr>
						<td>
							<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%= CaseHistoryAllUtil.getEncryptedUrl(corisWarrantDTO.getCaseNum(), corisWarrantDTO.getCourtType(), corisWarrantDTO.getLocnCode(),  logName) %>');">
								<%=TextUtil.print(corisWarrantDTO.getCaseNum()) %>									
							</a>
						</td>
						<td><%=TextUtil.print(corisWarrantDTO.getDefLastName() + ", " + corisWarrantDTO.getDefFirstName())%></td>
						<td><%=TextUtil.printDate(corisWarrantDTO.getBirthDate())%></td>
						<td align="right"><%=TextUtil.print(Constants.receiptDecimalFormatter.format(corisWarrantDTO.getBailAmt()))%></td>
						<td><%=TextUtil.print(corisWarrantDTO.getJudgeLastName() + ", " + corisWarrantDTO.getJudgeFirstName())%></td>
						<td><%=TextUtil.printDate(corisWarrantDTO.getOrderDate())%></td>
						<td><%=TextUtil.printDate(corisWarrantDTO.getIssueDate())%></td>
						<td><%=TextUtil.printDate(corisWarrantDTO.getExpDate())%></td>
						<td><%=TextUtil.printDate(corisWarrantDTO.getRecallDate())%></td>
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

			$('#warrantStatusId').focus();
		});
		
		//this is to display the Case History All or Single PDF
		function displayPDF(encryptedUrl){
		
			//loading spinner html
			var htmlDisplay = 
				'<div style="width: 100%; text-align: center; padding: 20px;">'
					+ '<span style="padding: 5px; font-size: 16px; border: 1px solid rgba(0,0,0,.25); color: rgba(0,0,0,.5);">'
						+ '<span class="fa fa-spinner fa-spin"></span>'
						+ ' Loading'
					+ '</span>'
				+ '</div>'
			;
			//open a new window
			var pdf = window.open("", "PDF", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
			if(pdf) {
				pdf.document.write(htmlDisplay); //display loading spinner
				pdf.location.href = encryptedUrl; //replace the window with the contents of the PDF
			} else {
				var message = "To view this PDF, please allow popups in the browser.";
				appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
			}
		}
	</script>
<body>
<%
	logName = null;
	locnCode = null;
	courtType = null;
	corisWarrantListDTO = null;
%>