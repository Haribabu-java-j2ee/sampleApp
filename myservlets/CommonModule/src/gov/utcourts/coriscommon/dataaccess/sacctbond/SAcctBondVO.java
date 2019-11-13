package gov.utcourts.coriscommon.dataaccess.sacctbond;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAcctBondVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAcctBondBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAcctBondBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAcctBondBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAcctBondBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAcctBondBO.SOPERATION.clear()).setNullable();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(SAcctBondBO.ACCTNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString bondNum = new TypeString("bond_num").setFieldDescriptor(SAcctBondBO.BONDNUM.clear()).setNullable();
	private TypeString bondCoId = new TypeString("bond_co_id").setFieldDescriptor(SAcctBondBO.BONDCOID.clear()).setNullable();
	private TypeInteger postPartyNum = new TypeInteger("post_party_num").setFieldDescriptor(SAcctBondBO.POSTPARTYNUM.clear()).setNullable();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(SAcctBondBO.RECVDDATE.clear()).setNullable();
	private TypeBigDecimal bondAmt = new TypeBigDecimal("bond_amt").setFieldDescriptor(SAcctBondBO.BONDAMT.clear()).setNullable();
	private TypeDate orderedDate = new TypeDate("ordered_date").setFieldDescriptor(SAcctBondBO.ORDEREDDATE.clear()).setNullable();
	private TypeBigDecimal exonRefundAmt = new TypeBigDecimal("exon_refund_amt").setFieldDescriptor(SAcctBondBO.EXONREFUNDAMT.clear()).setNullable();
	private TypeBigDecimal forfeitAmt = new TypeBigDecimal("forfeit_amt").setFieldDescriptor(SAcctBondBO.FORFEITAMT.clear()).setNullable();
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(SAcctBondBO.DISPDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(SAcctBondBO.DISPCODE.clear()).setNullable();
	private TypeDate forfeitPaidDate = new TypeDate("forfeit_paid_date").setFieldDescriptor(SAcctBondBO.FORFEITPAIDDATE.clear()).setNullable();
	private TypeString bondCode = new TypeString("bond_code").setFieldDescriptor(SAcctBondBO.BONDCODE.clear()).setNullable();

	public SAcctBondVO() {
		super(SAcctBondBO.TABLE, SAcctBondBO.SYSTEM, SAcctBondBO.CORIS_DISTRICT_DB.setSource("D"), SAcctBondBO.CORIS_JUSTICE_DB.setSource("J"), SAcctBondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctBondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAcctBondVO(String source) {
		super(SAcctBondBO.TABLE, SAcctBondBO.SYSTEM, SAcctBondBO.CORIS_DISTRICT_DB.setSource("D"), SAcctBondBO.CORIS_JUSTICE_DB.setSource("J"), SAcctBondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctBondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(bondNum);
		this.getPropertyList().add(bondCoId);
		this.getPropertyList().add(postPartyNum);
		this.getPropertyList().add(recvdDate);
		this.getPropertyList().add(bondAmt);
		this.getPropertyList().add(orderedDate);
		this.getPropertyList().add(exonRefundAmt);
		this.getPropertyList().add(forfeitAmt);
		this.getPropertyList().add(dispDate);
		this.getPropertyList().add(dispCode);
		this.getPropertyList().add(forfeitPaidDate);
		this.getPropertyList().add(bondCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAcctBondBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBondBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBondBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBondBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBondBO.ORDEREDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBondBO.ORDEREDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBondBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBondBO.DISPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SAcctBondBO.FORFEITPAIDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctBondBO.FORFEITPAIDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}