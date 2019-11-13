package gov.utcourts.coriscommon.dataaccess.charge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ChargeVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(ChargeBO.INTCASENUM.clear()).addForeignKey("chrg_attr","int_case_num",false).addForeignKey("party_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ChargeBO.PARTYNUM.clear()).addForeignKey("chrg_attr","party_num",false).addForeignKey("party_case","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(ChargeBO.PARTYCODE.clear()).addForeignKey("chrg_attr","party_code",false).addForeignKey("party_case","party_code",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(ChargeBO.SEQ.clear()).addForeignKey("chrg_attr","seq",false).setAsPrimaryKey();
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(ChargeBO.GOVCODE.clear());
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(ChargeBO.VIOLCODE.clear()).setNullable();
	private TypeDate violDatetime = new TypeDate("viol_datetime").setFieldDescriptor(ChargeBO.VIOLDATETIME.clear());
	private TypeString violLocn = new TypeString("viol_locn").setFieldDescriptor(ChargeBO.VIOLLOCN.clear()).setNullable();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(ChargeBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate offsEffectDate = new TypeDate("offs_effect_date").setFieldDescriptor(ChargeBO.OFFSEFFECTDATE.clear());
	private TypeString inchoateFlag = new TypeString("inchoate_flag").setFieldDescriptor(ChargeBO.INCHOATEFLAG.clear()).setNullable();
	private TypeString severity = new TypeString("severity").setFieldDescriptor(ChargeBO.SEVERITY.clear()).addForeignKey("severity_type","severity_code",false).setNullable();
	private TypeString pleaCode = new TypeString("plea_code").setFieldDescriptor(ChargeBO.PLEACODE.clear()).addForeignKey("plea_type","plea_code",false).setNullable();
	private TypeDate pleaDate = new TypeDate("plea_date").setFieldDescriptor(ChargeBO.PLEADATE.clear()).setNullable();
	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(ChargeBO.JDMTCODE.clear()).addForeignKey("jdmt_type","jdmt_code",false).setNullable();
	private TypeDate jdmtDate = new TypeDate("jdmt_date").setFieldDescriptor(ChargeBO.JDMTDATE.clear()).setNullable();
	private TypeString drLicSeverity = new TypeString("dr_lic_severity").setFieldDescriptor(ChargeBO.DRLICSEVERITY.clear()).setNullable();
	private TypeString drLicReduced = new TypeString("dr_lic_reduced").setFieldDescriptor(ChargeBO.DRLICREDUCED.clear()).setNullable();
	private TypeString drLicRptStatus = new TypeString("dr_lic_rpt_status").setFieldDescriptor(ChargeBO.DRLICRPTSTATUS.clear()).setNullable();
	private TypeDate drLicRptDate = new TypeDate("dr_lic_rpt_date").setFieldDescriptor(ChargeBO.DRLICRPTDATE.clear()).setNullable();
	private TypeDate bciRptDate = new TypeDate("bci_rpt_date").setFieldDescriptor(ChargeBO.BCIRPTDATE.clear()).setNullable();
	private TypeDate amendDate = new TypeDate("amend_date").setFieldDescriptor(ChargeBO.AMENDDATE.clear()).setNullable();
	private TypeString origGovCode = new TypeString("orig_gov_code").setFieldDescriptor(ChargeBO.ORIGGOVCODE.clear()).setNullable();
	private TypeString origViolCode = new TypeString("orig_viol_code").setFieldDescriptor(ChargeBO.ORIGVIOLCODE.clear()).setNullable();
	private TypeString origInchoateFlag = new TypeString("orig_inchoate_flag").setFieldDescriptor(ChargeBO.ORIGINCHOATEFLAG.clear()).setNullable();
	private TypeString origSeverity = new TypeString("orig_severity").setFieldDescriptor(ChargeBO.ORIGSEVERITY.clear()).setNullable();
	private TypeString amend402Type = new TypeString("amend402_type").setFieldDescriptor(ChargeBO.AMEND402TYPE.clear()).setNullable();
	private TypeString amend402Code = new TypeString("amend402_code").setFieldDescriptor(ChargeBO.AMEND402CODE.clear()).setNullable();
	private TypeString pre402Severity = new TypeString("pre402_severity").setFieldDescriptor(ChargeBO.PRE402SEVERITY.clear()).setNullable();
	private TypeString enhancType = new TypeString("enhanc_type").setFieldDescriptor(ChargeBO.ENHANCTYPE.clear()).setNullable();
	private TypeString preenhancSeverity = new TypeString("preenhanc_severity").setFieldDescriptor(ChargeBO.PREENHANCSEVERITY.clear()).setNullable();
	private TypeString spValue1 = new TypeString("sp_value_1").setFieldDescriptor(ChargeBO.SPVALUE1.clear()).setNullable();
	private TypeString spValue2 = new TypeString("sp_value_2").setFieldDescriptor(ChargeBO.SPVALUE2.clear()).setNullable();

	public ChargeVO() {
		super(ChargeBO.TABLE, ChargeBO.SYSTEM, ChargeBO.CORIS_DISTRICT_DB.setSource("D"), ChargeBO.CORIS_JUSTICE_DB.setSource("J"), ChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ChargeVO(String source) {
		super(ChargeBO.TABLE, ChargeBO.SYSTEM, ChargeBO.CORIS_DISTRICT_DB.setSource("D"), ChargeBO.CORIS_JUSTICE_DB.setSource("J"), ChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(ChargeBO.VIOLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.VIOLDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.OFFSEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.OFFSEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.PLEADATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.PLEADATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.JDMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.JDMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.DRLICRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.DRLICRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.BCIRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.BCIRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(ChargeBO.AMENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ChargeBO.AMENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}