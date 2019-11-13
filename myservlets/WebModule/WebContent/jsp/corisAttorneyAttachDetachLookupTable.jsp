<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%
List<AttorneyBO> attyList = (List<AttorneyBO>) request.getAttribute("attyList");
int colspan2 = 0;
%>

<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<%// if table changes, please also update the columns in two places in corisAttorneyAttachDetachLookup.jsp (the placeholder and clearForm()) %>
			<% colspan2 = 11; //keep this updated as table changes so that the last column is not orderable %>
			<th>Last Name</th>
			<th>First Name</th>
			<th>Bar Number</th>
			<th>Bar State</th>
			<th>Bar Status</th>
			<th>City</th>
			<th>State</th>
			<th>Country</th>
			<th>Business Phone</th>
			<th>Email</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<%
		if(attyList != null && attyList.size() > 0){
			String listLastName = "";
			String listFirstName = "";
			int listBarNum = 0;
			String listBarState = "";
			String listBarStatus = "";
			String listCity = "";
			String listState = "";
			String listCountry = "";
			String listBusPhone = "";
			String listEmail = "";
			for(AttorneyBO temp : attyList){ 
				listLastName = temp.getLastName();
				listFirstName = temp.getFirstName();
				listBarNum = temp.getBarNum();
				listBarState = temp.getBarState();
				listBarStatus = temp.getBarStatus();
				listCity = temp.getCity();
				listState = temp.getStateCode();
				listCountry = temp.getCountry();
				listBusPhone = temp.getBusPhone();
				listEmail = temp.getEmailAddress();
				%>
				<tr>
					<td><%=TextUtil.print(listLastName) %></td>
					<td><%=TextUtil.print(listFirstName) %></td>
					<td><%=TextUtil.print(listBarNum) %></td>
					<td><%=TextUtil.print(listBarState) %></td>
					<td><%=TextUtil.print(listBarStatus) %></td>
					<td><%=TextUtil.print(listCity) %></td>
					<td><%=TextUtil.print(listState) %></td>
					<td><%=TextUtil.print(listCountry) %></td>
					<td><%=TextUtil.print(listBusPhone) %></td>
					<td><%=TextUtil.print(listEmail) %></td>
					<td nowrap>
						<% if ("A".equals(TextUtil.print(listBarStatus))) { %>
							<i title="Add to Selected Attorney(s) List" onclick='addToList(<%=TextUtil.print(listBarNum) %>, "<%=TextUtil.print(listBarState) %>", "<%=TextUtil.print(listLastName) %>", "<%=TextUtil.print(listFirstName) %>");' class="text-success fas fa-plus-circle fa-lg"></i>
						<% } else {%>
							<i title="Non-Active Attorney" class="text-muted fas fa-plus-circle fa-lg"></i>
						<% } %>
					</td>
				</tr>
				<% 

				}
			} %>
	</tbody>
</table>

<script>						
	$(document).ready(function(){

	    $('#searchResultsTable').DataTable( {
	    	"retrieve": true,
			"oLanguage": {
				"sSearch": "Filter results:"
			},
	        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
			"columnDefs": [
   				{ "orderable": false, "targets": <%= colspan2 - 1 %> }
			]
	    } );
		$('#searchResultsTable tbody tr').click(function() {
		    $(this).addClass('bg-light').siblings().removeClass('bg-light');
		});

		<%
		if(attyList != null && Constants.MAX_RESULTS == attyList.size()){
			%>
			message = "Number of results exceeds <%=Constants.MAX_RESULTS%>. Please enter additional search criteria.";
			appUX.pop.declare("Error", message, 300, 'auto', appUX.pop.styles.WARNING);
			<%		
		}
		%>

	});
	
</script>