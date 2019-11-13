package gov.utcourts.coriscommon.dataaccess.efilingparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingPartyVO extends BaseVO { 

	private TypeString efspCode = new TypeString("efsp_code").setFieldDescriptor(EfilingPartyBO.EFSPCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeString orgCode = new TypeString("org_code").setFieldDescriptor(EfilingPartyBO.ORGCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(EfilingPartyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setAsPrimaryKey();

	public EfilingPartyVO() {
		super(EfilingPartyBO.TABLE, EfilingPartyBO.SYSTEM, EfilingPartyBO.CORIS_DISTRICT_DB.setSource("D"), EfilingPartyBO.CORIS_JUSTICE_DB.setSource("J"), EfilingPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingPartyVO(String source) {
		super(EfilingPartyBO.TABLE, EfilingPartyBO.SYSTEM, EfilingPartyBO.CORIS_DISTRICT_DB.setSource("D"), EfilingPartyBO.CORIS_JUSTICE_DB.setSource("J"), EfilingPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(efspCode);
		this.getPropertyList().add(orgCode);
		this.getPropertyList().add(partyNum);
	}

}