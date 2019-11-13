/*
 * JavaScript source file for main.jsp
 */

/*
 * On-ready function
 */
$(document).ready(function() {
	// name main window
	window.name = 'appControlCenter';
	
	// setup app nav data load
	appNavDataLoad();
	
	// load first corn
	// loadCorn();
});

/**
 * Setup app nav data load and initialize nav library
 */
function appNavDataLoad() {
	appUX.ajax.call('/CorisWEB/MenuAjaxServlet?action=retrieveAppNavTree&~=' + new Date().getTime(), function(err, data, xhr) {
		if (err) {
			appUX.pop.alert(err, 400, 'auto', appUX.pop.styles.DANGER);
		} else {
			var jsonData = JSON.parse(data);
			jsonData.error ? appUX.pop.alert(jsonData.errorMessage, 400, 'auto', appUX.pop.styles.DANGER) : appUX.nav.init(jsonData, appNavDataLoad);
		}
	});
}

/**
 * Load first corn
 */
function loadCorn() {
	var corn = appUX.popcorn.create({
		content: '<div class="display-1 text-center">Welcome to CORIS...</div>',
		contentSize: '550 200',
		header: 'auto-show-hide',
		headerTitle: '',
		headerControls: 'closeonly',
		theme: 'none',
		boxShadow: 0,
		dragit: {
			handles: '.popcorn-content'
		},
		resizeit: false,
		callback: function() {
			this.style.background = 'transparent';
			this.content.style.background = 'transparent';
		}
	});
	
	window.onresize = function() {
		corn.reposition('center');
	};
}
