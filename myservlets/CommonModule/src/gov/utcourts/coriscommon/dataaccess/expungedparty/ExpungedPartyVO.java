package gov.utcourts.coriscommon.dataaccess.expungedparty;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ExpungedPartyVO extends BaseVO { 

	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(ExpungedPartyBO.PARTYNUM.clear()).setAsPrimaryKey();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(ExpungedPartyBO.LASTNAME.clear());
	private TypeString lastSndx = new TypeString("last_sndx").setFieldDescriptor(ExpungedPartyBO.LASTSNDX.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(ExpungedPartyBO.FIRSTNAME.clear()).setNullable();
	private TypeString firstSndx = new TypeString("first_sndx").setFieldDescriptor(ExpungedPartyBO.FIRSTSNDX.clear()).setNullable();
	private TypeString ssn = new TypeString("ssn").setFieldDescriptor(ExpungedPartyBO.SSN.clear()).setNullable();
	private TypeString gender = new TypeString("gender").setFieldDescriptor(ExpungedPartyBO.GENDER.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(ExpungedPartyBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(ExpungedPartyBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(ExpungedPartyBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(ExpungedPartyBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(ExpungedPartyBO.ZIPCODE.clear()).setNullable();
	private TypeString country = new TypeString("country").setFieldDescriptor(ExpungedPartyBO.COUNTRY.clear()).setNullable();
	private TypeString drLicNum = new TypeString("dr_lic_num").setFieldDescriptor(ExpungedPartyBO.DRLICNUM.clear()).setNullable();
	private TypeString drLicState = new TypeString("dr_lic_state").setFieldDescriptor(ExpungedPartyBO.DRLICSTATE.clear()).setNullable();
	private TypeString homePhone = new TypeString("home_phone").setFieldDescriptor(ExpungedPartyBO.HOMEPHONE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(ExpungedPartyBO.BUSPHONE.clear()).setNullable();
	private TypeDate birthDate = new TypeDate("birth_date").setFieldDescriptor(ExpungedPartyBO.BIRTHDATE.clear()).setNullable();
	private TypeString employerName = new TypeString("employer_name").setFieldDescriptor(ExpungedPartyBO.EMPLOYERNAME.clear()).setNullable();
	private TypeString raceCode = new TypeString("race_code").setFieldDescriptor(ExpungedPartyBO.RACECODE.clear()).setNullable();
	private TypeString language = new TypeString("language").setFieldDescriptor(ExpungedPartyBO.LANGUAGE.clear()).setNullable();
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(ExpungedPartyBO.LANGID.clear()).addForeignKey("language","lang_id",false).setNullable();
	private TypeString warrStatus = new TypeString("warr_status").setFieldDescriptor(ExpungedPartyBO.WARRSTATUS.clear()).setNullable();

	public ExpungedPartyVO() {
		super(ExpungedPartyBO.TABLE, ExpungedPartyBO.SYSTEM, ExpungedPartyBO.CORIS_DISTRICT_DB.setSource("D"), ExpungedPartyBO.CORIS_JUSTICE_DB.setSource("J"), ExpungedPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ExpungedPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ExpungedPartyVO(String source) {
		super(ExpungedPartyBO.TABLE, ExpungedPartyBO.SYSTEM, ExpungedPartyBO.CORIS_DISTRICT_DB.setSource("D"), ExpungedPartyBO.CORIS_JUSTICE_DB.setSource("J"), ExpungedPartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ExpungedPartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
		this.getPropertyList().add(birthDate);
		this.getPropertyList().add(employerName);
		this.getPropertyList().add(raceCode);
		this.getPropertyList().add(language);
		this.getPropertyList().add(langId);
		this.getPropertyList().add(warrStatus);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(ExpungedPartyBO.BIRTHDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(ExpungedPartyBO.BIRTHDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}