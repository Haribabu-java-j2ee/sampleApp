package gov.utcourts.coriscommon.dataaccess.casemevalueold;

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
public class CaseMeValueOldBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("case_me_value_old", "gov.utcourts.coriscommon.dataaccess.casemevalueold.CaseMeValueOld");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACTIONTYPE = new FieldDescriptor(TABLE, "action_type", "setActionType", 0);
	public static final FieldDescriptor ACTIONUSERIDSRL = new FieldDescriptor(TABLE, "action_userid_srl", "setActionUseridSrl", 1);
	public static final FieldDescriptor CHANGEDATETIME = new FieldDescriptor(TABLE, "change_datetime", "setChangeDatetime", 2);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 3);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 4);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 5);
	public static final FieldDescriptor MEID = new FieldDescriptor(TABLE, "me_id", "setMeId", 6);
	public static final FieldDescriptor METYPESEQ = new FieldDescriptor(TABLE, "me_type_seq", "setMeTypeSeq", 7);
	public static final FieldDescriptor MESCREENID = new FieldDescriptor(TABLE, "me_screen_id", "setMeScreenId", 8);
	public static final FieldDescriptor MEFIELDID = new FieldDescriptor(TABLE, "me_field_id", "setMeFieldId", 9);
	public static final FieldDescriptor MEFIELDSEQ = new FieldDescriptor(TABLE, "me_field_seq", "setMeFieldSeq", 10);
	public static final FieldDescriptor MEFIELDVALUE = new FieldDescriptor(TABLE, "me_field_value", "setMeFieldValue", 11);

	public CaseMeValueOldBO() {
		super(new CaseMeValueOldVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public CaseMeValueOldBO(String source) {
		super(new CaseMeValueOldVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public CaseMeValueOldBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getActionType() {
		return getBaseVO().getTypeString(ACTIONTYPE).getValue();
	}
	
	public CaseMeValueOldBO setActionType(String actionType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONTYPE, actionType);
		return this;
	}

	public int getActionUseridSrl() {
		return getBaseVO().getTypeInteger(ACTIONUSERIDSRL).getValue();
	}
	
	public CaseMeValueOldBO setActionUseridSrl(Integer actionUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONUSERIDSRL, actionUseridSrl);
		return this;
	}

	public Date getChangeDatetime() {
		return getBaseVO().getTypeDate(CHANGEDATETIME).getValue();
	}
	
	public CaseMeValueOldBO setChangeDatetime(Date changeDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime);
		return this;
	}
	
	public CaseMeValueOldBO setChangeDatetime(Date changeDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime, dateFormat);
		getBaseVO().setAttribute(CHANGEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public CaseMeValueOldBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public CaseMeValueOldBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public CaseMeValueOldBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public int getMeId() {
		return getBaseVO().getTypeInteger(MEID).getValue();
	}
	
	public CaseMeValueOldBO setMeId(Integer meId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEID, meId);
		return this;
	}

	public int getMeTypeSeq() {
		return getBaseVO().getTypeInteger(METYPESEQ).getValue();
	}
	
	public CaseMeValueOldBO setMeTypeSeq(Integer meTypeSeq) {
		getBaseVO().setPropertyListValue(SET_BY_USER, METYPESEQ, meTypeSeq);
		return this;
	}

	public String getMeScreenId() {
		return getBaseVO().getTypeString(MESCREENID).getValue();
	}
	
	public CaseMeValueOldBO setMeScreenId(String meScreenId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MESCREENID, meScreenId);
		return this;
	}

	public String getMeFieldId() {
		return getBaseVO().getTypeString(MEFIELDID).getValue();
	}
	
	public CaseMeValueOldBO setMeFieldId(String meFieldId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEFIELDID, meFieldId);
		return this;
	}

	public int getMeFieldSeq() {
		return getBaseVO().getTypeInteger(MEFIELDSEQ).getValue();
	}
	
	public CaseMeValueOldBO setMeFieldSeq(Integer meFieldSeq) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEFIELDSEQ, meFieldSeq);
		return this;
	}

	public String getMeFieldValue() {
		return getBaseVO().getTypeString(MEFIELDVALUE).getValue();
	}
	
	public CaseMeValueOldBO setMeFieldValue(String meFieldValue) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEFIELDVALUE, meFieldValue);
		return this;
	}
	
	public CaseMeValueOldBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<CaseMeValueOldBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new CaseMeValueOldBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new CaseMeValueOldBO(getBaseVO());
		}
	}
	
	public CaseMeValueOldBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public CaseMeValueOldBO insert(FieldDescriptor... specificFields) throws Exception {
		return new CaseMeValueOldBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public CaseMeValueOldBO update(FieldDescriptor... specificFields) throws Exception {
		return new CaseMeValueOldBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public CaseMeValueOldBO delete() throws Exception {
		return new CaseMeValueOldBO(super.delete(getNativeDescriptor()));
	}
	
	public CaseMeValueOldBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CaseMeValueOldBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CaseMeValueOldBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public CaseMeValueOldBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public CaseMeValueOldBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public CaseMeValueOldBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public CaseMeValueOldBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CaseMeValueOldBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CaseMeValueOldBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public CaseMeValueOldBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CaseMeValueOldBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CaseMeValueOldBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public CaseMeValueOldBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CaseMeValueOldBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public CaseMeValueOldBO where(FieldDescriptor findBy, Object value) {
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
	
	public CaseMeValueOldBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CaseMeValueOldBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public CaseMeValueOldBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public CaseMeValueOldBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public CaseMeValueOldBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public CaseMeValueOldBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public CaseMeValueOldBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public CaseMeValueOldBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public CaseMeValueOldBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public CaseMeValueOldBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public CaseMeValueOldBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public CaseMeValueOldBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public CaseMeValueOldBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public CaseMeValueOldBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public CaseMeValueOldBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public CaseMeValueOldBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<CaseMeValueOldBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public CaseMeValueOldBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CaseMeValueOldBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public CaseMeValueOldBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public CaseMeValueOldBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public CaseMeValueOldBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public CaseMeValueOldBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public CaseMeValueOldBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public CaseMeValueOldBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public CaseMeValueOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CaseMeValueOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public CaseMeValueOldBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public CaseMeValueOldBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public CaseMeValueOldBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public CaseMeValueOldBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public CaseMeValueOldBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public CaseMeValueOldBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public CaseMeValueOldBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public CaseMeValueOldBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CaseMeValueOldBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CaseMeValueOldBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public CaseMeValueOldBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative CaseMeValueOldBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public CaseMeValueOldBO setQuestionMarks(Object... values) {
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
	public CaseMeValueOldBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public CaseMeValueOldBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public CaseMeValueOldBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public CaseMeValueOldBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public CaseMeValueOldBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public CaseMeValueOldBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public CaseMeValueOldBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public CaseMeValueOldBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public CaseMeValueOldBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public CaseMeValueOldBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public CaseMeValueOldBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public CaseMeValueOldBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public CaseMeValueOldBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}