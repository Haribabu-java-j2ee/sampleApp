/**
 * Javascript source file for admionTableCompareProcess.jsp
 */
function validate(){
	var thisForm = document.forms[0];
	appUX.pop.declare("Background Processing", "Table Compare Background Processing. This may take some time, you may close the window.", 300, 'auto', appUX.pop.styles.INFO);
	thisForm.submit();
}

