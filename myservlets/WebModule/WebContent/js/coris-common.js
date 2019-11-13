/*
 * Common JavaScript source file for use throughout application
 */

/** Retrieve browser info */
function browserInfo(){var b,a=navigator.userAgent,c=a.match(/(opera|chrome|safari|firefox|msie|trident|edge(?=\/))\/?\s*(\d+)/i)||[],d=/Mobi/i.test(a);return/trident/i.test(c[1])?(b=/\brv[ :]+(\d+)/g.exec(a)||[],{name:'MSIE',version:b[1]||''}):'Chrome'===c[1]&&(b=a.match(/\b(OPR|Edge)\/(\d+)/i),null!=b)?{name:'OPR'===b[1]?'Opera':b[1],version:b[2]}:(c=c[2]?[c[1],c[2]]:[navigator.appName,navigator.appVersion,'-?'],null!=(b=a.match(/version\/(\d+)/i))&&c.splice(1,1,b[1]),{name:c[0],version:c[1],mobile:d});}

/** Check support for touch events */
function supportsTouchEvent(){return('ontouchstart'in window||window.DocumentTouch&&document instanceof DocumentTouch);}

/**
 * jQuery specific functions. Can only run if jQuery is available
 */
if (window.jQuery) {
	(function(kbJQ, $, undefined) {
		
		/** Function to initialize tooltips with default option */
		kbJQ.TooltipOn = function() {
			$('[data-toggle="tooltip"]').tooltip({
				container: 'body',
				trigger: supportsTouchEvent() ? 'click' : 'hover',
				delay : {
					show : 500
				}
			});
			
			if (supportsTouchEvent()) {
				$('[data-toggle="tooltip"]').on('shown.bs.tooltip', function(e) {
					setTimeout(function() {
						$(e.target).tooltip('hide');
					}, 2000);
				});
			}
		};
		
		/** Function to initialize popovers with default option */
		kbJQ.PopoverOn = function() {
			$('[data-toggle="popover"]').popover({
				container: 'body',
				html: true,
				placement: 'auto',
				trigger: supportsTouchEvent() ? 'click' : 'focus'
			});
			
			if (supportsTouchEvent()) {
				$('[data-toggle="popover"]').on('shown.bs.popover', function(e) {
					setTimeout(function() {
						$(e.target).popover('hide');
					}, 10000);
				});
			}
		};
		
		/** Function to call another function when enter is pressed on an element */
		kbJQ.onEnter = function(elemSelector, callback) {
			$(elemSelector).keypress(function(e) {
				e = e || window.event;
				if ((e.keyCode ? e.keyCode : e.which) == 13) {
					callback();
					return false;
				}
			});
		};
		
	} (window.kbJQ = window.kbJQ || {}, jQuery));
}

function displayPDF(encryptedUrl){
	// loading spinner html
	var htmlDisplay = 
		'<div style="width: 100%; text-align: center; padding: 20px;">'
			+ '<span style="padding: 5px; font-size: 16px; border: 1px solid rgba(0,0,0,.25); color: rgba(0,0,0,.5);">'
				+ '<span class="fa fa-spinner fa-spin"></span>'
				+ ' Loading'
			+ '</span>'
		+ '</div>';
	
	// open a new window
	var waitWin = window.open(encryptedUrl, "PDF", "status=0,title=0,width=800,height=600,resizable=yes,scrollbars=1");
	if (waitWin){
		waitWin.document.write(htmlDisplay); // display loading spinner
		waitWin.location.href = encryptedUrl; // replace the window with the contents of the PDF
	} else {
		var message = "To view this PDF, please allow popups in the browser.";
		appUX.pop.notify(message, 300, 'auto', appUX.pop.styles.DANGER);
	}
}

function windowOpenUrlEncrypt(servlet, params) {
	var postparams = "servlet=" + servlet + "&params=" + encodeURIComponent(params);
	appUX.ajax.call('/CorisWEB/UrlEncryptionAjaxServlet',
		function (err, encryptedUrl, xhr) { 
			if (err)
				alert(err);
			
			window.open(encryptedUrl); 
		}, 
		'POST', 
		postparams, 
		'', 
		10000
	);
}

