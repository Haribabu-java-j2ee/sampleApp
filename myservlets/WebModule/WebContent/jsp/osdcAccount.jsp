<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.xo.OsdcXmlXO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.codedescriptionxref.CodeDescriptionXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dto.DebtCollectionSearchDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.CaseHeaderDTO"%>
<%@page import="gov.utcourts.coriscommon.xo.CodeDescriptionXrefXO"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.dto.OsdcXmlHeaderDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.DebtCollectionAccountDTO"%>
<%@page import="gov.utcourts.coriscommon.dto.DebtPaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%@page	language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<html>
<head>
<%
	String searchMode=null;

	if(request.getAttribute("searchMode")!=null)
	{
		searchMode=(String)request.getAttribute("searchMode");
	}
	
	DecimalFormat moneyFmt = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
	
	CaseHeaderDTO caseHeaderDTO = (CaseHeaderDTO) request.getAttribute("CASE_HEADER");
	List<DebtCollectionAccountDTO> accounts = (List<DebtCollectionAccountDTO>) request.getAttribute("ACCOUNT_DETAIL");
	List<DebtPaymentDTO> payments = (List<DebtPaymentDTO>) request.getAttribute("PAYMENTS");
	String warnings = (String) request.getAttribute("WARNING_MESSAGES");
	
	String btnCaption = "Unset";
	String localStatus =  TextUtil.print(caseHeaderDTO.getLocalDebtColl());
	if ("".equals(localStatus) || "N".equals(localStatus))
		btnCaption = "Set";
		
	String errorMsg = (String) request.getAttribute("ERROR_MSG");
	List<CodeDescriptionXrefBO> codeDescriptionXrefVOList=CodeDescriptionXrefXO.getDescriptionListByTableName("osdc_acct_history", caseHeaderDTO.getCourtType());
	Map<String,String> codeAndDescriptionMap=new HashMap<String,String>();
	
	for(CodeDescriptionXrefBO codeDescriptionXrefBO:codeDescriptionXrefVOList)
	{
		codeAndDescriptionMap.put(codeDescriptionXrefBO.getCode(),codeDescriptionXrefBO.getDescription());
	}

 %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Accounts to OSDC</title>
<meta charset="utf-8">
<style type="text/css">
	#accountTable tbody { display:block; max-height:450px; }
	#paymentTable tbody { display:block; max-height:450px; }
	#accountTable thead, #accountTable tbody tr { display:table; width:100%; }
	#paymentTable thead, #paymentTable tbody tr { display:table; width:100%; }
	table.table.table-sm {
	    border: 1px solid black;
	}
}

</style>




 
<script>		    
	
