package gov.utcourts.coriscommon.dataaccess.sysbldobjects;

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
public class SysbldobjectsBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("sysbldobjects", "gov.utcourts.coriscommon.dataaccess.sysbldobjects.Sysbldobjects");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor CREATEORDER = new FieldDescriptor(TABLE, "create_order", "setCreateOrder", 0);
	public static final FieldDescriptor BLDID = new FieldDescriptor(TABLE, "bld_id", "setBldId", 1);
	public static final FieldDescriptor OBJKIND = new FieldDescriptor(TABLE, "obj_kind", "setObjKind", 2);
	public static final FieldDescriptor OBJSIGNATURE = new FieldDescriptor(TABLE, "obj_signature", "setObjSignature", 3);
	public static final FieldDescriptor OBJOWNER = new FieldDescriptor(TABLE, "obj_owner", "setObjOwner", 4);
	public static final FieldDescriptor SEQUENCE = new FieldDescriptor(TABLE, "sequence", "setSequence", 5);
	public static final FieldDescriptor CREATESQL = new FieldDescriptor(TABLE, "create_sql", "setCreateSql", 6);
	public static final FieldDescriptor CREATECANFAIL = new FieldDescriptor(TABLE, "create_can_fail", "setCreateCanFail", 7);
	public static final FieldDescriptor DROPSQL = new FieldDescriptor(TABLE, "drop_sql", "setDropSql", 8);
	public static final FieldDescriptor DROPCANFAIL = new FieldDescriptor(TABLE, "drop_can_fail", "setDropCanFail", 9);
	public static final FieldDescriptor ALTERSQL = new FieldDescriptor(TABLE, "alter_sql", "setAlterSql", 10);
	public static final FieldDescriptor ALTERCANFAIL = new FieldDescriptor(TABLE, "alter_can_fail", "setAlterCanFail", 11);
	public static final FieldDescriptor STATE = new FieldDescriptor(TABLE, "state", "setState", 12);
	public static final FieldDescriptor TEMPSTATE = new FieldDescriptor(TABLE, "temp_state", "setTempState", 13);
	public static final FieldDescriptor FINALSTATE = new FieldDescriptor(TABLE, "final_state", "setFinalState", 14);
	public static final FieldDescriptor METADATA = new FieldDescriptor(TABLE, "metadata", "setMetadata", 15);
	public static final FieldDescriptor RESERVED1 = new FieldDescriptor(TABLE, "reserved1", "setReserved1", 16);
	public static final FieldDescriptor RESERVED2 = new FieldDescriptor(TABLE, "reserved2", "setReserved2", 17);
	public static final FieldDescriptor RESERVED3 = new FieldDescriptor(TABLE, "reserved3", "setReserved3", 18);

	public SysbldobjectsBO() {
		super(new SysbldobjectsVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public SysbldobjectsBO(String source) {
		super(new SysbldobjectsVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public SysbldobjectsBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public long getCreateOrder() {
		return getBaseVO().getTypeLong(CREATEORDER).getValue();
	}
	
	public SysbldobjectsBO setCreateOrder(Long createOrder) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEORDER, createOrder);
		return this;
	}

	public String getBldId() {
		return getBaseVO().getTypeString(BLDID).getValue();
	}
	
	public SysbldobjectsBO setBldId(String bldId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BLDID, bldId);
		return this;
	}

	public int getObjKind() {
		return getBaseVO().getTypeInteger(OBJKIND).getValue();
	}
	
	public SysbldobjectsBO setObjKind(Integer objKind) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OBJKIND, objKind);
		return this;
	}

	public String getObjSignature() {
		return getBaseVO().getTypeText(OBJSIGNATURE).getValue();
	}
	
	public SysbldobjectsBO setObjSignature(String objSignature) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OBJSIGNATURE, objSignature);
		return this;
	}

	public String getObjOwner() {
		return getBaseVO().getTypeString(OBJOWNER).getValue();
	}
	
	public SysbldobjectsBO setObjOwner(String objOwner) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OBJOWNER, objOwner);
		return this;
	}

	public int getSequence() {
		return getBaseVO().getTypeInteger(SEQUENCE).getValue();
	}
	
	public SysbldobjectsBO setSequence(Integer sequence) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SEQUENCE, sequence);
		return this;
	}

	public String getCreateSql() {
		return getBaseVO().getTypeText(CREATESQL).getValue();
	}
	
	public SysbldobjectsBO setCreateSql(String createSql) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATESQL, createSql);
		return this;
	}

	public boolean getCreateCanFail() {
		return getBaseVO().getTypeBoolean(CREATECANFAIL).getValue();
	}
	
	public SysbldobjectsBO setCreateCanFail(boolean createCanFail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATECANFAIL, createCanFail);
		return this;
	}

	public String getDropSql() {
		return getBaseVO().getTypeText(DROPSQL).getValue();
	}
	
	public SysbldobjectsBO setDropSql(String dropSql) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DROPSQL, dropSql);
		return this;
	}

	public boolean getDropCanFail() {
		return getBaseVO().getTypeBoolean(DROPCANFAIL).getValue();
	}
	
	public SysbldobjectsBO setDropCanFail(boolean dropCanFail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DROPCANFAIL, dropCanFail);
		return this;
	}

	public String getAlterSql() {
		return getBaseVO().getTypeText(ALTERSQL).getValue();
	}
	
	public SysbldobjectsBO setAlterSql(String alterSql) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ALTERSQL, alterSql);
		return this;
	}

	public boolean getAlterCanFail() {
		return getBaseVO().getTypeBoolean(ALTERCANFAIL).getValue();
	}
	
	public SysbldobjectsBO setAlterCanFail(boolean alterCanFail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ALTERCANFAIL, alterCanFail);
		return this;
	}

	public String getState() {
		return getBaseVO().getTypeString(STATE).getValue();
	}
	
	public SysbldobjectsBO setState(String state) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATE, state);
		return this;
	}

	public String getTempState() {
		return getBaseVO().getTypeString(TEMPSTATE).getValue();
	}
	
	public SysbldobjectsBO setTempState(String tempState) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TEMPSTATE, tempState);
		return this;
	}

	public String getFinalState() {
		return getBaseVO().getTypeString(FINALSTATE).getValue();
	}
	
	public SysbldobjectsBO setFinalState(String finalState) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FINALSTATE, finalState);
		return this;
	}

	public String getMetadata() {
		return getBaseVO().getTypeText(METADATA).getValue();
	}
	
	public SysbldobjectsBO setMetadata(String metadata) {
		getBaseVO().setPropertyListValue(SET_BY_USER, METADATA, metadata);
		return this;
	}

	public String getReserved1() {
		return getBaseVO().getTypeString(RESERVED1).getValue();
	}
	
	public SysbldobjectsBO setReserved1(String reserved1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RESERVED1, reserved1);
		return this;
	}

	public int getReserved2() {
		return getBaseVO().getTypeInteger(RESERVED2).getValue();
	}
	
	public SysbldobjectsBO setReserved2(Integer reserved2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RESERVED2, reserved2);
		return this;
	}

	public int getReserved3() {
		return getBaseVO().getTypeInteger(RESERVED3).getValue();
	}
	
	public SysbldobjectsBO setReserved3(Integer reserved3) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RESERVED3, reserved3);
		return this;
	}
	
	public SysbldobjectsBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<SysbldobjectsBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new SysbldobjectsBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new SysbldobjectsBO(getBaseVO());
		}
	}
	
	public SysbldobjectsBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public SysbldobjectsBO insert(FieldDescriptor... specificFields) throws Exception {
		return new SysbldobjectsBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public SysbldobjectsBO update(FieldDescriptor... specificFields) throws Exception {
		return new SysbldobjectsBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public SysbldobjectsBO delete() throws Exception {
		return new SysbldobjectsBO(super.delete(getNativeDescriptor()));
	}
	
	public SysbldobjectsBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SysbldobjectsBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SysbldobjectsBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public SysbldobjectsBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public SysbldobjectsBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public SysbldobjectsBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public SysbldobjectsBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SysbldobjectsBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SysbldobjectsBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public SysbldobjectsBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SysbldobjectsBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SysbldobjectsBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public SysbldobjectsBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public SysbldobjectsBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public SysbldobjectsBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SysbldobjectsBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SysbldobjectsBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public SysbldobjectsBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SysbldobjectsBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public SysbldobjectsBO where(FieldDescriptor findBy, Object value) {
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
	
	public SysbldobjectsBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SysbldobjectsBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public SysbldobjectsBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public SysbldobjectsBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public SysbldobjectsBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public SysbldobjectsBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public SysbldobjectsBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public SysbldobjectsBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public SysbldobjectsBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public SysbldobjectsBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public SysbldobjectsBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public SysbldobjectsBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public SysbldobjectsBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public SysbldobjectsBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public SysbldobjectsBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public SysbldobjectsBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysbldobjectsBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public SysbldobjectsBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public SysbldobjectsBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SysbldobjectsBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public SysbldobjectsBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public SysbldobjectsBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public SysbldobjectsBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public SysbldobjectsBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public SysbldobjectsBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public SysbldobjectsBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public SysbldobjectsBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public SysbldobjectsBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public SysbldobjectsBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SysbldobjectsBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SysbldobjectsBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SysbldobjectsBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public SysbldobjectsBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public SysbldobjectsBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public SysbldobjectsBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public SysbldobjectsBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public SysbldobjectsBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public SysbldobjectsBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public SysbldobjectsBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public SysbldobjectsBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SysbldobjectsBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SysbldobjectsBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public SysbldobjectsBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative SysbldobjectsBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public SysbldobjectsBO setQuestionMarks(Object... values) {
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
	public SysbldobjectsBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public SysbldobjectsBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public SysbldobjectsBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public SysbldobjectsBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public SysbldobjectsBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public SysbldobjectsBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public SysbldobjectsBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public SysbldobjectsBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public SysbldobjectsBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public SysbldobjectsBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public SysbldobjectsBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public SysbldobjectsBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public SysbldobjectsBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}