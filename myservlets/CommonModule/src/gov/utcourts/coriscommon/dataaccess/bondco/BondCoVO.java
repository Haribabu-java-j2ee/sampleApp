package gov.utcourts.coriscommon.dataaccess.bondco;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BondCoVO extends BaseVO { 

	private TypeString licNum = new TypeString("lic_num").setFieldDescriptor(BondCoBO.LICNUM.clear()).addForeignKey("acct_bond","bond_co_id",true).setAsPrimaryKey();
	private TypeString name = new TypeString("name").setFieldDescriptor(BondCoBO.NAME.clear());
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(BondCoBO.ADDRESS1.clear());
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(BondCoBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(BondCoBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(BondCoBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(BondCoBO.ZIPCODE.clear()).setNullable();
	private TypeString phone = new TypeString("phone").setFieldDescriptor(BondCoBO.PHONE.clear()).setNullable();
	private TypeBigDecimal amtAuth = new TypeBigDecimal("amt_auth").setFieldDescriptor(BondCoBO.AMTAUTH.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(BondCoBO.REMOVEDFLAG.clear());
	private TypeString bondLimitType = new TypeString("bond_limit_type").setFieldDescriptor(BondCoBO.BONDLIMITTYPE.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(BondCoBO.PARTYNUM.clear());

	public BondCoVO() {
		super(BondCoBO.TABLE, BondCoBO.SYSTEM, BondCoBO.CORIS_DISTRICT_DB.setSource("D"), BondCoBO.CORIS_JUSTICE_DB.setSource("J"), BondCoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BondCoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BondCoVO(String source) {
		super(BondCoBO.TABLE, BondCoBO.SYSTEM, BondCoBO.CORIS_DISTRICT_DB.setSource("D"), BondCoBO.CORIS_JUSTICE_DB.setSource("J"), BondCoBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BondCoBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(licNum);
		this.getPropertyList().add(name);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(phone);
		this.getPropertyList().add(amtAuth);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(bondLimitType);
		this.getPropertyList().add(partyNum);
	}

}