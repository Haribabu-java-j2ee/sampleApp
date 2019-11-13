package gov.utcourts.coriscommon.dataaccess.smoter;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeBoolean;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeLong;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;
import gov.utcourts.courtscommon.dataaccess.types.TypeText;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SmotErVO extends BaseVO { 

	private TypeLong offenseId = new TypeLong("offense_id").setFieldDescriptor(SmotErBO.OFFENSEID.clear());
	private TypeString violationCode = new TypeString("violation_code").setFieldDescriptor(SmotErBO.VIOLATIONCODE.clear()).setNullable();
	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(SmotErBO.GOVCODE.clear()).setNullable();
	private TypeDate startDate = new TypeDate("start_date").setFieldDescriptor(SmotErBO.STARTDATE.clear()).setNullable();
	private TypeDate repealDate = new TypeDate("repeal_date").setFieldDescriptor(SmotErBO.REPEALDATE.clear()).setNullable();
	private TypeString shortDesc = new TypeString("short_desc").setFieldDescriptor(SmotErBO.SHORTDESC.clear()).setNullable();
	private TypeString longDesc = new TypeString("long_desc").setFieldDescriptor(SmotErBO.LONGDESC.clear()).setNullable();
	private TypeString category = new TypeString("category").setFieldDescriptor(SmotErBO.CATEGORY.clear()).setNullable();
	private TypeString ncicCode = new TypeString("ncic_code").setFieldDescriptor(SmotErBO.NCICCODE.clear()).setNullable();
	private TypeString aamvaCode = new TypeString("aamva_code").setFieldDescriptor(SmotErBO.AAMVACODE.clear()).setNullable();
	private TypeString dlReportable = new TypeString("dl_reportable").setFieldDescriptor(SmotErBO.DLREPORTABLE.clear()).setNullable();
	private TypeBoolean bciReportable = new TypeBoolean("bci_reportable").setFieldDescriptor(SmotErBO.BCIREPORTABLE.clear()).setNullable();
	private TypeBoolean ftaFlag = new TypeBoolean("fta_flag").setFieldDescriptor(SmotErBO.FTAFLAG.clear()).setNullable();
	private TypeBoolean warrantFlag = new TypeBoolean("warrant_flag").setFieldDescriptor(SmotErBO.WARRANTFLAG.clear()).setNullable();
	private TypeBoolean mandAppearFlag = new TypeBoolean("mand_appear_flag").setFieldDescriptor(SmotErBO.MANDAPPEARFLAG.clear()).setNullable();
	private TypeString specProcessAttr = new TypeString("spec_process_attr").setFieldDescriptor(SmotErBO.SPECPROCESSATTR.clear()).setNullable();
	private TypeString defaultSeverity = new TypeString("default_severity").setFieldDescriptor(SmotErBO.DEFAULTSEVERITY.clear()).setNullable();
	private TypeBoolean nonMovingTraffic = new TypeBoolean("non_moving_traffic").setFieldDescriptor(SmotErBO.NONMOVINGTRAFFIC.clear()).setNullable();
	private TypeBigDecimal suggestedBail = new TypeBigDecimal("suggested_bail").setFieldDescriptor(SmotErBO.SUGGESTEDBAIL.clear()).setNullable();
	private TypeBigDecimal complianceCredit = new TypeBigDecimal("compliance_credit").setFieldDescriptor(SmotErBO.COMPLIANCECREDIT.clear()).setNullable();
	private TypeBoolean jcSecuritySrchg = new TypeBoolean("jc_security_srchg").setFieldDescriptor(SmotErBO.JCSECURITYSRCHG.clear()).setNullable();
	private TypeString statuteCode = new TypeString("statute_code").setFieldDescriptor(SmotErBO.STATUTECODE.clear()).setNullable();
	private TypeDate insertDate = new TypeDate("insert_date").setFieldDescriptor(SmotErBO.INSERTDATE.clear()).setNullable();
	private TypeString insertBy = new TypeString("insert_by").setFieldDescriptor(SmotErBO.INSERTBY.clear()).setNullable();
	private TypeLong pimId = new TypeLong("pim_id").setFieldDescriptor(SmotErBO.PIMID.clear()).setNullable();
	private TypeString chargeType = new TypeString("charge_type").setFieldDescriptor(SmotErBO.CHARGETYPE.clear()).setNullable();
	private TypeBoolean dnaReq = new TypeBoolean("dna_req").setFieldDescriptor(SmotErBO.DNAREQ.clear()).setNullable();
	private TypeString offsViolationCode = new TypeString("offs_violation_code").setFieldDescriptor(SmotErBO.OFFSVIOLATIONCODE.clear()).setNullable();
	private TypeDate lastModifiedDate = new TypeDate("last_modified_date").setFieldDescriptor(SmotErBO.LASTMODIFIEDDATE.clear()).setNullable();
	private TypeString lastModifiedUser = new TypeString("last_modified_user").setFieldDescriptor(SmotErBO.LASTMODIFIEDUSER.clear()).setNullable();
	private TypeString lastModifiedAgency = new TypeString("last_modified_agency").setFieldDescriptor(SmotErBO.LASTMODIFIEDAGENCY.clear()).setNullable();
	private TypeString lastModifiedReason = new TypeString("last_modified_reason").setFieldDescriptor(SmotErBO.LASTMODIFIEDREASON.clear()).setNullable();
	private TypeText chargingLanguage = new TypeText("charging_language").setFieldDescriptor(SmotErBO.CHARGINGLANGUAGE.clear()).setNullable();
	private TypeString udcOffTypeCode = new TypeString("udc_off_type_code").setFieldDescriptor(SmotErBO.UDCOFFTYPECODE.clear()).setNullable();
	private TypeBoolean fullTerminationFlag = new TypeBoolean("full_termination_flag").setFieldDescriptor(SmotErBO.FULLTERMINATIONFLAG.clear()).setNullable();
	private TypeDate fullTerminationEffDt = new TypeDate("full_termination_eff_dt").setFieldDescriptor(SmotErBO.FULLTERMINATIONEFFDT.clear()).setNullable();
	private TypeBoolean odnaCollectAtArrest = new TypeBoolean("odna_collect_at_arrest").setFieldDescriptor(SmotErBO.ODNACOLLECTATARREST.clear()).setNullable();
	private TypeBoolean odnaCollectAtConviction = new TypeBoolean("odna_collect_at_conviction").setFieldDescriptor(SmotErBO.ODNACOLLECTATCONVICTION.clear()).setNullable();

	public SmotErVO() {
		super(SmotErBO.TABLE, SmotErBO.SYSTEM, SmotErBO.CORIS_DISTRICT_DB.setSource("D"), SmotErBO.CORIS_JUSTICE_DB.setSource("J"), SmotErBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SmotErBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SmotErVO(String source) {
		super(SmotErBO.TABLE, SmotErBO.SYSTEM, SmotErBO.CORIS_DISTRICT_DB.setSource("D"), SmotErBO.CORIS_JUSTICE_DB.setSource("J"), SmotErBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SmotErBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(offenseId);
		this.getPropertyList().add(violationCode);
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(startDate);
		this.getPropertyList().add(repealDate);
		this.getPropertyList().add(shortDesc);
		this.getPropertyList().add(longDesc);
		this.getPropertyList().add(category);
		this.getPropertyList().add(ncicCode);
		this.getPropertyList().add(aamvaCode);
		this.getPropertyList().add(dlReportable);
		this.getPropertyList().add(bciReportable);
		this.getPropertyList().add(ftaFlag);
		this.getPropertyList().add(warrantFlag);
		this.getPropertyList().add(mandAppearFlag);
		this.getPropertyList().add(specProcessAttr);
		this.getPropertyList().add(defaultSeverity);
		this.getPropertyList().add(nonMovingTraffic);
		this.getPropertyList().add(suggestedBail);
		this.getPropertyList().add(complianceCredit);
		this.getPropertyList().add(jcSecuritySrchg);
		this.getPropertyList().add(statuteCode);
		this.getPropertyList().add(insertDate);
		this.getPropertyList().add(insertBy);
		this.getPropertyList().add(pimId);
		this.getPropertyList().add(chargeType);
		this.getPropertyList().add(dnaReq);
		this.getPropertyList().add(offsViolationCode);
		this.getPropertyList().add(lastModifiedDate);
		this.getPropertyList().add(lastModifiedUser);
		this.getPropertyList().add(lastModifiedAgency);
		this.getPropertyList().add(lastModifiedReason);
		this.getPropertyList().add(chargingLanguage);
		this.getPropertyList().add(udcOffTypeCode);
		this.getPropertyList().add(fullTerminationFlag);
		this.getPropertyList().add(fullTerminationEffDt);
		this.getPropertyList().add(odnaCollectAtArrest);
		this.getPropertyList().add(odnaCollectAtConviction);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SmotErBO.STARTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SmotErBO.STARTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SmotErBO.REPEALDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SmotErBO.REPEALDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SmotErBO.INSERTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SmotErBO.INSERTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SmotErBO.LASTMODIFIEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SmotErBO.LASTMODIFIEDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SmotErBO.FULLTERMINATIONEFFDT.getPosition())).setDateFormat((DateFormat) super.getAttribute(SmotErBO.FULLTERMINATIONEFFDT.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}