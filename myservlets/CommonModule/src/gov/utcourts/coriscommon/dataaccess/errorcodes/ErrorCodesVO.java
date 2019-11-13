package gov.utcourts.coriscommon.dataaccess.errorcodes;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ErrorCodesVO extends BaseVO { 

	private TypeInteger errorCode = new TypeInteger("error_code").setFieldDescriptor(ErrorCodesBO.ERRORCODE.clear()).setAsPrimaryKey();
	private TypeString errorDescr = new TypeString("error_descr").setFieldDescriptor(ErrorCodesBO.ERRORDESCR.clear()).setNullable();

	public ErrorCodesVO() {
		super(ErrorCodesBO.TABLE, ErrorCodesBO.SYSTEM, ErrorCodesBO.CORIS_DISTRICT_DB.setSource("D"), ErrorCodesBO.CORIS_JUSTICE_DB.setSource("J"), ErrorCodesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ErrorCodesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ErrorCodesVO(String source) {
		super(ErrorCodesBO.TABLE, ErrorCodesBO.SYSTEM, ErrorCodesBO.CORIS_DISTRICT_DB.setSource("D"), ErrorCodesBO.CORIS_JUSTICE_DB.setSource("J"), ErrorCodesBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ErrorCodesBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(errorCode);
		this.getPropertyList().add(errorDescr);
	}

}