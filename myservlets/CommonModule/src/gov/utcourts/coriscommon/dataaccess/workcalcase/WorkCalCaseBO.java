package gov.utcourts.coriscommon.dataaccess.workcalcase;

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
public class WorkCalCaseBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("work_cal_case", "gov.utcourts.coriscommon.dataaccess.workcalcase.WorkCalCase");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 0);
	public static final FieldDescriptor HEARINGCODE = new FieldDescriptor(TABLE, "hearing_code", "setHearingCode", 1);
	public static final FieldDescriptor APPEARDATE = new FieldDescriptor(TABLE, "appear_date", "setAppearDate", 2);
	public static final FieldDescriptor CREATEDATETIME = new FieldDescriptor(TABLE, "create_datetime", "setCreateDatetime", 3);
	public static final FieldDescriptor HEARINGTIME = new FieldDescriptor(TABLE, "hearing_time", "setHearingTime", 4);
	public static final FieldDescriptor CASETYPE = new FieldDescriptor(TABLE, "case_type", "setCaseType", 5);
	public static final FieldDescriptor PRIORITY = new FieldDescriptor(TABLE, "priority", "setPriority", 6);
	public static final FieldDescriptor PLAINTIFFCOUNT = new FieldDescriptor(TABLE, "plaintiff_count", "setPlaintiffCount", 7);
	public static final FieldDescriptor DEFENDANTCOUNT = new FieldDescriptor(TABLE, "defendant_count", "setDefendantCount", 8);
	public static final FieldDescriptor JUDGEID = new FieldDescriptor(TABLE, "judge_id", "setJudgeId", 9);
	public static final FieldDescriptor TITLEPLANUM = new FieldDescriptor(TABLE, "title_pla_num", "setTitlePlaNum", 10);
	public static final FieldDescriptor TITLEDEFNUM = new FieldDescriptor(TABLE, "title_def_num", "setTitleDefNum", 11);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 12);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 13);
	public static final FieldDescriptor CASETYPECATEGORY = new FieldDescriptor(TABLE, "case_type_category", "setCaseTypeCategory", 14);
	public static final FieldDescriptor ROOM = new FieldDescriptor(TABLE, "room", "setRoom", 15);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 16);
	public static final FieldDescriptor CASETYPEDESCR = new FieldDescriptor(TABLE, "case_type_descr", "setCaseTypeDescr", 17);
	public static final FieldDescriptor HEARINGDESCR = new FieldDescriptor(TABLE, "hearing_descr", "setHearingDescr", 18);
	public static final FieldDescriptor JUDGEFIRSTNAME = new FieldDescriptor(TABLE, "judge_first_name", "setJudgeFirstName", 19);
	public static final FieldDescriptor JUDGELASTNAME = new FieldDescriptor(TABLE, "judge_last_name", "setJudgeLastName", 20);
	public static final FieldDescriptor EVENTDESCR = new FieldDescriptor(TABLE, "event_descr", "setEventDescr", 21);
	public static final FieldDescriptor CASETITLE = new FieldDescriptor(TABLE, "case_title", "setCaseTitle", 22);
	public static final FieldDescriptor CASESECURITY = new FieldDescriptor(TABLE, "case_security", "setCaseSecurity", 23);

	public WorkCalCaseBO() {
		super(new WorkCalCaseVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public WorkCalCaseBO(String source) {
		super(new WorkCalCaseVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public WorkCalCaseBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public WorkCalCaseBO setIntCaseNum(Integer intCaseNum) {
		setPrimaryKey(INTCASENUM, intCaseNum);
		return this;
	}

	public String getHearingCode() {
		return getBaseVO().getTypeString(HEARINGCODE).getValue();
	}
	
	public WorkCalCaseBO setHearingCode(String hearingCode) {
		setPrimaryKey(HEARINGCODE, hearingCode);
		return this;
	}

	public Date getAppearDate() {
		return getBaseVO().getTypeDate(APPEARDATE).getValue();
	}
	
	public WorkCalCaseBO setAppearDate(Date appearDate) {
		setPrimaryKey(APPEARDATE, appearDate);
		return this;
	}
	
	public WorkCalCaseBO setAppearDate(Date appearDate, DateFormat dateFormat) {
		setPrimaryKey(APPEARDATE, appearDate);
		getBaseVO().setAttribute(APPEARDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getCreateDatetime() {
		return getBaseVO().getTypeDate(CREATEDATETIME).getValue();
	}
	
	public WorkCalCaseBO setCreateDatetime(Date createDatetime) {
		setPrimaryKey(CREATEDATETIME, createDatetime);
		return this;
	}
	
	public WorkCalCaseBO setCreateDatetime(Date createDatetime, DateFormat dateFormat) {
		setPrimaryKey(CREATEDATETIME, createDatetime);
		getBaseVO().setAttribute(CREATEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getHearingTime() {
		return getBaseVO().getTypeDate(HEARINGTIME).getValue();
	}
	
	public WorkCalCaseBO setHearingTime(Date hearingTime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HEARINGTIME, hearingTime);
		return this;
	}
	
	public WorkCalCaseBO setHearingTime(Date hearingTime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HEARINGTIME, hearingTime, dateFormat);
		getBaseVO().setAttribute(HEARINGTIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseType() {
		return getBaseVO().getTypeString(CASETYPE).getValue();
	}
	
	public WorkCalCaseBO setCaseType(String caseType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETYPE, caseType);
		return this;
	}

	public int getPriority() {
		return getBaseVO().getTypeInteger(PRIORITY).getValue();
	}
	
	public WorkCalCaseBO setPriority(Integer priority) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRIORITY, priority);
		return this;
	}

	public int getPlaintiffCount() {
		return getBaseVO().getTypeInteger(PLAINTIFFCOUNT).getValue();
	}
	
	public WorkCalCaseBO setPlaintiffCount(Integer plaintiffCount) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLAINTIFFCOUNT, plaintiffCount);
		return this;
	}

	public int getDefendantCount() {
		return getBaseVO().getTypeInteger(DEFENDANTCOUNT).getValue();
	}
	
	public WorkCalCaseBO setDefendantCount(Integer defendantCount) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFENDANTCOUNT, defendantCount);
		return this;
	}

	public int getJudgeId() {
		return getBaseVO().getTypeInteger(JUDGEID).getValue();
	}
	
	public WorkCalCaseBO setJudgeId(Integer judgeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JUDGEID, judgeId);
		return this;
	}

	public int getTitlePlaNum() {
		return getBaseVO().getTypeInteger(TITLEPLANUM).getValue();
	}
	
	public WorkCalCaseBO setTitlePlaNum(Integer titlePlaNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TITLEPLANUM, titlePlaNum);
		return this;
	}

	public int getTitleDefNum() {
		return getBaseVO().getTypeInteger(TITLEDEFNUM).getValue();
	}
	
	public WorkCalCaseBO setTitleDefNum(Integer titleDefNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TITLEDEFNUM, titleDefNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public WorkCalCaseBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public WorkCalCaseBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public String getCaseTypeCategory() {
		return getBaseVO().getTypeString(CASETYPECATEGORY).getValue();
	}
	
	public WorkCalCaseBO setCaseTypeCategory(String caseTypeCategory) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETYPECATEGORY, caseTypeCategory);
		return this;
	}

	public String getRoom() {
		return getBaseVO().getTypeString(ROOM).getValue();
	}
	
	public WorkCalCaseBO setRoom(String room) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ROOM, room);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public WorkCalCaseBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getCaseTypeDescr() {
		return getBaseVO().getTypeString(CASETYPEDESCR).getValue();
	}
	
	public WorkCalCaseBO setCaseTypeDescr(String caseTypeDescr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETYPEDESCR, caseTypeDescr);
		return this;
	}

	public String getHearingDescr() {
		return getBaseVO().getTypeString(HEARINGDESCR).getValue();
	}
	
	public WorkCalCaseBO setHearingDescr(String hearingDescr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HEARINGDESCR, hearingDescr);
		return this;
	}

	public String getJudgeFirstName() {
		return getBaseVO().getTypeString(JUDGEFIRSTNAME).getValue();
	}
	
	public WorkCalCaseBO setJudgeFirstName(String judgeFirstName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JUDGEFIRSTNAME, judgeFirstName);
		return this;
	}

	public String getJudgeLastName() {
		return getBaseVO().getTypeString(JUDGELASTNAME).getValue();
	}
	
	public WorkCalCaseBO setJudgeLastName(String judgeLastName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JUDGELASTNAME, judgeLastName);
		return this;
	}

	public String getEventDescr() {
		return getBaseVO().getTypeString(EVENTDESCR).getValue();
	}
	
	public WorkCalCaseBO setEventDescr(String eventDescr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EVENTDESCR, eventDescr);
		return this;
	}

	public String getCaseTitle() {
		return getBaseVO().getTypeString(CASETITLE).getValue();
	}
	
	public WorkCalCaseBO setCaseTitle(String caseTitle) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETITLE, caseTitle);
		return this;
	}

	public String getCaseSecurity() {
		return getBaseVO().getTypeString(CASESECURITY).getValue();
	}
	
	public WorkCalCaseBO setCaseSecurity(String caseSecurity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASESECURITY, caseSecurity);
		return this;
	}
	
	public WorkCalCaseBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<WorkCalCaseBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new WorkCalCaseBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new WorkCalCaseBO(getBaseVO());
		}
	}
	
	public WorkCalCaseBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public WorkCalCaseBO insert(FieldDescriptor... specificFields) throws Exception {
		return new WorkCalCaseBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public WorkCalCaseBO update(FieldDescriptor... specificFields) throws Exception {
		return new WorkCalCaseBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public WorkCalCaseBO delete() throws Exception {
		return new WorkCalCaseBO(super.delete(getNativeDescriptor()));
	}
	
	public WorkCalCaseBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public WorkCalCaseBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public WorkCalCaseBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public WorkCalCaseBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public WorkCalCaseBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public WorkCalCaseBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public WorkCalCaseBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public WorkCalCaseBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public WorkCalCaseBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public WorkCalCaseBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WorkCalCaseBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WorkCalCaseBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public WorkCalCaseBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public WorkCalCaseBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public WorkCalCaseBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WorkCalCaseBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public WorkCalCaseBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public WorkCalCaseBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public WorkCalCaseBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public WorkCalCaseBO where(FieldDescriptor findBy, Object value) {
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
	
	public WorkCalCaseBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public WorkCalCaseBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public WorkCalCaseBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public WorkCalCaseBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public WorkCalCaseBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public WorkCalCaseBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public WorkCalCaseBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public WorkCalCaseBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public WorkCalCaseBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public WorkCalCaseBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public WorkCalCaseBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public WorkCalCaseBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public WorkCalCaseBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public WorkCalCaseBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public WorkCalCaseBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public WorkCalCaseBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<WorkCalCaseBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public WorkCalCaseBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public WorkCalCaseBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public WorkCalCaseBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public WorkCalCaseBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public WorkCalCaseBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public WorkCalCaseBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public WorkCalCaseBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public WorkCalCaseBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public WorkCalCaseBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public WorkCalCaseBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public WorkCalCaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public WorkCalCaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public WorkCalCaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public WorkCalCaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public WorkCalCaseBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public WorkCalCaseBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public WorkCalCaseBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public WorkCalCaseBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public WorkCalCaseBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public WorkCalCaseBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public WorkCalCaseBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public WorkCalCaseBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public WorkCalCaseBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public WorkCalCaseBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public WorkCalCaseBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public WorkCalCaseBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative WorkCalCaseBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public WorkCalCaseBO setQuestionMarks(Object... values) {
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
	public WorkCalCaseBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public WorkCalCaseBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public WorkCalCaseBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public WorkCalCaseBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public WorkCalCaseBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public WorkCalCaseBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public WorkCalCaseBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public WorkCalCaseBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public WorkCalCaseBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public WorkCalCaseBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public WorkCalCaseBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public WorkCalCaseBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public WorkCalCaseBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}