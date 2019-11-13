package gov.utcourts.coriscommon.dataaccess.sysbldregistered;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SysbldregisteredVO extends BaseVO { 

	private TypeString bldId = new TypeString("bld_id").setFieldDescriptor(SysbldregisteredBO.BLDID.clear()).setAsPrimaryKey();

	public SysbldregisteredVO() {
		super(SysbldregisteredBO.TABLE, SysbldregisteredBO.SYSTEM, SysbldregisteredBO.CORIS_DISTRICT_DB.setSource("D"), SysbldregisteredBO.CORIS_JUSTICE_DB.setSource("J"), SysbldregisteredBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldregisteredBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SysbldregisteredVO(String source) {
		super(SysbldregisteredBO.TABLE, SysbldregisteredBO.SYSTEM, SysbldregisteredBO.CORIS_DISTRICT_DB.setSource("D"), SysbldregisteredBO.CORIS_JUSTICE_DB.setSource("J"), SysbldregisteredBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SysbldregisteredBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bldId);
	}

}