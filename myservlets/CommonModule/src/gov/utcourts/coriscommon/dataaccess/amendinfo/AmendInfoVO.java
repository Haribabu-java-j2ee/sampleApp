package gov.utcourts.coriscommon.dataaccess.amendinfo;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AmendInfoVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AmendInfoBO.INTCASENUM.clear()).addForeignKey("amend_info_attr","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(AmendInfoBO.PARTYNUM.clear()).addForeignKey("amend_info_attr","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(AmendInfoBO.PARTYCODE.clear()).addForeignKey("amend_info_attr","party_code",false).setAsPrimaryKey();
	private TypeInteger infoNum = new TypeInteger("info_num").setFieldDescriptor(AmendInfoBO.INFONUM.clear()).addForeignKey("amend_info_attr","info_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(AmendInfoBO.SEQ.clear()).addForeignKey("amend_info_attr","seq",false).setAsPrimaryKey();
	private TypeDate amendInfoDate = new TypeDate("amend_info_date").setFieldDescriptor(AmendInfoBO.AMENDINFODATE.clear()).setNullable();
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(AmendInfoBO.GOVCODE.clear());
	private TypeString violCode = new TypeString("viol_code").setFieldDescriptor(AmendInfoBO.VIOLCODE.clear()).setNullable();
	private TypeDate violDatetime = new TypeDate("viol_datetime").setFieldDescriptor(AmendInfoBO.VIOLDATETIME.clear()).setNullable();
	private TypeString violLocn = new TypeString("viol_locn").setFieldDescriptor(AmendInfoBO.VIOLLOCN.clear()).setNullable();
	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(AmendInfoBO.OFFSVIOLCODE.clear()).setNullable();
	private TypeDate offsEffectDate = new TypeDate("offs_effect_date").setFieldDescriptor(AmendInfoBO.OFFSEFFECTDATE.clear()).setNullable();
	private TypeString inchoateFlag = new TypeString("inchoate_flag").setFieldDescriptor(AmendInfoBO.INCHOATEFLAG.clear()).setNullable();
	private TypeString severity = new TypeString("severity").setFieldDescriptor(AmendInfoBO.SEVERITY.clear()).setNullable();
	private TypeString pleaCode = new TypeString("plea_code").setFieldDescriptor(AmendInfoBO.PLEACODE.clear()).setNullable();
	private TypeDate pleaDate = new TypeDate("plea_date").setFieldDescriptor(AmendInfoBO.PLEADATE.clear()).setNullable();
	private TypeString jdmtCode = new TypeString("jdmt_code").setFieldDescriptor(AmendInfoBO.JDMTCODE.clear()).setNullable();
	private TypeDate jdmtDate = new TypeDate("jdmt_date").setFieldDescriptor(AmendInfoBO.JDMTDATE.clear()).setNullable();
	private TypeString drLicSeverity = new TypeString("dr_lic_severity").setFieldDescriptor(AmendInfoBO.DRLICSEVERITY.clear()).setNullable();
	private TypeString drLicReduced = new TypeString("dr_lic_reduced").setFieldDescriptor(AmendInfoBO.DRLICREDUCED.clear()).setNullable();
	private TypeString drLicRptStatus = new TypeString("dr_lic_rpt_status").setFieldDescriptor(AmendInfoBO.DRLICRPTSTATUS.clear()).setNullable();
	private TypeDate drLicRptDate = new TypeDate("dr_lic_rpt_date").setFieldDescriptor(AmendInfoBO.DRLICRPTDATE.clear()).setNullable();
	private TypeDate bciRptDate = new TypeDate("bci_rpt_date").setFieldDescriptor(AmendInfoBO.BCIRPTDATE.clear()).setNullable();
	private TypeDate amendDate = new TypeDate("amend_date").setFieldDescriptor(AmendInfoBO.AMENDDATE.clear()).setNullable();
	private TypeString origGovCode = new TypeString("orig_gov_code").setFieldDescriptor(AmendInfoBO.ORIGGOVCODE.clear()).setNullable();
	private TypeString origViolCode = new TypeString("orig_viol_code").setFieldDescriptor(AmendInfoBO.ORIGVIOLCODE.clear()).setNullable();
	private TypeString origInchoateFlag = new TypeString("orig_inchoate_flag").setFieldDescriptor(AmendInfoBO.ORIGINCHOATEFLAG.clear()).setNullable();
	private TypeString origSeverity = new TypeString("orig_severity").setFieldDescriptor(AmendInfoBO.ORIGSEVERITY.clear()).setNullable();
	private TypeString amend402Type = new TypeString("amend402_type").setFieldDescriptor(AmendInfoBO.AMEND402TYPE.clear()).setNullable();
	private TypeString amend402Code = new TypeString("amend402_code").setFieldDescriptor(AmendInfoBO.AMEND402CODE.clear()).setNullable();
	private TypeString pre402Severity = new TypeString("pre402_severity").setFieldDescriptor(AmendInfoBO.PRE402SEVERITY.clear()).setNullable();
	private TypeString enhancType = new TypeString("enhanc_type").setFieldDescriptor(AmendInfoBO.ENHANCTYPE.clear()).setNullable();
	private TypeString preenhancSeverity = new TypeString("preenhanc_severity").setFieldDescriptor(AmendInfoBO.PREENHANCSEVERITY.clear()).setNullable();
	private TypeString spValue1 = new TypeString("sp_value_1").setFieldDescriptor(AmendInfoBO.SPVALUE1.clear()).setNullable();
	private TypeString spValue2 = new TypeString("sp_value_2").setFieldDescriptor(AmendInfoBO.SPVALUE2.clear()).setNullable();

	public AmendInfoVO() {
		super(AmendInfoBO.TABLE, AmendInfoBO.SYSTEM, AmendInfoBO.CORIS_DISTRICT_DB.setSource("D"), AmendInfoBO.CORIS_JUSTICE_DB.setSource("J"), AmendInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AmendInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AmendInfoVO(String source) {
		super(AmendInfoBO.TABLE, AmendInfoBO.SYSTEM, AmendInfoBO.CORIS_DISTRICT_DB.setSource("D"), AmendInfoBO.CORIS_JUSTICE_DB.setSource("J"), AmendInfoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AmendInfoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(infoNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(amendInfoDate);
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
			((TypeDate) this.getPropertyList().get(AmendInfoBO.AMENDINFODATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.AMENDINFODATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.VIOLDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.VIOLDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.OFFSEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.OFFSEFFECTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.PLEADATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.PLEADATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.JDMTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.JDMTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.DRLICRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.DRLICRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.BCIRPTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.BCIRPTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AmendInfoBO.AMENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AmendInfoBO.AMENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}