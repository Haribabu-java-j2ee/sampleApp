package gov.utcourts.coriscommon.dataaccess.swarrant;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SWarrantVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SWarrantBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SWarrantBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SWarrantBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SWarrantBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SWarrantBO.SOPERATION.clear()).setNullable();
	private TypeInteger warrNum = new TypeInteger("warr_num").setFieldDescriptor(SWarrantBO.WARRNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SWarrantBO.PARTYNUM.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SWarrantBO.INTCASENUM.clear()).setNullable();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SWarrantBO.CREATEDATETIME.clear()).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(SWarrantBO.TYPE.clear()).setNullable();
	private TypeString orderType = new TypeString("order_type").setFieldDescriptor(SWarrantBO.ORDERTYPE.clear()).setNullable();
	private TypeDate orderDate = new TypeDate("order_date").setFieldDescriptor(SWarrantBO.ORDERDATE.clear()).setNullable();
	private TypeDate issueDate = new TypeDate("issue_date").setFieldDescriptor(SWarrantBO.ISSUEDATE.clear()).setNullable();
	private TypeDate printDate = new TypeDate("print_date").setFieldDescriptor(SWarrantBO.PRINTDATE.clear()).setNullable();
	private TypeDate recallDate = new TypeDate("recall_date").setFieldDescriptor(SWarrantBO.RECALLDATE.clear()).setNullable();
	private TypeDate expDate = new TypeDate("exp_date").setFieldDescriptor(SWarrantBO.EXPDATE.clear()).setNullable();
	private TypeInteger issueJudgeId = new TypeInteger("issue_judge_id").setFieldDescriptor(SWarrantBO.ISSUEJUDGEID.clear()).setNullable();
	private TypeInteger issueClerkId = new TypeInteger("issue_clerk_id").setFieldDescriptor(SWarrantBO.ISSUECLERKID.clear()).setNullable();
	private TypeString issueReasonCode = new TypeString("issue_reason_code").setFieldDescriptor(SWarrantBO.ISSUEREASONCODE.clear()).setNullable();
	private TypeString otherReasonText = new TypeString("other_reason_text").setFieldDescriptor(SWarrantBO.OTHERREASONTEXT.clear()).setNullable();
	private TypeString recallReasonCode = new TypeString("recall_reason_code").setFieldDescriptor(SWarrantBO.RECALLREASONCODE.clear()).setNullable();
	private TypeString swwsStatus = new TypeString("swws_status").setFieldDescriptor(SWarrantBO.SWWSSTATUS.clear()).setNullable();
	private TypeString slcStatus = new TypeString("slc_status").setFieldDescriptor(SWarrantBO.SLCSTATUS.clear()).setNullable();
	private TypeString ncicStatus = new TypeString("ncic_status").setFieldDescriptor(SWarrantBO.NCICSTATUS.clear()).setNullable();
	private TypeString bailFlag = new TypeString("bail_flag").setFieldDescriptor(SWarrantBO.BAILFLAG.clear()).setNullable();
	private TypeBigDecimal bailAmt = new TypeBigDecimal("bail_amt").setFieldDescriptor(SWarrantBO.BAILAMT.clear()).setNullable();
	private TypeString feeFlag = new TypeString("fee_flag").setFieldDescriptor(SWarrantBO.FEEFLAG.clear()).setNullable();
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(SWarrantBO.MEID.clear()).setNullable();
	private TypeInteger recallMeId = new TypeInteger("recall_me_id").setFieldDescriptor(SWarrantBO.RECALLMEID.clear()).setNullable();
	private TypeInteger recallUseridSrl = new TypeInteger("recall_userid_srl").setFieldDescriptor(SWarrantBO.RECALLUSERIDSRL.clear()).setNullable();

	public SWarrantVO() {
		super(SWarrantBO.TABLE, SWarrantBO.SYSTEM, SWarrantBO.CORIS_DISTRICT_DB.setSource("D"), SWarrantBO.CORIS_JUSTICE_DB.setSource("J"), SWarrantBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SWarrantBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SWarrantVO(String source) {
		super(SWarrantBO.TABLE, SWarrantBO.SYSTEM, SWarrantBO.CORIS_DISTRICT_DB.setSource("D"), SWarrantBO.CORIS_JUSTICE_DB.setSource("J"), SWarrantBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SWarrantBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
		this.getPropertyList().add(warrNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(type);
		this.getPropertyList().add(orderType);
		this.getPropertyList().add(orderDate);
		this.getPropertyList().add(issueDate);
		this.getPropertyList().add(printDate);
		this.getPropertyList().add(recallDate);
		this.getPropertyList().add(expDate);
		this.getPropertyList().add(issueJudgeId);
		this.getPropertyList().add(issueClerkId);
		this.getPropertyList().add(issueReasonCode);
		this.getPropertyList().add(otherReasonText);
		this.getPropertyList().add(recallReasonCode);
		this.getPropertyList().add(swwsStatus);
		this.getPropertyList().add(slcStatus);
		this.getPropertyList().add(ncicStatus);
		this.getPropertyList().add(bailFlag);
		this.getPropertyList().add(bailAmt);
		this.getPropertyList().add(feeFlag);
		this.getPropertyList().add(meId);
		this.getPropertyList().add(recallMeId);
		this.getPropertyList().add(recallUseridSrl);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SWarrantBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.ORDERDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.ORDERDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.ISSUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.ISSUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.PRINTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.PRINTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.RECALLDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.RECALLDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SWarrantBO.EXPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SWarrantBO.EXPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}