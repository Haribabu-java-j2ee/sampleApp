package gov.utcourts.coriscommon.dataaccess.acctdist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AcctDistVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(AcctDistBO.ACCTNUM.clear()).addForeignKey("comp_new_dist","acct_num",false).addForeignKey("account","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(AcctDistBO.DISTCODE.clear()).addForeignKey("comp_new_dist","dist_code",false).addForeignKey("dist_type","dist_code",false).setAsPrimaryKey();
	private TypeBigDecimal amtDue = new TypeBigDecimal("amt_due").setFieldDescriptor(AcctDistBO.AMTDUE.clear());
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(AcctDistBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(AcctDistBO.AMTCREDIT.clear());

	public AcctDistVO() {
		super(AcctDistBO.TABLE, AcctDistBO.SYSTEM, AcctDistBO.CORIS_DISTRICT_DB.setSource("D"), AcctDistBO.CORIS_JUSTICE_DB.setSource("J"), AcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public AcctDistVO(String source) {
		super(AcctDistBO.TABLE, AcctDistBO.SYSTEM, AcctDistBO.CORIS_DISTRICT_DB.setSource("D"), AcctDistBO.CORIS_JUSTICE_DB.setSource("J"), AcctDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), AcctDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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