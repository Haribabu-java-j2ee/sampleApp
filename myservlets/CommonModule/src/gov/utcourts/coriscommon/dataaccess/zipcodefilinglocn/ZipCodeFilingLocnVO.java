package gov.utcourts.coriscommon.dataaccess.zipcodefilinglocn;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ZipCodeFilingLocnVO extends BaseVO { 

	private TypeString filingZipCode = new TypeString("filing_zip_code").setFieldDescriptor(ZipCodeFilingLocnBO.FILINGZIPCODE.clear()).setAsPrimaryKey();
	private TypeString cntyCode = new TypeString("cnty_code").setFieldDescriptor(ZipCodeFilingLocnBO.CNTYCODE.clear()).addForeignKey("county","cnty_code",false).setAsPrimaryKey();
	private TypeString filingLocnCode = new TypeString("filing_locn_code").setFieldDescriptor(ZipCodeFilingLocnBO.FILINGLOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString filingCourtType = new TypeString("filing_court_type").setFieldDescriptor(ZipCodeFilingLocnBO.FILINGCOURTTYPE.clear()).addForeignKey("profile","court_type",false);

	public ZipCodeFilingLocnVO() {
		super(ZipCodeFilingLocnBO.TABLE, ZipCodeFilingLocnBO.SYSTEM, ZipCodeFilingLocnBO.CORIS_DISTRICT_DB.setSource("D"), ZipCodeFilingLocnBO.CORIS_JUSTICE_DB.setSource("J"), ZipCodeFilingLocnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ZipCodeFilingLocnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ZipCodeFilingLocnVO(String source) {
		super(ZipCodeFilingLocnBO.TABLE, ZipCodeFilingLocnBO.SYSTEM, ZipCodeFilingLocnBO.CORIS_DISTRICT_DB.setSource("D"), ZipCodeFilingLocnBO.CORIS_JUSTICE_DB.setSource("J"), ZipCodeFilingLocnBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ZipCodeFilingLocnBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(filingZipCode);
		this.getPropertyList().add(cntyCode);
		this.getPropertyList().add(filingLocnCode);
		this.getPropertyList().add(filingCourtType);
	}

}