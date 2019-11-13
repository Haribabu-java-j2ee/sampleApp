package gov.utcourts.coriscommon.dataaccess.severitytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SeverityTypeVO extends BaseVO { 

	private TypeString severityCode = new TypeString("severity_code").setFieldDescriptor(SeverityTypeBO.SEVERITYCODE.clear()).addForeignKey("charge","severity",true).addForeignKey("offense_override","deflt_severity",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SeverityTypeBO.DESCR.clear());
	private TypeInteger severityLvl = new TypeInteger("severity_lvl").setFieldDescriptor(SeverityTypeBO.SEVERITYLVL.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(SeverityTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SeverityTypeBO.REMOVEDFLAG.clear());

	public SeverityTypeVO() {
		super(SeverityTypeBO.TABLE, SeverityTypeBO.SYSTEM, SeverityTypeBO.CORIS_DISTRICT_DB.setSource("D"), SeverityTypeBO.CORIS_JUSTICE_DB.setSource("J"), SeverityTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SeverityTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SeverityTypeVO(String source) {
		super(SeverityTypeBO.TABLE, SeverityTypeBO.SYSTEM, SeverityTypeBO.CORIS_DISTRICT_DB.setSource("D"), SeverityTypeBO.CORIS_JUSTICE_DB.setSource("J"), SeverityTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SeverityTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(severityCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(severityLvl);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}