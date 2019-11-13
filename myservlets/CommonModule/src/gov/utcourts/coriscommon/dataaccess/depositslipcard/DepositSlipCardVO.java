package gov.utcourts.coriscommon.dataaccess.depositslipcard;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DepositSlipCardVO extends BaseVO { 

	private TypeInteger intCcDepNum = new TypeInteger("int_cc_dep_num").setFieldDescriptor(DepositSlipCardBO.INTCCDEPNUM.clear()).setAsPrimaryKey();
	private TypeString bankAcctNum = new TypeString("bank_acct_num").setFieldDescriptor(DepositSlipCardBO.BANKACCTNUM.clear()).addForeignKey("deposit_slip","bank_acct_num",false);
	private TypeInteger intJournalNum = new TypeInteger("int_journal_num").setFieldDescriptor(DepositSlipCardBO.INTJOURNALNUM.clear()).addForeignKey("deposit_slip","int_journal_num",false);
	private TypeInteger depositSeq = new TypeInteger("deposit_seq").setFieldDescriptor(DepositSlipCardBO.DEPOSITSEQ.clear()).addForeignKey("deposit_slip","deposit_seq",false);
	private TypeDate paymentDate = new TypeDate("payment_date").setFieldDescriptor(DepositSlipCardBO.PAYMENTDATE.clear());
	private TypeBigDecimal ccDepAmount = new TypeBigDecimal("cc_dep_amount").setFieldDescriptor(DepositSlipCardBO.CCDEPAMOUNT.clear());
	private TypeDate reconDate = new TypeDate("recon_date").setFieldDescriptor(DepositSlipCardBO.RECONDATE.clear()).setNullable();
	private TypeInteger reconUserid = new TypeInteger("recon_userid").setFieldDescriptor(DepositSlipCardBO.RECONUSERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger reconStatus = new TypeInteger("recon_status").setFieldDescriptor(DepositSlipCardBO.RECONSTATUS.clear()).setNullable();

	public DepositSlipCardVO() {
		super(DepositSlipCardBO.TABLE, DepositSlipCardBO.SYSTEM, DepositSlipCardBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipCardBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipCardBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipCardBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DepositSlipCardVO(String source) {
		super(DepositSlipCardBO.TABLE, DepositSlipCardBO.SYSTEM, DepositSlipCardBO.CORIS_DISTRICT_DB.setSource("D"), DepositSlipCardBO.CORIS_JUSTICE_DB.setSource("J"), DepositSlipCardBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DepositSlipCardBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCcDepNum);
		this.getPropertyList().add(bankAcctNum);
		this.getPropertyList().add(intJournalNum);
		this.getPropertyList().add(depositSeq);
		this.getPropertyList().add(paymentDate);
		this.getPropertyList().add(ccDepAmount);
		this.getPropertyList().add(reconDate);
		this.getPropertyList().add(reconUserid);
		this.getPropertyList().add(reconStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DepositSlipCardBO.PAYMENTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipCardBO.PAYMENTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DepositSlipCardBO.RECONDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DepositSlipCardBO.RECONDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}