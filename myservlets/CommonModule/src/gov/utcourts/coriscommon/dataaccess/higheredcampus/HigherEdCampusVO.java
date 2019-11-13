package gov.utcourts.coriscommon.dataaccess.higheredcampus;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class HigherEdCampusVO extends BaseVO { 

	private TypeString campusCode = new TypeString("campus_code").setFieldDescriptor(HigherEdCampusBO.CAMPUSCODE.clear()).setAsPrimaryKey();
	private TypeString campusName = new TypeString("campus_name").setFieldDescriptor(HigherEdCampusBO.CAMPUSNAME.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(HigherEdCampusBO.REMOVEDFLAG.clear()).setNullable();

	public HigherEdCampusVO() {
		super(HigherEdCampusBO.TABLE, HigherEdCampusBO.SYSTEM, HigherEdCampusBO.CORIS_DISTRICT_DB.setSource("D"), HigherEdCampusBO.CORIS_JUSTICE_DB.setSource("J"), HigherEdCampusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HigherEdCampusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public HigherEdCampusVO(String source) {
		super(HigherEdCampusBO.TABLE, HigherEdCampusBO.SYSTEM, HigherEdCampusBO.CORIS_DISTRICT_DB.setSource("D"), HigherEdCampusBO.CORIS_JUSTICE_DB.setSource("J"), HigherEdCampusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), HigherEdCampusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(campusCode);
		this.getPropertyList().add(campusName);
		this.getPropertyList().add(removedFlag);
	}

}