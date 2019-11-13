package gov.utcourts.coriscommon.dataaccess.ssummaryevent;

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
public class SSummaryEventBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("s_summary_event", "gov.utcourts.coriscommon.dataaccess.ssummaryevent.SSummaryEvent");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor SINTCASENUM = new FieldDescriptor(TABLE, "s_int_case_num", "setSIntCaseNum", 0);
	public static final FieldDescriptor SUSERIDSRL = new FieldDescriptor(TABLE, "s_userid_srl", "setSUseridSrl", 1);
	public static final FieldDescriptor SDATETIME = new FieldDescriptor(TABLE, "s_datetime", "setSDatetime", 2);
	public static final FieldDescriptor SMEID = new FieldDescriptor(TABLE, "s_me_id", "setSMeId", 3);
	public static final FieldDescriptor SOPERATION = new FieldDescriptor(TABLE, "s_operation", "setSOperation", 4);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 5);
	public static final FieldDescriptor EVENTDATETIME = new FieldDescriptor(TABLE, "event_datetime", "setEventDatetime", 6);
	public static final FieldDescriptor CREATEDATETIME = new FieldDescriptor(TABLE, "create_datetime", "setCreateDatetime", 7);
	public static final FieldDescriptor FUNCID = new FieldDescriptor(TABLE, "func_id", "setFuncId", 8);
	public static final FieldDescriptor DISPLAYFLAG = new FieldDescriptor(TABLE, "display_flag", "setDisplayFlag", 9);
	public static final FieldDescriptor REMOVEDFLAG = new FieldDescriptor(TABLE, "removed_flag", "setRemovedFlag", 10);
	public static final FieldDescriptor DESCR = new FieldDescriptor(TABLE, "descr", "setDescr", 11);
	public static final FieldDescriptor KEY1 = new FieldDescriptor(TABLE, "key_1", "setKey1", 12);
	public static final FieldDescriptor KEY2 = new FieldDescriptor(TABLE, "key_2", "setKey2", 13);
	public static final FieldDescriptor KEY3 = new FieldDescriptor(TABLE, "key_3", "setKey3", 14);
	public static final FieldDescriptor KEY4 = new FieldDescriptor(TABLE, "key_4", "setKey4", 15);

	public SSummaryEventBO() {
		super(new SSummaryEventVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public SSummaryEventBO(String source) {
		super(new SSummaryEventVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public SSummaryEventBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getSIntCaseNum() {
		return getBaseVO().getTypeInteger(SINTCASENUM).getValue();
	}
	
	public SSummaryEventBO setSIntCaseNum(Integer sIntCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SINTCASENUM, sIntCaseNum);
		return this;
	}

	public int getSUseridSrl() {
		return getBaseVO().getTypeInteger(SUSERIDSRL).getValue();
	}
	
	public SSummaryEventBO setSUseridSrl(Integer sUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUSERIDSRL, sUseridSrl);
		return this;
	}

	public Date getSDatetime() {
		return getBaseVO().getTypeDate(SDATETIME).getValue();
	}
	
	public SSummaryEventBO setSDatetime(Date sDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime);
		return this;
	}
	
	public SSummaryEventBO setSDatetime(Date sDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime, dateFormat);
		getBaseVO().setAttribute(SDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getSMeId() {
		return getBaseVO().getTypeInteger(SMEID).getValue();
	}
	
	public SSummaryEventBO setSMeId(Integer sMeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SMEID, sMeId);
		return this;
	}

	public String getSOperation() {
		return getBaseVO().getTypeString(SOPERATION).getValue();
	}
	
	public SSummaryEventBO setSOperation(String sOperation) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SOPERATION, sOperation);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public SSummaryEventBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public Date getEventDatetime() {
		return getBaseVO().getTypeDate(EVENTDATETIME).getValue();
	}
	
	public SSummaryEventBO setEventDatetime(Date eventDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EVENTDATETIME, eventDatetime);
		return this;
	}
	
	public SSummaryEventBO setEventDatetime(Date eventDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EVENTDATETIME, eventDatetime, dateFormat);
		getBaseVO().setAttribute(EVENTDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getCreateDatetime() {
		return getBaseVO().getTypeDate(CREATEDATETIME).getValue();
	}
	
	public SSummaryEventBO setCreateDatetime(Date createDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime);
		return this;
	}
	
	public SSummaryEventBO setCreateDatetime(Date createDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime, dateFormat);
		getBaseVO().setAttribute(CREATEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getFuncId() {
		return getBaseVO().getTypeString(FUNCID).getValue();
	}
	
	public SSummaryEventBO setFuncId(String funcId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FUNCID, funcId);
		return this;
	}

	public String getDisplayFlag() {
		return getBaseVO().getTypeString(DISPLAYFLAG).getValue();
	}
	
	public SSummaryEventBO setDisplayFlag(String displayFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISPLAYFLAG, displayFlag);
		return this;
	}

	public String getRemovedFlag() {
		return getBaseVO().getTypeString(REMOVEDFLAG).getValue();
	}
	
	public SSummaryEventBO setRemovedFlag(String removedFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REMOVEDFLAG, removedFlag);
		return this;
	}

	public String getDescr() {
		return getBaseVO().getTypeString(DESCR).getValue();
	}
	
	public SSummaryEventBO setDescr(String descr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DESCR, descr);
		return this;
	}

	public String getKey1() {
		return getBaseVO().getTypeString(KEY1).getValue();
	}
	
	public SSummaryEventBO setKey1(String key1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, KEY1, key1);
		return this;
	}

	public String getKey2() {
		return getBaseVO().getTypeString(KEY2).getValue();
	}
	
	public SSummaryEventBO setKey2(String key2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, KEY2, key2);
		return this;
	}

	public String getKey3() {
		return getBaseVO().getTypeString(KEY3).getValue();
	}
	
	public SSummaryEventBO setKey3(String key3) {
		getBaseVO().setPropertyListValue(SET_BY_USER, KEY3, key3);
		return this;
	}

	public String getKey4() {
		return getBaseVO().getTypeString(KEY4).getValue();
	}
	
	public SSummaryEventBO setKey4(String key4) {
		getBaseVO().setPropertyListValue(SET_BY_USER, KEY4, key4);
		return this;
	}
	
	public SSummaryEventBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<SSummaryEventBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new SSummaryEventBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new SSummaryEventBO(getBaseVO());
		}
	}
	
	public SSummaryEventBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public SSummaryEventBO insert(FieldDescriptor... specificFields) throws Exception {
		return new SSummaryEventBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public SSummaryEventBO update(FieldDescriptor... specificFields) throws Exception {
		return new SSummaryEventBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public SSummaryEventBO delete() throws Exception {
		return new SSummaryEventBO(super.delete(getNativeDescriptor()));
	}
	
	public SSummaryEventBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SSummaryEventBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SSummaryEventBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public SSummaryEventBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public SSummaryEventBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public SSummaryEventBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public SSummaryEventBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SSummaryEventBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SSummaryEventBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public SSummaryEventBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SSummaryEventBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SSummaryEventBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public SSummaryEventBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public SSummaryEventBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public SSummaryEventBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SSummaryEventBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SSummaryEventBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public SSummaryEventBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SSummaryEventBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public SSummaryEventBO where(FieldDescriptor findBy, Object value) {
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
	
	public SSummaryEventBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SSummaryEventBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public SSummaryEventBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public SSummaryEventBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public SSummaryEventBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public SSummaryEventBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public SSummaryEventBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public SSummaryEventBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public SSummaryEventBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public SSummaryEventBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public SSummaryEventBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public SSummaryEventBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public SSummaryEventBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public SSummaryEventBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public SSummaryEventBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public SSummaryEventBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<SSummaryEventBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public SSummaryEventBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public SSummaryEventBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SSummaryEventBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public SSummaryEventBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public SSummaryEventBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public SSummaryEventBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public SSummaryEventBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public SSummaryEventBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public SSummaryEventBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public SSummaryEventBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public SSummaryEventBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public SSummaryEventBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SSummaryEventBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SSummaryEventBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SSummaryEventBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public SSummaryEventBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public SSummaryEventBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public SSummaryEventBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public SSummaryEventBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public SSummaryEventBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public SSummaryEventBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public SSummaryEventBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public SSummaryEventBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SSummaryEventBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SSummaryEventBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public SSummaryEventBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative SSummaryEventBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public SSummaryEventBO setQuestionMarks(Object... values) {
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
	public SSummaryEventBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public SSummaryEventBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public SSummaryEventBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public SSummaryEventBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public SSummaryEventBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public SSummaryEventBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public SSummaryEventBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public SSummaryEventBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public SSummaryEventBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public SSummaryEventBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public SSummaryEventBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public SSummaryEventBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public SSummaryEventBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}