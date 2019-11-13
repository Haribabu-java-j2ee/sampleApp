package gov.utcourts.coriscommon.dataaccess.osdcaccthistory;

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
public class OsdcAcctHistoryBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("osdc_acct_history", "gov.utcourts.coriscommon.dataaccess.osdcaccthistory.OsdcAcctHistory");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor OSDCACCTSEQ = new FieldDescriptor(TABLE, "osdc_acct_seq", "setOsdcAcctSeq", 0);
	public static final FieldDescriptor ACCTNUM = new FieldDescriptor(TABLE, "acct_num", "setAcctNum", 1);
	public static final FieldDescriptor OSDCCREATEUSERID = new FieldDescriptor(TABLE, "osdc_create_userid", "setOsdcCreateUserid", 2);
	public static final FieldDescriptor OSDCSTATUS = new FieldDescriptor(TABLE, "osdc_status", "setOsdcStatus", 3);
	public static final FieldDescriptor OSDCCREATEDATETIME = new FieldDescriptor(TABLE, "osdc_create_datetime", "setOsdcCreateDatetime", 4);
	public static final FieldDescriptor OSDCRECALLUSERID = new FieldDescriptor(TABLE, "osdc_recall_userid", "setOsdcRecallUserid", 5);
	public static final FieldDescriptor OSDCRECALLDATETIME = new FieldDescriptor(TABLE, "osdc_recall_datetime", "setOsdcRecallDatetime", 6);
	public static final FieldDescriptor OSDCSENTDATETIME = new FieldDescriptor(TABLE, "osdc_sent_datetime", "setOsdcSentDatetime", 7);
	public static final FieldDescriptor DEBTCOLLNOTE = new FieldDescriptor(TABLE, "debt_coll_note", "setDebtCollNote", 8);

	public OsdcAcctHistoryBO() {
		super(new OsdcAcctHistoryVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public OsdcAcctHistoryBO(String source) {
		super(new OsdcAcctHistoryVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public OsdcAcctHistoryBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getOsdcAcctSeq() {
		return getBaseVO().getTypeInteger(OSDCACCTSEQ).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcAcctSeq(Integer osdcAcctSeq) {
		setPrimaryKey(OSDCACCTSEQ, osdcAcctSeq);
		return this;
	}

	public int getAcctNum() {
		return getBaseVO().getTypeInteger(ACCTNUM).getValue();
	}
	
	public OsdcAcctHistoryBO setAcctNum(Integer acctNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACCTNUM, acctNum);
		return this;
	}

	public int getOsdcCreateUserid() {
		return getBaseVO().getTypeInteger(OSDCCREATEUSERID).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcCreateUserid(Integer osdcCreateUserid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCCREATEUSERID, osdcCreateUserid);
		return this;
	}

	public String getOsdcStatus() {
		return getBaseVO().getTypeString(OSDCSTATUS).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcStatus(String osdcStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCSTATUS, osdcStatus);
		return this;
	}

	public Date getOsdcCreateDatetime() {
		return getBaseVO().getTypeDate(OSDCCREATEDATETIME).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcCreateDatetime(Date osdcCreateDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCCREATEDATETIME, osdcCreateDatetime);
		return this;
	}
	
	public OsdcAcctHistoryBO setOsdcCreateDatetime(Date osdcCreateDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCCREATEDATETIME, osdcCreateDatetime, dateFormat);
		getBaseVO().setAttribute(OSDCCREATEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getOsdcRecallUserid() {
		return getBaseVO().getTypeInteger(OSDCRECALLUSERID).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcRecallUserid(Integer osdcRecallUserid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCRECALLUSERID, osdcRecallUserid);
		return this;
	}

	public Date getOsdcRecallDatetime() {
		return getBaseVO().getTypeDate(OSDCRECALLDATETIME).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcRecallDatetime(Date osdcRecallDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCRECALLDATETIME, osdcRecallDatetime);
		return this;
	}
	
	public OsdcAcctHistoryBO setOsdcRecallDatetime(Date osdcRecallDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCRECALLDATETIME, osdcRecallDatetime, dateFormat);
		getBaseVO().setAttribute(OSDCRECALLDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getOsdcSentDatetime() {
		return getBaseVO().getTypeDate(OSDCSENTDATETIME).getValue();
	}
	
	public OsdcAcctHistoryBO setOsdcSentDatetime(Date osdcSentDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCSENTDATETIME, osdcSentDatetime);
		return this;
	}
	
	public OsdcAcctHistoryBO setOsdcSentDatetime(Date osdcSentDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OSDCSENTDATETIME, osdcSentDatetime, dateFormat);
		getBaseVO().setAttribute(OSDCSENTDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDebtCollNote() {
		return getBaseVO().getTypeString(DEBTCOLLNOTE).getValue();
	}
	
	public OsdcAcctHistoryBO setDebtCollNote(String debtCollNote) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLNOTE, debtCollNote);
		return this;
	}
	
	public OsdcAcctHistoryBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<OsdcAcctHistoryBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new OsdcAcctHistoryBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new OsdcAcctHistoryBO(getBaseVO());
		}
	}
	
	public OsdcAcctHistoryBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public OsdcAcctHistoryBO insert(FieldDescriptor... specificFields) throws Exception {
		return new OsdcAcctHistoryBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public OsdcAcctHistoryBO update(FieldDescriptor... specificFields) throws Exception {
		return new OsdcAcctHistoryBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public OsdcAcctHistoryBO delete() throws Exception {
		return new OsdcAcctHistoryBO(super.delete(getNativeDescriptor()));
	}
	
	public OsdcAcctHistoryBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public OsdcAcctHistoryBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public OsdcAcctHistoryBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public OsdcAcctHistoryBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public OsdcAcctHistoryBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public OsdcAcctHistoryBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public OsdcAcctHistoryBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public OsdcAcctHistoryBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public OsdcAcctHistoryBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OsdcAcctHistoryBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public OsdcAcctHistoryBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public OsdcAcctHistoryBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public OsdcAcctHistoryBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public OsdcAcctHistoryBO where(FieldDescriptor findBy, Object value) {
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
	
	public OsdcAcctHistoryBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public OsdcAcctHistoryBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public OsdcAcctHistoryBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public OsdcAcctHistoryBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public OsdcAcctHistoryBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public OsdcAcctHistoryBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public OsdcAcctHistoryBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public OsdcAcctHistoryBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public OsdcAcctHistoryBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public OsdcAcctHistoryBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public OsdcAcctHistoryBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public OsdcAcctHistoryBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public OsdcAcctHistoryBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public OsdcAcctHistoryBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public OsdcAcctHistoryBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public OsdcAcctHistoryBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<OsdcAcctHistoryBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public OsdcAcctHistoryBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public OsdcAcctHistoryBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public OsdcAcctHistoryBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public OsdcAcctHistoryBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public OsdcAcctHistoryBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public OsdcAcctHistoryBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public OsdcAcctHistoryBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public OsdcAcctHistoryBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public OsdcAcctHistoryBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public OsdcAcctHistoryBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public OsdcAcctHistoryBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public OsdcAcctHistoryBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public OsdcAcctHistoryBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public OsdcAcctHistoryBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public OsdcAcctHistoryBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public OsdcAcctHistoryBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public OsdcAcctHistoryBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public OsdcAcctHistoryBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public OsdcAcctHistoryBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public OsdcAcctHistoryBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public OsdcAcctHistoryBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative OsdcAcctHistoryBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public OsdcAcctHistoryBO setQuestionMarks(Object... values) {
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
	public OsdcAcctHistoryBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public OsdcAcctHistoryBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public OsdcAcctHistoryBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public OsdcAcctHistoryBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public OsdcAcctHistoryBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public OsdcAcctHistoryBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public OsdcAcctHistoryBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public OsdcAcctHistoryBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public OsdcAcctHistoryBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public OsdcAcctHistoryBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public OsdcAcctHistoryBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public OsdcAcctHistoryBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public OsdcAcctHistoryBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}