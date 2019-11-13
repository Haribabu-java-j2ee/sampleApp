package gov.utcourts.coriscommon.dataaccess.zipcodecasefiling;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ZipCodeCaseFilingVO extends BaseVO { 

	private TypeString cntyCode = new TypeString("cnty_code").setFieldDescriptor(ZipCodeCaseFilingBO.CNTYCODE.clear()).addForeignKey("county","cnty_code",false).setAsPrimaryKey();
	private TypeString caseType = new TypeString("case_type").setFieldDescriptor(ZipCodeCaseFilingBO.CASETYPE.clear()).addForeignKey("case_type","case_type",false).setAsPrimaryKey();
	private TypeString proRataFlag = new TypeString("pro_rata_flag").setFieldDescriptor(ZipCodeCaseFilingBO.PRORATAFLAG.clear());

	public ZipCodeCaseFilingVO() {
		super(ZipCodeCaseFilingBO.TABLE, ZipCodeCaseFilingBO.SYSTEM, ZipCodeCaseFilingBO.CORIS_DISTRICT_DB.setSource("D"), ZipCodeCaseFilingBO.CORIS_JUSTICE_DB.setSource("J"), ZipCodeCaseFilingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ZipCodeCaseFilingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ZipCodeCaseFilingVO(String source) {
		super(ZipCodeCaseFilingBO.TABLE, ZipCodeCaseFilingBO.SYSTEM, ZipCodeCaseFilingBO.CORIS_DISTRICT_DB.setSource("D"), ZipCodeCaseFilingBO.CORIS_JUSTICE_DB.setSource("J"), ZipCodeCaseFilingBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ZipCodeCaseFilingBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(cntyCode);
		this.getPropertyList().add(caseType);
		this.getPropertyList().add(proRataFlag);
	}

}