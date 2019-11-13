package gov.utcourts.coriscommon.dataaccess.pointofsale;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PointOfSaleVO extends BaseVO { 

	private TypeString ipAddress = new TypeString("ip_address").setFieldDescriptor(PointOfSaleBO.IPADDRESS.clear()).setNullable().setAsPrimaryKey();
	private TypeString machineName = new TypeString("machine_name").setFieldDescriptor(PointOfSaleBO.MACHINENAME.clear());

	public PointOfSaleVO() {
		super(PointOfSaleBO.TABLE, PointOfSaleBO.SYSTEM, PointOfSaleBO.CORIS_DISTRICT_DB.setSource("D"), PointOfSaleBO.CORIS_JUSTICE_DB.setSource("J"), PointOfSaleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PointOfSaleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PointOfSaleVO(String source) {
		super(PointOfSaleBO.TABLE, PointOfSaleBO.SYSTEM, PointOfSaleBO.CORIS_DISTRICT_DB.setSource("D"), PointOfSaleBO.CORIS_JUSTICE_DB.setSource("J"), PointOfSaleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PointOfSaleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(ipAddress);
		this.getPropertyList().add(machineName);
	}

}