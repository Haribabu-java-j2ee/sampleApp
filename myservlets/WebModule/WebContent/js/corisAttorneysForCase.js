
$(document).ready(function(){
	
	$('#caseNum').focus();

	loadLocation(pageVars.courtType, false);

	$('[id^=datetimepicker]').datetimepicker({
		format: 'MM/DD/YYYY',			
		icons: {
            previous: "fa fa-angle-left",
            next: "fa fa-angle-right"
        }
	});

    $('#resultsTable').DataTable({
        "retrieve": true,
        "stateSave": true,
		"oLanguage": {
			"sSearch": "Filter results:"
		},		        
        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		"columnDefs": [
			{ "orderable": false, "targets": pageVars.colspan1 - 1 },
			{ "type": "date", "targets": 6 },
			{ "type": "date", "targets": 8 }
		]
    });
	$('#resultsTable tbody tr').click(function(){
	    $(this).addClass('bg-light').siblings().removeClass('bg-light');
	});

	if (pageVars.attyListSize == pageVars.maxResults) {
		message = 'Number of results exceeds '+pageVars.maxResults+'. Please use the search form to filter results further.';
		appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);			
	}

	$("svg").on("mouseenter", function(){ //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
		$(this).css("cursor", "pointer" );
	});
	$("svg").on("mouseleave", function(){
		$(this).css("cursor", "default" );
	});

	if ($('#caseNum').val() != "") {
		$('#btnEmail').show();
		$('#btnSave').show();
	} else {
		$('#btnEmail').hide();
		$('#btnSave').hide();
	}

	$("#caseNum").on("keyup", function(e) {
		if($("#caseNum").val() != undefined && $("#caseNum").val() != "" && $("#caseNum").val() != null){
			if($("#caseNum").val().length == 9){
				e = e || window.event;
				if ((e.keyCode ? e.keyCode : e.which) == 13) { //13 = Enter key
					validateSearchForm(event);
					return false;
				}
			}
		}
	});
	
	$("#courtType").on("change", function(event){
		loadLocation($(this).val(), true);
	});
	
	$("#searchBtn").on("click", function(event){
		validateSearchForm(event);
	});
	
	$("#clearBtn").on("click", function(event){
		clearSearchForm();
	});
	
	$("#attachBtn").on("click", function(event){
		launchAttachAttorney();
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
	
});

var parentCornId = appUX.pop.getSelfHandle().id;
var parentCorn = appUX.pop.getCornHandle(parentCornId);

function closePop() {
	var corn = appUX.pop.getSelfHandle();
	corn.close();
}

function launchAttachAttorney() {
	var cornId = "attorneyAttachSingleCase";
	var title = "AttachAttorney";
	var url = "/CorisWEB/CorisAttorneyAttachDetachSingleServlet?caseNum="+$('#caseNum').val();
	var width = 1400;
	var height = 600;
	var style = appUX.pop.styles.PRIMARY;
	appUX.pop.modeless(cornId, title, url, width, height, style);
}

function clearSearchForm() {
	$('#caseNum').val("");
	$('#caseNum').focus();
	loadLocation(pageVars.courtType, true);
	$('#attachDatetimeStart').val("");
	$('#attachDatetimeEnd').val("");
	$('#checkboxIncludeDetached').prop( "checked", false );
	$('#resultsTable').DataTable().clear().draw();
	$('#btnEmail').hide();
	$('#btnSave').hide();
}

function validateSearchForm(e) {
	e.preventDefault();
	var table = $('#resultsTable').DataTable();
	var orderBy = table.order();
	$('#orderBy').val(JSON.stringify(orderBy));
	var isValid = true;
	var message = "";
	$('#caseNum').val($.trim($('#caseNum').val()));
	if ($('#attachDatetimeEnd').val() != "" && $('#attachDatetimeEnd').val() < $('#attachDatetimeStart').val()) {
		isValid = false;
		message += "End date must be greater than or equal to start date.<br>";
	}
	if ($('#caseNum').val() == "") {
		isValid = false;
		message += "Case Number is required.<br>";
	}
	if ($('#locnCode').val() == "") {
		isValid = false;
		message += "Location is required.<br>";
	}
	if ($('#courtType').val() == "") {
		isValid = false;
		message += "Court Type is required.<br>";
	}
	if (isValid) {
		$("#searchBtn").prop('disabled', true); //so they don't keep clicking the button, if it's taking a long time
		$('#resultsTable').DataTable().clear().draw();
		$('#resultsTable tbody').html('<tr><td class="text-center" colspan="'+pageVars.colspan1+'">Loading...</td></tr>');
		$("#searchAttyForm").submit();
	} else {
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
	}
}

function editDatetime(datetime, counter, type) {
	$('#display'+type+'Datetime'+counter).hide();
	$('#edit'+type+'Datetime'+counter).show();
	$('#atty'+type+'Datetime'+counter).val(datetime);
	$('#atty'+type+'Datetime'+counter).focus();
}

function cancelDatetimeEdit(counter, type) {
	$('#display'+type+'Datetime'+counter).show();
	$('#edit'+type+'Datetime'+counter).hide();
}

function updateDatetime(e, attyPartyId, datetime, counter, type) {
	e.preventDefault();
	var isValid = true;
	if (datetime == null || datetime == "" || datetime == "undefined") {
		isValid = false;
		appUX.pop.alert("Date cannot be blank.", 400, 'auto', appUX.pop.styles.DANGER);
	}
	if (isValid) {
		$('#edit'+type+'Datetime'+counter).hide();
		var table = $('#resultsTable').DataTable();
		var orderBy = table.order();
		$('#orderBy').val(JSON.stringify(orderBy));
		var data = $('#searchAttyForm').serialize();
		if (type == "Attach") {
			var url = pageVars.urlUpdateAttachDatetime;
			data += '&attachDatetime='+datetime;
		} else if (type == "Detach") {
			var url = pageVars.urlUpdateDetachDatetime;
			data += '&detachDatetime='+datetime;
		}
		appUX.ajax.call(url, 
			function (err, data, xhr) {
				if (err) {
					message = "Error: "+xhr.responseText;
					appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				}
				var jsonObj = JSON.parse(data);
				if (jsonObj.error) {
					message = jsonObj.errorMessage;
					appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
				} else if (!jsonObj.error) {
					$("#searchAttyForm").submit();
					message = "Changes have been saved.";
					appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
				}
			}, 
			'POST', 
			'&attyPartyId='+attyPartyId+'&'+data
		);
	}
}

//this is to display the Case History All
function displayCaseHistory(encryptedUrl){
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
	var pdf = window.open("", "PDF", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
	if(pdf) {
		pdf.document.write(htmlDisplay); //display loading spinner
		pdf.location.href = encryptedUrl; //replace the window with the contents of the PDF
	} else {
		var message = "To view this PDF, please allow popups in the browser.";
		appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
	}
}

function saveReport(format) {
	if ($('#caseNum').val() != null && $('#caseNum').val() != undefined && $('#caseNum').val() != "") {
		var table = $('#resultsTable').DataTable();
		var orderBy = table.order();
		$('#orderBy').val(JSON.stringify(orderBy));
		$('#format').val(format);
		var url = pageVars.urlSaveReport+"&" + $('#searchAttyForm').serialize();
		window.open(url);
	} else {
		var message = "Case Number is required.";
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
			var table = $('#resultsTable').DataTable();
			var orderBy = table.order();
			$('#orderBy').val(JSON.stringify(orderBy));
			$('#format').val(format);
			var data = $('#searchAttyForm').serialize();
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
	var cornId = "attorneyDetails";
	var title = "Attorney Details";
	var url = pageVars.urlAttorneyDetails+"&barNum=" + barNum + "&barState=" + barState + "&parentCornId=" + parentCornId;
	var width = 800;
	var height = 525;
	var style = appUX.pop.styles.LIGHT;
	appUX.pop.modal(cornId, title, url, width, height, style);
}

function attorneyDetach(attyPartyId) {
	var title = "Detach Attorney";
	var message = "Are you sure?";
	var trueText = "Detach";
	var falseText = "Cancel";
	var width = 300;
	var height = "auto";
	var style = appUX.pop.styles.DANGER;
	var corn = appUX.pop.confirm(title, message, trueText, falseText, width, height, style, confirmCallback);
	
	function confirmCallback(action) {
		corn.close();
		if (action) {
			var table = $('#resultsTable').DataTable();
			var orderBy = table.order();
			$('#orderBy').val(JSON.stringify(orderBy));
			var data = $('#searchAttyForm').serialize();
			appUX.ajax.call(pageVars.urlAttorneyDetach, 
				function (err, data, xhr) { 
					if (err) {
						message = "Error: "+xhr.responseText;
						appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
					}
					var jsonObj = JSON.parse(data);
					if (jsonObj.error) {
						message = jsonObj.errorMessage;
						appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
					} else if (!jsonObj.error) {
						$("#searchAttyForm").submit();
						message = "Changes have been saved.";
						appUX.pop.declare("Success", message, 300, 'auto', appUX.pop.styles.SUCCESS);
					}
				}, 
				'POST', 
				'&attyPartyId='+attyPartyId+'&'+data
			);
		}
	}
}

function loadLocation(courtType, isCleared) {
	if (courtType == "D") {
		$('#locnCode').html($('#locationDistrictOptions').html());
	} else if (courtType == "J") {
		$('#locnCode').html($('#locationJusticeOptions').html());
	}
	if (!isCleared && courtType == pageVars.courtType) {
		$('#locnCode').val(pageVars.locnCode);
	}
}

