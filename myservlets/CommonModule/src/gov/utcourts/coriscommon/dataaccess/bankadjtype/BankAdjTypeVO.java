package gov.utcourts.coriscommon.dataaccess.bankadjtype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BankAdjTypeVO extends BaseVO { 

	private TypeString type = new TypeString("type").setFieldDescriptor(BankAdjTypeBO.TYPE.clear()).addForeignKey("bank_adj","type",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(BankAdjTypeBO.DESCR.clear()).setNullable();

	public BankAdjTypeVO() {
		super(BankAdjTypeBO.TABLE, BankAdjTypeBO.SYSTEM, BankAdjTypeBO.CORIS_DISTRICT_DB.setSource("D"), BankAdjTypeBO.CORIS_JUSTICE_DB.setSource("J"), BankAdjTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAdjTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BankAdjTypeVO(String source) {
		super(BankAdjTypeBO.TABLE, BankAdjTypeBO.SYSTEM, BankAdjTypeBO.CORIS_DISTRICT_DB.setSource("D"), BankAdjTypeBO.CORIS_JUSTICE_DB.setSource("J"), BankAdjTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BankAdjTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(type);
		this.getPropertyList().add(descr);
	}

}