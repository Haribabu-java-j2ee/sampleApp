package gov.utcourts.coriscommon.dataaccess.attorney;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AttorneyVO extends BaseVO { 

	private TypeInteger barNum = new TypeInteger("bar_num").setFieldDescriptor(AttorneyBO.BARNUM.clear()).addForeignKey("atty_party","bar_num",false).addForeignKey("atty_present","bar_num",false).addForeignKey("disc_notice_print","bar_num",true).addForeignKey("gov_type","dist_atty_bar_num",true).addForeignKey("prosec_agency_atty","bar_num",false).addForeignKey("prosecutor","bar_num",false).setAsPrimaryKey();
	private TypeString barState = new TypeString("bar_state").setFieldDescriptor(AttorneyBO.BARSTATE.clear()).addForeignKey("atty_party","bar_state",false).addForeignKey("atty_present","bar_state",false).addForeignKey("disc_notice_print","bar_state",true).addForeignKey("gov_type","dist_atty_bar_state",true).addForeignKey("prosec_agency_atty","bar_state",false).addForeignKey("prosecutor","bar_state",false).setAsPrimaryKey();
	private TypeString prefix = new TypeString("prefix").setFieldDescriptor(AttorneyBO.PREFIX.clear()).setNullable();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(AttorneyBO.LASTNAME.clear()).setNullable();
	private TypeString middleInitial = new TypeString("middle_initial").setFieldDescriptor(AttorneyBO.MIDDLEINITIAL.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(AttorneyBO.FIRSTNAME.clear()).setNullable();
	private TypeString organization = new TypeString("organization").setFieldDescriptor(AttorneyBO.ORGANIZATION.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(AttorneyBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(AttorneyBO.ADDRESS2.clear()).setNullable();
	private TypeString address3 = new TypeString("address_3").setFieldDescriptor(AttorneyBO.ADDRESS3.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(AttorneyBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(AttorneyBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(AttorneyBO.ZIPCODE.clear()).setNullable();
	private TypeString country = new TypeString("country").setFieldDescriptor(AttorneyBO.COUNTRY.clear()).setNullable();
	private TypeString homePhone = new TypeString("home_phone").setFieldDescriptor(AttorneyBO.HOMEPHONE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(AttorneyBO.BUSPHONE.clear()).setNullable();
	private TypeString faxNum = new TypeString("fax_num").setFieldDescriptor(AttorneyBO.FAXNUM.clear()).setNullable();
	private TypeString cellPhone = new TypeString("cell_phone").setFieldDescriptor(AttorneyBO.CELLPHONE.clear()).setNullable();
	private TypeString barStatus = new TypeString("bar_status").setFieldDescriptor(AttorneyBO.BARSTATUS.clear()).setNullable();
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(AttorneyBO.EMAILADDRESS.clear()).setNullable();

	public AttorneyVO() {
		super(AttorneyBO.TABLE, AttorneyBO.SYSTEM, AttorneyBO.CORIS_DISTRICT_DB.setSource("D"), AttorneyBO.CORIS_JUSTICE_DB.setSource("J"), AttorneyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttorneyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AttorneyVO(String source) {
		super(AttorneyBO.TABLE, AttorneyBO.SYSTEM, AttorneyBO.CORIS_DISTRICT_DB.setSource("D"), AttorneyBO.CORIS_JUSTICE_DB.setSource("J"), AttorneyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AttorneyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(barNum);
		this.getPropertyList().add(barState);
		this.getPropertyList().add(prefix);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(middleInitial);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(organization);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(address3);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(country);
		this.getPropertyList().add(homePhone);
		this.getPropertyList().add(busPhone);
		this.getPropertyList().add(faxNum);
		this.getPropertyList().add(cellPhone);
		this.getPropertyList().add(barStatus);
		this.getPropertyList().add(emailAddress);
	}

}