package gov.utcourts.coriscommon.dataaccess.sacctdist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAcctDistVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAcctDistBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAcctDistBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAcctDistBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAcctDistBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAcctDistBO.SOPERATION.clear()).setNullable();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(SAcctDistBO.ACCTNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(SAcctDistBO.DISTCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(SAcctDistBO.AMTDUE.clear()).setNullable();
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(SAcctDistBO.AMTPAID.clear()).setNullable();
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(SAcctDistBO.AMTCREDIT.clear()).setNullable();

	public SAcctDistVO() {
		super(SAcctDistBO.TABLE, SAcctDistBO.SYSTEM, SAcctDistBO.CORIS_DISTRICT_DB.setSource("D"), SAcctDistBO.CORIS_JUSTICE_DB.setSource("J"), SAcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAcctDistVO(String source) {
		super(SAcctDistBO.TABLE, SAcctDistBO.SYSTEM, SAcctDistBO.CORIS_DISTRICT_DB.setSource("D"), SAcctDistBO.CORIS_JUSTICE_DB.setSource("J"), SAcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(amtPaid);
		this.getPropertyList().add(amtCredit);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAcctDistBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctDistBO.SDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}