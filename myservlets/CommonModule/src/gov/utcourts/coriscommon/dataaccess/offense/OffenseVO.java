package gov.utcourts.coriscommon.dataaccess.offense;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OffenseVO extends BaseVO { 

	private TypeString offsViolCode = new TypeString("offs_viol_code").setFieldDescriptor(OffenseBO.OFFSVIOLCODE.clear()).addForeignKey("offense_registry_code","offs_viol_code",false).setNullable().setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(OffenseBO.LASTEFFECTDATE.clear()).addForeignKey("offense_registry_code","last_effect_date",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(OffenseBO.DESCR.clear()).setNullable();
	private TypeString category = new TypeString("category").setFieldDescriptor(OffenseBO.CATEGORY.clear());
	private TypeString ncicCode = new TypeString("ncic_code").setFieldDescriptor(OffenseBO.NCICCODE.clear()).setNullable();
	private TypeString aamvaCode = new TypeString("aamva_code").setFieldDescriptor(OffenseBO.AAMVACODE.clear()).setNullable();
	private TypeString drLicRpt = new TypeString("dr_lic_rpt").setFieldDescriptor(OffenseBO.DRLICRPT.clear()).setNullable();
	private TypeString bciRpt = new TypeString("bci_rpt").setFieldDescriptor(OffenseBO.BCIRPT.clear()).setNullable();
	private TypeString specProcAttr = new TypeString("spec_proc_attr").setFieldDescriptor(OffenseBO.SPECPROCATTR.clear()).setNullable();
	private TypeString ftaFlag = new TypeString("fta_flag").setFieldDescriptor(OffenseBO.FTAFLAG.clear()).setNullable();
	private TypeString warrFlag = new TypeString("warr_flag").setFieldDescriptor(OffenseBO.WARRFLAG.clear()).setNullable();
	private TypeString mandAppearFlag = new TypeString("mand_appear_flag").setFieldDescriptor(OffenseBO.MANDAPPEARFLAG.clear()).setNullable();
	private TypeString defltSeverity = new TypeString("deflt_severity").setFieldDescriptor(OffenseBO.DEFLTSEVERITY.clear()).setNullable();
	private TypeBigDecimal suggestBail = new TypeBigDecimal("suggest_bail").setFieldDescriptor(OffenseBO.SUGGESTBAIL.clear()).setNullable();
	private TypeBigDecimal complianceCredit = new TypeBigDecimal("compliance_credit").setFieldDescriptor(OffenseBO.COMPLIANCECREDIT.clear()).setNullable();
	private TypeString nonMoveTraf = new TypeString("non_move_traf").setFieldDescriptor(OffenseBO.NONMOVETRAF.clear()).setNullable();
	private TypeString jcSecuritySchg = new TypeString("jc_security_schg").setFieldDescriptor(OffenseBO.JCSECURITYSCHG.clear());

	public OffenseVO() {
		super(OffenseBO.TABLE, OffenseBO.SYSTEM, OffenseBO.CORIS_DISTRICT_DB.setSource("D"), OffenseBO.CORIS_JUSTICE_DB.setSource("J"), OffenseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OffenseVO(String source) {
		super(OffenseBO.TABLE, OffenseBO.SYSTEM, OffenseBO.CORIS_DISTRICT_DB.setSource("D"), OffenseBO.CORIS_JUSTICE_DB.setSource("J"), OffenseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OffenseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offsViolCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(category);
		this.getPropertyList().add(ncicCode);
		this.getPropertyList().add(aamvaCode);
		this.getPropertyList().add(drLicRpt);
		this.getPropertyList().add(bciRpt);
		this.getPropertyList().add(specProcAttr);
		this.getPropertyList().add(ftaFlag);
		this.getPropertyList().add(warrFlag);
		this.getPropertyList().add(mandAppearFlag);
		this.getPropertyList().add(defltSeverity);
		this.getPropertyList().add(suggestBail);
		this.getPropertyList().add(complianceCredit);
		this.getPropertyList().add(nonMoveTraf);
		this.getPropertyList().add(jcSecuritySchg);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OffenseBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OffenseBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}