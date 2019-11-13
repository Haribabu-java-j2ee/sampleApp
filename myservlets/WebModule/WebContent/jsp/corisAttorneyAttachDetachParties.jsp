<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.coriscommon.util.CorisSecurityCommon"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partycase.PartyCaseBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attyparty.AttyPartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%
String caseNum = "";
if(!TextUtil.isEmpty((String) request.getAttribute("caseNum"))){
	caseNum = (String) request.getAttribute("caseNum");
}
if(!TextUtil.isEmpty((String) request.getParameter("caseNum"))){
	caseNum = (String) request.getParameter("caseNum");
}
List<KaseBO> singleCasePartyList = (List<KaseBO>) request.getAttribute("singleCasePartyList");

HashMap<String, Boolean> userAccess = (HashMap<String, Boolean>) request.getAttribute("userAccess");
%>
<div class='input-group col-md-7 col-12'>
	<%//for single, populate with the party types of the parties on this particular case %>
	<% if(!TextUtil.isEmpty(caseNum)){ %>
		<% if(singleCasePartyList.size() > 0){
			int partyNum = 0;
			String partyType = "";
			String partyLastName = "";
			String partyFirstName = "";
			String safeguarded = "";
			int counter1 = 0;
			for(KaseBO temp : singleCasePartyList){
				counter1++;
				partyNum = (Integer) temp.get(PartyCaseBO.PARTYNUM);
				partyType = (String) temp.get(PartyCaseBO.PARTYCODE);
				partyLastName = (String) temp.get(PartyBO.LASTNAME);
				partyFirstName = (String) temp.get(PartyBO.FIRSTNAME);
				%>
				<div class="form-check col-24">
					<input type="checkbox" class="form-check-input" id="selectParties<%=counter1 %>" name="selectParties<%=counter1 %>" value="<%=partyType %>-<%=partyNum %>">
					<label class="form-check-label"><%=partyType %> - 
						<% if (CorisSecurityCommon.hasPartyAccess(userAccess, (String) temp.getCaseType(), (String) temp.get(PartyCaseBO.PARTYCODE))) { %>
							<%=partyLastName %><%=(!TextUtil.isEmpty(partyFirstName))?", "+partyFirstName:"" %>
						<% } else { %>
							***, ***
						<% } %>
					</label>
				</div>
				<%
			}
			counter1 = 0;
		} else if(!TextUtil.isEmpty(caseNum)){ %>
			<div class="form-check col-24 text-danger">
				No parties found for case <%=TextUtil.print(caseNum) %>
			</div>
		<% } %>
	<% } else { %>
			<div class="form-check col-24 text-danger">
				Please enter Case Number to display parties for that case.
			</div>
	<% } %>
</div>
