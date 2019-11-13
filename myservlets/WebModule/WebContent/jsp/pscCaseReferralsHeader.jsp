<!DOCTYPE html>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.kase.KaseBO"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil"%>
<%@page import="gov.utcourts.corisweb.util.RoleUtil.Role"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%
String participants1 = null;
String sids1 = null;
String dobs1 = null;
String caseNum1 = null;
int intCaseNum1 = 0;

	participants1 = (String) request.getAttribute("participants");
	sids1 = (String) request.getAttribute("sids");
	dobs1 = (String) request.getAttribute("dobs");
	intCaseNum1 = (Integer)request.getAttribute(Constants.INT_CASE_NUM);
	KaseBO kase = new KaseBO((String)request.getSession().getAttribute(SessionVariables.COURT_TYPE)).where(KaseBO.INTCASENUM,intCaseNum1).find(KaseBO.CASENUM);
	if(kase != null){
		caseNum1 = kase.getCaseNum();
	}
%>
<% if (!TextUtil.isEmpty(participants1)) { %>		
	<table class="table table-borderless">
		<tr>
			<td><strong>Name:</strong></td><td> <%=TextUtil.printWeb(participants1) %></td>
			<td><strong>Case:</strong></td><td> <%=TextUtil.printWeb(caseNum1) %></td>
		</tr>
		<tr>
			<td><strong>DOB:</strong></td><td> <%=TextUtil.print(dobs1) %></td>
			<td><strong>SID:</strong></td><td> <%=TextUtil.printWeb(sids1) %></td>
		</tr>
	</table>
<% } %>
