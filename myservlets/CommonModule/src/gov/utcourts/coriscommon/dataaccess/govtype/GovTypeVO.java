package gov.utcourts.coriscommon.dataaccess.govtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class GovTypeVO extends BaseVO { 

	private TypeString govCode = new TypeString("gov_code").setFieldDescriptor(GovTypeBO.GOVCODE.clear()).addForeignKey("crim_case","prosec_agency",true).addForeignKey("gov_location","gov_code",true).addForeignKey("prosec_agency_atty","gov_code",false).addForeignKey("synonym","gov_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(GovTypeBO.DESCR.clear());
	private TypeString ncicId = new TypeString("ncic_id").setFieldDescriptor(GovTypeBO.NCICID.clear()).setNullable();
	private TypeString jurisLvl = new TypeString("juris_lvl").setFieldDescriptor(GovTypeBO.JURISLVL.clear()).setNullable();
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(GovTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(GovTypeBO.REMOVEDFLAG.clear());
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(GovTypeBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(GovTypeBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(GovTypeBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(GovTypeBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(GovTypeBO.ZIPCODE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(GovTypeBO.BUSPHONE.clear()).setNullable();
	private TypeString faxNum = new TypeString("fax_num").setFieldDescriptor(GovTypeBO.FAXNUM.clear()).setNullable();
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(GovTypeBO.EMAILADDRESS.clear()).setNullable();
	private TypeInteger distAttyBarNum = new TypeInteger("dist_atty_bar_num").setFieldDescriptor(GovTypeBO.DISTATTYBARNUM.clear()).addForeignKey("attorney","bar_num",false).setNullable();
	private TypeString distAttyBarState = new TypeString("dist_atty_bar_state").setFieldDescriptor(GovTypeBO.DISTATTYBARSTATE.clear()).addForeignKey("attorney","bar_state",false).setNullable();

	public GovTypeVO() {
		super(GovTypeBO.TABLE, GovTypeBO.SYSTEM, GovTypeBO.CORIS_DISTRICT_DB.setSource("D"), GovTypeBO.CORIS_JUSTICE_DB.setSource("J"), GovTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GovTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public GovTypeVO(String source) {
		super(GovTypeBO.TABLE, GovTypeBO.SYSTEM, GovTypeBO.CORIS_DISTRICT_DB.setSource("D"), GovTypeBO.CORIS_JUSTICE_DB.setSource("J"), GovTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GovTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(govCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(ncicId);
		this.getPropertyList().add(jurisLvl);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(busPhone);
		this.getPropertyList().add(faxNum);
		this.getPropertyList().add(emailAddress);
		this.getPropertyList().add(distAttyBarNum);
		this.getPropertyList().add(distAttyBarState);
	}

}