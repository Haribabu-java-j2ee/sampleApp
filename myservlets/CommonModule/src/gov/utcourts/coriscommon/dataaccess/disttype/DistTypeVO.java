package gov.utcourts.coriscommon.dataaccess.disttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DistTypeVO extends BaseVO { 

	private TypeString distCode = new TypeString("dist_code").setFieldDescriptor(DistTypeBO.DISTCODE.clear()).addForeignKey("acct_adj","dist_code",false).addForeignKey("acct_dist","dist_code",false).addForeignKey("dc_acct_dist","dist_code",false).addForeignKey("fee_dist","dist_code",false).addForeignKey("offense_dist","surcharge_code",true).addForeignKey("offense_dist","dist_code",false).addForeignKey("trans_dist","dist_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(DistTypeBO.DESCR.clear());
	private TypeString fundOrg = new TypeString("fund_org").setFieldDescriptor(DistTypeBO.FUNDORG.clear()).setNullable();
	private TypeString acctNum = new TypeString("acct_num").setFieldDescriptor(DistTypeBO.ACCTNUM.clear()).setNullable();
	private TypeBigDecimal surchargePercent = new TypeBigDecimal("surcharge_percent").setFieldDescriptor(DistTypeBO.SURCHARGEPERCENT.clear()).setNullable();
	private TypeBigDecimal localPercent = new TypeBigDecimal("local_percent").setFieldDescriptor(DistTypeBO.LOCALPERCENT.clear()).setNullable();
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(DistTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(DistTypeBO.REMOVEDFLAG.clear());
	private TypeString type = new TypeString("type").setFieldDescriptor(DistTypeBO.TYPE.clear());
	private TypeInteger paymentPriority = new TypeInteger("payment_priority").setFieldDescriptor(DistTypeBO.PAYMENTPRIORITY.clear());

	public DistTypeVO() {
		super(DistTypeBO.TABLE, DistTypeBO.SYSTEM, DistTypeBO.CORIS_DISTRICT_DB.setSource("D"), DistTypeBO.CORIS_JUSTICE_DB.setSource("J"), DistTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DistTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DistTypeVO(String source) {
		super(DistTypeBO.TABLE, DistTypeBO.SYSTEM, DistTypeBO.CORIS_DISTRICT_DB.setSource("D"), DistTypeBO.CORIS_JUSTICE_DB.setSource("J"), DistTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DistTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(distCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(fundOrg);
		this.getPropertyList().add(acctNum);
		this.getPropertyList().add(surchargePercent);
		this.getPropertyList().add(localPercent);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(type);
		this.getPropertyList().add(paymentPriority);
	}

}