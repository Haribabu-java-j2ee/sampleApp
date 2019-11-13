package gov.utcourts.coriscommon.dataaccess.agency;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AgencyVO extends BaseVO { 

	private TypeString agencyCode = new TypeString("agency_code").setFieldDescriptor(AgencyBO.AGENCYCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(AgencyBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(AgencyBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeString agencyType = new TypeString("agency_type").setFieldDescriptor(AgencyBO.AGENCYTYPE.clear());
	private TypeString agencyName = new TypeString("agency_name").setFieldDescriptor(AgencyBO.AGENCYNAME.clear()).setNullable();
	private TypeString agencyAddress1 = new TypeString("agency_address_1").setFieldDescriptor(AgencyBO.AGENCYADDRESS1.clear()).setNullable();
	private TypeString agencyAddress2 = new TypeString("agency_address_2").setFieldDescriptor(AgencyBO.AGENCYADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(AgencyBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(AgencyBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(AgencyBO.ZIPCODE.clear()).setNullable();
	private TypeString phone = new TypeString("phone").setFieldDescriptor(AgencyBO.PHONE.clear()).setNullable();
	private TypeString agencyComment = new TypeString("agency_comment").setFieldDescriptor(AgencyBO.AGENCYCOMMENT.clear()).setNullable();
	private TypeInteger preference = new TypeInteger("preference").setFieldDescriptor(AgencyBO.PREFERENCE.clear()).setNullable();
	private TypeString sentTrackCode = new TypeString("sent_track_code").setFieldDescriptor(AgencyBO.SENTTRACKCODE.clear()).setNullable();

	public AgencyVO() {
		super(AgencyBO.TABLE, AgencyBO.SYSTEM, AgencyBO.CORIS_DISTRICT_DB.setSource("D"), AgencyBO.CORIS_JUSTICE_DB.setSource("J"), AgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AgencyVO(String source) {
		super(AgencyBO.TABLE, AgencyBO.SYSTEM, AgencyBO.CORIS_DISTRICT_DB.setSource("D"), AgencyBO.CORIS_JUSTICE_DB.setSource("J"), AgencyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AgencyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(agencyCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(agencyType);
		this.getPropertyList().add(agencyName);
		this.getPropertyList().add(agencyAddress1);
		this.getPropertyList().add(agencyAddress2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(phone);
		this.getPropertyList().add(agencyComment);
		this.getPropertyList().add(preference);
		this.getPropertyList().add(sentTrackCode);
	}

}