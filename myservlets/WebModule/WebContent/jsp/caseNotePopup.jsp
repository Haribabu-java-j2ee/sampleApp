<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="java.util.Date"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	String caseNum = TextUtil.getParamAsString(request, "caseNum");
	String locnCode = TextUtil.getParamAsString(request, "locnCode");
	String caseNote = TextUtil.getParamAsString(request, "caseNote");
	String calledFrom = TextUtil.getParamAsString(request, "calledFrom");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Case History Note</title>
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
</head>
<body>
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div id="myModal" class="input-group m-4">
					<label for="caseNote" style="width: 15%; text-align: right; padding-right: 3px;">Case&nbsp;Note <span class="text-danger">*</span></label>
					<textarea class="form-control" rows="6" id="caseNote" name="caseNote" maxlength="70" autofocus><%= TextUtil.print(caseNote) %></textarea>
					<div class="invalid-feedback">Note is required and must be 70 or less letters or spaces.</div>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml-2" title="Update" onclick="updateForm($('#caseNote').val());">Update</button>
					<button type="button" class="btn btn-primary btn-sm float-right" title="Delete" onclick="remove();">Delete</button>
					<% if (!"caseHistoryNotePopin".equals(calledFrom)) { %>
						<button type="button" class="btn btn-primary btn-sm float-left ml-2" title="Case History Note" onclick="addCaseHistoryNote()">Add Case History Note</button>
					<% } %>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
	mode = null;
%>
	<script type="text/javascript" src="/CorisWEB/js/coris.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script type="text/javascript" src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
	
		$(document).ready(function(){
			<% // make sure the form focus is the first form field %>
			$("#caseNote").focus();
		});
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function remove() {
			var corn = appUX.pop.confirm("Warning","Are you sure that you want to delete this case note?", "Yes", "No", 300, 'auto', appUX.pop.styles.INFO,
			function(result){
				if (result) {
					appUX.ajax.call("/CorisWEB/CaseNoteServlet", 
						function (err, data, xhr) { 
							closePop();
						}, 
						'POST', 
						'mode=delete&caseNum=<%= caseNum %>&courtType=<%= courtType %>&locnCode=<%= locnCode %>'
					); 
				}
				corn.close();
			});
		}
		
		function updateForm(caseNote) {
			var caseNoteLength = caseNote.length; 
				
			caseNote = encodeURIComponent(replaceExtendedAsciiChars(caseNote.trim()));
			
			// do some validation of caseNote
			$('#caseNote').removeClass("is-invalid");
			$('#caseNote').addClass("is-valid");
			
			var validatedText = true;
			//if (caseNote == "") { validatedText = false; } // can't be an empty string
			//if (caseNote == null) { validatedText = false; } // must contain a value
			if (caseNoteLength > 70) { validatedText = false; } // must be 70 chars long or shorter
			// if caseNote.search(/[^a-zA-Z ]+/) != -1) { validatedText = false; } // must be only chars/spaces
			if (validatedText) {
				appUX.ajax.call("/CorisWEB/CaseNoteServlet", 
					function (err, data, xhr) { 
						closePop();
					}, 
					'POST', 
					'mode=update&caseNum=<%= caseNum %>&courtType=<%= courtType %>&locnCode=<%= locnCode %>&caseNote='+caseNote
				); 
			} else {
				// highlight the field in red and display message
				$('#caseNote').removeClass("is-valid");
				$('#caseNote').addClass("is-invalid");
			}
			
			parent.refresh();
		}
		
		function addCaseHistoryNote() {
			var cornId = "modalCaseHistoryNoteId";
			var title = "Add Case History Note";
			var url = "/CorisWEB/CaseHistServlet?"
				+ "caseNum=<%= caseNum %>" 
				+ "&locnCode=<%= locnCode %>"
				+ "&courtType=<%= courtType %>"  
				+ "&chText="
				+ "&mode=add"
				+ "&target=_blank"
				+ "&calledFrom=caseNotePopin";
				
			var width = 500;
			var height = 300;
			var style = appUX.pop.styles.PRIMARY;
			appUX.pop.modal(cornId, title, url, width, height, style);
		}
	</script>
</body>
</html>
