package gov.utcourts.coriscommon.dataaccess.crimcaseold;

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
public class CrimCaseOldBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("crim_case_old", "gov.utcourts.coriscommon.dataaccess.crimcaseold.CrimCaseOld");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACTIONTYPE = new FieldDescriptor(TABLE, "action_type", "setActionType", 0);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 1);
	public static final FieldDescriptor CHANGEDATETIME = new FieldDescriptor(TABLE, "change_datetime", "setChangeDatetime", 2);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 3);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 4);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 5);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 6);
	public static final FieldDescriptor CITNUM = new FieldDescriptor(TABLE, "cit_num", "setCitNum", 7);
	public static final FieldDescriptor LEA = new FieldDescriptor(TABLE, "lea", "setLea", 8);
	public static final FieldDescriptor LEACASENUM = new FieldDescriptor(TABLE, "lea_case_num", "setLeaCaseNum", 9);
	public static final FieldDescriptor PROSECAGENCY = new FieldDescriptor(TABLE, "prosec_agency", "setProsecAgency", 10);
	public static final FieldDescriptor PROSECAGENCYNUM = new FieldDescriptor(TABLE, "prosec_agency_num", "setProsecAgencyNum", 11);
	public static final FieldDescriptor OFFICERSRL = new FieldDescriptor(TABLE, "officer_srl", "setOfficerSrl", 12);
	public static final FieldDescriptor COMMVEHICLE = new FieldDescriptor(TABLE, "comm_vehicle", "setCommVehicle", 13);
	public static final FieldDescriptor HAZMATERIAL = new FieldDescriptor(TABLE, "haz_material", "setHazMaterial", 14);
	public static final FieldDescriptor FTASTATUS = new FieldDescriptor(TABLE, "fta_status", "setFtaStatus", 15);
	public static final FieldDescriptor FTAORDERDATE = new FieldDescriptor(TABLE, "fta_order_date", "setFtaOrderDate", 16);
	public static final FieldDescriptor FTAISSUEDATE = new FieldDescriptor(TABLE, "fta_issue_date", "setFtaIssueDate", 17);
	public static final FieldDescriptor FTACLEARDATE = new FieldDescriptor(TABLE, "fta_clear_date", "setFtaClearDate", 18);
	public static final FieldDescriptor DELINQNOTICEDATE = new FieldDescriptor(TABLE, "delinq_notice_date", "setDelinqNoticeDate", 19);
	public static final FieldDescriptor IIBEGINDATE = new FieldDescriptor(TABLE, "ii_begin_date", "setIiBeginDate", 20);
	public static final FieldDescriptor IIENDDATE = new FieldDescriptor(TABLE, "ii_end_date", "setIiEndDate", 21);
	public static final FieldDescriptor SHERIFFNUM = new FieldDescriptor(TABLE, "sheriff_num", "setSheriffNum", 22);
	public static final FieldDescriptor INFORMATION = new FieldDescriptor(TABLE, "information", "setInformation", 23);
	public static final FieldDescriptor FTACLEARFLAG = new FieldDescriptor(TABLE, "fta_clear_flag", "setFtaClearFlag", 24);
	public static final FieldDescriptor TRIALDENOVO = new FieldDescriptor(TABLE, "trialdenovo", "setTrialdenovo", 25);
	public static final FieldDescriptor DLRVBEGINDATE = new FieldDescriptor(TABLE, "dlrv_begin_date", "setDlrvBeginDate", 26);
	public static final FieldDescriptor DLRVENDDATE = new FieldDescriptor(TABLE, "dlrv_end_date", "setDlrvEndDate", 27);
	public static final FieldDescriptor OCCUPANTS16UP = new FieldDescriptor(TABLE, "occupants_16up", "setOccupants16up", 28);

	public CrimCaseOldBO() {
		super(new CrimCaseOldVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public CrimCaseOldBO(String source) {
		super(new CrimCaseOldVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public CrimCaseOldBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getActionType() {
		return getBaseVO().getTypeString(ACTIONTYPE).getValue();
	}
	
	public CrimCaseOldBO setActionType(String actionType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONTYPE, actionType);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public CrimCaseOldBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public Date getChangeDatetime() {
		return getBaseVO().getTypeDate(CHANGEDATETIME).getValue();
	}
	
	public CrimCaseOldBO setChangeDatetime(Date changeDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime);
		return this;
	}
	
	public CrimCaseOldBO setChangeDatetime(Date changeDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime, dateFormat);
		getBaseVO().setAttribute(CHANGEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public CrimCaseOldBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public CrimCaseOldBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public CrimCaseOldBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public CrimCaseOldBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public String getCitNum() {
		return getBaseVO().getTypeString(CITNUM).getValue();
	}
	
	public CrimCaseOldBO setCitNum(String citNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITNUM, citNum);
		return this;
	}

	public String getLea() {
		return getBaseVO().getTypeString(LEA).getValue();
	}
	
	public CrimCaseOldBO setLea(String lea) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LEA, lea);
		return this;
	}

	public String getLeaCaseNum() {
		return getBaseVO().getTypeString(LEACASENUM).getValue();
	}
	
	public CrimCaseOldBO setLeaCaseNum(String leaCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LEACASENUM, leaCaseNum);
		return this;
	}

	public String getProsecAgency() {
		return getBaseVO().getTypeString(PROSECAGENCY).getValue();
	}
	
	public CrimCaseOldBO setProsecAgency(String prosecAgency) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROSECAGENCY, prosecAgency);
		return this;
	}

	public String getProsecAgencyNum() {
		return getBaseVO().getTypeString(PROSECAGENCYNUM).getValue();
	}
	
	public CrimCaseOldBO setProsecAgencyNum(String prosecAgencyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PROSECAGENCYNUM, prosecAgencyNum);
		return this;
	}

	public int getOfficerSrl() {
		return getBaseVO().getTypeInteger(OFFICERSRL).getValue();
	}
	
	public CrimCaseOldBO setOfficerSrl(Integer officerSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFICERSRL, officerSrl);
		return this;
	}

	public String getCommVehicle() {
		return getBaseVO().getTypeString(COMMVEHICLE).getValue();
	}
	
	public CrimCaseOldBO setCommVehicle(String commVehicle) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMMVEHICLE, commVehicle);
		return this;
	}

	public String getHazMaterial() {
		return getBaseVO().getTypeString(HAZMATERIAL).getValue();
	}
	
	public CrimCaseOldBO setHazMaterial(String hazMaterial) {
		getBaseVO().setPropertyListValue(SET_BY_USER, HAZMATERIAL, hazMaterial);
		return this;
	}

	public String getFtaStatus() {
		return getBaseVO().getTypeString(FTASTATUS).getValue();
	}
	
	public CrimCaseOldBO setFtaStatus(String ftaStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTASTATUS, ftaStatus);
		return this;
	}

	public Date getFtaOrderDate() {
		return getBaseVO().getTypeDate(FTAORDERDATE).getValue();
	}
	
	public CrimCaseOldBO setFtaOrderDate(Date ftaOrderDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAORDERDATE, ftaOrderDate);
		return this;
	}
	
	public CrimCaseOldBO setFtaOrderDate(Date ftaOrderDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAORDERDATE, ftaOrderDate, dateFormat);
		getBaseVO().setAttribute(FTAORDERDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getFtaIssueDate() {
		return getBaseVO().getTypeDate(FTAISSUEDATE).getValue();
	}
	
	public CrimCaseOldBO setFtaIssueDate(Date ftaIssueDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAISSUEDATE, ftaIssueDate);
		return this;
	}
	
	public CrimCaseOldBO setFtaIssueDate(Date ftaIssueDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAISSUEDATE, ftaIssueDate, dateFormat);
		getBaseVO().setAttribute(FTAISSUEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getFtaClearDate() {
		return getBaseVO().getTypeDate(FTACLEARDATE).getValue();
	}
	
	public CrimCaseOldBO setFtaClearDate(Date ftaClearDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARDATE, ftaClearDate);
		return this;
	}
	
	public CrimCaseOldBO setFtaClearDate(Date ftaClearDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARDATE, ftaClearDate, dateFormat);
		getBaseVO().setAttribute(FTACLEARDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getDelinqNoticeDate() {
		return getBaseVO().getTypeDate(DELINQNOTICEDATE).getValue();
	}
	
	public CrimCaseOldBO setDelinqNoticeDate(Date delinqNoticeDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DELINQNOTICEDATE, delinqNoticeDate);
		return this;
	}
	
	public CrimCaseOldBO setDelinqNoticeDate(Date delinqNoticeDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DELINQNOTICEDATE, delinqNoticeDate, dateFormat);
		getBaseVO().setAttribute(DELINQNOTICEDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIiBeginDate() {
		return getBaseVO().getTypeDate(IIBEGINDATE).getValue();
	}
	
	public CrimCaseOldBO setIiBeginDate(Date iiBeginDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIBEGINDATE, iiBeginDate);
		return this;
	}
	
	public CrimCaseOldBO setIiBeginDate(Date iiBeginDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIBEGINDATE, iiBeginDate, dateFormat);
		getBaseVO().setAttribute(IIBEGINDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getIiEndDate() {
		return getBaseVO().getTypeDate(IIENDDATE).getValue();
	}
	
	public CrimCaseOldBO setIiEndDate(Date iiEndDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIENDDATE, iiEndDate);
		return this;
	}
	
	public CrimCaseOldBO setIiEndDate(Date iiEndDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, IIENDDATE, iiEndDate, dateFormat);
		getBaseVO().setAttribute(IIENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getSheriffNum() {
		return getBaseVO().getTypeString(SHERIFFNUM).getValue();
	}
	
	public CrimCaseOldBO setSheriffNum(String sheriffNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SHERIFFNUM, sheriffNum);
		return this;
	}

	public String getInformation() {
		return getBaseVO().getTypeString(INFORMATION).getValue();
	}
	
	public CrimCaseOldBO setInformation(String information) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INFORMATION, information);
		return this;
	}

	public String getFtaClearFlag() {
		return getBaseVO().getTypeString(FTACLEARFLAG).getValue();
	}
	
	public CrimCaseOldBO setFtaClearFlag(String ftaClearFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTACLEARFLAG, ftaClearFlag);
		return this;
	}

	public String getTrialdenovo() {
		return getBaseVO().getTypeString(TRIALDENOVO).getValue();
	}
	
	public CrimCaseOldBO setTrialdenovo(String trialdenovo) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TRIALDENOVO, trialdenovo);
		return this;
	}

	public Date getDlrvBeginDate() {
		return getBaseVO().getTypeDate(DLRVBEGINDATE).getValue();
	}
	
	public CrimCaseOldBO setDlrvBeginDate(Date dlrvBeginDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVBEGINDATE, dlrvBeginDate);
		return this;
	}
	
	public CrimCaseOldBO setDlrvBeginDate(Date dlrvBeginDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVBEGINDATE, dlrvBeginDate, dateFormat);
		getBaseVO().setAttribute(DLRVBEGINDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getDlrvEndDate() {
		return getBaseVO().getTypeDate(DLRVENDDATE).getValue();
	}
	
	public CrimCaseOldBO setDlrvEndDate(Date dlrvEndDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVENDDATE, dlrvEndDate);
		return this;
	}
	
	public CrimCaseOldBO setDlrvEndDate(Date dlrvEndDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLRVENDDATE, dlrvEndDate, dateFormat);
		getBaseVO().setAttribute(DLRVENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getOccupants16up() {
		return getBaseVO().getTypeString(OCCUPANTS16UP).getValue();
	}
	
	public CrimCaseOldBO setOccupants16up(String occupants16up) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OCCUPANTS16UP, occupants16up);
		return this;
	}
	
	public CrimCaseOldBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<CrimCaseOldBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new CrimCaseOldBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new CrimCaseOldBO(getBaseVO());
		}
	}
	
	public CrimCaseOldBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public CrimCaseOldBO insert(FieldDescriptor... specificFields) throws Exception {
		return new CrimCaseOldBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public CrimCaseOldBO update(FieldDescriptor... specificFields) throws Exception {
		return new CrimCaseOldBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public CrimCaseOldBO delete() throws Exception {
		return new CrimCaseOldBO(super.delete(getNativeDescriptor()));
	}
	
	public CrimCaseOldBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CrimCaseOldBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public CrimCaseOldBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public CrimCaseOldBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public CrimCaseOldBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public CrimCaseOldBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public CrimCaseOldBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CrimCaseOldBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public CrimCaseOldBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public CrimCaseOldBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseOldBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseOldBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public CrimCaseOldBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public CrimCaseOldBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public CrimCaseOldBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseOldBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public CrimCaseOldBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public CrimCaseOldBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CrimCaseOldBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public CrimCaseOldBO where(FieldDescriptor findBy, Object value) {
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
	
	public CrimCaseOldBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public CrimCaseOldBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public CrimCaseOldBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public CrimCaseOldBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public CrimCaseOldBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public CrimCaseOldBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public CrimCaseOldBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public CrimCaseOldBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public CrimCaseOldBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public CrimCaseOldBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public CrimCaseOldBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public CrimCaseOldBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public CrimCaseOldBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public CrimCaseOldBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public CrimCaseOldBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public CrimCaseOldBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<CrimCaseOldBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public CrimCaseOldBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public CrimCaseOldBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CrimCaseOldBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public CrimCaseOldBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public CrimCaseOldBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public CrimCaseOldBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public CrimCaseOldBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public CrimCaseOldBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public CrimCaseOldBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public CrimCaseOldBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public CrimCaseOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public CrimCaseOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CrimCaseOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public CrimCaseOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public CrimCaseOldBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public CrimCaseOldBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public CrimCaseOldBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public CrimCaseOldBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public CrimCaseOldBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public CrimCaseOldBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public CrimCaseOldBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public CrimCaseOldBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public CrimCaseOldBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CrimCaseOldBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public CrimCaseOldBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public CrimCaseOldBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative CrimCaseOldBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public CrimCaseOldBO setQuestionMarks(Object... values) {
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
	public CrimCaseOldBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public CrimCaseOldBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public CrimCaseOldBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public CrimCaseOldBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public CrimCaseOldBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public CrimCaseOldBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public CrimCaseOldBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public CrimCaseOldBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public CrimCaseOldBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public CrimCaseOldBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public CrimCaseOldBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public CrimCaseOldBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public CrimCaseOldBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}