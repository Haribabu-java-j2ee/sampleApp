package gov.utcourts.coriscommon.dataaccess.creditcharge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CreditChargeVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(CreditChargeBO.INTJOURNALNUM.clear()).addForeignKey("trans_dist","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(CreditChargeBO.TRANSNUM.clear()).addForeignKey("trans_dist","trans_num",false).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(CreditChargeBO.ACCTNUM.clear()).addForeignKey("trans_dist","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(CreditChargeBO.DISTCODE.clear()).addForeignKey("trans_dist","dist_code",false).setAsPrimaryKey();
	private TypeInteger chargeSeq = new TypeInteger("charge_seq").setFieldDescriptor(CreditChargeBO.CHARGESEQ.clear()).setAsPrimaryKey();
	private TypeBigDecimal creditAmt = new TypeBigDecimal("credit_amt").setFieldDescriptor(CreditChargeBO.CREDITAMT.clear()).setNullable();

	public CreditChargeVO() {
		super(CreditChargeBO.TABLE, CreditChargeBO.SYSTEM, CreditChargeBO.CORIS_DISTRICT_DB.setSource("D"), CreditChargeBO.CORIS_JUSTICE_DB.setSource("J"), CreditChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CreditChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CreditChargeVO(String source) {
		super(CreditChargeBO.TABLE, CreditChargeBO.SYSTEM, CreditChargeBO.CORIS_DISTRICT_DB.setSource("D"), CreditChargeBO.CORIS_JUSTICE_DB.setSource("J"), CreditChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CreditChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(chargeSeq);
		this.getPropertyList().add(creditAmt);
	}

}