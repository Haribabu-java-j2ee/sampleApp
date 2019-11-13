package gov.utcourts.coriscommon.dataaccess.bondtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BondTypeVO extends BaseVO { 

	private TypeString bondCode = new TypeString("bond_code").setFieldDescriptor(BondTypeBO.BONDCODE.clear()).addForeignKey("acct_bond","bond_code",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(BondTypeBO.DESCR.clear());
	private TypeString cashFlag = new TypeString("cash_flag").setFieldDescriptor(BondTypeBO.CASHFLAG.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(BondTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(BondTypeBO.REMOVEDFLAG.clear());

	public BondTypeVO() {
		super(BondTypeBO.TABLE, BondTypeBO.SYSTEM, BondTypeBO.CORIS_DISTRICT_DB.setSource("D"), BondTypeBO.CORIS_JUSTICE_DB.setSource("J"), BondTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BondTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BondTypeVO(String source) {
		super(BondTypeBO.TABLE, BondTypeBO.SYSTEM, BondTypeBO.CORIS_DISTRICT_DB.setSource("D"), BondTypeBO.CORIS_JUSTICE_DB.setSource("J"), BondTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BondTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bondCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(cashFlag);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}