package gov.utcourts.coriscommon.dataaccess.depositslip;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DepositSlipVO extends BaseVO { 

	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(DepositSlipBO.BANKACCTNUM.clear()).addForeignKey("deposit_slip_ach","bank_acct_num",false).addForeignKey("deposit_slip_card","bank_acct_num",false).setAsPrimaryKey();
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(DepositSlipBO.INTJOURNALNUM.clear()).addForeignKey("deposit_slip_ach","int_journal_num",false).addForeignKey("deposit_slip_card","int_journal_num",false).setAsPrimaryKey();
	private TypeInteger depositSeq = new TypeInteger("deposit_seq").setFieldDescriptor(DepositSlipBO.DEPOSITSEQ.clear()).addForeignKey("deposit_slip_ach","deposit_seq",false).addForeignKey("deposit_slip_card","deposit_seq",false).setAsPrimaryKey();
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(DepositSlipBO.ENTRYDATETIME.clear()).setNullable();
	private TypeInteger clerkId = new TypeInteger("clerk_id").setFieldDescriptor(DepositSlipBO.CLERKID.clear()).setNullable();
	private TypeBigDecimal currencyAmt = new TypeBigDecimal("currency_amt").setFieldDescriptor(DepositSlipBO.CURRENCYAMT.clear()).setNullable();
	private TypeBigDecimal coinAmt = new TypeBigDecimal("coin_amt").setFieldDescriptor(DepositSlipBO.COINAMT.clear()).setNullable();
	private TypeBigDecimal depositAdj = new TypeBigDecimal("deposit_adj").setFieldDescriptor(DepositSlipBO.DEPOSITADJ.clear()).setNullable();
	private TypeBigDecimal checkTotal = new TypeBigDecimal("check_total").setFieldDescriptor(DepositSlipBO.CHECKTOTAL.clear()).setNullable();
	private TypeBigDecimal correctionAmt = new TypeBigDecimal("correction_amt").setFieldDescriptor(DepositSlipBO.CORRECTIONAMT.clear()).setNullable();
	private TypeBigDecimal elecXfer = new TypeBigDecimal("elec_xfer").setFieldDescriptor(DepositSlipBO.ELECXFER.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(DepositSlipBO.NOTE.clear()).setNullable();
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(DepositSlipBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(DepositSlipBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(DepositSlipBO.RECONSTATUS.clear()).setNullable();
	private TypeDate depositDate = new TypeDate("deposit_date").setFieldDescriptor(DepositSlipBO.DEPOSITDATE.clear());

	public DepositSlipVO() {
		super(DepositSlipBO.TABLE, DepositSlipBO.SYSTEM, DepositSlipBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DepositSlipVO(String source) {
		super(DepositSlipBO.TABLE, DepositSlipBO.SYSTEM, DepositSlipBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(depositSeq);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(clerkId);
		this.getPropertyList().add(currencyAmt);
		this.getPropertyList().add(coinAmt);
		this.getPropertyList().add(depositAdj);
		this.getPropertyList().add(checkTotal);
		this.getPropertyList().add(correctionAmt);
		this.getPropertyList().add(elecXfer);
		this.getPropertyList().add(note);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
		this.getPropertyList().add(depositDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DepositSlipBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipBO.ENTRYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DepositSlipBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipBO.RECONDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DepositSlipBO.DEPOSITDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipBO.DEPOSITDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}