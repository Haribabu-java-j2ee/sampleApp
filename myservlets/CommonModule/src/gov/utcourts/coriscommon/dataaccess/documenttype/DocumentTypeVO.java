package gov.utcourts.coriscommon.dataaccess.documenttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentTypeVO extends BaseVO { 

	private TypeString category = new TypeString("category").setFieldDescriptor(DocumentTypeBO.CATEGORY.clear()).addForeignKey("document","category",true).addForeignKey("document_due_date","report_category",true).addForeignKey("document_profile","category",true).addForeignKey("document_type_profile","category",false).setNullable().setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DocumentTypeBO.DESCR.clear());

	public DocumentTypeVO() {
		super(DocumentTypeBO.TABLE, DocumentTypeBO.SYSTEM, DocumentTypeBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentTypeVO(String source) {
		super(DocumentTypeBO.TABLE, DocumentTypeBO.SYSTEM, DocumentTypeBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(category);
		this.getPropertyList().add(descr);
	}

}