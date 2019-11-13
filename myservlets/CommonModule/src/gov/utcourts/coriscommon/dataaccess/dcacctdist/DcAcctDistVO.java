package gov.utcourts.coriscommon.dataaccess.dcacctdist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DcAcctDistVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(DcAcctDistBO.ACCTNUM.clear()).addForeignKey("dc_account","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(DcAcctDistBO.DISTCODE.clear()).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(DcAcctDistBO.AMTDUE.clear());
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(DcAcctDistBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(DcAcctDistBO.AMTCREDIT.clear());

	public DcAcctDistVO() {
		super(DcAcctDistBO.TABLE, DcAcctDistBO.SYSTEM, DcAcctDistBO.CORIS_DISTRICT_DB.setSource("D"), DcAcctDistBO.CORIS_JUSTICE_DB.setSource("J"), DcAcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DcAcctDistVO(String source) {
		super(DcAcctDistBO.TABLE, DcAcctDistBO.SYSTEM, DcAcctDistBO.CORIS_DISTRICT_DB.setSource("D"), DcAcctDistBO.CORIS_JUSTICE_DB.setSource("J"), DcAcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DcAcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(amtDue);
		this.getPropertyList().add(amtPaid);
		this.getPropertyList().add(amtCredit);
	}

}