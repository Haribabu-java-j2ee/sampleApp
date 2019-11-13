
$(document).ready(function(){
	
	$('#barNum').focus();

	loadLocation(pageVars.courtType, false);
	loadJudge(pageVars.courtType);
	$("#searchBtn").prop('disabled', false);

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
			{ "orderable": false, "targets": pageVars.colspan1 - 3 },
			{ "orderable": false, "targets": pageVars.colspan1 - 4 }
		]
    });
	$('#resultsTable tbody tr').click(function(){
	    $(this).addClass('bg-light').siblings().removeClass('bg-light');
	});

	if (pageVars.appearanceListSize == pageVars.maxResults) {
		message = 'Number of results exceeds '+pageVars.maxResults+'. Please use the search form to filter results further.';
		appUX.pop.declare("Warning", message, 300, 'auto', appUX.pop.styles.WARNING);			
	}

	$("svg").on("mouseenter", function(){ //for an <i> tag which is changed to an <svg> tag by font awesome when the page is rendered
		$(this).css("cursor", "pointer" );
	});
	$("svg").on("mouseleave", function(){
		$(this).css("cursor", "default" );
	});

	if ($('#barNum').val() != "" || $('#judgeId').val() != "") {
		$('#btnEmail').show();
		$('#btnSave').show();
	} else {
		$('#btnEmail').hide();
		$('#btnSave').hide();
	}

	$("#barNum").on("keyup", function(e) {
		if($("#barNum").val() != undefined && $("#barNum").val() != "" && $("#barNum").val() != null){
			e = e || window.event;
			if ((e.keyCode ? e.keyCode : e.which) == 13) { //13 = Enter key
				validateSearchForm(event);
				return false;
			}
		}
	});
	
	$("#courtType").on("change", function(event){
		loadLocation($(this).val(), true);
		loadJudge($(this).val());
	});
	
	$("#locnCode").on("change", function(event){
		loadJudge($('#courtType').val());
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
	$('#barNum').val("");
	$('#barNum').focus();
	$('#barState').val("UT");
	loadLocation(pageVars.courtType, true);
	loadJudge(pageVars.courtType);
	$('#startDate').val("");
	$('#endDate').val("");
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
	$('#barNum').val($.trim($('#barNum').val()));
	if ($('#endDate').val() == "" || $('#startDate').val() == "") {
		isValid = false;
		message += "Start/End Date is required.<br>";
	}
	if ($('#endDate').val() != "" && $('#endDate').val() < $('#startDate').val()) {
		isValid = false;
		message += "End date must be greater than or equal to start date.<br>";
	}
	var endYear = new Date($('#endDate').val()).getFullYear(); 
	var startYear = new Date($('#startDate').val()).getFullYear();
	if (endYear - startYear > 1) {
		isValid = false;
		message += "Start/End Date range must be a year or less.<br>";
	}	
	if ($('#barNum').val() == "" && $('#judgeId').val() == "") {
		isValid = false;
		message += "Judge or Attorney Bar Number/State or both are required.<br>";
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
		$("#searchForm").submit();
	} else {
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
	}
}

function saveReport(format) {
	if ($('#barNum').val() != null && $('#barNum').val() != undefined && $('#caseNum').val() != "") {
		var table = $('#resultsTable').DataTable();
		var orderBy = table.order();
		$('#orderBy').val(JSON.stringify(orderBy));
		$('#format').val(format);
		var url = pageVars.urlSaveReport+"&" + $('#searchForm').serialize();
		window.open(url);
	} else {
		var message = "Case Number is required.";
		appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
	}
}

function emailReport(format) {
	var title = "Attorney Appearances";
	var message = 'Would you like to receive an email with Attorney Appearances report?';
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

function attorneyDetails(barNum, barState) {
	var cornId = "attorneyDetails";
	var title = "Attorney Details";
	var url = pageVars.urlAttorneyDetails+"&barNum=" + barNum + "&barState=" + barState + "&parentCornId=" + parentCornId;
	var width = 800;
	var height = 525;
	var style = appUX.pop.styles.LIGHT;
	appUX.pop.modal(cornId, title, url, width, height, style);
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

