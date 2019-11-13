package gov.utcourts.coriscommon.dataaccess.maillbl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class MailLblVO extends BaseVO { 

	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(MailLblBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(MailLblBO.TYPE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(MailLblBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(MailLblBO.LASTNAME.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(MailLblBO.FIRSTNAME.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(MailLblBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(MailLblBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(MailLblBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(MailLblBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(MailLblBO.ZIPCODE.clear()).setNullable();

	public MailLblVO() {
		super(MailLblBO.TABLE, MailLblBO.SYSTEM, MailLblBO.CORIS_DISTRICT_DB.setSource("D"), MailLblBO.CORIS_JUSTICE_DB.setSource("J"), MailLblBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MailLblBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public MailLblVO(String source) {
		super(MailLblBO.TABLE, MailLblBO.SYSTEM, MailLblBO.CORIS_DISTRICT_DB.setSource("D"), MailLblBO.CORIS_JUSTICE_DB.setSource("J"), MailLblBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), MailLblBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(type);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
	}

}