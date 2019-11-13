package gov.utcourts.coriscommon.dataaccess.dcaccttrust;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DcAcctTrustVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(DcAcctTrustBO.ACCTNUM.clear()).addForeignKey("dc_account","acct_num",false).setAsPrimaryKey();
	private TypeString trustCode = new TypeString("trust_code").setFieldDescriptor(DcAcctTrustBO.TRUSTCODE.clear()).addForeignKey("trust_type","trust_code",false);
	private TypeBigDecimal amtPaidOut = new TypeBigDecimal("amt_paid_out").setFieldDescriptor(DcAcctTrustBO.AMTPAIDOUT.clear());
	private TypeInteger payeePartyNum = new TypeInteger("payee_party_num").setFieldDescriptor(DcAcctTrustBO.PAYEEPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger payorPartyNum = new TypeInteger("payor_party_num").setFieldDescriptor(DcAcctTrustBO.PAYORPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(DcAcctTrustBO.PRIORITY.clear()).setNullable();
	private TypeString checkStubDescr = new TypeString("check_stub_descr").setFieldDescriptor(DcAcctTrustBO.CHECKSTUBDESCR.clear()).setNullable();
	private TypeInteger jointSevNum = new TypeInteger("joint_sev_num").setFieldDescriptor(DcAcctTrustBO.JOINTSEVNUM.clear()).addForeignKey("joint_sev","joint_sev_num",false).setNullable();
	private TypeInteger interestAcctNum = new TypeInteger("interest_acct_num").setFieldDescriptor(DcAcctTrustBO.INTERESTACCTNUM.clear()).setNullable();

	public DcAcctTrustVO() {
		super(DcAcctTrustBO.TABLE, DcAcctTrustBO.SYSTEM, DcAcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), DcAcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), DcAcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DcAcctTrustVO(String source) {
		super(DcAcctTrustBO.TABLE, DcAcctTrustBO.SYSTEM, DcAcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), DcAcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), DcAcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(trustCode);
		this.getPropertyList().add(amtPaidOut);
		this.getPropertyList().add(payeePartyNum);
		this.getPropertyList().add(payorPartyNum);
		this.getPropertyList().add(priority);
		this.getPropertyList().add(checkStubDescr);
		this.getPropertyList().add(jointSevNum);
		this.getPropertyList().add(interestAcctNum);
	}

}