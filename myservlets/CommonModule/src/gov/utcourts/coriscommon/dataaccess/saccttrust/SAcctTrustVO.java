package gov.utcourts.coriscommon.dataaccess.saccttrust;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SAcctTrustVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SAcctTrustBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SAcctTrustBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SAcctTrustBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SAcctTrustBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SAcctTrustBO.SOPERATION.clear()).setNullable();
	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(SAcctTrustBO.ACCTNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString trustCode = new TypeString("trust_code").setFieldDescriptor(SAcctTrustBO.TRUSTCODE.clear()).setNullable();
	private TypeBigDecimal amtPaidOut = new TypeBigDecimal("amt_paid_out").setFieldDescriptor(SAcctTrustBO.AMTPAIDOUT.clear()).setNullable();
	private TypeInteger payeePartyNum = new TypeInteger("payee_party_num").setFieldDescriptor(SAcctTrustBO.PAYEEPARTYNUM.clear()).setNullable();
	private TypeInteger payorPartyNum = new TypeInteger("payor_party_num").setFieldDescriptor(SAcctTrustBO.PAYORPARTYNUM.clear()).setNullable();
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(SAcctTrustBO.PRIORITY.clear()).setNullable();
	private TypeString checkStubDescr = new TypeString("check_stub_descr").setFieldDescriptor(SAcctTrustBO.CHECKSTUBDESCR.clear()).setNullable();
	private TypeInteger jointSevNum = new TypeInteger("joint_sev_num").setFieldDescriptor(SAcctTrustBO.JOINTSEVNUM.clear()).setNullable();

	public SAcctTrustVO() {
		super(SAcctTrustBO.TABLE, SAcctTrustBO.SYSTEM, SAcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), SAcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), SAcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SAcctTrustVO(String source) {
		super(SAcctTrustBO.TABLE, SAcctTrustBO.SYSTEM, SAcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), SAcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), SAcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SAcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
		this.getPropertyList().add(trustCode);
		this.getPropertyList().add(amtPaidOut);
		this.getPropertyList().add(payeePartyNum);
		this.getPropertyList().add(payorPartyNum);
		this.getPropertyList().add(priority);
		this.getPropertyList().add(checkStubDescr);
		this.getPropertyList().add(jointSevNum);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SAcctTrustBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SAcctTrustBO.SDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}