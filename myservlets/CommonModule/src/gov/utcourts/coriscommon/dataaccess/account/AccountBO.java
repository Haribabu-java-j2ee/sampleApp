package gov.utcourts.coriscommon.dataaccess.account;

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
import java.util.Date;
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class AccountBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("account", "gov.utcourts.coriscommon.dataaccess.account.Account");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACCTNUM = new FieldDescriptor(TABLE, "acct_num", "setAcctNum", 0);
	public static final FieldDescriptor ACCTTYPE = new FieldDescriptor(TABLE, "acct_type", "setAcctType", 1);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 2);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 3);
	public static final FieldDescriptor PARTYCODE = new FieldDescriptor(TABLE, "party_code", "setPartyCode", 4);
	public static final FieldDescriptor TIMEPAYNUM = new FieldDescriptor(TABLE, "timepay_num", "setTimepayNum", 5);
	public static final FieldDescriptor TIMEPAYSEQ = new FieldDescriptor(TABLE, "timepay_seq", "setTimepaySeq", 6);
	public static final FieldDescriptor ORIGAMTDUE = new FieldDescriptor(TABLE, "orig_amt_due", "setOrigAmtDue", 7);
	public static final FieldDescriptor AMTDUE = new FieldDescriptor(TABLE, "amt_due", "setAmtDue", 8);
	public static final FieldDescriptor AMTPAID = new FieldDescriptor(TABLE, "amt_paid", "setAmtPaid", 9);
	public static final FieldDescriptor AMTCREDIT = new FieldDescriptor(TABLE, "amt_credit", "setAmtCredit", 10);
	public static final FieldDescriptor STATUS = new FieldDescriptor(TABLE, "status", "setStatus", 11);
	public static final FieldDescriptor DUEDATE = new FieldDescriptor(TABLE, "due_date", "setDueDate", 12);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 13);
	public static final FieldDescriptor INTEFFECTDATE = new FieldDescriptor(TABLE, "int_effect_date", "setIntEffectDate", 14);
	public static final FieldDescriptor NOTE = new FieldDescriptor(TABLE, "note", "setNote", 15);

	public AccountBO() {
		super(new AccountVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public AccountBO(String source) {
		super(new AccountVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public AccountBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getAcctNum() {
		return getBaseVO().getTypeInteger(ACCTNUM).getValue();
	}
	
	public AccountBO setAcctNum(Integer acctNum) {
		setPrimaryKey(ACCTNUM, acctNum);
		return this;
	}

	public String getAcctType() {
		return getBaseVO().getTypeString(ACCTTYPE).getValue();
	}
	
	public AccountBO setAcctType(String acctType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACCTTYPE, acctType);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public AccountBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public AccountBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public String getPartyCode() {
		return getBaseVO().getTypeString(PARTYCODE).getValue();
	}
	
	public AccountBO setPartyCode(String partyCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYCODE, partyCode);
		return this;
	}

	public int getTimepayNum() {
		return getBaseVO().getTypeInteger(TIMEPAYNUM).getValue();
	}
	
	public AccountBO setTimepayNum(Integer timepayNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TIMEPAYNUM, timepayNum);
		return this;
	}

	public int getTimepaySeq() {
		return getBaseVO().getTypeInteger(TIMEPAYSEQ).getValue();
	}
	
	public AccountBO setTimepaySeq(Integer timepaySeq) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TIMEPAYSEQ, timepaySeq);
		return this;
	}

	public BigDecimal getOrigAmtDue() {
		return getBaseVO().getTypeBigDecimal(ORIGAMTDUE).getValue();
	}
	
	public AccountBO setOrigAmtDue(BigDecimal origAmtDue) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGAMTDUE, origAmtDue);
		return this;
	}
	
	public AccountBO setOrigAmtDue(double origAmtDue) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGAMTDUE, new BigDecimal(String.valueOf(new Double(origAmtDue))));
		return this;
	}

	public BigDecimal getAmtDue() {
		return getBaseVO().getTypeBigDecimal(AMTDUE).getValue();
	}
	
	public AccountBO setAmtDue(BigDecimal amtDue) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTDUE, amtDue);
		return this;
	}
	
	public AccountBO setAmtDue(double amtDue) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTDUE, new BigDecimal(String.valueOf(new Double(amtDue))));
		return this;
	}

	public BigDecimal getAmtPaid() {
		return getBaseVO().getTypeBigDecimal(AMTPAID).getValue();
	}
	
	public AccountBO setAmtPaid(BigDecimal amtPaid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTPAID, amtPaid);
		return this;
	}
	
	public AccountBO setAmtPaid(double amtPaid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTPAID, new BigDecimal(String.valueOf(new Double(amtPaid))));
		return this;
	}

	public BigDecimal getAmtCredit() {
		return getBaseVO().getTypeBigDecimal(AMTCREDIT).getValue();
	}
	
	public AccountBO setAmtCredit(BigDecimal amtCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTCREDIT, amtCredit);
		return this;
	}
	
	public AccountBO setAmtCredit(double amtCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMTCREDIT, new BigDecimal(String.valueOf(new Double(amtCredit))));
		return this;
	}

	public String getStatus() {
		return getBaseVO().getTypeString(STATUS).getValue();
	}
	
	public AccountBO setStatus(String status) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATUS, status);
		return this;
	}

	public Date getDueDate() {
		return getBaseVO().getTypeDate(DUEDATE).getValue();
	}
	
	public AccountBO setDueDate(Date dueDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DUEDATE, dueDate);
		return this;
	}
	
	public AccountBO setDueDate(Date dueDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DUEDATE, dueDate, dateFormat);
		getBaseVO().setAttribute(DUEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public AccountBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public Date getIntEffectDate() {
		return getBaseVO().getTypeDate(INTEFFECTDATE).getValue();
	}
	
	public AccountBO setIntEffectDate(Date intEffectDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTEFFECTDATE, intEffectDate);
		return this;
	}
	
	public AccountBO setIntEffectDate(Date intEffectDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTEFFECTDATE, intEffectDate, dateFormat);
		getBaseVO().setAttribute(INTEFFECTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getNote() {
		return getBaseVO().getTypeString(NOTE).getValue();
	}
	
	public AccountBO setNote(String note) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NOTE, note);
		return this;
	}
	
	public AccountBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<AccountBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new AccountBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new AccountBO(getBaseVO());
		}
	}
	
	public AccountBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public AccountBO insert(FieldDescriptor... specificFields) throws Exception {
		return new AccountBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public AccountBO update(FieldDescriptor... specificFields) throws Exception {
		return new AccountBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public AccountBO delete() throws Exception {
		return new AccountBO(super.delete(getNativeDescriptor()));
	}
	
	public AccountBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AccountBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AccountBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public AccountBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public AccountBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public AccountBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public AccountBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AccountBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AccountBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public AccountBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AccountBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AccountBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public AccountBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public AccountBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public AccountBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AccountBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AccountBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public AccountBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AccountBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public AccountBO where(FieldDescriptor findBy, Object value) {
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
	
	public AccountBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AccountBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public AccountBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public AccountBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public AccountBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public AccountBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public AccountBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public AccountBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public AccountBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public AccountBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public AccountBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public AccountBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public AccountBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public AccountBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public AccountBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public AccountBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public AccountBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public AccountBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AccountBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public AccountBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public AccountBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public AccountBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public AccountBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public AccountBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public AccountBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public AccountBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public AccountBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public AccountBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AccountBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AccountBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AccountBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public AccountBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public AccountBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public AccountBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public AccountBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public AccountBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public AccountBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public AccountBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public AccountBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AccountBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AccountBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public AccountBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative AccountBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public AccountBO setQuestionMarks(Object... values) {
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
	public AccountBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public AccountBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public AccountBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public AccountBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public AccountBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public AccountBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public AccountBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public AccountBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public AccountBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public AccountBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public AccountBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public AccountBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public AccountBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}