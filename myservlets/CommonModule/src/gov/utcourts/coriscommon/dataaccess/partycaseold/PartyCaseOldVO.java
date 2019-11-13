package gov.utcourts.coriscommon.dataaccess.partycaseold;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyCaseOldVO extends BaseVO { 

	private TypeString actionType = new TypeString("action_type").setFieldDescriptor(PartyCaseOldBO.ACTIONTYPE.clear());
	private TypeInteger useridSrl = new TypeInteger("userid_srl").setFieldDescriptor(PartyCaseOldBO.USERIDSRL.clear());
	private TypeDate changeDatetime = new TypeDate("change_datetime").setFieldDescriptor(PartyCaseOldBO.CHANGEDATETIME.clear());
	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(PartyCaseOldBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(PartyCaseOldBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(PartyCaseOldBO.COURTTYPE.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PartyCaseOldBO.INTCASENUM.clear());
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PartyCaseOldBO.PARTYNUM.clear());
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(PartyCaseOldBO.PARTYCODE.clear());
	private TypeString otn = new TypeString("otn").setFieldDescriptor(PartyCaseOldBO.OTN.clear()).setNullable();
	private TypeDate arrestDate = new TypeDate("arrest_date").setFieldDescriptor(PartyCaseOldBO.ARRESTDATE.clear()).setNullable();
	private TypeString bookingNum = new TypeString("booking_num").setFieldDescriptor(PartyCaseOldBO.BOOKINGNUM.clear()).setNullable();
	private TypeBigDecimal setbailAmt = new TypeBigDecimal("setbail_amt").setFieldDescriptor(PartyCaseOldBO.SETBAILAMT.clear()).setNullable();
	private TypeBigDecimal setbondAmt = new TypeBigDecimal("setbond_amt").setFieldDescriptor(PartyCaseOldBO.SETBONDAMT.clear()).setNullable();
	private TypeString cashBailFlag = new TypeString("cash_bail_flag").setFieldDescriptor(PartyCaseOldBO.CASHBAILFLAG.clear());
	private TypeString proSe = new TypeString("pro_se").setFieldDescriptor(PartyCaseOldBO.PROSE.clear()).setNullable();
	private TypeInteger relatedPartyNum = new TypeInteger("related_party_num").setFieldDescriptor(PartyCaseOldBO.RELATEDPARTYNUM.clear()).setNullable();
	private TypeString warrStatus = new TypeString("warr_status").setFieldDescriptor(PartyCaseOldBO.WARRSTATUS.clear()).setNullable();
	private TypeString safeguarded = new TypeString("safeguarded").setFieldDescriptor(PartyCaseOldBO.SAFEGUARDED.clear()).setNullable();
	private TypeString custodialParent = new TypeString("custodial_parent").setFieldDescriptor(PartyCaseOldBO.CUSTODIALPARENT.clear()).setNullable();
	private TypeString otnAvailable = new TypeString("otn_available").setFieldDescriptor(PartyCaseOldBO.OTNAVAILABLE.clear()).setNullable();
	private TypeString dismissed = new TypeString("dismissed").setFieldDescriptor(PartyCaseOldBO.DISMISSED.clear()).setNullable();
	private TypeString probateRptExcuse = new TypeString("probate_rpt_excuse").setFieldDescriptor(PartyCaseOldBO.PROBATERPTEXCUSE.clear()).setNullable();
	private TypeString odrExempt = new TypeString("odr_exempt").setFieldDescriptor(PartyCaseOldBO.ODREXEMPT.clear()).setNullable();

	public PartyCaseOldVO() {
		super(PartyCaseOldBO.TABLE, PartyCaseOldBO.SYSTEM, PartyCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), PartyCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), PartyCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyCaseOldVO(String source) {
		super(PartyCaseOldBO.TABLE, PartyCaseOldBO.SYSTEM, PartyCaseOldBO.CORIS_DISTRICT_DB.setSource("D"), PartyCaseOldBO.CORIS_JUSTICE_DB.setSource("J"), PartyCaseOldBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyCaseOldBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(actionType);
		this.getPropertyList().add(useridSrl);
		this.getPropertyList().add(changeDatetime);
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(partyNum);
		this.getPropertyList().add(partyCode);
		this.getPropertyList().add(otn);
		this.getPropertyList().add(arrestDate);
		this.getPropertyList().add(bookingNum);
		this.getPropertyList().add(setbailAmt);
		this.getPropertyList().add(setbondAmt);
		this.getPropertyList().add(cashBailFlag);
		this.getPropertyList().add(proSe);
		this.getPropertyList().add(relatedPartyNum);
		this.getPropertyList().add(warrStatus);
		this.getPropertyList().add(safeguarded);
		this.getPropertyList().add(custodialParent);
		this.getPropertyList().add(otnAvailable);
		this.getPropertyList().add(dismissed);
		this.getPropertyList().add(probateRptExcuse);
		this.getPropertyList().add(odrExempt);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(PartyCaseOldBO.CHANGEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyCaseOldBO.CHANGEDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(PartyCaseOldBO.ARRESTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyCaseOldBO.ARRESTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}