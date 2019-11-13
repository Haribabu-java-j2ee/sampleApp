package gov.utcourts.coriscommon.dataaccess.partycase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class PartyCaseVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(PartyCaseBO.INTCASENUM.clear()).addForeignKey("atty_party","int_case_num",false).addForeignKey("charge","int_case_num",false).addForeignKey("judgment_creditor","int_case_num",false).addForeignKey("judgment_debtor","int_case_num",false).addForeignKey("kase","int_case_num",false).setAsPrimaryKey();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(PartyCaseBO.PARTYNUM.clear()).addForeignKey("atty_party","party_num",false).addForeignKey("charge","party_num",false).addForeignKey("judgment_creditor","party_num",false).addForeignKey("judgment_debtor","party_num",false).addForeignKey("party","party_num",false).setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(PartyCaseBO.PARTYCODE.clear()).addForeignKey("atty_party","party_code",false).addForeignKey("charge","party_code",false).addForeignKey("judgment_creditor","party_code",false).addForeignKey("judgment_debtor","party_code",false).addForeignKey("party_type","party_code",false).setAsPrimaryKey();
	private TypeString otn = new TypeString("otn").setFieldDescriptor(PartyCaseBO.OTN.clear()).setNullable();
	private TypeDate arrestDate = new TypeDate("arrest_date").setFieldDescriptor(PartyCaseBO.ARRESTDATE.clear()).setNullable();
	private TypeString bookingNum = new TypeString("booking_num").setFieldDescriptor(PartyCaseBO.BOOKINGNUM.clear()).setNullable();
	private TypeBigDecimal setbailAmt = new TypeBigDecimal("setbail_amt").setFieldDescriptor(PartyCaseBO.SETBAILAMT.clear()).setNullable();
	private TypeBigDecimal setbondAmt = new TypeBigDecimal("setbond_amt").setFieldDescriptor(PartyCaseBO.SETBONDAMT.clear()).setNullable();
	private TypeString cashBailFlag = new TypeString("cash_bail_flag").setFieldDescriptor(PartyCaseBO.CASHBAILFLAG.clear());
	private TypeString proSe = new TypeString("pro_se").setFieldDescriptor(PartyCaseBO.PROSE.clear()).setNullable();
	private TypeInteger relatedPartyNum = new TypeInteger("related_party_num").setFieldDescriptor(PartyCaseBO.RELATEDPARTYNUM.clear()).setNullable();
	private TypeString warrStatus = new TypeString("warr_status").setFieldDescriptor(PartyCaseBO.WARRSTATUS.clear()).setNullable();
	private TypeString safeguarded = new TypeString("safeguarded").setFieldDescriptor(PartyCaseBO.SAFEGUARDED.clear()).setNullable();
	private TypeString custodialParent = new TypeString("custodial_parent").setFieldDescriptor(PartyCaseBO.CUSTODIALPARENT.clear()).setNullable();
	private TypeString otnAvailable = new TypeString("otn_available").setFieldDescriptor(PartyCaseBO.OTNAVAILABLE.clear()).setNullable();
	private TypeString dismissed = new TypeString("dismissed").setFieldDescriptor(PartyCaseBO.DISMISSED.clear()).setNullable();
	private TypeString probateRptExcuse = new TypeString("probate_rpt_excuse").setFieldDescriptor(PartyCaseBO.PROBATERPTEXCUSE.clear()).setNullable();
	private TypeString odrExempt = new TypeString("odr_exempt").setFieldDescriptor(PartyCaseBO.ODREXEMPT.clear()).setNullable();

	public PartyCaseVO() {
		super(PartyCaseBO.TABLE, PartyCaseBO.SYSTEM, PartyCaseBO.CORIS_DISTRICT_DB.setSource("D"), PartyCaseBO.CORIS_JUSTICE_DB.setSource("J"), PartyCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public PartyCaseVO(String source) {
		super(PartyCaseBO.TABLE, PartyCaseBO.SYSTEM, PartyCaseBO.CORIS_DISTRICT_DB.setSource("D"), PartyCaseBO.CORIS_JUSTICE_DB.setSource("J"), PartyCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), PartyCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
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
			((TypeDate) this.getPropertyList().get(PartyCaseBO.ARRESTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(PartyCaseBO.ARRESTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}