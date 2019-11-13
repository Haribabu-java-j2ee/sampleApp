package gov.utcourts.coriscommon.dataaccess.state;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class StateVO extends BaseVO { 

	private TypeInteger stateId = new TypeInteger("state_id").setFieldDescriptor(StateBO.STATEID.clear()).addForeignKey("countrystate_xref","state_id",false).addForeignKey("id_issuer","state_id",true).setAsPrimaryKey();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(StateBO.STATECODE.clear());
	private TypeString name = new TypeString("name").setFieldDescriptor(StateBO.NAME.clear());
	private TypeString countryCode = new TypeString("country_code").setFieldDescriptor(StateBO.COUNTRYCODE.clear()).setNullable();

	public StateVO() {
		super(StateBO.TABLE, StateBO.SYSTEM, StateBO.CORIS_DISTRICT_DB.setSource("D"), StateBO.CORIS_JUSTICE_DB.setSource("J"), StateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public StateVO(String source) {
		super(StateBO.TABLE, StateBO.SYSTEM, StateBO.CORIS_DISTRICT_DB.setSource("D"), StateBO.CORIS_JUSTICE_DB.setSource("J"), StateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(stateId);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(name);
		this.getPropertyList().add(countryCode);
	}

}