<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>

<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.application.ApplicationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagedefn.PageDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.systemareadefn.SystemareaDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.pagesystemxref.PagesystemXrefBO"%>
<%@page import="gov.utcourts.coriscommon.util.SyncHelper"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
	List<String> dbServers = (List<String>) request.getAttribute("dbServers");
	
	String fromServer = (String) request.getAttribute("fromServer");
	String toServer = (String) request.getAttribute("toServer");
	
	List<PageDefnBO> fromPageList = (List<PageDefnBO>) request.getAttribute("fromPageDefnList");
	List<PageDefnBO> toPageList = (List<PageDefnBO>) request.getAttribute("toPageDefnList");
	HashMap<Integer, String> parentMenus = (HashMap<Integer, String>) request.getAttribute("parentMenus");
	
	String errorMessage = (String) request.getAttribute(Constants.ERROR_MESSAGE);
%>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Admin - Publish Pages</title>
	
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

	<script src="/CorisWEB/js/admin.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

	<script>
		<% if (!TextUtil.isEmpty(errorMessage)) { %>
			appUX.pop.declare("Error", '<%= errorMessage %>', 300, 'auto', appUX.pop.styles.DANGER);
		<% } %>

		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
		
		function compare() {
			var fromServer = $('#fromServer').val();
			var toServer = $('#toServer').val();
			appUX.ajax.call("/CorisWEB/AdminPublishPagesServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data);
				}, 
				'POST', 
				'mode=compare&fromServer=' + fromServer + '&toServer=' + toServer
			); 
		}
		
		function publish(pageId) {
			appUX.ajax.call("/CorisWEB/AdminPublishPagesServlet", 
				function (err, data, xhr) { 
					$("#displayResults").html(data);
				}, 
				'POST', 
				'mode=publish&fromServer=<%= fromServer %>&toServer=<%= toServer %>&pageId=' + pageId
			); 
		}
		 
		function deletePage(pageId, description) {
			var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this page (" + description + ") from <%= toServer %>?", "Yes", "No", 550, 'auto', appUX.pop.styles.INFO,
				function(result){
					if (result) {
						appUX.ajax.call("/CorisWEB/AdminPublishPagesServlet", 
							function (err, data, xhr) { 
								$("#displayResults").html(data); 
							},
							'POST',
							'mode=delete&fromServer=<%= fromServer %>&toServer=<%= toServer %>&pageId=' + pageId
						);
					}
					corn.close();
				}
			);
		}
	</script>

</head>
<body>
	<div id="displayResults" class="container-fluid">
		<div class="card m-2">
			<div>
				<table class="table table-borderless" style="width: 100%;" id="tblRoleCompare">
					<thead><tr><th style="width: 46%;">From Server</th><th style="width: 2%;"></th><th style="width: 46%;">To Server</th><th style="width: 6%;"></th></tr></thead><tbody>
					<tr>
						<td>
							<div class="form-group">
								<select class="form-control" id="fromServer">
									<% for (String server : dbServers) { %>
										<option <%= server.equals(fromServer) ? "selected" : "" %> value="<%= server %>"><%= server %></option>
									<% } %>
								</select>
							</div>
						</td>
						<td></td>
						<td>
							<div class="form-group">
								<select class="form-control" id="toServer">
									<% for (String server : dbServers) { %>
										<option <%= server.equals(toServer) ? "selected" : "" %> value="<%= server %>"><%= server %></option>
									<% } %>
								</select>
							</div>
						</td>
						<td>
							<button type="button" class="btn btn-primary btn-sm" title="Compare" onclick="javascript:compare();">Compare</button>
						</td>
					</tr>
					<tr>
						<td>
							<table>
						<%
							if (fromPageList != null)
								for (int i = 0; i < fromPageList.size(); i++) {
									PageDefnBO fromPageBO = fromPageList.get(i);
						%>
									<tr><td>
										<table>
											<tr>
												<td width="325px" nowrap>
													<% 
														String parentMenu = TextUtil.print(parentMenus.get((Integer) fromPageBO.get(PagesystemXrefBO.PARENTPAGESYSTEMXREFID)));
														parentMenu = TextUtil.isEmpty(parentMenu) ? "" : " - " + parentMenu;
													%>
													<%= fromPageBO.getDescription() %> (<%= fromPageBO.getPageid() + " " + fromPageBO.getIsavailable() %>) <%= parentMenu %>  
												</td>
												<td align="right" height="24">
													<button type="button" class="btn btn-primary btn-sm" title="Publish" onclick="javascript:publish(<%= fromPageBO.getPageid() %>);">></button>
												</td>
											</tr>
										</table>
									</td></tr>
						<%= 		SyncHelper.insertPageBlankRows(i, toPageList, fromPageList) %>
						<%
								}
						%>
							</table>
						</td>
						<td></td>
						<td>
							<table>
						<%
							if (toPageList != null)
								for (int j = 0; j < toPageList.size(); j++) {
									PageDefnBO toPageBO = toPageList.get(j);
						%>
						<%= 		SyncHelper.insertPageBlankRows(j, fromPageList, toPageList) %>
									<tr><td>
										<table>
											<tr>
												<td width="325px" nowrap>
													<% 
														String parentMenu = TextUtil.print(parentMenus.get((Integer) toPageBO.get(PagesystemXrefBO.PARENTPAGESYSTEMXREFID)));
														parentMenu = TextUtil.isEmpty(parentMenu) ? "" : " - " + parentMenu;
													%>
													<%= toPageBO.getDescription() %> (<%= toPageBO.getPageid() + " " + toPageBO.getIsavailable() %>) <%= parentMenu %> <i onclick="deletePage('<%= toPageBO.getPageid() %>', '<%= toPageBO.getDescription() %>')" title="Delete" style="cursor: pointer;" class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
												</td>
												<td align="right" height="24"></td>
											</tr>
										</table>
									</td></tr>
						<%
								}
						%>
							</table>
						</td>
						<td></td>
					</tr>
				</tbody></table>
				<br><br><br>
			</div>
			<div class="std-fixed bottom bg-light p-2 popcorn-ftr active">
				<button type="button" class="btn btn-secondary btn-sm float-right ml-2" title="Cancel" onclick="javascript:closePop();">Cancel</button>
			</div>
		</div>
	</div>
</body>
</html>
