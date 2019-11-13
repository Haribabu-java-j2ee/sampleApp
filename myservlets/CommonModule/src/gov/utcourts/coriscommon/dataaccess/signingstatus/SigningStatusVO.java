package gov.utcourts.coriscommon.dataaccess.signingstatus;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SigningStatusVO extends BaseVO { 

	private TypeString code = new TypeString("code").setFieldDescriptor(SigningStatusBO.CODE.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(SigningStatusBO.TYPE.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SigningStatusBO.DESCR.clear()).setNullable();
	private TypeInteger sortOrder = new TypeInteger("sort_order").setFieldDescriptor(SigningStatusBO.SORTORDER.clear()).setNullable();

	public SigningStatusVO() {
		super(SigningStatusBO.TABLE, SigningStatusBO.SYSTEM, SigningStatusBO.CORIS_DISTRICT_DB.setSource("D"), SigningStatusBO.CORIS_JUSTICE_DB.setSource("J"), SigningStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SigningStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SigningStatusVO(String source) {
		super(SigningStatusBO.TABLE, SigningStatusBO.SYSTEM, SigningStatusBO.CORIS_DISTRICT_DB.setSource("D"), SigningStatusBO.CORIS_JUSTICE_DB.setSource("J"), SigningStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SigningStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(code);
		this.getPropertyList().add(type);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(sortOrder);
	}

}