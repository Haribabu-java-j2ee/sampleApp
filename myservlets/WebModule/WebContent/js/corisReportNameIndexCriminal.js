
$(document).ready(function(){
	
	$('#reportDateStart').focus();
	
	loadLocation(pageVars.courtType, false);
	loadCaseType($('#reportCategory').val());

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
			{ "orderable": false, "targets": 6 },
			{ "type": "date", "targets": 7 },
			{ "type": "date", "targets": 9 }
		]
    });
	$('#resultsTable tbody tr').click(function(){
	    $(this).addClass('bg-light').siblings().removeClass('bg-light');
	});

	if ($('#reportDateStart').val() != "" && $('#reportDateEnd').val() != "") {
		$('#btnEmail').show();
		$('#btnSave').show();
	} else {
		$('#btnEmail').hide();
		$('#btnSave').hide();
	}

	$("#reportDateStart").on("keyup", function(e) {
		if($("#reportDateStart").val() != ""){
			if($("#reportDateStart").val().length >= 8){
				$("#reportDateEnd").focus(); //hack to keep the date from disappearing
				$("#reportDateStart").focus();
				e = e || window.event;
				if ((e.keyCode ? e.keyCode : e.which) == 13) { //13 = Enter key
					validateSearchForm(event);
					return false;
				}
			}
		}
	});
	
	$("#reportDateEnd").on("keyup", function(e) {
		if($("#reportDateEnd").val() != ""){
			if($("#reportDateEnd").val().length >= 8){
				$("#reportDateStart").focus(); //hack to keep the date from disappearing
				$("#reportDateEnd").focus();
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
	
	$("#reportCategory").on("change", function(event){
		loadCaseType($(this).val());
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
	
});

var parentCornId = appUX.pop.getSelfHandle().id;
var parentCorn = appUX.pop.getCornHandle(parentCornId);

function closePop() {
	var corn = appUX.pop.getSelfHandle();
	corn.close();
}

function clearSearchForm() {
	$('#caseNum').val("");
	$('#caseNum').focus();
	loadLocation(pageVars.courtType, true);
	$('#typeOfDate').val("");
	$('#caseType').val("");
	$('#reportCategory').val("");
	loadCaseType($('#reportCategory').val());
	$('#reportDateStart').val("");
	$('#reportDateEnd').val("");
	$('#totalsTable').html("");
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
	var startDate = new Date($('#reportDateStart').val()); 
    var endDate = new Date($('#reportDateEnd').val()); 
	// difference of two dates - returns milliseconds
	var differenceInMil = endDate.getTime() - startDate.getTime(); 
	// number of days between two dates - converts milliseconds to days
	var differenceInDays = differenceInMil / (1000 * 3600 * 24); 	
	if (differenceInDays >= 7) {
		isValid = false;
		message += "Report Start/End Date range must be a week or less.<br>";
	}
	if ($('#reportDateEnd').val() != "" && $('#reportDateEnd').val() < $('#reportDateStart').val()) {
		isValid = false;
		message += "Report end date must be greater than or equal to start date.<br>";
	}
	if ($('#reportDateStart').val() == "") {
		isValid = false;
		message += "Report Date Start is required.<br>";
	}
	if ($('#reportDateEnd').val() == "") {
		isValid = false;
		message += "Report Date End is required.<br>";
	}
	if ($('#typeOfDate').val() == "") {
		isValid = false;
		message += "Type of Date is required.<br>";
	}
	if ($('#reportCategory').val() == "") {
		isValid = false;
		message += "Report Category is required.<br>";
	}
	if ($('#locnCode').val() == "") {
		isValid = false;
		message += "Location is required.<br>";
	}
	if ($('#courtType').val() == "") {
		isValid = false;
		message += "Court Type is required.<br>";
	}
	if ($('#caseType').val() == "") {
		isValid = false;
		message += "Case Type is required.<br>";
	}
	if (isValid) {
		$("#searchBtn").prop('disabled', true); //so they don't keep clicking the button, if it's taking a long time
		$('#totalsTable').html("");
		$('#resultsTable').DataTable().clear().draw();
		$('#resultsTable tbody').html('<tr><td class="text-center" colspan="'+pageVars.colspan1+'">Loading...</td></tr>');
		$("#searchForm").submit();
	} else {
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
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
	var table = $('#resultsTable').DataTable();
	var orderBy = table.order();
	$('#orderBy').val(JSON.stringify(orderBy));
	$('#format').val(format);
	var url = pageVars.urlSaveReport+"&" + $('#searchForm').serialize();
	window.open(url);
}

function emailReport(format) {
	var title = "Name Index - Criminal";
	var message = 'Would you like to receive an email with Name Index - Criminal report?';
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
			var data = $('#searchForm').serialize();
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

function loadCaseType(reportCategory) {
	if (reportCategory == "F") {
		$('#caseType').html($('#caseTypeTrafficOptions').html());
	} else if (reportCategory == "C") {
		$('#caseType').html($('#caseTypeCriminalOptions').html());
	} else if (reportCategory == "B") {
		$('#caseType').html($('#caseTypeBothOptions').html());
	}
}

