/**
 * 
 */
var servletUrl='/CorisWEB/CorisReportDomModiPendingServlet';

$(document).ready(function() {			
			$('[id^=datetimepicker]').datetimepicker({
				format: 'MM/DD/YYYY',
				icons: {
					previous: "fa fa-angle-left",
					next: "fa fa-angle-right"
				}
			});			

			$('#reportResultsDiv').load('jsp/corisReportDomModiPendingResults.jsp');
			$('#loadingContainer').hide();
			
		});

function search(locnCode, courtType){
	
	if(validateReportDate()){
		$('body').addClass('waiting');
		$('#loadingContainer').show();
		var dataTable = $('#searchResultsTable').DataTable();
		var order = dataTable.order();
		var url = servletUrl + '?mode=find&' + $('#searchForm').serialize() 
				+ '&locnCode='+ locnCode + '&courtType=' + courtType + '&orderBy=' + JSON.stringify(order);
		$('#reportResultsDiv').load(url,function(responseTxt, statusTxt, xhr){
				$('body').removeClass('waiting');
				$('#loadingContainer').hide();
				if(statusTxt=="success"){
					try{
						var jsonObj = JSON.parse(responseTxt);
						if(jsonObj && jsonObj.errorMessage){
							appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
						}
					}catch(e){
						console.log('response is not a json object.');
					}
				}
				});
	}else {
		appUX.pop.alert('Valid report date range (<=31 days) is required.', 400, 'auto', appUX.pop.styles.DANGER);
	}
	
}

function saveReport(fmt, locnCode, courtType){
	
	if(validateReportDate()){
		var dataTable = $('#searchResultsTable').DataTable();
		var order = dataTable.order();
		window.open(servletUrl + "?mode=save&locnCode="+locnCode + "&courtType=" + courtType + "&format=" + fmt 
					+ "&" + $('#searchForm').serialize() + "&orderBy=" + JSON.stringify(order));
	} else {
		appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
		$('#startDate').focus();
	}
	
}

function emailReport(fmt,locnCode,courtType){
	$('body').addClass('waiting');
	
	if(validateReportDate()){
		var dataTable = $('#searchResultsTable').DataTable();
		var order = dataTable.order();
		var url = servletUrl + '?mode=email&locnCode=' + locnCode + '&courtType=' + courtType + '&format=' + fmt
					+ "&orderBy=" + JSON.stringify(order);
		appUX.ajax.call(url, 
			function (err, data, xhr) {
				$('body').removeClass('waiting');
				var jsonObj = JSON.parse(data);
				if(jsonObj && jsonObj.errorMessage){
					appUX.pop.alert(jsonObj.errorMessage, 400, 'auto', appUX.pop.styles.DANGER);
				}else {
					appUX.pop.declare("Success", "Report Emailed successfully", 400, 'auto', appUX.pop.styles.SUCCESS);
				}
			}, 
			'POST', 
			'&' + $('#searchForm').serialize()
		);
	} else {
		appUX.pop.alert("Valid report date range (<=31 days) is required.", 400, 'auto', appUX.pop.styles.DANGER);
		$('#startDate').focus();
	}
}

function clearForm(){
	$('#startDate').val('');
	$('#endDate').val('');
}

function validateReportDate(){
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($('#endDate').val());
	var startDateValid = startDate != "Invalid Date" && !isNaN(startDate) && startDate.getTime() <= new Date().getTime();
	var endDateValid = endDate != "Invalid Date" && !isNaN(endDate);
	if(endDateValid && startDateValid && (endDate.getTime() - startDate.getTime())/86400000 > 31 || startDate.getTime() > endDate.getTime() ){
		endDateValid = false;
	}
	
	return startDateValid && endDateValid;
	
}