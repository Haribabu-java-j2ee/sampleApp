package gov.utcourts.coriscommon.dataaccess.sacctbail;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAcctBailVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAcctBailBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAcctBailBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAcctBailBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAcctBailBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAcctBailBO.SOPERATION.clear()).setNullable();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(SAcctBailBO.ACCTNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger postPartyNum = new TypeInteger("post_party_num").setFieldDescriptor(SAcctBailBO.POSTPARTYNUM.clear()).setNullable();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(SAcctBailBO.RECVDDATE.clear()).setNullable();
	private TypeBigDecimal bailAmt = new TypeBigDecimal("bail_amt").setFieldDescriptor(SAcctBailBO.BAILAMT.clear()).setNullable();
	private TypeDate orderedDate = new TypeDate("ordered_date").setFieldDescriptor(SAcctBailBO.ORDEREDDATE.clear()).setNullable();
	private TypeBigDecimal refundAmt = new TypeBigDecimal("refund_amt").setFieldDescriptor(SAcctBailBO.REFUNDAMT.clear()).setNullable();
	private TypeBigDecimal forfeitAmt = new TypeBigDecimal("forfeit_amt").setFieldDescriptor(SAcctBailBO.FORFEITAMT.clear()).setNullable();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(SAcctBailBO.DISPDATE.clear()).setNullable();
	private TypeString cashOnly = new TypeString("cash_only").setFieldDescriptor(SAcctBailBO.CASHONLY.clear()).setNullable();
	private TypeString jailReceiptNum = new TypeString("jail_receipt_num").setFieldDescriptor(SAcctBailBO.JAILRECEIPTNUM.clear()).setNullable();

	public SAcctBailVO() {
		super(SAcctBailBO.TABLE, SAcctBailBO.SYSTEM, SAcctBailBO.CORIS_DISTRICT_DB.setSource("D"), SAcctBailBO.CORIS_JUSTICE_DB.setSource("J"), SAcctBailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctBailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAcctBailVO(String source) {
		super(SAcctBailBO.TABLE, SAcctBailBO.SYSTEM, SAcctBailBO.CORIS_DISTRICT_DB.setSource("D"), SAcctBailBO.CORIS_JUSTICE_DB.setSource("J"), SAcctBailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctBailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(postPartyNum);
		this.getPropertyList().add(recvdDate);
		this.getPropertyList().add(bailAmt);
		this.getPropertyList().add(orderedDate);
		this.getPropertyList().add(refundAmt);
		this.getPropertyList().add(forfeitAmt);
		this.getPropertyList().add(dispDate);
		this.getPropertyList().add(cashOnly);
		this.getPropertyList().add(jailReceiptNum);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAcctBailBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBailBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBailBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBailBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBailBO.ORDEREDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBailBO.ORDEREDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBailBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBailBO.DISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}