package gov.utcourts.coriscommon.dataaccess.courtroom;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CourtroomVO extends BaseVO { 

	private TypeString courtroomCode = new TypeString("courtroom_code").setFieldDescriptor(CourtroomBO.COURTROOMCODE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CourtroomBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CourtroomBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CourtroomBO.DESCR.clear());
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(CourtroomBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(CourtroomBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(CourtroomBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(CourtroomBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(CourtroomBO.ZIPCODE.clear()).setNullable();

	public CourtroomVO() {
		super(CourtroomBO.TABLE, CourtroomBO.SYSTEM, CourtroomBO.CORIS_DISTRICT_DB.setSource("D"), CourtroomBO.CORIS_JUSTICE_DB.setSource("J"), CourtroomBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtroomBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CourtroomVO(String source) {
		super(CourtroomBO.TABLE, CourtroomBO.SYSTEM, CourtroomBO.CORIS_DISTRICT_DB.setSource("D"), CourtroomBO.CORIS_JUSTICE_DB.setSource("J"), CourtroomBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CourtroomBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(courtroomCode);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
	}

}