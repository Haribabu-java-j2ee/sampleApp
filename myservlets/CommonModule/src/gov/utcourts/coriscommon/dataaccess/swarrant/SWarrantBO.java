package gov.utcourts.coriscommon.dataaccess.swarrant;

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
public class SWarrantBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("s_warrant", "gov.utcourts.coriscommon.dataaccess.swarrant.SWarrant");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor SINTCASENUM = new FieldDescriptor(TABLE, "s_int_case_num", "setSIntCaseNum", 0);
	public static final FieldDescriptor SUSERIDSRL = new FieldDescriptor(TABLE, "s_userid_srl", "setSUseridSrl", 1);
	public static final FieldDescriptor SDATETIME = new FieldDescriptor(TABLE, "s_datetime", "setSDatetime", 2);
	public static final FieldDescriptor SMEID = new FieldDescriptor(TABLE, "s_me_id", "setSMeId", 3);
	public static final FieldDescriptor SOPERATION = new FieldDescriptor(TABLE, "s_operation", "setSOperation", 4);
	public static final FieldDescriptor WARRNUM = new FieldDescriptor(TABLE, "warr_num", "setWarrNum", 5);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 6);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 7);
	public static final FieldDescriptor CREATEDATETIME = new FieldDescriptor(TABLE, "create_datetime", "setCreateDatetime", 8);
	public static final FieldDescriptor TYPE = new FieldDescriptor(TABLE, "type", "setType", 9);
	public static final FieldDescriptor ORDERTYPE = new FieldDescriptor(TABLE, "order_type", "setOrderType", 10);
	public static final FieldDescriptor ORDERDATE = new FieldDescriptor(TABLE, "order_date", "setOrderDate", 11);
	public static final FieldDescriptor ISSUEDATE = new FieldDescriptor(TABLE, "issue_date", "setIssueDate", 12);
	public static final FieldDescriptor PRINTDATE = new FieldDescriptor(TABLE, "print_date", "setPrintDate", 13);
	public static final FieldDescriptor RECALLDATE = new FieldDescriptor(TABLE, "recall_date", "setRecallDate", 14);
	public static final FieldDescriptor EXPDATE = new FieldDescriptor(TABLE, "exp_date", "setExpDate", 15);
	public static final FieldDescriptor ISSUEJUDGEID = new FieldDescriptor(TABLE, "issue_judge_id", "setIssueJudgeId", 16);
	public static final FieldDescriptor ISSUECLERKID = new FieldDescriptor(TABLE, "issue_clerk_id", "setIssueClerkId", 17);
	public static final FieldDescriptor ISSUEREASONCODE = new FieldDescriptor(TABLE, "issue_reason_code", "setIssueReasonCode", 18);
	public static final FieldDescriptor OTHERREASONTEXT = new FieldDescriptor(TABLE, "other_reason_text", "setOtherReasonText", 19);
	public static final FieldDescriptor RECALLREASONCODE = new FieldDescriptor(TABLE, "recall_reason_code", "setRecallReasonCode", 20);
	public static final FieldDescriptor SWWSSTATUS = new FieldDescriptor(TABLE, "swws_status", "setSwwsStatus", 21);
	public static final FieldDescriptor SLCSTATUS = new FieldDescriptor(TABLE, "slc_status", "setSlcStatus", 22);
	public static final FieldDescriptor NCICSTATUS = new FieldDescriptor(TABLE, "ncic_status", "setNcicStatus", 23);
	public static final FieldDescriptor BAILFLAG = new FieldDescriptor(TABLE, "bail_flag", "setBailFlag", 24);
	public static final FieldDescriptor BAILAMT = new FieldDescriptor(TABLE, "bail_amt", "setBailAmt", 25);
	public static final FieldDescriptor FEEFLAG = new FieldDescriptor(TABLE, "fee_flag", "setFeeFlag", 26);
	public static final FieldDescriptor MEID = new FieldDescriptor(TABLE, "me_id", "setMeId", 27);
	public static final FieldDescriptor RECALLMEID = new FieldDescriptor(TABLE, "recall_me_id", "setRecallMeId", 28);
	public static final FieldDescriptor RECALLUSERIDSRL = new FieldDescriptor(TABLE, "recall_userid_srl", "setRecallUseridSrl", 29);

	public SWarrantBO() {
		super(new SWarrantVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public SWarrantBO(String source) {
		super(new SWarrantVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public SWarrantBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getSIntCaseNum() {
		return getBaseVO().getTypeInteger(SINTCASENUM).getValue();
	}
	
	public SWarrantBO setSIntCaseNum(Integer sIntCaseNum) {
		setPrimaryKey(SINTCASENUM, sIntCaseNum);
		return this;
	}

	public int getSUseridSrl() {
		return getBaseVO().getTypeInteger(SUSERIDSRL).getValue();
	}
	
	public SWarrantBO setSUseridSrl(Integer sUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUSERIDSRL, sUseridSrl);
		return this;
	}

	public Date getSDatetime() {
		return getBaseVO().getTypeDate(SDATETIME).getValue();
	}
	
	public SWarrantBO setSDatetime(Date sDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime);
		return this;
	}
	
	public SWarrantBO setSDatetime(Date sDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime, dateFormat);
		getBaseVO().setAttribute(SDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getSMeId() {
		return getBaseVO().getTypeInteger(SMEID).getValue();
	}
	
	public SWarrantBO setSMeId(Integer sMeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SMEID, sMeId);
		return this;
	}

	public String getSOperation() {
		return getBaseVO().getTypeString(SOPERATION).getValue();
	}
	
	public SWarrantBO setSOperation(String sOperation) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SOPERATION, sOperation);
		return this;
	}

	public int getWarrNum() {
		return getBaseVO().getTypeInteger(WARRNUM).getValue();
	}
	
	public SWarrantBO setWarrNum(Integer warrNum) {
		setPrimaryKey(WARRNUM, warrNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public SWarrantBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public SWarrantBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public Date getCreateDatetime() {
		return getBaseVO().getTypeDate(CREATEDATETIME).getValue();
	}
	
	public SWarrantBO setCreateDatetime(Date createDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime);
		return this;
	}
	
	public SWarrantBO setCreateDatetime(Date createDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREATEDATETIME, createDatetime, dateFormat);
		getBaseVO().setAttribute(CREATEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getType() {
		return getBaseVO().getTypeString(TYPE).getValue();
	}
	
	public SWarrantBO setType(String type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TYPE, type);
		return this;
	}

	public String getOrderType() {
		return getBaseVO().getTypeString(ORDERTYPE).getValue();
	}
	
	public SWarrantBO setOrderType(String orderType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERTYPE, orderType);
		return this;
	}

	public Date getOrderDate() {
		return getBaseVO().getTypeDate(ORDERDATE).getValue();
	}
	
	public SWarrantBO setOrderDate(Date orderDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERDATE, orderDate);
		return this;
	}
	
	public SWarrantBO setOrderDate(Date orderDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORDERDATE, orderDate, dateFormat);
		getBaseVO().setAttribute(ORDERDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIssueDate() {
		return getBaseVO().getTypeDate(ISSUEDATE).getValue();
	}
	
	public SWarrantBO setIssueDate(Date issueDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEDATE, issueDate);
		return this;
	}
	
	public SWarrantBO setIssueDate(Date issueDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEDATE, issueDate, dateFormat);
		getBaseVO().setAttribute(ISSUEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getPrintDate() {
		return getBaseVO().getTypeDate(PRINTDATE).getValue();
	}
	
	public SWarrantBO setPrintDate(Date printDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRINTDATE, printDate);
		return this;
	}
	
	public SWarrantBO setPrintDate(Date printDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRINTDATE, printDate, dateFormat);
		getBaseVO().setAttribute(PRINTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getRecallDate() {
		return getBaseVO().getTypeDate(RECALLDATE).getValue();
	}
	
	public SWarrantBO setRecallDate(Date recallDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLDATE, recallDate);
		return this;
	}
	
	public SWarrantBO setRecallDate(Date recallDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLDATE, recallDate, dateFormat);
		getBaseVO().setAttribute(RECALLDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getExpDate() {
		return getBaseVO().getTypeDate(EXPDATE).getValue();
	}
	
	public SWarrantBO setExpDate(Date expDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EXPDATE, expDate);
		return this;
	}
	
	public SWarrantBO setExpDate(Date expDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EXPDATE, expDate, dateFormat);
		getBaseVO().setAttribute(EXPDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getIssueJudgeId() {
		return getBaseVO().getTypeInteger(ISSUEJUDGEID).getValue();
	}
	
	public SWarrantBO setIssueJudgeId(Integer issueJudgeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEJUDGEID, issueJudgeId);
		return this;
	}

	public int getIssueClerkId() {
		return getBaseVO().getTypeInteger(ISSUECLERKID).getValue();
	}
	
	public SWarrantBO setIssueClerkId(Integer issueClerkId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUECLERKID, issueClerkId);
		return this;
	}

	public String getIssueReasonCode() {
		return getBaseVO().getTypeString(ISSUEREASONCODE).getValue();
	}
	
	public SWarrantBO setIssueReasonCode(String issueReasonCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ISSUEREASONCODE, issueReasonCode);
		return this;
	}

	public String getOtherReasonText() {
		return getBaseVO().getTypeString(OTHERREASONTEXT).getValue();
	}
	
	public SWarrantBO setOtherReasonText(String otherReasonText) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OTHERREASONTEXT, otherReasonText);
		return this;
	}

	public String getRecallReasonCode() {
		return getBaseVO().getTypeString(RECALLREASONCODE).getValue();
	}
	
	public SWarrantBO setRecallReasonCode(String recallReasonCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLREASONCODE, recallReasonCode);
		return this;
	}

	public String getSwwsStatus() {
		return getBaseVO().getTypeString(SWWSSTATUS).getValue();
	}
	
	public SWarrantBO setSwwsStatus(String swwsStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SWWSSTATUS, swwsStatus);
		return this;
	}

	public String getSlcStatus() {
		return getBaseVO().getTypeString(SLCSTATUS).getValue();
	}
	
	public SWarrantBO setSlcStatus(String slcStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SLCSTATUS, slcStatus);
		return this;
	}

	public String getNcicStatus() {
		return getBaseVO().getTypeString(NCICSTATUS).getValue();
	}
	
	public SWarrantBO setNcicStatus(String ncicStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICSTATUS, ncicStatus);
		return this;
	}

	public String getBailFlag() {
		return getBaseVO().getTypeString(BAILFLAG).getValue();
	}
	
	public SWarrantBO setBailFlag(String bailFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILFLAG, bailFlag);
		return this;
	}

	public BigDecimal getBailAmt() {
		return getBaseVO().getTypeBigDecimal(BAILAMT).getValue();
	}
	
	public SWarrantBO setBailAmt(BigDecimal bailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILAMT, bailAmt);
		return this;
	}
	
	public SWarrantBO setBailAmt(double bailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BAILAMT, new BigDecimal(String.valueOf(new Double(bailAmt))));
		return this;
	}

	public String getFeeFlag() {
		return getBaseVO().getTypeString(FEEFLAG).getValue();
	}
	
	public SWarrantBO setFeeFlag(String feeFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FEEFLAG, feeFlag);
		return this;
	}

	public int getMeId() {
		return getBaseVO().getTypeInteger(MEID).getValue();
	}
	
	public SWarrantBO setMeId(Integer meId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEID, meId);
		return this;
	}

	public int getRecallMeId() {
		return getBaseVO().getTypeInteger(RECALLMEID).getValue();
	}
	
	public SWarrantBO setRecallMeId(Integer recallMeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLMEID, recallMeId);
		return this;
	}

	public int getRecallUseridSrl() {
		return getBaseVO().getTypeInteger(RECALLUSERIDSRL).getValue();
	}
	
	public SWarrantBO setRecallUseridSrl(Integer recallUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECALLUSERIDSRL, recallUseridSrl);
		return this;
	}
	
	public SWarrantBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<SWarrantBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new SWarrantBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new SWarrantBO(getBaseVO());
		}
	}
	
	public SWarrantBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public SWarrantBO insert(FieldDescriptor... specificFields) throws Exception {
		return new SWarrantBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public SWarrantBO update(FieldDescriptor... specificFields) throws Exception {
		return new SWarrantBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public SWarrantBO delete() throws Exception {
		return new SWarrantBO(super.delete(getNativeDescriptor()));
	}
	
	public SWarrantBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SWarrantBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SWarrantBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public SWarrantBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public SWarrantBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public SWarrantBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public SWarrantBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SWarrantBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SWarrantBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public SWarrantBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SWarrantBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SWarrantBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public SWarrantBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public SWarrantBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public SWarrantBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SWarrantBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SWarrantBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public SWarrantBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SWarrantBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public SWarrantBO where(FieldDescriptor findBy, Object value) {
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
	
	public SWarrantBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SWarrantBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public SWarrantBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public SWarrantBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public SWarrantBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public SWarrantBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public SWarrantBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public SWarrantBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public SWarrantBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public SWarrantBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public SWarrantBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public SWarrantBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public SWarrantBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public SWarrantBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public SWarrantBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public SWarrantBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<SWarrantBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public SWarrantBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public SWarrantBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SWarrantBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public SWarrantBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public SWarrantBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public SWarrantBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public SWarrantBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public SWarrantBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public SWarrantBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public SWarrantBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public SWarrantBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public SWarrantBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SWarrantBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SWarrantBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SWarrantBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public SWarrantBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public SWarrantBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public SWarrantBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public SWarrantBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public SWarrantBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public SWarrantBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public SWarrantBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public SWarrantBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SWarrantBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SWarrantBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public SWarrantBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative SWarrantBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public SWarrantBO setQuestionMarks(Object... values) {
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
	public SWarrantBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public SWarrantBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public SWarrantBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public SWarrantBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public SWarrantBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public SWarrantBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public SWarrantBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public SWarrantBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public SWarrantBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public SWarrantBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public SWarrantBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public SWarrantBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public SWarrantBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}