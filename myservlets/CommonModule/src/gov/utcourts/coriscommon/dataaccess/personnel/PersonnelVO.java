package gov.utcourts.coriscommon.dataaccess.personnel;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PersonnelVO extends BaseVO { 

	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PersonnelBO.USERIDSRL.clear()).addForeignKey("acct_adj_charge","userid_srl",false).addForeignKey("acct_waiver","userid_srl",false).addForeignKey("atty_present","judge_id",false).addForeignKey("auto_expungement_order","presiding_judge_id",false).addForeignKey("auto_expungement_signing_judge","judge_id",false).addForeignKey("bank_adj","adj_userid",false).addForeignKey("bank_adj","recon_userid",true).addForeignKey("bindover_judge","userid_srl",false).addForeignKey("calendar","judge_id",true).addForeignKey("case_me","clerk_id",true).addForeignKey("case_type_assn","judge_comm_id",false).addForeignKey("case_warning","userid_srl",true).addForeignKey("cashier","userid_srl",false).addForeignKey("cashier_cnt","userid_srl",false).addForeignKey("check_batch","cancel_userid",true).addForeignKey("check_batch","create_userid",true).addForeignKey("check_detl","recon_userid",true).addForeignKey("court_division","judge_comm_id",false).addForeignKey("custody_history","userid_srl",true).addForeignKey("data_changes","userid_srl",false).addForeignKey("deposit_slip","recon_userid",true).addForeignKey("deposit_slip_ach","recon_userid",true).addForeignKey("deposit_slip_card","recon_userid",true).addForeignKey("doc_issue","judge_comm_id",true).addForeignKey("doc_order","judge_comm_id",false).addForeignKey("form","clerk_id",true).addForeignKey("fta_history","userid_srl",true).addForeignKey("hist_me","userid_srl",false).addForeignKey("incourt_clerk_text","userid_srl",false).addForeignKey("judge_away","cancel_userid_srl",true).addForeignKey("judge_away","judge_id",false).addForeignKey("judge_hist","clerk_id",false).addForeignKey("judge_hist","judge_id",false).addForeignKey("judge_type_profile","userid_srl",true).addForeignKey("kase","assn_judge_id",true).addForeignKey("kase","disp_id",true).addForeignKey("kase","assn_comm_id",true).addForeignKey("mail_lbl","userid_srl",true).addForeignKey("mediation","clerk_id",true).addForeignKey("mediation","judge_comm_id",true).addForeignKey("osdc_acct_history","osdc_create_userid",false).addForeignKey("osdc_acct_history","osdc_recall_userid",true).addForeignKey("pers_applic","userid_srl",true).addForeignKey("personnel","reporter_id",true).addForeignKey("personnel_printer","userid_srl",false).addForeignKey("prelim_judge","userid_srl",false).addForeignKey("previous_cases","userid_srl",true).addForeignKey("print_idx","userid_srl",false).addForeignKey("pseudo_judge","userid_srl",false).addForeignKey("senior_judges","userid_srl",false).addForeignKey("sent_fulfill","userid_srl",false).addForeignKey("tracking","clerk_id",false).addForeignKey("tracking","end_userid_srl",true).addForeignKey("trans","supervisor_id",true).addForeignKey("trans","userid_srl",false).addForeignKey("trans_creditcard","recon_userid",true).addForeignKey("warrant","issue_clerk_id",true).addForeignKey("warrant","issue_judge_id",true).addForeignKey("warrant","recall_userid_srl",true).addForeignKey("warrant","order_userid_srl",true).addForeignKey("warrant_retransmission","userid_srl",true).addForeignKey("work_cal_case","judge_id",true).setAsPrimaryKey();
	private TypeString lastName = new TypeString("last_name").setFieldDescriptor(PersonnelBO.LASTNAME.clear());
	private TypeString firstName = new TypeString("first_name").setFieldDescriptor(PersonnelBO.FIRSTNAME.clear()).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(PersonnelBO.TYPE.clear());
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PersonnelBO.COURTTYPE.clear()).addForeignKey("profile","court_type",false);
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PersonnelBO.LOCNCODE.clear()).addForeignKey("profile","locn_code",false);
	private TypeString logname = new TypeString("logname").setFieldDescriptor(PersonnelBO.LOGNAME.clear());
	private TypeString emailAddress = new TypeString("email_address").setFieldDescriptor(PersonnelBO.EMAILADDRESS.clear()).setNullable();
	private TypeString userPasswd = new TypeString("user_passwd").setFieldDescriptor(PersonnelBO.USERPASSWD.clear()).setNullable();
	private TypeInteger reporterId = new TypeInteger("reporter_id").setFieldDescriptor(PersonnelBO.REPORTERID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString applicProfil = new TypeString("applic_profil").setFieldDescriptor(PersonnelBO.APPLICPROFIL.clear()).setNullable();
	private TypeString defltLogin = new TypeString("deflt_login").setFieldDescriptor(PersonnelBO.DEFLTLOGIN.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(PersonnelBO.REMOVEDFLAG.clear());
	private TypeInteger sortOrder = new TypeInteger("sort_order").setFieldDescriptor(PersonnelBO.SORTORDER.clear()).setNullable();
	private TypeDate passwordExpDate = new TypeDate("password_exp_date").setFieldDescriptor(PersonnelBO.PASSWORDEXPDATE.clear()).setNullable();
	private TypeInteger passwordAttempts = new TypeInteger("password_attempts").setFieldDescriptor(PersonnelBO.PASSWORDATTEMPTS.clear()).setNullable();
	private TypeDate lastLogin = new TypeDate("last_login").setFieldDescriptor(PersonnelBO.LASTLOGIN.clear()).setNullable();
	private TypeInteger graceLoginsLeft = new TypeInteger("grace_logins_left").setFieldDescriptor(PersonnelBO.GRACELOGINSLEFT.clear()).setNullable();
	private TypeString organization = new TypeString("organization").setFieldDescriptor(PersonnelBO.ORGANIZATION.clear()).setNullable();

	public PersonnelVO() {
		super(PersonnelBO.TABLE, PersonnelBO.SYSTEM, PersonnelBO.CORIS_DISTRICT_DB.setSource("D"), PersonnelBO.CORIS_JUSTICE_DB.setSource("J"), PersonnelBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersonnelBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PersonnelVO(String source) {
		super(PersonnelBO.TABLE, PersonnelBO.SYSTEM, PersonnelBO.CORIS_DISTRICT_DB.setSource("D"), PersonnelBO.CORIS_JUSTICE_DB.setSource("J"), PersonnelBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PersonnelBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(lastName);
		this.getPropertyList().add(firstName);
		this.getPropertyList().add(type);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(logname);
		this.getPropertyList().add(emailAddress);
		this.getPropertyList().add(userPasswd);
		this.getPropertyList().add(reporterId);
		this.getPropertyList().add(applicProfil);
		this.getPropertyList().add(defltLogin);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(sortOrder);
		this.getPropertyList().add(passwordExpDate);
		this.getPropertyList().add(passwordAttempts);
		this.getPropertyList().add(lastLogin);
		this.getPropertyList().add(graceLoginsLeft);
		this.getPropertyList().add(organization);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PersonnelBO.PASSWORDEXPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PersonnelBO.PASSWORDEXPDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PersonnelBO.LASTLOGIN.getPosition())).setDateFormat((DateFormat) super.getAttribute(PersonnelBO.LASTLOGIN.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}