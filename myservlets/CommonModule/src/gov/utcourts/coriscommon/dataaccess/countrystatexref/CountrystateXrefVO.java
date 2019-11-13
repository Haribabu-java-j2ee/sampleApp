package gov.utcourts.coriscommon.dataaccess.countrystatexref;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CountrystateXrefVO extends BaseVO { 

	private TypeInteger countrystatexrefId = new TypeInteger("countrystatexref_id").setFieldDescriptor(CountrystateXrefBO.COUNTRYSTATEXREFID.clear()).setAsPrimaryKey();
	private TypeInteger stateId = new TypeInteger("state_id").setFieldDescriptor(CountrystateXrefBO.STATEID.clear()).addForeignKey("state","state_id",false);
	private TypeInteger countryId = new TypeInteger("country_id").setFieldDescriptor(CountrystateXrefBO.COUNTRYID.clear()).addForeignKey("country","country_id",false);

	public CountrystateXrefVO() {
		super(CountrystateXrefBO.TABLE, CountrystateXrefBO.SYSTEM, CountrystateXrefBO.CORIS_DISTRICT_DB.setSource("D"), CountrystateXrefBO.CORIS_JUSTICE_DB.setSource("J"), CountrystateXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountrystateXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CountrystateXrefVO(String source) {
		super(CountrystateXrefBO.TABLE, CountrystateXrefBO.SYSTEM, CountrystateXrefBO.CORIS_DISTRICT_DB.setSource("D"), CountrystateXrefBO.CORIS_JUSTICE_DB.setSource("J"), CountrystateXrefBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountrystateXrefBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(countrystatexrefId);
		this.getPropertyList().add(stateId);
		this.getPropertyList().add(countryId);
	}

}