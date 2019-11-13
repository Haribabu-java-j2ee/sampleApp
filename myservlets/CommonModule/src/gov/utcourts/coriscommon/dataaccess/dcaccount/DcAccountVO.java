package gov.utcourts.coriscommon.dataaccess.dcaccount;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DcAccountVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(DcAccountBO.ACCTNUM.clear()).addForeignKey("dc_acct_dist","acct_num",false).addForeignKey("dc_acct_trust","acct_num",false).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString acctType = new TypeString("acct_type").setFieldDescriptor(DcAccountBO.ACCTTYPE.clear());
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(DcAccountBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(DcAccountBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(DcAccountBO.PARTYCODE.clear()).setNullable();
	private TypeInteger timepayNum = new TypeInteger("timepay_num").setFieldDescriptor(DcAccountBO.TIMEPAYNUM.clear()).addForeignKey("timepay","timepay_num",false).setNullable();
	private TypeInteger timepaySeq = new TypeInteger("timepay_seq").setFieldDescriptor(DcAccountBO.TIMEPAYSEQ.clear()).setNullable();
	private TypeBigDecimal origAmtDue = new TypeBigDecimal("orig_amt_due").setFieldDescriptor(DcAccountBO.ORIGAMTDUE.clear());
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(DcAccountBO.AMTDUE.clear());
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(DcAccountBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(DcAccountBO.AMTCREDIT.clear());
	private TypeString status = new TypeString("status").setFieldDescriptor(DcAccountBO.STATUS.clear()).setNullable();
	private TypeDate dueDate = new TypeDate("due_date").setFieldDescriptor(DcAccountBO.DUEDATE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(DcAccountBO.USERIDSRL.clear()).setNullable();
	private TypeDate intEffectDate = new TypeDate("int_effect_date").setFieldDescriptor(DcAccountBO.INTEFFECTDATE.clear()).setNullable();

	public DcAccountVO() {
		super(DcAccountBO.TABLE, DcAccountBO.SYSTEM, DcAccountBO.CORIS_DISTRICT_DB.setSource("D"), DcAccountBO.CORIS_JUSTICE_DB.setSource("J"), DcAccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DcAccountVO(String source) {
		super(DcAccountBO.TABLE, DcAccountBO.SYSTEM, DcAccountBO.CORIS_DISTRICT_DB.setSource("D"), DcAccountBO.CORIS_JUSTICE_DB.setSource("J"), DcAccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(DcAccountBO.DUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcAccountBO.DUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(DcAccountBO.INTEFFECTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(DcAccountBO.INTEFFECTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}