<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
	String mode = TextUtil.getParamAsString(request, "mode");
	String courtType = TextUtil.getParamAsString(request, "courtType");
	int pageId = TextUtil.getParamAsInt(request, "pageId");
	
	PagesystemXrefBO pageSystemXrefBO = (PagesystemXrefBO) request.getAttribute("pageSystem");
	
	String areaDescription = (String) request.getAttribute("areaDescription");
	String pageDescription = (pageId > 0) ? new PageDefnBO(courtType).where(PageDefnBO.PAGEID, pageId).find().getDescription() : "";
	List<SystemareaDefnBO> availableSystems = (List<SystemareaDefnBO>) request.getAttribute("availableSystems");
	List<PagesystemXrefBO> availablePageSystemXrefs = (List<PagesystemXrefBO>) request.getAttribute("availablePageSystemXrefs");
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Define Page Areas</title>
	
	<!-- styles -->
	<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	
	<!-- scripts -->
	<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	
	<!-- this is used for the popcorn window -->
	<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<script>

	$(document).ready(function(){
		// make sure the form focus is the first form field 
		$("#areaId").focus();
	});

	function closePop(){
		var corn = appUX.pop.getSelfHandle();
		corn.close();
	}
	
	function refresh() {
		parent.showPageSystems('Page Areas - <%= pageDescription %>', '<%= courtType %>', <%= pageId %>);
	}
		
	function updateForm(areaId, parentPageSysemXrefId, displayOrder) {
		appUX.ajax.call('/CorisWEB/AdminPageSystemServlet?mode=update&courtType=<%= courtType %>&pageSystemXrefId=<%= pageSystemXrefBO != null ? pageSystemXrefBO.getPagesystemxrefid() : 0 %>&pageId=<%= pageId %>&areaId=' + areaId + '&parentPageSysemXrefId=' + parentPageSysemXrefId + '&displayOrder=' + displayOrder, function (err, data, xhr) { refresh(); closePop(); });
	}
	
</script>

</head>
<body>
	
	<!-- main content -->
	<main>
		<div class="container-fluid">
			<form class="form-inline" action="#" method="post">
				<div class="input-group m-4">
					<label for="isAvailable" style="width: 20%; text-align: right; padding-right: 3px;">Area <span class="text-danger">*</span></label>
					<% if ("add".equals(mode)) { %> 
						<select class="form-control" id="areaId" name="areaId">
							<% 
								int selectedSystem = pageSystemXrefBO != null ? pageSystemXrefBO.getAreaid() : 0;
								if (availableSystems != null && availableSystems.size() > 0) { 
									for (SystemareaDefnBO system : availableSystems) {
							%>
										<option value="<%= system.getAreaid() %>" <%= selectedSystem == system.getAreaid() ? "selected" : ""%>><%= system.getDescription() %></option>
							<% 
									} 
								}
					%>
						</select>
					<% } else { %>
						<b><%= areaDescription %></b>
					<% } %>
				</div>
				<div class="input-group m-4">
					<label for="parentPageSysemXrefId" style="width: 20%; text-align: right; padding-right: 3px;">Parent Menu&nbsp;&nbsp;</label>
					<select class="form-control" id="parentPageSysemXrefId" name="parentPageSysemXrefId">
						<option value="0"><no parent menu></option>
						<%
							int selectedXref = pageSystemXrefBO != null ? pageSystemXrefBO.getParentpagesystemxrefid() : 0;
							if (availablePageSystemXrefs != null && availablePageSystemXrefs.size() > 0) { 
								for (PagesystemXrefBO xref : availablePageSystemXrefs) {
						%>
									<option value="<%= xref.getPagesystemxrefid() %>" <%= selectedXref == xref.getPagesystemxrefid() ? "selected" : ""%>><%= xref.get(PageDefnBO.PAGEID) %> - <%= xref.get(SystemareaDefnBO.DESCRIPTION) %> - <%= xref.get(PageDefnBO.DESCRIPTION) %></option>
						<% 			
								}
							}
						%>
					</select>
				</div>
				<div class="input-group m-4">
					<label for="displayOrder" style="width: 20%; text-align: right; padding-right: 3px;">Display Order <span class="text-danger">*</span></label>
					<input class="form-control" id="displayOrder" name="displayOrder" autofocus value="<%= pageSystemXrefBO != null ? TextUtil.print(pageSystemXrefBO.getDisplayorder()) : "" %>" \>
				</div>
				<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
					<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
					<button type="button" class="btn btn-primary btn-sm float-right ml" title="Update" onclick="updateForm($('#areaId').val(), $('#parentPageSysemXrefId').val(), $('#displayOrder').val());">Update</button>
				</div>
			</form>
		</div>
	</main>
<%
	// cleanup
	mode = null;
%>
</body>
</html>
