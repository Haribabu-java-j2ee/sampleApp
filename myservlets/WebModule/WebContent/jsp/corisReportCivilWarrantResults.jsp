<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.dto.CorisWarrantCivilDTO"%>
<%@page import="gov.utcourts.corisweb.util.CaseHistoryAllUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
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
String warrantStatus = (String) request.getAttribute("warrantStatus");
List<CorisWarrantCivilDTO> corisWarrantCivilListDTO = (List<CorisWarrantCivilDTO>) request.getAttribute("corisWarrantCivilListDTO");

%>
<body>
	<table id="searchResultsTable" class="table hover row-border compact">
		<thead class="text-light bg-dark">
			<tr>
				<th>Case Number</th>
				<th>Party</th>
				<th>Name</th>
				
				<% if ("Recall".equals(warrantStatus)){ %>
					<th>Recall</th>
				<%} else { %>
					<th>Issue</th>
				<% } %>
				<th>Clerk</th>
			</tr>
		</thead>
		<tbody id="bodyResults">
			<% if (corisWarrantCivilListDTO != null){%>
				<% for(CorisWarrantCivilDTO corisWarrantCivilDTO : corisWarrantCivilListDTO){%>
					<tr>
						<td>
							<a href="javascript:void(0);" title="View Case History All" onclick="displayPDF('<%= CaseHistoryAllUtil.getEncryptedUrl(corisWarrantCivilDTO.getCaseNum(), corisWarrantCivilDTO.getCourtType(), corisWarrantCivilDTO.getLocnCode(),  logName) %>');">
								<%=TextUtil.print(corisWarrantCivilDTO.getCaseNum()) %>									
							</a>
						</td>
						<td><%=TextUtil.print(corisWarrantCivilDTO.getPartyCode())%></td>
						<%if(TextUtil.isEmpty(corisWarrantCivilDTO.getPartyFirstName())){%>
							<td>
								<%=TextUtil.print(corisWarrantCivilDTO.getPartyLastName())%>
							</td>
						<%} else { %>
							<td>
								<%=TextUtil.print(corisWarrantCivilDTO.getPartyLastName() + ", " + corisWarrantCivilDTO.getPartyFirstName())%>
							</td>
						<%}%>
						</td>
						<td><%=TextUtil.printDate(corisWarrantCivilDTO.getDocumentDate())%></td>
						<td><%=TextUtil.print(corisWarrantCivilDTO.getClerkLastName() + ", " + corisWarrantCivilDTO.getClerkFirstName())%></td>
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
			
			loadLocation('<%=courtType%>');
			changeDateText();
			
		});
</script>
		
<%
	logName = null;
	locnCode = null;
	courtType = null;
	corisWarrantCivilListDTO = null;
	warrantStatus = null;
%>