package gov.utcourts.coriscommon.dataaccess.summaryevent;

import gov.utcourts.courtscommon.dataaccess.base.BaseVO;
import gov.utcourts.courtscommon.dataaccess.types.FieldType;
import gov.utcourts.courtscommon.dataaccess.types.TypeDate;
import gov.utcourts.courtscommon.dataaccess.types.TypeInteger;
import gov.utcourts.courtscommon.dataaccess.types.TypeString;

import java.text.DateFormat;
import java.util.List;

// SYSTEM GENERATED CLASS - DO NOT MODIFY
public class SummaryEventVO extends BaseVO { 

	private TypeInteger intCaseNum = new TypeInteger("int_case_num").setFieldDescriptor(SummaryEventBO.INTCASENUM.clear()).addForeignKey("kase","int_case_num",false);
	private TypeDate eventDatetime = new TypeDate("event_datetime").setFieldDescriptor(SummaryEventBO.EVENTDATETIME.clear());
	private TypeDate createDatetime = new TypeDate("create_datetime").setFieldDescriptor(SummaryEventBO.CREATEDATETIME.clear());
	private TypeString funcId = new TypeString("func_id").setFieldDescriptor(SummaryEventBO.FUNCID.clear());
	private TypeString displayFlag = new TypeString("display_flag").setFieldDescriptor(SummaryEventBO.DISPLAYFLAG.clear());
	private TypeString removedFlag = new TypeString("removed_flag").setFieldDescriptor(SummaryEventBO.REMOVEDFLAG.clear());
	private TypeString descr = new TypeString("descr").setFieldDescriptor(SummaryEventBO.DESCR.clear());
	private TypeString key1 = new TypeString("key_1").setFieldDescriptor(SummaryEventBO.KEY1.clear()).setNullable();
	private TypeString key2 = new TypeString("key_2").setFieldDescriptor(SummaryEventBO.KEY2.clear()).setNullable();
	private TypeString key3 = new TypeString("key_3").setFieldDescriptor(SummaryEventBO.KEY3.clear()).setNullable();
	private TypeString key4 = new TypeString("key_4").setFieldDescriptor(SummaryEventBO.KEY4.clear()).setNullable();

	public SummaryEventVO() {
		super(SummaryEventBO.TABLE, SummaryEventBO.SYSTEM, SummaryEventBO.CORIS_DISTRICT_DB.setSource("D"), SummaryEventBO.CORIS_JUSTICE_DB.setSource("J"), SummaryEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SummaryEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
	}

	public SummaryEventVO(String source) {
		super(SummaryEventBO.TABLE, SummaryEventBO.SYSTEM, SummaryEventBO.CORIS_DISTRICT_DB.setSource("D"), SummaryEventBO.CORIS_JUSTICE_DB.setSource("J"), SummaryEventBO.CORIS_DISTRICT_READONLY_DB.setSource("D_READONLY"), SummaryEventBO.CORIS_JUSTICE_READONLY_DB.setSource("J_READONLY"));
		init();
		setSource(source);
	}

	private void init() {
		this.getPropertyList().add(intCaseNum);
		this.getPropertyList().add(eventDatetime);
		this.getPropertyList().add(createDatetime);
		this.getPropertyList().add(funcId);
		this.getPropertyList().add(displayFlag);
		this.getPropertyList().add(removedFlag);
		this.getPropertyList().add(descr);
		this.getPropertyList().add(key1);
		this.getPropertyList().add(key2);
		this.getPropertyList().add(key3);
		this.getPropertyList().add(key4);
	}
	
	@SuppressWarnings("rawtypes")
	public void setDateFormatters(List<FieldType> fieldTypes) {
		try {
			((TypeDate) this.getPropertyList().get(SummaryEventBO.EVENTDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SummaryEventBO.EVENTDATETIME.getDateFormatterKey()));
			((TypeDate) this.getPropertyList().get(SummaryEventBO.CREATEDATETIME.getPosition())).setDateFormat((DateFormat) super.getAttribute(SummaryEventBO.CREATEDATETIME.getDateFormatterKey()));
		} catch (Exception e) {
			// do nothing
		}
	}

}