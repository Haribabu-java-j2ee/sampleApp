package gov.utcourts.coriscommon.dataaccess.partyold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(PartyOldBO.ACTIONTYPE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PartyOldBO.USERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(PartyOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(PartyOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PartyOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PartyOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PartyOldBO.INTCASENUM.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PartyOldBO.PARTYNUM.clear());
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(PartyOldBO.LASTNAME.clear()).setNullable();
	private TypeString lastSndx = new TypeString("last_sndx").setFieldDescriptor(PartyOldBO.LASTSNDX.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(PartyOldBO.FIRSTNAME.clear()).setNullable();
	private TypeString firstSndx = new TypeString("first_sndx").setFieldDescriptor(PartyOldBO.FIRSTSNDX.clear()).setNullable();
	private TypeString ssn = new TypeString("ssn").setFieldDescriptor(PartyOldBO.SSN.clear()).setNullable();
	private TypeString gender = new TypeString("gender").setFieldDescriptor(PartyOldBO.GENDER.clear()).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(PartyOldBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(PartyOldBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(PartyOldBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(PartyOldBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(PartyOldBO.ZIPCODE.clear()).setNullable();
	private TypeString country = new TypeString("country").setFieldDescriptor(PartyOldBO.COUNTRY.clear()).setNullable();
	private TypeString drLicNum = new TypeString("dr_lic_num").setFieldDescriptor(PartyOldBO.DRLICNUM.clear()).setNullable();
	private TypeString drLicState = new TypeString("dr_lic_state").setFieldDescriptor(PartyOldBO.DRLICSTATE.clear()).setNullable();
	private TypeString homePhone = new TypeString("home_phone").setFieldDescriptor(PartyOldBO.HOMEPHONE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(PartyOldBO.BUSPHONE.clear()).setNullable();
	private TypeString faxNum = new TypeString("fax_num").setFieldDescriptor(PartyOldBO.FAXNUM.clear()).setNullable();
	private TypeDate birthDate = new TypeDate("birth_date").setFieldDescriptor(PartyOldBO.BIRTHDATE.clear()).setNullable();
	private TypeString employerName = new TypeString("employer_name").setFieldDescriptor(PartyOldBO.EMPLOYERNAME.clear()).setNullable();
	private TypeString raceCode = new TypeString("race_code").setFieldDescriptor(PartyOldBO.RACECODE.clear()).setNullable();
	private TypeString language = new TypeString("language").setFieldDescriptor(PartyOldBO.LANGUAGE.clear()).setNullable();
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(PartyOldBO.LANGID.clear()).addForeignKey("language","lang_id",false).setNullable();
	private TypeString disabled = new TypeString("disabled").setFieldDescriptor(PartyOldBO.DISABLED.clear()).setNullable();
	private TypeString empAddr = new TypeString("emp_addr").setFieldDescriptor(PartyOldBO.EMPADDR.clear()).setNullable();
	private TypeString commercialDl = new TypeString("commercial_dl").setFieldDescriptor(PartyOldBO.COMMERCIALDL.clear()).setNullable();
	private TypeString inmateNum = new TypeString("inmate_num").setFieldDescriptor(PartyOldBO.INMATENUM.clear()).setNullable();
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(PartyOldBO.EMAILADDRESS.clear()).setNullable();

	public PartyOldVO() {
		super(PartyOldBO.TABLE, PartyOldBO.SYSTEM, PartyOldBO.CORIS_DISTRICT_DB.setSource("D"), PartyOldBO.CORIS_JUSTICE_DB.setSource("J"), PartyOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyOldVO(String source) {
		super(PartyOldBO.TABLE, PartyOldBO.SYSTEM, PartyOldBO.CORIS_DISTRICT_DB.setSource("D"), PartyOldBO.CORIS_JUSTICE_DB.setSource("J"), PartyOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(changeDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(intCaseNum);
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
		this.getPropertyList().add(empAddr);
		this.getPropertyList().add(commercialDl);
		this.getPropertyList().add(inmateNum);
		this.getPropertyList().add(emailAddress);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PartyOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PartyOldBO.BIRTHDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyOldBO.BIRTHDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}