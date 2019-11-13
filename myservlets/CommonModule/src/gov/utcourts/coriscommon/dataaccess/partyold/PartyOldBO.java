package gov.utcourts.coriscommon.dataaccess.partyold;

import gov.utcourts.courtscommon.dispatcher.SearchDispatcher;
import gov.utcourts.courtscommon.dataaccess.base.BaseBO;
import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.Expression;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FieldOperationDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.ForeignKeyDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.FromDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.GroupByCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.IntoDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.JoinFindDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.NativeDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortCustomDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SortOrdinalDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.StringLiteral;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.SystemDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableAndFieldDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.TableDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.UnionDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.WhereDescriptor;
import gov.utcourts.courtscommon.dataaccess.base.descriptor.test.TestingContainer;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.DirectionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.JoinType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Operation;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.UnionType;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.Exp;
import gov.utcourts.courtscommon.dataaccess.base.enumeration.FieldOperationType;
import gov.utcourts.courtscommon.dataaccess.connection.ConnectionProperties;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dispatcher.BaseSearchDispatcher;
import gov.utcourts.courtscommon.dispatcher.PropertyFileHelper;
import gov.utcourts.courtscommon.util.TextUtil;

import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyOldBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("party_old", "gov.utcourts.coriscommon.dataaccess.partyold.PartyOld");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACTIONTYPE = new FieldDescriptor(TABLE, "action_type", "setActionType", 0);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 1);
	public static final FieldDescriptor CHANGEDATETIME = new FieldDescriptor(TABLE, "change_datetime", "setChangeDatetime", 2);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 3);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 4);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 5);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 6);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 7);
	public static final FieldDescriptor LASTNAME = new FieldDescriptor(TABLE, "last_name", "setLastName", 8);
	public static final FieldDescriptor LASTSNDX = new FieldDescriptor(TABLE, "last_sndx", "setLastSndx", 9);
	public static final FieldDescriptor FIRSTNAME = new FieldDescriptor(TABLE, "first_name", "setFirstName", 10);
	public static final FieldDescriptor FIRSTSNDX = new FieldDescriptor(TABLE, "first_sndx", "setFirstSndx", 11);
	public static final FieldDescriptor SSN = new FieldDescriptor(TABLE, "ssn", "setSsn", 12);
	public static final FieldDescriptor GENDER = new FieldDescriptor(TABLE, "gender", "setGender", 13);
	public static final FieldDescriptor ADDRESS1 = new FieldDescriptor(TABLE, "address_1", "setAddress1", 14);
	public static final FieldDescriptor ADDRESS2 = new FieldDescriptor(TABLE, "address_2", "setAddress2", 15);
	public static final FieldDescriptor CITY = new FieldDescriptor(TABLE, "city", "setCity", 16);
	public static final FieldDescriptor STATECODE = new FieldDescriptor(TABLE, "state_code", "setStateCode", 17);
	public static final FieldDescriptor ZIPCODE = new FieldDescriptor(TABLE, "zip_code", "setZipCode", 18);
	public static final FieldDescriptor COUNTRY = new FieldDescriptor(TABLE, "country", "setCountry", 19);
	public static final FieldDescriptor DRLICNUM = new FieldDescriptor(TABLE, "dr_lic_num", "setDrLicNum", 20);
	public static final FieldDescriptor DRLICSTATE = new FieldDescriptor(TABLE, "dr_lic_state", "setDrLicState", 21);
	public static final FieldDescriptor HOMEPHONE = new FieldDescriptor(TABLE, "home_phone", "setHomePhone", 22);
	public static final FieldDescriptor BUSPHONE = new FieldDescriptor(TABLE, "bus_phone", "setBusPhone", 23);
	public static final FieldDescriptor FAXNUM = new FieldDescriptor(TABLE, "fax_num", "setFaxNum", 24);
	public static final FieldDescriptor BIRTHDATE = new FieldDescriptor(TABLE, "birth_date", "setBirthDate", 25);
	public static final FieldDescriptor EMPLOYERNAME = new FieldDescriptor(TABLE, "employer_name", "setEmployerName", 26);
	public static final FieldDescriptor RACECODE = new FieldDescriptor(TABLE, "race_code", "setRaceCode", 27);
	public static final FieldDescriptor LANGUAGE = new FieldDescriptor(TABLE, "language", "setLanguage", 28);
	public static final FieldDescriptor LANGID = new FieldDescriptor(TABLE, "lang_id", "setLangId", 29);
	public static final FieldDescriptor DISABLED = new FieldDescriptor(TABLE, "disabled", "setDisabled", 30);
	public static final FieldDescriptor EMPADDR = new FieldDescriptor(TABLE, "emp_addr", "setEmpAddr", 31);
	public static final FieldDescriptor COMMERCIALDL = new FieldDescriptor(TABLE, "commercial_dl", "setCommercialDl", 32);
	public static final FieldDescriptor INMATENUM = new FieldDescriptor(TABLE, "inmate_num", "setInmateNum", 33);
	public static final FieldDescriptor EMAILADDRESS = new FieldDescriptor(TABLE, "email_address", "setEmailAddress", 34);

	public PartyOldBO() {
		super(new PartyOldVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public PartyOldBO(String source) {
		super(new PartyOldVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public PartyOldBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getActionType() {
		return getBaseVO().getTypeString(ACTIONTYPE).getValue();
	}
	
	public PartyOldBO setActionType(String actionType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONTYPE, actionType);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public PartyOldBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public Date getChangeDatetime() {
		return getBaseVO().getTypeDate(CHANGEDATETIME).getValue();
	}
	
	public PartyOldBO setChangeDatetime(Date changeDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime);
		return this;
	}
	
	public PartyOldBO setChangeDatetime(Date changeDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime, dateFormat);
		getBaseVO().setAttribute(CHANGEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public PartyOldBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public PartyOldBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public PartyOldBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public PartyOldBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public PartyOldBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public String getLastName() {
		return getBaseVO().getTypeString(LASTNAME).getValue();
	}
	
	public PartyOldBO setLastName(String lastName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTNAME, lastName);
		return this;
	}

	public String getLastSndx() {
		return getBaseVO().getTypeString(LASTSNDX).getValue();
	}
	
	public PartyOldBO setLastSndx(String lastSndx) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTSNDX, lastSndx);
		return this;
	}

	public String getFirstName() {
		return getBaseVO().getTypeString(FIRSTNAME).getValue();
	}
	
	public PartyOldBO setFirstName(String firstName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FIRSTNAME, firstName);
		return this;
	}

	public String getFirstSndx() {
		return getBaseVO().getTypeString(FIRSTSNDX).getValue();
	}
	
	public PartyOldBO setFirstSndx(String firstSndx) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FIRSTSNDX, firstSndx);
		return this;
	}

	public String getSsn() {
		return getBaseVO().getTypeString(SSN).getValue();
	}
	
	public PartyOldBO setSsn(String ssn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SSN, ssn);
		return this;
	}

	public String getGender() {
		return getBaseVO().getTypeString(GENDER).getValue();
	}
	
	public PartyOldBO setGender(String gender) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GENDER, gender);
		return this;
	}

	public String getAddress1() {
		return getBaseVO().getTypeString(ADDRESS1).getValue();
	}
	
	public PartyOldBO setAddress1(String address1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS1, address1);
		return this;
	}

	public String getAddress2() {
		return getBaseVO().getTypeString(ADDRESS2).getValue();
	}
	
	public PartyOldBO setAddress2(String address2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS2, address2);
		return this;
	}

	public String getCity() {
		return getBaseVO().getTypeString(CITY).getValue();
	}
	
	public PartyOldBO setCity(String city) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITY, city);
		return this;
	}

	public String getStateCode() {
		return getBaseVO().getTypeString(STATECODE).getValue();
	}
	
	public PartyOldBO setStateCode(String stateCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATECODE, stateCode);
		return this;
	}

	public String getZipCode() {
		return getBaseVO().getTypeString(ZIPCODE).getValue();
	}
	
	public PartyOldBO setZipCode(String zipCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ZIPCODE, zipCode);
		return this;
	}

	public String getCountry() {
		return getBaseVO().getTypeString(COUNTRY).getValue();
	}
	
	public PartyOldBO setCountry(String country) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COUNTRY, country);
		return this;
	}

	public String getDrLicNum() {
		return getBaseVO().getTypeString(DRLICNUM).getValue();
	}
	
	public PartyOldBO setDrLicNum(String drLicNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICNUM, drLicNum);
		return this;
	}

	public String getDrLicState() {
		return getBaseVO().getTypeString(DRLICSTATE).getValue();
	}
	
	public PartyOldBO setDrLicState(String drLicState) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICSTATE, drLicState);
		return this;
	}

	public String getHomePhone() {
		return getBaseVO().getTypeString(HOMEPHONE).getValue();
	}
	
	public PartyOldBO setHomePhone(String homePhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HOMEPHONE, homePhone);
		return this;
	}

	public String getBusPhone() {
		return getBaseVO().getTypeString(BUSPHONE).getValue();
	}
	
	public PartyOldBO setBusPhone(String busPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BUSPHONE, busPhone);
		return this;
	}

	public String getFaxNum() {
		return getBaseVO().getTypeString(FAXNUM).getValue();
	}
	
	public PartyOldBO setFaxNum(String faxNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FAXNUM, faxNum);
		return this;
	}

	public Date getBirthDate() {
		return getBaseVO().getTypeDate(BIRTHDATE).getValue();
	}
	
	public PartyOldBO setBirthDate(Date birthDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BIRTHDATE, birthDate);
		return this;
	}
	
	public PartyOldBO setBirthDate(Date birthDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BIRTHDATE, birthDate, dateFormat);
		getBaseVO().setAttribute(BIRTHDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getEmployerName() {
		return getBaseVO().getTypeString(EMPLOYERNAME).getValue();
	}
	
	public PartyOldBO setEmployerName(String employerName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EMPLOYERNAME, employerName);
		return this;
	}

	public String getRaceCode() {
		return getBaseVO().getTypeString(RACECODE).getValue();
	}
	
	public PartyOldBO setRaceCode(String raceCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RACECODE, raceCode);
		return this;
	}

	public String getLanguage() {
		return getBaseVO().getTypeString(LANGUAGE).getValue();
	}
	
	public PartyOldBO setLanguage(String language) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LANGUAGE, language);
		return this;
	}

	public int getLangId() {
		return getBaseVO().getTypeInteger(LANGID).getValue();
	}
	
	public PartyOldBO setLangId(Integer langId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LANGID, langId);
		return this;
	}

	public String getDisabled() {
		return getBaseVO().getTypeString(DISABLED).getValue();
	}
	
	public PartyOldBO setDisabled(String disabled) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISABLED, disabled);
		return this;
	}

	public String getEmpAddr() {
		return getBaseVO().getTypeString(EMPADDR).getValue();
	}
	
	public PartyOldBO setEmpAddr(String empAddr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EMPADDR, empAddr);
		return this;
	}

	public String getCommercialDl() {
		return getBaseVO().getTypeString(COMMERCIALDL).getValue();
	}
	
	public PartyOldBO setCommercialDl(String commercialDl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMMERCIALDL, commercialDl);
		return this;
	}

	public String getInmateNum() {
		return getBaseVO().getTypeString(INMATENUM).getValue();
	}
	
	public PartyOldBO setInmateNum(String inmateNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INMATENUM, inmateNum);
		return this;
	}

	public String getEmailAddress() {
		return getBaseVO().getTypeString(EMAILADDRESS).getValue();
	}
	
	public PartyOldBO setEmailAddress(String emailAddress) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EMAILADDRESS, emailAddress);
		return this;
	}
	
	public PartyOldBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<PartyOldBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new PartyOldBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new PartyOldBO(getBaseVO());
		}
	}
	
	public PartyOldBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public PartyOldBO insert(FieldDescriptor... specificFields) throws Exception {
		return new PartyOldBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public PartyOldBO update(FieldDescriptor... specificFields) throws Exception {
		return new PartyOldBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public PartyOldBO delete() throws Exception {
		return new PartyOldBO(super.delete(getNativeDescriptor()));
	}
	
	public PartyOldBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PartyOldBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PartyOldBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public PartyOldBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public PartyOldBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public PartyOldBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public PartyOldBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PartyOldBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PartyOldBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public PartyOldBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyOldBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyOldBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public PartyOldBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public PartyOldBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public PartyOldBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyOldBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyOldBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public PartyOldBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PartyOldBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public PartyOldBO where(FieldDescriptor findBy, Object value) {
		if (findBy instanceof FieldDescriptor && value instanceof StringLiteral) {
			setFinder(new FindDescriptor(new Expression(findBy, value)));
		} else if (findBy instanceof Expression && value instanceof Expression) {
			setFinder(new FindDescriptor(findBy));
			setFinder(new FindDescriptor((Expression) value));
		} else {
			setFinder(findBy, value, null, null);
		}
		return this;
	}
	
	public PartyOldBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PartyOldBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public PartyOldBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public PartyOldBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public PartyOldBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.COUNT.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public int getCount() throws Exception {
		Object countValue = get(FieldOperationType.COUNT.toString());
		if (countValue instanceof String) 
			return Integer.parseInt((String) get(FieldOperationType.COUNT.toString()));
		else if (countValue instanceof Integer)
			return ((Integer) get(FieldOperationType.COUNT.toString()));
		else 
			return 0;
	}
	
	public PartyOldBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public PartyOldBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public PartyOldBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public PartyOldBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public PartyOldBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public PartyOldBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public PartyOldBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public PartyOldBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public PartyOldBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public PartyOldBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public PartyOldBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyOldBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
		BaseSearchDispatcher searchDispatcher = getSearchDispatcher().search(fieldDescriptors);
		return searchDispatcher.returnNull() ? null : searchDispatcher.getResults();
	}
	
	@SuppressWarnings("rawtypes")
	public List searchAndGetResults(FieldDescriptor... fieldDescriptors) throws Exception {
		search(fieldDescriptors);
		return getSearchDispatcher().returnNull() ? null : getSearchDispatcher().getResults();
	}
	
	@SuppressWarnings("rawtypes")
	public List searchAndGetColumnResults(FieldDescriptor columnObject) throws Exception {
		BaseSearchDispatcher searchDispatcher = getSearchDispatcher().search(columnObject);
		return getSearchDispatcher().returnNull() ? null : searchDispatcher.getColumnResults(columnObject);
	}
	
	public PartyOldBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public PartyOldBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PartyOldBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public PartyOldBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public PartyOldBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public PartyOldBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public PartyOldBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public PartyOldBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public PartyOldBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public PartyOldBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public PartyOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public PartyOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PartyOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PartyOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PartyOldBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public PartyOldBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public PartyOldBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public PartyOldBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
		getBaseVO().setAttribute(fieldDescriptor.getDateFormatterKey(), dateFormat);
		getBaseVO().setPropertyListValue(SET_BY_USER, fieldDescriptor, null, dateFormat);
		return this;
	}
	
	public String getSql() throws Exception {
		return getSql(Operation.SEARCH, (FieldDescriptor[]) null);
	}
	public String getSql(Operation operation, FieldDescriptor... specificFields) throws Exception {
		switch (operation) {
			case FIND:
			case INSERT: 
			case UPDATE: 
			case DELETE: 
				return super.getSql(operation, getNativeDescriptor(), getBaseVO(), specificFields);
	
			case SEARCH: return getSearchDispatcher().getSql(specificFields);
		}
		return ".getSql operation not supported - " + operation.toString();
	}
	
	private NativeDescriptor getNativeDescriptor() {
		return (searchDispatcher != null) ? searchDispatcher.getSearchDispatcherParameters().getNativeDescriptor() : null;
	}
	
	public PartyOldBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public PartyOldBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public PartyOldBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public PartyOldBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public PartyOldBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PartyOldBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PartyOldBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public PartyOldBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative PartyOldBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public PartyOldBO setQuestionMarks(Object... values) {
		getSearchDispatcher().getSearchDispatcherParameters().getNativeDescriptor().setQuestionMarks(values);
		return this;
	}
	
	public Object getAttribute(FieldDescriptor fieldDescriptor) {
		return getBaseVO().getAttribute(fieldDescriptor.getKey());
	}
	
	public Object getAttribute(String key) {
		return getBaseVO().getAttribute(key);
	}
	public Object setAttribute(String key, Object value) {
		return getBaseVO().setAttribute(key, value);
	}
	public PartyOldBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public PartyOldBO setIsolationDirtyRead(boolean isolationDirtyRead) {
		getBaseVO().setIsolationDirtyRead(isolationDirtyRead);
		return this;
	}
	
	public int getToString() {
		return getBaseVO().getToString();
	}
	
	public Object get(Object descriptorObject) throws Exception {
		return getBaseVO().get(descriptorObject);
	}
	
	@SuppressWarnings("rawtypes")
	public List<FieldType> getPropertyList() {
		return getBaseVO().getPropertyList();
	}
	
	@SuppressWarnings("rawtypes")
	public PartyOldBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public PartyOldBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public PartyOldBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public PartyOldBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public PartyOldBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public PartyOldBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public PartyOldBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public PartyOldBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public PartyOldBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public PartyOldBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public PartyOldBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}