</script>
</head>
<body>
	<div class="container-fluid">
	
		  <% if (errorMsg != null && errorMsg.length()>0) { %>
		  	<div class="alert alert-danger">
		    	<a href="#" class="close" data-dismiss="alert">&times;</a>
		    	<strong>Error!</strong> <%=errorMsg %>.
		  	</div>
		  <% } %>
		  
		  <form method="post" name="accountForm" id="accountForm">
		  		<input type="hidden" name="intCaseNum" id="intCaseNum" value="<%=caseHeaderDTO.getIntCaseNum()%>">
		  		<input type="hidden" name="courtType" id="courtType" value="<%=caseHeaderDTO.getCourtType()%>">
		  		<input type="hidden" name="debtCollStatusDesc" id="debtCollStatusDesc" value="<%=request.getAttribute("debtCollStatusDesc")%>">
		  		<div class="row">
			  		<div class="col-12 col-xl-6 form-group">
			  			<div class="row">
				      		<label class="col-form-label col-12 text-right" for="caseNum">Case Number:</label>
			        		<input type="text" class="col-12 form-control" id="caseNum" name="caseNum" 
			        		value="<%=caseHeaderDTO.getCaseNum() %>" DISABLED>
			        	</div>
			      	</div>
			    
			    	<div class="col-12 col-xl-6 form-group">
			    		<div class="row">
				        	<label class="col-form-label col-12 text-right" for="ssn">Case Type:</label>
				        	<input type="text" class="col-12 form-control" id="caseType" name="caseType" 
				        	value="<%=caseHeaderDTO.getDescr() %>" DISABLED>
			      		</div>
			   		 </div>

		    		<div class="col-12 col-xl-6 form-group">
			    		<div class="row">
				        	<label class="col-form-label col-12 text-right" for="stateStatus">Local DC Status:</label>
				        	<input type="text" class="col-4 form-control" id="localStatus" name="localStatus" 
				        	value="<%=TextUtil.print(caseHeaderDTO.getLocalDebtColl()) %>" DISABLED>
			      		</div>
		   		 	</div>
		   		 	
		   		 	<div class="col-12 col-xl-6 form-group">
			    		<div class="row">
				        	<label class="col-form-label col-12 text-right" style="padding-left: 10px" for="stateStatus">OSDC Status:</label>
				        	<input type="text" class="col-4 form-control" id="stateStatus" name="stateStatus" 
				        	value="<%=TextUtil.print(caseHeaderDTO.getDebtCollection()) %>" DISABLED>
			      		</div>
		   		 	</div>					   		 	
		   		 </div>
		   		 
		   		 <% if (!TextUtil.isEmpty(warnings)) { %>
			   		<div class="col-24">
					  <div class="row border border-light font-weight-bold mb-4 p-2">
							<%= TextUtil.print(warnings) %>
					  </div>
					</div>
				<% } %>
		   		 
		    	 <div class="table-responsive col-24">
			    	 <table id="accountTable" class="table table-fixed table-bordered table-sm" style="width:100%; min-width: 950px">
						<thead class="bg-secondary">
							<tr>
								<th style="text-align:center; width:50px;"><input type="checkbox" name="checkall" class="select-checkall" id="checkall" onclick="selectAll(this);"></th>
								<th width="10%" style="color:white;">Acct Num</th>
								<th width="15%" style="color:white;">Acct Type</th>							 
								<th width="10%" style="color:white;">Due Date</th>
								<th width="10%" style="color:white;">Days Past Due</th>
								<th width="10%" style="color:white;" class="text-right">Amt Due</th>
								<th width="10%" style="color:white;" class="text-right">Amt Paid</th>
								<th width="10%" style="color:white;" class="text-right">Amt Credit</th>
								<th width="10%" style="color:white;" class="text-right">Balance</th>
								<th width="15%" style="color:white;">OSDC Status</th>
							</tr>
						</thead>
						<tbody>
						<% 
						String color = "#ffffff";
						String accountType = "";
						String osdcStatus = "";
						String osdcStatusDesc = "";
						String currNameOnAcct = "";
						String prevNameOnAcct = "";
						String separator = "";
						int recallAccNum = 0;
					 
					
					
					if(searchMode!=null  && ("byAccount".equalsIgnoreCase(searchMode)|| "byCase".equalsIgnoreCase(searchMode)))
						{
						for (DebtCollectionAccountDTO account:accounts){
							double balance = account.getAmtDue().floatValue() - account.getAmtPaid().floatValue() - account.getAmtCredit().floatValue();	
							osdcStatus = TextUtil.print(account.getOsdcStatus());
							if (balance <= 0 && !("S".equals(osdcStatus) || "R".equals(osdcStatus)))
							   	continue; // No need to show zero balances not sent
							   	
						    if (account.getAcctDescr() != null)
						    	separator = " - ";
						    else
						    	separator = "";
							   	
						   	accountType = OsdcXmlXO.getAccountType(account.getAcctType()) + separator +  TextUtil.print(account.getAcctDescr());
						   
						   	osdcStatusDesc = "";
						   	
						   
						   	
							if ("".equalsIgnoreCase(osdcStatus))
							{						
						   		osdcStatusDesc = "No";
							}
							else if (osdcStatus.equalsIgnoreCase("S"))
							{
						   		osdcStatusDesc = codeAndDescriptionMap.get("S");
							}
						   	else if (osdcStatus.equalsIgnoreCase("R"))
						   	{
						   		osdcStatusDesc = codeAndDescriptionMap.get("R");
						   	}
						   		
						   	else  if (osdcStatus.equalsIgnoreCase("N"))
						   	{
						   		osdcStatusDesc = codeAndDescriptionMap.get("N");
						   	}
							   		
						   	else if (osdcStatus.equalsIgnoreCase("C"))
						   	{
						   		osdcStatusDesc = codeAndDescriptionMap.get("C");
						   	}
						   		
						
						   	if (color.equals("#ffffff"))
						   		color = "#e0e0e0";
						   	else
						   		color = "#ffffff";
						   		
							currNameOnAcct = TextUtil.print(account.getFirstName()) + " " + account.getLastName();
							if (!currNameOnAcct.equalsIgnoreCase(prevNameOnAcct)) { %>
								<tr style="background-color:<%=color %>">
									<td colspan="9"><%=currNameOnAcct%></td>
								</tr> <%
							}						   		
						 	prevNameOnAcct = currNameOnAcct;
						%> 
						
							<tr style="background-color:<%=color %>">
								<% if (osdcStatus != null && ("R".equals(osdcStatus) || "S".equals(osdcStatus))) {
									recallAccNum = recallAccNum+1;
								%>
									<td align="center" style="border-bottom: none; width:50px;"><a href="#" onclick="recall('<%=caseHeaderDTO.getCourtType() %>', <%=caseHeaderDTO.getIntCaseNum() %>, <%=account.getAcctNum() %>);"><img src="/CorisWEB/images/refresh.png" alt="Recall account" title="Recall account"/></a></td>
								<% } else { %>
									<td align="center" style="border-bottom: none; width:50px;"><input type="checkbox" name="checkAccount<%=account.getAcctNum() %>" id="checkAccount<%=account.getAcctNum() %>"  value="<%=account.getAcctNum() %>"></td>
								<% } %>
								<td width="10%"><%=account.getAcctNum() %>								
								<input type="hidden" name="childOf<%=account.getAcctNum() %>" id="childOf<%=account.getAcctNum() %>" value="<%=account.getInterestAcctNum()%>">
								<input type="hidden" name="parentOf<%=account.getInterestAcctNum() %>" id="parentOf<%=account.getInterestAcctNum() %>" value="<%=account.getAcctNum()%>">
								</td>
								<td width="15%"><%=accountType %>								 
								<input type="hidden" name="<%=account.getAcctNum() %>acctType" id="<%=account.getAcctNum() %>acctType" value="<%=OsdcXmlXO.getAccountType(account.getAcctType())%>">
								<input type="hidden" name="<%=account.getAcctNum() %>category" id="<%=account.getAcctNum() %>category" value="<%=account.getCategory()%>">
								<input type="hidden" name="<%=account.getAcctNum()%>feeCode" id="<%=account.getAcctNum()%>feeCode" value="<%=TextUtil.print(account.getFeeCode()) %>">
								</td>								
								<td width="10%"><%=TextUtil.printDate(account.getDueDate(), "MM/dd/yyyy") %></td>
								<td width="10%"><%=account.getDaysPastDue() %></td> 
								<td width="10%" align="right"><%=moneyFmt.format(account.getAmtDue()).replace("$","") %></td>
								<td width="10%" align="right"><%=moneyFmt.format(account.getAmtPaid()).replace("$","") %></td>
								<td width="10%" align="right"><%=moneyFmt.format(account.getAmtCredit()).replace("$","") %></td>
								<td width="10%" align="right"><%=moneyFmt.format(balance).replace("$","") %></td>
								<td width="15%"><%=TextUtil.print(osdcStatusDesc) %></td>
							</tr>
							<tr style="background-color:<%=color %>">
								<td style="border-top: none; width:50px;"></td>
								<td colspan="8">
									<textarea <%= osdcStatus != null &&"S".equals(osdcStatus)?"disabled":""  %> style="background: #f7f7ed;" rows="1" cols="110" class="form-control" maxlength="250" name="note<%=account.getAcctNum() %>" placeholder="Note" ><%=TextUtil.print(account.getNote()) %></textarea>
								</td>
							</tr>
						<tr>
							<td></td>
						</tr>
						<% }} %>
						</tbody>
					</table>
				</div>
				
				<div class="col-24">
			    	<div class="btn-toolbar float-right">
			    	<button type="button" class="btn btn-primary mr-1" onclick="this.disabled=true;saveNote(this);">Save Note</button>
			    	<button type="button" class="btn btn-primary mr-1" style="display:none" data-toggle="modal" data-target="#myModal">Payment History</button>
		    		<button type="button" class="btn btn-primary mr-1" onclick="this.disabled=true;showCaseHistory('<%=caseHeaderDTO.getCourtType() %>', '<%=caseHeaderDTO.getIntCaseNum() %>',this);">Case History</button>
		    		<button type="button" class="btn btn-primary mr-1" onclick="this.disabled=true;recallAll('<%=caseHeaderDTO.getCourtType() %>', '<%=caseHeaderDTO.getIntCaseNum() %>',this);" <%if(recallAccNum ==0 ){ %>disabled<%} %>>Recall All</button>
		    		<button type="button" class="btn btn-primary mr-1" onclick="this.disabled=true;sendOsdc(this);">Send to OSDC</button>
		    		<button type="button" class="btn btn-primary" id="setLocalBtn" onclick="this.disabled=true;setLocalDebtFlag('<%=caseHeaderDTO.getCourtType() %>', '<%=caseHeaderDTO.getIntCaseNum() %>',this);"><%=btnCaption %> for Local Debt Collections</button>
		    		</div>
		    	</div>
				
		 </form> 
	</div>
	
	<div id="myModal" class="modal fade">
	  <div class="modal-dialog modal-lg">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	      	<h4 class="modal-title">Payment History - Case: <%=caseHeaderDTO.getCaseNum() %></h4>
	        <button type="button" class="close" onclick="$('#myModal').modal('hide');">&times;</button>
	      </div>
	      <div class="modal-body">
	            <div style="background-color: #ffffff;">
		        <table id="paymentTable" class="table table-bordered table-sm" style="width:100%">
					<thead class="bg-secondary" style="color:white;">
						<tr>
							<th style="width: 30%">Payor</th>
							<th>Transaction</th>
							<th>Date</th>
							<th>Tender Type</th>
							<th>Amount</th>
						</tr>
					</thead>
					<tbody>
					<% int accountNum = 0;
					   for (DebtPaymentDTO payment:payments){ 
					   		if (accountNum != payment.getAccountNumber()){
					%>
								<tr class="info">
									<td colspan="5"><strong>Account Number:</strong> <%=payment.getAccountNumber() %> &nbsp;<strong>Account Type:</strong> <%=OsdcXmlXO.getAccountType(payment.getAccountType()) %>&nbsp; <strong>Party:</strong> <%=payment.getPayerLastName() %>, <%=payment.getPayerFirstName() %></td>
								</tr>
							<% accountNum = payment.getAccountNumber();
							} %>
						<tr>
							<td nowrap style="width: 30%"><%=payment.getPayerLastName() %>, <%=payment.getPayerFirstName() %></td>
							<td><%=payment.getTransNum() %></td>
							<td><%=TextUtil.printDate(payment.getTransDateTime(), "MM/dd/yyyy") %></td>
							<td><%=payment.getTenderType() %></td>
							<td><%=moneyFmt.format(payment.getAmountPaid()) %></td>
						</tr>
					<% } %>
					</tbody>
				</table>
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick="$('#myModal').modal('hide'); saveNote();">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
</html>
