package gov.utcourts.coriscommon.dataaccess.caseaccessrule;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseAccessRuleVO extends BaseVO { 

	private TypeInteger accessId = new TypeInteger("access_id").setFieldDescriptor(CaseAccessRuleBO.ACCESSID.clear()).setAsPrimaryKey();
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(CaseAccessRuleBO.CASESECURITY.clear());
	private TypeInteger securProfilValue = new TypeInteger("secur_profil_value").setFieldDescriptor(CaseAccessRuleBO.SECURPROFILVALUE.clear());

	public CaseAccessRuleVO() {
		super(CaseAccessRuleBO.TABLE, CaseAccessRuleBO.SYSTEM, CaseAccessRuleBO.CORIS_DISTRICT_DB.setSource("D"), CaseAccessRuleBO.CORIS_JUSTICE_DB.setSource("J"), CaseAccessRuleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseAccessRuleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseAccessRuleVO(String source) {
		super(CaseAccessRuleBO.TABLE, CaseAccessRuleBO.SYSTEM, CaseAccessRuleBO.CORIS_DISTRICT_DB.setSource("D"), CaseAccessRuleBO.CORIS_JUSTICE_DB.setSource("J"), CaseAccessRuleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseAccessRuleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(accessId);
		this.getPropertyList().add(caseSecurity);
		this.getPropertyList().add(securProfilValue);
	}

}