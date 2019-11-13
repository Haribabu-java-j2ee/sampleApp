package gov.utcourts.coriscommon.dataaccess.calnote;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CalNoteVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CalNoteBO.INTCASENUM.clear()).addForeignKey("calendar","int_case_num",false).setAsPrimaryKey();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(CalNoteBO.APPEARDATE.clear()).addForeignKey("calendar","appear_date",false).setAsPrimaryKey();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(CalNoteBO.HEARINGCODE.clear()).addForeignKey("calendar","hearing_code",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CalNoteBO.CREATEDATETIME.clear()).addForeignKey("calendar","create_datetime",false).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(CalNoteBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString note = new TypeString("note").setFieldDescriptor(CalNoteBO.NOTE.clear());

	public CalNoteVO() {
		super(CalNoteBO.TABLE, CalNoteBO.SYSTEM, CalNoteBO.CORIS_DISTRICT_DB.setSource("D"), CalNoteBO.CORIS_JUSTICE_DB.setSource("J"), CalNoteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalNoteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CalNoteVO(String source) {
		super(CalNoteBO.TABLE, CalNoteBO.SYSTEM, CalNoteBO.CORIS_DISTRICT_DB.setSource("D"), CalNoteBO.CORIS_JUSTICE_DB.setSource("J"), CalNoteBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalNoteBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(hearingCode);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(note);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CalNoteBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalNoteBO.APPEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CalNoteBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalNoteBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}