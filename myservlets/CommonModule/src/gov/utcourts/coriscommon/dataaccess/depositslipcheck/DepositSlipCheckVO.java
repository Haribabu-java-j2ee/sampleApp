package gov.utcourts.coriscommon.dataaccess.depositslipcheck;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DepositSlipCheckVO extends BaseVO { 

	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(DepositSlipCheckBO.BANKACCTNUM.clear()).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(DepositSlipCheckBO.INTJOURNALNUM.clear()).setAsPrimaryKey();
	private TypeInteger depositSeq = new TypeInteger("deposit_seq").setFieldDescriptor(DepositSlipCheckBO.DEPOSITSEQ.clear()).setAsPrimaryKey();
	private TypeInteger checkSeq = new TypeInteger("check_seq").setFieldDescriptor(DepositSlipCheckBO.CHECKSEQ.clear()).setAsPrimaryKey();
	private TypeString memo = new TypeString("memo").setFieldDescriptor(DepositSlipCheckBO.MEMO.clear()).setNullable();
	private TypeBigDecimal amt = new TypeBigDecimal("amt").setFieldDescriptor(DepositSlipCheckBO.AMT.clear()).setNullable();

	public DepositSlipCheckVO() {
		super(DepositSlipCheckBO.TABLE, DepositSlipCheckBO.SYSTEM, DepositSlipCheckBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipCheckBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipCheckBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipCheckBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DepositSlipCheckVO(String source) {
		super(DepositSlipCheckBO.TABLE, DepositSlipCheckBO.SYSTEM, DepositSlipCheckBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipCheckBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipCheckBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipCheckBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(depositSeq);
		this.getPropertyList().add(checkSeq);
		this.getPropertyList().add(memo);
		this.getPropertyList().add(amt);
	}

}