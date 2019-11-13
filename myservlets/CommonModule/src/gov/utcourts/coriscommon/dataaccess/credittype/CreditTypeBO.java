package gov.utcourts.coriscommon.dataaccess.credittype;

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
public class CreditTypeBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("credit_type", "gov.utcourts.coriscommon.dataaccess.credittype.CreditType");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor CREDITCODE = new FieldDescriptor(TABLE, "credit_code", "setCreditCode", 0);
	public static final FieldDescriptor DESCR = new FieldDescriptor(TABLE, "descr", "setDescr", 1);
	public static final FieldDescriptor VALIDCOURT = new FieldDescriptor(TABLE, "valid_court", "setValidCourt", 2);
	public static final FieldDescriptor REMOVEDFLAG = new FieldDescriptor(TABLE, "removed_flag", "setRemovedFlag", 3);

	public CreditTypeBO() {
		super(new CreditTypeVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public CreditTypeBO(String source) {
		super(new CreditTypeVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public CreditTypeBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getCreditCode() {
		return getBaseVO().getTypeString(CREDITCODE).getValue();
	}
	
	public CreditTypeBO setCreditCode(String creditCode) {
		setPrimaryKey(CREDITCODE, creditCode);
		return this;
	}

	public String getDescr() {
		return getBaseVO().getTypeString(DESCR).getValue();
	}
	
	public CreditTypeBO setDescr(String descr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DESCR, descr);
		return this;
	}

	public String getValidCourt() {
		return getBaseVO().getTypeString(VALIDCOURT).getValue();
	}
	
	public CreditTypeBO setValidCourt(String validCourt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VALIDCOURT, validCourt);
		return this;
	}

	public String getRemovedFlag() {
		return getBaseVO().getTypeString(REMOVEDFLAG).getValue();
	}
	
	public CreditTypeBO setRemovedFlag(String removedFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REMOVEDFLAG, removedFlag);
		return this;
	}
	
	public CreditTypeBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<CreditTypeBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new CreditTypeBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new CreditTypeBO(getBaseVO());
		}
	}
	
	public CreditTypeBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public CreditTypeBO insert(FieldDescriptor... specificFields) throws Exception {
		return new CreditTypeBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public CreditTypeBO update(FieldDescriptor... specificFields) throws Exception {
		return new CreditTypeBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public CreditTypeBO delete() throws Exception {
		return new CreditTypeBO(super.delete(getNativeDescriptor()));
	}
	
	public CreditTypeBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CreditTypeBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CreditTypeBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public CreditTypeBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public CreditTypeBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public CreditTypeBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public CreditTypeBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CreditTypeBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CreditTypeBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public CreditTypeBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CreditTypeBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CreditTypeBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public CreditTypeBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public CreditTypeBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public CreditTypeBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CreditTypeBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CreditTypeBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public CreditTypeBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CreditTypeBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public CreditTypeBO where(FieldDescriptor findBy, Object value) {
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
	
	public CreditTypeBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CreditTypeBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public CreditTypeBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public CreditTypeBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public CreditTypeBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public CreditTypeBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public CreditTypeBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public CreditTypeBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public CreditTypeBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public CreditTypeBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public CreditTypeBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public CreditTypeBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public CreditTypeBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public CreditTypeBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public CreditTypeBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public CreditTypeBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<CreditTypeBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public CreditTypeBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public CreditTypeBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CreditTypeBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public CreditTypeBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public CreditTypeBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public CreditTypeBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public CreditTypeBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public CreditTypeBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public CreditTypeBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public CreditTypeBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public CreditTypeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public CreditTypeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CreditTypeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CreditTypeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CreditTypeBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public CreditTypeBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public CreditTypeBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public CreditTypeBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public CreditTypeBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public CreditTypeBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public CreditTypeBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public CreditTypeBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public CreditTypeBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CreditTypeBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CreditTypeBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public CreditTypeBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative CreditTypeBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public CreditTypeBO setQuestionMarks(Object... values) {
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
	public CreditTypeBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public CreditTypeBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public CreditTypeBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public CreditTypeBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public CreditTypeBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public CreditTypeBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public CreditTypeBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public CreditTypeBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public CreditTypeBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public CreditTypeBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public CreditTypeBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public CreditTypeBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public CreditTypeBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}