package gov.utcourts.coriscommon.dataaccess.officer;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OfficerVO extends BaseVO { 

	private TypeInteger officerSrl = new TypeInteger("officer_srl").setFieldDescriptor(OfficerBO.OFFICERSRL.clear()).addForeignKey("crim_case","officer_srl",true).setAsPrimaryKey();
	private TypeString leaCode = new TypeString("lea_code").setFieldDescriptor(OfficerBO.LEACODE.clear());
	private TypeString badgeNum = new TypeString("badge_num").setFieldDescriptor(OfficerBO.BADGENUM.clear());
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(OfficerBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(OfficerBO.COURTTYPE.clear());
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(OfficerBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(OfficerBO.FIRSTNAME.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(OfficerBO.REMOVEDFLAG.clear());

	public OfficerVO() {
		super(OfficerBO.TABLE, OfficerBO.SYSTEM, OfficerBO.CORIS_DISTRICT_DB.setSource("D"), OfficerBO.CORIS_JUSTICE_DB.setSource("J"), OfficerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OfficerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OfficerVO(String source) {
		super(OfficerBO.TABLE, OfficerBO.SYSTEM, OfficerBO.CORIS_DISTRICT_DB.setSource("D"), OfficerBO.CORIS_JUSTICE_DB.setSource("J"), OfficerBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OfficerBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(officerSrl);
		this.getPropertyList().add(leaCode);
		this.getPropertyList().add(badgeNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(removedFlag);
	}

}