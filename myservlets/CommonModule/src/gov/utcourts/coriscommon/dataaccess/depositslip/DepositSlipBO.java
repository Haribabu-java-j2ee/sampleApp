package gov.utcourts.coriscommon.dataaccess.depositslip;

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
public class DepositSlipBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("deposit_slip", "gov.utcourts.coriscommon.dataaccess.depositslip.DepositSlip");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor BANKACCTNUM = new FieldDescriptor(TABLE, "bank_acct_num", "setBankAcctNum", 0);
	public static final FieldDescriptor INTJOURNALNUM = new FieldDescriptor(TABLE, "int_journal_num", "setIntJournalNum", 1);
	public static final FieldDescriptor DEPOSITSEQ = new FieldDescriptor(TABLE, "deposit_seq", "setDepositSeq", 2);
	public static final FieldDescriptor ENTRYDATETIME = new FieldDescriptor(TABLE, "entry_datetime", "setEntryDatetime", 3);
	public static final FieldDescriptor CLERKID = new FieldDescriptor(TABLE, "clerk_id", "setClerkId", 4);
	public static final FieldDescriptor CURRENCYAMT = new FieldDescriptor(TABLE, "currency_amt", "setCurrencyAmt", 5);
	public static final FieldDescriptor COINAMT = new FieldDescriptor(TABLE, "coin_amt", "setCoinAmt", 6);
	public static final FieldDescriptor DEPOSITADJ = new FieldDescriptor(TABLE, "deposit_adj", "setDepositAdj", 7);
	public static final FieldDescriptor CHECKTOTAL = new FieldDescriptor(TABLE, "check_total", "setCheckTotal", 8);
	public static final FieldDescriptor CORRECTIONAMT = new FieldDescriptor(TABLE, "correction_amt", "setCorrectionAmt", 9);
	public static final FieldDescriptor ELECXFER = new FieldDescriptor(TABLE, "elec_xfer", "setElecXfer", 10);
	public static final FieldDescriptor NOTE = new FieldDescriptor(TABLE, "note", "setNote", 11);
	public static final FieldDescriptor RECONDATE = new FieldDescriptor(TABLE, "recon_date", "setReconDate", 12);
	public static final FieldDescriptor RECONUSERID = new FieldDescriptor(TABLE, "recon_userid", "setReconUserid", 13);
	public static final FieldDescriptor RECONSTATUS = new FieldDescriptor(TABLE, "recon_status", "setReconStatus", 14);
	public static final FieldDescriptor DEPOSITDATE = new FieldDescriptor(TABLE, "deposit_date", "setDepositDate", 15);

	public DepositSlipBO() {
		super(new DepositSlipVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public DepositSlipBO(String source) {
		super(new DepositSlipVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public DepositSlipBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getBankAcctNum() {
		return getBaseVO().getTypeString(BANKACCTNUM).getValue();
	}
	
	public DepositSlipBO setBankAcctNum(String bankAcctNum) {
		setPrimaryKey(BANKACCTNUM, bankAcctNum);
		return this;
	}

	public int getIntJournalNum() {
		return getBaseVO().getTypeInteger(INTJOURNALNUM).getValue();
	}
	
	public DepositSlipBO setIntJournalNum(Integer intJournalNum) {
		setPrimaryKey(INTJOURNALNUM, intJournalNum);
		return this;
	}

	public int getDepositSeq() {
		return getBaseVO().getTypeInteger(DEPOSITSEQ).getValue();
	}
	
	public DepositSlipBO setDepositSeq(Integer depositSeq) {
		setPrimaryKey(DEPOSITSEQ, depositSeq);
		return this;
	}

	public Date getEntryDatetime() {
		return getBaseVO().getTypeDate(ENTRYDATETIME).getValue();
	}
	
	public DepositSlipBO setEntryDatetime(Date entryDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ENTRYDATETIME, entryDatetime);
		return this;
	}
	
	public DepositSlipBO setEntryDatetime(Date entryDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ENTRYDATETIME, entryDatetime, dateFormat);
		getBaseVO().setAttribute(ENTRYDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getClerkId() {
		return getBaseVO().getTypeInteger(CLERKID).getValue();
	}
	
	public DepositSlipBO setClerkId(Integer clerkId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CLERKID, clerkId);
		return this;
	}

	public BigDecimal getCurrencyAmt() {
		return getBaseVO().getTypeBigDecimal(CURRENCYAMT).getValue();
	}
	
	public DepositSlipBO setCurrencyAmt(BigDecimal currencyAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CURRENCYAMT, currencyAmt);
		return this;
	}
	
	public DepositSlipBO setCurrencyAmt(double currencyAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CURRENCYAMT, new BigDecimal(String.valueOf(new Double(currencyAmt))));
		return this;
	}

	public BigDecimal getCoinAmt() {
		return getBaseVO().getTypeBigDecimal(COINAMT).getValue();
	}
	
	public DepositSlipBO setCoinAmt(BigDecimal coinAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COINAMT, coinAmt);
		return this;
	}
	
	public DepositSlipBO setCoinAmt(double coinAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COINAMT, new BigDecimal(String.valueOf(new Double(coinAmt))));
		return this;
	}

	public BigDecimal getDepositAdj() {
		return getBaseVO().getTypeBigDecimal(DEPOSITADJ).getValue();
	}
	
	public DepositSlipBO setDepositAdj(BigDecimal depositAdj) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEPOSITADJ, depositAdj);
		return this;
	}
	
	public DepositSlipBO setDepositAdj(double depositAdj) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEPOSITADJ, new BigDecimal(String.valueOf(new Double(depositAdj))));
		return this;
	}

	public BigDecimal getCheckTotal() {
		return getBaseVO().getTypeBigDecimal(CHECKTOTAL).getValue();
	}
	
	public DepositSlipBO setCheckTotal(BigDecimal checkTotal) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHECKTOTAL, checkTotal);
		return this;
	}
	
	public DepositSlipBO setCheckTotal(double checkTotal) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHECKTOTAL, new BigDecimal(String.valueOf(new Double(checkTotal))));
		return this;
	}

	public BigDecimal getCorrectionAmt() {
		return getBaseVO().getTypeBigDecimal(CORRECTIONAMT).getValue();
	}
	
	public DepositSlipBO setCorrectionAmt(BigDecimal correctionAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CORRECTIONAMT, correctionAmt);
		return this;
	}
	
	public DepositSlipBO setCorrectionAmt(double correctionAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CORRECTIONAMT, new BigDecimal(String.valueOf(new Double(correctionAmt))));
		return this;
	}

	public BigDecimal getElecXfer() {
		return getBaseVO().getTypeBigDecimal(ELECXFER).getValue();
	}
	
	public DepositSlipBO setElecXfer(BigDecimal elecXfer) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ELECXFER, elecXfer);
		return this;
	}
	
	public DepositSlipBO setElecXfer(double elecXfer) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ELECXFER, new BigDecimal(String.valueOf(new Double(elecXfer))));
		return this;
	}

	public String getNote() {
		return getBaseVO().getTypeString(NOTE).getValue();
	}
	
	public DepositSlipBO setNote(String note) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NOTE, note);
		return this;
	}

	public Date getReconDate() {
		return getBaseVO().getTypeDate(RECONDATE).getValue();
	}
	
	public DepositSlipBO setReconDate(Date reconDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECONDATE, reconDate);
		return this;
	}
	
	public DepositSlipBO setReconDate(Date reconDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECONDATE, reconDate, dateFormat);
		getBaseVO().setAttribute(RECONDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getReconUserid() {
		return getBaseVO().getTypeInteger(RECONUSERID).getValue();
	}
	
	public DepositSlipBO setReconUserid(Integer reconUserid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECONUSERID, reconUserid);
		return this;
	}

	public int getReconStatus() {
		return getBaseVO().getTypeInteger(RECONSTATUS).getValue();
	}
	
	public DepositSlipBO setReconStatus(Integer reconStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECONSTATUS, reconStatus);
		return this;
	}

	public Date getDepositDate() {
		return getBaseVO().getTypeDate(DEPOSITDATE).getValue();
	}
	
	public DepositSlipBO setDepositDate(Date depositDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEPOSITDATE, depositDate);
		return this;
	}
	
	public DepositSlipBO setDepositDate(Date depositDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEPOSITDATE, depositDate, dateFormat);
		getBaseVO().setAttribute(DEPOSITDATE.getDateFormatterKey(), dateFormat);
		return this;
	}
	
	public DepositSlipBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<DepositSlipBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new DepositSlipBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new DepositSlipBO(getBaseVO());
		}
	}
	
	public DepositSlipBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public DepositSlipBO insert(FieldDescriptor... specificFields) throws Exception {
		return new DepositSlipBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public DepositSlipBO update(FieldDescriptor... specificFields) throws Exception {
		return new DepositSlipBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public DepositSlipBO delete() throws Exception {
		return new DepositSlipBO(super.delete(getNativeDescriptor()));
	}
	
	public DepositSlipBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public DepositSlipBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public DepositSlipBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public DepositSlipBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public DepositSlipBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public DepositSlipBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public DepositSlipBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public DepositSlipBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public DepositSlipBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public DepositSlipBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public DepositSlipBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public DepositSlipBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public DepositSlipBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public DepositSlipBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public DepositSlipBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public DepositSlipBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public DepositSlipBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public DepositSlipBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public DepositSlipBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public DepositSlipBO where(FieldDescriptor findBy, Object value) {
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
	
	public DepositSlipBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public DepositSlipBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public DepositSlipBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public DepositSlipBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public DepositSlipBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public DepositSlipBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public DepositSlipBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public DepositSlipBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public DepositSlipBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public DepositSlipBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public DepositSlipBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public DepositSlipBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public DepositSlipBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public DepositSlipBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public DepositSlipBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public DepositSlipBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<DepositSlipBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public DepositSlipBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public DepositSlipBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public DepositSlipBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public DepositSlipBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public DepositSlipBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public DepositSlipBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public DepositSlipBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public DepositSlipBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public DepositSlipBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public DepositSlipBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public DepositSlipBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public DepositSlipBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public DepositSlipBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public DepositSlipBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public DepositSlipBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public DepositSlipBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public DepositSlipBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public DepositSlipBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public DepositSlipBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public DepositSlipBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public DepositSlipBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public DepositSlipBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public DepositSlipBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public DepositSlipBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public DepositSlipBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public DepositSlipBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative DepositSlipBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public DepositSlipBO setQuestionMarks(Object... values) {
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
	public DepositSlipBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public DepositSlipBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public DepositSlipBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public DepositSlipBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public DepositSlipBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public DepositSlipBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public DepositSlipBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public DepositSlipBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public DepositSlipBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public DepositSlipBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public DepositSlipBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public DepositSlipBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public DepositSlipBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}