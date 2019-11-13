<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dto.SummaryEventDTO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.document.DocumentBO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Date"%>
<%@page import="java.awt.Color"%>
<%@page import="gov.utcourts.coriscommon.dto.CaseCriticalMessagesDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.CaseWarningsDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.SafeGuardedDismissedPartyDTO"%>
<%@page import="java.text.DateFormat"%>
<%
//these come from either a login screen or are passed in as URL query params
boolean inquiryUser = "Y".equals(request.getAttribute("inquiryUser"));
boolean courtMaintAccess = (Boolean) request.getAttribute("courtMaintAccess");
boolean caseHistDelAccess = (Boolean) request.getAttribute("caseHistDelAccess");
String courtType = URLEncryption.getParamAsString(request, "courtType");
String logName = URLEncryption.getParamAsString(request, "logName");
String theUrl = "courtType="+courtType+"&logName="+logName;

//encrypt the URL
URLEncryption urlCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");
URLEncryption urlEmailCryptor = new URLEncryption("/CorisWEB/CaseSummarySearchLookupServlet");

URLEncryption urlCryptor2 = new URLEncryption("/CorisWEB/DocMgrServlet");
URLEncryption urlEmailCryptor2 = new URLEncryption("/CorisWEB/DocMgrEmailServlet");

URLEncryption urlCryptorReceipt = new URLEncryption("/CorisWEB/ReceiptServlet");
URLEncryption urlEmailCryptorReceipt = new URLEncryption("/CorisWEB/ReceiptServlet");

//initialize the incoming params
String caseNum = URLEncryption.getParamAsString(request, "caseNum");
String locnCode = URLEncryption.getParamAsString(request, "locnCode");
String caseTitle = URLEncryption.getParamAsString(request, "caseTitle");
String locnCodeDescr = URLEncryption.getParamAsString(request, "locnCodeDescr");

String judgeName = (String) request.getAttribute("judgeName");
String commName = (String) request.getAttribute("commName");

@SuppressWarnings("unchecked")
List<CaseCriticalMessagesDTO> caseCriticalMessagesListDTO = (List<CaseCriticalMessagesDTO>) TextUtil.checkNull(request.getAttribute("caseCriticalMessagesListDTO"), new ArrayList<CaseCriticalMessagesDTO>());

@SuppressWarnings("unchecked")
List<CaseWarningsDTO> caseWarningsListDTO = (List<CaseWarningsDTO>) TextUtil.checkNull(request.getAttribute("caseWarningsListDTO"), new ArrayList<CaseWarningsDTO>());

@SuppressWarnings("unchecked")
List<SafeGuardedDismissedPartyDTO> safeGuardedDismissedPartyListDTO = (List<SafeGuardedDismissedPartyDTO>) TextUtil.checkNull(request.getAttribute("safeGuardedDismissedPartyListDTO"), new ArrayList<SafeGuardedDismissedPartyDTO>());

ArrayList<String> filterList = new ArrayList<String>();

