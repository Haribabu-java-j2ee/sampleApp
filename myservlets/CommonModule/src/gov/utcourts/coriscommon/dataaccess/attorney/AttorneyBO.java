package gov.utcourts.coriscommon.dataaccess.attorney;

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
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AttorneyBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("attorney", "gov.utcourts.coriscommon.dataaccess.attorney.Attorney");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor BARNUM = new FieldDescriptor(TABLE, "bar_num", "setBarNum", 0);
	public static final FieldDescriptor BARSTATE = new FieldDescriptor(TABLE, "bar_state", "setBarState", 1);
	public static final FieldDescriptor PREFIX = new FieldDescriptor(TABLE, "prefix", "setPrefix", 2);
	public static final FieldDescriptor LASTNAME = new FieldDescriptor(TABLE, "last_name", "setLastName", 3);
	public static final FieldDescriptor MIDDLEINITIAL = new FieldDescriptor(TABLE, "middle_initial", "setMiddleInitial", 4);
	public static final FieldDescriptor FIRSTNAME = new FieldDescriptor(TABLE, "first_name", "setFirstName", 5);
	public static final FieldDescriptor ORGANIZATION = new FieldDescriptor(TABLE, "organization", "setOrganization", 6);
	public static final FieldDescriptor ADDRESS1 = new FieldDescriptor(TABLE, "address_1", "setAddress1", 7);
	public static final FieldDescriptor ADDRESS2 = new FieldDescriptor(TABLE, "address_2", "setAddress2", 8);
	public static final FieldDescriptor ADDRESS3 = new FieldDescriptor(TABLE, "address_3", "setAddress3", 9);
	public static final FieldDescriptor CITY = new FieldDescriptor(TABLE, "city", "setCity", 10);
	public static final FieldDescriptor STATECODE = new FieldDescriptor(TABLE, "state_code", "setStateCode", 11);
	public static final FieldDescriptor ZIPCODE = new FieldDescriptor(TABLE, "zip_code", "setZipCode", 12);
	public static final FieldDescriptor COUNTRY = new FieldDescriptor(TABLE, "country", "setCountry", 13);
	public static final FieldDescriptor HOMEPHONE = new FieldDescriptor(TABLE, "home_phone", "setHomePhone", 14);
	public static final FieldDescriptor BUSPHONE = new FieldDescriptor(TABLE, "bus_phone", "setBusPhone", 15);
	public static final FieldDescriptor FAXNUM = new FieldDescriptor(TABLE, "fax_num", "setFaxNum", 16);
	public static final FieldDescriptor CELLPHONE = new FieldDescriptor(TABLE, "cell_phone", "setCellPhone", 17);
	public static final FieldDescriptor BARSTATUS = new FieldDescriptor(TABLE, "bar_status", "setBarStatus", 18);
	public static final FieldDescriptor EMAILADDRESS = new FieldDescriptor(TABLE, "email_address", "setEmailAddress", 19);

	public AttorneyBO() {
		super(new AttorneyVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public AttorneyBO(String source) {
		super(new AttorneyVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public AttorneyBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getBarNum() {
		return getBaseVO().getTypeInteger(BARNUM).getValue();
	}
	
	public AttorneyBO setBarNum(Integer barNum) {
		setPrimaryKey(BARNUM, barNum);
		return this;
	}

	public String getBarState() {
		return getBaseVO().getTypeString(BARSTATE).getValue();
	}
	
	public AttorneyBO setBarState(String barState) {
		setPrimaryKey(BARSTATE, barState);
		return this;
	}

	public String getPrefix() {
		return getBaseVO().getTypeString(PREFIX).getValue();
	}
	
	public AttorneyBO setPrefix(String prefix) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PREFIX, prefix);
		return this;
	}

	public String getLastName() {
		return getBaseVO().getTypeString(LASTNAME).getValue();
	}
	
	public AttorneyBO setLastName(String lastName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTNAME, lastName);
		return this;
	}

	public String getMiddleInitial() {
		return getBaseVO().getTypeString(MIDDLEINITIAL).getValue();
	}
	
	public AttorneyBO setMiddleInitial(String middleInitial) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MIDDLEINITIAL, middleInitial);
		return this;
	}

	public String getFirstName() {
		return getBaseVO().getTypeString(FIRSTNAME).getValue();
	}
	
	public AttorneyBO setFirstName(String firstName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FIRSTNAME, firstName);
		return this;
	}

	public String getOrganization() {
		return getBaseVO().getTypeString(ORGANIZATION).getValue();
	}
	
	public AttorneyBO setOrganization(String organization) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORGANIZATION, organization);
		return this;
	}

	public String getAddress1() {
		return getBaseVO().getTypeString(ADDRESS1).getValue();
	}
	
	public AttorneyBO setAddress1(String address1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS1, address1);
		return this;
	}

	public String getAddress2() {
		return getBaseVO().getTypeString(ADDRESS2).getValue();
	}
	
	public AttorneyBO setAddress2(String address2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS2, address2);
		return this;
	}

	public String getAddress3() {
		return getBaseVO().getTypeString(ADDRESS3).getValue();
	}
	
	public AttorneyBO setAddress3(String address3) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS3, address3);
		return this;
	}

	public String getCity() {
		return getBaseVO().getTypeString(CITY).getValue();
	}
	
	public AttorneyBO setCity(String city) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITY, city);
		return this;
	}

	public String getStateCode() {
		return getBaseVO().getTypeString(STATECODE).getValue();
	}
	
	public AttorneyBO setStateCode(String stateCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATECODE, stateCode);
		return this;
	}

	public String getZipCode() {
		return getBaseVO().getTypeString(ZIPCODE).getValue();
	}
	
	public AttorneyBO setZipCode(String zipCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ZIPCODE, zipCode);
		return this;
	}

	public String getCountry() {
		return getBaseVO().getTypeString(COUNTRY).getValue();
	}
	
	public AttorneyBO setCountry(String country) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COUNTRY, country);
		return this;
	}

	public String getHomePhone() {
		return getBaseVO().getTypeString(HOMEPHONE).getValue();
	}
	
	public AttorneyBO setHomePhone(String homePhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HOMEPHONE, homePhone);
		return this;
	}

	public String getBusPhone() {
		return getBaseVO().getTypeString(BUSPHONE).getValue();
	}
	
	public AttorneyBO setBusPhone(String busPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BUSPHONE, busPhone);
		return this;
	}

	public String getFaxNum() {
		return getBaseVO().getTypeString(FAXNUM).getValue();
	}
	
	public AttorneyBO setFaxNum(String faxNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FAXNUM, faxNum);
		return this;
	}

	public String getCellPhone() {
		return getBaseVO().getTypeString(CELLPHONE).getValue();
	}
	
	public AttorneyBO setCellPhone(String cellPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CELLPHONE, cellPhone);
		return this;
	}

	public String getBarStatus() {
		return getBaseVO().getTypeString(BARSTATUS).getValue();
	}
	
	public AttorneyBO setBarStatus(String barStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BARSTATUS, barStatus);
		return this;
	}

	public String getEmailAddress() {
		return getBaseVO().getTypeString(EMAILADDRESS).getValue();
	}
	
	public AttorneyBO setEmailAddress(String emailAddress) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EMAILADDRESS, emailAddress);
		return this;
	}
	
	public AttorneyBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<AttorneyBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new AttorneyBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new AttorneyBO(getBaseVO());
		}
	}
	
	public AttorneyBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public AttorneyBO insert(FieldDescriptor... specificFields) throws Exception {
		return new AttorneyBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public AttorneyBO update(FieldDescriptor... specificFields) throws Exception {
		return new AttorneyBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public AttorneyBO delete() throws Exception {
		return new AttorneyBO(super.delete(getNativeDescriptor()));
	}
	
	public AttorneyBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AttorneyBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AttorneyBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public AttorneyBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public AttorneyBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public AttorneyBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public AttorneyBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AttorneyBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AttorneyBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public AttorneyBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AttorneyBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AttorneyBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public AttorneyBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public AttorneyBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public AttorneyBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AttorneyBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AttorneyBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public AttorneyBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AttorneyBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public AttorneyBO where(FieldDescriptor findBy, Object value) {
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
	
	public AttorneyBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AttorneyBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public AttorneyBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public AttorneyBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public AttorneyBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public AttorneyBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public AttorneyBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public AttorneyBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public AttorneyBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public AttorneyBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public AttorneyBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public AttorneyBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public AttorneyBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public AttorneyBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public AttorneyBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public AttorneyBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<AttorneyBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public AttorneyBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public AttorneyBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AttorneyBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public AttorneyBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public AttorneyBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public AttorneyBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public AttorneyBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public AttorneyBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public AttorneyBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public AttorneyBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public AttorneyBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public AttorneyBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AttorneyBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AttorneyBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AttorneyBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public AttorneyBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public AttorneyBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public AttorneyBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public AttorneyBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public AttorneyBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public AttorneyBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public AttorneyBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public AttorneyBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AttorneyBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AttorneyBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public AttorneyBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative AttorneyBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public AttorneyBO setQuestionMarks(Object... values) {
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
	public AttorneyBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public AttorneyBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public AttorneyBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public AttorneyBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public AttorneyBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public AttorneyBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public AttorneyBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public AttorneyBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public AttorneyBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public AttorneyBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public AttorneyBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public AttorneyBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public AttorneyBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}