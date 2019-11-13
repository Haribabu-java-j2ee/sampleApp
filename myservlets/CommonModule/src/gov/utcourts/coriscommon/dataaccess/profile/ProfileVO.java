package gov.utcourts.coriscommon.dataaccess.profile;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;


// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class ProfileVO extends BaseVO { 

	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(ProfileBO.LOCNCODE.clear()).addForeignKey("auto_expungement_signing_judge","locn_code",false).addForeignKey("bank_acct","locn_code",false).addForeignKey("case_type_profile","locn_code",false).addForeignKey("check_batch","locn_code",false).addForeignKey("court_process","locn_code",true).addForeignKey("courtroom","locn_code",false).addForeignKey("default_rejection","locn_code",true).addForeignKey("failed_ecitation","locn_code",false).addForeignKey("interpreter","locn_code",false).addForeignKey("kase","locn_code",true).addForeignKey("next_case_num","locn_code",false).addForeignKey("odr_eligible","locn_code",false).addForeignKey("offense_override","locn_code",false).addForeignKey("other_doc_title","locn_code",false).addForeignKey("personnel","locn_code",false).addForeignKey("post_locn","locn_code",false).addForeignKey("pre_cal_note","locn_code",true).addForeignKey("print_idx","locn_code",false).addForeignKey("reason_descr","locn_code",true).addForeignKey("warning","locn_code",true).addForeignKey("zip_code_filing_locn","filing_locn_code",false).setAsPrimaryKey();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(ProfileBO.COURTTYPE.clear()).addForeignKey("auto_expungement_signing_judge","court_type",false).addForeignKey("bank_acct","court_type",false).addForeignKey("case_type_profile","court_type",false).addForeignKey("check_batch","court_type",false).addForeignKey("court_process","court_type",true).addForeignKey("courtroom","court_type",false).addForeignKey("default_rejection","court_type",true).addForeignKey("failed_ecitation","court_type",false).addForeignKey("interpreter","court_type",true).addForeignKey("kase","court_type",true).addForeignKey("next_case_num","court_type",false).addForeignKey("odr_eligible","court_type",false).addForeignKey("offense_override","court_type",false).addForeignKey("other_doc_title","court_type",false).addForeignKey("personnel","court_type",false).addForeignKey("post_locn","court_type",false).addForeignKey("pre_cal_note","court_type",true).addForeignKey("print_idx","court_type",false).addForeignKey("reason_descr","court_type",true).addForeignKey("warning","court_type",true).addForeignKey("zip_code_filing_locn","filing_court_type",false).setAsPrimaryKey();
	private TypeString courtTitle = new TypeString("court_title").setFieldDescriptor(ProfileBO.COURTTITLE.clear());
	private TypeString address1 = new TypeString("address_1").setFieldDescriptor(ProfileBO.ADDRESS1.clear()).setNullable();
	private TypeString address2 = new TypeString("address_2").setFieldDescriptor(ProfileBO.ADDRESS2.clear()).setNullable();
	private TypeString city = new TypeString("city").setFieldDescriptor(ProfileBO.CITY.clear()).setNullable();
	private TypeString stateCode = new TypeString("state_code").setFieldDescriptor(ProfileBO.STATECODE.clear()).setNullable();
	private TypeString zipCode = new TypeString("zip_code").setFieldDescriptor(ProfileBO.ZIPCODE.clear()).setNullable();
	private TypeString cntyCode = new TypeString("cnty_code").setFieldDescriptor(ProfileBO.CNTYCODE.clear()).addForeignKey("county","cnty_code",false).setNullable();
	private TypeString generalPhone = new TypeString("general_phone").setFieldDescriptor(ProfileBO.GENERALPHONE.clear()).setNullable();
	private TypeString criminalPhone = new TypeString("criminal_phone").setFieldDescriptor(ProfileBO.CRIMINALPHONE.clear()).setNullable();
	private TypeString civilPhone = new TypeString("civil_phone").setFieldDescriptor(ProfileBO.CIVILPHONE.clear()).setNullable();
	private TypeString juryPhone = new TypeString("jury_phone").setFieldDescriptor(ProfileBO.JURYPHONE.clear()).setNullable();
	private TypeString interpreterPhone = new TypeString("interpreter_phone").setFieldDescriptor(ProfileBO.INTERPRETERPHONE.clear()).setNullable();
	private TypeString debtcollPhone = new TypeString("debtcoll_phone").setFieldDescriptor(ProfileBO.DEBTCOLLPHONE.clear()).setNullable();
	private TypeInteger judgeId = new TypeInteger("judge_id").setFieldDescriptor(ProfileBO.JUDGEID.clear()).setNullable();
	private TypeInteger adminId = new TypeInteger("admin_id").setFieldDescriptor(ProfileBO.ADMINID.clear()).setNullable();
	private TypeInteger warrCopyCnt = new TypeInteger("warr_copy_cnt").setFieldDescriptor(ProfileBO.WARRCOPYCNT.clear()).setNullable();
	private TypeInteger warrExpDeflt = new TypeInteger("warr_exp_deflt").setFieldDescriptor(ProfileBO.WARREXPDEFLT.clear()).setNullable();
	private TypeInteger warrGracePeriod = new TypeInteger("warr_grace_period").setFieldDescriptor(ProfileBO.WARRGRACEPERIOD.clear()).setNullable();
	private TypeInteger ftaGracePeriod = new TypeInteger("fta_grace_period").setFieldDescriptor(ProfileBO.FTAGRACEPERIOD.clear()).setNullable();
	private TypeInteger delinqPeriod = new TypeInteger("delinq_period").setFieldDescriptor(ProfileBO.DELINQPERIOD.clear()).setNullable();
	private TypeInteger altDelinqPeriod = new TypeInteger("alt_delinq_period").setFieldDescriptor(ProfileBO.ALTDELINQPERIOD.clear());
	private TypeBigDecimal overpayRetLimit = new TypeBigDecimal("overpay_ret_limit").setFieldDescriptor(ProfileBO.OVERPAYRETLIMIT.clear()).setNullable();
	private TypeInteger jointSevNum = new TypeInteger("joint_sev_num").setFieldDescriptor(ProfileBO.JOINTSEVNUM.clear()).setNullable();
	private TypeInteger multiDayHearing = new TypeInteger("multi_day_hearing").setFieldDescriptor(ProfileBO.MULTIDAYHEARING.clear());
	private TypeString tnTcForm = new TypeString("tn_tc_form").setFieldDescriptor(ProfileBO.TNTCFORM.clear());
	private TypeString defltCustodyLocn = new TypeString("deflt_custody_locn").setFieldDescriptor(ProfileBO.DEFLTCUSTODYLOCN.clear()).setNullable();
	private TypeString printPath = new TypeString("print_path").setFieldDescriptor(ProfileBO.PRINTPATH.clear()).setNullable();
	private TypeString executePath = new TypeString("execute_path").setFieldDescriptor(ProfileBO.EXECUTEPATH.clear()).setNullable();
	private TypeString supervisorCntVer = new TypeString("supervisor_cnt_ver").setFieldDescriptor(ProfileBO.SUPERVISORCNTVER.clear());
	private TypeString ncicCourtOri = new TypeString("ncic_court_ori").setFieldDescriptor(ProfileBO.NCICCOURTORI.clear()).setNullable();
	private TypeString ncicOriDescr = new TypeString("ncic_ori_descr").setFieldDescriptor(ProfileBO.NCICORIDESCR.clear()).setNullable();
	private TypeString adaName = new TypeString("ada_name").setFieldDescriptor(ProfileBO.ADANAME.clear()).setNullable();
	private TypeString adaPhone = new TypeString("ada_phone").setFieldDescriptor(ProfileBO.ADAPHONE.clear()).setNullable();
	private TypeString adaAddress = new TypeString("ada_address").setFieldDescriptor(ProfileBO.ADAADDRESS.clear()).setNullable();
	private TypeString defltSentTrack = new TypeString("deflt_sent_track").setFieldDescriptor(ProfileBO.DEFLTSENTTRACK.clear());
	private TypeString childSupportLbl = new TypeString("child_support_lbl").setFieldDescriptor(ProfileBO.CHILDSUPPORTLBL.clear());
	private TypeString certNoticeLbl = new TypeString("cert_notice_lbl").setFieldDescriptor(ProfileBO.CERTNOTICELBL.clear());
	private TypeString installFlag = new TypeString("install_flag").setFieldDescriptor(ProfileBO.INSTALLFLAG.clear());
	private TypeString drLicPrecinct = new TypeString("dr_lic_precinct").setFieldDescriptor(ProfileBO.DRLICPRECINCT.clear());
	private TypeString mediaType = new TypeString("media_type").setFieldDescriptor(ProfileBO.MEDIATYPE.clear()).setNullable();
	private TypeString surcharge = new TypeString("surcharge").setFieldDescriptor(ProfileBO.SURCHARGE.clear()).setNullable();
	private TypeInteger receiptCopies = new TypeInteger("receipt_copies").setFieldDescriptor(ProfileBO.RECEIPTCOPIES.clear()).setNullable();
	private TypeString cacSite = new TypeString("cac_site").setFieldDescriptor(ProfileBO.CACSITE.clear()).setNullable();
	private TypeString creditCardAccept = new TypeString("credit_card_accept").setFieldDescriptor(ProfileBO.CREDITCARDACCEPT.clear()).setNullable();
	private TypeString viewReceipt = new TypeString("view_receipt").setFieldDescriptor(ProfileBO.VIEWRECEIPT.clear()).setNullable();
	private TypeString defltGovCod = new TypeString("deflt_gov_cod").setFieldDescriptor(ProfileBO.DEFLTGOVCOD.clear()).setNullable();
	private TypeString reversalVerify = new TypeString("reversal_verify").setFieldDescriptor(ProfileBO.REVERSALVERIFY.clear()).setNullable();
	private TypeString adrEligible = new TypeString("adr_eligible").setFieldDescriptor(ProfileBO.ADRELIGIBLE.clear()).setNullable();
	private TypeString postJdmtIntFlag = new TypeString("post_jdmt_int_flag").setFieldDescriptor(ProfileBO.POSTJDMTINTFLAG.clear()).setNullable();
	private TypeString standAlone = new TypeString("stand_alone").setFieldDescriptor(ProfileBO.STANDALONE.clear());
	private TypeString petnModJudge = new TypeString("petn_mod_judge").setFieldDescriptor(ProfileBO.PETNMODJUDGE.clear());
	private TypeString depositSlips = new TypeString("deposit_slips").setFieldDescriptor(ProfileBO.DEPOSITSLIPS.clear());
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(ProfileBO.EMAILADDRESS.clear());

	public ProfileVO() {
		super(ProfileBO.TABLE, ProfileBO.SYSTEM, ProfileBO.CORIS_DISTRICT_DB.setSource("D"), ProfileBO.CORIS_JUSTICE_DB.setSource("J"), ProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public ProfileVO(String source) {
		super(ProfileBO.TABLE, ProfileBO.SYSTEM, ProfileBO.CORIS_DISTRICT_DB.setSource("D"), ProfileBO.CORIS_JUSTICE_DB.setSource("J"), ProfileBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), ProfileBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(courtTitle);
		this.getPropertyList().add(address1);
		this.getPropertyList().add(address2);
		this.getPropertyList().add(city);
		this.getPropertyList().add(stateCode);
		this.getPropertyList().add(zipCode);
		this.getPropertyList().add(cntyCode);
		this.getPropertyList().add(generalPhone);
		this.getPropertyList().add(criminalPhone);
		this.getPropertyList().add(civilPhone);
		this.getPropertyList().add(juryPhone);
		this.getPropertyList().add(interpreterPhone);
		this.getPropertyList().add(debtcollPhone);
		this.getPropertyList().add(judgeId);
		this.getPropertyList().add(adminId);
		this.getPropertyList().add(warrCopyCnt);
		this.getPropertyList().add(warrExpDeflt);
		this.getPropertyList().add(warrGracePeriod);
		this.getPropertyList().add(ftaGracePeriod);
		this.getPropertyList().add(delinqPeriod);
		this.getPropertyList().add(altDelinqPeriod);
		this.getPropertyList().add(overpayRetLimit);
		this.getPropertyList().add(jointSevNum);
		this.getPropertyList().add(multiDayHearing);
		this.getPropertyList().add(tnTcForm);
		this.getPropertyList().add(defltCustodyLocn);
		this.getPropertyList().add(printPath);
		this.getPropertyList().add(executePath);
		this.getPropertyList().add(supervisorCntVer);
		this.getPropertyList().add(ncicCourtOri);
		this.getPropertyList().add(ncicOriDescr);
		this.getPropertyList().add(adaName);
		this.getPropertyList().add(adaPhone);
		this.getPropertyList().add(adaAddress);
		this.getPropertyList().add(defltSentTrack);
		this.getPropertyList().add(childSupportLbl);
		this.getPropertyList().add(certNoticeLbl);
		this.getPropertyList().add(installFlag);
		this.getPropertyList().add(drLicPrecinct);
		this.getPropertyList().add(mediaType);
		this.getPropertyList().add(surcharge);
		this.getPropertyList().add(receiptCopies);
		this.getPropertyList().add(cacSite);
		this.getPropertyList().add(creditCardAccept);
		this.getPropertyList().add(viewReceipt);
		this.getPropertyList().add(defltGovCod);
		this.getPropertyList().add(reversalVerify);
		this.getPropertyList().add(adrEligible);
		this.getPropertyList().add(postJdmtIntFlag);
		this.getPropertyList().add(standAlone);
		this.getPropertyList().add(petnModJudge);
		this.getPropertyList().add(depositSlips);
		this.getPropertyList().add(emailAddress);
	}

}