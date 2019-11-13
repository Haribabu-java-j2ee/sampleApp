package gov.utcourts.coriscommon.dataaccess.crimcase;

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
public class CrimCaseBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("crim_case", "gov.utcourts.coriscommon.dataaccess.crimcase.CrimCase");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 0);
	public static final FieldDescriptor CITNUM = new FieldDescriptor(TABLE, "cit_num", "setCitNum", 1);
	public static final FieldDescriptor LEA = new FieldDescriptor(TABLE, "lea", "setLea", 2);
	public static final FieldDescriptor LEACASENUM = new FieldDescriptor(TABLE, "lea_case_num", "setLeaCaseNum", 3);
	public static final FieldDescriptor PROSECAGENCY = new FieldDescriptor(TABLE, "prosec_agency", "setProsecAgency", 4);
	public static final FieldDescriptor PROSECAGENCYNUM = new FieldDescriptor(TABLE, "prosec_agency_num", "setProsecAgencyNum", 5);
	public static final FieldDescriptor OFFICERSRL = new FieldDescriptor(TABLE, "officer_srl", "setOfficerSrl", 6);
	public static final FieldDescriptor COMMVEHICLE = new FieldDescriptor(TABLE, "comm_vehicle", "setCommVehicle", 7);
	public static final FieldDescriptor HAZMATERIAL = new FieldDescriptor(TABLE, "haz_material", "setHazMaterial", 8);
	public static final FieldDescriptor FTASTATUS = new FieldDescriptor(TABLE, "fta_status", "setFtaStatus", 9);
	public static final FieldDescriptor FTAORDERDATE = new FieldDescriptor(TABLE, "fta_order_date", "setFtaOrderDate", 10);
	public static final FieldDescriptor FTAISSUEDATE = new FieldDescriptor(TABLE, "fta_issue_date", "setFtaIssueDate", 11);
	public static final FieldDescriptor FTACLEARDATE = new FieldDescriptor(TABLE, "fta_clear_date", "setFtaClearDate", 12);
	public static final FieldDescriptor DELINQNOTICEDATE = new FieldDescriptor(TABLE, "delinq_notice_date", "setDelinqNoticeDate", 13);
	public static final FieldDescriptor IIBEGINDATE = new FieldDescriptor(TABLE, "ii_begin_date", "setIiBeginDate", 14);
	public static final FieldDescriptor IIENDDATE = new FieldDescriptor(TABLE, "ii_end_date", "setIiEndDate", 15);
	public static final FieldDescriptor SHERIFFNUM = new FieldDescriptor(TABLE, "sheriff_num", "setSheriffNum", 16);
	public static final FieldDescriptor INFORMATION = new FieldDescriptor(TABLE, "information", "setInformation", 17);
	public static final FieldDescriptor FTACLEARFLAG = new FieldDescriptor(TABLE, "fta_clear_flag", "setFtaClearFlag", 18);
	public static final FieldDescriptor TRIALDENOVO = new FieldDescriptor(TABLE, "trialdenovo", "setTrialdenovo", 19);
	public static final FieldDescriptor DLRVBEGINDATE = new FieldDescriptor(TABLE, "dlrv_begin_date", "setDlrvBeginDate", 20);
	public static final FieldDescriptor DLRVENDDATE = new FieldDescriptor(TABLE, "dlrv_end_date", "setDlrvEndDate", 21);
	public static final FieldDescriptor OCCUPANTS16UP = new FieldDescriptor(TABLE, "occupants_16up", "setOccupants16up", 22);

	public CrimCaseBO() {
		super(new CrimCaseVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public CrimCaseBO(String source) {
		super(new CrimCaseVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public CrimCaseBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public CrimCaseBO setIntCaseNum(Integer intCaseNum) {
		setPrimaryKey(INTCASENUM, intCaseNum);
		return this;
	}

	public String getCitNum() {
		return getBaseVO().getTypeString(CITNUM).getValue();
	}
	
	public CrimCaseBO setCitNum(String citNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITNUM, citNum);
		return this;
	}

	public String getLea() {
		return getBaseVO().getTypeString(LEA).getValue();
	}
	
	public CrimCaseBO setLea(String lea) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LEA, lea);
		return this;
	}

	public String getLeaCaseNum() {
		return getBaseVO().getTypeString(LEACASENUM).getValue();
	}
	
	public CrimCaseBO setLeaCaseNum(String leaCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LEACASENUM, leaCaseNum);
		return this;
	}

	public String getProsecAgency() {
		return getBaseVO().getTypeString(PROSECAGENCY).getValue();
	}
	
	public CrimCaseBO setProsecAgency(String prosecAgency) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROSECAGENCY, prosecAgency);
		return this;
	}

	public String getProsecAgencyNum() {
		return getBaseVO().getTypeString(PROSECAGENCYNUM).getValue();
	}
	
	public CrimCaseBO setProsecAgencyNum(String prosecAgencyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROSECAGENCYNUM, prosecAgencyNum);
		return this;
	}

	public int getOfficerSrl() {
		return getBaseVO().getTypeInteger(OFFICERSRL).getValue();
	}
	
	public CrimCaseBO setOfficerSrl(Integer officerSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFICERSRL, officerSrl);
		return this;
	}

	public String getCommVehicle() {
		return getBaseVO().getTypeString(COMMVEHICLE).getValue();
	}
	
	public CrimCaseBO setCommVehicle(String commVehicle) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMMVEHICLE, commVehicle);
		return this;
	}

	public String getHazMaterial() {
		return getBaseVO().getTypeString(HAZMATERIAL).getValue();
	}
	
	public CrimCaseBO setHazMaterial(String hazMaterial) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HAZMATERIAL, hazMaterial);
		return this;
	}

	public String getFtaStatus() {
		return getBaseVO().getTypeString(FTASTATUS).getValue();
	}
	
	public CrimCaseBO setFtaStatus(String ftaStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTASTATUS, ftaStatus);
		return this;
	}

	public Date getFtaOrderDate() {
		return getBaseVO().getTypeDate(FTAORDERDATE).getValue();
	}
	
	public CrimCaseBO setFtaOrderDate(Date ftaOrderDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAORDERDATE, ftaOrderDate);
		return this;
	}
	
	public CrimCaseBO setFtaOrderDate(Date ftaOrderDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAORDERDATE, ftaOrderDate, dateFormat);
		getBaseVO().setAttribute(FTAORDERDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getFtaIssueDate() {
		return getBaseVO().getTypeDate(FTAISSUEDATE).getValue();
	}
	
	public CrimCaseBO setFtaIssueDate(Date ftaIssueDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAISSUEDATE, ftaIssueDate);
		return this;
	}
	
	public CrimCaseBO setFtaIssueDate(Date ftaIssueDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAISSUEDATE, ftaIssueDate, dateFormat);
		getBaseVO().setAttribute(FTAISSUEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getFtaClearDate() {
		return getBaseVO().getTypeDate(FTACLEARDATE).getValue();
	}
	
	public CrimCaseBO setFtaClearDate(Date ftaClearDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARDATE, ftaClearDate);
		return this;
	}
	
	public CrimCaseBO setFtaClearDate(Date ftaClearDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARDATE, ftaClearDate, dateFormat);
		getBaseVO().setAttribute(FTACLEARDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getDelinqNoticeDate() {
		return getBaseVO().getTypeDate(DELINQNOTICEDATE).getValue();
	}
	
	public CrimCaseBO setDelinqNoticeDate(Date delinqNoticeDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DELINQNOTICEDATE, delinqNoticeDate);
		return this;
	}
	
	public CrimCaseBO setDelinqNoticeDate(Date delinqNoticeDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DELINQNOTICEDATE, delinqNoticeDate, dateFormat);
		getBaseVO().setAttribute(DELINQNOTICEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIiBeginDate() {
		return getBaseVO().getTypeDate(IIBEGINDATE).getValue();
	}
	
	public CrimCaseBO setIiBeginDate(Date iiBeginDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIBEGINDATE, iiBeginDate);
		return this;
	}
	
	public CrimCaseBO setIiBeginDate(Date iiBeginDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIBEGINDATE, iiBeginDate, dateFormat);
		getBaseVO().setAttribute(IIBEGINDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIiEndDate() {
		return getBaseVO().getTypeDate(IIENDDATE).getValue();
	}
	
	public CrimCaseBO setIiEndDate(Date iiEndDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIENDDATE, iiEndDate);
		return this;
	}
	
	public CrimCaseBO setIiEndDate(Date iiEndDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIENDDATE, iiEndDate, dateFormat);
		getBaseVO().setAttribute(IIENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getSheriffNum() {
		return getBaseVO().getTypeString(SHERIFFNUM).getValue();
	}
	
	public CrimCaseBO setSheriffNum(String sheriffNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SHERIFFNUM, sheriffNum);
		return this;
	}

	public String getInformation() {
		return getBaseVO().getTypeString(INFORMATION).getValue();
	}
	
	public CrimCaseBO setInformation(String information) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INFORMATION, information);
		return this;
	}

	public String getFtaClearFlag() {
		return getBaseVO().getTypeString(FTACLEARFLAG).getValue();
	}
	
	public CrimCaseBO setFtaClearFlag(String ftaClearFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARFLAG, ftaClearFlag);
		return this;
	}

	public String getTrialdenovo() {
		return getBaseVO().getTypeString(TRIALDENOVO).getValue();
	}
	
	public CrimCaseBO setTrialdenovo(String trialdenovo) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRIALDENOVO, trialdenovo);
		return this;
	}

	public Date getDlrvBeginDate() {
		return getBaseVO().getTypeDate(DLRVBEGINDATE).getValue();
	}
	
	public CrimCaseBO setDlrvBeginDate(Date dlrvBeginDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVBEGINDATE, dlrvBeginDate);
		return this;
	}
	
	public CrimCaseBO setDlrvBeginDate(Date dlrvBeginDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVBEGINDATE, dlrvBeginDate, dateFormat);
		getBaseVO().setAttribute(DLRVBEGINDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getDlrvEndDate() {
		return getBaseVO().getTypeDate(DLRVENDDATE).getValue();
	}
	
	public CrimCaseBO setDlrvEndDate(Date dlrvEndDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVENDDATE, dlrvEndDate);
		return this;
	}
	
	public CrimCaseBO setDlrvEndDate(Date dlrvEndDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVENDDATE, dlrvEndDate, dateFormat);
		getBaseVO().setAttribute(DLRVENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getOccupants16up() {
		return getBaseVO().getTypeString(OCCUPANTS16UP).getValue();
	}
	
	public CrimCaseBO setOccupants16up(String occupants16up) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OCCUPANTS16UP, occupants16up);
		return this;
	}
	
	public CrimCaseBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<CrimCaseBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new CrimCaseBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new CrimCaseBO(getBaseVO());
		}
	}
	
	public CrimCaseBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public CrimCaseBO insert(FieldDescriptor... specificFields) throws Exception {
		return new CrimCaseBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public CrimCaseBO update(FieldDescriptor... specificFields) throws Exception {
		return new CrimCaseBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public CrimCaseBO delete() throws Exception {
		return new CrimCaseBO(super.delete(getNativeDescriptor()));
	}
	
	public CrimCaseBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CrimCaseBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CrimCaseBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public CrimCaseBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public CrimCaseBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public CrimCaseBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public CrimCaseBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CrimCaseBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CrimCaseBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public CrimCaseBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public CrimCaseBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public CrimCaseBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public CrimCaseBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public CrimCaseBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CrimCaseBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public CrimCaseBO where(FieldDescriptor findBy, Object value) {
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
	
	public CrimCaseBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CrimCaseBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public CrimCaseBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public CrimCaseBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public CrimCaseBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public CrimCaseBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public CrimCaseBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public CrimCaseBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public CrimCaseBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public CrimCaseBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public CrimCaseBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public CrimCaseBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public CrimCaseBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public CrimCaseBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public CrimCaseBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public CrimCaseBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<CrimCaseBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public CrimCaseBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public CrimCaseBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CrimCaseBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public CrimCaseBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public CrimCaseBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public CrimCaseBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public CrimCaseBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public CrimCaseBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public CrimCaseBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public CrimCaseBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public CrimCaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public CrimCaseBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CrimCaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CrimCaseBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CrimCaseBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public CrimCaseBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public CrimCaseBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public CrimCaseBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public CrimCaseBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public CrimCaseBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public CrimCaseBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public CrimCaseBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public CrimCaseBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CrimCaseBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CrimCaseBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public CrimCaseBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative CrimCaseBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public CrimCaseBO setQuestionMarks(Object... values) {
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
	public CrimCaseBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public CrimCaseBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public CrimCaseBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public CrimCaseBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public CrimCaseBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public CrimCaseBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public CrimCaseBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public CrimCaseBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public CrimCaseBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public CrimCaseBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public CrimCaseBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public CrimCaseBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public CrimCaseBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}