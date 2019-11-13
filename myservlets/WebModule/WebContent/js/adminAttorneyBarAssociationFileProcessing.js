/**
 * Javascript source file for EmailJuryTermFile.jsp
 */
function validate(){
	var error;
	var thisForm = document.forms[0];
	error = "";
	if (thisForm.fileNameId.value == "" || thisForm.fileNameId.value == null){
		document.getElementById("fileNameId").focus();
		error += "* Please select a file for import\n";
	}
	
	if (error === ""){
		var fileUpload = document.getElementById("fileNameId");
	    if (typeof (fileUpload.files) != "undefined") {
	        var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2);
	        if (size >= 2048 ){
	            error += "File is to big, (" + size + " KB), Limit is (2048.00 KB).  Please spilt in two seperate files!";
	        }
	        
	        var fileName = fileUpload.files[0].name;
	        var extension = fileName.substr((fileName.lastIndexOf('.') + 1));
	        if (extension != "txt" && extension != "xls" && extension != "xlsx"){
	            error += "File extension of txt, xls, or xlsx are only allowed!";
	        }
	    } else {
	        error += "This browser does not support HTML5.";
	    }
	}    
	if (error != ""){
		appUX.pop.declare("Error", error, 300, 'auto', appUX.pop.styles.DANGER);
		return false;
	} else {
		appUX.pop.declare("Background Processing", "Utah Bar Association File Processing.  This may take some time, you may close the window.", 300, 'auto', appUX.pop.styles.INFO);
		thisForm.submit();
	}
}

