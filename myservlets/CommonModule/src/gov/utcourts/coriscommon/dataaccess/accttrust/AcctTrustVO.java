package gov.utcourts.coriscommon.dataaccess.accttrust;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctTrustVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctTrustBO.ACCTNUM.clear()).addForeignKey("check_batch_detl","acct_num",false).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString trustCode = new TypeString("trust_code").setFieldDescriptor(AcctTrustBO.TRUSTCODE.clear()).addForeignKey("trust_type","trust_code",false);
	private TypeBigDecimal amtPaidOut = new TypeBigDecimal("amt_paid_out").setFieldDescriptor(AcctTrustBO.AMTPAIDOUT.clear());
	private TypeInteger payeePartyNum = new TypeInteger("payee_party_num").setFieldDescriptor(AcctTrustBO.PAYEEPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger payorPartyNum = new TypeInteger("payor_party_num").setFieldDescriptor(AcctTrustBO.PAYORPARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger priority = new TypeInteger("priority").setFieldDescriptor(AcctTrustBO.PRIORITY.clear()).setNullable();
	private TypeString checkStubDescr = new TypeString("check_stub_descr").setFieldDescriptor(AcctTrustBO.CHECKSTUBDESCR.clear()).setNullable();
	private TypeInteger jointSevNum = new TypeInteger("joint_sev_num").setFieldDescriptor(AcctTrustBO.JOINTSEVNUM.clear()).addForeignKey("joint_sev","joint_sev_num",false).setNullable();
	private TypeInteger interestAcctNum = new TypeInteger("interest_acct_num").setFieldDescriptor(AcctTrustBO.INTERESTACCTNUM.clear()).addForeignKey("account","acct_num",false).setNullable();

	public AcctTrustVO() {
		super(AcctTrustBO.TABLE, AcctTrustBO.SYSTEM, AcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), AcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), AcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctTrustVO(String source) {
		super(AcctTrustBO.TABLE, AcctTrustBO.SYSTEM, AcctTrustBO.CORIS_DISTRICT_DB.setSource("D"), AcctTrustBO.CORIS_JUSTICE_DB.setSource("J"), AcctTrustBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctTrustBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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