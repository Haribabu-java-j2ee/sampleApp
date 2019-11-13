<!DOCTYPE html>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@include file="/fragments/NoCache.jspf"%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.xo.CodeDescriptionXrefXO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.codedescriptionxref.CodeDescriptionXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.casetype.CaseTypeBO"%>
<%@page import="gov.utcourts.coriscommon.dto.DebtCollectionSearchDTO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.personnel.PersonnelBO"%>
<%@page import="gov.utcourts.coriscommon.dto.DebtCollectionCaseDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>

<%
DecimalFormat moneyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
DebtCollectionCaseDTO dto = new DebtCollectionCaseDTO();
DebtCollectionSearchDTO sc = (DebtCollectionSearchDTO) request.getAttribute("searchCriteria");
if (sc == null)
	sc = new DebtCollectionSearchDTO();
Collection results = (Collection) request.getAttribute("results");
String resultsSize = (String) request.getAttribute("resultsSize");
if (results != null && results.size() == 1) { // get for the caseType
	dto = (DebtCollectionCaseDTO) results.iterator().next();
	results = (Collection) request.getAttribute("results");
}	
String searchMode = (String) request.getAttribute("searchMode");
Collection judgeList = (Collection) request.getAttribute("judgeList");
Collection caseTypeList = (Collection) request.getAttribute("caseTypes");
String accountTypesSelected = sc.getAccountTypes();
if (accountTypesSelected == null)
	accountTypesSelected = "";
String fineChecked = "", feeChecked = "", trustChecked = "";
if (accountTypesSelected.indexOf("I") > -1)
	fineChecked = "checked";
if (accountTypesSelected.indexOf("F") > -1)
	feeChecked = "checked";
if (accountTypesSelected.indexOf("T") > -1)
	trustChecked = "checked";
	
Iterator it;
PersonnelBO pvo;
CaseTypeBO ctvo;
boolean defaultByCase = true, defaultByAccount = false; // this sets the Radio button default so only one can be true
// default to last used mode 
if ("byAccount".equals(searchMode)) {
	defaultByCase = false; 
	defaultByAccount = true;
}	
String minStr = "";
String sysVer = "123";
String descrForCodeR = CodeDescriptionXrefXO.getDescriptionByCode("R", "osdc_acct_history", sc.getCourtType()).getDescription();
String descrForCodeS = CodeDescriptionXrefXO.getDescriptionByCode("S", "osdc_acct_history", sc.getCourtType()).getDescription();
List<CodeDescriptionXrefBO> codeDescriptionXrefVOList=CodeDescriptionXrefXO.getDescriptionListByTableName("osdc_acct_history", sc.getCourtType());
Map<String,String> codeAndDescriptionMap=new HashMap<String,String>();

for(CodeDescriptionXrefBO codeDescriptionXrefBO:codeDescriptionXrefVOList)
{
	codeAndDescriptionMap.put(codeDescriptionXrefBO.getCode(),codeDescriptionXrefBO.getDescription());
}
%>

<html lang="en">
<head>
<title>Cases Eligible for Debt Collection</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">

<link rel="stylesheet" href="/CorisWEB/css/jquery.dataTables.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">


<script type="text/javascript" src="/CorisWEB/js/fontawesome.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/moment.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script type="text/javascript" src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/datetime-moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>



<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script type="text/javascript" src="/CorisWEB/js/dataTables.buttons.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/buttons.print.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/pdfmake.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/vfs_fonts.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script type="text/javascript" src="/CorisWEB/js/buttons.html5.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<style>

table.dataTable thead th {
  background: transparent !important;
  white-space: nowrap;
}

table.dataTable thead span.sort-icon {
  display: inline-block;
  margin-left: 10px;
  padding-left: 5px;
  width: 14px;
  height: 14px;
}

