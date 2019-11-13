package gov.utcourts.coriscommon.dataaccess.trusttype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class TrustTypeVO extends BaseVO { 

	private TypeString trustCode = new TypeString("trust_code").setFieldDescriptor(TrustTypeBO.TRUSTCODE.clear()).addForeignKey("acct_trust","trust_code",false).addForeignKey("dc_acct_trust","trust_code",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(TrustTypeBO.DESCR.clear());
	private TypeString openendFlag = new TypeString("openend_flag").setFieldDescriptor(TrustTypeBO.OPENENDFLAG.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(TrustTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(TrustTypeBO.REMOVEDFLAG.clear());
	private TypeString intTrustFlag = new TypeString("int_trust_flag").setFieldDescriptor(TrustTypeBO.INTTRUSTFLAG.clear());

	public TrustTypeVO() {
		super(TrustTypeBO.TABLE, TrustTypeBO.SYSTEM, TrustTypeBO.CORIS_DISTRICT_DB.setSource("D"), TrustTypeBO.CORIS_JUSTICE_DB.setSource("J"), TrustTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrustTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public TrustTypeVO(String source) {
		super(TrustTypeBO.TABLE, TrustTypeBO.SYSTEM, TrustTypeBO.CORIS_DISTRICT_DB.setSource("D"), TrustTypeBO.CORIS_JUSTICE_DB.setSource("J"), TrustTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), TrustTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(trustCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(openendFlag);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(intTrustFlag);
	}

}