package gov.utcourts.coriscommon.dataaccess.profile;

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
import java.util.List;
import java.util.HashMap;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProfileBO extends BaseBO { 

	private SearchDispatcher searchDispatcher = null;
	
	public static final ConnectionProperties CORIS_DISTRICT_DB = new ConnectionProperties("coris_district_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_DB = new ConnectionProperties("coris_justice_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_DISTRICT_READONLY_DB = new ConnectionProperties("coris_district_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");
	public static final ConnectionProperties CORIS_JUSTICE_READONLY_DB = new ConnectionProperties("coris_justice_readonly_db", "/usr/local/WebSphere/AppServer/customlib/properties/Connection_Properties.xml");

	public static final SystemDescriptor SYSTEM = new SystemDescriptor("coris", "/usr/local/WebSphere/AppServer/customlib/properties", "CorisCommon_Properties.xml");
	public static final TableDescriptor TABLE = new TableDescriptor("profile", "gov.utcourts.coriscommon.dataaccess.profile.Profile");

	public static final FieldDescriptor ALL_FIELDS = new FieldDescriptor(TABLE, null, "include all fields from this table", -1);
	public static final FieldDescriptor NO_FIELDS = new FieldDescriptor(TABLE, null, "do not include fields from this table", -2);
	
	public static final FieldDescriptor LOCNCODE = new FieldDescriptor(TABLE, "locn_code", "setLocnCode", 0);
	public static final FieldDescriptor COURTTYPE = new FieldDescriptor(TABLE, "court_type", "setCourtType", 1);
	public static final FieldDescriptor COURTTITLE = new FieldDescriptor(TABLE, "court_title", "setCourtTitle", 2);
	public static final FieldDescriptor ADDRESS1 = new FieldDescriptor(TABLE, "address_1", "setAddress1", 3);
	public static final FieldDescriptor ADDRESS2 = new FieldDescriptor(TABLE, "address_2", "setAddress2", 4);
	public static final FieldDescriptor CITY = new FieldDescriptor(TABLE, "city", "setCity", 5);
	public static final FieldDescriptor STATECODE = new FieldDescriptor(TABLE, "state_code", "setStateCode", 6);
	public static final FieldDescriptor ZIPCODE = new FieldDescriptor(TABLE, "zip_code", "setZipCode", 7);
	public static final FieldDescriptor CNTYCODE = new FieldDescriptor(TABLE, "cnty_code", "setCntyCode", 8);
	public static final FieldDescriptor GENERALPHONE = new FieldDescriptor(TABLE, "general_phone", "setGeneralPhone", 9);
	public static final FieldDescriptor CRIMINALPHONE = new FieldDescriptor(TABLE, "criminal_phone", "setCriminalPhone", 10);
	public static final FieldDescriptor CIVILPHONE = new FieldDescriptor(TABLE, "civil_phone", "setCivilPhone", 11);
	public static final FieldDescriptor JURYPHONE = new FieldDescriptor(TABLE, "jury_phone", "setJuryPhone", 12);
	public static final FieldDescriptor INTERPRETERPHONE = new FieldDescriptor(TABLE, "interpreter_phone", "setInterpreterPhone", 13);
	public static final FieldDescriptor DEBTCOLLPHONE = new FieldDescriptor(TABLE, "debtcoll_phone", "setDebtcollPhone", 14);
	public static final FieldDescriptor JUDGEID = new FieldDescriptor(TABLE, "judge_id", "setJudgeId", 15);
	public static final FieldDescriptor ADMINID = new FieldDescriptor(TABLE, "admin_id", "setAdminId", 16);
	public static final FieldDescriptor WARRCOPYCNT = new FieldDescriptor(TABLE, "warr_copy_cnt", "setWarrCopyCnt", 17);
	public static final FieldDescriptor WARREXPDEFLT = new FieldDescriptor(TABLE, "warr_exp_deflt", "setWarrExpDeflt", 18);
	public static final FieldDescriptor WARRGRACEPERIOD = new FieldDescriptor(TABLE, "warr_grace_period", "setWarrGracePeriod", 19);
	public static final FieldDescriptor FTAGRACEPERIOD = new FieldDescriptor(TABLE, "fta_grace_period", "setFtaGracePeriod", 20);
	public static final FieldDescriptor DELINQPERIOD = new FieldDescriptor(TABLE, "delinq_period", "setDelinqPeriod", 21);
	public static final FieldDescriptor ALTDELINQPERIOD = new FieldDescriptor(TABLE, "alt_delinq_period", "setAltDelinqPeriod", 22);
	public static final FieldDescriptor OVERPAYRETLIMIT = new FieldDescriptor(TABLE, "overpay_ret_limit", "setOverpayRetLimit", 23);
	public static final FieldDescriptor JOINTSEVNUM = new FieldDescriptor(TABLE, "joint_sev_num", "setJointSevNum", 24);
	public static final FieldDescriptor MULTIDAYHEARING = new FieldDescriptor(TABLE, "multi_day_hearing", "setMultiDayHearing", 25);
	public static final FieldDescriptor TNTCFORM = new FieldDescriptor(TABLE, "tn_tc_form", "setTnTcForm", 26);
	public static final FieldDescriptor DEFLTCUSTODYLOCN = new FieldDescriptor(TABLE, "deflt_custody_locn", "setDefltCustodyLocn", 27);
	public static final FieldDescriptor PRINTPATH = new FieldDescriptor(TABLE, "print_path", "setPrintPath", 28);
	public static final FieldDescriptor EXECUTEPATH = new FieldDescriptor(TABLE, "execute_path", "setExecutePath", 29);
	public static final FieldDescriptor SUPERVISORCNTVER = new FieldDescriptor(TABLE, "supervisor_cnt_ver", "setSupervisorCntVer", 30);
	public static final FieldDescriptor NCICCOURTORI = new FieldDescriptor(TABLE, "ncic_court_ori", "setNcicCourtOri", 31);
	public static final FieldDescriptor NCICORIDESCR = new FieldDescriptor(TABLE, "ncic_ori_descr", "setNcicOriDescr", 32);
	public static final FieldDescriptor ADANAME = new FieldDescriptor(TABLE, "ada_name", "setAdaName", 33);
	public static final FieldDescriptor ADAPHONE = new FieldDescriptor(TABLE, "ada_phone", "setAdaPhone", 34);
	public static final FieldDescriptor ADAADDRESS = new FieldDescriptor(TABLE, "ada_address", "setAdaAddress", 35);
	public static final FieldDescriptor DEFLTSENTTRACK = new FieldDescriptor(TABLE, "deflt_sent_track", "setDefltSentTrack", 36);
	public static final FieldDescriptor CHILDSUPPORTLBL = new FieldDescriptor(TABLE, "child_support_lbl", "setChildSupportLbl", 37);
	public static final FieldDescriptor CERTNOTICELBL = new FieldDescriptor(TABLE, "cert_notice_lbl", "setCertNoticeLbl", 38);
	public static final FieldDescriptor INSTALLFLAG = new FieldDescriptor(TABLE, "install_flag", "setInstallFlag", 39);
	public static final FieldDescriptor DRLICPRECINCT = new FieldDescriptor(TABLE, "dr_lic_precinct", "setDrLicPrecinct", 40);
	public static final FieldDescriptor MEDIATYPE = new FieldDescriptor(TABLE, "media_type", "setMediaType", 41);
	public static final FieldDescriptor SURCHARGE = new FieldDescriptor(TABLE, "surcharge", "setSurcharge", 42);
	public static final FieldDescriptor RECEIPTCOPIES = new FieldDescriptor(TABLE, "receipt_copies", "setReceiptCopies", 43);
	public static final FieldDescriptor CACSITE = new FieldDescriptor(TABLE, "cac_site", "setCacSite", 44);
	public static final FieldDescriptor CREDITCARDACCEPT = new FieldDescriptor(TABLE, "credit_card_accept", "setCreditCardAccept", 45);
	public static final FieldDescriptor VIEWRECEIPT = new FieldDescriptor(TABLE, "view_receipt", "setViewReceipt", 46);
	public static final FieldDescriptor DEFLTGOVCOD = new FieldDescriptor(TABLE, "deflt_gov_cod", "setDefltGovCod", 47);
	public static final FieldDescriptor REVERSALVERIFY = new FieldDescriptor(TABLE, "reversal_verify", "setReversalVerify", 48);
	public static final FieldDescriptor ADRELIGIBLE = new FieldDescriptor(TABLE, "adr_eligible", "setAdrEligible", 49);
	public static final FieldDescriptor POSTJDMTINTFLAG = new FieldDescriptor(TABLE, "post_jdmt_int_flag", "setPostJdmtIntFlag", 50);
	public static final FieldDescriptor STANDALONE = new FieldDescriptor(TABLE, "stand_alone", "setStandAlone", 51);
	public static final FieldDescriptor PETNMODJUDGE = new FieldDescriptor(TABLE, "petn_mod_judge", "setPetnModJudge", 52);
	public static final FieldDescriptor DEPOSITSLIPS = new FieldDescriptor(TABLE, "deposit_slips", "setDepositSlips", 53);
	public static final FieldDescriptor EMAILADDRESS = new FieldDescriptor(TABLE, "email_address", "setEmailAddress", 54);

	public ProfileBO() {
		super(new ProfileVO());
		getBaseVO().reset();   // clear out aliases
	}
	
	private String source;
	
	public ProfileBO(String source) {
		super(new ProfileVO(source));
		this.source = source;
		getBaseVO().reset();   // clear out aliases
	}
	
	public ProfileBO(BaseVO baseVO) {
		super(baseVO);
		this.source = baseVO.getSource();
	}

	public String getLocnCode() {
		return getBaseVO().getTypeString(LOCNCODE).getValue();
	}
	
	public ProfileBO setLocnCode(String locnCode) {
		setPrimaryKey(LOCNCODE, locnCode);
		return this;
	}

	public String getCourtType() {
		return getBaseVO().getTypeString(COURTTYPE).getValue();
	}
	
	public ProfileBO setCourtType(String courtType) {
		setPrimaryKey(COURTTYPE, courtType);
		return this;
	}

	public String getCourtTitle() {
		return getBaseVO().getTypeString(COURTTITLE).getValue();
	}
	
	public ProfileBO setCourtTitle(String courtTitle) {
		getBaseVO().setPropertyListValue(SET_BY_USER, COURTTITLE, courtTitle);
		return this;
	}

	public String getAddress1() {
		return getBaseVO().getTypeString(ADDRESS1).getValue();
	}
	
	public ProfileBO setAddress1(String address1) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS1, address1);
		return this;
	}

	public String getAddress2() {
		return getBaseVO().getTypeString(ADDRESS2).getValue();
	}
	
	public ProfileBO setAddress2(String address2) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADDRESS2, address2);
		return this;
	}

	public String getCity() {
		return getBaseVO().getTypeString(CITY).getValue();
	}
	
	public ProfileBO setCity(String city) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CITY, city);
		return this;
	}

	public String getStateCode() {
		return getBaseVO().getTypeString(STATECODE).getValue();
	}
	
	public ProfileBO setStateCode(String stateCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STATECODE, stateCode);
		return this;
	}

	public String getZipCode() {
		return getBaseVO().getTypeString(ZIPCODE).getValue();
	}
	
	public ProfileBO setZipCode(String zipCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ZIPCODE, zipCode);
		return this;
	}

	public String getCntyCode() {
		return getBaseVO().getTypeString(CNTYCODE).getValue();
	}
	
	public ProfileBO setCntyCode(String cntyCode) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CNTYCODE, cntyCode);
		return this;
	}

	public String getGeneralPhone() {
		return getBaseVO().getTypeString(GENERALPHONE).getValue();
	}
	
	public ProfileBO setGeneralPhone(String generalPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, GENERALPHONE, generalPhone);
		return this;
	}

	public String getCriminalPhone() {
		return getBaseVO().getTypeString(CRIMINALPHONE).getValue();
	}
	
	public ProfileBO setCriminalPhone(String criminalPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CRIMINALPHONE, criminalPhone);
		return this;
	}

	public String getCivilPhone() {
		return getBaseVO().getTypeString(CIVILPHONE).getValue();
	}
	
	public ProfileBO setCivilPhone(String civilPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CIVILPHONE, civilPhone);
		return this;
	}

	public String getJuryPhone() {
		return getBaseVO().getTypeString(JURYPHONE).getValue();
	}
	
	public ProfileBO setJuryPhone(String juryPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JURYPHONE, juryPhone);
		return this;
	}

	public String getInterpreterPhone() {
		return getBaseVO().getTypeString(INTERPRETERPHONE).getValue();
	}
	
	public ProfileBO setInterpreterPhone(String interpreterPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INTERPRETERPHONE, interpreterPhone);
		return this;
	}

	public String getDebtcollPhone() {
		return getBaseVO().getTypeString(DEBTCOLLPHONE).getValue();
	}
	
	public ProfileBO setDebtcollPhone(String debtcollPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEBTCOLLPHONE, debtcollPhone);
		return this;
	}

	public int getJudgeId() {
		return getBaseVO().getTypeInteger(JUDGEID).getValue();
	}
	
	public ProfileBO setJudgeId(Integer judgeId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JUDGEID, judgeId);
		return this;
	}

	public int getAdminId() {
		return getBaseVO().getTypeInteger(ADMINID).getValue();
	}
	
	public ProfileBO setAdminId(Integer adminId) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADMINID, adminId);
		return this;
	}

	public int getWarrCopyCnt() {
		return getBaseVO().getTypeInteger(WARRCOPYCNT).getValue();
	}
	
	public ProfileBO setWarrCopyCnt(Integer warrCopyCnt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARRCOPYCNT, warrCopyCnt);
		return this;
	}

	public int getWarrExpDeflt() {
		return getBaseVO().getTypeInteger(WARREXPDEFLT).getValue();
	}
	
	public ProfileBO setWarrExpDeflt(Integer warrExpDeflt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARREXPDEFLT, warrExpDeflt);
		return this;
	}

	public int getWarrGracePeriod() {
		return getBaseVO().getTypeInteger(WARRGRACEPERIOD).getValue();
	}
	
	public ProfileBO setWarrGracePeriod(Integer warrGracePeriod) {
		getBaseVO().setPropertyListValue(SET_BY_USER, WARRGRACEPERIOD, warrGracePeriod);
		return this;
	}

	public int getFtaGracePeriod() {
		return getBaseVO().getTypeInteger(FTAGRACEPERIOD).getValue();
	}
	
	public ProfileBO setFtaGracePeriod(Integer ftaGracePeriod) {
		getBaseVO().setPropertyListValue(SET_BY_USER, FTAGRACEPERIOD, ftaGracePeriod);
		return this;
	}

	public int getDelinqPeriod() {
		return getBaseVO().getTypeInteger(DELINQPERIOD).getValue();
	}
	
	public ProfileBO setDelinqPeriod(Integer delinqPeriod) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DELINQPERIOD, delinqPeriod);
		return this;
	}

	public int getAltDelinqPeriod() {
		return getBaseVO().getTypeInteger(ALTDELINQPERIOD).getValue();
	}
	
	public ProfileBO setAltDelinqPeriod(Integer altDelinqPeriod) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ALTDELINQPERIOD, altDelinqPeriod);
		return this;
	}

	public BigDecimal getOverpayRetLimit() {
		return getBaseVO().getTypeBigDecimal(OVERPAYRETLIMIT).getValue();
	}
	
	public ProfileBO setOverpayRetLimit(BigDecimal overpayRetLimit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OVERPAYRETLIMIT, overpayRetLimit);
		return this;
	}
	
	public ProfileBO setOverpayRetLimit(double overpayRetLimit) {
		getBaseVO().setPropertyListValue(SET_BY_USER, OVERPAYRETLIMIT, new BigDecimal(String.valueOf(new Double(overpayRetLimit))));
		return this;
	}

	public int getJointSevNum() {
		return getBaseVO().getTypeInteger(JOINTSEVNUM).getValue();
	}
	
	public ProfileBO setJointSevNum(Integer jointSevNum) {
		getBaseVO().setPropertyListValue(SET_BY_USER, JOINTSEVNUM, jointSevNum);
		return this;
	}

	public int getMultiDayHearing() {
		return getBaseVO().getTypeInteger(MULTIDAYHEARING).getValue();
	}
	
	public ProfileBO setMultiDayHearing(Integer multiDayHearing) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MULTIDAYHEARING, multiDayHearing);
		return this;
	}

	public String getTnTcForm() {
		return getBaseVO().getTypeString(TNTCFORM).getValue();
	}
	
	public ProfileBO setTnTcForm(String tnTcForm) {
		getBaseVO().setPropertyListValue(SET_BY_USER, TNTCFORM, tnTcForm);
		return this;
	}

	public String getDefltCustodyLocn() {
		return getBaseVO().getTypeString(DEFLTCUSTODYLOCN).getValue();
	}
	
	public ProfileBO setDefltCustodyLocn(String defltCustodyLocn) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFLTCUSTODYLOCN, defltCustodyLocn);
		return this;
	}

	public String getPrintPath() {
		return getBaseVO().getTypeString(PRINTPATH).getValue();
	}
	
	public ProfileBO setPrintPath(String printPath) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PRINTPATH, printPath);
		return this;
	}

	public String getExecutePath() {
		return getBaseVO().getTypeString(EXECUTEPATH).getValue();
	}
	
	public ProfileBO setExecutePath(String executePath) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EXECUTEPATH, executePath);
		return this;
	}

	public String getSupervisorCntVer() {
		return getBaseVO().getTypeString(SUPERVISORCNTVER).getValue();
	}
	
	public ProfileBO setSupervisorCntVer(String supervisorCntVer) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SUPERVISORCNTVER, supervisorCntVer);
		return this;
	}

	public String getNcicCourtOri() {
		return getBaseVO().getTypeString(NCICCOURTORI).getValue();
	}
	
	public ProfileBO setNcicCourtOri(String ncicCourtOri) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICCOURTORI, ncicCourtOri);
		return this;
	}

	public String getNcicOriDescr() {
		return getBaseVO().getTypeString(NCICORIDESCR).getValue();
	}
	
	public ProfileBO setNcicOriDescr(String ncicOriDescr) {
		getBaseVO().setPropertyListValue(SET_BY_USER, NCICORIDESCR, ncicOriDescr);
		return this;
	}

	public String getAdaName() {
		return getBaseVO().getTypeString(ADANAME).getValue();
	}
	
	public ProfileBO setAdaName(String adaName) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADANAME, adaName);
		return this;
	}

	public String getAdaPhone() {
		return getBaseVO().getTypeString(ADAPHONE).getValue();
	}
	
	public ProfileBO setAdaPhone(String adaPhone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADAPHONE, adaPhone);
		return this;
	}

	public String getAdaAddress() {
		return getBaseVO().getTypeString(ADAADDRESS).getValue();
	}
	
	public ProfileBO setAdaAddress(String adaAddress) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADAADDRESS, adaAddress);
		return this;
	}

	public String getDefltSentTrack() {
		return getBaseVO().getTypeString(DEFLTSENTTRACK).getValue();
	}
	
	public ProfileBO setDefltSentTrack(String defltSentTrack) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFLTSENTTRACK, defltSentTrack);
		return this;
	}

	public String getChildSupportLbl() {
		return getBaseVO().getTypeString(CHILDSUPPORTLBL).getValue();
	}
	
	public ProfileBO setChildSupportLbl(String childSupportLbl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CHILDSUPPORTLBL, childSupportLbl);
		return this;
	}

	public String getCertNoticeLbl() {
		return getBaseVO().getTypeString(CERTNOTICELBL).getValue();
	}
	
	public ProfileBO setCertNoticeLbl(String certNoticeLbl) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CERTNOTICELBL, certNoticeLbl);
		return this;
	}

	public String getInstallFlag() {
		return getBaseVO().getTypeString(INSTALLFLAG).getValue();
	}
	
	public ProfileBO setInstallFlag(String installFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, INSTALLFLAG, installFlag);
		return this;
	}

	public String getDrLicPrecinct() {
		return getBaseVO().getTypeString(DRLICPRECINCT).getValue();
	}
	
	public ProfileBO setDrLicPrecinct(String drLicPrecinct) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DRLICPRECINCT, drLicPrecinct);
		return this;
	}

	public String getMediaType() {
		return getBaseVO().getTypeString(MEDIATYPE).getValue();
	}
	
	public ProfileBO setMediaType(String mediaType) {
		getBaseVO().setPropertyListValue(SET_BY_USER, MEDIATYPE, mediaType);
		return this;
	}

	public String getSurcharge() {
		return getBaseVO().getTypeString(SURCHARGE).getValue();
	}
	
	public ProfileBO setSurcharge(String surcharge) {
		getBaseVO().setPropertyListValue(SET_BY_USER, SURCHARGE, surcharge);
		return this;
	}

	public int getReceiptCopies() {
		return getBaseVO().getTypeInteger(RECEIPTCOPIES).getValue();
	}
	
	public ProfileBO setReceiptCopies(Integer receiptCopies) {
		getBaseVO().setPropertyListValue(SET_BY_USER, RECEIPTCOPIES, receiptCopies);
		return this;
	}

	public String getCacSite() {
		return getBaseVO().getTypeString(CACSITE).getValue();
	}
	
	public ProfileBO setCacSite(String cacSite) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CACSITE, cacSite);
		return this;
	}

	public String getCreditCardAccept() {
		return getBaseVO().getTypeString(CREDITCARDACCEPT).getValue();
	}
	
	public ProfileBO setCreditCardAccept(String creditCardAccept) {
		getBaseVO().setPropertyListValue(SET_BY_USER, CREDITCARDACCEPT, creditCardAccept);
		return this;
	}

	public String getViewReceipt() {
		return getBaseVO().getTypeString(VIEWRECEIPT).getValue();
	}
	
	public ProfileBO setViewReceipt(String viewReceipt) {
		getBaseVO().setPropertyListValue(SET_BY_USER, VIEWRECEIPT, viewReceipt);
		return this;
	}

	public String getDefltGovCod() {
		return getBaseVO().getTypeString(DEFLTGOVCOD).getValue();
	}
	
	public ProfileBO setDefltGovCod(String defltGovCod) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEFLTGOVCOD, defltGovCod);
		return this;
	}

	public String getReversalVerify() {
		return getBaseVO().getTypeString(REVERSALVERIFY).getValue();
	}
	
	public ProfileBO setReversalVerify(String reversalVerify) {
		getBaseVO().setPropertyListValue(SET_BY_USER, REVERSALVERIFY, reversalVerify);
		return this;
	}

	public String getAdrEligible() {
		return getBaseVO().getTypeString(ADRELIGIBLE).getValue();
	}
	
	public ProfileBO setAdrEligible(String adrEligible) {
		getBaseVO().setPropertyListValue(SET_BY_USER, ADRELIGIBLE, adrEligible);
		return this;
	}

	public String getPostJdmtIntFlag() {
		return getBaseVO().getTypeString(POSTJDMTINTFLAG).getValue();
	}
	
	public ProfileBO setPostJdmtIntFlag(String postJdmtIntFlag) {
		getBaseVO().setPropertyListValue(SET_BY_USER, POSTJDMTINTFLAG, postJdmtIntFlag);
		return this;
	}

	public String getStandAlone() {
		return getBaseVO().getTypeString(STANDALONE).getValue();
	}
	
	public ProfileBO setStandAlone(String standAlone) {
		getBaseVO().setPropertyListValue(SET_BY_USER, STANDALONE, standAlone);
		return this;
	}

	public String getPetnModJudge() {
		return getBaseVO().getTypeString(PETNMODJUDGE).getValue();
	}
	
	public ProfileBO setPetnModJudge(String petnModJudge) {
		getBaseVO().setPropertyListValue(SET_BY_USER, PETNMODJUDGE, petnModJudge);
		return this;
	}

	public String getDepositSlips() {
		return getBaseVO().getTypeString(DEPOSITSLIPS).getValue();
	}
	
	public ProfileBO setDepositSlips(String depositSlips) {
		getBaseVO().setPropertyListValue(SET_BY_USER, DEPOSITSLIPS, depositSlips);
		return this;
	}

	public String getEmailAddress() {
		return getBaseVO().getTypeString(EMAILADDRESS).getValue();
	}
	
	public ProfileBO setEmailAddress(String emailAddress) {
		getBaseVO().setPropertyListValue(SET_BY_USER, EMAILADDRESS, emailAddress);
		return this;
	}
	
	public ProfileBO find(FieldDescriptor... specificFields) throws Exception {
		if (getSearchDispatcher().isComplexSearch()) {
			List<ProfileBO> results = search(specificFields);
			return searchDispatcher.returnNull() ? null : results.size() > 0 ? results.get(0) : new ProfileBO(source);
		} else {
			findBaseVO(getNativeDescriptor(), specificFields);
			return getBaseVO().returnNull() ? null : new ProfileBO(getBaseVO());
		}
	}
	
	public ProfileBO setReturnNull(boolean returnNull) {
		getBaseVO().setReturnNull(returnNull);
		return this;
	}
	
	public ProfileBO insert(FieldDescriptor... specificFields) throws Exception {
		return new ProfileBO(super.insert(getNativeDescriptor(), specificFields));
	}
	
	public ProfileBO update(FieldDescriptor... specificFields) throws Exception {
		return new ProfileBO(super.update(getNativeDescriptor(), specificFields));
	}
	
	public ProfileBO delete() throws Exception {
		return new ProfileBO(super.delete(getNativeDescriptor()));
	}
	
	public ProfileBO includeFields(FieldDescriptor... specificFields) {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public ProfileBO set(FieldDescriptor... specificFields) throws Exception {
		getBaseVO().includeFields(specificFields);
		return this;
	}
	
	public ProfileBO set(FieldDescriptor specificField, Object boExtendedClass) throws Exception {
		getBaseVO().includeFields(new FieldDescriptor[] { specificField });
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public ProfileBO includeFields(FieldType... fields) {
		getBaseVO().includeFields(fields);
		return this;
	}
	
	public ProfileBO limit(int limit) {
		getBaseVO().setLimit(limit);
		return this;
	}
	
	public ProfileBO offset(int offset) {
		getBaseVO().setOffset(offset);
		return this;
	}
	
	public ProfileBO groupBy(FieldDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public ProfileBO groupBy(GroupByCustomDescriptor... groupBy) {
		setGroup(groupBy);
		return this;
	}
	
	public ProfileBO groupBy(int... ordinal) {
		setGroup(ordinal);
		return this;
	}
	
	public ProfileBO orderBy(SortCustomDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ProfileBO orderBy(FieldDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ProfileBO orderBy(FieldDescriptor orderBy, DirectionType directionType) {
		setOrder(orderBy, directionType);
		return this;
	}
	
	public ProfileBO orderBy(String orderBy, DirectionType... directionType) {
		setOrder(new SortCustomDescriptor(orderBy + (directionType != null ? " " + directionType[0].toString() : "")));
		return this;
	}
	
	public ProfileBO orderBy(int... ordinal) {
		setOrder(ordinal);
		return this;
	}
	
	public ProfileBO orderBy(SortDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ProfileBO orderBy(SortOrdinalDescriptor... orderBy) {
		setOrder(orderBy);
		return this;
	}
	
	public ProfileBO where(Object... findBy) {
		setFinder(new FindDescriptor(new Expression(findBy)));
		return this;
	}
	
	public ProfileBO where(FieldDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public ProfileBO where(Expression... findBy) {
		for (Expression expressionDescriptor : findBy)
			setFinder(new FindDescriptor(expressionDescriptor));
		return this;
	}
	
	public ProfileBO where(FieldDescriptor findBy, Object value) {
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
	
	public ProfileBO where(FindDescriptor... findBy) {
		setFinder(findBy);
		return this;
	}
	
	public ProfileBO where(Expression expressionDescriptor) {
		setFinder(expressionDescriptor);
		return this;
	}
	
	public ProfileBO where(FieldDescriptor fieldDescriptor, Exp exp) {
		setFinder(fieldDescriptor, exp);
		return this;
	}
	
	public ProfileBO setFieldOperations(FieldOperationDescriptor... fieldOperations) {
		setFieldOperators(fieldOperations);
		return this;
	}
	
	public ProfileBO count(FieldDescriptor fieldDescriptor, Expression... expressions) {
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
	
	public ProfileBO avg(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.AVG.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getAvg() throws Exception {
		return get(FieldOperationType.AVG.toString());
	}
	
	public ProfileBO min(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MIN.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMin() throws Exception {
		return get(FieldOperationType.MIN.toString());
	}
	
	public ProfileBO max(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.MAX.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getMax() throws Exception {
		return get(FieldOperationType.MAX.toString());
	}
	
	public ProfileBO sum(FieldDescriptor fieldDescriptor, Expression... expressions) {
		addFieldOperation(FieldOperationType.SUM.toString(), fieldDescriptor, expressions);
		return this;
	}
	
	public Object getSum() throws Exception {
		return get(FieldOperationType.SUM.toString());
	}
	
	public ProfileBO addFieldOperation(String operation, FieldDescriptor fieldDescriptor, Expression... expressions) {
		getBaseVO().addFieldOperation(operation, fieldDescriptor, expressions);
		return this;
	}
	
	public ProfileBO toString(int value) {
		value = TestingContainer.getToStringOverride(value, SYSTEM);
		this.getSearchDispatcher().getSearchDispatcherParameters().setToString(value);
		getBaseVO().setToString(value);
		return this;
	}
	
	public ProfileBO setDistinct() {
		getBaseVO().setDistinct();
		return this;
	}
	
	public ProfileBO setUnique() {
		getBaseVO().setUnique();
		return this;
	}
	
	public ProfileBO setOuter() {
		getBaseVO().setOuter();
		return this;
	}
	
	public ProfileBO setHaving(String value) {
		getBaseVO().setHaving(value);
		return this;
	}
	
	public SearchDispatcher getSearchDispatcher() {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);  // search performed without any additional tables
	
		return searchDispatcher;
	}
	
	public ProfileBO includeTables(BaseBO... baseBOs) {
		BaseBO[] BOs = new BaseBO[baseBOs.length + 1];
		BOs[0] = this;
		for (int i=1; i <= baseBOs.length; i++) {
			BOs[i] = baseBOs[i-1];
		}
		getSearchDispatcher().getSearchDispatcherParameters().addSearchDescriptors(BOs);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProfileBO> search(FieldDescriptor... fieldDescriptors) throws Exception {
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
	
	public ProfileBO addForeignKey(ForeignKeyDescriptor... foreignKeyDescriptors) {
		getSearchDispatcher().addForeignKey(foreignKeyDescriptors);
		return this;
	}
	
	public ProfileBO addForeignKey(FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addForeignKey(fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public ProfileBO addForeignKey(TableAndFieldDescriptor tableAndFieldDescriptor1, TableAndFieldDescriptor tableAndFieldDescriptor2) {
		getSearchDispatcher().addForeignKey(tableAndFieldDescriptor1, tableAndFieldDescriptor2);
		return this;
	}
	
	public ProfileBO addSearchWrapDescriptor(String before, String after, FieldDescriptor... specificFields) {
		getSearchDispatcher().getSearchDispatcherParameters().addSearchWrapDescriptor(before, after, specificFields);
		return this;
	}
	
	public ProfileBO setOuter(BaseBO... baseBOs) {
		getSearchDispatcher().getSearchDispatcherParameters().addMultiOuterDescriptor(baseBOs);
		return this;
	}
	
	public ProfileBO setResultContainer(Object object) {
		getSearchDispatcher().getSearchDispatcherParameters().setResultContainer(object);
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public List getResults() throws Exception {
		return getSearchDispatcher().getResults();
	}
	
	public ProfileBO addUnionDescriptors(UnionDescriptor... unionDescriptors) {
		getSearchDispatcher().addUnionDescriptors(unionDescriptors);
		return this;
	}
	
	public ProfileBO union(UnionType unionType, BaseBO baseBO) {
		getSearchDispatcher().addUnionDescriptors(new UnionDescriptor(baseBO).setSelect(baseBO.getBaseVO().getFields()).setUnionType(unionType));
		return this;
	}
	
	public ProfileBO union(BaseBO baseBO) {
		union(UnionType.UNION, baseBO);
		return this;
	}
	
	public ProfileBO addJoin(JoinDescriptor... joinDescriptors) {
		getSearchDispatcher().addJoin(joinDescriptors);
		return this;
	}
	
	public ProfileBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, Expression... expression) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, expression);
		return this;
	}
	
	public ProfileBO addJoin(JoinType joinType, BaseBO baseBO, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor joinFindDescriptors) {
		includeTables(baseBO);
		getSearchDispatcher().addJoin(joinType, baseBO, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public ProfileBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2);
		return this;
	}
	
	public ProfileBO addJoin(JoinType joinType, String tableName, FieldDescriptor fieldDescriptor1, FieldDescriptor fieldDescriptor2, JoinFindDescriptor... joinFindDescriptors) {
		getSearchDispatcher().addJoin(joinType, tableName, fieldDescriptor1, fieldDescriptor2, joinFindDescriptors);
		return this;
	}
	
	public ProfileBO setJoin(List<JoinDescriptor> joinDescriptors) {
		getSearchDispatcher().setJoin(joinDescriptors);
		return this;
	}
	
	public ProfileBO addWhereDescriptors(WhereDescriptor... whereDescriptors) {
		for (int i=0; i < whereDescriptors.length; i++) 
			getBaseVO().addWhereDescriptor(whereDescriptors[i]);
		
		return this;
	}
	
	public ProfileBO setUseConnection(Connection connection) {
		if (searchDispatcher != null)
			getSearchDispatcher().getSearchDispatcherParameters().getBaseVO().setConnection(connection);
	
		getBaseVO().setConnection(connection);
		return this;
	}
	
	public ProfileBO setDateFormat(FieldDescriptor fieldDescriptor, DateFormat dateFormat) {
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
	
	public ProfileBO overrideTable(String tableName) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()));
		return this;
	}
	
	public ProfileBO overrideTable(String tableName, String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(tableName, getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public ProfileBO setTableAlias(String tableAlias) {
		getBaseVO().setTableDescriptor(new TableDescriptor(getBaseVO().getTableDescriptor().getTableName(), getBaseVO().getTableDescriptor().getClassName()).setTableAlias(tableAlias));
		return this;
	}
	
	public String getTableAlias() {
		return getBaseVO().getTableDescriptor().getTableAlias();
	}
	
	public ProfileBO as(String tableAlias) {
		setTableAlias(tableAlias);
		return this;
	}
	
	public ProfileBO setFieldAlias(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public ProfileBO as(FieldDescriptor fieldDescriptor, String alias) {
		getBaseVO().setFieldAlias(fieldDescriptor, alias, getTableAlias());
		return this;
	}
	
	public ProfileBO goNaked(String statement) {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(statement);
		return this;
	}
	
	public ProfileBO goNative(String propertiesFilename, String propertyName) throws Exception {
		if (searchDispatcher == null)
			searchDispatcher = new SearchDispatcher(this);
	
		String sqlStatement = PropertyFileHelper.getProperty(propertiesFilename, propertyName);
		if (TextUtil.isEmpty(sqlStatement))
			throw new Exception(".goNative ProfileBO Exception: sql statement is empty - " + propertiesFilename + "  " + propertyName);
	
		getSearchDispatcher().getSearchDispatcherParameters().setNativeDescriptor(sqlStatement);
		return this;
	}
	
	public ProfileBO setQuestionMarks(Object... values) {
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
	public ProfileBO setPrimaryKey(FieldDescriptor field, Object value) {
		overridePrimaryKey(field, value);
		return this;
	}
	
	public ProfileBO setIsolationDirtyRead(boolean isolationDirtyRead) {
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
	public ProfileBO setPropertyList(List<FieldType> propertyList) {
		getBaseVO().setPropertyList(propertyList);
		return this;
	}
	
	public HashMap<String, Object> getAttributes() {
		return getBaseVO().getAttributes();
	}
	
	public ProfileBO setAttributes(HashMap<String, Object> attributes) {
		getBaseVO().setAttributes(attributes);
		return this;
	}
	
	public ProfileBO set(String key, Object value) {
		getBaseVO().setAttribute(key, value);
		return this;
	}
	
	public ProfileBO select(Object boExtendedClass) {
		getBaseVO().setFrom(new FromDescriptor(boExtendedClass));
		return this;
	}
	
	public ProfileBO into(Object boExtendedClass) {
		getBaseVO().setInto(new IntoDescriptor(boExtendedClass));
		return this;
	}
	
	public ProfileBO setAllowDuplicateQuestionMarks(boolean allowDuplicateQuestionMarks) {
		getSearchDispatcher().getSearchDispatcherParameters().setAllowDuplicateQuestionMarks(allowDuplicateQuestionMarks);
		return this;
	}
	
	public ProfileBO useServerName(String serverName) {
		getBaseVO().useServerName(serverName);
		return this;
	}
	
	public ProfileBO useDatabaseName(String databaseName) {
		getBaseVO().useDatabaseName(databaseName);
		return this;
	}
	
	public ProfileBO setAsZero(FieldDescriptor field) {
		super.setAsZero(field);
		return this;
	}
	
	public ProfileBO setAsEmpty(FieldDescriptor field) {
		super.setAsEmpty(field);
		return this;
	}
	
	public ProfileBO setAsNull(FieldDescriptor field) {
		super.setAsNull(field);
		return this;
	}
	
	@Override
	public String toString() {
		return getBaseVO().toString();
	}
	

}