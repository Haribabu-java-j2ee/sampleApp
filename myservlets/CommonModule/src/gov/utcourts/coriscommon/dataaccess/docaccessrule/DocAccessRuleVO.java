package gov.utcourts.coriscommon.dataaccess.docaccessrule;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class DocAccessRuleVO extends BaseVO { 

	private TypeInteger accessId = new TypeInteger("access_id").setFieldDescriptor(DocAccessRuleBO.ACCESSID.clear()).setAsPrimaryKey();
	private TypeString docSecurity = new TypeString("doc_security").setFieldDescriptor(DocAccessRuleBO.DOCSECURITY.clear());
	private TypeString caseSecurity = new TypeString("case_security").setFieldDescriptor(DocAccessRuleBO.CASESECURITY.clear());
	private TypeInteger securProfilValue = new TypeInteger("secur_profil_value").setFieldDescriptor(DocAccessRuleBO.SECURPROFILVALUE.clear());

	public DocAccessRuleVO() {
		super(DocAccessRuleBO.TABLE, DocAccessRuleBO.SYSTEM, DocAccessRuleBO.CORIS_DISTRICT_DB.setSource("D"), DocAccessRuleBO.CORIS_JUSTICE_DB.setSource("J"), DocAccessRuleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAccessRuleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public DocAccessRuleVO(String source) {
		super(DocAccessRuleBO.TABLE, DocAccessRuleBO.SYSTEM, DocAccessRuleBO.CORIS_DISTRICT_DB.setSource("D"), DocAccessRuleBO.CORIS_JUSTICE_DB.setSource("J"), DocAccessRuleBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), DocAccessRuleBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(accessId);
		this.getPropertyList().add(docSecurity);
		this.getPropertyList().add(caseSecurity);
		this.getPropertyList().add(securProfilValue);
	}

}