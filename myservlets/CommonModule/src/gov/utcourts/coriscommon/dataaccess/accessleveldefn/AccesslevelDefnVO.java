package gov.utcourts.coriscommon.dataaccess.accessleveldefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AccesslevelDefnVO extends BaseVO { 

	private TypeInteger accesslevelid = new TypeInteger("accesslevelid").setFieldDescriptor(AccesslevelDefnBO.ACCESSLEVELID.clear()).addForeignKey("pagerole_xref","accesslevelid",false).setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(AccesslevelDefnBO.DESCRIPTION.clear());

	public AccesslevelDefnVO() {
		super(AccesslevelDefnBO.TABLE, AccesslevelDefnBO.SYSTEM, AccesslevelDefnBO.CORIS_DISTRICT_DB.setSource("D"), AccesslevelDefnBO.CORIS_JUSTICE_DB.setSource("J"), AccesslevelDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccesslevelDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AccesslevelDefnVO(String source) {
		super(AccesslevelDefnBO.TABLE, AccesslevelDefnBO.SYSTEM, AccesslevelDefnBO.CORIS_DISTRICT_DB.setSource("D"), AccesslevelDefnBO.CORIS_JUSTICE_DB.setSource("J"), AccesslevelDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccesslevelDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(accesslevelid);
		this.getPropertyList().add(description);
	}

}