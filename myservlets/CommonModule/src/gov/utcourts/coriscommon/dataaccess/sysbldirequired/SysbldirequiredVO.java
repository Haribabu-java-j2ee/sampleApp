package gov.utcourts.coriscommon.dataaccess.sysbldirequired;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldirequiredVO extends BaseVO { 

	private TypeString bldiId = new TypeString("bldi_id").setFieldDescriptor(SysbldirequiredBO.BLDIID.clear()).setAsPrimaryKey();
	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysbldirequiredBO.BLDID.clear()).setAsPrimaryKey();

	public SysbldirequiredVO() {
		super(SysbldirequiredBO.TABLE, SysbldirequiredBO.SYSTEM, SysbldirequiredBO.CORIS_DISTRICT_DB.setSource("D"), SysbldirequiredBO.CORIS_JUSTICE_DB.setSource("J"), SysbldirequiredBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldirequiredBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldirequiredVO(String source) {
		super(SysbldirequiredBO.TABLE, SysbldirequiredBO.SYSTEM, SysbldirequiredBO.CORIS_DISTRICT_DB.setSource("D"), SysbldirequiredBO.CORIS_JUSTICE_DB.setSource("J"), SysbldirequiredBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldirequiredBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bldiId);
		this.getPropertyList().add(bldId);
	}

}