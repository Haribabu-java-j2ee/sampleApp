
$(document).ready(function(){
	
	$('#barNum').focus();

    $('#searchResultsTable').DataTable({
        "retrieve": true,
        "stateSave": true,
		"oLanguage": {
			"sSearch": "Filter results:"
		},		        
        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		"columnDefs": [
			{ "orderable": false, "targets": pageVars.colspan2 - 1 }
		]
    });
	$('#searchResultsTable tbody tr').click(function(){
	    $(this).addClass('bg-light').siblings().removeClass('bg-light');
	});

	if (pageVars.attorneyListSize == pageVars.maxResults) {
		message = 'Number of results exceeds '+pageVars.maxResults+'. Please use the search form to filter results further.';
		appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);			
	}

	$("svg").on("mouseenter", function(){ //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
		$(this).css("cursor", "pointer" );
	});
	$("svg").on("mouseleave", function(){
		$(this).css("cursor", "default" );
	});

	if ($('#barNum').val() > 0 || $('#attyLastName').val() != "") {
		$('#btnEmail').show();
		$('#btnSave').show();
	} else {
		$('#btnEmail').hide();
		$('#btnSave').hide();
	}
	
	var count = 0;
	$('#searchResultsTable tr').each(function(){
		barNum = $(this).find('td:nth-child(3)').html();
		if(!isNaN(barNum)){
			count++;
		}
	});
	if (count == 1 && $('#showDetails').val() == "true") { //display the details popup, if there is only one result, unless refreshing the page
		$('#attyDetailsBtn').click();
	}

	$("#barNum, #attyLastName, #attyFirstName").on("keyup", function(e) {
		if($("#barNum").val() > 0 || $("#attyLastName").val() != "" || $("#attyFirstName").val() != ""){
			e = e || window.event;
			if ((e.keyCode ? e.keyCode : e.which) == 13) { //13 = Enter key
				validateSearchForm(event);
				return false;
			}
		}
	});
	
	$("#searchBtn").on("click", function(event){
		validateSearchForm(event);
	});
	
	$("#clearBtn").on("click", function(event){
		clearSearchForm();
	});
	
	$("#closeBtn").on("click", function(event){
		closePop();
	});
	
	$("#emailExcelBtn").on("click", function(event){
		emailReport("xlsx");
	});
	
	$("#emailPDFBtn").on("click", function(event){
		emailReport("pdf");
	});

	$("#savePDFBtn").on("click", function(event){
		saveReport("pdf");
	});
	
	$("#saveExcelBtn").on("click", function(event){
		saveReport("xlsx");
	});
	
	$("#addAttyBtn").on("click", function(event){
		attorneyAdd("");
	});
	
});

var parentCornId = appUX.pop.getSelfHandle().id;
var parentCorn = appUX.pop.getCornHandle(parentCornId);

function closePop() {
	var corn = appUX.pop.getSelfHandle();
	corn.close();
}

function clearSearchForm() {
	$('#barNum').val("");
	$('#attyLastName').val("");
	$('#attyFirstName').val("");
	$('#searchResultsTable').DataTable().clear().draw();
	$('#btnEmail').hide();
	$('#btnSave').hide();
	$('#barNum').focus();
}

function validateSearchForm(e) {
	e.preventDefault();
	var table = $('#searchResultsTable').DataTable();
	var orderBy = table.order();
	$('#orderBy').val(JSON.stringify(orderBy));
	var isValid = true;
	var message = "";
	$('#barNum').val($.trim($('#barNum').val()));
	$('#attyLastName').val($.trim($('#attyLastName').val()));
	$('#attyFirstName').val($.trim($('#attyFirstName').val()));
	if (($('#barNum').val() == "" || $('#barState').val() == "") && $('#attyLastName').val() == "") {
		isValid = false;
		message += "Bar Number/State or Attorney Last Name is required for search.";
	}
	$('#barNum').focus();
	if (isValid) {
		$('#showDetails').val("true"); //this is a hack to make sure a new details popup is displayed
		$("#searchAttorneysForm").submit();
	} else {
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
	}
}

function saveReport(format) {
	if (($('#barNum').val() != "" && $('#barState').val() != "") || $('#attyLastName').val() != "") {
		var table = $('#searchResultsTable').DataTable();
		var orderBy = table.order();
		$('#orderBy').val(JSON.stringify(orderBy));
		$('#format').val(format);
		var url = pageVars.urlSaveReport+"&" + $('#searchAttorneysForm').serialize();
		window.open(url);
	} else {
		var message = "Bar Number/State or Attorney Last Name is required.";
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
	}
}

function emailReport(format) {
	var title = "Attorneys for Case Report";
	var message = 'Would you like to receive an email with Attorneys for Case report?';
	var trueText = "Yes, Email";
	var falseText = "Cancel";
	var width = 300;
	var height = "auto";
	var style = appUX.pop.styles.PRIMARY;
	var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
	function confirmCallback(action) {
		corn.close();
		if (action) {
			var table = $('#searchResultsTable').DataTable();
			var orderBy = table.order();
			$('#orderBy').val(JSON.stringify(orderBy));
			$('#format').val(format);
			var data = $('#searchAttorneysForm').serialize();
			appUX.ajax.call(pageVars.urlEmailReport, 
				function (err, data, xhr) {
					if (err) {
						message = "Error: "+xhr.responseText;
						appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
					}
					var jsonObj = JSON.parse(data); 
					if (jsonObj.error) {
						message = "There was an error and email could not be sent.";
						appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
					} else {
						message = "Email is in the queue to be sent with the next process.";
						appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
					}
				}, 
				'POST', 
				'&'+data
			);
		}
	}
}

function attorneyDetails(barNum, barState) {
	barNum = $.trim(barNum);
	var cornId = "attorneyDetails";
	var title = "Attorney Details";
	var url = pageVars.urlAttorneyDetails+"&barNum=" + barNum + "&barState=" + barState + "&parentCornId=" + parentCornId;
	var width = 800;
	var height = 525;
	var style = appUX.pop.styles.LIGHT;
	appUX.pop.modal(cornId, title, url, width, height, style);
}

function attorneyCases(firstName, lastName, barNum, barState){
	barNum = $.trim(barNum);
	firstName = $.trim(firstName);
	lastName = $.trim(lastName);
	var cornId = "attorneyCases";
	var title = "Cases for Attorney";
	var url = pageVars.urlAttorneyCases+"&firstName=" + firstName +"&lastName=" + lastName + "&barNum=" + barNum + "&barState=" + barState;
	var width = 1365;
	var height = 750;
	var style = appUX.pop.styles.LIGHT;
	appUX.pop.modal(cornId, title, url, width, height, style);
}

function attorneyAdd(){
	var cornId = "attorneyAdd";
	var title = "Attorney Add";
	var url = pageVars.urlAttorneyAdd+"&parentCornId=" + parentCornId;
	var width = 800;
	var height = 525;
	var style = appUX.pop.styles.LIGHT;
	appUX.pop.modal(cornId, title, url, width, height, style);
}

function refreshPage(paramObj){
	$('#barNum').val(paramObj.barNum);
	$('#barState').val(paramObj.barState);
	$('#showDetails').val(paramObj.showDetails); //this is a hack to prevent a new details popup which then causes the alerts to disappear
	$("#searchAttorneysForm").submit();
}
