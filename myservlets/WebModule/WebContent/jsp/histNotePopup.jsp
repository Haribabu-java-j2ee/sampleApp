<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="java.util.Date"%>
<%
	String target = URLEncryption.getParamAsString(request, "target");
	String mode = URLEncryption.getParamAsString(request, "mode");
	String courtType = URLEncryption.getParamAsString(request, "courtType");
	String caseNum = URLEncryption.getParamAsString(request, "caseNum");
	String locnCode = URLEncryption.getParamAsString(request, "locnCode");
	String eventDatetime = URLEncryption.getParamAsString(request, "eventDatetime");
	String createDatetime = URLEncryption.getParamAsString(request, "createDatetime");
	String errorMessage = URLEncryption.getParamAsString(request, Constants.ERROR_MESSAGE);
	String chText = URLEncryption.getParamAsString(request, "histNote");
	String calledFrom = TextUtil.getParamAsString(request, "calledFrom");
		
	Date displayDate = eventDatetime != null ? Constants.dateFormatDatetime.parse(eventDatetime) : new Date();
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Case History Note</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/bootstrap-datetimepicker.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<div class="container-fluid" style="text-align:center" id="errorMessage"></div>
	
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div style="width:100%; margin:0.5rem !important">
					<div class="row align-items-center">
						<div class='col input-group date' id='datetimepicker1'>
							<label for="newEventDate" style="width: 25%; text-align: right; padding-right: 3px;">Date <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="newEventDate" name="newEventDate" value="<%= TextUtil.printDate(displayDate) %>">
							<span class="input-group-text input-group-addon">
								<span class="fa fa-calendar"></span>
							</span>
						</div>
						<div class='col input-group date' id='datetimepicker2'>
							<label for="newEventTime" style="width: 25%; text-align: right; padding-right: 3px;">Time <span class="text-danger">*</span></label>
							<input type="text" class="form-control" id="newEventTime" name="newEventTime" value="<%= TextUtil.printTime(displayDate) %>">
							<span class="input-group-text input-group-addon">
								<span class="fa fa-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="input-group m-4">
					<label for="chText" style="width: 25%; text-align: right; padding-right: 3px;">Case&nbsp;History&nbsp;Note <span class="text-danger">*</span></label>
					<textarea class="form-control" rows="8" id="chText" name="chText" placeholder="Case History Note cannot be blank" maxlength="255" autofocus><%= chText %></textarea>
					<div class="invalid-feedback">Note is required and must be 255 or less letters or spaces.</div>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="closePop();">Cancel</button>
					<% if ("edit".equals(mode)) { %>
						<button type="button" class="btn btn-primary btn-sm float-right ml-2" title="Update" onclick="updateForm($('#chText').val(), $('#newEventDate').val(), $('#newEventTime').val());">Update</button>
				    	<button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove();">Delete</button>
				    <% } else { %>
				    	<button type="button" class="btn btn-primary btn-sm float-right ml-2" title="Save" onclick="updateForm($('#chText').val(), $('#newEventDate').val(), $('#newEventTime').val());">Save</button>
				    <% } %>
				    <% if (!"caseNotePopin".equals(calledFrom)) { %>
				    	<button type="button" class="btn btn-primary btn-sm float-left ml-2" title="Edit Case Note" onclick="showCaseNote()">Edit Case Note</button>
				    <% } %>
				</div>
			</form>
		</div>
	</main>
	
	<script type="text/javascript" src="/CorisWEB/js/coris.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.dataTables.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/datetime-moment.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/bootstrap-datetimepicker.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
			<% // make sure the form focus is the first form field %>
			$("#chText").focus();
			
			$('#datetimepicker1').datetimepicker({ 
				format: 'MM/DD/YYYY'
			});
			
			$('#datetimepicker2').datetimepicker({ 
				format: 'LT'
			});
			
			<% if (!TextUtil.isEmpty(errorMessage)) { %> 
				$('#errorMessage').html('<span style="color:red;"><%= errorMessage %></span>');
			<% } %>	
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function remove() {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this case history note?", "Yes", "No", 340, 'auto', appUX.pop.styles.WARNING, deleteNoteCallback);
			function deleteNoteCallback(action) {
			    if (action) {
					appUX.ajax.call("/CorisWEB/CaseHistServlet", 
						function (err, data, xhr) { 
							appUX.pop.refreshCornFrame('viewCaseSummary');
							closePop();
						}, 
						'POST', 
						'target=<%= target %>&mode=delete&caseNum=<%=caseNum%>&courtType=<%=courtType%>&locnCode=<%=locnCode%>&eventDatetime=<%=eventDatetime%>&createDatetime=<%=createDatetime%>'
					); 
			    }
			    corn.close();
			}
		}
		
		function updateForm(chText, newEventDate, newEventTime) {
			var chTextLength = chText.length;
				
			chText = encodeURIComponent(replaceExtendedAsciiChars(chText.trim()));
			
			// do some validation of chText
			$('#chText').removeClass("is-invalid");
			$('#chText').addClass("is-valid");
			
			var validatedText = true;
			if (chText == "") { validatedText = false; } // can't be an empty string
			if (chText == null) { validatedText = false; } // must contain a value
			if (chTextLength > 255) { validatedText = false; } // must be 255 chars long or shorter
			// if chText.search(/[^a-zA-Z ]+/) != -1) { validatedText = false; } // must be only chars/spaces
			if (validatedText) {
				appUX.ajax.call("/CorisWEB/CaseHistServlet", 
					function (err, data, xhr) {
						if (err) {
							// appUX.pop.declare("Case History Note", xhr.responseText, 300, 'auto', appUX.pop.styles.DANGER);  // -- show as popup message
							$('#errorMessage').html('<span style="color:red;">'+ xhr.responseText.replace('Error 500: javax.servlet.ServletException: ', '') + '</span>');
						} else {
							appUX.pop.refreshCornFrame('viewCaseSummary');
							closePop();
						}
					},
					'POST', 
					'target=<%= target %>&mode=' + ('edit' == '<%= mode %>' ? "update" : "insert") + '&caseNum=<%= caseNum %>&courtType=<%= courtType %>&locnCode=<%= locnCode %>&chText=' + chText + '&eventDatetime=<%= eventDatetime %>&createDatetime=<%= createDatetime %>&newEventDate=' + newEventDate + '&newEventTime=' + newEventTime 
				); 
			} else {
				// highlight the field in red and display message
				$('#chText').removeClass("is-valid");
				$('#chText').addClass("is-invalid");
			}
		}
		
		function showCaseNote() {
			var cornId = "caseNotePrimary";
			var title = "Edit Case Note";
			var url = "/CorisWEB/CaseNoteServlet?"
				+ "caseNum=<%= caseNum %>" 
				+ "&locnCode=<%= locnCode %>"
				+ "&courtType=<%= courtType %>"  
				+ "&mode=edit"
				+ "&calledFrom=caseHistoryNotePopin";
			
			var width = 500;
			var height = 200;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
	</script>
</body>
</html>
