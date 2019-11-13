package gov.utcourts.coriscommon.dataaccess.autoexpungementstatus;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementStatusVO extends BaseVO { 

	private TypeString aexpStatusId = new TypeString("aexp_status_id").setFieldDescriptor(AutoExpungementStatusBO.AEXPSTATUSID.clear()).addForeignKey("auto_expungement","aexp_status_id",false).setNullable().setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(AutoExpungementStatusBO.DESCRIPTION.clear());

	public AutoExpungementStatusVO() {
		super(AutoExpungementStatusBO.TABLE, AutoExpungementStatusBO.SYSTEM, AutoExpungementStatusBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementStatusBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementStatusVO(String source) {
		super(AutoExpungementStatusBO.TABLE, AutoExpungementStatusBO.SYSTEM, AutoExpungementStatusBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementStatusBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementStatusBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementStatusBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(aexpStatusId);
		this.getPropertyList().add(description);
	}

}