package gov.utcourts.coriscommon.dataaccess.bciexceptions;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BciExceptionsVO extends BaseVO { 

	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(BciExceptionsBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(BciExceptionsBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(BciExceptionsBO.COURTTYPE.clear()).setNullable();
	private TypeString otn = new TypeString("otn").setFieldDescriptor(BciExceptionsBO.OTN.clear()).setNullable();
	private TypeString citationNum = new TypeString("citation_num").setFieldDescriptor(BciExceptionsBO.CITATIONNUM.clear()).setNullable();

	public BciExceptionsVO() {
		super(BciExceptionsBO.TABLE, BciExceptionsBO.SYSTEM, BciExceptionsBO.CORIS_DISTRICT_DB.setSource("D"), BciExceptionsBO.CORIS_JUSTICE_DB.setSource("J"), BciExceptionsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciExceptionsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public BciExceptionsVO(String source) {
		super(BciExceptionsBO.TABLE, BciExceptionsBO.SYSTEM, BciExceptionsBO.CORIS_DISTRICT_DB.setSource("D"), BciExceptionsBO.CORIS_JUSTICE_DB.setSource("J"), BciExceptionsBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), BciExceptionsBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(otn);
		this.getPropertyList().add(citationNum);
	}

}