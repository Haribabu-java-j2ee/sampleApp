<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.location.LocationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.tracking.TrackingBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.profile.ProfileBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.util.DateUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>  
<%
	String courtType = (String) request.getAttribute("courtType");
	String locnCode = (String) request.getAttribute("locnCode");
	Date dateFrom = (Date) request.getAttribute("dateFrom");
	Date dateTo = (Date) request.getAttribute("dateTo");
	
	List<TrackingBO> searchResults = (List<TrackingBO>) request.getAttribute("searchResults");
	
	SimpleDateFormat dateFormat = Constants.dateFormatCoris;
%>
	<table id="searchResultsTable" class="table hover row-border compact">
		<thead class="text-light bg-dark">
			<tr>
				<th>Review Date</th>
				<th>Case Number</th>
				<th></th>
				<th>Name</th>
				<th>Set Date</th>
				<th>Clerk</th>
				<th>Judge / Comm</th>
				<th>Aged Days</th>
			</tr>
		</thead>
		<tbody id="bodyResults">
			<%
				if (searchResults != null) {
					Date setDate = null;
					int agedDays = 0;
					for (TrackingBO result : searchResults) {
						StringBuilder judge = new StringBuilder();
						if ("J".equals((String) result.get("judge_type"))) {
							judge.append((String) result.get("judge_last_name")); 
							if (!TextUtil.isEmpty((String) result.get("judge_first_name"))) {
								judge.append(", ").append(result.get("judge_first_name"));
							}
						}
						
						StringBuilder commissioner = new StringBuilder();
						if ("C".equals((String) result.get("commissioner_type"))) {
							commissioner.append((String) result.get("commissioner_last_name")); 
							if (!TextUtil.isEmpty((String) result.get("commissioner_first_name"))) {
								commissioner.append(", ").append(result.get("commissioner_first_name"));
							}
						}
						
						setDate = (Date) result.get(TrackingBO.CREATEDATETIME);
						agedDays = DateUtil.getDaysBetween(new Date(), setDate);
						
						StringBuilder caseName = new StringBuilder();
						caseName.append(result.get(PartyBO.LASTNAME));
						if (!TextUtil.isEmpty((String) result.get(PartyBO.FIRSTNAME))) {
							caseName.append(", ").append(result.get(PartyBO.FIRSTNAME));
						}
			 %>
						<tr>
							<td><%= TextUtil.printDate((Date) result.get(TrackingBO.REVIEWDATE)) %></td>
							<td><%= TextUtil.print(result.get(KaseBO.CASENUM)) %></td>
							<td><%= TextUtil.print(result.get(KaseBO.CASETYPE)) %></td>
							<td><%= TextUtil.print(caseName) %></td>
							<% if (setDate != null) { %>
								<td data-sort="<%= (setDate != null) ? dateFormat.format(setDate) : "" %>"><%= dateFormat.format(setDate) %></td>
							<% } else { %>
								<td><%= TextUtil.printDate(setDate) %></td>
							<% } %>
							<td><%= TextUtil.print(result.get("clerk_logname")) %></td>
							<td><%= TextUtil.print(result.get("judge_logname")) %></td>
							<td> <%= agedDays %></td>
						</tr>
			<% 		
					}	// for
				}  // if
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
