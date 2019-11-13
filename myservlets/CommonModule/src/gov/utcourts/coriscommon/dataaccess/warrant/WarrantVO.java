package gov.utcourts.coriscommon.dataaccess.warrant;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class WarrantVO extends BaseVO { 

	private TypeInteger warrNum = new TypeInteger("warr_num").setFieldDescriptor(WarrantBO.WARRNUM.clear()).addForeignKey("warrant_retransmission","warr_num",true).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(WarrantBO.INTCASENUM.clear()).addForeignKey("warrant_retransmission","int_case_num",true).addForeignKey("crim_case","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(WarrantBO.PARTYNUM.clear()).addForeignKey("party","party_num",false);
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(WarrantBO.CREATEDATETIME.clear()).setNullable();
	private TypeString type = new TypeString("type").setFieldDescriptor(WarrantBO.TYPE.clear());
	private TypeString orderType = new TypeString("order_type").setFieldDescriptor(WarrantBO.ORDERTYPE.clear());
	private TypeDate orderDate = new TypeDate("order_date").setFieldDescriptor(WarrantBO.ORDERDATE.clear());
	private TypeDate issueDate = new TypeDate("issue_date").setFieldDescriptor(WarrantBO.ISSUEDATE.clear()).setNullable();
	private TypeDate printDate = new TypeDate("print_date").setFieldDescriptor(WarrantBO.PRINTDATE.clear()).setNullable();
	private TypeDate recallDate = new TypeDate("recall_date").setFieldDescriptor(WarrantBO.RECALLDATE.clear()).setNullable();
	private TypeDate expDate = new TypeDate("exp_date").setFieldDescriptor(WarrantBO.EXPDATE.clear()).setNullable();
	private TypeInteger issueJudgeId = new TypeInteger("issue_judge_id").setFieldDescriptor(WarrantBO.ISSUEJUDGEID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeInteger issueClerkId = new TypeInteger("issue_clerk_id").setFieldDescriptor(WarrantBO.ISSUECLERKID.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString issueReasonCode = new TypeString("issue_reason_code").setFieldDescriptor(WarrantBO.ISSUEREASONCODE.clear());
	private TypeString otherReasonText = new TypeString("other_reason_text").setFieldDescriptor(WarrantBO.OTHERREASONTEXT.clear()).setNullable();
	private TypeString recallReasonCode = new TypeString("recall_reason_code").setFieldDescriptor(WarrantBO.RECALLREASONCODE.clear()).setNullable();
	private TypeString swwsStatus = new TypeString("swws_status").setFieldDescriptor(WarrantBO.SWWSSTATUS.clear()).setNullable();
	private TypeString slcStatus = new TypeString("slc_status").setFieldDescriptor(WarrantBO.SLCSTATUS.clear()).setNullable();
	private TypeString ncicStatus = new TypeString("ncic_status").setFieldDescriptor(WarrantBO.NCICSTATUS.clear()).setNullable();
	private TypeString bailFlag = new TypeString("bail_flag").setFieldDescriptor(WarrantBO.BAILFLAG.clear()).setNullable();
	private TypeBigDecimal bailAmt = new TypeBigDecimal("bail_amt").setFieldDescriptor(WarrantBO.BAILAMT.clear()).setNullable();
	private TypeString feeFlag = new TypeString("fee_flag").setFieldDescriptor(WarrantBO.FEEFLAG.clear());
	private TypeInteger meId = new TypeInteger("me_id").setFieldDescriptor(WarrantBO.MEID.clear()).addForeignKey("case_me","me_id",false).setNullable();
	private TypeInteger recallMeId = new TypeInteger("recall_me_id").setFieldDescriptor(WarrantBO.RECALLMEID.clear()).addForeignKey("case_me","me_id",false).setNullable();
	private TypeInteger recallUseridSrl = new TypeInteger("recall_userid_srl").setFieldDescriptor(WarrantBO.RECALLUSERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString mustAppear = new TypeString("must_appear").setFieldDescriptor(WarrantBO.MUSTAPPEAR.clear()).setNullable();
	private TypeInteger orderUseridSrl = new TypeInteger("order_userid_srl").setFieldDescriptor(WarrantBO.ORDERUSERIDSRL.clear()).addForeignKey("personnel","userid_srl",false).setNullable();
	private TypeString transportScope = new TypeString("transport_scope").setFieldDescriptor(WarrantBO.TRANSPORTSCOPE.clear()).setNullable();

	public WarrantVO() {
		super(WarrantBO.TABLE, WarrantBO.SYSTEM, WarrantBO.CORIS_DISTRICT_DB.setSource("D"), WarrantBO.CORIS_JUSTICE_DB.setSource("J"), WarrantBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrantBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public WarrantVO(String source) {
		super(WarrantBO.TABLE, WarrantBO.SYSTEM, WarrantBO.CORIS_DISTRICT_DB.setSource("D"), WarrantBO.CORIS_JUSTICE_DB.setSource("J"), WarrantBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), WarrantBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(warrNum);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
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
		this.getPropertyList().add(mustAppear);
		this.getPropertyList().add(orderUseridSrl);
		this.getPropertyList().add(transportScope);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(WarrantBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.CREATEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantBO.ORDERDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.ORDERDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantBO.ISSUEDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.ISSUEDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantBO.PRINTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.PRINTDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantBO.RECALLDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.RECALLDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(WarrantBO.EXPDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(WarrantBO.EXPDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}