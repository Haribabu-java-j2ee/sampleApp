package gov.utcourts.coriscommon.dataaccess.sparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SPartyVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SPartyBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SPartyBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SPartyBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SPartyBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SPartyBO.SOPERATION.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SPartyBO.PARTYNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(SPartyBO.LASTNAME.clear()).setNullable();
	private TypeString lastSndx = new TypeString("last_sndx").setFieldDescriptor(SPartyBO.LASTSNDX.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(SPartyBO.FIRSTNAME.clear()).setNullable();
	private TypeString firstSndx = new TypeString("first_sndx").setFieldDescriptor(SPartyBO.FIRSTSNDX.clear()).setNullable();
	private TypeString ssn = new TypeString("ssn").setFieldDescriptor(SPartyBO.SSN.clear()).setNullable();
	private TypeString gender = new TypeString("gender").setFieldDescriptor(SPartyBO.GENDER.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(SPartyBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(SPartyBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(SPartyBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(SPartyBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(SPartyBO.ZIPCODE.clear()).setNullable();
	private TypeString country = new TypeString("country").setFieldDescriptor(SPartyBO.COUNTRY.clear()).setNullable();
	private TypeString drLicNum = new TypeString("dr_lic_num").setFieldDescriptor(SPartyBO.DRLICNUM.clear()).setNullable();
	private TypeString drLicState = new TypeString("dr_lic_state").setFieldDescriptor(SPartyBO.DRLICSTATE.clear()).setNullable();
	private TypeString homePhone = new TypeString("home_phone").setFieldDescriptor(SPartyBO.HOMEPHONE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(SPartyBO.BUSPHONE.clear()).setNullable();
	private TypeString faxNum = new TypeString("fax_num").setFieldDescriptor(SPartyBO.FAXNUM.clear()).setNullable();
	private TypeDate birthDate = new TypeDate("birth_date").setFieldDescriptor(SPartyBO.BIRTHDATE.clear()).setNullable();
	private TypeString employerName = new TypeString("employer_name").setFieldDescriptor(SPartyBO.EMPLOYERNAME.clear()).setNullable();
	private TypeString raceCode = new TypeString("race_code").setFieldDescriptor(SPartyBO.RACECODE.clear()).setNullable();
	private TypeString language = new TypeString("language").setFieldDescriptor(SPartyBO.LANGUAGE.clear()).setNullable();
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(SPartyBO.LANGID.clear()).addForeignKey("language","lang_id",false).setNullable();
	private TypeString disabled = new TypeString("disabled").setFieldDescriptor(SPartyBO.DISABLED.clear()).setNullable();

	public SPartyVO() {
		super(SPartyBO.TABLE, SPartyBO.SYSTEM, SPartyBO.CORIS_DISTRICT_DB.setSource("D"), SPartyBO.CORIS_JUSTICE_DB.setSource("J"), SPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SPartyVO(String source) {
		super(SPartyBO.TABLE, SPartyBO.SYSTEM, SPartyBO.CORIS_DISTRICT_DB.setSource("D"), SPartyBO.CORIS_JUSTICE_DB.setSource("J"), SPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(lastSndx);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(firstSndx);
		this.getPropertyList().add(ssn);
		this.getPropertyList().add(gender);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(country);
		this.getPropertyList().add(drLicNum);
		this.getPropertyList().add(drLicState);
		this.getPropertyList().add(homePhone);
		this.getPropertyList().add(busPhone);
		this.getPropertyList().add(faxNum);
		this.getPropertyList().add(birthDate);
		this.getPropertyList().add(employerName);
		this.getPropertyList().add(raceCode);
		this.getPropertyList().add(language);
		this.getPropertyList().add(langId);
		this.getPropertyList().add(disabled);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SPartyBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SPartyBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SPartyBO.BIRTHDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SPartyBO.BIRTHDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}