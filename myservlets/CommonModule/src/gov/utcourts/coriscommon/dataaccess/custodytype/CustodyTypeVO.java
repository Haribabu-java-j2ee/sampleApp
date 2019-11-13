package gov.utcourts.coriscommon.dataaccess.custodytype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CustodyTypeVO extends BaseVO { 

	private TypeString custodyCode = new TypeString("custody_code").setFieldDescriptor(CustodyTypeBO.CUSTODYCODE.clear()).addForeignKey("custody_history","begin_custody_code",true).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CustodyTypeBO.DESCR.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(CustodyTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CustodyTypeBO.REMOVEDFLAG.clear());

	public CustodyTypeVO() {
		super(CustodyTypeBO.TABLE, CustodyTypeBO.SYSTEM, CustodyTypeBO.CORIS_DISTRICT_DB.setSource("D"), CustodyTypeBO.CORIS_JUSTICE_DB.setSource("J"), CustodyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CustodyTypeVO(String source) {
		super(CustodyTypeBO.TABLE, CustodyTypeBO.SYSTEM, CustodyTypeBO.CORIS_DISTRICT_DB.setSource("D"), CustodyTypeBO.CORIS_JUSTICE_DB.setSource("J"), CustodyTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CustodyTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(custodyCode);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
	}

}