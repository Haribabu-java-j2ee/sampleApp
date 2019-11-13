package gov.utcourts.coriscommon.dataaccess.saccount;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAccountVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAccountBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAccountBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAccountBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAccountBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAccountBO.SOPERATION.clear()).setNullable();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(SAccountBO.ACCTNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString acctType = new TypeString("acct_type").setFieldDescriptor(SAccountBO.ACCTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SAccountBO.INTCASENUM.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SAccountBO.PARTYNUM.clear()).setNullable();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SAccountBO.PARTYCODE.clear()).setNullable();
	private TypeInteger timepayNum = new TypeInteger("timepay_num").setFieldDescriptor(SAccountBO.TIMEPAYNUM.clear()).setNullable();
	private TypeInteger timepaySeq = new TypeInteger("timepay_seq").setFieldDescriptor(SAccountBO.TIMEPAYSEQ.clear()).setNullable();
	private TypeBigDecimal origAmtDue = new TypeBigDecimal("orig_amt_due").setFieldDescriptor(SAccountBO.ORIGAMTDUE.clear()).setNullable();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(SAccountBO.AMTDUE.clear()).setNullable();
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(SAccountBO.AMTPAID.clear()).setNullable();
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(SAccountBO.AMTCREDIT.clear()).setNullable();
	private TypeString status = new TypeString("status").setFieldDescriptor(SAccountBO.STATUS.clear()).setNullable();
	private TypeDate dueDate = new TypeDate("due_date").setFieldDescriptor(SAccountBO.DUEDATE.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(SAccountBO.USERIDSRL.clear()).setNullable();

	public SAccountVO() {
		super(SAccountBO.TABLE, SAccountBO.SYSTEM, SAccountBO.CORIS_DISTRICT_DB.setSource("D"), SAccountBO.CORIS_JUSTICE_DB.setSource("J"), SAccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAccountVO(String source) {
		super(SAccountBO.TABLE, SAccountBO.SYSTEM, SAccountBO.CORIS_DISTRICT_DB.setSource("D"), SAccountBO.CORIS_JUSTICE_DB.setSource("J"), SAccountBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAccountBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
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
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAccountBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAccountBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAccountBO.DUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAccountBO.DUEDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}