package gov.utcourts.coriscommon.dataaccess.documentprocess;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentProcessVO extends BaseVO { 

	private TypeInteger documentProcessId = new TypeInteger("document_process_id").setFieldDescriptor(DocumentProcessBO.DOCUMENTPROCESSID.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DocumentProcessBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeString docDetailCode = new TypeString("doc_detail_code").setFieldDescriptor(DocumentProcessBO.DOCDETAILCODE.clear());
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(DocumentProcessBO.CREATEDATETIME.clear());

	public DocumentProcessVO() {
		super(DocumentProcessBO.TABLE, DocumentProcessBO.SYSTEM, DocumentProcessBO.CORIS_DISTRICT_DB.setSource("D"), DocumentProcessBO.CORIS_JUSTICE_DB.setSource("J"), DocumentProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentProcessVO(String source) {
		super(DocumentProcessBO.TABLE, DocumentProcessBO.SYSTEM, DocumentProcessBO.CORIS_DISTRICT_DB.setSource("D"), DocumentProcessBO.CORIS_JUSTICE_DB.setSource("J"), DocumentProcessBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentProcessBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentProcessId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(docDetailCode);
		this.getPropertyList().add(createDatetime);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocumentProcessBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocumentProcessBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}