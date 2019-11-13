package gov.utcourts.coriscommon.dataaccess.smoter;

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
public class SmotErBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("smot_er", "gov.utcourts.coriscommon.dataaccess.smoter.SmotEr");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor OFFENSEID = new FieldDescriptor(TABLE, "offense_id", "setOffenseId", 0);
	public static final FieldDescriptor VIOLATIONCODE = new FieldDescriptor(TABLE, "violation_code", "setViolationCode", 1);
	public static final FieldDescriptor GOVCODE = new FieldDescriptor(TABLE, "gov_code", "setGovCode", 2);
	public static final FieldDescriptor STARTDATE = new FieldDescriptor(TABLE, "start_date", "setStartDate", 3);
	public static final FieldDescriptor REPEALDATE = new FieldDescriptor(TABLE, "repeal_date", "setRepealDate", 4);
	public static final FieldDescriptor SHORTDESC = new FieldDescriptor(TABLE, "short_desc", "setShortDesc", 5);
	public static final FieldDescriptor LONGDESC = new FieldDescriptor(TABLE, "long_desc", "setLongDesc", 6);
	public static final FieldDescriptor CATEGORY = new FieldDescriptor(TABLE, "category", "setCategory", 7);
	public static final FieldDescriptor NCICCODE = new FieldDescriptor(TABLE, "ncic_code", "setNcicCode", 8);
	public static final FieldDescriptor AAMVACODE = new FieldDescriptor(TABLE, "aamva_code", "setAamvaCode", 9);
	public static final FieldDescriptor DLREPORTABLE = new FieldDescriptor(TABLE, "dl_reportable", "setDlReportable", 10);
	public static final FieldDescriptor BCIREPORTABLE = new FieldDescriptor(TABLE, "bci_reportable", "setBciReportable", 11);
	public static final FieldDescriptor FTAFLAG = new FieldDescriptor(TABLE, "fta_flag", "setFtaFlag", 12);
	public static final FieldDescriptor WARRANTFLAG = new FieldDescriptor(TABLE, "warrant_flag", "setWarrantFlag", 13);
	public static final FieldDescriptor MANDAPPEARFLAG = new FieldDescriptor(TABLE, "mand_appear_flag", "setMandAppearFlag", 14);
	public static final FieldDescriptor SPECPROCESSATTR = new FieldDescriptor(TABLE, "spec_process_attr", "setSpecProcessAttr", 15);
	public static final FieldDescriptor DEFAULTSEVERITY = new FieldDescriptor(TABLE, "default_severity", "setDefaultSeverity", 16);
	public static final FieldDescriptor NONMOVINGTRAFFIC = new FieldDescriptor(TABLE, "non_moving_traffic", "setNonMovingTraffic", 17);
	public static final FieldDescriptor SUGGESTEDBAIL = new FieldDescriptor(TABLE, "suggested_bail", "setSuggestedBail", 18);
	public static final FieldDescriptor COMPLIANCECREDIT = new FieldDescriptor(TABLE, "compliance_credit", "setComplianceCredit", 19);
	public static final FieldDescriptor JCSECURITYSRCHG = new FieldDescriptor(TABLE, "jc_security_srchg", "setJcSecuritySrchg", 20);
	public static final FieldDescriptor STATUTECODE = new FieldDescriptor(TABLE, "statute_code", "setStatuteCode", 21);
	public static final FieldDescriptor INSERTDATE = new FieldDescriptor(TABLE, "insert_date", "setInsertDate", 22);
	public static final FieldDescriptor INSERTBY = new FieldDescriptor(TABLE, "insert_by", "setInsertBy", 23);
	public static final FieldDescriptor PIMID = new FieldDescriptor(TABLE, "pim_id", "setPimId", 24);
	public static final FieldDescriptor CHARGETYPE = new FieldDescriptor(TABLE, "charge_type", "setChargeType", 25);
	public static final FieldDescriptor DNAREQ = new FieldDescriptor(TABLE, "dna_req", "setDnaReq", 26);
	public static final FieldDescriptor OFFSVIOLATIONCODE = new FieldDescriptor(TABLE, "offs_violation_code", "setOffsViolationCode", 27);
	public static final FieldDescriptor LASTMODIFIEDDATE = new FieldDescriptor(TABLE, "last_modified_date", "setLastModifiedDate", 28);
	public static final FieldDescriptor LASTMODIFIEDUSER = new FieldDescriptor(TABLE, "last_modified_user", "setLastModifiedUser", 29);
	public static final FieldDescriptor LASTMODIFIEDAGENCY = new FieldDescriptor(TABLE, "last_modified_agency", "setLastModifiedAgency", 30);
	public static final FieldDescriptor LASTMODIFIEDREASON = new FieldDescriptor(TABLE, "last_modified_reason", "setLastModifiedReason", 31);
	public static final FieldDescriptor CHARGINGLANGUAGE = new FieldDescriptor(TABLE, "charging_language", "setChargingLanguage", 32);
	public static final FieldDescriptor UDCOFFTYPECODE = new FieldDescriptor(TABLE, "udc_off_type_code", "setUdcOffTypeCode", 33);
	public static final FieldDescriptor FULLTERMINATIONFLAG = new FieldDescriptor(TABLE, "full_termination_flag", "setFullTerminationFlag", 34);
	public static final FieldDescriptor FULLTERMINATIONEFFDT = new FieldDescriptor(TABLE, "full_termination_eff_dt", "setFullTerminationEffDt", 35);
	public static final FieldDescriptor ODNACOLLECTATARREST = new FieldDescriptor(TABLE, "odna_collect_at_arrest", "setOdnaCollectAtArrest", 36);
	public static final FieldDescriptor ODNACOLLECTATCONVICTION = new FieldDescriptor(TABLE, "odna_collect_at_conviction", "setOdnaCollectAtConviction", 37);

	public SmotErBO() {
		super(new SmotErVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public SmotErBO(String source) {
		super(new SmotErVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public SmotErBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public long getOffenseId() {
		return getBaseVO().getTypeLong(OFFENSEID).getValue();
	}
	
	public SmotErBO setOffenseId(Long offenseId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFENSEID, offenseId);
		return this;
	}

	public String getViolationCode() {
		return getBaseVO().getTypeString(VIOLATIONCODE).getValue();
	}
	
	public SmotErBO setViolationCode(String violationCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIOLATIONCODE, violationCode);
		return this;
	}

	public String getGovCode() {
		return getBaseVO().getTypeString(GOVCODE).getValue();
	}
	
	public SmotErBO setGovCode(String govCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GOVCODE, govCode);
		return this;
	}

	public Date getStartDate() {
		return getBaseVO().getTypeDate(STARTDATE).getValue();
	}
	
	public SmotErBO setStartDate(Date startDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STARTDATE, startDate);
		return this;
	}
	
	public SmotErBO setStartDate(Date startDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STARTDATE, startDate, dateFormat);
		getBaseVO().setAttribute(STARTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public Date getRepealDate() {
		return getBaseVO().getTypeDate(REPEALDATE).getValue();
	}
	
	public SmotErBO setRepealDate(Date repealDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REPEALDATE, repealDate);
		return this;
	}
	
	public SmotErBO setRepealDate(Date repealDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REPEALDATE, repealDate, dateFormat);
		getBaseVO().setAttribute(REPEALDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getShortDesc() {
		return getBaseVO().getTypeString(SHORTDESC).getValue();
	}
	
	public SmotErBO setShortDesc(String shortDesc) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SHORTDESC, shortDesc);
		return this;
	}

	public String getLongDesc() {
		return getBaseVO().getTypeString(LONGDESC).getValue();
	}
	
	public SmotErBO setLongDesc(String longDesc) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LONGDESC, longDesc);
		return this;
	}

	public String getCategory() {
		return getBaseVO().getTypeString(CATEGORY).getValue();
	}
	
	public SmotErBO setCategory(String category) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CATEGORY, category);
		return this;
	}

	public String getNcicCode() {
		return getBaseVO().getTypeString(NCICCODE).getValue();
	}
	
	public SmotErBO setNcicCode(String ncicCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICCODE, ncicCode);
		return this;
	}

	public String getAamvaCode() {
		return getBaseVO().getTypeString(AAMVACODE).getValue();
	}
	
	public SmotErBO setAamvaCode(String aamvaCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, AAMVACODE, aamvaCode);
		return this;
	}

	public String getDlReportable() {
		return getBaseVO().getTypeString(DLREPORTABLE).getValue();
	}
	
	public SmotErBO setDlReportable(String dlReportable) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DLREPORTABLE, dlReportable);
		return this;
	}

	public boolean getBciReportable() {
		return getBaseVO().getTypeBoolean(BCIREPORTABLE).getValue();
	}
	
	public SmotErBO setBciReportable(boolean bciReportable) {
		getBaseVO().setPropertyListValue(SET_BY_USER, BCIREPORTABLE, bciReportable);
		return this;
	}

	public boolean getFtaFlag() {
		return getBaseVO().getTypeBoolean(FTAFLAG).getValue();
	}
	
	public SmotErBO setFtaFlag(boolean ftaFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAFLAG, ftaFlag);
		return this;
	}

	public boolean getWarrantFlag() {
		return getBaseVO().getTypeBoolean(WARRANTFLAG).getValue();
	}
	
	public SmotErBO setWarrantFlag(boolean warrantFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARRANTFLAG, warrantFlag);
		return this;
	}

	public boolean getMandAppearFlag() {
		return getBaseVO().getTypeBoolean(MANDAPPEARFLAG).getValue();
	}
	
	public SmotErBO setMandAppearFlag(boolean mandAppearFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MANDAPPEARFLAG, mandAppearFlag);
		return this;
	}

	public String getSpecProcessAttr() {
		return getBaseVO().getTypeString(SPECPROCESSATTR).getValue();
	}
	
	public SmotErBO setSpecProcessAttr(String specProcessAttr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SPECPROCESSATTR, specProcessAttr);
		return this;
	}

	public String getDefaultSeverity() {
		return getBaseVO().getTypeString(DEFAULTSEVERITY).getValue();
	}
	
	public SmotErBO setDefaultSeverity(String defaultSeverity) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFAULTSEVERITY, defaultSeverity);
		return this;
	}

	public boolean getNonMovingTraffic() {
		return getBaseVO().getTypeBoolean(NONMOVINGTRAFFIC).getValue();
	}
	
	public SmotErBO setNonMovingTraffic(boolean nonMovingTraffic) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NONMOVINGTRAFFIC, nonMovingTraffic);
		return this;
	}

	public BigDecimal getSuggestedBail() {
		return getBaseVO().getTypeBigDecimal(SUGGESTEDBAIL).getValue();
	}
	
	public SmotErBO setSuggestedBail(BigDecimal suggestedBail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUGGESTEDBAIL, suggestedBail);
		return this;
	}
	
	public SmotErBO setSuggestedBail(double suggestedBail) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUGGESTEDBAIL, new BigDecimal(String.valueOf(new Double(suggestedBail))));
		return this;
	}

	public BigDecimal getComplianceCredit() {
		return getBaseVO().getTypeBigDecimal(COMPLIANCECREDIT).getValue();
	}
	
	public SmotErBO setComplianceCredit(BigDecimal complianceCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMPLIANCECREDIT, complianceCredit);
		return this;
	}
	
	public SmotErBO setComplianceCredit(double complianceCredit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COMPLIANCECREDIT, new BigDecimal(String.valueOf(new Double(complianceCredit))));
		return this;
	}

	public boolean getJcSecuritySrchg() {
		return getBaseVO().getTypeBoolean(JCSECURITYSRCHG).getValue();
	}
	
	public SmotErBO setJcSecuritySrchg(boolean jcSecuritySrchg) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JCSECURITYSRCHG, jcSecuritySrchg);
		return this;
	}

	public String getStatuteCode() {
		return getBaseVO().getTypeString(STATUTECODE).getValue();
	}
	
	public SmotErBO setStatuteCode(String statuteCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATUTECODE, statuteCode);
		return this;
	}

	public Date getInsertDate() {
		return getBaseVO().getTypeDate(INSERTDATE).getValue();
	}
	
	public SmotErBO setInsertDate(Date insertDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INSERTDATE, insertDate);
		return this;
	}
	
	public SmotErBO setInsertDate(Date insertDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INSERTDATE, insertDate, dateFormat);
		getBaseVO().setAttribute(INSERTDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getInsertBy() {
		return getBaseVO().getTypeString(INSERTBY).getValue();
	}
	
	public SmotErBO setInsertBy(String insertBy) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INSERTBY, insertBy);
		return this;
	}

	public long getPimId() {
		return getBaseVO().getTypeLong(PIMID).getValue();
	}
	
	public SmotErBO setPimId(Long pimId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PIMID, pimId);
		return this;
	}

	public String getChargeType() {
		return getBaseVO().getTypeString(CHARGETYPE).getValue();
	}
	
	public SmotErBO setChargeType(String chargeType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHARGETYPE, chargeType);
		return this;
	}

	public boolean getDnaReq() {
		return getBaseVO().getTypeBoolean(DNAREQ).getValue();
	}
	
	public SmotErBO setDnaReq(boolean dnaReq) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DNAREQ, dnaReq);
		return this;
	}

	public String getOffsViolationCode() {
		return getBaseVO().getTypeString(OFFSVIOLATIONCODE).getValue();
	}
	
	public SmotErBO setOffsViolationCode(String offsViolationCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OFFSVIOLATIONCODE, offsViolationCode);
		return this;
	}

	public Date getLastModifiedDate() {
		return getBaseVO().getTypeDate(LASTMODIFIEDDATE).getValue();
	}
	
	public SmotErBO setLastModifiedDate(Date lastModifiedDate) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTMODIFIEDDATE, lastModifiedDate);
		return this;
	}
	
	public SmotErBO setLastModifiedDate(Date lastModifiedDate, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTMODIFIEDDATE, lastModifiedDate, dateFormat);
		getBaseVO().setAttribute(LASTMODIFIEDDATE.getDateFormatterKey(), dateFormat);
		return this;
	}

	public String getLastModifiedUser() {
		return getBaseVO().getTypeString(LASTMODIFIEDUSER).getValue();
	}
	
	public SmotErBO setLastModifiedUser(String lastModifiedUser) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTMODIFIEDUSER, lastModifiedUser);
		return this;
	}

	public String getLastModifiedAgency() {
		return getBaseVO().getTypeString(LASTMODIFIEDAGENCY).getValue();
	}
	
	public SmotErBO setLastModifiedAgency(String lastModifiedAgency) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTMODIFIEDAGENCY, lastModifiedAgency);
		return this;
	}

	public String getLastModifiedReason() {
		return getBaseVO().getTypeString(LASTMODIFIEDREASON).getValue();
	}
	
	public SmotErBO setLastModifiedReason(String lastModifiedReason) {
		getBaseVO().setPropertyListValue(SET_BY_USER, LASTMODIFIEDREASON, lastModifiedReason);
		return this;
	}

	public String getChargingLanguage() {
		return getBaseVO().getTypeText(CHARGINGLANGUAGE).getValue();
	}
	
	public SmotErBO setChargingLanguage(String chargingLanguage) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHARGINGLANGUAGE, chargingLanguage);
		return this;
	}

	public String getUdcOffTypeCode() {
		return getBaseVO().getTypeString(UDCOFFTYPECODE).getValue();
	}
	
	public SmotErBO setUdcOffTypeCode(String udcOffTypeCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, UDCOFFTYPECODE, udcOffTypeCode);
		return this;
	}

	public boolean getFullTerminationFlag() {
		return getBaseVO().getTypeBoolean(FULLTERMINATIONFLAG).getValue();
	}
	
	public SmotErBO setFullTerminationFlag(boolean fullTerminationFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FULLTERMINATIONFLAG, fullTerminationFlag);
		return this;
	}

	public Date getFullTerminationEffDt() {
		return getBaseVO().getTypeDate(FULLTERMINATIONEFFDT).getValue();
	}
	
	public SmotErBO setFullTerminationEffDt(Date fullTerminationEffDt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FULLTERMINATIONEFFDT, fullTerminationEffDt);
		return this;
	}
	
	public SmotErBO setFullTerminationEffDt(Date fullTerminationEffDt, DateFormat dateFormat) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FULLTERMINATIONEFFDT, fullTerminationEffDt, dateFormat);
		getBaseVO().setAttribute(FULLTERMINATIONEFFDT.getDateFormatterKey(), dateFormat);
		return this;
	}

	public boolean getOdnaCollectAtArrest() {
		return getBaseVO().getTypeBoolean(ODNACOLLECTATARREST).getValue();
	}
	
	public SmotErBO setOdnaCollectAtArrest(boolean odnaCollectAtArrest) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ODNACOLLECTATARREST, odnaCollectAtArrest);
		return this;
	}

	public boolean getOdnaCollectAtConviction() {
		return getBaseVO().getTypeBoolean(ODNACOLLECTATCONVICTION).getValue();
	}
	
	public SmotErBO setOdnaCollectAtConviction(boolean odnaCollectAtConviction) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ODNACOLLECTATCONVICTION, odnaCollectAtConviction);
		return this;
	}
	
	public SmotErBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<SmotErBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new SmotErBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new SmotErBO(getBaseVO());
		}
	}
	
	public SmotErBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public SmotErBO insert(FieldDescriptor... specificFields) throws Exception {
		return new SmotErBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public SmotErBO update(FieldDescriptor... specificFields) throws Exception {
		return new SmotErBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public SmotErBO delete() throws Exception {
		return new SmotErBO(super.delete(getNativeDescriptor()));
	}
	
	public SmotErBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SmotErBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public SmotErBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public SmotErBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public SmotErBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public SmotErBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public SmotErBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SmotErBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public SmotErBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public SmotErBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SmotErBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SmotErBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public SmotErBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public SmotErBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public SmotErBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SmotErBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public SmotErBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public SmotErBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SmotErBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public SmotErBO where(FieldDescriptor findBy, Object value) {
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
	
	public SmotErBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public SmotErBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public SmotErBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public SmotErBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public SmotErBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public SmotErBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public SmotErBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public SmotErBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public SmotErBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public SmotErBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public SmotErBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public SmotErBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public SmotErBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public SmotErBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public SmotErBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public SmotErBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<SmotErBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public SmotErBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public SmotErBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SmotErBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public SmotErBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public SmotErBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public SmotErBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public SmotErBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public SmotErBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public SmotErBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public SmotErBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public SmotErBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public SmotErBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SmotErBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public SmotErBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public SmotErBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public SmotErBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public SmotErBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public SmotErBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public SmotErBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public SmotErBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public SmotErBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public SmotErBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public SmotErBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SmotErBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public SmotErBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public SmotErBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative SmotErBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public SmotErBO setQuestionMarks(Object... values) {
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
	public SmotErBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public SmotErBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public SmotErBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public SmotErBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public SmotErBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public SmotErBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public SmotErBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public SmotErBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public SmotErBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public SmotErBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public SmotErBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public SmotErBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public SmotErBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}