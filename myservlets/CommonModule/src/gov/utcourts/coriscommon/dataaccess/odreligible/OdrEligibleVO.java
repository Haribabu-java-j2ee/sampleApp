package gov.utcourts.coriscommon.dataaccess.odreligible;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OdrEligibleVO extends BaseVO { 

	private TypeInteger odrEligibleId = new TypeInteger("odr_eligible_id").setFieldDescriptor(OdrEligibleBO.ODRELIGIBLEID.clear()).setAsPrimaryKey();
	private TypeString odrEligibleCaseType = new TypeString("odr_eligible_case_type").setFieldDescriptor(OdrEligibleBO.ODRELIGIBLECASETYPE.clear()).addForeignKey("case_type","case_type",false);
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(OdrEligibleBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(OdrEligibleBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);
	private TypeDate effectiveDate = new TypeDate("effective_date").setFieldDescriptor(OdrEligibleBO.EFFECTIVEDATE.clear());

	public OdrEligibleVO() {
		super(OdrEligibleBO.TABLE, OdrEligibleBO.SYSTEM, OdrEligibleBO.CORIS_DISTRICT_DB.setSource("D"), OdrEligibleBO.CORIS_JUSTICE_DB.setSource("J"), OdrEligibleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrEligibleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OdrEligibleVO(String source) {
		super(OdrEligibleBO.TABLE, OdrEligibleBO.SYSTEM, OdrEligibleBO.CORIS_DISTRICT_DB.setSource("D"), OdrEligibleBO.CORIS_JUSTICE_DB.setSource("J"), OdrEligibleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrEligibleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(odrEligibleId);
		this.getPropertyList().add(odrEligibleCaseType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(effectiveDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OdrEligibleBO.EFFECTIVEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(OdrEligibleBO.EFFECTIVEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}