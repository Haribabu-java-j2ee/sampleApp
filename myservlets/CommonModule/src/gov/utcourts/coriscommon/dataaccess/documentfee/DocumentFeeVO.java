package gov.utcourts.coriscommon.dataaccess.documentfee;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentFeeVO extends BaseVO { 

	private TypeString docCode = new TypeString("doc_code").setFieldDescriptor(DocumentFeeBO.DOCCODE.clear()).addForeignKey("document_profile","doc_code",false).setAsPrimaryKey();
	private TypeDate lastEffectDate = new TypeDate("last_effect_date").setFieldDescriptor(DocumentFeeBO.LASTEFFECTDATE.clear()).addForeignKey("fee_type","last_effect_date",false).setNullable().setAsPrimaryKey();
	private TypeBigDecimal leastAmt = new TypeBigDecimal("least_amt").setFieldDescriptor(DocumentFeeBO.LEASTAMT.clear()).setNullable();
	private TypeString feeCode = new TypeString("fee_code").setFieldDescriptor(DocumentFeeBO.FEECODE.clear()).addForeignKey("fee_type","fee_code",false).setNullable();

	public DocumentFeeVO() {
		super(DocumentFeeBO.TABLE, DocumentFeeBO.SYSTEM, DocumentFeeBO.CORIS_DISTRICT_DB.setSource("D"), DocumentFeeBO.CORIS_JUSTICE_DB.setSource("J"), DocumentFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentFeeVO(String source) {
		super(DocumentFeeBO.TABLE, DocumentFeeBO.SYSTEM, DocumentFeeBO.CORIS_DISTRICT_DB.setSource("D"), DocumentFeeBO.CORIS_JUSTICE_DB.setSource("J"), DocumentFeeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentFeeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docCode);
		this.getPropertyList().add(lastEffectDate);
		this.getPropertyList().add(leastAmt);
		this.getPropertyList().add(feeCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocumentFeeBO.LASTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocumentFeeBO.LASTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}