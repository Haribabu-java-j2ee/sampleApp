package gov.utcourts.coriscommon.dataaccess.systemareadefn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SystemareaDefnVO extends BaseVO { 

	private TypeInteger areaid = new TypeInteger("areaid").setFieldDescriptor(SystemareaDefnBO.AREAID.clear()).addForeignKey("pagesystem_xref","areaid",false).setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(SystemareaDefnBO.DESCRIPTION.clear());

	public SystemareaDefnVO() {
		super(SystemareaDefnBO.TABLE, SystemareaDefnBO.SYSTEM, SystemareaDefnBO.CORIS_DISTRICT_DB.setSource("D"), SystemareaDefnBO.CORIS_JUSTICE_DB.setSource("J"), SystemareaDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SystemareaDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SystemareaDefnVO(String source) {
		super(SystemareaDefnBO.TABLE, SystemareaDefnBO.SYSTEM, SystemareaDefnBO.CORIS_DISTRICT_DB.setSource("D"), SystemareaDefnBO.CORIS_JUSTICE_DB.setSource("J"), SystemareaDefnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SystemareaDefnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(areaid);
		this.getPropertyList().add(description);
	}

}