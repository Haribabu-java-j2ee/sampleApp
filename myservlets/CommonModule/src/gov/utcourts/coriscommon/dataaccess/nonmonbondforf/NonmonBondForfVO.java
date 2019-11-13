package gov.utcourts.coriscommon.dataaccess.nonmonbondforf;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class NonmonBondForfVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(NonmonBondForfBO.ACCTNUM.clear()).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(NonmonBondForfBO.INTJOURNALNUM.clear()).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(NonmonBondForfBO.TRANSNUM.clear()).setAsPrimaryKey();
	private TypeInteger forfAcctNum = new TypeInteger("forf_acct_num").setFieldDescriptor(NonmonBondForfBO.FORFACCTNUM.clear()).setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(NonmonBondForfBO.AMTDUE.clear());
	private TypeBigDecimal amtPd = new TypeBigDecimal("amt_pd").setFieldDescriptor(NonmonBondForfBO.AMTPD.clear());

	public NonmonBondForfVO() {
		super(NonmonBondForfBO.TABLE, NonmonBondForfBO.SYSTEM, NonmonBondForfBO.CORIS_DISTRICT_DB.setSource("D"), NonmonBondForfBO.CORIS_JUSTICE_DB.setSource("J"), NonmonBondForfBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NonmonBondForfBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public NonmonBondForfVO(String source) {
		super(NonmonBondForfBO.TABLE, NonmonBondForfBO.SYSTEM, NonmonBondForfBO.CORIS_DISTRICT_DB.setSource("D"), NonmonBondForfBO.CORIS_JUSTICE_DB.setSource("J"), NonmonBondForfBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), NonmonBondForfBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(forfAcctNum);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(amtPd);
	}

}