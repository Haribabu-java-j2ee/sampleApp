package gov.utcourts.coriscommon.dataaccess.postlocn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PostLocnVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PostLocnBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PostLocnBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(PostLocnBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString locnName = new TypeString("locn_name").setFieldDescriptor(PostLocnBO.LOCNNAME.clear());
	private TypeString address = new TypeString("address").setFieldDescriptor(PostLocnBO.ADDRESS.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(PostLocnBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(PostLocnBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(PostLocnBO.ZIPCODE.clear()).setNullable();

	public PostLocnVO() {
		super(PostLocnBO.TABLE, PostLocnBO.SYSTEM, PostLocnBO.CORIS_DISTRICT_DB.setSource("D"), PostLocnBO.CORIS_JUSTICE_DB.setSource("J"), PostLocnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostLocnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PostLocnVO(String source) {
		super(PostLocnBO.TABLE, PostLocnBO.SYSTEM, PostLocnBO.CORIS_DISTRICT_DB.setSource("D"), PostLocnBO.CORIS_JUSTICE_DB.setSource("J"), PostLocnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PostLocnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(locnName);
		this.getPropertyList().add(address);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
	}

}