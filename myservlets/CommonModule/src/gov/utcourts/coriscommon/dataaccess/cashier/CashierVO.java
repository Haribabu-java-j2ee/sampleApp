package gov.utcourts.coriscommon.dataaccess.cashier;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CashierVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CashierBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(CashierBO.INTJOURNALNUM.clear()).addForeignKey("journal","int_journal_num",false).setNullable();
	private TypeBigDecimal cashDrawerAmt = new TypeBigDecimal("cash_drawer_amt").setFieldDescriptor(CashierBO.CASHDRAWERAMT.clear()).setNullable();

	public CashierVO() {
		super(CashierBO.TABLE, CashierBO.SYSTEM, CashierBO.CORIS_DISTRICT_DB.setSource("D"), CashierBO.CORIS_JUSTICE_DB.setSource("J"), CashierBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CashierVO(String source) {
		super(CashierBO.TABLE, CashierBO.SYSTEM, CashierBO.CORIS_DISTRICT_DB.setSource("D"), CashierBO.CORIS_JUSTICE_DB.setSource("J"), CashierBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(cashDrawerAmt);
	}

}