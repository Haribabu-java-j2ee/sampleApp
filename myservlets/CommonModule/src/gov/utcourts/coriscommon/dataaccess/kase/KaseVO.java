package gov.utcourts.coriscommon.dataaccess.kase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class KaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(KaseBO.INTCASENUM.clear()).addForeignKey("account","int_case_num",true).addForeignKey("appellate","int_case_num",false).addForeignKey("archive","int_case_num",false).addForeignKey("atty_present","int_case_num",false).addForeignKey("auto_expungement","int_case_num",true).addForeignKey("calendar","int_case_num",false).addForeignKey("case_feature","int_case_num",false).addForeignKey("case_hist","int_case_num",false).addForeignKey("case_me","int_case_num",false).addForeignKey("case_po_type","int_case_num",false).addForeignKey("case_warning","int_case_num",false).addForeignKey("civil_case","int_case_num",false).addForeignKey("comp_date","int_case_num",false).addForeignKey("continuance","int_case_num",false).addForeignKey("crim_case","int_case_num",false).addForeignKey("days_to_disp","int_case_num",false).addForeignKey("dc_account","int_case_num",true).addForeignKey("dc_letters","int_case_num",false).addForeignKey("ddo_draft","int_case_num",false).addForeignKey("document","int_case_num",false).addForeignKey("document_due_date","int_case_num",true).addForeignKey("document_process","int_case_num",false).addForeignKey("dom_viol_dismissal","int_case_num",false).addForeignKey("dom_viol_link","int_case_num",false).addForeignKey("domestic_modifications","int_case_num",false).addForeignKey("edoc","int_case_num",false).addForeignKey("efiling_activity","int_case_num",true).addForeignKey("efiling_notify","int_case_num",false).addForeignKey("event","int_case_num",false).addForeignKey("evidence","int_case_num",false).addForeignKey("evidence_list","int_case_num",false).addForeignKey("form","int_case_num",false).addForeignKey("hist_me","int_case_num",false).addForeignKey("jdmt_abstract","int_case_num",false).addForeignKey("jdmt_roll","int_case_num",false).addForeignKey("judge_hist","int_case_num",false).addForeignKey("judgment","int_case_num",false).addForeignKey("mental_safety","int_case_num",false).addForeignKey("odr_activity","odr_activity_int_case_num",false).addForeignKey("odr_case","int_case_num",false).addForeignKey("ors_registry","int_case_num",false).addForeignKey("otsc_hold","int_case_num",true).addForeignKey("party_case","int_case_num",false).addForeignKey("post_jdmt_activ","int_case_num",true).addForeignKey("post_jdmt_int_comp","int_case_num",false).addForeignKey("proof_case","int_case_num",false).addForeignKey("protectiveorder_draft","int_case_num",false).addForeignKey("psi_app_request","int_case_num",false).addForeignKey("rotate_judges","int_case_num",false).addForeignKey("sc_interest","int_case_num",true).addForeignKey("sent_fulfill","int_case_num",false).addForeignKey("summary_event","int_case_num",false).addForeignKey("tracking","int_case_num",false).addForeignKey("transfer_hist","int_case_num",false).addForeignKey("vehicle","int_case_num",false).addForeignKey("work_cal_case","int_case_num",false).setAsPrimaryKey();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(KaseBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(KaseBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(KaseBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setNullable();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(KaseBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false);
	private TypeString filingType = new TypeString("filing_type").setFieldDescriptor(KaseBO.FILINGTYPE.clear()).setNullable();
	private TypeDate filingDate = new TypeDate("filing_date").setFieldDescriptor(KaseBO.FILINGDATE.clear());
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(KaseBO.DISPDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(KaseBO.DISPCODE.clear()).addForeignKey("disp_type","disp_code",false).setNullable();
	private TypeString oldCaseNum = new TypeString("old_case_num").setFieldDescriptor(KaseBO.OLDCASENUM.clear()).setNullable();
	private TypeString refNum = new TypeString("ref_num").setFieldDescriptor(KaseBO.REFNUM.clear()).setNullable();
	private TypeDate archiveDate = new TypeDate("archive_date").setFieldDescriptor(KaseBO.ARCHIVEDATE.clear()).setNullable();
	private TypeInteger assnJudgeId = new TypeInteger("assn_judge_id").setFieldDescriptor(KaseBO.ASSNJUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger assnCommId = new TypeInteger("assn_comm_id").setFieldDescriptor(KaseBO.ASSNCOMMID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger dispId = new TypeInteger("disp_id").setFieldDescriptor(KaseBO.DISPID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString domViolence = new TypeString("dom_violence").setFieldDescriptor(KaseBO.DOMVIOLENCE.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(KaseBO.NOTE.clear()).setNullable();
	private TypeInteger linkedCaseId = new TypeInteger("linked_case_id").setFieldDescriptor(KaseBO.LINKEDCASEID.clear()).addForeignKey("link","linked_id",false).setNullable();
	private TypeInteger linkedDefId = new TypeInteger("linked_def_id").setFieldDescriptor(KaseBO.LINKEDDEFID.clear()).addForeignKey("link","linked_id",false).setNullable();
	private TypeString calStatus = new TypeString("cal_status").setFieldDescriptor(KaseBO.CALSTATUS.clear()).setNullable();
	private TypeDate trackDate = new TypeDate("track_date").setFieldDescriptor(KaseBO.TRACKDATE.clear()).setNullable();
	private TypeString debtCollection = new TypeString("debt_collection").setFieldDescriptor(KaseBO.DEBTCOLLECTION.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(KaseBO.USERIDSRL.clear()).setNullable();
	private TypeString transferred = new TypeString("transferred").setFieldDescriptor(KaseBO.TRANSFERRED.clear()).setNullable();
	private TypeString localDebtColl = new TypeString("local_debt_coll").setFieldDescriptor(KaseBO.LOCALDEBTCOLL.clear()).setNullable();
	private TypeDate debtCollDate = new TypeDate("debt_coll_date").setFieldDescriptor(KaseBO.DEBTCOLLDATE.clear()).setNullable();
	private TypeInteger debtCollUserid = new TypeInteger("debt_coll_userid").setFieldDescriptor(KaseBO.DEBTCOLLUSERID.clear()).setNullable();
	private TypeString edocNum = new TypeString("edoc_num").setFieldDescriptor(KaseBO.EDOCNUM.clear()).setNullable();
	private TypeString debtCollNote = new TypeString("debt_coll_note").setFieldDescriptor(KaseBO.DEBTCOLLNOTE.clear()).setNullable();
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(KaseBO.CASESECURITY.clear()).addForeignKey("security_type","code",false);

	public KaseVO() {
		super(KaseBO.TABLE, KaseBO.SYSTEM, KaseBO.CORIS_DISTRICT_DB.setSource("D"), KaseBO.CORIS_JUSTICE_DB.setSource("J"), KaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), KaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public KaseVO(String source) {
		super(KaseBO.TABLE, KaseBO.SYSTEM, KaseBO.CORIS_DISTRICT_DB.setSource("D"), KaseBO.CORIS_JUSTICE_DB.setSource("J"), KaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), KaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(filingType);
		this.getPropertyList().add(filingDate);
		this.getPropertyList().add(dispDate);
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(oldCaseNum);
		this.getPropertyList().add(refNum);
		this.getPropertyList().add(archiveDate);
		this.getPropertyList().add(assnJudgeId);
		this.getPropertyList().add(assnCommId);
		this.getPropertyList().add(dispId);
		this.getPropertyList().add(domViolence);
		this.getPropertyList().add(note);
		this.getPropertyList().add(linkedCaseId);
		this.getPropertyList().add(linkedDefId);
		this.getPropertyList().add(calStatus);
		this.getPropertyList().add(trackDate);
		this.getPropertyList().add(debtCollection);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(transferred);
		this.getPropertyList().add(localDebtColl);
		this.getPropertyList().add(debtCollDate);
		this.getPropertyList().add(debtCollUserid);
		this.getPropertyList().add(edocNum);
		this.getPropertyList().add(debtCollNote);
		this.getPropertyList().add(caseSecurity);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(KaseBO.FILINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseBO.FILINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseBO.DISPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseBO.ARCHIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseBO.ARCHIVEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseBO.TRACKDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseBO.TRACKDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseBO.DEBTCOLLDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseBO.DEBTCOLLDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}