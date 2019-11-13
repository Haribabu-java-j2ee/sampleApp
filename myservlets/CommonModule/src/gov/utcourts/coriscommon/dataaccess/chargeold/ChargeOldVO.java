package gov.utcourts.coriscommon.dataaccess.chargeold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ChargeOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(ChargeOldBO.ACTIONTYPE.clear());
	private TypeString reason = new TypeString("reason").setFieldDescriptor(ChargeOldBO.REASON.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(ChargeOldBO.USERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(ChargeOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(ChargeOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ChargeOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ChargeOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ChargeOldBO.INTCASENUM.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ChargeOldBO.PARTYNUM.clear());
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(ChargeOldBO.PARTYCODE.clear());
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(ChargeOldBO.SEQ.clear());
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(ChargeOldBO.GOVCODE.clear());
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(ChargeOldBO.VIOLCODE.clear()).setNullable();
	private TypeDate violDatetime = new TypeDate("viol_datetime").setFieldDescriptor(ChargeOldBO.VIOLDATETIME.clear());
	private TypeString violLocn = new TypeString("viol_locn").setFieldDescriptor(ChargeOldBO.VIOLLOCN.clear()).setNullable();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(ChargeOldBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate offsEffectDate = new TypeDate("offs_effect_date").setFieldDescriptor(ChargeOldBO.OFFSEFFECTDATE.clear());
	private TypeString inchoateFlag = new TypeString("inchoate_flag").setFieldDescriptor(ChargeOldBO.INCHOATEFLAG.clear()).setNullable();
	private TypeString severity = new TypeString("severity").setFieldDescriptor(ChargeOldBO.SEVERITY.clear()).setNullable();
	private TypeString pleaCode = new TypeString("plea_code").setFieldDescriptor(ChargeOldBO.PLEACODE.clear()).setNullable();
	private TypeDate pleaDate = new TypeDate("plea_date").setFieldDescriptor(ChargeOldBO.PLEADATE.clear()).setNullable();
	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(ChargeOldBO.JDMTCODE.clear()).setNullable();
	private TypeDate jdmtDate = new TypeDate("jdmt_date").setFieldDescriptor(ChargeOldBO.JDMTDATE.clear()).setNullable();
	private TypeString drLicSeverity = new TypeString("dr_lic_severity").setFieldDescriptor(ChargeOldBO.DRLICSEVERITY.clear()).setNullable();
	private TypeString drLicReduced = new TypeString("dr_lic_reduced").setFieldDescriptor(ChargeOldBO.DRLICREDUCED.clear()).setNullable();
	private TypeString drLicRptStatus = new TypeString("dr_lic_rpt_status").setFieldDescriptor(ChargeOldBO.DRLICRPTSTATUS.clear()).setNullable();
	private TypeDate drLicRptDate = new TypeDate("dr_lic_rpt_date").setFieldDescriptor(ChargeOldBO.DRLICRPTDATE.clear()).setNullable();
	private TypeDate bciRptDate = new TypeDate("bci_rpt_date").setFieldDescriptor(ChargeOldBO.BCIRPTDATE.clear()).setNullable();
	private TypeDate amendDate = new TypeDate("amend_date").setFieldDescriptor(ChargeOldBO.AMENDDATE.clear()).setNullable();
	private TypeString origGovCode = new TypeString("orig_gov_code").setFieldDescriptor(ChargeOldBO.ORIGGOVCODE.clear()).setNullable();
	private TypeString origViolCode = new TypeString("orig_viol_code").setFieldDescriptor(ChargeOldBO.ORIGVIOLCODE.clear()).setNullable();
	private TypeString origInchoateFlag = new TypeString("orig_inchoate_flag").setFieldDescriptor(ChargeOldBO.ORIGINCHOATEFLAG.clear()).setNullable();
	private TypeString origSeverity = new TypeString("orig_severity").setFieldDescriptor(ChargeOldBO.ORIGSEVERITY.clear()).setNullable();
	private TypeString amend402Type = new TypeString("amend402_type").setFieldDescriptor(ChargeOldBO.AMEND402TYPE.clear()).setNullable();
	private TypeString amend402Code = new TypeString("amend402_code").setFieldDescriptor(ChargeOldBO.AMEND402CODE.clear()).setNullable();
	private TypeString pre402Severity = new TypeString("pre402_severity").setFieldDescriptor(ChargeOldBO.PRE402SEVERITY.clear()).setNullable();
	private TypeString enhancType = new TypeString("enhanc_type").setFieldDescriptor(ChargeOldBO.ENHANCTYPE.clear()).setNullable();
	private TypeString preenhancSeverity = new TypeString("preenhanc_severity").setFieldDescriptor(ChargeOldBO.PREENHANCSEVERITY.clear()).setNullable();
	private TypeString spValue1 = new TypeString("sp_value_1").setFieldDescriptor(ChargeOldBO.SPVALUE1.clear()).setNullable();
	private TypeString spValue2 = new TypeString("sp_value_2").setFieldDescriptor(ChargeOldBO.SPVALUE2.clear()).setNullable();

	public ChargeOldVO() {
		super(ChargeOldBO.TABLE, ChargeOldBO.SYSTEM, ChargeOldBO.CORIS_DISTRICT_DB.setSource("D"), ChargeOldBO.CORIS_JUSTICE_DB.setSource("J"), ChargeOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChargeOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ChargeOldVO(String source) {
		super(ChargeOldBO.TABLE, ChargeOldBO.SYSTEM, ChargeOldBO.CORIS_DISTRICT_DB.setSource("D"), ChargeOldBO.CORIS_JUSTICE_DB.setSource("J"), ChargeOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChargeOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(changeDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(violCode);
		this.getPropertyList().add(violDatetime);
		this.getPropertyList().add(violLocn);
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(offsEffectDate);
		this.getPropertyList().add(inchoateFlag);
		this.getPropertyList().add(severity);
		this.getPropertyList().add(pleaCode);
		this.getPropertyList().add(pleaDate);
		this.getPropertyList().add(jdmtCode);
		this.getPropertyList().add(jdmtDate);
		this.getPropertyList().add(drLicSeverity);
		this.getPropertyList().add(drLicReduced);
		this.getPropertyList().add(drLicRptStatus);
		this.getPropertyList().add(drLicRptDate);
		this.getPropertyList().add(bciRptDate);
		this.getPropertyList().add(amendDate);
		this.getPropertyList().add(origGovCode);
		this.getPropertyList().add(origViolCode);
		this.getPropertyList().add(origInchoateFlag);
		this.getPropertyList().add(origSeverity);
		this.getPropertyList().add(amend402Type);
		this.getPropertyList().add(amend402Code);
		this.getPropertyList().add(pre402Severity);
		this.getPropertyList().add(enhancType);
		this.getPropertyList().add(preenhancSeverity);
		this.getPropertyList().add(spValue1);
		this.getPropertyList().add(spValue2);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ChargeOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.VIOLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.VIOLDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.OFFSEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.OFFSEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.PLEADATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.PLEADATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.JDMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.JDMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.DRLICRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.DRLICRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.BCIRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.BCIRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeOldBO.AMENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeOldBO.AMENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}