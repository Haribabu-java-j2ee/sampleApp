package gov.utcourts.coriscommon.dataaccess.sysbldobjkinds;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldobjkindsVO extends BaseVO { 

	private TypeInteger objKind = new TypeInteger("obj_kind").setFieldDescriptor(SysbldobjkindsBO.OBJKIND.clear()).setAsPrimaryKey();
	private TypeString description = new TypeString("description").setFieldDescriptor(SysbldobjkindsBO.DESCRIPTION.clear()).setNullable();
	private TypeString format = new TypeString("format").setFieldDescriptor(SysbldobjkindsBO.FORMAT.clear()).setNullable();

	public SysbldobjkindsVO() {
		super(SysbldobjkindsBO.TABLE, SysbldobjkindsBO.SYSTEM, SysbldobjkindsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjkindsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjkindsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjkindsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldobjkindsVO(String source) {
		super(SysbldobjkindsBO.TABLE, SysbldobjkindsBO.SYSTEM, SysbldobjkindsBO.CORIS_DISTRICT_DB.setSource("D"), SysbldobjkindsBO.CORIS_JUSTICE_DB.setSource("J"), SysbldobjkindsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldobjkindsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(objKind);
		this.getPropertyList().add(description);
		this.getPropertyList().add(format);
	}

}