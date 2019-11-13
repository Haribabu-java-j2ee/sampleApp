package gov.utcourts.coriscommon.dataaccess.partycaseold;

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
public class PartyCaseOldBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("party_case_old", "gov.utcourts.coriscommon.dataaccess.partycaseold.PartyCaseOld");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACTIONTYPE = new FieldDescriptor(TABLE, "action_type", "setActionType", 0);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 1);
	public static final FieldDescriptor CHANGEDATETIME = new FieldDescriptor(TABLE, "change_datetime", "setChangeDatetime", 2);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 3);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 4);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 5);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 6);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 7);
	public static final FieldDescriptor PARTYCODE = new FieldDescriptor(TABLE, "party_code", "setPartyCode", 8);
	public static final FieldDescriptor OTN = new FieldDescriptor(TABLE, "otn", "setOtn", 9);
	public static final FieldDescriptor ARRESTDATE = new FieldDescriptor(TABLE, "arrest_date", "setArrestDate", 10);
	public static final FieldDescriptor BOOKINGNUM = new FieldDescriptor(TABLE, "booking_num", "setBookingNum", 11);
	public static final FieldDescriptor SETBAILAMT = new FieldDescriptor(TABLE, "setbail_amt", "setSetbailAmt", 12);
	public static final FieldDescriptor SETBONDAMT = new FieldDescriptor(TABLE, "setbond_amt", "setSetbondAmt", 13);
	public static final FieldDescriptor CASHBAILFLAG = new FieldDescriptor(TABLE, "cash_bail_flag", "setCashBailFlag", 14);
	public static final FieldDescriptor PROSE = new FieldDescriptor(TABLE, "pro_se", "setProSe", 15);
	public static final FieldDescriptor RELATEDPARTYNUM = new FieldDescriptor(TABLE, "related_party_num", "setRelatedPartyNum", 16);
	public static final FieldDescriptor WARRSTATUS = new FieldDescriptor(TABLE, "warr_status", "setWarrStatus", 17);
	public static final FieldDescriptor SAFEGUARDED = new FieldDescriptor(TABLE, "safeguarded", "setSafeguarded", 18);
	public static final FieldDescriptor CUSTODIALPARENT = new FieldDescriptor(TABLE, "custodial_parent", "setCustodialParent", 19);
	public static final FieldDescriptor OTNAVAILABLE = new FieldDescriptor(TABLE, "otn_available", "setOtnAvailable", 20);
	public static final FieldDescriptor DISMISSED = new FieldDescriptor(TABLE, "dismissed", "setDismissed", 21);
	public static final FieldDescriptor PROBATERPTEXCUSE = new FieldDescriptor(TABLE, "probate_rpt_excuse", "setProbateRptExcuse", 22);
	public static final FieldDescriptor ODREXEMPT = new FieldDescriptor(TABLE, "odr_exempt", "setOdrExempt", 23);

	public PartyCaseOldBO() {
		super(new PartyCaseOldVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public PartyCaseOldBO(String source) {
		super(new PartyCaseOldVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public PartyCaseOldBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getActionType() {
		return getBaseVO().getTypeString(ACTIONTYPE).getValue();
	}
	
	public PartyCaseOldBO setActionType(String actionType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONTYPE, actionType);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public PartyCaseOldBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public Date getChangeDatetime() {
		return getBaseVO().getTypeDate(CHANGEDATETIME).getValue();
	}
	
	public PartyCaseOldBO setChangeDatetime(Date changeDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime);
		return this;
	}
	
	public PartyCaseOldBO setChangeDatetime(Date changeDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime, dateFormat);
		getBaseVO().setAttribute(CHANGEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public PartyCaseOldBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public PartyCaseOldBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public PartyCaseOldBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public PartyCaseOldBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public PartyCaseOldBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public String getPartyCode() {
		return getBaseVO().getTypeString(PARTYCODE).getValue();
	}
	
	public PartyCaseOldBO setPartyCode(String partyCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYCODE, partyCode);
		return this;
	}

	public String getOtn() {
		return getBaseVO().getTypeString(OTN).getValue();
	}
	
	public PartyCaseOldBO setOtn(String otn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OTN, otn);
		return this;
	}

	public Date getArrestDate() {
		return getBaseVO().getTypeDate(ARRESTDATE).getValue();
	}
	
	public PartyCaseOldBO setArrestDate(Date arrestDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ARRESTDATE, arrestDate);
		return this;
	}
	
	public PartyCaseOldBO setArrestDate(Date arrestDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ARRESTDATE, arrestDate, dateFormat);
		getBaseVO().setAttribute(ARRESTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getBookingNum() {
		return getBaseVO().getTypeString(BOOKINGNUM).getValue();
	}
	
	public PartyCaseOldBO setBookingNum(String bookingNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BOOKINGNUM, bookingNum);
		return this;
	}

	public BigDecimal getSetbailAmt() {
		return getBaseVO().getTypeBigDecimal(SETBAILAMT).getValue();
	}
	
	public PartyCaseOldBO setSetbailAmt(BigDecimal setbailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SETBAILAMT, setbailAmt);
		return this;
	}
	
	public PartyCaseOldBO setSetbailAmt(double setbailAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SETBAILAMT, new BigDecimal(String.valueOf(new Double(setbailAmt))));
		return this;
	}

	public BigDecimal getSetbondAmt() {
		return getBaseVO().getTypeBigDecimal(SETBONDAMT).getValue();
	}
	
	public PartyCaseOldBO setSetbondAmt(BigDecimal setbondAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SETBONDAMT, setbondAmt);
		return this;
	}
	
	public PartyCaseOldBO setSetbondAmt(double setbondAmt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SETBONDAMT, new BigDecimal(String.valueOf(new Double(setbondAmt))));
		return this;
	}

	public String getCashBailFlag() {
		return getBaseVO().getTypeString(CASHBAILFLAG).getValue();
	}
	
	public PartyCaseOldBO setCashBailFlag(String cashBailFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASHBAILFLAG, cashBailFlag);
		return this;
	}

	public String getProSe() {
		return getBaseVO().getTypeString(PROSE).getValue();
	}
	
	public PartyCaseOldBO setProSe(String proSe) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROSE, proSe);
		return this;
	}

	public int getRelatedPartyNum() {
		return getBaseVO().getTypeInteger(RELATEDPARTYNUM).getValue();
	}
	
	public PartyCaseOldBO setRelatedPartyNum(Integer relatedPartyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RELATEDPARTYNUM, relatedPartyNum);
		return this;
	}

	public String getWarrStatus() {
		return getBaseVO().getTypeString(WARRSTATUS).getValue();
	}
	
	public PartyCaseOldBO setWarrStatus(String warrStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARRSTATUS, warrStatus);
		return this;
	}

	public String getSafeguarded() {
		return getBaseVO().getTypeString(SAFEGUARDED).getValue();
	}
	
	public PartyCaseOldBO setSafeguarded(String safeguarded) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SAFEGUARDED, safeguarded);
		return this;
	}

	public String getCustodialParent() {
		return getBaseVO().getTypeString(CUSTODIALPARENT).getValue();
	}
	
	public PartyCaseOldBO setCustodialParent(String custodialParent) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CUSTODIALPARENT, custodialParent);
		return this;
	}

	public String getOtnAvailable() {
		return getBaseVO().getTypeString(OTNAVAILABLE).getValue();
	}
	
	public PartyCaseOldBO setOtnAvailable(String otnAvailable) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OTNAVAILABLE, otnAvailable);
		return this;
	}

	public String getDismissed() {
		return getBaseVO().getTypeString(DISMISSED).getValue();
	}
	
	public PartyCaseOldBO setDismissed(String dismissed) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DISMISSED, dismissed);
		return this;
	}

	public String getProbateRptExcuse() {
		return getBaseVO().getTypeString(PROBATERPTEXCUSE).getValue();
	}
	
	public PartyCaseOldBO setProbateRptExcuse(String probateRptExcuse) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROBATERPTEXCUSE, probateRptExcuse);
		return this;
	}

	public String getOdrExempt() {
		return getBaseVO().getTypeString(ODREXEMPT).getValue();
	}
	
	public PartyCaseOldBO setOdrExempt(String odrExempt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ODREXEMPT, odrExempt);
		return this;
	}
	
	public PartyCaseOldBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<PartyCaseOldBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new PartyCaseOldBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new PartyCaseOldBO(getBaseVO());
		}
	}
	
	public PartyCaseOldBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public PartyCaseOldBO insert(FieldDescriptor... specificFields) throws Exception {
		return new PartyCaseOldBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public PartyCaseOldBO update(FieldDescriptor... specificFields) throws Exception {
		return new PartyCaseOldBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public PartyCaseOldBO delete() throws Exception {
		return new PartyCaseOldBO(super.delete(getNativeDescriptor()));
	}
	
	public PartyCaseOldBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PartyCaseOldBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public PartyCaseOldBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public PartyCaseOldBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public PartyCaseOldBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public PartyCaseOldBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public PartyCaseOldBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PartyCaseOldBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public PartyCaseOldBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public PartyCaseOldBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyCaseOldBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyCaseOldBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public PartyCaseOldBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public PartyCaseOldBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public PartyCaseOldBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyCaseOldBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public PartyCaseOldBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public PartyCaseOldBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PartyCaseOldBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public PartyCaseOldBO where(FieldDescriptor findBy, Object value) {
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
	
	public PartyCaseOldBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public PartyCaseOldBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public PartyCaseOldBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public PartyCaseOldBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public PartyCaseOldBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public PartyCaseOldBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public PartyCaseOldBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public PartyCaseOldBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public PartyCaseOldBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public PartyCaseOldBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public PartyCaseOldBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public PartyCaseOldBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public PartyCaseOldBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public PartyCaseOldBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public PartyCaseOldBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public PartyCaseOldBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyCaseOldBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public PartyCaseOldBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public PartyCaseOldBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PartyCaseOldBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public PartyCaseOldBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public PartyCaseOldBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public PartyCaseOldBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public PartyCaseOldBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public PartyCaseOldBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public PartyCaseOldBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public PartyCaseOldBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public PartyCaseOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public PartyCaseOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PartyCaseOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public PartyCaseOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public PartyCaseOldBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public PartyCaseOldBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public PartyCaseOldBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public PartyCaseOldBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public PartyCaseOldBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public PartyCaseOldBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public PartyCaseOldBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public PartyCaseOldBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public PartyCaseOldBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PartyCaseOldBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public PartyCaseOldBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public PartyCaseOldBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative PartyCaseOldBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public PartyCaseOldBO setQuestionMarks(Object... values) {
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
	public PartyCaseOldBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public PartyCaseOldBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public PartyCaseOldBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public PartyCaseOldBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public PartyCaseOldBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public PartyCaseOldBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public PartyCaseOldBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public PartyCaseOldBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public PartyCaseOldBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public PartyCaseOldBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public PartyCaseOldBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public PartyCaseOldBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public PartyCaseOldBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}