/**
 * JavaScript source file for caseSummarySearchLookupDetail.jsp
 */

function emailPDF(emailUrl) {
	appUX.pop.declare("Email", "Email being generated", 300, 'auto', appUX.pop.styles.INFO);
	appUX.ajax.call(emailUrl, function(err, data, xhr) {
		err ? appUX.pop.declare("Email", "Email could not be sent", 300, 'auto', appUX.pop.styles.DANGER) : appUX.pop.declare("Email", "Email will be delivered shortly", 300, 'auto', appUX.pop.styles.SUCCESS);
	});
}
