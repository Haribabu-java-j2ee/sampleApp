package gov.utcourts.coriscommon.dataaccess.acctbond;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctBondVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctBondBO.ACCTNUM.clear()).addForeignKey("nonmon_bond_detl","acct_num",false).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString bondNum = new TypeString("bond_num").setFieldDescriptor(AcctBondBO.BONDNUM.clear()).setNullable();
	private TypeString bondCoId = new TypeString("bond_co_id").setFieldDescriptor(AcctBondBO.BONDCOID.clear()).addForeignKey("bond_co","lic_num",false).setNullable();
	private TypeInteger postPartyNum = new TypeInteger("post_party_num").setFieldDescriptor(AcctBondBO.POSTPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeDate recvdDate = new TypeDate("recvd_date").setFieldDescriptor(AcctBondBO.RECVDDATE.clear());
	private TypeBigDecimal bondAmt = new TypeBigDecimal("bond_amt").setFieldDescriptor(AcctBondBO.BONDAMT.clear());
	private TypeDate orderedDate = new TypeDate("ordered_date").setFieldDescriptor(AcctBondBO.ORDEREDDATE.clear()).setNullable();
	private TypeBigDecimal exonRefundAmt = new TypeBigDecimal("exon_refund_amt").setFieldDescriptor(AcctBondBO.EXONREFUNDAMT.clear());
	private TypeBigDecimal forfeitAmt = new TypeBigDecimal("forfeit_amt").setFieldDescriptor(AcctBondBO.FORFEITAMT.clear());
	private TypeDate dispDate = new TypeDate("disp_date").setFieldDescriptor(AcctBondBO.DISPDATE.clear()).setNullable();
	private TypeString dispCode = new TypeString("disp_code").setFieldDescriptor(AcctBondBO.DISPCODE.clear()).setNullable();
	private TypeDate forfeitPaidDate = new TypeDate("forfeit_paid_date").setFieldDescriptor(AcctBondBO.FORFEITPAIDDATE.clear()).setNullable();
	private TypeString bondCode = new TypeString("bond_code").setFieldDescriptor(AcctBondBO.BONDCODE.clear()).addForeignKey("bond_type","bond_code",false).setNullable();

	public AcctBondVO() {
		super(AcctBondBO.TABLE, AcctBondBO.SYSTEM, AcctBondBO.CORIS_DISTRICT_DB.setSource("D"), AcctBondBO.CORIS_JUSTICE_DB.setSource("J"), AcctBondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctBondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctBondVO(String source) {
		super(AcctBondBO.TABLE, AcctBondBO.SYSTEM, AcctBondBO.CORIS_DISTRICT_DB.setSource("D"), AcctBondBO.CORIS_JUSTICE_DB.setSource("J"), AcctBondBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctBondBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(AcctBondBO.RECVDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBondBO.RECVDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctBondBO.ORDEREDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBondBO.ORDEREDDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctBondBO.DISPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBondBO.DISPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(AcctBondBO.FORFEITPAIDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctBondBO.FORFEITPAIDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}