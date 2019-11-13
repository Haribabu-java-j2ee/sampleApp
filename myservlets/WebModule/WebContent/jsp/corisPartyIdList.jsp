<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.country.CountryBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idissuer.IdIssuerBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idtypedefn.IdTypeDefnBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partyidentifier.PartyIdentifierBO"%>
<%@page import="java.util.List"%>

<%
	int partyNum = TextUtil.getParamAsInt(request, "partyNum");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	int caseNum = TextUtil.getParamAsInt(request, "caseNum");
	List<PartyIdentifierBO> partyIds = request.getAttribute("partyIds")==null?new ArrayList<PartyIdentifierBO>():(List<PartyIdentifierBO>)request.getAttribute("partyIds"); 
	HashMap<Integer, IdIssuerBO> partyIssuerMap = (HashMap<Integer,IdIssuerBO>)request.getAttribute("partyIssuerMap");
 %>

<table id="searchResultsTable" class="table hover row-border compact">
	<thead class="text-light bg-dark">
		<tr>
			<th>ID Type</th>
			<th>ID Number</th>
			<th>ID Issuer State</th>
			<th>ID Issuer Country</th>
			<th></th>
		</tr>
	</thead>
	<tbody id="bodyResults">
		<%
			IdIssuerBO issuer = null;
			for(PartyIdentifierBO pid:partyIds){
				if(!TextUtil.isEmpty(pid.getIdNumber())){
					issuer = (IdIssuerBO)partyIssuerMap.get(pid.getPartyIdentifierId());										
		%>
		<tr>
			<td><a href="#" onclick="editId('<%=pid.getPartyIdentifierId() %>');"> <%=pid.get(IdTypeDefnBO.TYPEDESCR) %> </a>
			</td>
			<td><%=TextUtil.print(pid.getIdNumber()) %></td>
			<td><%=TextUtil.print(issuer.get(StateBO.NAME)) %></td>
			<td><%=issuer.get(CountryBO.COUNTRYNAME) %></td>
			<td nowrap>
				<i title="Edit" id="editId" onclick="editId('<%=pid.getPartyIdentifierId() %>');" class="text-primary fas fa-address-card fa-lg"></i> 
				<i class="text-danger fas fa-minus-circle fa-lg fa-fw" style="cursor: pointer;" title="Delete" onclick="deleteId('<%=pid.getPartyIdentifierId()%>');"></i>
			</td>
		</tr>
		<% 		}   
			} %>
	</tbody>
</table>
<script>

	$(document).ready(function(){
	
		$("svg").on( "mouseenter", function() { //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
			$(this).css("cursor", "pointer" );
		});
		$("svg").on( "mouseleave", function() {
			$(this).css("cursor", "default" );
		});
	
	});
		
		function editId(pIdId){
			var cornId = "editPartyId";
			var title = "Edit Party ID";
			var url = '/CorisWEB/CorisPartyIDServlet?partyNum=<%=partyNum%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&mode=open&partyIdId=' + pIdId;
			var width = 900;
			var height = 600;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
		
		function deleteId(partyIdId){
			var title = "Delete Action";
			var message = "Would you like to delete the ID?";
			var trueText = "Delete";
			var falseText = "Cancel";
			var width = 300;
			var height = "auto";
			var style = appUX.pop.styles.PRIMARY;
			var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
		
			function confirmCallback(action) {
				if (action) {
					appUX.ajax.call("/CorisWEB/CorisPartyIDServlet", 
						function (err, data, xhr) { 
							var jsonObj = JSON.parse(data);
							if(err){
								message = "Error: Changes were not saved.";
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}else if (jsonObj && jsonObj.error) {
								appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							}else{
								 $('#partyIDTable').load('/CorisWEB/CorisPartyIDServlet?partyNum=<%=partyNum%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&mode=find');
							}
						}, 
						'POST', 
						'mode=delete&partyIdId=' + partyIdId
					);	
			}
			corn.close();
		}
	}
</script>