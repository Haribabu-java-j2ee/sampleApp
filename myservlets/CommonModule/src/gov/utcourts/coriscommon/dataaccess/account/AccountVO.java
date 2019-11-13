package gov.utcourts.coriscommon.dataaccess.account;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AccountVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AccountBO.ACCTNUM.clear()).addForeignKey("account_judgment_xref","acct_num",false).addForeignKey("acct_adj","acct_num",false).addForeignKey("acct_adj_charge","acct_num",false).addForeignKey("acct_bail","acct_num",false).addForeignKey("acct_bond","acct_num",false).addForeignKey("acct_dist","acct_num",false).addForeignKey("acct_fee","acct_num",false).addForeignKey("acct_trust","interest_acct_num",true).addForeignKey("acct_trust","acct_num",false).addForeignKey("acct_waiver","acct_num",true).addForeignKey("dc_account","acct_num",false).addForeignKey("debt_coll","acct_num",true).addForeignKey("osdc_acct_history","acct_num",false).addForeignKey("trans_acct","acct_num",false).addForeignKey("trans_dist","acct_num",false).setAsPrimaryKey();
	private TypeString acctType = new TypeString("acct_type").setFieldDescriptor(AccountBO.ACCTTYPE.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(AccountBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(AccountBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(AccountBO.PARTYCODE.clear()).setNullable();
	private TypeInteger timepayNum = new TypeInteger("timepay_num").setFieldDescriptor(AccountBO.TIMEPAYNUM.clear()).addForeignKey("timepay","timepay_num",false).setNullable();
	private TypeInteger timepaySeq = new TypeInteger("timepay_seq").setFieldDescriptor(AccountBO.TIMEPAYSEQ.clear()).setNullable();
	private TypeBigDecimal origAmtDue = new TypeBigDecimal("orig_amt_due").setFieldDescriptor(AccountBO.ORIGAMTDUE.clear());
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(AccountBO.AMTDUE.clear());
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(AccountBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(AccountBO.AMTCREDIT.clear());
	private TypeString status = new TypeString("status").setFieldDescriptor(AccountBO.STATUS.clear()).setNullable();
	private TypeDate dueDate = new TypeDate("due_date").setFieldDescriptor(AccountBO.DUEDATE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(AccountBO.USERIDSRL.clear()).setNullable();
	private TypeDate intEffectDate = new TypeDate("int_effect_date").setFieldDescriptor(AccountBO.INTEFFECTDATE.clear()).setNullable();
	private TypeString note = new TypeString("note").setFieldDescriptor(AccountBO.NOTE.clear()).setNullable();

	public AccountVO() {
		super(AccountBO.TABLE, AccountBO.SYSTEM, AccountBO.CORIS_DISTRICT_DB.setSource("D"), AccountBO.CORIS_JUSTICE_DB.setSource("J"), AccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AccountVO(String source) {
		super(AccountBO.TABLE, AccountBO.SYSTEM, AccountBO.CORIS_DISTRICT_DB.setSource("D"), AccountBO.CORIS_JUSTICE_DB.setSource("J"), AccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(acctType);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(timepayNum);
		this.getPropertyList().add(timepaySeq);
		this.getPropertyList().add(origAmtDue);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(amtPaid);
		this.getPropertyList().add(amtCredit);
		this.getPropertyList().add(status);
		this.getPropertyList().add(dueDate);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(intEffectDate);
		this.getPropertyList().add(note);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AccountBO.DUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AccountBO.DUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AccountBO.INTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AccountBO.INTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}