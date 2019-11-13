package gov.utcourts.coriscommon.dataaccess.document;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentVO extends BaseVO { 

	private TypeInteger documentNum = new TypeInteger("document_num").setFieldDescriptor(DocumentBO.DOCUMENTNUM.clear()).addForeignKey("case_me_document","document_num",true).addForeignKey("disc_notice_print","document_num",true).addForeignKey("doc_answer","document_num",false).addForeignKey("doc_dd","document_num",false).addForeignKey("doc_dv","document_num",false).addForeignKey("doc_issue","document_num",false).addForeignKey("doc_issue_party","document_num",false).addForeignKey("doc_judgment_info","document_num",false).addForeignKey("doc_motion","document_num",false).addForeignKey("doc_order","document_num",false).addForeignKey("doc_return","document_num",false).addForeignKey("doc_return_party","document_num",false).addForeignKey("domestic_modifications","document_num",false).addForeignKey("edoc","document_num",true).addForeignKey("odr_activity","odr_activity_document_num",false).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DocumentBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(DocumentBO.ENTRYDATETIME.clear());
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(DocumentBO.CREATEDATETIME.clear());
	private TypeString category = new TypeString("category").setFieldDescriptor(DocumentBO.CATEGORY.clear()).addForeignKey("document_type","category",true).setNullable();
	private TypeString title = new TypeString("title").setFieldDescriptor(DocumentBO.TITLE.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(DocumentBO.CLERKID.clear()).setNullable();
	private TypeString docSecurity = new TypeString("doc_security").setFieldDescriptor(DocumentBO.DOCSECURITY.clear()).addForeignKey("security_type","code",false);
	private TypeInteger dmDocid = new TypeInteger("dm_docid").setFieldDescriptor(DocumentBO.DMDOCID.clear()).setNullable();
	private TypeString detailCode = new TypeString("detail_code").setFieldDescriptor(DocumentBO.DETAILCODE.clear()).addForeignKey("document_type_profile","doc_detail_code",false).setNullable();

	public DocumentVO() {
		super(DocumentBO.TABLE, DocumentBO.SYSTEM, DocumentBO.CORIS_DISTRICT_DB.setSource("D"), DocumentBO.CORIS_JUSTICE_DB.setSource("J"), DocumentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentVO(String source) {
		super(DocumentBO.TABLE, DocumentBO.SYSTEM, DocumentBO.CORIS_DISTRICT_DB.setSource("D"), DocumentBO.CORIS_JUSTICE_DB.setSource("J"), DocumentBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(documentNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(category);
		this.getPropertyList().add(title);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(docSecurity);
		this.getPropertyList().add(dmDocid);
		this.getPropertyList().add(detailCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DocumentBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocumentBO.ENTRYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DocumentBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DocumentBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}