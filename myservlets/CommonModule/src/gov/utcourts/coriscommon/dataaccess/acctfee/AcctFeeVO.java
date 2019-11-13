package gov.utcourts.coriscommon.dataaccess.acctfee;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctFeeVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctFeeBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString feeCode = new TypeString("fee_code").setFieldDescriptor(AcctFeeBO.FEECODE.clear()).addForeignKey("fee_type","fee_code",false).setNullable();
	private TypeDate feeEffectDate = new TypeDate("fee_effect_date").setFieldDescriptor(AcctFeeBO.FEEEFFECTDATE.clear()).addForeignKey("fee_type","last_effect_date",false);

	public AcctFeeVO() {
		super(AcctFeeBO.TABLE, AcctFeeBO.SYSTEM, AcctFeeBO.CORIS_DISTRICT_DB.setSource("D"), AcctFeeBO.CORIS_JUSTICE_DB.setSource("J"), AcctFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctFeeVO(String source) {
		super(AcctFeeBO.TABLE, AcctFeeBO.SYSTEM, AcctFeeBO.CORIS_DISTRICT_DB.setSource("D"), AcctFeeBO.CORIS_JUSTICE_DB.setSource("J"), AcctFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(feeCode);
		this.getPropertyList().add(feeEffectDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AcctFeeBO.FEEEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctFeeBO.FEEEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}