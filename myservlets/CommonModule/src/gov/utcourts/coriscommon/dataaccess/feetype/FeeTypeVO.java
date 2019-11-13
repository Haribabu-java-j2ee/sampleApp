package gov.utcourts.coriscommon.dataaccess.feetype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class FeeTypeVO extends BaseVO { 

	private TypeString feeCode = new TypeString("fee_code").setFieldDescriptor(FeeTypeBO.FEECODE.clear()).addForeignKey("acct_fee","fee_code",true).addForeignKey("case_fee","fee_code",true).addForeignKey("document_fee","fee_code",true).addForeignKey("fee_dist","fee_code",false).setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(FeeTypeBO.LASTEFFECTDATE.clear()).addForeignKey("acct_fee","fee_effect_date",false).addForeignKey("case_fee","last_effect_date",true).addForeignKey("document_fee","last_effect_date",true).addForeignKey("fee_dist","last_effect_date",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(FeeTypeBO.DESCR.clear());
	private TypeString caseReqdFlag = new TypeString("case_reqd_flag").setFieldDescriptor(FeeTypeBO.CASEREQDFLAG.clear()).setNullable();
	private TypeString category = new TypeString("category").setFieldDescriptor(FeeTypeBO.CATEGORY.clear());

	public FeeTypeVO() {
		super(FeeTypeBO.TABLE, FeeTypeBO.SYSTEM, FeeTypeBO.CORIS_DISTRICT_DB.setSource("D"), FeeTypeBO.CORIS_JUSTICE_DB.setSource("J"), FeeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FeeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public FeeTypeVO(String source) {
		super(FeeTypeBO.TABLE, FeeTypeBO.SYSTEM, FeeTypeBO.CORIS_DISTRICT_DB.setSource("D"), FeeTypeBO.CORIS_JUSTICE_DB.setSource("J"), FeeTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), FeeTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(feeCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(caseReqdFlag);
		this.getPropertyList().add(category);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(FeeTypeBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(FeeTypeBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}