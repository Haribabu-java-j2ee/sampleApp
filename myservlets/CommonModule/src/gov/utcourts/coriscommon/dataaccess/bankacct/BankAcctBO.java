package gov.utcourts.coriscommon.dataaccess.bankacct;

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

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class BankAcctBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("bank_acct", "gov.utcourts.coriscommon.dataaccess.bankacct.BankAcct");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor BANKACCTNUM = new FieldDescriptor(TABLE, "bank_acct_num", "setBankAcctNum", 0);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 1);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 2);
	public static final FieldDescriptor TYPE = new FieldDescriptor(TABLE, "type", "setType", 3);
	public static final FieldDescriptor BEGINBAL = new FieldDescriptor(TABLE, "begin_bal", "setBeginBal", 4);
	public static final FieldDescriptor CLOSEDFLAG = new FieldDescriptor(TABLE, "closed_flag", "setClosedFlag", 5);
	public static final FieldDescriptor BANKNAME = new FieldDescriptor(TABLE, "bank_name", "setBankName", 6);
	public static final FieldDescriptor ADDRESS1 = new FieldDescriptor(TABLE, "address_1", "setAddress1", 7);
	public static final FieldDescriptor ADDRESS2 = new FieldDescriptor(TABLE, "address_2", "setAddress2", 8);
	public static final FieldDescriptor CITY = new FieldDescriptor(TABLE, "city", "setCity", 9);
	public static final FieldDescriptor STATECODE = new FieldDescriptor(TABLE, "state_code", "setStateCode", 10);
	public static final FieldDescriptor ZIPCODE = new FieldDescriptor(TABLE, "zip_code", "setZipCode", 11);
	public static final FieldDescriptor ROUTINGNUM = new FieldDescriptor(TABLE, "routing_num", "setRoutingNum", 12);
	public static final FieldDescriptor TRANSITNUM = new FieldDescriptor(TABLE, "transit_num", "setTransitNum", 13);
	public static final FieldDescriptor NEXTCHECKNUM = new FieldDescriptor(TABLE, "next_check_num", "setNextCheckNum", 14);

	public BankAcctBO() {
		super(new BankAcctVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public BankAcctBO(String source) {
		super(new BankAcctVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public BankAcctBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getBankAcctNum() {
		return getBaseVO().getTypeString(BANKACCTNUM).getValue();
	}
	
	public BankAcctBO setBankAcctNum(String bankAcctNum) {
		setPrimaryKey(BANKACCTNUM, bankAcctNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public BankAcctBO setLocnCode(String locnCode) {
		setPrimaryKey(LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public BankAcctBO setCourtType(String courtType) {
		setPrimaryKey(COURTTYPE, courtType);
		return this;
	}

	public String getType() {
		return getBaseVO().getTypeString(TYPE).getValue();
	}
	
	public BankAcctBO setType(String type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TYPE, type);
		return this;
	}

	public BigDecimal getBeginBal() {
		return getBaseVO().getTypeBigDecimal(BEGINBAL).getValue();
	}
	
	public BankAcctBO setBeginBal(BigDecimal beginBal) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BEGINBAL, beginBal);
		return this;
	}
	
	public BankAcctBO setBeginBal(double beginBal) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BEGINBAL, new BigDecimal(String.valueOf(new Double(beginBal))));
		return this;
	}

	public String getClosedFlag() {
		return getBaseVO().getTypeString(CLOSEDFLAG).getValue();
	}
	
	public BankAcctBO setClosedFlag(String closedFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CLOSEDFLAG, closedFlag);
		return this;
	}

	public String getBankName() {
		return getBaseVO().getTypeString(BANKNAME).getValue();
	}
	
	public BankAcctBO setBankName(String bankName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BANKNAME, bankName);
		return this;
	}

	public String getAddress1() {
		return getBaseVO().getTypeString(ADDRESS1).getValue();
	}
	
	public BankAcctBO setAddress1(String address1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS1, address1);
		return this;
	}

	public String getAddress2() {
		return getBaseVO().getTypeString(ADDRESS2).getValue();
	}
	
	public BankAcctBO setAddress2(String address2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS2, address2);
		return this;
	}

	public String getCity() {
		return getBaseVO().getTypeString(CITY).getValue();
	}
	
	public BankAcctBO setCity(String city) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITY, city);
		return this;
	}

	public String getStateCode() {
		return getBaseVO().getTypeString(STATECODE).getValue();
	}
	
	public BankAcctBO setStateCode(String stateCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATECODE, stateCode);
		return this;
	}

	public String getZipCode() {
		return getBaseVO().getTypeString(ZIPCODE).getValue();
	}
	
	public BankAcctBO setZipCode(String zipCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ZIPCODE, zipCode);
		return this;
	}

	public String getRoutingNum() {
		return getBaseVO().getTypeString(ROUTINGNUM).getValue();
	}
	
	public BankAcctBO setRoutingNum(String routingNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ROUTINGNUM, routingNum);
		return this;
	}

	public String getTransitNum() {
		return getBaseVO().getTypeString(TRANSITNUM).getValue();
	}
	
	public BankAcctBO setTransitNum(String transitNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRANSITNUM, transitNum);
		return this;
	}

	public int getNextCheckNum() {
		return getBaseVO().getTypeInteger(NEXTCHECKNUM).getValue();
	}
	
	public BankAcctBO setNextCheckNum(Integer nextCheckNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NEXTCHECKNUM, nextCheckNum);
		return this;
	}
	
	public BankAcctBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<BankAcctBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new BankAcctBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new BankAcctBO(getBaseVO());
		}
	}
	
	public BankAcctBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public BankAcctBO insert(FieldDescriptor... specificFields) throws Exception {
		return new BankAcctBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public BankAcctBO update(FieldDescriptor... specificFields) throws Exception {
		return new BankAcctBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public BankAcctBO delete() throws Exception {
		return new BankAcctBO(super.delete(getNativeDescriptor()));
	}
	
	public BankAcctBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public BankAcctBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public BankAcctBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public BankAcctBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public BankAcctBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public BankAcctBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public BankAcctBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public BankAcctBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public BankAcctBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public BankAcctBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BankAcctBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BankAcctBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public BankAcctBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public BankAcctBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public BankAcctBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BankAcctBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public BankAcctBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public BankAcctBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public BankAcctBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public BankAcctBO where(FieldDescriptor findBy, Object value) {
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
	
	public BankAcctBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public BankAcctBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public BankAcctBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public BankAcctBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public BankAcctBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public BankAcctBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public BankAcctBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public BankAcctBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public BankAcctBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public BankAcctBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public BankAcctBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public BankAcctBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public BankAcctBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public BankAcctBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public BankAcctBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public BankAcctBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<BankAcctBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public BankAcctBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public BankAcctBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public BankAcctBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public BankAcctBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public BankAcctBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public BankAcctBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public BankAcctBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public BankAcctBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public BankAcctBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public BankAcctBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public BankAcctBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public BankAcctBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public BankAcctBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public BankAcctBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public BankAcctBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public BankAcctBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public BankAcctBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public BankAcctBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public BankAcctBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public BankAcctBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public BankAcctBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public BankAcctBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public BankAcctBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public BankAcctBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public BankAcctBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public BankAcctBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative BankAcctBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public BankAcctBO setQuestionMarks(Object... values) {
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
	public BankAcctBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public BankAcctBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public BankAcctBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public BankAcctBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public BankAcctBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public BankAcctBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public BankAcctBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public BankAcctBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public BankAcctBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public BankAcctBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public BankAcctBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public BankAcctBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public BankAcctBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}