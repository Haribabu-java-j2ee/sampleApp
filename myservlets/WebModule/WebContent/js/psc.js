var ACTION_DEFINITIONS = "ActionDefinitions";
var APPEARANCE_DEFINITIONS = "AppearanceDefinitions";
var EVALUATION_DEFINITIONS = "EvaluationDefinitions";
var GOAL_DEFINITIONS = "GoalDefinitions";
var PHASE_DEFINITIONS = "PhaseDefinitions";
var REWARD_DEFINITIONS = "RewardDefinitions";
var SANCTION_DEFINITIONS = "SanctionDefinitions";
var STATUS_DEFINITIONS = "StatusDefinitions";
var TERMINATION_DEFINITIONS = "TerminationDefinitions";
var TREATMENT_DEFINITIONS = "TreatmentDefinitions";
var ACTION_COURT_XREF = "ActionCourtXref";
var COURT_LOCATION_XREF = "CourtLocationXref";
var REWARD_COURT_XREF = "RewardCourtXref";
var SANCTION_COURT_XREF = "SanctionCourtXref";
var STATUS_ACTION_XREF = "StatusActionXref";

var accessActionDefnPopup = null;
function showActionDefn() {
	if (accessActionDefnPopup != null)
		accessActionDefnPopup.close();
	
	var title = "Action Definitions";
	var cornId = "modal" + ACTION_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceActionDefnServlet";
	
	accessActionDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessAppearanceDefnPopup = null;
function showAppearanceDefn() {
	if (accessAppearanceDefnPopup != null)
		accessAppearanceDefnPopup.close();
	
	var title = "Appearance Definitions";
	var cornId = "modal" + APPEARANCE_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceAppearanceDefnServlet";
	
	accessAppearanceDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessEvaluationDefnPopup = null;
function showEvaluationDefn() {
	if (accessEvaluationDefnPopup != null)
		accessEvaluationDefnPopup.close();
	
	var title = "Evaluation Definitions";
	var cornId = "modal" + EVALUATION_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceEvaluationDefnServlet";
	
	accessEvaluationDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessGoalDefnPopup = null;
function showGoalDefn() {
	if (accessGoalDefnPopup != null)
		accessGoalDefnPopup.close();
	
	var title = "Goal Definitions";
	var cornId = "modal" + GOAL_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceGoalDefnServlet";
	
	accessGoalDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessPhaseDefnPopup = null;
function showPhaseDefn() {
	if (accessPhaseDefnPopup != null)
		accessPhaseDefnPopup.close();
	
	var title = "Phase Definitions";
	var cornId = "modal" + PHASE_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenancePhaseDefnServlet";
	
	accessPhaseDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessRewardDefnPopup = null;
function showRewardDefn() {
	if (accessRewardDefnPopup != null)
		accessRewardDefnPopup.close();
	
	var title = "Reward Definitions";
	var cornId = "modal" + REWARD_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceRewardDefnServlet";
	
	accessRewardDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessSanctionDefnPopup = null;
function showSanctionDefn() {
	if (accessSanctionDefnPopup != null)
		accessSanctionDefnPopup.close();
	
	var title = "Sanction Definitions";
	var cornId = "modal" + SANCTION_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceSanctionDefnServlet";
	
	accessSanctionDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessStatusDefnPopup = null;
function showStatusDefn() {
	if (accessStatusDefnPopup != null)
		accessStatusDefnPopup.close();
	
	var title = "Status Definitions";
	var cornId = "modal" + STATUS_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceStatusDefnServlet";
	
	accessStatusDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessTerminationDefnPopup = null;
function showTerminationDefn() {
	if (accessTerminationDefnPopup != null)
		accessTerminationDefnPopup.close();
	
	var title = "Termination Definitions";
	var cornId = "modal" + TERMINATION_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceTerminationDefnServlet";
	
	accessTerminationDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessTreatmentDefnPopup = null;
function showTreatmentDefn() {
	if (accessTreatmentDefnPopup != null)
		accessTreatmentDefnPopup.close();
	
	var title = "Treatment Definitions";
	var cornId = "modal" + TREATMENT_DEFINITIONS + "Primary";
	var url = "/CorisWEB/PSCMaintenanceTreatmentDefnServlet";
	
	accessTreatmentDefnPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessActionCourtXrefPopup = null;
function showActionCourtXref() {
	if (accessActionCourtXrefPopup != null)
		accessActionCourtXrefPopup.close();
	
	var title = "Action - Court Cross Reference";
	var cornId = "modal" + ACTION_COURT_XREF + "Primary";
	var url = "/CorisWEB/PSCMaintenanceXrefActionCourtServlet";
	
	accessActionCourtXrefPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessCourtLocationXrefPopup = null;
function showCourtLocationXref() {
	if (accessCourtLocationXrefPopup != null)
		accessCourtLocationXrefPopup.close();
	
	var title = "Court - Location Cross Reference";
	var cornId = "modal" + COURT_LOCATION_XREF + "Primary";
	var url = "/CorisWEB/PSCMaintenanceXrefCourtLocationRedoServlet";
	
	accessCourtLocationXrefPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessRewardCourtXrefPopup = null;
function showRewardCourtXref() {
	if (accessRewardCourtXrefPopup != null)
		accessRewardCourtXrefPopup.close();
	
	var title = "Reward - Court Cross Reference";
	var cornId = "modal" + REWARD_COURT_XREF + "Primary";
	var url = "/CorisWEB/PSCMaintenanceXrefRewardCourtServlet";
	
	accessRewardCourtXrefPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessSanctionCourtXrefPopup = null;
function showSanctionCourtXref() {
	if (accessSanctionCourtXrefPopup != null)
		accessSanctionCourtXrefPopup.close();
	
	var title = "Sanction - Court Cross Reference";
	var cornId = "modal" + SANCTION_COURT_XREF + "Primary";
	var url = "/CorisWEB/PSCMaintenanceXrefSanctionCourtServlet";
	
	accessSanctionCourtXrefPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

var accessStatusActionXrefPopup = null;
function showStatusActionXref() {
	if (accessStatusActionXrefPopup != null)
		accessStatusActionXrefPopup.close();
	
	var title = "Status - Action Cross Reference";
	var cornId = "modal" + STATUS_ACTION_XREF + "Primary";
	var url = "/CorisWEB/PSCMaintenanceXrefStatusActionServlet";
	
	accessStatusActionXrefPopup = appUX.pop.modal(cornId, title, url, 700, 500, appUX.pop.styles.PRIMARY);
}

function displayReferralHeader(intCaseNum) {
	appUX.ajax.call("/CorisWEB/PSCReferralsServlet", 
		function (err, data, xhr) { 
			$("#displayReferralHeader").html(data); 
		}, 
		'POST', 
		'mode=displayReferralHeader&intCaseNum=' + intCaseNum
	); 
}
