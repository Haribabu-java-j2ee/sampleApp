package gov.utcourts.coriscommon.dataaccess.genderdefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class GenderDefnVO extends BaseVO { 

	private TypeInteger genderId = new TypeInteger("gender_id").setFieldDescriptor(GenderDefnBO.GENDERID.clear()).setAsPrimaryKey();
	private TypeString genderCode = new TypeString("gender_code").setFieldDescriptor(GenderDefnBO.GENDERCODE.clear()).addForeignKey("party","gender",true);
	private TypeString genderDescr = new TypeString("gender_descr").setFieldDescriptor(GenderDefnBO.GENDERDESCR.clear()).setNullable();
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(GenderDefnBO.REMOVEDFLAG.clear()).setNullable();

	public GenderDefnVO() {
		super(GenderDefnBO.TABLE, GenderDefnBO.SYSTEM, GenderDefnBO.CORIS_DISTRICT_DB.setSource("D"), GenderDefnBO.CORIS_JUSTICE_DB.setSource("J"), GenderDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GenderDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public GenderDefnVO(String source) {
		super(GenderDefnBO.TABLE, GenderDefnBO.SYSTEM, GenderDefnBO.CORIS_DISTRICT_DB.setSource("D"), GenderDefnBO.CORIS_JUSTICE_DB.setSource("J"), GenderDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), GenderDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(genderId);
		this.getPropertyList().add(genderCode);
		this.getPropertyList().add(genderDescr);
		this.getPropertyList().add(removedFlag);
	}

}