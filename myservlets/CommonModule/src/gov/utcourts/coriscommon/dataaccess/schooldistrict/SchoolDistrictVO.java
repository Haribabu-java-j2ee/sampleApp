package gov.utcourts.coriscommon.dataaccess.schooldistrict;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SchoolDistrictVO extends BaseVO { 

	private TypeInteger schoolDistrictSrl = new TypeInteger("school_district_srl").setFieldDescriptor(SchoolDistrictBO.SCHOOLDISTRICTSRL.clear()).setAsPrimaryKey();
	private TypeString schoolCode = new TypeString("school_code").setFieldDescriptor(SchoolDistrictBO.SCHOOLCODE.clear());
	private TypeString schoolType = new TypeString("school_type").setFieldDescriptor(SchoolDistrictBO.SCHOOLTYPE.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SchoolDistrictBO.REMOVEDFLAG.clear());
	private TypeString schoolShortName = new TypeString("school_short_name").setFieldDescriptor(SchoolDistrictBO.SCHOOLSHORTNAME.clear());
	private TypeString schoolName = new TypeString("school_name").setFieldDescriptor(SchoolDistrictBO.SCHOOLNAME.clear());
	private TypeString schoolAddress1 = new TypeString("school_address1").setFieldDescriptor(SchoolDistrictBO.SCHOOLADDRESS1.clear()).setNullable();
	private TypeString schoolAddress2 = new TypeString("school_address2").setFieldDescriptor(SchoolDistrictBO.SCHOOLADDRESS2.clear()).setNullable();
	private TypeString schoolCity = new TypeString("school_city").setFieldDescriptor(SchoolDistrictBO.SCHOOLCITY.clear()).setNullable();
	private TypeString schoolStateCode = new TypeString("school_state_code").setFieldDescriptor(SchoolDistrictBO.SCHOOLSTATECODE.clear()).setNullable();
	private TypeString schoolZipCode = new TypeString("school_zip_code").setFieldDescriptor(SchoolDistrictBO.SCHOOLZIPCODE.clear()).setNullable();

	public SchoolDistrictVO() {
		super(SchoolDistrictBO.TABLE, SchoolDistrictBO.SYSTEM, SchoolDistrictBO.CORIS_DISTRICT_DB.setSource("D"), SchoolDistrictBO.CORIS_JUSTICE_DB.setSource("J"), SchoolDistrictBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SchoolDistrictBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SchoolDistrictVO(String source) {
		super(SchoolDistrictBO.TABLE, SchoolDistrictBO.SYSTEM, SchoolDistrictBO.CORIS_DISTRICT_DB.setSource("D"), SchoolDistrictBO.CORIS_JUSTICE_DB.setSource("J"), SchoolDistrictBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SchoolDistrictBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(schoolDistrictSrl);
		this.getPropertyList().add(schoolCode);
		this.getPropertyList().add(schoolType);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(schoolShortName);
		this.getPropertyList().add(schoolName);
		this.getPropertyList().add(schoolAddress1);
		this.getPropertyList().add(schoolAddress2);
		this.getPropertyList().add(schoolCity);
		this.getPropertyList().add(schoolStateCode);
		this.getPropertyList().add(schoolZipCode);
	}

}