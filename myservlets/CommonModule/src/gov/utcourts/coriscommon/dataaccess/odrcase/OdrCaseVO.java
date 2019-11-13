package gov.utcourts.coriscommon.dataaccess.odrcase;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeBigDecimal;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class OdrCaseVO extends BaseVO { 

	private TypeInteger odrEntryId = new TypeInteger("odr_entry_id").setFieldDescriptor(OdrCaseBO.ODRENTRYID.clear()).setAsPrimaryKey();
	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(OdrCaseBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate entryDatetime = new TypeDate("entry_datetime").setFieldDescriptor(OdrCaseBO.ENTRYDATETIME.clear());
	private TypeBigDecimal claimAmount = new TypeBigDecimal("claim_amount").setFieldDescriptor(OdrCaseBO.CLAIMAMOUNT.clear()).setNullable();
	private TypeInteger nextTransferCount = new TypeInteger("next_transfer_count").setFieldDescriptor(OdrCaseBO.NEXTTRANSFERCOUNT.clear());
	private TypeInteger odrCaseId = new TypeInteger("odr_case_id").setFieldDescriptor(OdrCaseBO.ODRCASEID.clear()).setNullable();
	private TypeString odrStatus = new TypeString("odr_status").setFieldDescriptor(OdrCaseBO.ODRSTATUS.clear()).setNullable();
	private TypeDate statusDatetime = new TypeDate("status_datetime").setFieldDescriptor(OdrCaseBO.STATUSDATETIME.clear()).setNullable();
	private TypeString odrExempt = new TypeString("odr_exempt").setFieldDescriptor(OdrCaseBO.ODREXEMPT.clear()).setNullable();
	private TypeString odrCalendar = new TypeString("odr_calendar").setFieldDescriptor(OdrCaseBO.ODRCALENDAR.clear()).setNullable();

	public OdrCaseVO() {
		super(OdrCaseBO.TABLE, OdrCaseBO.SYSTEM, OdrCaseBO.CORIS_DISTRICT_DB.setSource("D"), OdrCaseBO.CORIS_JUSTICE_DB.setSource("J"), OdrCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public OdrCaseVO(String source) {
		super(OdrCaseBO.TABLE, OdrCaseBO.SYSTEM, OdrCaseBO.CORIS_DISTRICT_DB.setSource("D"), OdrCaseBO.CORIS_JUSTICE_DB.setSource("J"), OdrCaseBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), OdrCaseBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(odrEntryId);
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(entryDatetime);
		this.getPropertyList().add(claimAmount);
		this.getPropertyList().add(nextTransferCount);
		this.getPropertyList().add(odrCaseId);
		this.getPropertyList().add(odrStatus);
		this.getPropertyList().add(statusDatetime);
		this.getPropertyList().add(odrExempt);
		this.getPropertyList().add(odrCalendar);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(OdrCaseBO.ENTRYDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OdrCaseBO.ENTRYDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(OdrCaseBO.STATUSDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(OdrCaseBO.STATUSDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}