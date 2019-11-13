package gov.utcourts.coriscommon.dataaccess.documenttypeprofile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentTypeProfileVO extends BaseVO { 

	private TypeString docDetailCode = new TypeString("doc_detail_code").setFieldDescriptor(DocumentTypeProfileBO.DOCDETAILCODE.clear()).addForeignKey("document","detail_code",true).addForeignKey("document_profile","doc_detail_code",true).addForeignKey("document_type_allow","doc_detail_code",true).setAsPrimaryKey();
	private TypeString category = new TypeString("category").setFieldDescriptor(DocumentTypeProfileBO.CATEGORY.clear()).addForeignKey("document_type","category",true);
	private TypeString ddlbEntry = new TypeString("ddlb_entry").setFieldDescriptor(DocumentTypeProfileBO.DDLBENTRY.clear());
	private TypeString ddlbType = new TypeString("ddlb_type").setFieldDescriptor(DocumentTypeProfileBO.DDLBTYPE.clear());
	private TypeString batchDoc = new TypeString("batch_doc").setFieldDescriptor(DocumentTypeProfileBO.BATCHDOC.clear());
	private TypeString docFormCode = new TypeString("doc_form_code").setFieldDescriptor(DocumentTypeProfileBO.DOCFORMCODE.clear());
	private TypeString defaultDocSecurity = new TypeString("default_doc_security").setFieldDescriptor(DocumentTypeProfileBO.DEFAULTDOCSECURITY.clear()).addForeignKey("security_type","code",false);
	private TypeString userEntry = new TypeString("user_entry").setFieldDescriptor(DocumentTypeProfileBO.USERENTRY.clear());
	private TypeString mleTitle = new TypeString("mle_title").setFieldDescriptor(DocumentTypeProfileBO.MLETITLE.clear()).setNullable();

	public DocumentTypeProfileVO() {
		super(DocumentTypeProfileBO.TABLE, DocumentTypeProfileBO.SYSTEM, DocumentTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentTypeProfileVO(String source) {
		super(DocumentTypeProfileBO.TABLE, DocumentTypeProfileBO.SYSTEM, DocumentTypeProfileBO.CORIS_DISTRICT_DB.setSource("D"), DocumentTypeProfileBO.CORIS_JUSTICE_DB.setSource("J"), DocumentTypeProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentTypeProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(docDetailCode);
		this.getPropertyList().add(category);
		this.getPropertyList().add(ddlbEntry);
		this.getPropertyList().add(ddlbType);
		this.getPropertyList().add(batchDoc);
		this.getPropertyList().add(docFormCode);
		this.getPropertyList().add(defaultDocSecurity);
		this.getPropertyList().add(userEntry);
		this.getPropertyList().add(mleTitle);
	}

}