package gov.utcourts.coriscommon.dataaccess.chargeold;

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
public class ChargeOldBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("charge_old", "gov.utcourts.coriscommon.dataaccess.chargeold.ChargeOld");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor ACTIONTYPE = new FieldDescriptor(TABLE, "action_type", "setActionType", 0);
	public static final FieldDescriptor REASON = new FieldDescriptor(TABLE, "reason", "setReason", 1);
	public static final FieldDescriptor USERIDSRL = new FieldDescriptor(TABLE, "userid_srl", "setUseridSrl", 2);
	public static final FieldDescriptor CHANGEDATETIME = new FieldDescriptor(TABLE, "change_datetime", "setChangeDatetime", 3);
	public static final FieldDescriptor CASENUM = new FieldDescriptor(TABLE, "case_num", "setCaseNum", 4);
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 5);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 6);
	public static final FieldDescriptor INTCASENUM = new FieldDescriptor(TABLE, "int_case_num", "setIntCaseNum", 7);
	public static final FieldDescriptor PARTYNUM = new FieldDescriptor(TABLE, "party_num", "setPartyNum", 8);
	public static final FieldDescriptor PARTYCODE = new FieldDescriptor(TABLE, "party_code", "setPartyCode", 9);
	public static final FieldDescriptor SEQ = new FieldDescriptor(TABLE, "seq", "setSeq", 10);
	public static final FieldDescriptor GOVCODE = new FieldDescriptor(TABLE, "gov_code", "setGovCode", 11);
	public static final FieldDescriptor VIOLCODE = new FieldDescriptor(TABLE, "viol_code", "setViolCode", 12);
	public static final FieldDescriptor VIOLDATETIME = new FieldDescriptor(TABLE, "viol_datetime", "setViolDatetime", 13);
	public static final FieldDescriptor VIOLLOCN = new FieldDescriptor(TABLE, "viol_locn", "setViolLocn", 14);
	public static final FieldDescriptor OFFSVIOLCODE = new FieldDescriptor(TABLE, "offs_viol_code", "setOffsViolCode", 15);
	public static final FieldDescriptor OFFSEFFECTDATE = new FieldDescriptor(TABLE, "offs_effect_date", "setOffsEffectDate", 16);
	public static final FieldDescriptor INCHOATEFLAG = new FieldDescriptor(TABLE, "inchoate_flag", "setInchoateFlag", 17);
	public static final FieldDescriptor SEVERITY = new FieldDescriptor(TABLE, "severity", "setSeverity", 18);
	public static final FieldDescriptor PLEACODE = new FieldDescriptor(TABLE, "plea_code", "setPleaCode", 19);
	public static final FieldDescriptor PLEADATE = new FieldDescriptor(TABLE, "plea_date", "setPleaDate", 20);
	public static final FieldDescriptor JDMTCODE = new FieldDescriptor(TABLE, "jdmt_code", "setJdmtCode", 21);
	public static final FieldDescriptor JDMTDATE = new FieldDescriptor(TABLE, "jdmt_date", "setJdmtDate", 22);
	public static final FieldDescriptor DRLICSEVERITY = new FieldDescriptor(TABLE, "dr_lic_severity", "setDrLicSeverity", 23);
	public static final FieldDescriptor DRLICREDUCED = new FieldDescriptor(TABLE, "dr_lic_reduced", "setDrLicReduced", 24);
	public static final FieldDescriptor DRLICRPTSTATUS = new FieldDescriptor(TABLE, "dr_lic_rpt_status", "setDrLicRptStatus", 25);
	public static final FieldDescriptor DRLICRPTDATE = new FieldDescriptor(TABLE, "dr_lic_rpt_date", "setDrLicRptDate", 26);
	public static final FieldDescriptor BCIRPTDATE = new FieldDescriptor(TABLE, "bci_rpt_date", "setBciRptDate", 27);
	public static final FieldDescriptor AMENDDATE = new FieldDescriptor(TABLE, "amend_date", "setAmendDate", 28);
	public static final FieldDescriptor ORIGGOVCODE = new FieldDescriptor(TABLE, "orig_gov_code", "setOrigGovCode", 29);
	public static final FieldDescriptor ORIGVIOLCODE = new FieldDescriptor(TABLE, "orig_viol_code", "setOrigViolCode", 30);
	public static final FieldDescriptor ORIGINCHOATEFLAG = new FieldDescriptor(TABLE, "orig_inchoate_flag", "setOrigInchoateFlag", 31);
	public static final FieldDescriptor ORIGSEVERITY = new FieldDescriptor(TABLE, "orig_severity", "setOrigSeverity", 32);
	public static final FieldDescriptor AMEND402TYPE = new FieldDescriptor(TABLE, "amend402_type", "setAmend402Type", 33);
	public static final FieldDescriptor AMEND402CODE = new FieldDescriptor(TABLE, "amend402_code", "setAmend402Code", 34);
	public static final FieldDescriptor PRE402SEVERITY = new FieldDescriptor(TABLE, "pre402_severity", "setPre402Severity", 35);
	public static final FieldDescriptor ENHANCTYPE = new FieldDescriptor(TABLE, "enhanc_type", "setEnhancType", 36);
	public static final FieldDescriptor PREENHANCSEVERITY = new FieldDescriptor(TABLE, "preenhanc_severity", "setPreenhancSeverity", 37);
	public static final FieldDescriptor SPVALUE1 = new FieldDescriptor(TABLE, "sp_value_1", "setSpValue1", 38);
	public static final FieldDescriptor SPVALUE2 = new FieldDescriptor(TABLE, "sp_value_2", "setSpValue2", 39);

	public ChargeOldBO() {
		super(new ChargeOldVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public ChargeOldBO(String source) {
		super(new ChargeOldVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public ChargeOldBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getActionType() {
		return getBaseVO().getTypeString(ACTIONTYPE).getValue();
	}
	
	public ChargeOldBO setActionType(String actionType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ACTIONTYPE, actionType);
		return this;
	}

	public String getReason() {
		return getBaseVO().getTypeString(REASON).getValue();
	}
	
	public ChargeOldBO setReason(String reason) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REASON, reason);
		return this;
	}

	public int getUseridSrl() {
		return getBaseVO().getTypeInteger(USERIDSRL).getValue();
	}
	
	public ChargeOldBO setUseridSrl(Integer useridSrl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, USERIDSRL, useridSrl);
		return this;
	}

	public Date getChangeDatetime() {
		return getBaseVO().getTypeDate(CHANGEDATETIME).getValue();
	}
	
	public ChargeOldBO setChangeDatetime(Date changeDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime);
		return this;
	}
	
	public ChargeOldBO setChangeDatetime(Date changeDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHANGEDATETIME, changeDatetime, dateFormat);
		getBaseVO().setAttribute(CHANGEDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getCaseNum() {
		return getBaseVO().getTypeString(CASENUM).getValue();
	}
	
	public ChargeOldBO setCaseNum(String caseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CASENUM, caseNum);
		return this;
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public ChargeOldBO setLocnCode(String locnCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public ChargeOldBO setCourtType(String courtType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTYPE, courtType);
		return this;
	}

	public int getIntCaseNum() {
		return getBaseVO().getTypeInteger(INTCASENUM).getValue();
	}
	
	public ChargeOldBO setIntCaseNum(Integer intCaseNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTCASENUM, intCaseNum);
		return this;
	}

	public int getPartyNum() {
		return getBaseVO().getTypeInteger(PARTYNUM).getValue();
	}
	
	public ChargeOldBO setPartyNum(Integer partyNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYNUM, partyNum);
		return this;
	}

	public String getPartyCode() {
		return getBaseVO().getTypeString(PARTYCODE).getValue();
	}
	
	public ChargeOldBO setPartyCode(String partyCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PARTYCODE, partyCode);
		return this;
	}

	public int getSeq() {
		return getBaseVO().getTypeInteger(SEQ).getValue();
	}
	
	public ChargeOldBO setSeq(Integer seq) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SEQ, seq);
		return this;
	}

	public String getGovCode() {
		return getBaseVO().getTypeString(GOVCODE).getValue();
	}
	
	public ChargeOldBO setGovCode(String govCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GOVCODE, govCode);
		return this;
	}

	public String getViolCode() {
		return getBaseVO().getTypeString(VIOLCODE).getValue();
	}
	
	public ChargeOldBO setViolCode(String violCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLCODE, violCode);
		return this;
	}

	public Date getViolDatetime() {
		return getBaseVO().getTypeDate(VIOLDATETIME).getValue();
	}
	
	public ChargeOldBO setViolDatetime(Date violDatetime) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime);
		return this;
	}
	
	public ChargeOldBO setViolDatetime(Date violDatetime, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLDATETIME, violDatetime, dateFormat);
		getBaseVO().setAttribute(VIOLDATETIME.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getViolLocn() {
		return getBaseVO().getTypeString(VIOLLOCN).getValue();
	}
	
	public ChargeOldBO setViolLocn(String violLocn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLLOCN, violLocn);
		return this;
	}

	public String getOffsViolCode() {
		return getBaseVO().getTypeString(OFFSVIOLCODE).getValue();
	}
	
	public ChargeOldBO setOffsViolCode(String offsViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSVIOLCODE, offsViolCode);
		return this;
	}

	public Date getOffsEffectDate() {
		return getBaseVO().getTypeDate(OFFSEFFECTDATE).getValue();
	}
	
	public ChargeOldBO setOffsEffectDate(Date offsEffectDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate);
		return this;
	}
	
	public ChargeOldBO setOffsEffectDate(Date offsEffectDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSEFFECTDATE, offsEffectDate, dateFormat);
		getBaseVO().setAttribute(OFFSEFFECTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getInchoateFlag() {
		return getBaseVO().getTypeString(INCHOATEFLAG).getValue();
	}
	
	public ChargeOldBO setInchoateFlag(String inchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INCHOATEFLAG, inchoateFlag);
		return this;
	}

	public String getSeverity() {
		return getBaseVO().getTypeString(SEVERITY).getValue();
	}
	
	public ChargeOldBO setSeverity(String severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SEVERITY, severity);
		return this;
	}

	public String getPleaCode() {
		return getBaseVO().getTypeString(PLEACODE).getValue();
	}
	
	public ChargeOldBO setPleaCode(String pleaCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEACODE, pleaCode);
		return this;
	}

	public Date getPleaDate() {
		return getBaseVO().getTypeDate(PLEADATE).getValue();
	}
	
	public ChargeOldBO setPleaDate(Date pleaDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate);
		return this;
	}
	
	public ChargeOldBO setPleaDate(Date pleaDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PLEADATE, pleaDate, dateFormat);
		getBaseVO().setAttribute(PLEADATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getJdmtCode() {
		return getBaseVO().getTypeString(JDMTCODE).getValue();
	}
	
	public ChargeOldBO setJdmtCode(String jdmtCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTCODE, jdmtCode);
		return this;
	}

	public Date getJdmtDate() {
		return getBaseVO().getTypeDate(JDMTDATE).getValue();
	}
	
	public ChargeOldBO setJdmtDate(Date jdmtDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate);
		return this;
	}
	
	public ChargeOldBO setJdmtDate(Date jdmtDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JDMTDATE, jdmtDate, dateFormat);
		getBaseVO().setAttribute(JDMTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getDrLicSeverity() {
		return getBaseVO().getTypeString(DRLICSEVERITY).getValue();
	}
	
	public ChargeOldBO setDrLicSeverity(String drLicSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICSEVERITY, drLicSeverity);
		return this;
	}

	public String getDrLicReduced() {
		return getBaseVO().getTypeString(DRLICREDUCED).getValue();
	}
	
	public ChargeOldBO setDrLicReduced(String drLicReduced) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICREDUCED, drLicReduced);
		return this;
	}

	public String getDrLicRptStatus() {
		return getBaseVO().getTypeString(DRLICRPTSTATUS).getValue();
	}
	
	public ChargeOldBO setDrLicRptStatus(String drLicRptStatus) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTSTATUS, drLicRptStatus);
		return this;
	}

	public Date getDrLicRptDate() {
		return getBaseVO().getTypeDate(DRLICRPTDATE).getValue();
	}
	
	public ChargeOldBO setDrLicRptDate(Date drLicRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate);
		return this;
	}
	
	public ChargeOldBO setDrLicRptDate(Date drLicRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICRPTDATE, drLicRptDate, dateFormat);
		getBaseVO().setAttribute(DRLICRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getBciRptDate() {
		return getBaseVO().getTypeDate(BCIRPTDATE).getValue();
	}
	
	public ChargeOldBO setBciRptDate(Date bciRptDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate);
		return this;
	}
	
	public ChargeOldBO setBciRptDate(Date bciRptDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIRPTDATE, bciRptDate, dateFormat);
		getBaseVO().setAttribute(BCIRPTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getAmendDate() {
		return getBaseVO().getTypeDate(AMENDDATE).getValue();
	}
	
	public ChargeOldBO setAmendDate(Date amendDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate);
		return this;
	}
	
	public ChargeOldBO setAmendDate(Date amendDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMENDDATE, amendDate, dateFormat);
		getBaseVO().setAttribute(AMENDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getOrigGovCode() {
		return getBaseVO().getTypeString(ORIGGOVCODE).getValue();
	}
	
	public ChargeOldBO setOrigGovCode(String origGovCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGGOVCODE, origGovCode);
		return this;
	}

	public String getOrigViolCode() {
		return getBaseVO().getTypeString(ORIGVIOLCODE).getValue();
	}
	
	public ChargeOldBO setOrigViolCode(String origViolCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGVIOLCODE, origViolCode);
		return this;
	}

	public String getOrigInchoateFlag() {
		return getBaseVO().getTypeString(ORIGINCHOATEFLAG).getValue();
	}
	
	public ChargeOldBO setOrigInchoateFlag(String origInchoateFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGINCHOATEFLAG, origInchoateFlag);
		return this;
	}

	public String getOrigSeverity() {
		return getBaseVO().getTypeString(ORIGSEVERITY).getValue();
	}
	
	public ChargeOldBO setOrigSeverity(String origSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ORIGSEVERITY, origSeverity);
		return this;
	}

	public String getAmend402Type() {
		return getBaseVO().getTypeString(AMEND402TYPE).getValue();
	}
	
	public ChargeOldBO setAmend402Type(String amend402Type) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402TYPE, amend402Type);
		return this;
	}

	public String getAmend402Code() {
		return getBaseVO().getTypeString(AMEND402CODE).getValue();
	}
	
	public ChargeOldBO setAmend402Code(String amend402Code) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AMEND402CODE, amend402Code);
		return this;
	}

	public String getPre402Severity() {
		return getBaseVO().getTypeString(PRE402SEVERITY).getValue();
	}
	
	public ChargeOldBO setPre402Severity(String pre402Severity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRE402SEVERITY, pre402Severity);
		return this;
	}

	public String getEnhancType() {
		return getBaseVO().getTypeString(ENHANCTYPE).getValue();
	}
	
	public ChargeOldBO setEnhancType(String enhancType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ENHANCTYPE, enhancType);
		return this;
	}

	public String getPreenhancSeverity() {
		return getBaseVO().getTypeString(PREENHANCSEVERITY).getValue();
	}
	
	public ChargeOldBO setPreenhancSeverity(String preenhancSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PREENHANCSEVERITY, preenhancSeverity);
		return this;
	}

	public String getSpValue1() {
		return getBaseVO().getTypeString(SPVALUE1).getValue();
	}
	
	public ChargeOldBO setSpValue1(String spValue1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE1, spValue1);
		return this;
	}

	public String getSpValue2() {
		return getBaseVO().getTypeString(SPVALUE2).getValue();
	}
	
	public ChargeOldBO setSpValue2(String spValue2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPVALUE2, spValue2);
		return this;
	}
	
	public ChargeOldBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<ChargeOldBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new ChargeOldBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new ChargeOldBO(getBaseVO());
		}
	}
	
	public ChargeOldBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public ChargeOldBO insert(FieldDescriptor... specificFields) throws Exception {
		return new ChargeOldBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public ChargeOldBO update(FieldDescriptor... specificFields) throws Exception {
		return new ChargeOldBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public ChargeOldBO delete() throws Exception {
		return new ChargeOldBO(super.delete(getNativeDescriptor()));
	}
	
	public ChargeOldBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public ChargeOldBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public ChargeOldBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public ChargeOldBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public ChargeOldBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public ChargeOldBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public ChargeOldBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public ChargeOldBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public ChargeOldBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public ChargeOldBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ChargeOldBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ChargeOldBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public ChargeOldBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public ChargeOldBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public ChargeOldBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ChargeOldBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ChargeOldBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public ChargeOldBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public ChargeOldBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public ChargeOldBO where(FieldDescriptor findBy, Object value) {
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
	
	public ChargeOldBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public ChargeOldBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public ChargeOldBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public ChargeOldBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public ChargeOldBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public ChargeOldBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public ChargeOldBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public ChargeOldBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public ChargeOldBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public ChargeOldBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public ChargeOldBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public ChargeOldBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public ChargeOldBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public ChargeOldBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public ChargeOldBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public ChargeOldBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<ChargeOldBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public ChargeOldBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public ChargeOldBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public ChargeOldBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public ChargeOldBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public ChargeOldBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public ChargeOldBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public ChargeOldBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public ChargeOldBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public ChargeOldBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public ChargeOldBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public ChargeOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public ChargeOldBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public ChargeOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public ChargeOldBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public ChargeOldBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public ChargeOldBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public ChargeOldBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public ChargeOldBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public ChargeOldBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public ChargeOldBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public ChargeOldBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public ChargeOldBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public ChargeOldBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public ChargeOldBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public ChargeOldBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public ChargeOldBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative ChargeOldBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public ChargeOldBO setQuestionMarks(Object... values) {
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
	public ChargeOldBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public ChargeOldBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public ChargeOldBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public ChargeOldBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public ChargeOldBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public ChargeOldBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public ChargeOldBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public ChargeOldBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public ChargeOldBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public ChargeOldBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public ChargeOldBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public ChargeOldBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public ChargeOldBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}