%>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Coris Search</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
<div class="container-fluid">

	<div class="card m-2">
		<div class="card-header text-dark">
			<div class="mb-2">
				<span class="float-left"><strong>Case Number</strong> <%=caseNum %> - <%=caseTitle %></span>
				<span class="float-right"><strong>Location</strong> <%=locnCodeDescr %></span>
			</div>
			<br/>
			<div>
				<% if (!TextUtil.isEmpty(judgeName) || !TextUtil.isEmpty(commName)){ %>
						<span class="float-left">
							<% if (!TextUtil.isEmpty(judgeName)){ %>
								<strong> Judge:</strong> <%=judgeName %>
							<% } %>
							<% if (!TextUtil.isEmpty(commName)){ %>
								<strong> Commissioner:</strong> <%=commName %>
							<% } %>
						</span>
				<% } %>
				<% if(caseCriticalMessagesListDTO.size() > 0 || caseWarningsListDTO.size() > 0){ %>
						<a href="#" class="float-right ml-4 text-danger" title="View Critical Messages and Warnings" onclick="displayCriticalMessagesAndWarnings();">
							<strong>Critical Messages and Warnings</strong>
						</a>
				<% } else { %>
						<span class="float-right ml-4 text-muted">
							<strong>Critical Messages and Warnings</strong>
						</span>
				<% } %>
				<% if(safeGuardedDismissedPartyListDTO.size() > 0){ %>
						<a href="#" class="float-right text-danger ml-4" title="View Safeguarded/Dismissed Parties" onclick="displaySafeGuardedDismissedParty();">
							<strong>Safeguarded/Dismissed Parties</strong>
						</a>
				<% } else { %>
						<span class="float-right text-muted ml-4">
							<strong>Safeguarded/Dismissed Parties</strong>
						</span>
				<% } %>
			</div>
		</div>
		<div class="card-body">
			<%//display the list of actions %>
			<div class="row">
				<div class="col-12">
					<div class="form-group">
						<span>
							<label for="eventDropdownId">Actions</label>
							<select  id="actionDropdownId" name="actionDropdownId">
								<option value=""></option>
								<% if (!inquiryUser) { %><option value="caseHistoryNoteBtn">Add Case History Note</option><% } %>
								<% if (!inquiryUser) { %><option value="caseNoteBtn">Edit Case Note</option><% } %>
								<% if (!inquiryUser) { %><option value="accountHistoryAll">View Account History All</option><% } %>
								<option value="allPdfBtn">View Case History All</option>
								<option value="chargeHistoryPdfBtn">View Charges</option>
								<option value="defendantInfoPdfBtn">View Defendant Information</option>
								<option value="judgeHistoryPdfBtn">View Judge History</option>
								<% if (!inquiryUser) { %><option value="noteHistoryPdfBtn">View Case History Note All</option><% } %>
								<% if (!inquiryUser) { %><option value="trackingHistoryPdfBtn">View Tracking History</option><% } %>
								<% if (!inquiryUser) { %><option value="trackingManageBtn">Manage Case Tracking</option><% } %>
								<option value="warrantHistoryPdfBtn">View Warrant History</option>
							</select>
						</span>
					</div>
				</div>
				<div class="col-12">
					<div class="form-group float-right">
						<div id="funcidTypeDiv" style="display: inline-block; right: 10px; position: absolute; overflow: hidden; z-index: 100;"
				     		onmouseover="funcidFilterMouseover();"
				     		onmouseout="funcidFilterMouseout();">
								<select id="funcId" name="funcId" multiple size="1" class="form-control"> 
									<option value="all">All</option> 
								</select>
						</div>
						<label for="funcidTypeDiv" style="display: inline-block; right: 120px; position: absolute;">Filter</label>
					</div>
				</div>
			</div>
			<table id="eventTable" class="col-24 table table-hover table-highlight table-responsive-sm">
				<thead>
					<tr class="bg-dark text-light">
						<% int colspan = 5; //update this when new columns are added to the table %>
						<th class="sorter-false"></th>
						<th>Date</th>
						<th>Type</th>
						<th>Description</th>
						<th class="sorter-false"></th>
					</tr>
				</thead>
				<tbody class="table-activate">
					<%
					//get the list of events for this case, which are already sorted by eventDatetime
					List<SummaryEventDTO> eventResults = (List<SummaryEventDTO>) request.getAttribute("summaryeventlistDTO");

					//display the rows of results
					if(eventResults != null) {
						int	docSeq = 0;
						String funcId = "";
						String descr = "";
						String eventDatetime;
						String createDatetime;
						Date eventDate;
						Date createDate;
						String key1 = "";
						String key2 = "";
						String key3 = "";
						String key4 = "";
						String security = "";
						long dmDocid = 0;
						int docNum = 0;
						boolean muteAndDisable = false;
						int rowId = 0;
						for (SummaryEventDTO summaryEventDTO : eventResults) {
							rowId++;
							docSeq = summaryEventDTO.getDocSeq();
							funcId = summaryEventDTO.getFuncId();
							descr = TextUtil.print(summaryEventDTO.getDescr());
							eventDatetime = summaryEventDTO.getEventDatetime();
							createDatetime = summaryEventDTO.getCreateDatetime();
							eventDate = TextUtil.parseDate(summaryEventDTO.getEventDatetime(), "yyyy-MM-dd");
							createDate = TextUtil.parseDate(summaryEventDTO.getCreateDatetime(), "yyyy-MM-dd");
							key1 = summaryEventDTO.getKey1();
							key2 = summaryEventDTO.getKey2();
							key3 = summaryEventDTO.getKey3();
							key4 = summaryEventDTO.getKey4();
							dmDocid = summaryEventDTO.getDmDocId();
							security = summaryEventDTO.getSecurity();
							//gather the list of funcIds to populate the filter dropdown
							filterList.add(funcId);
							if (!funcId.equals("MINUTE") && !funcId.equals("DOCUMENT") && !funcId.equals("ORDER")){
								if(("CONSOLIDATED".equals(key4) || "TRANSFERED".equals(key4) || "TRANSFERRED".equals(key4))){
									muteAndDisable = true;
								}
							}

							if(!"".equals(funcId) && !"".equals(descr)) {
								String onclickURL = ""; 
								String emailURL = ""; %>
								<tr data-event="<%=funcId%>" id="row_id_<%= rowId %>">
									<%if (inquiryUser || docSeq == 0){ %>
										<td style="white-space: nowrap;"></td>
									<%} else { %>
										<td class="text-danger" style="white-space: nowrap;"><strong><%=TextUtil.print(docSeq)%></strong></td>
									<%} %>
									
									<td class="dateColumn" style="white-space: nowrap;"><%=TextUtil.printDate(eventDate, "MM/dd/yyyy")%></td>
									<td class="stringColumn" style="white-space: nowrap;"><%=funcId%></td>
									<% if("DOCUMENT".equals(funcId) || "ORDER".equals(funcId)) { %>
										<% if(dmDocid > 0) { %>
											<% onclickURL = urlCryptor2.urlEncrypt(theUrl + "&locnCode=" + locnCode + "&docId=" + dmDocid + "&caseNum="+ caseNum); %>
											<td>
												<a href="javascript:void(0);" <%=muteAndDisable?"class='text-muted'":"class='text-danger'" %> title="View Image" 
												<% if(!muteAndDisable){ %>
													onclick="openDocMgrDoc('<%= onclickURL %>');"
												<% } %>
												>
													<%= descr %>
												</a>
											</td>
											<td style="white-space: nowrap;">
												<% if(!muteAndDisable){ %>
													<a href="javascript:void(0);" title="View Image" onclick="openDocMgrDoc('<%= onclickURL %>');">
														<i class="text-danger fas fa-file-pdf fa-lg fa-fw"></i>
													</a>
													<%if (!inquiryUser){%>
														<% emailURL = urlEmailCryptor2.urlEncrypt(theUrl + "&logname" + logName + "&locnCode=" + locnCode + "&courtType=" + courtType + "&docId=" + dmDocid + "&caseNum="+ caseNum); %> 
														<a href="javascript:void(0);" title="Email" onclick="emailPDF('<%= emailURL %>');">
															<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
														</a>
													<% } %>
												<% } %>
											</td>
										<% } else { %>
											<% onclickURL = urlCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=N"); %>
											<td>
												<a href="javascript:void(0);" <%=muteAndDisable?"class='text-muted'":"" %> title="View Document" 
													<% if(!muteAndDisable){ %>
														onclick="displayPDF('<%= onclickURL %>');"
													<% } %>
												>
													<%= descr %>
												</a>
											</td>
											<td style="white-space: nowrap;">
												<% if(!muteAndDisable){ %>
													<a href="javascript:void(0);" title="View Document" onclick="displayPDF('<%= onclickURL %>');">
														<i class="fas fa-sticky-note fa-lg fa-fw"></i>
													</a>
													<%if (!inquiryUser){%>
														<% emailURL = urlEmailCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&getSinglePDF=true" +  "&mode=getSinglePDF"  + "&email=Y"); %>
														<a href="javascript:void(0);" title="Email" onclick="emailPDF('<%= emailURL %>');">
															<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
														</a>
													<% } %>
												<% } %>
											</td>
										<% } %>
									<% } else if("MINUTE".equals(funcId)) { %>
											<% onclickURL = urlCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=N"); %>
											<td>
												<a href="javascript:void(0);" <%=muteAndDisable?"class='text-muted'":"class='text-primary'" %> title="View Minute" 
												<% if(!muteAndDisable){ %>
													onclick="displayPDF('<%= onclickURL %>');"
												<% } %>
												>
													<%= descr %>
												</a>
											</td>
											<td style="white-space: nowrap;">
												<% if(!muteAndDisable){ %>
													<a href="javascript:void(0);" title="View Minute" onclick="displayPDF('<%= onclickURL %>');">
														<i class="text-primary fas fa-sticky-note fa-lg fa-fw"></i>
													</a>
													<%if (!inquiryUser){%>
														<% emailURL = urlEmailCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&getSinglePDF=true" + "&mode=getSinglePDF"  + "&email=Y"); %>
														<a href="javascript:void(0);" title="Email" onclick="emailPDF('<%= emailURL %>');">
															<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
														</a>
														<% if(caseHistDelAccess && courtMaintAccess && "Minute: Non-Appearance Plea in Abeyance".equalsIgnoreCase(descr)){ %>
																<a href="javascript:void(0);" title='Delete' onclick="deleteCaseSummaryEvent('<%= funcId %>', '<%= caseNum %>', '<%= locnCode %>', '<%= courtType %>', '<%= eventDatetime %>', '<%= createDatetime %>');">
																				<i class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
																</a>																																 							
																										 
														<% } %>
													<% } %>
												<% } %>
											</td>
									<% } else if("JUDGMENT".equals(funcId)) { %>
											<% String viewHover = "Display Only " + TextUtil.makeProper(funcId);%>
											<td>
												<a class="text-default" <%=muteAndDisable?"class='text-muted'":"" %> title='<%=viewHover%>'">
													<%= descr %>
												</a>
											</td>
											<% if(!muteAndDisable && !inquiryUser && caseHistDelAccess && courtMaintAccess){ %>
												<td style="white-space: nowrap;">								
																								
														<a href="javascript:void(0);" title='Delete' onclick="deleteCaseSummaryEvent('<%= funcId %>', '<%= caseNum %>', '<%= locnCode %>', '<%= courtType %>', '<%= eventDatetime %>', '<%= createDatetime %>');">
																<i class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
														</a>					
														
														<% viewHover = "";%>								
											</td>
											<% } %>											
											<td></td>
									<% } else if("ACCOUNT".equals(funcId)) { %>
											<% onclickURL = urlCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=N"); %>
											<% String viewHover = "View " + TextUtil.makeProper(funcId);%>
											<td>
												<a href="javascript:void(0);" <%=muteAndDisable?"class='text-muted'":"class='text-primary'" %> title='<%=viewHover%>' 
												<% if(!muteAndDisable){ %>
													onclick="displayPDF('<%= onclickURL %>');"
												<% } %>
												>
													<%= descr %>
												</a>
											</td>
											<td style="white-space: nowrap;">
												<% if(!muteAndDisable){ %>
													<a href="javascript:void(0);" title='<%=viewHover%>' onclick="displayPDF('<%= onclickURL %>');">
														<i class="text-primary fas fa-sticky-note fa-lg fa-fw"></i>
													</a>
													<%if (!inquiryUser){%>
														<% if(!TextUtil.isEmpty(key1) &&
															  !TextUtil.isEmpty(key2)) { %>
																<% emailURL = urlEmailCryptorReceipt.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=Y"); %>
																<a href="javascript:void(0);" title="Email Receipt" onclick="emailPDF('<%= emailURL %>');">
																	<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
																</a>
																<% onclickURL = urlCryptorReceipt.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=N"); %>
																<a href="javascript:void(0);" title="View Receipt" onclick="displayPDF('<%= onclickURL %>');">
																	<i class="text-success fas fa-receipt fa-lg fa-fw"></i>																
																</a>
														<% } else {%>
															<% emailURL = urlEmailCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&getSinglePDF=true" + "&mode=getSinglePDF"  + "&email=Y"); %>
															<a href="javascript:void(0);" title="Email Transaction" onclick="emailPDF('<%= emailURL %>');">
																<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
															</a>
														<% } %>
														<% viewHover = "";%>
													<% } %>
												<% } %>
											</td>
									<% } else {%>
											<% onclickURL = urlCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&mode=getSinglePDF" + "&email=N"); %>
											<% String viewHover = "View " + TextUtil.makeProper(funcId);%>
											<td>
												<a href="javascript:void(0);" <%=muteAndDisable?"class='text-muted'":"class='text-primary'" %> title='<%=viewHover%>' 
												<% if(!muteAndDisable){ %>
													onclick="displayPDF('<%= onclickURL %>');"
												<% } %>
												>
													<%= descr %>
												</a>
											</td>
											<td style="white-space: nowrap;">
												<% if(!muteAndDisable){ %>
													<a href="javascript:void(0);" title='<%=viewHover%>' onclick="displayPDF('<%= onclickURL %>');">
														<i class="text-primary fas fa-sticky-note fa-lg fa-fw"></i>
													</a>
													<%if (!inquiryUser){%>
														<% emailURL = urlEmailCryptor.urlEncrypt(theUrl + "&funcId=" + funcId + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&descr=" + descr + "&eventDatetime=" + eventDatetime + "&createDatetime=" + createDatetime + "&key1=" + key1 + "&key2=" + key2 + "&key3=" + key3 + "&key4=" + key4 + "&getSinglePDF=true" + "&mode=getSinglePDF"  + "&email=Y"); %>
														<a href="javascript:void(0);" title="Email" onclick="emailPDF('<%= emailURL %>');">
															<i class="text-dark fas fa-envelope fa-lg fa-fw"></i>
														</a>
														<% if ("HISTNOTE".equals(funcId)) { %>
															<a href="javascript:void(0);" title='Edit Case History Note' onclick='editHistNote("row_id_<%= rowId %>", "edit", "<%= caseNum %>", "<%= locnCode %>", "<%= courtType %>", "<%= TextUtil.replaceHTMLCharacters(descr) %>", "<%= eventDatetime %>", "<%= createDatetime %>");'>
																<i class="text-warning far fa-edit fa-lg fa-fw"></i>
															</a>
														<% } %>
														<% if(caseHistDelAccess){ %>
															<% if ("AMNDINFO".equals(funcId)
																|| "CALENDAR".equals(funcId)
																|| "CASEDISP".equals(funcId)
																|| "CASEFILE".equals(funcId)
																|| "CASETRAN".equals(funcId)
																|| "CCDSPNTC".equals(funcId)
																|| "CHARGE".equals(funcId)
																|| "CHARGES".equals(funcId)
																|| "DEFENDNT".equals(funcId)
																|| "DISMISS".equals(funcId) 
																|| "EVIDENCE".equals(funcId)
																|| "FORFNOTC".equals(funcId)
																|| "FTA/FTC".equals(funcId) 
																|| "FTACHRG".equals(funcId) 
																|| "HISTNOTE".equals(funcId)
																|| "JUDGMENT".equals(funcId)
																|| "MEDIATIO".equals(funcId)
																|| "MINUTE".equals(funcId) 
																|| "NAME/ADD".equals(funcId)
																|| "NOTICE".equals(funcId) 
																|| "STAY".equals(funcId) 
																|| "TRACKING".equals(funcId)
																|| "WARRANT".equals(funcId)
															 ) { %>
																<a href="javascript:void(0);" title='Delete' onclick="deleteCaseSummaryEvent('<%= funcId %>', '<%= caseNum %>', '<%= locnCode %>', '<%= courtType %>', '<%= eventDatetime %>', '<%= createDatetime %>');">
																	<i class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
																</a>
															<% } %>
														<% } %>
														<% viewHover = "";%>
													<% } %>
												<% } %>
											</td>
									<% } %>
								</tr>
						<%
							}
							muteAndDisable = false;
						}

						
						//cleanup
						funcId = null;
						descr = null;
						eventDatetime = null;
						createDatetime = null;
						eventDate = null;
						createDate = null;
						key1 = null;
						key2 = null;
						key3 = null;
						key4 = null;
						security = null;
					} else {
						%>
						<tr>
							<td colspan="<%=colspan %>">No Results.</td>
						</tr>
						<% 
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="p-12"></div>
<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
	<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="appUX.pop.getSelfHandle().close();">Cancel</button>
</div>
	
<script src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-popcorn.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script>

	$(document).ready(function(){
	
		$("#eventTable").tablesorter();
			
		<% if(caseNum != null) { %>
			$(document).attr("title", "Case <%=caseNum%>");
		<% } else { %>
			$(document).attr("title", "Coris Search");
		<% } %>
		
		//fix for the content being hidden by the fixed-top navbar/search area on different sized screens
		$(document.body).css('padding-top', $('#headerDiv').height() + 2);
		$(window).resize(function(){
			$(document.body).css('padding-top', $('#headerDiv').height() + 2);
		});
		
		<%//filter the list of events when the funcId dropdown is changed %>
		$('#funcId').on("change", function(){
			var filter = $(this).val();
	
			// if "All" is selected on a multi-select -- force it to be the only option selected
			if (filter.length > 1 && filter[0] == "all") {
				$('#funcId').val('all');
				for (var i=0; i < filter.length; i++) {
					filter[i] = 'all';
				}
			}
			
			for (var i=0; i < filter.length; i++) {
				$("table tr").each(function() {
					if($(this).data('event')) {
						var find  = $(this).data('event');
						if (filter[i] == "all") {
							$(this).show();
						} else if (filter.indexOf(find) !== -1){	
							$(this).show();
						} else {
							$(this).hide();
						}
					}
				})
			}
		});
		
		<%//pull up the search screen so they can search for other cases %>
		$('#searchFormBtn').on("click", function() {
			thisEncryptedUrl = '<%= urlCryptor.urlEncrypt(theUrl + "&mode=getCasesList&courtType=" + courtType + "&locnCode=" + locnCode + "&locnCodeDescr=" + locnCodeDescr + "&email=N") %>';
			window.location.replace(thisEncryptedUrl);
		});

		$('#actionDropdownId').on("change", function(){
			var action = $(this).val();
			if (action == 'allPdfBtn') {
				displayPDF('<%= urlCryptor.urlEncrypt(theUrl + "&funcId=all&mode=getPDF&caseNum=" + caseNum + "&locnCode=" + locnCode) %>');
			} else if (action == 'judgeHistoryPdfBtn') {
				displayPDF('<%= urlCryptor.urlEncrypt(theUrl + "&funcId=JUDGHISTALL" + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');	
			} else if (action == 'chargeHistoryPdfBtn') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=CHARGE" + "&caseNum=" + caseNum	+ "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'accountHistoryAll') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=ACCOUNTHISTORYALL" + "&caseNum=" + caseNum	+ "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'defendantInfoPdfBtn') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=DEFENDNT" + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'trackingHistoryPdfBtn') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=TRACKINGHIST" + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'noteHistoryPdfBtn') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=NOTEHIST" + "&caseNum=" + caseNum + "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'warrantHistoryPdfBtn') {
				displayPDF('<%=urlCryptor.urlEncrypt(theUrl	+ "&funcId=WARRANTHIST" + "&caseNum=" + caseNum	+ "&locnCode=" + locnCode + "&mode=getSinglePDF" + "&email=N") %>');
			} else if (action == 'caseNoteBtn') {
				var cornId = "caseNotePrimary";
				var title = "Edit Case Note";
				var url = "/CorisWEB/CaseNoteServlet?"
					+ "caseNum=<%= caseNum %>" 
					+ "&locnCode=<%= locnCode %>"
					+ "&courtType=<%= courtType %>"  
					+ "&mode=edit";
				
				var width = 500;
				var height = 200;
				var style = appUX.pop.styles.PRIMARY;
				appUX.pop.modal(cornId, title, url, width, height, style);
			} else if (action == 'caseHistoryNoteBtn') {
				editHistNote('row_id_0', 'add', '<%= caseNum %>', '<%= locnCode %>', '<%= courtType %>', null, null, null);
			} else if (action == 'trackingManageBtn') {
				var cornId = "caseTracking";
				var title = "Manage Case Tracking";
				var url = "/CorisWEB/CaseTrackingServlet?"
					+ "caseNum=<%= caseNum %>" 
					+ "&locnCode=<%= locnCode %>"
					+ "&courtType=<%= courtType %>"
					+ "&locnCodeDescr=<%= locnCodeDescr %>"  
					+ "&caseTitle=<%= caseTitle %>"
					+ "&parentCornId="+cornId
					+ "&mode=default";
				var width = 800;
				var height = 600;
				var style = appUX.pop.styles.PRIMARY;
				appUX.pop.modal(cornId, title, url, width, height, style);
			}

			if (action != '') {
				$('#actionDropdownId').val(''); 	// clear the dropdown
			}
		});

	});
	
	<%	
	//sort the filterList and remove any duplicates, then create the options and populate the dropdown
	Collections.sort(filterList);
	String funcIdCurrent = "";
	for(String temp : filterList){
		//don't show duplicate funcIds
		if(!temp.equals(funcIdCurrent)) {
			funcIdCurrent = temp;
			if(!"".equals(temp)) {
				%>
				$('#funcId').append('<option value="<%=temp%>"><%=temp%></option>');
				<% 
			}
		}
	}
	filterList = null;
	funcIdCurrent = null;
	%>

	<%//this is used for the funcId filter dropdown list %>
	function getHiddenField(name, value) {
		var input = document.createElement("input");
		input.setAttribute("type", "hidden");
		input.setAttribute("name", name);
		input.setAttribute("value", value);
		return input;
	}
	
	function funcidFilterMouseover(){
		var selectBoxSize = document.getElementById('funcId').length;
		var div = document.getElementById('funcidTypeDiv');
		var selectSz = selectBoxSize;
		var filter = document.getElementById('funcId');
		filter.focus();
		if(selectSz > 30){
			filter.size = 30;
			div.style.height = filter.clientHeight + 'px';	
		}else{
			div.style.height='';
			filter.size = selectSz;
		}
	}
	
	function funcidFilterMouseout(){
		var div = document.getElementById('funcidTypeDiv');
		div.style.height= '21px';
	}
	
	function emailPDF(emailUrl) {
		appUX.pop.declare("Email", "Email being generated", 300, 'auto', appUX.pop.styles.INFO);
		appUX.ajax.call(emailUrl, function(err, data, xhr) {
			err ? appUX.pop.declare("Email", "Email could not be sent", 300, 'auto', appUX.pop.styles.DANGER) : appUX.pop.declare("Email", "Email will be delivered shortly", 300, 'auto', appUX.pop.styles.SUCCESS);
		});
	}
	
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
		var pdf = window.open("", "_blank", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
		if(pdf) {
			pdf.document.write(htmlDisplay); //display loading spinner
			pdf.location.href = encryptedUrl; //replace the window with the contents of the PDF
		} else {
			var message = "To view this PDF, please allow popups in the browser.";
			appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
		}
	}
	
	function refresh() {
	 appUX.pop.refreshCornFrame(appUX.pop.getSelfHandle().id);
	//parent.refreshCaseSummaryDetails();
	}
	
	// edit hist notes
	function editHistNote(target, mode, caseNum, locnCode, courtType, chText, eventDatetime, createDatetime) {
		var cornId = "modalCornPrimary";
		var title = (mode == 'edit' ? "Edit" : "Add") + " Case History Note";
		
		var url = "/CorisWEB/CaseHistServlet?"
			+ "caseNum=" + caseNum 
			+ "&locnCode=" + locnCode
			+ "&courtType=" + courtType  
			+ "&chText=" + encodeURIComponent(chText)
			+ "&eventDatetime=" + eventDatetime
			+ "&createDatetime=" + createDatetime 
			+ "&mode=" + mode
			+ "&target=" + target;
			
		var width = 500;
		var height = 300;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}
	
	// delete
	function deleteCaseSummaryEvent(funcId, caseNum, locnCode, courtType, eventDatetime, createDatetime) {
		var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this case summary event?", "Yes", "No", 450, 'auto', appUX.pop.styles.INFO,
			function (result) {
				if (result) {
					var url = "/CorisWEB/CaseSummaryAjaxServlet?"
						+ "funcId=" + funcId
						+ "&caseNum=" + caseNum 
						+ "&locnCode=" + locnCode
						+ "&courtType=" + courtType  
						+ "&eventDatetime=" + eventDatetime
						+ "&createDatetime=" + createDatetime;
					
					appUX.ajax.call(url, 
						function (err, data, xhr) {
							err ? appUX.pop.declare("Remove Event", "Case Summary Event could not be removed. " + err, 300, 'auto', appUX.pop.styles.DANGER) : refresh(); 
						}, 
						'POST', 
						'mode=delete'
					); 
				}
				corn.close();
			}
		);
	}

	//this is to display the PDF for "DOCUMENT" funcId when there is an image (static PDF) available on the server
	function openDocMgrDoc(encryptedUrl) {
			appUX.ajax.call(encryptedUrl, 
				function (err, data, xhr) { 
					<% //data is a JSON string, something like this: {"authKey":"9T!nR6@wQ!7Rx5@41eW@rbc2","cost":"2mCOL3TBCEA=","docId":"L4hOLbJqLCtRNMQEK6RXVg==","caseLocation":"31unzMTls6o=","docMgrUser":"ME4ILJjd8gW6IOown5S1eA==","transactionKey":"WTZj21y5veUYweeedIHRhPSj6y0j2dSYtM0xQ61mAUeL5oRFFJKF7nzl7dne","callingSystem":"3ySHL2PBA38=","userId":"2mCOL3TBCEA=","url":"https:\/\/devws.utcourts.gov\/DocDirectWEB\/ShowDocServlet","transactionId":"161083"} %>
		
					var jsonObj = JSON.parse(data);
					var authKey = jsonObj.authKey;
					var url = jsonObj.url;
					var docId = jsonObj.docId;
					var callingSystem = jsonObj.callingSystem;
					var docMgrUser = jsonObj.docMgrUser;
					var transactionKey = jsonObj.transactionKey;
					var transactionId = jsonObj.transactionId;
					var userId = jsonObj.userId;
					var caseLocation = jsonObj.caseLocation;
					var cost = jsonObj.cost;
					var documentTitle = jsonObj.documentTitle;
					
					var form = document.createElement('form');
					form.setAttribute('action', url);
					form.setAttribute('method', 'post');
					
					form.appendChild(getHiddenField('docId', docId));
					form.appendChild(getHiddenField('docMgrUserName', docMgrUser));
					form.appendChild(getHiddenField('callingSystem', callingSystem));
					form.appendChild(getHiddenField('transactionKey', transactionKey));
					form.appendChild(getHiddenField('transactionId', transactionId));
					form.appendChild(getHiddenField('userId', userId));
					form.appendChild(getHiddenField('location', caseLocation));
					form.appendChild(getHiddenField('applicationAuthKey', authKey));
					form.appendChild(getHiddenField('cost', cost));
					form.appendChild(getHiddenField('documentTitle',documentTitle));
					form.setAttribute('target', '_blank');
					
					document.body.appendChild(form);
					form.submit(); 
				}, 
				'POST'
			);
	}
	
	function displayCriticalMessagesAndWarnings(){
		var message = "";
		message += '<table class="table table-borderless" style="width: 100%;"><thead><tr><th style="width: 49%;">Critical Messages</th><th style="width: 1%;"></th><th style="width: 50%;">Warnings</th></tr></thead><tbody><tr><td>';
		<%
		if(caseCriticalMessagesListDTO.size() > 0){
			for(CaseCriticalMessagesDTO caseCriticalMessagesDTO : caseCriticalMessagesListDTO) {
				%>
					message += '<span><%=TextUtil.print(caseCriticalMessagesDTO.getTitle()) %></span><br/>';
				<%
				caseCriticalMessagesDTO = null;
			} 
		}%>
		message += '</td><td class="border-left border-dark"></td><td>';
		<%
		if(caseWarningsListDTO.size() > 0){
			String buildMessage = "";
			DateFormat dateFormat = Constants.dateFormatCoris;
			for(CaseWarningsDTO caseWarningsDTO : caseWarningsListDTO) {
				if (caseWarningsDTO.getWarningDateTime() != null){
					buildMessage += dateFormat.format(caseWarningsDTO.getWarningDateTime()) + " " ;			
				}
				if (caseWarningsDTO.getLogName() != null){
					buildMessage += caseWarningsDTO.getLogName() + " " ;			
				}
				buildMessage += TextUtil.print(caseWarningsDTO.getWarning());
				
			%>
				message += '<span><%=buildMessage %></span><br/>';
				
			<%
				buildMessage = "";
				caseWarningsDTO=null;
			} 
		}%>
		message += '</td></tr></tbody></table>';
		var corn = appUX.pop.confirm("Critical Messages and Warnings", message, "Close", "", 750, 'auto', appUX.pop.styles.DANGER, verifyCallback);
		function verifyCallback(action) {
	        corn.close();
		}
	}
	
	function displaySafeGuardedDismissedParty(){
		var message = "";
		message += '<table class="table table-borderless" style="width: 100%;"><thead><tr><th style="width: 49%;">Safeguarded Party</th><th style="width: 1%;"></th><th style="width: 50%;">Dismissed Party</th></tr></thead><tbody><tr><td>';
		<%
		if(safeGuardedDismissedPartyListDTO.size() > 0){
			for(SafeGuardedDismissedPartyDTO safeGuardedDismissedPartyDTO : safeGuardedDismissedPartyListDTO) {
				if ("S".equals(safeGuardedDismissedPartyDTO.getType())){
					if (TextUtil.isEmpty(safeGuardedDismissedPartyDTO.getFirstName())){
					%>
						message += '<span><%=safeGuardedDismissedPartyDTO.getPartyCode() + " " + safeGuardedDismissedPartyDTO.getLastName() %></span><br/>';
					<%
						
					} else {
					%>
						message += '<span><%=safeGuardedDismissedPartyDTO.getPartyCode() + " " + safeGuardedDismissedPartyDTO.getFirstName() + " " + safeGuardedDismissedPartyDTO.getLastName() %></span><br/>';
					<%
						
					}
				}
				safeGuardedDismissedPartyDTO = null;
			} 
		}%>
		message += '</td><td class="border-left border-dark"></td><td>';
		<%
		if(safeGuardedDismissedPartyListDTO.size() > 0){
			for(SafeGuardedDismissedPartyDTO safeGuardedDismissedPartyDTO : safeGuardedDismissedPartyListDTO) {
				if ("D".equals(safeGuardedDismissedPartyDTO.getType())){
					if (TextUtil.isEmpty(safeGuardedDismissedPartyDTO.getFirstName())){
						%>
							message += '<span><%=safeGuardedDismissedPartyDTO.getPartyCode() + " " + safeGuardedDismissedPartyDTO.getLastName() %></span><br/>';
						<%
							
						} else {
						%>
							message += '<span><%=safeGuardedDismissedPartyDTO.getPartyCode() + " " + safeGuardedDismissedPartyDTO.getFirstName() + " " + safeGuardedDismissedPartyDTO.getLastName() %></span><br/>';
						<%
							
						}
				}
				safeGuardedDismissedPartyDTO = null;
			} 
		}%>
		message += '</td></tr></tbody></table>';
		var corn = appUX.pop.confirm("Safeguarded/Dismissed Party", message, "Close", "", 750, 'auto', appUX.pop.styles.DANGER, verifyCallback);
		function verifyCallback(action) {
	        corn.close();
		}
	}
	
</script>
<%
//cleanup
caseNum = null;
locnCode = null;
eventResults = null;
caseNum = null;
locnCode = null;
caseTitle = null;
locnCodeDescr = null;
judgeName = null;
commName = null;
caseCriticalMessagesListDTO = null;
caseWarningsListDTO = null;
safeGuardedDismissedPartyListDTO = null;
%>
</body>
</html>
