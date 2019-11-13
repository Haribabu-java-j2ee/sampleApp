package gov.utcourts.coriscommon.dataaccess.documenttypeallow;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentTypeAllowVO extends BaseVO { 

	private TypeString docDetailCode = new TypeString("doc_detail_code").setFieldDescriptor(DocumentTypeAllowBO.DOCDETAILCODE.clear()).addForeignKey("document_type_profile","doc_detail_code",false).setNullable();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(DocumentTypeAllowBO.CASETYPE.clear()).setNullable();
	private TypeString caseCategory = new TypeString("case_category").setFieldDescriptor(DocumentTypeAllowBO.CASECATEGORY.clear()).setNullable();

	public DocumentTypeAllowVO() {
		super(DocumentTypeAllowBO.TABLE, DocumentTypeAllowBO.SYSTEM, DocumentTypeAllowBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeAllowBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeAllowBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeAllowBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentTypeAllowVO(String source) {
		super(DocumentTypeAllowBO.TABLE, DocumentTypeAllowBO.SYSTEM, DocumentTypeAllowBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeAllowBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeAllowBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeAllowBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docDetailCode);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(caseCategory);
	}

}