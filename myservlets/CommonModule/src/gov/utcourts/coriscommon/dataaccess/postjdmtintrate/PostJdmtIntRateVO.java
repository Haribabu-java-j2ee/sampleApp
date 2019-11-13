package gov.utcourts.coriscommon.dataaccess.postjdmtintrate;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PostJdmtIntRateVO extends BaseVO { 

	private TypeDate firstEffectDate = new TypeDate("first_effect_date").setFieldDescriptor(PostJdmtIntRateBO.FIRSTEFFECTDATE.clear()).setAsPrimaryKey();
	private TypeBigDecimal interestRate = new TypeBigDecimal("interest_rate").setFieldDescriptor(PostJdmtIntRateBO.INTERESTRATE.clear()).setNullable();

	public PostJdmtIntRateVO() {
		super(PostJdmtIntRateBO.TABLE, PostJdmtIntRateBO.SYSTEM, PostJdmtIntRateBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtIntRateBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtIntRateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtIntRateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PostJdmtIntRateVO(String source) {
		super(PostJdmtIntRateBO.TABLE, PostJdmtIntRateBO.SYSTEM, PostJdmtIntRateBO.CORIS_DISTRICT_DB.setSource("D"), PostJdmtIntRateBO.CORIS_JUSTICE_DB.setSource("J"), PostJdmtIntRateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostJdmtIntRateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(firstEffectDate);
		this.getPropertyList().add(interestRate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PostJdmtIntRateBO.FIRSTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PostJdmtIntRateBO.FIRSTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}