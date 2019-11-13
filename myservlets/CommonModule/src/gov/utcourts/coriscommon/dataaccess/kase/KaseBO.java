package gov.utcourts.coriscommon.dataaccess.kase;

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
public class KaseBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("kase", "gov.utcourts.coriscommon.dataaccess.kase.Kase");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 0);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 1);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 2);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 3);
	public static final FieldDescriptor CASETYPE = new FieldDescriptor(TABLE, "case_type", "setCaseType", 4);
	public static final FieldDescriptor FILINGTYPE = new FieldDescriptor(TABLE, "filing_type", "setFilingType", 5);
	public static final FieldDescriptor FILINGDATE = new FieldDescriptor(TABLE, "filing_date", "setFilingDate", 6);
	public static final FieldDescriptor DISPDATE = new FieldDescriptor(TABLE, "disp_date", "setDispDate", 7);
	public static final FieldDescriptor DISPCODE = new FieldDescriptor(TABLE, "disp_code", "setDispCode", 8);
	public static final FieldDescriptor OLDCASENUM = new FieldDescriptor(TABLE, "old_case_num", "setOldCaseNum", 9);
	public static final FieldDescriptor REFNUM = new FieldDescriptor(TABLE, "ref_num", "setRefNum", 10);
	public static final FieldDescriptor ARCHIVEDATE = new FieldDescriptor(TABLE, "archive_date", "setArchiveDate", 11);
	public static final FieldDescriptor ASSNJUDGEID = new FieldDescriptor(TABLE, "assn_judge_id", "setAssnJudgeId", 12);
	public static final FieldDescriptor ASSNCOMMID = new FieldDescriptor(TABLE, "assn_comm_id", "setAssnCommId", 13);
	public static final FieldDescriptor DISPID = new FieldDescriptor(TABLE, "disp_id", "setDispId", 14);
	public static final FieldDescriptor DOMVIOLENCE = new FieldDescriptor(TABLE, "dom_violence", "setDomViolence", 15);
	public static final FieldDescriptor NOTE = new FieldDescriptor(TABLE, "note", "setNote", 16);
	public static final FieldDescriptor LINKEDCASEID = new FieldDescriptor(TABLE, "linked_case_id", "setLinkedCaseId", 17);
	public static final FieldDescriptor LINKEDDEFID = new FieldDescriptor(TABLE, "linked_def_id", "setLinkedDefId", 18);
	public static final FieldDescriptor CALSTATUS = new FieldDescriptor(TABLE, "cal_status", "setCalStatus", 19);
	public static final FieldDescriptor TRACKDATE = new FieldDescriptor(TABLE, "track_date", "setTrackDate", 20);
	public static final FieldDescriptor DEBTCOLLECTION = new FieldDescriptor(TABLE, "debt_collection", "setDebtCollection", 21);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 22);
	public static final FieldDescriptor TRANSFERRED = new FieldDescriptor(TABLE, "transferred", "setTransferred", 23);
	public static final FieldDescriptor LOCALDEBTCOLL = new FieldDescriptor(TABLE, "local_debt_coll", "setLocalDebtColl", 24);
	public static final FieldDescriptor DEBTCOLLDATE = new FieldDescriptor(TABLE, "debt_coll_date", "setDebtCollDate", 25);
	public static final FieldDescriptor DEBTCOLLUSERID = new FieldDescriptor(TABLE, "debt_coll_userid", "setDebtCollUserid", 26);
	public static final FieldDescriptor EDOCNUM = new FieldDescriptor(TABLE, "edoc_num", "setEdocNum", 27);
	public static final FieldDescriptor DEBTCOLLNOTE = new FieldDescriptor(TABLE, "debt_coll_note", "setDebtCollNote", 28);
	public static final FieldDescriptor CASESECURITY = new FieldDescriptor(TABLE, "case_security", "setCaseSecurity", 29);

	public KaseBO() {
		super(new KaseVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public KaseBO(String source) {
		super(new KaseVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public KaseBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public KaseBO setIntCaseNum(Integer intCaseNum) {
		setPrimaryKey(INTCASENUM, intCaseNum);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public KaseBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public KaseBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public KaseBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public String getCaseType() {
		return getBaseVO().getTypeString(CASETYPE).getValue();
	}
	
	public KaseBO setCaseType(String caseType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASETYPE, caseType);
		return this;
	}

	public String getFilingType() {
		return getBaseVO().getTypeString(FILINGTYPE).getValue();
	}
	
	public KaseBO setFilingType(String filingType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FILINGTYPE, filingType);
		return this;
	}

	public Date getFilingDate() {
		return getBaseVO().getTypeDate(FILINGDATE).getValue();
	}
	
	public KaseBO setFilingDate(Date filingDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FILINGDATE, filingDate);
		return this;
	}
	
	public KaseBO setFilingDate(Date filingDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FILINGDATE, filingDate, dateFormat);
		getBaseVO().setAttribute(FILINGDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getDispDate() {
		return getBaseVO().getTypeDate(DISPDATE).getValue();
	}
	
	public KaseBO setDispDate(Date dispDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISPDATE, dispDate);
		return this;
	}
	
	public KaseBO setDispDate(Date dispDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISPDATE, dispDate, dateFormat);
		getBaseVO().setAttribute(DISPDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDispCode() {
		return getBaseVO().getTypeString(DISPCODE).getValue();
	}
	
	public KaseBO setDispCode(String dispCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISPCODE, dispCode);
		return this;
	}

	public String getOldCaseNum() {
		return getBaseVO().getTypeString(OLDCASENUM).getValue();
	}
	
	public KaseBO setOldCaseNum(String oldCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OLDCASENUM, oldCaseNum);
		return this;
	}

	public String getRefNum() {
		return getBaseVO().getTypeString(REFNUM).getValue();
	}
	
	public KaseBO setRefNum(String refNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REFNUM, refNum);
		return this;
	}

	public Date getArchiveDate() {
		return getBaseVO().getTypeDate(ARCHIVEDATE).getValue();
	}
	
	public KaseBO setArchiveDate(Date archiveDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ARCHIVEDATE, archiveDate);
		return this;
	}
	
	public KaseBO setArchiveDate(Date archiveDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ARCHIVEDATE, archiveDate, dateFormat);
		getBaseVO().setAttribute(ARCHIVEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getAssnJudgeId() {
		return getBaseVO().getTypeInteger(ASSNJUDGEID).getValue();
	}
	
	public KaseBO setAssnJudgeId(Integer assnJudgeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ASSNJUDGEID, assnJudgeId);
		return this;
	}

	public int getAssnCommId() {
		return getBaseVO().getTypeInteger(ASSNCOMMID).getValue();
	}
	
	public KaseBO setAssnCommId(Integer assnCommId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ASSNCOMMID, assnCommId);
		return this;
	}

	public int getDispId() {
		return getBaseVO().getTypeInteger(DISPID).getValue();
	}
	
	public KaseBO setDispId(Integer dispId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISPID, dispId);
		return this;
	}

	public String getDomViolence() {
		return getBaseVO().getTypeString(DOMVIOLENCE).getValue();
	}
	
	public KaseBO setDomViolence(String domViolence) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DOMVIOLENCE, domViolence);
		return this;
	}

	public String getNote() {
		return getBaseVO().getTypeString(NOTE).getValue();
	}
	
	public KaseBO setNote(String note) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NOTE, note);
		return this;
	}

	public int getLinkedCaseId() {
		return getBaseVO().getTypeInteger(LINKEDCASEID).getValue();
	}
	
	public KaseBO setLinkedCaseId(Integer linkedCaseId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LINKEDCASEID, linkedCaseId);
		return this;
	}

	public int getLinkedDefId() {
		return getBaseVO().getTypeInteger(LINKEDDEFID).getValue();
	}
	
	public KaseBO setLinkedDefId(Integer linkedDefId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LINKEDDEFID, linkedDefId);
		return this;
	}

	public String getCalStatus() {
		return getBaseVO().getTypeString(CALSTATUS).getValue();
	}
	
	public KaseBO setCalStatus(String calStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CALSTATUS, calStatus);
		return this;
	}

	public Date getTrackDate() {
		return getBaseVO().getTypeDate(TRACKDATE).getValue();
	}
	
	public KaseBO setTrackDate(Date trackDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRACKDATE, trackDate);
		return this;
	}
	
	public KaseBO setTrackDate(Date trackDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRACKDATE, trackDate, dateFormat);
		getBaseVO().setAttribute(TRACKDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDebtCollection() {
		return getBaseVO().getTypeString(DEBTCOLLECTION).getValue();
	}
	
	public KaseBO setDebtCollection(String debtCollection) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLECTION, debtCollection);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public KaseBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public String getTransferred() {
		return getBaseVO().getTypeString(TRANSFERRED).getValue();
	}
	
	public KaseBO setTransferred(String transferred) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRANSFERRED, transferred);
		return this;
	}

	public String getLocalDebtColl() {
		return getBaseVO().getTypeString(LOCALDEBTCOLL).getValue();
	}
	
	public KaseBO setLocalDebtColl(String localDebtColl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCALDEBTCOLL, localDebtColl);
		return this;
	}

	public Date getDebtCollDate() {
		return getBaseVO().getTypeDate(DEBTCOLLDATE).getValue();
	}
	
	public KaseBO setDebtCollDate(Date debtCollDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLDATE, debtCollDate);
		return this;
	}
	
	public KaseBO setDebtCollDate(Date debtCollDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLDATE, debtCollDate, dateFormat);
		getBaseVO().setAttribute(DEBTCOLLDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getDebtCollUserid() {
		return getBaseVO().getTypeInteger(DEBTCOLLUSERID).getValue();
	}
	
	public KaseBO setDebtCollUserid(Integer debtCollUserid) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLUSERID, debtCollUserid);
		return this;
	}

	public String getEdocNum() {
		return getBaseVO().getTypeString(EDOCNUM).getValue();
	}
	
	public KaseBO setEdocNum(String edocNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EDOCNUM, edocNum);
		return this;
	}

	public String getDebtCollNote() {
		return getBaseVO().getTypeString(DEBTCOLLNOTE).getValue();
	}
	
	public KaseBO setDebtCollNote(String debtCollNote) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLNOTE, debtCollNote);
		return this;
	}

	public String getCaseSecurity() {
		return getBaseVO().getTypeString(CASESECURITY).getValue();
	}
	
	public KaseBO setCaseSecurity(String caseSecurity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASESECURITY, caseSecurity);
		return this;
	}
	
	public KaseBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<KaseBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new KaseBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new KaseBO(getBaseVO());
		}
	}
	
	public KaseBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public KaseBO insert(FieldDescriptor... specificFields) throws Exception {
		return new KaseBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public KaseBO update(FieldDescriptor... specificFields) throws Exception {
		return new KaseBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public KaseBO delete() throws Exception {
		return new KaseBO(super.delete(getNativeDescriptor()));
	}
	
	public KaseBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public KaseBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public KaseBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public KaseBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public KaseBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public KaseBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public KaseBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public KaseBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public KaseBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public KaseBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public KaseBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public KaseBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public KaseBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public KaseBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public KaseBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public KaseBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public KaseBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public KaseBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public KaseBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public KaseBO where(FieldDescriptor findBy, Object value) {
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
	
	public KaseBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public KaseBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public KaseBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public KaseBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public KaseBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public KaseBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public KaseBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public KaseBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public KaseBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public KaseBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public KaseBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public KaseBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public KaseBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public KaseBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public KaseBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public KaseBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<KaseBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public KaseBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public KaseBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public KaseBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public KaseBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public KaseBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public KaseBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public KaseBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public KaseBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public KaseBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public KaseBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public KaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public KaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public KaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public KaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public KaseBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public KaseBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public KaseBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public KaseBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public KaseBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public KaseBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public KaseBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public KaseBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public KaseBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public KaseBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public KaseBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public KaseBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative KaseBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public KaseBO setQuestionMarks(Object... values) {
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
	public KaseBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public KaseBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public KaseBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public KaseBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public KaseBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public KaseBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public KaseBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public KaseBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public KaseBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public KaseBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public KaseBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public KaseBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public KaseBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}