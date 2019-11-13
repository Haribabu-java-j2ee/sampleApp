package gov.utcourts.coriscommon.dataaccess.casefee;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseFeeVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseFeeBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false).setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(CaseFeeBO.LASTEFFECTDATE.clear()).addForeignKey("fee_type","last_effect_date",false).setNullable().setAsPrimaryKey();
	private TypeBigDecimal leastAmt = new TypeBigDecimal("least_amt").setFieldDescriptor(CaseFeeBO.LEASTAMT.clear()).setNullable();
	private TypeString feeCode = new TypeString("fee_code").setFieldDescriptor(CaseFeeBO.FEECODE.clear()).addForeignKey("fee_type","fee_code",false).setNullable();

	public CaseFeeVO() {
		super(CaseFeeBO.TABLE, CaseFeeBO.SYSTEM, CaseFeeBO.CORIS_DISTRICT_DB.setSource("D"), CaseFeeBO.CORIS_JUSTICE_DB.setSource("J"), CaseFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseFeeVO(String source) {
		super(CaseFeeBO.TABLE, CaseFeeBO.SYSTEM, CaseFeeBO.CORIS_DISTRICT_DB.setSource("D"), CaseFeeBO.CORIS_JUSTICE_DB.setSource("J"), CaseFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(leastAmt);
		this.getPropertyList().add(feeCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CaseFeeBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CaseFeeBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}