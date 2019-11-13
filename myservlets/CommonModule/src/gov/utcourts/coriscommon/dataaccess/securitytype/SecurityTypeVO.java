package gov.utcourts.coriscommon.dataaccess.securitytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SecurityTypeVO extends BaseVO { 

	private TypeString code = new TypeString("code").setFieldDescriptor(SecurityTypeBO.CODE.clear()).addForeignKey("barcode","case_security",false).addForeignKey("barcode","doc_security",false).addForeignKey("case_me","me_security",false).addForeignKey("case_type","default_case_security",false).addForeignKey("document","doc_security",false).addForeignKey("document_type_profile","default_doc_security",false).addForeignKey("kase","case_security",false).addForeignKey("work_cal_case","case_security",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SecurityTypeBO.DESCR.clear()).setNullable();

	public SecurityTypeVO() {
		super(SecurityTypeBO.TABLE, SecurityTypeBO.SYSTEM, SecurityTypeBO.CORIS_DISTRICT_DB.setSource("D"), SecurityTypeBO.CORIS_JUSTICE_DB.setSource("J"), SecurityTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SecurityTypeVO(String source) {
		super(SecurityTypeBO.TABLE, SecurityTypeBO.SYSTEM, SecurityTypeBO.CORIS_DISTRICT_DB.setSource("D"), SecurityTypeBO.CORIS_JUSTICE_DB.setSource("J"), SecurityTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(code);
		this.getPropertyList().add(descr);
	}

}