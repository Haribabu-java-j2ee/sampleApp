package gov.utcourts.coriscommon.dataaccess.commonparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CommonPartyVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CommonPartyBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CommonPartyBO.COURTTYPE.clear()).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(CommonPartyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setAsPrimaryKey();

	public CommonPartyVO() {
		super(CommonPartyBO.TABLE, CommonPartyBO.SYSTEM, CommonPartyBO.CORIS_DISTRICT_DB.setSource("D"), CommonPartyBO.CORIS_JUSTICE_DB.setSource("J"), CommonPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CommonPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CommonPartyVO(String source) {
		super(CommonPartyBO.TABLE, CommonPartyBO.SYSTEM, CommonPartyBO.CORIS_DISTRICT_DB.setSource("D"), CommonPartyBO.CORIS_JUSTICE_DB.setSource("J"), CommonPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CommonPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(partyNum);
	}

}