table.dataTable thead .sorting span { background: url(data:image/gif;base64,R0lGODlhFQAJAIAAAM/b48/b4yH5BAEKAAEALAAAAAAVAAkAAAIXjI+AywnaYnhUMoqt3gZXPmVg94yJVQAAOw==) no-repeat bottom right; }
table.dataTable thead .sorting_asc span { background: url(data:image/gif;base64,R0lGODlhFQAEAIABAP///8/b4yH5BAEKAAEALAAAAAAVAAQAAAINjI+AywnaYnhUMopbAQA7) no-repeat center right; }
table.dataTable thead .sorting_desc span { background: url(data:image/gif;base64,R0lGODlhFQAEAIABAP///8/b4yH5BAEKAAEALAAAAAAVAAQAAAINjB+gC+jP2ptn0WskLQA7) no-repeat center right; }
table.dataTable thead .sorting_asc_disabled span { background: none; }
table.dataTable thead .sorting_desc_disabled span { background: none; }
</style>
  				
<script>

 
 

/**
 * On-ready function
 */
 
 
$(document).ready(function() {
	 
	if (<%=defaultByCase%>)
		{
		searchRadioClicked('byCase');
		}
		
	else 
		{
		searchRadioClicked('byAccount');		
		}
	
	 $('#datetimepicker1').datetimepicker({ 
			format: 'MM/DD/YYYY'
			});
		$('#datetimepicker2').datetimepicker({ 
			format: 'MM/DD/YYYY'
		});
	
}); 

function findByCaseBtnClicked() {
	if (validateFindCase()) {
		document.lookup.accountTypesHidden.value = "'F', 'I', 'T'"; // default to these 3 account types
		document.lookup.action = "OsdcEligibleLookupServlet";
		document.lookup.method = "post";
		document.lookup.submit();
		// this needs to come after the submit for IE to show the gif as moving
		document.getElementById("loadingDiv").style.display = '';
	}
}

function validateFindCase() {
	var x = document.forms[0];
	var submitOK = true;
	var errorMessage = "";
	var focusElement = null;
	var firstName = x.firstName.value.trim();
	var lastName = x.lastName.value.trim();
	
	if (x.caseNumber.value.length > 0) {
		if (isNaN(x.caseNumber.value)) {
			errorMessage = errorMessage+"\n* Case number must be numeric. ";
			submitOK = false;
			if (focusElement == null) focusElement = x.caseNumber;
		} else {
			return true;
		}
	} else {
		if (firstName.length == 0 && lastName.length == 0) {
		
			errorMessage = errorMessage+"* Case number or Name required for search. ";
			 
			 var corn=appUX.pop.alert(errorMessage, 350, 'auto', appUX.pop.styles.DANGER);
			 corn.options.onclosed.push(function() {
		 		 x.caseNumber.focus();
			   	});
			  
	
			return false;
		}	
	 
		if (x.firstName.value.indexOf('"') >= 0) {
				errorMessage = errorMessage+"* First name cannot contain double-quote characters. \n (use single-quotes instead)"
				submitOK = false;
				if (focusElement == null) focusElement = x.firstName;
		} 
		if (lastName.length < 2) {
			errorMessage = errorMessage+"<br>* Last Name must contain at least 2 characters. ";
			submitOK = false;
			if (focusElement == null) focusElement = x.lastName;
		} 
		if (x.lastName.value.indexOf('"') >= 0) {
				errorMessage = errorMessage+"<br>* Last name cannot contain double-quote characters. \n (use single-quotes instead)"
				submitOK = false;
				if (focusElement == null) focusElement = x.lastName;
		}
	}
	
	if (submitOK) {
		return true; 
	} else {
		 var corn=appUX.pop.alert(errorMessage, 350, 'auto', appUX.pop.styles.DANGER);
		 corn.options.onclosed.push(function() {
			  focusElement.focus();	
		   	});		 
	
		return false;
	}	
}

function buildAccountsTypeList(){
	// return a list of values that are checked by user
	var acctList = "";
	var acctBoxes = document.getElementsByName('acctTypeChkbox');
	for(var i=0; i<acctBoxes.length; ++i){
	    if(acctBoxes[i].checked){
	    	if(acctList != "")
	    		acctList += ", ";
	    	acctList += "'" + acctBoxes[i].value + "'";
	    }
	}
	return acctList;
}

function findAccountsBtnClicked() {
	var sufficientCriteria = true;
	var judgeNotSelected = (document.lookup.judge.value == 'all');
	// will need either a sentence date range or a case type
	var startDate = document.getElementById('start_date').value;
	var endDate = document.getElementById('end_date').value;		
	if ( (startDate==null || startDate=='') && (endDate==null || endDate=='')) {
		sufficientCriteria = false;
	}
	
	if (judgeNotSelected && !sufficientCriteria) {
		var caseType = document.getElementById('caseType').value;
		if (!sufficientCriteria && caseType == 'all') {
			sufficientCriteria = false;
			var corn=appUX.pop.confirm("Warning", "Searching all judges may take a long time. Do you want to continue?", "Yes", "No", 500, 'auto', appUX.pop.styles.PRIMARY, function(action){
				if(action)
					{
					 
					validateFindAccounts();
					corn.close();
					}
				else
					{
					corn.close();
					
					}
			});					
		} else {
			validateFindAccounts();
		}
	} else {
		validateFindAccounts();
	}
}

function validateFindAccounts() {
	var x = document.forms[0];
	var submitOK = true;
	var errorMessage = '';
	var focusElement = null;
	var accountTypes = buildAccountsTypeList();
	document.lookup.accountTypesHidden.value = accountTypes;	
	
	
	if (accountTypes == '') {
		errorMessage = errorMessage+'\n* At least one Account Type needs to be checked. ';
		submitOK = false;
		if (focusElement == null) focusElement = x.accountType;
	}		
	
	if (isNaN(x.balanceAmt.value)) {
		errorMessage = errorMessage+'\n* Balance must be numeric. ';
		submitOK = false;
		if (focusElement == null) focusElement = x.caseNumber;
	}
	
	if (x.balanceAmt.value < 0) {
		errorMessage = errorMessage+'\n* Balance must be non negative. ';
		submitOK = false;
		if (focusElement == null) focusElement = x.caseNumber;
	}			
	
//	if (x.start_date.value > '' && x.end_date.value == '')
//		x.end_date.value = '12/12/3000';
		
	if (x.start_date.value > '' && x.end_date.value > '') {
		if (x.start_date.value > x.end_date.value) {
			errorMessage = errorMessage+'\n* Start Date must be before End Date.';
			submitOK = false;
		}
		if (x.start_date.value == x.end_date.value) {
			x.end_date.value = '';
		}
	}	 
	
	if (document.lookup.judge.value == 'all')
		document.lookup.judge.value = '';
	if (document.lookup.caseType.value == 'all')
		document.lookup.caseType.value = '';

	if (submitOK) {
		document.lookup.action = "OsdcEligibleLookupServlet";
		document.lookup.method = "post";
		document.lookup.submit();
		
		// this needs to come after the submit for IE to show the gif as moving
		document.getElementById("loadingDiv").style.display = '';
			
		return true; 
	} else {
		 var corn=appUX.pop.alert(errorMessage, 350, 'auto', appUX.pop.styles.DANGER);
		 corn.options.onclosed.push(function() {
			 focusElement.focus();
		   	});
		  
		return false;
	}
}			

function searchRadioClicked(whichRadio){
	document.getElementById('searchDivRadio').value = whichRadio;
	
	// display or hide divs according to radio button selected
	if (whichRadio == 'byCase') {
		document.getElementById('caseNumber').select();
		document.getElementById('caseNumber').focus();
	} else if (whichRadio == 'byAccount'){
		if (document.getElementById('balanceAmt').value == '0.0')
			document.getElementById('balanceAmt').value = '100.00';		
	}
}



function clearBtnClicked(mode) {
	if (mode == 'case') {
		document.getElementById('caseNumber').value = '';
		document.getElementById('lastName').value = '';
		document.getElementById('firstName').value = '';
		document.getElementById('displayCaseType').value = '';
	} else if (mode == 'account') {
		// reset the drop down boxes		
		var selects = document.getElementsByTagName('select');
		for(var i=0; i<selects.length; ++i){
			temp = selects[i].id;						
			selects[i].selectedIndex = 0;
		}
		document.getElementById('start_date').value = '';
		document.getElementById('end_date').value = '';
		document.getElementById('balanceAmt').value = '100.00';
		document.getElementById('overdue').value = 90;
	}	
	// clear table data
	$("#resultBody").remove();
}
		
function setLocalDebtFlagFromLookup(flag, courtType, intCaseNum, stateStatusDescr){
	if (stateStatusDescr.indexOf('<%=descrForCodeR%>') > -1 || stateStatusDescr == '<%=descrForCodeS%>') {
		 appUX.pop.alert("You can't set as Local Debt Collection since it has been sent to State Debt Collection.", 450, 'auto', appUX.pop.styles.DANGER);		
		 return false;
	}
	if (stateStatusDescr.indexOf('Accounts Sent') > 0) {
		 appUX.pop.alert("You can't set as Local Debt Collection since some accounts have been sent to State Debt Collection.", 450, 'auto', appUX.pop.styles.DANGER);	
		return false;
	}	
	$.ajax({
	   url: '/CorisWEB/OsdcAjaxServlet?mode=setFlag&userId=' + document.getElementById("userId").value,
	   data: {
	   	  localDebtFlag: flag,
	      courtType: courtType,
	      intCaseNum: intCaseNum
	   },
	   error: function() {
		   appUX.pop.alert("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER);	 
	   },
	   dataType: 'json',
	   success: function(data) {
		   appUX.pop.alert(data.message, 300, 'auto', appUX.pop.styles.INFO);
	   	 var updatedValue = 'Y';
	   	 if (flag == 'Y')
	   	 	updatedValue = 'N';
	   	 	
	   	 // need to see if there are other accounts for this case and mark them as local (or not) as well.
	   	 var className = '.'+intCaseNum;
	   	 var allCases = $(className);
	   	 numRows = allCases.length;	   	 	
	   	 var buildImage = '';
	   	 var graphicRowId = '';
	   	 if (updatedValue == 'Y') {
	   	 	for (var i=0; i<numRows; i++) {
	   	 		// update the Local Debt Coll. column icon
	   	 		graphicRowId = allCases[i].id;
	   	 		if (graphicRowId.indexOf('col8') > -1) {
			   	 	buildImage = "<td><img src='/CorisWEB/images/green-check-icon.gif' onclick=\"" + "setLocalDebtFlagFromLookup('" 
			   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + stateStatusDescr + "'" + ");\"" + " border='0' title=\"Undo Local Status\"></td>";	   	 
					document.getElementById(graphicRowId).innerHTML = buildImage;
				}	
			}	   	 
	   	 } else {
	   	 	for (var i=0; i<numRows; i++) {
 	   	 		// update the Local Debt Coll. column icon
	   	 		graphicRowId = allCases[i].id;
	   	 		if (graphicRowId.indexOf('col8') > -1) {
			   	 	buildImage = "<td><img src='/CorisWEB/images/delete.png' onclick=\"" + "setLocalDebtFlagFromLookup('" 
			   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + stateStatusDescr + "'" + ");\"" + " border='0' title=\"Set Local Status\"></td>";
			   	 	document.getElementById(graphicRowId).innerHTML = buildImage;
			   	} 	
		   	}
	   	 }	 	
	   },
	   type: 'POST'
	});
}

function getInterestAccrued(courtType, intCaseNum, userId, caseNumber,debtCollStatusDesc){
	$.ajax({
	   url: '/CorisWEB/OsdcAjaxServlet?mode=calcInterest&userId=' + userId,
	   data: {
	   	  courtType: courtType,
	      intCaseNum: intCaseNum,
	      userId: userId
	   },
	   error: function() {		   
			 appUX.pop.alert("Unable to reach server!", 350, 'auto', appUX.pop.styles.DANGER);	  
	   },
	   dataType: 'json',
	   success: function(data) {
	   		var url = "/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + courtType + "&intCaseNum=" + intCaseNum + "&userId=" + userId;
	 
	 		
			if (data.interestMsg == "No Message") {
				loadJsp(courtType, intCaseNum, userId,debtCollStatusDesc);
		 	} else {
		 		
			 	var message=data.interestMsg + ' on case ' + caseNumber;
			 	var corn=appUX.pop.alert(message, 500, 'auto', appUX.pop.styles.INFO);
		 		 
		 	  	corn.options.onclosed.push(function() {
		 	  	  loadJsp(courtType, intCaseNum, userId,debtCollStatusDesc);
			   	});
		 		
		 	 
			  
		 	}	   	    	
	   },
	   type: 'POST'
	});
}


function openDetail(courtType, intCaseNum, userId, caseNumber,debtCollStatusDesc) {
	getInterestAccrued(courtType, intCaseNum, userId, caseNumber,debtCollStatusDesc);
}

function loadJsp(courtType, intCaseNum, userId,debtCollStatusDesc){	 

	appUX.ajax.call("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + courtType + "&intCaseNum=" + intCaseNum + "&userId=" + userId+ "&searchMode=" + document.getElementById("searchMode").value+ "&debtCollStatusDesc=" + debtCollStatusDesc, displayAccounts,'POST');
    var div = document.getElementById('loadModalBody');
	div.innerHTML = '';
    $('#detailModal').modal('show');
}

var displayAccounts = function(err, data,xhr){
	 
	if(err){
		appUX.pop.alert(err, 300, 'auto', appUX.pop.styles.DANGER);
	}else {
		var div = document.getElementById('loadModalBody');
		div.innerHTML = data; 	 	
	}
}
//osdcAccount.jsp related script

function selectAll(selectAll){
		 $('input[type=checkbox]').each(
		 	function(){
		 		if (selectAll.checked)
		     		this.checked = true;
		  		else
		     		this.checked = false;
			}
		);
	}	
	
	function popupCenter(url, title, w, h) {
      var left = (screen.width/2)-(w/2);
      var top = (screen.height/2)-(h/2);
      return window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
    }

	function showCaseHistory(courtType, intCaseNum,obj){
	    showHistory(courtType, intCaseNum);
		obj.disabled=false;
	}
	
	function showHistory(courtType, intCaseNum){
		 popupCenter("/CorisWEB/OsdcDetailServlet?mode=caseHistory&courtType="+courtType+"&intCaseNum="+intCaseNum+ '&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>, "CASE HISTORY", 800, 600);
	}
	
	function popAlertWithEnableObj(message, width,height,style,obj)
	{
	
	 var corn=appUX.pop.alert(message, width, height, style);
	  corn.options.onclosed.push(function() {
			   enableObject(obj);
		   	});
			  
	}
	
	
	function popAlert(message, width,height,style)
	{
	
	   appUX.pop.alert(message, width, height, style);
			 
	}
	
	
	
	function saveNote(obj){	 
		
			$.ajax({
			   url: '/CorisWEB/OsdcAjaxServlet?mode=saveNote&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>,
			   data: {
			   	  frmData: $('#accountForm').serialize()
			   },
			   error: function() {
			   
			   popAlertWithEnableObj("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER,obj);	   
			    
			   },
			   
		      dataType: 'json',
		    
			   success: function(data) {	
			   popAlertWithEnableObj(data.message, 300, 'auto', appUX.pop.styles.SUCCESS,obj);	   
			
			   },
		   type: 'POST'
		});
		 
	}
	
	function enableObject(obj)
	{	    
		obj.disabled=false;
	}
		
 
	
	function setLocalDebtFlag(courtType, intCaseNum, obj){

		$.ajax({
		   url: '/CorisWEB/OsdcAjaxServlet?mode=setFlag&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>,
		   data: {
		   	  localDebtFlag: $('#localStatus').val(),
		      courtType: courtType,
		      intCaseNum: intCaseNum
		   },
		   error: function() {
		     popAlertWithEnableObj("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER,obj);		      
		    
		   },
		   dataType: 'json',
		   success: function(data) {
		     if (data.error) {
		     
		      popAlertWithEnableObj(data.message, 300, 'auto', appUX.pop.styles.DANGER,obj);	 
		     
		     	return false; // nothing was done so no need to update anything on the screen
		     }	
		   
		     $('#localStatus').val(data.status);
		     if (data.status == 'Y')
		     	$('#setLocalBtn').text("Unset for Local Debt Collections");
		     else
		     	$('#setLocalBtn').text("Set for Local Debt Collections");
		     	
		     	  popAlertWithEnableObj(data.message, 300, 'auto', appUX.pop.styles.SUCCESS,obj);		 
		   	 
			   	 // Refresh the Case State Debt Collection Status on main screen
			   	 var updatedValue = data.status;
			   	 // need to see if there are other accounts for this case and mark them as local (or not) as well.
			   	 var className = '.'+intCaseNum;
			   	 var allCases = $(className);
			   	 numRows = allCases.length;	   	 	
			   	 var buildImage = '';
			   	 var graphicRowId = '';			 
			   	 if (updatedValue == 'Y') {
			   	 	for (var i=0; i<numRows; i++) {
			   	 		// update the Local Debt Coll. column icon
			   	 		graphicRowId = allCases[i].id;
			   	 		if (graphicRowId.indexOf('col8') > -1) {
					   	 	buildImage = "<td><img src='/CorisWEB/images/green-check-icon.gif' onclick=\"" + "setLocalDebtFlagFromLookup('" 
					   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + document.getElementById('debtCollStatusDesc').value + "'" + ");\"" + " border='0' title=\"Undo Local Status\"></td>";	   	 
							document.getElementById(graphicRowId).innerHTML = buildImage;
						}	
					}	   	 
			   	 } else {			   	   
			   	 	for (var i=0; i<numRows; i++) {
		 	   	 		// update the Local Debt Coll. column icon
			   	 		graphicRowId = allCases[i].id;
			   	 		if (graphicRowId.indexOf('col8') > -1) {
					   	 	buildImage = "<td><img src='/CorisWEB/images/delete.png' onclick=\"" + "setLocalDebtFlagFromLookup('" 
					   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum  + "', '" + document.getElementById('debtCollStatusDesc').value  + "'" + ");\"" + " border='0' title=\"Set Local Status\"></td>";
					   	 	document.getElementById(graphicRowId).innerHTML = buildImage;
					   	} 	
				   	}
			   	 }	 			   	 
			   	 
			   },
			   type: 'POST'
		});
	}
	
	 
	
	function recallAll(courtType, intCaseNum,obj){
		var corn=appUX.pop.confirm("Warning","Are you sure that you want to recall all accounts in this case?", "Yes","No",300,'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
				 	$.ajax({
					   url: '/CorisWEB/OsdcAjaxServlet?mode=recallAll&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>,
					   data: {
					   	  courtType: courtType,
					   	  intCaseNum: intCaseNum
					   },
					   error: function() {
					         popAlertWithEnableObj("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER,obj);
					      corn.close();
					   },
					   dataType: 'json',
					   success: function(data) {
					     //refresh the div
					     if (!data.error){
					   
					     $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + data.courtType + "&intCaseNum=" + data.intCaseNum + "&userId=" + <%=request.getSession(false).getAttribute("USER_ID")%>+ "&searchMode=" + document.getElementById("searchMode").value);
					      var corn=appUX.pop.alert(data.message, 300, 'auto', appUX.pop.styles.SUCCESS);
		                  document.addEventListener('popcornclosed', function (event) {
						   if (event.detail == corn.options.id) {
						         enableObject(obj);
							 	
							
							 }
						});		
			     
					     
					     }
					     else
					     {
					       appUX.pop.alert(data.message, 300, 'auto', appUX.pop.styles.DANGER);
					     }
					     
					 
					   	 // Refresh the Case State Debt Collection Status on main screen
					   	 var updatedValue = 'N';
						 var newStateDebtCollStatus = 'No';
						 if (data.numAcctsOnCaseAtOsdc > 0)
						 	newStateDebtCollStatus = '<%=codeAndDescriptionMap.get("R")%>';
						 var courtType = document.getElementById('courtType').value;
						 var intCaseNum = document.getElementById('intCaseNum').value;
					   	 var className = '.'+intCaseNum;
					   	 var allCases = $(className); // there may be multiple rows for this case#
					   	 numRows = allCases.length;	   	 	
					   	 var graphicRowId = '';
				   	 	 for (var i=0; i<numRows; i++) {
				   	 		 graphicRowId = allCases[i].id;
				   	 		 if (graphicRowId.indexOf('col7') > -1) {
								document.getElementById(graphicRowId).innerHTML = newStateDebtCollStatus;
							 }
							 
							 // Rebuild the set local debt collection graphic as available.
							 //alert("graphicRowId.indexOf('col8') is " + graphicRowId.indexOf('col8'));
							 if (graphicRowId.indexOf('col8') > -1) {
						   	 	buildImage = "<td><img src='/CorisWEB/images/delete.png' onclick=\"" + "setLocalDebtFlagFromLookup('" 
						   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + newStateDebtCollStatus + "'" + ");\"" + " border='0' title=\"Set Local Status\"></td>";
						   	 	document.getElementById(graphicRowId).innerHTML = buildImage;
						   	 } 	
						 }						   	 
					   },
					   type: 'POST'
					});
						corn.close();
				}
				else
				{
				    enableObject(obj);
					corn.close();
				}
			});
			
			 corn.options.onclosed.push(function() {
				 enableObject(obj);
				});
			
			 
			
	}
	
 
	
	function recall(courtType, intCaseNum, accountNum){
	 	$.ajax({
		   url: '/CorisWEB/OsdcAjaxServlet?mode=recall&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>,
		   data: {
		   	  courtType: courtType,
		   	  intCaseNum: intCaseNum,
		   	  accountNum: accountNum
		   },
		   error: function() {
		     	popAlert("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER);
		    
		   },
		   dataType: 'json',
		   success: function(data) {
		     //refresh the div
		     if (!data.error){
		     $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + data.courtType + "&intCaseNum=" + data.intCaseNum + "&userId=" + <%=request.getSession(false).getAttribute("USER_ID")%>+ "&searchMode=" + document.getElementById("searchMode").value);
		       var corn=appUX.pop.alert(data.message, 300, 'auto', appUX.pop.styles.SUCCESS);
		       		               	
		   
		     
		     }
		     else
		     {
		       popAlert(data.message, 300, 'auto', appUX.pop.styles.DANGER);
		     }
		     	
		   
		   	 var updatedValue = 'N';
		   	 var courtType = document.getElementById('courtType').value;
			 var intCaseNum = document.getElementById('intCaseNum').value;
		   	 var newStateDebtCollStatus = 'No';
			 if (data.numAcctsOnCaseAtOsdc > 0)
			 	newStateDebtCollStatus = '<%=codeAndDescriptionMap.get("R")%>';
		   	 // Refresh the Case State Debt Collection Status on main screen
		   	 var className = '.' + data.intCaseNum;
		   	 var allCases = $(className);
		   	 numRows = allCases.length;	   	 	
		   	 var graphicRowId = '';
	  	 	 for (var i=0; i<numRows; i++) {
	  	 		 graphicRowId = allCases[i].id;
	  	 		 if (graphicRowId.indexOf('col7') > -1) {
					document.getElementById(graphicRowId).innerHTML = newStateDebtCollStatus;
			 	 }
			 	 if (newStateDebtCollStatus == 'No') {
					 // Rebuild the set local debt collection graphic as available.
					 if (graphicRowId.indexOf('col8') > -1) {
				   	 	buildImage = "<td><img src='/CorisWEB/images/delete.png' onclick=\"" + "setLocalDebtFlagFromLookup('" 
				   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + newStateDebtCollStatus + "'" + ");\"" + " border='0' title=\"Set Local Status\"></td>";
				   	 	document.getElementById(graphicRowId).innerHTML = buildImage;
				   	 } 	
			 	 }
			 }					   	 
		   },
		   type: 'POST'
		});
	}
	
 
	
		function sendOsdc(obj){
		
			var updatedValue = 'N';
			var stateStatusDescr = '<%=codeAndDescriptionMap.get("R")%>';
			var courtType = document.getElementById('courtType').value;
			var intCaseNum = document.getElementById('intCaseNum').value;		 
			
			var atLeastOne = false;
			var localStatus = $('#localStatus').val();
			var i=0;
			var accNumArray=[];
			$('input[type=checkbox]').each(
			 	function(){
			 	
			 	 
			 		if (this.checked && this.name.indexOf('checkAccount') > -1){
			 			accNumArray[i]=this.name.substring("checkAccount".length, this.name.length);
			 			atLeastOne = true;	
			 			i++;
			 		}	
				}
			);
		 
			if (localStatus == 'Y'){
			
		
			    popAlertWithEnableObj("Account(s) cannot be sent to OSDC because it is set for Local Debt Collection.", 500, 'auto', appUX.pop.styles.DANGER,obj);			 
				return false;
			}
			
			if (!atLeastOne){
			
				popAlertWithEnableObj("Please select at least one account!", 300,'auto',appUX.pop.styles.DANGER,obj);		 
				return false;
			}			 
	 
			
			var relatedAcctSelectionMsg=validateTrustReleatedAccounts(accNumArray);
		
       
			if(relatedAcctSelectionMsg!="" && relatedAcctSelectionMsg.length>0)
				{
				relatedAcctSelectionMsg=relatedAcctSelectionMsg+"Required account numbers selected, please review and click on Send to OSDC button";
				popAlertWithEnableObj(relatedAcctSelectionMsg, 300, 'auto', appUX.pop.styles.DANGER,obj);				
				return false;
				}
			
			
			var warningMsg=checkForWarningMessage(accNumArray);
			
		 
			
			if(warningMsg!="" && warningMsg.length>0)
				{
				
				warningMsg="For account number(s) "+warningMsg+" <br>A judgment has not been automatically created.  If a Ruling or Order to create a judgment was made, it should be entered manually."
			 
				}
			
			if (warningMsg!="" && warningMsg.length>0) {
			
			
				 var corn=appUX.pop.alert(warningMsg, 500, 'auto', appUX.pop.styles.INFO);
				  
				  corn.options.onclosed.push(function() {
				    sendOSDCAjaxRequest(updatedValue,stateStatusDescr,courtType,intCaseNum,obj);
			    	});
			 	
			 
				
							 
			 						
			} else {
				sendOSDCAjaxRequest(updatedValue,stateStatusDescr,courtType,intCaseNum,obj);
			}
			 		
			
	}
	
	function sendOSDCAjaxRequest(updatedValue,stateStatusDescr,courtType,intCaseNum,obj)
	{ 		
		$.ajax({
		   url: '/CorisWEB/OsdcAjaxServlet?mode=osdcSend&userId=' + <%=request.getSession(false).getAttribute("USER_ID")%>,
		    data: {
		   	  frmData: $('#accountForm').serialize()
		   },
		   error: function() {
		   popAlertWithEnableObj("Unable to reach server!", 300, 'auto', appUX.pop.styles.DANGER,obj);
		    
		   },
		   dataType: 'json',
		   success: function(data) {
		   
		     //refresh the div
		     if (!data.error){
		   
		         var corn=appUX.pop.alert(data.message, 350, 'auto', appUX.pop.styles.SUCCESS);
		         corn.options.onclosed.push(function() {
				     $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + data.courtType + "&intCaseNum=" + data.intCaseNum + "&userId=" + <%=request.getSession(false).getAttribute("USER_ID")%>+ "&searchMode=" + document.getElementById("searchMode").value);
				     enableObject(obj);		
			    	});
			 	
		       	
		     
		     }
		     else
		     {
		        
		       var corn= appUX.pop.alert(data.message, 300, 'auto', appUX.pop.styles.DANGER);
		  	   corn.options.onclosed.push(function() {
				     $("#loadModalBody").load("/CorisWEB/OsdcDetailServlet?mode=accountDetail&courtType=" + courtType + "&intCaseNum=" + intCaseNum + "&userId=" + <%=request.getSession(false).getAttribute("USER_ID")%>+ "&searchMode=" + document.getElementById("searchMode").value);
				     enableObject(obj);		
			    	});
		    
		     }
		       
		   	 
		   	 // Refresh the Case State Debt Collection Status on main screen
		   	 var className = '.' + intCaseNum;		   
		   	 var allCases = $(className);
		   	 numRows = allCases.length;		   	
		   	 var graphicRowId = '';
	   	 	 for (var i=0; i<numRows; i++) {
	   	 		 graphicRowId = allCases[i].id;
	   	 		 if (graphicRowId.indexOf('col7') > -1) {
					document.getElementById(graphicRowId).innerHTML = '<%=codeAndDescriptionMap.get("R")%>';
				 }
				 
				 // Rebuild the set local debt collection graphic as greyed out.
				 if (graphicRowId.indexOf('col8') > -1) {
			   	 	buildImage = "<td><img src='/CorisWEB/images/deleteGreyed.png' onclick=\"" + "setLocalDebtFlagFromLookup('" 
			   	 	+ updatedValue + "', '" + courtType + "', '" + intCaseNum + "', '" + stateStatusDescr + "'" + ");\"" + " border='0' title=\"Set Local Status\"></td>";
			   	 	document.getElementById(graphicRowId).innerHTML = buildImage;
			   	 } 	
				 
			 }			   	 
		   },
		   type: 'POST'
		});	
		
	}
	
	function validateTrustReleatedAccounts(accNumArray)
	{
    	var relatedAcctSelectionMsg="";
		
		if(accNumArray.length>0)
			{
			for(var accIndex=0;accIndex<accNumArray.length;accIndex++)
				{				
				
				if(document.getElementById(accNumArray[accIndex]+"acctType")!=null && document.getElementById(accNumArray[accIndex]+"acctType").value=='Trust')
					{
					
					if(document.getElementById("childOf"+accNumArray[accIndex])!=null && document.getElementById("childOf"+accNumArray[accIndex]).value!=0)
						{
					 
						 if(document.getElementById("checkAccount"+document.getElementById("childOf"+accNumArray[accIndex]).value)!=null)
							 {
							 
							  if( ((accNumArray.indexOf(document.getElementById("childOf"+accNumArray[accIndex]).value)) == -1))
								  {
								  
								  relatedAcctSelectionMsg=relatedAcctSelectionMsg+"Trust account "+accNumArray[accIndex]+" and interest account " +document.getElementById("childOf"+accNumArray[accIndex]).value+" must be sent to OSDC at the same time. <br>";
								  document.getElementById("checkAccount"+document.getElementById("childOf"+accNumArray[accIndex]).value).checked=true;
								  }
							 
							 
							 }
						
						}
					
				    else if(document.getElementById("parentOf"+accNumArray[accIndex])!=null && document.getElementById("parentOf"+accNumArray[accIndex]).value!=0)
						{
				    	
				     
						
				    	
				    	 if(document.getElementById("checkAccount"+document.getElementById("parentOf"+accNumArray[accIndex]).value)!=null)
						 {
				    	 
				    		 
				    		 if( ((accNumArray.indexOf(document.getElementById("parentOf"+accNumArray[accIndex]).value)) == -1))
							  {
				    			 relatedAcctSelectionMsg=relatedAcctSelectionMsg+"Trust account "+document.getElementById("parentOf"+accNumArray[accIndex]).value+" and interest account " +accNumArray[accIndex]+" must be sent to OSDC at the same time. <br>";
				    		     document.getElementById("checkAccount"+document.getElementById("parentOf"+accNumArray[accIndex]).value).checked=true;
							  }
						 
						 
						 }
						
						
						}
					
					
					}
				
				}
			
			}
		
		return relatedAcctSelectionMsg;
		
	}
	
	
	function checkForWarningMessage(accNumArray)
	{
    	var warningMsg="";
		
		
		var feeCodeArray = ['PN', 'PS', 'FN', 'FS'];
	
		
		if(accNumArray.length>0)
			{
			
			for(var accIndex=0;accIndex<accNumArray.length;accIndex++)
				{
			 
				if("R"!=document.getElementById(accNumArray[accIndex]+"category").value)
					{
					warningMsg=warningMsg+accNumArray[accIndex];
					
					if((accIndex+1)!=accNumArray.length)
						{
						warningMsg=warningMsg+",";
						}
					
					}
				
			  	else if (document.getElementById(accNumArray[accIndex]+"feeCode")!=null && ((feeCodeArray.indexOf(document.getElementById(accNumArray[accIndex]+"feeCode").value)) != -1)) {
					
					warningMsg=warningMsg+accNumArray[accIndex];
					
					if((accIndex+1)!=accNumArray.length)
						{
						warningMsg=warningMsg+",";
						}
					
				   }  
				}
			
			}
		return warningMsg;
	}
</script>
		
</head>

<body>

<!-- navigation -->
<!-- 	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> -->
<!-- 		<span class="navbar-brand">CORIS</span> -->
<!-- 		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-dark"> -->
<!-- 			<span class="navbar-toggler-icon"></span> -->
<!-- 		</button> -->
<!-- 		<div class="collapse navbar-collapse" id="navbar-dark"> -->
<!-- 			<div class="nav navbar-nav ml-auto"> -->
<!-- 				<a href="/CorisWEB/LogoutServlet" class="nav-item nav-link" id="logOutBtn">Logout</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</nav> -->
	
	<!-- main content -->
	<div class="container-fluid">
		<div class="card mx-2 my-4">
			<div class="card-header font-weight-bold">
				Cases eligible for transfer to Office of State Debt Collection
			</div>
			<div class="card-body">
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<a href="#byCaseContent" class="nav-link <%= defaultByCase ? "active" : "" %>" id="byCase" onclick="searchRadioClicked('byCase');clearTableData()" data-toggle="tab">Search by Case/Name</a>
					</li>
					<li class="nav-item">
						<a href="#byAccountContent" class="nav-link <%= defaultByAccount ? "active" : "" %>" id="byAccount" onclick="searchRadioClicked('byAccount');clearTableData()" data-toggle="tab">Search by Judge/Account</a>
					</li>
				</ul>
				
				<form id="lookup" name="lookup">
					<input type="hidden" id="accountTypesHidden" name="accountTypesHidden" value="">
					<input type="hidden" id="courtTypeHidden" name="courtTypeHidden" value="<%= sc.getCourtType() %>">
					<input type="hidden" id="locnCodeHidden" name="locnCodeHidden" value="<%= sc.getLocationCode() %>">
					<input type="hidden" id="searchDivRadio" name="searchDivRadio" value="<%= defaultByCase ? "byCase" : "byAccount" %>">
					<input type="hidden" id="userId" name="userId" value="<%= request.getSession(false).getAttribute("USER_ID") %>">
					
					<div class="tab-content">
						<div class="tab-pane fade <%= defaultByCase ? "show active" : "" %>" id="byCaseContent">
							<div class="form-row mt-4">
								<div class="form-group col-md offset-lg-2 col-lg-10">
									<div class="row align-items-center">
										<label for="caseNumber" class="col-sm-8 col-md-9 control-label text-sm-right">Case</label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="caseNumber" name="caseNumber" value="<%= TextUtil.print(sc.getCaseNumber()) %>" placeholder="Enter case number" maxlength="9">
										</div>
									</div>
								</div>
								<div class="form-group col-md col-lg-10">
									<div class="row align-items-center">
										<label for="displayCaseType" class="col-sm-8 col-md-9 control-label text-sm-right">Case Type</label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="displayCaseType" name="displayCaseType" value="<%= TextUtil.print(dto.getCaseType()) %>" readonly="readonly">
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md offset-lg-2 col-lg-10">
									<div class="row align-items-center">
										<label for="lastName" class="col-sm-8 col-md-9 control-label text-sm-right">Last Name</label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="lastName" name="lastName" value="<%= TextUtil.print(sc.getLastName()) %>" placeholder="Enter last name" maxlength="60">
										</div>
									</div>
								</div>
								<div class="form-group col-md col-lg-10">
									<div class="row align-items-center">
										<label for="firstName" class="col-sm-8 col-md-9 control-label text-sm-right">First Name</label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="firstName" name="firstName" value="<%= TextUtil.print(sc.getFirstName()) %>" placeholder="Enter first name" maxlength="60">
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-sm-20 col-md-23 col-lg-21 clearfix">
									<button type="button" class="btn btn-secondary ml-2 float-right" id="clearCaseBtn" onclick="clearBtnClicked('case');">Clear</button>
									<button type="button" class="btn btn-primary ml-2 float-right" id="findByCaseBtn" onclick="findByCaseBtnClicked();">Find By Case</button>
								</div>
							</div>
						</div>
						
						<div class="tab-pane fade <%= defaultByAccount ? "show active" : "" %>" id="byAccountContent">
							<div class="form-row mt-4">
								<div class="form-group col-md-13 offset-lg-2 col-lg-10">
									<div class="row align-items-center">
										<label for="judge" class="col-sm-8 col-md-9 control-label text-sm-right">Judge</label>
										<div class="col-sm-12 col-md-13">
											<select class="form-control" id="judge" name="judge">
												<option value="all">All Judges</option>
												<% if (judgeList != null && judgeList.size() > 0) {
													it = judgeList.iterator();
													while (it.hasNext()) {
														pvo = (PersonnelBO) it.next(); %>
														<option value="<%= pvo.getUseridSrl() %>" <%= TextUtil.isSelected(pvo.getUseridSrl(), sc.getJudgeId()) %>><%= TextUtil.getNameSortedLastFirstMiddle(pvo.getFirstName(), null, pvo.getLastName(), null) %></option>
													<% }
												} %>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group col-md-11 col-lg-10">
									<div class="row align-items-center">
										<label for="accountType" class="col-sm-8 control-label text-sm-right">Account(s)</label>
										<div class="col-sm-12 col-md-14">
											<div class="form-check form-check-inline">
												<input type="checkbox" class="form-check-input" id="fineChkbox" name="acctTypeChkbox" value="I" <%= fineChecked %>>
												<label class="form-check-label" for="fineChkbox">Fine</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="checkbox" class="form-check-input" id="feeChkbox" name="acctTypeChkbox" value="F" <%= feeChecked %>>
												<label class="form-check-label" for="feeChkbox">Fee</label>
											</div>
											<div class="form-check form-check-inline">
												<input type="checkbox" class="form-check-input" id="trustChkbox" name="acctTypeChkbox" value="T" <%= trustChecked %>>
												<label class="form-check-label" for="trustChkbox">Trust</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-13 offset-lg-2 col-lg-10">
									<div class="row align-items-center">
										<label for="caseNumber" class="col-sm-8 col-md-9 control-label text-sm-right">Sentenced</label>
										<div class="col-sm-12 col-md-13">
											<div class="row">
												<div class="col input-group date" id='datetimepicker1'>
													<input type="text" class="form-control" id="start_date" name="start_date" value="<%= TextUtil.printDatetime(sc.getFromSentDate()) %>">
													<span class="input-group-text input-group-addon">
														<span class="fa fa-calendar"></span>
													</span>
												</div>
												<div class="col input-group date" id='datetimepicker2'>
													<input type="text" class="form-control" id="end_date" name="end_date" value="<%= TextUtil.printDatetime(sc.getToSentDate()) %>">
													<span class="input-group-text input-group-addon">
														<span class="fa fa-calendar"></span>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group col-md-11 col-lg-10">
									<div class="row align-items-center">
										<label for="overdue" class="col-sm-8 control-label text-sm-right">Overdue</label>
										<div class="col-sm-12 col-md-14">
											<select class="form-control" id="overdue" name="overdue">
												<option value="0" <%= TextUtil.isSelected(0, sc.getOverDueLimit()) %>>1 - 29 days</option>
												<option value="30" <%= TextUtil.isSelected(30, sc.getOverDueLimit()) %>>30 - 59 days</option>
												<option value="60" <%= TextUtil.isSelected(60, sc.getOverDueLimit()) %>>60 - 89 days</option>
												<option value="90" <%= TextUtil.isSelected(90, sc.getOverDueLimit()) %>>90 days or more</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-13 offset-lg-2 col-lg-10">
									<div class="row align-items-center">
										<label for="balanceAmt" class="col-sm-8 col-md-9 control-label text-sm-right">Balance (Case Minimum)</label>
										<div class="col-sm-12 col-md-13">
											<input type="text" class="form-control" id="balanceAmt" name="balanceAmt" value="<%= TextUtil.print(numberFormat.format(sc.getBalance())).replaceAll(",", "") %>"maxlength="10">
										</div>
									</div>
								</div>
								<div class="form-group col-md-11 col-lg-10">
									<div class="row align-items-center">
										<label for="caseType" class="col-sm-8 control-label text-sm-right">Case Type</label>
										<div class="col-sm-12 col-md-14">
											<select class="form-control" id="caseType" name="caseType">
												<option value="all">All Case Types</option>
												<% if (caseTypeList != null && caseTypeList.size() > 0) {
													it = caseTypeList.iterator();
													while (it.hasNext()) {
														ctvo = (CaseTypeBO) it.next(); %>
														<option value="<%= ctvo.getCaseType() %>" <%= TextUtil.isSelected(ctvo.getCaseType(), sc.getCaseType()) %>><%= ctvo.getDescr() %></option>
													<% }
												} %>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-sm-20 col-md-23 col-lg-21 clearfix">
									<button type="button" class="btn btn-secondary ml-2 float-right" id="clearAccountBtn" onclick="clearBtnClicked('account');">Clear</button>
									<button type="button" class="btn btn-primary ml-2 float-right" id="findAccountsBtn" onclick="findAccountsBtnClicked();">Find By Account</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="card-header card-footer font-weight-bold">
				Search Results
			</div>
			<div class="card-body">
				<div id="loadingDiv" style="display: none; text-align: center;">
					<div class="d-inline-block">
						<div id="loadingMessage">Loading...</div>
					</div>
				</div>
				<table id="caseTable" class="table compact row-border hover w-100" style="width:100%">
					<thead>
						<tr class="bg-dark text-light">
							<th >Case</th>
							<th width="20%">Name</th>
							<th class="text-right">Balance</th>
							<th class="text-center">Due Date</th>
							<th class="text-center">Last Payment</th>
							<th class="text-center">Sentenced</th>
							<th >OSDC Status</th>
							<th >Local Debt Coll. Status</th>
						</tr>
					</thead>
					<tbody id="resultBody">
						<% if (results != null && results.size() > 0) {
							it = results.iterator();
							boolean showAsNotLocal;
							int idx = 0;
							while (it.hasNext()) {
								dto = new DebtCollectionCaseDTO();
								dto = (DebtCollectionCaseDTO) it.next();
								idx++;
								showAsNotLocal = false;
								if (!"Y".equalsIgnoreCase(dto.getLocalDebtColl())) showAsNotLocal = true; %>
								<tr>
									<td ><a href="#" data-target="#detailModal" onclick="openDetail('<%= sc.getCourtType() %>', <%= dto.getIntCaseNum() %>, <%= request.getSession(false).getAttribute("USER_ID") %>, '<%= dto.getCaseNum() %>', '<%= dto.getDebtCollStatusDesc() %>');"><%= dto.getCaseNum() %></a></td>
									<td nowrap><%= TextUtil.getNameSortedLastFirstMiddle(dto.getFirstName(), dto.getMiddleName(), dto.getLastName(), dto.getNameSuffix()) %></td>
									<td class="text-right"><%= moneyFormat.format(dto.getBalance()).replace("$", "") %></td>
									<td class="text-center"><%= TextUtil.printDate(dto.getDueDate()) %></td>
									<td class="text-center"><%= TextUtil.printDate(dto.getLastPaidDate()) %></td>
									<td class="text-center"><%= TextUtil.printDate(dto.getSentenceDate()) %></td>
									<td  class="<%= dto.getIntCaseNum() %>" id="<%= dto.getIntCaseNum() %>row<%= idx %>col7"><%= TextUtil.print(dto.getDebtCollStatusDesc()) %></td>
									<% if (showAsNotLocal) {
										if ("No".equalsIgnoreCase(dto.getDebtCollStatusDesc())) { %>
											<td  class="<%= dto.getIntCaseNum() %>" id="<%= dto.getIntCaseNum() %>row<%= idx %>col8"><span style="display: none"><%= dto.getLocalDebtColl() %></span><img src='/CorisWEB/images/delete.png' onclick="setLocalDebtFlagFromLookup('N', '<%= sc.getCourtType() %>', '<%= dto.getIntCaseNum() %>', '<%= dto.getDebtCollStatusDesc() %>');" border='0' title="Set to Local Status"></td>
										<% } else { %>
											<td  class="<%= dto.getIntCaseNum() %>" id="<%= dto.getIntCaseNum() %>row<%= idx %>col8"><span style="display: none"><%= dto.getLocalDebtColl() %></span><img src='/CorisWEB/images/deleteGreyed.png' onclick="setLocalDebtFlagFromLookup('N', '<%= sc.getCourtType() %>', '<%= dto.getIntCaseNum() %>', '<%= dto.getDebtCollStatusDesc() %>');" border='0' title="Set to Local Status"></td>
										<% }
									} else { %>
										<td  class="<%= dto.getIntCaseNum() %>" id="<%= dto.getIntCaseNum() %>row<%= idx %>col8"><span style="display: none"><%= dto.getLocalDebtColl() %></span><img src='/CorisWEB/images/green-check-icon.gif' onclick="setLocalDebtFlagFromLookup('Y', '<%= sc.getCourtType() %>', '<%= dto.getIntCaseNum() %>', '<%= dto.getDebtCollStatusDesc() %>');" border='0' title="Undo Local Status"></td>
									<% } %>
								</tr>
							<% }
						} %>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<!-- modals -->
	<div id="detailModal" class="modal fade">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h6 class="modal-title">Debt Collection Account Details</h6>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<input type="hidden" name="searchMode" id="searchMode" value="<%= searchMode %>">
				</div>
				<div class="modal-body">
					<div id="loadModalBody"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script>
var input = document.getElementById("caseNumber");
input.addEventListener("keyup", function(event) { // Execute function when user releases a key
	event.preventDefault(); // Cancel the default action, if needed
	if (event.keyCode === 13) { // 13 is the "Enter" key on the keyboard
		document.getElementById("findByCaseBtn").click();
	}
});
var input = document.getElementById("lastName");
// Execute function when user releases a key
input.addEventListener("keyup", function(event) {
	event.preventDefault(); // Cancel the default action, if needed
	if (event.keyCode === 13) { // 13 is the "Enter" key on the keyboard
		document.getElementById("findByCaseBtn").click();
	}
});
var input = document.getElementById("firstName");
input.addEventListener("keyup", function(event) { // Execute function when user releases a key
	event.preventDefault(); // Cancel the default action, if needed
	if (event.keyCode === 13) { // 13 is the "Enter" key on the keyboard
		document.getElementById("findByCaseBtn").click();
	}
});
</script>

<script>

$(document).ready(function() {
   
  <% if (!TextUtil.isEmpty(resultsSize) && Integer.parseInt(resultsSize) == Constants.MAX_RESULTS) { %> 
  	appUX.pop.declare("Maximum Results Reached", "Your search results may have been limited.", 300, "auto", appUX.pop.styles.DANGER);
  <% } %>						
							
  $.fn.dataTable.moment( 'MM-DD-YYYY' );
  
  var todayDate = new Date().toLocaleDateString(); 

  var table= $('#caseTable').DataTable( {
  
  
   /*   "paging":   false,
        "ordering": false,
        "info":     false,
	  "searching":     false,*/
	 
	  "scrollX": true,
	  "pageLength": 25,
	   scrollY:"500px",
	   scrollCollapse:true,
	   processing: true,
	   dom: 'Bfrtip',
       buttons: [
       		{
          		 extend: 'pdf',
          		 text: 'Print',
          		 orientation: 'landscape',
          		 className: 'btn btn-primary ml-2 float-right', 
          		 title: 'OSDC Eligible Accounts',
          		 
          		 customize: function (doc) {
          		 	doc.pageMargins = [30, 10, 45, 20];
			      	doc.defaultStyle.fontSize = 10;
			      	doc.styles.tableHeader.fontSize = 12;
			      	
    				doc.styles.tableHeader.alignment = 'center';
    				doc.styles.tableBodyEven.alignment = 'center';
    				doc.styles.tableBodyOdd.alignment = 'center';
			      	doc.styles.title.fontSize = 14;
			      	doc.footer = function(page, pages) {
			        	return {
			          		margin: [5, 0, 10, 0],
			          		height: 30,
			          		columns: [{
			            		alignment: "left",
			            		text: todayDate
			          		}, {
			             		alignment: "right",
			             		text: page.toString() + " of " + pages
			          		}]
			        	}
			      	}; 
			      	 
				    doc.content[1].table.widths = [70, 200, '*', '*', '*', '*', '*', '*'];
				    
				    var rowCount = doc.content[1].table.body.length;
				    for (i = 1; i < rowCount; i++) {
						doc.content[1].table.body[i][1].alignment = 'left';
						doc.content[1].table.body[i][2].alignment = 'right';
						
						if (!doc.content[1].table.body[i][7].text)
							doc.content[1].table.body[i][7].text = '';
					}
				      
				 }
            }
       ],
		 
 
  columnDefs: [
    {   "orderable": false,
         "targets": [ 7 ]
            
     },
     { "type": "date", "targets": [3,4,5] }
     
     
  ]
} );

table.columns().iterator( 'column', function (ctx, idx) {
    $( table.column(idx).header() ).append('<span class="sort-icon"/>');
  } );
  

});

function clearTableData()
{
	$('#caseTable').DataTable().clear().draw();
 	
}
</script>
</html>
