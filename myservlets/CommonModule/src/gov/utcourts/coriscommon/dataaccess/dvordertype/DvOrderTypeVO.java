package gov.utcourts.coriscommon.dataaccess.dvordertype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DvOrderTypeVO extends BaseVO { 

	private TypeString orderCode = new TypeString("order_code").setFieldDescriptor(DvOrderTypeBO.ORDERCODE.clear()).setNullable();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DvOrderTypeBO.DESCR.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(DvOrderTypeBO.REMOVEDFLAG.clear());

	public DvOrderTypeVO() {
		super(DvOrderTypeBO.TABLE, DvOrderTypeBO.SYSTEM, DvOrderTypeBO.CORIS_DISTRICT_DB.setSource("D"), DvOrderTypeBO.CORIS_JUSTICE_DB.setSource("J"), DvOrderTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DvOrderTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DvOrderTypeVO(String source) {
		super(DvOrderTypeBO.TABLE, DvOrderTypeBO.SYSTEM, DvOrderTypeBO.CORIS_DISTRICT_DB.setSource("D"), DvOrderTypeBO.CORIS_JUSTICE_DB.setSource("J"), DvOrderTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DvOrderTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(orderCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(removedFlag);
	}

}