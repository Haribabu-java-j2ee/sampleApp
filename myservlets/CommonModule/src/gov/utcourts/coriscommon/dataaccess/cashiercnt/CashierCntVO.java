package gov.utcourts.coriscommon.dataaccess.cashiercnt;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CashierCntVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CashierCntBO.USERIDSRL.clear()).addForeignKey("cashier_cnt_detl","userid_srl",false).addForeignKey("personnel","userid_srl",false).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(CashierCntBO.INTJOURNALNUM.clear()).addForeignKey("cashier_cnt_detl","int_journal_num",false).addForeignKey("journal","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(CashierCntBO.SEQ.clear()).addForeignKey("cashier_cnt_detl","seq",false).setAsPrimaryKey();
	private TypeBigDecimal cashTot = new TypeBigDecimal("cash_tot").setFieldDescriptor(CashierCntBO.CASHTOT.clear()).setNullable();
	private TypeBigDecimal revnCheckTot = new TypeBigDecimal("revn_check_tot").setFieldDescriptor(CashierCntBO.REVNCHECKTOT.clear()).setNullable();
	private TypeBigDecimal revnCcTot = new TypeBigDecimal("revn_cc_tot").setFieldDescriptor(CashierCntBO.REVNCCTOT.clear()).setNullable();
	private TypeBigDecimal trustCheckTot = new TypeBigDecimal("trust_check_tot").setFieldDescriptor(CashierCntBO.TRUSTCHECKTOT.clear()).setNullable();
	private TypeBigDecimal trustCcTot = new TypeBigDecimal("trust_cc_tot").setFieldDescriptor(CashierCntBO.TRUSTCCTOT.clear()).setNullable();
	private TypeBigDecimal drawerBeginAmt = new TypeBigDecimal("drawer_begin_amt").setFieldDescriptor(CashierCntBO.DRAWERBEGINAMT.clear());
	private TypeInteger cntSupervisor = new TypeInteger("cnt_supervisor").setFieldDescriptor(CashierCntBO.CNTSUPERVISOR.clear()).setNullable();
	private TypeInteger verSupervisor = new TypeInteger("ver_supervisor").setFieldDescriptor(CashierCntBO.VERSUPERVISOR.clear());

	public CashierCntVO() {
		super(CashierCntBO.TABLE, CashierCntBO.SYSTEM, CashierCntBO.CORIS_DISTRICT_DB.setSource("D"), CashierCntBO.CORIS_JUSTICE_DB.setSource("J"), CashierCntBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierCntBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CashierCntVO(String source) {
		super(CashierCntBO.TABLE, CashierCntBO.SYSTEM, CashierCntBO.CORIS_DISTRICT_DB.setSource("D"), CashierCntBO.CORIS_JUSTICE_DB.setSource("J"), CashierCntBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierCntBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(cashTot);
		this.getPropertyList().add(revnCheckTot);
		this.getPropertyList().add(revnCcTot);
		this.getPropertyList().add(trustCheckTot);
		this.getPropertyList().add(trustCcTot);
		this.getPropertyList().add(drawerBeginAmt);
		this.getPropertyList().add(cntSupervisor);
		this.getPropertyList().add(verSupervisor);
	}

}