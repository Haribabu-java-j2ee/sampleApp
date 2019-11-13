package gov.utcourts.coriscommon.dataaccess.compnewdist;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CompNewDistVO extends BaseVO { 

	private TypeInteger acctNum = new TypeInteger("acct_num").setFieldDescriptor(CompNewDistBO.ACCTNUM.clear()).addForeignKey("acct_dist","acct_num",false).setAsPrimaryKey();
	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(CompNewDistBO.DISTCODE.clear()).addForeignKey("acct_dist","dist_code",false).setAsPrimaryKey();
	private TypeBigDecimal amtPaid = new TypeBigDecimal("amt_paid").setFieldDescriptor(CompNewDistBO.AMTPAID.clear());
	private TypeBigDecimal amtCredit = new TypeBigDecimal("amt_credit").setFieldDescriptor(CompNewDistBO.AMTCREDIT.clear());

	public CompNewDistVO() {
		super(CompNewDistBO.TABLE, CompNewDistBO.SYSTEM, CompNewDistBO.CORIS_DISTRICT_DB.setSource("D"), CompNewDistBO.CORIS_JUSTICE_DB.setSource("J"), CompNewDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompNewDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CompNewDistVO(String source) {
		super(CompNewDistBO.TABLE, CompNewDistBO.SYSTEM, CompNewDistBO.CORIS_DISTRICT_DB.setSource("D"), CompNewDistBO.CORIS_JUSTICE_DB.setSource("J"), CompNewDistBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CompNewDistBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(amtPaid);
		this.getPropertyList().add(amtCredit);
	}

}