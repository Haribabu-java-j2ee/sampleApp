package gov.utcourts.coriscommon.dataaccess.casemedocumenttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseMeDocumentTypeVO extends BaseVO { 

	private TypeString code = new TypeString("code").setFieldDescriptor(CaseMeDocumentTypeBO.CODE.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CaseMeDocumentTypeBO.DESCR.clear()).setNullable();

	public CaseMeDocumentTypeVO() {
		super(CaseMeDocumentTypeBO.TABLE, CaseMeDocumentTypeBO.SYSTEM, CaseMeDocumentTypeBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeDocumentTypeBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeDocumentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeDocumentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseMeDocumentTypeVO(String source) {
		super(CaseMeDocumentTypeBO.TABLE, CaseMeDocumentTypeBO.SYSTEM, CaseMeDocumentTypeBO.CORIS_DISTRICT_DB.setSource("D"), CaseMeDocumentTypeBO.CORIS_JUSTICE_DB.setSource("J"), CaseMeDocumentTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseMeDocumentTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(code);
		this.getPropertyList().add(descr);
	}

}