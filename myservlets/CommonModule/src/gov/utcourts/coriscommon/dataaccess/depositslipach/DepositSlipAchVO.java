package gov.utcourts.coriscommon.dataaccess.depositslipach;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DepositSlipAchVO extends BaseVO { 

	private TypeInteger intAchDepNum = new TypeInteger("int_ach_dep_num").setFieldDescriptor(DepositSlipAchBO.INTACHDEPNUM.clear()).setAsPrimaryKey();
	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(DepositSlipAchBO.BANKACCTNUM.clear()).addForeignKey("deposit_slip","bank_acct_num",false);
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(DepositSlipAchBO.INTJOURNALNUM.clear()).addForeignKey("deposit_slip","int_journal_num",false);
	private TypeInteger depositSeq = new TypeInteger("deposit_seq").setFieldDescriptor(DepositSlipAchBO.DEPOSITSEQ.clear()).addForeignKey("deposit_slip","deposit_seq",false);
	private TypeDate paymentDate = new TypeDate("payment_date").setFieldDescriptor(DepositSlipAchBO.PAYMENTDATE.clear());
	private TypeBigDecimal achDepAmount = new TypeBigDecimal("ach_dep_amount").setFieldDescriptor(DepositSlipAchBO.ACHDEPAMOUNT.clear());
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(DepositSlipAchBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(DepositSlipAchBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(DepositSlipAchBO.RECONSTATUS.clear()).setNullable();

	public DepositSlipAchVO() {
		super(DepositSlipAchBO.TABLE, DepositSlipAchBO.SYSTEM, DepositSlipAchBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipAchBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipAchBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipAchBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DepositSlipAchVO(String source) {
		super(DepositSlipAchBO.TABLE, DepositSlipAchBO.SYSTEM, DepositSlipAchBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipAchBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipAchBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipAchBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intAchDepNum);
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(depositSeq);
		this.getPropertyList().add(paymentDate);
		this.getPropertyList().add(achDepAmount);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DepositSlipAchBO.PAYMENTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipAchBO.PAYMENTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DepositSlipAchBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipAchBO.RECONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}