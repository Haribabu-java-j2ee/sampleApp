package gov.utcourts.coriscommon.dataaccess.transdist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TransDistVO extends BaseVO { 

	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(TransDistBO.INTJOURNALNUM.clear()).addForeignKey("credit_charge","int_journal_num",false).addForeignKey("trans","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger transNum = new TypeInteger("trans_num").setFieldDescriptor(TransDistBO.TRANSNUM.clear()).addForeignKey("credit_charge","trans_num",false).addForeignKey("trans","trans_num",false).setAsPrimaryKey();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(TransDistBO.ACCTNUM.clear()).addForeignKey("credit_charge","acct_num",false).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(TransDistBO.DISTCODE.clear()).addForeignKey("credit_charge","dist_code",false).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(TransDistBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(TransDistBO.AMTCREDIT.clear());
	private TypeString applyCode = new TypeString("apply_code").setFieldDescriptor(TransDistBO.APPLYCODE.clear()).setNullable();

	public TransDistVO() {
		super(TransDistBO.TABLE, TransDistBO.SYSTEM, TransDistBO.CORIS_DISTRICT_DB.setSource("D"), TransDistBO.CORIS_JUSTICE_DB.setSource("J"), TransDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TransDistVO(String source) {
		super(TransDistBO.TABLE, TransDistBO.SYSTEM, TransDistBO.CORIS_DISTRICT_DB.setSource("D"), TransDistBO.CORIS_JUSTICE_DB.setSource("J"), TransDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TransDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(transNum);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(amtPaid);
		this.getPropertyList().add(amtCredit);
		this.getPropertyList().add(applyCode);
	}

}