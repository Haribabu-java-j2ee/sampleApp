package gov.utcourts.coriscommon.dataaccess.emvregkeys;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EmvregkeysVO extends BaseVO { 

	private TypeInteger emvregkeysid = new TypeInteger("emvregkeysid").setFieldDescriptor(EmvregkeysBO.EMVREGKEYSID.clear()).setAsPrimaryKey();
	private TypeString regkeyvalue = new TypeString("regkeyvalue").setFieldDescriptor(EmvregkeysBO.REGKEYVALUE.clear());

	public EmvregkeysVO() {
		super(EmvregkeysBO.TABLE, EmvregkeysBO.SYSTEM, EmvregkeysBO.CORIS_DISTRICT_DB.setSource("D"), EmvregkeysBO.CORIS_JUSTICE_DB.setSource("J"), EmvregkeysBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EmvregkeysBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EmvregkeysVO(String source) {
		super(EmvregkeysBO.TABLE, EmvregkeysBO.SYSTEM, EmvregkeysBO.CORIS_DISTRICT_DB.setSource("D"), EmvregkeysBO.CORIS_JUSTICE_DB.setSource("J"), EmvregkeysBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EmvregkeysBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(emvregkeysid);
		this.getPropertyList().add(regkeyvalue);
	}

}