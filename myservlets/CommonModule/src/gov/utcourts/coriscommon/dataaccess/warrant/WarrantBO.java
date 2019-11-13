package gov.utcourts.coriscommon.dataaccess.warrant;

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
public class WarrantBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("warrant", "gov.utcourts.coriscommon.dataaccess.warrant.Warrant");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor WARRNUM = new FieldDescriptor(TABLE, "warr_num", "setWarrNum", 0);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 1);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 2);
	public static final FieldDescriptor CREATEDATETIME = new FieldDescriptor(TABLE, "create_datetime", "setCreateDatetime", 3);
	public static final FieldDescriptor TYPE = new FieldDescriptor(TABLE, "type", "setType", 4);
	public static final FieldDescriptor ORDERTYPE = new FieldDescriptor(TABLE, "order_type", "setOrderType", 5);
	public static final FieldDescriptor ORDERDATE = new FieldDescriptor(TABLE, "order_date", "setOrderDate", 6);
	public static final FieldDescriptor ISSUEDATE = new FieldDescriptor(TABLE, "issue_date", "setIssueDate", 7);
	public static final FieldDescriptor PRINTDATE = new FieldDescriptor(TABLE, "print_date", "setPrintDate", 8);
	public static final FieldDescriptor RECALLDATE = new FieldDescriptor(TABLE, "recall_date", "setRecallDate", 9);
	public static final FieldDescriptor EXPDATE = new FieldDescriptor(TABLE, "exp_date", "setExpDate", 10);
	public static final FieldDescriptor ISSUEJUDGEID = new FieldDescriptor(TABLE, "issue_judge_id", "setIssueJudgeId", 11);
	public static final FieldDescriptor ISSUECLERKID = new FieldDescriptor(TABLE, "issue_clerk_id", "setIssueClerkId", 12);
	public static final FieldDescriptor ISSUEREASONCODE = new FieldDescriptor(TABLE, "issue_reason_code", "setIssueReasonCode", 13);
	public static final FieldDescriptor OTHERREASONTEXT = new FieldDescriptor(TABLE, "other_reason_text", "setOtherReasonText", 14);
	public static final FieldDescriptor RECALLREASONCODE = new FieldDescriptor(TABLE, "recall_reason_code", "setRecallReasonCode", 15);
	public static final FieldDescriptor SWWSSTATUS = new FieldDescriptor(TABLE, "swws_status", "setSwwsStatus", 16);
	public static final FieldDescriptor SLCSTATUS = new FieldDescriptor(TABLE, "slc_status", "setSlcStatus", 17);
	public static final FieldDescriptor NCICSTATUS = new FieldDescriptor(TABLE, "ncic_status", "setNcicStatus", 18);
	public static final FieldDescriptor BAILFLAG = new FieldDescriptor(TABLE, "bail_flag", "setBailFlag", 19);
	public static final FieldDescriptor BAILAMT = new FieldDescriptor(TABLE, "bail_amt", "setBailAmt", 20);
	public static final FieldDescriptor FEEFLAG = new FieldDescriptor(TABLE, "fee_flag", "setFeeFlag", 21);
	public static final FieldDescriptor MEID = new FieldDescriptor(TABLE, "me_id", "setMeId", 22);
	public static final FieldDescriptor RECALLMEID = new FieldDescriptor(TABLE, "recall_me_id", "setRecallMeId", 23);
	public static final FieldDescriptor RECALLUSERIDSRL = new FieldDescriptor(TABLE, "recall_userid_srl", "setRecallUseridSrl", 24);
	public static final FieldDescriptor MUSTAPPEAR = new FieldDescriptor(TABLE, "must_appear", "setMustAppear", 25);
	public static final FieldDescriptor ORDERUSERIDSRL = new FieldDescriptor(TABLE, "order_userid_srl", "setOrderUseridSrl", 26);
	public static final FieldDescriptor TRANSPORTSCOPE = new FieldDescriptor(TABLE, "transport_scope", "setTransportScope", 27);

	public WarrantBO() {
		super(new WarrantVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public WarrantBO(String source) {
		super(new WarrantVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public WarrantBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getWarrNum() {
		return getBaseVO().getTypeInteger(WARRNUM).getValue();
	}
	
	public WarrantBO setWarrNum(Integer warrNum) {
		setPrimaryKey(WARRNUM, warrNum);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public WarrantBO setIntCaseNum(Integer intCaseNum) {
		setPrimaryKey(INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public WarrantBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public Date getCreateDatetime() {
		return getBaseVO().getTypeDate(CREATEDATETIME).getValue();
	}
	
	public WarrantBO setCreateDatetime(Date createDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime);
		return this;
	}
	
	public WarrantBO setCreateDatetime(Date createDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime, dateFormat);
		getBaseVO().setAttribute(CREATEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getType() {
		return getBaseVO().getTypeString(TYPE).getValue();
	}
	
	public WarrantBO setType(String type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TYPE, type);
		return this;
	}

	public String getOrderType() {
		return getBaseVO().getTypeString(ORDERTYPE).getValue();
	}
	
	public WarrantBO setOrderType(String orderType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERTYPE, orderType);
		return this;
	}

	public Date getOrderDate() {
		return getBaseVO().getTypeDate(ORDERDATE).getValue();
	}
	
	public WarrantBO setOrderDate(Date orderDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERDATE, orderDate);
		return this;
	}
	
	public WarrantBO setOrderDate(Date orderDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERDATE, orderDate, dateFormat);
		getBaseVO().setAttribute(ORDERDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIssueDate() {
		return getBaseVO().getTypeDate(ISSUEDATE).getValue();
	}
	
	public WarrantBO setIssueDate(Date issueDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEDATE, issueDate);
		return this;
	}
	
	public WarrantBO setIssueDate(Date issueDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEDATE, issueDate, dateFormat);
		getBaseVO().setAttribute(ISSUEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getPrintDate() {
		return getBaseVO().getTypeDate(PRINTDATE).getValue();
	}
	
	public WarrantBO setPrintDate(Date printDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRINTDATE, printDate);
		return this;
	}
	
	public WarrantBO setPrintDate(Date printDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRINTDATE, printDate, dateFormat);
		getBaseVO().setAttribute(PRINTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getRecallDate() {
		return getBaseVO().getTypeDate(RECALLDATE).getValue();
	}
	
	public WarrantBO setRecallDate(Date recallDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLDATE, recallDate);
		return this;
	}
	
	public WarrantBO setRecallDate(Date recallDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLDATE, recallDate, dateFormat);
		getBaseVO().setAttribute(RECALLDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getExpDate() {
		return getBaseVO().getTypeDate(EXPDATE).getValue();
	}
	
	public WarrantBO setExpDate(Date expDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EXPDATE, expDate);
		return this;
	}
	
	public WarrantBO setExpDate(Date expDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EXPDATE, expDate, dateFormat);
		getBaseVO().setAttribute(EXPDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getIssueJudgeId() {
		return getBaseVO().getTypeInteger(ISSUEJUDGEID).getValue();
	}
	
	public WarrantBO setIssueJudgeId(Integer issueJudgeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEJUDGEID, issueJudgeId);
		return this;
	}

	public int getIssueClerkId() {
		return getBaseVO().getTypeInteger(ISSUECLERKID).getValue();
	}
	
	public WarrantBO setIssueClerkId(Integer issueClerkId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUECLERKID, issueClerkId);
		return this;
	}

	public String getIssueReasonCode() {
		return getBaseVO().getTypeString(ISSUEREASONCODE).getValue();
	}
	
	public WarrantBO setIssueReasonCode(String issueReasonCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEREASONCODE, issueReasonCode);
		return this;
	}

	public String getOtherReasonText() {
		return getBaseVO().getTypeString(OTHERREASONTEXT).getValue();
	}
	
	public WarrantBO setOtherReasonText(String otherReasonText) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OTHERREASONTEXT, otherReasonText);
		return this;
	}

	public String getRecallReasonCode() {
		return getBaseVO().getTypeString(RECALLREASONCODE).getValue();
	}
	
	public WarrantBO setRecallReasonCode(String recallReasonCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLREASONCODE, recallReasonCode);
		return this;
	}

	public String getSwwsStatus() {
		return getBaseVO().getTypeString(SWWSSTATUS).getValue();
	}
	
	public WarrantBO setSwwsStatus(String swwsStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SWWSSTATUS, swwsStatus);
		return this;
	}

	public String getSlcStatus() {
		return getBaseVO().getTypeString(SLCSTATUS).getValue();
	}
	
	public WarrantBO setSlcStatus(String slcStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SLCSTATUS, slcStatus);
		return this;
	}

	public String getNcicStatus() {
		return getBaseVO().getTypeString(NCICSTATUS).getValue();
	}
	
	public WarrantBO setNcicStatus(String ncicStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICSTATUS, ncicStatus);
		return this;
	}

	public String getBailFlag() {
		return getBaseVO().getTypeString(BAILFLAG).getValue();
	}
	
	public WarrantBO setBailFlag(String bailFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILFLAG, bailFlag);
		return this;
	}

	public BigDecimal getBailAmt() {
		return getBaseVO().getTypeBigDecimal(BAILAMT).getValue();
	}
	
	public WarrantBO setBailAmt(BigDecimal bailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILAMT, bailAmt);
		return this;
	}
	
	public WarrantBO setBailAmt(double bailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILAMT, new BigDecimal(String.valueOf(new Double(bailAmt))));
		return this;
	}

	public String getFeeFlag() {
		return getBaseVO().getTypeString(FEEFLAG).getValue();
	}
	
	public WarrantBO setFeeFlag(String feeFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FEEFLAG, feeFlag);
		return this;
	}

	public int getMeId() {
		return getBaseVO().getTypeInteger(MEID).getValue();
	}
	
	public WarrantBO setMeId(Integer meId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEID, meId);
		return this;
	}

	public int getRecallMeId() {
		return getBaseVO().getTypeInteger(RECALLMEID).getValue();
	}
	
	public WarrantBO setRecallMeId(Integer recallMeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLMEID, recallMeId);
		return this;
	}

	public int getRecallUseridSrl() {
		return getBaseVO().getTypeInteger(RECALLUSERIDSRL).getValue();
	}
	
	public WarrantBO setRecallUseridSrl(Integer recallUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLUSERIDSRL, recallUseridSrl);
		return this;
	}

	public String getMustAppear() {
		return getBaseVO().getTypeString(MUSTAPPEAR).getValue();
	}
	
	public WarrantBO setMustAppear(String mustAppear) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MUSTAPPEAR, mustAppear);
		return this;
	}

	public int getOrderUseridSrl() {
		return getBaseVO().getTypeInteger(ORDERUSERIDSRL).getValue();
	}
	
	public WarrantBO setOrderUseridSrl(Integer orderUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERUSERIDSRL, orderUseridSrl);
		return this;
	}

	public String getTransportScope() {
		return getBaseVO().getTypeString(TRANSPORTSCOPE).getValue();
	}
	
	public WarrantBO setTransportScope(String transportScope) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRANSPORTSCOPE, transportScope);
		return this;
	}
	
	public WarrantBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<WarrantBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new WarrantBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new WarrantBO(getBaseVO());
		}
	}
	
	public WarrantBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public WarrantBO insert(FieldDescriptor... specificFields) throws Exception {
		return new WarrantBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public WarrantBO update(FieldDescriptor... specificFields) throws Exception {
		return new WarrantBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public WarrantBO delete() throws Exception {
		return new WarrantBO(super.delete(getNativeDescriptor()));
	}
	
	public WarrantBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public WarrantBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public WarrantBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public WarrantBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public WarrantBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public WarrantBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public WarrantBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public WarrantBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public WarrantBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public WarrantBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WarrantBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WarrantBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public WarrantBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public WarrantBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public WarrantBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WarrantBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WarrantBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public WarrantBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public WarrantBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public WarrantBO where(FieldDescriptor findBy, Object value) {
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
	
	public WarrantBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public WarrantBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public WarrantBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public WarrantBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public WarrantBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public WarrantBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public WarrantBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public WarrantBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public WarrantBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public WarrantBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public WarrantBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public WarrantBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public WarrantBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public WarrantBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public WarrantBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public WarrantBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<WarrantBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public WarrantBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public WarrantBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public WarrantBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public WarrantBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public WarrantBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public WarrantBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public WarrantBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public WarrantBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public WarrantBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public WarrantBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public WarrantBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public WarrantBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public WarrantBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public WarrantBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public WarrantBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public WarrantBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public WarrantBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public WarrantBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public WarrantBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public WarrantBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public WarrantBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public WarrantBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public WarrantBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public WarrantBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public WarrantBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public WarrantBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative WarrantBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public WarrantBO setQuestionMarks(Object... values) {
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
	public WarrantBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public WarrantBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public WarrantBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public WarrantBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public WarrantBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public WarrantBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public WarrantBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public WarrantBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public WarrantBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public WarrantBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public WarrantBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public WarrantBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public WarrantBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}