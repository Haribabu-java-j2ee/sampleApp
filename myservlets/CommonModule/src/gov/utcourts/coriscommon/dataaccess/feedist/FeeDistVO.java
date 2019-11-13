package gov.utcourts.coriscommon.dataaccess.feedist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class FeeDistVO extends BaseVO { 

	private TypeString feeCode = new TypeString("fee_code").setFieldDescriptor(FeeDistBO.FEECODE.clear()).addForeignKey("fee_type","fee_code",false).setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(FeeDistBO.LASTEFFECTDATE.clear()).addForeignKey("fee_type","last_effect_date",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(FeeDistBO.DISTCODE.clear()).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeBigDecimal amt = new TypeBigDecimal("amt").setFieldDescriptor(FeeDistBO.AMT.clear());
	private TypeString surchargeCode = new TypeString("surcharge_code").setFieldDescriptor(FeeDistBO.SURCHARGECODE.clear()).setNullable();

	public FeeDistVO() {
		super(FeeDistBO.TABLE, FeeDistBO.SYSTEM, FeeDistBO.CORIS_DISTRICT_DB.setSource("D"), FeeDistBO.CORIS_JUSTICE_DB.setSource("J"), FeeDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FeeDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public FeeDistVO(String source) {
		super(FeeDistBO.TABLE, FeeDistBO.SYSTEM, FeeDistBO.CORIS_DISTRICT_DB.setSource("D"), FeeDistBO.CORIS_JUSTICE_DB.setSource("J"), FeeDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FeeDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(feeCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(amt);
		this.getPropertyList().add(surchargeCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(FeeDistBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(FeeDistBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}