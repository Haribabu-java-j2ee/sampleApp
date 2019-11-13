package gov.utcourts.coriscommon.dataaccess.scharge;

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
public class SChargeBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("s_charge", "gov.utcourts.coriscommon.dataaccess.scharge.SCharge");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor SINTCASENUM = new FieldDescriptor(TABLE, "s_int_case_num", "setSIntCaseNum", 0);
	public static final FieldDescriptor SUSERIDSRL = new FieldDescriptor(TABLE, "s_userid_srl", "setSUseridSrl", 1);
	public static final FieldDescriptor SDATETIME = new FieldDescriptor(TABLE, "s_datetime", "setSDatetime", 2);
	public static final FieldDescriptor SMEID = new FieldDescriptor(TABLE, "s_me_id", "setSMeId", 3);
	public static final FieldDescriptor SOPERATION = new FieldDescriptor(TABLE, "s_operation", "setSOperation", 4);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 5);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 6);
	public static final FieldDescriptor PARTYCODE = new FieldDescriptor(TABLE, "party_code", "setPartyCode", 7);
	public static final FieldDescriptor SEQ = new FieldDescriptor(TABLE, "seq", "setSeq", 8);
	public static final FieldDescriptor GOVCODE = new FieldDescriptor(TABLE, "gov_code", "setGovCode", 9);
	public static final FieldDescriptor VIOLCODE = new FieldDescriptor(TABLE, "viol_code", "setViolCode", 10);
	public static final FieldDescriptor VIOLDATETIME = new FieldDescriptor(TABLE, "viol_datetime", "setViolDatetime", 11);
	public static final FieldDescriptor VIOLLOCN = new FieldDescriptor(TABLE, "viol_locn", "setViolLocn", 12);
	public static final FieldDescriptor OFFSVIOLCODE = new FieldDescriptor(TABLE, "offs_viol_code", "setOffsViolCode", 13);
	public static final FieldDescriptor OFFSEFFECTDATE = new FieldDescriptor(TABLE, "offs_effect_date", "setOffsEffectDate", 14);
	public static final FieldDescriptor INCHOATEFLAG = new FieldDescriptor(TABLE, "inchoate_flag", "setInchoateFlag", 15);
	public static final FieldDescriptor SEVERITY = new FieldDescriptor(TABLE, "severity", "setSeverity", 16);
	public static final FieldDescriptor PLEACODE = new FieldDescriptor(TABLE, "plea_code", "setPleaCode", 17);
	public static final FieldDescriptor PLEADATE = new FieldDescriptor(TABLE, "plea_date", "setPleaDate", 18);
	public static final FieldDescriptor JDMTCODE = new FieldDescriptor(TABLE, "jdmt_code", "setJdmtCode", 19);
	public static final FieldDescriptor JDMTDATE = new FieldDescriptor(TABLE, "jdmt_date", "setJdmtDate", 20);
	public static final FieldDescriptor DRLICSEVERITY = new FieldDescriptor(TABLE, "dr_lic_severity", "setDrLicSeverity", 21);
	public static final FieldDescriptor DRLICREDUCED = new FieldDescriptor(TABLE, "dr_lic_reduced", "setDrLicReduced", 22);
	public static final FieldDescriptor DRLICRPTSTATUS = new FieldDescriptor(TABLE, "dr_lic_rpt_status", "setDrLicRptStatus", 23);
	public static final FieldDescriptor DRLICRPTDATE = new FieldDescriptor(TABLE, "dr_lic_rpt_date", "setDrLicRptDate", 24);
	public static final FieldDescriptor BCIRPTDATE = new FieldDescriptor(TABLE, "bci_rpt_date", "setBciRptDate", 25);
	public static final FieldDescriptor AMENDDATE = new FieldDescriptor(TABLE, "amend_date", "setAmendDate", 26);
	public static final FieldDescriptor ORIGGOVCODE = new FieldDescriptor(TABLE, "orig_gov_code", "setOrigGovCode", 27);
	public static final FieldDescriptor ORIGVIOLCODE = new FieldDescriptor(TABLE, "orig_viol_code", "setOrigViolCode", 28);
	public static final FieldDescriptor ORIGINCHOATEFLAG = new FieldDescriptor(TABLE, "orig_inchoate_flag", "setOrigInchoateFlag", 29);
	public static final FieldDescriptor ORIGSEVERITY = new FieldDescriptor(TABLE, "orig_severity", "setOrigSeverity", 30);
	public static final FieldDescriptor AMEND402TYPE = new FieldDescriptor(TABLE, "amend402_type", "setAmend402Type", 31);
	public static final FieldDescriptor AMEND402CODE = new FieldDescriptor(TABLE, "amend402_code", "setAmend402Code", 32);
	public static final FieldDescriptor PRE402SEVERITY = new FieldDescriptor(TABLE, "pre402_severity", "setPre402Severity", 33);
	public static final FieldDescriptor ENHANCTYPE = new FieldDescriptor(TABLE, "enhanc_type", "setEnhancType", 34);
	public static final FieldDescriptor PREENHANCSEVERITY = new FieldDescriptor(TABLE, "preenhanc_severity", "setPreenhancSeverity", 35);
	public static final FieldDescriptor SPVALUE1 = new FieldDescriptor(TABLE, "sp_value_1", "setSpValue1", 36);
	public static final FieldDescriptor SPVALUE2 = new FieldDescriptor(TABLE, "sp_value_2", "setSpValue2", 37);

	public SChargeBO() {
		super(new SChargeVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public SChargeBO(String source) {
		super(new SChargeVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public SChargeBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public int getSIntCaseNum() {
		return getBaseVO().getTypeInteger(SINTCASENUM).getValue();
	}
	
	public SChargeBO setSIntCaseNum(Integer sIntCaseNum) {
		setPrimaryKey(SINTCASENUM, sIntCaseNum);
		return this;
	}

	public int getSUseridSrl() {
		return getBaseVO().getTypeInteger(SUSERIDSRL).getValue();
	}
	
	public SChargeBO setSUseridSrl(Integer sUseridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUSERIDSRL, sUseridSrl);
		return this;
	}

	public Date getSDatetime() {
		return getBaseVO().getTypeDate(SDATETIME).getValue();
	}
	
	public SChargeBO setSDatetime(Date sDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime);
		return this;
	}
	
	public SChargeBO setSDatetime(Date sDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SDATETIME, sDatetime, dateFormat);
		getBaseVO().setAttribute(SDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public int getSMeId() {
		return getBaseVO().getTypeInteger(SMEID).getValue();
	}
	
	public SChargeBO setSMeId(Integer sMeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SMEID, sMeId);
		return this;
	}

	public String getSOperation() {
		return getBaseVO().getTypeString(SOPERATION).getValue();
	}
	
	public SChargeBO setSOperation(String sOperation) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SOPERATION, sOperation);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public SChargeBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public SChargeBO setPartyNum(Integer partyNum) {
		setPrimaryKey(PARTYNUM, partyNum);
		return this;
	}

	public String getPartyCode() {
		return getBaseVO().getTypeString(PARTYCODE).getValue();
	}
	
	public SChargeBO setPartyCode(String partyCode) {
		setPrimaryKey(PARTYCODE, partyCode);
		return this;
	}

	public int getSeq() {
		return getBaseVO().getTypeInteger(SEQ).getValue();
	}
	
	public SChargeBO setSeq(Integer seq) {
		setPrimaryKey(SEQ, seq);
		return this;
	}

	public String getGovCode() {
		return getBaseVO().getTypeString(GOVCODE).getValue();
	}
	
	public SChargeBO setGovCode(String govCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GOVCODE, govCode);
		return this;
	}

	public String getViolCode() {
		return getBaseVO().getTypeString(VIOLCODE).getValue();
	}
	
	public SChargeBO setViolCode(String violCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLCODE, violCode);
		return this;
	}

	public Date getViolDatetime() {
		return getBaseVO().getTypeDate(VIOLDATETIME).getValue();
	}
	
	public SChargeBO setViolDatetime(Date violDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime);
		return this;
	}
	
	public SChargeBO setViolDatetime(Date violDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime, dateFormat);
		getBaseVO().setAttribute(VIOLDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getViolLocn() {
		return getBaseVO().getTypeString(VIOLLOCN).getValue();
	}
	
	public SChargeBO setViolLocn(String violLocn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLLOCN, violLocn);
		return this;
	}

	public String getOffsViolCode() {
		return getBaseVO().getTypeString(OFFSVIOLCODE).getValue();
	}
	
	public SChargeBO setOffsViolCode(String offsViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSVIOLCODE, offsViolCode);
		return this;
	}

	public Date getOffsEffectDate() {
		return getBaseVO().getTypeDate(OFFSEFFECTDATE).getValue();
	}
	
	public SChargeBO setOffsEffectDate(Date offsEffectDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate);
		return this;
	}
	
	public SChargeBO setOffsEffectDate(Date offsEffectDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate, dateFormat);
		getBaseVO().setAttribute(OFFSEFFECTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getInchoateFlag() {
		return getBaseVO().getTypeString(INCHOATEFLAG).getValue();
	}
	
	public SChargeBO setInchoateFlag(String inchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INCHOATEFLAG, inchoateFlag);
		return this;
	}

	public String getSeverity() {
		return getBaseVO().getTypeString(SEVERITY).getValue();
	}
	
	public SChargeBO setSeverity(String severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SEVERITY, severity);
		return this;
	}

	public String getPleaCode() {
		return getBaseVO().getTypeString(PLEACODE).getValue();
	}
	
	public SChargeBO setPleaCode(String pleaCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEACODE, pleaCode);
		return this;
	}

	public Date getPleaDate() {
		return getBaseVO().getTypeDate(PLEADATE).getValue();
	}
	
	public SChargeBO setPleaDate(Date pleaDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate);
		return this;
	}
	
	public SChargeBO setPleaDate(Date pleaDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate, dateFormat);
		getBaseVO().setAttribute(PLEADATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getJdmtCode() {
		return getBaseVO().getTypeString(JDMTCODE).getValue();
	}
	
	public SChargeBO setJdmtCode(String jdmtCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTCODE, jdmtCode);
		return this;
	}

	public Date getJdmtDate() {
		return getBaseVO().getTypeDate(JDMTDATE).getValue();
	}
	
	public SChargeBO setJdmtDate(Date jdmtDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate);
		return this;
	}
	
	public SChargeBO setJdmtDate(Date jdmtDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate, dateFormat);
		getBaseVO().setAttribute(JDMTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDrLicSeverity() {
		return getBaseVO().getTypeString(DRLICSEVERITY).getValue();
	}
	
	public SChargeBO setDrLicSeverity(String drLicSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICSEVERITY, drLicSeverity);
		return this;
	}

	public String getDrLicReduced() {
		return getBaseVO().getTypeString(DRLICREDUCED).getValue();
	}
	
	public SChargeBO setDrLicReduced(String drLicReduced) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICREDUCED, drLicReduced);
		return this;
	}

	public String getDrLicRptStatus() {
		return getBaseVO().getTypeString(DRLICRPTSTATUS).getValue();
	}
	
	public SChargeBO setDrLicRptStatus(String drLicRptStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTSTATUS, drLicRptStatus);
		return this;
	}

	public Date getDrLicRptDate() {
		return getBaseVO().getTypeDate(DRLICRPTDATE).getValue();
	}
	
	public SChargeBO setDrLicRptDate(Date drLicRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate);
		return this;
	}
	
	public SChargeBO setDrLicRptDate(Date drLicRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate, dateFormat);
		getBaseVO().setAttribute(DRLICRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getBciRptDate() {
		return getBaseVO().getTypeDate(BCIRPTDATE).getValue();
	}
	
	public SChargeBO setBciRptDate(Date bciRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate);
		return this;
	}
	
	public SChargeBO setBciRptDate(Date bciRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate, dateFormat);
		getBaseVO().setAttribute(BCIRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getAmendDate() {
		return getBaseVO().getTypeDate(AMENDDATE).getValue();
	}
	
	public SChargeBO setAmendDate(Date amendDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate);
		return this;
	}
	
	public SChargeBO setAmendDate(Date amendDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate, dateFormat);
		getBaseVO().setAttribute(AMENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getOrigGovCode() {
		return getBaseVO().getTypeString(ORIGGOVCODE).getValue();
	}
	
	public SChargeBO setOrigGovCode(String origGovCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGGOVCODE, origGovCode);
		return this;
	}

	public String getOrigViolCode() {
		return getBaseVO().getTypeString(ORIGVIOLCODE).getValue();
	}
	
	public SChargeBO setOrigViolCode(String origViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGVIOLCODE, origViolCode);
		return this;
	}

	public String getOrigInchoateFlag() {
		return getBaseVO().getTypeString(ORIGINCHOATEFLAG).getValue();
	}
	
	public SChargeBO setOrigInchoateFlag(String origInchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGINCHOATEFLAG, origInchoateFlag);
		return this;
	}

	public String getOrigSeverity() {
		return getBaseVO().getTypeString(ORIGSEVERITY).getValue();
	}
	
	public SChargeBO setOrigSeverity(String origSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGSEVERITY, origSeverity);
		return this;
	}

	public String getAmend402Type() {
		return getBaseVO().getTypeString(AMEND402TYPE).getValue();
	}
	
	public SChargeBO setAmend402Type(String amend402Type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402TYPE, amend402Type);
		return this;
	}

	public String getAmend402Code() {
		return getBaseVO().getTypeString(AMEND402CODE).getValue();
	}
	
	public SChargeBO setAmend402Code(String amend402Code) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402CODE, amend402Code);
		return this;
	}

	public String getPre402Severity() {
		return getBaseVO().getTypeString(PRE402SEVERITY).getValue();
	}
	
	public SChargeBO setPre402Severity(String pre402Severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRE402SEVERITY, pre402Severity);
		return this;
	}

	public String getEnhancType() {
		return getBaseVO().getTypeString(ENHANCTYPE).getValue();
	}
	
	public SChargeBO setEnhancType(String enhancType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ENHANCTYPE, enhancType);
		return this;
	}

	public String getPreenhancSeverity() {
		return getBaseVO().getTypeString(PREENHANCSEVERITY).getValue();
	}
	
	public SChargeBO setPreenhancSeverity(String preenhancSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PREENHANCSEVERITY, preenhancSeverity);
		return this;
	}

	public String getSpValue1() {
		return getBaseVO().getTypeString(SPVALUE1).getValue();
	}
	
	public SChargeBO setSpValue1(String spValue1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE1, spValue1);
		return this;
	}

	public String getSpValue2() {
		return getBaseVO().getTypeString(SPVALUE2).getValue();
	}
	
	public SChargeBO setSpValue2(String spValue2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE2, spValue2);
		return this;
	}
	
	public SChargeBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<SChargeBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new SChargeBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new SChargeBO(getBaseVO());
		}
	}
	
	public SChargeBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public SChargeBO insert(FieldDescriptor... specificFields) throws Exception {
		return new SChargeBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public SChargeBO update(FieldDescriptor... specificFields) throws Exception {
		return new SChargeBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public SChargeBO delete() throws Exception {
		return new SChargeBO(super.delete(getNativeDescriptor()));
	}
	
	public SChargeBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SChargeBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SChargeBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public SChargeBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public SChargeBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public SChargeBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public SChargeBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SChargeBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SChargeBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public SChargeBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SChargeBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SChargeBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public SChargeBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public SChargeBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public SChargeBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SChargeBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SChargeBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public SChargeBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SChargeBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public SChargeBO where(FieldDescriptor findBy, Object value) {
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
	
	public SChargeBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SChargeBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public SChargeBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public SChargeBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public SChargeBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public SChargeBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public SChargeBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public SChargeBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public SChargeBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public SChargeBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public SChargeBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public SChargeBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public SChargeBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public SChargeBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public SChargeBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public SChargeBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<SChargeBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public SChargeBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public SChargeBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SChargeBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public SChargeBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public SChargeBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public SChargeBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public SChargeBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public SChargeBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public SChargeBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public SChargeBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public SChargeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public SChargeBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SChargeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SChargeBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SChargeBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public SChargeBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public SChargeBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public SChargeBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public SChargeBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public SChargeBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public SChargeBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public SChargeBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public SChargeBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SChargeBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SChargeBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public SChargeBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative SChargeBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public SChargeBO setQuestionMarks(Object... values) {
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
	public SChargeBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public SChargeBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public SChargeBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public SChargeBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public SChargeBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public SChargeBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public SChargeBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public SChargeBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public SChargeBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public SChargeBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public SChargeBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public SChargeBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public SChargeBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}