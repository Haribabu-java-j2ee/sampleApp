<!DOCTYPE html>
<%@include file="/fragments/NoCache.jspf"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="java.util.List"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.application.ApplicationBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roledefn.RoleDefnBO"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.roleapplicxref.RoleapplicXrefBO"%>
<%
	String courtType = (String) request.getAttribute("courtType");

	List<RoleapplicXrefBO> xrefList = (List<RoleapplicXrefBO>) request.getAttribute("xrefList");
	List<RoleDefnBO> roleDefnList = (List<RoleDefnBO>) request.getAttribute("roleDefnList");
	List<ApplicationBO> applicationList = (List<ApplicationBO>) request.getAttribute("applicationList");
%>
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<link rel="stylesheet" href="/CorisWEB/css/jquery.tablesorter.css?nocache=<%= Constants.SYSTEM_VERSION %>">
<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/app-ajax.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
<script src="/CorisWEB/js/jquery.tablesorter.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>

<table id="tableResults" name="tableResults" class="table table-hover table-activate">
	<thead class="bg-dark text-white">
		<tr>
			<th>ID</th>
			<th>Role</th>
			<th class="sorter-false">Applications</th>
			<th class="sorter-false"><span style="white-space: nowrap;" class="text-white"><i onclick="toggleRow(null, 'show')" title="Expand All" style="cursor: pointer;" class="far fa-plus-square fa-lg fa-fw"></i><i onclick="toggleRow(null, 'hide')" title="Collapse All" style="cursor: pointer;" class="far fa-minus-square fa-lg fa-fw"></i></span></th>
		</tr>
	</thead>
	<tbody>
	<%
		boolean codesResults = false;
		for (RoleDefnBO roleDefnBO : roleDefnList) {
	%>
			<tr>
				<td><a href="javascript:editRole(<%= roleDefnBO.getRoleId() %>)"><%= roleDefnBO.getRoleId() %></a></td>
				<td><%= roleDefnBO.getDescription() %></td>
				<td>
					<div id="div<%= roleDefnBO.getRoleId() %>" class="initialState">
					<table class="table table-borderless">
						<tbody>
	<%
							for (ApplicationBO applicationBO : applicationList) {
								for (RoleapplicXrefBO xrefBO : xrefList) {
									if (xrefBO.getApplicId() == applicationBO.getApplicId() && xrefBO.getRoleId() == roleDefnBO.getRoleId()) {
	%>
										<tr>
											<td style="margin: 0px; padding: 0px;">
												<%= applicationBO.getApplicName() %> (<%= applicationBO.getApplicId() %>)
											</td>
										</tr>
	<%
										codesResults = true;
									}
								}
							}
	%>
						</tbody>
					</table>
					</div>
				</td>
				<td>
					<% if (codesResults) { %>
						<span class="expandRow" style="cursor: pointer; display: none;" id="expand<%= roleDefnBO.getRoleId() %>" title="Expand" onclick="toggleRow('<%= roleDefnBO.getRoleId() %>', 'show');"><i class="text-primary far fa-plus-square fa-lg fa-fw"></i></span>
						<span class="collapseRow" style="cursor: pointer;" id="collapse<%= roleDefnBO.getRoleId() %>" title="Collapse" onclick="toggleRow('<%= roleDefnBO.getRoleId() %>', 'hide');"><i class="text-primary far fa-minus-square fa-lg fa-fw"></i></span>
					<% } else { %>
						<span><i class="text-white far fa-square fa-lg fa-fw"></i></span>
					<% }
					   codesResults = false;
					%>
					<i onclick="editRecordPopup('<%= roleDefnBO.getRoleId() %>')" title="Edit" style="cursor: pointer;" class="text-warning far fa-edit fa-lg fa-fw"></i>
					<i onclick="deleteRole('<%= roleDefnBO.getRoleId() %>', '<%= roleDefnBO.getDescription() %>')" title="Delete" style="cursor: pointer;" class="text-warning far fa-trash-alt fa-lg fa-fw"></i>
				</td>
			</tr>
	<%
		}
		
		// cleanup
		xrefList = null;
		roleDefnList = null;
		applicationList = null;
	%>
	</tbody>
</table>
		
<script>

	$(document).ready(function() {
		$("#tableResults").tablesorter();
	});
	
	function editRecordPopup(roleId) {
		var cornId = "modalCornPrimary";
		var title = "Edit";
		var url = "/CorisWEB/AdminRoleServlet?mode=edit&courtType=<%= courtType %>&roleId=" + roleId;
		var width = 600;
		var height = 450;
		var style = appUX.pop.styles.PRIMARY;
		appUX.pop.modal(cornId, title, url, width, height, style);
	}

	function editRole(roleId) {
		parent.modifyRole('update', 'Edit Role', roleId, '<%= courtType %>');
	}
		
	function deleteRole(roleId, description) {
		var corn = appUX.pop.confirm("Warning", "Are you sure that you want to delete this role (" + description + ")?", "Yes", "No", 450, 'auto', appUX.pop.styles.INFO,
		function(result){
			if (result) {
				appUX.ajax.call('/CorisWEB/AdminRoleServlet?mode=delete&roleId=' + roleId + "&courtType=<%= courtType %>", function (err, data, xhr) { refresh(); });
			}
			corn.close();
		});
	}
	
	function toggleRow(row, toggle) {
		if (row == null && toggle == 'show') {
			$('.initialState').show();
			$('.expandRow').hide();
			$('.collapseRow').show();
		} else if (row == null && toggle == 'hide') {
			$('.initialState').hide();
			$('.expandRow').show();
			$('.collapseRow').hide();
		} else {
			if (toggle == 'show') {
				$('#div' + row).show();
				$('#expand' + row).hide();
				$('#collapse' + row).show();
			} else if (toggle == 'hide') {
				$('#div' + row).hide();
				$('#expand' + row).show();
				$('#collapse' + row).hide();
			}
		}
	}
</script>
