package gov.utcourts.coriscommon.dataaccess.securitylogname;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SecurityLognameVO extends BaseVO { 

	private TypeInteger securityLognameId = new TypeInteger("security_logname_id").setFieldDescriptor(SecurityLognameBO.SECURITYLOGNAMEID.clear()).setAsPrimaryKey();
	private TypeString logname = new TypeString("logname").setFieldDescriptor(SecurityLognameBO.LOGNAME.clear()).setNullable();
	private TypeString securityType = new TypeString("security_type").setFieldDescriptor(SecurityLognameBO.SECURITYTYPE.clear()).setNullable();

	public SecurityLognameVO() {
		super(SecurityLognameBO.TABLE, SecurityLognameBO.SYSTEM, SecurityLognameBO.CORIS_DISTRICT_DB.setSource("D"), SecurityLognameBO.CORIS_JUSTICE_DB.setSource("J"), SecurityLognameBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityLognameBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SecurityLognameVO(String source) {
		super(SecurityLognameBO.TABLE, SecurityLognameBO.SYSTEM, SecurityLognameBO.CORIS_DISTRICT_DB.setSource("D"), SecurityLognameBO.CORIS_JUSTICE_DB.setSource("J"), SecurityLognameBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SecurityLognameBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(securityLognameId);
		this.getPropertyList().add(logname);
		this.getPropertyList().add(securityType);
	}

}