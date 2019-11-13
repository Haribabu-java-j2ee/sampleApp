package gov.utcourts.coriscommon.dataaccess.acctbail;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctBailVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctBailBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeInteger postPartyNum = new TypeInteger("post_party_num").setFieldDescriptor(AcctBailBO.POSTPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(AcctBailBO.RECVDDATE.clear());
	private TypeBigDecimal bailAmt = new TypeBigDecimal("bail_amt").setFieldDescriptor(AcctBailBO.BAILAMT.clear());
	private TypeDate orderedDate = new TypeDate("ordered_date").setFieldDescriptor(AcctBailBO.ORDEREDDATE.clear()).setNullable();
	private TypeBigDecimal refundAmt = new TypeBigDecimal("refund_amt").setFieldDescriptor(AcctBailBO.REFUNDAMT.clear());
	private TypeBigDecimal forfeitAmt = new TypeBigDecimal("forfeit_amt").setFieldDescriptor(AcctBailBO.FORFEITAMT.clear());
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(AcctBailBO.DISPDATE.clear()).setNullable();
	private TypeString cashOnly = new TypeString("cash_only").setFieldDescriptor(AcctBailBO.CASHONLY.clear()).setNullable();
	private TypeString jailReceiptNum = new TypeString("jail_receipt_num").setFieldDescriptor(AcctBailBO.JAILRECEIPTNUM.clear()).setNullable();

	public AcctBailVO() {
		super(AcctBailBO.TABLE, AcctBailBO.SYSTEM, AcctBailBO.CORIS_DISTRICT_DB.setSource("D"), AcctBailBO.CORIS_JUSTICE_DB.setSource("J"), AcctBailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctBailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctBailVO(String source) {
		super(AcctBailBO.TABLE, AcctBailBO.SYSTEM, AcctBailBO.CORIS_DISTRICT_DB.setSource("D"), AcctBailBO.CORIS_JUSTICE_DB.setSource("J"), AcctBailBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctBailBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(AcctBailBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBailBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctBailBO.ORDEREDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBailBO.ORDEREDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctBailBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBailBO.DISPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}