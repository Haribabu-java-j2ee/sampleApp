package gov.utcourts.coriscommon.dataaccess.caseassnspecial;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CaseAssnSpecialVO extends BaseVO { 

	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(CaseAssnSpecialBO.CASETYPE.clear()).setAsPrimaryKey();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CaseAssnSpecialBO.LOCNCODE.clear()).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CaseAssnSpecialBO.COURTTYPE.clear()).setAsPrimaryKey();

	public CaseAssnSpecialVO() {
		super(CaseAssnSpecialBO.TABLE, CaseAssnSpecialBO.SYSTEM, CaseAssnSpecialBO.CORIS_DISTRICT_DB.setSource("D"), CaseAssnSpecialBO.CORIS_JUSTICE_DB.setSource("J"), CaseAssnSpecialBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseAssnSpecialBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CaseAssnSpecialVO(String source) {
		super(CaseAssnSpecialBO.TABLE, CaseAssnSpecialBO.SYSTEM, CaseAssnSpecialBO.CORIS_DISTRICT_DB.setSource("D"), CaseAssnSpecialBO.CORIS_JUSTICE_DB.setSource("J"), CaseAssnSpecialBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CaseAssnSpecialBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
	}

}