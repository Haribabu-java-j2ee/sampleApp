package gov.utcourts.coriscommon.dataaccess.roledefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class RoleDefnVO extends BaseVO { 

	private TypeInteger roleId = new TypeInteger("role_id").setFieldDescriptor(RoleDefnBO.ROLEID.clear()).addForeignKey("pagerole_xref","role_id",false).addForeignKey("roleapplic_xref","role_id",false).setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(RoleDefnBO.DESCRIPTION.clear());

	public RoleDefnVO() {
		super(RoleDefnBO.TABLE, RoleDefnBO.SYSTEM, RoleDefnBO.CORIS_DISTRICT_DB.setSource("D"), RoleDefnBO.CORIS_JUSTICE_DB.setSource("J"), RoleDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RoleDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public RoleDefnVO(String source) {
		super(RoleDefnBO.TABLE, RoleDefnBO.SYSTEM, RoleDefnBO.CORIS_DISTRICT_DB.setSource("D"), RoleDefnBO.CORIS_JUSTICE_DB.setSource("J"), RoleDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RoleDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(roleId);
		this.getPropertyList().add(description);
	}

}