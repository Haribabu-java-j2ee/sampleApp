package gov.utcourts.coriscommon.dataaccess.county;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CountyVO extends BaseVO { 

	private TypeString cntyCode = new TypeString("cnty_code").setFieldDescriptor(CountyBO.CNTYCODE.clear()).addForeignKey("profile","cnty_code",true).addForeignKey("zip_code_case_filing","cnty_code",false).addForeignKey("zip_code_filing_locn","cnty_code",false).setAsPrimaryKey();
	private TypeString name = new TypeString("name").setFieldDescriptor(CountyBO.NAME.clear());
	private TypeString taxlienLocnCode = new TypeString("taxlien_locn_code").setFieldDescriptor(CountyBO.TAXLIENLOCNCODE.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(CountyBO.PARTYNUM.clear()).addForeignKey("party","party_num",false).setNullable();
	private TypeInteger courtDistrict = new TypeInteger("court_district").setFieldDescriptor(CountyBO.COURTDISTRICT.clear());

	public CountyVO() {
		super(CountyBO.TABLE, CountyBO.SYSTEM, CountyBO.CORIS_DISTRICT_DB.setSource("D"), CountyBO.CORIS_JUSTICE_DB.setSource("J"), CountyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CountyVO(String source) {
		super(CountyBO.TABLE, CountyBO.SYSTEM, CountyBO.CORIS_DISTRICT_DB.setSource("D"), CountyBO.CORIS_JUSTICE_DB.setSource("J"), CountyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(cntyCode);
		this.getPropertyList().add(name);
		this.getPropertyList().add(taxlienLocnCode);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(courtDistrict);
	}

}