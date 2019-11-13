<%@page import="gov.utcourts.coriscommon.util.TextUtil"%>
<%@page import="gov.utcourts.coriscommon.enumeration.PageMode"%>
<%@page import="gov.utcourts.corisweb.session.SessionVariables"%>
<%@page import="gov.utcourts.coriscommon.dataaccess.attorney.AttorneyBO"%>
<%@page import="gov.utcourts.coriscommon.constants.Constants"%>
<%@page import="gov.utcourts.corisweb.util.URLEncryption"%>
<%
	String errorMessage = (String) request.getAttribute("errorMessage");
	int barNum = URLEncryption.getParamAsInt(request,"barNum");
	String barState = URLEncryption.getParamAsString(request, "barState");
	String courtType = URLEncryption.getParamAsString(request, "courtType");
	String labelTemplate = URLEncryption.getParamAsString(request, "labelTemplate");
	int xIndex = labelTemplate.indexOf("x");
	int rows = Integer.parseInt(labelTemplate.substring(xIndex+1));
	int cols = Integer.parseInt(labelTemplate.substring(0,xIndex));
	AttorneyBO attorney = new AttorneyBO(courtType).where(AttorneyBO.BARNUM,barNum).where(AttorneyBO.BARSTATE,barState).find();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Print Attorney Labels</title>
<link rel="stylesheet" href="/CorisWEB/css/app-toolkit.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
<link rel="stylesheet" href="/CorisWEB/css/app-popcorn.min.css?nocache=<%=Constants.SYSTEM_VERSION%>">
</head>
<body>
	<div class="container-fluid">
		<% if (!TextUtil.isEmpty(TextUtil.print(errorMessage))) { %>
		<div class="alert alert-danger text-center m-2"><%=TextUtil.print(errorMessage) %></div>
		<% } %>
		<div class="card m-2">
			<div class="card-header">
				<strong>Print Label for <%=attorney.getFirstName() + " " + attorney.getLastName()%>
				</strong>
			</div>
			<div class="card-body">
				<form id="form" name="form" action="#" method="post">
					Please click and drag "Start" to desired beginning label and "End" to desired last label.
					<button type='button' title='Start' id='start1' draggable='true' ondragstart='drag(event)' class='btn btn-success mr-1 ml-1 mb-1 mt-1' style='cursor: grab;'>Start</button><button type='button' title='End' id='end1' draggable='true' ondragstart='drag(event)' class='btn btn-danger mr-1 ml-1 mt-1 mb-1' style='cursor: grab;'>End</button>
					<table style="width: 100%;">
						<tbody>
							<% for(int i=0; i <= rows; i++){
								%>
								<tr>
								<% for(int j=0; j <= cols; j++){ %>
									<td id="<%=i+"-"+j %>" <%=(i!=0 && j!=0)?"ondrop='drop(event)' ondragover='allowDrop(event)'":"" %> style="width: <%=(j==0)?"10":"30"%>%; height: <%=300/rows%>px; <%=(i>0 && j>0)?"border: 1px solid #cccccc;":""%> text-align: center;">
									<%=(i==0 && j!=0)?j:"" %> <%=(j==0 && i!=0)?i:"" %>
									</td>
								<% } %>
								</tr>
							<% } %>
						</tbody>
					</table>
					<div class="std-fixed bottom bg-light popcorn-ftr active">
						<div class='mb-1 col-24'>
							<button type="button" class="btn btn-secondary ml-2 mt-2 mr-2 float-right" id="cancelBtn" onclick="closePop();">Cancel</button>
							<button type="button" class="btn btn-primary ml-2 mt-2 mr-2 float-right" id="saveBtn" onclick="printLabel()">Print</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- footer -->
    <footer></footer>

    <!-- scripts -->
	<script src="/CorisWEB/js/jquery.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/fontawesome.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-toolkit.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-ajax.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/app-popcorn.min.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script src="/CorisWEB/js/text.util.js?nocache=<%= Constants.SYSTEM_VERSION %>"></script>
	<script>
		var startId = "";
		var endId = "";
		function allowDrop(e) {
			e.preventDefault();
		}
		
		function drag(e) {
			e.dataTransfer.setData("text", e.target.id);
		}
		
		function drop(e) {
			e.preventDefault();
			var data = e.dataTransfer.getData("text");
			if(data == "start1"){
				startId = e.target.id;
				if(startId == "end1"){
					startId = endId;
				}
			}else if(data == "end1"){
				endId = e.target.id;
				if(endId == "start1"){
					endId = startId;
				}
			}
			var tempStartId = parseInt(startId.replace(/[^0-9]/g, ''));
			var tempEndId = parseInt(endId.replace(/[^0-9]/g, ''));
			if(tempEndId == tempStartId){ //make sure Start shows up first, if both are in the same table cell
				if(e.target.id == "start1"){ //place within Start button's table cell, not Start button itself
					var x = document.getElementById("start1").parentElement;
					x.appendChild(document.getElementById("start1"));
					x.appendChild(document.getElementById("end1"));
				}else if(e.target.id == "end1"){ //place within End button's table cell, not End button itself
					var x = document.getElementById("end1").parentElement;
					x.appendChild(document.getElementById("start1"));
					x.appendChild(document.getElementById("end1"));
				}else{ //if they managed to drop a button into the white space of a table cell, then we're good
					e.target.appendChild(document.getElementById("start1"));
					e.target.appendChild(document.getElementById("end1"));
				}
			}else{
				e.target.appendChild(document.getElementById(data));
 			}
		}
			
		function printLabel(){
 			var startCleaned = parseInt(startId.replace(/[^0-9]/g, '')); // remove everything but digits and convert to number
 			var endCleaned = parseInt(endId.replace(/[^0-9]/g, '')); // remove everything but digits and convert to number
			var arrStart = startId.split("-");
			var arrEnd = endId.split("-");
			var startRow = arrStart[0];
			var startCol = arrStart[1];
			var endRow = arrEnd[0];
			var endCol = arrEnd[1];
			var labelNum = ((endRow - startRow) * <%=cols%>) + (endCol - startCol) + 1;
			if(endId == "" || startId == ""){
				message = "Start and End must be set.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}else if(endCleaned < startCleaned){
				message = "Start must be before End.";
				appUX.pop.alert(message, 400, 'auto', appUX.pop.styles.DANGER);
			}else{
				window.open("/CorisWEB/CorisAttorneyDetailsServlet?mode=print&labelTemplate=<%=labelTemplate%>" +
								"&barNum=<%=attorney.getBarNum()%>&barState=<%=attorney.getBarState()%>" +
								"&startRow=" + startRow + "&startCol=" + startCol +
								"&labelNum=" + labelNum, "Attorney Labels", 'width=800, height=950');
				closePop();
			}
		}
		
		function closePop(){
			var corn = appUX.pop.getSelfHandle();
			corn.close();
		}
	</script>
</body>
</html>