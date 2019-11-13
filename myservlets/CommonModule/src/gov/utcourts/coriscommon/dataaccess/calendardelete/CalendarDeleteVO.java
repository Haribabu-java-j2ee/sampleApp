package gov.utcourts.coriscommon.dataaccess.calendardelete;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CalendarDeleteVO extends BaseVO { 

	private TypeString caseNum = new TypeString("case_num").setFieldDescriptor(CalendarDeleteBO.CASENUM.clear()).setNullable();
	private TypeString locnCode = new TypeString("locn_code").setFieldDescriptor(CalendarDeleteBO.LOCNCODE.clear()).setNullable();
	private TypeString courtType = new TypeString("court_type").setFieldDescriptor(CalendarDeleteBO.COURTTYPE.clear()).setNullable();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(CalendarDeleteBO.APPEARDATE.clear()).setNullable();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(CalendarDeleteBO.HEARINGCODE.clear()).setNullable();

	public CalendarDeleteVO() {
		super(CalendarDeleteBO.TABLE, CalendarDeleteBO.SYSTEM, CalendarDeleteBO.CORIS_DISTRICT_DB.setSource("D"), CalendarDeleteBO.CORIS_JUSTICE_DB.setSource("J"), CalendarDeleteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalendarDeleteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CalendarDeleteVO(String source) {
		super(CalendarDeleteBO.TABLE, CalendarDeleteBO.SYSTEM, CalendarDeleteBO.CORIS_DISTRICT_DB.setSource("D"), CalendarDeleteBO.CORIS_JUSTICE_DB.setSource("J"), CalendarDeleteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalendarDeleteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(caseNum);
		this.getPropertyList().add(locnCode);
		this.getPropertyList().add(courtType);
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(hearingCode);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CalendarDeleteBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalendarDeleteBO.APPEARDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}