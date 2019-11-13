<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idtypedefn.IdTypeDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idissuer.IdIssuerBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partyidentifier.PartyIdentifierBO"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.country.CountryBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.state.StateBO"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.party.PartyBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Party Identifier</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%
	int partyNum = TextUtil.getParamAsInt(request, "partyNum");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	int caseNum = TextUtil.getParamAsInt(request, "caseNum");
	PartyBO party = request.getAttribute("party")==null? new PartyBO(courtType):(PartyBO)request.getAttribute("party");
%>

<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
</head>
<body>
	<main> <!-- header --> <header></header> <!-- navigation -->
	<nav></nav>
	<div class="container-fluid">

		<div class="card m-2">
			<div class="card-header">
				Party Name:&nbsp;<strong><%=TextUtil.print(party.getFirstName()) + " " + TextUtil.print(party.getLastName()) %></strong> &nbsp;&nbsp;&nbsp;&nbsp;Case Number:&nbsp;<strong><%=caseNum %></strong>

			</div>
			<div class="card-body">
				<div class="form-row">
					<div class="col-12">
						<div class="form-group col-md-16 offset-lg-2 col-lg-18"></div>
					</div>
					<div class="col-12">
						<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
							<button type="submit" class="btn btn-primary ml-2 float-right" id="addBtn" onclick="addId();">Add</button>
						</div>
					</div>
				</div>
				<div id="partyIDTable">
				</div>

				<div class="std-fixed bottom bg-light popcorn-ftr active">
					<div class='mb-1 col-24'>
						<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn" onclick="closePop();">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>

	<script src="/CorisWEB/js/jquery.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/moment.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>
	<script src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%=Constants.SYSTEM_VERSION%>"></script>

	<script type="text/javascript">
	
		$(document).ready(function(){
			$('#partyIDTable').load('/CorisWEB/CorisPartyIDServlet?partyNum=<%=partyNum%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&mode=find');
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function addId(){
			var cornId = "addPartyId";
			var title = "Add Party ID";
			var url = "/CorisWEB/CorisPartyIDServlet?partyNum=<%=partyNum%>&courtType=<%=courtType%>&caseNum=<%=caseNum%>&mode=open";
			var width = 900;
			var height = 600;
			var style = appUX.pop.styles.LIGHT;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}	
	
	</script>
</body>
</html>