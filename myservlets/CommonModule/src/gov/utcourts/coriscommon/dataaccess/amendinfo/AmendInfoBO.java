package gov.utcourts.coriscommon.dataaccess.amendinfo;

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
public class AmendInfoBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("amend_info", "gov.utcourts.coriscommon.dataaccess.amendinfo.AmendInfo");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 0);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 1);
	public static final FieldDescriptor PARTYCODE = new FieldDescriptor(TABLE, "party_code", "setPartyCode", 2);
	public static final FieldDescriptor INFONUM = new FieldDescriptor(TABLE, "info_num", "setInfoNum", 3);
	public static final FieldDescriptor SEQ = new FieldDescriptor(TABLE, "seq", "setSeq", 4);
	public static final FieldDescriptor AMENDINFODATE = new FieldDescriptor(TABLE, "amend_info_date", "setAmendInfoDate", 5);
	public static final FieldDescriptor GOVCODE = new FieldDescriptor(TABLE, "gov_code", "setGovCode", 6);
	public static final FieldDescriptor VIOLCODE = new FieldDescriptor(TABLE, "viol_code", "setViolCode", 7);
	public static final FieldDescriptor VIOLDATETIME = new FieldDescriptor(TABLE, "viol_datetime", "setViolDatetime", 8);
	public static final FieldDescriptor VIOLLOCN = new FieldDescriptor(TABLE, "viol_locn", "setViolLocn", 9);
	public static final FieldDescriptor OFFSVIOLCODE = new FieldDescriptor(TABLE, "offs_viol_code", "setOffsViolCode", 10);
	public static final FieldDescriptor OFFSEFFECTDATE = new FieldDescriptor(TABLE, "offs_effect_date", "setOffsEffectDate", 11);
	public static final FieldDescriptor INCHOATEFLAG = new FieldDescriptor(TABLE, "inchoate_flag", "setInchoateFlag", 12);
	public static final FieldDescriptor SEVERITY = new FieldDescriptor(TABLE, "severity", "setSeverity", 13);
	public static final FieldDescriptor PLEACODE = new FieldDescriptor(TABLE, "plea_code", "setPleaCode", 14);
	public static final FieldDescriptor PLEADATE = new FieldDescriptor(TABLE, "plea_date", "setPleaDate", 15);
	public static final FieldDescriptor JDMTCODE = new FieldDescriptor(TABLE, "jdmt_code", "setJdmtCode", 16);
	public static final FieldDescriptor JDMTDATE = new FieldDescriptor(TABLE, "jdmt_date", "setJdmtDate", 17);
	public static final FieldDescriptor DRLICSEVERITY = new FieldDescriptor(TABLE, "dr_lic_severity", "setDrLicSeverity", 18);
	public static final FieldDescriptor DRLICREDUCED = new FieldDescriptor(TABLE, "dr_lic_reduced", "setDrLicReduced", 19);
	public static final FieldDescriptor DRLICRPTSTATUS = new FieldDescriptor(TABLE, "dr_lic_rpt_status", "setDrLicRptStatus", 20);
	public static final FieldDescriptor DRLICRPTDATE = new FieldDescriptor(TABLE, "dr_lic_rpt_date", "setDrLicRptDate", 21);
	public static final FieldDescriptor BCIRPTDATE = new FieldDescriptor(TABLE, "bci_rpt_date", "setBciRptDate", 22);
	public static final FieldDescriptor AMENDDATE = new FieldDescriptor(TABLE, "amend_date", "setAmendDate", 23);
	public static final FieldDescriptor ORIGGOVCODE = new FieldDescriptor(TABLE, "orig_gov_code", "setOrigGovCode", 24);
	public static final FieldDescriptor ORIGVIOLCODE = new FieldDescriptor(TABLE, "orig_viol_code", "setOrigViolCode", 25);
	public static final FieldDescriptor ORIGINCHOATEFLAG = new FieldDescriptor(TABLE, "orig_inchoate_flag", "setOrigInchoateFlag", 26);
	public static final FieldDescriptor ORIGSEVERITY = new FieldDescriptor(TABLE, "orig_severity", "setOrigSeverity", 27);
	public static final FieldDescriptor AMEND402TYPE = new FieldDescriptor(TABLE, "amend402_type", "setAmend402Type", 28);
	public static final FieldDescriptor AMEND402CODE = new FieldDescriptor(TABLE, "amend402_code", "setAmend402Code", 29);
	public static final FieldDescriptor PRE402SEVERITY = new FieldDescriptor(TABLE, "pre402_severity", "setPre402Severity", 30);
	public static final FieldDescriptor ENHANCTYPE = new FieldDescriptor(TABLE, "enhanc_type", "setEnhancType", 31);
	public static final FieldDescriptor PREENHANCSEVERITY = new FieldDescriptor(TABLE, "preenhanc_severity", "setPreenhancSeverity", 32);
	public static final FieldDescriptor SPVALUE1 = new FieldDescriptor(TABLE, "sp_value_1", "setSpValue1", 33);
	public static final FieldDescriptor SPVALUE2 = new FieldDescriptor(TABLE, "sp_value_2", "setSpValue2", 34);

	public AmendInfoBO() {
		super(new AmendInfoVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public AmendInfoBO(String source) {
		super(new AmendInfoVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public AmendInfoBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public AmendInfoBO setIntCaseNum(Integer intCaseNum) {
		setPrimaryKey(INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public AmendInfoBO setPartyNum(Integer partyNum) {
		setPrimaryKey(PARTYNUM, partyNum);
		return this;
	}

	public String getPartyCode() {
		return getBaseVO().getTypeString(PARTYCODE).getValue();
	}
	
	public AmendInfoBO setPartyCode(String partyCode) {
		setPrimaryKey(PARTYCODE, partyCode);
		return this;
	}

	public int getInfoNum() {
		return getBaseVO().getTypeInteger(INFONUM).getValue();
	}
	
	public AmendInfoBO setInfoNum(Integer infoNum) {
		setPrimaryKey(INFONUM, infoNum);
		return this;
	}

	public int getSeq() {
		return getBaseVO().getTypeInteger(SEQ).getValue();
	}
	
	public AmendInfoBO setSeq(Integer seq) {
		setPrimaryKey(SEQ, seq);
		return this;
	}

	public Date getAmendInfoDate() {
		return getBaseVO().getTypeDate(AMENDINFODATE).getValue();
	}
	
	public AmendInfoBO setAmendInfoDate(Date amendInfoDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDINFODATE, amendInfoDate);
		return this;
	}
	
	public AmendInfoBO setAmendInfoDate(Date amendInfoDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDINFODATE, amendInfoDate, dateFormat);
		getBaseVO().setAttribute(AMENDINFODATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getGovCode() {
		return getBaseVO().getTypeString(GOVCODE).getValue();
	}
	
	public AmendInfoBO setGovCode(String govCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GOVCODE, govCode);
		return this;
	}

	public String getViolCode() {
		return getBaseVO().getTypeString(VIOLCODE).getValue();
	}
	
	public AmendInfoBO setViolCode(String violCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLCODE, violCode);
		return this;
	}

	public Date getViolDatetime() {
		return getBaseVO().getTypeDate(VIOLDATETIME).getValue();
	}
	
	public AmendInfoBO setViolDatetime(Date violDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime);
		return this;
	}
	
	public AmendInfoBO setViolDatetime(Date violDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime, dateFormat);
		getBaseVO().setAttribute(VIOLDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getViolLocn() {
		return getBaseVO().getTypeString(VIOLLOCN).getValue();
	}
	
	public AmendInfoBO setViolLocn(String violLocn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLLOCN, violLocn);
		return this;
	}

	public String getOffsViolCode() {
		return getBaseVO().getTypeString(OFFSVIOLCODE).getValue();
	}
	
	public AmendInfoBO setOffsViolCode(String offsViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSVIOLCODE, offsViolCode);
		return this;
	}

	public Date getOffsEffectDate() {
		return getBaseVO().getTypeDate(OFFSEFFECTDATE).getValue();
	}
	
	public AmendInfoBO setOffsEffectDate(Date offsEffectDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate);
		return this;
	}
	
	public AmendInfoBO setOffsEffectDate(Date offsEffectDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate, dateFormat);
		getBaseVO().setAttribute(OFFSEFFECTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getInchoateFlag() {
		return getBaseVO().getTypeString(INCHOATEFLAG).getValue();
	}
	
	public AmendInfoBO setInchoateFlag(String inchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INCHOATEFLAG, inchoateFlag);
		return this;
	}

	public String getSeverity() {
		return getBaseVO().getTypeString(SEVERITY).getValue();
	}
	
	public AmendInfoBO setSeverity(String severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SEVERITY, severity);
		return this;
	}

	public String getPleaCode() {
		return getBaseVO().getTypeString(PLEACODE).getValue();
	}
	
	public AmendInfoBO setPleaCode(String pleaCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEACODE, pleaCode);
		return this;
	}

	public Date getPleaDate() {
		return getBaseVO().getTypeDate(PLEADATE).getValue();
	}
	
	public AmendInfoBO setPleaDate(Date pleaDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate);
		return this;
	}
	
	public AmendInfoBO setPleaDate(Date pleaDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate, dateFormat);
		getBaseVO().setAttribute(PLEADATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getJdmtCode() {
		return getBaseVO().getTypeString(JDMTCODE).getValue();
	}
	
	public AmendInfoBO setJdmtCode(String jdmtCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTCODE, jdmtCode);
		return this;
	}

	public Date getJdmtDate() {
		return getBaseVO().getTypeDate(JDMTDATE).getValue();
	}
	
	public AmendInfoBO setJdmtDate(Date jdmtDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate);
		return this;
	}
	
	public AmendInfoBO setJdmtDate(Date jdmtDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate, dateFormat);
		getBaseVO().setAttribute(JDMTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDrLicSeverity() {
		return getBaseVO().getTypeString(DRLICSEVERITY).getValue();
	}
	
	public AmendInfoBO setDrLicSeverity(String drLicSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICSEVERITY, drLicSeverity);
		return this;
	}

	public String getDrLicReduced() {
		return getBaseVO().getTypeString(DRLICREDUCED).getValue();
	}
	
	public AmendInfoBO setDrLicReduced(String drLicReduced) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICREDUCED, drLicReduced);
		return this;
	}

	public String getDrLicRptStatus() {
		return getBaseVO().getTypeString(DRLICRPTSTATUS).getValue();
	}
	
	public AmendInfoBO setDrLicRptStatus(String drLicRptStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTSTATUS, drLicRptStatus);
		return this;
	}

	public Date getDrLicRptDate() {
		return getBaseVO().getTypeDate(DRLICRPTDATE).getValue();
	}
	
	public AmendInfoBO setDrLicRptDate(Date drLicRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate);
		return this;
	}
	
	public AmendInfoBO setDrLicRptDate(Date drLicRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate, dateFormat);
		getBaseVO().setAttribute(DRLICRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getBciRptDate() {
		return getBaseVO().getTypeDate(BCIRPTDATE).getValue();
	}
	
	public AmendInfoBO setBciRptDate(Date bciRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate);
		return this;
	}
	
	public AmendInfoBO setBciRptDate(Date bciRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate, dateFormat);
		getBaseVO().setAttribute(BCIRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getAmendDate() {
		return getBaseVO().getTypeDate(AMENDDATE).getValue();
	}
	
	public AmendInfoBO setAmendDate(Date amendDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate);
		return this;
	}
	
	public AmendInfoBO setAmendDate(Date amendDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate, dateFormat);
		getBaseVO().setAttribute(AMENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getOrigGovCode() {
		return getBaseVO().getTypeString(ORIGGOVCODE).getValue();
	}
	
	public AmendInfoBO setOrigGovCode(String origGovCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGGOVCODE, origGovCode);
		return this;
	}

	public String getOrigViolCode() {
		return getBaseVO().getTypeString(ORIGVIOLCODE).getValue();
	}
	
	public AmendInfoBO setOrigViolCode(String origViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGVIOLCODE, origViolCode);
		return this;
	}

	public String getOrigInchoateFlag() {
		return getBaseVO().getTypeString(ORIGINCHOATEFLAG).getValue();
	}
	
	public AmendInfoBO setOrigInchoateFlag(String origInchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGINCHOATEFLAG, origInchoateFlag);
		return this;
	}

	public String getOrigSeverity() {
		return getBaseVO().getTypeString(ORIGSEVERITY).getValue();
	}
	
	public AmendInfoBO setOrigSeverity(String origSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGSEVERITY, origSeverity);
		return this;
	}

	public String getAmend402Type() {
		return getBaseVO().getTypeString(AMEND402TYPE).getValue();
	}
	
	public AmendInfoBO setAmend402Type(String amend402Type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402TYPE, amend402Type);
		return this;
	}

	public String getAmend402Code() {
		return getBaseVO().getTypeString(AMEND402CODE).getValue();
	}
	
	public AmendInfoBO setAmend402Code(String amend402Code) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402CODE, amend402Code);
		return this;
	}

	public String getPre402Severity() {
		return getBaseVO().getTypeString(PRE402SEVERITY).getValue();
	}
	
	public AmendInfoBO setPre402Severity(String pre402Severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRE402SEVERITY, pre402Severity);
		return this;
	}

	public String getEnhancType() {
		return getBaseVO().getTypeString(ENHANCTYPE).getValue();
	}
	
	public AmendInfoBO setEnhancType(String enhancType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ENHANCTYPE, enhancType);
		return this;
	}

	public String getPreenhancSeverity() {
		return getBaseVO().getTypeString(PREENHANCSEVERITY).getValue();
	}
	
	public AmendInfoBO setPreenhancSeverity(String preenhancSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PREENHANCSEVERITY, preenhancSeverity);
		return this;
	}

	public String getSpValue1() {
		return getBaseVO().getTypeString(SPVALUE1).getValue();
	}
	
	public AmendInfoBO setSpValue1(String spValue1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE1, spValue1);
		return this;
	}

	public String getSpValue2() {
		return getBaseVO().getTypeString(SPVALUE2).getValue();
	}
	
	public AmendInfoBO setSpValue2(String spValue2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE2, spValue2);
		return this;
	}
	
	public AmendInfoBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<AmendInfoBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new AmendInfoBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new AmendInfoBO(getBaseVO());
		}
	}
	
	public AmendInfoBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public AmendInfoBO insert(FieldDescriptor... specificFields) throws Exception {
		return new AmendInfoBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public AmendInfoBO update(FieldDescriptor... specificFields) throws Exception {
		return new AmendInfoBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public AmendInfoBO delete() throws Exception {
		return new AmendInfoBO(super.delete(getNativeDescriptor()));
	}
	
	public AmendInfoBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AmendInfoBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public AmendInfoBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public AmendInfoBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public AmendInfoBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public AmendInfoBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public AmendInfoBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AmendInfoBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public AmendInfoBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public AmendInfoBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AmendInfoBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AmendInfoBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public AmendInfoBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public AmendInfoBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public AmendInfoBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AmendInfoBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public AmendInfoBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public AmendInfoBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AmendInfoBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public AmendInfoBO where(FieldDescriptor findBy, Object value) {
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
	
	public AmendInfoBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public AmendInfoBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public AmendInfoBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public AmendInfoBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public AmendInfoBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public AmendInfoBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public AmendInfoBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public AmendInfoBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public AmendInfoBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public AmendInfoBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public AmendInfoBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public AmendInfoBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public AmendInfoBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public AmendInfoBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public AmendInfoBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public AmendInfoBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<AmendInfoBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public AmendInfoBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public AmendInfoBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AmendInfoBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public AmendInfoBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public AmendInfoBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public AmendInfoBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public AmendInfoBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public AmendInfoBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public AmendInfoBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public AmendInfoBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public AmendInfoBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public AmendInfoBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AmendInfoBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public AmendInfoBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public AmendInfoBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public AmendInfoBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public AmendInfoBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public AmendInfoBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public AmendInfoBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public AmendInfoBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public AmendInfoBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public AmendInfoBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public AmendInfoBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AmendInfoBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public AmendInfoBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public AmendInfoBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative AmendInfoBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public AmendInfoBO setQuestionMarks(Object... values) {
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
	public AmendInfoBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public AmendInfoBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public AmendInfoBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public AmendInfoBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public AmendInfoBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public AmendInfoBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public AmendInfoBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public AmendInfoBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public AmendInfoBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public AmendInfoBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public AmendInfoBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public AmendInfoBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public AmendInfoBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}