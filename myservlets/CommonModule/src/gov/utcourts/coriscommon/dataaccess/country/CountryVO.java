package gov.utcourts.coriscommon.dataaccess.country;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CountryVO extends BaseVO { 

	private TypeInteger countryId = new TypeInteger("country_id").setFieldDescriptor(CountryBO.COUNTRYID.clear()).addForeignKey("countrystate_xref","country_id",false).addForeignKey("id_issuer","country_id",true).setAsPrimaryKey();
	private TypeString countryName = new TypeString("country_name").setFieldDescriptor(CountryBO.COUNTRYNAME.clear());
	private TypeString psCountryCode = new TypeString("ps_country_code").setFieldDescriptor(CountryBO.PSCOUNTRYCODE.clear());
	private TypeString unCountryCode = new TypeString("un_country_code").setFieldDescriptor(CountryBO.UNCOUNTRYCODE.clear()).setNullable();
	private TypeDate disabledDate = new TypeDate("disabled_date").setFieldDescriptor(CountryBO.DISABLEDDATE.clear()).setNullable();

	public CountryVO() {
		super(CountryBO.TABLE, CountryBO.SYSTEM, CountryBO.CORIS_DISTRICT_DB.setSource("D"), CountryBO.CORIS_JUSTICE_DB.setSource("J"), CountryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CountryVO(String source) {
		super(CountryBO.TABLE, CountryBO.SYSTEM, CountryBO.CORIS_DISTRICT_DB.setSource("D"), CountryBO.CORIS_JUSTICE_DB.setSource("J"), CountryBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CountryBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(countryId);
		this.getPropertyList().add(countryName);
		this.getPropertyList().add(psCountryCode);
		this.getPropertyList().add(unCountryCode);
		this.getPropertyList().add(disabledDate);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CountryBO.DISABLEDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CountryBO.DISABLEDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}