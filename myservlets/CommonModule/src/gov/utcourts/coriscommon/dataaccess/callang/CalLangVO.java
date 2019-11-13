package gov.utcourts.coriscommon.dataaccess.callang;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class CalLangVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(CalLangBO.INTCASENUM.clear()).addForeignKey("calendar","int_case_num",false).setAsPrimaryKey();
	private TypeDate appearDate = new TypeDate("appear_date").setFieldDescriptor(CalLangBO.APPEARDATE.clear()).addForeignKey("calendar","appear_date",false).setAsPrimaryKey();
	private TypeString hearingCode = new TypeString("hearing_code").setFieldDescriptor(CalLangBO.HEARINGCODE.clear()).addForeignKey("calendar","hearing_code",false).setAsPrimaryKey();
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(CalLangBO.CREATEDATETIME.clear()).addForeignKey("calendar","create_datetime",false).setAsPrimaryKey();
	private TypeInteger langId = new TypeInteger("lang_id").setFieldDescriptor(CalLangBO.LANGID.clear()).addForeignKey("language","lang_id",false).setAsPrimaryKey();
	private TypeInteger count = new TypeInteger("count").setFieldDescriptor(CalLangBO.COUNT.clear());

	public CalLangVO() {
		super(CalLangBO.TABLE, CalLangBO.SYSTEM, CalLangBO.CORIS_DISTRICT_DB.setSource("D"), CalLangBO.CORIS_JUSTICE_DB.setSource("J"), CalLangBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalLangBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public CalLangVO(String source) {
		super(CalLangBO.TABLE, CalLangBO.SYSTEM, CalLangBO.CORIS_DISTRICT_DB.setSource("D"), CalLangBO.CORIS_JUSTICE_DB.setSource("J"), CalLangBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), CalLangBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(appearDate);
		this.getPropertyList().add(hearingCode);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(langId);
		this.getPropertyList().add(count);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(CalLangBO.APPEARDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalLangBO.APPEARDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(CalLangBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(CalLangBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}