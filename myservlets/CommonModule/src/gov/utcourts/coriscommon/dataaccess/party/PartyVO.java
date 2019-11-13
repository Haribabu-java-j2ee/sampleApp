package gov.utcourts.coriscommon.dataaccess.party;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyVO extends BaseVO { 

	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PartyBO.PARTYNUM.clear()).addForeignKey("account","party_num",true).addForeignKey("acct_bail","post_party_num",true).addForeignKey("acct_bond","post_party_num",true).addForeignKey("acct_trust","payee_party_num",true).addForeignKey("acct_trust","payor_party_num",true).addForeignKey("check_detl","payee_num",false).addForeignKey("civil_case","title_def_num",true).addForeignKey("civil_case","title_pla_num",true).addForeignKey("common_party","party_num",false).addForeignKey("county","party_num",true).addForeignKey("dc_account","party_num",true).addForeignKey("dc_acct_trust","payee_party_num",true).addForeignKey("dc_acct_trust","payor_party_num",true).addForeignKey("disc_notice_print","party_num",true).addForeignKey("doc_issue_party","party_num",false).addForeignKey("doc_judgment_info","debtor_party_num",false).addForeignKey("doc_motion","party_num",false).addForeignKey("doc_return_party","party_num",false).addForeignKey("document_due_date","party_num",false).addForeignKey("efiling_party","party_num",false).addForeignKey("joint_sev","payee_party_num",false).addForeignKey("mail_lbl","party_num",true).addForeignKey("party_case","party_num",false).addForeignKey("party_identifier","party_num",true).addForeignKey("prosec_agency","party_num",false).addForeignKey("trans","payor_party_num",true).addForeignKey("warrant","party_num",false).addForeignKey("work_cal_case","title_def_num",true).addForeignKey("work_cal_case","title_pla_num",true).setAsPrimaryKey();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(PartyBO.LASTNAME.clear());
	private TypeString lastSndx = new TypeString("last_sndx").setFieldDescriptor(PartyBO.LASTSNDX.clear()).setNullable();
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(PartyBO.FIRSTNAME.clear()).setNullable();
	private TypeString firstSndx = new TypeString("first_sndx").setFieldDescriptor(PartyBO.FIRSTSNDX.clear()).setNullable();
	private TypeString ssn = new TypeString("ssn").setFieldDescriptor(PartyBO.SSN.clear()).setNullable();
	private TypeString gender = new TypeString("gender").setFieldDescriptor(PartyBO.GENDER.clear()).addForeignKey("gender_defn","gender_code",false).setNullable();
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(PartyBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(PartyBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(PartyBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(PartyBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(PartyBO.ZIPCODE.clear()).setNullable();
	private TypeString country = new TypeString("country").setFieldDescriptor(PartyBO.COUNTRY.clear()).setNullable();
	private TypeString drLicNum = new TypeString("dr_lic_num").setFieldDescriptor(PartyBO.DRLICNUM.clear()).setNullable();
	private TypeString drLicState = new TypeString("dr_lic_state").setFieldDescriptor(PartyBO.DRLICSTATE.clear()).setNullable();
	private TypeString homePhone = new TypeString("home_phone").setFieldDescriptor(PartyBO.HOMEPHONE.clear()).setNullable();
	private TypeString busPhone = new TypeString("bus_phone").setFieldDescriptor(PartyBO.BUSPHONE.clear()).setNullable();
	private TypeString faxNum = new TypeString("fax_num").setFieldDescriptor(PartyBO.FAXNUM.clear()).setNullable();
	private TypeDate birthDate = new TypeDate("birth_date").setFieldDescriptor(PartyBO.BIRTHDATE.clear()).setNullable();
	private TypeString employerName = new TypeString("employer_name").setFieldDescriptor(PartyBO.EMPLOYERNAME.clear()).setNullable();
	private TypeString raceCode = new TypeString("race_code").setFieldDescriptor(PartyBO.RACECODE.clear()).setNullable();
	private TypeString language = new TypeString("language").setFieldDescriptor(PartyBO.LANGUAGE.clear()).setNullable();
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(PartyBO.LANGID.clear()).addForeignKey("language","lang_id",false).setNullable();
	private TypeString disabled = new TypeString("disabled").setFieldDescriptor(PartyBO.DISABLED.clear());
	private TypeString empAddr = new TypeString("emp_addr").setFieldDescriptor(PartyBO.EMPADDR.clear()).setNullable();
	private TypeString commercialDl = new TypeString("commercial_dl").setFieldDescriptor(PartyBO.COMMERCIALDL.clear()).setNullable();
	private TypeString inmateNum = new TypeString("inmate_num").setFieldDescriptor(PartyBO.INMATENUM.clear()).setNullable();
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(PartyBO.EMAILADDRESS.clear()).setNullable();

	public PartyVO() {
		super(PartyBO.TABLE, PartyBO.SYSTEM, PartyBO.CORIS_DISTRICT_DB.setSource("D"), PartyBO.CORIS_JUSTICE_DB.setSource("J"), PartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyVO(String source) {
		super(PartyBO.TABLE, PartyBO.SYSTEM, PartyBO.CORIS_DISTRICT_DB.setSource("D"), PartyBO.CORIS_JUSTICE_DB.setSource("J"), PartyBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
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
			((TypeDate) this.getPropertyList().get(PartyBO.BIRTHDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyBO.BIRTHDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}