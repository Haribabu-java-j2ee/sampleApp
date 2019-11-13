package gov.utcourts.coriscommon.dataaccess.documentpriority;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocumentPriorityVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(DocumentPriorityBO.CASETYPE.clear()).setAsPrimaryKey();
	private TypeString docCode = new TypeString("doc_code").setFieldDescriptor(DocumentPriorityBO.DOCCODE.clear()).setAsPrimaryKey();
	private TypeString docPriority = new TypeString("doc_priority").setFieldDescriptor(DocumentPriorityBO.DOCPRIORITY.clear());

	public DocumentPriorityVO() {
		super(DocumentPriorityBO.TABLE, DocumentPriorityBO.SYSTEM, DocumentPriorityBO.CORIS_DISTRICT_DB.setSource("D"), DocumentPriorityBO.CORIS_JUSTICE_DB.setSource("J"), DocumentPriorityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentPriorityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocumentPriorityVO(String source) {
		super(DocumentPriorityBO.TABLE, DocumentPriorityBO.SYSTEM, DocumentPriorityBO.CORIS_DISTRICT_DB.setSource("D"), DocumentPriorityBO.CORIS_JUSTICE_DB.setSource("J"), DocumentPriorityBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocumentPriorityBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(docCode);
		this.getPropertyList().add(docPriority);
	}

}