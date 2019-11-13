package gov.utcourts.coriscommon.dataaccess.warractivate;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WarrActivateVO extends BaseVO { 

	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(WarrActivateBO.CASENUM.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(WarrActivateBO.INTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger warrNum = new TypeInteger("warr_num").setFieldDescriptor(WarrActivateBO.WARRNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(WarrActivateBO.PARTYNUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(WarrActivateBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(WarrActivateBO.COURTTYPE.clear()).setNullable();
	private TypeString fullName = new TypeString("full_name").setFieldDescriptor(WarrActivateBO.FULLNAME.clear()).setNullable();

	public WarrActivateVO() {
		super(WarrActivateBO.TABLE, WarrActivateBO.SYSTEM, WarrActivateBO.CORIS_DISTRICT_DB.setSource("D"), WarrActivateBO.CORIS_JUSTICE_DB.setSource("J"), WarrActivateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrActivateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WarrActivateVO(String source) {
		super(WarrActivateBO.TABLE, WarrActivateBO.SYSTEM, WarrActivateBO.CORIS_DISTRICT_DB.setSource("D"), WarrActivateBO.CORIS_JUSTICE_DB.setSource("J"), WarrActivateBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrActivateBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(warrNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(fullName);
	}

}