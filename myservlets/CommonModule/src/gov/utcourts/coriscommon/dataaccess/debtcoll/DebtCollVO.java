package gov.utcourts.coriscommon.dataaccess.debtcoll;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DebtCollVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(DebtCollBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setNullable().setAsPrimaryKey();

	public DebtCollVO() {
		super(DebtCollBO.TABLE, DebtCollBO.SYSTEM, DebtCollBO.CORIS_DISTRICT_DB.setSource("D"), DebtCollBO.CORIS_JUSTICE_DB.setSource("J"), DebtCollBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DebtCollBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DebtCollVO(String source) {
		super(DebtCollBO.TABLE, DebtCollBO.SYSTEM, DebtCollBO.CORIS_DISTRICT_DB.setSource("D"), DebtCollBO.CORIS_JUSTICE_DB.setSource("J"), DebtCollBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DebtCollBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
	}

}