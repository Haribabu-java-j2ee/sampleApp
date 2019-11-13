package gov.utcourts.coriscommon.dataaccess.scharge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SChargeVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SChargeBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SChargeBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SChargeBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SChargeBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SChargeBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SChargeBO.INTCASENUM.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SChargeBO.PARTYNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SChargeBO.PARTYCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(SChargeBO.SEQ.clear()).setNullable().setAsPrimaryKey();
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(SChargeBO.GOVCODE.clear()).setNullable();
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(SChargeBO.VIOLCODE.clear()).setNullable();
	private TypeDate violDatetime = new TypeDate("viol_datetime").setFieldDescriptor(SChargeBO.VIOLDATETIME.clear()).setNullable();
	private TypeString violLocn = new TypeString("viol_locn").setFieldDescriptor(SChargeBO.VIOLLOCN.clear()).setNullable();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(SChargeBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate offsEffectDate = new TypeDate("offs_effect_date").setFieldDescriptor(SChargeBO.OFFSEFFECTDATE.clear()).setNullable();
	private TypeString inchoateFlag = new TypeString("inchoate_flag").setFieldDescriptor(SChargeBO.INCHOATEFLAG.clear()).setNullable();
	private TypeString severity = new TypeString("severity").setFieldDescriptor(SChargeBO.SEVERITY.clear()).setNullable();
	private TypeString pleaCode = new TypeString("plea_code").setFieldDescriptor(SChargeBO.PLEACODE.clear()).setNullable();
	private TypeDate pleaDate = new TypeDate("plea_date").setFieldDescriptor(SChargeBO.PLEADATE.clear()).setNullable();
	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(SChargeBO.JDMTCODE.clear()).setNullable();
	private TypeDate jdmtDate = new TypeDate("jdmt_date").setFieldDescriptor(SChargeBO.JDMTDATE.clear()).setNullable();
	private TypeString drLicSeverity = new TypeString("dr_lic_severity").setFieldDescriptor(SChargeBO.DRLICSEVERITY.clear()).setNullable();
	private TypeString drLicReduced = new TypeString("dr_lic_reduced").setFieldDescriptor(SChargeBO.DRLICREDUCED.clear()).setNullable();
	private TypeString drLicRptStatus = new TypeString("dr_lic_rpt_status").setFieldDescriptor(SChargeBO.DRLICRPTSTATUS.clear()).setNullable();
	private TypeDate drLicRptDate = new TypeDate("dr_lic_rpt_date").setFieldDescriptor(SChargeBO.DRLICRPTDATE.clear()).setNullable();
	private TypeDate bciRptDate = new TypeDate("bci_rpt_date").setFieldDescriptor(SChargeBO.BCIRPTDATE.clear()).setNullable();
	private TypeDate amendDate = new TypeDate("amend_date").setFieldDescriptor(SChargeBO.AMENDDATE.clear()).setNullable();
	private TypeString origGovCode = new TypeString("orig_gov_code").setFieldDescriptor(SChargeBO.ORIGGOVCODE.clear()).setNullable();
	private TypeString origViolCode = new TypeString("orig_viol_code").setFieldDescriptor(SChargeBO.ORIGVIOLCODE.clear()).setNullable();
	private TypeString origInchoateFlag = new TypeString("orig_inchoate_flag").setFieldDescriptor(SChargeBO.ORIGINCHOATEFLAG.clear()).setNullable();
	private TypeString origSeverity = new TypeString("orig_severity").setFieldDescriptor(SChargeBO.ORIGSEVERITY.clear()).setNullable();
	private TypeString amend402Type = new TypeString("amend402_type").setFieldDescriptor(SChargeBO.AMEND402TYPE.clear()).setNullable();
	private TypeString amend402Code = new TypeString("amend402_code").setFieldDescriptor(SChargeBO.AMEND402CODE.clear()).setNullable();
	private TypeString pre402Severity = new TypeString("pre402_severity").setFieldDescriptor(SChargeBO.PRE402SEVERITY.clear()).setNullable();
	private TypeString enhancType = new TypeString("enhanc_type").setFieldDescriptor(SChargeBO.ENHANCTYPE.clear()).setNullable();
	private TypeString preenhancSeverity = new TypeString("preenhanc_severity").setFieldDescriptor(SChargeBO.PREENHANCSEVERITY.clear()).setNullable();
	private TypeString spValue1 = new TypeString("sp_value_1").setFieldDescriptor(SChargeBO.SPVALUE1.clear()).setNullable();
	private TypeString spValue2 = new TypeString("sp_value_2").setFieldDescriptor(SChargeBO.SPVALUE2.clear()).setNullable();

	public SChargeVO() {
		super(SChargeBO.TABLE, SChargeBO.SYSTEM, SChargeBO.CORIS_DISTRICT_DB.setSource("D"), SChargeBO.CORIS_JUSTICE_DB.setSource("J"), SChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SChargeVO(String source) {
		super(SChargeBO.TABLE, SChargeBO.SYSTEM, SChargeBO.CORIS_DISTRICT_DB.setSource("D"), SChargeBO.CORIS_JUSTICE_DB.setSource("J"), SChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
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
			((TypeDate) this.getPropertyList().get(SChargeBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.VIOLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.VIOLDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.OFFSEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.OFFSEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.PLEADATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.PLEADATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.JDMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.JDMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.DRLICRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.DRLICRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.BCIRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.BCIRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SChargeBO.AMENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SChargeBO.AMENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}