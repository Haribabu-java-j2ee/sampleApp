package gov.utcourts.coriscommon.dataaccess.custodylocation;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CustodyLocationVO extends BaseVO { 

	private TypeString custodyLocationCode = new TypeString("custody_location_code").setFieldDescriptor(CustodyLocationBO.CUSTODYLOCATIONCODE.clear());
	private TypeString custodyTypeCode = new TypeString("custody_type_code").setFieldDescriptor(CustodyLocationBO.CUSTODYTYPECODE.clear());
	private TypeString custodyLocationName = new TypeString("custody_location_name").setFieldDescriptor(CustodyLocationBO.CUSTODYLOCATIONNAME.clear());
	private TypeString custodyAddress1 = new TypeString("custody_address_1").setFieldDescriptor(CustodyLocationBO.CUSTODYADDRESS1.clear()).setNullable();
	private TypeString custodyAddress2 = new TypeString("custody_address_2").setFieldDescriptor(CustodyLocationBO.CUSTODYADDRESS2.clear()).setNullable();
	private TypeString custodyCity = new TypeString("custody_city").setFieldDescriptor(CustodyLocationBO.CUSTODYCITY.clear()).setNullable();
	private TypeString custodyStateCode = new TypeString("custody_state_code").setFieldDescriptor(CustodyLocationBO.CUSTODYSTATECODE.clear()).setNullable();
	private TypeString custodyZipCode = new TypeString("custody_zip_code").setFieldDescriptor(CustodyLocationBO.CUSTODYZIPCODE.clear()).setNullable();
	private TypeString custodyPhone = new TypeString("custody_phone").setFieldDescriptor(CustodyLocationBO.CUSTODYPHONE.clear()).setNullable();
	private TypeString custodyEmail = new TypeString("custody_email").setFieldDescriptor(CustodyLocationBO.CUSTODYEMAIL.clear()).setNullable();
	private TypeString custodyRemovedFlag = new TypeString("custody_removed_flag").setFieldDescriptor(CustodyLocationBO.CUSTODYREMOVEDFLAG.clear()).setNullable();
	private TypeString oriNum = new TypeString("ori_num").setFieldDescriptor(CustodyLocationBO.ORINUM.clear()).setNullable();

	public CustodyLocationVO() {
		super(CustodyLocationBO.TABLE, CustodyLocationBO.SYSTEM, CustodyLocationBO.CORIS_DISTRICT_DB.setSource("D"), CustodyLocationBO.CORIS_JUSTICE_DB.setSource("J"), CustodyLocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyLocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CustodyLocationVO(String source) {
		super(CustodyLocationBO.TABLE, CustodyLocationBO.SYSTEM, CustodyLocationBO.CORIS_DISTRICT_DB.setSource("D"), CustodyLocationBO.CORIS_JUSTICE_DB.setSource("J"), CustodyLocationBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyLocationBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(custodyLocationCode);
		this.getPropertyList().add(custodyTypeCode);
		this.getPropertyList().add(custodyLocationName);
		this.getPropertyList().add(custodyAddress1);
		this.getPropertyList().add(custodyAddress2);
		this.getPropertyList().add(custodyCity);
		this.getPropertyList().add(custodyStateCode);
		this.getPropertyList().add(custodyZipCode);
		this.getPropertyList().add(custodyPhone);
		this.getPropertyList().add(custodyEmail);
		this.getPropertyList().add(custodyRemovedFlag);
		this.getPropertyList().add(oriNum);
	}

}