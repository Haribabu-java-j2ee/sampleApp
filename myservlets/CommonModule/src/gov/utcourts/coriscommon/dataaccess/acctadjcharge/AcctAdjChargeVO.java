package gov.utcourts.coriscommon.dataaccess.acctadjcharge;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctAdjChargeVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctAdjChargeBO.ACCTNUM.clear()).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeInteger chargeSeq = new TypeInteger("charge_seq").setFieldDescriptor(AcctAdjChargeBO.CHARGESEQ.clear()).setAsPrimaryKey();
	private TypeDate adjDatetime = new TypeDate("adj_datetime").setFieldDescriptor(AcctAdjChargeBO.ADJDATETIME.clear()).setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(AcctAdjChargeBO.AMTDUE.clear());
	private TypeString reason = new TypeString("reason").setFieldDescriptor(AcctAdjChargeBO.REASON.clear()).setNullable();
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(AcctAdjChargeBO.USERIDSRL.clear()).addForeignKey("personnel","userid_srl",false);

	public AcctAdjChargeVO() {
		super(AcctAdjChargeBO.TABLE, AcctAdjChargeBO.SYSTEM, AcctAdjChargeBO.CORIS_DISTRICT_DB.setSource("D"), AcctAdjChargeBO.CORIS_JUSTICE_DB.setSource("J"), AcctAdjChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctAdjChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctAdjChargeVO(String source) {
		super(AcctAdjChargeBO.TABLE, AcctAdjChargeBO.SYSTEM, AcctAdjChargeBO.CORIS_DISTRICT_DB.setSource("D"), AcctAdjChargeBO.CORIS_JUSTICE_DB.setSource("J"), AcctAdjChargeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctAdjChargeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(chargeSeq);
		this.getPropertyList().add(adjDatetime);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(reason);
		this.getPropertyList().add(useridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(AcctAdjChargeBO.ADJDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(AcctAdjChargeBO.ADJDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}