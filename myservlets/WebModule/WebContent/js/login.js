/*
 * JavaScript source file for login.jsp
 */

if (self !== top) {
	top.location.href = self.location.href;
}

function browserInfo(){var b,a=navigator.userAgent,c=a.match(/(opera|chrome|safari|firefox|msie|trident|edge(?=\/))\/?\s*(\d+)/i)||[],d=/Mobi/i.test(a);return/trident/i.test(c[1])?(b=/\brv[ :]+(\d+)/g.exec(a)||[],{name:'MSIE',version:b[1]||''}):'Chrome'===c[1]&&(b=a.match(/\b(OPR|Edge)\/(\d+)/i),null!=b)?{name:'OPR'===b[1]?'Opera':b[1],version:b[2]}:(c=c[2]?[c[1],c[2]]:[navigator.appName,navigator.appVersion,'-?'],null!=(b=a.match(/version\/(\d+)/i))&&c.splice(1,1,b[1]),{name:c[0],version:c[1],mobile:d});}

/**
 * On-ready function
 */
$(document).ready(function() {
	
	var noSupportText = '';
	var browser = browserInfo();
	if (browser.name.toLowerCase() !== 'chrome' && browser.name.toLowerCase() !== 'firefox' && browser.name.toLowerCase() !== 'safari' && browser.name.toLowerCase() !== 'edge' && !(browser.name.toLowerCase() === 'msie' && browser.version > 9)) {
		noSupportText = 'Your browser is not supported. Please use Chrome, Firefox, Safari or Edge browser.';
	}
	
	if (noSupportText !== '') {
		$('#noSupportId').html(noSupportText);
		$('#noSupportId').show();
	} else {
		// hide no support container
		$('#noSupportContainerId').hide();
		
		// setup check for caps lock
		setupCapsLockCheck();
		
		// default focus
		$("#username").focus();
	}
});

/**
 * Setup check for caps lock
 */
function setupCapsLockCheck() {
	$('#password').keypress(function(e) {
		kc = e.keyCode ? e.keyCode : e.which;
		sk = e.shiftKey ? e.shiftKey : ((kc == 16) ? true : false);
		if (((kc >= 65 && kc <= 90) && !sk) || ((kc >= 97 && kc <= 122) && sk)) {
			$('#capsLockId').removeClass('invisible');
			$('#capsLockId').addClass('visible');
		} else {
			$('#capsLockId').removeClass('visible');
			$('#capsLockId').addClass('invisible');
		}
	});
}

/**
 * Submit login form
 */
function submitLoginForm(logname) {
	appUX.ajax.call('/CorisWEB/PasswordServlet?action=checkExpiration&logname=' + logname, 
   		function (err, data, xhr) { 
   			var jsonObj = JSON.parse(data);
			if (jsonObj && jsonObj.error) {
				var corn = appUX.pop.confirm("Password Expired", jsonObj.error, "Yes", "No", 470, 'auto', appUX.pop.styles.INFO,
				function(result) {
					if (result) {
						appUX.ajax.call('/CorisWEB/PasswordServlet?action=graceLoginsLeft&logname=' + logname, function (err, data, xhr) { });
						
						$('#loginForm').submit();
					} 
					corn.close();
				});
			} else if (jsonObj && jsonObj.success) {
				$('#loginForm').submit();
			}
   		}
   	);
}