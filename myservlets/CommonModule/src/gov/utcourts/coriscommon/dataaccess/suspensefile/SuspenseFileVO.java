package gov.utcourts.coriscommon.dataaccess.suspensefile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SuspenseFileVO extends BaseVO { 

	private TypeString otn = new TypeString("otn").setFieldDescriptor(SuspenseFileBO.OTN.clear()).setNullable();
	private TypeString citNum = new TypeString("cit_num").setFieldDescriptor(SuspenseFileBO.CITNUM.clear()).setNullable();

	public SuspenseFileVO() {
		super(SuspenseFileBO.TABLE, SuspenseFileBO.SYSTEM, SuspenseFileBO.CORIS_DISTRICT_DB.setSource("D"), SuspenseFileBO.CORIS_JUSTICE_DB.setSource("J"), SuspenseFileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SuspenseFileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SuspenseFileVO(String source) {
		super(SuspenseFileBO.TABLE, SuspenseFileBO.SYSTEM, SuspenseFileBO.CORIS_DISTRICT_DB.setSource("D"), SuspenseFileBO.CORIS_JUSTICE_DB.setSource("J"), SuspenseFileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SuspenseFileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(otn);
		this.getPropertyList().add(citNum);
	}

}