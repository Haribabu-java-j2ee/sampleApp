package gov.utcourts.coriscommon.dataaccess.roleapplicxref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class RoleapplicXrefVO extends BaseVO { 

	private TypeInteger roleapplicId = new TypeInteger("roleapplic_id").setFieldDescriptor(RoleapplicXrefBO.ROLEAPPLICID.clear()).setAsPrimaryKey();
	private TypeInteger roleId = new TypeInteger("role_id").setFieldDescriptor(RoleapplicXrefBO.ROLEID.clear()).addForeignKey("role_defn","role_id",false);
	private TypeInteger applicId = new TypeInteger("applic_id").setFieldDescriptor(RoleapplicXrefBO.APPLICID.clear()).addForeignKey("application","applic_id",false);

	public RoleapplicXrefVO() {
		super(RoleapplicXrefBO.TABLE, RoleapplicXrefBO.SYSTEM, RoleapplicXrefBO.CORIS_DISTRICT_DB.setSource("D"), RoleapplicXrefBO.CORIS_JUSTICE_DB.setSource("J"), RoleapplicXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RoleapplicXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public RoleapplicXrefVO(String source) {
		super(RoleapplicXrefBO.TABLE, RoleapplicXrefBO.SYSTEM, RoleapplicXrefBO.CORIS_DISTRICT_DB.setSource("D"), RoleapplicXrefBO.CORIS_JUSTICE_DB.setSource("J"), RoleapplicXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), RoleapplicXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(roleapplicId);
		this.getPropertyList().add(roleId);
		this.getPropertyList().add(applicId);
	}

}