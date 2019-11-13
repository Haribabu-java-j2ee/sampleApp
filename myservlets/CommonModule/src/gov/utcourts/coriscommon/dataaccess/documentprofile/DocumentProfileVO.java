package gov.utcourts.coriscommon.dataaccess.documentprofile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentProfileVO extends BaseVO { 

	private TypeString docCode = new TypeString("doc_code").setFieldDescriptor(DocumentProfileBO.DOCCODE.clear()).addForeignKey("document_fee","doc_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DocumentProfileBO.DESCR.clear()).setNullable();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(DocumentProfileBO.CASETYPE.clear()).setNullable();
	private TypeString feeFlag = new TypeString("fee_flag").setFieldDescriptor(DocumentProfileBO.FEEFLAG.clear()).setNullable();
	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(DocumentProfileBO.ACTIONTYPE.clear()).setNullable();
	private TypeString category = new TypeString("category").setFieldDescriptor(DocumentProfileBO.CATEGORY.clear()).addForeignKey("document_type","category",true).setNullable();
	private TypeString docDetailCode = new TypeString("doc_detail_code").setFieldDescriptor(DocumentProfileBO.DOCDETAILCODE.clear()).addForeignKey("document_type_profile","doc_detail_code",false).setNullable();
	private TypeString attyAssign = new TypeString("atty_assign").setFieldDescriptor(DocumentProfileBO.ATTYASSIGN.clear()).setNullable();

	public DocumentProfileVO() {
		super(DocumentProfileBO.TABLE, DocumentProfileBO.SYSTEM, DocumentProfileBO.CORIS_DISTRICT_DB.setSource("D"), DocumentProfileBO.CORIS_JUSTICE_DB.setSource("J"), DocumentProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentProfileVO(String source) {
		super(DocumentProfileBO.TABLE, DocumentProfileBO.SYSTEM, DocumentProfileBO.CORIS_DISTRICT_DB.setSource("D"), DocumentProfileBO.CORIS_JUSTICE_DB.setSource("J"), DocumentProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(feeFlag);
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(category);
		this.getPropertyList().add(docDetailCode);
		this.getPropertyList().add(attyAssign);
	}

}