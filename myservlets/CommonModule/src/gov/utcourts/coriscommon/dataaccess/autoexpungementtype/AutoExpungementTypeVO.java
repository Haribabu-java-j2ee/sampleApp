package gov.utcourts.coriscommon.dataaccess.autoexpungementtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AutoExpungementTypeVO extends BaseVO { 

	private TypeString aexpTypeId = new TypeString("aexp_type_id").setFieldDescriptor(AutoExpungementTypeBO.AEXPTYPEID.clear()).addForeignKey("auto_expungement","aexp_type_id",false).setNullable().setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(AutoExpungementTypeBO.DESCRIPTION.clear());

	public AutoExpungementTypeVO() {
		super(AutoExpungementTypeBO.TABLE, AutoExpungementTypeBO.SYSTEM, AutoExpungementTypeBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementTypeBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AutoExpungementTypeVO(String source) {
		super(AutoExpungementTypeBO.TABLE, AutoExpungementTypeBO.SYSTEM, AutoExpungementTypeBO.CORIS_DISTRICT_DB.setSource("D"), AutoExpungementTypeBO.CORIS_JUSTICE_DB.setSource("J"), AutoExpungementTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AutoExpungementTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(aexpTypeId);
		this.getPropertyList().add(description);
	}

}