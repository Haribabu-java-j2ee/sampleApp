package gov.utcourts.coriscommon.dataaccess.casetype;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseTypeVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseTypeBO.CASETYPE.clear()).addForeignKey("auto_expungement_archive","case_type",false).addForeignKey("case_fee","case_type",false).addForeignKey("case_type_assn","case_type",false).addForeignKey("case_type_profile","case_type",false).addForeignKey("efiling_profile","case_type",true).addForeignKey("kase","case_type",false).addForeignKey("odr_eligible","odr_eligible_case_type",false).addForeignKey("zip_code_case_filing","case_type",false).setAsPrimaryKey();
	private TypeString descr = new TypeString("descr").setFieldDescriptor(CaseTypeBO.DESCR.clear());
	private TypeString category = new TypeString("category").setFieldDescriptor(CaseTypeBO.CATEGORY.clear());
	private TypeString validCourt = new TypeString("valid_court").setFieldDescriptor(CaseTypeBO.VALIDCOURT.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(CaseTypeBO.REMOVEDFLAG.clear());
	private TypeString rptCategory = new TypeString("rpt_category").setFieldDescriptor(CaseTypeBO.RPTCATEGORY.clear());
	private TypeString defaultCaseSecurity = new TypeString("default_case_security").setFieldDescriptor(CaseTypeBO.DEFAULTCASESECURITY.clear()).addForeignKey("security_type","code",false);

	public CaseTypeVO() {
		super(CaseTypeBO.TABLE, CaseTypeBO.SYSTEM, CaseTypeBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseTypeVO(String source) {
		super(CaseTypeBO.TABLE, CaseTypeBO.SYSTEM, CaseTypeBO.CORIS_DISTRICT_DB.setSource("D"), CaseTypeBO.CORIS_JUSTICE_DB.setSource("J"), CaseTypeBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseTypeBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(category);
		this.getPropertyList().add(validCourt);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(rptCategory);
		this.getPropertyList().add(defaultCaseSecurity);
	}

}