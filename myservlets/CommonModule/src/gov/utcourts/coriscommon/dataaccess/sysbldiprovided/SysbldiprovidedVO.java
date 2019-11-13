package gov.utcourts.coriscommon.dataaccess.sysbldiprovided;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldiprovidedVO extends BaseVO { 

	private TypeString bldiId = new TypeString("bldi_id").setFieldDescriptor(SysbldiprovidedBO.BLDIID.clear()).setAsPrimaryKey();
	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysbldiprovidedBO.BLDID.clear());

	public SysbldiprovidedVO() {
		super(SysbldiprovidedBO.TABLE, SysbldiprovidedBO.SYSTEM, SysbldiprovidedBO.CORIS_DISTRICT_DB.setSource("D"), SysbldiprovidedBO.CORIS_JUSTICE_DB.setSource("J"), SysbldiprovidedBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldiprovidedBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldiprovidedVO(String source) {
		super(SysbldiprovidedBO.TABLE, SysbldiprovidedBO.SYSTEM, SysbldiprovidedBO.CORIS_DISTRICT_DB.setSource("D"), SysbldiprovidedBO.CORIS_JUSTICE_DB.setSource("J"), SysbldiprovidedBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldiprovidedBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bldiId);
		this.getPropertyList().add(bldId);
	}

}