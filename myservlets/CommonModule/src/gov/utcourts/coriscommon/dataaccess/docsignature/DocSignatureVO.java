package gov.utcourts.coriscommon.dataaccess.docsignature;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocSignatureVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocSignatureBO.DOCUMENTNUM.clear()).setAsPrimaryKey();
	private TypeInteger judgeCommId = new TypeInteger("judge_comm_id").setFieldDescriptor(DocSignatureBO.JUDGECOMMID.clear());
	private TypeDate signedDate = new TypeDate("signed_date").setFieldDescriptor(DocSignatureBO.SIGNEDDATE.clear());

	public DocSignatureVO() {
		super(DocSignatureBO.TABLE, DocSignatureBO.SYSTEM, DocSignatureBO.CORIS_DISTRICT_DB.setSource("D"), DocSignatureBO.CORIS_JUSTICE_DB.setSource("J"), DocSignatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocSignatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocSignatureVO(String source) {
		super(DocSignatureBO.TABLE, DocSignatureBO.SYSTEM, DocSignatureBO.CORIS_DISTRICT_DB.setSource("D"), DocSignatureBO.CORIS_JUSTICE_DB.setSource("J"), DocSignatureBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocSignatureBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(judgeCommId);
		this.getPropertyList().add(signedDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocSignatureBO.SIGNEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocSignatureBO.SIGNEDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}