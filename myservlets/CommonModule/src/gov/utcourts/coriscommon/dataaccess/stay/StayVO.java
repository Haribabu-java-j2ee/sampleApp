package gov.utcourts.coriscommon.dataaccess.stay;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class StayVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(StayBO.INTCASENUM.clear()).setAsPrimaryKey();
	private TypeInteger seq = new TypeInteger("seq").setFieldDescriptor(StayBO.SEQ.clear()).setAsPrimaryKey();
	private TypeString stayCode = new TypeString("stay_code").setFieldDescriptor(StayBO.STAYCODE.clear()).addForeignKey("stay_type","stay_code",false);
	private TypeDate beginDate = new TypeDate("begin_date").setFieldDescriptor(StayBO.BEGINDATE.clear());
	private TypeDate endDate = new TypeDate("end_date").setFieldDescriptor(StayBO.ENDDATE.clear()).setNullable();
	private TypeString noteText = new TypeString("note_text").setFieldDescriptor(StayBO.NOTETEXT.clear()).setNullable();

	public StayVO() {
		super(StayBO.TABLE, StayBO.SYSTEM, StayBO.CORIS_DISTRICT_DB.setSource("D"), StayBO.CORIS_JUSTICE_DB.setSource("J"), StayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public StayVO(String source) {
		super(StayBO.TABLE, StayBO.SYSTEM, StayBO.CORIS_DISTRICT_DB.setSource("D"), StayBO.CORIS_JUSTICE_DB.setSource("J"), StayBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), StayBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(seq);
		this.getPropertyList().add(stayCode);
		this.getPropertyList().add(beginDate);
		this.getPropertyList().add(endDate);
		this.getPropertyList().add(noteText);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(StayBO.BEGINDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(StayBO.BEGINDATE.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(StayBO.ENDDATE.getPosition())).setDateFormat((DateFormat) super.getAttribute(StayBO.ENDDATE.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}