package gov.utcourts.coriscommon.dataaccess.credittype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CreditTypeVO extends BaseVO { 

	private TypeString creditCode = new TypeString("credit_code").setFieldDescriptor(CreditTypeBO.CREDITCODE.clear()).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CreditTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(CreditTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CreditTypeBO.REMOVEDFLAG.clear());

	public CreditTypeVO() {
		super(CreditTypeBO.TABLE, CreditTypeBO.SYSTEM, CreditTypeBO.CORIS_DISTRICT_DB.setSource("D"), CreditTypeBO.CORIS_JUSTICE_DB.setSource("J"), CreditTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CreditTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CreditTypeVO(String source) {
		super(CreditTypeBO.TABLE, CreditTypeBO.SYSTEM, CreditTypeBO.CORIS_DISTRICT_DB.setSource("D"), CreditTypeBO.CORIS_JUSTICE_DB.setSource("J"), CreditTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CreditTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(creditCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}