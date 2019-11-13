package gov.utcourts.coriscommon.dataaccess.cashiercntdetl;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CashierCntDetlVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(CashierCntDetlBO.USERIDSRL.clear()).addForeignKey("cashier_cnt","userid_srl",false).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(CashierCntDetlBO.INTJOURNALNUM.clear()).addForeignKey("cashier_cnt","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(CashierCntDetlBO.SEQ.clear()).addForeignKey("cashier_cnt","seq",false).setAsPrimaryKey();
	private TypeInteger detlSeq = new TypeInteger("detl_seq").setFieldDescriptor(CashierCntDetlBO.DETLSEQ.clear()).setAsPrimaryKey();
	private TypeBigDecimal checkAmt = new TypeBigDecimal("check_amt").setFieldDescriptor(CashierCntDetlBO.CHECKAMT.clear());
	private TypeString checkNum = new TypeString("check_num").setFieldDescriptor(CashierCntDetlBO.CHECKNUM.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(CashierCntDetlBO.TYPE.clear());

	public CashierCntDetlVO() {
		super(CashierCntDetlBO.TABLE, CashierCntDetlBO.SYSTEM, CashierCntDetlBO.CORIS_DISTRICT_DB.setSource("D"), CashierCntDetlBO.CORIS_JUSTICE_DB.setSource("J"), CashierCntDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierCntDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CashierCntDetlVO(String source) {
		super(CashierCntDetlBO.TABLE, CashierCntDetlBO.SYSTEM, CashierCntDetlBO.CORIS_DISTRICT_DB.setSource("D"), CashierCntDetlBO.CORIS_JUSTICE_DB.setSource("J"), CashierCntDetlBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CashierCntDetlBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(detlSeq);
		this.getPropertyList().add(checkAmt);
		this.getPropertyList().add(checkNum);
		this.getPropertyList().add(type);
	}

}