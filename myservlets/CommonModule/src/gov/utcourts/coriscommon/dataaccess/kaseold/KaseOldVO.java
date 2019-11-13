package gov.utcourts.coriscommon.dataaccess.kaseold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class KaseOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(KaseOldBO.ACTIONTYPE.clear());
	private TypeString reason = new TypeString("reason").setFieldDescriptor(KaseOldBO.REASON.clear());
	private TypeInteger actionUseridSrl = new TypeInteger("action_userid_srl").setFieldDescriptor(KaseOldBO.ACTIONUSERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(KaseOldBO.CHANGEDATETIME.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(KaseOldBO.INTCASENUM.clear()).setAsPrimaryKey();
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(KaseOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(KaseOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(KaseOldBO.COURTTYPE.clear()).setNullable();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(KaseOldBO.CASETYPE.clear()).setNullable();
	private TypeString filingType = new TypeString("filing_type").setFieldDescriptor(KaseOldBO.FILINGTYPE.clear()).setNullable();
	private TypeDate filingDate = new TypeDate("filing_date").setFieldDescriptor(KaseOldBO.FILINGDATE.clear()).setNullable();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(KaseOldBO.DISPDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(KaseOldBO.DISPCODE.clear()).setNullable();
	private TypeString oldCaseNum = new TypeString("old_case_num").setFieldDescriptor(KaseOldBO.OLDCASENUM.clear()).setNullable();
	private TypeString refNum = new TypeString("ref_num").setFieldDescriptor(KaseOldBO.REFNUM.clear()).setNullable();
	private TypeDate archiveDate = new TypeDate("archive_date").setFieldDescriptor(KaseOldBO.ARCHIVEDATE.clear()).setNullable();
	private TypeInteger assnJudgeId = new TypeInteger("assn_judge_id").setFieldDescriptor(KaseOldBO.ASSNJUDGEID.clear()).setNullable();
	private TypeInteger assnCommId = new TypeInteger("assn_comm_id").setFieldDescriptor(KaseOldBO.ASSNCOMMID.clear()).setNullable();
	private TypeInteger dispId = new TypeInteger("disp_id").setFieldDescriptor(KaseOldBO.DISPID.clear()).setNullable();
	private TypeString domViolence = new TypeString("dom_violence").setFieldDescriptor(KaseOldBO.DOMVIOLENCE.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(KaseOldBO.NOTE.clear()).setNullable();
	private TypeInteger linkedCaseId = new TypeInteger("linked_case_id").setFieldDescriptor(KaseOldBO.LINKEDCASEID.clear()).setNullable();
	private TypeInteger linkedDefId = new TypeInteger("linked_def_id").setFieldDescriptor(KaseOldBO.LINKEDDEFID.clear()).setNullable();
	private TypeString calStatus = new TypeString("cal_status").setFieldDescriptor(KaseOldBO.CALSTATUS.clear()).setNullable();
	private TypeDate trackDate = new TypeDate("track_date").setFieldDescriptor(KaseOldBO.TRACKDATE.clear()).setNullable();
	private TypeString debtCollection = new TypeString("debt_collection").setFieldDescriptor(KaseOldBO.DEBTCOLLECTION.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(KaseOldBO.USERIDSRL.clear()).setNullable();
	private TypeString transferred = new TypeString("transferred").setFieldDescriptor(KaseOldBO.TRANSFERRED.clear()).setNullable();
	private TypeString localDebtColl = new TypeString("local_debt_coll").setFieldDescriptor(KaseOldBO.LOCALDEBTCOLL.clear()).setNullable();
	private TypeDate debtCollDate = new TypeDate("debt_coll_date").setFieldDescriptor(KaseOldBO.DEBTCOLLDATE.clear()).setNullable();
	private TypeInteger debtCollUserid = new TypeInteger("debt_coll_userid").setFieldDescriptor(KaseOldBO.DEBTCOLLUSERID.clear()).setNullable();
	private TypeString edocNum = new TypeString("edoc_num").setFieldDescriptor(KaseOldBO.EDOCNUM.clear()).setNullable();
	private TypeString debtCollNote = new TypeString("debt_coll_note").setFieldDescriptor(KaseOldBO.DEBTCOLLNOTE.clear()).setNullable();
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(KaseOldBO.CASESECURITY.clear()).setNullable();

	public KaseOldVO() {
		super(KaseOldBO.TABLE, KaseOldBO.SYSTEM, KaseOldBO.CORIS_DISTRICT_DB.setSource("D"), KaseOldBO.CORIS_JUSTICE_DB.setSource("J"), KaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), KaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public KaseOldVO(String source) {
		super(KaseOldBO.TABLE, KaseOldBO.SYSTEM, KaseOldBO.CORIS_DISTRICT_DB.setSource("D"), KaseOldBO.CORIS_JUSTICE_DB.setSource("J"), KaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), KaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(actionUseridSrl);
		this.getPropertyList().add(changeDatetime);
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
			((TypeDate) this.getPropertyList().get(KaseOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseOldBO.FILINGDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.FILINGDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseOldBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.DISPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseOldBO.ARCHIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.ARCHIVEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseOldBO.TRACKDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.TRACKDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(KaseOldBO.DEBTCOLLDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(KaseOldBO.DEBTCOLLDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}