package gov.utcourts.coriscommon.dataaccess.spartycase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SPartyCaseVO extends BaseVO { 

	private TypeInteger sIntCaseNum = new TypeInteger("s_int_case_num").setFieldDescriptor(SPartyCaseBO.SINTCASENUM.clear()).setNullable().setAsPrimaryKey();
	private TypeInteger sUseridSrl = new TypeInteger("s_userid_srl").setFieldDescriptor(SPartyCaseBO.SUSERIDSRL.clear()).setNullable();
	private TypeDate sDatetime = new TypeDate("s_datetime").setFieldDescriptor(SPartyCaseBO.SDATETIME.clear()).setNullable();
	private TypeInteger sMeId = new TypeInteger("s_me_id").setFieldDescriptor(SPartyCaseBO.SMEID.clear()).setNullable();
	private TypeString sOperation = new TypeString("s_operation").setFieldDescriptor(SPartyCaseBO.SOPERATION.clear()).setNullable();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SPartyCaseBO.INTCASENUM.clear()).setNullable();
	private TypeInteger partyNum = new TypeInteger("party_num").setFieldDescriptor(SPartyCaseBO.PARTYNUM.clear()).setNullable().setAsPrimaryKey();
	private TypeString partyCode = new TypeString("party_code").setFieldDescriptor(SPartyCaseBO.PARTYCODE.clear()).setNullable().setAsPrimaryKey();
	private TypeString otn = new TypeString("otn").setFieldDescriptor(SPartyCaseBO.OTN.clear()).setNullable();
	private TypeDate arrestDate = new TypeDate("arrest_date").setFieldDescriptor(SPartyCaseBO.ARRESTDATE.clear()).setNullable();
	private TypeString bookingNum = new TypeString("booking_num").setFieldDescriptor(SPartyCaseBO.BOOKINGNUM.clear()).setNullable();
	private TypeBigDecimal setbailAmt = new TypeBigDecimal("setbail_amt").setFieldDescriptor(SPartyCaseBO.SETBAILAMT.clear()).setNullable();
	private TypeBigDecimal setbondAmt = new TypeBigDecimal("setbond_amt").setFieldDescriptor(SPartyCaseBO.SETBONDAMT.clear()).setNullable();
	private TypeString cashBailFlag = new TypeString("cash_bail_flag").setFieldDescriptor(SPartyCaseBO.CASHBAILFLAG.clear()).setNullable();
	private TypeString proSe = new TypeString("pro_se").setFieldDescriptor(SPartyCaseBO.PROSE.clear()).setNullable();
	private TypeInteger relatedPartyNum = new TypeInteger("related_party_num").setFieldDescriptor(SPartyCaseBO.RELATEDPARTYNUM.clear()).setNullable();
	private TypeString warrStatus = new TypeString("warr_status").setFieldDescriptor(SPartyCaseBO.WARRSTATUS.clear()).setNullable();
	private TypeString otnAvailable = new TypeString("otn_available").setFieldDescriptor(SPartyCaseBO.OTNAVAILABLE.clear()).setNullable();

	public SPartyCaseVO() {
		super(SPartyCaseBO.TABLE, SPartyCaseBO.SYSTEM, SPartyCaseBO.CORIS_DISTRICT_DB.setSource("D"), SPartyCaseBO.CORIS_JUSTICE_DB.setSource("J"), SPartyCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SPartyCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SPartyCaseVO(String source) {
		super(SPartyCaseBO.TABLE, SPartyCaseBO.SYSTEM, SPartyCaseBO.CORIS_DISTRICT_DB.setSource("D"), SPartyCaseBO.CORIS_JUSTICE_DB.setSource("J"), SPartyCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SPartyCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(sIntCaseNum);
		this.getPropertyList().add(sUseridSrl);
		this.getPropertyList().add(sDatetime);
		this.getPropertyList().add(sMeId);
		this.getPropertyList().add(sOperation);
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
		this.getPropertyList().add(otnAvailable);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SPartyCaseBO.SDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SPartyCaseBO.SDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SPartyCaseBO.ARRESTDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(SPartyCaseBO.ARRESTDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}