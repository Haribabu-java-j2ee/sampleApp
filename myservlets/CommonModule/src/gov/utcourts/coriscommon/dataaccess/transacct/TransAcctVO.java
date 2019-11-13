package gov.utcourts.coriscommon.dataaccess.transacct;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransAcctVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransAcctBO.INTJOURNALNUM.clear()).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransAcctBO.TRANSNUM.clear()).addForeignKey("trans","trans_num",false).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(TransAcctBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setAsPrimaryKey();

	public TransAcctVO() {
		super(TransAcctBO.TABLE, TransAcctBO.SYSTEM, TransAcctBO.CORIS_DISTRICT_DB.setSource("D"), TransAcctBO.CORIS_JUSTICE_DB.setSource("J"), TransAcctBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransAcctBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransAcctVO(String source) {
		super(TransAcctBO.TABLE, TransAcctBO.SYSTEM, TransAcctBO.CORIS_DISTRICT_DB.setSource("D"), TransAcctBO.CORIS_JUSTICE_DB.setSource("J"), TransAcctBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransAcctBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(acctNum);
	}

}