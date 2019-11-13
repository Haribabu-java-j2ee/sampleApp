package gov.utcourts.coriscommon.dataaccess.efilingprofile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class EfilingProfileVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(EfilingProfileBO.LOCNCODE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(EfilingProfileBO.COURTTYPE.clear());
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(EfilingProfileBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false).setNullable();
	private TypeString filingType = new TypeString("filing_type").setFieldDescriptor(EfilingProfileBO.FILINGTYPE.clear()).setNullable();
	private TypeString docCode = new TypeString("doc_code").setFieldDescriptor(EfilingProfileBO.DOCCODE.clear()).setNullable();
	private TypeString efspCode = new TypeString("efsp_code").setFieldDescriptor(EfilingProfileBO.EFSPCODE.clear()).setNullable();
	private TypeString orgCode = new TypeString("org_code").setFieldDescriptor(EfilingProfileBO.ORGCODE.clear()).setNullable();
	private TypeString rule = new TypeString("rule").setFieldDescriptor(EfilingProfileBO.RULE.clear());

	public EfilingProfileVO() {
		super(EfilingProfileBO.TABLE, EfilingProfileBO.SYSTEM, EfilingProfileBO.CORIS_DISTRICT_DB.setSource("D"), EfilingProfileBO.CORIS_JUSTICE_DB.setSource("J"), EfilingProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public EfilingProfileVO(String source) {
		super(EfilingProfileBO.TABLE, EfilingProfileBO.SYSTEM, EfilingProfileBO.CORIS_DISTRICT_DB.setSource("D"), EfilingProfileBO.CORIS_JUSTICE_DB.setSource("J"), EfilingProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), EfilingProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(filingType);
		this.getPropertyList().add(docCode);
		this.getPropertyList().add(efspCode);
		this.getPropertyList().add(orgCode);
		this.getPropertyList().add(rule);
	}

}