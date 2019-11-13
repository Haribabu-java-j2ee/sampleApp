<%@page import="java.util.ArrayList"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idtypedefn.IdTypeDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.idissuer.IdIssuerBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.partyidentifier.PartyIdentifierBO"%>
<%@page import="gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType"%>
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
	String mode = (String)request.getAttribute("mode"); //"add";
	int partyNum = TextUtil.getParamAsInt(request, "partyNum");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	int caseNum = TextUtil.getParamAsInt(request, "caseNum");
	int partyIdId =TextUtil.getParamAsInt(request, "partyIdId");
	IdIssuerBO defaultIssuer = request.getAttribute("defaultIssuer")==null?new IdIssuerBO(courtType):(IdIssuerBO)request.getAttribute("defaultIssuer");
	PartyIdentifierBO partyIdBO = request.getAttribute("partyIdBO")==null? new PartyIdentifierBO(courtType):(PartyIdentifierBO)request.getAttribute("partyIdBO");
	PartyBO party = request.getAttribute("party")==null?new PartyBO(courtType):(PartyBO)request.getAttribute("party");
	List<IdIssuerBO> idIssuers = request.getAttribute("idIssuers")==null? new ArrayList<IdIssuerBO>(): (List<IdIssuerBO>)request.getAttribute("idIssuers"); 
	List<IdTypeDefnBO> idTypes = request.getAttribute("idTypes")==null?new ArrayList<IdTypeDefnBO>():(List<IdTypeDefnBO>)request.getAttribute("idTypes"); 
	List<CountryBO> countries = request.getAttribute("countries")==null?new ArrayList<CountryBO>():(List<CountryBO>)request.getAttribute("countries");
%>

<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
</style>
</head>
<body>
	<main> <!-- header --> <header></header> <!-- navigation -->
	<nav></nav>
	<div class="container-fluid">

		<div class="card m-2">
			<div class="card-header">
				<strong>Party Identifiers</strong>
			</div>
			<div class="card-body">
				<form id="partyIDForm" name="partyIDForm" onsubmit="saveForm(event);">
					<input type="hidden" name="mode" id="mode" value="<%=mode%>"/>
					<input type="hidden" name="partyNum" id="partyNum" value="<%=partyNum%>" /> 
					<input type="hidden" name="partyIdId" id="partyIdId" value="<%=partyIdId %>" />
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Party Name <span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<%=TextUtil.print(party.getFirstName()) + " " + TextUtil.print(party.getLastName()) %>
									</div>
								</div>
							</div>
						</div>

						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Case Number <span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<%=caseNum %>
									</div>
								</div>
							</div>
						</div>

					</div>


					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">

								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>ID Type<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<select class="form-control" id="idTypeId" name="idTypeId">
											<% for(IdTypeDefnBO typeBO:idTypes){
											 %>
											<option value="<%=typeBO.getIdTypeDefnId()%>" <%=typeBO.getIdTypeDefnId()==partyIdBO.getIdTypeDefnId()?"selected":"" %>><%=typeBO.getTypeDescr() %></option>
											<%} %>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>ID Number:<span class="text-danger"></span></strong></label>
									<div class="col-sm-12 col-md-13" id="idDiv">
										<input type="text" class="form-control" name="idValue" id="idValue" value="<%=TextUtil.print(partyIdBO.getIdNumber()) %>" maxlength="30">
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Issuer Country<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<select class="form-control" id="issuerCountry" name="issuerCountry" onchange="syncState(this.value);">
											<% 
												for(CountryBO country:countries) {
											%>
											<option value="<%=country.getCountryId()%>" <%=country.getCountryId()==defaultIssuer.getCountryId()?"selected":"" %>><%=country.getCountryName() %></option>
											<%		}
											 %>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18">
								<div class="row align-items-center">
									<label class="col-sm-8 col-md-9 control-label text-sm-right"><strong>Issuer State<span class="text-danger"></span>
									</strong></label>
									<div class="col-sm-12 col-md-13">
										<select class="form-control" id="idIssuerId" name="idIssuerId">
										</select>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="form-row">
						<div class="col-12">
							<div class="form-group col-md-16 offset-lg-2 col-lg-18"></div>
						</div>
						<div class="col-12">
							<div class="form-group col-sm-20 col-md-23 col-lg-23 clearfix">
								<button type="button" class="btn btn-secondary ml-2 float-right" id="clearBtn" onclick="$('#idValue').val(''); $('#idValue').focus();">Clear</button>
								<div id="submitBtnDiv"></div>
							</div>
						</div>
					</div>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='mb-1 col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn" onclick="closePop();">Close</button>
						</div>
					</div>
				</form>
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
			$('#idValue').focus();
			<% if(partyIdId==0) { %>
				$('#submitBtnDiv').html('<button type="submit" class="btn btn-primary ml-2 float-right" id="saveBtn">Add</button>'); 
			<% }else { %>
				$('#submitBtnDiv').html('<button type="submit" class="btn btn-primary ml-2 float-right" id="saveBtn">Update</button>'); 
			<% } %>
			syncState('<%=defaultIssuer.getCountryId()%>','<%=defaultIssuer.getIdIssuerId()%>');
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function syncState(countryId, issuerId){
			var opts = "";
			if(!issuerId){
				issuerId = '<%=defaultIssuer.getIdIssuerId()%>';
			}
			<% 
				for(IdIssuerBO issuer:idIssuers){
			%>
					if(countryId == '<%=issuer.getCountryId()%>') {
						if(issuerId && issuerId == '<%=issuer.getIdIssuerId()%>'){
							opts += '<option value="<%=issuer.getIdIssuerId() %>" <%="selected"%> >' + '<%=TextUtil.print(issuer.get(StateBO.NAME)) %>' + '</option>';
						}else {
							opts += '<option value="<%=issuer.getIdIssuerId() %>" >' + '<%=TextUtil.print(issuer.get(StateBO.NAME)) %>' + '</option>';
						}
					}
			<%	
				}
			 %>
			 
			 $('#idIssuerId').html(opts);	
		}
		
		function saveForm(e){
			if(e) { e.preventDefault() };
			if($('#idValue').val()==''){
				appUX.pop.alert('ID number cannot be empty', 400, 'auto', appUX.pop.styles.DANGER);
				$('#idValue').focus();
			}else if($('#idTypeId').val()== 2 && $("#idIssuerId option:selected" ).text()==''){
				appUX.pop.alert('State cannot be empty for State ID.', 400, 'auto', appUX.pop.styles.DANGER);
				$('#idIssuerId').focus();
			}else{
				appUX.ajax.call("/CorisWEB/CorisPartyIDServlet", 
						function (err, data, xhr) { 
							var jsonObj = JSON.parse(data);
							if(err){
								message = "Error: Changes were not saved.";
								appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
							}else if (jsonObj && jsonObj.error) {
								appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
							}else{
								appUX.pop.refreshCornFrame(appUX.pop.getLimitedFamily().parent, true);
								appUX.pop.declare("Success", "Party ID <%=mode%> successful.", 300, 'auto', appUX.pop.styles.SUCCESS);
								closePop();
							}
						}, 
						'POST', 
						$('#partyIDForm').serialize()  + '&courtType=<%=courtType%>'
					);
			}
		}
	
	</script>
</body>
</html>